//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 02:29:09 PM IST 
//


package com.iexceed.esoko.jaxb.se;

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
 *         &lt;element name="deleteNetworkReq" type="{http://www.iexceed.com/deleteNetwork}deleteNetworkReq"/>
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
    "deleteNetworkReq"
})
@XmlRootElement(name = "deleteNetworkReqWrap")
public class DeleteNetworkReqWrap {

    @XmlElement(required = true)
    protected DeleteNetworkReq deleteNetworkReq;

    /**
     * Gets the value of the deleteNetworkReq property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteNetworkReq }
     *     
     */
    public DeleteNetworkReq getDeleteNetworkReq() {
        return deleteNetworkReq;
    }

    /**
     * Sets the value of the deleteNetworkReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteNetworkReq }
     *     
     */
    public void setDeleteNetworkReq(DeleteNetworkReq value) {
        this.deleteNetworkReq = value;
    }

}
