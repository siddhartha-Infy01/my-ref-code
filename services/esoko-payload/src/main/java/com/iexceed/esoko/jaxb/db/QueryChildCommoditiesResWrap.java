//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.13 at 06:34:50 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryChildCommoditiesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryChildCommoditiesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryChildCommoditiesRes" type="{http://www.iexceed.com/queryCommodities}queryChildCommoditiesRes"/>
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
    "queryChildCommoditiesRes"
})
@XmlRootElement(name = "queryChildCommoditiesResWrap")
public class QueryChildCommoditiesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected QueryChildCommoditiesRes queryChildCommoditiesRes;

    /**
     * Gets the value of the queryChildCommoditiesRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryChildCommoditiesRes }
     *     
     */
    public QueryChildCommoditiesRes getQueryChildCommoditiesRes() {
        return queryChildCommoditiesRes;
    }

    /**
     * Sets the value of the queryChildCommoditiesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryChildCommoditiesRes }
     *     
     */
    public void setQueryChildCommoditiesRes(QueryChildCommoditiesRes value) {
        this.queryChildCommoditiesRes = value;
    }

}
