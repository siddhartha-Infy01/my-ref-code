//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 02:56:25 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryUserAccountInformationResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="queryUserAccountInformationResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="queryUserAccountInformationRes" type="{http://www.iexceed.com/queryUserAccountInformation}queryUserAccountInformationRes"/>
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
    "queryUserAccountInformationRes"
})
@XmlRootElement(name = "queryUserAccountInformationResWrap")
public class QueryUserAccountInformationResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/queryUserAccountInformation", required = true)
    protected QueryUserAccountInformationRes queryUserAccountInformationRes;

    /**
     * Gets the value of the queryUserAccountInformationRes property.
     * 
     * @return
     *     possible object is
     *     {@link QueryUserAccountInformationRes }
     *     
     */
    public QueryUserAccountInformationRes getQueryUserAccountInformationRes() {
        return queryUserAccountInformationRes;
    }

    /**
     * Sets the value of the queryUserAccountInformationRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryUserAccountInformationRes }
     *     
     */
    public void setQueryUserAccountInformationRes(QueryUserAccountInformationRes value) {
        this.queryUserAccountInformationRes = value;
    }

}
