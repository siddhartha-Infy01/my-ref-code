//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.08 at 07:07:32 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteResellerDtlsReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteResellerDtlsReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="DLRSLRDTLS" type="{http://www.iexceed.com/deleteResellerDtls}DLRSLRDTLS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteResellerDtlsReq", propOrder = {
    "header",
    "dlrslrdtls"
})
public class DeleteResellerDtlsReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/deleteResellerDtls", required = true)
    protected Header header;
    @XmlElement(name = "DLRSLRDTLS", namespace = "http://www.iexceed.com/deleteResellerDtls", required = true)
    protected DLRSLRDTLS dlrslrdtls;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the dlrslrdtls property.
     * 
     * @return
     *     possible object is
     *     {@link DLRSLRDTLS }
     *     
     */
    public DLRSLRDTLS getDLRSLRDTLS() {
        return dlrslrdtls;
    }

    /**
     * Sets the value of the dlrslrdtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link DLRSLRDTLS }
     *     
     */
    public void setDLRSLRDTLS(DLRSLRDTLS value) {
        this.dlrslrdtls = value;
    }

}
