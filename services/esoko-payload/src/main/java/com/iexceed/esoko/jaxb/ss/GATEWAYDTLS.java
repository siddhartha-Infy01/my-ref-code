//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.04.28 at 08:09:10 PM IST 
//


package com.iexceed.esoko.jaxb.ss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GATEWAYDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GATEWAYDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gatewayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nuOfCountries" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nuOfOperators" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OPERATORDTLS" type="{http://www.iexceed.com/queryGatewayDtls}OPERATORDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GATEWAYDTLS", propOrder = {
    "gatewayName",
    "nuOfCountries",
    "nuOfOperators",
    "operatordtls"
})
public class GATEWAYDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/queryGatewayDtls", required = true)
    protected String gatewayName;
    @XmlElement(namespace = "http://www.iexceed.com/queryGatewayDtls", required = true)
    protected String nuOfCountries;
    @XmlElement(namespace = "http://www.iexceed.com/queryGatewayDtls", required = true)
    protected String nuOfOperators;
    @XmlElement(name = "OPERATORDTLS", namespace = "http://www.iexceed.com/queryGatewayDtls", required = true)
    protected List<OPERATORDTLS> operatordtls;

    /**
     * Gets the value of the gatewayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayName() {
        return gatewayName;
    }

    /**
     * Sets the value of the gatewayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayName(String value) {
        this.gatewayName = value;
    }

    /**
     * Gets the value of the nuOfCountries property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuOfCountries() {
        return nuOfCountries;
    }

    /**
     * Sets the value of the nuOfCountries property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuOfCountries(String value) {
        this.nuOfCountries = value;
    }

    /**
     * Gets the value of the nuOfOperators property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuOfOperators() {
        return nuOfOperators;
    }

    /**
     * Sets the value of the nuOfOperators property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuOfOperators(String value) {
        this.nuOfOperators = value;
    }

    /**
     * Gets the value of the operatordtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatordtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOPERATORDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OPERATORDTLS }
     * 
     * 
     */
    public List<OPERATORDTLS> getOPERATORDTLS() {
        if (operatordtls == null) {
            operatordtls = new ArrayList<OPERATORDTLS>();
        }
        return this.operatordtls;
    }

}
