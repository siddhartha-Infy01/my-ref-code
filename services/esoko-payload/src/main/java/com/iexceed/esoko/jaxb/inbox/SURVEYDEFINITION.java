//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.29 at 07:13:33 PM IST 
//


package com.iexceed.esoko.jaxb.inbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SURVEYDEFINITION complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SURVEYDEFINITION">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="servey_definition" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SURVEYDEFINITION", propOrder = {
    "serveyDefinition"
})
public class SURVEYDEFINITION {

    @XmlElement(name = "servey_definition", namespace = "http://www.iexceed.com/querySurveyDefinition", required = true)
    protected String serveyDefinition;

    /**
     * Gets the value of the serveyDefinition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServeyDefinition() {
        return serveyDefinition;
    }

    /**
     * Sets the value of the serveyDefinition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServeyDefinition(String value) {
        this.serveyDefinition = value;
    }

}
