//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.11.11 at 05:13:22 PM IST 
//


package com.iexceed.esoko.jaxb.people;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EDITPPLDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EDITPPLDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="peopleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mainOffice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="town" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mob1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mob2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthYear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fixedPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="address2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="privacy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="networkId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CMMDTLS" type="{http://www.iexceed.com/editPeople}CMMDTLS" maxOccurs="unbounded"/>
 *         &lt;element name="LOCDTLS" type="{http://www.iexceed.com/editPeople}LOCDTLS" maxOccurs="unbounded"/>
 *         &lt;element name="OCCDTLS" type="{http://www.iexceed.com/editPeople}OCCDTLS" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EDITPPLDTLS", propOrder = {
    "peopleId",
    "title",
    "firstName",
    "lastName",
    "email",
    "country",
    "mainOffice",
    "town",
    "mob1",
    "mob2",
    "gender",
    "birthYear",
    "currency",
    "company",
    "position",
    "description",
    "fixedPhone",
    "fax",
    "address1",
    "address2",
    "website",
    "privacy",
    "language",
    "networkId",
    "cmmdtls",
    "locdtls",
    "occdtls"
})
public class EDITPPLDTLS {

    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String peopleId;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String title;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String firstName;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String lastName;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String email;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String country;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String mainOffice;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String town;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String mob1;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String mob2;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String gender;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String birthYear;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String currency;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String company;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String position;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String description;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String fixedPhone;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String fax;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String address1;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String address2;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String website;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String privacy;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String language;
    @XmlElement(namespace = "http://www.iexceed.com/editPeople", required = true)
    protected String networkId;
    @XmlElement(name = "CMMDTLS", namespace = "http://www.iexceed.com/editPeople", required = true)
    protected List<CMMDTLS> cmmdtls;
    @XmlElement(name = "LOCDTLS", namespace = "http://www.iexceed.com/editPeople", required = true)
    protected List<LOCDTLS> locdtls;
    @XmlElement(name = "OCCDTLS", namespace = "http://www.iexceed.com/editPeople", required = true)
    protected List<OCCDTLS> occdtls;

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
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the mainOffice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainOffice() {
        return mainOffice;
    }

    /**
     * Sets the value of the mainOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainOffice(String value) {
        this.mainOffice = value;
    }

    /**
     * Gets the value of the town property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of the town property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTown(String value) {
        this.town = value;
    }

    /**
     * Gets the value of the mob1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMob1() {
        return mob1;
    }

    /**
     * Sets the value of the mob1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMob1(String value) {
        this.mob1 = value;
    }

    /**
     * Gets the value of the mob2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMob2() {
        return mob2;
    }

    /**
     * Sets the value of the mob2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMob2(String value) {
        this.mob2 = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the birthYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthYear() {
        return birthYear;
    }

    /**
     * Sets the value of the birthYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthYear(String value) {
        this.birthYear = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the fixedPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedPhone() {
        return fixedPhone;
    }

    /**
     * Sets the value of the fixedPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedPhone(String value) {
        this.fixedPhone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
    }

    /**
     * Gets the value of the privacy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivacy() {
        return privacy;
    }

    /**
     * Sets the value of the privacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivacy(String value) {
        this.privacy = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

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
     * Gets the value of the cmmdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cmmdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCMMDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CMMDTLS }
     * 
     * 
     */
    public List<CMMDTLS> getCMMDTLS() {
        if (cmmdtls == null) {
            cmmdtls = new ArrayList<CMMDTLS>();
        }
        return this.cmmdtls;
    }

    /**
     * Gets the value of the locdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLOCDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LOCDTLS }
     * 
     * 
     */
    public List<LOCDTLS> getLOCDTLS() {
        if (locdtls == null) {
            locdtls = new ArrayList<LOCDTLS>();
        }
        return this.locdtls;
    }

    /**
     * Gets the value of the occdtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the occdtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOCCDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OCCDTLS }
     * 
     * 
     */
    public List<OCCDTLS> getOCCDTLS() {
        if (occdtls == null) {
            occdtls = new ArrayList<OCCDTLS>();
        }
        return this.occdtls;
    }

}