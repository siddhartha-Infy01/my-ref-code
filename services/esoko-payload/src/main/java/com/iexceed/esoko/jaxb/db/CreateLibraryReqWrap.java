//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.23 at 01:03:20 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createLibraryReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createLibraryReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createLibraryReq" type="{http://www.iexceed.com/createLibrary}createLibraryReq"/>
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
    "createLibraryReq"
})
@XmlRootElement(name = "createLibraryReqWrap")
public class CreateLibraryReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createLibrary", required = true)
    protected CreateLibraryReq createLibraryReq;

    /**
     * Gets the value of the createLibraryReq property.
     * 
     * @return
     *     possible object is
     *     {@link CreateLibraryReq }
     *     
     */
    public CreateLibraryReq getCreateLibraryReq() {
        return createLibraryReq;
    }

    /**
     * Sets the value of the createLibraryReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateLibraryReq }
     *     
     */
    public void setCreateLibraryReq(CreateLibraryReq value) {
        this.createLibraryReq = value;
    }

}
