//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.13 at 09:15:23 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPushAlertResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createPushAlertResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createPushAlertRes" type="{http://www.iexceed.com/createPushAlert}createPushAlertRes"/>
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
    "createPushAlertRes"
})
@XmlRootElement(name = "createPushAlertResWrap")
public class CreatePushAlertResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createPushAlert", required = true)
    protected CreatePushAlertRes createPushAlertRes;

    /**
     * Gets the value of the createPushAlertRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePushAlertRes }
     *     
     */
    public CreatePushAlertRes getCreatePushAlertRes() {
        return createPushAlertRes;
    }

    /**
     * Sets the value of the createPushAlertRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePushAlertRes }
     *     
     */
    public void setCreatePushAlertRes(CreatePushAlertRes value) {
        this.createPushAlertRes = value;
    }

}
