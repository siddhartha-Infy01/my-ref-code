package com.iexceed.esoko.domain.dao4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity4.SmsController;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings("unchecked")
public class SmsControllerRepo extends AbstractRepoForEntity<SmsController, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(SmsController entity, String String) {
		log.info("Inside SmsControllerRepo -> exists");
		return super.exists(SmsController.class, String);
	}

	public SmsController merge(SmsController entity) {
		log.info("Inside SmsControllerRepo -> merge");
		return super.merge(entity);
	}

	public SmsController save(SmsController entity) {
		log.info("Inside SmsControllerRepo -> save");
		return super.save(entity);
	}

	public SmsController findOne(SmsController entity, String id) {
		log.info("Inside SmsControllerRepo -> findOne");
		return super.findOne(SmsController.class, id);
	}

	public void delete(SmsController entity) {
		log.info("Inside SmsControllerRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside SmsControllerRepo -> deleteAll");
		super.delete(entities);
	}


}

