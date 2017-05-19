package com.iexceed.esoko.db.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iexceed.esoko.domain.dao.UserPersonalizationRepo;
import com.iexceed.esoko.domain.entity.UserPersonalization;
import com.iexceed.esoko.domain.entity.UserPersonalizationPK;
import com.iexceed.esoko.domain.dao.UserWidgetSettingRepo;
import com.iexceed.esoko.domain.entity.UserWidgetSetting;
import com.iexceed.esoko.domain.entity.UserWidgetSettingPK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.CRDASHBOARDDETAILS;
import com.iexceed.esoko.jaxb.db.CRWIDGETDETAILS;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.QRDASHBOARDDETAILS;
import com.iexceed.esoko.jaxb.db.QRWIDGETDETAILS;
import com.iexceed.esoko.jaxb.db.QueryDashBoardSettingsRes;
import com.iexceed.esoko.jaxb.db.QueryWidgetSettingsRes;
import com.iexceed.esoko.jaxb.db.SaveDashBoardSettingsReq;
import com.iexceed.esoko.jaxb.db.SaveDashBoardSettingsRes;
import com.iexceed.esoko.jaxb.db.SavewidgetSettingsReq;
import com.iexceed.esoko.jaxb.db.SavewidgetSettingsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class SaveSettingsService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	UserPersonalizationRepo userPerRepo;
	@Autowired
	UserWidgetSettingRepo repo;
	Header header = null;
	Enum<ERROR_CODE> errorCode = null;

	public SaveSettingsService() {

	}

	@Transactional
	public SaveDashBoardSettingsRes saveDashBoardSettings(
			SaveDashBoardSettingsReq req) {
		log.info("Inside SaveDashBoardSettingsRes -> saveDashBoardSettings");
		SaveDashBoardSettingsRes settingsRes = new SaveDashBoardSettingsRes();
		Date timeStamp = new Date();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		try {
			List<CRDASHBOARDDETAILS> dbDtslLst = req.getCRDASHBOARDDETAILS();
			for (CRDASHBOARDDETAILS dbDtl : dbDtslLst) {
				if (StringUtils.isNotEmpty(dbDtl.getUserId())
						&& StringUtils.isNotEmpty(dbDtl.getWidgetId())) {
					UserPersonalizationPK userPerPk = new UserPersonalizationPK();
					userPerPk.setUserId(dbDtl.getUserId());
					log.debug("UserId: " + dbDtl.getUserId());
					userPerPk.setWidgetId(dbDtl.getWidgetId());
					log.debug("WidgetId: " + dbDtl.getWidgetId());
					boolean recExist = userPerRepo.exists(
							UserPersonalization.class, userPerPk);
					UserPersonalization userPer = new UserPersonalization();
					userPer.setId(userPerPk);
					userPer.setWidgetStatus(dbDtl.getEnabled());
					userPer.setXCordinates(dbDtl.getXCoordinate());
					userPer.setYCordinates(dbDtl.getYCoordinate());
					if (recExist) {
						log.info("Merging record");
						UserPersonalization prevRec = userPerRepo.findOne(
								UserPersonalization.class, userPerPk);
						userPer.setCreatedBy(prevRec.getCreatedBy());
						log.debug("CreatedBy: " + prevRec.getCreatedBy());
						userPer.setCreatedTs(prevRec.getCreatedTs());
						log.debug("CreatedTs: " + prevRec.getCreatedTs());
						userPer.setModifiedBy(req.getHeader().getUserId());
						log.debug("ModifiedBy: " + req.getHeader().getUserId());
						userPer.setModifiedTs(timeStamp);
						log.debug("ModifiedTs: " + timeStamp);
						userPerRepo.merge(userPer);
					} else {
						log.info("Saving record");
						userPer.setCreatedBy(req.getHeader().getUserId());
						log.debug("CreatedBy: " + req.getHeader().getUserId());
						userPer.setCreatedTs(timeStamp);
						log.debug("CreatedTs: " + timeStamp);
						userPerRepo.save(userPer);
					}

				} else {
					log.debug("Primary key is blank");
					errorCode = ERROR_CODE.ES_PK_001;
					break;
				}
				errorCode = ERROR_CODE.ES_SV_001;
			}
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"SaveSettingsService", "saveDashBoardSettings", req.getHeader()
						.getUserId(), errorCode);
		settingsRes.setHeader(header);
		return settingsRes;
	}

	public QueryDashBoardSettingsRes queryDBSettings(String userId) {
		log.info("Inside QueryDashBoardSettingsRes -> queryDBSettings");
		log.debug("UserId: " + userId);
		QueryDashBoardSettingsRes settingsRes = new QueryDashBoardSettingsRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<UserPersonalization> userPerLst = userPerRepo
				.queryDashBoardSettings(userId);

		if (userPerLst.size() != 0) {
			log.info("Record found");
			List<QRDASHBOARDDETAILS> dbDtlsLst = new ArrayList<QRDASHBOARDDETAILS>();
			for (UserPersonalization userPer : userPerLst) {
				QRDASHBOARDDETAILS dbDtls = new QRDASHBOARDDETAILS();
				dbDtls.setEnabled(userPer.getWidgetStatus());
				dbDtls.setWidgetId(userPer.getId().getWidgetId());
				dbDtls.setXCoordinate(userPer.getXCordinates());
				dbDtls.setYCoordinate(userPer.getYCordinates());
				dbDtlsLst.add(dbDtls);
			}
			settingsRes.getQRDASHBOARDDETAILS().addAll(dbDtlsLst);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"SaveSettingsService", "queryDashBoardSettings", "", errorCode);
		settingsRes.setHeader(header);
		return settingsRes;
	}

	@Transactional
	public SavewidgetSettingsRes savewidgetSettings(SavewidgetSettingsReq req) {
		log.info("Inside SavewidgetSettingsRes -> savewidgetSettings");
		SavewidgetSettingsRes settingsRes = new SavewidgetSettingsRes();
		CRWIDGETDETAILS wdgtDtls = req.getCRWIDGETDETAILS().get(0);
		UserWidgetSetting entity = new UserWidgetSetting();
		UserWidgetSettingPK entityPk = new UserWidgetSettingPK();
		log.debug("WidgetId:: " + wdgtDtls.getWidgetId());
		entityPk.setWidgetId(wdgtDtls.getWidgetId());
		log.debug("UserId:: " + wdgtDtls.getUserId());
		entityPk.setUserId(wdgtDtls.getUserId());
		entity.setId(entityPk);
		if (StringUtils.isNotEmpty(wdgtDtls.getUserId())
				&& StringUtils.isNotEmpty(wdgtDtls.getWidgetId())) {
			log.debug("NwkOffers:: " + wdgtDtls.getNwkOffers());
			entity.setBidsOffer(wdgtDtls.getNwkOffers());
			log.debug("CommodityId:: " + wdgtDtls.getCommodityId());
			entity.setCommodityList(wdgtDtls.getCommodityId());
			log.debug("Currency:: " + wdgtDtls.getCurrency());
			entity.setCurrency(wdgtDtls.getCurrency());
			log.debug("DateRange:: " + wdgtDtls.getDateRange());
			entity.setDateRange(wdgtDtls.getDateRange());
			log.debug("LocationId:: " + wdgtDtls.getLocationId());
			entity.setLocationList(wdgtDtls.getLocationId());
			log.debug("NetworkId:: " + wdgtDtls.getNetworkId());
			entity.setNetworkList(wdgtDtls.getNetworkId());
			log.debug("IncPbl:: " + wdgtDtls.getIncPbl());
			entity.setNetworkPublic(wdgtDtls.getIncPbl());
			log.debug("PriceType:: " + wdgtDtls.getPriceType());
			entity.setPriceType(wdgtDtls.getPriceType());
			try {
				if (!repo.exists(entity, entityPk)) {
					entity.setCreatedBy(req.getHeader().getUserId());
					entity.setCreatedTs(Utils.getCurrentDate());
					repo.save(entity);
					errorCode = ERROR_CODE.ES_SV_001;
				} else {
					UserWidgetSetting tempEntity = repo.findOne(entity,
							entityPk);
					entity.setCreatedBy(tempEntity.getCreatedBy());
					entity.setCreatedTs(tempEntity.getCreatedTs());
					entity.setModifiedBy(req.getHeader().getUserId());
					entity.setModifiedTs(Utils.getCurrentDate());
					repo.merge(entity);
					errorCode = ERROR_CODE.ES_UD_001;
				}

			} catch (DomainException e) {
				errorCode = ERROR_CODE.DM_SV_001;
				log.error(e);
			}
		} else {
			log.debug("Primary key is blank");
			errorCode = ERROR_CODE.ES_PK_001;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"SaveSettings Service", "Save widgetSettings", "", errorCode);
		settingsRes.setHeader(header);
		return settingsRes;
	}

	public QueryWidgetSettingsRes queryWidgetSettings(String userId,
			String widgetId) {
		QueryWidgetSettingsRes settingsRes = new QueryWidgetSettingsRes();
		log.info("Inside QueryWidgetSettingsResWrap -> queryWidgetSettings");
		log.debug("UserId: " + userId);
		log.debug("WidgetId: " + widgetId);
		UserWidgetSetting entity = repo.queryWidgetSettings(userId, widgetId);
		try {
			if (entity != null) {
				QRWIDGETDETAILS qryDtls = new QRWIDGETDETAILS();
				qryDtls.setCommodityId(entity.getCommodityList());
				qryDtls.setCurrency(entity.getCurrency());
				qryDtls.setDateRange(entity.getDateRange());
				qryDtls.setIncPbl(entity.getNetworkPublic());
				qryDtls.setLocationId(entity.getLocationList());
				qryDtls.setNetworkId(entity.getNetworkList());
				qryDtls.setNwkOffers(entity.getBidsOffer());
				qryDtls.setPriceType(entity.getPriceType());
				qryDtls.setWidgetId(widgetId);
				settingsRes.getQRWIDGETDETAILS().add(qryDtls);
				errorCode = ERROR_CODE.ES_SC_001;
			} else {
				errorCode = ERROR_CODE.ES_NR_001;
			}

		} catch (DomainException e) {
			errorCode = ERROR_CODE.DM_SV_001;
			log.error(e);
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"SaveSettings Service", "Query WidgetSettings", "", errorCode);
		settingsRes.setHeader(header);
		return settingsRes;
	}
}
