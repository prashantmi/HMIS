package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.SampleSendToExtLabFB;
import mortuary.transaction.controller.utl.SampleSendToExtLabUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SampleSendToExtLabACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		SampleSendToExtLabFB fb=(SampleSendToExtLabFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.resetRowAdd(mapping, request);
		SampleSendToExtLabUTL.getEssentialForSampleSend(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleSendToExtLabFB fb=(SampleSendToExtLabFB)form;
		SampleSendToExtLabUTL.addRow(fb,request);
		fb.resetRowAdd(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SampleSendToExtLabFB fb=(SampleSendToExtLabFB)form;
		SampleSendToExtLabUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		SampleSendToExtLabFB fb=(SampleSendToExtLabFB)form;
		SampleSendToExtLabUTL.saveSampleSendDetail(fb,request);
	//	return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
}
