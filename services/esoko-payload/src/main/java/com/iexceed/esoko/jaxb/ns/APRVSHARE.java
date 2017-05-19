//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.14 at 01:05:03 PM IST 
//


package com.iexceed.esoko.jaxb.ns;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APRVSHARE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APRVSHARE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fromNetwork" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toNetwork" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="approveFlag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="peopleShareType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="peopleMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="APPSHAREITEMS" type="{http://www.iexceed.com/approveSharingRequest}APPSHAREITEMS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APRVSHARE", propOrder = {
    "fromNetwork",
    "toNetwork",
    "approveFlag",
    "peopleShareType",
    "peopleMode",
    "appshareitems"
})
public class APRVSHARE {

    @XmlElement(namespace = "http://www.iexceed.com/approveSharingRequest", required = true)
    protected String fromNetwork;
    @XmlElement(namespace = "http://www.iexceed.com/approveSharingRequest", required = true)
    protected String toNetwork;
    @XmlElement(namespace = "http://www.iexceed.com/approveSharingRequest", required = true)
    protected String approveFlag;
    @XmlElement(namespace = "http://www.iexceed.com/approveSharingRequest", required = true)
    protected String peopleShareType;
    @XmlElement(namespace = "http://www.iexceed.com/approveSharingRequest", required = true)
    protected String peopleMode;
    @XmlElement(name = "APPSHAREITEMS", namespace = "http://www.iexceed.com/approveSharingRequest", required = true)
    protected List<APPSHAREITEMS> appshareitems;

    /**
     * Gets the value of the fromNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromNetwork() {
        return fromNetwork;
    }

    /**
     * Sets the value of the fromNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromNetwork(String value) {
        this.fromNetwork = value;
    }

    /**
     * Gets the value of the toNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToNetwork() {
        return toNetwork;
    }

    /**
     * Sets the value of the toNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToNetwork(String value) {
        this.toNetwork = value;
    }

    /**
     * Gets the value of the approveFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApproveFlag() {
        return approveFlag;
    }

    /**
     * Sets the value of the approveFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApproveFlag(String value) {
        this.approveFlag = value;
    }

    /**
     * Gets the value of the peopleShareType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeopleShareType() {
        return peopleShareType;
    }

    /**
     * Sets the value of the peopleShareType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeopleShareType(String value) {
        this.peopleShareType = value;
    }

    /**
     * Gets the value of the peopleMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeopleMode() {
        return peopleMode;
    }

    /**
     * Sets the value of the peopleMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeopleMode(String value) {
        this.peopleMode = value;
    }

    /**
     * Gets the value of the appshareitems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appshareitems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAPPSHAREITEMS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link APPSHAREITEMS }
     * 
     * 
     */
    public List<APPSHAREITEMS> getAPPSHAREITEMS() {
        if (appshareitems == null) {
            appshareitems = new ArrayList<APPSHAREITEMS>();
        }
        return this.appshareitems;
    }

}
