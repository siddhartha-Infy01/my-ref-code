package com.iexceed.esoko.ne.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.NetworkPriceTypeRepo;
import com.iexceed.esoko.domain.dao.Network_pricetypeRepo;
import com.iexceed.esoko.domain.dao.PriceTypeRepo;
import com.iexceed.esoko.domain.entity.NetworkPriceType;
import com.iexceed.esoko.domain.entity.NetworkPriceTypePK;
import com.iexceed.esoko.domain.entity.Network_pricetype;
import com.iexceed.esoko.domain.entity.PriceType;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.CRNWKPRCDTLS;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.NWKPRICETYPES;
import com.iexceed.esoko.jaxb.ns.NwkPriceTypeSaveReq;
import com.iexceed.esoko.jaxb.ns.NwkPriceTypeSaveRes;
import com.iexceed.esoko.jaxb.ns.PRICETYPES;
import com.iexceed.esoko.jaxb.ns.QRNWKPRCDTLS;
import com.iexceed.esoko.jaxb.ns.QueryNetworkPriceTypesRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkPriceTypesRes;
import com.iexceed.esoko.jaxb.ns.SaveNwkPriceTypesReq;
import com.iexceed.esoko.jaxb.ns.SaveNwkPriceTypesRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Gangadhar
 */
@Service
public class NetworkPriceTypesService {

	public static Logger log = LoggerUtils.getLogger();
	@Autowired
	Network_pricetypeRepo repo;
	@Autowired
	NetworkPriceTypeRepo nwkPriceTypeRepo;
	@Autowired
	PriceTypeRepo priceTypeRepo;

	Enum<ERROR_CODE> errorCode = ERROR_CODE.DM_SV_002;

	// Delete this method
	@Transactional
	public SaveNwkPriceTypesRes saveNwkPriceType(SaveNwkPriceTypesReq req) {
		log.info("Inside NetworkPriceTypesService -> saveNwkPriceType");
		Header header = null;
		SaveNwkPriceTypesRes svNwkPriceType = new SaveNwkPriceTypesRes();
		Network_pricetype entity = new Network_pricetype();
		CRNWKPRCDTLS nwkDtls = req.getCRNWKPRCDTLS().get(0);
		log.debug("Wholesale -> " + nwkDtls.getWholesale());
		entity.setWholesale(nwkDtls.getWholesale());
		log.debug("Retail -> " + nwkDtls.getRetail());
		entity.setRetail(nwkDtls.getRetail());
		log.debug("Producer -> " + nwkDtls.getProducer());
		entity.setProducer(nwkDtls.getProducer());
		log.debug("OffLorry -> " + nwkDtls.getOffLorry());
		entity.setOffLorry(nwkDtls.getOffLorry());
		log.debug("NetworkId -> " + nwkDtls.getNetworkId());
		entity.setNetworkId(nwkDtls.getNetworkId());
		log.debug("FarmGate -> " + nwkDtls.getFarmGate());
		entity.setFarmGate(nwkDtls.getFarmGate());
		svNwkPriceType.setHeader(req.getHeader());
		if (!repo.exists(entity, entity.getNetworkId())) {
			log.debug("Wholesale -> " + nwkDtls.getWholesale());
			entity.setCreatedTs(Utils.getCurrentDate());
			log.debug("CreatedBy: " + req.getHeader().getUserId());
			entity.setCreatedBy(req.getHeader().getUserId());
			repo.save(entity);
			errorCode = ERROR_CODE.NW_PR_SV;
		} else {
			Network_pricetype temp_entity = repo.findOne(entity,
					nwkDtls.getNetworkId());
			log.debug("ModifiedTs -> " + Utils.getCurrentDate());
			entity.setModifiedTs(Utils.getCurrentDate());
			log.debug("CreatedTs -> " + temp_entity.getCreatedTs());
			entity.setCreatedTs(temp_entity.getCreatedTs());
			log.debug("CreatedBy: " + req.getHeader().getUserId());
			entity.setCreatedBy(temp_entity.getCreatedBy());
			log.debug("ModifiedBy: " + req.getHeader().getUserId());
			entity.setModifiedBy(req.getHeader().getUserId());
			repo.merge(entity);
			errorCode = ERROR_CODE.NW_PR_SV;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPriceTypesService", "Save Network Price Type", "",
				errorCode);
		svNwkPriceType.setHeader(header);
		return svNwkPriceType;
	}

	@Transactional
	public NwkPriceTypeSaveRes saveNetworkPriceTypes(NwkPriceTypeSaveReq req) {
		log.info("Inside NetworkPriceTypesService -> saveNwkPriceTypes");
		NwkPriceTypeSaveRes saveRes = new NwkPriceTypeSaveRes();
		Enum<ERROR_CODE> errorCode = null;
		String networkId = req.getSVNWKPRCDTLS().getNetworkId();
		List<NetworkPriceType> exstngList = nwkPriceTypeRepo
				.queryPriceTypes(networkId);
		if (exstngList.size() != 0) {
			nwkPriceTypeRepo.delete(exstngList);
		}
		try {
			List<PRICETYPES> priceTypeList = req.getSVNWKPRCDTLS()
					.getPRICETYPES();
			for (PRICETYPES priceType : priceTypeList) {
				NetworkPriceType nwkPriceType = new NetworkPriceType();
				nwkPriceType.setCreatedBy(req.getHeader().getUserId());
				nwkPriceType.setCreatedTs(new Date());
				nwkPriceType.setSelectedValue(priceType.getSelectedValue());
				NetworkPriceTypePK pk = new NetworkPriceTypePK();
				pk.setNetworkId(networkId);
				pk.setPricetypeId(priceType.getPriceTyeId());
				nwkPriceType.setId(pk);
				nwkPriceTypeRepo.save(nwkPriceType);
			}
			log.info("Network price type saved successfully");
			errorCode = ERROR_CODE.NW_PR_SV;
		} catch (DomainException e) {
			errorCode = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPriceTypesService", "saveNetworkPriceTypes", "",
				errorCode);
		saveRes.setHeader(header);
		return saveRes;
	}

	// Delete this method
	public QueryNwkPriceTypesRes queryNwkPriceTypes(String networkId) {
		log.info("Inside NetworkPriceTypesService -> queryNwkPriceTypes");
		log.debug("NetworkId: " + networkId);
		Enum<ERROR_CODE> ERROR = null;
		QueryNwkPriceTypesRes qNwkPriceTypes = new QueryNwkPriceTypesRes();
		try {
			List<Network_pricetype> entity = repo
					.queryNetworkPriceTyps(networkId);
			List<QRNWKPRCDTLS> priceTypeList = new ArrayList<QRNWKPRCDTLS>();
			log.info("entity -> " + entity);
			if (entity.size() != 0) {
				for (Network_pricetype entityLoop : entity) {
					QRNWKPRCDTLS priceTypeDtls = new QRNWKPRCDTLS();
					priceTypeDtls.setFarmGate(entityLoop.getFarmGate());
					priceTypeDtls.setNetworkId(entityLoop.getNetworkId());
					priceTypeDtls.setOffLorry(entityLoop.getOffLorry());
					priceTypeDtls.setRetail(entityLoop.getRetail());
					priceTypeDtls.setWholesale(entityLoop.getWholesale());
					priceTypeDtls.setProducer(entityLoop.getProducer());
					priceTypeList.add(priceTypeDtls);
					priceTypeDtls = null;

				}
				ERROR = ERROR_CODE.ES_SC_001;
				qNwkPriceTypes.getQRNWKPRCDTLS().addAll(priceTypeList);
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}

		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_002;
		}

		Header header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPriceTypesService", "queryNwkPriceTypes", "", ERROR);
		qNwkPriceTypes.setHeader(header);
		return qNwkPriceTypes;
	}

	public QueryNetworkPriceTypesRes queryNetworkPriceTypes(String networkId) {
		log.info("Inside NetworkPriceTypesService -> queryNetworkPriceTypes");
		QueryNetworkPriceTypesRes queryRes = new QueryNetworkPriceTypesRes();
		Enum<ERROR_CODE> errorCode = null;
		List<NetworkPriceType> priceTypeList = nwkPriceTypeRepo
				.queryPriceTypes(networkId);
		if (priceTypeList.size() == 0) {
			errorCode = ERROR_CODE.ES_NR_001;
		} else {
			List<NWKPRICETYPES> resultList = new ArrayList<NWKPRICETYPES>();
			for (NetworkPriceType entity : priceTypeList) {
				NWKPRICETYPES nwkPriceType = new NWKPRICETYPES();
				NetworkPriceTypePK entityId = entity.getId();
				nwkPriceType.setPriceTypeId(entityId.getPricetypeId());
				nwkPriceType.setSelectedValue(entity.getSelectedValue());
				PriceType priceType = priceTypeRepo.findOne(PriceType.class,
						entityId.getPricetypeId());
				if (priceType != null) {
					nwkPriceType.setPriceTypeName(priceType.getName());
				}
				resultList.add(nwkPriceType);
			}
			errorCode = ERROR_CODE.ES_SC_001;
			queryRes.getNWKPRICETYPES().addAll(resultList);
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkPriceTypesService", "queryNetworkPriceTypes", "",
				errorCode);
		queryRes.setHeader(header);
		return queryRes;
	}
}
