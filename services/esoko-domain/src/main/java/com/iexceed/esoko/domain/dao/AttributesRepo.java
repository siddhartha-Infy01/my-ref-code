package com.iexceed.esoko.domain.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Attribute;
import com.iexceed.esoko.domain.entity.Type;
@Component
public class AttributesRepo extends AbstractRepoForEntity<Attribute, String> {

	public List<Attribute> queryAllAttributes() {
		log.info("Entered queryall available attributes");

		Query query = entityManager.createNativeQuery(
				getSql("findAllAttributes"), Attribute.class);
		return query.getResultList();
	}

}
