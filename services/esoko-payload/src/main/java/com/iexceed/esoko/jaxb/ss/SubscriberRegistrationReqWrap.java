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
 * <p>Java class for subscriberRegistrationReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="subscriberRegistrationReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="subscriberRegistrationReq" type="{http://www.iexceed.com/subscriberRegistration}subscriberRegistrationReq"/>
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
    "subscriberRegistrationReq"
})
@XmlRootElement(name = "subscriberRegistrationReqWrap")
public class SubscriberRegistrationReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/subscriberRegistration", required = true)
    protected SubscriberRegistrationReq subscriberRegistrationReq;

    /**
     * Gets the value of the subscriberRegistrationReq property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberRegistrationReq }
     *     
     */
    public SubscriberRegistrationReq getSubscriberRegistrationReq() {
        return subscriberRegistrationReq;
    }

    /**
     * Sets the value of the subscriberRegistrationReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberRegistrationReq }
     *     
     */
    public void setSubscriberRegistrationReq(SubscriberRegistrationReq value) {
        this.subscriberRegistrationReq = value;
    }

}
