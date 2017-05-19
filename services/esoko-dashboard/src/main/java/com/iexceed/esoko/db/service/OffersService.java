package com.iexceed.esoko.db.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.SystemUserRepo;
import com.iexceed.esoko.domain.dao2.AgentDetailRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.UploadMasterRepo;
import com.iexceed.esoko.domain.dao3.BidsOffersMasterRepo;
import com.iexceed.esoko.domain.entity.SystemUser;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.domain.entity3.BidsOffersMaster;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.CROFFERDETAILS;
import com.iexceed.esoko.jaxb.db.CreateOffersReq;
import com.iexceed.esoko.jaxb.db.CreateOffersRes;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.QROFFERDETAILS;
import com.iexceed.esoko.jaxb.db.QueryOffersRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;


/*
 * Ankur
 */
@Service
public class OffersService {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	UploadMasterRepo masterRepo;

	@Autowired
	AgentDetailRepo agentRepo;

	@Autowired
	PeopleRepo peopleRepo;

	@Autowired
	EsokoAppRepo esokoAppRepo;

	@Autowired
	BidsOffersMasterRepo bidsOfferMasterRepo;

	@Autowired
	SystemUserRepo sysUserRepo;

	public QueryOffersRes queryOffers(String networkId, String userId) {
		log.info("Inside QueryOffersRes -> queryOffers");
		log.debug("NetworkId: " + networkId);
		log.debug("UserId: " + userId);
		QueryOffersRes offersRes = new QueryOffersRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<QROFFERDETAILS> offerDtlsLst = new ArrayList<QROFFERDETAILS>();

		QROFFERDETAILS offerDtls1 = new QROFFERDETAILS();
		offerDtls1.setBids(12000);
		offerDtls1.setOfferName("Red Apple");
		offerDtls1.setOffers(1000);

		QROFFERDETAILS offerDtls2 = new QROFFERDETAILS();
		offerDtls2.setBids(0);
		offerDtls2.setOfferName("Green Pepper");
		offerDtls2.setOffers(1);

		offerDtlsLst.add(offerDtls1);
		offerDtlsLst.add(offerDtls2);

		offersRes.getQROFFERDETAILS().addAll(offerDtlsLst);
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"OffersService", "queryOffers", "", errorCode);
		offersRes.setHeader(header);
		return offersRes;
	}

	@Transactional
	public CreateOffersRes createOffers(CreateOffersReq req) {
		log.info("Inside CreateOffersRes -> createOffers");
		CreateOffersRes offersRes = new CreateOffersRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		CROFFERDETAILS offerDtls = req.getCROFFERDETAILS();
        
		AgentDetail agentDetail = agentRepo.findOfferDetailId(
				offerDtls.getNetworkId(), offerDtls.getAgentId());
		if (agentDetail == null && !req.getHeader().getOperationName().equalsIgnoreCase("inbox upload")) {
			People people = peopleRepo.getNameByNwkId(offerDtls.getAgentId(),
					offerDtls.getNetworkId());
			String name = null;
			if (people != null) {
				if (people.getLastName() != null) {
					name = people.getFirstName() + " " + people.getLastName();
				} else {
					name = people.getFirstName();
				}
			} else {
				name = "User";
			}
			errorCode = ERROR_CODE.NEWS_ER_001;
			HeaderFactory.setExtraParamMap("$1", name);
		} else {
			String id = "OFFER" + esokoAppRepo.getSequenceNumber();

			UploadMaster master = new UploadMaster();
			master.setUploadId(id);
			master.setApplicationId("Offers");
			master.setAgentId(offerDtls.getAgentId());
			if(agentDetail != null)
			{
			master.setDetailId(agentDetail.getDetailId());
			}
		
			master.setNetworkId(offerDtls.getNetworkId());
			master.setUploadDt(Utils.getCurrentDate());
			master.setAuthStat("U");
			master.setAuthBy(null);
			master.setAuthTs(null);

			BidsOffersMaster bidsOfferMaster = new BidsOffersMaster();
			bidsOfferMaster.setUpload_ID(id);
			bidsOfferMaster.setBidOfferFlag(offerDtls.getOfferType());
			bidsOfferMaster.setCommodity(offerDtls.getCommodity());
			bidsOfferMaster.setVariety(offerDtls.getVariety());
			if(offerDtls.getType() != null)
			{
			bidsOfferMaster.setType(offerDtls.getType());
			}
			if(offerDtls.getOrigin() != null)
			{
			bidsOfferMaster.setOrigin(offerDtls.getOrigin());
			}
			bidsOfferMaster
					.setQuantity(new BigDecimal(offerDtls.getQuantity()));
			bidsOfferMaster.setMeasure(offerDtls.getMeasure());
			if ("Y".equalsIgnoreCase(offerDtls.getNegotiable())) {
				bidsOfferMaster.setNegotiableFlag("Y");

			} else {
				bidsOfferMaster.setPriceAmount(new BigDecimal(offerDtls
						.getPrice()));
				bidsOfferMaster.setCurrencyId(offerDtls.getPriceCurrency());
				bidsOfferMaster.setAmountUnit(offerDtls.getUnit());
				bidsOfferMaster.setNegotiableFlag("N");
			}
			if(offerDtls.getValidTill() != null)
			{
			bidsOfferMaster.setExpiryDate(Utils.getFmtDtDDMMYYYY(offerDtls
					.getValidTill()));
			}
			if (("M".equalsIgnoreCase(offerDtls.getOfferBelongsTo()))) {
				if(offerDtls.getAgentId() != null)
				{
				bidsOfferMaster.setOfferUserid(offerDtls.getAgentId());
				}
				bidsOfferMaster.setOfferOwner("M");
				if(offerDtls.getLocation() == null)
				{
				SystemUser sysUser = sysUserRepo.findTownByUserId(offerDtls
						.getAgentId());
				if (sysUser != null) {
					bidsOfferMaster.setLocation(sysUser.getTown());
				}
				}
				else
				{
					bidsOfferMaster.setLocation(offerDtls.getLocation());	
				}
			}

			else {
				bidsOfferMaster.setOfferUserid(offerDtls.getUserNameOrMobNo());
				bidsOfferMaster.setOfferUploadedBy(offerDtls.getAgentId());
				bidsOfferMaster.setOfferOwner("A");
				if(offerDtls.getLocation() == null)
				{
				SystemUser sysUser = sysUserRepo.findTownByUserId(offerDtls
						.getUserNameOrMobNo());
				if (sysUser != null) {
					bidsOfferMaster.setLocation(sysUser.getTown());
				}
				}
				else
				{
					bidsOfferMaster.setLocation(offerDtls.getLocation());
				}
			}
            if(offerDtls.getPaymentDetails() != null)
            {
			bidsOfferMaster.setPayementMode(offerDtls.getPaymentDetails());
            }
            if(offerDtls.getDeliveryPoint() != null)
            {
			bidsOfferMaster.setDeliveryPoint(offerDtls.getDeliveryPoint());
            }
            if(offerDtls.getDeliverBy() != null)
            {
			bidsOfferMaster.setDeliveryType(offerDtls.getDeliverBy());
			if (!("I".equalsIgnoreCase(offerDtls.getDeliverBy()))) {
				bidsOfferMaster.setNoOfDays(offerDtls.getDeliverDays());
			}
            }
            if(offerDtls.getDocuments() != null)
            {
			bidsOfferMaster.setDocuments(offerDtls.getDocuments());
            }
            if(offerDtls.getNotes() != null)
            {
			bidsOfferMaster.setNotes(offerDtls.getNotes());
            }
            if(offerDtls.getGrade() != null)
            {
			bidsOfferMaster.setGrade(offerDtls.getGrade());
            }
			bidsOfferMaster.setUploadMode(offerDtls.getMode());

			bidsOfferMaster.setNetworkId(offerDtls.getNetworkId());
			bidsOfferMaster.setAuthStat("U");
			bidsOfferMaster.setAuthBy("");
			bidsOfferMaster.setAuthTs(null);
			bidsOfferMaster.setCreatedBy("");
			bidsOfferMaster.setCreatedTs(Utils.getCurrentDate());

			masterRepo.save(master);
			bidsOfferMasterRepo.save(bidsOfferMaster);
			errorCode = ERROR_CODE.OFR_SC;

		}

		header = (Header) HeaderFactory.getHeader(HeaderType.DB,
				"OffersService", "createOffers", req.getHeader().getUserId(),
				errorCode);
		offersRes.setHeader(header);
		return offersRes;
	}
}
