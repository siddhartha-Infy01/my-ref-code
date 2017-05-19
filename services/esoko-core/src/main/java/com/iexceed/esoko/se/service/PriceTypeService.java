package com.iexceed.esoko.se.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.PriceTypeAliaRepo;
import com.iexceed.esoko.domain.dao.PriceTypeRepo;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.PriceType;
import com.iexceed.esoko.domain.entity.PriceTypeAlia;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.SMSCDALS;
import com.iexceed.esoko.jaxb.se.CRPRCALSDTLS;
import com.iexceed.esoko.jaxb.se.CRPRCTYPDTLS;
import com.iexceed.esoko.jaxb.se.CreatepriceTypeReq;
import com.iexceed.esoko.jaxb.se.CreatepriceTypeRes;
import com.iexceed.esoko.jaxb.se.DLPRCTYPDTLS;
import com.iexceed.esoko.jaxb.se.DeletepriceTypeReq;
import com.iexceed.esoko.jaxb.se.DeletepriceTypeRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRPRCALSDTLS;
import com.iexceed.esoko.jaxb.se.QRPRCTYPDTLS;
import com.iexceed.esoko.jaxb.se.QuerypriceTypeRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class PriceTypeService {
	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	PriceTypeRepo priceTypeRepo;
	@Autowired
	PriceTypeAliaRepo priceAliasRepo;
	Enum<ERROR_CODE> ERROR = null;
	Header header = null;
	private final String serviceName = "PriceTypeService";

	@Transactional
	public CreatepriceTypeRes createpriceType(CreatepriceTypeReq req) {
		log.info("Inside CreatepriceTypeRes -> createpriceType");
		CreatepriceTypeRes crtPriceTypeRes = new CreatepriceTypeRes();
		List<CRPRCTYPDTLS> crtPrTypeList = null;
		List<CRPRCALSDTLS> crPrcAliasDtls = null;
		crtPrTypeList = req.getCRPRCTYPDTLS();
		for (CRPRCTYPDTLS crtPrTypeDtls : crtPrTypeList) {
			crPrcAliasDtls = crtPrTypeDtls.getCRPRCALSDTLS();
			PriceType priceTypeEntity = new PriceType();
			log.info("Price Type:" + crtPrTypeDtls.getPriceType());
			priceTypeEntity.setPriceTypeId(crtPrTypeDtls.getPriceType());
			log.info("Price name:: " + crtPrTypeDtls.getName());
			priceTypeEntity.setName(crtPrTypeDtls.getName());
			log.info("Description:: " + crtPrTypeDtls.getDescription());
			priceTypeEntity.setDescription(crtPrTypeDtls.getDescription());
			PriceTypeAlia priceAliasEntiy = new PriceTypeAlia();
			priceAliasEntiy.setPriceTypeId(crtPrTypeDtls.getPriceType());
			try {
				if (!priceTypeRepo.exists(priceTypeEntity,
						priceTypeEntity.getPriceTypeId())) {
					priceTypeEntity.setCreatedBy(req.getHeader().getUserId());
					priceTypeEntity.setCreatedTs(Utils.getCurrentDate());
					priceTypeEntity.setRecordStatus("A");
					priceTypeRepo.save(priceTypeEntity);
					ERROR = ERROR_CODE.PRC_SV_001;
				} else {
					PriceType tempEntity = priceTypeRepo.findOne(
							priceTypeEntity, priceTypeEntity.getPriceTypeId());
					priceTypeEntity.setCreatedBy(tempEntity.getCreatedBy());
					priceTypeEntity.setCreatedTs(tempEntity.getCreatedTs());
					priceTypeEntity.setModifiedBy(req.getHeader().getUserId());
					priceTypeEntity.setModifiedTs(Utils.getCurrentDate());
					priceTypeEntity.setRecordStatus("A");
					priceTypeRepo.merge(priceTypeEntity);
					ERROR = ERROR_CODE.PRC_SV_001;
				}
				log.debug("smsAliasList -> " + crPrcAliasDtls);
				for (CRPRCALSDTLS alisLoop : crPrcAliasDtls) {
					log.debug("Aliases -> " + alisLoop.getAliasID());
					priceAliasEntiy.setAliasId(alisLoop.getAliasID());
					log.debug("Name() ->" + alisLoop.getName());
					priceAliasEntiy.setName(alisLoop.getName());
					if (!priceAliasRepo.exists(priceAliasEntiy,
							priceAliasEntiy.getAliasId())) {
						priceAliasRepo.save(priceAliasEntiy);
					} else {
						priceAliasRepo.merge(priceAliasEntiy);
					}

				}
			} catch (DomainException e) {
				ERROR = ERROR_CODE.ES_SV_002;
				log.error(Utils.getStackTrace(e));
			}
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Create Price Type", "", ERROR_CODE.PRC_SV_001);
		crtPriceTypeRes.setHeader(header);
		return crtPriceTypeRes;
	}

	public QuerypriceTypeRes querypriceType(String priceType, String networkId,
			String userId, String userData) {
		log.info("Inside QuerypriceTypeRes -> querypriceType");
		QuerypriceTypeRes qryTypeRes = new QuerypriceTypeRes();
		log.info("PriceType :: " + priceType);

		if (priceType != null) {
			PriceType priceTypeEntity = priceTypeRepo
					.querypriceTypebyId(priceType);
			List<PriceTypeAlia> priceTypeAlsEntity = priceAliasRepo
					.querypriceTypealiabyId(priceType);
			QRPRCTYPDTLS qrPrcDtls = new QRPRCTYPDTLS();
			qrPrcDtls.setPriceType(priceTypeEntity.getPriceTypeId());
			qrPrcDtls.setName(priceTypeEntity.getName());
			qrPrcDtls.setDescription(priceTypeEntity.getDescription());
			qrPrcDtls.setStatus(priceTypeEntity.getRecordStatus());
			for (PriceTypeAlia alsEntity : priceTypeAlsEntity) {
				QRPRCALSDTLS qrPrAlsDtls = new QRPRCALSDTLS();
				qrPrAlsDtls.setAliasID(alsEntity.getAliasId());
				qrPrAlsDtls.setName(alsEntity.getName());
				qrPrcDtls.getPRCALSDTLS().add(qrPrAlsDtls);
			}
			qryTypeRes.getPRCTYPDTLS().add(qrPrcDtls);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {

			List<PriceType> priceTypeEntity = priceTypeRepo.queryAllPriceType();
			for (PriceType type : priceTypeEntity) {
				QRPRCTYPDTLS qrPrcDtls = new QRPRCTYPDTLS();
				List<PriceTypeAlia> priceTypeAlsEntity = priceAliasRepo
						.querypriceTypealiabyId(type.getPriceTypeId());
				qrPrcDtls.setPriceType(type.getPriceTypeId());
				qrPrcDtls.setName(type.getName());
				qrPrcDtls.setDescription(type.getDescription());
				qrPrcDtls.setStatus(type.getRecordStatus());
				for (PriceTypeAlia alsEntity : priceTypeAlsEntity) {
					QRPRCALSDTLS qrPrAlsDtls = new QRPRCALSDTLS();
					qrPrAlsDtls.setAliasID(alsEntity.getAliasId());
					qrPrAlsDtls.setName(alsEntity.getName());
					qrPrcDtls.getPRCALSDTLS().add(qrPrAlsDtls);
				}
				qryTypeRes.getPRCTYPDTLS().add(qrPrcDtls);
			}

			ERROR = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Query PriceType", "", ERROR);
		qryTypeRes.setHeader(header);
		return qryTypeRes;
	}

	@Transactional
	public DeletepriceTypeRes deletepriceType(DeletepriceTypeReq req) {
		log.info("Inside DeletepriceTypeRes -> deletepriceType");
		DeletepriceTypeRes delPrcTypeRes = new DeletepriceTypeRes();
		DLPRCTYPDTLS dlPrcTypeDtls = new DLPRCTYPDTLS();
		PriceType priceTypeEntity = new PriceType();
		dlPrcTypeDtls = (DLPRCTYPDTLS) req.getDLPRCTYPDTLS().get(0);
		log.info("Price Type:" + dlPrcTypeDtls.getPriceType());
		priceTypeEntity = priceTypeRepo.findOne(priceTypeEntity,
				dlPrcTypeDtls.getPriceType());
		priceTypeEntity.setRecordStatus("D");
		try {
			if (priceTypeEntity != null) {
				priceTypeEntity.setRecordStatus("D");
				priceTypeEntity.setModifiedBy(req.getHeader().getUserId());
				priceTypeEntity.setModifiedTs(Utils.getCurrentDate());
				priceTypeRepo.save(priceTypeEntity);
				ERROR = ERROR_CODE.PRC_SV_002;
			} else {
				ERROR = ERROR_CODE.DM_SV_003;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.SE, serviceName,
				"Delete Occupations", "", ERROR);
		delPrcTypeRes.setHeader(header);
		return delPrcTypeRes;
	}

}
