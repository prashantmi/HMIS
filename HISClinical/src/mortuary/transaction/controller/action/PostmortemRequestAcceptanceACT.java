package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.MortuaryConfig;
import mortuary.transaction.controller.fb.PostmortemRequestAcceptanceFB;
import mortuary.transaction.controller.utl.PostmortemRequestAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostmortemRequestAcceptanceACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		generateToken(request);
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		WebUTIL.refreshTransState(request);
		PostmortemRequestAcceptanceUTL.getPostmortemReqList(fb,request);
		return mapping.findForward("NEW"); 
	}
	
	public ActionForward ACCEPT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.getPostmortemRequestDetail(fb,request);
		fb.setAcceptanceFlag(MortuaryConfig.ACCEPTANCE_FLAG_ACCEPT);
		fb.setAddOpinionFlag(MortuaryConfig.NO);
		fb.setConductedBy(MortuaryConfig.POSTMORTEM_CONDUCTED_BY_TEAM);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REJECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.getPostmortemRequestDetail(fb, request);
		fb.setAcceptanceFlag(MortuaryConfig.ACCEPTANCE_FLAG_REJECT);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDOPINION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.addOpinionRow(fb,request); 
		fb.resetOpinion(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEOPINION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.deleteOpinionRow(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDITEM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.addItemRow(fb,request); 
		fb.resetItem(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEITEM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.deleteItemRow(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDPANELINCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.addPanelInchargeRow(fb,request); 
		fb.resetItem(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEPANELINCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.deletePanelInchargeRow(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward APPROVED(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request,response);
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.approvedPostmortemRequest(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCELSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		validateToken(request,response);
		PostmortemRequestAcceptanceFB fb=(PostmortemRequestAcceptanceFB)form;
		PostmortemRequestAcceptanceUTL.cancelledPostmortemRequest(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
	{
		return this.NEW(mapping, form, request, response); 
	}
		
	
	
}
