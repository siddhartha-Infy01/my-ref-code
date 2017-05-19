package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the operator_templates database table.
 * 
 */
@Entity
@Table(name="operator_templates")
@NamedQuery(name="OperatorTemplate.findAll", query="SELECT o FROM OperatorTemplate o")
public class OperatorTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OperatorTemplatePK id;

	private String template;

	@Column(name="template_length")
	private int templateLength;

	@Column(name="template_prefix")
	private String templatePrefix;

	public OperatorTemplate() {
	}

	public OperatorTemplatePK getId() {
		return this.id;
	}

	public void setId(OperatorTemplatePK id) {
		this.id = id;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public int getTemplateLength() {
		return this.templateLength;
	}

	public void setTemplateLength(int templateLength) {
		this.templateLength = templateLength;
	}

	public String getTemplatePrefix() {
		return this.templatePrefix;
	}

	public void setTemplatePrefix(String templatePrefix) {
		this.templatePrefix = templatePrefix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((template == null) ? 0 : template.hashCode());
		result = prime * result + templateLength;
		result = prime * result
				+ ((templatePrefix == null) ? 0 : templatePrefix.hashCode());
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
		OperatorTemplate other = (OperatorTemplate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (template == null) {
			if (other.template != null)
				return false;
		} else if (!template.equals(other.template))
			return false;
		if (templateLength != other.templateLength)
			return false;
		if (templatePrefix == null) {
			if (other.templatePrefix != null)
				return false;
		} else if (!templatePrefix.equals(other.templatePrefix))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperatorTemplate [id=" + id + ", template=" + template
				+ ", templateLength=" + templateLength + ", templatePrefix="
				+ templatePrefix + "]";
	}	

}