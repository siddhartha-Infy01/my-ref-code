//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.12.24 at 06:18:31 PM IST 
//


package com.iexceed.esoko.jaxb.se;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QRMSRFCTDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QRMSRFCTDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="commodityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="defined" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QRFCTDTLS" type="{http://www.iexceed.com/queryMeasureFactor}QRFCTDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QRMSRFCTDTLS", propOrder = {
    "commodityId",
    "defined",
    "qrfctdtls"
})
public class QRMSRFCTDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected String commodityId;
    @XmlElement(namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected String defined;
    @XmlElement(name = "QRFCTDTLS", namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected List<QRFCTDTLS> qrfctdtls;

    /**
     * Gets the value of the commodityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommodityId() {
        return commodityId;
    }

    /**
     * Sets the value of the commodityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommodityId(String value) {
        this.commodityId = value;
    }

    /**
     * Gets the value of the defined property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefined() {
        return defined;
    }

    /**
     * Sets the value of the defined property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefined(String value) {
        this.defined = value;
    }

    /**
     * Gets the value of the qrfctdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrfctdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQRFCTDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QRFCTDTLS }
     * 
     * 
     */
    public List<QRFCTDTLS> getQRFCTDTLS() {
        if (qrfctdtls == null) {
            qrfctdtls = new ArrayList<QRFCTDTLS>();
        }
        return this.qrfctdtls;
    }

}
