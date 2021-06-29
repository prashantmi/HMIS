package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.DeceasedItemDetailFB;
import mortuary.transaction.controller.utl.DeceasedItemDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedItemDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeceasedItemDetailFB fb=(DeceasedItemDetailFB)form;
		WebUTIL.refreshTransState(request);
		fb.resetItem(mapping, request);
		DeceasedItemDetailUTL.getItemToBePreserved(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeceasedItemDetailFB fb=(DeceasedItemDetailFB)form;
		DeceasedItemDetailUTL.saveItemToBePreserved(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedItemDetailFB fb=(DeceasedItemDetailFB)form;
		DeceasedItemDetailUTL.addRow(fb,request);
		fb.resetItem(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DeceasedItemDetailFB fb=(DeceasedItemDetailFB)form;
		DeceasedItemDetailUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
}
