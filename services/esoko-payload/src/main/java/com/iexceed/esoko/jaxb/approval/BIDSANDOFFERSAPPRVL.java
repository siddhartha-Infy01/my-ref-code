//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.26 at 12:51:06 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BIDSANDOFFERSAPPRVL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BIDSANDOFFERSAPPRVL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BIDSANDOFFERSDTLS" type="{http://www.iexceed.com/libraryApprovalApproval}BIDSANDOFFERSDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BIDSANDOFFERSAPPRVL", propOrder = {
    "networkId",
    "bidsandoffersdtls"
})
public class BIDSANDOFFERSAPPRVL {

    @XmlElement(namespace = "http://www.iexceed.com/libraryApprovalApproval", required = true)
    protected String networkId;
    @XmlElement(name = "BIDSANDOFFERSDTLS", namespace = "http://www.iexceed.com/libraryApprovalApproval", required = true)
    protected List<BIDSANDOFFERSDTLS> bidsandoffersdtls;

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
     * Gets the value of the bidsandoffersdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bidsandoffersdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBIDSANDOFFERSDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BIDSANDOFFERSDTLS }
     * 
     * 
     */
    public List<BIDSANDOFFERSDTLS> getBIDSANDOFFERSDTLS() {
        if (bidsandoffersdtls == null) {
            bidsandoffersdtls = new ArrayList<BIDSANDOFFERSDTLS>();
        }
        return this.bidsandoffersdtls;
    }

}