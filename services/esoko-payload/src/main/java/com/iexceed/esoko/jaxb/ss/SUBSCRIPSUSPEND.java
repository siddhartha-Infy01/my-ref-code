//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.10 at 11:24:06 AM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SUBSCRIPSUSPEND complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SUBSCRIPSUSPEND">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="network_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscription_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subscription_type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prevent_logs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suspend_debits" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SUBSCRIPSUSPEND", propOrder = {
    "networkId",
    "subscriptionId",
    "subscriptionType",
    "preventLogs",
    "suspendDebits",
    "message"
})
public class SUBSCRIPSUSPEND {

    @XmlElement(name = "network_id", namespace = "http://www.iexceed.com/subscriptionSuspend", required = true)
    protected String networkId;
    @XmlElement(name = "subscription_id", namespace = "http://www.iexceed.com/subscriptionSuspend")
    protected int subscriptionId;
    @XmlElement(name = "subscription_type", namespace = "http://www.iexceed.com/subscriptionSuspend", required = true)
    protected String subscriptionType;
    @XmlElement(name = "prevent_logs", namespace = "http://www.iexceed.com/subscriptionSuspend", required = true)
    protected String preventLogs;
    @XmlElement(name = "suspend_debits", namespace = "http://www.iexceed.com/subscriptionSuspend", required = true)
    protected String suspendDebits;
    @XmlElement(namespace = "http://www.iexceed.com/subscriptionSuspend", required = true)
    protected String message;

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
     * Gets the value of the subscriptionId property.
     * 
     */
    public int getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the value of the subscriptionId property.
     * 
     */
    public void setSubscriptionId(int value) {
        this.subscriptionId = value;
    }

    /**
     * Gets the value of the subscriptionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Sets the value of the subscriptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionType(String value) {
        this.subscriptionType = value;
    }

    /**
     * Gets the value of the preventLogs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreventLogs() {
        return preventLogs;
    }

    /**
     * Sets the value of the preventLogs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreventLogs(String value) {
        this.preventLogs = value;
    }

    /**
     * Gets the value of the suspendDebits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuspendDebits() {
        return suspendDebits;
    }

    /**
     * Sets the value of the suspendDebits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuspendDebits(String value) {
        this.suspendDebits = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}