//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.02 at 05:05:00 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addAttributeReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addAttributeReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="ADDATTRIBUTE" type="{http://www.iexceed.com/addAttribute}ADDATTRIBUTE"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addAttributeReq", propOrder = {
    "header",
    "addattribute"
})
public class AddAttributeReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/addAttribute", required = true)
    protected Header header;
    @XmlElement(name = "ADDATTRIBUTE", namespace = "http://www.iexceed.com/addAttribute", required = true)
    protected ADDATTRIBUTE addattribute;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the addattribute property.
     * 
     * @return
     *     possible object is
     *     {@link ADDATTRIBUTE }
     *     
     */
    public ADDATTRIBUTE getADDATTRIBUTE() {
        return addattribute;
    }

    /**
     * Sets the value of the addattribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link ADDATTRIBUTE }
     *     
     */
    public void setADDATTRIBUTE(ADDATTRIBUTE value) {
        this.addattribute = value;
    }

}
