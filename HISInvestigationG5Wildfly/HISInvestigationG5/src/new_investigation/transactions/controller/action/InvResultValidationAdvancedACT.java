package new_investigation.transactions.controller.action;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.utl.InvResultValidationUTIL;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InvResultValidationAdvancedACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		WebUTIL.refreshTransState(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationFB fb=(InvResultValidationFB)form;
		HttpSession session=WebUTIL.getSession(request);
		ResultEntryVO vo=null;
		//Resetting selected lab test code array
				fb.setLabTestCodeArray("");
				
				if(fb.getShowStatus()!=null){
					if(fb.getShowStatus().equals("1"))
					{
						fb.setShowStatus("1");
					}else
						fb.setShowStatus("0");
				}
				else
					fb.setShowStatus("0");
				
				//added by chandan
				if(fb.getIsSampleAreaSelected()!=null){
					if(fb.getIsSampleAreaSelected().equals("1"))
					{
						fb.setIsSampleAreaSelected("1");
					}else
						fb.setIsSampleAreaSelected("0");
				}
				else
					fb.setIsSampleAreaSelected("0");

				
				String isFilter="";	
				if(session.getAttribute("isFilter")!=null)
				{
					isFilter =	(String) session.getAttribute("isFilter");
					
					vo=(ResultEntryVO) session.getAttribute(InvestigationConfig.FILTER_LIST);	
					//vo=lstPatVO.get(0);
					session.setAttribute("isFilterList", vo);
					session.setAttribute("isFilter", "1");
					//HelperMethods.populate(fb, vo);
				}else
				{
					isFilter="0";	
					//WebUTIL.refreshTransState(request);
					if(!fb.getIsSampleAreaSelected().equals("1"))
					{
						WebUTIL.refreshTransState(request);
						fb.setLabTestCodeArray("");
						InvResultValidationUTIL.getSampleCollectionArea(fb,request);
						//OnlinePatientAcceptanceUTL.getEssential(fb,request);
					}
					else
					{
						
						//SampleCollectionUTL.getPatList(fb,request);
						//WebUTIL.refreshTransState(request);
						String sampleAreaName=fb.getSampleAreaName();
						//updated by krishnan nema on 27/09/2018
						//String sampleAreaCode=fb.getSampleAreaCode();
						String sampleAreaCode=fb.getSampleAreaCode().split("#")[0];
						fb.setSampleAreaCode(sampleAreaCode);
						
						InvResultValidationUTIL.getEssential(fb,request);
						//InvResultEntryUTIL.getEssential(fb,request);
					     fb.setIsSampleAreaSelected("1");
					     fb.setShowStatus("0");
					     fb.setSampleAreaName(sampleAreaName);
					     fb.setSampleAreaCode(sampleAreaCode);
					     session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
					}	
				}
	  
				//session.removeAttribute(InvestigationConfig.COMPONENT_WISE_INDICATION_MAP);
			
			
		  		 
			//	InvResultValidationUTIL.getEssential(fb,request);
		
		if(fb.getNewEntry()!=null){
			if(fb.getNewEntry().equals("2"))
			{
				fb.setNewEntry("2");
			}else
				fb.setNewEntry("1");
		}
		else
			fb.setNewEntry("1");
		
	
		fb.setLabCode("%");
		fb.setSearchBy("1");
		
		if(isFilter.equals("1"))
			return this.GETDETAILSSESSION(mapping, form, request, response);
			else
		return mapping.findForward("NEW");
	}

	/*public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvResultValidationFB fb=(InvResultValidationFB)form;
		HttpSession session=WebUTIL.getSession(request);
		//Resetting selected lab test code array
		fb.setLabTestCodeArray("");

		if(fb.getShowStatus()!=null){
			if(fb.getShowStatus().equals("1"))
			{
				fb.setShowStatus("1");
			}else
				fb.setShowStatus("0");
		}
		else
			fb.setShowStatus("0");
		WebUTIL.refreshTransState(request);		 
		InvResultValidationUTIL.getEssential(fb,request);

		if(fb.getNewEntry()!=null){
			if(fb.getNewEntry().equals("2"))
			{
				fb.setNewEntry("2");
			}else
				fb.setNewEntry("1");
		}
		else
			fb.setNewEntry("1");



		return mapping.findForward("NEW");
	}*/
	
	public ActionForward GETDETAILSSESSION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
		HttpSession session=WebUTIL.getSession(request);
		InvResultValidationFB fb = (InvResultValidationFB) form;
		
		fb.setShowStatus("0");
		ResultEntryVO vo=null;
		List labcombo=new ArrayList();
		//WebUTIL.refreshTransState(request);
		ControllerUTIL.setSysdate(request);
	      	 
		if(session.getAttribute("isFilter")!=null)
		{
			vo=(ResultEntryVO) session.getAttribute("isFilterList");
			HelperMethods.populate(fb, vo);
		}
		
		if(fb.getIsSampleAreaSelected()!=null){
			if(fb.getIsSampleAreaSelected().equals("1"))
			{
				fb.setIsSampleAreaSelected("1");
			}else
				fb.setIsSampleAreaSelected("0");
		}
		else
			fb.setIsSampleAreaSelected("0");
			
		
		labcombo=(List) session.getAttribute(InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY);
		WebUTIL.refreshTransState(request);
		
		
		/*if(!fb.getIsSampleAreaSelected().equals("1"))
		{
			WebUTIL.refreshTransState(request);
			fb.setLabTestCodeArray("");
			InvResultValidationUTIL.getSampleCollectionArea(fb,request);
			//OnlinePatientAcceptanceUTL.getEssential(fb,request);
		}
		else
		{
			*///SampleCollectionUTL.getPatList(fb,request);
			WebUTIL.refreshTransState(request);
			String sampleAreaName=fb.getSampleAreaName();
			String sampleAreaCode=fb.getSampleAreaCode();
			InvResultValidationUTIL.setPatientEssentials(fb,request);
			//InvResultEntryUTIL.getEssential(fb,request);
			//InvResultEntryUTIL.getEssential(fb,request);
		     fb.setIsSampleAreaSelected("1");
		     fb.setShowStatus("0");
		     fb.setSampleAreaName(sampleAreaName);
		     fb.setSampleAreaCode(sampleAreaCode);
		     
		/*}*/
		
		//InvResultValidationUTIL.setPatientEssentials(fb,request);
		session.setAttribute(InvestigationConfig.LAB_COMBO_FOR_RESULT_ENTRY, labcombo);
		session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
		
		//session.setAttribute("isFilter", "1");
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
            HttpSession session=request.getSession();
            session.removeAttribute(InvestigationConfig.LIST_RESULT_ENTRY_PATIENT_TEMPLATE_VO);
		InvResultValidationFB fb = (InvResultValidationFB) form;
		fb.setShowStatus("0");
		String searchBy = fb.getSearchBy();
		ControllerUTIL.setSysdate(request);

		if(searchBy.equals("0")){
			fb.setFromCollDate(fb.getFromDate());
			fb.setToCollDate(fb.getToDate());
			
		}
		
		InvResultValidationUTIL.setPatientEssentials(fb,request);
		
		if(searchBy.equals("1"))
	    	fb.setSearchBy("1");
	    else 
	    	fb.setSearchBy("0");

		return mapping.findForward("NEW");
	}


	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
		InvResultValidationFB fb = (InvResultValidationFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		fb.setToviewonly("0");
		InvResultValidationUTIL.showResultValidationPatDetails(fb,request);
		fb.setSampleAreaName(sampleAreaName);
	     fb.setSampleAreaCode(sampleAreaCode);
		return mapping.findForward("NEW");
	}

	public ActionForward REVALIDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: REVALIDATE  ");
		InvResultValidationFB fb = (InvResultValidationFB) form;
		//	ControllerUTIL.setSysdate(request);
		//	fb.setShowStatus("1");
		InvResultValidationUTIL.revalidateResultValidationPatDetails(fb,request);
		// fb.reset();
		fb.setLabCode("-1");
		fb.setFromDate(fb.getToDate());
		return this.NEW(mapping, form, request, response);

	}



	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: SEARCHLABWISETEST  ");
		InvResultValidationFB fb = (InvResultValidationFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		//InvResultValidationUTIL.searchLaboratoryWiseTest(fb,request);

		return mapping.findForward("NEW");
	}




	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvResultValidationFB fb=(InvResultValidationFB)form;
		Status  objStatus=new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}





	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		InvResultValidationFB fb=(InvResultValidationFB)form;
		fb.setNewEntry("1");
		InvResultValidationUTIL.saveResultValidationPatientDetails(fb, request);
		return this.NEW(mapping, form, request, response);
	} 
	
	//added by krishnan nema on 01/02/2019 for save to draft changes
	public ActionForward SAVETODRAFT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request, response);
		InvResultValidationFB fb=(InvResultValidationFB)form;
		fb.setNewEntry("1");
		InvResultValidationUTIL.saveResultValidationPatientDetails(fb, request);
		fb.setIsSaveToDraft(null);
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
	public ActionForward AJX_DUPLICACY_DAILYLABNO(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationFB fb=(InvResultValidationFB)objForm_p;

		//StringBuffer strBuff = InvResultValidationUTIL.checkDailyLabNoDuplicacy(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		//objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}



	public ActionForward GOFORRESULTENTRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		InvResultValidationFB fb=(InvResultValidationFB) form;
		try {
			InvResultValidationUTIL.getResultValidationTemplateForSelectedWorkOrders(request,fb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("SAME");
	}

	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultValidationFB fb=(InvResultValidationFB)form;
		fb.setNewEntry("2");
		InvResultValidationUTIL.saveResultValidationPatientDetails(fb, request);
		fb.setNewEntry("1");
		return this.NEW(mapping, form, request, response);
	} 
	
	public ActionForward AJX_INSERT_MODIFY_CANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationFB fb=(InvResultValidationFB)objForm_p;
		System.out.println("ajax fire");
		String val=InvResultValidationUTIL.checkCannedCodeName(fb,objRequest_p);
		/*StringBuffer strBuff = InvResultValidationUTIL.autoCannedDetails(fb,objRequest_p);*/
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(val);
		return null;
	}


	
	public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("viewInvestigationACT: printreport  ");
		InvResultValidationFB fb = (InvResultValidationFB) form;
		
		InvResultValidationUTIL.printReport(fb,request,response);
		
		return null;
		//return mapping.findForward("NEW");
	}
	

	
	/*public ActionForward SHOWPATDETAILSPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		HttpSession session=WebUTIL.getSession(request);
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILSPOPUP  ");
		InvResultValidationFB fb = (InvResultValidationFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		
		InvResultValidationUTIL.showResultValidationPatDetails(fb,request);
		fb.setSampleAreaName(sampleAreaName);
	     fb.setSampleAreaCode(sampleAreaCode);
	     fb.setIspreview("2");
	     session.removeAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
	     	     return mapping.findForward("NEW");
	}*/

	public ActionForward SHOWPATDETAILSPOPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		HttpSession session=WebUTIL.getSession(request);
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILSPOPUP  ");
		InvResultValidationFB fb = (InvResultValidationFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		String sampleAreaName=fb.getSampleAreaName();
		String sampleAreaCode=fb.getSampleAreaCode();
		String reqDno = fb.getSelectedCheckbox().split("#")[2];
		System.out.println("reqDno = "+reqDno);
		request.setAttribute("dnoForRangeValidation", reqDno);
		fb.setToviewonly("1");
		InvResultValidationUTIL.showResultValidationPatDetails(fb,request);
		fb.setSampleAreaName(sampleAreaName);
	     fb.setSampleAreaCode(sampleAreaCode);
	     fb.setIspreview("2");
	     session.removeAttribute(InvestigationConfig.GROUP_MODIFIED_LIST_PATIENT_RESULT_ENTRY_ESSENTIALS_VO);
	     	     return mapping.findForward("NEW");
	}
	
	
	public ActionForward AJX_CHECK_REQFORM_TESTTYPE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationFB fb=(InvResultValidationFB)objForm_p;
		
		StringBuffer strBuff = InvResultValidationUTIL.getRequisitionFormMasterData(fb, objRequest_p);
		//System.out.println("strBuff act"+strBuff);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}

	public ActionForward GETFILEUPLOADDATATESTWISE(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvResultValidationFB fb=(InvResultValidationFB)objForm_p;
		
		String strBuff = InvResultValidationUTIL.getfileuploaddatatestwise(fb, objRequest_p);
		//System.out.println("strBuff act"+strBuff);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff);
		return null;
	}


}
