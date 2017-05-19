//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.24 at 10:16:40 AM IST 
//


package com.iexceed.esoko.jaxb.approval;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APPROVALSERV complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APPROVALSERV">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PEOPLEAPPROVAL" type="{http://www.iexceed.com/RecordStsApproval}PEOPLEAPPROVAL" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APPROVALSERV", propOrder = {
    "networkId",
    "peopleapproval"
})
public class APPROVALSERV {

    @XmlElement(namespace = "http://www.iexceed.com/RecordStsApproval", required = true)
    protected String networkId;
    @XmlElement(name = "PEOPLEAPPROVAL", namespace = "http://www.iexceed.com/RecordStsApproval", required = true)
    protected List<PEOPLEAPPROVAL> peopleapproval;

    /**
     * Gets the value of the networkId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * Sets the value of the networkId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkId(String value) {
        this.networkId = value;
    }

    /**
     * Gets the value of the peopleapproval property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peopleapproval property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPEOPLEAPPROVAL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PEOPLEAPPROVAL }
     * 
     * 
     */
    public List<PEOPLEAPPROVAL> getPEOPLEAPPROVAL() {
        if (peopleapproval == null) {
            peopleapproval = new ArrayList<PEOPLEAPPROVAL>();
        }
        return this.peopleapproval;
    }

}