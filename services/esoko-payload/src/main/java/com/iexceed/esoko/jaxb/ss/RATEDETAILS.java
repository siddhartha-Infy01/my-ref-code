//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.24 at 05:17:11 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RATEDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RATEDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OperatorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OperatorCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MCC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MNC" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Margin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Premium" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RATEDETAILS", propOrder = {
    "operatorName",
    "operatorCountry",
    "mcc",
    "mnc",
    "cost",
    "margin",
    "premium"
})
public class RATEDETAILS {

    @XmlElement(name = "OperatorName", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String operatorName;
    @XmlElement(name = "OperatorCountry", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String operatorCountry;
    @XmlElement(name = "MCC", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String mcc;
    @XmlElement(name = "MNC", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String mnc;
    @XmlElement(name = "Cost", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String cost;
    @XmlElement(name = "Margin", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String margin;
    @XmlElement(name = "Premium", namespace = "http://www.iexceed.com/uploadRates", required = true)
    protected String premium;

    /**
     * Gets the value of the operatorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * Sets the value of the operatorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    /**
     * Gets the value of the operatorCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorCountry() {
        return operatorCountry;
    }

    /**
     * Sets the value of the operatorCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorCountry(String value) {
        this.operatorCountry = value;
    }

    /**
     * Gets the value of the mcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMCC() {
        return mcc;
    }

    /**
     * Sets the value of the mcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMCC(String value) {
        this.mcc = value;
    }

    /**
     * Gets the value of the mnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMNC() {
        return mnc;
    }

    /**
     * Sets the value of the mnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMNC(String value) {
        this.mnc = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCost(String value) {
        this.cost = value;
    }

    /**
     * Gets the value of the margin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMargin() {
        return margin;
    }

    /**
     * Sets the value of the margin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMargin(String value) {
        this.margin = value;
    }

    /**
     * Gets the value of the premium property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPremium() {
        return premium;
    }

    /**
     * Sets the value of the premium property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPremium(String value) {
        this.premium = value;
    }

}