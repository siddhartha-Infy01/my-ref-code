//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.06 at 09:52:20 AM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createPricesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createPricesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createPricesRes" type="{http://www.iexceed.com/createPrices}createPricesRes"/>
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
    "createPricesRes"
})
@XmlRootElement(name = "createPricesResWrap")
public class CreatePricesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createPrices", required = true)
    protected CreatePricesRes createPricesRes;

    /**
     * Gets the value of the createPricesRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreatePricesRes }
     *     
     */
    public CreatePricesRes getCreatePricesRes() {
        return createPricesRes;
    }

    /**
     * Sets the value of the createPricesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreatePricesRes }
     *     
     */
    public void setCreatePricesRes(CreatePricesRes value) {
        this.createPricesRes = value;
    }

}
