//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 08:04:50 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAlertDeliveryRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryAlertDeliveryRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="ALRTDLVRYRPT" type="{http://www.iexceed.com/queryAlertDelivery}ALRTDLVRYRPT" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryAlertDeliveryRes", propOrder = {
    "header",
    "alrtdlvryrpt"
})
public class QueryAlertDeliveryRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected Header header;
    @XmlElement(name = "ALRTDLVRYRPT", namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected List<ALRTDLVRYRPT> alrtdlvryrpt;

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
     * Gets the value of the alrtdlvryrpt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alrtdlvryrpt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getALRTDLVRYRPT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ALRTDLVRYRPT }
     * 
     * 
     */
    public List<ALRTDLVRYRPT> getALRTDLVRYRPT() {
        if (alrtdlvryrpt == null) {
            alrtdlvryrpt = new ArrayList<ALRTDLVRYRPT>();
        }
        return this.alrtdlvryrpt;
    }

}