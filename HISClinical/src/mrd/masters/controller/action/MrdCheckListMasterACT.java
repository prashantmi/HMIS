package mrd.masters.controller.action;

/**
 * @author Pawan Kumar B N
 * Creation Date 03-Jul-2012
 */
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.MrdCheckListMasterFB;
import mrd.masters.controller.util.MrdCheckListMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MrdCheckListMasterACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		MrdCheckListMasterFB fb=(MrdCheckListMasterFB)form;
		fb.reset(mapping, _request);
		WebUTIL.refreshTransState(_request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MrdCheckListMasterFB fb=(MrdCheckListMasterFB)form;
		
		boolean exist=MrdCheckListMasterUTL.saveMrdCheckListMstDetail(fb, request);
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
		MrdCheckListMasterFB fb = (MrdCheckListMasterFB) form;
		fb.setHmode("MODIFY");
		MrdCheckListMasterUTL.getDataForModify(fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		MrdCheckListMasterFB _fb = (MrdCheckListMasterFB) form;
		flag=MrdCheckListMasterUTL.saveModMrdCheckListMaster(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("MODIFY");}
		
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		MrdCheckListMasterFB _fb = (MrdCheckListMasterFB) form;
		WebUTIL.refreshTransState(_request); 
		MrdCheckListMasterUTL.getDataForModify(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
}
