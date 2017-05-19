package com.iexceed.esoko.domain.dao2;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Profile_Sharing_type;
import com.iexceed.esoko.domain.entity.Profile_Sharing_typePK;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class ProfileSharingTypeRepo extends
		AbstractRepoForEntity<Profile_Sharing_type, Profile_Sharing_typePK> {

	public Profile_Sharing_type querySharedEditableFields(String fromShare,
			String toShare) {
		log.debug("FromShare Nwk: " + fromShare);
		log.debug("ToShare Nwk: " + toShare);
		Profile_Sharing_type profile = null;
		Query query = entityManager.createNativeQuery(
				getSql("querySharedEditableFields"), Profile_Sharing_type.class);
		query.setParameter(1, fromShare);
		query.setParameter(2, toShare);
		query.setParameter(3, fromShare);
		try {
			profile = (Profile_Sharing_type) query.getSingleResult();
		} catch (NoResultException e) {
			log.error(Utils.getStackTrace(e));
		}
		return profile;
	}

}
