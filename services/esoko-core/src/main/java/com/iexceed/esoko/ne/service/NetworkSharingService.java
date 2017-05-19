package com.iexceed.esoko.ne.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.beans.NwkSharingBean;
import com.iexceed.esoko.beans.SharingRequestBean;
import com.iexceed.esoko.domain.dao.NetworkPictureRepo;
import com.iexceed.esoko.domain.dao.NetworkRepo;
import com.iexceed.esoko.domain.dao.Profile_Sharing_typeRepo;
import com.iexceed.esoko.domain.dao.UserShareRepo;
import com.iexceed.esoko.domain.entity.Network;
import com.iexceed.esoko.domain.entity.NetworkPicture;
import com.iexceed.esoko.domain.entity.Profile_Sharing_type;
import com.iexceed.esoko.domain.entity.Profile_Sharing_typePK;
import com.iexceed.esoko.domain.entity.UserShare;
import com.iexceed.esoko.domain.entity.UserSharePK;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.ns.ALLNWK;
import com.iexceed.esoko.jaxb.ns.APPSHAREITEMS;
import com.iexceed.esoko.jaxb.ns.APRVSHARE;
import com.iexceed.esoko.jaxb.ns.ApproveSharingRequest;
import com.iexceed.esoko.jaxb.ns.ApproveSharingRequestRes;
import com.iexceed.esoko.jaxb.ns.CRSHAREDTLS;
import com.iexceed.esoko.jaxb.ns.CreateShareRequest;
import com.iexceed.esoko.jaxb.ns.CreateShareRequestRes;
import com.iexceed.esoko.jaxb.ns.EDITPROILESHARE;
import com.iexceed.esoko.jaxb.ns.EditProfileShareReq;
import com.iexceed.esoko.jaxb.ns.EditProfileShareRes;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.MDSHAREDTLS;
import com.iexceed.esoko.jaxb.ns.ModifyShareDtlsReq;
import com.iexceed.esoko.jaxb.ns.ModifyShareDtlsRes;
import com.iexceed.esoko.jaxb.ns.PROFILEDTLS;
import com.iexceed.esoko.jaxb.ns.QRYNWKCOUNTDTLS;
import com.iexceed.esoko.jaxb.ns.QRYPROFILEDTLS;
import com.iexceed.esoko.jaxb.ns.QueryAllNwksRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkCountDtlsRes;
import com.iexceed.esoko.jaxb.ns.QueryNwkSharingDataRes;
import com.iexceed.esoko.jaxb.ns.QueryPeopleProfilesRes;
import com.iexceed.esoko.jaxb.ns.QueryProfileDetailsRes;
import com.iexceed.esoko.jaxb.ns.SHAREDTLS;
import com.iexceed.esoko.jaxb.ns.SHAREITEMS;
import com.iexceed.esoko.jaxb.ns.SHAREREQDTLS;
import com.iexceed.esoko.jaxb.ns.STPSHARE;
import com.iexceed.esoko.jaxb.ns.SharingRequestsDataRes;
import com.iexceed.esoko.jaxb.ns.StopSharingReq;
import com.iexceed.esoko.jaxb.ns.StopSharingRes;
import com.iexceed.esoko.jaxb.people.NWKDTLS;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

/*
 * @author Gangadhar
 */
@Service
public class NetworkSharingService {
	public static Logger log = LoggerUtils.getLogger();

	Enum<ERROR_CODE> ERROR = ERROR_CODE.ES_SV_002;
	Header header = null;
	private final String serviceName = "NetworkSharingService";

	public NetworkSharingService() {

	}

	@Autowired
	UserShareRepo repo;
	@Autowired
	Profile_Sharing_typeRepo profileRepo;
	@Autowired
	NetworkRepo nwkRepo;
	@Autowired
	NetworkPictureRepo nwpic;

	@Transactional
	public CreateShareRequestRes createShareRes(CreateShareRequest req) {
		log.info("Inside CreateShareRequestRes -> createShareRes");
		CreateShareRequestRes svDataSharingRes = new CreateShareRequestRes();
		CRSHAREDTLS shareDtls = req.getCRSHAREDTLS();
		String fromNetwork = null;
		String toNetwork = null;
		try {
			List<SHAREITEMS> shareItemsList = req.getCRSHAREDTLS()
					.getSHAREITEMS();
			log.debug("shareItemsList ->" + shareItemsList);
			log.debug("shareDtls.getFromNetwork()::"
					+ shareDtls.getFromNetwork());
			log.debug("shareDtls.getToNetwork()::" + shareDtls.getToNetwork());
			if (!StringUtils.isNotEmpty(shareDtls.getFromNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Create Share DtlsRes", "",
						ERROR_CODE.FRM_NW_ER);
				svDataSharingRes.setHeader(header);
				return svDataSharingRes;
			}
			if (!StringUtils.isNotEmpty(shareDtls.getToNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Create Share DtlsRes", "",
						ERROR_CODE.TO_NW_ER);
				svDataSharingRes.setHeader(header);
				return svDataSharingRes;
			}
			if (!StringUtils.isNotEmpty(req.getCRSHAREDTLS().getSHAREITEMS()
					.toString())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Create Share DtlsRes", "",
						ERROR_CODE.SS_SUBPR_ER2);
				svDataSharingRes.setHeader(header);
				return svDataSharingRes;
			} else {
				if (shareItemsList.size() != 0) {
					for (SHAREITEMS shareItems : shareItemsList) {
						UserShare entity = new UserShare();
						UserSharePK entityPk = new UserSharePK();
						if (shareItems.getShareType().equalsIgnoreCase("S")) {
							fromNetwork = shareDtls.getFromNetwork();
							toNetwork = shareDtls.getToNetwork();

						} else {
							toNetwork = shareDtls.getFromNetwork();
							fromNetwork = shareDtls.getToNetwork();

						}
						entityPk.setFromShare(fromNetwork);
						entityPk.setToShare(toNetwork);
						entityPk.setShareItem(shareItems.getShareItem());
						entity.setShareType(shareItems.getShareType());
						entity.setId(entityPk);
						if (!repo.exists(entity, entityPk)) {
							if (shareItems.getShareType().equalsIgnoreCase("S")) {
								log.debug("PeopleType::"
										+ shareDtls.getPeopleType());
								entity.setPeopleType(shareDtls.getPeopleType());

							}
							log.debug("ShareItem -> "
									+ shareItems.getShareItem());
							entity.setShareStatus(shareItems.getShareStatus());
							entity.setAuthStat("U");
							entity.setCreatedBy(req.getHeader().getUserId());
							entity.setCreatedTs(Utils.getCurrentDate());
							repo.save(entity);
						} else {
							entity = repo.findOne(entity, entityPk);
							repo.delete(entity);
							if (shareItems.getShareType().equalsIgnoreCase("S")) {
								log.debug("PeopleType::"
										+ shareDtls.getPeopleType());
								entity.setPeopleType(shareDtls.getPeopleType());
								entity.setPeopleMode(shareDtls.getPeopleMode());

							}
							entityPk.setFromShare(fromNetwork);
							entityPk.setToShare(toNetwork);
							entityPk.setShareItem(shareItems.getShareItem());
							entity.setShareType(shareItems.getShareType());
							entity.setId(entityPk);
							entity.setShareStatus(shareItems.getShareStatus());
							entity.setAuthStat("U");
							entity.setCreatedBy(req.getHeader().getUserId());
							entity.setCreatedTs(Utils.getCurrentDate());
							repo.save(entity);
						}
					}
					ERROR = ERROR_CODE.NS_SHR_CR;
					Network entity = new Network();
					entity = nwkRepo.findOne(entity,toNetwork);
					HeaderFactory.setExtraParamMap("$1",
							entity.getName());
				}
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Create Share", "", ERROR);
		svDataSharingRes.setHeader(header);
		return svDataSharingRes;
	}

	public QueryNwkSharingDataRes queryNwkSharingData(String networkId) {
		log.info("Inside QueryNwkSharingDataRes -> queryNwkSharingData");
		log.debug("NetworkId: " + networkId);
		QueryNwkSharingDataRes qNwkSharingData = new QueryNwkSharingDataRes();
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					serviceName, "Query Share DtlsRes", "",
					ERROR_CODE.ES_PK_002);
			qNwkSharingData.setHeader(header);
			return qNwkSharingData;
		}
		List<Map<String, Object>> sendingShareList = repo
				.querySendingShareDtls(networkId);
		List<Map<String, Object>> receivingShareList = repo
				.queryReceivingShareDtls(networkId);
		Set<NwkSharingBean> treeSet = this.compareNwkShareList(
				sendingShareList, receivingShareList);
		List<SHAREDTLS> shareDtlsList = new ArrayList<SHAREDTLS>();
		if (treeSet.size() != 0) {
			for (NwkSharingBean str : treeSet) {
				log.debug("str -> " + str.getNetworkId());
				SHAREDTLS shareDtls = new SHAREDTLS();
				shareDtls.setNetworkId(str.getNetworkId());
				Network nwkEntity = nwkRepo.queryNetworkUserInfo(str
						.getNetworkId());
				if (nwkEntity != null) {
					shareDtls.setNetworkName(nwkEntity.getName());
				}
				shareDtls.setPeople(str.getPeople());
				shareDtls.setOffers(str.getOffers());
				shareDtls.setPrices(str.getPrices());
				shareDtls.setNewsAndLibrary(str.getNewsAndLibrary());
				shareDtlsList.add(shareDtls);

			}
			qNwkSharingData.getSHAREDTLS().addAll(shareDtlsList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		Header header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				serviceName, "queryNwkSharingData", "", ERROR);
		qNwkSharingData.setHeader(header);
		return qNwkSharingData;
	}

	@Transactional
	public StopSharingRes stopSharingRes(StopSharingReq req) {
		log.info("Inside StopSharingRes -> stopSharingRes");
		StopSharingRes stopSharing = new StopSharingRes();
		STPSHARE shareDtls = req.getSTPSHARE();
		ERROR = ERROR_CODE.DM_SV_002;
		try {
			int count = 0;
			if (!StringUtils.isNotEmpty(shareDtls.getFromNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Stop Share DtlsRes", "",
						ERROR_CODE.FRM_NW_ER);
				stopSharing.setHeader(header);
				return stopSharing;
			}
			if (!StringUtils.isNotEmpty(shareDtls.getToNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Stop Share DtlsRes", "",
						ERROR_CODE.TO_NW_ER);
				stopSharing.setHeader(header);
				return stopSharing;
			}
			if (shareDtls.getFlag().equalsIgnoreCase("D")) {
				count = repo.delete(shareDtls.getFromNetwork(),
						shareDtls.getToNetwork());
				if (count != 0) {
					ERROR = ERROR_CODE.NS_SHR_STP;
				} else {
					ERROR = ERROR_CODE.DM_SV_002;
				}
			} else if (shareDtls.getFlag().equalsIgnoreCase("S")) {
				count = repo.suspendSharings(shareDtls.getFromNetwork(),
						shareDtls.getToNetwork(), req.getHeader().getUserId());
				if (count != 0) {
					ERROR = ERROR_CODE.ES_SH_001;
				} else {
					ERROR = ERROR_CODE.DM_SV_002;
				}
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_003;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Stop Share", "", ERROR);
		stopSharing.setHeader(header);
		return stopSharing;
	}

	@Transactional
	public ModifyShareDtlsRes modifySharingTypeDtls(ModifyShareDtlsReq req) {
		log.info("Inside StopSharingRes -> stopSharingRes");
		ModifyShareDtlsRes modifySharing = new ModifyShareDtlsRes();
		MDSHAREDTLS shareDtls = req.getMDSHAREDTLS();
		ERROR = ERROR_CODE.DM_SV_002;
		try {
			int count = 0;
			if (!StringUtils.isNotEmpty(shareDtls.getFromNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Modify Share DtlsRes", "",
						ERROR_CODE.FRM_NW_ER);
				modifySharing.setHeader(header);
				return modifySharing;
			}
			if (!StringUtils.isNotEmpty(shareDtls.getToNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Modify Share DtlsRes", "",
						ERROR_CODE.TO_NW_ER);
				modifySharing.setHeader(header);
				return modifySharing;
			}
			if (!StringUtils.isNotEmpty(shareDtls.getShareItem())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Modify Share DtlsRes", "",
						ERROR_CODE.SH_ITEM_ER);
				modifySharing.setHeader(header);
				return modifySharing;
			}
			if (!StringUtils.isNotEmpty(req.getHeader().getUserId())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Modify Share DtlsRes", "",
						ERROR_CODE.RQ_USER_ER);
				modifySharing.setHeader(header);
				return modifySharing;
			}
			if (shareDtls.getSendReceive().equalsIgnoreCase("S")) {
				if (shareDtls.getAction().equalsIgnoreCase("S")) {
					count = repo.suspendShareTypeDtls(
							shareDtls.getFromNetwork(),
							shareDtls.getToNetwork(), shareDtls.getShareItem(),
							shareDtls.getAction(), req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_ED;
					}
				} else if (shareDtls.getAction().equalsIgnoreCase("R")) {
					count = repo.suspendShareTypeDtls(
							shareDtls.getFromNetwork(),
							shareDtls.getToNetwork(), shareDtls.getShareItem(),
							shareDtls.getAction(), req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_ED;
					}
				} else if (shareDtls.getAction().equalsIgnoreCase("D")) {
					count = repo.suspendShareTypeDtls(
							shareDtls.getFromNetwork(),
							shareDtls.getToNetwork(), shareDtls.getShareItem(),
							shareDtls.getAction(), req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_STP;
					}
				}
			}
			if (shareDtls.getSendReceive().equalsIgnoreCase("R")) {
				if (shareDtls.getAction().equalsIgnoreCase("A")) {
					count = repo.suspendShareTypeDtls(shareDtls.getToNetwork(),
							shareDtls.getFromNetwork(),
							shareDtls.getShareItem(), shareDtls.getAction(),
							req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_ED;
					}
				} else if (shareDtls.getAction().equalsIgnoreCase("S")) {
					count = repo.suspendShareTypeDtls(shareDtls.getToNetwork(),
							shareDtls.getFromNetwork(),
							shareDtls.getShareItem(), shareDtls.getAction(),
							req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_ED;
					}
				} else if (shareDtls.getAction().equalsIgnoreCase("R")) {
					count = repo.suspendShareTypeDtls(shareDtls.getToNetwork(),
							shareDtls.getFromNetwork(),
							shareDtls.getShareItem(), shareDtls.getAction(),
							req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_ED;
					}
				} else if (shareDtls.getAction().equalsIgnoreCase("D")) {
					count = repo.suspendShareTypeDtls(shareDtls.getToNetwork(),
							shareDtls.getFromNetwork(),
							shareDtls.getShareItem(), shareDtls.getAction(),
							req.getHeader().getUserId());
					if (count > 0) {
						ERROR = ERROR_CODE.NS_SHR_STP;
					}
				}
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.DM_SV_003;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Stop Share", "", ERROR);
		modifySharing.setHeader(header);
		return modifySharing;
	}

	public Set<NwkSharingBean> compareNwkShareList(
			List<Map<String, Object>> sendingShareList,
			List<Map<String, Object>> receivingShareList) {
		ArrayList<NwkSharingBean> al1 = new ArrayList<NwkSharingBean>();
		ArrayList<NwkSharingBean> al2 = new ArrayList<NwkSharingBean>();
		Set<NwkSharingBean> treeSet = new HashSet<NwkSharingBean>();
		if (sendingShareList.size() != 0) {
			for (Map<String, Object> entity : sendingShareList) {
				NwkSharingBean bean = new NwkSharingBean();
				log.debug("networId11 -> " + entity.get("networId"));
				bean.setNetworkId(entity.get("networId").toString());
				bean.setPrices(entity.get("prices").toString());
				bean.setOffers(entity.get("offers").toString());
				bean.setNewsAndLibrary(entity.get("newsAndLibrary").toString());
				bean.setPeople(entity.get("people").toString());
				al1.add(bean);
			}

		}
		if (receivingShareList.size() != 0) {
			for (Map<String, Object> entity : receivingShareList) {
				log.debug("networId22 -> " + entity.get("networId"));
				NwkSharingBean bean1 = new NwkSharingBean();
				bean1.setNetworkId(entity.get("networId").toString());
				bean1.setPrices(entity.get("prices").toString());
				bean1.setOffers(entity.get("offers").toString());
				bean1.setNewsAndLibrary(entity.get("newsAndLibrary").toString());
				bean1.setPeople(entity.get("people").toString());
				al2.add(bean1);
			}
		}
		treeSet.addAll(al1);
		treeSet.addAll(al2);
		return treeSet;
	}

	public SharingRequestsDataRes sharingRequestsDtls(String networkId) {
		SharingRequestsDataRes res = new SharingRequestsDataRes();
		ERROR = ERROR_CODE.ES_NR_001;
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					serviceName, "Query Share DtlsRes", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}
		List<Map<String, Object>> yourRequestedShareList = repo
				.queryyourRequestedShareDtls(networkId);
		List<Map<String, Object>> yourRequestedShareList1 = repo
				.queryyourRequestedShareDtls1(networkId);
		Set<SharingRequestBean> treeSet = this.compareShareRequestList(
				yourRequestedShareList, yourRequestedShareList1);
		log.debug("yourRequestedShareList::" + yourRequestedShareList);
		log.debug("yourRequestedShareList1::" + yourRequestedShareList1);
		List<Map<String, Object>> theyRequestedShareList = repo
				.queryTheyRequestedShareDtls(networkId);
		List<Map<String, Object>> theyRequestedShareList1 = repo
				.queryTheyRequestedShareDtls1(networkId);
		Set<SharingRequestBean> treeSet1 = this.compareShareRequestList(
				theyRequestedShareList, theyRequestedShareList1);
		log.debug("theyRequestedShareList::" + theyRequestedShareList);
		log.debug("theyRequestedShareList1::" + theyRequestedShareList1);
		List<SHAREREQDTLS> shareDtlsList = new ArrayList<SHAREREQDTLS>();
		if (treeSet.size() != 0) {
			for (SharingRequestBean str : treeSet) {
				SHAREREQDTLS shareDtls = new SHAREREQDTLS();
				shareDtls.setNetworkId(str.getNetworkId());
				shareDtls.setPeople(str.getPeople());
				shareDtls.setOffers(str.getOffers());
				shareDtls.setPrices(str.getPrices());
				shareDtls.setNewsAndLibrary(str.getNewsAndLibrary());
				shareDtls.setSendReceiveFlag("S");
				List<QRYNWKCOUNTDTLS> networkList = this
						.queryNetworkProfileDtls(str.getNetworkId());
				if (networkList.size() != 0) {
					for (QRYNWKCOUNTDTLS nwkDtls : networkList) {
						shareDtls.setNetworkName(nwkDtls.getNetwork());
						shareDtls.setNoOfPeople(nwkDtls.getNoOfPeople());
						NetworkPicture pictureEntity = nwpic.queryNwrPic(str.getNetworkId());
						if (pictureEntity != null) {
							shareDtls.setProfile(pictureEntity.getContent());
						}
						shareDtls.setLocation(nwkDtls.getLocation());
						UserShare tmp = repo.queryProfileTypeforshare(networkId, str.getNetworkId());
						if(tmp != null)
						{
							shareDtls.setProfileType(tmp.getPeopleType());
						}

					}
					
					
				}
				shareDtlsList.add(shareDtls);

			}
		}
		log.debug("treeSet -> " + treeSet);
		if (treeSet1.size() != 0) {
			for (SharingRequestBean str : treeSet1) {
				log.debug("str11 -> " + str.getNetworkId());
				SHAREREQDTLS shareDtls = new SHAREREQDTLS();
				shareDtls.setNetworkId(str.getNetworkId());
				shareDtls.setPeople(str.getPeople());
				shareDtls.setOffers(str.getOffers());
				shareDtls.setPrices(str.getPrices());
				shareDtls.setNewsAndLibrary(str.getNewsAndLibrary());
				shareDtls.setSendReceiveFlag("R");
				List<QRYNWKCOUNTDTLS> networkList = this
						.queryNetworkProfileDtls(str.getNetworkId());
				if (networkList.size() != 0) {
					for (QRYNWKCOUNTDTLS nwkDtls : networkList) {
						shareDtls.setNetworkName(nwkDtls.getNetwork());
						shareDtls.setNoOfPeople(nwkDtls.getNoOfPeople());
						NetworkPicture pictureEntity = nwpic.queryNwrPic(str.getNetworkId());
						if (pictureEntity != null) {
							shareDtls.setProfile(pictureEntity.getContent());
						}
						shareDtls.setLocation(nwkDtls.getLocation());

					}
				}
				shareDtlsList.add(shareDtls);

			}
			log.debug("treeSet1 -> " + treeSet1);
		}
		res.getSHAREREQDTLS().addAll(shareDtlsList);
		ERROR = ERROR_CODE.ES_SC_001;
		Header header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				serviceName, "Sharing Requests Dtls", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public Set<SharingRequestBean> compareShareRequestList(
			List<Map<String, Object>> sendingShareList,
			List<Map<String, Object>> receivingShareList) {
		ArrayList<SharingRequestBean> al1 = new ArrayList<SharingRequestBean>();
		ArrayList<SharingRequestBean> al2 = new ArrayList<SharingRequestBean>();
		Set<SharingRequestBean> treeSet = new HashSet<SharingRequestBean>();
		if (sendingShareList.size() != 0) {
			for (Map<String, Object> entity : sendingShareList) {
				SharingRequestBean bean = new SharingRequestBean();
				log.debug("networId11 -> " + entity.get("networId"));
				bean.setNetworkId(entity.get("networId").toString());
				bean.setPrices(entity.get("prices").toString());
				bean.setOffers(entity.get("offers").toString());
				bean.setNewsAndLibrary(entity.get("newsAndLibrary").toString());
				bean.setPeople(entity.get("people").toString());
				al1.add(bean);
			}

		}
		if (receivingShareList.size() != 0) {
			for (Map<String, Object> entity : receivingShareList) {
				log.debug("networId22 -> " + entity.get("networId"));
				SharingRequestBean bean1 = new SharingRequestBean();
				bean1.setNetworkId(entity.get("networId").toString());
				bean1.setPrices(entity.get("prices").toString());
				bean1.setOffers(entity.get("offers").toString());
				bean1.setNewsAndLibrary(entity.get("newsAndLibrary").toString());
				bean1.setPeople(entity.get("people").toString());
				al2.add(bean1);
			}
		}
		treeSet.addAll(al1);
		treeSet.addAll(al2);
		return treeSet;
	}

	@Transactional
	public EditProfileShareRes editProfileShare(EditProfileShareReq req) {
		log.info("Inside EditProfileShareRes -> editProfileShare");
		EditProfileShareRes svDataSharingRes = new EditProfileShareRes();
		EDITPROILESHARE shareDtls = req.getEDITPROILESHARE();

		try {
			log.debug("shareDtls.getNetworkId()::" + shareDtls.getNetworkId());
			log.debug("shareDtls.getProfileId()::" + shareDtls.getProfileId());
			if (!StringUtils.isNotEmpty(shareDtls.getNetworkId())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Edit Profile Share DtlsRes", "",
						ERROR_CODE.ES_PK_002);
				svDataSharingRes.setHeader(header);
				return svDataSharingRes;
			}
			if (!StringUtils.isNotEmpty(shareDtls.getProfileId())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Edit Profile Share DtlsRes", "",
						ERROR_CODE.SH_PRFL_ER);
				svDataSharingRes.setHeader(header);
				return svDataSharingRes;
			} else {
				Profile_Sharing_type entity = new Profile_Sharing_type();
				Profile_Sharing_typePK entityPk = new Profile_Sharing_typePK();
				Profile_Sharing_type tmpentity = new Profile_Sharing_type();
				Profile_Sharing_typePK tmpentitypk = new Profile_Sharing_typePK();
				entityPk.setNetworkId(shareDtls.getNetworkId());
				entityPk.setProfileId(shareDtls.getProfileId());
				entity.setId(entityPk);
				tmpentitypk.setNetworkId(shareDtls.getNetworkId());
				if(shareDtls.getOldprofileId() != null)
				{
					tmpentitypk.setProfileId(shareDtls.getOldprofileId());
					tmpentity.setId(tmpentitypk);
				}
				entity.setAddressFlag(shareDtls.getAddress());
				entity.setBirthYearFlag(shareDtls.getBirthYear());
				entity.setGenderFlag(shareDtls.getGender());
				entity.setLanguageFlag(shareDtls.getLanguage());
				entity.setMobileNumberFlag(shareDtls.getMobileNumber());
				entity.setNameFlag(shareDtls.getName());
				entity.setCompanyFlag(shareDtls.getCompany());
				entity.setEmailFlag(shareDtls.getEmail());
				entity.setCurrencyFlag(shareDtls.getCurrency());
				if(shareDtls.getEditFlag().toString().equalsIgnoreCase("E"))
				{
					if (profileRepo.exists(entity, entityPk)) {
						profileRepo.merge(entity);
						ERROR = ERROR_CODE.NS_SHPRF_ED;
					}
					
					else
					{
						profileRepo.save(entity);
						tmpentity = profileRepo.findOne(tmpentity,tmpentitypk);
						profileRepo.delete(tmpentity);
						ERROR = ERROR_CODE.NS_SHPRF_ED;
					}
					
				}
				 else {
					
					profileRepo.save(entity);
					ERROR = ERROR_CODE.NS_SHPRF_CR;
				}

			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Edit Profile", "", ERROR);
		svDataSharingRes.setHeader(header);
		return svDataSharingRes;
	}

	public QueryPeopleProfilesRes queryPeopleProfiles(String networkId) {
		log.info("Inside QueryPeopleProfilesRes -> queryPeopleProfiles");
		log.debug("networkId::" + networkId);
		QueryPeopleProfilesRes res = new QueryPeopleProfilesRes();
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					serviceName, "Query Profile Share DtlsRes", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}
		List<Profile_Sharing_type> entityList = profileRepo
				.querySharingProfile(networkId);
		List<PROFILEDTLS> profileDtlsList = new ArrayList<PROFILEDTLS>();
		if (entityList.size() != 0) {
			log.debug("entityList::" + entityList);
			for (Profile_Sharing_type entity : entityList) {
				PROFILEDTLS profileDtls = new PROFILEDTLS();
				log.debug("ProfileId::" + entity.getId().getProfileId());
				profileDtls.setProfileId(entity.getId().getProfileId());
				profileDtlsList.add(profileDtls);
			}
			res.getPROFILEDTLS().addAll(profileDtlsList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Query Sharing Peofile", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryAllNwksRes queryAllNetworks() {
		log.info("Inside QueryAllNwksRes -> queryAllNetworks");
		QueryAllNwksRes res = new QueryAllNwksRes();
		List<Network> entityList = nwkRepo.listAllNetworks();
		List<ALLNWK> allNwksList = new ArrayList<ALLNWK>();
		if (entityList.size() != 0) {
			log.debug("entityList::" + entityList);
			for (Network entity : entityList) {
				ALLNWK nwkDtls = new ALLNWK();
				log.debug("ProfileId::" + entity.getName());
				nwkDtls.setNetworkId(entity.getNetworkId());
				nwkDtls.setNetworkName(entity.getName());
				allNwksList.add(nwkDtls);
			}
			res.getALLNWK().addAll(allNwksList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Query All Networks", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryProfileDetailsRes queryProfileDetails(String networkId) {
		log.info("Inside QueryProfileDetailsRes -> queryProfileDetails");
		QueryProfileDetailsRes res = new QueryProfileDetailsRes();
		log.debug("networkId::" + networkId);
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					serviceName, "Query Profile DtlsRes", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}
		List<Map<String, Object>> mapList = profileRepo
				.queryProfileDetails(networkId);
		List<QRYPROFILEDTLS> qryProfileList = new ArrayList<QRYPROFILEDTLS>();
		int filedsCount = 0;
		String bioData = "";
		log.debug("qryProfileList::" + qryProfileList);
		if (mapList.size() != 0) {
			for (Map<String, Object> map : mapList) {
				QRYPROFILEDTLS profileDtls = new QRYPROFILEDTLS();
				if (map.get("Profile") != null) {
					profileDtls.setProfileId(map.get("Profile").toString());
				}
				if (map.get("Name") != null) {
					if (map.get("Name").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 2;
						bioData = bioData + "FName,LName" + ",";
					}
				}
				if (map.get("birth_year") != null) {
					if (map.get("birth_year").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 1;
						bioData = bioData + "Age" + ",";
					}
				}
				if (map.get("gender") != null) {
					if (map.get("gender").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 1;
						bioData = bioData + "Gender" + ",";
					}
				}
				if (map.get("mobile") != null) {
					if (map.get("mobile").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 3;
						bioData = bioData + "Mobile Number" + "," + "Fax" + ","
								+ "Fixed TelePhone Number" + ",";
					}
				}
				if (map.get("address") != null) {
					if (map.get("address").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 1;
						bioData = bioData + "Address" + ",";
					}
				}
				if (map.get("company") != null) {
					if (map.get("company").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 2;
						bioData = bioData + "Company" + "," + "Position" + ",";
					}
				}
				if (map.get("language") != null) {
					if (map.get("language").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 1;
						bioData = bioData + "Language" + ",";
					}
				}
				if (map.get("email") != null) {
					if (map.get("email").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 1;
						bioData = bioData + "email" + ",";
					}
				}
				if (map.get("currency") != null) {
					if (map.get("currency").toString().equalsIgnoreCase("Y")) {
						filedsCount = filedsCount + 1;
						bioData = bioData + "Currency";
					}
				}
				if (bioData.charAt(bioData.length() - 1) == ',') {
					bioData = bioData.substring(0, bioData.length() - 1);
				}
				log.debug("filedsCount::" + filedsCount);
				log.debug("bioData::" + bioData);
				profileDtls.setNoOfFields(filedsCount);
				profileDtls.setBioData(bioData);
				qryProfileList.add(profileDtls);
				filedsCount = 0;
				bioData = "";
			}
			log.debug("qryProfileList::" + qryProfileList);
			res.getQRYPROFILEDTLS().addAll(qryProfileList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Query Profile Details", "", ERROR);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public ApproveSharingRequestRes approveSharingRequest(
			ApproveSharingRequest req) {
		ApproveSharingRequestRes res = new ApproveSharingRequestRes();
		APRVSHARE shareDtls = req.getAPRVSHARE();
		String fromNetwork = null;
		String toNetwork = null;
		try {
			List<APPSHAREITEMS> shareItemsList = req.getAPRVSHARE()
					.getAPPSHAREITEMS();
			log.debug("shareItemsList ->" + shareItemsList);
			log.debug("shareDtls.getFromNetwork()::"
					+ shareDtls.getFromNetwork());
			log.debug("shareDtls.getToNetwork()::" + shareDtls.getToNetwork());
			int returnCount = 0;
			if (!StringUtils.isNotEmpty(shareDtls.getFromNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Approve Share DtlsRes", "",
						ERROR_CODE.FRM_NW_ER);
				res.setHeader(header);
				return res;
			}
			if (!StringUtils.isNotEmpty(shareDtls.getToNetwork())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Approve Share DtlsRes", "",
						ERROR_CODE.TO_NW_ER);
				res.setHeader(header);
				return res;
			}
			if (!StringUtils.isNotEmpty(req.getAPRVSHARE().getAPPSHAREITEMS()
					.toString())) {
				header = (Header) HeaderFactory.getHeader(HeaderType.SS,
						serviceName, "Approve Share DtlsRes", "",
						ERROR_CODE.SS_SUBPR_ER2);
				res.setHeader(header);
				return res;
			} else {
				if (shareItemsList.size() != 0) {
					for (APPSHAREITEMS shareItems : shareItemsList) {
						if (shareItems.getSendReceive().equalsIgnoreCase("S")) {
							fromNetwork = shareDtls.getFromNetwork();
							toNetwork = shareDtls.getToNetwork();

						} else {
							toNetwork = shareDtls.getFromNetwork();
							fromNetwork = shareDtls.getToNetwork();

						}
						returnCount = profileRepo.approveSharingProfileDtls(
								fromNetwork, toNetwork, shareItems
										.getShareItem(), shareDtls
										.getApproveFlag(), shareDtls
										.getPeopleShareType(), shareDtls
										.getPeopleMode(), req.getHeader()
										.getUserId());
						log.debug("ShareItem -> " + shareItems.getShareItem());
						if (returnCount > 0) {
							if(shareDtls.getApproveFlag().toString().equals("A"))
							{
							ERROR = ERROR_CODE.NS_SHPRF_AP;
							}
							
							else if(shareDtls.getApproveFlag().toString().equals("R"))
							{
							ERROR = ERROR_CODE.NS_SHPRF_RJ;
							}
							
						}
					}
				}
			}
		} catch (DomainException e) {
			ERROR = ERROR_CODE.ES_SV_002;
			log.error(Utils.getStackTrace(e));
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Approve Sharing Details", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public QueryNwkCountDtlsRes queryNwkCountDtlsRes(String networkId) {
		log.info("Inside QueryNwkCountDtlsRes -> queryNwkCountDtlsRes");
		QueryNwkCountDtlsRes res = new QueryNwkCountDtlsRes();
		log.debug("networkId::" + networkId);
		if (!StringUtils.isNotEmpty(networkId)) {
			header = (Header) HeaderFactory.getHeader(HeaderType.SS,
					serviceName, "Query Network Count DtlsRes", "",
					ERROR_CODE.ES_PK_002);
			res.setHeader(header);
			return res;
		}
		List<QRYNWKCOUNTDTLS> qryNwkList = new ArrayList<QRYNWKCOUNTDTLS>();
		List<QRYNWKCOUNTDTLS> networkList = this
				.queryNetworkProfileDtls(networkId);
		if (networkList.size() != 0) {
			log.debug("qryProfileList::" + qryNwkList);
			res.getQRYNWKCOUNTDTLS().addAll(networkList);
			ERROR = ERROR_CODE.ES_SC_001;
		} else {
			ERROR = ERROR_CODE.ES_NR_001;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS, serviceName,
				"Query Network Count Details", "", ERROR);
		res.setHeader(header);
		return res;
	}

	public List queryNetworkProfileDtls(String networkId) {
		List<Map<String, Object>> mapList = profileRepo
				.queryNwkPeopleCounts(networkId);
		List<QRYNWKCOUNTDTLS> qryNwkList = new ArrayList<QRYNWKCOUNTDTLS>();
		log.debug("mapList::" + mapList);
		if (mapList.size() != 0) {
			for (Map<String, Object> map : mapList) {
				QRYNWKCOUNTDTLS profileDtls = new QRYNWKCOUNTDTLS();
				if (map.get("count") != null) {
					profileDtls.setNoOfPeople(map.get("count").toString());
				}
				if (map.get("network") != null) {
					profileDtls.setNetwork(map.get("network").toString());
				}
				if (map.get("location") != null) {
					profileDtls.setLocation(map.get("location").toString());
				}
				
				NetworkPicture pictureEntity = nwpic.queryNwrPic(networkId);
				if (pictureEntity != null) {
					profileDtls.setProfile(pictureEntity.getContent());
				}
				
				qryNwkList.add(profileDtls);
			}
		}
		return qryNwkList;
	}
}
