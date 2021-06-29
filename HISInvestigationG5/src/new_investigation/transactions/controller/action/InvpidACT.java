/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN BASHA 
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :  REPORT PRINTING ACTION
 ## Purpose						        : 
 ## Date of Creation					: 25-05-2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;



import investigationDesk.transactions.controller.fb.viewInvestigationFB;
import investigationDesk.transactions.controller.utl.viewInvestigationUTL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.backutil.exception.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.InvpidFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingNewUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingUTIL;
import new_investigation.transactions.controller.utl.InvestigationRaisingDtlUTL;
import new_investigation.transactions.controller.utl.InvpidUTIL;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InvpidACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session=WebUTIL.getSession(request);
		WebUTIL.refreshTransState(request);
	     session.setAttribute("iscall_pidfrom", "0");
	     session.setAttribute("shw_pattile_piform", "0");
	     session.setAttribute("issave_pidform", "");
			
		return mapping.findForward("NEW");

		//return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		
		
		String patcrno=(String)request.getAttribute("patCrNo");
		
		WebUTIL.refreshTransState(request);
		InvpidFB fb=(InvpidFB)form;
		HttpSession session=WebUTIL.getSession(request);
	
		session.setAttribute("issave_pidform", "");
		
		
		  //   fb.setPatCRNo("1");			
		     InvResultReportPrintingFB _fb=new InvResultReportPrintingFB();
		     _fb.setPatCrNo(fb.getPatCrNo());
		     InvpidUTIL.getEssential( _fb,request);
		     
		     session.setAttribute("iscall_pidfrom", "0");
		     session.setAttribute("shw_pattile_piform", "1");
		     if(session.getAttribute("issave_pidform")!=null)
				{
				
					String msgg=(String)session.getAttribute("issave_pidform");
					if(msgg.equals("1"))
					{
						
					}
					else
					{
						session.setAttribute("issave_pidform", "");
					}
				}
				else
				{
				session.setAttribute("issave_pidform", "");
				}

	
		return mapping.findForward("NEW");
    	
	
	}
	
	/*
	 * public ActionForward GETDATACRNOWISE(ActionMapping mapping,ActionForm
	 * form,HttpServletRequest request,HttpServletResponse response) {
	 * generateToken(request);
	 * 
	 * String patcrno=(String)request.getAttribute("patCrNo");
	 * 
	 * 
	 * InvpidFB fb=(InvpidFB)form; HttpSession session=WebUTIL.getSession(request);
	 * 
	 * // fb.setPatCRNo(patcrno);
	 * 
	 * Map mp=new HashMap(); String json =
	 * InvResultReportPrintingNewUTIL.getdatacrnowise(fb,request);
	 * 
	 * response.setHeader("Cache-Control", "no-cache"); try {
	 * response.getWriter().print(json);
	 * response.setContentType("application/Json");
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } System.out.println(json);
	 * 
	 * return null;
	 * 
	 * 
	 * }
	 */
	
	
	/*
	 * public ActionForward GETDETAILS(ActionMapping mapping,ActionForm
	 * form,HttpServletRequest request,HttpServletResponse response){
	 * System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
	 * 
	 * InvResultReportPrintingFB fb=(InvResultReportPrintingFB)form;
	 * fb.setShowStatus("0"); String dateType=fb.getDateType();
	 * ControllerUTIL.setSysdate(request);
	 * 
	 * InvResultReportPrintingUTIL.setResultReportPrintingEssentials(fb,request);
	 * fb.setDateType(dateType); return mapping.findForward("NEW"); }
	 * 
	 * 
	 * public ActionForward SHOWREPORT(ActionMapping mapping,ActionForm
	 * form,HttpServletRequest request,HttpServletResponse response){ //isValue=1;
	 * System.out.
	 * println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ======chandannn");
	 * InvpidFB fb=(InvpidFB)form;
	 * ControllerUTIL.setSysdate(request); fb.setShowStatus("1");
	 * 
	 * boolean flg=false;
	 * 
	 * String
	 * ftpserver=InvResultReportPrintingUTIL.isfromFTPorMONGO(request,response);
	 * fb.setFtpserver(ftpserver);
	 * 
	 * 
	 * if(ftpserver==null || ftpserver.equals(""))
	 * InvResultReportPrintingNewUTIL.showResultEntryPatDetails(fb,request,response)
	 * ; else
	 * InvResultReportPrintingNewUTIL.showResultEntryPatDetailsFTPnew(fb,request,
	 * response);
	 * 
	 * return null; }
	 * 
	 * 
	 * 
	 */
	 
	 

	
	public ActionForward setpidd(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
			HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
	{
		InvpidFB fb=(InvpidFB)objForm_p;
		HttpSession session= objRequest_p.getSession();
		
		String strBuff="";
		
		strBuff=fb.getDateType() ;
		session.setAttribute("issave_pidform", "");
		 String data=InvpidUTIL.getEssentialdata( fb,objRequest_p);
		 session.setAttribute("patcrno_pid", fb.getPatcrno_pid());
		 session.setAttribute("reqno_pid", fb.getReqno_pid());
		System.out.println("strBuff"+data);
		objRequest_p.setAttribute("getpidata_piform",data);
		session.setAttribute("iscall_pidfrom", "1");
		
		return objMapping_p.findForward("NEW");

		
	}
	

	
	
	public ActionForward NEWCALL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session= request.getSession();
		InvpidFB fb=(InvpidFB)form;

		String crno=(String)session.getAttribute("patcrno_pid");
		 String reqno=(String)session.getAttribute("reqno_pid");
		
		fb.setPatCrNo(crno);
	    
		
		//   fb.setPatCRNo("1");			
		     InvResultReportPrintingFB _fb=new InvResultReportPrintingFB();
		     _fb.setPatCrNo(fb.getPatCrNo());
		     InvpidUTIL.getEssential( _fb,request);
		     session.setAttribute("issave_pidform", "");
		     session.setAttribute("iscall_pidfrom", "0");
				

	
		return mapping.findForward("NEW");
    	
	
	}
	
	
	public ActionForward SAVEDATA(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		HttpSession session= request.getSession();
		InvpidFB fb=(InvpidFB)form;

		String crno=(String)session.getAttribute("patcrno_pid");
		 String reqno=(String)session.getAttribute("reqno_pid");
		
		fb.setPatCrNo(crno);
	    
		
		  //   fb.setPatCRNo("1");			
		 
		
		     InvpidUTIL.savedata( fb,request,response);
		     
		     session.setAttribute("iscall_pidfrom", "0");
				

	
		return this.NEW1(mapping,form,request,response);
    	
	
	}
	
	
	
	
	


public ActionForward NEW1(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
{
	generateToken(request);
	
	
	String patcrno=(String)request.getAttribute("patCrNo");
	
	WebUTIL.refreshTransState(request);
	InvpidFB fb=(InvpidFB)form;
	HttpSession session=WebUTIL.getSession(request);

	
	
	  //   fb.setPatCRNo("1");			
	     InvResultReportPrintingFB _fb=new InvResultReportPrintingFB();
	     _fb.setPatCrNo(fb.getPatCrNo());
	     InvpidUTIL.getEssential( _fb,request);
	     
	     session.setAttribute("iscall_pidfrom", "0");
	     session.setAttribute("shw_pattile_piform", "1");
	     if(session.getAttribute("issave_pidform")!=null)
			{
			
				String msgg=(String)session.getAttribute("issave_pidform");
				if(msgg.equals("1"))
				{
					
				}
				else
				{
					session.setAttribute("issave_pidform", "");
				}
			}
			else
			{
			session.setAttribute("issave_pidform", "");
			}


	return mapping.findForward("NEW");
	

}

}