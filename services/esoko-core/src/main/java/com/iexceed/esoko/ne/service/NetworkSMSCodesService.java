package com.iexceed.esoko.ne.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.SmsCodeAliaRepo;
import com.iexceed.esoko.domain.dao.SmsCodeRepo;
import com.iexceed.esoko.domain.entity.SmsCode;
import com.iexceed.esoko.domain.entity.SmsCodeAlia;
import com.iexceed.esoko.domain.entity.SmsCodePK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.CRSMSCDDTLS;
import com.iexceed.esoko.jaxb.ns.CreateSMSCodesReq;
import com.iexceed.esoko.jaxb.ns.CreateSMSCodesRes;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.QRCOMMLIST;
import com.iexceed.esoko.jaxb.ns.QRSMSCDDTLS;
import com.iexceed.esoko.jaxb.ns.QueryCommListRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkSmsCodesRes;
import com.iexceed.esoko.jaxb.ns.SMSCDALS;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Gangadhar
 */
@Service
public class NetworkSMSCodesService {

	public static Logger log = LoggerUtils.getLogger();
	private final String serviceName = "NetworkSMSCodesService";

	public NetworkSMSCodesService() {

	}

	@Autowired
	SmsCodeRepo nwSMSRepo;
	@Autowired
	SmsCodeAliaRepo aliasRepo;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.DM_SV_002;
	Header header = null;

	public QueryNwkSmsCodesRes qNetworkSmsCodes(String type, String networkId,
			String subType) {
		log.info("Inside QueryNwkSmsCodesRes -> qNetworkSmsCodes");
		log.debug("Type: " + type);
		log.debug("NetworkId: " + networkId);
		QueryNwkSmsCodesRes qNwkSmsCodes = new QueryNwkSmsCodesRes();

		List<QRSMSCDDTLS> smsList = null;
		try {
			if ("C".equalsIgnoreCase(type)) {
				smsList = this.querySmscodesbyCommodities(networkId, type,
						subType);
				if (smsList.size() != 0) {
					qNwkSmsCodes.getQRSMSCDDTLS().addAll(smsList);
					ERROR = ERROR_CODE.ES_SC_001;
				} else {
					ERROR = ERROR_CODE.ES_NR_001;
				}
			} else {
				smsList = this.querySmscodesbyLocation(networkId, type);
				if (smsList.size() != 0) {
					qNwkSmsCodes.getQRSMSCDDTLS().addAll(smsList);
					ERROR = ERROR_CODE.ES_SC_001;
				} else {
					ERROR = ERROR_CODE.ES_NR_001;
				}
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Query Network SmsCodes", "", ERROR);
		qNwkSmsCodes.setHeader(header);
		return qNwkSmsCodes;
	}

	@Transactional
	public CreateSMSCodesRes createSMSCodes(CreateSMSCodesReq req) {
		log.info("Inside CreateSMSCodesRes -> createSMSCodes");
		CreateSMSCodesRes smsCodesRes = new CreateSMSCodesRes();
		List<CRSMSCDDTLS> codeList = req.getCRSMSCDDTLS();
		try {
			if (codeList.size() != 0) {
				String networkId = codeList.get(0).getNetworkId();
				List<SmsCode> smsCodeDtls = nwSMSRepo
						.queryAllSMSCodes(networkId);
				if (smsCodeDtls.size() != 0) {
					nwSMSRepo.delete(smsCodeDtls);
				}
				List<SmsCodeAlia> aliasDtls = aliasRepo
						.queryAllSMSAlias(networkId);
				if (aliasDtls.size() != 0) {
					aliasRepo.delete(aliasDtls);
				}
				for (CRSMSCDDTLS smsCode : codeList) {
					if (StringUtils.isNotEmpty(smsCode.getCode())) {
						SmsCode entity = new SmsCode();
						SmsCodePK entityPK = new SmsCodePK();
						entityPK.setNetworkId(networkId);
						entityPK.setSmsId(smsCode.getLocation());
						entity.setId(entityPK);
						entity.setCreatedBy(req.getHeader().getUserId());
						entity.setCreatedTs(new Date());
						entity.setSms_Code(smsCode.getCode());
						entity.setType(smsCode.getType());
						nwSMSRepo.save(entity);
						List<SMSCDALS> aliasList = smsCode.getSMSCDALS();
						if (aliasList.size() != 0) {
							for (SMSCDALS alias : aliasList) {
								if (StringUtils.isNotEmpty(alias.getAliases())) {
									SmsCodeAlia entityAlias = new SmsCodeAlia();
									entityAlias.setAliasId(alias.getAliases());
									entityAlias.setNetworkId(networkId);
									entityAlias
											.setSmidId(smsCode.getLocation());
									entityAlias.setSmsCode(smsCode.getCode());
									aliasRepo.save(entityAlias);
								}
							}
						}
					}
				}
			}
			ERROR = ERROR_CODE.SMS_DP_001;
			log.info("SMS Codes saved successfully");
		} catch (DomainException e) {
			ERROR = e.getCode();
			log.error(Utils.getStackTrace(e));
		}
		
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Create SMSCodes", "", ERROR);
		smsCodesRes.setHeader(header);
		return smsCodesRes;
	}

	public List<SmsCodeAlia> getSMScodeAlias(String smsCode, String networkId) {
		List<SmsCodeAlia> aliasList = aliasRepo.querySmscodeAlia(smsCode,
				networkId);
		return aliasList;
	}

	public List<QRSMSCDDTLS> querySmscodesbyLocation(String networkId,
			String type) {
		log.debug("networkId -> " + networkId);
		log.debug("type -> " + type);
		List<Map<String, Object>> entity = nwSMSRepo.querySmscodesbyLocType(
				networkId, type);
		List<QRSMSCDDTLS> smsList = new ArrayList<QRSMSCDDTLS>();
		List<SMSCDALS> aliasList = new ArrayList<SMSCDALS>();
		List<SmsCodeAlia> aliaEntity = null;
		log.info("entity -> " + entity);
		try {
			if (entity.size() != 0) {
				for (Map<String, Object> smsLoop : entity) {
					log.info("Inside smsLoop");
					QRSMSCDDTLS smsDtls = new QRSMSCDDTLS();

					if (smsLoop.get("location_id") != null) {
						smsDtls.setLocation(smsLoop.get("location_id")
								.toString());
					}
					if (smsLoop.get("sms_code") != null) {
						String l_smsCode = String.valueOf(smsLoop
								.get("sms_code"));
						smsDtls.setCode((l_smsCode == "null") ? "" : l_smsCode);
						aliaEntity = getSMScodeAlias(l_smsCode, networkId);
						log.info("aliaEntity -> " + aliaEntity);
						StringBuilder lAliasID = new StringBuilder();
						if (aliaEntity.size() != 0) {
							for (SmsCodeAlia alisLoop : aliaEntity) {
								SMSCDALS alsDtls = new SMSCDALS();
								log.info("Inside alisLoop");
								lAliasID.append(alisLoop.getAliasId() + ",");
							}
							log.info("aliasList ->" + aliasList);
							smsDtls.setAlsDetails(lAliasID.toString());
						} else {
							log.info("Inside alisLoop else");

						}
					}
					smsList.add(smsDtls);
				}
			}

		} catch (DomainException e) {
			log.error(Utils.getStackTrace(e));
		}
		return smsList;
	}

	public List<QRSMSCDDTLS> querySmscodesbyCommodities(String networkId,
			String type, String subType) {
		log.debug("networkId -> " + networkId);
		log.debug("type -> " + type);
		log.debug("subType -> " + subType);
		List<Map<String, Object>> entity = nwSMSRepo.querySmscodesbyComm(
				networkId, type, subType);
		List<QRSMSCDDTLS> smsList = new ArrayList<QRSMSCDDTLS>();
		List<SMSCDALS> aliasList = new ArrayList<SMSCDALS>();
		List<SmsCodeAlia> aliaEntity = null;
		log.info("entity -> " + entity);
		try {
			if (entity.size() != 0) {
				for (Map<String, Object> smsLoop : entity) {
					log.info("Inside smsLoop");
					QRSMSCDDTLS smsDtls = new QRSMSCDDTLS();
					if (smsLoop.get("commodity_id") != null) {
						smsDtls.setLocation(smsLoop.get("commodity_id")
								.toString());
					}
					String l_smsCode = String.valueOf(smsLoop.get("sms_code"));
					smsDtls.setCode((l_smsCode == "null") ? "" : l_smsCode);
					aliaEntity = getSMScodeAlias(l_smsCode, networkId);
					log.info("aliaEntity -> " + aliaEntity);
					StringBuilder lAliasID = new StringBuilder();
					if (aliaEntity.size() != 0) {
						for (SmsCodeAlia alisLoop : aliaEntity) {
							log.info("Inside alisLoop");
							lAliasID.append(alisLoop.getAliasId() + ",");
						}
						log.info("aliasList ->" + aliasList);
						smsDtls.setAlsDetails(lAliasID.toString());
						aliasList = new ArrayList<SMSCDALS>();
					} else {
						log.info("Inside alisLoop else");

					}
					smsList.add(smsDtls);
				}
			}

		} catch (DomainException e) {
			log.error(Utils.getStackTrace(e));
		}
		return smsList;
	}

	public QueryCommListRes queryCommList(String networkId) {
		log.info("Inside NetworkSMSCodesService -> queryCommList");
		QueryCommListRes qryResp = new QueryCommListRes();
		log.debug("Network id ->" + networkId);
		List<String> respList = nwSMSRepo.queryCommDroplist(networkId);
		log.debug("commdList -> " + respList);
		List<QRCOMMLIST> commList = new ArrayList<QRCOMMLIST>();
		try {
			if (respList.size() != 0) {
				for (String mapLoop : respList) {
					QRCOMMLIST commDtls = new QRCOMMLIST();
					commDtls.setCommodityId(mapLoop);
					commList.add(commDtls);
					commDtls = null;
				}
				ERROR = ERROR_CODE.ES_SC_001;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Query CommList", "", ERROR);
		qryResp.setHeader(header);
		qryResp.getQRCOMMLIST().addAll(commList);
		return qryResp;
	}
}
