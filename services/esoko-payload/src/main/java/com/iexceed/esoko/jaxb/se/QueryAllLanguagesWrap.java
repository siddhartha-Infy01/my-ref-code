//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.10 at 11:08:15 AM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryAllLanguages" type="{http://www.iexceed.com/queryTowns}queryAllLanguages"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryAllLanguages"
})
@XmlRootElement(name = "queryAllLanguagesWrap")
public class QueryAllLanguagesWrap {

    @XmlElement(required = true)
    protected QueryAllLanguages queryAllLanguages;

    /**
     * Gets the value of the queryAllLanguages property.
     * 
     * @return
     *     possible object is
     *     {@link QueryAllLanguages }
     *     
     */
    public QueryAllLanguages getQueryAllLanguages() {
        return queryAllLanguages;
    }

    /**
     * Sets the value of the queryAllLanguages property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryAllLanguages }
     *     
     */
    public void setQueryAllLanguages(QueryAllLanguages value) {
        this.queryAllLanguages = value;
    }

}
