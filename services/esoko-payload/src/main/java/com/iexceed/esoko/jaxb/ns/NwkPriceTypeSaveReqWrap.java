//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.23 at 02:48:10 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for nwkPriceTypeSaveReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="nwkPriceTypeSaveReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="nwkPriceTypeSaveReq" type="{http://www.iexceed.com/nwkPriceTypeSave}nwkPriceTypeSaveReq"/>
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
    "nwkPriceTypeSaveReq"
})
@XmlRootElement(name = "nwkPriceTypeSaveReqWrap")
public class NwkPriceTypeSaveReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/nwkPriceTypeSave", required = true)
    protected NwkPriceTypeSaveReq nwkPriceTypeSaveReq;

    /**
     * Gets the value of the nwkPriceTypeSaveReq property.
     * 
     * @return
     *     possible object is
     *     {@link NwkPriceTypeSaveReq }
     *     
     */
    public NwkPriceTypeSaveReq getNwkPriceTypeSaveReq() {
        return nwkPriceTypeSaveReq;
    }

    /**
     * Sets the value of the nwkPriceTypeSaveReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link NwkPriceTypeSaveReq }
     *     
     */
    public void setNwkPriceTypeSaveReq(NwkPriceTypeSaveReq value) {
        this.nwkPriceTypeSaveReq = value;
    }

}
