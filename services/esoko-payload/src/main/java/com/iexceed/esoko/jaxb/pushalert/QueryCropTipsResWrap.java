//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.31 at 07:13:48 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryCropTipsResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryCropTipsResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryCropTipsRes" type="{http://www.iexceed.com/queryCropTips}queryCropTipsRes"/>
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
    "queryCropTipsRes"
})
@XmlRootElement(name = "queryCropTipsResWrap")
public class QueryCropTipsResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryCropTips", required = true)
    protected QueryCropTipsRes queryCropTipsRes;

    /**
     * Gets the value of the queryCropTipsRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryCropTipsRes }
     *     
     */
    public QueryCropTipsRes getQueryCropTipsRes() {
        return queryCropTipsRes;
    }

    /**
     * Sets the value of the queryCropTipsRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryCropTipsRes }
     *     
     */
    public void setQueryCropTipsRes(QueryCropTipsRes value) {
        this.queryCropTipsRes = value;
    }

}
