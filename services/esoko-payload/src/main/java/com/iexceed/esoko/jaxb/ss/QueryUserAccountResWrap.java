//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.13 at 06:39:33 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryUserAccountResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryUserAccountResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryUserAccountRes" type="{http://www.iexceed.com/queryUserAccount}queryUserAccountRes"/>
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
    "queryUserAccountRes"
})
@XmlRootElement(name = "queryUserAccountResWrap")
public class QueryUserAccountResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryUserAccount", required = true)
    protected QueryUserAccountRes queryUserAccountRes;

    /**
     * Gets the value of the queryUserAccountRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryUserAccountRes }
     *     
     */
    public QueryUserAccountRes getQueryUserAccountRes() {
        return queryUserAccountRes;
    }

    /**
     * Sets the value of the queryUserAccountRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryUserAccountRes }
     *     
     */
    public void setQueryUserAccountRes(QueryUserAccountRes value) {
        this.queryUserAccountRes = value;
    }

}
