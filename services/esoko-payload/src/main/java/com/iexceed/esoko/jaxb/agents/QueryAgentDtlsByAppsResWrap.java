//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.19 at 03:36:19 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAgentDtlsByAppsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryAgentDtlsByAppsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryAgentDtlsByAppsRes" type="{http://www.iexceed.com/queryAgentDtlsByApps}queryAgentDtlsByAppsRes"/>
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
    "queryAgentDtlsByAppsRes"
})
@XmlRootElement(name = "queryAgentDtlsByAppsResWrap")
public class QueryAgentDtlsByAppsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected QueryAgentDtlsByAppsRes queryAgentDtlsByAppsRes;

    /**
     * Gets the value of the queryAgentDtlsByAppsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryAgentDtlsByAppsRes }
     *     
     */
    public QueryAgentDtlsByAppsRes getQueryAgentDtlsByAppsRes() {
        return queryAgentDtlsByAppsRes;
    }

    /**
     * Sets the value of the queryAgentDtlsByAppsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryAgentDtlsByAppsRes }
     *     
     */
    public void setQueryAgentDtlsByAppsRes(QueryAgentDtlsByAppsRes value) {
        this.queryAgentDtlsByAppsRes = value;
    }

}