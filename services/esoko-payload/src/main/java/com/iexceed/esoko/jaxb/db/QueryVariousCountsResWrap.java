//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 03:56:55 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryVariousCountsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryVariousCountsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryVariousCountsRes" type="{http://www.iexceed.com/queryVariousCounts}queryVariousCountsRes"/>
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
    "queryVariousCountsRes"
})
@XmlRootElement(name = "queryVariousCountsResWrap")
public class QueryVariousCountsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryVariousCounts", required = true)
    protected QueryVariousCountsRes queryVariousCountsRes;

    /**
     * Gets the value of the queryVariousCountsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryVariousCountsRes }
     *     
     */
    public QueryVariousCountsRes getQueryVariousCountsRes() {
        return queryVariousCountsRes;
    }

    /**
     * Sets the value of the queryVariousCountsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryVariousCountsRes }
     *     
     */
    public void setQueryVariousCountsRes(QueryVariousCountsRes value) {
        this.queryVariousCountsRes = value;
    }

}