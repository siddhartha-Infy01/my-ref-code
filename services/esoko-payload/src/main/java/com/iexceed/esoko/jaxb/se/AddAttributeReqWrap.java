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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addAttributeReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="addAttributeReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="addAttributeReq" type="{http://www.iexceed.com/addAttribute}addAttributeReq"/>
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
    "addAttributeReq"
})
@XmlRootElement(name = "addAttributeReqWrap")
public class AddAttributeReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/addAttribute", required = true)
    protected AddAttributeReq addAttributeReq;

    /**
     * Gets the value of the addAttributeReq property.
     * 
     * @return
     *     possible object is
     *     {@link AddAttributeReq }
     *     
     */
    public AddAttributeReq getAddAttributeReq() {
        return addAttributeReq;
    }

    /**
     * Sets the value of the addAttributeReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddAttributeReq }
     *     
     */
    public void setAddAttributeReq(AddAttributeReq value) {
        this.addAttributeReq = value;
    }

}
