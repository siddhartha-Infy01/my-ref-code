//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.23 at 11:09:56 AM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createResellerDtlsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createResellerDtlsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createResellerDtlsRes" type="{http://www.iexceed.com/createResellerDtls}createResellerDtlsRes"/>
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
    "createResellerDtlsRes"
})
@XmlRootElement(name = "createResellerDtlsResWrap")
public class CreateResellerDtlsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createResellerDtls", required = true)
    protected CreateResellerDtlsRes createResellerDtlsRes;

    /**
     * Gets the value of the createResellerDtlsRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreateResellerDtlsRes }
     *     
     */
    public CreateResellerDtlsRes getCreateResellerDtlsRes() {
        return createResellerDtlsRes;
    }

    /**
     * Sets the value of the createResellerDtlsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateResellerDtlsRes }
     *     
     */
    public void setCreateResellerDtlsRes(CreateResellerDtlsRes value) {
        this.createResellerDtlsRes = value;
    }

}
