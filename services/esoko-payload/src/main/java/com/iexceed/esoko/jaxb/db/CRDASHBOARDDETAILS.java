//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 06:44:41 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRDASHBOARDDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRDASHBOARDDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="widgetId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xCoordinate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="yCoordinate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRDASHBOARDDETAILS", propOrder = {
    "userId",
    "widgetId",
    "xCoordinate",
    "yCoordinate",
    "enabled"
})
public class CRDASHBOARDDETAILS {

    @XmlElement(namespace = "http://www.iexceed.com/saveDashBoardSettings", required = true)
    protected String userId;
    @XmlElement(namespace = "http://www.iexceed.com/saveDashBoardSettings", required = true)
    protected String widgetId;
    @XmlElement(namespace = "http://www.iexceed.com/saveDashBoardSettings")
    protected int xCoordinate;
    @XmlElement(namespace = "http://www.iexceed.com/saveDashBoardSettings")
    protected int yCoordinate;
    @XmlElement(namespace = "http://www.iexceed.com/saveDashBoardSettings", required = true)
    protected String enabled;

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the widgetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWidgetId() {
        return widgetId;
    }

    /**
     * Sets the value of the widgetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWidgetId(String value) {
        this.widgetId = value;
    }

    /**
     * Gets the value of the xCoordinate property.
     * 
     */
    public int getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Sets the value of the xCoordinate property.
     * 
     */
    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    /**
     * Gets the value of the yCoordinate property.
     * 
     */
    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Sets the value of the yCoordinate property.
     * 
     */
    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnabled(String value) {
        this.enabled = value;
    }

}
