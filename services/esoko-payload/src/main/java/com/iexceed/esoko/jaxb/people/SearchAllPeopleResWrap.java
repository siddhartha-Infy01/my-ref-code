//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.06 at 07:08:51 PM IST 
//


package com.iexceed.esoko.jaxb.people;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchAllPeopleResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="searchAllPeopleResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="searchAllPeopleRes" type="{http://www.iexceed.com/searchAllPeople}searchAllPeopleRes"/>
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
    "searchAllPeopleRes"
})
@XmlRootElement(name = "searchAllPeopleResWrap")
public class SearchAllPeopleResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/searchAllPeople", required = true)
    protected SearchAllPeopleRes searchAllPeopleRes;

    /**
     * Gets the value of the searchAllPeopleRes property.
     * 
     * @return
     *     possible object is
     *     {@link SearchAllPeopleRes }
     *     
     */
    public SearchAllPeopleRes getSearchAllPeopleRes() {
        return searchAllPeopleRes;
    }

    /**
     * Sets the value of the searchAllPeopleRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchAllPeopleRes }
     *     
     */
    public void setSearchAllPeopleRes(SearchAllPeopleRes value) {
        this.searchAllPeopleRes = value;
    }

}