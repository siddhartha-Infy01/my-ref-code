//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.25 at 05:15:40 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editOperatorResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="editOperatorResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="editOperatorRes" type="{http://www.iexceed.com/editOperator}editOperatorRes"/>
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
    "editOperatorRes"
})
@XmlRootElement(name = "editOperatorResWrap")
public class EditOperatorResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/editOperator", required = true)
    protected EditOperatorRes editOperatorRes;

    /**
     * Gets the value of the editOperatorRes property.
     * 
     * @return
     *     possible object is
     *     {@link EditOperatorRes }
     *     
     */
    public EditOperatorRes getEditOperatorRes() {
        return editOperatorRes;
    }

    /**
     * Sets the value of the editOperatorRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link EditOperatorRes }
     *     
     */
    public void setEditOperatorRes(EditOperatorRes value) {
        this.editOperatorRes = value;
    }

}
