//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 06:49:14 PM IST 
//


package com.iexceed.esoko.jaxb.people;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for checkGroupNameResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="checkGroupNameResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="checkGroupNameRes" type="{http://www.iexceed.com/checkGroupName}checkGroupNameRes"/>
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
    "checkGroupNameRes"
})
@XmlRootElement(name = "checkGroupNameResWrap")
public class CheckGroupNameResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/checkGroupName", required = true)
    protected CheckGroupNameRes checkGroupNameRes;

    /**
     * Gets the value of the checkGroupNameRes property.
     * 
     * @return
     *     possible object is
     *     {@link CheckGroupNameRes }
     *     
     */
    public CheckGroupNameRes getCheckGroupNameRes() {
        return checkGroupNameRes;
    }

    /**
     * Sets the value of the checkGroupNameRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckGroupNameRes }
     *     
     */
    public void setCheckGroupNameRes(CheckGroupNameRes value) {
        this.checkGroupNameRes = value;
    }

}