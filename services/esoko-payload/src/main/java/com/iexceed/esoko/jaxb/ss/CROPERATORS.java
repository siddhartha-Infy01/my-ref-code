//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.24 at 02:19:56 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CROPERATORS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CROPERATORS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operatorCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mcc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mnc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gatewayId1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smpp1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gatewayId2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smpp2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="margin" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="premium" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CROPERATORS", propOrder = {
    "operatorName",
    "operatorCountry",
    "mcc",
    "mnc",
    "gatewayId1",
    "smpp1",
    "gatewayId2",
    "smpp2",
    "currencyId",
    "direction",
    "channel",
    "cost",
    "networkId",
    "margin",
    "premium"
})
public class CROPERATORS {

    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String operatorName;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String operatorCountry;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String mcc;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String mnc;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String gatewayId1;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String smpp1;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String gatewayId2;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String smpp2;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String currencyId;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String direction;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String channel;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator")
    protected float cost;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator", required = true)
    protected String networkId;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator")
    protected float margin;
    @XmlElement(namespace = "http://www.iexceed.com/createOperator")
    protected float premium;

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
     * Gets the value of the operatorCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCountry() {
        return operatorCountry;
    }

    /**
     * Sets the value of the operatorCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCountry(String value) {
        this.operatorCountry = value;
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
     * Gets the value of the gatewayId1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayId1() {
        return gatewayId1;
    }

    /**
     * Sets the value of the gatewayId1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayId1(String value) {
        this.gatewayId1 = value;
    }

    /**
     * Gets the value of the smpp1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmpp1() {
        return smpp1;
    }

    /**
     * Sets the value of the smpp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmpp1(String value) {
        this.smpp1 = value;
    }

    /**
     * Gets the value of the gatewayId2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayId2() {
        return gatewayId2;
    }

    /**
     * Sets the value of the gatewayId2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayId2(String value) {
        this.gatewayId2 = value;
    }

    /**
     * Gets the value of the smpp2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmpp2() {
        return smpp2;
    }

    /**
     * Sets the value of the smpp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmpp2(String value) {
        this.smpp2 = value;
    }

    /**
     * Gets the value of the currencyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyId() {
        return currencyId;
    }

    /**
     * Sets the value of the currencyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyId(String value) {
        this.currencyId = value;
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
     * Gets the value of the premium property.
     * 
     */
    public float getPremium() {
        return premium;
    }

    /**
     * Sets the value of the premium property.
     * 
     */
    public void setPremium(float value) {
        this.premium = value;
    }

}
