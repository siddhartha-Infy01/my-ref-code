//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.25 at 06:19:33 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editLocationReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="editLocationReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="EDITLOCATION" type="{http://www.iexceed.com/editLocationReq}EDITLOCATION"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "editLocationReq", propOrder = {
    "header",
    "editlocation"
})
public class EditLocationReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected Header header;
    @XmlElement(name = "EDITLOCATION", namespace = "http://www.iexceed.com/editLocationReq", required = true)
    protected EDITLOCATION editlocation;

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
     * Gets the value of the editlocation property.
     * 
     * @return
     *     possible object is
     *     {@link EDITLOCATION }
     *     
     */
    public EDITLOCATION getEDITLOCATION() {
        return editlocation;
    }

    /**
     * Sets the value of the editlocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EDITLOCATION }
     *     
     */
    public void setEDITLOCATION(EDITLOCATION value) {
        this.editlocation = value;
    }

}
