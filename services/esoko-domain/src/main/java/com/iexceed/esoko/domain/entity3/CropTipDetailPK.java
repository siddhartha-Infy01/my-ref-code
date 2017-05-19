package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the crop_tip_details database table.
 * 
 */
@Embeddable
public class CropTipDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="crop_tip_id")
	private String cropTipId;

	@Column(name="tip_no")
	private String tipNo;

	public CropTipDetailPK() {
	}
	public String getCropTipId() {
		return this.cropTipId;
	}
	public void setCropTipId(String cropTipId) {
		this.cropTipId = cropTipId;
	}
	public String getTipNo() {
		return this.tipNo;
	}
	public void setTipNo(String tipNo) {
		this.tipNo = tipNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CropTipDetailPK)) {
			return false;
		}
		CropTipDetailPK castOther = (CropTipDetailPK)other;
		return 
			this.cropTipId.equals(castOther.cropTipId)
			&& this.tipNo.equals(castOther.tipNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cropTipId.hashCode();
		hash = hash * prime + this.tipNo.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "CropTipDetailPK [cropTipId=" + cropTipId + ", tipNo=" + tipNo
				+ "]";
	}
		
}