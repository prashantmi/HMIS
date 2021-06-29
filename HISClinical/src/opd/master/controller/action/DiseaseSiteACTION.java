package opd.master.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.DiseaseSiteFB;
import opd.master.controller.util.DiseaseSiteUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class DiseaseSiteACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{		
		return this.ADD(mapping,form,request,response);		
	}
	
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		generateToken(request);
		DiseaseSiteFB fb =(DiseaseSiteFB)form;		 
				
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);		
		DiseaseSiteUTIL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SETSUBGROUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		DiseaseSiteFB fb =(DiseaseSiteFB)form;
		DiseaseSiteUTIL.setSubGroups(fb, request);
		if(fb.getDiseaseSiteId()==null || fb.getDiseaseSiteId().trim().equals(""))
			fb.setHmode("ADD");
		else
			fb.setHmode("MODIFY");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SETDISEASE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		DiseaseSiteFB fb =(DiseaseSiteFB)form;
		DiseaseSiteUTIL.setDiseaseSubGroupWise(fb, request);
		if(fb.getDiseaseSiteId()==null || fb.getDiseaseSiteId().trim().equals(""))
			fb.setHmode("ADD");
		else
			fb.setHmode("MODIFY");
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		 DiseaseSiteFB fb = (DiseaseSiteFB) form;
		 if(DiseaseSiteUTIL.save(fb,request))
		 {
			WebUTIL.refreshTransState(request);
			fb.reset(mapping,request);		
			DiseaseSiteUTIL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.TRANSINPROCESS, "Record Saved Successfully", "");
			fb.setHmode("ADD");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		 }
		 return mapping.findForward("NEW");			
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DiseaseSiteFB fb = (DiseaseSiteFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		DiseaseSiteUTIL.getRecord(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DiseaseSiteFB fb = (DiseaseSiteFB) form;
		if(DiseaseSiteUTIL.modifyRecord(fb, request))
			return mapping.findForward("LIST");
		else
		{
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		DiseaseSiteFB fb = (DiseaseSiteFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		DiseaseSiteUTIL.getRecord(fb, request);
		return mapping.findForward("NEW");
	}
}
