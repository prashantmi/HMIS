package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.MrdRecordMovementDetailFB;
import mrd.transaction.controller.utl.MrdRecordMovementDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MrdRecordMovementDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		WebUTIL.refreshTransState(request);
		fb.setRecordType("");
		fb.reset(mapping, request);
		MrdRecordMovementDetailUTL.getAllRecordTypeList(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMRD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		fb.reset(mapping, request);
		if(!fb.getRecordType().equals("-1"))
		{
			boolean flag =MrdRecordMovementDetailUTL.getMrdList(fb,request); 
			if(flag)
				return mapping.findForward("NEW");
			else
				return this.FROMNTORACK(mapping, form, request, response);
		}
		else
			return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward FROMNTORACK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		MrdRecordMovementDetailUTL.getFromNToRackBasedOnMrd(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward FROMRACK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		MrdRecordMovementDetailUTL.getFromRackBasedOnMrdList(fb,request); 
		fb.setTempChkValue("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward FROMSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		MrdRecordMovementDetailUTL.getFromShelfBasedOnRackList(fb,request); 
		fb.setTempChkValue("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETRECORD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		MrdRecordMovementDetailUTL.getMrdRecordBasedOnShelfList(fb,request);
		fb.setRecordSelectionFlag(MrdConfig.RECORD_SELECTION_ALL);
		return mapping.findForward("NEW");
	}
	
	public ActionForward TORACK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		MrdRecordMovementDetailUTL.getToRackBasedOnMrdList(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward TOSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		MrdRecordMovementDetailUTL.getToShelfBasedOnRackList(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		System.out.println("MrdRecordMovementDetailACT.SAVE()");
		MrdRecordMovementDetailFB fb=(MrdRecordMovementDetailFB)form;
		System.out.println("Movement Option Flag :"+fb.getMovementOptionFlag());
		MrdRecordMovementDetailUTL.saveRecordMovementDetail(fb,request); 
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
}
