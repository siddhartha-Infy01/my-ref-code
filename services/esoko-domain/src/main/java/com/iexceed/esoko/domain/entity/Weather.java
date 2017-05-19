package com.iexceed.esoko.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the weather database table.
 * 
 */
@Entity
@Table(name = "weather")
@NamedQuery(name = "Weather.findAll", query = "SELECT w FROM Weather w")
public class Weather implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "weather_id")
	private String weatherId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "measure_id")
	private String measureId;

	private String rain;

	private String min_temp;

	private String max_temp;

	private String summary;

	private String response;

	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Weather() {
	}

	public String getWeatherId() {
		return this.weatherId;
	}

	public void setWeatherId(String weatherId) {
		this.weatherId = weatherId;
	}

	public String getMeasureId() {
		return measureId;
	}

	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}

	public String getMin_temp() {
		return min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_temp() {
		return max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getRain() {
		return this.rain;
	}

	public void setRain(String rain) {
		this.rain = rain;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Weather [weatherId=" + weatherId + ", date=" + date
				+ ", locationId=" + locationId + ", measureId=" + measureId
				+ ", rain=" + rain + ", min_temp=" + min_temp + ", max_temp="
				+ max_temp + ", summary=" + summary + ", response=" + response
				+ ", source=" + source + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((locationId == null) ? 0 : locationId.hashCode());
		result = prime * result
				+ ((max_temp == null) ? 0 : max_temp.hashCode());
		result = prime * result
				+ ((measureId == null) ? 0 : measureId.hashCode());
		result = prime * result
				+ ((min_temp == null) ? 0 : min_temp.hashCode());
		result = prime * result + ((rain == null) ? 0 : rain.hashCode());
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result
				+ ((weatherId == null) ? 0 : weatherId.hashCode());
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
		Weather other = (Weather) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (locationId == null) {
			if (other.locationId != null)
				return false;
		} else if (!locationId.equals(other.locationId))
			return false;
		if (max_temp == null) {
			if (other.max_temp != null)
				return false;
		} else if (!max_temp.equals(other.max_temp))
			return false;
		if (measureId == null) {
			if (other.measureId != null)
				return false;
		} else if (!measureId.equals(other.measureId))
			return false;
		if (min_temp == null) {
			if (other.min_temp != null)
				return false;
		} else if (!min_temp.equals(other.min_temp))
			return false;
		if (rain == null) {
			if (other.rain != null)
				return false;
		} else if (!rain.equals(other.rain))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (weatherId == null) {
			if (other.weatherId != null)
				return false;
		} else if (!weatherId.equals(other.weatherId))
			return false;
		return true;
	}

}