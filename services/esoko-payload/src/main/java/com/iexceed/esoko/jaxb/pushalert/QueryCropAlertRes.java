//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 11:39:07 AM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryCropAlertRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryCropAlertRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="CROPTIP" type="{http://www.iexceed.com/queryCropAlert}CROPTIP"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryCropAlertRes", propOrder = {
    "header",
    "croptip"
})
public class QueryCropAlertRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryCropAlert", required = true)
    protected Header header;
    @XmlElement(name = "CROPTIP", namespace = "http://www.iexceed.com/queryCropAlert", required = true)
    protected CROPTIP croptip;

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
     * Gets the value of the croptip property.
     * 
     * @return
     *     possible object is
     *     {@link CROPTIP }
     *     
     */
    public CROPTIP getCROPTIP() {
        return croptip;
    }

    /**
     * Sets the value of the croptip property.
     * 
     * @param value
     *     allowed object is
     *     {@link CROPTIP }
     *     
     */
    public void setCROPTIP(CROPTIP value) {
        this.croptip = value;
    }

}
