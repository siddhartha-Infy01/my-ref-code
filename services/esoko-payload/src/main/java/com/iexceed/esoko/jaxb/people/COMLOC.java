//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.07 at 11:52:52 AM IST 
//


package com.iexceed.esoko.jaxb.people;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMLOC complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMLOC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="common_location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMLOC", propOrder = {
    "commonLocation"
})
public class COMLOC {

    @XmlElement(name = "common_location", namespace = "http://www.iexceed.com/createPeople", required = true)
    protected String commonLocation;

    /**
     * Gets the value of the commonLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommonLocation() {
        return commonLocation;
    }

    /**
     * Sets the value of the commonLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommonLocation(String value) {
        this.commonLocation = value;
    }

}
