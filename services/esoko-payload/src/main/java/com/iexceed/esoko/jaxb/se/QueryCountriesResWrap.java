//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 02:25:48 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryCountriesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryCountriesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryCountriesRes" type="{http://www.iexceed.com/queryCountries}queryCountriesRes"/>
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
    "queryCountriesRes"
})
@XmlRootElement(name = "queryCountriesResWrap")
public class QueryCountriesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryCountries", required = true)
    protected QueryCountriesRes queryCountriesRes;

    /**
     * Gets the value of the queryCountriesRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryCountriesRes }
     *     
     */
    public QueryCountriesRes getQueryCountriesRes() {
        return queryCountriesRes;
    }

    /**
     * Sets the value of the queryCountriesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryCountriesRes }
     *     
     */
    public void setQueryCountriesRes(QueryCountriesRes value) {
        this.queryCountriesRes = value;
    }

}