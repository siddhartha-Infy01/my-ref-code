//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.02 at 09:16:24 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteNewsLibReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteNewsLibReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="DELNWSLIB" type="{http://www.iexceed.com/deleteNewsLib}DELNWSLIB"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteNewsLibReq", propOrder = {
    "header",
    "delnwslib"
})
public class DeleteNewsLibReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/deleteNewsLib", required = true)
    protected Header header;
    @XmlElement(name = "DELNWSLIB", namespace = "http://www.iexceed.com/deleteNewsLib", required = true)
    protected DELNWSLIB delnwslib;

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
     * Gets the value of the delnwslib property.
     * 
     * @return
     *     possible object is
     *     {@link DELNWSLIB }
     *     
     */
    public DELNWSLIB getDELNWSLIB() {
        return delnwslib;
    }

    /**
     * Sets the value of the delnwslib property.
     * 
     * @param value
     *     allowed object is
     *     {@link DELNWSLIB }
     *     
     */
    public void setDELNWSLIB(DELNWSLIB value) {
        this.delnwslib = value;
    }

}
