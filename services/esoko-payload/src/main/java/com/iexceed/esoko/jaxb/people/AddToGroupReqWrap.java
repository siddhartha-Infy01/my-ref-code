//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.21 at 05:32:29 PM IST 
//


package com.iexceed.esoko.jaxb.people;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addToGroupReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="addToGroupReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="addToGroupReq" type="{http://www.iexceed.com/addToGroup}addToGroupReq"/>
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
    "addToGroupReq"
})
@XmlRootElement(name = "addToGroupReqWrap")
public class AddToGroupReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/addToGroup", required = true)
    protected AddToGroupReq addToGroupReq;

    /**
     * Gets the value of the addToGroupReq property.
     * 
     * @return
     *     possible object is
     *     {@link AddToGroupReq }
     *     
     */
    public AddToGroupReq getAddToGroupReq() {
        return addToGroupReq;
    }

    /**
     * Sets the value of the addToGroupReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddToGroupReq }
     *     
     */
    public void setAddToGroupReq(AddToGroupReq value) {
        this.addToGroupReq = value;
    }

}
