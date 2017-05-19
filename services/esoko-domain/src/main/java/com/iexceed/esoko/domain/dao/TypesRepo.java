package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Type;

@Component
public class TypesRepo extends AbstractRepoForEntity<Type, String> {

	public List<Type> queryAllTypes() {
		log.info("Query all attributes");

		Query query = entityManager.createNativeQuery(getSql("findAllTypes"),
				Type.class);
		return query.getResultList();
	}

	public Type save(Type type) {
		log.info("Inside TypesRepo -> save");
		return super.save(type);
	}

}
