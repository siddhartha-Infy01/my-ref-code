//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.08 at 07:07:32 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DLRSLRDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DLRSLRDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reseller_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="network_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DLRSLRDTLS", propOrder = {
    "resellerId",
    "networkId"
})
public class DLRSLRDTLS {

    @XmlElement(name = "reseller_id", namespace = "http://www.iexceed.com/deleteResellerDtls", required = true)
    protected String resellerId;
    @XmlElement(name = "network_id", namespace = "http://www.iexceed.com/deleteResellerDtls", required = true)
    protected String networkId;

    /**
     * Gets the value of the resellerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResellerId() {
        return resellerId;
    }

    /**
     * Sets the value of the resellerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResellerId(String value) {
        this.resellerId = value;
    }

    /**
     * Gets the value of the networkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the value of the networkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkId(String value) {
        this.networkId = value;
    }

}
