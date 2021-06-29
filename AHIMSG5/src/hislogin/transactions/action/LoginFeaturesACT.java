/**********************************************************
 Project:	   AHIMS_G5	
 File:         LoginFeaturesACT.java
 Created:      Jan, 2014
 Last Changed: Mar, 2016
 Author:       Pragya Sharma

This code is copyright (c) 2014 C-DAC Noida.
***********************************************************/

package hislogin.transactions.action;

import hislogin.transactions.actionsupport.LoginFeatureSUP;
import hislogin.transactions.utl.LoginFeaturesUTL;
import hisglobal.config.HISConfig;
import hisglobal.utility.helper.DateHelperMethods;
import hissso.config.HISSSOConfig;
import hissso.controller.util.LoginUTL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.math.NumberUtils;

//import bsh.StringUtil;
//import antlr.StringUtils;


import java.io.IOException;
import java.util.Date;


public class LoginFeaturesACT extends LoginFeatureSUP
{
	private static final long serialVersionUID = 0L;
	private static final String PAGE_HIS_LOGIN_FORGET_PASSWORD = "FORGETPASSWORD";
	private static final String PAGE_HIS_LOGIN_FIRST_LOGIN = "FIRSTLOGIN";
	private static final String PAGE_HIS_LOGIN_CHANGE_PASSWORD = "CHANGEPASSWORD";	
	private static final String PAGE_HIS_LOGIN_CHANGE_USER_DETAILS = "CHANGEUSERDETAILS";
	private static final String PAGE_HIS_USER_LOG_DETAILS = "USERLOGDETAILS";
	
	
	
	public String execute() throws Exception
	{
		return null;
	}

	// Initializing Forgot Password Session
	public String initForgetPassword() throws Exception
	{
		LoginFeaturesUTL.initForgotPassword(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_FORGET_PASSWORD;
	}

	public String validateForgetPassword() throws Exception
	{
		HttpSession objSession = null;
		objSession = objRequest.getSession();
		
		// Captcha Check
		if(HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON"))
		{
			String parm = this.getCaptchaResponse();
			if(parm==null)
		    {
		       addActionError("Captcha is Empty! Please enter captcha!");
		       return PAGE_HIS_LOGIN_FORGET_PASSWORD;
		    }			
	    String c= (String)objSession.getAttribute("CAPTCHA_KEY") ;
	    int captchaResponse = Integer.parseInt(parm);
	    parm = Integer.toString(captchaResponse);
	    //if(!this.varQuestionId.matches("[0-9]+"))
		    if(!parm.equals(c) )
		    {
		       addActionError("Invalid Captcha! Please try again!");
		       return PAGE_HIS_LOGIN_FORGET_PASSWORD;
		    }
		}
	    if(!this.varUserName.matches("[A-Za-z0-9_]+") || this.varUserName.length()>20)
		{
	    	addActionError("Invalid User Name!");
	    	return PAGE_HIS_LOGIN_FORGET_PASSWORD;
		}
	    if(!NumberUtils.isNumber(this.varQuestionId))
	    {
	       addActionError("Invalid Question!");
	       return PAGE_HIS_LOGIN_FORGET_PASSWORD;
	    }
	    
		LoginFeaturesUTL.validateForgotPassword(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_FORGET_PASSWORD;
	}

	public String saveForgetPassword() throws Exception
	{
		LoginFeaturesUTL.saveForgotPassword(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_FORGET_PASSWORD;
	}

	public String initFirstLogin() throws Exception
	{
		LoginFeaturesUTL.initFirstLogin(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_FIRST_LOGIN;
	}

	public String saveFirstLogin() throws Exception
	{
		HttpSession objSession = null;
		objSession = objRequest.getSession();
		String parm = this.getCaptchaResponse();
	    String c= (String)objSession.getAttribute("CAPTCHA_KEY") ;
	    if(!NumberUtils.isNumber(this.varQuestionId))
	    {
	       addActionError("Invalid Question!");
	       return PAGE_HIS_LOGIN_FIRST_LOGIN;
	    }
	    if(parm!=null && !parm.equals(c) )
	    {
	     // LoginUTL.setInititals(this, objRequest, objResponse);
	      addActionError("Invalid Captcha! Please try again!");
	       return PAGE_HIS_LOGIN_FIRST_LOGIN;
	    }
		LoginFeaturesUTL.saveFirstLogin(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_FIRST_LOGIN;
	}

	public String initChangePassword() throws Exception
	{
		LoginFeaturesUTL.initChangePassword(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_CHANGE_PASSWORD;
	}

	public String saveChangePassword() throws Exception
	{
		LoginFeaturesUTL.saveChangePassword(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_CHANGE_PASSWORD;
	}
	
	
	public String initChangeUserDetails() throws Exception
	{
		LoginFeaturesUTL.initChangeUserDetails(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_CHANGE_USER_DETAILS;
	}
	public String validateChangeUserDetails() throws Exception
	{
		LoginFeaturesUTL.validatePassword(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_CHANGE_USER_DETAILS;
	}
	
	
	public String saveChangeUserDetails() throws Exception
	{
		LoginFeaturesUTL.saveChangeUserDetails(this, objRequest, objResponse);
		return PAGE_HIS_LOGIN_CHANGE_USER_DETAILS;
	}
	
	public void getMenuList()
	{
		LoginFeaturesUTL.getMenuList(objRequest, objResponse);
	}
	public String initUserLogDetails() throws Exception
	{
		LoginFeaturesUTL.inituserLogDetails(this,objRequest,objResponse,null,null);
		return PAGE_HIS_USER_LOG_DETAILS;
	}
	public String allUserLogDetails() throws Exception
	{
		
		HttpSession objSession=objRequest.getSession();
		Date objSysDate = (Date) objSession.getAttribute(HISSSOConfig.LOGGEDIN_SYSDATE_OBJECT);
		frDate = DateHelperMethods.getDateString(objSysDate,"dd-MMM-yyyy");
		toDate = DateHelperMethods.getDateString(objSysDate,"dd-MMM-yyyy");	System.out.println("in act fdate"+frDate+" "+toDate);
		LoginFeaturesUTL.allUserLogDetails(this, objRequest, objResponse,frDate,toDate);
		
		return PAGE_HIS_USER_LOG_DETAILS;
	}
	public String datewiseUserLogDetails() throws Exception
	{	//System.out.println("in datewise act fdate"+frDate+" "+toDate);
		LoginFeaturesUTL.datewiseUserLogDetails(this, objRequest, objResponse,frDate,toDate);
		//System.out.println("in datewise act fdate  2"+frDate+" "+toDate);
		return PAGE_HIS_USER_LOG_DETAILS;
	}
	public void captcha() throws ServletException, IOException {
 		  
		LoginFeaturesUTL.setCaptcha(this,objRequest, objResponse);
		  
	  }

}
