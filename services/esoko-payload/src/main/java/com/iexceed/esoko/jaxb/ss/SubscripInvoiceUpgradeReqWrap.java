//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.27 at 02:09:59 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subscripInvoiceUpgradeReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="subscripInvoiceUpgradeReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="subscripInvoiceUpgradeReq" type="{http://www.iexceed.com/subscripInvoiceUpgrade}subscripInvoiceUpgradeReq"/>
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
    "subscripInvoiceUpgradeReq"
})
@XmlRootElement(name = "subscripInvoiceUpgradeReqWrap")
public class SubscripInvoiceUpgradeReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/subscripInvoiceUpgrade", required = true)
    protected SubscripInvoiceUpgradeReq subscripInvoiceUpgradeReq;

    /**
     * Gets the value of the subscripInvoiceUpgradeReq property.
     * 
     * @return
     *     possible object is
     *     {@link SubscripInvoiceUpgradeReq }
     *     
     */
    public SubscripInvoiceUpgradeReq getSubscripInvoiceUpgradeReq() {
        return subscripInvoiceUpgradeReq;
    }

    /**
     * Sets the value of the subscripInvoiceUpgradeReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscripInvoiceUpgradeReq }
     *     
     */
    public void setSubscripInvoiceUpgradeReq(SubscripInvoiceUpgradeReq value) {
        this.subscripInvoiceUpgradeReq = value;
    }

}
