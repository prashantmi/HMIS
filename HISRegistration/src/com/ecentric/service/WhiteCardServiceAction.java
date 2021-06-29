package com.ecentric.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;

import com.ecentric.servicemodels.WhiteCardSearchSUP;

 
public class WhiteCardServiceAction extends WhiteCardSearchSUP 
{
    private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 

	 
	 public void getPatDtlOnWhiteCardId()
	 {
		 WhiteCardServiceUTIL.getPatDtlOnCardId(objRequest, objResponse);
			
	 }
		
	 public String openWhiteCardSearchPopup()
	 {
		return "WhiteCardSearchPopup";
	 }
	 
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
}

