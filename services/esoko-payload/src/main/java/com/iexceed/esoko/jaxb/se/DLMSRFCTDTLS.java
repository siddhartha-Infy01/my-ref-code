//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.11 at 05:45:02 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DLMSRFCTDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DLMSRFCTDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="measureConvId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DLMSRFCTDTLS", propOrder = {
    "measureConvId"
})
public class DLMSRFCTDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/deleteMeasureFactor", required = true)
    protected String measureConvId;

    /**
     * Gets the value of the measureConvId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasureConvId() {
        return measureConvId;
    }

    /**
     * Sets the value of the measureConvId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasureConvId(String value) {
        this.measureConvId = value;
    }

}