package com.iexceed.esoko.engine.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class JdbcConnector {

	private static Logger log = LoggerUtils.getSmartGroupLogger();

	public Connection getJdbcConnection(String jndiTag) {
		Connection connection = null;
		try {
			Context initialContext = new InitialContext();
			DataSource ds = (DataSource) initialContext
					.lookup("java:jboss/jdbc/" + jndiTag);
			if (ds != null) {
				// connection = DataSourceUtils.getConnection(ds);
				connection = ds.getConnection();
				log.info("Connection established");
			} else {
				log.debug("Failed to lookup datasource");
			}
		} catch (SQLException e) {
			log.debug(Utils.getStackTrace(e));
		} catch (NamingException e) {
			log.debug(Utils.getStackTrace(e));
		}
		return connection;
	}
}
