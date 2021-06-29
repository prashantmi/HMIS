package srdhuidinfoservices.ecentric.com;

public class ServicesPortTypeProxy implements srdhuidinfoservices.ecentric.com.ServicesPortType {
  private String _endpoint = null;
  private srdhuidinfoservices.ecentric.com.ServicesPortType servicesPortType = null;
  
  public ServicesPortTypeProxy() {
    _initServicesPortTypeProxy();
  }
  
  public ServicesPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicesPortTypeProxy();
  }
  
  private void _initServicesPortTypeProxy() {
    try {
      servicesPortType = (new srdhuidinfoservices.ecentric.com.ServicesLocator()).getServicesSOAP11port_http();
      if (servicesPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)servicesPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)servicesPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (servicesPortType != null)
      ((javax.xml.rpc.Stub)servicesPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public srdhuidinfoservices.ecentric.com.ServicesPortType getServicesPortType() {
    if (servicesPortType == null)
      _initServicesPortTypeProxy();
    return servicesPortType;
  }
  
  public java.lang.String uidOrEIDValidation(java.lang.String UID, java.lang.String EID) throws java.rmi.RemoteException{
    if (servicesPortType == null)
      _initServicesPortTypeProxy();
    return servicesPortType.uidOrEIDValidation(UID, EID);
  }
  
  public java.lang.String searchAadhaarInfoByDistrictMandalVillagePincodeWise(java.lang.String citizenName, java.lang.String UID, java.lang.String EID, java.lang.String districtCode, java.lang.String mandalCode, java.lang.String villageCode, java.lang.String pincode) throws java.rmi.RemoteException{
    if (servicesPortType == null)
      _initServicesPortTypeProxy();
    return servicesPortType.searchAadhaarInfoByDistrictMandalVillagePincodeWise(citizenName, UID, EID, districtCode, mandalCode, villageCode, pincode);
  }
  
  public java.lang.String getAadhaarPhoto(java.lang.String pincode, java.lang.String UID) throws java.rmi.RemoteException{
    if (servicesPortType == null)
      _initServicesPortTypeProxy();
    return servicesPortType.getAadhaarPhoto(pincode, UID);
  }
  
  public java.lang.String getAadhaarInfo(java.lang.String UID, java.lang.String EID) throws java.rmi.RemoteException{
    if (servicesPortType == null)
      _initServicesPortTypeProxy();
    return servicesPortType.getAadhaarInfo(UID, EID);
  }
  
  public java.lang.String getMultiSchemeBenifitsByAadhaar(java.lang.String UID, java.lang.String EID) throws java.rmi.RemoteException{
    if (servicesPortType == null)
      _initServicesPortTypeProxy();
    return servicesPortType.getMultiSchemeBenifitsByAadhaar(UID, EID);
  }
  
  
}