//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.08 at 07:07:32 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteResellerDtlsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="deleteResellerDtlsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="deleteResellerDtlsRes" type="{http://www.iexceed.com/deleteResellerDtls}deleteResellerDtlsRes"/>
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
    "deleteResellerDtlsRes"
})
@XmlRootElement(name = "deleteResellerDtlsResWrap")
public class DeleteResellerDtlsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/deleteResellerDtls", required = true)
    protected DeleteResellerDtlsRes deleteResellerDtlsRes;

    /**
     * Gets the value of the deleteResellerDtlsRes property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteResellerDtlsRes }
     *     
     */
    public DeleteResellerDtlsRes getDeleteResellerDtlsRes() {
        return deleteResellerDtlsRes;
    }

    /**
     * Sets the value of the deleteResellerDtlsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteResellerDtlsRes }
     *     
     */
    public void setDeleteResellerDtlsRes(DeleteResellerDtlsRes value) {
        this.deleteResellerDtlsRes = value;
    }

}
