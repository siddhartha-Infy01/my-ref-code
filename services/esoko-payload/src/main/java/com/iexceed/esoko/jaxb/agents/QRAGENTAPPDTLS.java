//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.19 at 03:36:19 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRAGENTAPPDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRAGENTAPPDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="people_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QRAGENTAPPSUBDTLS" type="{http://www.iexceed.com/queryAgentDtlsByApps}QRAGENTAPPSUBDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRAGENTAPPDTLS", propOrder = {
    "name",
    "peopleId",
    "qragentappsubdtls"
})
public class QRAGENTAPPDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String name;
    @XmlElement(name = "people_id", namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String peopleId;
    @XmlElement(name = "QRAGENTAPPSUBDTLS", namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected List<QRAGENTAPPSUBDTLS> qragentappsubdtls;

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
     * Gets the value of the peopleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeopleId() {
        return peopleId;
    }

    /**
     * Sets the value of the peopleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeopleId(String value) {
        this.peopleId = value;
    }

    /**
     * Gets the value of the qragentappsubdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qragentappsubdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQRAGENTAPPSUBDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QRAGENTAPPSUBDTLS }
     * 
     * 
     */
    public List<QRAGENTAPPSUBDTLS> getQRAGENTAPPSUBDTLS() {
        if (qragentappsubdtls == null) {
            qragentappsubdtls = new ArrayList<QRAGENTAPPSUBDTLS>();
        }
        return this.qragentappsubdtls;
    }

}
