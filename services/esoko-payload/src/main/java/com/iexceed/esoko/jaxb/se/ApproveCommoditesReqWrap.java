//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.01 at 05:20:01 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for approveCommoditesReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="approveCommoditesReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="approveCommoditesReq" type="{http://www.iexceed.com/approveCommodites}approveCommoditesReq"/>
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
    "approveCommoditesReq"
})
@XmlRootElement(name = "approveCommoditesReqWrap")
public class ApproveCommoditesReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/approveCommodites", required = true)
    protected ApproveCommoditesReq approveCommoditesReq;

    /**
     * Gets the value of the approveCommoditesReq property.
     * 
     * @return
     *     possible object is
     *     {@link ApproveCommoditesReq }
     *     
     */
    public ApproveCommoditesReq getApproveCommoditesReq() {
        return approveCommoditesReq;
    }

    /**
     * Sets the value of the approveCommoditesReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApproveCommoditesReq }
     *     
     */
    public void setApproveCommoditesReq(ApproveCommoditesReq value) {
        this.approveCommoditesReq = value;
    }

}
