//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.28 at 08:09:10 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryGatewayDtlsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryGatewayDtlsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryGatewayDtlsRes" type="{http://www.iexceed.com/queryGatewayDtls}queryGatewayDtlsRes"/>
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
    "queryGatewayDtlsRes"
})
@XmlRootElement(name = "queryGatewayDtlsResWrap")
public class QueryGatewayDtlsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryGatewayDtls", required = true)
    protected QueryGatewayDtlsRes queryGatewayDtlsRes;

    /**
     * Gets the value of the queryGatewayDtlsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryGatewayDtlsRes }
     *     
     */
    public QueryGatewayDtlsRes getQueryGatewayDtlsRes() {
        return queryGatewayDtlsRes;
    }

    /**
     * Sets the value of the queryGatewayDtlsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryGatewayDtlsRes }
     *     
     */
    public void setQueryGatewayDtlsRes(QueryGatewayDtlsRes value) {
        this.queryGatewayDtlsRes = value;
    }

}