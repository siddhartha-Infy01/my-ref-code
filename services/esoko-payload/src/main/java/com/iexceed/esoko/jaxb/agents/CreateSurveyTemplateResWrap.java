//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.20 at 04:22:20 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createSurveyTemplateResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="createSurveyTemplateResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="createSurveyTemplateRes" type="{http://www.iexceed.com/createSurveyTemplate}createSurveyTemplateRes"/>
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
    "createSurveyTemplateRes"
})
@XmlRootElement(name = "createSurveyTemplateResWrap")
public class CreateSurveyTemplateResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createSurveyTemplate", required = true)
    protected CreateSurveyTemplateRes createSurveyTemplateRes;

    /**
     * Gets the value of the createSurveyTemplateRes property.
     * 
     * @return
     *     possible object is
     *     {@link CreateSurveyTemplateRes }
     *     
     */
    public CreateSurveyTemplateRes getCreateSurveyTemplateRes() {
        return createSurveyTemplateRes;
    }

    /**
     * Sets the value of the createSurveyTemplateRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateSurveyTemplateRes }
     *     
     */
    public void setCreateSurveyTemplateRes(CreateSurveyTemplateRes value) {
        this.createSurveyTemplateRes = value;
    }

}
