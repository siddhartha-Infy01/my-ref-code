//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.27 at 06:27:17 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createMeasureFactorResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createMeasureFactorResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createMeasureFactorRes" type="{http://www.iexceed.com/createMeasureFactor}createMeasureFactorRes"/>
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
    "createMeasureFactorRes"
})
@XmlRootElement(name = "createMeasureFactorResWrap")
public class CreateMeasureFactorResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createMeasureFactor", required = true)
    protected CreateMeasureFactorRes createMeasureFactorRes;

    /**
     * Gets the value of the createMeasureFactorRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreateMeasureFactorRes }
     *     
     */
    public CreateMeasureFactorRes getCreateMeasureFactorRes() {
        return createMeasureFactorRes;
    }

    /**
     * Sets the value of the createMeasureFactorRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateMeasureFactorRes }
     *     
     */
    public void setCreateMeasureFactorRes(CreateMeasureFactorRes value) {
        this.createMeasureFactorRes = value;
    }

}
