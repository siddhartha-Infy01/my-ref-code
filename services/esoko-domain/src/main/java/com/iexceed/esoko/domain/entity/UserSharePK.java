package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_shares database table.
 * 
 */
@Embeddable
public class UserSharePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="from_share")
	private String fromShare;

	@Column(name="to_share")
	private String toShare;

	@Column(name="share_item")
	private String shareItem;

	public UserSharePK() {
	}
	public String getFromShare() {
		return this.fromShare;
	}
	public void setFromShare(String fromShare) {
		this.fromShare = fromShare;
	}
	public String getToShare() {
		return this.toShare;
	}
	public void setToShare(String toShare) {
		this.toShare = toShare;
	}
	public String getShareItem() {
		return this.shareItem;
	}
	public void setShareItem(String shareItem) {
		this.shareItem = shareItem;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserSharePK)) {
			return false;
		}
		UserSharePK castOther = (UserSharePK)other;
		return 
			this.fromShare.equals(castOther.fromShare)
			&& this.toShare.equals(castOther.toShare)
			&& this.shareItem.equals(castOther.shareItem);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.fromShare.hashCode();
		hash = hash * prime + this.toShare.hashCode();
		hash = hash * prime + this.shareItem.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "UserSharePK [fromShare=" + fromShare + ", toShare=" + toShare
				+ ", shareItem=" + shareItem + "]";
	}
}