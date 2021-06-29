package Investigation_webservice.service;

public class InvCrNoServiceProxy implements Investigation_webservice.service.InvCrNoService {
  private String _endpoint = null;
  private Investigation_webservice.service.InvCrNoService invCrNoService = null;
  
  public InvCrNoServiceProxy() {
    _initInvCrNoServiceProxy();
  }
  
  public InvCrNoServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initInvCrNoServiceProxy();
  }
  
  private void _initInvCrNoServiceProxy() {
    try {
      invCrNoService = (new Investigation_webservice.service.InvCrNoServiceServiceLocator()).getInvCrNoServicePort();
      if (invCrNoService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)invCrNoService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)invCrNoService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (invCrNoService != null)
      ((javax.xml.rpc.Stub)invCrNoService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Investigation_webservice.service.InvCrNoService getInvCrNoService() {
    if (invCrNoService == null)
      _initInvCrNoServiceProxy();
    return invCrNoService;
  }
  
  public java.lang.String getDetailsBasedOnCRNumber(java.lang.String crNo) throws Investigation_webservice.service.Exception{
    if (invCrNoService == null)
      _initInvCrNoServiceProxy();
    return invCrNoService.getDetailsBasedOnCRNumber(crNo);
  }
  
  public java.lang.String authfunction(java.lang.String ipAddressRequestCameFrom, java.lang.String source, java.lang.String key){
    if (invCrNoService == null)
      _initInvCrNoServiceProxy();
    return invCrNoService.authfunction(ipAddressRequestCameFrom, source, key);
  }
  
  public org.codehaus.jettison.json.JSONArray messageeror(java.lang.String message) throws org.codehaus.jettison.json.JSONException{
    if (invCrNoService == null)
      _initInvCrNoServiceProxy();
    return invCrNoService.messageeror(message);
  }
  
  public void LogCreate(java.lang.String ipAddressRequestCameFrom, java.lang.String source, java.lang.String key, java.lang.String url, java.lang.String type){
    if (invCrNoService == null)
      _initInvCrNoServiceProxy();
    invCrNoService.LogCreate(ipAddressRequestCameFrom, source, key, url, type);
  }
  
  
}