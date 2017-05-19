package com.iexceed.esoko.se.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.MeasureAliaRepo;
import com.iexceed.esoko.domain.dao.MeasureFactorRepo;
import com.iexceed.esoko.domain.dao.MeasureRepo;
import com.iexceed.esoko.domain.entity.Measure;
import com.iexceed.esoko.domain.entity.MeasureAlia;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.se.CRALSDTLS;
import com.iexceed.esoko.jaxb.se.CRMSRDTLS;
import com.iexceed.esoko.jaxb.se.CreateMeasureReq;
import com.iexceed.esoko.jaxb.se.CreateMeasureRes;
import com.iexceed.esoko.jaxb.se.DLMSRDTLS;
import com.iexceed.esoko.jaxb.se.DeleteMeasureReq;
import com.iexceed.esoko.jaxb.se.DeleteMeasureRes;
import com.iexceed.esoko.jaxb.se.Header;
import com.iexceed.esoko.jaxb.se.QRALSDTLS;
import com.iexceed.esoko.jaxb.se.QRMSRDTLS;
import com.iexceed.esoko.jaxb.se.QueryMeasureRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class MeasureService {

	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	MeasureRepo measureRepo;
	@Autowired
	MeasureAliaRepo measureAliasRepo;
	@Autowired
	MeasureFactorRepo factorRepo;

	enum MeasureTpes {
		U("Unit"), V("Volume"), M("Mass"), D("Distance"), A("Area");

		String value;

		MeasureTpes(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public QueryMeasureRes queryMeasure(String measureId, String type,
			String isSystem, String networkId, String userId, String userData) {
		log.info("Inside QueryMeasureRes -> queryMeasure :: ");
		QueryMeasureRes qryMeasureRes = new QueryMeasureRes();
		log.info("measureId:: " + measureId);
		Measure qrMsrEntity = null;
		List<Measure> qrMsrDtlsRes = null;
		List<QRMSRDTLS> qrMsrDtlsList = new ArrayList<QRMSRDTLS>();

		if (measureId != null) {
			qrMsrEntity = measureRepo.queryMeasurebyMeasureId(measureId);
			log.info("qrMsrEntity -> " + qrMsrEntity);
			QRMSRDTLS qrMsrDtls = new QRMSRDTLS();
			if (qrMsrEntity != null) {
				qrMsrDtls.setMeasureId(qrMsrEntity.getMeasureId());
				qrMsrDtls.setName(qrMsrEntity.getMeasureName());
				qrMsrDtls.setSymbol(qrMsrEntity.getSymbol());
				qrMsrDtls.setType(MeasureTpes.valueOf(qrMsrEntity.getType())
						.getValue());
				qrMsrDtls.setIsSystem(qrMsrEntity.getIsSystem());
				qrMsrDtls.setDescription(qrMsrEntity.getDescription());
				qrMsrDtls.setStatus(qrMsrEntity.getRecordStatus());
				qryMeasureRes.getMSRDTLS().add(qrMsrDtls);
			}
		} else {
			qrMsrDtlsRes = measureRepo.queryAllSystemMeasures();
			log.info("qrMsrEntity -> " + qrMsrEntity);
			for (Measure msrEntity : qrMsrDtlsRes) {
				QRMSRDTLS qrMsrDtls = new QRMSRDTLS();
				qrMsrDtls.setMeasureId(msrEntity.getMeasureId());
				qrMsrDtls.setName(msrEntity.getMeasureName());
				qrMsrDtls.setSymbol(msrEntity.getSymbol());
				log.info("Enum Value From DB->" + msrEntity.getType());
				log.info("Enum Value 1-> "
						+ MeasureTpes.valueOf(msrEntity.getType()).getValue());
				log.info("Enum Value2-> "
						+ MeasureTpes.valueOf(msrEntity.getType()));
				qrMsrDtls.setType(MeasureTpes.valueOf(msrEntity.getType())
						.getValue());
				qrMsrDtls.setIsSystem(msrEntity.getIsSystem());
				qrMsrDtls.setDescription(msrEntity.getDescription());
				qrMsrDtls.setStatus(msrEntity.getRecordStatus());
				qrMsrDtlsList.add(qrMsrDtls);
			}
			qryMeasureRes.getMSRDTLS().addAll(qrMsrDtlsList);
		}

		List<MeasureAlia> qrMsrAlsRes = (List<MeasureAlia>) measureAliasRepo
				.queryMeasureAliabymeasureId(measureId);
		List<QRALSDTLS> qrMsrAlsList = new ArrayList<QRALSDTLS>();
		log.info("qrMsrAlsRes -> " + qrMsrAlsRes);
		for (MeasureAlia measureAlsResp : qrMsrAlsRes) {
			QRALSDTLS qrAlsDtls = new QRALSDTLS();
			measureAlsResp.setAliasId(measureAlsResp.getAliasId());
			measureAlsResp.setMeasureName(measureAlsResp.getMeasureName());
			measureAlsResp.setMeasureId(measureAlsResp.getMeasureId());
			qrMsrAlsList.add(qrAlsDtls);
			qrAlsDtls = null;
		}
		qryMeasureRes.getQRALSDTLS().addAll(qrMsrAlsList);
		return qryMeasureRes;
	}

	@Transactional
	public CreateMeasureRes createMeasure(CreateMeasureReq req) {
		// TODO Auto-generated method stub
		log.info("Measure Service -> createMeasure");
		CreateMeasureRes crtmeasureRes = new CreateMeasureRes();
		Measure entity = new Measure();
		MeasureAlia aliasEntity = new MeasureAlia();
		CRMSRDTLS msrDtls = new CRMSRDTLS();
		CRALSDTLS alsDtls = new CRALSDTLS();
		msrDtls = (CRMSRDTLS) req.getCRMSRDTLS().get(0);
		alsDtls = (CRALSDTLS) msrDtls.getCRALSDTLS().get(0);
		log.info("Measure Id:: " + msrDtls.getMeasureId());
		entity.setMeasureId(msrDtls.getMeasureId().toString());
		log.info("Measure Symbol:: " + msrDtls.getSymbol());
		entity.setSymbol(msrDtls.getSymbol());
		log.info("Measure Type:: " + msrDtls.getType());
		entity.setType(msrDtls.getType());
		log.info("Measure Is System:: " + msrDtls.getIsSystem());
		entity.setIsSystem(msrDtls.getIsSystem());
		log.info("Measure Name::" + msrDtls.getName());
		entity.setMeasureName(msrDtls.getName());
		log.info("Measure Description::" + msrDtls.getDescription());
		entity.setDescription(msrDtls.getDescription());
		entity.setRecordStatus("A");
		log.info("Measure AliasId::" + alsDtls.getAliasID());
		aliasEntity.setAliasId(alsDtls.getAliasID().toString()
				.equalsIgnoreCase("") ? msrDtls.getMeasureId().toString()
				: alsDtls.getAliasID().toString());
		aliasEntity.setMeasureId(msrDtls.getMeasureId().toString());
		aliasEntity.setMeasureName(alsDtls.getAliasID());
		Measure mesure = measureRepo.findOne(entity, msrDtls.getMeasureId());
		if (mesure != null) {
			measureRepo.merge(entity);

		} else {
			measureRepo.save(entity);
			measureAliasRepo.save(aliasEntity);
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"Measure Service", "Create Measure", req.getHeader()
						.getUserId(), ERROR_CODE.MS_SC_001);
		crtmeasureRes.setHeader(header);
		return crtmeasureRes;
	}

	@Transactional
	public DeleteMeasureRes deleteMeasure(DeleteMeasureReq req) {
		log.info("DeleteMeasureRes -> deleteMeasure");
		DeleteMeasureRes delMeasureRes = new DeleteMeasureRes();
		Measure entity = new Measure();
		DLMSRDTLS dlMsrDtls = new DLMSRDTLS();
		dlMsrDtls = req.getDLMSRDTLS().get(0);
		log.info("Measure Id:: " + dlMsrDtls.getMeasureId());
		entity = measureRepo.findOne(Measure.class, dlMsrDtls.getMeasureId()
				.toString());
		entity.setRecordStatus("D");
		measureRepo.save(entity);
		Header header = (Header) HeaderFactory.getHeader(HeaderType.SE,
				"Measure Service", "Delete Measure", req.getHeader()
						.getUserId(), ERROR_CODE.MS_SC_002);
		delMeasureRes.setHeader(header);
		return delMeasureRes;
	}

}
