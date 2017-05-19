package com.iexceed.esoko.domain.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Library;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
public class LibraryRepo extends AbstractRepoForEntity<Library, String> {
	public static Logger log = LoggerUtils.getLogger();

	public List<Map<String, Object>> querynews(String networkId, String userId) {

		log.info("query news");
		log.debug("userData::" + userId);
		log.debug("networkid::" + networkId);
		Query query = entityManager.createNativeQuery("{call queryNews(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("title", obj[i]);

				} else if (i == 1) {
					recordMap.put("story", obj[i]);
				} else if (i == 2) {
					recordMap.put("published_ts", obj[i]);
				} else if (i == 3) {
					recordMap.put("source_url", obj[i]);
				}

				else if (i == 4) {
					recordMap.put("image", obj[i]);
				}
			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> querylibrary(String networkId,
			String userId) {

		log.info("query Library");
		log.debug("userData::" + userId);
		log.debug("networkid::" + networkId);
		Query query = entityManager
				.createNativeQuery("{call querylibrary(?,?)}");
		query.setParameter(1, networkId);
		query.setParameter(2, userId);
		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();
		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();

			for (int i = 0; i < obj.length; i++) {
				if (i == 0) {
					recordMap.put("title", obj[i]);

				} else if (i == 1) {
					recordMap.put("story", obj[i]);
				} else if (i == 2) {
					recordMap.put("published_ts", obj[i]);
				} else if (i == 3) {
					recordMap.put("source_url", obj[i]);
				} else if (i == 4) {
					recordMap.put("filename", obj[i]);
				} else if (i == 5) {
					recordMap.put("filetype", obj[i]);
				} else if (i == 6) {
					recordMap.put("filecontent", obj[i]);
				} else if (i == 7) {
					recordMap.put("image", obj[i]);
				}
			}

			recordList.add(recordMap);
		}

		entityManager.close();
		return recordList;
	}

	public void createLibrary(Library library) {
		super.save(library);
	}

	public Library findOne(Library library, String uploadId) {
		log.info("Inside LibraryRepo -> findOne");
		return super.findOne(Library.class, uploadId);
	}

	public boolean exists(Library people, String uploadId) {
		log.info("Inside LibraryRepo -> exists");
		return super.exists(Library.class, uploadId);
	}
	public int updateLibrary(String authBy, Date authSts, String uploadId) {
		log.info("Inside LibraryRepo -> updateLibrary");
		int i = 0;
		log.debug("AuthBy: " + authBy);
		log.debug("AuthSts: " + authSts);
		log.debug("uploadId: " + uploadId);
		Query query = entityManager.createNativeQuery(
				getSql("updateLibrary"), Library.class);
		query.setParameter(1, "A");
		query.setParameter(2, authBy);
		query.setParameter(3, authSts);
		query.setParameter(4, uploadId);
		i = query.executeUpdate();
		entityManager.close();
		return i;

	}

}
