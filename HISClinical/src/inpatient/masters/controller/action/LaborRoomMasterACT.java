package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inpatient.masters.controller.fb.LaborRoomMasterFB;
import inpatient.masters.controller.util.LaborRoomMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class LaborRoomMasterACT extends CSRFGardTokenAction 
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		LaborRoomMasterFB fb= (LaborRoomMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);
		LaborRoomMasterUTIL.getEssentails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		boolean flag=false;
		LaborRoomMasterFB fb= (LaborRoomMasterFB)form;
		WebUTIL.refreshTransState(request);
		flag=LaborRoomMasterUTIL.saveDetail(fb, request);
			if(flag==true)
			{
				LaborRoomMasterUTIL.getEssentails(fb, request);
				fb.reset(mapping,request);
			}
	
		return mapping.findForward("SAVE");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	

	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		LaborRoomMasterFB fb= (LaborRoomMasterFB)form;
		WebUTIL.refreshTransState(request);
		LaborRoomMasterUTIL.getEssentails(fb, request);
		LaborRoomMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
		
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		LaborRoomMasterFB fb = (LaborRoomMasterFB)form;
		WebUTIL.refreshTransState(request);
		LaborRoomMasterUTIL.getEssentails(fb, request);
		boolean flag=LaborRoomMasterUTIL.saveModifyDetail(fb,request);

		if(flag)
		{
			return mapping.findForward("LIST");
		}
		else
		{	
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}
		
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LaborRoomMasterFB fb= (LaborRoomMasterFB)form;
		LaborRoomMasterUTIL.getEssentails(fb, request);
		LaborRoomMasterUTIL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}

}
