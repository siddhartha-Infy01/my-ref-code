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
 * <p>Java class for queryMeasureFactorRes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryMeasureFactorRes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Header" type="{http://www.iexceed.com/Header}Header"/>
 *         &lt;element name="QRMSRFCTDTLS" type="{http://www.iexceed.com/queryMeasureFactor}QRMSRFCTDTLS" maxOccurs="unbounded"/>
 *         &lt;element name="CUSTMSR" type="{http://www.iexceed.com/queryMeasureFactor}CUSTMSR" maxOccurs="unbounded"/>
 *         &lt;element name="METCMSR" type="{http://www.iexceed.com/queryMeasureFactor}METCMSR" maxOccurs="unbounded"/>
 *         &lt;element name="PRCTYP" type="{http://www.iexceed.com/queryMeasureFactor}PRCTYP" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryMeasureFactorRes", propOrder = {
    "header",
    "qrmsrfctdtls",
    "custmsr",
    "metcmsr",
    "prctyp"
})
public class QueryMeasureFactorRes {

    @XmlElement(name = "Header", namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected Header header;
    @XmlElement(name = "QRMSRFCTDTLS", namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected List<QRMSRFCTDTLS> qrmsrfctdtls;
    @XmlElement(name = "CUSTMSR", namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected List<CUSTMSR> custmsr;
    @XmlElement(name = "METCMSR", namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected List<METCMSR> metcmsr;
    @XmlElement(name = "PRCTYP", namespace = "http://www.iexceed.com/queryMeasureFactor", required = true)
    protected List<PRCTYP> prctyp;

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
     * Gets the value of the qrmsrfctdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrmsrfctdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQRMSRFCTDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QRMSRFCTDTLS }
     * 
     * 
     */
    public List<QRMSRFCTDTLS> getQRMSRFCTDTLS() {
        if (qrmsrfctdtls == null) {
            qrmsrfctdtls = new ArrayList<QRMSRFCTDTLS>();
        }
        return this.qrmsrfctdtls;
    }

    /**
     * Gets the value of the custmsr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custmsr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCUSTMSR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CUSTMSR }
     * 
     * 
     */
    public List<CUSTMSR> getCUSTMSR() {
        if (custmsr == null) {
            custmsr = new ArrayList<CUSTMSR>();
        }
        return this.custmsr;
    }

    /**
     * Gets the value of the metcmsr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metcmsr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMETCMSR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link METCMSR }
     * 
     * 
     */
    public List<METCMSR> getMETCMSR() {
        if (metcmsr == null) {
            metcmsr = new ArrayList<METCMSR>();
        }
        return this.metcmsr;
    }

    /**
     * Gets the value of the prctyp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prctyp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPRCTYP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRCTYP }
     * 
     * 
     */
    public List<PRCTYP> getPRCTYP() {
        if (prctyp == null) {
            prctyp = new ArrayList<PRCTYP>();
        }
        return this.prctyp;
    }

}
