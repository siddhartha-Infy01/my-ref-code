//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.04 at 06:37:37 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRLIMITSLIST complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRLIMITSLIST">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="row_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CRLIMITS" type="{http://www.iexceed.com/addSubLimitsDtls}CRLIMITS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRLIMITSLIST", propOrder = {
    "type",
    "level",
    "rowId",
    "crlimits"
})
public class CRLIMITSLIST {

    @XmlElement(namespace = "http://www.iexceed.com/addSubLimitsDtls", required = true)
    protected String type;
    @XmlElement(namespace = "http://www.iexceed.com/addSubLimitsDtls", required = true)
    protected String level;
    @XmlElement(name = "row_id", namespace = "http://www.iexceed.com/addSubLimitsDtls")
    protected int rowId;
    @XmlElement(name = "CRLIMITS", namespace = "http://www.iexceed.com/addSubLimitsDtls", required = true)
    protected List<CRLIMITS> crlimits;

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
     * Gets the value of the rowId property.
     * 
     */
    public int getRowId() {
        return rowId;
    }

    /**
     * Sets the value of the rowId property.
     * 
     */
    public void setRowId(int value) {
        this.rowId = value;
    }

    /**
     * Gets the value of the crlimits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crlimits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCRLIMITS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CRLIMITS }
     * 
     * 
     */
    public List<CRLIMITS> getCRLIMITS() {
        if (crlimits == null) {
            crlimits = new ArrayList<CRLIMITS>();
        }
        return this.crlimits;
    }

}
