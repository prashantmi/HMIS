package mrd.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.masters.controller.fb.RackMstFB;
import mrd.masters.controller.util.RackMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RackMstACT extends CSRFGardTokenAction
{
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		generateToken(_request);
		WebUTIL.refreshTransState(_request); 
		RackMstFB fb=(RackMstFB)form;
		fb.reset(mapping, _request);
		//ControllerUTIL.setSysdate(_request);
		RackMstUTL.getEssentialForRackMst(fb, _request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RackMstFB _fb = (RackMstFB) form;
		boolean flag=RackMstUTL.saveRackDetais(_fb, _request);
		if(!flag)
			return mapping.findForward("ADD");
		else
			return this.ADD(mapping, form, _request, _response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RackMstFB _fb = (RackMstFB) form;
		WebUTIL.refreshTransState(_request); 
		_fb.setMode("MODIFY");
		RackMstUTL.getRackDetails(_fb, _request);
		//RackMstUTL.getEssentialForRackMst(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response)
	{
		RackMstFB _fb = (RackMstFB) form;
		WebUTIL.refreshTransState(_request); 
		_fb.setMode("MODIFY");
		RackMstUTL.getRackDetails(_fb, _request);
		//RackMstUTL.getEssentialForRackMst(_fb, _request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse _response) throws Exception
	{
		validateToken(_request,_response);
		RackMstFB _fb = (RackMstFB) form;
		boolean hasFlag=true;
		hasFlag=RackMstUTL.modifyRackDetails(_fb, _request);
		if(hasFlag==true)
		{
			return mapping.findForward("LIST");	
		}
		else
		{
			return mapping.findForward("MODIFY");
		}
		
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		//WebUTIL.refreshTransState(_request);
		return mapping.findForward("LIST");
	}
	
	public ActionForward BLOCK_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response)
	{
		RackMstFB _fb = (RackMstFB) form;
		RackMstUTL.getBlockList(_fb, _request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward FLOOR_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response)
	{
		RackMstFB _fb = (RackMstFB) form;
		RackMstUTL.getFloorList(_fb, _request);
		return mapping.findForward("ADD");
	}
	
	public ActionForward ROOM_LIST(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response)
	{
		RackMstFB _fb = (RackMstFB) form;
		RackMstUTL.getRoomList(_fb, _request);
		return mapping.findForward("ADD");
	}
	

}
