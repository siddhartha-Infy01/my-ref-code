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
 * <p>Java class for QRAGENTAPPMASDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRAGENTAPPMASDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="application_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QRAGENTAPPDTLS" type="{http://www.iexceed.com/queryAgentDtlsByApps}QRAGENTAPPDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRAGENTAPPMASDTLS", propOrder = {
    "applicationId",
    "count",
    "qragentappdtls"
})
public class QRAGENTAPPMASDTLS {

    @XmlElement(name = "application_id", namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String applicationId;
    @XmlElement(namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected String count;
    @XmlElement(name = "QRAGENTAPPDTLS", namespace = "http://www.iexceed.com/queryAgentDtlsByApps", required = true)
    protected List<QRAGENTAPPDTLS> qragentappdtls;

    /**
     * Gets the value of the applicationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Sets the value of the applicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationId(String value) {
        this.applicationId = value;
    }

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCount(String value) {
        this.count = value;
    }

    /**
     * Gets the value of the qragentappdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qragentappdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQRAGENTAPPDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QRAGENTAPPDTLS }
     * 
     * 
     */
    public List<QRAGENTAPPDTLS> getQRAGENTAPPDTLS() {
        if (qragentappdtls == null) {
            qragentappdtls = new ArrayList<QRAGENTAPPDTLS>();
        }
        return this.qragentappdtls;
    }

}
