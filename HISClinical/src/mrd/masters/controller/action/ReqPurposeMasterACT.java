package mrd.masters.controller.action;
/**
 * @author Pawan Kumar B N
 * Creation Date 03-Jul-2012
 */

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.ReqPurposeMasterFB;
import mrd.masters.controller.util.RequestPurposeMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ReqPurposeMasterACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		ReqPurposeMasterFB fb=(ReqPurposeMasterFB)form;
		fb.reset(mapping, _request);
		WebUTIL.refreshTransState(_request);
		RequestPurposeMasterUTL.getEssentialForReqPurposeMst(fb, _request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ReqPurposeMasterFB fb=(ReqPurposeMasterFB)form;
		
		boolean exist=RequestPurposeMasterUTL.saveReqPurposeDetail(fb, request);
		if(exist)
		{
			fb.setHmode("ADD");
			return this.ADD(mapping, form, request, response);
		}
		else
		{
			fb.setHmode("ADD");
			return mapping.findForward("NEW");
		}
			
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ReqPurposeMasterFB fb = (ReqPurposeMasterFB) form;
		fb.setHmode("MODIFY");
		RequestPurposeMasterUTL.getDataForModify(fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		ReqPurposeMasterFB _fb = (ReqPurposeMasterFB) form;
		flag=RequestPurposeMasterUTL.saveModReqPurposeMaster(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("MODIFY");}
		
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		ReqPurposeMasterFB _fb = (ReqPurposeMasterFB) form;
		WebUTIL.refreshTransState(_request); 
		RequestPurposeMasterUTL.getDataForModify(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
}
