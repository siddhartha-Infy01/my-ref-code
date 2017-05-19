package com.iexceed.esoko.sch.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iexceed.esoko.domain.dao.GroupMasterRepo;
import com.iexceed.esoko.domain.dao.UserSmartGroupRepo;
import com.iexceed.esoko.domain.dao2.PeopleRepo;
import com.iexceed.esoko.domain.entity.GroupMaster;
import com.iexceed.esoko.domain.entity.GroupMasterPK;
import com.iexceed.esoko.domain.entity.SmartGroupHistory;
import com.iexceed.esoko.domain.entity.SmartGroupHistoryPK;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

public class SmartGroupPopulationJob extends QuartzJobBean {

	private static Logger log = LoggerUtils.getSmartGroupLogger();	
	private PeopleRepo pplRepo;	
	private GroupMasterRepo grpRepo;	
	private UserSmartGroupRepo usrSmrtGrpRepo;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		log.info("Inside SmartGroupPopulationJob -> executeInternal");
		if (Utils.springContext == null) {
			log.info("Spring context is null");
		} else {
			pplRepo = Utils.springContext.getBean(PeopleRepo.class);
			grpRepo = Utils.springContext.getBean(GroupMasterRepo.class);
			usrSmrtGrpRepo = Utils.springContext
					.getBean(UserSmartGroupRepo.class);
			log.info("Tiggering SmartGroupJob");
			this.triggerSmartGroupJob();
		}
	}
		
	public void triggerSmartGroupJob() {
		log.info("Inside SmartGroupPopulationJob -> triggerSmartGroupJob");
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new Runnable() {
			public void run() {
				SmartGroupPopulationJob.this.populateSmartGroupHistory();
			}
		});
		executorService.shutdown();
	}

	public void populateSmartGroupHistory() {
		log.info("Inside SmartGroupPopulationJob -> populateSmartGroups");
		List<GroupMaster> groupList = grpRepo.queryAllSmartGroups();
		if (groupList.size() != 0) {
			List<SmartGroupHistory> entityList = new ArrayList<SmartGroupHistory>();
			for (GroupMaster group : groupList) {
				GroupMasterPK masterPK = group.getId();
				String groupId = masterPK.getGroupId();
				String networkId = masterPK.getNetworkId();
				String createdBy = group.getCreatedBy();
				Date createdTs = group.getCreatedTs();
				List<Map<String, Object>> pplLst = pplRepo
						.getSmartGroupRecords(groupId, networkId);
				log.info(groupId + ": " + pplLst.size());
				if (pplLst.size() != 0) {
					for (Map<String, Object> ppl : pplLst) {
						SmartGroupHistoryPK smrtGrpPK = new SmartGroupHistoryPK();
						smrtGrpPK.setGroupId(groupId);
						smrtGrpPK.setNetworkId(networkId);
						smrtGrpPK.setUserId(ppl.get("peopleId").toString());
						SmartGroupHistory smrtGrp = new SmartGroupHistory();
						smrtGrp.setCreatedBy(createdBy);
						smrtGrp.setCreatedTs(createdTs);
						smrtGrp.setId(smrtGrpPK);
						entityList.add(smrtGrp);
					}
				}
			}
			if (entityList.size() != 0) {
				usrSmrtGrpRepo.populateUserSmartGroup(entityList);
			}
		}
	}

}
