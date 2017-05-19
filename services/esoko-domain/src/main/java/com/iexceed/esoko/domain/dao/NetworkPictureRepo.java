package com.iexceed.esoko.domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.NetworkPicture;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
public class NetworkPictureRepo extends
		AbstractRepoForEntity<NetworkPicture, String> {

	public static Logger log = LoggerUtils.getLogger();

	public boolean exists(NetworkPicture nwpic, String networkId) {
		boolean exists = super.exists(NetworkPicture.class, networkId);
		return exists;
	}

	public NetworkPicture merge(NetworkPicture nwpic) {
		nwpic.setContent(Utils.encodeImage(nwpic.getContent()));
		nwpic = super.merge(nwpic);
		return nwpic;
	}

	public NetworkPicture queryNwrPic(String networkId) {
		log.info("Entered network picture ");
		log.info("networkid::" + networkId);
		NetworkPicture nwpic;

		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryNwrPic"), NetworkPicture.class);
			query.setParameter(1, networkId);

			nwpic = (NetworkPicture) query.getSingleResult();

			nwpic.setContent(Utils.decodeImage(nwpic.getContent()));
		} catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return nwpic;

	}

	public NetworkPicture save(NetworkPicture nwpic) {
		nwpic.setContent(Utils.encodeImage(nwpic.getContent()));
		super.save(nwpic);
		return nwpic;

	}
}
