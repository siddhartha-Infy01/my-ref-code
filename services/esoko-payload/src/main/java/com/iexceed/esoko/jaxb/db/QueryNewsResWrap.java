//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.25 at 12:39:13 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryNewsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryNewsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryNewsRes" type="{http://www.iexceed.com/queryNews}queryNewsRes"/>
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
    "queryNewsRes"
})
@XmlRootElement(name = "queryNewsResWrap")
public class QueryNewsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryNews", required = true)
    protected QueryNewsRes queryNewsRes;

    /**
     * Gets the value of the queryNewsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryNewsRes }
     *     
     */
    public QueryNewsRes getQueryNewsRes() {
        return queryNewsRes;
    }

    /**
     * Sets the value of the queryNewsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryNewsRes }
     *     
     */
    public void setQueryNewsRes(QueryNewsRes value) {
        this.queryNewsRes = value;
    }

}
