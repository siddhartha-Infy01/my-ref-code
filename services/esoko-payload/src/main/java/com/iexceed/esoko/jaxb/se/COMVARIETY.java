//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.23 at 05:34:53 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMVARIETY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMVARIETY">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Rank" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Parent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Picture" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="Desc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="COMTYPES" type="{http://www.iexceed.com/querySysCommodities}COMTYPES" maxOccurs="unbounded"/>
 *         &lt;element name="COMATTRIBS" type="{http://www.iexceed.com/querySysCommodities}COMATTRIBS" maxOccurs="unbounded"/>
 *         &lt;element name="COMMEASURES" type="{http://www.iexceed.com/querySysCommodities}COMMEASURES" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMVARIETY", propOrder = {
    "name",
    "rank",
    "status",
    "parent",
    "level",
    "picture",
    "desc",
    "comtypes",
    "comattribs",
    "commeasures"
})
public class COMVARIETY {

    @XmlElement(name = "Name", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected String name;
    @XmlElement(name = "Rank", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected String rank;
    @XmlElement(name = "Status", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected String status;
    @XmlElement(name = "Parent", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected String parent;
    @XmlElement(name = "Level", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected String level;
    @XmlElement(name = "Picture", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected byte[] picture;
    @XmlElement(name = "Desc", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected String desc;
    @XmlElement(name = "COMTYPES", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected List<COMTYPES> comtypes;
    @XmlElement(name = "COMATTRIBS", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected List<COMATTRIBS> comattribs;
    @XmlElement(name = "COMMEASURES", namespace = "http://www.iexceed.com/querySysCommodities", required = true)
    protected List<COMMEASURES> commeasures;

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
     * Gets the value of the rank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRank() {
        return rank;
    }

    /**
     * Sets the value of the rank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRank(String value) {
        this.rank = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParent(String value) {
        this.parent = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the picture property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Sets the value of the picture property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPicture(byte[] value) {
        this.picture = ((byte[]) value);
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the comtypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comtypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMTYPES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMTYPES }
     * 
     * 
     */
    public List<COMTYPES> getCOMTYPES() {
        if (comtypes == null) {
            comtypes = new ArrayList<COMTYPES>();
        }
        return this.comtypes;
    }

    /**
     * Gets the value of the comattribs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comattribs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMATTRIBS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMATTRIBS }
     * 
     * 
     */
    public List<COMATTRIBS> getCOMATTRIBS() {
        if (comattribs == null) {
            comattribs = new ArrayList<COMATTRIBS>();
        }
        return this.comattribs;
    }

    /**
     * Gets the value of the commeasures property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commeasures property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMMEASURES().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMMEASURES }
     * 
     * 
     */
    public List<COMMEASURES> getCOMMEASURES() {
        if (commeasures == null) {
            commeasures = new ArrayList<COMMEASURES>();
        }
        return this.commeasures;
    }

}
