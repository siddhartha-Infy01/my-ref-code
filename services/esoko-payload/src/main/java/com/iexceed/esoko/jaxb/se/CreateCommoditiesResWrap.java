//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.24 at 11:49:25 AM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createCommoditiesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createCommoditiesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createCommoditiesRes" type="{http://www.iexceed.com/createCommodities}createCommoditiesRes"/>
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
    "createCommoditiesRes"
})
@XmlRootElement(name = "createCommoditiesResWrap")
public class CreateCommoditiesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createCommodities", required = true)
    protected CreateCommoditiesRes createCommoditiesRes;

    /**
     * Gets the value of the createCommoditiesRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreateCommoditiesRes }
     *     
     */
    public CreateCommoditiesRes getCreateCommoditiesRes() {
        return createCommoditiesRes;
    }

    /**
     * Sets the value of the createCommoditiesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateCommoditiesRes }
     *     
     */
    public void setCreateCommoditiesRes(CreateCommoditiesRes value) {
        this.createCommoditiesRes = value;
    }

}
