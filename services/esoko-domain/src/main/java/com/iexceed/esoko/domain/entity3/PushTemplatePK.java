package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the push_templates database table.
 * 
 */
@Embeddable
public class PushTemplatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="network_id")
	private String networkId;

	private String templateId;

	public PushTemplatePK() {
	}
	public String getNetworkId() {
		return this.networkId;
	}
	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}
	public String getTemplateId() {
		return this.templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PushTemplatePK)) {
			return false;
		}
		PushTemplatePK castOther = (PushTemplatePK)other;
		return 
			this.networkId.equals(castOther.networkId)
			&& this.templateId.equals(castOther.templateId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.networkId.hashCode();
		hash = hash * prime + this.templateId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "PushTemplatePK [networkId=" + networkId + ", templateId="
				+ templateId + "]";
	}
}