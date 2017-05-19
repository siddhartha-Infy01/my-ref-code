package com.iexceed.esoko.db.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.EsokoAppRepo;
import com.iexceed.esoko.domain.dao.LibraryCommodityRepo;
import com.iexceed.esoko.domain.dao.LibraryFileRepo;
import com.iexceed.esoko.domain.dao.LibraryImageRepo;
import com.iexceed.esoko.domain.dao.LibraryLocationRepo;
import com.iexceed.esoko.domain.dao.LibraryRepo;
import com.iexceed.esoko.domain.dao2.AgentDetailRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.dao2.UploadMasterRepo;
import com.iexceed.esoko.domain.entity.Library;
import com.iexceed.esoko.domain.entity.LibraryCommodity;
import com.iexceed.esoko.domain.entity.LibraryImage;
import com.iexceed.esoko.domain.entity.LibraryLocation;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.CRNEWSDETAILS;
import com.iexceed.esoko.jaxb.db.CreateNewsReq;
import com.iexceed.esoko.jaxb.db.CreateNewsRes;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.QRNEWSDETAILS;
import com.iexceed.esoko.jaxb.db.QueryNewsRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class NewsService {

	@Autowired
	LibraryRepo newsRepo;

	@Autowired
	UploadMasterRepo masterRepo;

	@Autowired
	AgentDetailRepo agentRepo;

	@Autowired
	PeopleRepo peopleRepo;

	@Autowired
	EsokoAppRepo esokoAppRepo;

	@Autowired
	LibraryFileRepo fileRepo;

	@Autowired
	LibraryImageRepo imageRepo;

	@Autowired
	LibraryCommodityRepo commodityRepo;

	@Autowired
	LibraryLocationRepo locationRepo;

	private final String serviceName = "NewsService";
	public static Logger log = LoggerUtils.getLogger();
	Enum<ERROR_CODE> errorCode = ERROR_CODE.DM_SV_002;
	Header header = null;

	public QueryNewsRes queryNews(String networkId, String userId) {
		log.info("Inside QueryNewsRes -> queryNews");
		log.debug("NetworkId: " + networkId);
		log.debug("UserId: " + userId);
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		QueryNewsRes qNews = new QueryNewsRes();
		List<Map<String, Object>> newsList = newsRepo.querynews(networkId,
				userId);

		if (newsList.size() != 0) {
			log.info("Record found");
			List<QRNEWSDETAILS> newsDtlsLst = new ArrayList<QRNEWSDETAILS>();
			for (Map<String, Object> map : newsList) {
				QRNEWSDETAILS newsDtls = new QRNEWSDETAILS();
				Object srcUrl = map.get("source_url");
				if (srcUrl != null) {
					newsDtls.setSourceURL(srcUrl.toString());
				}
				Object story = map.get("story");
				if (story != null) {
					newsDtls.setStory(story.toString());
				}
				Object date = map.get("published_ts");
				if (date != null) {
					newsDtls.setDate(Utils.getDDMMMYYFormat(date.toString(), 1));
				}
				Object title = map.get("title");
				if (title != null) {
					newsDtls.setTitle(title.toString());
				}
				Object img = map.get("image");
				if (img != null) {
					byte[] imgArr = SerializationUtils
							.serialize((Serializable) map.get("image"));
					newsDtls.setImage(imgArr);
				}
				newsDtlsLst.add(newsDtls);
			}
			qNews.getQRNEWSDETAILS().addAll(newsDtlsLst);
			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			log.info("No record found");
			errorCode = ERROR_CODE.ES_NR_001;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.DB, "NewsService",
				"queryNews", "", errorCode);
		qNews.setHeader(header);

		return qNews;
	}

	@Transactional
	public CreateNewsRes createNews(CreateNewsReq req) {
		log.info("Inside QueryNewsRes -> createNews");
		CreateNewsRes res = new CreateNewsRes();
		CRNEWSDETAILS crnewsdetails = req.getCRNEWSDETAILS();
		log.debug("Network id:" + crnewsdetails.getNetworkId());
		log.debug("Agent id:" + crnewsdetails.getAgentId());
		log.debug("Title:" + crnewsdetails.getTitle());
		log.debug("Summary:" + crnewsdetails.getSummary());
		log.debug("Story:" + crnewsdetails.getStory());
		log.debug("Published dt:" + crnewsdetails.getDate());
		log.debug("Author:" + crnewsdetails.getAuthor());
		/*
		 * String detailId = String.valueOf(agentRepo.findDetailId(
		 * crnewsdetails.getNetworkId(), req.getHeader().getUserId())
		 * .getDetailId());
		 */
		AgentDetail agentDetail = agentRepo.findDetailId(
				crnewsdetails.getNetworkId(), crnewsdetails.getAgentId());
		if (agentDetail == null) {
			People people = peopleRepo.getNameByNwkId(
					crnewsdetails.getAgentId(), crnewsdetails.getNetworkId());

			String name = null;
			if (people != null) {
				if (people.getLastName() != null) {
					name = people.getFirstName() + " " + people.getLastName();
				}

				else {
					name = people.getFirstName();
				}
			}

			else {
				name = "User";
			}
			errorCode = ERROR_CODE.NEWS_ER_001;
			HeaderFactory.setExtraParamMap("$1", name);

		} else {
			if (crnewsdetails.getNetworkId() != null) {
				String id = "NWS" + esokoAppRepo.getSequenceNumber();

				UploadMaster master = new UploadMaster();
				master.setUploadId(id);
				master.setApplicationId("news");
				master.setAgentId(crnewsdetails.getAgentId());
				master.setDetailId((agentRepo.findDetailId(
						crnewsdetails.getNetworkId(),
						crnewsdetails.getAgentId())).getDetailId());
				master.setNetworkId(crnewsdetails.getNetworkId());
				master.setUploadDt(Utils.getCurrentDate());
				master.setAuthStat("U");
				master.setAuthBy(null);
				master.setAuthTs(null);

				Library library = new Library();
				library.setLibId(id);
				library.setNetworkId(crnewsdetails.getNetworkId());
				library.setTitle(crnewsdetails.getTitle());
				library.setSummary(crnewsdetails.getSummary());
				library.setStory(crnewsdetails.getStory());
				library.setSourceUrl(crnewsdetails.getSourceUrl());
				library.setPublishedTs(Utils.getCurrentDate());

				library.setType("N");
				library.setCategoryId(crnewsdetails.getCategory());
				library.setCreatedBy(req.getHeader().getUserId());
				library.setCreatedTs(Utils.getCurrentDate());
				library.setAuthBy(null);
				library.setAuthTs(null);
				library.setAuthStat("U");
				library.setAuthor(crnewsdetails.getAuthor());

				LibraryImage libraryImage = new LibraryImage();
				if (crnewsdetails.getImage() != null) {
					libraryImage.setContent(Utils.encodeImage(crnewsdetails
							.getImage()));
				}
				libraryImage.setLibraryId(id);

				List<LibraryLocation> libraryLocations = new ArrayList<LibraryLocation>();
				for (String location : crnewsdetails.getLocations()) {
					LibraryLocation libraryLocation = new LibraryLocation();
					libraryLocation.setLocationId(location);
					libraryLocation.setLibraryId(id);
					libraryLocations.add(libraryLocation);
				}

				List<LibraryCommodity> libraryCommodities = new ArrayList<LibraryCommodity>();
				for (String commodity : crnewsdetails.getCommodities()) {
					LibraryCommodity libraryCommodity = new LibraryCommodity();
					libraryCommodity.setCommodityId(commodity);
					libraryCommodity.setLibraryId(id);
					libraryCommodities.add(libraryCommodity);
				}

				// inserting record into uploadmaster table
				masterRepo.save(master);

				// inserting record into library table
				newsRepo.createLibrary(library);

				// inserting record into libraryImage table
				imageRepo.createLibraryImage(libraryImage);

				// inserting records into libraryLocation table
				for (LibraryLocation libraryLocation : libraryLocations) {
					locationRepo.createLibraryLocation(libraryLocation);
				}

				// inserting records into libraryCommodity table
				for (LibraryCommodity libraryCommodity : libraryCommodities) {
					commodityRepo.createLibraryCommodity(libraryCommodity);
				}
				errorCode = ERROR_CODE.NW_SV_001;

			} else {
				errorCode = ERROR_CODE.ES_PK_002;

			}

		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"CreateNews", req.getHeader().getUserId(), errorCode);
		res.setHeader(header);

		return res;
	}
}
