//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 11:56:12 AM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryNwkBillingSummaryRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryNwkBillingSummaryRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="USRBILDTLS" type="{http://www.iexceed.com/queryNwkBillingSummary}USRBILDTLS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryNwkBillingSummaryRes", propOrder = {
    "header",
    "usrbildtls"
})
public class QueryNwkBillingSummaryRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryNwkBillingSummary", required = true)
    protected Header header;
    @XmlElement(name = "USRBILDTLS", namespace = "http://www.iexceed.com/queryNwkBillingSummary", required = true)
    protected USRBILDTLS usrbildtls;

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
     * Gets the value of the usrbildtls property.
     * 
     * @return
     *     possible object is
     *     {@link USRBILDTLS }
     *     
     */
    public USRBILDTLS getUSRBILDTLS() {
        return usrbildtls;
    }

    /**
     * Sets the value of the usrbildtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link USRBILDTLS }
     *     
     */
    public void setUSRBILDTLS(USRBILDTLS value) {
        this.usrbildtls = value;
    }

}
