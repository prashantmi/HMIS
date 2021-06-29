package registration.transactions.controller.action;

/***************************Start of program*****************************\
## Copyright Information				: C-DAC, Noida  
## Project Name							: HIMS G5
## Name of Developer		 			: Singaravelan
## Module Name							: Registration
## Process/Database Object Name			:
## Purpose								: Offline Merging of CRNO
## Date of Creation						: 10-Dec-2014
## Modification Log						:				
##		Modify Date						: 
##		Reason	(CR/PRS)				: 
##		Modify By						: 
*/

import java.util.logging.Logger;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


import com.opensymphony.xwork2.inject.Inject;

import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.MergeCRNOSUP;
import registration.transactions.controller.util.OfflineMergeCRNOUTL;

public class OfflineMergeCRNOAction extends MergeCRNOSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	 @Inject
	 private Logger logger;
	 
	public String execute() throws Exception
	{
		logger.info("OfflineMergeCRNOAction :: execute()");
		message = "Inside execute method";
		return init();
	}
	 
	public String init() throws Exception
	{		   
		logger.info("OfflineMergeCRNOAction :: init()");
		message = "Inside init method";			
		this.reset();	
		HttpSession ses=objRequest.getSession();
		OfflineMergeCRNOUTL.setSysdateAndDefaultCrNoFormat(objRequest);
		ses.removeAttribute(RegistrationConfig.ARR_TO_BE_MERGED_CR_NUMBER_VO);
		ses.removeAttribute(RegistrationConfig.ARR_MERGED_PATIENT);
		Status objStatus=new Status();		 	
	 	WebUTIL.setStatus(objRequest,objStatus);			 	
		return "NEW";			 
		
	}
	 
	public String GETPATDTL() throws Exception
	{
		logger.info("OfflineMergeCRNOAction :: GETPATDTL()");
		message = "Inside GETPATDTL method";	
		String strMode="";
		this.clearMessageField();
		
		this.afterGo="1";		
		OfflineMergeCRNOUTL.setPatientDtlByCrno(this,objRequest,logger);

		return "NEW";
	}
	
	public String GO() throws Exception
	{
		logger.info("OfflineMergeCRNOAction :: GO()");
		message = "Inside GO method";	
		this.clearMessageField();
		
		this.afterGo="1";		
		OfflineMergeCRNOUTL.getNotUsedCrNo(this,objRequest,logger);

		return "NEW";
	}
	
	public String DELETEROW() throws Exception
	{
		
		 logger.info("OfflineMergeCRNOAction :: DELETEROW()");
		 message = "Inside DELETEROW method";
		 OfflineMergeCRNOUTL.deleteRow(this,objRequest,logger);
		 this.afterGo="1";		
		 return GETPATDTL();
	}
	
	public String REVOKE() throws Exception
	{
		
		 logger.info("OfflineMergeCRNOAction :: REVOKE()");
		 message = "Inside REVOKE method";
		 OfflineMergeCRNOUTL.revokeMergedCRNo(this,objRequest,logger);
		
		 return execute();
	}
	
	public String CANCEL() throws Exception
	{
		this.clearMessageField();
		return execute();
	}
	
	public String CLEAR() throws Exception
	{
		 this.clearMessageField();
		 return execute();
	}
	
	 
	public String SAVE() throws Exception
	{
		
		logger.info("OfflineMergeCRNOAction :: SAVE()");
		message = "Inside SAVE method";
		OfflineMergeCRNOUTL.saveNotUsedCrNo(this,objRequest,logger);
		
		return execute();
	}
	 
	 
	 public void setServletContext(ServletContext context) {
			this.objContext=context;
			
		}

	}
