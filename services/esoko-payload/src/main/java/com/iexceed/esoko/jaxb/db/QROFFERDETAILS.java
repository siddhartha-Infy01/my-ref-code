//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 05:09:07 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QROFFERDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QROFFERDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bids" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="offers" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QROFFERDETAILS", propOrder = {
    "offerName",
    "bids",
    "offers"
})
public class QROFFERDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/queryOffers", required = true)
    protected String offerName;
    @XmlElement(namespace = "http://www.iexceed.com/queryOffers")
    protected int bids;
    @XmlElement(namespace = "http://www.iexceed.com/queryOffers")
    protected int offers;

    /**
     * Gets the value of the offerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferName() {
        return offerName;
    }

    /**
     * Sets the value of the offerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferName(String value) {
        this.offerName = value;
    }

    /**
     * Gets the value of the bids property.
     * 
     */
    public int getBids() {
        return bids;
    }

    /**
     * Sets the value of the bids property.
     * 
     */
    public void setBids(int value) {
        this.bids = value;
    }

    /**
     * Gets the value of the offers property.
     * 
     */
    public int getOffers() {
        return offers;
    }

    /**
     * Sets the value of the offers property.
     * 
     */
    public void setOffers(int value) {
        this.offers = value;
    }

}
