/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Collection Cum Acceptance Process
 ## Purpose						        :  
 ## Date of Creation					:25-May-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.transactions.controller.action;

import hisglobal.backutil.exception.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.SampleCollectionCumAcceptanceFB;
import new_investigation.transactions.controller.utl.SampleCollectionCumAcceptanceUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SampleCollectionCumAcceptanceACT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		SampleCollectionCumAcceptanceFB fb=(SampleCollectionCumAcceptanceFB)form;
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
			SampleCollectionCumAcceptanceUTIL.getSampleCollectionArea(fb,request);
		}
		else
			SampleCollectionCumAcceptanceUTIL.getPatList(fb,request);

		fb.setCurrentPage(1);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward GETPATLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: GETPATList  ");
		//WebUTIL.refreshTransState(request);
		SampleCollectionCumAcceptanceFB fb = (SampleCollectionCumAcceptanceFB) form;
		//fb.setShowStatus("0");

		SampleCollectionCumAcceptanceUTIL.getPatList(fb,request);
		
		Date date1;
		Date date2;
		try {
			if(fb.getFromDate()!=null && fb.getToDate()!=null)
			{
			date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(fb.getFromDate());
			date2 = new SimpleDateFormat("dd-MMM-yyyy").parse(fb.getToDate());
			WebUTIL.setAttributeInSession(request,Config.SELECTED_FROM_DATE,date1);
			WebUTIL.setAttributeInSession(request,Config.SELECTED_TO_DATE,date2);
			}
			
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//fb.setCurrentPageNo("1");
		fb.setCurrentPage(1);
		return mapping.findForward("NEW");
	}
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvestigationRaisingDtlACT: SHOWPATDETAILS  ");
		SampleCollectionCumAcceptanceFB fb = (SampleCollectionCumAcceptanceFB) form;
		fb.setShowStatus("1");

		SampleCollectionCumAcceptanceUTIL.showPatDetails(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionCumAcceptanceFB fb=(SampleCollectionCumAcceptanceFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleCollectionCumAcceptanceFB fb=(SampleCollectionCumAcceptanceFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 SampleCollectionCumAcceptanceFB fb=(SampleCollectionCumAcceptanceFB)form;
		 Date date = new Date();
		 String systemdate=WebUTIL.getCustomisedSysDate(date, "dd-MMM-yyyy");

		 String fromDate=systemdate;
		 String toDate=systemdate;
		 SampleCollectionCumAcceptanceUTIL.saveSampleCollectionDetails(fb, request);
		 fb.setShowStatus("0");
		 fb.setToDate(fromDate);
		 fb.setFromDate(fromDate);
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
			SampleCollectionCumAcceptanceFB fb=(SampleCollectionCumAcceptanceFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionCumAcceptanceUTIL.checkSampleNoDuplicacy(fb, objRequest_p);
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
			SampleCollectionCumAcceptanceFB fb=(SampleCollectionCumAcceptanceFB)objForm_p;
			
			StringBuffer strBuff = SampleCollectionCumAcceptanceUTIL.checkAutoGenFormate(fb, objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
}


