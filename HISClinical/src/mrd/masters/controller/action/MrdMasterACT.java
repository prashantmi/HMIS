package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.MrdMasterFB;
import mrd.masters.controller.util.MrdMasterUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MrdMasterACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		return this.ADD(mapping, form, _request, _response);
	}
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		MrdMasterFB fb=(MrdMasterFB)form;
		fb.reset(mapping, _request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		WebUTIL.refreshTransState(_request);
		MrdMasterUTL.getEssentialForMrdMst(fb, _request);
		return mapping.findForward("NEW");
	}
	
	//Getting the List of Employee on the Basis of Department
	public ActionForward EMP_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdMasterFB fb=(MrdMasterFB)form;
		fb.setHmode(fb.getTempMode());
		MrdMasterUTL.getEmployeeListBasedOnDept(fb, request);
		if(fb.getHmode().equals("ADD"))
		{
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("MODIFY");
		}
		
	}
	
	public ActionForward BLOCK_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdMasterFB fb=(MrdMasterFB)form;
		fb.setHmode(fb.getTempMode());
		MrdMasterUTL.getBlockList(fb, request);
		if(fb.getHmode().equals("ADD"))
		{
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("MODIFY");
		}
	}
	
	public ActionForward FLOOR_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdMasterFB fb=(MrdMasterFB)form;
		fb.setHmode(fb.getTempMode());
		MrdMasterUTL.getFloorList(fb, request);
		if(fb.getHmode().equals("ADD"))
		{
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("MODIFY");
		}
	}
	
	public ActionForward ROOM_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MrdMasterFB fb=(MrdMasterFB)form;
		fb.setHmode(fb.getTempMode());
		MrdMasterUTL.getRoomList(fb, request);
		if(fb.getHmode().equals("ADD"))
		{
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("MODIFY");
		}
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MrdMasterFB fb=(MrdMasterFB)form;
		
		boolean exist=MrdMasterUTL.saveMrdDetais(fb, request);
		if(exist)
		{
			return this.ADD(mapping, form, request, response);
		}
		else
		{
			fb.setHmode(fb.getTempMode());
			return mapping.findForward("NEW");
		}
			
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		MrdMasterFB fb = (MrdMasterFB) form;
		WebUTIL.refreshTransState(_request); 
		fb.setHmode("MODIFY");
		fb.setTempMode(fb.getHmode());
		MrdMasterUTL.getDataForModify(fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		MrdMasterFB _fb = (MrdMasterFB) form;
		WebUTIL.refreshTransState(_request); 
		MrdMasterUTL.getDataForModify(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		boolean flag=true;
		MrdMasterFB _fb = (MrdMasterFB) form;
		flag=MrdMasterUTL.saveModMrdMaster(_fb, _request);
		if(flag){
			return mapping.findForward("LIST");	
		}
		else{_fb.setHmode("MODIFY");
			return mapping.findForward("MODIFY");}
		
	}
}
