//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.04 at 03:15:01 PM IST 
//


package com.iexceed.esoko.jaxb.ms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryUserNetworkDetailsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryUserNetworkDetailsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryUserNetworkDetailsRes" type="{http://www.iexceed.com/queryUserNetworkDetails}queryUserNetworkDetailsRes"/>
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
    "queryUserNetworkDetailsRes"
})
@XmlRootElement(name = "queryUserNetworkDetailsResWrap")
public class QueryUserNetworkDetailsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryUserNetworkDetails", required = true)
    protected QueryUserNetworkDetailsRes queryUserNetworkDetailsRes;

    /**
     * Gets the value of the queryUserNetworkDetailsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryUserNetworkDetailsRes }
     *     
     */
    public QueryUserNetworkDetailsRes getQueryUserNetworkDetailsRes() {
        return queryUserNetworkDetailsRes;
    }

    /**
     * Sets the value of the queryUserNetworkDetailsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryUserNetworkDetailsRes }
     *     
     */
    public void setQueryUserNetworkDetailsRes(QueryUserNetworkDetailsRes value) {
        this.queryUserNetworkDetailsRes = value;
    }

}
