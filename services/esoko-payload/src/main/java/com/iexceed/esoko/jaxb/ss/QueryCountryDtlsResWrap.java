//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.28 at 08:09:04 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryCountryDtlsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryCountryDtlsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryCountryDtlsRes" type="{http://www.iexceed.com/queryCountryDtls}queryCountryDtlsRes"/>
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
    "queryCountryDtlsRes"
})
@XmlRootElement(name = "queryCountryDtlsResWrap")
public class QueryCountryDtlsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected QueryCountryDtlsRes queryCountryDtlsRes;

    /**
     * Gets the value of the queryCountryDtlsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryCountryDtlsRes }
     *     
     */
    public QueryCountryDtlsRes getQueryCountryDtlsRes() {
        return queryCountryDtlsRes;
    }

    /**
     * Sets the value of the queryCountryDtlsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryCountryDtlsRes }
     *     
     */
    public void setQueryCountryDtlsRes(QueryCountryDtlsRes value) {
        this.queryCountryDtlsRes = value;
    }

}