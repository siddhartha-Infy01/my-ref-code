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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteNewsLibResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="deleteNewsLibResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="deleteNewsLibRes" type="{http://www.iexceed.com/deleteNewsLib}deleteNewsLibRes"/>
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
    "deleteNewsLibRes"
})
@XmlRootElement(name = "deleteNewsLibResWrap")
public class DeleteNewsLibResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/deleteNewsLib", required = true)
    protected DeleteNewsLibRes deleteNewsLibRes;

    /**
     * Gets the value of the deleteNewsLibRes property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteNewsLibRes }
     *     
     */
    public DeleteNewsLibRes getDeleteNewsLibRes() {
        return deleteNewsLibRes;
    }

    /**
     * Sets the value of the deleteNewsLibRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteNewsLibRes }
     *     
     */
    public void setDeleteNewsLibRes(DeleteNewsLibRes value) {
        this.deleteNewsLibRes = value;
    }

}