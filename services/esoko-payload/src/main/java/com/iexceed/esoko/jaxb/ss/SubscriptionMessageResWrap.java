//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.17 at 12:44:36 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriptionMessageResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="subscriptionMessageResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="subscriptionMessageRes" type="{http://www.iexceed.com/subscriptionMessage}subscriptionMessageRes"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "subscriptionMessageRes"
})
@XmlRootElement(name = "subscriptionMessageResWrap")
public class SubscriptionMessageResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/subscriptionMessage", required = true)
    protected SubscriptionMessageRes subscriptionMessageRes;

    /**
     * Gets the value of the subscriptionMessageRes property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionMessageRes }
     *     
     */
    public SubscriptionMessageRes getSubscriptionMessageRes() {
        return subscriptionMessageRes;
    }

    /**
     * Sets the value of the subscriptionMessageRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionMessageRes }
     *     
     */
    public void setSubscriptionMessageRes(SubscriptionMessageRes value) {
        this.subscriptionMessageRes = value;
    }

}
