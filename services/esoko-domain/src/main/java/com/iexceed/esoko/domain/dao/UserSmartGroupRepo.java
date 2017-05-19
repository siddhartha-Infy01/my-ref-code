package com.iexceed.esoko.domain.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity.SmartGroupHistory;
import com.iexceed.esoko.domain.entity.SmartGroupHistoryPK;
import com.iexceed.esoko.domain.entity.UserSmartGroup;
import com.iexceed.esoko.domain.entity.UserSmartGroupPK;
import com.iexceed.esoko.engine.utils.JdbcConnector;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserSmartGroupRepo extends
		AbstractRepoForEntity<UserSmartGroup, UserSmartGroupPK> {

	private static Logger log = LoggerUtils.getSmartGroupLogger();
	private JdbcConnector connector = new JdbcConnector();
	private CallableStatement callableStmt;
	private PreparedStatement stmt;

	public UserSmartGroup merge(UserSmartGroup userSmartGroup) {
		log.info("Inside UserSmartGroupRepo -> merge");
		return super.merge(userSmartGroup);
	}

	public UserSmartGroup save(UserSmartGroup userSmartGroup) {
		log.info("Inside UserSmartGroupRepo -> save");
		return super.save(userSmartGroup);
	}
	
	public List<UserSmartGroup> queryAllEntities(String groupId, String networkId){
		log.info("Inside UserSmartGroupRepo -> queryAllEntities");
		Query query = entityManager.createNativeQuery(getSql("queryAllUserSmarGroup"), UserSmartGroup.class);
		query.setParameter(1, groupId);
		query.setParameter(2, networkId);
		List<UserSmartGroup> resultList = query.getResultList();
		return resultList;
	}
	
	public void populateUserSmartGroup(List<SmartGroupHistory> entityList) {
		log.info("Inside UserSmartGroupRepo -> populateUserSmartGroup");
		Connection connection = connector.getJdbcConnection("IEsokoDS");
		if (connection != null) {
			try {
				log.info("Executing process");
				stmt = connection
						.prepareStatement("insert into smart_group_history values(?,?,?,?,?,?,?)");
				callableStmt = connection
						.prepareCall("{call processSmartGroup()}");
				if (stmt == null || callableStmt == null) {
					log.info("Statement is null");
				} else {
					for (SmartGroupHistory smrtGrp : entityList) {
						SmartGroupHistoryPK historyPk = smrtGrp.getId();
						stmt.setString(1, historyPk.getGroupId());
						stmt.setString(2, historyPk.getUserId());
						stmt.setString(3, smrtGrp.getCreatedBy());
						if (smrtGrp.getCreatedTs() == null) {
							stmt.setDate(4, null);
						} else {
							stmt.setDate(4, new java.sql.Date(smrtGrp
									.getCreatedTs().getTime()));
						}
						stmt.setString(5, smrtGrp.getModifiedBy());
						if (smrtGrp.getModifiedTs() == null) {
							stmt.setDate(6, null);
						} else {
							stmt.setDate(6, new java.sql.Date(smrtGrp
									.getModifiedTs().getTime()));
						}
						stmt.setString(7, historyPk.getNetworkId());
						stmt.executeUpdate();
					}
					log.info(entityList.size() + " records inserted");
					
					callableStmt.execute();
					log.info("UserSmartGroup populated successfully ");									
				}

			} catch (SQLException e) {
				log.error(Utils.getStackTrace(e));
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (callableStmt != null) {
						callableStmt.close();
					}
					if(connection != null){
						connection.close();
					}
					
				} catch (SQLException e) {
					log.error(Utils.getStackTrace(e));
				}
			}
		} else {
			log.info("Unable to get Connection");
		}

	}

	public UserSmartGroup findOne(UserSmartGroup userSmartGroup,
			UserSmartGroupPK userSmartGroupPk) {
		log.info("Inside UserSmartGroupRepo -> findOne");
		return super.findOne(UserSmartGroup.class, userSmartGroupPk);
	}

	public void delete(Iterable userSmartGroup) {
		log.info("Inside UserSmartGroupRepo -> delete");
		super.delete(userSmartGroup);
	}

	public void delete(UserSmartGroup userSmartGroup) {
		log.info("Inside UserSmartGroupRepo -> delete");
		super.delete(userSmartGroup);
	}

}
