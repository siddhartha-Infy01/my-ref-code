//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.24 at 03:46:11 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for querySmsTrafficRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="querySmsTrafficRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="TRAFFICDTLS" type="{http://www.iexceed.com/querySmsTraffic}TRAFFICDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "querySmsTrafficRes", propOrder = {
    "header",
    "trafficdtls"
})
public class QuerySmsTrafficRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/querySmsTraffic", required = true)
    protected Header header;
    @XmlElement(name = "TRAFFICDTLS", namespace = "http://www.iexceed.com/querySmsTraffic", required = true)
    protected List<TRAFFICDTLS> trafficdtls;

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
     * Gets the value of the trafficdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trafficdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTRAFFICDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRAFFICDTLS }
     * 
     * 
     */
    public List<TRAFFICDTLS> getTRAFFICDTLS() {
        if (trafficdtls == null) {
            trafficdtls = new ArrayList<TRAFFICDTLS>();
        }
        return this.trafficdtls;
    }

}
