//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 02:34:59 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saveNetworkLocationsReq" type="{http://www.iexceed.com/saveNetworkLocations}saveNetworkLocationsReq"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "saveNetworkLocationsReq"
})
@XmlRootElement(name = "saveNetworkLocationsReqWrap")
public class SaveNetworkLocationsReqWrap {

    @XmlElement(required = true)
    protected SaveNetworkLocationsReq saveNetworkLocationsReq;

    /**
     * Gets the value of the saveNetworkLocationsReq property.
     * 
     * @return
     *     possible object is
     *     {@link SaveNetworkLocationsReq }
     *     
     */
    public SaveNetworkLocationsReq getSaveNetworkLocationsReq() {
        return saveNetworkLocationsReq;
    }

    /**
     * Sets the value of the saveNetworkLocationsReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveNetworkLocationsReq }
     *     
     */
    public void setSaveNetworkLocationsReq(SaveNetworkLocationsReq value) {
        this.saveNetworkLocationsReq = value;
    }

}
