//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.02 at 07:38:16 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRBIDSOFFRSAPPRV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRBIDSOFFRSAPPRV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uploadId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="uploadedUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ownerUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expiresBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeliveryPoint" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DeliveryBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="grade" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRBIDSOFFRSAPPRV", propOrder = {
    "uploadId",
    "commodity",
    "quantity",
    "price",
    "user",
    "uploadedUserId",
    "ownerUserId",
    "mode",
    "date",
    "expiresBy",
    "location",
    "paymentDetails",
    "deliveryPoint",
    "deliveryBy",
    "grade"
})
public class QRBIDSOFFRSAPPRV {

    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String uploadId;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String commodity;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String quantity;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String price;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String user;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String uploadedUserId;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String ownerUserId;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String mode;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String date;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String expiresBy;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String location;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String paymentDetails;
    @XmlElement(name = "DeliveryPoint", namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String deliveryPoint;
    @XmlElement(name = "DeliveryBy", namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String deliveryBy;
    @XmlElement(namespace = "http://www.iexceed.com/queryBidsOfferApprvl", required = true)
    protected String grade;

    /**
     * Gets the value of the uploadId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadId() {
        return uploadId;
    }

    /**
     * Sets the value of the uploadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadId(String value) {
        this.uploadId = value;
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
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrice(String value) {
        this.price = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the uploadedUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUploadedUserId() {
        return uploadedUserId;
    }

    /**
     * Sets the value of the uploadedUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUploadedUserId(String value) {
        this.uploadedUserId = value;
    }

    /**
     * Gets the value of the ownerUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * Sets the value of the ownerUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerUserId(String value) {
        this.ownerUserId = value;
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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the expiresBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpiresBy() {
        return expiresBy;
    }

    /**
     * Sets the value of the expiresBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiresBy(String value) {
        this.expiresBy = value;
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
     * Gets the value of the deliveryBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryBy() {
        return deliveryBy;
    }

    /**
     * Sets the value of the deliveryBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryBy(String value) {
        this.deliveryBy = value;
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

}
