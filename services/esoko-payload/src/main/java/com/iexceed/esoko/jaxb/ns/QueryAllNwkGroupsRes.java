//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.05 at 12:38:41 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryAllNwkGroupsRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryAllNwkGroupsRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="GRPDTLS" type="{http://www.iexceed.com/queryAllNwkGroups}GRPDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryAllNwkGroupsRes", propOrder = {
    "header",
    "grpdtls"
})
public class QueryAllNwkGroupsRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryAllNwkGroups", required = true)
    protected Header header;
    @XmlElement(name = "GRPDTLS", namespace = "http://www.iexceed.com/queryAllNwkGroups", required = true)
    protected List<GRPDTLS> grpdtls;

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
     * Gets the value of the grpdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the grpdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGRPDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GRPDTLS }
     * 
     * 
     */
    public List<GRPDTLS> getGRPDTLS() {
        if (grpdtls == null) {
            grpdtls = new ArrayList<GRPDTLS>();
        }
        return this.grpdtls;
    }

}