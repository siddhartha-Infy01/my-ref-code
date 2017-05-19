package com.iexceed.esoko.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;


/**
 * The persistent class for the System_invoices database table.
 * 
 */
@Entity
@Table(name="System_invoices")
@NamedQuery(name="System_invoice.findAll", query="SELECT s FROM System_invoice s")
public class System_invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="invoice_no")
	private String invoiceNo;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_ts")
	private Date createdTs;

	private String currency;

	private int dso;

	@Lob
	@Column(name="inv_image")
	private byte[] invImage;

	@Column(name="invoice_amount")
	private BigDecimal invoiceAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="invoice_date")
	private Date invoiceDate;

	@Column(name="invoice_from")
	private String invoiceFrom;

	@Column(name="invoice_to")
	private String invoiceTo;

	@Column(name="modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_ts")
	private Date modifiedTs;

	@Temporal(TemporalType.TIMESTAMP)
	private Date paid_Date;

	private String paid_Status;

	public System_invoice() {
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getDso() {
		return this.dso;
	}

	public void setDso(int dso) {
		this.dso = dso;
	}

	public byte[] getInvImage() {
		return this.invImage;
	}

	public void setInvImage(byte[] invImage) {
		this.invImage = invImage;
	}

	public BigDecimal getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceFrom() {
		return this.invoiceFrom;
	}

	public void setInvoiceFrom(String invoiceFrom) {
		this.invoiceFrom = invoiceFrom;
	}

	public String getInvoiceTo() {
		return this.invoiceTo;
	}

	public void setInvoiceTo(String invoiceTo) {
		this.invoiceTo = invoiceTo;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedTs() {
		return this.modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public Date getPaid_Date() {
		return this.paid_Date;
	}

	public void setPaid_Date(Date paid_Date) {
		this.paid_Date = paid_Date;
	}

	public String getPaid_Status() {
		return this.paid_Status;
	}

	public void setPaid_Status(String paid_Status) {
		this.paid_Status = paid_Status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdTs == null) ? 0 : createdTs.hashCode());
		result = prime * result
				+ ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + dso;
		result = prime * result + Arrays.hashCode(invImage);
		result = prime * result
				+ ((invoiceAmount == null) ? 0 : invoiceAmount.hashCode());
		result = prime * result
				+ ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result
				+ ((invoiceFrom == null) ? 0 : invoiceFrom.hashCode());
		result = prime * result
				+ ((invoiceNo == null) ? 0 : invoiceNo.hashCode());
		result = prime * result
				+ ((invoiceTo == null) ? 0 : invoiceTo.hashCode());
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedTs == null) ? 0 : modifiedTs.hashCode());
		result = prime * result
				+ ((paid_Date == null) ? 0 : paid_Date.hashCode());
		result = prime * result
				+ ((paid_Status == null) ? 0 : paid_Status.hashCode());
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
		System_invoice other = (System_invoice) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdTs == null) {
			if (other.createdTs != null)
				return false;
		} else if (!createdTs.equals(other.createdTs))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (dso != other.dso)
			return false;
		if (!Arrays.equals(invImage, other.invImage))
			return false;
		if (invoiceAmount == null) {
			if (other.invoiceAmount != null)
				return false;
		} else if (!invoiceAmount.equals(other.invoiceAmount))
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		if (invoiceFrom == null) {
			if (other.invoiceFrom != null)
				return false;
		} else if (!invoiceFrom.equals(other.invoiceFrom))
			return false;
		if (invoiceNo == null) {
			if (other.invoiceNo != null)
				return false;
		} else if (!invoiceNo.equals(other.invoiceNo))
			return false;
		if (invoiceTo == null) {
			if (other.invoiceTo != null)
				return false;
		} else if (!invoiceTo.equals(other.invoiceTo))
			return false;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedTs == null) {
			if (other.modifiedTs != null)
				return false;
		} else if (!modifiedTs.equals(other.modifiedTs))
			return false;
		if (paid_Date == null) {
			if (other.paid_Date != null)
				return false;
		} else if (!paid_Date.equals(other.paid_Date))
			return false;
		if (paid_Status == null) {
			if (other.paid_Status != null)
				return false;
		} else if (!paid_Status.equals(other.paid_Status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "System_invoice [invoiceNo=" + invoiceNo + ", createdBy="
				+ createdBy + ", createdTs=" + createdTs + ", currency="
				+ currency + ", dso=" + dso + ", invImage="
				+ Arrays.toString(invImage) + ", invoiceAmount="
				+ invoiceAmount + ", invoiceDate=" + invoiceDate
				+ ", invoiceFrom=" + invoiceFrom + ", invoiceTo=" + invoiceTo
				+ ", modifiedBy=" + modifiedBy + ", modifiedTs=" + modifiedTs
				+ ", paid_Date=" + paid_Date + ", paid_Status=" + paid_Status
				+ "]";
	}

}