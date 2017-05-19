package com.iexceed.esoko.domain.entity2;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;

/**
 * The persistent class for the survey_templates database table.
 * 
 */
@Entity
@Table(name = "survey_templates")
@NamedQuery(name = "SurveyTemplate.findAll", query = "SELECT s FROM SurveyTemplate s")
public class SurveyTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SurveyTemplatePK id;

	@Column(name = "auth_stat")
	private String authStat;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created_TS;

	private String description;

	@Column(name = "survey_definition")
	private String surveyDefinition;

	public SurveyTemplate() {
	}

	public SurveyTemplatePK getId() {
		return this.id;
	}

	public void setId(SurveyTemplatePK id) {
		this.id = id;
	}

	public String getAuthStat() {
		return this.authStat;
	}

	public void setAuthStat(String authStat) {
		this.authStat = authStat;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreated_TS() {
		return this.created_TS;
	}

	public void setCreated_TS(Date created_TS) {
		this.created_TS = created_TS;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSurveyDefinition() {
		return surveyDefinition;
	}

	public void setSurveyDefinition(String surveyDefinition) {
		this.surveyDefinition = surveyDefinition;
	}

	@Override
	public String toString() {
		return "SurveyTemplate [id=" + id + ", authStat=" + authStat
				+ ", createdBy=" + createdBy + ", created_TS=" + created_TS
				+ ", description=" + description + ", surveyDefinition="
				+ surveyDefinition + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authStat == null) ? 0 : authStat.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((created_TS == null) ? 0 : created_TS.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((surveyDefinition == null) ? 0 : surveyDefinition.hashCode());
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
		SurveyTemplate other = (SurveyTemplate) obj;
		if (authStat == null) {
			if (other.authStat != null)
				return false;
		} else if (!authStat.equals(other.authStat))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (created_TS == null) {
			if (other.created_TS != null)
				return false;
		} else if (!created_TS.equals(other.created_TS))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (surveyDefinition == null) {
			if (other.surveyDefinition != null)
				return false;
		} else if (!surveyDefinition.equals(other.surveyDefinition))
			return false;
		return true;
	}

}