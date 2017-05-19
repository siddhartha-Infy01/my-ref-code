package com.iexceed.esoko.domain.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.iexceed.esoko.engine.resources.IResourceLocator;

public abstract class AbstractRepository<T, ID extends Serializable> implements
		IRepository<T, ID> {

	@Autowired
	IResourceLocator resourceLocator;

	@PersistenceContext(unitName = "esoko-domain")
	protected EntityManager entityManager;

	private static Map<String, String> queryMap = new HashMap<String, String>();

	/**
	 * Loads the SQL file from the classpath.
	 * 
	 * @param sqlFile
	 *            Name of the SQL file.
	 * @return Contents of the SQL file.
	 */
	protected String getSql(String sqlFile) {
		InputStream inputStream = null;

		String query = queryMap.get(sqlFile);

		try {
			if (query == null) {
				inputStream = resourceLocator.getResourceFromClasspath("sql/"
						+ sqlFile + ".sql");
				query = IOUtils.toString(inputStream);
				queryMap.put(sqlFile, query);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(inputStream);
		}
		return query;
	}

}
