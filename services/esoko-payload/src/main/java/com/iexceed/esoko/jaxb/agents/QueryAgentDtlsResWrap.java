//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.19 at 12:53:52 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAgentDtlsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryAgentDtlsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryAgentDtlsRes" type="{http://www.iexceed.com/queryAgentDtls}queryAgentDtlsRes"/>
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
    "queryAgentDtlsRes"
})
@XmlRootElement(name = "queryAgentDtlsResWrap")
public class QueryAgentDtlsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtls", required = true)
    protected QueryAgentDtlsRes queryAgentDtlsRes;

    /**
     * Gets the value of the queryAgentDtlsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryAgentDtlsRes }
     *     
     */
    public QueryAgentDtlsRes getQueryAgentDtlsRes() {
        return queryAgentDtlsRes;
    }

    /**
     * Sets the value of the queryAgentDtlsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryAgentDtlsRes }
     *     
     */
    public void setQueryAgentDtlsRes(QueryAgentDtlsRes value) {
        this.queryAgentDtlsRes = value;
    }

}