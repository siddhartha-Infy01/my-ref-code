//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.19 at 03:36:19 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRAGENTAPPSUBDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRAGENTAPPSUBDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="location_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locationList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodity_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="commodityList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="template" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incentive" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bonus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRAGENTAPPSUBDTLS", propOrder = {
    "locationId",
    "locationList",
    "commodityId",
    "commodityList",
    "template",
    "target",
    "incentive",
    "bonus"
})
public class QRAGENTAPPSUBDTLS {

    @XmlElement(name = "location_id", namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String locationId;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String locationList;
    @XmlElement(name = "commodity_id", namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String commodityId;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String commodityList;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String template;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String target;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String incentive;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String bonus;

    /**
     * Gets the value of the locationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationId(String value) {
        this.locationId = value;
    }

    /**
     * Gets the value of the locationList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationList() {
        return locationList;
    }

    /**
     * Sets the value of the locationList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationList(String value) {
        this.locationList = value;
    }

    /**
     * Gets the value of the commodityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityId() {
        return commodityId;
    }

    /**
     * Sets the value of the commodityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityId(String value) {
        this.commodityId = value;
    }

    /**
     * Gets the value of the commodityList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityList() {
        return commodityList;
    }

    /**
     * Sets the value of the commodityList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityList(String value) {
        this.commodityList = value;
    }

    /**
     * Gets the value of the template property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplate(String value) {
        this.template = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the incentive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncentive() {
        return incentive;
    }

    /**
     * Sets the value of the incentive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncentive(String value) {
        this.incentive = value;
    }

    /**
     * Gets the value of the bonus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBonus() {
        return bonus;
    }

    /**
     * Sets the value of the bonus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBonus(String value) {
        this.bonus = value;
    }

}