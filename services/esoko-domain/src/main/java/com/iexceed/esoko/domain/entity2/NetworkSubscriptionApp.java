package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the network_subscription_apps database table.
 * 
 */
@Entity
@Table(name="network_subscription_apps")
@NamedQuery(name="NetworkSubscriptionApp.findAll", query="SELECT n FROM NetworkSubscriptionApp n")
public class NetworkSubscriptionApp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private NetworkSubscriptionAppPK id;

	private String description;

	@Column(name="param_value")
	private String paramValue;

	private String period;

	public NetworkSubscriptionApp() {
	}

	public NetworkSubscriptionAppPK getId() {
		return this.id;
	}

	public void setId(NetworkSubscriptionAppPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}