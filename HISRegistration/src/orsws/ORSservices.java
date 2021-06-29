/**
 * ORSservices.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package orsws;

public interface ORSservices extends javax.xml.rpc.Service {
    public java.lang.String getservicesPortAddress();

    public orsws.Services getservicesPort() throws javax.xml.rpc.ServiceException;

    public orsws.Services getservicesPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
