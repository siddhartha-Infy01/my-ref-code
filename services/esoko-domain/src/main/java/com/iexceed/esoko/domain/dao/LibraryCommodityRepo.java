package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.LibraryCommodity;
import com.iexceed.esoko.domain.entity.LibraryImage;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Repository
public class LibraryCommodityRepo extends
		AbstractRepoForEntity<LibraryCommodity, Integer> {
	public static Logger log = LoggerUtils.getLogger();

	public void createLibraryCommodity(LibraryCommodity libraryCommodity) {
		log.info("Inside LibraryCommodityRepo -> createLibraryCommodity");
		super.save(libraryCommodity);
	}

	public LibraryCommodity findOne(LibraryCommodity library, String uploadId) {
		log.info("Inside LibraryCommodityRepo -> findOne");
		return super
				.findOne(LibraryCommodity.class, Integer.parseInt(uploadId));
	}

	public boolean exists(LibraryCommodity people, String uploadId) {
		log.info("Inside LibraryCommodityRepo -> exists");
		return super.exists(LibraryCommodity.class, Integer.parseInt(uploadId));
	}

}
