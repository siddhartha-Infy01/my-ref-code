package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the alert_source_networks database table.
 * 
 */
@Entity
@Table(name = "alert_source_networks")
@NamedQuery(name = "AlertSourceNetwork.findAll", query = "SELECT a FROM AlertSourceNetwork a")
public class AlertSourceNetwork implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlertSourceNetworkPK id;

	public AlertSourceNetwork() {
	}

	public AlertSourceNetworkPK getId() {
		return this.id;
	}

	public void setId(AlertSourceNetworkPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertSourceNetwork other = (AlertSourceNetwork) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertSourceNetwork [id=" + id + "]";
	}

}