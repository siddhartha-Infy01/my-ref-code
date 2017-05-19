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
import com.iexceed.esoko.domain.entity.LibraryFile;
import com.iexceed.esoko.domain.entity.LibraryImage;
import com.iexceed.esoko.domain.entity.LibraryLocation;
import com.iexceed.esoko.domain.entity2.AgentDetail;
import com.iexceed.esoko.domain.entity2.People;
import com.iexceed.esoko.domain.entity2.UploadMaster;
import com.iexceed.esoko.domain.exception.DomainException;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.jaxb.db.CRLIBRARYDETAILS;
import com.iexceed.esoko.jaxb.db.CreateLibraryReq;
import com.iexceed.esoko.jaxb.db.CreateLibraryRes;
import com.iexceed.esoko.jaxb.db.EDITNEWSLIBRARY;
import com.iexceed.esoko.jaxb.db.EditNewsAndLibraryReq;
import com.iexceed.esoko.jaxb.db.EditNewsAndLibraryRes;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.QRLIBRARYDETAILS;
import com.iexceed.esoko.jaxb.db.QueryLibraryRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class LibraryService {
	@Autowired
	UploadMasterRepo masterRepo;

	@Autowired
	AgentDetailRepo agentRepo;

	@Autowired
	PeopleRepo peopleRepo;

	@Autowired
	EsokoAppRepo esokoAppRepo;

	@Autowired
	LibraryRepo repo;

	@Autowired
	LibraryFileRepo fileRepo;

	@Autowired
	LibraryImageRepo imageRepo;

	@Autowired
	LibraryCommodityRepo commodityRepo;

	@Autowired
	LibraryLocationRepo locationRepo;

	private final String serviceName = "LibraryService";
	public static Logger log = LoggerUtils.getLogger();
	Enum<ERROR_CODE> errorCode = ERROR_CODE.DM_SV_002;
	Header header = null;

	public QueryLibraryRes queryLibrary(String networkId, String userId) {
		log.info("Insid LibraryService -> queryLibrary");
		log.debug("networkId ->" + networkId);
		log.debug("userId ->" + userId);
		QueryLibraryRes libRes = new QueryLibraryRes();
		List<Map<String, Object>> libMapList = repo.querylibrary(networkId,
				userId);
		log.debug("libMapList -> " + libMapList);
		List<QRLIBRARYDETAILS> libDtlsList = new ArrayList<QRLIBRARYDETAILS>();
		log.debug("entity -> " + libMapList);
		try {
			log.debug("libMapList.size()" + libMapList.size());
			if (libMapList.size() > 0) {
				for (Map<String, Object> map : libMapList) {
					QRLIBRARYDETAILS libDtls = new QRLIBRARYDETAILS();
					libDtls.setTitle(map.get("title").toString());
					libDtls.setStory(map.get("story").toString());
					if (null != map.get("published_ts")) {
						libDtls.setDate(Utils.getDDMMMYYFormat(
								map.get("published_ts").toString(), 1));
					}
					if (null != map.get("source_url")) {
						libDtls.setSourceURL(map.get("source_url").toString());
					}
					if (null != map.get("filename")) {
						libDtls.setFileName(map.get("filename").toString());
					}
					if (null != map.get("filetype")) {
						libDtls.setFileType(map.get("filetype").toString());
					}
					if (null != map.get("filecontent")) {
						libDtls.setFileContent(map.get("filecontent")
								.toString().getBytes());
					}
					if (null != map.get("image")) {
						byte[] imgArr = SerializationUtils
								.serialize((Serializable) map.get("image"));
						libDtls.setImage(imgArr);
					}
					libDtlsList.add(libDtls);
				}
				errorCode = ERROR_CODE.ES_SC_001;
				libRes.getQRLIBRARYDETAILS().addAll(libDtlsList);
			} else {
				errorCode = ERROR_CODE.ES_NR_001;
			}
		} catch (DomainException e) {
			errorCode = ERROR_CODE.DM_SV_002;
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"QueryLibrary", "", errorCode);
		libRes.setHeader(header);
		return libRes;
	}

	@Transactional
	public CreateLibraryRes createLibrary(CreateLibraryReq req) {
		log.info("Inside LibraryService -> createLibrary");
		CreateLibraryRes res = new CreateLibraryRes();
		CRLIBRARYDETAILS crlibraraydetails = req.getCRLIBRARYDETAILS();
		log.debug("Network id:" + crlibraraydetails.getNetworkId());
		log.debug("Title:" + crlibraraydetails.getTitle());
		log.debug("Summary:" + crlibraraydetails.getSummary());
		log.debug("Story:" + crlibraraydetails.getStory());
		log.debug("Published dt:" + crlibraraydetails.getDate());
		log.debug("Author:" + crlibraraydetails.getAuthor());
		log.debug("File name:" + crlibraraydetails.getFileName());
		log.debug("File extension:" + crlibraraydetails.getFileType());

		AgentDetail agentDetail = agentRepo.findDetailId(
				crlibraraydetails.getNetworkId(),
				crlibraraydetails.getAgentId());
		if (agentDetail == null) {
			People people = peopleRepo.getNameByNwkId(
					crlibraraydetails.getAgentId(),
					crlibraraydetails.getNetworkId());
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

			HeaderFactory.setExtraParamMap("$1", name);
			errorCode = ERROR_CODE.LIB_ER_001;

		} else {
			if (crlibraraydetails.getNetworkId() != null) {
				String id = "LIB" + esokoAppRepo.getSequenceNumber();

				UploadMaster master = new UploadMaster();
				master.setUploadId(id);
				master.setApplicationId("library");
				master.setAgentId(crlibraraydetails.getAgentId());
				master.setDetailId((agentRepo.findDetailId(
						crlibraraydetails.getNetworkId(),
						crlibraraydetails.getAgentId()).getDetailId()));
				master.setNetworkId(crlibraraydetails.getNetworkId());

				master.setUploadDt(Utils.getCurrentDate());

				master.setAuthStat("U");
				master.setAuthBy(null);
				master.setAuthTs(null);

				Library library = new Library();
				library.setLibId(id);
				library.setNetworkId(crlibraraydetails.getNetworkId());
				library.setTitle(crlibraraydetails.getTitle());
				library.setSummary(crlibraraydetails.getSummary());
				library.setStory(crlibraraydetails.getStory());
				library.setSourceUrl(crlibraraydetails.getSourceUrl());
				library.setPublishedTs(Utils.getCurrentDate());
				library.setType("L");
				library.setCategoryId(crlibraraydetails.getCategory());
				library.setCreatedBy(req.getHeader().getUserId());

				library.setCreatedTs(Utils.getCurrentDate());

				library.setAuthBy(null);
				library.setAuthTs(null);
				library.setAuthStat("U");
				library.setAuthor(crlibraraydetails.getAuthor());

				LibraryFile libraryFile = new LibraryFile();
				libraryFile.setFileContent(Utils.encodeImage(crlibraraydetails
						.getFile()));
				libraryFile.setFilename(crlibraraydetails.getFileName());
				libraryFile.setFiletype(crlibraraydetails.getFileType());
				libraryFile.setLibraryId(id);

				LibraryImage libraryImage = new LibraryImage();
				if (crlibraraydetails.getImage() != null) {
					libraryImage.setContent(Utils.encodeImage(crlibraraydetails
							.getImage()));
				}
				libraryImage.setLibraryId(id);

				List<LibraryLocation> libraryLocations = new ArrayList<LibraryLocation>();
				for (String location : crlibraraydetails.getLocations()) {
					LibraryLocation libraryLocation = new LibraryLocation();
					libraryLocation.setLocationId(location);
					libraryLocation.setLibraryId(id);
					libraryLocations.add(libraryLocation);
				}

				List<LibraryCommodity> libraryCommodities = new ArrayList<LibraryCommodity>();
				for (String commodity : crlibraraydetails.getCommodities()) {
					LibraryCommodity libraryCommodity = new LibraryCommodity();
					libraryCommodity.setCommodityId(commodity);
					libraryCommodity.setLibraryId(id);
					libraryCommodities.add(libraryCommodity);
				}

				// inserting record into uploadmaster table
				masterRepo.save(master);

				// inserting records into library table
				repo.createLibrary(library);

				// inserting record into libraryFile table
				fileRepo.createLibraryFile(libraryFile);

				// inserting record into libraryImage table
				imageRepo.createLibraryImage(libraryImage);

				// inserting records into libraryLocation table
				for (LibraryLocation libraryLocation : libraryLocations) {
					locationRepo.createLibraryLocation(libraryLocation);
				}

				// inserting records into libraryLocation table
				for (LibraryCommodity libraryCommodity : libraryCommodities) {
					commodityRepo.createLibraryCommodity(libraryCommodity);
				}
				errorCode = ERROR_CODE.LIB_SV_001;
				header = (Header) HeaderFactory.getHeader(HeaderType.DB,
						serviceName, "CreateLibrary", req.getHeader()
								.getUserId(), errorCode);
			} else {
				errorCode = ERROR_CODE.ES_PK_002;

			}

		}

		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"CreateLibrary", req.getHeader().getUserId(), errorCode);
		res.setHeader(header);
		return res;
	}

	@Transactional
	public EditNewsAndLibraryRes editLibrary(EditNewsAndLibraryReq req) {
		log.info("Inside LibraryService --> editLibrary");
		log.debug("User Id:" + req.getHeader().getUserId());
		EditNewsAndLibraryRes res = new EditNewsAndLibraryRes();
		EDITNEWSLIBRARY editNwsAndLib = req.getEDITNEWSLIBRARY();
		Library lib = new Library();
		lib.setLibId(editNwsAndLib.getLibId());
		log.debug("Lib id:" + editNwsAndLib.getLibId());
		Library library = repo.findOne(lib, editNwsAndLib.getLibId());
		if (library != null) {
			library.setCategoryId(editNwsAndLib.getCategory());
			library.setTitle(editNwsAndLib.getTitle());
			library.setSummary(editNwsAndLib.getSummary());
			library.setStory(editNwsAndLib.getStory());
			library.setSourceUrl(editNwsAndLib.getSourceUrl());
			library.setAuthor(editNwsAndLib.getAuthor());
			library.setModifiedBy(req.getHeader().getUserId());
			library.setModifiedTs(Utils.getCurrentDate());
			repo.merge(library);
			// deleting a records from library_image
			imageRepo.deleteLibImgByLibId(editNwsAndLib.getLibId());
			LibraryImage image = new LibraryImage();
			image.setLibraryId(editNwsAndLib.getLibId());
			image.setContent(editNwsAndLib.getImage());
			// inserting a records into library_image
			imageRepo.save(image);
			errorCode = ERROR_CODE.LIB_UPDT;
		} else {
			errorCode = ERROR_CODE.ES_UD_002;
		}

		header = (Header) HeaderFactory.getHeader(HeaderType.DB, serviceName,
				"editLibrary", req.getHeader().getUserId(), errorCode);
		res.setHeader(header);
		return res;
	}
}
