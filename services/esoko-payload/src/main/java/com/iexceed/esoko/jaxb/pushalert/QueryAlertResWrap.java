//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.07 at 07:30:27 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAlertResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryAlertResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryAlertRes" type="{http://www.iexceed.com/queryAlert}queryAlertRes"/>
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
    "queryAlertRes"
})
@XmlRootElement(name = "queryAlertResWrap")
public class QueryAlertResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryAlert", required = true)
    protected QueryAlertRes queryAlertRes;

    /**
     * Gets the value of the queryAlertRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryAlertRes }
     *     
     */
    public QueryAlertRes getQueryAlertRes() {
        return queryAlertRes;
    }

    /**
     * Sets the value of the queryAlertRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryAlertRes }
     *     
     */
    public void setQueryAlertRes(QueryAlertRes value) {
        this.queryAlertRes = value;
    }

}
