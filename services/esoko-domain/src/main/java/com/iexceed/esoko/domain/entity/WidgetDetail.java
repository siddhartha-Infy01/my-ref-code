package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the widget_details database table.
 * 
 */
@Entity
@Table(name="widget_details")
@NamedQuery(name="WidgetDetail.findAll", query="SELECT w FROM WidgetDetail w")
public class WidgetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="widget_id")
	private String widgetId;

	private String description;

	public WidgetDetail() {
	}

	public String getWidgetId() {
		return this.widgetId;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((widgetId == null) ? 0 : widgetId.hashCode());
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
		WidgetDetail other = (WidgetDetail) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (widgetId == null) {
			if (other.widgetId != null)
				return false;
		} else if (!widgetId.equals(other.widgetId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WidgetDetail [widgetId=" + widgetId + ", description="
				+ description + "]";
	}

}