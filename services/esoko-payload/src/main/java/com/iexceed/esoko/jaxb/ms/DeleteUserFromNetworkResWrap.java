//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.12 at 12:16:14 PM IST 
//


package com.iexceed.esoko.jaxb.ms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteUserFromNetworkRes" type="{http://www.iexceed.com/deleteUserFromNetwork}deleteUserFromNetworkRes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "deleteUserFromNetworkRes"
})
@XmlRootElement(name = "deleteUserFromNetworkResWrap")
public class DeleteUserFromNetworkResWrap {

    @XmlElement(required = true)
    protected DeleteUserFromNetworkRes deleteUserFromNetworkRes;

    /**
     * Gets the value of the deleteUserFromNetworkRes property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteUserFromNetworkRes }
     *     
     */
    public DeleteUserFromNetworkRes getDeleteUserFromNetworkRes() {
        return deleteUserFromNetworkRes;
    }

    /**
     * Sets the value of the deleteUserFromNetworkRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteUserFromNetworkRes }
     *     
     */
    public void setDeleteUserFromNetworkRes(DeleteUserFromNetworkRes value) {
        this.deleteUserFromNetworkRes = value;
    }

}