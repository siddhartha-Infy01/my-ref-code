//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.20 at 02:32:01 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRPSHTMPLT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRPSHTMPLT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="editFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldTemplateId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="templateId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="characters" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noofmessages" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRPSHTMPLT", propOrder = {
    "editFlag",
    "oldTemplateId",
    "templateId",
    "networkId",
    "message",
    "characters",
    "noofmessages"
})
public class CRPSHTMPLT {

    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String editFlag;
    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String oldTemplateId;
    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String templateId;
    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String networkId;
    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String message;
    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String characters;
    @XmlElement(namespace = "http://www.iexceed.com/createPushTemplate", required = true)
    protected String noofmessages;

    /**
     * Gets the value of the editFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditFlag() {
        return editFlag;
    }

    /**
     * Sets the value of the editFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditFlag(String value) {
        this.editFlag = value;
    }

    /**
     * Gets the value of the oldTemplateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldTemplateId() {
        return oldTemplateId;
    }

    /**
     * Sets the value of the oldTemplateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldTemplateId(String value) {
        this.oldTemplateId = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * Sets the value of the templateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplateId(String value) {
        this.templateId = value;
    }

    /**
     * Gets the value of the networkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the value of the networkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkId(String value) {
        this.networkId = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the characters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharacters() {
        return characters;
    }

    /**
     * Sets the value of the characters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharacters(String value) {
        this.characters = value;
    }

    /**
     * Gets the value of the noofmessages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoofmessages() {
        return noofmessages;
    }

    /**
     * Sets the value of the noofmessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoofmessages(String value) {
        this.noofmessages = value;
    }

}
