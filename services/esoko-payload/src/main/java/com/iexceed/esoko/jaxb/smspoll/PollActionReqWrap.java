//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.28 at 10:31:37 AM IST 
//


package com.iexceed.esoko.jaxb.smspoll;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pollActionReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="pollActionReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="pollActionReq" type="{http://www.iexceed.com/pollAction}pollActionReq"/>
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
    "pollActionReq"
})
@XmlRootElement(name = "pollActionReqWrap")
public class PollActionReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/pollAction", required = true)
    protected PollActionReq pollActionReq;

    /**
     * Gets the value of the pollActionReq property.
     * 
     * @return
     *     possible object is
     *     {@link PollActionReq }
     *     
     */
    public PollActionReq getPollActionReq() {
        return pollActionReq;
    }

    /**
     * Sets the value of the pollActionReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link PollActionReq }
     *     
     */
    public void setPollActionReq(PollActionReq value) {
        this.pollActionReq = value;
    }

}