//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.26 at 12:01:41 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NWKPRICETYPES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NWKPRICETYPES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="priceTypeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="priceTypeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selectedValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NWKPRICETYPES", propOrder = {
    "priceTypeId",
    "priceTypeName",
    "selectedValue"
})
public class NWKPRICETYPES {

    @XmlElement(namespace = "http://www.iexceed.com/queryNetworkPriceTypes", required = true)
    protected String priceTypeId;
    @XmlElement(namespace = "http://www.iexceed.com/queryNetworkPriceTypes", required = true)
    protected String priceTypeName;
    @XmlElement(namespace = "http://www.iexceed.com/queryNetworkPriceTypes", required = true)
    protected String selectedValue;

    /**
     * Gets the value of the priceTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceTypeId() {
        return priceTypeId;
    }

    /**
     * Sets the value of the priceTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceTypeId(String value) {
        this.priceTypeId = value;
    }

    /**
     * Gets the value of the priceTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceTypeName() {
        return priceTypeName;
    }

    /**
     * Sets the value of the priceTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceTypeName(String value) {
        this.priceTypeName = value;
    }

    /**
     * Gets the value of the selectedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedValue() {
        return selectedValue;
    }

    /**
     * Sets the value of the selectedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedValue(String value) {
        this.selectedValue = value;
    }

}