//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.02 at 12:37:14 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMMOD_VARIETY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMMOD_VARIETY">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="variety_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMMOD_VARIETY", propOrder = {
    "varietyName"
})
public class COMMODVARIETY {

    @XmlElement(name = "variety_name", namespace = "http://www.iexceed.com/queryAllUnAuthCommodities", required = true)
    protected String varietyName;

    /**
     * Gets the value of the varietyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVarietyName() {
        return varietyName;
    }

    /**
     * Sets the value of the varietyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVarietyName(String value) {
        this.varietyName = value;
    }

}
