//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.19 at 05:15:05 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for usrExstsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="usrExstsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="usrExstsRes" type="{http://www.iexceed.com/usrExsts}usrExstsRes"/>
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
    "usrExstsRes"
})
@XmlRootElement(name = "usrExstsResWrap")
public class UsrExstsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/usrExsts", required = true)
    protected UsrExstsRes usrExstsRes;

    /**
     * Gets the value of the usrExstsRes property.
     * 
     * @return
     *     possible object is
     *     {@link UsrExstsRes }
     *     
     */
    public UsrExstsRes getUsrExstsRes() {
        return usrExstsRes;
    }

    /**
     * Sets the value of the usrExstsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsrExstsRes }
     *     
     */
    public void setUsrExstsRes(UsrExstsRes value) {
        this.usrExstsRes = value;
    }

}
