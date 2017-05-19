package com.iexceed.esoko.service.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iexceed.esoko.engine.utils.ERROR_CODE;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.jaxb.common.ParentHeader;

/*
 * @author Ankur
 */
public class HeaderFactory {

	public static Logger log = LoggerUtils.getLogger();

	public static Map<String, String> extraParamMap = new HashMap<String, String>();

	public static ParentHeader getHeader(HeaderType type, String serviceName,
			String operationName, String userId, Enum<ERROR_CODE> enumCode) {

		ERROR_CODE errorCode = (ERROR_CODE) enumCode;
		ParentHeader header = null;
		log.info("Inside HeaderFactory -> getHeader");
		log.debug("HeaderType: " + type);
		log.debug("ServiceName: " + serviceName);
		log.debug("OperationName: " + operationName);
		log.debug("UserId: " + userId);
		log.debug("ErrorCode: " + errorCode.getCode());
		log.debug("ErrorDescription: " + errorCode.getDescription());
		switch (type) {
		case CRTUSR:
			header = new com.iexceed.esoko.jaxb.login.crtusr.Header();
			com.iexceed.esoko.jaxb.login.crtusr.Header lTmp1 = (com.iexceed.esoko.jaxb.login.crtusr.Header) header;
			lTmp1.setServiceName(serviceName);
			lTmp1.setOperationName(operationName);
			lTmp1.setUserId(userId);
			lTmp1.setErrorCode(errorCode.getCode());
			lTmp1.setErrorDescription(errorCode.getDescription());
			header = lTmp1;
			break;
		case RESETDTLS:
			header = new com.iexceed.esoko.jaxb.login.resetdtls.Header();
			com.iexceed.esoko.jaxb.login.resetdtls.Header lTmp4 = (com.iexceed.esoko.jaxb.login.resetdtls.Header) header;
			lTmp4.setServiceName(serviceName);
			lTmp4.setOperationName(operationName);
			lTmp4.setUserId(userId);
			lTmp4.setErrorCode(errorCode.getCode());
			lTmp4.setErrorDescription(errorCode.getDescription());
			header = lTmp4;
			break;
		case USRQUERY:
			header = new com.iexceed.esoko.jaxb.login.usrquery.Header();
			com.iexceed.esoko.jaxb.login.usrquery.Header lTmp6 = (com.iexceed.esoko.jaxb.login.usrquery.Header) header;
			lTmp6.setServiceName(serviceName);
			lTmp6.setOperationName(operationName);
			lTmp6.setUserId(userId);
			lTmp6.setErrorCode(errorCode.getCode());
			lTmp6.setErrorDescription(errorCode.getDescription());
			header = lTmp6;
			break;
		case MS:
			header = new com.iexceed.esoko.jaxb.ms.Header();
			com.iexceed.esoko.jaxb.ms.Header lTmp7 = (com.iexceed.esoko.jaxb.ms.Header) header;
			lTmp7.setServiceName(serviceName);
			lTmp7.setOperationName(operationName);
			lTmp7.setUserId(userId);
			lTmp7.setErrorCode(errorCode.getCode());
			lTmp7.setErrorDescription(errorCode.getDescription());
			header = lTmp7;
			break;
		case NS:
			header = new com.iexceed.esoko.jaxb.ns.Header();
			com.iexceed.esoko.jaxb.ns.Header lTmp8 = (com.iexceed.esoko.jaxb.ns.Header) header;
			lTmp8.setServiceName(serviceName);
			lTmp8.setOperationName(operationName);
			lTmp8.setUserId(userId);
			lTmp8.setErrorCode(errorCode.getCode());
			lTmp8.setErrorDescription(errorCode.getDescription());
			header = lTmp8;
			break;
		case SE:
			header = new com.iexceed.esoko.jaxb.se.Header();
			com.iexceed.esoko.jaxb.se.Header lTmp9 = (com.iexceed.esoko.jaxb.se.Header) header;
			lTmp9.setServiceName(serviceName);
			lTmp9.setOperationName(operationName);
			lTmp9.setUserId(userId);
			lTmp9.setErrorCode(errorCode.getCode());
			lTmp9.setErrorDescription(errorCode.getDescription());
			header = lTmp9;
			break;

		case DB:
			header = new com.iexceed.esoko.jaxb.db.Header();
			com.iexceed.esoko.jaxb.db.Header lTmp10 = (com.iexceed.esoko.jaxb.db.Header) header;
			lTmp10.setServiceName(serviceName);
			lTmp10.setOperationName(operationName);
			lTmp10.setUserId(userId);
			lTmp10.setErrorCode(errorCode.getCode());
			lTmp10.setErrorDescription(errorCode.getDescription());
			header = lTmp10;
			break;

		case PEOPLE:
			header = new com.iexceed.esoko.jaxb.people.Header();
			com.iexceed.esoko.jaxb.people.Header lTmp11 = (com.iexceed.esoko.jaxb.people.Header) header;
			lTmp11.setServiceName(serviceName);
			lTmp11.setOperationName(operationName);
			lTmp11.setUserId(userId);
			lTmp11.setErrorCode(errorCode.getCode());
			lTmp11.setErrorDescription(errorCode.getDescription());
			header = lTmp11;

			break;
		case SS:
			header = new com.iexceed.esoko.jaxb.ss.Header();
			com.iexceed.esoko.jaxb.ss.Header lTmp13 = (com.iexceed.esoko.jaxb.ss.Header) header;
			lTmp13.setServiceName(serviceName);
			lTmp13.setOperationName(operationName);
			lTmp13.setUserId(userId);
			lTmp13.setErrorCode(errorCode.getCode());
			lTmp13.setErrorDescription(errorCode.getDescription());
			header = lTmp13;
			break;

		case AGENTS:
			header = new com.iexceed.esoko.jaxb.agents.Header();
			com.iexceed.esoko.jaxb.agents.Header lTmp12 = (com.iexceed.esoko.jaxb.agents.Header) header;
			lTmp12.setServiceName(serviceName);
			lTmp12.setOperationName(operationName);
			lTmp12.setUserId(userId);
			lTmp12.setErrorCode(errorCode.getCode());
			lTmp12.setErrorDescription(errorCode.getDescription());
			header = lTmp12;
			break;

		case PUSHALERT:
			header = new com.iexceed.esoko.jaxb.pushalert.Header();
			com.iexceed.esoko.jaxb.pushalert.Header lTmp14 = (com.iexceed.esoko.jaxb.pushalert.Header) header;
			lTmp14.setServiceName(serviceName);
			lTmp14.setOperationName(operationName);
			lTmp14.setUserId(userId);
			lTmp14.setErrorCode(errorCode.getCode());
			lTmp14.setErrorDescription(errorCode.getDescription());
			header = lTmp14;
			break;
			
		case APPROVAL:
			header = new com.iexceed.esoko.jaxb.approval.Header();
			com.iexceed.esoko.jaxb.approval.Header lTmp15 = (com.iexceed.esoko.jaxb.approval.Header) header;
			lTmp15.setServiceName(serviceName);
			lTmp15.setOperationName(operationName);
			lTmp15.setUserId(userId);
			lTmp15.setErrorCode(errorCode.getCode());
			lTmp15.setErrorDescription(errorCode.getDescription());
			header = lTmp15;
			break;
		
		case INBOX:
			header = new com.iexceed.esoko.jaxb.inbox.Header();
			com.iexceed.esoko.jaxb.inbox.Header lTmp16 = (com.iexceed.esoko.jaxb.inbox.Header) header;
			lTmp16.setServiceName(serviceName);
			lTmp16.setOperationName(operationName);
			lTmp16.setUserId(userId);
			lTmp16.setErrorCode(errorCode.getCode());
			lTmp16.setErrorDescription(errorCode.getDescription());
			header = lTmp16;
			break;
			
		case SMSPOLL:
			header = new com.iexceed.esoko.jaxb.smspoll.Header();
			com.iexceed.esoko.jaxb.smspoll.Header lTmp17 = (com.iexceed.esoko.jaxb.smspoll.Header) header;
			lTmp17.setServiceName(serviceName);
			lTmp17.setOperationName(operationName);
			lTmp17.setUserId(userId);
			lTmp17.setErrorCode(errorCode.getCode());
			lTmp17.setErrorDescription(errorCode.getDescription());
			header = lTmp17;
			break;
			
		default:
			break;
		}

		header.setExtraParams(HeaderFactory.getExtraParams());
		header.setType(errorCode.getType());
		return header;
	}

	public static String getExtraParams() {
		StringBuffer result = new StringBuffer();
		if (extraParamMap.size() != 0) {
			if (extraParamMap.size() == 1) {
				result.append(extraParamMap.get("$1"));
			} else {
				result.append(extraParamMap.get("$1"));
				for (int i = 1; i < extraParamMap.size() - 1; i++) {
					result.append("," + extraParamMap.get("$" + (i + 1)));
				}
				result.append(","
						+ extraParamMap.get("$" + (extraParamMap.size())));
			}
		} else {
			result.append("");
		}
		extraParamMap.clear();
		return result.toString();
	}

	public static void setExtraParamMap(String key, String value) {
		HeaderFactory.extraParamMap.put(key, value);
	}
}
