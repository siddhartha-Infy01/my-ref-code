//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.09 at 07:43:32 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRLOCAPPRV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRLOCAPPRV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationTree" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="markdetDays" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marketPic" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="commodities" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploaddate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadmode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Requestorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRLOCAPPRV", propOrder = {
    "locationId",
    "locationType",
    "locationTree",
    "requestor",
    "latitude",
    "longitude",
    "markdetDays",
    "marketPic",
    "commodities",
    "country",
    "uploaddate",
    "uploadmode",
    "networkId",
    "networkName",
    "requestorname"
})
public class QRLOCAPPRV {

    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String locationId;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String locationType;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String locationTree;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String requestor;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String latitude;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String longitude;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String markdetDays;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected byte[] marketPic;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String commodities;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String country;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String uploaddate;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String uploadmode;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String networkId;
    @XmlElement(namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String networkName;
    @XmlElement(name = "Requestorname", namespace = "http://www.iexceed.com/queryLocationsApprvl", required = true)
    protected String requestorname;

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
     * Gets the value of the locationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Sets the value of the locationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationType(String value) {
        this.locationType = value;
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
     * Gets the value of the requestor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestor() {
        return requestor;
    }

    /**
     * Sets the value of the requestor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestor(String value) {
        this.requestor = value;
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
     * Gets the value of the markdetDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarkdetDays() {
        return markdetDays;
    }

    /**
     * Sets the value of the markdetDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarkdetDays(String value) {
        this.markdetDays = value;
    }

    /**
     * Gets the value of the marketPic property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMarketPic() {
        return marketPic;
    }

    /**
     * Sets the value of the marketPic property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMarketPic(byte[] value) {
        this.marketPic = ((byte[]) value);
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
     * Gets the value of the uploaddate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploaddate() {
        return uploaddate;
    }

    /**
     * Sets the value of the uploaddate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploaddate(String value) {
        this.uploaddate = value;
    }

    /**
     * Gets the value of the uploadmode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadmode() {
        return uploadmode;
    }

    /**
     * Sets the value of the uploadmode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadmode(String value) {
        this.uploadmode = value;
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
     * Gets the value of the requestorname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestorname() {
        return requestorname;
    }

    /**
     * Sets the value of the requestorname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestorname(String value) {
        this.requestorname = value;
    }

}
