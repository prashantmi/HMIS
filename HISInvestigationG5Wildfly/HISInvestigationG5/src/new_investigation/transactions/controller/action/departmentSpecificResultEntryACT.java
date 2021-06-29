/**
 <!--
  
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: SIDDHARTH SRIVASTAVA
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    : RESULT ENTRY ACTION
 ## Purpose						        : 
 ## Date of Creation					: 13/04/2015
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 


*/


package new_investigation.transactions.controller.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import new_investigation.transactions.controller.fb.departmentSpecificResultEntryFB;
import new_investigation.transactions.controller.fb.InvResultValidationFB;
import new_investigation.transactions.controller.fb.OnlinePatientAcceptanceFB;
import new_investigation.transactions.controller.utl.departmentSpecificResultEntryUTIL;
import new_investigation.transactions.controller.utl.OnlinePatientAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import hisglobal.backutil.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class departmentSpecificResultEntryACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		departmentSpecificResultEntryFB fb=(departmentSpecificResultEntryFB)form;
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
		departmentSpecificResultEntryUTIL.getEssential(fb,request);
		
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
		return mapping.findForward("NEW");
	}
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: GETDETAILS  ");
        
		departmentSpecificResultEntryFB fb = (departmentSpecificResultEntryFB) form;
		fb.setShowStatus("0");
          
		ControllerUTIL.setSysdate(request);
	      	 
		departmentSpecificResultEntryUTIL.setPatientEssentials(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
	 
	public ActionForward SHOWPATDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//isValue=1;
		System.out.println("InvResultEntryACT: SHOWPATDETAILS  ");
		departmentSpecificResultEntryFB fb = (departmentSpecificResultEntryFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		departmentSpecificResultEntryUTIL.showResultEntryPatDetails(fb,request);
		 
		return mapping.findForward("NEW");
	}
	
	
	
	public ActionForward SEARCHLABWISETEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("InvResultEntryACT: SEARCHLABWISETEST  ");
		departmentSpecificResultEntryFB fb = (departmentSpecificResultEntryFB) form;
		ControllerUTIL.setSysdate(request);
		fb.setShowStatus("1");
		//departmentSpecificResultEntryUTIL.searchLaboratoryWiseTest(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	 
	 
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		departmentSpecificResultEntryFB fb=(departmentSpecificResultEntryFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}
	
	
	 
	
	
	 public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	 {
		 validateToken(request, response);
		 departmentSpecificResultEntryFB fb=(departmentSpecificResultEntryFB)form;
		 
		 
		/* List<FormFile> myFiless = fb.getFiles();
		 System.out.println("upload files size:");
		 System.out.println(myFiless.size());
		 for(FormFile f : myFiless){
			 System.out.println(f.getFileName());
			// FormFile myFile =(FormFile)myFiles.get(i) ;
			 //System.out.println(myFile.getContentType());
			 }*/
		 
		 
		 
		 
		departmentSpecificResultEntryUTIL.saveResultEntryPatientDetails(fb, request);
		 return this.NEW(mapping, form, request, response);
	 } 
	 
	 /**
		 * SHOWCANNEDDETAILS     
	 	*  
	 	* @param objMapping_p
	 	* @param objForm_p
	 	* @param objRequest_p
	 	* @param objResponse_p
	 	* @return
	 	* @throws Exception,HisException,SQLException
	 	*/
		public ActionForward SHOWCANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
				HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
		{
			departmentSpecificResultEntryFB fb=(departmentSpecificResultEntryFB)objForm_p;
			
		 
			
			
			StringBuffer strBuff = departmentSpecificResultEntryUTIL.showCannedDetails(fb,objRequest_p);
			objResponse_p.setHeader("Cache-Control", "no-cache");
			objResponse_p.getWriter().print(strBuff.toString());
			return null;
		}
	  
	
 //SHOWCANNEDDETAILS
		
		
		/*public ActionForward SHOWCANNEDDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			//isValue=1;
			System.out.println("OnlinePatientAcceptanceACT: SHOWPATDETAILS  ");
			departmentSpecificResultEntryFB fb = (departmentSpecificResultEntryFB) form;
			ControllerUTIL.setSysdate(request);
			fb.setShowStatus("1");
			departmentSpecificResultEntryUTIL.showCannedDetails(fb,request);
			 
			return mapping.findForward("NEW");
		}
*/
		//GETTYPEWISEDETAIL
		
		public ActionForward GETTYPEWISEDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
			System.out.println("InvResultEntryACT: GETTYPEWISEDETAIL  ");
	        
			departmentSpecificResultEntryFB fb = (departmentSpecificResultEntryFB) form;
			fb.setShowStatus("0");
	          
			ControllerUTIL.setSysdate(request);
		      	 
			departmentSpecificResultEntryUTIL.getEntryTypeDetails(fb,request);
			
			 
			return mapping.findForward("NEW");
		}
		
		
		 public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
		 {
			 departmentSpecificResultEntryFB fb=(departmentSpecificResultEntryFB)form;
			departmentSpecificResultEntryUTIL.modifyResultEntryPatientDetails(fb, request);
			 return this.NEW(mapping, form, request, response);
		 } 
		 
		 /**
			 * SHOWCANNEDDETAILS     
		 	*  
		 	* @param objMapping_p
		 	* @param objForm_p
		 	* @param objRequest_p
		 	* @param objResponse_p
		 	* @return
		 	* @throws Exception,HisException,SQLException
		 	*/
			public ActionForward AUTOCANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				departmentSpecificResultEntryFB fb=(departmentSpecificResultEntryFB)objForm_p;
					
				StringBuffer strBuff = departmentSpecificResultEntryUTIL.autoCannedDetails(fb,objRequest_p);
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(strBuff.toString());
				return null;
			}
			
			public ActionForward AJX_INSERT_MODIFY_CANNEDDETAILS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p,
					HttpServletResponse objResponse_p) throws HisException, Exception, SQLException
			{
				InvResultValidationFB fb=(InvResultValidationFB)objForm_p;
				System.out.println("ajax fire");
				String val=departmentSpecificResultEntryUTIL.checkCannedCodeName(fb,objRequest_p);
				/*StringBuffer strBuff = departmentSpecificResultEntryUTIL.autoCannedDetails(fb,objRequest_p);*/
				objResponse_p.setHeader("Cache-Control", "no-cache");
				objResponse_p.getWriter().print(val);
				return null;
			}
		 
}
