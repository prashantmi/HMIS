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
 * <p>Java class for SetupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="general" type="{http://www.cdacnoida.in/his/setup_bill}GeneralType"/>
 *         &lt;element name="opd" type="{http://www.cdacnoida.in/his/setup_bill}OpdType"/>
 *         &lt;element name="billformat" type="{http://www.cdacnoida.in/his/setup_bill}BillFormatType"/>
 *         &lt;element name="ipd" type="{http://www.cdacnoida.in/his/setup_bill}IpdType"/>
 *         &lt;element name="emergency" type="{http://www.cdacnoida.in/his/setup_bill}EmergencyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetupType", propOrder = {
    "general",
    "opd",
    "billformat",
    "ipd",
    "emergency"
})
public class SetupType {

    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected GeneralType general;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected OpdType opd;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected BillFormatType billformat;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected IpdType ipd;
    @XmlElement(namespace = "http://www.cdacnoida.in/his/setup_bill", required = true)
    protected EmergencyType emergency;

    /**
     * Gets the value of the general property.
     * 
     * @return
     *     possible object is
     *     {@link GeneralType }
     *     
     */
    public GeneralType getGeneral() {
        return general;
    }

    /**
     * Sets the value of the general property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralType }
     *     
     */
    public void setGeneral(GeneralType value) {
        this.general = value;
    }

    /**
     * Gets the value of the opd property.
     * 
     * @return
     *     possible object is
     *     {@link OpdType }
     *     
     */
    public OpdType getOpd() {
        return opd;
    }

    /**
     * Sets the value of the opd property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpdType }
     *     
     */
    public void setOpd(OpdType value) {
        this.opd = value;
    }

    /**
     * Gets the value of the billformat property.
     * 
     * @return
     *     possible object is
     *     {@link BillFormatType }
     *     
     */
    public BillFormatType getBillformat() {
        return billformat;
    }

    /**
     * Sets the value of the billformat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillFormatType }
     *     
     */
    public void setBillformat(BillFormatType value) {
        this.billformat = value;
    }

    /**
     * Gets the value of the ipd property.
     * 
     * @return
     *     possible object is
     *     {@link IpdType }
     *     
     */
    public IpdType getIpd() {
        return ipd;
    }

    /**
     * Sets the value of the ipd property.
     * 
     * @param value
     *     allowed object is
     *     {@link IpdType }
     *     
     */
    public void setIpd(IpdType value) {
        this.ipd = value;
    }

    /**
     * Gets the value of the emergency property.
     * 
     * @return
     *     possible object is
     *     {@link EmergencyType }
     *     
     */
    public EmergencyType getEmergency() {
        return emergency;
    }

    /**
     * Sets the value of the emergency property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmergencyType }
     *     
     */
    public void setEmergency(EmergencyType value) {
        this.emergency = value;
    }

}
