//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 11:35:11 AM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryfxRatesReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryfxRatesReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryfxRatesReq" type="{http://www.iexceed.com/queryfxRates}queryfxRatesReq"/>
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
    "queryfxRatesReq"
})
@XmlRootElement(name = "queryfxRatesReqWrap")
public class QueryfxRatesReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryfxRates", required = true)
    protected QueryfxRatesReq queryfxRatesReq;

    /**
     * Gets the value of the queryfxRatesReq property.
     * 
     * @return
     *     possible object is
     *     {@link QueryfxRatesReq }
     *     
     */
    public QueryfxRatesReq getQueryfxRatesReq() {
        return queryfxRatesReq;
    }

    /**
     * Sets the value of the queryfxRatesReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryfxRatesReq }
     *     
     */
    public void setQueryfxRatesReq(QueryfxRatesReq value) {
        this.queryfxRatesReq = value;
    }

}