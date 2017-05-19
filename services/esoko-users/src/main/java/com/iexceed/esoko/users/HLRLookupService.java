package com.iexceed.esoko.users;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao3.OperatorRepo;
import com.iexceed.esoko.domain.dao3.OperatorTemplateRepo;
import com.iexceed.esoko.domain.entity3.OperatorTemplate;
import com.iexceed.esoko.domain.entity3.OperatorTemplatePK;
import com.iexceed.esoko.engine.http.HttpClient;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Service
public class HLRLookupService {

	@Autowired
	private OperatorRepo operatorRepo;
	@Autowired
	private OperatorTemplateRepo operatorTempl;
	private static Logger log = LoggerUtils.getLogger();

	public String fetchOperator(String destination) {
		return (fetchOperatorTemplate(destination) != null ? fetchOperatorTemplate(
				destination).getId().getOperatorId()
				: "");
	}

	public OperatorTemplate fetchOperatorTemplate(String destination) {
		boolean flag = false;
		for (OperatorTemplate operatorTemplate : operatorTempl
				.getAllTemplates()) {
			if (matchPhonePattern(destination, operatorTemplate.getTemplate(),
					operatorTemplate.getTemplateLength())) {
				return operatorTemplate;
			}
		}
		if (!flag) {
			return populateTemplate(destination);
		}
		return null;
	}

	private boolean matchPhonePattern(String phoneno, String pattern, int length) {
		String prefix = pattern.substring(0, pattern.indexOf("x"));
		String regex = prefix + "[0-9]" + "{" + (length - prefix.length())
				+ "}";
		return phoneno.matches(regex);
	}

	@Transactional
	public OperatorTemplate populateTemplate(String destination) {
		OperatorTemplate operatorTemplate = new OperatorTemplate();
		OperatorTemplatePK operatorTemplatePK = new OperatorTemplatePK();
		JSONObject HLRObject = HttpClient.callHLRService(destination);
		log.debug("HLR Response: " + HLRObject.toString());
		try {
			if (HLRObject.getString("err").equals("0")
					|| HLRObject.getString("err").equals("6")
					|| HLRObject.getString("err").equals("27")) {
				operatorTemplate.setTemplatePrefix(HLRObject.getString("ocp")
						+ HLRObject.getString("onp"));
				operatorTemplate.setTemplateLength(destination.length());
				operatorTemplate.setTemplate(StringUtils.rightPad(
						operatorTemplate.getTemplatePrefix(),
						operatorTemplate.getTemplateLength(), 'x'));
				operatorTemplatePK.setOperatorId(HLRObject.getString("mccmnc"));
				operatorTemplate.setId(operatorTemplatePK);
			} else {
				return null;
			}

		} catch (JSONException e) {
			log.error(Utils.getStackTrace(e));
		}
		return operatorTempl.save(operatorTemplate);
	}
}
