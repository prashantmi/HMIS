//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.28 at 03:32:50 PM IST 
//


package mms.setup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MmsConfigGeneral complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MmsConfigGeneral">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="committeeFilePath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="costRequired" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="indianDeliveryTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importedDeliveryTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="residualCostAuction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="committeeFilePathLin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="committeeFilePathWin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expAlertDays" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="storeCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strCountryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strStateCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strIssueRateConfigFlg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strConfigIssueRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strBudgetFlg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strContractValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strStampPaperAmt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strBillingIntegration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MmsConfigGeneral", propOrder = {
    "committeeFilePath",
    "costRequired",
    "indianDeliveryTime",
    "importedDeliveryTime",
    "residualCostAuction",
    "committeeFilePathLin",
    "committeeFilePathWin",
    "expAlertDays",
    "storeCategory",
    "strCountryCode",
    "strStateCode",
    "strIssueRateConfigFlg",
    "strConfigIssueRate",
    "strBudgetFlg",
    "strContractValue",
    "strStampPaperAmt",
    "strBillingIntegration",
    "strTinNo"
})
public class MmsConfigGeneral {

    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String committeeFilePath;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String costRequired;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String indianDeliveryTime;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String importedDeliveryTime;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String residualCostAuction;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String committeeFilePathLin;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String committeeFilePathWin;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String expAlertDays;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String storeCategory;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strCountryCode;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strStateCode;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strIssueRateConfigFlg;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strConfigIssueRate;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strBudgetFlg;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strContractValue;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strStampPaperAmt;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/MmsConfig", required = true)
    protected String strBillingIntegration;
    protected String strTinNo;

    

	/**
     * Gets the value of the committeeFilePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitteeFilePath() {
        return committeeFilePath;
    }

    /**
     * Sets the value of the committeeFilePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitteeFilePath(String value) {
        this.committeeFilePath = value;
    }

    /**
     * Gets the value of the costRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCostRequired() {
        return costRequired;
    }

    /**
     * Sets the value of the costRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCostRequired(String value) {
        this.costRequired = value;
    }

    /**
     * Gets the value of the indianDeliveryTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndianDeliveryTime() {
        return indianDeliveryTime;
    }

    /**
     * Sets the value of the indianDeliveryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndianDeliveryTime(String value) {
        this.indianDeliveryTime = value;
    }

    /**
     * Gets the value of the importedDeliveryTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportedDeliveryTime() {
        return importedDeliveryTime;
    }

    /**
     * Sets the value of the importedDeliveryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportedDeliveryTime(String value) {
        this.importedDeliveryTime = value;
    }

    /**
     * Gets the value of the residualCostAuction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResidualCostAuction() {
        return residualCostAuction;
    }

    /**
     * Sets the value of the residualCostAuction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResidualCostAuction(String value) {
        this.residualCostAuction = value;
    }

    /**
     * Gets the value of the committeeFilePathLin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitteeFilePathLin() {
        return committeeFilePathLin;
    }

    /**
     * Sets the value of the committeeFilePathLin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitteeFilePathLin(String value) {
        this.committeeFilePathLin = value;
    }

    /**
     * Gets the value of the committeeFilePathWin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommitteeFilePathWin() {
        return committeeFilePathWin;
    }

    /**
     * Sets the value of the committeeFilePathWin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitteeFilePathWin(String value) {
        this.committeeFilePathWin = value;
    }

    /**
     * Gets the value of the expAlertDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpAlertDays() {
        return expAlertDays;
    }

    /**
     * Sets the value of the expAlertDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpAlertDays(String value) {
        this.expAlertDays = value;
    }

    /**
     * Gets the value of the storeCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreCategory() {
        return storeCategory;
    }

    /**
     * Sets the value of the storeCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreCategory(String value) {
        this.storeCategory = value;
    }

    /**
     * Gets the value of the strCountryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCountryCode() {
        return strCountryCode;
    }

    /**
     * Sets the value of the strCountryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCountryCode(String value) {
        this.strCountryCode = value;
    }

    /**
     * Gets the value of the strStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrStateCode() {
        return strStateCode;
    }

    /**
     * Sets the value of the strStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrStateCode(String value) {
        this.strStateCode = value;
    }

    /**
     * Gets the value of the strIssueRateConfigFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrIssueRateConfigFlg() {
        return strIssueRateConfigFlg;
    }

    /**
     * Sets the value of the strIssueRateConfigFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrIssueRateConfigFlg(String value) {
        this.strIssueRateConfigFlg = value;
    }

    /**
     * Gets the value of the strConfigIssueRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrConfigIssueRate() {
        return strConfigIssueRate;
    }

    /**
     * Sets the value of the strConfigIssueRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrConfigIssueRate(String value) {
        this.strConfigIssueRate = value;
    }

    /**
     * Gets the value of the strBudgetFlg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrBudgetFlg() {
        return strBudgetFlg;
    }

    /**
     * Sets the value of the strBudgetFlg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrBudgetFlg(String value) {
        this.strBudgetFlg = value;
    }

    /**
     * Gets the value of the strContractValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrContractValue() {
        return strContractValue;
    }

    /**
     * Sets the value of the strContractValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrContractValue(String value) {
        this.strContractValue = value;
    }

    /**
     * Gets the value of the strStampPaperAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrStampPaperAmt() {
        return strStampPaperAmt;
    }

    /**
     * Sets the value of the strStampPaperAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrStampPaperAmt(String value) {
        this.strStampPaperAmt = value;
    }
    
    /**
     * Sets the value of the strBillingIntegration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */

	public String getStrBillingIntegration() {
		return strBillingIntegration;
	}
	
	
	
	 /**
     * gets the value of the strBillingIntegration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
	public void setStrBillingIntegration(String strBillingIntegration) {
		this.strBillingIntegration = strBillingIntegration;
	}
    
	public String getStrTinNo() {
		return strTinNo;
	}

	public void setStrTinNo(String strTinNo) {
		this.strTinNo = strTinNo;
	}

}