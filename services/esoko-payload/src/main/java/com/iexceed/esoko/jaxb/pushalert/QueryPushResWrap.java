//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 03:16:00 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPushResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryPushResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryPushRes" type="{http://www.iexceed.com/queryPush}queryPushRes"/>
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
    "queryPushRes"
})
@XmlRootElement(name = "queryPushResWrap")
public class QueryPushResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryPush", required = true)
    protected QueryPushRes queryPushRes;

    /**
     * Gets the value of the queryPushRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryPushRes }
     *     
     */
    public QueryPushRes getQueryPushRes() {
        return queryPushRes;
    }

    /**
     * Sets the value of the queryPushRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryPushRes }
     *     
     */
    public void setQueryPushRes(QueryPushRes value) {
        this.queryPushRes = value;
    }

}