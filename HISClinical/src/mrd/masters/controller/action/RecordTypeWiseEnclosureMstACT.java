package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.RecordTypeWiseEnclosureMstFB;
import mrd.masters.controller.util.RecordTypeWiseEnclosureMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RecordTypeWiseEnclosureMstACT extends CSRFGardTokenAction
{
	
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		RecordTypeWiseEnclosureMstFB fb=(RecordTypeWiseEnclosureMstFB)form;
		WebUTIL.refreshTransState(_request);
		fb.reset(mapping, _request);
		RecordTypeWiseEnclosureMstUTL.getRecordTypeName(fb, _request);
		
		return mapping.findForward("ADD");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeWiseEnclosureMstFB fb=(RecordTypeWiseEnclosureMstFB)form;
		RecordTypeWiseEnclosureMstUTL.viewEnclosure(fb,_request);
		return this.ADD(mapping, form, _request, _response);
	}
	
	public ActionForward GETENCLOSURE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeWiseEnclosureMstFB fb=(RecordTypeWiseEnclosureMstFB)form;
		RecordTypeWiseEnclosureMstUTL.getEnclosureRecordListNotMapped(fb,_request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward POPULATE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeWiseEnclosureMstFB fb=(RecordTypeWiseEnclosureMstFB)form;
		RecordTypeWiseEnclosureMstUTL.populateSelectedEnclosure(fb,_request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RecordTypeWiseEnclosureMstFB _fb = (RecordTypeWiseEnclosureMstFB) form;
		RecordTypeWiseEnclosureMstUTL.saveEnclosure(_fb, _request);
		return mapping.findForward("ADD");
		//return this.ADD(mapping, form, _request, _response);
	}
	
	/*public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeWiseEnclosureMstFB _fb = (RecordTypeWiseEnclosureMstFB) form;
		WebUTIL.refreshTransState(_request); 
		RecordTypeWiseEnclosureMstUTL.getEnclosureRecord(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeWiseEnclosureMstFB _fb = (RecordTypeWiseEnclosureMstFB) form;
		WebUTIL.refreshTransState(_request); 
		RecordTypeWiseEnclosureMstUTL.getEnclosureRecord(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RecordTypeWiseEnclosureMstFB _fb = (RecordTypeWiseEnclosureMstFB) form;
		boolean hasFlag=true;
		hasFlag=RecordTypeWiseEnclosureMstUTL.modifyEnclosureRecord(_fb, _request);
		if(hasFlag==true)
		{
			return mapping.findForward("LIST");	
		}
		else
		{
			return mapping.findForward("MODIFY");
		}
	}*/
	

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

}
