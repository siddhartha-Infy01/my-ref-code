//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.12 at 06:53:50 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscriberRegistrationResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="subscriberRegistrationResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="subscriberRegistrationRes" type="{http://www.iexceed.com/subscriberRegistration}subscriberRegistrationRes"/>
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
    "subscriberRegistrationRes"
})
@XmlRootElement(name = "subscriberRegistrationResWrap")
public class SubscriberRegistrationResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/subscriberRegistration", required = true)
    protected SubscriberRegistrationRes subscriberRegistrationRes;

    /**
     * Gets the value of the subscriberRegistrationRes property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberRegistrationRes }
     *     
     */
    public SubscriberRegistrationRes getSubscriberRegistrationRes() {
        return subscriberRegistrationRes;
    }

    /**
     * Sets the value of the subscriberRegistrationRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberRegistrationRes }
     *     
     */
    public void setSubscriberRegistrationRes(SubscriberRegistrationRes value) {
        this.subscriberRegistrationRes = value;
    }

}
