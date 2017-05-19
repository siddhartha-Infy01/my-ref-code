package com.iexceed.esoko.domain.dao3;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.UploadRateReport;
import com.iexceed.esoko.engine.utils.LoggerUtils;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UploadRateReportRepo extends
		AbstractRepoForEntity<UploadRateReport, String> {
	private static Logger log = LoggerUtils.getLogger();

	public boolean exists(UploadRateReport updateReport, String reportId) {
		log.info("Inside UploadRateReportRepo -> exists");
		return super.exists(UploadRateReport.class, reportId);
	}

	public UploadRateReport merge(UploadRateReport updateReport) {
		log.info("Inside UploadRateReportRepo -> merge");
		return super.merge(updateReport);
	}

	public UploadRateReport save(UploadRateReport updateReport) {
		log.info("Inside UploadRateReportRepo -> save");
		return super.save(updateReport);
	}

	public UploadRateReport findOne(UploadRateReport updateReport, String reportId) {
		log.info("Inside UploadRateReportRepo -> findOne");
		return super.findOne(UploadRateReport.class, reportId);
	}

	public void delete(UploadRateReport updateReport) {
		log.info("Inside UploadRateReportRepo -> delete");
		super.delete(updateReport);
	}
	
	public void delete(Iterable updateReports) {
		log.info("Inside UploadRateReportRepo -> deleteAll");
		super.delete(updateReports);
	}
}
