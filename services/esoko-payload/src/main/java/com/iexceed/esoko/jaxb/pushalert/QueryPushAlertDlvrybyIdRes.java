//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 09:22:17 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryPushAlertDlvrybyIdRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryPushAlertDlvrybyIdRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="PSHALRTDTLSID" type="{http://www.iexceed.com/queryPushAlertDlvrybyId}PSHALRTDTLSID" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryPushAlertDlvrybyIdRes", propOrder = {
    "header",
    "pshalrtdtlsid"
})
public class QueryPushAlertDlvrybyIdRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryPushAlertDlvrybyId", required = true)
    protected Header header;
    @XmlElement(name = "PSHALRTDTLSID", namespace = "http://www.iexceed.com/queryPushAlertDlvrybyId", required = true)
    protected List<PSHALRTDTLSID> pshalrtdtlsid;

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
     * Gets the value of the pshalrtdtlsid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pshalrtdtlsid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPSHALRTDTLSID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PSHALRTDTLSID }
     * 
     * 
     */
    public List<PSHALRTDTLSID> getPSHALRTDTLSID() {
        if (pshalrtdtlsid == null) {
            pshalrtdtlsid = new ArrayList<PSHALRTDTLSID>();
        }
        return this.pshalrtdtlsid;
    }

}