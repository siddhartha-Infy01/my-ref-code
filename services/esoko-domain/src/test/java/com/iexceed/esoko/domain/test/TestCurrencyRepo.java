/**
 * 
 */
package com.iexceed.esoko.domain.test;

import junit.framework.TestCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iexceed.esoko.domain.dao.CurrencyRepo;

/**
 * @author siddharth
 * 
 */
public class TestCurrencyRepo extends TestCase {

	/**
	 * @param name
	 */
	@Autowired 
	CurrencyRepo ccyRepo;

	public TestCurrencyRepo(String name) {
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
//	protected void tearDown() throws Exception {
//		super.tearDown();
//	}

	/**
	 * Test method for
	 * {@link com.iexceed.esoko.domain.dao.CurrencyRepo#findByCcyCode(java.lang.String)}
	 * .
	 */
	public void testFindByCcyCode() {
		AbstractXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/domain-config-test.xml");
		ccyRepo = applicationContext.getBean(CurrencyRepo.class);
		ccyRepo.findByCcyCode("USD");
	}

	/**
	 * Test method for
	 * {@link com.iexceed.esoko.domain.dao.CurrencyRepo#save(com.iexceed.esoko.domain.entity.Currency)}
	 * .
	 */

}
