//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 05:01:39 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cropActionResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="cropActionResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="cropActionRes" type="{http://www.iexceed.com/cropAction}cropActionRes"/>
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
    "cropActionRes"
})
@XmlRootElement(name = "cropActionResWrap")
public class CropActionResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/cropAction", required = true)
    protected CropActionRes cropActionRes;

    /**
     * Gets the value of the cropActionRes property.
     * 
     * @return
     *     possible object is
     *     {@link CropActionRes }
     *     
     */
    public CropActionRes getCropActionRes() {
        return cropActionRes;
    }

    /**
     * Sets the value of the cropActionRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CropActionRes }
     *     
     */
    public void setCropActionRes(CropActionRes value) {
        this.cropActionRes = value;
    }

}
