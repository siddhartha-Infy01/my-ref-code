//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.03 at 12:29:28 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryNwkPermissionsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryNwkPermissionsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryNwkPermissionsRes" type="{http://www.iexceed.com/queryNwkPermissions}queryNwkPermissionsRes"/>
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
    "queryNwkPermissionsRes"
})
@XmlRootElement(name = "queryNwkPermissionsResWrap")
public class QueryNwkPermissionsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryNwkPermissions", required = true)
    protected QueryNwkPermissionsRes queryNwkPermissionsRes;

    /**
     * Gets the value of the queryNwkPermissionsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryNwkPermissionsRes }
     *     
     */
    public QueryNwkPermissionsRes getQueryNwkPermissionsRes() {
        return queryNwkPermissionsRes;
    }

    /**
     * Sets the value of the queryNwkPermissionsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryNwkPermissionsRes }
     *     
     */
    public void setQueryNwkPermissionsRes(QueryNwkPermissionsRes value) {
        this.queryNwkPermissionsRes = value;
    }

}
