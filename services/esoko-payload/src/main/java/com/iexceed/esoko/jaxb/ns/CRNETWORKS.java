//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.03 at 12:29:20 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRNETWORKS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRNETWORKS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="configurations" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="permissions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sharing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRNETWORKS", propOrder = {
    "billing",
    "configurations",
    "permissions",
    "sharing"
})
public class CRNETWORKS {

    @XmlElement(namespace = "http://www.iexceed.com/saveNwkPermissions", required = true)
    protected String billing;
    @XmlElement(namespace = "http://www.iexceed.com/saveNwkPermissions", required = true)
    protected String configurations;
    @XmlElement(namespace = "http://www.iexceed.com/saveNwkPermissions", required = true)
    protected String permissions;
    @XmlElement(namespace = "http://www.iexceed.com/saveNwkPermissions", required = true)
    protected String sharing;

    /**
     * Gets the value of the billing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBilling() {
        return billing;
    }

    /**
     * Sets the value of the billing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBilling(String value) {
        this.billing = value;
    }

    /**
     * Gets the value of the configurations property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigurations() {
        return configurations;
    }

    /**
     * Sets the value of the configurations property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigurations(String value) {
        this.configurations = value;
    }

    /**
     * Gets the value of the permissions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * Sets the value of the permissions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissions(String value) {
        this.permissions = value;
    }

    /**
     * Gets the value of the sharing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSharing() {
        return sharing;
    }

    /**
     * Sets the value of the sharing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSharing(String value) {
        this.sharing = value;
    }

}
