//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.24 at 08:44:32 AM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ALLNOTIF complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ALLNOTIF">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="notifId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notifMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usrOrGrp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ALLNOTIF", propOrder = {
    "notifId",
    "notifMsg",
    "type",
    "usrOrGrp"
})
public class ALLNOTIF {

    @XmlElement(required = true)
    protected String notifId;
    @XmlElement(required = true)
    protected String notifMsg;
    @XmlElement(required = true)
    protected String type;
    @XmlElement(required = true)
    protected String usrOrGrp;

    /**
     * Gets the value of the notifId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifId() {
        return notifId;
    }

    /**
     * Sets the value of the notifId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifId(String value) {
        this.notifId = value;
    }

    /**
     * Gets the value of the notifMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotifMsg() {
        return notifMsg;
    }

    /**
     * Sets the value of the notifMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotifMsg(String value) {
        this.notifMsg = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the usrOrGrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsrOrGrp() {
        return usrOrGrp;
    }

    /**
     * Sets the value of the usrOrGrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsrOrGrp(String value) {
        this.usrOrGrp = value;
    }

}
