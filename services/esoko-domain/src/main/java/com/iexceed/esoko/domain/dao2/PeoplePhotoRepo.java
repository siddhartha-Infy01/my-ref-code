package com.iexceed.esoko.domain.dao2;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity2.PeoplePhoto;
import com.iexceed.esoko.domain.entity2.PeoplePhotoPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class PeoplePhotoRepo extends
		AbstractRepoForEntity<PeoplePhoto, PeoplePhotoPK> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(PeoplePhoto pplPhoto, PeoplePhotoPK photoPk) {
		log.info("Inside PeoplePhotoRepo -> exists");
		return super.exists(PeoplePhoto.class, photoPk);
	}

	@SuppressWarnings("unchecked")
	public PeoplePhoto merge(PeoplePhoto pplPhoto) {
		log.info("Inside PeoplePhotoRepo -> merge");
		return super.merge(pplPhoto);
	}

	@SuppressWarnings("unchecked")
	public PeoplePhoto save(PeoplePhoto pplPhoto) {
		log.info("Inside PeoplePhotoRepo -> save");
		return super.save(pplPhoto);
	}

	public PeoplePhoto findOne(PeoplePhoto pplPhoto, PeoplePhotoPK photoPk) {
		log.info("Inside PeoplePhotoRepo -> findOne");
		return super.findOne(PeoplePhoto.class, photoPk);
	}
}
