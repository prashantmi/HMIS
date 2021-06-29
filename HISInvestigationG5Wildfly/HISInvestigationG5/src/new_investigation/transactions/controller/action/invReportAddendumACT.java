/**
 <!--

 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PUNEET SINGH KHURANA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT REPORT ADDENDUM
 ## Purpose						        : 
 ## Date of Creation					: 19/07/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


 */


package new_investigation.transactions.controller.action;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.invReportAddendumFB;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.invReportAddendumUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class invReportAddendumACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		
		invReportAddendumFB fb=(invReportAddendumFB)form;
		HttpSession session=WebUTIL.getSession(request);
		
		        if(request.getParameter("istrue")==null)
		        {
		        	session.removeAttribute("addendumStatusFromRaising");
		        	System.out.println("chanksss:");
		        }
		       
		        session.removeAttribute("IsAddendumENTRY");        	
			//session.removeAttribute("addendumStatusFromRaising");
		
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
		invReportAddendumUTIL.getEssential(fb,request);

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
	}
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		invReportAddendumFB fb = (invReportAddendumFB) form;
		fb.setShowStatus("0");

		ControllerUTIL.setSysdate(request);

		invReportAddendumUTIL.setPatientEssentials(fb,request);

		/*fb.setShowStatus("1");*/
		return mapping.findForward("NEW");
	}


	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
		invReportAddendumFB fb = (invReportAddendumFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		invReportAddendumUTIL.showResultValidationPatDetails(fb,request);

		fb.setShowStatusLegends("0");
		return mapping.findForward("NEW");
	}

	public ActionForward REVALIDATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		System.out.println("OnlinePatientAcceptanceACT: REVALIDATE  ");
		invReportAddendumFB fb = (invReportAddendumFB) form;
		//	ControllerUTIL.setSysdate(request);
		//	fb.setShowStatus("1");
		invReportAddendumUTIL.revalidateResultValidationPatDetails(fb,request);
		// fb.reset();
		fb.setLabCode("-1");
		fb.setFromDate(fb.getToDate());
		return this.NEW(mapping, form, request, response);

	}



	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		System.out.println("OnlinePatientAcceptanceACT: SEARCHLABWISETEST  ");
		invReportAddendumFB fb = (invReportAddendumFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		//invReportAddendumUTIL.searchLaboratoryWiseTest(fb,request);

		return mapping.findForward("NEW");
	}




	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		invReportAddendumFB fb=(invReportAddendumFB)form;
		Status  objStatus=new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}





	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		 validateToken(request, response);
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		invReportAddendumFB fb=(invReportAddendumFB)form;
		fb.setNewEntry("1");
		invReportAddendumUTIL.saveResultValidationPatientDetails(fb, request);
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
		HttpSession session=WebUTIL.getSession(objRequest_p);
		session.removeAttribute("addendumStatusFromRaising");
		invReportAddendumFB fb=(invReportAddendumFB)objForm_p;

		//StringBuffer strBuff = invReportAddendumUTIL.checkDailyLabNoDuplicacy(fb, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		//objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}



	public ActionForward GOFORRESULTENTRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		invReportAddendumFB fb=(invReportAddendumFB) form;
		try {
			invReportAddendumUTIL.getResultValidationTemplateForSelectedWorkOrders(request,fb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("SAME");
	}

	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		invReportAddendumFB fb=(invReportAddendumFB)form;
		fb.setNewEntry("2");
		invReportAddendumUTIL.saveResultValidationPatientDetails(fb, request);
		fb.setNewEntry("1");
		return this.NEW(mapping, form, request, response);
	} 

	public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("viewInvestigationACT: printreport  ");
		invReportAddendumFB fb = (invReportAddendumFB) form;
		
		invReportAddendumUTIL.printReport(fb,request,response);
		
		return null;
		//return mapping.findForward("NEW");
	}

	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute("addendumStatusFromRaising");
		System.out.println("OnlinePatientAcceptanceACT: GETPATDTL ");
		invReportAddendumFB fb = (invReportAddendumFB) form;
		ControllerUTIL.setSysdate(request);
		//fb.setShowStatus("1");
		String selectedCheckBoxValue=fb.getSelectedCheckbox();
		String[] arrSelectedRequisitions=selectedCheckBoxValue.split("@");
		String crNo=arrSelectedRequisitions[0].split("#")[0];
		String reqNO=arrSelectedRequisitions[0].split("#")[1];
		String reqDNo=arrSelectedRequisitions[0].split("#")[2];
		 String grpCode=arrSelectedRequisitions[0].split("#")[3];
		 String testCode=arrSelectedRequisitions[0].split("#")[4];
		 String labCode=arrSelectedRequisitions[0].split("#")[5];
		
		 // save data for addendum report 
		 invReportAddendumUTIL.saveAddendumDetails(fb, request);
		 
		 session.setAttribute("PatCrNo", crNo);
		 session.setAttribute("testCode", testCode);
		 session.setAttribute("labCode", labCode);
		 session.setAttribute("IsAddendum", "1");
		 session.setAttribute("reqNo", reqNO);
		 session.setAttribute("reqDno", reqDNo);
		 InvestigationRaisingDtlFB fb1 = new InvestigationRaisingDtlFB(); 
			
		 request.setAttribute("InvestigationRaisingDtlFB", fb1);
		 fb1.setAptStatus("10");
			fb1.setPatCrNo(crNo);
			fb1.setTestCodee(testCode);
		     fb1.setLabCodee(labCode);
             fb1.setIsAddendum("1");
             
		     return mapping.findForward("GETPATDTL");
	}
	
}
