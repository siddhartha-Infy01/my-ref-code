//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.17 at 12:06:04 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mapCommoditiesResWrap element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="mapCommoditiesResWrap">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="mapCommoditiesRes" type="{http://www.iexceed.com/createCommodities}mapCommoditiesRes"/>
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
    "mapCommoditiesRes"
})
@XmlRootElement(name = "mapCommoditiesResWrap")
public class MapCommoditiesResWrap {

    @XmlElement(namespace = "http://www.iexceed.com/createCommodities", required = true)
    protected MapCommoditiesRes mapCommoditiesRes;

    /**
     * Gets the value of the mapCommoditiesRes property.
     * 
     * @return
     *     possible object is
     *     {@link MapCommoditiesRes }
     *     
     */
    public MapCommoditiesRes getMapCommoditiesRes() {
        return mapCommoditiesRes;
    }

    /**
     * Sets the value of the mapCommoditiesRes property.
     * 
     * @param value
     *     allowed object is
     *     {@link MapCommoditiesRes }
     *     
     */
    public void setMapCommoditiesRes(MapCommoditiesRes value) {
        this.mapCommoditiesRes = value;
    }

}
