//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.24 at 03:45:58 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NWKDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NWKDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xCo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="yCo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PEOPLEDTLS" type="{http://www.iexceed.com/queryMapCoordinates}PEOPLEDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NWKDETAILS", propOrder = {
    "xCo",
    "yCo",
    "peopledtls"
})
public class NWKDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/queryMapCoordinates")
    protected float xCo;
    @XmlElement(namespace = "http://www.iexceed.com/queryMapCoordinates")
    protected float yCo;
    @XmlElement(name = "PEOPLEDTLS", namespace = "http://www.iexceed.com/queryMapCoordinates", required = true)
    protected List<PEOPLEDTLS> peopledtls;

    /**
     * Gets the value of the xCo property.
     * 
     */
    public float getXCo() {
        return xCo;
    }

    /**
     * Sets the value of the xCo property.
     * 
     */
    public void setXCo(float value) {
        this.xCo = value;
    }

    /**
     * Gets the value of the yCo property.
     * 
     */
    public float getYCo() {
        return yCo;
    }

    /**
     * Sets the value of the yCo property.
     * 
     */
    public void setYCo(float value) {
        this.yCo = value;
    }

    /**
     * Gets the value of the peopledtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peopledtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPEOPLEDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PEOPLEDTLS }
     * 
     * 
     */
    public List<PEOPLEDTLS> getPEOPLEDTLS() {
        if (peopledtls == null) {
            peopledtls = new ArrayList<PEOPLEDTLS>();
        }
        return this.peopledtls;
    }

}