//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.24 at 06:07:36 PM IST 
//


package com.iexceed.esoko.jaxb.smspoll;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPollResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createPollResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createPollRes" type="{http://www.iexceed.com/createPoll}createPollRes"/>
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
    "createPollRes"
})
@XmlRootElement(name = "createPollResWrap")
public class CreatePollResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createPoll", required = true)
    protected CreatePollRes createPollRes;

    /**
     * Gets the value of the createPollRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePollRes }
     *     
     */
    public CreatePollRes getCreatePollRes() {
        return createPollRes;
    }

    /**
     * Sets the value of the createPollRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePollRes }
     *     
     */
    public void setCreatePollRes(CreatePollRes value) {
        this.createPollRes = value;
    }

}
