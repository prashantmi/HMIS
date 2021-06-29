package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdServiceRequisitionFB;
import opd.transaction.controller.util.OpdServiceRequisitionUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdServiceRequisitionACTION extends CSRFGardTokenAction {
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{				
		return OPDSERVICEREQUISITIONPAGE(mapping,form,request,response); 	
	}
	public ActionForward OPDSERVICEREQUISITIONPAGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	generateToken(request);	
		System.out.println("inside OpdServiceRequisitionPage ..........");
		WebUTIL.refreshTransState(request);
		OpdServiceRequisitionFB fb = (OpdServiceRequisitionFB)form;		
		OpdServiceRequisitionUTIL.setSysdate(request);				
		OpdServiceRequisitionUTIL.getServiceEssentials(request,fb);		
		return mapping.findForward("OpdServiceRequisitionpPage");
	}
	
	public ActionForward GETCOMBOOPTIONS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		System.out.println("inside OpdServiceRequisitionPage ..........");		
		OpdServiceRequisitionFB fb = (OpdServiceRequisitionFB)form;				
		OpdServiceRequisitionUTIL.getComboOptions(request,fb,response);		
		return null;
	}	
	public ActionForward GETSERVICEPARAMETERS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		System.out.println("inside OpdServiceRequisitionPage ..........");		
		OpdServiceRequisitionFB fb = (OpdServiceRequisitionFB)form;				
		OpdServiceRequisitionUTIL.getServiceWiseParameters(request,fb,response);		
		return null;
	}
	
	public ActionForward SAVESERVICEREQUISITION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		validateToken(request,response);
		System.out.println("inside OpdServiceRequisitionPage ..........");		
		OpdServiceRequisitionFB fb = (OpdServiceRequisitionFB)form;
		if(OpdServiceRequisitionUTIL.SaveServiceRequisition(fb,request))
			return this.OPDSERVICEREQUISITIONPAGE(mapping, form, request, response);
		else
			return mapping.findForward("OpdServiceRequisitionpPage");
	}
}
