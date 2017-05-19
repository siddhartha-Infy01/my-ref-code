/**
 * 
 */
package com.iexceed.esoko.core.test;

import java.math.BigInteger;

import junit.framework.TestCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iexceed.esoko.domain.dao.CurrencyRepo;
//import com.iexceed.esoko.jaxb.se.crtcommdty.COMMDTLS;
//import com.iexceed.esoko.jaxb.se.crtcommdty.CreateCommoditiesReq;
//import com.iexceed.esoko.jaxb.se.crtcommdty.CreateCommoditiesRes;
import com.iexceed.esoko.se.service.CommoditiesService;

/**
 * @author siddharth
 * 
 */
public class TestCommodityRepo extends TestCase {

	/**
	 * @param name
	 */
	@Autowired 
	CommoditiesService commoditiesService;

	public TestCommodityRepo(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link com.iexceed.esoko.domain.dao.CommoditiesService#createCommodities(java.lang.String)}
	 * .
	 */
	public void testCreateCommodities() {
		AbstractXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/core-config-test.xml");
//		commoditiesService = applicationContext.getBean(CommoditiesService.class);
//		CreateCommoditiesReq req = new CreateCommoditiesReq();
//		CreateCommoditiesRes res = new CreateCommoditiesRes();
//		COMMDTLS cmdtls = (COMMDTLS)req.getHeaderAndCOMMDTLS().get(0);
//		cmdtls.setCommodityID(new BigInteger("1"));
//		cmdtls.setCommodityAlias("Rice");
//		cmdtls.setName("Rice");
//		cmdtls.setDescription("Description");
//		cmdtls.setType("type");
//		cmdtls.setRank(new BigInteger("1"));
//		cmdtls.setParentId(new BigInteger("1"));
		
//		commoditiesService.createCommodities(req);
		System.out.println("hi there");
//		commoditiesService.findByCcyCode("USD");
//		System.out.println("Count of records"+commoditiesService.count());
	}

	/**
	 * Test method for
	 * {@link com.iexceed.esoko.domain.dao.CurrencyRepo#save(com.iexceed.esoko.domain.entity.Currency)}
	 * .
	 */

}
