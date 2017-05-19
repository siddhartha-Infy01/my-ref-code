package com.iexceed.esoko.acc.test;

import java.util.Date;

import junit.framework.TestCase;

import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iexceed.esoko.acc.AccountingEngine;
import com.iexceed.esoko.objects.Accounting;
import com.iexceed.esoko.objects.Cost;
import com.iexceed.esoko.objects.TRANSACTION_CODE;

/**
 * The class <code>AccountingEngineTest</code> contains tests for the class
 * {@link <code>AccountingEngine</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 2/23/15 10:02 PM
 *
 * @author siddharth
 *
 * @version $Revision$
 */
public class AccountingEngineTest extends TestCase {

	AccountingEngine accEngine;

	/**
	 * Construct new test instance
	 *
	 * @param name
	 *            the test name
	 */
	public AccountingEngineTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		AbstractXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/account-config.xml");
		accEngine = applicationContext.getBean(AccountingEngine.class);
		super.setUp();
		// Add additional set up code here
	}

	/**
	 * Perform post-test clean up
	 *
	 * @throws Exception
	 *
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		// Add additional tear down code here
	}

	/**
	 * Run the boolean doAccounting(Accounting) method test
	 */
	public void testDoAccounting() {

		Accounting accounting = new Accounting();
		accounting.setDebitAcNo("N2015010707758");
		accounting.setDebitCcy("USD");
		accounting.setNetworkId("NWK2015010705743");
		accounting.setTransactionDate(new Date());
		Cost cost = new Cost();
		cost.setBaseCost(10.00);
		accounting.setTrnAmt(cost);
		accounting.setUserId("sagar");
		accounting.setTrnCode(TRANSACTION_CODE.FT_ACC.name());
		boolean result = accEngine.doAccounting(accounting).endsWith("");
		assertTrue(result);
	}
}

/*
 * $CPS$ This comment was generated by CodePro. Do not edit it. patternId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern strategyId =
 * com.instantiations.assist.eclipse.pattern.testCasePattern.junitTestCase
 * additionalTestNames = assertTrue = false callTestMethod = true createMain =
 * false createSetUp = false createTearDown = false createTestFixture = false
 * createTestStubs = false methods = doAccounting(QAccounting;) package =
 * com.iexceed.esoko.acc.test package.sourceFolder =
 * esoko-accounting/src/test/java superclassType = junit.framework.TestCase
 * testCase = AccountingEngineTest testClassType =
 * com.iexceed.esoko.acc.AccountingEngine
 */