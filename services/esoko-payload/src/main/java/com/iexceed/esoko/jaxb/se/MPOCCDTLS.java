//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 03:28:40 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MPOCCDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MPOCCDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="occupationID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mapType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mapValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MPOCCDTLS", propOrder = {
    "occupationID",
    "name",
    "description",
    "mapType",
    "mapValue",
    "networkId"
})
public class MPOCCDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/OccupationMap", required = true)
    protected String occupationID;
    @XmlElement(namespace = "http://www.iexceed.com/OccupationMap", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.iexceed.com/OccupationMap", required = true)
    protected String description;
    @XmlElement(namespace = "http://www.iexceed.com/OccupationMap", required = true)
    protected String mapType;
    @XmlElement(namespace = "http://www.iexceed.com/OccupationMap", required = true)
    protected String mapValue;
    @XmlElement(namespace = "http://www.iexceed.com/OccupationMap", required = true)
    protected String networkId;

    /**
     * Gets the value of the occupationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupationID() {
        return occupationID;
    }

    /**
     * Sets the value of the occupationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupationID(String value) {
        this.occupationID = value;
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
     * Gets the value of the mapType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapType() {
        return mapType;
    }

    /**
     * Sets the value of the mapType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapType(String value) {
        this.mapType = value;
    }

    /**
     * Gets the value of the mapValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapValue() {
        return mapValue;
    }

    /**
     * Sets the value of the mapValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapValue(String value) {
        this.mapValue = value;
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
