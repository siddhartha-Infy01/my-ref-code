//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.25 at 03:52:21 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GENREPORTRES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GENREPORTRES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="month" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="survey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newsLibrary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prices" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="offers" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contacts" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="percentTarget" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GENREPORTRES", propOrder = {
    "month",
    "survey",
    "newsLibrary",
    "prices",
    "offers",
    "contacts",
    "cost",
    "percentTarget"
})
public class GENREPORTRES {

    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String month;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String survey;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String newsLibrary;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String prices;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String offers;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String contacts;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String cost;
    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected String percentTarget;

    /**
     * Gets the value of the month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonth(String value) {
        this.month = value;
    }

    /**
     * Gets the value of the survey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurvey() {
        return survey;
    }

    /**
     * Sets the value of the survey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurvey(String value) {
        this.survey = value;
    }

    /**
     * Gets the value of the newsLibrary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewsLibrary() {
        return newsLibrary;
    }

    /**
     * Sets the value of the newsLibrary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewsLibrary(String value) {
        this.newsLibrary = value;
    }

    /**
     * Gets the value of the prices property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrices() {
        return prices;
    }

    /**
     * Sets the value of the prices property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrices(String value) {
        this.prices = value;
    }

    /**
     * Gets the value of the offers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffers() {
        return offers;
    }

    /**
     * Sets the value of the offers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffers(String value) {
        this.offers = value;
    }

    /**
     * Gets the value of the contacts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * Sets the value of the contacts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContacts(String value) {
        this.contacts = value;
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
     * Gets the value of the percentTarget property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercentTarget() {
        return percentTarget;
    }

    /**
     * Sets the value of the percentTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercentTarget(String value) {
        this.percentTarget = value;
    }

}
