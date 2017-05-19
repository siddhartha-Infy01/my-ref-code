package com.iexceed.esoko.ne.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.domain.dao.CommodityRepo;
import com.iexceed.esoko.domain.dao.NetworkCommodityRepo;
import com.iexceed.esoko.domain.entity.Commodity;
import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.ns.GROUP;
import com.iexceed.esoko.jaxb.ns.Header;
import com.iexceed.esoko.jaxb.ns.QueryNetworkCommoditiesRes;
import com.iexceed.esoko.jaxb.ns.VARIETIES;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class NetworkCommoditiesService {
	public static Logger log = LoggerUtils.getLogger();

	@Autowired
	NetworkCommodityRepo nwkCommodRepo;

	@Autowired
	CommodityRepo commdRepo;

	public QueryNetworkCommoditiesRes queryNetworkCommodities(String networkId) {
		log.info("inside queryNetworkCommoditiesRes");
		log.debug("Network Id" + networkId);
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		QueryNetworkCommoditiesRes queryNetworkCommoditiesRes = new QueryNetworkCommoditiesRes();
		List<Commodity> parentCommoditiesList = nwkCommodRepo
				.queryGroupsByNwkId(networkId);
		if (parentCommoditiesList != null) {

			for (Commodity parentComodity : parentCommoditiesList) {
				List<Commodity> commdVarietiesList = commdRepo
						.findAllVarietiesFromRoot(parentComodity
								.getCommodityId());
				GROUP group = new GROUP();
				if (commdVarietiesList != null) {

					for (Commodity commodityVariety : commdVarietiesList) {
						VARIETIES verieties = new VARIETIES();
						verieties.setVarietyName(commodityVariety
								.getCommodityId());
						if (commodityVariety != null) {
							verieties.getVarietyInfo().add(
									commodityVariety.getCommodityId());
							group.getVARIETIES().add(verieties);
						}

					}
					group.setGroupName(parentComodity.getCommodityId());
				}
				if (group != null) {
					queryNetworkCommoditiesRes.getGROUP().add(group);
				}
			}

			errorCode = ERROR_CODE.ES_SC_001;
		} else {
			errorCode = ERROR_CODE.ES_NR_001;
			log.info("No record found");
		}
		header = (Header) HeaderFactory.getHeader(HeaderType.NS,
				"NetworkCommoditiesService", "queryNetworkCommodities", "",
				errorCode);
		queryNetworkCommoditiesRes.setHeader(header);

		return queryNetworkCommoditiesRes;
	}
}
