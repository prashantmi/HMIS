package investigationDesk.transactions.controller.action;

/**
<!--
 
## Copyright Information				: C-DAC, Noida  
## Project Name				       		: NIMS
## Name of Developer		 			: PAWAN KUMAR B N
## Module Name					        : INVESTIGATION
## Process/Database Object Name	    	: Packing List Generation

## Purpose						        : 
## Date of Creation						: 11-Mar-2015
## Modification Log						:				
## Modify Date					        :  
## Reason	(CR/PRS)			    	: 
## Modify By				        	: 


*/

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import investigationDesk.InvestigationConfig;
import investigationDesk.transactions.controller.fb.PackingListGenerationFB;
import investigationDesk.transactions.controller.utl.PackingListGenerationUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
public class PackingListGenerationACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PackingListGenerationFB fb=(PackingListGenerationFB)form;
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
				//Get  Laboratory Combo based on sample Area Code
				PackingListGenerationUTL.getPackingListEssentials(fb,request);
				
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
			PackingListGenerationUTL.getSampleCollectionArea(fb,request);
		}
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATList  ");
		//WebUTIL.refreshTransState(request);
		PackingListGenerationFB fb = (PackingListGenerationFB) form;
		//fb.setShowStatus("0");
		
		//original print packing list
		if(fb.getPackingListGenerationType().equals("0"))
		PackingListGenerationUTL.getPatList(fb,request);
		
		//for duplicate get all packing list data 
		else
			PackingListGenerationUTL.getPackingListDetails(fb,request);
			
		return mapping.findForward("NEW");
	}
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SHOWPATDETAILS  ");
		PackingListGenerationFB fb = (PackingListGenerationFB) form;
		fb.setShowStatus("1");

		PackingListGenerationUTL.showPatDetails(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PackingListGenerationFB fb=(PackingListGenerationFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PackingListGenerationFB fb=(PackingListGenerationFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	 {
		 PackingListGenerationFB fb=(PackingListGenerationFB)form;
		 String forwardAction="";
		 if(fb.getPackingListGenerationType().equals(InvestigationConfig.PACKING_LIST_GENERATION_TYPE_NORMAL))
		 {
			 PackingListGenerationUTL.savePackingListGeneration(fb, request);
			 forwardAction="PACKINGLISTREPORT";
		 }
		 else
		 {
			 PackingListGenerationUTL.generateDuplicatePackingList(fb, request);
			 forwardAction="DUPLICATEPACKINGLISTREPORT";
		 }
		 fb.setShowStatus("0");
	    return  mapping.findForward(forwardAction);
	 } 
	 
	 
	 public ActionForward GENERATEALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	 {
		 PackingListGenerationFB fb=(PackingListGenerationFB)form;
		 PackingListGenerationUTL.saveAllPackingListGeneration(fb, request);
		 fb.setShowStatus("0");
	    return  mapping.findForward("PACKINGLISTREPORT");
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
			PackingListGenerationFB fb=(PackingListGenerationFB)objForm_p;
			
			StringBuffer strBuff = PackingListGenerationUTL.checkSampleNoDuplicacy(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
}
