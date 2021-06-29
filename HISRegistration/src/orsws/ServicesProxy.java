package orsws;

public class ServicesProxy implements orsws.Services {
  private String _endpoint = null;
  private orsws.Services services = null;
  
  public ServicesProxy() {
    _initServicesProxy();
  }
  
  public ServicesProxy(String endpoint) {
    _endpoint = endpoint;
    _initServicesProxy();
  }
  
  private void _initServicesProxy() {
    try {
      services = (new orsws.ORSservicesLocator()).getservicesPort();
      if (services != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)services)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)services)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (services != null)
      ((javax.xml.rpc.Stub)services)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public orsws.Services getServices() {
    if (services == null)
      _initServicesProxy();
    return services;
  }
  
  public java.lang.String getAppointmentDetailsDateWise(java.lang.String inToken, int hos_id, java.lang.String appointmentBookingDate, java.lang.String outputFormat) throws java.rmi.RemoteException{
    if (services == null)
      _initServicesProxy();
    return services.getAppointmentDetailsDateWise(inToken, hos_id, appointmentBookingDate, outputFormat);
  }
  
  public java.lang.String getDateWiseAppointmentCount(java.lang.String inToken, java.lang.String appointmentRequestDate) throws java.rmi.RemoteException{
    if (services == null)
      _initServicesProxy();
    return services.getDateWiseAppointmentCount(inToken, appointmentRequestDate);
  }
  
  
}