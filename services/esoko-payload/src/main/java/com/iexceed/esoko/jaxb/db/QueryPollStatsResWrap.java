//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.24 at 03:46:04 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPollStatsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryPollStatsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryPollStatsRes" type="{http://www.iexceed.com/queryPollStats}queryPollStatsRes"/>
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
    "queryPollStatsRes"
})
@XmlRootElement(name = "queryPollStatsResWrap")
public class QueryPollStatsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryPollStats", required = true)
    protected QueryPollStatsRes queryPollStatsRes;

    /**
     * Gets the value of the queryPollStatsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryPollStatsRes }
     *     
     */
    public QueryPollStatsRes getQueryPollStatsRes() {
        return queryPollStatsRes;
    }

    /**
     * Sets the value of the queryPollStatsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryPollStatsRes }
     *     
     */
    public void setQueryPollStatsRes(QueryPollStatsRes value) {
        this.queryPollStatsRes = value;
    }

}
