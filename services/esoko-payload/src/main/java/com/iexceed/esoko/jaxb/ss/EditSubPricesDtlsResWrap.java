//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.21 at 03:34:37 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for editSubPricesDtlsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="editSubPricesDtlsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="editSubPricesDtlsRes" type="{http://www.iexceed.com/editSubPricesDtls}editSubPricesDtlsRes"/>
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
    "editSubPricesDtlsRes"
})
@XmlRootElement(name = "editSubPricesDtlsResWrap")
public class EditSubPricesDtlsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/editSubPricesDtls", required = true)
    protected EditSubPricesDtlsRes editSubPricesDtlsRes;

    /**
     * Gets the value of the editSubPricesDtlsRes property.
     * 
     * @return
     *     possible object is
     *     {@link EditSubPricesDtlsRes }
     *     
     */
    public EditSubPricesDtlsRes getEditSubPricesDtlsRes() {
        return editSubPricesDtlsRes;
    }

    /**
     * Sets the value of the editSubPricesDtlsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link EditSubPricesDtlsRes }
     *     
     */
    public void setEditSubPricesDtlsRes(EditSubPricesDtlsRes value) {
        this.editSubPricesDtlsRes = value;
    }

}
