package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.util.IcdHospitalMasterUTIL;
import opd.master.controller.fb.IcdHospitalMasterFB;

public class IcdHospitalMasterACTION extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		generateToken(request);
		IcdHospitalMasterFB fb= (IcdHospitalMasterFB)form;
	    Status objStatus=new Status();
	    objStatus.add(Status.NEW);
	    IcdHospitalMasterUTIL.getEssentails(fb, request);
		WebUTIL.setStatus(request,objStatus);	    
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		IcdHospitalMasterFB DMFb= (IcdHospitalMasterFB)form;
	    IcdHospitalMasterUTIL.fetchDiseaseList(DMFb, request);
			    
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		IcdHospitalMasterFB fb= (IcdHospitalMasterFB)form;
		IcdHospitalMasterUTIL.save(fb, request);
		
	   IcdHospitalMasterUTIL.fetchDiseaseList(fb, request);
			    
	 	return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		
			    
	 	return mapping.findForward("NEW");
	}
	
}
