//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.28 at 08:09:04 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CNTRYOPERATORS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CNTRYOPERATORS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="routeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gatewayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mcc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mnc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exceptions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exceptionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="margin" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CNTRYOPERATORS", propOrder = {
    "routeId",
    "operatorName",
    "gatewayName",
    "mcc",
    "mnc",
    "direction",
    "channel",
    "exceptions",
    "exceptionName",
    "currency",
    "currencyName",
    "cost",
    "margin",
    "price"
})
public class CNTRYOPERATORS {

    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String routeId;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String operatorName;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String gatewayName;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String mcc;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String mnc;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String direction;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String channel;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String exceptions;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String exceptionName;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String currency;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls", required = true)
    protected String currencyName;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls")
    protected float cost;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls")
    protected float margin;
    @XmlElement(namespace = "http://www.iexceed.com/queryCountryDtls")
    protected float price;

    /**
     * Gets the value of the routeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRouteId() {
        return routeId;
    }

    /**
     * Sets the value of the routeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRouteId(String value) {
        this.routeId = value;
    }

    /**
     * Gets the value of the operatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Sets the value of the operatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    /**
     * Gets the value of the gatewayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayName() {
        return gatewayName;
    }

    /**
     * Sets the value of the gatewayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayName(String value) {
        this.gatewayName = value;
    }

    /**
     * Gets the value of the mcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcc() {
        return mcc;
    }

    /**
     * Sets the value of the mcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcc(String value) {
        this.mcc = value;
    }

    /**
     * Gets the value of the mnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMnc() {
        return mnc;
    }

    /**
     * Sets the value of the mnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMnc(String value) {
        this.mnc = value;
    }

    /**
     * Gets the value of the direction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Gets the value of the exceptions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptions() {
        return exceptions;
    }

    /**
     * Sets the value of the exceptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptions(String value) {
        this.exceptions = value;
    }

    /**
     * Gets the value of the exceptionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExceptionName() {
        return exceptionName;
    }

    /**
     * Sets the value of the exceptionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExceptionName(String value) {
        this.exceptionName = value;
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
     * Gets the value of the currencyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * Sets the value of the currencyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyName(String value) {
        this.currencyName = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     */
    public float getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     */
    public void setCost(float value) {
        this.cost = value;
    }

    /**
     * Gets the value of the margin property.
     * 
     */
    public float getMargin() {
        return margin;
    }

    /**
     * Sets the value of the margin property.
     * 
     */
    public void setMargin(float value) {
        this.margin = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(float value) {
        this.price = value;
    }

}
