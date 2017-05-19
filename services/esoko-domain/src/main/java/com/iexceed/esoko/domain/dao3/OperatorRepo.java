package com.iexceed.esoko.domain.dao3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iexceed.esoko.domain.common.AbstractRepoForEntity;
import com.iexceed.esoko.domain.entity3.Operator;
import com.iexceed.esoko.engine.utils.JdbcConnector;
import com.iexceed.esoko.engine.utils.LoggerUtils;
import com.iexceed.esoko.engine.utils.Utils;

@Component
@SuppressWarnings("unchecked")
public class OperatorRepo extends AbstractRepoForEntity<Operator, String> {

	private static Logger log = LoggerUtils.getLogger();

	private JdbcConnector connector = new JdbcConnector();
	private CallableStatement callableStmt;
	private PreparedStatement prepstmt;

	public boolean exists(Operator entity, String id) {
		log.info("Inside OperatorRepo -> exists");
		return super.exists(Operator.class, id);
	}

	public Operator merge(Operator entity) {
		log.info("Inside OperatorRepo -> merge");
		return super.merge(entity);
	}

	public Operator save(Operator entity) {
		log.info("Inside OperatorRepo -> save");
		return super.save(entity);
	}

	public Operator findOne(Operator entity, String id) {
		log.info("Inside OperatorRepo -> findOne");
		return super.findOne(Operator.class, id);
	}

	public void delete(Operator entity) {
		log.info("Inside OperatorRepo -> delete");
		super.delete(entity);
	}

	@SuppressWarnings("rawtypes")
	public void delete(Iterable entities) {
		log.info("Inside OperatorRepo -> deleteAll");
		super.delete(entities);
	}

	public List<Operator> queryOperatorByLoc(String locationId) {

		log.info("Entered query operators based on locations");

		Query query = entityManager.createNativeQuery(
				getSql("queryOperatorByLoc"), Operator.class);
		query.setParameter(1, locationId);

		List<Operator> resultList = query.getResultList();

		entityManager.close();
		return resultList;

	}

	public Map<String, String> getOperatorByOperatorid(String operatorId) {
		log.info("Entered query operators based on operatorId:::" + operatorId);
		Query query = entityManager
				.createNativeQuery(getSql("getOperatorByOperatorid"));
		query.setParameter(1, operatorId);
		return getResult(query);
	}

	private Map<String, String> getResult(Query query) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		List<Object[]> entityList = query.getResultList();
		for (Object[] obj : entityList) {
			Map<String, String> recordMap = new HashMap<String, String>();
			for (int i = 0; i < obj.length; i++) {
				switch (i) {
				case 0:
					recordMap.put("operator", obj[i].toString());
					break;
				case 1:
					recordMap.put("mcc", obj[i].toString());
					break;
				case 2:
					recordMap.put("mnc", obj[i].toString());
					break;
				case 3:
					recordMap.put("gateway", obj[i].toString());
					break;
				case 4:
					recordMap.put("country", obj[i].toString());
					break;
				case 5:
					recordMap.put("routeId", obj[i].toString());
					break;
				case 6:
					recordMap.put("cost", obj[i].toString());
					break;
				case 7:
					recordMap.put("margin", obj[i].toString());
					break;
				case 8:
					recordMap.put("premium", obj[i].toString());
					break;
				case 9:
					recordMap.put("currency", obj[i].toString());
					break;
				case 10:
					if (obj[i] == null) {
						recordMap.put("exception", "None");
					} else {
						recordMap.put("exception", obj[i].toString());
					}
					break;
				case 11:
					recordMap.put("channel", obj[i].toString());
					break;
				case 12:
					recordMap.put("direction", obj[i].toString());
					break;
				case 13:
					recordMap.put("smsc", obj[i].toString());
					break;
				}
			}
			resultList.add(recordMap);
		}
		return resultList.get(0);
	}

	public Map<String, Double> deriveFinalCost(String networkID,
			String surchargeable, String operatorId) {
		log.info("Inside Derive Final Cost");
		Connection connection = connector.getJdbcConnection("IEsokoDS");
		Map<String, Double> costMap = new HashMap<String, Double>();
		if (connection != null) {
			try {
				log.info("Executing process");

				callableStmt = connection
						.prepareCall("{call deriveCostForOperator(?,?,?,?,?,?)}");

				callableStmt.setString(1, networkID);
				callableStmt.setString(2, surchargeable);
				callableStmt.setString(3, operatorId);
				callableStmt.registerOutParameter(4, java.sql.Types.DOUBLE);
				callableStmt.registerOutParameter(5, java.sql.Types.DOUBLE);
				callableStmt.registerOutParameter(6, java.sql.Types.DOUBLE);
				callableStmt.executeQuery();
				costMap.put("baseCost", callableStmt.getDouble(4));
				costMap.put("retailCost", callableStmt.getDouble(5));
				costMap.put("wholesaleCost", callableStmt.getDouble(6));

			} catch (SQLException e) {
				log.error(Utils.getStackTrace(e));
			} finally {
				try {
					if (callableStmt != null) {
						callableStmt.close();
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
		return costMap;
	}

	public String deriveLocalCurrency(String networkId) {
		log.info("Inside Derive Final Cost");
		Connection connection = connector.getJdbcConnection("IEsokoDS");
		String currency="USD";
		try {
			prepstmt = connection
					.prepareStatement("select b.currency from networks a, locations b where b.location_id=a.primary_location_id and a.network_id=?");
			prepstmt.setString(1, networkId);
			ResultSet result=prepstmt.executeQuery();
			while(result.next()){
				currency=result.getString(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (prepstmt != null) {
					prepstmt.close();
				}
				if (connection != null) {
					connection.close();
				}

			} catch (SQLException e) {
				log.error(Utils.getStackTrace(e));
			}
		}
		return currency;
	}

}
