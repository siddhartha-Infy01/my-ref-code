//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.22 at 12:00:11 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryfxRatesDateResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryfxRatesDateResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryfxRatesDateRes" type="{http://www.iexceed.com/queryfxRatesDate}queryfxRatesDateRes"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "queryfxRatesDateRes"
})
@XmlRootElement(name = "queryfxRatesDateResWrap")
public class QueryfxRatesDateResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryfxRatesDate", required = true)
    protected QueryfxRatesDateRes queryfxRatesDateRes;

    /**
     * Gets the value of the queryfxRatesDateRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryfxRatesDateRes }
     *     
     */
    public QueryfxRatesDateRes getQueryfxRatesDateRes() {
        return queryfxRatesDateRes;
    }

    /**
     * Sets the value of the queryfxRatesDateRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryfxRatesDateRes }
     *     
     */
    public void setQueryfxRatesDateRes(QueryfxRatesDateRes value) {
        this.queryfxRatesDateRes = value;
    }

}
