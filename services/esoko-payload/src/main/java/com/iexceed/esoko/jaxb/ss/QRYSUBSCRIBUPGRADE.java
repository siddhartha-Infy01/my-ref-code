//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.29 at 12:04:33 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRYSUBSCRIBUPGRADE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRYSUBSCRIBUPGRADE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="period_3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="period_6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="period_9" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="period_year" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRYSUBSCRIBUPGRADE", propOrder = {
    "paramName",
    "period3",
    "period6",
    "period9",
    "periodYear"
})
public class QRYSUBSCRIBUPGRADE {

    @XmlElement(name = "param_name", namespace = "http://www.iexceed.com/querySubscriptionUpgrade", required = true)
    protected String paramName;
    @XmlElement(name = "period_3", namespace = "http://www.iexceed.com/querySubscriptionUpgrade", required = true)
    protected String period3;
    @XmlElement(name = "period_6", namespace = "http://www.iexceed.com/querySubscriptionUpgrade", required = true)
    protected String period6;
    @XmlElement(name = "period_9", namespace = "http://www.iexceed.com/querySubscriptionUpgrade", required = true)
    protected String period9;
    @XmlElement(name = "period_year", namespace = "http://www.iexceed.com/querySubscriptionUpgrade", required = true)
    protected String periodYear;

    /**
     * Gets the value of the paramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets the value of the paramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamName(String value) {
        this.paramName = value;
    }

    /**
     * Gets the value of the period3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod3() {
        return period3;
    }

    /**
     * Sets the value of the period3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod3(String value) {
        this.period3 = value;
    }

    /**
     * Gets the value of the period6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod6() {
        return period6;
    }

    /**
     * Sets the value of the period6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod6(String value) {
        this.period6 = value;
    }

    /**
     * Gets the value of the period9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod9() {
        return period9;
    }

    /**
     * Sets the value of the period9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod9(String value) {
        this.period9 = value;
    }

    /**
     * Gets the value of the periodYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodYear() {
        return periodYear;
    }

    /**
     * Sets the value of the periodYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodYear(String value) {
        this.periodYear = value;
    }

}