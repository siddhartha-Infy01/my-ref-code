//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.23 at 02:48:10 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nwkPriceTypeSaveResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="nwkPriceTypeSaveResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="nwkPriceTypeSaveRes" type="{http://www.iexceed.com/nwkPriceTypeSave}nwkPriceTypeSaveRes"/>
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
    "nwkPriceTypeSaveRes"
})
@XmlRootElement(name = "nwkPriceTypeSaveResWrap")
public class NwkPriceTypeSaveResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/nwkPriceTypeSave", required = true)
    protected NwkPriceTypeSaveRes nwkPriceTypeSaveRes;

    /**
     * Gets the value of the nwkPriceTypeSaveRes property.
     * 
     * @return
     *     possible object is
     *     {@link NwkPriceTypeSaveRes }
     *     
     */
    public NwkPriceTypeSaveRes getNwkPriceTypeSaveRes() {
        return nwkPriceTypeSaveRes;
    }

    /**
     * Sets the value of the nwkPriceTypeSaveRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link NwkPriceTypeSaveRes }
     *     
     */
    public void setNwkPriceTypeSaveRes(NwkPriceTypeSaveRes value) {
        this.nwkPriceTypeSaveRes = value;
    }

}
