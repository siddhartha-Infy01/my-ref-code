//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.24 at 03:46:34 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryUserLimitDetailsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryUserLimitDetailsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryUserLimitDetailsRes" type="{http://www.iexceed.com/queryUserLimitDetails}queryUserLimitDetailsRes"/>
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
    "queryUserLimitDetailsRes"
})
@XmlRootElement(name = "queryUserLimitDetailsResWrap")
public class QueryUserLimitDetailsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryUserLimitDetails", required = true)
    protected QueryUserLimitDetailsRes queryUserLimitDetailsRes;

    /**
     * Gets the value of the queryUserLimitDetailsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryUserLimitDetailsRes }
     *     
     */
    public QueryUserLimitDetailsRes getQueryUserLimitDetailsRes() {
        return queryUserLimitDetailsRes;
    }

    /**
     * Sets the value of the queryUserLimitDetailsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryUserLimitDetailsRes }
     *     
     */
    public void setQueryUserLimitDetailsRes(QueryUserLimitDetailsRes value) {
        this.queryUserLimitDetailsRes = value;
    }

}
