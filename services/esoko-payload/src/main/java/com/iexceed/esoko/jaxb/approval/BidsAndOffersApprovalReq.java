//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.26 at 12:51:06 PM IST 
//


package com.iexceed.esoko.jaxb.approval;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bidsAndOffersApprovalReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bidsAndOffersApprovalReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="BIDSANDOFFERSAPPRVL" type="{http://www.iexceed.com/libraryApprovalApproval}BIDSANDOFFERSAPPRVL"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bidsAndOffersApprovalReq", propOrder = {
    "header",
    "bidsandoffersapprvl"
})
public class BidsAndOffersApprovalReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/libraryApprovalApproval", required = true)
    protected Header header;
    @XmlElement(name = "BIDSANDOFFERSAPPRVL", namespace = "http://www.iexceed.com/libraryApprovalApproval", required = true)
    protected BIDSANDOFFERSAPPRVL bidsandoffersapprvl;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the bidsandoffersapprvl property.
     * 
     * @return
     *     possible object is
     *     {@link BIDSANDOFFERSAPPRVL }
     *     
     */
    public BIDSANDOFFERSAPPRVL getBIDSANDOFFERSAPPRVL() {
        return bidsandoffersapprvl;
    }

    /**
     * Sets the value of the bidsandoffersapprvl property.
     * 
     * @param value
     *     allowed object is
     *     {@link BIDSANDOFFERSAPPRVL }
     *     
     */
    public void setBIDSANDOFFERSAPPRVL(BIDSANDOFFERSAPPRVL value) {
        this.bidsandoffersapprvl = value;
    }

}