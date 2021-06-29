/**
 * Services.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package orsws;

public interface Services extends java.rmi.Remote {
    public java.lang.String getAppointmentDetailsDateWise(java.lang.String inToken, int hos_id, java.lang.String appointmentBookingDate, java.lang.String outputFormat) throws java.rmi.RemoteException;
    public java.lang.String getDateWiseAppointmentCount(java.lang.String inToken, java.lang.String appointmentRequestDate) throws java.rmi.RemoteException;
}
