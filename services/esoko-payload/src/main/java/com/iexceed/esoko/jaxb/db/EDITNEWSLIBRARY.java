//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.25 at 04:24:57 PM IST 
//


package com.iexceed.esoko.jaxb.db;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EDITNEWSLIBRARY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EDITNEWSLIBRARY">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lib_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="story" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EDITNEWSLIBRARY", propOrder = {
    "libId",
    "category",
    "title",
    "summary",
    "story",
    "sourceUrl",
    "author",
    "image"
})
public class EDITNEWSLIBRARY {

    @XmlElement(name = "lib_id", namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String libId;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String category;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String title;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String summary;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String story;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String sourceUrl;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected String author;
    @XmlElement(namespace = "http://www.iexceed.com/editNewsAndLibraryReq", required = true)
    protected byte[] image;

    /**
     * Gets the value of the libId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLibId() {
        return libId;
    }

    /**
     * Sets the value of the libId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLibId(String value) {
        this.libId = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
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
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the story property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStory() {
        return story;
    }

    /**
     * Sets the value of the story property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStory(String value) {
        this.story = value;
    }

    /**
     * Gets the value of the sourceUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * Sets the value of the sourceUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceUrl(String value) {
        this.sourceUrl = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = ((byte[]) value);
    }

}
