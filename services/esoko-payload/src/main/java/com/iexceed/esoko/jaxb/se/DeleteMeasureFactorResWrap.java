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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteMeasureFactorResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="deleteMeasureFactorResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="deleteMeasureFactorRes" type="{http://www.iexceed.com/deleteMeasureFactor}deleteMeasureFactorRes"/>
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
    "deleteMeasureFactorRes"
})
@XmlRootElement(name = "deleteMeasureFactorResWrap")
public class DeleteMeasureFactorResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/deleteMeasureFactor", required = true)
    protected DeleteMeasureFactorRes deleteMeasureFactorRes;

    /**
     * Gets the value of the deleteMeasureFactorRes property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteMeasureFactorRes }
     *     
     */
    public DeleteMeasureFactorRes getDeleteMeasureFactorRes() {
        return deleteMeasureFactorRes;
    }

    /**
     * Sets the value of the deleteMeasureFactorRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteMeasureFactorRes }
     *     
     */
    public void setDeleteMeasureFactorRes(DeleteMeasureFactorRes value) {
        this.deleteMeasureFactorRes = value;
    }

}
