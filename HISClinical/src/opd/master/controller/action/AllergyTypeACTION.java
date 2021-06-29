package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.master.controller.fb.AllergyTypeFB;
import opd.master.controller.util.AllergyTypeUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class AllergyTypeACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		generateToken(request);
		AllergyTypeFB fb =(AllergyTypeFB)form;		 
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		fb.setSourceFlag(OpdConfig.ALLERY_TYPE_SOURCE_FLAG_STATIC);
		AllergyTypeUTIL.getAllergySensistivity(fb, request);
		AllergyTypeUTIL.getTheValues(fb, request);
		AllergyTypeUTIL.getTableData(fb, request);
		AllergyTypeUTIL.getPrimaryKey(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCOLUMNS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		AllergyTypeFB fb =(AllergyTypeFB)form;
		AllergyTypeUTIL.getTableData(fb, request);
		AllergyTypeUTIL.getPrimaryKey(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		 AllergyTypeFB fb = (AllergyTypeFB) form;
		 AllergyTypeUTIL.saveAllergyType(fb,request);	
		 fb.reset(mapping, request);
		 
		 return mapping.findForward(fb.getHmode());			
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		 	
		AllergyTypeFB fb = (AllergyTypeFB) form;
		HttpSession session=request.getSession();
		session.setAttribute("mstFilename", "AllergyTypeMaster");
		
		fb.setHmode("unspecified");
		return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
			return this.NEW(mapping,form,request,response);		 	   	
	}

}
