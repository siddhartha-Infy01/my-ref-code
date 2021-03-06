//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 05:32:45 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRCOMMDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRCOMMDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commodityID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="selected" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="childCount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parentId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="attribute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ALSDTLS" type="{http://www.iexceed.com/queryCommodities}ALSDTLS" maxOccurs="unbounded"/>
 *         &lt;element name="CMDPICS" type="{http://www.iexceed.com/queryCommodities}CMDPICS" maxOccurs="unbounded"/>
 *         &lt;element name="QRCOMMDTLS" type="{http://www.iexceed.com/queryCommodities}QRCOMMDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRCOMMDTLS", propOrder = {
    "commodityID",
    "name",
    "description",
    "selected",
    "childCount",
    "parentId",
    "type",
    "attribute",
    "alsdtls",
    "cmdpics",
    "qrcommdtls"
})
public class QRCOMMDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String commodityID;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String description;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String selected;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String childCount;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String parentId;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String type;
    @XmlElement(namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected String attribute;
    @XmlElement(name = "ALSDTLS", namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected List<ALSDTLS> alsdtls;
    @XmlElement(name = "CMDPICS", namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected List<CMDPICS> cmdpics;
    @XmlElement(name = "QRCOMMDTLS", namespace = "http://www.iexceed.com/queryCommodities", required = true)
    protected List<QRCOMMDTLS> qrcommdtls;

    /**
     * Gets the value of the commodityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityID() {
        return commodityID;
    }

    /**
     * Sets the value of the commodityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityID(String value) {
        this.commodityID = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelected() {
        return selected;
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelected(String value) {
        this.selected = value;
    }

    /**
     * Gets the value of the childCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChildCount() {
        return childCount;
    }

    /**
     * Sets the value of the childCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChildCount(String value) {
        this.childCount = value;
    }

    /**
     * Gets the value of the parentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Sets the value of the parentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentId(String value) {
        this.parentId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribute(String value) {
        this.attribute = value;
    }

    /**
     * Gets the value of the alsdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alsdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getALSDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ALSDTLS }
     * 
     * 
     */
    public List<ALSDTLS> getALSDTLS() {
        if (alsdtls == null) {
            alsdtls = new ArrayList<ALSDTLS>();
        }
        return this.alsdtls;
    }

    /**
     * Gets the value of the cmdpics property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cmdpics property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCMDPICS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CMDPICS }
     * 
     * 
     */
    public List<CMDPICS> getCMDPICS() {
        if (cmdpics == null) {
            cmdpics = new ArrayList<CMDPICS>();
        }
        return this.cmdpics;
    }

    /**
     * Gets the value of the qrcommdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcommdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQRCOMMDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QRCOMMDTLS }
     * 
     * 
     */
    public List<QRCOMMDTLS> getQRCOMMDTLS() {
        if (qrcommdtls == null) {
            qrcommdtls = new ArrayList<QRCOMMDTLS>();
        }
        return this.qrcommdtls;
    }

}
