//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.23 at 07:40:31 AM IST 
//


package billing.setup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmergencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmergencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="thirdPartyBilling" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discountClerk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="refundPenalty" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roundOff" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emgServiceTax" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="emgFreeCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmergencyType", propOrder = {
    "thirdPartyBilling",
    "discount",
    "discountClerk",
    "refundPenalty",
    "roundOff",
    "emgServiceTax",
    "emgFreeCategory"
})
public class EmergencyType {

    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String thirdPartyBilling;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String discount;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String discountClerk;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String refundPenalty;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String roundOff;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String emgServiceTax;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected String emgFreeCategory;

    /**
     * Gets the value of the thirdPartyBilling property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPartyBilling() {
        return thirdPartyBilling;
    }

    /**
     * Sets the value of the thirdPartyBilling property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPartyBilling(String value) {
        this.thirdPartyBilling = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscount(String value) {
        this.discount = value;
    }

    /**
     * Gets the value of the discountClerk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountClerk() {
        return discountClerk;
    }

    /**
     * Sets the value of the discountClerk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountClerk(String value) {
        this.discountClerk = value;
    }

    /**
     * Gets the value of the refundPenalty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefundPenalty() {
        return refundPenalty;
    }

    /**
     * Sets the value of the refundPenalty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefundPenalty(String value) {
        this.refundPenalty = value;
    }

    /**
     * Gets the value of the roundOff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoundOff() {
        return roundOff;
    }

    /**
     * Sets the value of the roundOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoundOff(String value) {
        this.roundOff = value;
    }

    /**
     * Gets the value of the emgServiceTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmgServiceTax() {
        return emgServiceTax;
    }

    /**
     * Sets the value of the emgServiceTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmgServiceTax(String value) {
        this.emgServiceTax = value;
    }

    /**
     * Gets the value of the emgFreeCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmgFreeCategory() {
        return emgFreeCategory;
    }

    /**
     * Sets the value of the emgFreeCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmgFreeCategory(String value) {
        this.emgFreeCategory = value;
    }

}
