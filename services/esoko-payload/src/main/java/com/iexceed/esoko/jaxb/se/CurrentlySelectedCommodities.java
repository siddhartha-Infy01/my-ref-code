//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.20 at 03:36:20 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for currentlySelectedCommodities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="currentlySelectedCommodities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="CRSELCOM" type="{http://www.iexceed.com/currentlySelectedCommodities}CRSELCOM"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "currentlySelectedCommodities", propOrder = {
    "header",
    "crselcom"
})
public class CurrentlySelectedCommodities {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/currentlySelectedCommodities", required = true)
    protected Header header;
    @XmlElement(name = "CRSELCOM", namespace = "http://www.iexceed.com/currentlySelectedCommodities", required = true)
    protected CRSELCOM crselcom;

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
     * Gets the value of the crselcom property.
     * 
     * @return
     *     possible object is
     *     {@link CRSELCOM }
     *     
     */
    public CRSELCOM getCRSELCOM() {
        return crselcom;
    }

    /**
     * Sets the value of the crselcom property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRSELCOM }
     *     
     */
    public void setCRSELCOM(CRSELCOM value) {
        this.crselcom = value;
    }

}
