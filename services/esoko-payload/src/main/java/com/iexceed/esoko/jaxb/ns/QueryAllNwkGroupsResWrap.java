//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.05 at 12:38:41 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAllNwkGroupsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryAllNwkGroupsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryAllNwkGroupsRes" type="{http://www.iexceed.com/queryAllNwkGroups}queryAllNwkGroupsRes"/>
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
    "queryAllNwkGroupsRes"
})
@XmlRootElement(name = "queryAllNwkGroupsResWrap")
public class QueryAllNwkGroupsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryAllNwkGroups", required = true)
    protected QueryAllNwkGroupsRes queryAllNwkGroupsRes;

    /**
     * Gets the value of the queryAllNwkGroupsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryAllNwkGroupsRes }
     *     
     */
    public QueryAllNwkGroupsRes getQueryAllNwkGroupsRes() {
        return queryAllNwkGroupsRes;
    }

    /**
     * Sets the value of the queryAllNwkGroupsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryAllNwkGroupsRes }
     *     
     */
    public void setQueryAllNwkGroupsRes(QueryAllNwkGroupsRes value) {
        this.queryAllNwkGroupsRes = value;
    }

}