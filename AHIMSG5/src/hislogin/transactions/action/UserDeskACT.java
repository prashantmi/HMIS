/**********************************************************
 Project:	   AHIMS_G5	
 File:         UserDeskACT.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.transactions.action;

import java.io.PrintWriter;
import hislogin.transactions.actionsupport.UserDeskSUP;
import hislogin.transactions.utl.UserDeskUTL;
import hissso.config.HISSSOConfig;

public class UserDeskACT extends UserDeskSUP
{
	private static final long serialVersionUID = 1L;
	private static final String PAGE_HIS_LOGIN_DESK = "SUCCESS";
	private static final String PAGE_HIS_ERROR = "ERROR";

	public String execute() throws Exception
	{
		return load();
	}

	@SuppressWarnings("unchecked")
	public String load() throws Exception
	{
		if(UserDeskUTL.setDeskInititals(this, objRequest, objResponse))
			return PAGE_HIS_LOGIN_DESK; 
		else
			return PAGE_HIS_ERROR;
	}
	
	@SuppressWarnings("unchecked")
	public String cashCollectionDtl() throws Exception
	{
		UserDeskUTL.fetchUserWiseCashCollected(this, objRequest, objResponse);
		return null;	
		
	}
	
	@SuppressWarnings("unchecked")
	public String getAlertDtl() throws Exception
	{
		UserDeskUTL.getAlertDtls(this, objRequest, objResponse);
		return null;	
		
	}
	// Added For getting High Priority Alert By Raj Kumar
	@SuppressWarnings("unchecked")
	public String getHighPriorityAlert() throws Exception
	{
		String jsonAlertString=UserDeskUTL.getAlertDtlsData(this, objRequest, objResponse);
		
		objResponse.setContentType("application/json");
		PrintWriter pw = objResponse.getWriter();
		if(jsonAlertString==null || jsonAlertString.equals(""))
			jsonAlertString="{}";
		
		pw.write(jsonAlertString);
		
		return null;	
				
	}
	public String reloadCash() throws Exception
	{
		if(super.getObjRequest().getSession().getAttribute(HISSSOConfig.SHOW_USERWISE_CASH_COLLECTED_DTL)!=null && super.getObjRequest().getSession().getAttribute(HISSSOConfig.SHOW_USERWISE_CASH_COLLECTED_DTL).equals("1"))
		{
			//System.out.println("Inside the Cash Refresh");
			UserDeskUTL.reloadUserWiseCashCollected(this, objRequest, objResponse);
		}
		else
		{
			//System.out.println("No Cash Refresh Needed");
		}
	    return null;
	}
	
	public String refreshHome() throws Exception
	{		
		UserDeskUTL.refreshHomeTab(this,objRequest, objResponse);
		return null;
	}
	
	

	//	@SuppressWarnings("unchecked")
//	public String login() throws Exception
//	{
//		if(LoginUTL.validateUser(this, objRequest, objResponse, mapSession))
//			return PAGE_HIS_LOGIN_DESK;
//		else
//			return PAGE_INITIAL;
//	}
//
//	@SuppressWarnings("unchecked")
//	public String logout() throws Exception
//	{
//		LoginUTL.logoutUser(this, objRequest, objResponse, mapSession);
//		LoginUTL.setInititals(this, objRequest, objResponse, mapSession);
//		return PAGE_INITIAL;
//	}
}
