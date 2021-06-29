package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.masters.controller.fb.RecordBoundingFB;
import mrd.masters.controller.util.RecordBoundingUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RecordBoundingACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		RecordBoundingFB fb=(RecordBoundingFB)form;
		WebUTIL.refreshTransState(request);
		RecordBoundingUTL.getMrdBoundingEssential(fb,request);
		fb.setBoundingMode(MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE);
		return mapping.findForward("NEW");
	}
	
	public ActionForward BOUNDREC(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RecordBoundingFB fb=(RecordBoundingFB)form;
		RecordBoundingUTL.getMrdBoundedRecordType(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETRACK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		RecordBoundingFB fb=(RecordBoundingFB)form;
		RecordBoundingUTL.getRackBasedOnMrd(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RecordBoundingFB fb=(RecordBoundingFB)form;
		RecordBoundingUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		RecordBoundingFB fb=(RecordBoundingFB)form;
		RecordBoundingUTL.saveRecordBoundingDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
}
