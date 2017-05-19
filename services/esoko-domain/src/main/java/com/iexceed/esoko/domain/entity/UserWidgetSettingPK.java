package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the user_widget_settings database table.
 * 
 */
@Embeddable
public class UserWidgetSettingPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private String userId;

	@Column(name="widget_id")
	private String widgetId;

	public UserWidgetSettingPK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getWidgetId() {
		return this.widgetId;
	}
	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserWidgetSettingPK)) {
			return false;
		}
		UserWidgetSettingPK castOther = (UserWidgetSettingPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.widgetId.equals(castOther.widgetId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.widgetId.hashCode();
		
		return hash;
	}
	@Override
	public String toString() {
		return "UserWidgetSettingPK [userId=" + userId + ", widgetId="
				+ widgetId + "]";
	}
}