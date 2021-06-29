//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.05.15 at 10:11:21 AM IST 
//


package ipd.setup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IPDBillDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IPDBillDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="advanceRequestAdmissionAdvice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="advanceamountAdmission" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="patientAdjustedFinalBillAtAdmission" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bedChange" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="advanceRefundRequestAtAdmissionCancellation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="integrationWithBilling" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPDBillDetails", propOrder = {
    "advanceRequestAdmissionAdvice",
    "advanceamountAdmission",
    "patientAdjustedFinalBillAtAdmission",
    "bedChange",
    "advanceRefundRequestAtAdmissionCancellation",
    "integrationWithBilling"
})
public class IPDBillDetails {

    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig", required = true)
    protected String advanceRequestAdmissionAdvice;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig", required = true)
    protected String advanceamountAdmission;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig", required = true)
    protected String patientAdjustedFinalBillAtAdmission;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig", required = true)
    protected String bedChange;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig", required = true)
    protected String advanceRefundRequestAtAdmissionCancellation;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/IpdConfig", required = true)
    protected String integrationWithBilling;

    /**
     * Gets the value of the advanceRequestAdmissionAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdvanceRequestAdmissionAdvice() {
        return advanceRequestAdmissionAdvice;
    }

    /**
     * Sets the value of the advanceRequestAdmissionAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdvanceRequestAdmissionAdvice(String value) {
        this.advanceRequestAdmissionAdvice = value;
    }

    /**
     * Gets the value of the advanceamountAdmission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdvanceamountAdmission() {
        return advanceamountAdmission;
    }

    /**
     * Sets the value of the advanceamountAdmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdvanceamountAdmission(String value) {
        this.advanceamountAdmission = value;
    }

    /**
     * Gets the value of the patientAdjustedFinalBillAtAdmission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAdjustedFinalBillAtAdmission() {
        return patientAdjustedFinalBillAtAdmission;
    }

    /**
     * Sets the value of the patientAdjustedFinalBillAtAdmission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAdjustedFinalBillAtAdmission(String value) {
        this.patientAdjustedFinalBillAtAdmission = value;
    }

    /**
     * Gets the value of the bedChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBedChange() {
        return bedChange;
    }

    /**
     * Sets the value of the bedChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBedChange(String value) {
        this.bedChange = value;
    }

    /**
     * Gets the value of the advanceRefundRequestAtAdmissionCancellation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdvanceRefundRequestAtAdmissionCancellation() {
        return advanceRefundRequestAtAdmissionCancellation;
    }

    /**
     * Sets the value of the advanceRefundRequestAtAdmissionCancellation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdvanceRefundRequestAtAdmissionCancellation(String value) {
        this.advanceRefundRequestAtAdmissionCancellation = value;
    }

    /**
     * Gets the value of the integrationWithBilling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntegrationWithBilling() {
        return integrationWithBilling;
    }

    /**
     * Sets the value of the integrationWithBilling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntegrationWithBilling(String value) {
        this.integrationWithBilling = value;
    }

}