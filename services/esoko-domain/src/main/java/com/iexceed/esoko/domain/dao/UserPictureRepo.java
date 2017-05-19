package com.iexceed.esoko.domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class UserPictureRepo extends
		AbstractRepoForEntity<UserPicture, Integer> {
	public static Logger log = LoggerUtils.getLogger();

	public UserPicture queryUserPic(String userId) {
		log.info("Entered query picture of user");
		UserPicture usrpic;
		log.info("user::" + userId);
		try {

			Query query = entityManager.createNativeQuery(
					getSql("queryUserPic"), UserPicture.class);
			query.setParameter(1, userId);
			usrpic = (UserPicture) query.getSingleResult();
			usrpic.setContent(Utils.decodeImage(usrpic.getContent()));
		} catch (NoResultException e) {
			return null;
		}
		entityManager.close();

		return usrpic;

	}

	public UserPicture save(UserPicture usrpic) {
		usrpic.setContent(Utils.encodeImage(usrpic.getContent()));
		super.save(usrpic);
		return usrpic;

	}

	public UserPicture findOne(UserPicture entity , Integer userPicId) {
		UserPicture pic = super.findOne(UserPicture.class , userPicId);
		pic.setContent(Utils.decodeImage(pic.getContent()));
		return pic;
	}

	public boolean exists(UserPicture entity, Integer id) {
		return super.exists(UserPicture.class, id);

	}

	public UserPicture merge(UserPicture entity) {
		entity.setContent(Utils.encodeImage(entity.getContent()));
		return super.merge(entity);
	}
}
