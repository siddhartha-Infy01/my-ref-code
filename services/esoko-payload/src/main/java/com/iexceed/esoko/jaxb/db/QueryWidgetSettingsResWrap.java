//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 06:41:20 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryWidgetSettingsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryWidgetSettingsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryWidgetSettingsRes" type="{http://www.iexceed.com/queryWidgetSettings}queryWidgetSettingsRes"/>
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
    "queryWidgetSettingsRes"
})
@XmlRootElement(name = "queryWidgetSettingsResWrap")
public class QueryWidgetSettingsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryWidgetSettings", required = true)
    protected QueryWidgetSettingsRes queryWidgetSettingsRes;

    /**
     * Gets the value of the queryWidgetSettingsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryWidgetSettingsRes }
     *     
     */
    public QueryWidgetSettingsRes getQueryWidgetSettingsRes() {
        return queryWidgetSettingsRes;
    }

    /**
     * Sets the value of the queryWidgetSettingsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryWidgetSettingsRes }
     *     
     */
    public void setQueryWidgetSettingsRes(QueryWidgetSettingsRes value) {
        this.queryWidgetSettingsRes = value;
    }

}
