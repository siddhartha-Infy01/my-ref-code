//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 06:12:58 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryCmdsChildsCountRes" type="{http://www.iexceed.com/queryCmdsChildsCount}queryCmdsChildsCountRes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryCmdsChildsCountRes"
})
@XmlRootElement(name = "queryCmdsChildsCountResWrap")
public class QueryCmdsChildsCountResWrap {

    @XmlElement(required = true)
    protected QueryCmdsChildsCountRes queryCmdsChildsCountRes;

    /**
     * Gets the value of the queryCmdsChildsCountRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryCmdsChildsCountRes }
     *     
     */
    public QueryCmdsChildsCountRes getQueryCmdsChildsCountRes() {
        return queryCmdsChildsCountRes;
    }

    /**
     * Sets the value of the queryCmdsChildsCountRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryCmdsChildsCountRes }
     *     
     */
    public void setQueryCmdsChildsCountRes(QueryCmdsChildsCountRes value) {
        this.queryCmdsChildsCountRes = value;
    }

}
