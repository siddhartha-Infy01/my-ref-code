//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.14 at 10:26:17 AM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SHAREDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SHAREDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prices" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newsAndLibrary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="people" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SHAREDTLS", propOrder = {
    "networkId",
    "networkName",
    "prices",
    "offers",
    "newsAndLibrary",
    "people"
})
public class SHAREDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryNwkSharingData", required = true)
    protected String networkId;
    @XmlElement(namespace = "http://www.iexceed.com/queryNwkSharingData", required = true)
    protected String networkName;
    @XmlElement(namespace = "http://www.iexceed.com/queryNwkSharingData", required = true)
    protected String prices;
    @XmlElement(namespace = "http://www.iexceed.com/queryNwkSharingData", required = true)
    protected String offers;
    @XmlElement(namespace = "http://www.iexceed.com/queryNwkSharingData", required = true)
    protected String newsAndLibrary;
    @XmlElement(namespace = "http://www.iexceed.com/queryNwkSharingData", required = true)
    protected String people;

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
     * Gets the value of the networkName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Sets the value of the networkName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkName(String value) {
        this.networkName = value;
    }

    /**
     * Gets the value of the prices property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrices() {
        return prices;
    }

    /**
     * Sets the value of the prices property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrices(String value) {
        this.prices = value;
    }

    /**
     * Gets the value of the offers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffers() {
        return offers;
    }

    /**
     * Sets the value of the offers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffers(String value) {
        this.offers = value;
    }

    /**
     * Gets the value of the newsAndLibrary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewsAndLibrary() {
        return newsAndLibrary;
    }

    /**
     * Sets the value of the newsAndLibrary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewsAndLibrary(String value) {
        this.newsAndLibrary = value;
    }

    /**
     * Gets the value of the people property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeople() {
        return people;
    }

    /**
     * Sets the value of the people property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeople(String value) {
        this.people = value;
    }

}
