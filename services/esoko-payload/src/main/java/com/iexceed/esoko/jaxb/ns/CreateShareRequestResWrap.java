//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.24 at 11:22:52 AM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createShareRequestResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createShareRequestResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createShareRequestRes" type="{http://www.iexceed.com/createShareRequest}createShareRequestRes"/>
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
    "createShareRequestRes"
})
@XmlRootElement(name = "createShareRequestResWrap")
public class CreateShareRequestResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createShareRequest", required = true)
    protected CreateShareRequestRes createShareRequestRes;

    /**
     * Gets the value of the createShareRequestRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreateShareRequestRes }
     *     
     */
    public CreateShareRequestRes getCreateShareRequestRes() {
        return createShareRequestRes;
    }

    /**
     * Sets the value of the createShareRequestRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateShareRequestRes }
     *     
     */
    public void setCreateShareRequestRes(CreateShareRequestRes value) {
        this.createShareRequestRes = value;
    }

}
