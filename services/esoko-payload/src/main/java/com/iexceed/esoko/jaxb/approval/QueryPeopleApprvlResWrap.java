//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.09 at 09:42:59 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPeopleApprvlResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryPeopleApprvlResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryPeopleApprvlRes" type="{http://www.iexceed.com/queryPeopleApprvl}queryPeopleApprvlRes"/>
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
    "queryPeopleApprvlRes"
})
@XmlRootElement(name = "queryPeopleApprvlResWrap")
public class QueryPeopleApprvlResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryPeopleApprvl", required = true)
    protected QueryPeopleApprvlRes queryPeopleApprvlRes;

    /**
     * Gets the value of the queryPeopleApprvlRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryPeopleApprvlRes }
     *     
     */
    public QueryPeopleApprvlRes getQueryPeopleApprvlRes() {
        return queryPeopleApprvlRes;
    }

    /**
     * Sets the value of the queryPeopleApprvlRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryPeopleApprvlRes }
     *     
     */
    public void setQueryPeopleApprvlRes(QueryPeopleApprvlRes value) {
        this.queryPeopleApprvlRes = value;
    }

}
