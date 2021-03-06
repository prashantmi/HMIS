//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.01.05 at 02:41:57 PM IST 
//


package mms.setup;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mms.setup package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MmsConfig_QNAME = new QName("http://www.cdacnoida.in/his/MmsConfig", "mmsConfig");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mms.setup
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MmsConfigGeneral }
     * 
     */
    public MmsConfigGeneral createMmsConfigGeneral() {
        return new MmsConfigGeneral();
    }

    /**
     * Create an instance of {@link MmsConfigType }
     * 
     */
    public MmsConfigType createMmsConfigType() {
        return new MmsConfigType();
    }

    /**
     * Create an instance of {@link MmsIssueProcessType }
     * 
     */
    public MmsIssueProcessType createMmsIssueProcessType() {
        return new MmsIssueProcessType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MmsConfigType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.cdacnoida.in/his/MmsConfig", name = "mmsConfig")
    public JAXBElement<MmsConfigType> createMmsConfig(MmsConfigType value) {
        return new JAXBElement<MmsConfigType>(_MmsConfig_QNAME, MmsConfigType.class, null, value);
    }

}
