//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.19 at 08:04:50 PM IST 
//


package com.iexceed.esoko.jaxb.pushalert;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ALRTDLVRYRPT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ALRTDLVRYRPT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="schedule_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="alert_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pending" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="failed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ALRTDLVRYRPT", propOrder = {
    "scheduleId",
    "alertName",
    "date",
    "time",
    "sent",
    "pending",
    "failed",
    "total"
})
public class ALRTDLVRYRPT {

    @XmlElement(name = "schedule_id", namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String scheduleId;
    @XmlElement(name = "alert_name", namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String alertName;
    @XmlElement(namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String date;
    @XmlElement(namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String time;
    @XmlElement(namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String sent;
    @XmlElement(namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String pending;
    @XmlElement(namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String failed;
    @XmlElement(namespace = "http://www.iexceed.com/queryAlertDelivery", required = true)
    protected String total;

    /**
     * Gets the value of the scheduleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets the value of the scheduleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleId(String value) {
        this.scheduleId = value;
    }

    /**
     * Gets the value of the alertName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertName() {
        return alertName;
    }

    /**
     * Sets the value of the alertName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertName(String value) {
        this.alertName = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the sent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSent() {
        return sent;
    }

    /**
     * Sets the value of the sent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSent(String value) {
        this.sent = value;
    }

    /**
     * Gets the value of the pending property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPending() {
        return pending;
    }

    /**
     * Sets the value of the pending property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPending(String value) {
        this.pending = value;
    }

    /**
     * Gets the value of the failed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailed() {
        return failed;
    }

    /**
     * Sets the value of the failed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailed(String value) {
        this.failed = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotal(String value) {
        this.total = value;
    }

}
