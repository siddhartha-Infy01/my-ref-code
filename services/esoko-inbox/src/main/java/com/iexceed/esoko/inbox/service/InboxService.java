package com.iexceed.esoko.inbox.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.approval.service.ApprovalService;
import com.iexceed.esoko.db.service.OffersService;
import com.iexceed.esoko.db.service.PriceUploadService;
import com.iexceed.esoko.domain.dao.CommodityRepo;
import com.iexceed.esoko.domain.dao.CurrencyRepo;
import com.iexceed.esoko.domain.dao.ForexRepo;
import com.iexceed.esoko.domain.dao.LocationRepo;
import com.iexceed.esoko.domain.dao.MeasureFactorRepo;
import com.iexceed.esoko.domain.dao.MeasureRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao2.AgentDetailRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao3.BidsOffersMasterRepo;
import com.iexceed.esoko.domain.dao3.PriceUploadMasterRepo;
import com.iexceed.esoko.domain.dao3.PushAlertMasterRepo;
import com.iexceed.esoko.domain.dao4.InboxMonitorRepo;
import com.iexceed.esoko.domain.dao4.KeywordAliaRepo;
import com.iexceed.esoko.domain.dao4.KeywordRepo;
import com.iexceed.esoko.domain.dao4.SmsControllerRepo;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.domain.entity.Currency;
import com.iexceed.esoko.domain.entity.Forex;
import com.iexceed.esoko.domain.entity.Location;
import com.iexceed.esoko.domain.entity.Measure;
import com.iexceed.esoko.domain.entity.MeasureFactor;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.PeoplePK;
import com.iexceed.esoko.domain.entity3.BidsOffersMaster;
import com.iexceed.esoko.domain.entity3.PriceUploadMaster;
import com.iexceed.esoko.domain.entity3.PushAlertMaster;
import com.iexceed.esoko.domain.entity4.InboxMonitor;
import com.iexceed.esoko.domain.entity4.Keyword;
import com.iexceed.esoko.domain.entity4.KeywordAlia;
import com.iexceed.esoko.domain.entity4.KeywordAliaPK;
import com.iexceed.esoko.domain.entity4.KeywordPK;
import com.iexceed.esoko.domain.entity4.SmsController;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.domain.utils.GeoUtils;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.COMMODITIES;
import com.iexceed.esoko.jaxb.db.CROFFERDETAILS;
import com.iexceed.esoko.jaxb.db.CRPRICEDETAILS;
import com.iexceed.esoko.jaxb.db.CreateOffersReq;
import com.iexceed.esoko.jaxb.db.CreatePricesReq;
import com.iexceed.esoko.jaxb.inbox.CRKEYWORD;
import com.iexceed.esoko.jaxb.inbox.CreateKeywordReq;
import com.iexceed.esoko.jaxb.inbox.CreateKeywordRes;
import com.iexceed.esoko.jaxb.inbox.DELINBOXACTIVTY;
import com.iexceed.esoko.jaxb.inbox.DeleteInboxActivityReq;
import com.iexceed.esoko.jaxb.inbox.DeleteInboxActivityReqWrap;
import com.iexceed.esoko.jaxb.inbox.DeleteInboxActivityRes;
import com.iexceed.esoko.jaxb.inbox.Header;
import com.iexceed.esoko.jaxb.inbox.INBOXFACILITY;
import com.iexceed.esoko.jaxb.inbox.INBXACTVTYMON;
import com.iexceed.esoko.jaxb.inbox.INBXACTVTYMONCTDATA;
import com.iexceed.esoko.jaxb.inbox.InboxActivityMonitorRes;
import com.iexceed.esoko.jaxb.inbox.InboxFacilityReq;
import com.iexceed.esoko.jaxb.inbox.InboxFacilityRes;
import com.iexceed.esoko.jaxb.inbox.KEYWORDACTN;
import com.iexceed.esoko.jaxb.inbox.KEYWORDDTLS;
import com.iexceed.esoko.jaxb.inbox.KeywordActionsReq;
import com.iexceed.esoko.jaxb.inbox.KeywordActionsRes;
import com.iexceed.esoko.jaxb.inbox.QRALLALRTSNW;
import com.iexceed.esoko.jaxb.inbox.QRKEYWORDS;
import com.iexceed.esoko.jaxb.inbox.QueryAllAlertsByNwk;
import com.iexceed.esoko.jaxb.inbox.QueryKeywordsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;
import com.itextpdf.awt.geom.misc.RenderingHints.Key;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.operation.distance.ConnectedElementLocationFilter;

@Service
public class InboxService {
	
	@Autowired
	private KeywordRepo kywrdRepo;
	

	
	@Autowired
	private PushAlertMasterRepo alertRepo;
	
	@Autowired
	private InboxMonitorRepo inboxRepo;
	
	@Autowired
	private PeopleRepo pplrepo;
	
	@Autowired
	private SystemUserRepo sysRepo;
	
	@Autowired
	private LocationRepo locRepo;
	
	@Autowired
	private CommodityRepo comRepo;
	
	@Autowired
	private AgentDetailRepo agntRepo;
	
    @Autowired
    private NetworkRepo nwkRepo;
    
    @Autowired
    private MeasureRepo measureRepo;
    
    @Autowired
    private MeasureFactorRepo msrRepo;
    
    @Autowired
    private PriceUploadService prcSrvc;
    
    @Autowired
    private CurrencyRepo ccyRepo;
    
    @Autowired
    private KeywordAliaRepo kyrdAliRepo;
    
    @Autowired
    private OffersService offsrvc;
    
    @Autowired
	private PriceUploadMasterRepo priceUpldMstrRepo;
    
    @Autowired
    private MeasureFactorRepo factorRepo;
    
    @Autowired
    private ForexRepo forexRepo;
    
    @Autowired
    private ApprovalService apprvc;
    
    @Autowired
    private SmsControllerRepo contRepo;
    
    @Autowired
    private BidsOffersMasterRepo boRepo;
   
   
  
	
	Header header = null;

	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_NR_001;

	public static Logger log = LoggerUtils.getLogger();
	
	@Transactional
	public CreateKeywordRes crKeyword(CreateKeywordReq req)
	{
		log.info("Inside InboxService -> crKeyword");
		
	CreateKeywordRes res = new CreateKeywordRes();	
	CRKEYWORD crkywrd = req.getCRKEYWORD();
	
	log.debug("Network id:" + crkywrd.getNetworkId());
	log.debug("keyword:" + crkywrd.getKeyword());
	log.debug("joinFlag:" + crkywrd.getJoinFlag());
	log.debug("replyFlag:" + crkywrd.getReplyFlag());
	log.debug("airtimeFlag:" + crkywrd.getAirtimeFlag());
	log.debug("payee_account:" + crkywrd.getPayeeAccount());
	log.debug("airtime:" + crkywrd.getAirtime());
	log.debug("airtime_currency" + crkywrd.getAirtimeCurrency());
	log.debug("noOfMessages:" + crkywrd.getNoOfMessages());
	log.debug("noOfCharacters:" + crkywrd.getNoOfCharacters());
	log.debug("ealerts:" + crkywrd.getEalerts());
	log.debug("groups:" + crkywrd.getGroups());
	log.debug("Mynetwork" + crkywrd.getMyNetwork());
	
	int aliasExist =0;
	Keyword entity = new Keyword();
	KeywordPK id = new KeywordPK();
	id.setKeyword(crkywrd.getKeyword());
	id.setChannel(crkywrd.getChannel());
	entity.setId(id);
	
	if(crkywrd.getAirtimeFlag().toString().equalsIgnoreCase("Y"))
	{
	if(StringUtils.isNotEmpty(crkywrd.getAirtime())){
	entity.setAirtime(BigDecimal.valueOf(Double
			.parseDouble(crkywrd.getAirtime())));
	}
	}
	if(crkywrd.getAirtimeCurrency() != null)
	{
		entity.setAirtimeCurrency(crkywrd.getAirtimeCurrency());
	}
	if(crkywrd.getAirtimeFlag() != null)
	{
	entity.setAirtimeFlag(crkywrd.getAirtimeFlag());
	}
	if (StringUtils.isNotEmpty(crkywrd.getEalerts()))
	{
		entity.setEalerts(crkywrd.getEalerts());
	}
	if (StringUtils.isNotEmpty(crkywrd.getGroups()))
	{
		entity.setGroups(crkywrd.getGroups());
	}
	if(crkywrd.getJoinFlag() != null)
	{
		entity.setJoinFlag(crkywrd.getJoinFlag());
	}
	if(crkywrd.getMyNetwork() != null)
	{
		entity.setMyNetwork(crkywrd.getMyNetwork());
	}
	if(crkywrd.getNoOfCharacters() != null)
	{
		entity.setNoCharacters(crkywrd.getNoOfCharacters().intValue());
	}
	if(crkywrd.getNoOfMessages() != null)
	{
		entity.setNoOfMessages(crkywrd.getNoOfMessages().intValue());
	}
	if(crkywrd.getPayeeAccount() != null)
	{
		entity.setPayeeAccount(crkywrd.getPayeeAccount());
	}
	if(crkywrd.getReplyFlag() != null)
	{
		entity.setReplyFlag(crkywrd.getReplyFlag());
	}
	if(StringUtils.isNotEmpty(crkywrd.getResponse()))
	{
		entity.setResponse(crkywrd.getResponse());
	}
	if(crkywrd.getNetworkId() != null)
	{
		entity.setNetworkId(crkywrd.getNetworkId());
	}
	
    OUTER:
	if(crkywrd.getEditFlag().toString().equalsIgnoreCase("N"))
	{
	  		
	
	 Keyword keyword = kywrdRepo.findOne(entity, id);
	 aliasExist = kyrdAliRepo.keywordCheck(crkywrd.getChannel(), crkywrd.getKeyword());
	 
	 if(keyword != null || aliasExist != 0)
	 {
		 ERROR = ERROR_CODE.KEYWORD_EXISTS;
	 }
	 
	 else
	 {  
		 
		 if (StringUtils.isNotEmpty(crkywrd.getAliases()))
		 {
			 aliasExist = kyrdAliRepo.aliasCheck(crkywrd.getAliases(), crkywrd.getChannel(), crkywrd.getKeyword());
			 
			 if (aliasExist > 0 )
			 {
				 ERROR = ERROR_CODE.KEYWORD_ALS_DUP;
				 break OUTER;
				 
			 }
			 
		 }
		 
		
	
		 
		 entity.setRecordStatus("A");
		entity.setCreatedBy(req.getHeader().getUserId());
		entity.setCreatedTs(Utils.getCurrentDate());
		kywrdRepo.save(entity);
		ERROR = ERROR_CODE.KEYWORD_SC;
		
		
	 }
	 
	 
	}
	
	else
	{  
		
		 
		 Keyword entity1 = new Keyword();
		 KeywordPK entity1pk = new KeywordPK();
		 entity1pk.setKeyword(crkywrd.getOLdKeyword());
		 entity1pk.setChannel(crkywrd.getChannel());
		 entity1.setId(entity1pk);
		 
		 Keyword entity2 = kywrdRepo.findOne(entity1, entity1pk);
		 entity.setRecordStatus(entity2.getRecordStatus());
		 entity.setCreatedBy(entity2.getCreatedBy());
			entity.setCreatedTs(entity2.getCreatedTs());
			entity.setModifiedBy(req.getHeader().getUserId());
			entity.setModifiedTs(Utils.getCurrentDate());
		if (crkywrd.getKeyword().toString().equalsIgnoreCase(crkywrd.getOLdKeyword().toString()))
		
	{
			 if (StringUtils.isNotEmpty(crkywrd.getAliases()))
			 {
				 aliasExist = kyrdAliRepo.aliasCheck(crkywrd.getAliases(), crkywrd.getChannel(), crkywrd.getKeyword());
				 
				 if (aliasExist > 0 )
				 {
					 ERROR = ERROR_CODE.KEYWORD_ALS_DUP;
					 break OUTER;
					 
				 }
				 
			 }

			
	
		
		kywrdRepo.merge(entity);
		ERROR = ERROR_CODE.KEYWORD_UD;
	}
	else
	{
		

	 Keyword keyword = kywrdRepo.findOne(entity, id);
	 aliasExist = kyrdAliRepo.keywordCheck(crkywrd.getChannel(), crkywrd.getKeyword());
	 if(keyword != null || aliasExist !=0)
	 {
		 ERROR = ERROR_CODE.KEYWORD_EXISTS;
	 }
	 
	 else
	 {
		 
		 if (StringUtils.isNotEmpty(crkywrd.getAliases()))
		 {
			 aliasExist = kyrdAliRepo.aliasCheck(crkywrd.getAliases(), crkywrd.getChannel(), crkywrd.getKeyword());
			 
			 if (aliasExist > 0 )
			 {
				 ERROR = ERROR_CODE.KEYWORD_ALS_DUP;
				 break OUTER;
				 
			 }
			 
		 }
		
			kywrdRepo.save(entity); 
			ERROR = ERROR_CODE.KEYWORD_UD;
			kywrdRepo.delete(entity2);
			kyrdAliRepo.delAlias(crkywrd.getChannel(), crkywrd.getOLdKeyword());
	 }
	}
	}
	header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
			"InboxService", "Create Keyword", "", ERROR);
	res.setHeader(header);
	
	return res;
		
	}
	
	@Transactional
	public KeywordActionsRes keywrdaction(KeywordActionsReq req) {
		List<KEYWORDDTLS> keywordDtls = req.getKEYWORDACTN().getKEYWORDDTLS();
		KEYWORDACTN flagDtls = req.getKEYWORDACTN();
		KeywordActionsRes res = new KeywordActionsRes();
		int returnCount = 0;
	
		 String activity = flagDtls.getFlag().toString();
		if (keywordDtls.size() != 0) {
			for (KEYWORDDTLS modDtls : keywordDtls) {
				
				if (activity.equalsIgnoreCase("D"))
				{
					Keyword keyword = new Keyword();
				    KeywordPK keywordpk = new KeywordPK();
				    keywordpk.setKeyword(modDtls.getKeyword());
				    keywordpk.setChannel(modDtls.getChannel());
				    keyword.setId(keywordpk);
				    Keyword entity = kywrdRepo.findOne(keyword, keywordpk);
				    kywrdRepo.delete(entity);
				}
				
				else
				{
					returnCount = kywrdRepo.keywordactn(modDtls.getKeyword(), modDtls.getChannel(), activity, req.getHeader().getUserId());
					
				}

			}
			
			if(activity.equalsIgnoreCase("S"))
			{
			ERROR = ERROR_CODE.KEYWORD_SUS;
			}
			else if (activity.equalsIgnoreCase("A"))
			{
				ERROR = ERROR_CODE.KEYWORD_ACTIVATE;	
			}
			
			else
			{
				ERROR = ERROR_CODE.KEYWORD_DEL;
			}
		}
			
			else {
				ERROR = ERROR_CODE.ES_NR_001;
			}
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"InboxService", "Keyword action", "", ERROR);
		res.setHeader(header);
		
		return res;
	}
	
	public QueryKeywordsRes queryKeywords(String networkId,String sortBy)
	{
		QueryKeywordsRes res = new QueryKeywordsRes();
		StringBuilder builder = new StringBuilder();
		
		List<QRKEYWORDS> qrkywds = new ArrayList<QRKEYWORDS>();
		try
		{
			List<Map<String, Object>> list = (List<Map<String, Object>>) kywrdRepo.queryKeywords(networkId, sortBy);
			log.debug("mapList::" + list);
			if(list.size() != 0)
			{
				
				for (Map<String, Object> loop : list)
				{
					QRKEYWORDS qrky = new QRKEYWORDS();
								
					if(loop.get("keyword") != null){
						qrky.setKeyword(loop.get("keyword").toString());
						}
					if(loop.get("count") != null){
						qrky.setUsageCount(loop.get("count").toString());
						}
						
					if(loop.get("recordStatus") != null){
							qrky.setStatus(loop.get("recordStatus").toString());
						}
					if(loop.get("join_flag") != null){
						qrky.setJoinFlag(loop.get("join_flag").toString());
						}
				    if(loop.get("alert_name") != null){
					     qrky.setAlertName(loop.get("alert_name").toString());
					     builder.append("Subscribe to Alert: " + loop.get("alert_name").toString() + "\n" );
					   
			            }
				    if(loop.get("group_name") != null){
				    	 qrky.setGroupName(loop.get("group_name").toString());
				    	 builder.append("Join Groups: " + loop.get("group_name").toString() + "\n" );
				    	}
				    if(loop.get("my_network") != null){
				        qrky.setMyNetwork(loop.get("my_network").toString());
				        if(loop.get("my_network").toString().equalsIgnoreCase("Y"))
				        {
				        builder.append("Join Network: " + loop.get("networkName").toString() + "\n" );
				        }
				        }
		     		if(loop.get("networkd_id") != null){
						qrky.setNetworkId(loop.get("networkd_id").toString());
						}
					if(loop.get("reply_flag") != null){
						qrky.setReplyFlag(loop.get("reply_flag").toString());
					    }
				    if(loop.get("airtime_flag") != null){
					    qrky.setAirtimeFlag(loop.get("airtime_flag").toString());
					    }
					
	     			if(loop.get("payee_account") != null){
						qrky.setPayeeAcc(loop.get("payee_account").toString());
					    }
	     		    if(loop.get("response") != null){
						qrky.setResponse(loop.get("response").toString());
						 builder.append("REPLY: " + loop.get("response").toString() + "\n" );
					    }
	     			if(loop.get("airtime") != null){
						qrky.setAirtime(loop.get("airtime").toString());
						 builder.append("AMOUNT: " + loop.get("airtime").toString() );
						}
						
		     		if(loop.get("airtime_currency") != null){
							qrky.setAirtimeCurrency(loop.get("airtime_currency").toString());
						}
		     		if(loop.get("noOfMsgs") != null){
							qrky.setNoOfMsgs(loop.get("noOfMsgs").toString());
						}
							
			     	if(loop.get("noOfCharcts") != null){
								qrky.setNoOfCharc(loop.get("noOfCharcts").toString());
						}
			     	if(loop.get("alert_id") != null){
						qrky.setAlertIds(loop.get("alert_id").toString());
				        }
			    	if(loop.get("group_id") != null){
						qrky.setGroupId(loop.get("group_id").toString());
				        }
			    	
			      	if(loop.get("channel") != null){
						qrky.setChannel(loop.get("channel").toString());
				        }
			      	if(loop.get("alias") != null){
						qrky.setAliases(loop.get("alias").toString());
				        }
			    	
			    	qrky.setAction(builder.toString());
			    	qrkywds.add(qrky);
			    	qrky = null;
			    	builder = new StringBuilder();
	     	    	     	     
				}
				res.getQRKEYWORDS().addAll(qrkywds);
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "Query Keywords", "", ERROR_CODE.ES_SC_001);
				
			}
			
			else
			{
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "Query Keywords", "", ERROR_CODE.ES_NR_001);
			}
		}
		
		catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"InboxService", "Query Keywords", "", ERROR_CODE.DM_SV_002);
					
			Utils.getStackTrace(e);
		}

		res.setHeader(header);

		return res;
	}
	
	public QueryAllAlertsByNwk queryAllAlertsBynwk(String networkId)
	{
		QueryAllAlertsByNwk res = new QueryAllAlertsByNwk();
		List<QRALLALRTSNW> qralts = new ArrayList<QRALLALRTSNW>();
		
		try 
		{
			List<Map<String, Object>> list = (List<Map<String, Object>>)  alertRepo.queryAllAlertsByNetwork(networkId);
			
			if (list.size() != 0)
			{
				for(Map<String, Object> loop : list)
				{
					QRALLALRTSNW qral = new QRALLALRTSNW();
					qral.setAlertId(loop.get("push_alert_id").toString());
					qral.setAlertName(loop.get("alertName").toString());
					qralts.add(qral);
					qral = null;
					
				}
				res.getQRALLALRTSNW().addAll(qralts);
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "Query all alerts", "", ERROR_CODE.ES_SC_001);
				
			}
			
			else
			{
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "Query Keywords", "", ERROR_CODE.ES_NR_001);
			}
		}
		
		catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"InboxService", "queryAllAlertsBynwk", "", ERROR_CODE.DM_SV_002);
					
			Utils.getStackTrace(e);
		}
		res.setHeader(header);
		return res;
	}
	
	@Transactional
	public DeleteInboxActivityRes deleteInboxActivity(DeleteInboxActivityReq req)
	{
		DeleteInboxActivityRes res = new DeleteInboxActivityRes();
		DELINBOXACTIVTY delactvty = req.getDELINBOXACTIVTY();
		
		InboxMonitor entity = new InboxMonitor();
		entity.setActivityId(Integer.parseInt(delactvty.getActivityId()));
		try
		{
		InboxMonitor enitity1 = inboxRepo.findOne(entity,Integer.parseInt(delactvty.getActivityId()));
		
		inboxRepo.delete(enitity1);
		
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"InboxService", "deleteInboxActivity", "", ERROR_CODE.DEL_INBOX_SC);
		}
		
		catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"InboxService", "deleteInboxActivity", "", ERROR_CODE.DM_SV_002);
					
			Utils.getStackTrace(e);
		}
		res.setHeader(header);
		return res;
	}
	
	public InboxActivityMonitorRes inboxActivity(String fromdate,	String todate, String service, String subservice,String networkId,String sortBy)
	{
		InboxActivityMonitorRes res = new InboxActivityMonitorRes();
		List<INBXACTVTYMON> inbxact = new ArrayList<INBXACTVTYMON>();
		List<INBXACTVTYMONCTDATA> inbxact1 = new ArrayList<INBXACTVTYMONCTDATA>();
		try
		{
			List<InboxMonitor> list = inboxRepo.queryActivity(Utils.getFormatedDate(fromdate), Utils.getFormatedDate(todate), service, subservice, networkId,sortBy);
			log.info("entity" + list);
			if (list != null)
			{
				for(InboxMonitor loop : list)
				{
					INBXACTVTYMON inx = new INBXACTVTYMON();
					inx.setActivityId(Integer.toString(loop.getActivityId()));
					if(loop.getErrorStat() != null)
					{
						inx.setError(loop.getErrorStat());
					}
					if(loop.getServiceName() != null)
					{
						inx.setServiceName(loop.getServiceName());
					}
					
					if(loop.getPeopleId() != null)
					{
						inx.setPeopleId(loop.getPeopleId());
						People ppl = new People();
						PeoplePK pplpk = new PeoplePK();
						pplpk.setDefaultNetworkId(loop.getNetworkId());
						pplpk.setPeopleId(loop.getPeopleId());
						
						ppl.setId(pplpk);
						People ppl1 = pplrepo.findOne(ppl, pplpk);
						if(ppl1 != null)
						{
							if (ppl1.getFirstName() != null) {
								if (ppl1.getLastName() != null) {
									inx.setPeopleName(ppl1.getFirstName() + " "
											+ ppl1.getLastName());
								} else {
									inx.setPeopleName(ppl1.getFirstName());
								}
							}
						}
						else
						{
							inx.setPeopleName("Unknown("+loop.getMsisdn()+")");
						}
					
					}
					
					if(loop.getMsisdn() != null)
					{
						inx.setMsisdn(loop.getMsisdn());
					}
					
					if(loop.getMessage() != null)
					{
						inx.setMessage(loop.getMessage().toString());
					}
					
					if(loop.getCreatedTs() != null)
					{
						inx.setDate(Utils.getDDMMMYYFormat(loop.getCreatedTs().toString(),0));
					}
					if(loop.getChannel() != null)
					{
						inx.setChannel(loop.getChannel());
					}
					
					inbxact.add(inx);
					inx= null;
				}
				
				res.getINBXACTVTYMON().addAll(inbxact);
				
				
			}
			
			else
			{
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "inbox monitor", "", ERROR_CODE.ES_NR_001);
			}
			
			List<Map<String, Object>> list1 = inboxRepo.queryActivitygraph(Utils.getFormatedDate(fromdate), Utils.getFormatedDate(todate), service, subservice, networkId);
			log.info("list1" + list1);
			if (list1 != null)
			{
				for (Map<String, Object> loop : list1)
				{
					INBXACTVTYMONCTDATA inx1= new INBXACTVTYMONCTDATA();
					
					inx1.setDate(Utils.getDDMMMYYFormat(loop.get("date").toString(),0));
					inx1.setNOSMS(loop.get("no_sms").toString());
					inx1.setType(loop.get("type").toString());
					inbxact1.add(inx1);
					inx1=null;
				}
				
				res.getINBXACTVTYMONCTDATA().addAll(inbxact1);
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "inbox activity", "", ERROR_CODE.ES_SC_001);
				
			}
			
			else
			{
				header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
						"InboxService", "inbox monitor", "", ERROR_CODE.ES_NR_001);
			}
			
		}
		
		catch (DomainException e) {
			header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
					"InboxService", "inbox monitor", "", ERROR_CODE.DM_SV_002);
					
			Utils.getStackTrace(e);
		}

		res.setHeader(header);

		return res;
		
	}
	
	@Transactional
	public InboxFacilityRes inboxfacility(InboxFacilityReq req)
	{
		InboxFacilityRes res = new InboxFacilityRes();
		
		INBOXFACILITY inbox = req.getINBOXFACILITY();
		com.iexceed.esoko.jaxb.db.Header dbheader = new com.iexceed.esoko.jaxb.db.Header();
		
		String message = null;
		String mystring = inbox.getKeyword().toString().toLowerCase();
		String arr[] = mystring.split(",", 2);
		String firstWord = arr[0].toString();
		String networkId = null;
		String userId = null;
		int i;
		log.info("firstword:" + firstWord);
		int returnCount = 0;
		String country = Utils.getCountry(inbox.getMsisdn());
		SystemUser user = sysRepo.queryUserIdbyPhone(inbox.getMsisdn());
		
		if(firstWord.equalsIgnoreCase("W+") || firstWord.equalsIgnoreCase("R+") || firstWord.equalsIgnoreCase("F+") || firstWord.equalsIgnoreCase("L+"))
		{  
			
			int intIndex = mystring.indexOf("n=");
			if(intIndex == - 1)
			{
				
				if(user != null)
						{
						networkId= user.getDefaultNetworkId().toString();
						userId = user.getUserId().toString();
						}
				else
				       {
					    Network network = nwkRepo.queryCountryNwk(country);
					    if(network != null)
					    {
					    	networkId = network.getNetworkId().toString();
					    }
				
				       }
								
			}
			
			else
			{ 
				 String arr1[] = mystring.split("n=", 2);
				 String arr2[] = arr1[1].split(",",2);
				 networkId = arr2[0].toString();
				 log.info("networkID:" + networkId);
				 People ppl = pplrepo.findByMsisdnNwkId1(inbox.getMsisdn(), networkId);
				 if (ppl != null)
				 {
					userId = ppl.getId().getPeopleId(); 
				 }
				 
				 else
				 {   if(user != null){
					 userId = user.getUserId().toString();
				 }
				 }
				 
				 
				
			}
		 // price upload
			
			if (networkId != null)
			{
				Network network = new Network();
				network.setNetworkId(networkId);
				Network nw = nwkRepo.findOne(network, networkId);
				if (nw != null)
				{
				
			String arr3[] = mystring.split(",",3);
			if (arr3.length > 1)
			{
			String locationId = arr3[1].toString();
			 log.info("locationId:" + locationId);			
			 
			Location entity = new Location();
			
			entity.setLocationId(locationId);
			Location loc = locRepo.findOne(entity, locationId);
			if(loc == null)
			{
				loc = locRepo.findLocationFromCode(networkId, locationId);
			}
			if(loc != null)
			{ locationId = loc.getLocationId().toString();
				if (arr3.length > 2)
				{
					String arr5[] = arr3[2].split(",");
					outer:
					for (i=0 ; i<= arr5.length -1; i++)
					{   if(arr5[i].startsWith("n=") || arr5[i].startsWith("c=") || arr5[i].startsWith("m="))
					   {
						 
						break outer;
					   }
					
					else
					  { String arr8[] = arr5[i].split(":");
				
					    if(arr8.length > 1)
					    {
						String commodityId = arr8[0].toString();
						log.info("commodityId:" + commodityId);
						Double price_datatype;
						try 
						{
						price_datatype = Double.parseDouble((arr8[1].toString()));
						log.info("price_datatype:" + price_datatype);
					     
						Commodity comm = new Commodity();
						
						comm.setCommodityId(commodityId);
						Commodity comm1 = comRepo.findOne(comm,commodityId);
						if(comm1 == null)
						{
							comm1= comRepo.findCommodityFromCode(networkId, commodityId);
						}
						
						outer1:
						if (comm1 != null)
						{  commodityId = comm.getCommodityId().toString();
							String DetailId=null;
							if (userId != null)
							{
								 AgentDetail agent = agntRepo.findDetailId(networkId, userId, commodityId, locationId);
								
								 if(agent != null)
								 {
									  DetailId = Integer.toString(agent.getDetailId());
								 }
								 
							}
							String PriceType ;
							if(firstWord.equalsIgnoreCase("W+"))
							{
							PriceType = "wholesale";
							}
							else if (firstWord.equalsIgnoreCase("R+"))
							{
								PriceType = "retail";
							}	
							else if (firstWord.equalsIgnoreCase("F+"))
							{
								PriceType = "Farm-Gate";
							}	
							else 
							{
								PriceType = "Off-lorry";
							}	
							int intIndex1 = mystring.indexOf("m=");
							String measureId = null;
							if(intIndex1 == - 1)
							{
								MeasureFactor msrfctr = msrRepo.queryByNwkCMdPrct(networkId, commodityId, PriceType);
								if(msrfctr != null)
								{
								measureId = msrfctr.getBaseMeasureId().toString();
								}
							}
							
							else
							{
								String arr6[] = mystring.split("m=", 2);
								String arr7[] = arr6[1].split(",");
								measureId = arr7[0].toString();
								log.info("measureId" + measureId);
							}
							
							if(measureId == null)
							{
								
								message ="Measure is not maintained for commodity.Please contact your system administrator";
								break outer1;
							}
							Measure measure = new Measure();
							measure.setMeasureId(measureId);
							Measure msr = measureRepo.findOne(measure, measureId);
							if(msr != null)
							{
							int intIndex2 = mystring.indexOf("c=");
							String currencyId = null;
							if(intIndex2 == - 1)
							{
								Location locccy = new Location();
								locccy.setLocationId(country);
								Location loccy1 = locRepo.findOne(locccy, country);
								if(loccy1 != null)
								{
								currencyId = loccy1.getCurrency().toString();
								}
							}
							
							else
							{
								String arr6[] = mystring.split("c=", 2);
								String arr7[] = arr6[1].split(",");
								currencyId = arr7[0].toString();
							}
							
							Currency ccy = new Currency();
							ccy.setCurrencyId(currencyId);
							Currency ccy1 = ccyRepo.findOne(ccy, currencyId);
							
							if (ccy1 != null)
							{
								CreatePricesReq req1 = new CreatePricesReq();
							CRPRICEDETAILS crpriceDtls = new CRPRICEDETAILS();
							 crpriceDtls.setUploadMode("M");
							 crpriceDtls.setCollectdDate((Utils.getDateFormaterinbox(Utils.getCurrentDate().toString(),2)).toString());
							 crpriceDtls.setCurrencyId(currencyId);
							 crpriceDtls.setMarket(locationId);
							 crpriceDtls.setNetworkId(networkId);
							 crpriceDtls.setLatitude("");
							 crpriceDtls.setLongitude("");
							 if (DetailId != null)
							 {
							 crpriceDtls.setDetailId(DetailId);
							 }
							 if(userId != null)
							 {
								 crpriceDtls.setAgentId(userId);
							 }
							 com.iexceed.esoko.jaxb.db.Commodity commsub = new com.iexceed.esoko.jaxb.db.Commodity();
							 commsub.setCommodityId(commodityId);
							 commsub.setMeasureId(measureId);
							 commsub.setPrice(price_datatype);
							 commsub.setPriceType(PriceType);
							 crpriceDtls.getCommodities().add(commsub);
							 
							log.info("userId" + userId);
							 if(userId != null)
							 { dbheader.setUserId(userId);
							 }
							req1.setHeader(dbheader);
							 req1.setCRPRICEDETAILS(crpriceDtls);
							 
							 
							 prcSrvc.createPrices(req1);
							 message ="Uploaded Successfully";
								
							}
							
							else
							{
								message = "Currency is not maintained for country.Please contact your system administrator";
							}
							}
							
							else
							{
								message = "Measure is not maintained for commodity.Please contact your system administrator";
							}
						
						}
						
						else
						{
							message =  "Please Provide a Valid commodity";
						}
						}
						catch (NumberFormatException e)
						{
							message = "Invalid Price";
						}
					    }
					    else
					    { if(i > 0)
					    {
					    	message = "Prices were uploaded only partially.Some of the commodities or prices were invalid/Invalid message format";
					    }
					    else
					    {
					    	message = "Invalid Message Format";
					    }
					    }
					}
					
					}
					
			
				}
				
				else
				{
					message =  "Please Provide a Valid commodity";
				}
			}
			
			else
				
			{
				message =  "Please Provide a Valid Location";
			}
			}
			
			else
			{
				message = "Please Provide a Valid Location";
			}
				}
				else
				{
					message = "No Valid Network Available";
				}
			}
			else
			{
				message = "No Valid Network Available";
			}
		}
		
		
		else if (firstWord.equalsIgnoreCase("B+") || firstWord.equalsIgnoreCase("O+"))
		{   
		
			String offerType;
			String offerBelongsto = null;
			String userNameOrMobNo = null;
			String negotiable = null;
			int intIndex = mystring.indexOf("n=");
			if(intIndex == - 1)
			{
				
				if(user != null)
						{
						networkId= user.getDefaultNetworkId().toString();
						userId = user.getUserId().toString();
						}
				else
				       {
					    Network network = nwkRepo.queryCountryNwk(country);
					    if(network != null)
					    {
					    	networkId = network.getNetworkId().toString();
					    }
				
				       }
								
			}
			
			else
			{ 
				 String arr1[] = mystring.split("n=", 2);
				 String arr2[] = arr1[1].split(",",2);
				 networkId = arr2[0].toString();
				 log.info("networkID:" + networkId);
				 People ppl = pplrepo.findByMsisdnNwkId1(inbox.getMsisdn(), networkId);
				 if (ppl != null)
				 {
					userId = ppl.getId().getPeopleId(); 
				 }
				 
				 else
				 {   if(user != null){
					 userId = user.getUserId().toString();
				 }
				 }
				 
				 
				
			}
			// bids & offer upload
			
			if (networkId != null)
			{
				
				Network network = new Network();
				network.setNetworkId(networkId);
				Network nw = nwkRepo.findOne(network, networkId);
				if (nw != null)
				{
					String arr3[] = mystring.split(",",5);
					if (arr3.length > 1)
					{
						String commoditId = arr3[1].toString();
						 log.info("commoditId:" + commoditId);
						Commodity entity = new Commodity();
						
						entity.setCommodityId(commoditId);
						Commodity comm = comRepo.findOne(entity, commoditId);
						if(comm == null)
						{
							comm= comRepo.findCommodityFromCode(networkId, commoditId);
						}
						if(comm != null)
						{ commoditId = comm.getCommodityId().toString();
						 log.info("commoditId:" + commoditId);
							if (arr3.length > 2)
							{
								if(arr3[2].toString().equalsIgnoreCase("Nego"))
								{
									negotiable ="y";
								}
								
								else
								{
									negotiable ="n";
								}
									
								Double price_datatype = 0.0;
								try
								{   
									if(negotiable.equalsIgnoreCase("n"))
									{
									price_datatype = Double.parseDouble((arr3[2].toString()));
									log.info("price_datatype:" + price_datatype);
									}
									
									if (arr3.length > 3)
									{
									Double quantity;
									outer1:
									try
									{
										quantity = Double.parseDouble((arr3[3].toString()));
										log.info("quantity:" + quantity);
										int intIndex1 = mystring.indexOf("m=");
										String measureId = null;
										if(intIndex1 == - 1)
										{
											MeasureFactor msrfctr = msrRepo.queryByNwkCMdPrct(networkId, commoditId, "wholesale");
											if(msrfctr != null)
											{
											measureId = msrfctr.getBaseMeasureId().toString();
											}
										}
										
										else
										{
											String arr6[] = mystring.split("m=", 2);
											String arr7[] = arr6[1].split(",");
											measureId = arr7[0].toString();
											log.info("measureId" + measureId);
										}
										
										if(measureId == null)
										{
											
											message ="Measure is not maintained for commodity.Please contact your system administrator";
											break outer1;
										}
										
										Measure measure = new Measure();
										measure.setMeasureId(measureId);
										Measure msr = measureRepo.findOne(measure, measureId);
										if(msr != null)
										{
											
											int intIndex2 = mystring.indexOf("c=");
											String currencyId = null;
											if(intIndex2 == - 1)
											{
												Location locccy = new Location();
												locccy.setLocationId(country);
												Location loccy1 = locRepo.findOne(locccy, country);
												if(loccy1 != null)
												{
													if(loccy1.getCurrency() != null)
													{
												currencyId = loccy1.getCurrency().toString();
													}
													else
													{
														message= "Currency is not maintained for country.Please contact your system administrator";
														break outer1;
													}
												}
											}
											
											else
											{
												String arr6[] = mystring.split("c=", 2);
												String arr7[] = arr6[1].split(",");
												currencyId = arr7[0].toString();
											}
											
											Currency ccy = new Currency();
											ccy.setCurrencyId(currencyId);
											Currency ccy1 = ccyRepo.findOne(ccy, currencyId);
											outer:
											if (ccy1 != null)
											{
												int intIndex3 = mystring.indexOf("o=");
												String owner = null;
												if(intIndex3 == - 1)
												{
													offerBelongsto = "M";
													log.info("offerBelongsto" + offerBelongsto ); 
												}
												
												else
												{
													offerBelongsto = "A";
													log.info("offerBelongsto" + offerBelongsto ); 

													String arr6[] = mystring.split("o=", 2);
													String arr7[] = arr6[1].split(",");
													owner = arr7[0].toString();
																					
														Number	ownerNumber;
														try
														{
														ownerNumber = Integer.parseInt(owner);
												
														}
														
														catch (NumberFormatException e)
														{
															message = "Invalid entry for Owner Phone number";
															break outer;
														}
													
													SystemUser user1 = sysRepo.queryUserIdbyPhone(owner);
													if(networkId != null)
													{
														People ppl = pplrepo.findByMsisdnNwkId1(owner, networkId);
														if(ppl !=null)
														{
															userNameOrMobNo = ppl.getId().getPeopleId();
														}
														else if (user1 != null)
														{
															userNameOrMobNo = user1.getUserId();
														}
														
														else
														{
															userNameOrMobNo = owner;
														}
													}
												}
												
												if(firstWord.equalsIgnoreCase("B+"))
												{
													offerType = "B";
												}
												else
												{
													offerType = "O";
												}
												
												int intIndex4 = mystring.indexOf("l=");
												String locationId = null;
												if(intIndex4 != - 1)
												{
													String arr6[] = mystring.split("l=", 2);
													String arr7[] = arr6[1].split(",");
													locationId = arr7[0].toString();
													log.info("locationId" + locationId);
												}
												
												if (locationId != null)
												{
												Location locccy = new Location();
												locccy.setLocationId(locationId);
												Location loccy1 = locRepo.findOne(locccy, locationId);
												if(loccy1 == null)
												{
													loccy1 = locRepo.findLocationFromCode(networkId, locationId);
												}	
												
												if (loccy1 == null)
												{
													message = "Invalid Location";	
													break outer;
												}
												locationId = loccy1.getLocationId().toString();
												}
												
												CreateOffersReq req1 = new CreateOffersReq();
												CROFFERDETAILS croffr = new CROFFERDETAILS();
												croffr.setMode("M");
												if(userId != null)
												{
													croffr.setAgentId(userId);
												}
												
												else
												{
													croffr.setAgentId("unknown");
												}
												croffr.setNetworkId(networkId);
												
											

												
												
												Calendar dt = Calendar.getInstance();
												dt.setTime(Utils.getCurrentDate());
												log.info("currentDAte" + dt.getTime().toString());
												Date finalDate = null;
												dt.add(Calendar.DAY_OF_WEEK, 14);
												finalDate = new Date(dt.getTimeInMillis());
												log.info("FinalDate" + finalDate.toString());
												DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
												croffr.setValidTill(dateFormat.format(finalDate));
												
												
												croffr.setNegotiable(negotiable);
												croffr.setCommodity(commoditId);
												croffr.setMeasure(measureId);
												croffr.setOfferType(offerType);
												croffr.setVariety(commoditId);
												if(negotiable.equalsIgnoreCase("n"))
												{
													croffr.setPrice(price_datatype);
													croffr.setPriceCurrency(currencyId);
												}
												croffr.setOfferBelongsTo(offerBelongsto);
											 
												croffr.setUnit(measureId);
												croffr.setQuantity(quantity);
												if(userNameOrMobNo != null)
												{
													croffr.setUserNameOrMobNo(userNameOrMobNo);
												}
												
												else
												{
													croffr.setUserNameOrMobNo("unknown");
												}
                                                if(locationId != null)
                                                {
                                                	croffr.setLocation(locationId);
                                                }
												
                                            	log.info("userId" + userId);
                   							 if(userId != null)
                   							 { dbheader.setUserId(userId);
                   							 }
                   							 dbheader.setOperationName("Inbox upload");
                   							req1.setHeader(dbheader);
                   							req1.setCROFFERDETAILS(croffr);
                   							offsrvc.createOffers(req1);
                   							if(offerType.equalsIgnoreCase("B"))
                   							{
               							    message ="Bid Uploaded Successfully";
                   							}
                   							
                   							else
                   							{
                   							 message ="Offer Uploaded Successfully";
                   							}

											}
											
											else
											{
												message = "Currency is not maintained for country.Please contact your system administrator";
											}
											
										}
										
										else
										{
											message = "Measure is not maintained for commodity.Please contact your system administrator";
										}
										

										
									}
									catch (NumberFormatException e)
									{
										message = "Invalid Value provided for quantity";
									}
										
									}
									
									else
									{
										message = "Invalid Value provided for quantity";
									}
									
								}
								
								catch (NumberFormatException e)
								{
									message = "Invalid Price";
								}
								
							}
							else
							{
								message = "Invalid Price";
							}
							
						}
						
						else
						{
							message = "Please Provide a Valid commodity";
							
						}

						
					}
					
					else
					{
						message = "Please Provide a Valid commodity";
						
					}
				}
				
				else
				{
					message = "No Valid Network Available";
				}
				
			}
			
			else
			{
				message = "No Valid Network Available";
			}
			
			
		}
		
		else
		{	
			 String arr2[] = mystring.split(" ", 2);
				firstWord = arr2[0].toString();
		if (firstWord.equalsIgnoreCase("Price"))
		{   
			if(user != null)
			{
			networkId= user.getDefaultNetworkId().toString();
			userId = user.getUserId().toString();
			}
			
			if(networkId == null)
			{
			Network network = nwkRepo.queryCountryNwk(country);
		    if(network != null)
		    {
		    	networkId = network.getNetworkId().toString();
		    }
		    
			}
				
			// price request
			
			if(networkId != null)
			{
			
			String arr3[] = mystring.split(" ",3);
			if(arr3.length > 1)
			{
				String commodityId = arr3[1].toString();
				log.info("commodityId:" + commodityId);
				
				Commodity entity = new Commodity();
				entity.setCommodityId(commodityId);
				
				Commodity comm = comRepo.findOne(entity, commodityId);
				if(comm == null)
				{
					comm= comRepo.findCommodityFromCode(networkId, commodityId);
				}
				outer:
				if(comm != null)
				{commodityId = comm.getCommodityId().toString();
				 String locaitonId = null;
				log.info("commodityId:" + commodityId);
					if(arr3.length > 2)
					{
						locaitonId = arr3[2].toString();
						Location entity1 = new Location();
						entity1.setLocationId(locaitonId);
						
						Location loc = locRepo.findOne(entity1,locaitonId);
						
						if(loc == null)
						{
							loc = locRepo.findLocationFromCode(networkId, locaitonId);
						}
						
						
						if(loc == null)
						{
							message= "Please provide a valid location";
							break outer;
							
						}
						
						locaitonId = loc.getLocationId().toString();
						
					}
					else
					{
						locaitonId = country;
					}
					
				
					
					if(networkId != null)
					{
						List<PriceUploadMaster> uploadIdList = priceUpldMstrRepo.findPriceRequestDetails(networkId,locaitonId,commodityId);
						if(uploadIdList.size() == 0)
						{   
							Network network = nwkRepo.queryCountryNwk(country);
							if(network != null)
							{
							Network nw = nwkRepo.findOne(network, network.getNetworkId());
							
							if(nw.getType().toString().equalsIgnoreCase("K"))
							{
								message = "No Data Available";
								break outer;
							}
							
							else
							{
								uploadIdList = priceUpldMstrRepo.findPriceRequestDetails(networkId,locaitonId,commodityId);
							}
							}
							
							else
							{
								message = "No Data Available";
								break outer;
							}
					
						}
						
						if(uploadIdList.size() != 0)
						{
							String quoteCurrency = null;
							String toMeasureId = null;
							
							Location locccy = new Location();
							locccy.setLocationId(country);
							Location loccy1 = locRepo.findOne(locccy, country);
							if(loccy1 != null)
							{
								if(loccy1.getCurrency() != null)
								{
								quoteCurrency = loccy1.getCurrency().toString();
								}
								
								else
								{
									message= "Currency is not maintained for country.Please contact your system administrator";
									break outer;
								}
							}
							
							else
							{
								message= "Currency is not maintained for country.Please contact your system administrator";
								break outer;
								
							}
							
						
							List<PriceUploadMaster> prcUpldMstrList = new ArrayList<PriceUploadMaster>();
							for (PriceUploadMaster priceUldMstr : uploadIdList) {
								
							
								String BaseCurrency = priceUldMstr.getCurrencyId();
								String fromMeasureId = priceUldMstr.getMeasureId();
								MeasureFactor factor = factorRepo.queryByNwkCMdPrctDM(
										networkId,
										commodityId,
										priceUldMstr.getPriceType());
								if(factor != null)
								{
								toMeasureId = factor.getBaseMeasureId();
								}
								
								else
								{
									message = "Measure is not maintained for commodity.Please contact your system administrator";
									break outer;
								}
								
								if (BaseCurrency != quoteCurrency || toMeasureId != fromMeasureId) {
									priceUldMstr.setMeasureId(toMeasureId);
									
									log.debug("Quote currency:" + quoteCurrency);
									Forex forex = forexRepo.queryfxRatesbyCcy(
											BaseCurrency, quoteCurrency);
									 factor = null;
									if (toMeasureId != fromMeasureId) {
										factor = factorRepo.queryMeasureFactor(
												toMeasureId, fromMeasureId,priceUldMstr.getCommodity(),networkId,priceUldMstr.getPriceType());
										
									}

									if (forex != null && factor != null) {
										log.debug("Conversion Factor:"
												+ factor.getConvFactor());
										log.debug("Exchange rate:" + forex.getRate());
										priceUldMstr
												.setPrice(new BigDecimal(
														(priceUldMstr.getPrice()
																.doubleValue()
																* forex.getRate()
																		.doubleValue() / factor
																.getConvFactor()
																.doubleValue()))
														.setScale(
																2,
																BigDecimal.ROUND_DOWN));
									} else if (forex != null) {
										log.info("Exchange rate:" + forex.getRate());
										priceUldMstr
												.setPrice(new BigDecimal(
														(priceUldMstr.getPrice()
																.doubleValue() * forex
																.getRate()
																.doubleValue()))
														.setScale(
																2,
																BigDecimal.ROUND_DOWN));
									}
									
									else if (factor != null) {
										log.debug("Conversion Factor:"
												+ factor.getConvFactor());

										priceUldMstr.setPrice(new BigDecimal(
												(priceUldMstr.getPrice()
														.doubleValue() / factor
														.getConvFactor()
														.doubleValue())).setScale(
												2, BigDecimal.ROUND_DOWN));

									}
								}
								prcUpldMstrList.add(priceUldMstr);
								
							}
							
							String priceAlrtTemp;
							priceAlrtTemp = apprvc.getPrcAlrtTemplate(prcUpldMstrList,
									quoteCurrency);
					
							if(priceAlrtTemp != null)
							{
								message =(priceAlrtTemp);
							}
							
							else
							{
								message = "No Data Available";
							}
						}
						else
						{
							message = "No Data Available";
						}
					}
					
					
					
					
				}
				
				else
				{
					message = "Please Provide a Valid commodity";
				}
				
			}
			
			else
			{
				message = "Please Provide a Valid commodity";
			}
			
		}
			
			else
			{
				message= "No valid Network Available";	
			}
			
		}
		else if (firstWord.equalsIgnoreCase("offer") || firstWord.equalsIgnoreCase("sell") || firstWord.equalsIgnoreCase("bid") || firstWord.equalsIgnoreCase("buy"))
		{
			if(user != null)
			{
			networkId= user.getDefaultNetworkId().toString();
			userId = user.getUserId().toString();
			}
			String messageType;
			if(firstWord.equalsIgnoreCase("offer") || firstWord.equalsIgnoreCase("sell"))
			{
				messageType = "O";
			}
			
			else
			{
				messageType ="B";
			}
			
			if(networkId == null)
			{
			Network network = nwkRepo.queryCountryNwk(country);
		    if(network != null)
		    {
		    	networkId = network.getNetworkId().toString();
		    }
		    
			}
			
			if(networkId != null)
			{
			
			String arr3[] = mystring.split(" ",3);
			if(arr3.length > 1)
			{
				String commodityId = arr3[1].toString();
				log.info("commodityId:" + commodityId);
				
				Commodity entity = new Commodity();
				entity.setCommodityId(commodityId);
				
				Commodity comm = comRepo.findOne(entity, commodityId);
				if(comm == null)
				{
					comm= comRepo.findCommodityFromCode(networkId, commodityId);
				}
				
				outer:
					if(comm != null)
					{
						commodityId = comm.getCommodityId().toString();
						 String locaitonId = null;
						log.info("commodityId:" + commodityId);
							if(arr3.length > 2)
							{
								locaitonId = arr3[2].toString();
								Location entity1 = new Location();
								entity1.setLocationId(locaitonId);
								
								Location loc = locRepo.findOne(entity1,locaitonId);
								
								if(loc == null)
								{
									loc = locRepo.findLocationFromCode(networkId, locaitonId);
								}
								
								
								if(loc == null)
								{
									message= "Please provide a valid location";
									break outer;
									
								}
								
								locaitonId = loc.getLocationId().toString();
								
							}
							else
							{
								if(user != null)
								{
									locaitonId=user.getTown();	
								}
								if(locaitonId == null)
								{
									locaitonId = country;
								}
							}
							
							Location locgis = new Location();
							locgis.setLocationId(locaitonId);
							Location locgis1 = locRepo.findOne(locgis, locaitonId);
							
						String arr9[] = GeoUtils.getCoordinates( locgis1.getGis());	
						double Latitude = Double
								.parseDouble(arr9[0]);
						log.info("latitude" + Latitude);
						
						double Longitude = Double
								.parseDouble(arr9[1]);
						log.info("longitude" + Longitude);
						
						List<BidsOffersMaster> entity1 = boRepo.bidsofferbydistance(messageType, commodityId, locaitonId, Latitude, Longitude);
						StringBuilder request = new StringBuilder();
						int j=0;
						if(entity1 != null)
						{
						for(BidsOffersMaster bidsAndOffrs: entity1)
						{   if(j == 0)
						{ if (messageType.equalsIgnoreCase("B"))
						{
							request.append("bids" + " for " + commodityId );
						}
						else
						{
							request.append("offer" + " for " + commodityId);
						}
						}
						
						else
						{
							request.append("/");
						}
						
						
						
						if (bidsAndOffrs.getOfferUserid() != null)
						{
							request.append(" Contact  " + bidsAndOffrs.getOfferUserid());
							SystemUser usr = new SystemUser();
							usr.setUserId(bidsAndOffrs.getOfferUserid());
							SystemUser usr1 = sysRepo.findOne(usr, bidsAndOffrs.getOfferUserid());
							if (usr1 != null)
							{
								if(usr1.getMsisdn() != null)
								{
								request.append(" " + usr1.getMsisdn());
								}
							}
						}
							
						if (bidsAndOffrs.getLocation() != null)
						{
							request.append(" " + bidsAndOffrs.getLocation());
						}
						
						request.append(" " + bidsAndOffrs.getQuantity());
						if(bidsAndOffrs.getNegotiableFlag().equalsIgnoreCase("Y"))
						{
							request.append("(" + bidsAndOffrs.getMeasure() + ") Negotiable" );
						}
						
						else
						{
							request.append("(" + bidsAndOffrs.getMeasure() + ")" + " "  + bidsAndOffrs.getPriceAmount() + " " + bidsAndOffrs.getCurrencyId() );
						}
						j++;
						
						}
						
						message = request.toString();
						}
						else
						{
							message = "No Data Available";
						}
						
					}
				
					else
					{
						message = "Please Provide a Valid commodity";
					}
             }
			
			else
			{
				message = "Please Provide a Valid commodity";
			}
			
		}
			
			else
			{
				message= "No valid Network Available";	
			}
			// bids & offer request
		}
		
		else if (firstWord.equalsIgnoreCase("STOP") || firstWord.equalsIgnoreCase("START"))
		{
			// start & stop activity
			
			SmsController smscon = new SmsController();
			smscon.setMsisdn(inbox.getMsisdn());
			
			SmsController smscon1 = contRepo.findOne(smscon, inbox.getMsisdn());
			if(smscon1 == null)
			{
				smscon.setTimiLimit(null);
				smscon.setAction(firstWord);
				smscon.setCreatedTs(Utils.getCurrentDate());
				
				String arr3[] = mystring.split(" ",3);
				if(arr3.length >1)
				{   Integer timelim;
					try
					{
						timelim = Integer.parseInt(arr3[1].toString());
						smscon.setTimiLimit(arr3[1].toString());
					}
					catch (NumberFormatException e)
					{
						if(firstWord.toString().equalsIgnoreCase("STOP"))
						{
							smscon.setTimiLimit("-1");
						}
					
						}
					
				}
				else if(firstWord.toString().equalsIgnoreCase("STOP"))
				{
					smscon.setTimiLimit("-1");
				}
				
				if(smscon.getTimiLimit() != null)
				{
				contRepo.save(smscon);
				}
			   
			}
			
			else
			{   smscon1.setTimiLimit(null);
				smscon1.setAction(firstWord);
				smscon1.setCreatedTs(Utils.getCurrentDate());
				
				String arr3[] = mystring.split(" ",3);
				if(arr3.length >1)
				{   Integer timelim;
					try
					{
						timelim = Integer.parseInt(arr3[1].toString());
						smscon1.setTimiLimit(arr3[1].toString());
					}
					catch (NumberFormatException e)
					{
						if(firstWord.toString().equalsIgnoreCase("STOP"))
						{
							smscon1.setTimiLimit("-1");
						}
					
						}
					
				}
				else if(firstWord.toString().equalsIgnoreCase("STOP"))
				{
					smscon1.setTimiLimit("-1");
				}
				
				if(smscon1.getTimiLimit() != null)
				{
				contRepo.merge(smscon1);
				}
				
				else
				{
				 contRepo.delete(smscon1);	
				}
				
			}
		   
		
		}
		
		else
			
		{
			
			returnCount = kywrdRepo.inputKeywordCheck(inbox.getChannel(), firstWord);
			if(returnCount > 0)
			{
				Keyword entity = new Keyword();
				KeywordPK entitypk = new KeywordPK();
				entitypk.setChannel(inbox.getChannel());
				entitypk.setKeyword(firstWord);
				entity.setId(entitypk);
				Keyword keyword = kywrdRepo.findOne(entity, entitypk);
				if(keyword == null)
				{
					KeywordAlia alias = kyrdAliRepo.findKeyword(firstWord, inbox.getChannel());
					
					firstWord = alias.getId().getKeyword().toString();
					entitypk.setChannel(inbox.getChannel());
					entitypk.setKeyword(firstWord);
					keyword = kywrdRepo.findOne(entity, entitypk);
				}
				
				if(keyword.getJoinFlag().equalsIgnoreCase("Y"))
				{
					if(user != null)
					{
					networkId = keyword.getNetworkId().toString();
					pplrepo.keywordNWjoin(userId, networkId);
					}
					
					else
					{
						//create ppl
					}
					
					if(keyword.getGroups() != null)
					{
						// add ppl to group
					}
					
					if(keyword.getEalerts() != null)
					{
						// add ppl to alert recipeints
					}
				
				}
				
				
			}
			/*  
			 if matches keyword(action)
			
			else
			
			if ( matches price request )
			else
			
			if (poll code)
			
			 else
			  
			  invalid message
			 
			*/
			
		}
		
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.INBOX,
				"InboxService", message, "", ERROR_CODE.ES_SC_001);
		res.setHeader(header);

		return res;
	}
}
