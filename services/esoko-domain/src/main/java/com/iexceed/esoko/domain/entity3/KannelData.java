package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the kannel_data database table.
 * 
 */
@Entity
@Table(name = "kannel_data")
@NamedQuery(name = "KannelData.findAll", query = "SELECT k FROM KannelData k")
public class KannelData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String smpp;

	private String ipaddress;

	@Column(name = "port_no")
	private String portNo;

	private String smsc;

	public KannelData() {
	}

	public String getSmpp() {
		return this.smpp;
	}

	public void setSmpp(String smpp) {
		this.smpp = smpp;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getPortNo() {
		return this.portNo;
	}

	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}

	public String getSmsc() {
		return this.smsc;
	}

	public void setSmsc(String smsc) {
		this.smsc = smsc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ipaddress == null) ? 0 : ipaddress.hashCode());
		result = prime * result + ((portNo == null) ? 0 : portNo.hashCode());
		result = prime * result + ((smpp == null) ? 0 : smpp.hashCode());
		result = prime * result + ((smsc == null) ? 0 : smsc.hashCode());
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
		KannelData other = (KannelData) obj;
		if (ipaddress == null) {
			if (other.ipaddress != null)
				return false;
		} else if (!ipaddress.equals(other.ipaddress))
			return false;
		if (portNo == null) {
			if (other.portNo != null)
				return false;
		} else if (!portNo.equals(other.portNo))
			return false;
		if (smpp == null) {
			if (other.smpp != null)
				return false;
		} else if (!smpp.equals(other.smpp))
			return false;
		if (smsc == null) {
			if (other.smsc != null)
				return false;
		} else if (!smsc.equals(other.smsc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KannelData [smpp=" + smpp + ", ipaddress=" + ipaddress
				+ ", portNo=" + portNo + ", smsc=" + smsc + "]";
	}

}