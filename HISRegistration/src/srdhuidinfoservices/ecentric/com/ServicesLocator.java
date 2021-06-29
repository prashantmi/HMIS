/**
 * ServicesLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srdhuidinfoservices.ecentric.com;

public class ServicesLocator extends org.apache.axis.client.Service implements srdhuidinfoservices.ecentric.com.Services {

    public ServicesLocator() {
    }


    public ServicesLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicesLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicesSOAP11port_http
   // private java.lang.String ServicesSOAP11port_http_address = "http://164.100.132.193:8010/SRDHAadhaarInfoServices/services/Services.ServicesSOAP11port_http/";
 
    //By Mukund on 08.02.2017
    //private java.lang.String ServicesSOAP11port_http_address = "http://125.21.84.231:8010/SRDHAadhaarInfoServices/services/Services.ServicesSOAP11port_http/";
    private java.lang.String ServicesSOAP11port_http_address = "http://164.100.132.193:8010/TSSRDHAadhaarInfoServices/services/Services?wsdl";
    //End Mukund
    public java.lang.String getServicesSOAP11port_httpAddress() {
        return ServicesSOAP11port_http_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicesSOAP11port_httpWSDDServiceName = "ServicesSOAP11port_http";

    public java.lang.String getServicesSOAP11port_httpWSDDServiceName() {
        return ServicesSOAP11port_httpWSDDServiceName;
    }

    public void setServicesSOAP11port_httpWSDDServiceName(java.lang.String name) {
        ServicesSOAP11port_httpWSDDServiceName = name;
    }

    public srdhuidinfoservices.ecentric.com.ServicesPortType getServicesSOAP11port_http() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicesSOAP11port_http_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicesSOAP11port_http(endpoint);
    }

    public srdhuidinfoservices.ecentric.com.ServicesPortType getServicesSOAP11port_http(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            srdhuidinfoservices.ecentric.com.ServicesSOAP11BindingStub _stub = new srdhuidinfoservices.ecentric.com.ServicesSOAP11BindingStub(portAddress, this);
            _stub.setPortName(getServicesSOAP11port_httpWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicesSOAP11port_httpEndpointAddress(java.lang.String address) {
        ServicesSOAP11port_http_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (srdhuidinfoservices.ecentric.com.ServicesPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                srdhuidinfoservices.ecentric.com.ServicesSOAP11BindingStub _stub = new srdhuidinfoservices.ecentric.com.ServicesSOAP11BindingStub(new java.net.URL(ServicesSOAP11port_http_address), this);
                _stub.setPortName(getServicesSOAP11port_httpWSDDServiceName());
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
        if ("ServicesSOAP11port_http".equals(inputPortName)) {
            return getServicesSOAP11port_http();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://srdhuidinfoservices/ecentric/com/", "Services");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://srdhuidinfoservices/ecentric/com/", "ServicesSOAP11port_http"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServicesSOAP11port_http".equals(portName)) {
            setServicesSOAP11port_httpEndpointAddress(address);
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
