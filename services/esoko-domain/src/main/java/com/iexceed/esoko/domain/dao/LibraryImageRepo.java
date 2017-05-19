package com.iexceed.esoko.domain.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.LibraryFile;
import com.iexceed.esoko.domain.entity.LibraryImage;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Repository
public class LibraryImageRepo extends
		AbstractRepoForEntity<LibraryImage, Integer> {
	public static Logger log = LoggerUtils.getLogger();

	public void createLibraryImage(LibraryImage libraryImage) {
		super.save(libraryImage);
	}

	public LibraryImage queryNwrPic(String libId) {
		log.info("Entered network picture ");
		log.info("libId::" + libId);
		LibraryImage libpic;

		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryLibPic"), LibraryImage.class);
			query.setParameter(1, libId);

			libpic = (LibraryImage) query.getSingleResult();

			libpic.setContent(Utils.decodeImage(libpic.getContent()));
		} catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return libpic;

	}

	public LibraryImage findOne(LibraryImage library, String uploadId) {
		log.info("Inside LibraryImageRepo -> findOne");
		return super.findOne(LibraryImage.class, Integer.parseInt(uploadId));
	}

	public boolean exists(LibraryImage people, String uploadId) {
		log.info("Inside LibraryImageRepo -> exists");
		return super.exists(LibraryImage.class, Integer.parseInt(uploadId));
	}

	public void deleteLibImgByLibId(String libId) {
		log.info("Inside LibraryImageRepo -> deleteLibImgByLibId");
		log.debug("Lib id:" + libId);
		try {
			Query query = entityManager.createNativeQuery(
					getSql("deleteLibImgByLibId"), LibraryImage.class);
			query.setParameter(1, libId);
			int count = query.executeUpdate();
		} catch (NoResultException e) {
		}
	}
}
