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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.fb.InvResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingRespFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.utl.InvResultEntryUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingRespUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InvResultReportPrintingRespACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward NEW1(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
		HttpSession session=WebUTIL.getSession(request);
		//Resetting selected lab test code array
				fb.setLabTestCodeArray("");
				fb.setReportType("0");
				
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
		//updated by krishnan nema on 05/10/2018
		//fb.setPatTestType("2");
		Map mp=new HashMap();
		mp = InvResultReportPrintingRespUTIL.getEssential(fb,request);
		
		List labcombo=new ArrayList();
		labcombo = (List) mp.get(InvestigationConfig.LAB_COMBO_FOR_RESULT_REPORT_PRINTING);
		int count = 0;
		for(int i =0; i<labcombo.size();i++){
			String lab = labcombo.get(i).toString();
			String labtype = lab.split("#")[1].split("]")[0];
			System.out.println("remaining= "+lab.split("#")[1].split("]")[0]);
			if(labtype.equals("2")){
				count++;
			}
		}
		
		if(count==labcombo.size()){
			fb.setPatTestType("2");
			fb.setIsAllLabPatientBased("1");
		}
		
		fb.setDateType("r");
		return mapping.findForward("NEW");
	}
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
        
		InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
		fb.setShowStatus("0");
        String dateType=fb.getDateType();
		ControllerUTIL.setSysdate(request);
		
		InvResultReportPrintingRespUTIL.setResultReportPrintingEssentials(fb,request);
		fb.setDateType(dateType);
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ======chandannn");
		InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		
		boolean flg=false;
		
		String ftpserver=InvResultReportPrintingRespUTIL.isfromFTPorMONGO(request,response);
        fb.setFtpserver(ftpserver);

		
		if(ftpserver==null || ftpserver.equals(""))
		InvResultReportPrintingRespUTIL.showResultEntryPatDetails(fb,request,response);
		else
			InvResultReportPrintingRespUTIL.showResultEntryPatDetailsFTPnew(fb,request,response);
	
		return null;
	} 
	
	
	 public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		 InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
			Status  objStatus=new Status();
			  objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("NEW");
		}
		
	 public ActionForward GETTYPEWISEDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETTYPEWISEDETAIL  ");
	        
			InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			//InvResultEntryUTIL.getEntryTypeDetails(fb,request);
			
			 
			return mapping.findForward("NEW");
		}

	 
	 public ActionForward PRINTREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//isValue=1;
		 generateToken(request);
			System.out.println("InvResultReportPrintingACT: PRINTREPORT  ");
			InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;

				ControllerUTIL.setSysdate(request);
			
				if(fb.getFileName()!=null && !fb.getFileName().equals(""))
			request.setAttribute("hosptialcode", fb.getFileName().substring(0, 5));
				
		
			fb.setShowStatus("1");
			
			boolean flg=false;
			String ftpserver=InvResultReportPrintingRespUTIL.isfromFTPorMONGO(request,response);
            fb.setFtpserver(ftpserver);

			
			if(ftpserver==null || ftpserver.equals(""))
			InvResultReportPrintingRespUTIL.printReport(fb,request,response);
			else
				InvResultReportPrintingRespUTIL.printReportFTP(fb,request,response);	
			return null;
		} 

	 
	 public ActionForward SHOWPATDETAILS_VIEWINV(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//isValue=1;
			System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ======chandannn");
			InvResultReportPrintingRespFB fb=(InvResultReportPrintingRespFB)form;
			ControllerUTIL.setSysdate(request);
			//fb.setShowStatus("1");
			boolean flg=false;
			String ftpserver=InvResultReportPrintingRespUTIL.isfromFTPorMONGO(request,response);
                   fb.setFtpserver(ftpserver);
			
			if(ftpserver==null || ftpserver.equals(""))
			InvResultReportPrintingRespUTIL.showResultEntryPatDetails_viewinv(fb,request,response);
			else 
				InvResultReportPrintingRespUTIL.showResultEntryPatDetails_viewinvFTP(fb,request,response);	
			return null;
		} 
		
	 
}
