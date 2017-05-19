package com.iexceed.esoko.domain.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Library;
import com.iexceed.esoko.domain.entity.LibraryFile;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Repository
public class LibraryFileRepo extends
		AbstractRepoForEntity<LibraryFile, Integer> {
	public static Logger log = LoggerUtils.getLogger();

	public void createLibraryFile(LibraryFile libraryFile) {
		log.info("Inside LibraryFileRepo -> createLibraryFile");
		super.save(libraryFile);
	}

	public LibraryFile findOne(LibraryFile library, String uploadId) {
		log.info("Inside LibraryFileRepo -> findOne");
		return super.findOne(LibraryFile.class, Integer.parseInt(uploadId));
	}

	public boolean exists(LibraryFile people, String uploadId) {
		log.info("Inside LibraryFileRepo -> exists");
		return super.exists(LibraryFile.class, Integer.parseInt(uploadId));
	}
}
