//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.11 at 05:13:22 PM IST 
//


package com.iexceed.esoko.jaxb.people;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editPeopleReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editPeopleReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="EDITPPLDTLS" type="{http://www.iexceed.com/editPeople}EDITPPLDTLS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editPeopleReq", propOrder = {
    "header",
    "editppldtls"
})
public class EditPeopleReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/editPeople", required = true)
    protected Header header;
    @XmlElement(name = "EDITPPLDTLS", namespace = "http://www.iexceed.com/editPeople", required = true)
    protected EDITPPLDTLS editppldtls;

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
     * Gets the value of the editppldtls property.
     * 
     * @return
     *     possible object is
     *     {@link EDITPPLDTLS }
     *     
     */
    public EDITPPLDTLS getEDITPPLDTLS() {
        return editppldtls;
    }

    /**
     * Sets the value of the editppldtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link EDITPPLDTLS }
     *     
     */
    public void setEDITPPLDTLS(EDITPPLDTLS value) {
        this.editppldtls = value;
    }

}
