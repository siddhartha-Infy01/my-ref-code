//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.07 at 07:30:27 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAlertRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryAlertRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="ALERTDTLS" type="{http://www.iexceed.com/queryAlert}ALERTDTLS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryAlertRes", propOrder = {
    "header",
    "alertdtls"
})
public class QueryAlertRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryAlert", required = true)
    protected Header header;
    @XmlElement(name = "ALERTDTLS", namespace = "http://www.iexceed.com/queryAlert", required = true)
    protected ALERTDTLS alertdtls;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the alertdtls property.
     * 
     * @return
     *     possible object is
     *     {@link ALERTDTLS }
     *     
     */
    public ALERTDTLS getALERTDTLS() {
        return alertdtls;
    }

    /**
     * Sets the value of the alertdtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link ALERTDTLS }
     *     
     */
    public void setALERTDTLS(ALERTDTLS value) {
        this.alertdtls = value;
    }

}
