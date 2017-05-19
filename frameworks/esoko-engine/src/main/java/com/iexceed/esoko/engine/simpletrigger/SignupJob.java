package com.iexceed.esoko.engine.simpletrigger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.iexceed.esoko.engine.utils.JdbcConnector;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

public class SignupJob extends QuartzJobBean {
	private static Logger log = LoggerUtils.getSchLogger();

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		log.info("Inside SignupJob -> executeInternal");
		String userId = context.getTrigger().getKey().getName();
		log.debug("UserdId: " + userId);		
		if (Utils.springContext == null) {
			log.info("Spring context is null");
		} else {
			this.deleteUserRecords(userId);
		}
	}

	public void deleteUserRecords(String userId) {
		log.info("Inside UserSignUpService -> deleteUserRecords");
		JdbcConnector connector = new JdbcConnector();
		Connection connection = connector.getJdbcConnection("IEsokoDS");

		if (connection != null) {
			PreparedStatement stmt = null;
			try {
				log.info("Executing process");
				String networkId = null;
				String deviceId = null;
				stmt = connection
						.prepareStatement("SELECT * FROM signup_detail WHERE user_id=?");
				stmt.setString(1, userId);
				if (stmt != null) {
					ResultSet result = stmt.executeQuery();
					while (result.next()) {
						networkId = result.getString(5);
						deviceId = result.getString(6);
					}
					result.close();
					stmt.close();
					if (networkId != null && deviceId != null) {
						log.debug("NetworkId: " + networkId);
						log.debug("DeviceId: " + deviceId);						

						stmt = connection
								.prepareStatement("delete FROM people where people_id=?");
						if (stmt != null) {
							stmt.setString(1, userId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from people");
						}
						stmt = connection
								.prepareStatement("delete FROM system_user where user_id=?");
						if (stmt != null) {
							stmt.setString(1, userId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from system_user");
						}
						stmt = connection
								.prepareStatement("delete FROM System_accounts where owner_id=?");
						if (stmt != null) {
							stmt.setString(1, userId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from System_accounts");
						}
						stmt = connection
								.prepareStatement("delete FROM Networks where owner_user_id=?");
						if (stmt != null) {
							stmt.setString(1, userId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from Networks");
						}
						stmt = connection
								.prepareStatement("delete FROM Network_privacy where network_id=?");
						if (stmt != null) {
							stmt.setString(1, networkId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from Network_privacy");
						}
						stmt = connection
								.prepareStatement("delete FROM network_userid where network_id=?");
						if (stmt != null) {
							stmt.setString(1, networkId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from network_userid");
						}
						stmt = connection
								.prepareStatement("delete FROM group_master where network_id=?");
						if (stmt != null) {
							stmt.setString(1, networkId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from group_master");
						}
						stmt = connection
								.prepareStatement("delete FROM smartgroup_rules where network_id=?");
						if (stmt != null) {
							stmt.setString(1, networkId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from smartgroup_rules");
						}
						stmt = connection
								.prepareStatement("delete FROM System_accounts where owner_id=?");
						if (stmt != null) {
							stmt.setString(1, networkId);
							stmt.executeUpdate();
							stmt.close();
							log.info("Record deleted from System_accounts");
						}
						stmt = connection
								.prepareStatement("delete FROM signup_detail where user_id=?");
						if (stmt != null) {
							stmt.setString(1, userId);
							stmt.executeUpdate();
							log.info("Record deleted from signup_detail");
						}
					} else {
						log.info("Unbale to get record for userId: " + userId);
					}

				}

			} catch (SQLException e) {
				log.error(Utils.getStackTrace(e));
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
					if (connection != null) {
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

}
