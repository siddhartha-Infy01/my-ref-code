//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.07 at 08:18:32 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteResellerInvoiceReqWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="deleteResellerInvoiceReqWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="deleteResellerInvoiceReq" type="{http://www.iexceed.com/deleteResellerSubscripInvoice}deleteResellerInvoiceReq"/>
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
    "deleteResellerInvoiceReq"
})
@XmlRootElement(name = "deleteResellerInvoiceReqWrap")
public class DeleteResellerInvoiceReqWrap {

    @XmlElement(namespace = "http://www.iexceed.com/deleteResellerSubscripInvoice", required = true)
    protected DeleteResellerInvoiceReq deleteResellerInvoiceReq;

    /**
     * Gets the value of the deleteResellerInvoiceReq property.
     * 
     * @return
     *     possible object is
     *     {@link DeleteResellerInvoiceReq }
     *     
     */
    public DeleteResellerInvoiceReq getDeleteResellerInvoiceReq() {
        return deleteResellerInvoiceReq;
    }

    /**
     * Sets the value of the deleteResellerInvoiceReq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeleteResellerInvoiceReq }
     *     
     */
    public void setDeleteResellerInvoiceReq(DeleteResellerInvoiceReq value) {
        this.deleteResellerInvoiceReq = value;
    }

}
