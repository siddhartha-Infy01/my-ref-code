package com.iexceed.esoko.smscb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.iexceed.esoko.domain.dao.Message_delivary_DetailRepo;
import com.iexceed.esoko.domain.entity.Message_delivary_Detail;
import com.iexceed.esoko.engine.utils.Utils;
import com.iexceed.esoko.sch.service.SmsCallBackSrvc;

@WebServlet({ "/SMSApiCallback" })
@Component
public class SMSApiCallback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SmsCallBackSrvc callBacksrvc;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		callBacksrvc = Utils.springContext
				.getBean(SmsCallBackSrvc.class);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String> callBackResp=new HashMap<String,String>();

		System.out.println("type -> " + request.getParameter("type"));
		System.out.println("q -> " + request.getParameter("q"));
		System.out.println("smsc-id -> " + request.getParameter("smsc-id"));
		System.out.println("status -> " + request.getParameter("stat"));
		System.out.println("answer -> " + request.getParameter("answer"));
		System.out.println("from -> " + request.getParameter("from"));
		System.out.println("to -> " + request.getParameter("to"));
		System.out.println("ts -> " + request.getParameter("ts"));
		System.out.println("myId -> " + request.getParameter("myId"));
		
		callBackResp.put("myId", request.getParameter("myId"));
		callBackResp.put("answer", request.getParameter("answer"));
		callBackResp.put("status", request.getParameter("stat"));
		callBacksrvc.populateMessageReponse(callBackResp);

	}

}