//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.17 at 12:39:42 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryOperatorByLocResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryOperatorByLocResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryOperatorByLocRes" type="{http://www.iexceed.com/queryOperatorByLoc}queryOperatorByLocRes"/>
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
    "queryOperatorByLocRes"
})
@XmlRootElement(name = "queryOperatorByLocResWrap")
public class QueryOperatorByLocResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryOperatorByLoc", required = true)
    protected QueryOperatorByLocRes queryOperatorByLocRes;

    /**
     * Gets the value of the queryOperatorByLocRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryOperatorByLocRes }
     *     
     */
    public QueryOperatorByLocRes getQueryOperatorByLocRes() {
        return queryOperatorByLocRes;
    }

    /**
     * Sets the value of the queryOperatorByLocRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryOperatorByLocRes }
     *     
     */
    public void setQueryOperatorByLocRes(QueryOperatorByLocRes value) {
        this.queryOperatorByLocRes = value;
    }

}
