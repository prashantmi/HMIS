package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.MrdRecordReturnFB;
import mrd.transaction.controller.utl.MrdRecordReturnUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MrdRecordReturnACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		MrdRecordReturnFB fb=(MrdRecordReturnFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		MrdRecordReturnUTL.getRequestListForReturn(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREQDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MrdRecordReturnFB fb=(MrdRecordReturnFB)form;
		MrdRecordReturnUTL.getReturnedMrdRecordListByRequestId(fb,request);
		fb.setChangeArchivedFlag(MrdConfig.NO);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordReturnFB fb=(MrdRecordReturnFB)form; 
		MrdRecordReturnUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MrdRecordReturnFB fb=(MrdRecordReturnFB)form;
		if(MrdRecordReturnUTL.saveMrdRecordReturnDetail(fb,request))
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);
			MrdRecordReturnUTL.getRequestListForReturn(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Record Returned Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus= new Status();
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	
}
