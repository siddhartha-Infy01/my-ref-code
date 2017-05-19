package com.iexceed.esoko.engine.utils;

import org.apache.log4j.Logger;


public class LoggerUtils {
	public static Logger log1 = Logger.getLogger("com.iexceed.esoko.app");
	public static Logger log2 = Logger.getLogger("com.iexceed.esoko.sch");
	public static Logger log3 = Logger.getLogger("com.iexceed.esoko.people");

	public static Logger getLogger() {
		return log1;
	}
	
	public static Logger getSchLogger() {
		return log2;
	}
	
	public static Logger getSmartGroupLogger() {
		return log3;
	}
}
