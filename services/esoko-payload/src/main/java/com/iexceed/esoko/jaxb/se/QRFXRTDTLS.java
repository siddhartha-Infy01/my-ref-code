//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.09 at 11:35:11 AM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRFXRTDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRFXRTDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="baseCCy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quoteCCy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRFXRTDTLS", propOrder = {
    "baseCCy",
    "quoteCCy",
    "rate",
    "amount"
})
public class QRFXRTDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryfxRates", required = true)
    protected String baseCCy;
    @XmlElement(namespace = "http://www.iexceed.com/queryfxRates", required = true)
    protected String quoteCCy;
    @XmlElement(namespace = "http://www.iexceed.com/queryfxRates")
    protected float rate;
    @XmlElement(namespace = "http://www.iexceed.com/queryfxRates")
    protected float amount;

    /**
     * Gets the value of the baseCCy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseCCy() {
        return baseCCy;
    }

    /**
     * Sets the value of the baseCCy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseCCy(String value) {
        this.baseCCy = value;
    }

    /**
     * Gets the value of the quoteCCy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuoteCCy() {
        return quoteCCy;
    }

    /**
     * Sets the value of the quoteCCy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuoteCCy(String value) {
        this.quoteCCy = value;
    }

    /**
     * Gets the value of the rate property.
     * 
     */
    public float getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     * 
     */
    public void setRate(float value) {
        this.rate = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

}
