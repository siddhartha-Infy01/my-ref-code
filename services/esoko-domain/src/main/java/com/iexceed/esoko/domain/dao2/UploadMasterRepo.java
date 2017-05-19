package com.iexceed.esoko.domain.dao2;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.Library;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@SuppressWarnings("unchecked")
@Repository
public class UploadMasterRepo extends
		AbstractRepoForEntity<UploadMaster, String> {
	private static Logger log = LoggerUtils.getLogger();

	public UploadMaster save(UploadMaster entity) {
		log.info("Inside UploadMasterRepo-->save");
		return super.save(entity);
	}
	
	public void delete(UploadMaster master) {
		log.info("Inside UploadMasterRepo-->delete");
		super.delete(master);
	}


	public List<Map<String, Object>> queryNewsAndLibraryApproval(
			String networkId, String SortBy) {

		Query query = null;
		if (SortBy.equalsIgnoreCase("Type")) {
			query = entityManager
					.createNativeQuery(getSql("queryNewsAndLibraryApproval"));
			query.setParameter(1, networkId);

		}

		else

		{
			query = entityManager
					.createNativeQuery(getSql("queryNewsAndLibraryApproval1"));
			query.setParameter(1, networkId);
			query.setParameter(2, networkId);
		}

		List<Object[]> list = query.getResultList();
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		for (Object[] obj : list) {
			recordMap = new HashMap<String, Object>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("upload_id", obj[i]);
					break;
				case 1:
					recordMap.put("headline", obj[i]);
					break;
				case 2:
					recordMap.put("agent_id", obj[i]);
					break;

				case 3:
					recordMap.put("application_id", obj[i]);
					break;

				case 4:
					recordMap.put("upload_date", obj[i]);
					break;

				}
			}
			recordList.add(recordMap);
		}
		entityManager.close();
		return recordList;
	}

	public Library queryNewsLibById(String libId) {

		Library upmaster;
		try {
			Query query = entityManager.createNativeQuery(
					getSql("queryNewsLibById"), Library.class);
			query.setParameter(1, libId);
			upmaster = (Library) query.getSingleResult();
		}

		catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return upmaster;

	}

	public List<Map<String, Object>> queryBidsndOffersApproval(
			String networkId, String sortBy) {
		List<Object[]> list = null;
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		try

		{

			Query query = null;
			if (sortBy.equalsIgnoreCase("commodity")) {
				query = entityManager
						.createNativeQuery(getSql("queryBidsndOffersApproval"));
				query.setParameter(1, networkId);
			}

			else if (sortBy.equalsIgnoreCase("date")) {
				query = entityManager
						.createNativeQuery(getSql("queryBidsndOffersApproval1"));
				query.setParameter(1, networkId);
			}

			else if (sortBy.equalsIgnoreCase("location")) {
				query = entityManager
						.createNativeQuery(getSql("queryBidsndOffersApproval3"));
				query.setParameter(1, networkId);
			}

			else if (sortBy.equalsIgnoreCase("expiry")) {
				query = entityManager
						.createNativeQuery(getSql("queryBidsndOffersApproval4"));
				query.setParameter(1, networkId);
			}

			else {
				query = entityManager
						.createNativeQuery(getSql("queryBidsndOffersApproval2"));
				query.setParameter(1, networkId);
				query.setParameter(2, networkId);
			}

			list = query.getResultList();

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("upload_id", obj[i]);
						break;
					case 1:
						recordMap.put("commodity", obj[i]);
						break;
					case 2:
						recordMap.put("quantity", obj[i]);
						break;

					case 3:
						recordMap.put("measure", obj[i]);
						break;

					case 4:
						recordMap.put("price_amount", obj[i]);
						break;
					case 5:
						recordMap.put("currency_id", obj[i]);
						break;
					case 6:
						recordMap.put("amountUnit", obj[i]);
						break;

					case 7:
						recordMap.put("offerUploadedBy", obj[i]);
						break;

					case 8:
						recordMap.put("offerUsedId", obj[i]);
						break;
					case 9:
						recordMap.put("uploadMode", obj[i]);
						break;

					case 10:
						recordMap.put("createdTs", obj[i]);
						break;

					case 11:
						recordMap.put("expiryDate", obj[i]);
						break;
					case 12:
						recordMap.put("negotiable", obj[i]);
						break;

					case 13:
						recordMap.put("location", obj[i]);
						break;
					case 14:
						recordMap.put("payment_details", obj[i]);
						break;

					case 15:
						recordMap.put("delivery_point", obj[i]);
						break;

					case 16:
						recordMap.put("delivery_type", obj[i]);
						break;
					case 17:
						recordMap.put("noOfDays", obj[i]);
						break;
					case 18:
						recordMap.put("grade", obj[i]);
						break;

					}
				}
				recordList.add(recordMap);
			}

		}

		catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return recordList;
	}

	public List<Map<String, Object>> queryPricesApprvl(String networkId,
			String sortBy) {
		List<Object[]> list = null;
		Map<String, Object> recordMap = null;
		List<Map<String, Object>> recordList = new ArrayList<Map<String, Object>>();

		try

		{

			Query query = null;

			query = entityManager.createNativeQuery("{call priceupload(?,?)}");
			query.setParameter(1, networkId);
			query.setParameter(2, sortBy);

			list = query.getResultList();

			for (Object[] obj : list) {
				recordMap = new HashMap<String, Object>();
				for (int i = 0; i < obj.length; i++) {
					switch (i) {
					case 0:
						recordMap.put("upload_id", obj[i]);
						break;
					case 1:
						recordMap.put("commodity", obj[i]);
						break;
					case 2:
						recordMap.put("priceType", obj[i]);
						break;

					case 3:
						recordMap.put("market", obj[i]);
						break;

					case 4:
						recordMap.put("collectedOn", obj[i]);
						break;
					case 5:
						recordMap.put("currency_id", obj[i]);
						break;
					case 6:
						recordMap.put("price", obj[i]);
						break;

					case 7:
						recordMap.put("measureId", obj[i]);
						break;

					case 8:
						recordMap.put("variety", obj[i]);
						break;
					case 9:
						recordMap.put("weight", obj[i]);
						break;

					case 10:
						recordMap.put("weightMeasure", obj[i]);
						break;

					case 11:
						recordMap.put("uploadMode", obj[i]);
						break;
					case 12:
						recordMap.put("agentId", obj[i]);
						break;

					case 13:
						recordMap.put("uploadGis", obj[i]);
						break;
					case 14:
						recordMap.put("uploadedDate", obj[i]);
						break;
					case 15:
						recordMap.put("old_price", obj[i]);
						break;
					case 16:
						recordMap.put("change", obj[i]);
						break;
					case 17:
						recordMap.put("changePrice", obj[i]);
						break;
					case 18:
						recordMap.put("changePrice1", obj[i]);
						break;
						
					case 19:
						recordMap.put("comments", obj[i]);
						break;
					case 20:
						recordMap.put("symbol", obj[i]);
						break;	
					case 21:
						recordMap.put("baseMeasureId", obj[i]);
						break;
					case 22:
						recordMap.put("quoteMeasureId", obj[i]);
						break;
						
					case 23:
						recordMap.put("baseMeasureName", obj[i]);
						break;
					case 24:
						recordMap.put("quoteMeasureName", obj[i]);
						break;	
	

					}
				}
				recordList.add(recordMap);
			}

		}

		catch (NoResultException e) {
			return null;
		}

		entityManager.close();
		return recordList;
	}

	public int delteNewsLib(String libId, String applicationId) {
		log.info("delete news/lib id");
		log.debug("libId -> " + libId);

		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call deleteUpload(?,?)}");
			query.setParameter(1, libId);
			query.setParameter(2, applicationId);

			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}

	public UploadMaster findOne(UploadMaster people, String uploadId) {
		log.info("Inside UploadMasterRepo -> findOne");
		return super.findOne(UploadMaster.class, uploadId);
	}

	public boolean exists(UploadMaster people, String uploadId) {
		log.info("Inside UploadMasterRepo -> exists");
		return super.exists(UploadMaster.class, uploadId);
	}

	public int updateUpldMatsr(String authBy, Date authsts, String uploadId) {
		log.info("Inside UploadMasterRepo -> updateUpldMatsr");
		int i = 0;
		log.debug("uploadId: " + uploadId);
		Query query = entityManager.createNativeQuery(
				getSql("updateUpldMatsr"), UploadMaster.class);
		query.setParameter(1, authBy);
		query.setParameter(2, authsts);
		query.setParameter(3, uploadId);
		i = query.executeUpdate();
		entityManager.close();
		return i;

	}

	public int delteUpldMstrNewsLib(String libId) {
		log.info("Inside UploadMasterRepo -> delteUpldMstrNewsLib");
		log.debug("libId -> " + libId);

		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call deleteLibrary(?)}");
			query.setParameter(1, libId);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
	
	public int newsLibSharing(String uploadId,String networkId) {
		log.info("Inside UploadMasterRepo -> newsLibSharing");
		log.debug("uploadId -> " + uploadId);
		log.debug("networkId -> " + networkId);

		int count = 0;
		try {
			Query query = entityManager
					.createNativeQuery("{call approveNewsCopySharing(?,?)}");
			query.setParameter(1, uploadId);
			query.setParameter(2, networkId);
			count = query.executeUpdate();

		} catch (NoResultException e) {
			return 0;
		}
		entityManager.close();
		return count;
	}
}
