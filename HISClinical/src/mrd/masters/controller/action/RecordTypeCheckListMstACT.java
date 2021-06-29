package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.RecordTypeCheckListMstFB;
import mrd.masters.controller.util.RecordTypeCheckListMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RecordTypeCheckListMstACT extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		fb.reset(mapping, _request);
		WebUTIL.refreshTransState(_request);
		RecordTypeCheckListMstUTL.getEssentialForReqPurposeMst(fb, _request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		RecordTypeCheckListMstUTL.getDataForModify(fb, _request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETADDEDCHECKLST(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		RecordTypeCheckListMstUTL.getAddedCheckListByRecordType(fb, _request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCHECKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		RecordTypeCheckListMstUTL.getCheckListListNotMapped(fb, _request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		RecordTypeCheckListMstUTL.populateSelectedCheckList(fb,_request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		RecordTypeCheckListMstUTL.saveRecordTypeCheckListMst(fb, _request);
		//return mapping.findForward("NEW");
		return this.ADD(mapping, form, _request, _response);
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeCheckListMstFB fb=(RecordTypeCheckListMstFB)form;
		RecordTypeCheckListMstUTL.getDataForModify(fb, _request);
		return mapping.findForward("NEW");
		
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
}
