//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.17 at 03:18:49 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deletePushTemplateRequestResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="deletePushTemplateRequestResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="deletePushTemplateRequestRes" type="{http://www.iexceed.com/deletePushTemplateRequest}deletePushTemplateRequestRes"/>
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
    "deletePushTemplateRequestRes"
})
@XmlRootElement(name = "deletePushTemplateRequestResWrap")
public class DeletePushTemplateRequestResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/deletePushTemplateRequest", required = true)
    protected DeletePushTemplateRequestRes deletePushTemplateRequestRes;

    /**
     * Gets the value of the deletePushTemplateRequestRes property.
     * 
     * @return
     *     possible object is
     *     {@link DeletePushTemplateRequestRes }
     *     
     */
    public DeletePushTemplateRequestRes getDeletePushTemplateRequestRes() {
        return deletePushTemplateRequestRes;
    }

    /**
     * Sets the value of the deletePushTemplateRequestRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeletePushTemplateRequestRes }
     *     
     */
    public void setDeletePushTemplateRequestRes(DeletePushTemplateRequestRes value) {
        this.deletePushTemplateRequestRes = value;
    }

}