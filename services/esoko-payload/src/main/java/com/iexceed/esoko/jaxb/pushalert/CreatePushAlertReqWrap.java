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
 * <p>Java class for createPushAlertReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createPushAlertReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createPushAlertReq" type="{http://www.iexceed.com/createPushAlert}createPushAlertReq"/>
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
    "createPushAlertReq"
})
@XmlRootElement(name = "createPushAlertReqWrap")
public class CreatePushAlertReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createPushAlert", required = true)
    protected CreatePushAlertReq createPushAlertReq;

    /**
     * Gets the value of the createPushAlertReq property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePushAlertReq }
     *     
     */
    public CreatePushAlertReq getCreatePushAlertReq() {
        return createPushAlertReq;
    }

    /**
     * Sets the value of the createPushAlertReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePushAlertReq }
     *     
     */
    public void setCreatePushAlertReq(CreatePushAlertReq value) {
        this.createPushAlertReq = value;
    }

}
