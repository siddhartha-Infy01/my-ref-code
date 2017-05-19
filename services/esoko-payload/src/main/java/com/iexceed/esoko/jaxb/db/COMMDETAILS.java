//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 10:56:56 AM IST 
//


package com.iexceed.esoko.jaxb.db;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMMDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMMDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commodityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currencySymbol" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PRICEDETAILS" type="{http://www.iexceed.com/queryLatestPrice}PRICEDETAILS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMMDETAILS", propOrder = {
    "commodityId",
    "commodityName",
    "currencyCode",
    "currencySymbol",
    "pricedetails"
})
public class COMMDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/queryLatestPrice", required = true)
    protected String commodityId;
    @XmlElement(namespace = "http://www.iexceed.com/queryLatestPrice", required = true)
    protected String commodityName;
    @XmlElement(namespace = "http://www.iexceed.com/queryLatestPrice", required = true)
    protected String currencyCode;
    @XmlElement(namespace = "http://www.iexceed.com/queryLatestPrice", required = true)
    protected String currencySymbol;
    @XmlElement(name = "PRICEDETAILS", namespace = "http://www.iexceed.com/queryLatestPrice", required = true)
    protected List<PRICEDETAILS> pricedetails;

    /**
     * Gets the value of the commodityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityId() {
        return commodityId;
    }

    /**
     * Sets the value of the commodityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityId(String value) {
        this.commodityId = value;
    }

    /**
     * Gets the value of the commodityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityName() {
        return commodityName;
    }

    /**
     * Sets the value of the commodityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityName(String value) {
        this.commodityName = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the currencySymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * Sets the value of the currencySymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencySymbol(String value) {
        this.currencySymbol = value;
    }

    /**
     * Gets the value of the pricedetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricedetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPRICEDETAILS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRICEDETAILS }
     * 
     * 
     */
    public List<PRICEDETAILS> getPRICEDETAILS() {
        if (pricedetails == null) {
            pricedetails = new ArrayList<PRICEDETAILS>();
        }
        return this.pricedetails;
    }

}