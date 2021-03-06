//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.03.05 at 12:47:15 PM IST 
//


package ipd.setup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IPDConfigType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IPDConfigType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="onlineAdvice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="genWardAdmission" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="genWardApproval" type="{http://www.cdacnoida.in/his/IpdConfig}GenWardApprovalType"/>
 *         &lt;element name="privateWardAdmission" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="privateWardApproval" type="{http://www.cdacnoida.in/his/IpdConfig}PrivateWardApprovalType"/>
 *         &lt;element name="bookBed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billing" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="negativeBilling" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patientCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="govtEmpBasicPayLimit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pvtEmpMonIncomeLimit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="admissionAdviceValidity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="daysForPurgingRecordFromCurrentlyAdmissionDetails" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *          &lt;element name="purgeTimeForNotReported" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPDConfigType", propOrder = {
    "onlineAdvice",
    "genWardAdmission",
    "genWardApproval",
    "privateWardAdmission",
    "privateWardApproval",
    "bookBed",
    "billing",
    "negativeBilling",
    "patientCategory",
    "govtEmpBasicPayLimit",
    "pvtEmpMonIncomeLimit",
    "admissionAdviceValidity"
})
public class IPDConfigType {

    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String onlineAdvice;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String genWardAdmission;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected GenWardApprovalType genWardApproval;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String privateWardAdmission;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected PrivateWardApprovalType privateWardApproval;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String bookBed;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String billing;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String negativeBilling;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String patientCategory;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String govtEmpBasicPayLimit;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String pvtEmpMonIncomeLimit;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig")
    protected String admissionAdviceValidity;

    /**
     * Gets the value of the onlineAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineAdvice() {
        return onlineAdvice;
    }

    /**
     * Sets the value of the onlineAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineAdvice(String value) {
        this.onlineAdvice = value;
    }

    /**
     * Gets the value of the genWardAdmission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenWardAdmission() {
        return genWardAdmission;
    }

    /**
     * Sets the value of the genWardAdmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenWardAdmission(String value) {
        this.genWardAdmission = value;
    }

    /**
     * Gets the value of the genWardApproval property.
     * 
     * @return
     *     possible object is
     *     {@link GenWardApprovalType }
     *     
     */
    public GenWardApprovalType getGenWardApproval() {
        return genWardApproval;
    }

    /**
     * Sets the value of the genWardApproval property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenWardApprovalType }
     *     
     */
    public void setGenWardApproval(GenWardApprovalType value) {
        this.genWardApproval = value;
    }

    /**
     * Gets the value of the privateWardAdmission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivateWardAdmission() {
        return privateWardAdmission;
    }

    /**
     * Sets the value of the privateWardAdmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivateWardAdmission(String value) {
        this.privateWardAdmission = value;
    }

    /**
     * Gets the value of the privateWardApproval property.
     * 
     * @return
     *     possible object is
     *     {@link PrivateWardApprovalType }
     *     
     */
    public PrivateWardApprovalType getPrivateWardApproval() {
        return privateWardApproval;
    }

    /**
     * Sets the value of the privateWardApproval property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivateWardApprovalType }
     *     
     */
    public void setPrivateWardApproval(PrivateWardApprovalType value) {
        this.privateWardApproval = value;
    }

    /**
     * Gets the value of the bookBed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBookBed() {
        return bookBed;
    }

    /**
     * Sets the value of the bookBed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBookBed(String value) {
        this.bookBed = value;
    }

    /**
     * Gets the value of the billing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBilling() {
        return billing;
    }

    /**
     * Sets the value of the billing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBilling(String value) {
        this.billing = value;
    }

    /**
     * Gets the value of the negativeBilling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNegativeBilling() {
        return negativeBilling;
    }

    /**
     * Sets the value of the negativeBilling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNegativeBilling(String value) {
        this.negativeBilling = value;
    }

    /**
     * Gets the value of the patientCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientCategory() {
        return patientCategory;
    }

    /**
     * Sets the value of the patientCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientCategory(String value) {
        this.patientCategory = value;
    }

    /**
     * Gets the value of the govtEmpBasicPayLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGovtEmpBasicPayLimit() {
        return govtEmpBasicPayLimit;
    }

    /**
     * Sets the value of the govtEmpBasicPayLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGovtEmpBasicPayLimit(String value) {
        this.govtEmpBasicPayLimit = value;
    }

    /**
     * Gets the value of the pvtEmpMonIncomeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvtEmpMonIncomeLimit() {
        return pvtEmpMonIncomeLimit;
    }

    /**
     * Sets the value of the pvtEmpMonIncomeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvtEmpMonIncomeLimit(String value) {
        this.pvtEmpMonIncomeLimit = value;
    }

    /**
     * Gets the value of the admissionAdviceValidity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdmissionAdviceValidity() {
        return admissionAdviceValidity;
    }

    /**
     * Sets the value of the admissionAdviceValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdmissionAdviceValidity(String value) {
        this.admissionAdviceValidity = value;
    }

}
