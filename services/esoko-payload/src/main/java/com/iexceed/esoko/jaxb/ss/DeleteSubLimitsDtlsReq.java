//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.25 at 02:41:38 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteSubLimitsDtlsReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteSubLimitsDtlsReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="DLLIMITSLIST" type="{http://www.iexceed.com/deleteSubLimitsDtls}DLLIMITSLIST"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteSubLimitsDtlsReq", propOrder = {
    "header",
    "dllimitslist"
})
public class DeleteSubLimitsDtlsReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/deleteSubLimitsDtls", required = true)
    protected Header header;
    @XmlElement(name = "DLLIMITSLIST", namespace = "http://www.iexceed.com/deleteSubLimitsDtls", required = true)
    protected DLLIMITSLIST dllimitslist;

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
     * Gets the value of the dllimitslist property.
     * 
     * @return
     *     possible object is
     *     {@link DLLIMITSLIST }
     *     
     */
    public DLLIMITSLIST getDLLIMITSLIST() {
        return dllimitslist;
    }

    /**
     * Sets the value of the dllimitslist property.
     * 
     * @param value
     *     allowed object is
     *     {@link DLLIMITSLIST }
     *     
     */
    public void setDLLIMITSLIST(DLLIMITSLIST value) {
        this.dllimitslist = value;
    }

}