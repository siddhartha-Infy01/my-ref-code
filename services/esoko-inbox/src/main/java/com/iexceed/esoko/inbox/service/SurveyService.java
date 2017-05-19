package com.iexceed.esoko.inbox.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao2.AgentDetailRepo;
import com.iexceed.esoko.domain.dao2.SurveyTemplateRepo;
import com.iexceed.esoko.domain.dao4.SurveyRepo;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.SurveyTemplate;
import com.iexceed.esoko.domain.entity2.SurveyTemplatePK;
import com.iexceed.esoko.domain.entity4.Survey;
import com.iexceed.esoko.domain.utils.GeoUtils;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.inbox.CRSURQUEANS;
import com.iexceed.esoko.jaxb.inbox.CRSURVEYDETAILS;
import com.iexceed.esoko.jaxb.inbox.CreateSurveyReq;
import com.iexceed.esoko.jaxb.inbox.CreateSurveyRes;
import com.iexceed.esoko.jaxb.inbox.Header;
import com.iexceed.esoko.jaxb.inbox.QRAGENTSURVEY;
import com.iexceed.esoko.jaxb.inbox.QRSRVQUEANS;
import com.iexceed.esoko.jaxb.inbox.QRSURVEYDETAILS;
import com.iexceed.esoko.jaxb.inbox.QueryAgentSurveyRes;
import com.iexceed.esoko.jaxb.inbox.QuerySurveyDefinitionRes;
import com.iexceed.esoko.jaxb.inbox.QuerySurveyRes;
import com.iexceed.esoko.jaxb.inbox.SURVEYDEFINITION;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.vividsolutions.jts.geom.Point;

@Service
public class SurveyService {
	public static Logger log = LoggerUtils.getLogger();

	Enum<ERROR_CODE> errorCode = null;

	Header header = null;

	@Autowired
	SurveyRepo surveyRepo;

	@Autowired
	SurveyTemplateRepo templateRepo;

	@Autowired
	AgentDetailRepo agentRepo;

	public QuerySurveyRes querySurvey(String networkId, String userId,
			String surveyName, String phase) {
		log.info("Inside SurveyService -> querySurvey");
		log.debug("networkId:" + networkId);
		log.debug("userId:" + userId);
		log.debug("surveyName:" + surveyName);
		log.debug("phase:" + phase);

		QuerySurveyRes res = new QuerySurveyRes();
		Survey survey = null;
		if (StringUtils.isEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurvey", "", ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		} else if (StringUtils.isEmpty(userId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurvey", "", ERROR_CODE.RQ_USER_ER);
			res.setHeader(header);
			return res;
		} else if (StringUtils.isEmpty(surveyName)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurvey", "", ERROR_CODE.SURV_NM_ER);
			res.setHeader(header);
			return res;
		} else if (StringUtils.isEmpty(phase)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurvey", "", ERROR_CODE.PHASE_ER);
			res.setHeader(header);
			return res;
		}

		survey = surveyRepo.queryByNwkIdUserId(networkId, userId, surveyName,
				phase);
		if (survey != null) {
			QRSURVEYDETAILS qrSurveyDtl = new QRSURVEYDETAILS();
			qrSurveyDtl.setNetworkId(survey.getNetworkId());
			qrSurveyDtl.setSurveyName(survey.getSurveyName());
			qrSurveyDtl.setSurveyType(survey.getSurveyType());
			qrSurveyDtl.setTotalScore(survey.getTotalScore());
			qrSurveyDtl.setSecuredScore(survey.getSecuredScore());
			qrSurveyDtl.setPhase(survey.getPhase());

			try {
				for (int i = 1; i <= 35; i++) {
					Method m1 = Survey.class.getMethod("getTxt_qns_" + i);
					Method m2 = Survey.class.getMethod("getTxt_fld_" + i);
					String returnQue = (String) m1.invoke(survey);
					String returnAns = (String) m2.invoke(survey);

					if ("".equals(returnQue) || returnQue == null
							|| "".equals(returnAns) || returnAns == null) {
					} else {
						QRSRVQUEANS qrSurvQueAns = new QRSRVQUEANS();
						qrSurvQueAns.setQuestion(returnQue);
						qrSurvQueAns.setAnswer(returnAns);
						qrSurvQueAns.setFlag("A");
						qrSurveyDtl.getQRSRVQUEANS().add(qrSurvQueAns);
					}
				}
				for (int i = 1; i <= 10; i++) {
					Method m1 = Survey.class.getMethod("getBlob_fld_" + i);
					byte[] returnImage = (byte[]) m1.invoke(survey);
					if (returnImage != null) {
						QRSRVQUEANS qrSurvImage = new QRSRVQUEANS();
						Method m3 = Survey.class.getMethod("getTxt_qns_"
								+ (35 + i));
						String returnImgQue = (String) m3.invoke(survey);
						qrSurvImage.setQuestion(returnImgQue);
						qrSurvImage.setImage(returnImage);
						qrSurvImage.setFlag("I");
						qrSurveyDtl.getQRSRVQUEANS().add(qrSurvImage);
					}
				}
				for (int i = 1; i <= 5; i++) {
					Method m1 = Survey.class.getMethod("getGis_fld_" + i);
					Method m3 = Survey.class
							.getMethod("getTxt_qns_" + (45 + i));
					String returnGisQue = (String) m3.invoke(survey);
					Point returnPoint = (Point) m1.invoke(survey);
					if (returnPoint != null) {
						QRSRVQUEANS qrSurvGis = new QRSRVQUEANS();
						String[] coordinates = GeoUtils
								.getCoordinates(returnPoint);
						qrSurvGis.setAnswer(coordinates[0] + ","
								+ coordinates[1]);
						qrSurvGis.setQuestion(returnGisQue);
						qrSurvGis.setFlag("G");
						qrSurveyDtl.getQRSRVQUEANS().add(qrSurvGis);
					}
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res.setQRSURVEYDETAILS(qrSurveyDtl);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"SurveyService", "querySurvey", "", errorCode);
		res.setHeader(header);

		return res;
	}

	@Transactional
	public CreateSurveyRes createSurvey(CreateSurveyReq req) {
		log.info("Inside SurveyService -> createSurvey");
		log.debug("User Id" + req.getHeader().getUserId());
		log.debug("Network Id" + req.getCRSURVEYDETAILS().getNetworkId());
		log.debug("Survey name" + req.getCRSURVEYDETAILS().getSurveyName());
		log.debug("Survey type" + req.getCRSURVEYDETAILS().getSurveyType());
		log.debug("Phase" + req.getCRSURVEYDETAILS().getPhase());

		CreateSurveyRes res = new CreateSurveyRes();
		CRSURVEYDETAILS crSurveyDtls = req.getCRSURVEYDETAILS();
		AgentDetail dtl = new AgentDetail();
		Survey survey = new Survey();
		if (null != crSurveyDtls.getNetworkId()) {
			survey.setNetworkId(crSurveyDtls.getNetworkId());
			dtl.setNetworkId(req.getCRSURVEYDETAILS().getNetworkId());
		}
		if (null != req.getHeader().getUserId()) {
			survey.setUserId(req.getHeader().getUserId());
			dtl.setUserId(req.getHeader().getUserId());
		}
		if (null != crSurveyDtls.getSurveyName()) {
			survey.setSurveyName(crSurveyDtls.getSurveyName());
			dtl.setTemplate(req.getCRSURVEYDETAILS().getSurveyName());
		}
		if (null != crSurveyDtls.getSurveyType()) {
			survey.setSurveyType(crSurveyDtls.getSurveyType());
		}
		if (null != crSurveyDtls.getPhase()) {
			survey.setPhase(crSurveyDtls.getPhase());
		}
		if (null != crSurveyDtls.getTotalScore()) {
			survey.setTotalScore(crSurveyDtls.getTotalScore());
		}
		if (null != crSurveyDtls.getSecuredScore()) {
			survey.setSecuredScore(crSurveyDtls.getSecuredScore());
		}
		List<CRSURQUEANS> srvQueAnsList = crSurveyDtls.getCRSURQUEANS();
		if (srvQueAnsList != null) {
			int text = 1;
			int image = 36;
			int gis = 46;
			for (CRSURQUEANS srvQueAns : srvQueAnsList) {
				try {
					if (srvQueAns.getFlag().equalsIgnoreCase("A")) {
						Method setQns_fld_ = Survey.class.getMethod(
								"setTxt_qns_" + text, String.class);
						setQns_fld_.invoke(survey, srvQueAns.getQuestion());
						Method setTxt_fld_ = Survey.class.getMethod(
								"setTxt_fld_" + text, String.class);
						setTxt_fld_.invoke(survey, srvQueAns.getAnswer());
						++text;
					} else if (srvQueAns.getFlag().equalsIgnoreCase("I")) {
						Method setQns_fld_ = Survey.class.getMethod(
								"setTxt_qns_" + image, String.class);
						setQns_fld_.invoke(survey, srvQueAns.getQuestion());
						Method setBlob_fld_ = Survey.class.getMethod(
								"setBlob_fld_" + (image - 35), srvQueAns
										.getImage().getClass());
						setBlob_fld_.invoke(survey, srvQueAns.getImage());
						++image;
					} else if (srvQueAns.getFlag().equalsIgnoreCase("G")) {
						if (StringUtils.isNotEmpty(srvQueAns.getAnswer())
								&& srvQueAns.getAnswer() != null) {
							if (srvQueAns.getAnswer().contains(",")) {
								String[] gisParts = getCoordinates(srvQueAns
										.getAnswer());
								log.debug("Latitude:" + gisParts[0]);
								log.debug("Longitude:" + gisParts[1]);
								Point point = GeoUtils.getGisValue(gisParts[0],
										gisParts[1]);
								Method setQns_fld_ = Survey.class.getMethod(
										"setTxt_qns_" + gis, String.class);
								setQns_fld_.invoke(survey,
										srvQueAns.getQuestion());
								Method setGis_fld_ = Survey.class
										.getMethod("setGis_fld_" + (gis - 45),
												Point.class);
								setGis_fld_.invoke(survey, point);
								++gis;
							}
						}
					}
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (null != req.getHeader().getUserId()) {
			survey.setUserId(req.getHeader().getUserId());
			survey.setCreatedBy(req.getHeader().getUserId());
			dtl.setCreatedBy(req.getHeader().getUserId());
		}
		survey.setCreatedTs(Utils.getCurrentDate());
		survey.setRecordStatus("U");
		survey.setAuthStat("U");
		log.debug(survey);

		dtl.setCreatedTs(Utils.getCurrentDate());
		dtl.setApplicationId("Survey");
		agentRepo.save(dtl);
		surveyRepo.save(survey);
		errorCode = ERROR_CODE.SURV_SV_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"SurveyService", "createSurvey", req.getHeader().getUserId(),
				errorCode);
		res.setHeader(header);

		return res;
	}

	public QueryAgentSurveyRes querySurveyForAgent(String networkId,
			String userId) {
		log.info("Inside SurveyService -> querySurveyForAgent");
		log.debug("networkId:" + networkId);
		log.debug("userId:" + userId);

		QueryAgentSurveyRes res = new QueryAgentSurveyRes();
		if (StringUtils.isEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "queryAgentSurvey", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		} else if (StringUtils.isEmpty(userId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "queryAgentSurvey", "",
					ERROR_CODE.RQ_USER_ER);
			res.setHeader(header);
			return res;
		}

		List<AgentDetail> agentDtlsList = agentRepo.findSurveyTemplateId(
				networkId, userId);
		if (agentDtlsList != null) {
			if (agentDtlsList.size() != 0) {
				for (AgentDetail agentDtl : agentDtlsList) {
					String templateId = agentDtl.getTemplate();
					log.debug("template id:" + templateId);
					if (templateId != null
							&& StringUtils.isNotEmpty(templateId)) {
						SurveyTemplatePK pk = new SurveyTemplatePK();
						pk.setNetworkId(networkId);
						pk.setTemplateId(templateId);
						SurveyTemplate template = templateRepo.findOne(
								SurveyTemplate.class, pk);
						if (template != null) {
							QRAGENTSURVEY qrSurveyDtl = new QRAGENTSURVEY();
							qrSurveyDtl.setDescription(template
									.getDescription());
							qrSurveyDtl.setSurveyName(templateId);
							res.getQRAGENTSURVEY().add(qrSurveyDtl);
						}
					}
				}
				errorCode = ERROR_CODE.ES_SC_001;
			}
			else{
				errorCode = ERROR_CODE.SURV_NR_ER;
				log.info("No record found");
			}
		} else {
			errorCode = ERROR_CODE.SURV_NR_ER;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"SurveyService", "querySurvey", "", errorCode);
		res.setHeader(header);

		return res;
	}

	public QuerySurveyDefinitionRes querySurveyDef(String networkId,
			String surveyName) {
		log.info("Inside SurveyService -> querySurveyDef");
		log.debug("networkId:" + networkId);
		log.debug("surveyName:" + surveyName);
		QuerySurveyDefinitionRes res = new QuerySurveyDefinitionRes();
		if (StringUtils.isEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurveyDefintion", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		} else if (StringUtils.isEmpty(surveyName)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurveyDefintion", "",
					ERROR_CODE.SURV_NM_ER);
			res.setHeader(header);
			return res;
		}
		SurveyTemplatePK pk = new SurveyTemplatePK();
		pk.setNetworkId(networkId);
		pk.setTemplateId(surveyName);
		SurveyTemplate template = templateRepo
				.findOne(SurveyTemplate.class, pk);
		log.debug("template:" + template);
		SURVEYDEFINITION surveyDef = new SURVEYDEFINITION();
		if (template != null) {

			surveyDef.setServeyDefinition(template.getSurveyDefinition());
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"SurveyService", "querySurveyDefintion", "", errorCode);
			res.setHeader(header);
			return res;
		}
		res.setSURVEYDEFINITION(surveyDef);
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"SurveyService", "querySurveyDefintion", "", errorCode);
		res.setHeader(header);
		return res;
	}

	private String[] getCoordinates(String gis) {
		return gis.split(",");
	}
}
