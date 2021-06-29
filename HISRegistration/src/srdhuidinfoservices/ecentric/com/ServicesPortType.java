/**
 * ServicesPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package srdhuidinfoservices.ecentric.com;

public interface ServicesPortType extends java.rmi.Remote {
    public java.lang.String uidOrEIDValidation(java.lang.String UID, java.lang.String EID) throws java.rmi.RemoteException;
    public java.lang.String searchAadhaarInfoByDistrictMandalVillagePincodeWise(java.lang.String citizenName, java.lang.String UID, java.lang.String EID, java.lang.String districtCode, java.lang.String mandalCode, java.lang.String villageCode, java.lang.String pincode) throws java.rmi.RemoteException;
    public java.lang.String getAadhaarPhoto(java.lang.String pincode, java.lang.String UID) throws java.rmi.RemoteException;
    public java.lang.String getAadhaarInfo(java.lang.String UID, java.lang.String EID) throws java.rmi.RemoteException;
    public java.lang.String getMultiSchemeBenifitsByAadhaar(java.lang.String UID, java.lang.String EID) throws java.rmi.RemoteException;
}
