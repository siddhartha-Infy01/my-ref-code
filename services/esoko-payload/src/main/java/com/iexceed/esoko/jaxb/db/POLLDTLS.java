//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.24 at 03:46:04 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for POLLDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="POLLDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pollId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="noOfPositives" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noOfNegatives" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="noOfSuspensions" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POLLDTLS", propOrder = {
    "pollId",
    "description",
    "noOfPositives",
    "noOfNegatives",
    "noOfSuspensions"
})
public class POLLDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryPollStats", required = true)
    protected String pollId;
    @XmlElement(namespace = "http://www.iexceed.com/queryPollStats", required = true)
    protected String description;
    @XmlElement(namespace = "http://www.iexceed.com/queryPollStats")
    protected int noOfPositives;
    @XmlElement(namespace = "http://www.iexceed.com/queryPollStats")
    protected int noOfNegatives;
    @XmlElement(namespace = "http://www.iexceed.com/queryPollStats")
    protected int noOfSuspensions;

    /**
     * Gets the value of the pollId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPollId() {
        return pollId;
    }

    /**
     * Sets the value of the pollId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPollId(String value) {
        this.pollId = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the noOfPositives property.
     * 
     */
    public int getNoOfPositives() {
        return noOfPositives;
    }

    /**
     * Sets the value of the noOfPositives property.
     * 
     */
    public void setNoOfPositives(int value) {
        this.noOfPositives = value;
    }

    /**
     * Gets the value of the noOfNegatives property.
     * 
     */
    public int getNoOfNegatives() {
        return noOfNegatives;
    }

    /**
     * Sets the value of the noOfNegatives property.
     * 
     */
    public void setNoOfNegatives(int value) {
        this.noOfNegatives = value;
    }

    /**
     * Gets the value of the noOfSuspensions property.
     * 
     */
    public int getNoOfSuspensions() {
        return noOfSuspensions;
    }

    /**
     * Sets the value of the noOfSuspensions property.
     * 
     */
    public void setNoOfSuspensions(int value) {
        this.noOfSuspensions = value;
    }

}