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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryNwkPermissionsRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryNwkPermissionsRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="QRPRMSDTLS" type="{http://www.iexceed.com/queryNwkPermissions}QRPRMSDTLS"/>
 *         &lt;element name="QRNETWORKS" type="{http://www.iexceed.com/queryNwkPermissions}QRNETWORKS"/>
 *         &lt;element name="CRAPPS" type="{http://www.iexceed.com/queryNwkPermissions}CRAPPS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryNwkPermissionsRes", propOrder = {
    "header",
    "qrprmsdtls",
    "qrnetworks",
    "crapps"
})
public class QueryNwkPermissionsRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryNwkPermissions", required = true)
    protected Header header;
    @XmlElement(name = "QRPRMSDTLS", namespace = "http://www.iexceed.com/queryNwkPermissions", required = true)
    protected QRPRMSDTLS qrprmsdtls;
    @XmlElement(name = "QRNETWORKS", namespace = "http://www.iexceed.com/queryNwkPermissions", required = true)
    protected QRNETWORKS qrnetworks;
    @XmlElement(name = "CRAPPS", namespace = "http://www.iexceed.com/queryNwkPermissions", required = true)
    protected CRAPPS crapps;

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
     * Gets the value of the qrprmsdtls property.
     * 
     * @return
     *     possible object is
     *     {@link QRPRMSDTLS }
     *     
     */
    public QRPRMSDTLS getQRPRMSDTLS() {
        return qrprmsdtls;
    }

    /**
     * Sets the value of the qrprmsdtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link QRPRMSDTLS }
     *     
     */
    public void setQRPRMSDTLS(QRPRMSDTLS value) {
        this.qrprmsdtls = value;
    }

    /**
     * Gets the value of the qrnetworks property.
     * 
     * @return
     *     possible object is
     *     {@link QRNETWORKS }
     *     
     */
    public QRNETWORKS getQRNETWORKS() {
        return qrnetworks;
    }

    /**
     * Sets the value of the qrnetworks property.
     * 
     * @param value
     *     allowed object is
     *     {@link QRNETWORKS }
     *     
     */
    public void setQRNETWORKS(QRNETWORKS value) {
        this.qrnetworks = value;
    }

    /**
     * Gets the value of the crapps property.
     * 
     * @return
     *     possible object is
     *     {@link CRAPPS }
     *     
     */
    public CRAPPS getCRAPPS() {
        return crapps;
    }

    /**
     * Sets the value of the crapps property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRAPPS }
     *     
     */
    public void setCRAPPS(CRAPPS value) {
        this.crapps = value;
    }

}