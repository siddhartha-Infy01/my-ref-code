//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.17 at 12:25:26 PM IST 
//


package com.iexceed.esoko.jaxb.agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agentdetailsRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="agentdetailsRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="AGNTDTLS" type="{http://www.iexceed.com/agentdetails}AGNTDTLS"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agentdetailsRes", propOrder = {
    "header",
    "agntdtls"
})
public class AgentdetailsRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/agentdetails", required = true)
    protected Header header;
    @XmlElement(name = "AGNTDTLS", namespace = "http://www.iexceed.com/agentdetails", required = true)
    protected AGNTDTLS agntdtls;

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
     * Gets the value of the agntdtls property.
     * 
     * @return
     *     possible object is
     *     {@link AGNTDTLS }
     *     
     */
    public AGNTDTLS getAGNTDTLS() {
        return agntdtls;
    }

    /**
     * Sets the value of the agntdtls property.
     * 
     * @param value
     *     allowed object is
     *     {@link AGNTDTLS }
     *     
     */
    public void setAGNTDTLS(AGNTDTLS value) {
        this.agntdtls = value;
    }

}
