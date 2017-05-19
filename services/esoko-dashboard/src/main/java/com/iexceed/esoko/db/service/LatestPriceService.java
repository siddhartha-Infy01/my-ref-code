package com.iexceed.esoko.db.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.db.COMMDETAILS;
import com.iexceed.esoko.jaxb.db.Header;
import com.iexceed.esoko.jaxb.db.PRICEDETAILS;
import com.iexceed.esoko.jaxb.db.QueryLatestPriceRes;
import com.iexceed.esoko.service.factory.HeaderFactory;
import com.iexceed.esoko.service.factory.HeaderType;

@Service
public class LatestPriceService {

	public static Logger log = LoggerUtils.getLogger();

	public QueryLatestPriceRes queryLatestPrice(String networkId, String userId) {
		log.info("Inside QueryLatestPriceRes -> queryLatestPrice");
		log.debug("NetworkId: " + networkId);
		log.debug("UserId: " + userId);
		QueryLatestPriceRes priceRes = new QueryLatestPriceRes();
		Header header = null;
		Enum<ERROR_CODE> errorCode = null;
		List<COMMDETAILS> comDtList = new ArrayList<COMMDETAILS>();
		
		COMMDETAILS commDtls1 = new COMMDETAILS();
		commDtls1.setCommodityId("Banana");
		commDtls1.setCommodityName("Banana(Cooking, Matooke)");
		commDtls1.setCurrencyCode("USD");
		commDtls1.setCurrencySymbol("$");
		
		List<PRICEDETAILS> prDtlLst1 = new ArrayList<PRICEDETAILS>();
		
		PRICEDETAILS prDtls11 = new PRICEDETAILS();
		prDtls11.setMarketName("Eldoret Market");
		prDtls11.setPrice(0.42f);
		prDtls11.setUnits("kg");
		prDtls11.setUploadedBy("jeremiah thuo");
		prDtls11.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls12 = new PRICEDETAILS();
		prDtls12.setMarketName("Kibuye");
		prDtls12.setPrice(0.21f);
		prDtls12.setUnits("kg");
		prDtls12.setUploadedBy("elvis minyikha");
		prDtls12.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls13 = new PRICEDETAILS();
		prDtls13.setMarketName("Bawku Market");
		prDtls13.setPrice(0.36f);
		prDtls13.setUnits("kg");
		prDtls13.setUploadedBy("haruna sanusi");
		prDtls13.setUploadedOn("24 Sep 2014");

		PRICEDETAILS prDtls14 = new PRICEDETAILS();
		prDtls14.setMarketName("Kumasi Central Market");
		prDtls14.setPrice(0.48f);
		prDtls14.setUnits("kg");
		prDtls14.setUploadedBy("sampson aiyedun");
		prDtls14.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls15 = new PRICEDETAILS();
		prDtls15.setMarketName("Tamale Market");
		prDtls15.setPrice(0.59f);
		prDtls15.setUnits("kg");
		prDtls15.setUploadedBy("fauster nyuzaghl");
		prDtls15.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls16 = new PRICEDETAILS();
		prDtls16.setMarketName("Techiman Market");
		prDtls16.setPrice(0.41f);
		prDtls16.setUnits("kg");
		prDtls16.setUploadedBy("emma mensah");
		prDtls16.setUploadedOn("24 Sep 2014");
		
		prDtlLst1.add(prDtls11);
		prDtlLst1.add(prDtls12);
		prDtlLst1.add(prDtls13);
		prDtlLst1.add(prDtls14);
		prDtlLst1.add(prDtls15);
		prDtlLst1.add(prDtls16);
		
		commDtls1.getPRICEDETAILS().addAll(prDtlLst1);
		
		COMMDETAILS commDtls2 = new COMMDETAILS();
		commDtls2.setCommodityId("Mango");
		commDtls2.setCommodityName("Mango (Local)");
		commDtls2.setCurrencyCode("USD");
		commDtls2.setCurrencySymbol("$");
		
		List<PRICEDETAILS> prDtlLst2 = new ArrayList<PRICEDETAILS>();
		
		PRICEDETAILS prDtls21 = new PRICEDETAILS();
		prDtls21.setMarketName("Eldoret Market");
		prDtls21.setPrice(0.70f);
		prDtls21.setUnits("kg");
		prDtls21.setUploadedBy("jeremiah thuo");
		prDtls21.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls22 = new PRICEDETAILS();
		prDtls22.setMarketName("Kibuye");
		prDtls22.setPrice(0.86f);
		prDtls22.setUnits("kg");
		prDtls22.setUploadedBy("elvis minyikha");
		prDtls22.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls23 = new PRICEDETAILS();
		prDtls23.setMarketName("Bawku Market");
		prDtls23.setPrice(0.76f);
		prDtls23.setUnits("kg");
		prDtls23.setUploadedBy("haruna sanusi");
		prDtls23.setUploadedOn("24 Sep 2014");

		PRICEDETAILS prDtls24 = new PRICEDETAILS();
		prDtls24.setMarketName("Kumasi Central Market");
		prDtls24.setPrice(0.88f);
		prDtls24.setUnits("kg");
		prDtls24.setUploadedBy("sampson aiyedun");
		prDtls24.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls25 = new PRICEDETAILS();
		prDtls25.setMarketName("Tamale Market");
		prDtls25.setPrice(0.90f);
		prDtls25.setUnits("kg");
		prDtls25.setUploadedBy("fauster nyuzaghl");
		prDtls25.setUploadedOn("24 Sep 2014");
		
		PRICEDETAILS prDtls26 = new PRICEDETAILS();
		prDtls26.setMarketName("Techiman Market");
		prDtls26.setPrice(0.79f);
		prDtls26.setUnits("kg");
		prDtls26.setUploadedBy("emma mensah");
		prDtls26.setUploadedOn("24 Sep 2014");
		
		prDtlLst2.add(prDtls21);
		prDtlLst2.add(prDtls22);
		prDtlLst2.add(prDtls23);
		prDtlLst2.add(prDtls24);
		prDtlLst2.add(prDtls25);
		prDtlLst2.add(prDtls26);
		
		commDtls2.getPRICEDETAILS().addAll(prDtlLst2);			
		
		comDtList.add(commDtls1);
		comDtList.add(commDtls2);		
		
		errorCode = ERROR_CODE.ES_SC_001;
		header = (Header) HeaderFactory.getHeader(HeaderType.DB, "LatestPriceService",
				"queryLatestPrice", "", errorCode);
		priceRes.getCOMMDETAILS().addAll(comDtList);
		priceRes.setHeader(header);
		return priceRes;
	}
}
