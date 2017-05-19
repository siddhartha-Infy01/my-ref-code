//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.25 at 06:19:33 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EDITLOCATION complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EDITLOCATION">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location_tree" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="market_desk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodities" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EDITLOCATION", propOrder = {
    "locationId",
    "type",
    "locationTree",
    "latitude",
    "longitude",
    "marketDesk",
    "commodities",
    "image"
})
public class EDITLOCATION {

    @XmlElement(name = "location_id", namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String locationId;
    @XmlElement(namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String type;
    @XmlElement(name = "location_tree", namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String locationTree;
    @XmlElement(namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String latitude;
    @XmlElement(namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String longitude;
    @XmlElement(name = "market_desk", namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String marketDesk;
    @XmlElement(namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected String commodities;
    @XmlElement(namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected byte[] image;

    /**
     * Gets the value of the locationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationId(String value) {
        this.locationId = value;
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
     * Gets the value of the locationTree property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationTree() {
        return locationTree;
    }

    /**
     * Sets the value of the locationTree property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationTree(String value) {
        this.locationTree = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the marketDesk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketDesk() {
        return marketDesk;
    }

    /**
     * Sets the value of the marketDesk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketDesk(String value) {
        this.marketDesk = value;
    }

    /**
     * Gets the value of the commodities property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodities() {
        return commodities;
    }

    /**
     * Sets the value of the commodities property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodities(String value) {
        this.commodities = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = ((byte[]) value);
    }

}