//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.27 at 11:10:05 AM IST 
//


package com.iexceed.esoko.jaxb.inbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for querySurveyResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="querySurveyResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="querySurveyRes" type="{http://www.iexceed.com/querySurvey}querySurveyRes"/>
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
    "querySurveyRes"
})
@XmlRootElement(name = "querySurveyResWrap")
public class QuerySurveyResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/querySurvey", required = true)
    protected QuerySurveyRes querySurveyRes;

    /**
     * Gets the value of the querySurveyRes property.
     * 
     * @return
     *     possible object is
     *     {@link QuerySurveyRes }
     *     
     */
    public QuerySurveyRes getQuerySurveyRes() {
        return querySurveyRes;
    }

    /**
     * Sets the value of the querySurveyRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuerySurveyRes }
     *     
     */
    public void setQuerySurveyRes(QuerySurveyRes value) {
        this.querySurveyRes = value;
    }

}