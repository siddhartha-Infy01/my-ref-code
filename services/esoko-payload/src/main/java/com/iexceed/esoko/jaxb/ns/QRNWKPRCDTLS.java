//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 02:42:51 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRNWKPRCDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRNWKPRCDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="farmGate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offLorry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="producer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="retail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="wholesale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRNWKPRCDTLS", propOrder = {
    "networkId",
    "farmGate",
    "offLorry",
    "producer",
    "retail",
    "wholesale"
})
public class QRNWKPRCDTLS {

    @XmlElement(required = true)
    protected String networkId;
    @XmlElement(required = true)
    protected String farmGate;
    @XmlElement(required = true)
    protected String offLorry;
    @XmlElement(required = true)
    protected String producer;
    @XmlElement(required = true)
    protected String retail;
    @XmlElement(required = true)
    protected String wholesale;

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

    /**
     * Gets the value of the farmGate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFarmGate() {
        return farmGate;
    }

    /**
     * Sets the value of the farmGate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFarmGate(String value) {
        this.farmGate = value;
    }

    /**
     * Gets the value of the offLorry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffLorry() {
        return offLorry;
    }

    /**
     * Sets the value of the offLorry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffLorry(String value) {
        this.offLorry = value;
    }

    /**
     * Gets the value of the producer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Sets the value of the producer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducer(String value) {
        this.producer = value;
    }

    /**
     * Gets the value of the retail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRetail() {
        return retail;
    }

    /**
     * Sets the value of the retail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRetail(String value) {
        this.retail = value;
    }

    /**
     * Gets the value of the wholesale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWholesale() {
        return wholesale;
    }

    /**
     * Sets the value of the wholesale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWholesale(String value) {
        this.wholesale = value;
    }

}