//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.25 at 03:52:21 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generateReportsReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generateReportsReq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="GENREPORTREQ" type="{http://www.iexceed.com/generateReports}GENREPORTREQ"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generateReportsReq", propOrder = {
    "header",
    "genreportreq"
})
public class GenerateReportsReq {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/generateReports", required = true)
    protected Header header;
    @XmlElement(name = "GENREPORTREQ", namespace = "http://www.iexceed.com/generateReports", required = true)
    protected GENREPORTREQ genreportreq;

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
     * Gets the value of the genreportreq property.
     * 
     * @return
     *     possible object is
     *     {@link GENREPORTREQ }
     *     
     */
    public GENREPORTREQ getGENREPORTREQ() {
        return genreportreq;
    }

    /**
     * Sets the value of the genreportreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link GENREPORTREQ }
     *     
     */
    public void setGENREPORTREQ(GENREPORTREQ value) {
        this.genreportreq = value;
    }

}
