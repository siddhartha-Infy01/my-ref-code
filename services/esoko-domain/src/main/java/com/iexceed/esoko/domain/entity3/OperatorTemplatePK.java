package com.iexceed.esoko.domain.entity3;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the operator_templates database table.
 * 
 */
@Embeddable
public class OperatorTemplatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="template_id")
	private int templateId;

	@Column(name="operator_id")
	private String operatorId;

	public OperatorTemplatePK() {
	}
	public int getTemplateId() {
		return this.templateId;
	}
	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}
	public String getOperatorId() {
		return this.operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OperatorTemplatePK)) {
			return false;
		}
		OperatorTemplatePK castOther = (OperatorTemplatePK)other;
		return 
			(this.templateId == castOther.templateId)
			&& this.operatorId.equals(castOther.operatorId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.templateId;
		hash = hash * prime + this.operatorId.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "OperatorTemplatePK [templateId=" + templateId + ", operatorId="
				+ operatorId + "]";
	}
		
}