//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 06:41:20 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRWIDGETDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRWIDGETDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateRange" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incPbl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="widgetId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nwkOffers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRWIDGETDETAILS", propOrder = {
    "networkId",
    "locationId",
    "commodityId",
    "currency",
    "priceType",
    "dateRange",
    "incPbl",
    "widgetId",
    "nwkOffers"
})
public class QRWIDGETDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String networkId;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String locationId;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String commodityId;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String currency;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String priceType;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String dateRange;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String incPbl;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String widgetId;
    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected String nwkOffers;

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
     * Gets the value of the commodityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityId() {
        return commodityId;
    }

    /**
     * Sets the value of the commodityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityId(String value) {
        this.commodityId = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the priceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceType() {
        return priceType;
    }

    /**
     * Sets the value of the priceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceType(String value) {
        this.priceType = value;
    }

    /**
     * Gets the value of the dateRange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateRange() {
        return dateRange;
    }

    /**
     * Sets the value of the dateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateRange(String value) {
        this.dateRange = value;
    }

    /**
     * Gets the value of the incPbl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncPbl() {
        return incPbl;
    }

    /**
     * Sets the value of the incPbl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncPbl(String value) {
        this.incPbl = value;
    }

    /**
     * Gets the value of the widgetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWidgetId() {
        return widgetId;
    }

    /**
     * Sets the value of the widgetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWidgetId(String value) {
        this.widgetId = value;
    }

    /**
     * Gets the value of the nwkOffers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNwkOffers() {
        return nwkOffers;
    }

    /**
     * Sets the value of the nwkOffers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNwkOffers(String value) {
        this.nwkOffers = value;
    }

}
