//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.22 at 06:30:49 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actionReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="actionReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="actionReq" type="{http://www.iexceed.com/action}actionReq"/>
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
    "actionReq"
})
@XmlRootElement(name = "actionReqWrap")
public class ActionReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/action", required = true)
    protected ActionReq actionReq;

    /**
     * Gets the value of the actionReq property.
     * 
     * @return
     *     possible object is
     *     {@link ActionReq }
     *     
     */
    public ActionReq getActionReq() {
        return actionReq;
    }

    /**
     * Sets the value of the actionReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionReq }
     *     
     */
    public void setActionReq(ActionReq value) {
        this.actionReq = value;
    }

}
