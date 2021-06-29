package com.ecentric.service;

/**
 * @author s.singaravelan
 */

import javax.servlet.ServletContext;
import com.ecentric.servicemodels.AadhaarSUP;


public class AadhaarFeedAction extends AadhaarSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 

	 public String openPatAadhaarPopup()
	 {
		 System.out.println("AadhaarFeedAction :: openPatAdhaarPopup()");
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 String adhaarRegisteredFlag= (String)objRequest.getParameter("adhaarRegisteredFlag");
		 String fromProcess= (String)objRequest.getParameter("fromProcess");
		 String aadhaarId= (String)objRequest.getParameter("aadhaarId");
		 
		 this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		 this.adhaarRegisteredFlag=(adhaarRegisteredFlag==null && adhaarRegisteredFlag.equals(""))?"0":adhaarRegisteredFlag;
		 this.fromProcess=fromProcess==null?"":fromProcess;;
		 this.aadhaarId=aadhaarId==null?"":aadhaarId;
		 
		return "adhaarPopup";
	 }
	 
	 public void getPatDtlOnAadhaarId()
	 {
		 System.out.println("AadhaarFeedAction :: getPatDtlOnAdhaarId()");
		 message = "Inside getPatDtlOnAdhaarId() method";

		 AadhaarFeedService.getPatDtlOnAadhaarId(objRequest,objResponse);
	 }

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	 
	
}
