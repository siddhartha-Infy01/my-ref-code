//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.02 at 12:37:14 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAllUnAuthCommoditiesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryAllUnAuthCommoditiesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryAllUnAuthCommoditiesRes" type="{http://www.iexceed.com/queryAllUnAuthCommodities}queryAllUnAuthCommoditiesRes"/>
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
    "queryAllUnAuthCommoditiesRes"
})
@XmlRootElement(name = "queryAllUnAuthCommoditiesResWrap")
public class QueryAllUnAuthCommoditiesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryAllUnAuthCommodities", required = true)
    protected QueryAllUnAuthCommoditiesRes queryAllUnAuthCommoditiesRes;

    /**
     * Gets the value of the queryAllUnAuthCommoditiesRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryAllUnAuthCommoditiesRes }
     *     
     */
    public QueryAllUnAuthCommoditiesRes getQueryAllUnAuthCommoditiesRes() {
        return queryAllUnAuthCommoditiesRes;
    }

    /**
     * Sets the value of the queryAllUnAuthCommoditiesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryAllUnAuthCommoditiesRes }
     *     
     */
    public void setQueryAllUnAuthCommoditiesRes(QueryAllUnAuthCommoditiesRes value) {
        this.queryAllUnAuthCommoditiesRes = value;
    }

}