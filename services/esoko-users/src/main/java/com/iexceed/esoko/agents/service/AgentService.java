package com.iexceed.esoko.agents.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao.UserPictureRepo;
import com.iexceed.esoko.domain.dao2.AgentDetailRepo;
import com.iexceed.esoko.domain.dao2.AgentDetailsHistoryRepo;
import com.iexceed.esoko.domain.dao2.AgentRepo;
import com.iexceed.esoko.domain.dao2.AgentUploadCountRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.SurveyTemplateRepo;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity.UserPicture;
import com.iexceed.esoko.domain.entity2.Agent;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.AgentDetailsHistory;
import com.iexceed.esoko.domain.entity2.AgentDetailsHistoryPK;
import com.iexceed.esoko.domain.entity2.AgentPK;
import com.iexceed.esoko.domain.entity2.AgentUploadCount;
import com.iexceed.esoko.domain.entity2.AgentUploadCountPK;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity2.SurveyTemplate;
import com.iexceed.esoko.domain.entity2.SurveyTemplatePK;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.agents.ACTIVITY;
import com.iexceed.esoko.jaxb.agents.AGENTAPPDTLS;
import com.iexceed.esoko.jaxb.agents.AGNTDTLS;
import com.iexceed.esoko.jaxb.agents.AGNTSUBDTLS;
import com.iexceed.esoko.jaxb.agents.AGNTSUBLOWDTLS;
import com.iexceed.esoko.jaxb.agents.ALLUSRS;
import com.iexceed.esoko.jaxb.agents.AgentdetailsRes;
import com.iexceed.esoko.jaxb.agents.AllusersRes;
import com.iexceed.esoko.jaxb.agents.CHANGEDTLS;
import com.iexceed.esoko.jaxb.agents.CRAGENTDTLS;
import com.iexceed.esoko.jaxb.agents.CRNEWSUBUSRAGENT1;
import com.iexceed.esoko.jaxb.agents.CRSRVTMPLT;
import com.iexceed.esoko.jaxb.agents.CreateAgentRequest;
import com.iexceed.esoko.jaxb.agents.CreateAgentRequestRes;
import com.iexceed.esoko.jaxb.agents.CreateSurveyTemplate;
import com.iexceed.esoko.jaxb.agents.CreateSurveyTemplateRes;
import com.iexceed.esoko.jaxb.agents.DLTTEMPLATS;
import com.iexceed.esoko.jaxb.agents.DeleteTemplateRequest;
import com.iexceed.esoko.jaxb.agents.DeleteTemplateRequestRes;
import com.iexceed.esoko.jaxb.agents.GENREPORTREQ;
import com.iexceed.esoko.jaxb.agents.GENREPORTRES;
import com.iexceed.esoko.jaxb.agents.GenerateReportsReq;
import com.iexceed.esoko.jaxb.agents.GenerateReportsRes;
import com.iexceed.esoko.jaxb.agents.Header;
import com.iexceed.esoko.jaxb.agents.MODAGENTS;
import com.iexceed.esoko.jaxb.agents.ModifyAgentsReq;
import com.iexceed.esoko.jaxb.agents.ModifyAgentsRes;
import com.iexceed.esoko.jaxb.agents.PPLCHK;
import com.iexceed.esoko.jaxb.agents.QRAGENTAPPDTLS;
import com.iexceed.esoko.jaxb.agents.QRAGENTAPPMASDTLS;
import com.iexceed.esoko.jaxb.agents.QRAGENTAPPSUBDTLS;
import com.iexceed.esoko.jaxb.agents.QRAGENTDTLS;
import com.iexceed.esoko.jaxb.agents.QRAGENTSUBDTLS;
import com.iexceed.esoko.jaxb.agents.QRTEMPLATES;
import com.iexceed.esoko.jaxb.agents.QueryAgentDtlsByAppsRes;
import com.iexceed.esoko.jaxb.agents.QueryAgentDtlsRes;
import com.iexceed.esoko.jaxb.agents.QueryTemplatesRes;
import com.iexceed.esoko.jaxb.agents.SaveForMultipleReq;
import com.iexceed.esoko.jaxb.agents.SaveForMultipleRes;
import com.iexceed.esoko.jaxb.agents.USERTODTLS;
import com.iexceed.esoko.jaxb.agents.UsrExstsRes;
import com.iexceed.esoko.jaxb.login.crtusr.CRNEWSUBUSRAGENT;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserReq;
import com.iexceed.esoko.jaxb.login.crtusr.CreateEsokoUserRes;
import com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.iexceed.esoko.signup.service.UserSignUpService;

@Service
public class AgentService {

	private final String serviceName = "AgentService";
	public static Logger log = LoggerUtils.getLogger();

	public AgentService() {

	}

	@Autowired
	AgentDetailRepo agentDtlsRepo;
	@Autowired
	AgentRepo agentRepo;
	@Autowired
	PeopleRepo peopleRepo;
	@Autowired
	SystemUserRepo sysRepo;
	@Autowired
	AgentUploadCountRepo uploadCountRepo;
	@Autowired
	AgentDetailsHistoryRepo agenthistRepo;
	@Autowired
	SystemUserRepo sysUsrRepo;
	@Autowired
	SurveyTemplateRepo srvyTmpltRepo;
	@Autowired
	UserSignUpService usrSignup;
	@Autowired
	UserPictureRepo picrepo;
	Header header = null;
	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;

	@Transactional
	public SaveForMultipleRes saveForMultiple(SaveForMultipleReq req) {
		List<USERTODTLS> usrToList = req.getUSERTODTLS();
		SaveForMultipleRes res = new SaveForMultipleRes();
		int returnCount = 0;
		if (usrToList.size() != 0) {
			for (USERTODTLS map : usrToList) {
				List<CHANGEDTLS> chngDtlsList = req.getCHANGEDTLS();

				for (CHANGEDTLS chngdt : chngDtlsList) {
					returnCount = agentDtlsRepo.saveForMultiple(map
							.getNetworkId(), chngdt.getAppId(), chngdt
							.getParamValue(), chngdt.getParamName(), map
							.getUserId(), chngdt.getAction(), req.getHeader()
							.getUserId());
				}

			}

			ERROR = ERROR_CODE.AGENT_MUL_SV;

		} else {
			ERROR = ERROR_CODE.DM_SV_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Agent status changed", "", ERROR);
		res.setHeader(header);
		return res;

	}

	@Transactional
	public CreateEsokoUserRes createNewUserAgent(CreateEsokoUserReq req) {
		CreateEsokoUserRes res = new CreateEsokoUserRes();
		SystemUser entity = new SystemUser();
		List<com.iexceed.esoko.jaxb.login.crtusr.USERDETAIL> usrDtlList = req
				.getUSERDETAIL();
		CreateEsokoUserRes userRes = usrSignup.createEsokoUser(req);
		for (USERDETAIL usrDetail : usrDtlList) {
			com.iexceed.esoko.jaxb.login.crtusr.Header header1 = req
					.getHeader();

			if (usrDetail.getEmail() != null) {
				entity.setEmail(usrDetail.getEmail());
			}
			if (usrDetail.getPhone() != null) {
				entity.setMsisdn(usrDetail.getPhone());
			}

			if (userRes.getHeader().getType().equals("S"))

			{
				log.info(" am inside S flag");

				String userId = entity.getEmail().toString();

				List<CRNEWSUBUSRAGENT> agentSubList = req.getCRNEWSUBUSRAGENT();
				int returnCount = 0;

				for (CRNEWSUBUSRAGENT agentAppDtls : agentSubList) {

					returnCount = agentDtlsRepo.copyAgentDetails(usrDetail
							.getNetworkId(), agentAppDtls.getSourceUserId(),
							userId, agentAppDtls.getParamName(), req
									.getHeader().getUserId());

				}
				if (returnCount > 0) {
					ERROR = ERROR_CODE.ES_UD_001;
				} else {
					ERROR = ERROR_CODE.ES_NR_001;
				}

				Agent agent = new Agent();
				AgentPK agentpk = new AgentPK();
				agentpk.setNetworkId(usrDetail.getNetworkId());
				agentpk.setUserId(userId);
				agent.setId(agentpk);

				agent.setCurrencyId("USD");
				agent.setCreatedBy(req.getHeader().getUserId());
				agent.setCreatedTs(Utils.getCurrentDate());
				agent.setRecordStatus("A");
				agentRepo.save(agent);
				ERROR = ERROR_CODE.AG_CR_SC;
				header1 = (com.iexceed.esoko.jaxb.login.crtusr.Header) HeaderFactory
						.getHeader(HeaderType.CRTUSR, serviceName,
								"Query templates DtlsRes", "", ERROR);

				res.setHeader(header1);

			}

			else {
				log.info(" am inside E flag");
				res.setHeader(userRes.getHeader());
			}
		}
		return res;
	}

	public QueryTemplatesRes templateDetailsRes(String networkId) {

		log.info("Inside QueryTemplatesRes -> templateDetailsRes");
		log.debug("NetworkId: " + networkId);

		QueryTemplatesRes res = new QueryTemplatesRes();
		List<SurveyTemplate> surveyTmpt = srvyTmpltRepo
				.queryAllTemplates(networkId);
		List<QRTEMPLATES> qrtempList = new ArrayList<QRTEMPLATES>();

		if (surveyTmpt.size() != 0) {
			log.info("Record found");
			for (SurveyTemplate srvytem : surveyTmpt) {
				QRTEMPLATES qrTemplates = new QRTEMPLATES();
				qrTemplates.setCreatedTs(Utils.getDDMMMYYFormat(srvytem
						.getCreated_TS().toString(), 0));
				qrTemplates.setDescription(srvytem.getDescription());
				qrTemplates.setTemplateId(srvytem.getId().getTemplateId());
				qrTemplates.setNetworkId(srvytem.getId().getNetworkId());
				qrtempList.add(qrTemplates);
				qrTemplates = null;

			}
			res.getQRTEMPLATES().addAll(qrtempList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Query templates DtlsRes", "", ERROR);
		res.setHeader(header);
		return res;

	}

	@Transactional
	public CreateSurveyTemplateRes createSurveyTemplateRes(
			CreateSurveyTemplate req) {
		CreateSurveyTemplateRes res = new CreateSurveyTemplateRes();
		CRSRVTMPLT crsSrvTmp = req.getCRSRVTMPLT();
		SurveyTemplate entity = new SurveyTemplate();
		SurveyTemplatePK entitypk = new SurveyTemplatePK();
		SurveyTemplate tmpenity = new SurveyTemplate();
		SurveyTemplatePK tmpenitypk = new SurveyTemplatePK();
		entitypk.setNetworkId(crsSrvTmp.getNetworkId());
		entitypk.setTemplateId(crsSrvTmp.getTemplateId());
		entity.setId(entitypk);
		tmpenitypk.setNetworkId(crsSrvTmp.getNetworkId());
		if(crsSrvTmp.getOldTemplateId() != null)
		{
		tmpenitypk.setTemplateId(crsSrvTmp.getOldTemplateId());
		tmpenity.setId(tmpenitypk);
		}
		entity.setCreatedBy(req.getHeader().getUserId());
		entity.setCreated_TS(Utils.getCurrentDate());
		entity.setAuthStat("U");
		entity.setDescription(crsSrvTmp.getDescription());
		if(crsSrvTmp.getEditFlag().toString().equalsIgnoreCase("E"))
		{
		if (srvyTmpltRepo.exists(entity, entitypk)) {
			log.info("am existing hence inside merge method");
			srvyTmpltRepo.merge(entity);
			ERROR = ERROR_CODE.AGENT_TEMPLATE_ED;
			header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
					serviceName, "Create survey templates", "", ERROR);
		} 
		
		else
		{
			srvyTmpltRepo.save(entity);
			tmpenity = srvyTmpltRepo.findOne(tmpenity, tmpenitypk);
			srvyTmpltRepo.delete(tmpenity);
			ERROR = ERROR_CODE.AGENT_TEMPLATE_ED;
			header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
					serviceName, "Create survey templates", "", ERROR);
		}
		}
		else {
			log.info("i dont exist hence inside save");
			srvyTmpltRepo.save(entity);
			ERROR = ERROR_CODE.AGENT_TEMPLATE_SC;
			header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
					serviceName, "Create survey templates", "", ERROR);
		}

		
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Create survey templates", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public DeleteTemplateRequestRes deleteTemplateRes(DeleteTemplateRequest req) {
		DeleteTemplateRequestRes res = new DeleteTemplateRequestRes();
		DLTTEMPLATS dltTemplt = req.getDLTTEMPLATS();
		SurveyTemplate entity = new SurveyTemplate();
		SurveyTemplatePK entitypk = new SurveyTemplatePK();
		entitypk.setNetworkId(dltTemplt.getNetworkId());
		entitypk.setTemplateId(dltTemplt.getTemplateId());
		entity.setId(entitypk);

		entity = srvyTmpltRepo.findOne(entity, entitypk);

		if (srvyTmpltRepo.exists(entity, entitypk)) {
			log.info("deleting");
			srvyTmpltRepo.delete(entity);
			ERROR = ERROR_CODE.AG_TMP_DL_001;
			header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
					serviceName, "delete survey templates", "", ERROR);
		} else

		{
			ERROR = ERROR_CODE.DM_SV_002;
			header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
					serviceName, "delete survey templates", "", ERROR);
		}

		res.setHeader(header);
		return res;
	}

	public UsrExstsRes usrExtsRes(String networkId, String peopleId) {
		UsrExstsRes res = new UsrExstsRes();
		PeoplePK peoplepk = new PeoplePK();
		People people = new People();
		PPLCHK pplChk = new PPLCHK();

		peoplepk.setDefaultNetworkId(networkId);
		peoplepk.setPeopleId(peopleId);
		if (peopleRepo.exists(people, peoplepk)) {
			pplChk.setFlag("Y");
		} else
			pplChk.setFlag("N");

		res.getPPLCHK().add(pplChk);
		ERROR = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Query All Users", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public ModifyAgentsRes modifyAgentsDtls(ModifyAgentsReq req) {
		List<MODAGENTS> modifyAgentsList = req.getMODAGENTS();
		ModifyAgentsRes res = new ModifyAgentsRes();
		int returnCount = 0;
		String activity = null;
		if (modifyAgentsList.size() != 0) {
			for (MODAGENTS modAgents : modifyAgentsList) {
				returnCount = agentDtlsRepo.deleteSuspendAgents(
						modAgents.getNetworkId(), modAgents.getFlag(),
						modAgents.getAgentId(), req.getHeader().getUserId());
				activity = modAgents.getFlag().toString();

			}
			if (returnCount > 0) {
				if(activity.equalsIgnoreCase("S"))
				{
				ERROR = ERROR_CODE.AGENT_STATUS_ED;
				}
				else
				{
					ERROR = ERROR_CODE.AGENT_STATUS_ED1;	
				}
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		} else {
			ERROR = ERROR_CODE.DM_SV_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Agent status changed", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryAgentDtlsRes queryAgentDtlsRes(String networkId, String flagType) {
		log.info("Inside QueryAgentDtlsRes -> queryAgentDtlsRes");
		log.debug("NetworkId: " + networkId);

		String newPeopleId = null;
		String prevPeopleId = null;

		QRAGENTDTLS qrAgentDtls = null;
		QueryAgentDtlsRes res = new QueryAgentDtlsRes();
		List<Map<String, Object>> mapList = agentDtlsRepo.queryAgents(
				networkId, flagType);
		log.debug("mapList::" + mapList);
		List<QRAGENTDTLS> qrAgntDtlsList = new ArrayList<QRAGENTDTLS>();

		if (mapList.size() != 0) {
			log.info("Record found");
			for (Map<String, Object> map : mapList) {
				log.debug("Map::" + map);
				if (map.get("people_id") != null) {
					newPeopleId = map.get("people_id").toString();
				}
				if (!newPeopleId.equals(prevPeopleId)) {

					qrAgentDtls = new QRAGENTDTLS();
					if (map.get("first_name") != null) {
						if (map.get("last_name") != null) {
							qrAgentDtls.setName(map.get("first_name")
									.toString()
									+ " "
									+ map.get("last_name").toString());
						}

						else {
							qrAgentDtls.setName(map.get("first_name")
									.toString());
						}
					}
					if (map.get("town") != null) {
						qrAgentDtls.setTown(map.get("town").toString());
					}
					if (map.get("country") != null) {
						qrAgentDtls.setCountry(map.get("country").toString());
					}
					if (map.get("people_id") != null) {
						qrAgentDtls
								.setPeopleId(map.get("people_id").toString());
					}

					UserPicture pictureEntity = picrepo.queryUserPic(map.get(
							"people_id").toString());
					if (pictureEntity != null) {
						qrAgentDtls.setProfilePic(pictureEntity.getContent());
					}
				}
				QRAGENTSUBDTLS qrAgentSubdtls = new QRAGENTSUBDTLS();
				if (map.get("location_id") != null) {
					String locations = map.get("location_id").toString();
					String[] locationsarr = null;
					if (locations.indexOf(",") != -1) {
						locationsarr = locations.split(",");
						qrAgentSubdtls.setLocationId(locationsarr.length
								+ " Locations");
					} else {
						qrAgentSubdtls.setLocationId(map.get("location_id")
								.toString());
					}
				}

				if (map.get("location_id") != null) {
					qrAgentSubdtls.setLocationList(map.get("location_id")
							.toString());
				}
				if (map.get("commodity_id") != null) {
					String commodities = map.get("commodity_id").toString();
					String[] commditiesArr = null;
					if (commodities.indexOf(",") != -1) {
						commditiesArr = commodities.split(",");
						qrAgentSubdtls.setCommodityId(commditiesArr.length
								+ " Commodities");
					} else {
						qrAgentSubdtls.setCommodityId(map.get("commodity_id")
								.toString());
					}
				}
				if (map.get("commodity_id") != null) {
					qrAgentSubdtls.setCommodityList(map.get("commodity_id")
							.toString());
				}
				if (map.get("template") != null) {
					String templates = map.get("template").toString();
					String[] templatesArr = null;
					if (templates.indexOf(",") != -1) {
						templatesArr = templates.split(",");
						qrAgentSubdtls.setTemplate(templatesArr.length
								+ " Forms");
					} else {
						qrAgentSubdtls.setTemplate(map.get("template")
								.toString());
					}
				}
				if (map.get("template") != null) {
					qrAgentSubdtls.setTemplateList(map.get("template")
							.toString());
				}

				if (map.get("target") != null) {
					qrAgentSubdtls.setTarget(map.get("target").toString());
				}

				if (map.get("application_id") != null) {

					qrAgentSubdtls.setApplicationId(map.get("application_id")
							.toString());
				}

				qrAgentDtls.getQRAGENTSUBDTLS().add(qrAgentSubdtls);
				if (!newPeopleId.equals(prevPeopleId)) {
					qrAgntDtlsList.add(qrAgentDtls);
				}

				prevPeopleId = newPeopleId;

			}
			res.getQRAGENTDTLS().addAll(qrAgntDtlsList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Query Agent DtlsRes", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryAgentDtlsByAppsRes queryAgentAppsDtsRes(String networkId) {
		log.info("Inside QueryAgentDtlsByAppsRes -> queryAgentAppsDtsRes");
		log.debug("NetworkId: " + networkId);

		String newPeopleId = null;
		String prevPeopleId = null;
		String newAppId = null;
		String prevAppId = null;
		QRAGENTAPPMASDTLS qrAgentAppMasDtls = null;
		QueryAgentDtlsByAppsRes res = new QueryAgentDtlsByAppsRes();
		List<Map<String, Object>> mapList = agentDtlsRepo
				.queryAgentsByApplication(networkId);
		log.debug("mapList::" + mapList);
		List<QRAGENTAPPMASDTLS> qrAgntByAppDtlsList = new ArrayList<QRAGENTAPPMASDTLS>();
		List<QRAGENTAPPDTLS> subAppDtlsList = new ArrayList<QRAGENTAPPDTLS>();
		List<QRAGENTAPPSUBDTLS> subLowDtlsList = new ArrayList<QRAGENTAPPSUBDTLS>();
		int count = 0;
		QRAGENTAPPDTLS qrAgentAppDtls = new QRAGENTAPPDTLS();

		if (mapList.size() != 0) {
			log.info("Record found");
			for (Map<String, Object> map : mapList) {
				log.debug("Map::" + map);

				if (map.get("application_id") != null) {
					newAppId = map.get("application_id").toString();

				}
				if (newAppId != null) {
					if (!newAppId.equals(prevAppId)) {
						count = 0;
						prevPeopleId = newPeopleId = null;
						qrAgentAppMasDtls = new QRAGENTAPPMASDTLS();
						if (map.get("application_id") != null) {
							qrAgentAppMasDtls.setApplicationId(map.get(
									"application_id").toString());
						}
					}
					subAppDtlsList.clear();
				}

				if (map.get("people_id") != null) {
					newPeopleId = map.get("people_id").toString();
				}

				if (!newPeopleId.equals(prevPeopleId)) {
					count++;
					qrAgentAppDtls = null;
					qrAgentAppDtls = new QRAGENTAPPDTLS();
					if (map.get("first_name") != null) {
						if (map.get("last_name") != null) {
							qrAgentAppDtls.setName(map.get("first_name")
									.toString()
									+ " "
									+ map.get("last_name").toString());
						} else {
							qrAgentAppDtls.setName(map.get("first_name")
									.toString());

						}
					}

					if (map.get("people_id") != null) {
						qrAgentAppDtls.setPeopleId(map.get("people_id")
								.toString());
					}
					subLowDtlsList.clear();

				}
				QRAGENTAPPSUBDTLS qrAgentAppSubDtls = new QRAGENTAPPSUBDTLS();
				if (map.get("location_id") != null) {
					String locations = map.get("location_id").toString();
					String[] locationsarr = null;
					if (locations.indexOf(",") != -1) {
						locationsarr = locations.split(",");
						qrAgentAppSubDtls.setLocationId(locationsarr.length
								+ "");
					} else {
						qrAgentAppSubDtls.setLocationId(map.get("location_id")
								.toString());
					}
				}
				if (map.get("location_id") != null) {
					qrAgentAppSubDtls.setLocationList(map.get("location_id")
							.toString());
				}

				if (map.get("commodity_id") != null) {
					String commodities = map.get("commodity_id").toString();
					String[] commditiesArr = null;
					if (commodities.indexOf(",") != -1) {
						commditiesArr = commodities.split(",");
						qrAgentAppSubDtls.setCommodityId(commditiesArr.length
								+ "");
					} else {
						qrAgentAppSubDtls.setCommodityId(map
								.get("commodity_id").toString());
					}
				}
				if (map.get("commodity_id") != null) {
					qrAgentAppSubDtls.setCommodityList(map.get("commodity_id")
							.toString());
				}
				if (map.get("template") != null) {
					qrAgentAppSubDtls.setTemplate(map.get("template")
							.toString());
				}
				if (map.get("target") != null) {
					qrAgentAppSubDtls.setTarget(map.get("target").toString());
				}
				if (map.get("incentive") != null) {
					qrAgentAppSubDtls.setIncentive(map.get("incentive")
							.toString());
				}
				if (map.get("bonus") != null) {
					qrAgentAppSubDtls.setBonus(map.get("bonus").toString());
				}

				qrAgentAppMasDtls.setCount(count + "");

				qrAgentAppDtls.getQRAGENTAPPSUBDTLS().add(qrAgentAppSubDtls);

				if (!newPeopleId.equals(prevPeopleId)) {
					subAppDtlsList.add(qrAgentAppDtls);
					subLowDtlsList = null;
					subLowDtlsList = new ArrayList<QRAGENTAPPSUBDTLS>();

				}

				qrAgentAppMasDtls.getQRAGENTAPPDTLS().addAll(subAppDtlsList);
				if (!newAppId.equals(prevAppId)) {
					qrAgntByAppDtlsList.add(qrAgentAppMasDtls);
					subAppDtlsList = null;
					subAppDtlsList = new ArrayList<QRAGENTAPPDTLS>();

				}

				prevPeopleId = newPeopleId;
				prevAppId = newAppId;

			}

			res.getQRAGENTAPPMASDTLS().addAll(qrAgntByAppDtlsList);
			ERROR = ERROR_CODE.ES_SC_001;
		}

		else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Query Agent DtlsRes", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public GenerateReportsRes generateReports(GenerateReportsReq req) {
		log.info("Inside AgentService -> generateReports");
		GENREPORTREQ reportReq = req.getGENREPORTREQ();
		String agentId = reportReq.getAgentId();
		log.debug("AgentId: " + agentId);
		String networkId = reportReq.getNetworkId();
		log.debug("NetworkId: " + networkId);
		String appId = reportReq.getApplicationId();
		log.debug("ApplicationId: " + appId);
		String startDate = reportReq.getStartDate();
		log.debug("StartDate: " + startDate);
		String endDate = reportReq.getEndDate();
		log.debug("EndDate: " + endDate);
		GenerateReportsRes res = new GenerateReportsRes();
		List<Map<String, Object>> agntTrgtLst = agentDtlsRepo
				.queryAgentTargets(networkId, appId, agentId, startDate,
						endDate);
		if (agntTrgtLst.size() == 0) {
			ERROR = ERROR_CODE.ES_NR_001;
			log.info("No data found");
		} else {
			List<String> applicationList = agentDtlsRepo
					.getApplicationList(networkId);
			List<String> effDateList = getListOfMonths(startDate, endDate);

			StringBuffer effDateStr = new StringBuffer();
			for (int i = 0; i < effDateList.size(); i++) {
				if (i == 0) {
					effDateStr.append("'" + effDateList.get(i) + "'");
				} else {
					effDateStr.append(",'" + effDateList.get(i) + "'");
				}
			}

			Map<String, Map<String, Map<String, String>>> agentUploads = agentDtlsRepo
					.queryAgentUploads(networkId, appId, agentId,
							effDateStr.toString());
			Map<String, Map<String, String>> resultSet = new HashMap<String, Map<String, String>>();
			for (int i = 0; i < effDateList.size(); i++) {
				resultSet
						.put(effDateList.get(i), new HashMap<String, String>());
			}
			List<String> tmpEffDtList = new ArrayList<String>();
			for (Map<String, Object> map : agntTrgtLst) {
				if ((map.get("application_id") != null)
						&& (map.get("target") != null)
						&& (map.get("effectiveDate") != null)) {
					String applicationId = map.get("application_id").toString();
					String target = map.get("target").toString();
					String effectiveDate = map.get("effectiveDate").toString();
					tmpEffDtList.add(effectiveDate);
					Map<String, String> tmpMap = resultSet.get(effectiveDate);
					if (target.equals("0")) {
						tmpMap.put(applicationId, "NA");
					} else {
						tmpMap.put(applicationId, target);
					}

				}
			}

			List<GENREPORTRES> reportResLst = new ArrayList<GENREPORTRES>();
			List<ACTIVITY> reportsActList = new ArrayList<ACTIVITY>();
			String[] tempVals = new String[applicationList.size()];

			for (String effDate : effDateList) {
				Map<String, String> agentTargetMap = resultSet.get(effDate);
				Map<String, Map<String, String>> agentUploadMap = agentUploads
						.get(effDate);

				if (agentTargetMap.size() != applicationList.size()) {
					Set<String> set = agentTargetMap.keySet();
					for (String application : applicationList) {
						if (!set.contains(application)) {
							agentTargetMap.put(application, "NA");
						}
					}
				}
				if (tmpEffDtList.contains(effDate)) {
					int i = 0;
					for (String application : applicationList) {
						tempVals[i] = agentTargetMap.get(application);
						i++;
					}
				} else {
					int i = 0;
					for (String application : applicationList) {
						agentTargetMap.put(application, tempVals[i]);
						i++;
					}
				}

				float costSum = 0.0f;
				int uploadSum = 0;
				int targetSum = 0;
				GENREPORTRES reportRes = new GENREPORTRES();
				for (String application : applicationList) {
					if (application.equalsIgnoreCase("Contacts")) {
						String target = agentTargetMap.get(application);
						int upload = 0;
						if (agentUploadMap != null) {
							Map<String, String> map = agentUploadMap
									.get(application);
							if (map != null) {
								upload = Integer.parseInt(map.get("uploads"));
								uploadSum += upload;
								costSum += Float.parseFloat(map.get("cost"));
							}
						}
						if (!target.equals("NA")) {
							targetSum += Integer.parseInt(target);
						}
						reportRes.setContacts(upload + " / " + target);

					} else if (application.equalsIgnoreCase("Offers")) {
						String target = agentTargetMap.get(application);
						int upload = 0;
						if (agentUploadMap != null) {
							Map<String, String> map = agentUploadMap
									.get(application);
							if (map != null) {
								upload = Integer.parseInt(map.get("uploads"));
								uploadSum += upload;
								costSum += Float.parseFloat(map.get("cost"));
							}
						}
						if (!target.equals("NA")) {
							targetSum += Integer.parseInt(target);
						}
						reportRes.setOffers(upload + " / " + target);
					} else if (application.equalsIgnoreCase("Prices")) {
						String target = agentTargetMap.get(application);
						int upload = 0;
						if (agentUploadMap != null) {
							Map<String, String> map = agentUploadMap
									.get(application);
							if (map != null) {
								upload = Integer.parseInt(map.get("uploads"));
								uploadSum += upload;
								costSum += Float.parseFloat(map.get("cost"));
							}
						}
						if (!target.equals("NA")) {
							targetSum += Integer.parseInt(target);
						}
						reportRes.setPrices(upload + " / " + target);
					} else if (application.equalsIgnoreCase("newsAndLibrary")) {
						String target = agentTargetMap.get(application);
						int upload = 0;
						if (agentUploadMap != null) {
							Map<String, String> map = agentUploadMap
									.get(application);
							if (map != null) {
								upload = Integer.parseInt(map.get("uploads"));
								uploadSum += upload;
								costSum += Float.parseFloat(map.get("cost"));
							}
						}
						if (!target.equals("NA")) {
							targetSum += Integer.parseInt(target);
						}
						reportRes.setNewsLibrary(upload + " / " + target);
					} else if (application.equalsIgnoreCase("Survey")) {
						String target = agentTargetMap.get(application);
						int upload = 0;
						if (agentUploadMap != null) {
							Map<String, String> map = agentUploadMap
									.get(application);
							if (map != null) {
								upload = Integer.parseInt(map.get("uploads"));
								uploadSum += upload;
								costSum += Float.parseFloat(map.get("cost"));
							}
						}
						if (!target.equals("NA")) {
							targetSum += Integer.parseInt(target);
						}
						reportRes.setSurvey(upload + " / " + target);
					}
				}
				String[] tmpDate = effDate.split("-");
				String year = tmpDate[0];
				String month = Utils.getMonth(Integer.valueOf(tmpDate[1]));

				String reportDate = month + " " + year;

				reportRes.setMonth(reportDate);
				reportRes.setCost("" + costSum);

				float percentage = 0.0f;
				if (targetSum != 0) {
					percentage = (((float) uploadSum / (float) targetSum) * 100);
				}
				reportRes.setPercentTarget(""
						+ Utils.roundDoubleVal(new Double(percentage), 2));
				reportResLst.add(reportRes);

				ACTIVITY targets = new ACTIVITY();
				targets.setMonth(reportDate);
				targets.setType("Target");
				targets.setCount("" + targetSum);
				reportsActList.add(targets);

				ACTIVITY uploads = new ACTIVITY();
				uploads.setMonth(reportDate);
				uploads.setType("Upload");
				uploads.setCount("" + uploadSum);
				reportsActList.add(uploads);
			}

			res.getGENREPORTRES().addAll(reportResLst);
			res.getACTIVITY().addAll(reportsActList);
			ERROR = ERROR_CODE.ES_SC_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Generate Reports", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public CreateAgentRequestRes createAgentRes(CreateAgentRequest req) {
		CreateAgentRequestRes res = new CreateAgentRequestRes();
		CRAGENTDTLS agentDtls = req.getCRAGENTDTLS();
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int effectiveMonth = 0;
		Agent entity = new Agent();
		AgentPK entityPk = new AgentPK();
		entityPk.setNetworkId(agentDtls.getNetworkId());
		entityPk.setUserId(agentDtls.getPeopleId());
		entity.setId(entityPk);
		if (StringUtils.isNotEmpty(agentDtls.getBonus())) {
			entity.setBonus(BigDecimal.valueOf(Double.parseDouble(agentDtls
					.getBonus())));
		}
		if (StringUtils.isNotEmpty(agentDtls.getSalary())) {
			entity.setSalary(BigDecimal.valueOf(Double.parseDouble(agentDtls
					.getSalary())));
		}
		entity.setCurrencyId("USD");
		entity.setCreatedBy(req.getHeader().getUserId());
		entity.setCreatedTs(Utils.getCurrentDate());
		entity.setRecordStatus("A");

		if (!agentRepo.exists(entity, entityPk)) {
			agentRepo.save(entity);
			ERROR = ERROR_CODE.AG_CR_SC;
		} else {
			agentRepo.merge(entity);
			ERROR = ERROR_CODE.AG_UD_SC;
		}
		if(agentDtls.getCopyFlag().toString().equalsIgnoreCase("CF"))
		{
			
			List<CRNEWSUBUSRAGENT1> agentSubList = req.getCRNEWSUBUSRAGENT1();
			int returnCount = 0;

			for (CRNEWSUBUSRAGENT1 agentAppDtls : agentSubList) {

				returnCount = agentDtlsRepo.copyAgentDetails(agentDtls.getNetworkId(), agentAppDtls.getSourceUserId(),
						agentDtls.getPeopleId(), agentAppDtls.getParamName(), req
								.getHeader().getUserId());

			}
			if (returnCount > 0) {
				ERROR = ERROR_CODE.AG_CR_SC;
			} else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
			
		}
		
		else
		{
		List<AGENTAPPDTLS> agentAppDtlsList = agentDtls.getAGENTAPPDTLS();
		for (AGENTAPPDTLS agentAppDtls : agentAppDtlsList) {
			AgentDetail agentDtlsEntity = new AgentDetail();
			agentDtlsEntity.setApplicationId(agentAppDtls.getApplicationId());
			if (StringUtils.isNotEmpty(agentAppDtls.getDetailId())) {
				agentDtlsEntity.setDetailId(Integer.parseInt(agentAppDtls
						.getDetailId()));

				if (StringUtils.isNotEmpty(agentAppDtls.getActionFlag())) {
					agentDtlsEntity.setRecordStatus("D");
				}

				else {
					agentDtlsEntity.setRecordStatus("A");
				}

			}
			if (StringUtils.isEmpty(agentAppDtls.getDetailId())) {
				agentDtlsEntity.setRecordStatus("A");
			}
			if (StringUtils.isNotEmpty(agentAppDtls.getCommodityId())) {
				agentDtlsEntity.setCommodityId(agentAppDtls.getCommodityId());
			}

			agentDtlsEntity.setCurrencyId("USD");
			if (StringUtils.isNotEmpty(agentAppDtls.getLocationId())) {
				agentDtlsEntity.setLocationId(agentAppDtls.getLocationId());
			}
			agentDtlsEntity.setNetworkId(agentDtls.getNetworkId());
			agentDtlsEntity.setUserId(agentDtls.getPeopleId());
			if (StringUtils.isNotEmpty(agentAppDtls.getTemplate())) {
				agentDtlsEntity.setTemplate(agentAppDtls.getTemplate());
			}
			if (StringUtils.isNotEmpty(agentAppDtls.getApplicationBonus())) {
				agentDtlsEntity.setBonus(BigDecimal.valueOf(Double
						.parseDouble(agentAppDtls.getApplicationBonus())));
			}
			if (agentAppDtls.getIncentive() != null) {
				agentDtlsEntity.setIncentive(BigDecimal.valueOf(Double
						.parseDouble(agentAppDtls.getIncentive())));
			}
			if (agentAppDtls.getTarget() != null) {
				agentDtlsEntity.setTarget(Integer.parseInt(agentAppDtls
						.getTarget()));
			}
			agentDtlsEntity.setEffectiveDate(Utils.getCurrentDate());

			if (StringUtils.isEmpty(agentAppDtls.getDetailId())) {
				agentDtlsEntity.setCreatedBy(req.getHeader().getUserId());
				agentDtlsEntity.setCreatedTs(Utils.getCurrentDate());
				agentDtlsRepo.save(agentDtlsEntity);
			} else {
				agentDtlsEntity.setModifiedBy(req.getHeader().getUserId());
				agentDtlsEntity.setModifiedTs(Utils.getCurrentDate());
				AgentDetail agentDtlsEntity1 = new AgentDetail();
				agentDtlsEntity1 = agentDtlsRepo.findAgentByDetailId(Integer
						.parseInt(agentAppDtls.getDetailId()));
				
				if(agentDtlsEntity1.getCreatedBy() != null)
				{
				agentDtlsEntity.setCreatedBy(agentDtlsEntity1.getCreatedBy());
				}
				if(agentDtlsEntity1.getCreatedTs() != null)
				{
				agentDtlsEntity.setCreatedTs(agentDtlsEntity1.getCreatedTs());
				}
				try {
					Date date = Utils.getFormatedDateTimeStamp4(agentDtlsEntity1
							.getEffectiveDate().toString());
					effectiveMonth = Utils.getMonthFromDate(date);

					if (effectiveMonth == currentMonth) {
						log.debug("inside month comp: " + effectiveMonth);
						agentDtlsRepo.merge(agentDtlsEntity);
					} else {
						log.debug("lastloop " + effectiveMonth);
						AgentDetailsHistory agentHistDtlsEntity = new AgentDetailsHistory();
						AgentDetailsHistoryPK agentHistPkDtlsEntity = new AgentDetailsHistoryPK();
						agentHistPkDtlsEntity.setDetailId(agentDtlsEntity1
								.getDetailId());
						agentHistPkDtlsEntity.setEffectiveDate(agentDtlsEntity1
								.getEffectiveDate());
						agentHistDtlsEntity.setId(agentHistPkDtlsEntity);
						if (agentDtlsEntity1.getApplicationId() != null) {
							agentHistDtlsEntity
									.setApplicationId(agentDtlsEntity1
											.getApplicationId());
						}
						if (agentDtlsEntity1.getBonus() != null) {
							agentHistDtlsEntity.setBonus(agentDtlsEntity1
									.getBonus());
						}
						if (agentDtlsEntity1.getCommodityId() != null) {
							agentHistDtlsEntity.setCommodityId(agentDtlsEntity1
									.getCommodityId());
						}
						agentHistDtlsEntity.setCreatedBy("System");
						agentHistDtlsEntity.setCreatedTs(new Timestamp(Utils
								.getCurrentDate().getTime()));
						if (agentDtlsEntity1.getCurrencyId() != null) {
							agentHistDtlsEntity.setCurrencyId(agentDtlsEntity1
									.getCurrencyId());
						}

						if (agentDtlsEntity1.getIncentive() != null) {
							agentHistDtlsEntity.setIncentive(agentDtlsEntity1
									.getIncentive());
						}
						if (agentDtlsEntity1.getLocationId() != null) {
							agentHistDtlsEntity.setLocationId(agentDtlsEntity1
									.getLocationId());
						}
						if (agentDtlsEntity1.getNetworkId() != null) {
							agentHistDtlsEntity.setNetworkId(agentDtlsEntity1
									.getNetworkId());
						}
						if (agentDtlsEntity1.getTarget() != 0) {
							agentHistDtlsEntity.setTarget(agentDtlsEntity1
									.getTarget());
						}
						if (agentDtlsEntity1.getTemplate() != null) {
							agentHistDtlsEntity.setTemplate(agentDtlsEntity1
									.getTemplate());
						}
						if (agentDtlsEntity1.getUserId() != null) {
							agentHistDtlsEntity.setUserId(agentDtlsEntity1
									.getUserId());
						}
						agenthistRepo.save(agentHistDtlsEntity);
						agentDtlsRepo.merge(agentDtlsEntity);
					}
				} catch (Exception e) {
					Utils.getStackTrace(e);
				}
			}
		}
		}
		People people = new People();
		PeoplePK peoplepk = new PeoplePK();

		peoplepk.setDefaultNetworkId(agentDtls.getNetworkId());
		peoplepk.setPeopleId(agentDtls.getPeopleId());

		if (!peopleRepo.exists(people, peoplepk)) {
			SystemUser systemEntity = new SystemUser();
			systemEntity.setUserId(agentDtls.getPeopleId());
			systemEntity = sysRepo.findOne(systemEntity,
					systemEntity.getUserId());
			peoplepk.setPeopleId(systemEntity.getUserId());
			peoplepk.setDefaultNetworkId(agentDtls.getNetworkId());
			people.setId(peoplepk);
			if (StringUtils.isNotEmpty(systemEntity.getAddress())) {
				people.setAdd1(systemEntity.getAddress());
			}
			if (StringUtils.isNotEmpty(systemEntity.getBirthYear())) {
				people.setBirthyear(systemEntity.getBirthYear());
			}

			if (StringUtils.isNotEmpty(systemEntity.getCountry())) {
				people.setCountry(systemEntity.getCountry());
			}
			people.setCreatedBy(req.getHeader().getUserId());
			people.setCreatedTs(new Timestamp(Utils.getCurrentDate().getTime()));
			if (StringUtils.isNotEmpty(systemEntity.getCurrencyId())) {
				people.setCurrencyId(systemEntity.getCurrencyId());
			}
			if (StringUtils.isNotEmpty(systemEntity.getEmail())) {
				people.setEmail(systemEntity.getEmail());
			}
			people.setFirstName(systemEntity.getFirstName());
			people.setIsVisible(systemEntity.getIsVisible());
			people.setGender(systemEntity.getGender());
			people.setLanguageId(systemEntity.getLanguageId());
			if (StringUtils.isNotEmpty(systemEntity.getLastName())) {
				people.setLastName(systemEntity.getLastName());
			}
			people.setMasterFlag("N");
			people.setMsisdn(systemEntity.getMsisdn());
			people.setMsisdn2(systemEntity.getMsisdn2());
			people.setNickname(systemEntity.getNickname());
			people.setRecordStatus("A");
			people.setTown(systemEntity.getTown());
			people.setWebsite(systemEntity.getWebsite());
			peopleRepo.save(people);

		}

		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Create Agent Details", "", ERROR);
		res.setHeader(header);
		return res;
	}

	private List<String> getListOfMonths(String startDateStr, String endDateStr) {
		String[] tmp1 = startDateStr.split("-");
		int strtYrInt = Integer.valueOf(tmp1[0]);
		int strtMnthInt = Integer.valueOf(tmp1[1]);
		startDateStr = strtYrInt + "-" + strtMnthInt + "-01";
		List<String> effDateList = new ArrayList<String>();
		try {
			Date strtDate = new SimpleDateFormat("yyyy-MM-dd")
					.parse(startDateStr);
			Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
			int diff = differenceInMonths(strtDate, endDate);
			for (int i = 0; i <= diff; i++) {
				effDateList.add(strtYrInt + "-" + strtMnthInt);
				if (strtMnthInt == 12) {
					strtYrInt++;
					strtMnthInt = 1;
				} else {
					strtMnthInt++;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return effDateList;
	}

	private int differenceInMonths(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		int diff = 0;
		if (c2.after(c1)) {
			while (c2.after(c1)) {
				c1.add(Calendar.MONTH, 1);
				if (c2.after(c1)) {
					diff++;
				}
			}
		} else if (c2.before(c1)) {
			while (c2.before(c1)) {
				c1.add(Calendar.MONTH, -1);
				if (c1.before(c2)) {
					diff--;
				}
			}
		}
		return diff;
	}

	public void updateAgentUploads(int detailId, String uploadDate) {
		log.info("Inside AgentService -> updateAgentUploads");
		log.debug("DetailId: " + detailId);
		log.debug("UploadDate: " + uploadDate);
		String[] temp = uploadDate.split("-");
		uploadDate = temp[0] + "-" + temp[1];
		AgentUploadCountPK uploadPk = new AgentUploadCountPK();
		uploadPk.setDetailId(detailId);
		uploadPk.setEffectiveDate(uploadDate);
		AgentUploadCount agentUpload = uploadCountRepo.findOne(
				AgentUploadCount.class, uploadPk);
		if (agentUpload == null) {
			log.debug("No record found");
			agentUpload = new AgentUploadCount();
			agentUpload.setId(uploadPk);
			agentUpload.setUploads(1);
			uploadCountRepo.save(agentUpload);
			log.debug("Record saved");
		} else {
			log.debug("Record found");
			int count = agentUpload.getUploads();
			log.debug("Number of uploads: " + count);
			count++;
			agentUpload.setUploads(count);
			uploadCountRepo.merge(agentUpload);
			log.debug("Record updated");
		}
	}

	public AgentdetailsRes agentDtsRes(String networkId, String peopleId) {
		log.info("Inside AgentdetailsRes -> agentDtsRes");
		log.debug("NetworkId: " + networkId);
		log.debug("NetworkId: " + peopleId);

		String newAppId = null;
		String prevAppId = null;
		AGNTDTLS agDtls = new AGNTDTLS();
		AgentdetailsRes res = new AgentdetailsRes();
		List<Map<String, Object>> mapList = agentDtlsRepo.queryAgentDetails(
				networkId, peopleId);
		log.debug("mapList::" + mapList);
		List<AGNTSUBDTLS> qrAgntsubDtlsList = new ArrayList<AGNTSUBDTLS>();
		List<AGNTSUBLOWDTLS> qrAgntsublowDtlsList = new ArrayList<AGNTSUBLOWDTLS>();
		AGNTSUBDTLS agntsubdtls = new AGNTSUBDTLS();
		if (mapList.size() != 0) {
			log.info("Record found");

			/*
			 * Map<String, List<Map<String,String>>> tmpMap = new HashMap
			 * <String, List<Map<String,String>>>(); for (Map<String, Object>
			 * map : mapList) {
			 * 
			 * }
			 * 
			 * ///////////////////////////////////////
			 */
			for (Map<String, Object> map : mapList) {
				log.debug("Map::" + map);

				if (map.get("first_name") != null) {
					if (map.get("last_name") != null) {
						agDtls.setName(map.get("first_name").toString() + " "
								+ map.get("last_name").toString());
					} else {
						agDtls.setName(map.get("first_name").toString());
					}
				}
				if (map.get("town") != null) {
					agDtls.setTown(map.get("town").toString());
				}
				if (map.get("country") != null) {
					agDtls.setCountry(map.get("country").toString());
				}
				if (map.get("people_id") != null) {
					agDtls.setPeopleId(map.get("people_id").toString());
				}

				UserPicture pictureEntity = picrepo.queryUserPic(map.get(
						"people_id").toString());
				if (pictureEntity != null) {
					agDtls.setProfilePic(pictureEntity.getContent());
				}
				if (map.get("mobile") != null) {
					agDtls.setMobile(map.get("mobile").toString());
				}
				if (map.get("email") != null) {
					agDtls.setEmail(map.get("email").toString());
				}
				if (map.get("salary") != null) {
					agDtls.setSalary(map.get("salary").toString());
				}
				if (map.get("monthlybonus") != null) {
					agDtls.setMontlybonus(map.get("monthlybonus").toString());
				}

				if (map.get("application_id") != null) {
					newAppId = map.get("application_id").toString();

				}

				if (newAppId != null) {
					if (!newAppId.equals(prevAppId)) {
						agntsubdtls = null;
						agntsubdtls = new AGNTSUBDTLS();
						if (map.get("application_id") != null) {
							agntsubdtls.setApplicationId(map.get(
									"application_id").toString());
						}
						qrAgntsublowDtlsList.clear();

					}
				}

				AGNTSUBLOWDTLS agntsublowdtls = new AGNTSUBLOWDTLS();

				if (map.get("location_id") != null) {

					agntsublowdtls.setLocationId(map.get("location_id")
							.toString());
				}
				if (map.get("location_count") != null) {

					agntsublowdtls.setLocationCount(map.get("location_count")
							.toString());
				}
				if (map.get("commodity_id") != null) {
					agntsublowdtls.setCommodityId(map.get("commodity_id")
							.toString());

				}
				if (map.get("commodity_count") != null) {
					agntsublowdtls.setCommodityCount(map.get("commodity_count")
							.toString());

				}
				if (map.get("template") != null) {
					agntsublowdtls.setTemplate(map.get("template").toString());
				}
				if (map.get("template_count") != null) {
					agntsublowdtls.setTemplateCount(map.get("template_count")
							.toString());
				}
				if (map.get("target") != null) {
					agntsublowdtls.setTarget(map.get("target").toString());
				}
				if (map.get("incentive") != null) {
					agntsublowdtls
							.setIncentive(map.get("incentive").toString());
				}
				if (map.get("bonus") != null) {
					agntsublowdtls.setBonus(map.get("bonus").toString());
				}
				if (map.get("detail_id") != null) {
					agntsublowdtls.setDetailId(map.get("detail_id").toString());
				}

				agntsubdtls.getAGNTSUBLOWDTLS().add(agntsublowdtls);

				if (!newAppId.equals(prevAppId)) {

					qrAgntsubDtlsList.add(agntsubdtls);
					qrAgntsublowDtlsList = null;
					qrAgntsublowDtlsList = new ArrayList<AGNTSUBLOWDTLS>();

				}
				prevAppId = newAppId;
			}
			agDtls.getAGNTSUBDTLS().addAll(qrAgntsubDtlsList);
			res.setAGNTDTLS(agDtls);
			ERROR = ERROR_CODE.ES_SC_001;
		}

		else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Query Agent DtlsRes", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public AllusersRes allusersRes() {
		log.info("Inside allusersRes -> allusersRes");

		AllusersRes res = new AllusersRes();
		List<Map<String, Object>> mapList = sysUsrRepo.queryAllUsers();
		log.debug("mapList::" + mapList);
		List<ALLUSRS> allUsrList = new ArrayList<ALLUSRS>();
		if (mapList.size() != 0) {

			for (Map<String, Object> map : mapList) {
				ALLUSRS allUrs = new ALLUSRS();
				if (map.get("user_id") != null) {
					allUrs.setUserId(map.get("user_id").toString());
				}
				if (map.get("first_name") != null) {
					if (map.get("last_name") != null) {
						allUrs.setName(map.get("first_name").toString() + " "
								+ map.get("last_name").toString());
					} else {
						allUrs.setName(map.get("first_name").toString());
					}
				}
				if (map.get("email") != null) {
					allUrs.setEmail(map.get("email").toString());
				}
				if (map.get("msisdn") != null) {
					allUrs.setMsisdn(map.get("msisdn").toString());
				}
				if (map.get("town") != null) {
					allUrs.setTown(map.get("town").toString());
				}
				if (map.get("country") != null) {
					allUrs.setCountry(map.get("country").toString());
				}
				if (map.get("networkId") != null) {
					allUrs.setNetworkId(map.get("networkId").toString());
				}

				UserPicture pictureEntity = picrepo.queryUserPic(map.get(
						"user_id").toString());
				if (pictureEntity != null) {
					allUrs.setPhoto(pictureEntity.getContent());
				}

				allUsrList.add(allUrs);

			}

			ERROR = ERROR_CODE.ES_SC_001;
		}
		res.getALLUSRS().addAll(allUsrList);
		header = (Header) HeaderFactory.getHeader(HeaderType.AGENTS,
				serviceName, "Query All Users", "", ERROR);
		res.setHeader(header);
		return res;
	}
}
