//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.23 at 12:12:49 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CROFFERDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CROFFERDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offerType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="variety" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="origin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="measure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="negotiable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="priceCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validTill" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offerBelongsTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userNameOrMobNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliveryPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliverBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliverDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="documents" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grade" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CROFFERDETAILS", propOrder = {
    "networkId",
    "agentId",
    "offerType",
    "commodity",
    "variety",
    "type",
    "origin",
    "quantity",
    "measure",
    "negotiable",
    "price",
    "priceCurrency",
    "unit",
    "validTill",
    "offerBelongsTo",
    "userNameOrMobNo",
    "paymentDetails",
    "deliveryPoint",
    "deliverBy",
    "deliverDays",
    "documents",
    "grade",
    "notes",
    "mode",
    "location"
})
public class CROFFERDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String networkId;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String agentId;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String offerType;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String commodity;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String variety;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String type;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String origin;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers")
    protected double quantity;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String measure;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String negotiable;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers")
    protected Double price;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers")
    protected String priceCurrency;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers")
    protected String unit;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String validTill;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String offerBelongsTo;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers")
    protected String userNameOrMobNo;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String paymentDetails;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String deliveryPoint;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String deliverBy;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers")
    protected int deliverDays;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String documents;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String grade;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String notes;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String mode;
    @XmlElement(namespace = "http://www.iexceed.com/createOffers", required = true)
    protected String location;

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
     * Gets the value of the agentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * Sets the value of the agentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentId(String value) {
        this.agentId = value;
    }

    /**
     * Gets the value of the offerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferType() {
        return offerType;
    }

    /**
     * Sets the value of the offerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferType(String value) {
        this.offerType = value;
    }

    /**
     * Gets the value of the commodity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodity() {
        return commodity;
    }

    /**
     * Sets the value of the commodity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodity(String value) {
        this.commodity = value;
    }

    /**
     * Gets the value of the variety property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVariety() {
        return variety;
    }

    /**
     * Sets the value of the variety property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVariety(String value) {
        this.variety = value;
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
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigin(String value) {
        this.origin = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(double value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the measure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * Sets the value of the measure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasure(String value) {
        this.measure = value;
    }

    /**
     * Gets the value of the negotiable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNegotiable() {
        return negotiable;
    }

    /**
     * Sets the value of the negotiable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNegotiable(String value) {
        this.negotiable = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrice(Double value) {
        this.price = value;
    }

    /**
     * Gets the value of the priceCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceCurrency() {
        return priceCurrency;
    }

    /**
     * Sets the value of the priceCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceCurrency(String value) {
        this.priceCurrency = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the validTill property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidTill() {
        return validTill;
    }

    /**
     * Sets the value of the validTill property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidTill(String value) {
        this.validTill = value;
    }

    /**
     * Gets the value of the offerBelongsTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferBelongsTo() {
        return offerBelongsTo;
    }

    /**
     * Sets the value of the offerBelongsTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferBelongsTo(String value) {
        this.offerBelongsTo = value;
    }

    /**
     * Gets the value of the userNameOrMobNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserNameOrMobNo() {
        return userNameOrMobNo;
    }

    /**
     * Sets the value of the userNameOrMobNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserNameOrMobNo(String value) {
        this.userNameOrMobNo = value;
    }

    /**
     * Gets the value of the paymentDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentDetails() {
        return paymentDetails;
    }

    /**
     * Sets the value of the paymentDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentDetails(String value) {
        this.paymentDetails = value;
    }

    /**
     * Gets the value of the deliveryPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryPoint() {
        return deliveryPoint;
    }

    /**
     * Sets the value of the deliveryPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryPoint(String value) {
        this.deliveryPoint = value;
    }

    /**
     * Gets the value of the deliverBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliverBy() {
        return deliverBy;
    }

    /**
     * Sets the value of the deliverBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliverBy(String value) {
        this.deliverBy = value;
    }

    /**
     * Gets the value of the deliverDays property.
     * 
     */
    public int getDeliverDays() {
        return deliverDays;
    }

    /**
     * Sets the value of the deliverDays property.
     * 
     */
    public void setDeliverDays(int value) {
        this.deliverDays = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocuments(String value) {
        this.documents = value;
    }

    /**
     * Gets the value of the grade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the value of the grade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrade(String value) {
        this.grade = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

}
