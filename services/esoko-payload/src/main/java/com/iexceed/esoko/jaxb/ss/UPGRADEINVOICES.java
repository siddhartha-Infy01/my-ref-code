//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.27 at 02:09:59 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPGRADEINVOICES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPGRADEINVOICES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reseller_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="network_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="network_balance" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscription_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscriber_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="owner_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reseller_cost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="margin_earned" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vat" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start_date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expiry_date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dso" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="INVOICEAPPS" type="{http://www.iexceed.com/subscripInvoiceUpgrade}INVOICEAPPS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPGRADEINVOICES", propOrder = {
    "resellerId",
    "networkId",
    "networkBalance",
    "subscriptionType",
    "subscriberName",
    "ownerName",
    "period",
    "resellerCost",
    "marginEarned",
    "total",
    "currency",
    "discount",
    "vat",
    "startDate",
    "expiryDate",
    "dso",
    "invoiceapps"
})
public class UPGRADEINVOICES {

    @XmlElement(name = "reseller_id", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String resellerId;
    @XmlElement(name = "network_id", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String networkId;
    @XmlElement(name = "network_balance", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String networkBalance;
    @XmlElement(name = "subscription_type", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String subscriptionType;
    @XmlElement(name = "subscriber_name", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String subscriberName;
    @XmlElement(name = "owner_name", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String ownerName;
    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade")
    protected int period;
    @XmlElement(name = "reseller_cost", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String resellerCost;
    @XmlElement(name = "margin_earned", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String marginEarned;
    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String total;
    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String currency;
    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String discount;
    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String vat;
    @XmlElement(name = "start_date", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String startDate;
    @XmlElement(name = "expiry_date", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected String expiryDate;
    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade")
    protected int dso;
    @XmlElement(name = "INVOICEAPPS", namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected List<INVOICEAPPS> invoiceapps;

    /**
     * Gets the value of the resellerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResellerId() {
        return resellerId;
    }

    /**
     * Sets the value of the resellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResellerId(String value) {
        this.resellerId = value;
    }

    /**
     * Gets the value of the networkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the value of the networkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkId(String value) {
        this.networkId = value;
    }

    /**
     * Gets the value of the networkBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkBalance() {
        return networkBalance;
    }

    /**
     * Sets the value of the networkBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkBalance(String value) {
        this.networkBalance = value;
    }

    /**
     * Gets the value of the subscriptionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Sets the value of the subscriptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionType(String value) {
        this.subscriptionType = value;
    }

    /**
     * Gets the value of the subscriberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberName() {
        return subscriberName;
    }

    /**
     * Sets the value of the subscriberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberName(String value) {
        this.subscriberName = value;
    }

    /**
     * Gets the value of the ownerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Sets the value of the ownerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerName(String value) {
        this.ownerName = value;
    }

    /**
     * Gets the value of the period property.
     * 
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     */
    public void setPeriod(int value) {
        this.period = value;
    }

    /**
     * Gets the value of the resellerCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResellerCost() {
        return resellerCost;
    }

    /**
     * Sets the value of the resellerCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResellerCost(String value) {
        this.resellerCost = value;
    }

    /**
     * Gets the value of the marginEarned property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarginEarned() {
        return marginEarned;
    }

    /**
     * Sets the value of the marginEarned property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarginEarned(String value) {
        this.marginEarned = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscount(String value) {
        this.discount = value;
    }

    /**
     * Gets the value of the vat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVat() {
        return vat;
    }

    /**
     * Sets the value of the vat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVat(String value) {
        this.vat = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryDate(String value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the dso property.
     * 
     */
    public int getDso() {
        return dso;
    }

    /**
     * Sets the value of the dso property.
     * 
     */
    public void setDso(int value) {
        this.dso = value;
    }

    /**
     * Gets the value of the invoiceapps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceapps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINVOICEAPPS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link INVOICEAPPS }
     * 
     * 
     */
    public List<INVOICEAPPS> getINVOICEAPPS() {
        if (invoiceapps == null) {
            invoiceapps = new ArrayList<INVOICEAPPS>();
        }
        return this.invoiceapps;
    }

}