//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 03:54:46 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPushDeliveryResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryPushDeliveryResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryPushDeliveryRes" type="{http://www.iexceed.com/queryPushDelivery}queryPushDeliveryRes"/>
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
    "queryPushDeliveryRes"
})
@XmlRootElement(name = "queryPushDeliveryResWrap")
public class QueryPushDeliveryResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryPushDelivery", required = true)
    protected QueryPushDeliveryRes queryPushDeliveryRes;

    /**
     * Gets the value of the queryPushDeliveryRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryPushDeliveryRes }
     *     
     */
    public QueryPushDeliveryRes getQueryPushDeliveryRes() {
        return queryPushDeliveryRes;
    }

    /**
     * Sets the value of the queryPushDeliveryRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryPushDeliveryRes }
     *     
     */
    public void setQueryPushDeliveryRes(QueryPushDeliveryRes value) {
        this.queryPushDeliveryRes = value;
    }

}
