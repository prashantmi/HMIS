/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: PATHAN BASHA 
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : DUPLICATE REPORT PRINTING ACTION
 ## Purpose						        : 
 ## Date of Creation					: 05-06-2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;



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
import new_investigation.transactions.controller.fb.InvDuplicateResultReportPrintingFB;
import new_investigation.transactions.controller.fb.InvResultReportPrintingFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.utl.InvDuplicateResultReportPrintingUTIL;
import new_investigation.transactions.controller.utl.InvResultReportPrintingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class InvDuplicateResultReportPrintingACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);

		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		InvDuplicateResultReportPrintingFB fb=(InvDuplicateResultReportPrintingFB)form;
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
		//updated by krishnan nema on 05/10/2018
		
		Map mp=new HashMap();
		mp = InvDuplicateResultReportPrintingUTIL.getEssential(fb,request);
		
		List labcombo=new ArrayList();
		labcombo = (List) mp.get(InvestigationConfig.LAB_COMBO_FOR_DUPLICATE_RESULT_REPORT_PRINTING);
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
		
		//InvDuplicateResultReportPrintingUTIL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("OnlinePatientAcceptanceACT: GETDETAILS  ");
        
		InvDuplicateResultReportPrintingFB fb=(InvDuplicateResultReportPrintingFB)form;
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
	      	 
		InvDuplicateResultReportPrintingUTIL.setResultReportPrintingEssentials(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	
	 public ActionForward DUPLICATESHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
		InvDuplicateResultReportPrintingFB fb=(InvDuplicateResultReportPrintingFB)form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		
		boolean flg=false;
		
		String ftpserver=InvResultReportPrintingUTIL.isfromFTPorMONGO(request,response);
        fb.setFtpserver(ftpserver);

		
		if(ftpserver==null || ftpserver.equals(""))
		InvDuplicateResultReportPrintingUTIL.showResultEntryPatDetails(fb,request,response);
		else
			InvDuplicateResultReportPrintingUTIL.showResultEntryPatDetailsFTP(fb,request,response);
		
		return null;
	} 
	
	
	 public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		{
		 InvDuplicateResultReportPrintingFB fb=(InvDuplicateResultReportPrintingFB)form;
			Status  objStatus=new Status();
			  objStatus.add(Status.TRANSINPROCESS);
			WebUTIL.setStatus(request,objStatus);
			return mapping.findForward("NEW");
		}
		 
	 public ActionForward GETTYPEWISEDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETTYPEWISEDETAIL  ");
	        
			 InvDuplicateResultReportPrintingFB fb=(InvDuplicateResultReportPrintingFB)form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			//InvResultEntryUTIL.getEntryTypeDetails(fb,request);
			
			 
			return mapping.findForward("NEW");
		}
 
}
