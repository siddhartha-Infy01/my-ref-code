//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.04 at 05:18:28 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for QRWTHRDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRWTHRDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wthrId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="temp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRWTHRDTLS", propOrder = {
    "wthrId",
    "locId",
    "date",
    "temp",
    "summary",
    "rain",
    "gis"
})
public class QRWTHRDTLS {

    @XmlElement(required = true)
    protected String wthrId;
    @XmlElement(required = true)
    protected String locId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    protected String temp;
    @XmlElement(required = true)
    protected String summary;
    @XmlElement(required = true)
    protected String rain;
    @XmlElement(required = true)
    protected String gis;

    /**
     * Gets the value of the wthrId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWthrId() {
        return wthrId;
    }

    /**
     * Sets the value of the wthrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWthrId(String value) {
        this.wthrId = value;
    }

    /**
     * Gets the value of the locId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocId() {
        return locId;
    }

    /**
     * Sets the value of the locId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocId(String value) {
        this.locId = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the temp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp() {
        return temp;
    }

    /**
     * Sets the value of the temp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp(String value) {
        this.temp = value;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the rain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRain() {
        return rain;
    }

    /**
     * Sets the value of the rain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRain(String value) {
        this.rain = value;
    }

    /**
     * Gets the value of the gis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGis() {
        return gis;
    }

    /**
     * Sets the value of the gis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGis(String value) {
        this.gis = value;
    }

}
