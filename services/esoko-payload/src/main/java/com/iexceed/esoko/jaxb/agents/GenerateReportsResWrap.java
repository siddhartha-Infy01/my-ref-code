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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generateReportsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="generateReportsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="generateReportsRes" type="{http://www.iexceed.com/generateReports}generateReportsRes"/>
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
    "generateReportsRes"
})
@XmlRootElement(name = "generateReportsResWrap")
public class GenerateReportsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/generateReports", required = true)
    protected GenerateReportsRes generateReportsRes;

    /**
     * Gets the value of the generateReportsRes property.
     * 
     * @return
     *     possible object is
     *     {@link GenerateReportsRes }
     *     
     */
    public GenerateReportsRes getGenerateReportsRes() {
        return generateReportsRes;
    }

    /**
     * Sets the value of the generateReportsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenerateReportsRes }
     *     
     */
    public void setGenerateReportsRes(GenerateReportsRes value) {
        this.generateReportsRes = value;
    }

}