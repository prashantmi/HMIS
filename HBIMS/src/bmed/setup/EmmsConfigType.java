//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.04 at 05:52:29 PM GMT+05:30 
//

package bmed.setup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for MmsConfigType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;MmsConfigType&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;mmsIssueProcess&quot; type=&quot;{http://www.cdacnoida.in/his/MmsConfig}MmsIssueProcessType&quot;/&gt;
 *         &lt;element name=&quot;mmsConfigGerneral&quot; type=&quot;{http://www.cdacnoida.in/his/MmsConfig}MmsConfigGeneral&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmmsConfigType", propOrder = { "emmsIssueProcess",
		"emmsConfigGerneral" })
public class EmmsConfigType {

	@XmlElement(namespace = "http://www.cdacnoida.in/his/EmmsConfig", required = true)
	protected EmmsIssueProcessType emmsIssueProcess;
	@XmlElement(namespace = "http://www.cdacnoida.in/his/EmmsConfig", required = true)
	protected EmmsConfigGeneral emmsConfigGerneral;

	/**
	 * @return the emmsIssueProcess
	 */
	public EmmsIssueProcessType getEmmsIssueProcess() {
		return emmsIssueProcess;
	}
	/**
	 * @param emmsIssueProcess the emmsIssueProcess to set
	 */
	public void setEmmsIssueProcess(EmmsIssueProcessType emmsIssueProcess) {
		this.emmsIssueProcess = emmsIssueProcess;
	}
	/**
	 * @return the emmsConfigGerneral
	 */
	public EmmsConfigGeneral getEmmsConfigGerneral() {
		return emmsConfigGerneral;
	}
	/**
	 * @param emmsConfigGerneral the emmsConfigGerneral to set
	 */
	public void setEmmsConfigGerneral(EmmsConfigGeneral emmsConfigGerneral) {
		this.emmsConfigGerneral = emmsConfigGerneral;
	}

}
