//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.29 at 11:54:29 AM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRNWKDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRNWKDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkOwner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRNWKDETAILS", propOrder = {
    "networkID",
    "type",
    "name",
    "description",
    "country",
    "website",
    "networkOwner"
})
public class CRNWKDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String networkID;
    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String type;
    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String description;
    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String country;
    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String website;
    @XmlElement(namespace = "http://www.iexceed.com/createNetwork", required = true)
    protected String networkOwner;

    /**
     * Gets the value of the networkID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkID() {
        return networkID;
    }

    /**
     * Sets the value of the networkID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkID(String value) {
        this.networkID = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
    }

    /**
     * Gets the value of the networkOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkOwner() {
        return networkOwner;
    }

    /**
     * Sets the value of the networkOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkOwner(String value) {
        this.networkOwner = value;
    }

}