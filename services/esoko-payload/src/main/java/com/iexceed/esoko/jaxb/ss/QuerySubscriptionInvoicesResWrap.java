//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.16 at 12:33:16 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for querySubscriptionInvoicesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="querySubscriptionInvoicesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="querySubscriptionInvoicesRes" type="{http://www.iexceed.com/querySubscriptionInvoices}querySubscriptionInvoicesRes"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "querySubscriptionInvoicesRes"
})
@XmlRootElement(name = "querySubscriptionInvoicesResWrap")
public class QuerySubscriptionInvoicesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/querySubscriptionInvoices", required = true)
    protected QuerySubscriptionInvoicesRes querySubscriptionInvoicesRes;

    /**
     * Gets the value of the querySubscriptionInvoicesRes property.
     * 
     * @return
     *     possible object is
     *     {@link QuerySubscriptionInvoicesRes }
     *     
     */
    public QuerySubscriptionInvoicesRes getQuerySubscriptionInvoicesRes() {
        return querySubscriptionInvoicesRes;
    }

    /**
     * Sets the value of the querySubscriptionInvoicesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuerySubscriptionInvoicesRes }
     *     
     */
    public void setQuerySubscriptionInvoicesRes(QuerySubscriptionInvoicesRes value) {
        this.querySubscriptionInvoicesRes = value;
    }

}
