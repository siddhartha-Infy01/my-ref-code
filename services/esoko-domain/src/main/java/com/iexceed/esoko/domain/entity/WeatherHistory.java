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

@Entity
@Table(name = "weather_history")
@NamedQuery(name = "WeatherHistory.findAll", query = "SELECT w FROM WeatherHistory w")
public class WeatherHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "weather_id")
	private String weatherId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(name = "location_id")
	private String locationId;
	private String measure_id;
	private String source;
	private String min_temp;
	private String max_temp;
	private String summary;
	private String rain;
	private String response;

	public String getWeatherId() {
		return this.weatherId;
	}

	public void setWeatherId(String weatherId) {
		this.weatherId = weatherId;
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

	public String getMeasure_id() {
		return this.measure_id;
	}

	public void setMeasure_id(String measure_id) {
		this.measure_id = measure_id;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMin_temp() {
		return this.min_temp;
	}

	public void setMin_temp(String min_temp) {
		this.min_temp = min_temp;
	}

	public String getMax_temp() {
		return this.max_temp;
	}

	public void setMax_temp(String max_temp) {
		this.max_temp = max_temp;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String toString() {
		return "WeatherHistory [weatherId=" + this.weatherId + ", date="
				+ this.date + ", locationId=" + this.locationId
				+ ", measure_id=" + this.measure_id + ", source=" + this.source
				+ ", min_temp=" + this.min_temp + ", max_temp=" + this.max_temp
				+ ", summary=" + this.summary + ", rain=" + this.rain
				+ ", response=" + this.response + "]";
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.date == null ? 0 : this.date.hashCode());

		result = 31 * result
				+ (this.locationId == null ? 0 : this.locationId.hashCode());

		result = 31 * result
				+ (this.max_temp == null ? 0 : this.max_temp.hashCode());

		result = 31 * result
				+ (this.measure_id == null ? 0 : this.measure_id.hashCode());

		result = 31 * result
				+ (this.min_temp == null ? 0 : this.min_temp.hashCode());
		result = 31 * result + (this.rain == null ? 0 : this.rain.hashCode());

		result = 31 * result
				+ (this.response == null ? 0 : this.response.hashCode());
		result = 31 * result
				+ (this.source == null ? 0 : this.source.hashCode());
		result = 31 * result
				+ (this.summary == null ? 0 : this.summary.hashCode());

		result = 31 * result
				+ (this.weatherId == null ? 0 : this.weatherId.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WeatherHistory other = (WeatherHistory) obj;
		if (this.date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!this.date.equals(other.date)) {
			return false;
		}
		if (this.locationId == null) {
			if (other.locationId != null) {
				return false;
			}
		} else if (!this.locationId.equals(other.locationId)) {
			return false;
		}
		if (this.max_temp == null) {
			if (other.max_temp != null) {
				return false;
			}
		} else if (!this.max_temp.equals(other.max_temp)) {
			return false;
		}
		if (this.measure_id == null) {
			if (other.measure_id != null) {
				return false;
			}
		} else if (!this.measure_id.equals(other.measure_id)) {
			return false;
		}
		if (this.min_temp == null) {
			if (other.min_temp != null) {
				return false;
			}
		} else if (!this.min_temp.equals(other.min_temp)) {
			return false;
		}
		if (this.rain == null) {
			if (other.rain != null) {
				return false;
			}
		} else if (!this.rain.equals(other.rain)) {
			return false;
		}
		if (this.response == null) {
			if (other.response != null) {
				return false;
			}
		} else if (!this.response.equals(other.response)) {
			return false;
		}
		if (this.source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!this.source.equals(other.source)) {
			return false;
		}
		if (this.summary == null) {
			if (other.summary != null) {
				return false;
			}
		} else if (!this.summary.equals(other.summary)) {
			return false;
		}
		if (this.weatherId == null) {
			if (other.weatherId != null) {
				return false;
			}
		} else if (!this.weatherId.equals(other.weatherId)) {
			return false;
		}
		return true;
	}
}
