//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.12 at 12:16:37 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryProfileDetailsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryProfileDetailsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryProfileDetailsRes" type="{http://www.iexceed.com/queryProfileDetails}queryProfileDetailsRes"/>
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
    "queryProfileDetailsRes"
})
@XmlRootElement(name = "queryProfileDetailsResWrap")
public class QueryProfileDetailsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryProfileDetails", required = true)
    protected QueryProfileDetailsRes queryProfileDetailsRes;

    /**
     * Gets the value of the queryProfileDetailsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryProfileDetailsRes }
     *     
     */
    public QueryProfileDetailsRes getQueryProfileDetailsRes() {
        return queryProfileDetailsRes;
    }

    /**
     * Sets the value of the queryProfileDetailsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryProfileDetailsRes }
     *     
     */
    public void setQueryProfileDetailsRes(QueryProfileDetailsRes value) {
        this.queryProfileDetailsRes = value;
    }

}
