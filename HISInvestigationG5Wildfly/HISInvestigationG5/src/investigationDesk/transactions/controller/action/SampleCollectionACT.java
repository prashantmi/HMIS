package investigationDesk.transactions.controller.action;

/**
<!--
 
## Copyright Information				: C-DAC, Noida  
## Project Name				       		: NIMS
## Name of Developer		 			: PAWAN KUMAR B N
## Module Name					        : INVESTIGATION
## Process/Database Object Name	    	: Sample Collection

## Purpose						        : 
## Date of Creation						: 23-Feb-2015
## Modification Log						:				
## Modify Date					        :  
## Reason	(CR/PRS)			    	: 
## Modify By				        	: 


*/

import java.sql.SQLException;

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;



//import investigationDesk.transactions.controller.fb.SampleCollectionFB;
import investigationDesk.transactions.controller.utl.InvestigationRaisingDtlUTL;
import investigationDesk.transactions.controller.fb.SampleCollectionFB;
import investigationDesk.transactions.controller.utl.SampleCollectionUTL;
 
public class SampleCollectionACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}          
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionFB fb=(SampleCollectionFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
         
		
		if(fb.getShowStatus()!=null){
			if(fb.getShowStatus().equals("1"))
			{
				fb.setShowStatus("1");
			}else
				fb.setShowStatus("0");
		}
		else
			fb.setShowStatus("0");
		
        //
		if(fb.getIsSampleAreaSelected()!=null){
			if(fb.getIsSampleAreaSelected().equals("1"))
			{
				fb.setIsSampleAreaSelected("1");
			}else
				fb.setIsSampleAreaSelected("0");
		}
		else
			fb.setIsSampleAreaSelected("0");

		
		
		//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
		if(!fb.getIsSampleAreaSelected().equals("1"))
		{
			WebUTIL.refreshTransState(request);
			fb.setLabTestCodeArray("");
			SampleCollectionUTL.getSampleCollectionArea(fb,request);
		
			fb.setShowStatus("1");
			SampleCollectionUTL.showPatDetails(fb,request);//concatenating functions
	}
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATList  ");
		//WebUTIL.refreshTransState(request);
		SampleCollectionFB fb = (SampleCollectionFB) form;
		//fb.setShowStatus("0");

		SampleCollectionUTL.getPatList(fb,request);
		/*fb.setShowStatus("1");
		SampleCollectionUTL.showPatDetails(fb,request);//concatenating functions
*/		return mapping.findForward("NEW");
	}
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SHOWPATDETAILS  ");
		SampleCollectionFB fb = (SampleCollectionFB) form;
		fb.setShowStatus("1");

		SampleCollectionUTL.showPatDetails(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionFB fb=(SampleCollectionFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionFB fb=(SampleCollectionFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	 {
		 SampleCollectionFB fb=(SampleCollectionFB)form;
		 SampleCollectionUTL.saveSampleCollectionDetails(fb, request);
		 fb.setShowStatus("0");
	    return this.NEW(mapping, form, request, response);
	 } 
	 
	 

		/**
		 * AJX_DUPLICACY_SAMPLENO     
	 	* Ajax Function for Checking Duplicacy
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward AJX_DUPLICACY_SAMPLENO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionUTL.checkSampleNoDuplicacy(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
		
		
		/**
		 * AJX_CHECK_AUTO_SAMPLENO_GEN     
	 	* Ajax Function for Checking Duplicacy
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward AJX_CHECK_AUTO_SAMPLENO_GEN(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			SampleCollectionFB fb=(SampleCollectionFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionUTL.checkAutoGenFormate(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
		
}
