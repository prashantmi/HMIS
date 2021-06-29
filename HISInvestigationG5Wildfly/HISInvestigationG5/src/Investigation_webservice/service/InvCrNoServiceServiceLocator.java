/**
 * InvCrNoServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package Investigation_webservice.service;

public class InvCrNoServiceServiceLocator extends org.apache.axis.client.Service implements Investigation_webservice.service.InvCrNoServiceService {

    public InvCrNoServiceServiceLocator() {
    }


    public InvCrNoServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InvCrNoServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InvCrNoServicePort
    private java.lang.String InvCrNoServicePort_address = "http://10.226.21.131:8380/HISInvestigationG5/investigationCRNoservicenew";

    public java.lang.String getInvCrNoServicePortAddress() {
        return InvCrNoServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InvCrNoServicePortWSDDServiceName = "InvCrNoServicePort";

    public java.lang.String getInvCrNoServicePortWSDDServiceName() {
        return InvCrNoServicePortWSDDServiceName;
    }

    public void setInvCrNoServicePortWSDDServiceName(java.lang.String name) {
        InvCrNoServicePortWSDDServiceName = name;
    }

    public Investigation_webservice.service.InvCrNoService getInvCrNoServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InvCrNoServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInvCrNoServicePort(endpoint);
    }

    public Investigation_webservice.service.InvCrNoService getInvCrNoServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            InvCrNoServiceServiceSoapBindingStub _stub = new Investigation_webservice.service.InvCrNoServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getInvCrNoServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInvCrNoServicePortEndpointAddress(java.lang.String address) {
        InvCrNoServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (Investigation_webservice.service.InvCrNoService.class.isAssignableFrom(serviceEndpointInterface)) {
                Investigation_webservice.service.InvCrNoServiceServiceSoapBindingStub _stub = new Investigation_webservice.service.InvCrNoServiceServiceSoapBindingStub(new java.net.URL(InvCrNoServicePort_address), this);
                _stub.setPortName(getInvCrNoServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InvCrNoServicePort".equals(inputPortName)) {
            return getInvCrNoServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.Investigation_webservice/", "InvCrNoServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.Investigation_webservice/", "InvCrNoServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InvCrNoServicePort".equals(portName)) {
            setInvCrNoServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
