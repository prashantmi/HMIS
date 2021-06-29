package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.RecordFoundInMrdFB;
import mrd.transaction.controller.utl.RecordFoundInMrdUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RecordFoundInMrdACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		RecordFoundInMrdFB fb=(RecordFoundInMrdFB)form;
		WebUTIL.refreshTransState(request);
		RecordFoundInMrdUTL.getLostRecordInMrdList(fb,request) ;
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETFOUND(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RecordFoundInMrdFB fb=(RecordFoundInMrdFB)form;
		RecordFoundInMrdUTL.getFoundEssentialDtl(fb,request);
		fb.setChangeArchivedFlag(MrdConfig.NO);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RecordFoundInMrdFB fb=(RecordFoundInMrdFB)form;
		RecordFoundInMrdUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		RecordFoundInMrdFB fb=(RecordFoundInMrdFB)form;
		//RecordFoundInMrdUTL.saveRecordFoundNArchivalDetail(fb, request);
		if (RecordFoundInMrdUTL.saveRecordFoundNArchivalDetail(fb, request))
		{
			fb.reset(mapping, request);
			WebUTIL.refreshTransState(request);
			Status objStatus = new Status();
			ControllerUTIL.setSysdate(request);
			objStatus.add(Status.NEW);
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
			objStatus.add(Status.DONE,"","Record Found Detail Saved Successfully");
			return mapping.findForward("NEW");
		}
		fb.setHmode("NEW");
		return mapping.findForward("NEW");
		//return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Status objStatus = new Status();
		objStatus.add(Status.LIST);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
}
