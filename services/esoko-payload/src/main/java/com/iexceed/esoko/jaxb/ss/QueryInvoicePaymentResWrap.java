//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.31 at 05:15:15 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryInvoicePaymentResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryInvoicePaymentResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryInvoicePaymentRes" type="{http://www.iexceed.com/queryInvoicePayment}queryInvoicePaymentRes"/>
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
    "queryInvoicePaymentRes"
})
@XmlRootElement(name = "queryInvoicePaymentResWrap")
public class QueryInvoicePaymentResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryInvoicePayment", required = true)
    protected QueryInvoicePaymentRes queryInvoicePaymentRes;

    /**
     * Gets the value of the queryInvoicePaymentRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryInvoicePaymentRes }
     *     
     */
    public QueryInvoicePaymentRes getQueryInvoicePaymentRes() {
        return queryInvoicePaymentRes;
    }

    /**
     * Sets the value of the queryInvoicePaymentRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryInvoicePaymentRes }
     *     
     */
    public void setQueryInvoicePaymentRes(QueryInvoicePaymentRes value) {
        this.queryInvoicePaymentRes = value;
    }

}