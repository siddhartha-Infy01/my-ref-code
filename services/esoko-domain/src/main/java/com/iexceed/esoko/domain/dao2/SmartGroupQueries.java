package com.iexceed.esoko.domain.dao2;

public class SmartGroupQueries {

	String generateQuery(String type, String condition, String value,
			String networkId, int option) {
		String query = null;

		if (type.equalsIgnoreCase("Commodity")) {
			if (condition.equalsIgnoreCase("ISIN")) {
				query = "select user_id FROM user_commodities"
						+ " where commodity_id in (select commodity_id from commodities"
						+ " where commodity_id in ("
						+ value
						+ ") AND record_status = 'A' AND auth_stat = 'A') and network_id ";
			} else if (condition.equalsIgnoreCase("ISNOTIN")) {
				query = "select user_id FROM user_commodities"
						+ " where commodity_id in (select commodity_id from commodities"
						+ " where commodity_id not in ("
						+ value
						+ ") AND record_status = 'A' AND auth_stat = 'A') and network_id ";
			}
		} else if (type.equalsIgnoreCase("Location")) {
			if (condition.equalsIgnoreCase("ISIN")) {
				query = "SELECT user_id FROM user_locations "
						+ "WHERE location_id in (SELECT location_id from locations "
						+ "WHERE location_id in ("
						+ value
						+ ") AND record_status='A' AND auth_stat = 'A') and network_id ";

			} else if (condition.equalsIgnoreCase("ISNOTIN")) {
				query = "SELECT user_id FROM user_locations "
						+ "WHERE location_id in (SELECT location_id from locations "
						+ "WHERE location_id not in ("
						+ value
						+ ") AND record_status='A' AND auth_stat = 'A') and network_id ";
			}
		} else if (type.equalsIgnoreCase("Occupation")) {
			if (condition.equalsIgnoreCase("ISIN")) {
				query = "SELECT user_id FROM user_occupations "
						+ "WHERE occupation_id IN (SELECT occupation_id FROM occupations "
						+ "WHERE occupation_id in (" + value
						+ ") AND record_status = 'A') and network_id ";
			} else if (condition.equalsIgnoreCase("ISNOTIN")) {
				query = "SELECT user_id FROM user_occupations "
						+ "WHERE occupation_id IN (SELECT occupation_id FROM occupations "
						+ "WHERE occupation_id not in (" + value
						+ ") AND record_status = 'A') and network_id ";
			} else if (condition.equalsIgnoreCase("SW")) {
				query = "SELECT user_id FROM user_occupations "
						+ "WHERE occupation_id IN (SELECT occupation_id FROM occupations "
						+ "WHERE occupation_id like '" + value
						+ "%' AND record_status = 'A') and network_id ";
			} else if (condition.equalsIgnoreCase("EW")) {
				query = "SELECT user_id FROM user_occupations "
						+ "WHERE occupation_id IN (SELECT occupation_id FROM occupations "
						+ "WHERE occupation_id like '%" + value
						+ "' AND record_status = 'A') and network_id ";
			} else if (condition.equalsIgnoreCase("CONT")) {
				query = "SELECT user_id FROM user_occupations "
						+ "WHERE occupation_id IN (SELECT occupation_id FROM occupations "
						+ "WHERE occupation_id like '%" + value
						+ "%' AND record_status = 'A') and network_id ";
			} else if (condition.equalsIgnoreCase("DNCONT")) {
				query = "SELECT user_id FROM user_occupations "
						+ "WHERE occupation_id IN (SELECT occupation_id FROM occupations "
						+ "WHERE occupation_id not like '%" + value
						+ "%' AND record_status = 'A') and network_id ";
			}
		} else if (type.equalsIgnoreCase("People")) {
			if (condition.equalsIgnoreCase("ARE")) {
				if (value.equalsIgnoreCase("Agent")) {
					query = "SELECT people_id FROM people "
							+ "where people_id in (SELECT user_id FROM agent_details "
							+ "where record_status = 'A') and default_network_id ";
				} else if (value.equalsIgnoreCase("Male")) {
					query = "SELECT people_id FROM people "
							+ "where gender = 'M' and default_network_id ";
				} else if (value.equalsIgnoreCase("Female")) {
					query = "SELECT people_id FROM people "
							+ "where gender = 'F' and default_network_id ";
				}

			} else if (condition.equalsIgnoreCase("ARENOT")) {
				if (value.equalsIgnoreCase("Agent")) {
					query = "SELECT people_id FROM people "
							+ "where people_id not in (SELECT user_id FROM agent_details "
							+ "where record_status = 'A') and default_network_id ";
				} else if (value.equalsIgnoreCase("Male")) {
					query = "SELECT people_id FROM people "
							+ "where gender = 'F' and default_network_id ";
				} else if (value.equalsIgnoreCase("Female")) {
					query = "SELECT people_id FROM people "
							+ "where gender = 'M' and default_network_id ";
				}
			} else if (condition.equalsIgnoreCase("ADDED")) {
				query = "SELECT people_id FROM people "
						+ "where created_ts > date((sysdate() - interval "
						+ value + " day)) and default_network_id ";
			} else if (condition.equalsIgnoreCase("UPDATED")) {
				query = "SELECT people_id FROM people "
						+ "where modified_ts > date((sysdate() - interval "
						+ value + " day)) and default_network_id ";
			}
		} else if (type.equalsIgnoreCase("PeopleAge")) {
			if (condition.equalsIgnoreCase("EQ")) {
				query = "SELECT people_id FROM people "
						+ "where (YEAR(CURDATE()) - birthyear) = "
						+ value + " and default_network_id ";
			} else if (condition.equalsIgnoreCase("ILT")) {
				query = "SELECT people_id FROM people "
						+ "where (YEAR(CURDATE()) - birthyear) < "
						+ value + " and default_network_id ";
			} else if (condition.equalsIgnoreCase("IGT")) {
				query = "SELECT people_id FROM people "
						+ "where (YEAR(CURDATE()) - birthyear) > "
						+ value + " and default_network_id ";
			} else if (condition.equalsIgnoreCase("ILTEQ")) {
				query = "SELECT people_id FROM people "
						+ "where (YEAR(CURDATE()) - birthyear) <= "
						+ value + " and default_network_id ";
			} else if (condition.equalsIgnoreCase("IGTEQ")) {
				query = "SELECT people_id FROM people "
						+ "where (YEAR(CURDATE()) - birthyear) >= "
						+ value + " and default_network_id ";
			}
		} else if (type.equalsIgnoreCase("Language")) {
			if (condition.equalsIgnoreCase("ISIN")) {
				query = "SELECT people_id FROM people "						
						+ "WHERE language_id in (" + value
						+ ") and default_network_id ";
			} else if (condition.equalsIgnoreCase("ISNOTIN")) {
				query = "SELECT people_id FROM people "						
						+ "WHERE language_id not in (" + value
						+ ") and default_network_id ";
			} else if (condition.equalsIgnoreCase("SW")) {
				query = "SELECT people_id FROM people "						
						+ "WHERE language_id like '" + value
						+ "%' and default_network_id ";
			} else if (condition.equalsIgnoreCase("EW")) {
				query = "SELECT people_id FROM people "						
						+ "WHERE language_id like '%" + value
						+ "' and default_network_id ";
			} else if (condition.equalsIgnoreCase("CONT")) {
				query = "SELECT people_id FROM people "						
						+ "WHERE language_id like '%" + value
						+ "%' and default_network_id ";
			} else if (condition.equalsIgnoreCase("DNCONT")) {
				query = "SELECT people_id FROM people "						
						+ "WHERE language_id not like '%" + value
						+ "%' and default_network_id ";
			}
		}
		StringBuilder finalQuery = new StringBuilder(query);

		switch (option) {
		case 1:
			finalQuery.append("= '" + networkId + "'");
			break;
		case 2:
			finalQuery
					.append("in (SELECT from_share FROM user_shares WHERE to_share = '"
							+ networkId
							+ "' AND share_item='people' AND auth_stat = 'A')");
			break;
		}
		return finalQuery.toString();
	}
}
