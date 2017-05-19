package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.LibraryCommodity;
import com.iexceed.esoko.domain.entity.LibraryLocation;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Repository
public class LibraryLocationRepo extends
		AbstractRepoForEntity<LibraryLocation, Integer> {
	public static Logger log = LoggerUtils.getLogger();

	public void createLibraryLocation(LibraryLocation libraryLocation) {
		log.info("Inside LibraryLocationRepo -> findOne");
		super.save(libraryLocation);
	}

	public LibraryLocation findOne(LibraryLocation library, String uploadId) {
		log.info("Inside LibraryLocationRepo -> findOne");
		return super.findOne(LibraryLocation.class, Integer.parseInt(uploadId));
	}

	public boolean exists(LibraryLocation people, String uploadId) {
		log.info("Inside LibraryLocationRepo -> exists");
		return super.exists(LibraryLocation.class, Integer.parseInt(uploadId));
	}
}
