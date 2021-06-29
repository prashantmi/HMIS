package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.masters.controller.fb.LaborRoomAreaMstFB;
import inpatient.masters.controller.util.LaborRoomAreaMstUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import opd.master.controller.fb.DeskMenuMasterFB;
import opd.master.controller.util.DeskMenuMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class LaborRoomAreaMstACT extends CSRFGardTokenAction{
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//Getting Essential Data For Add Page 
		generateToken(request);
		LaborRoomAreaMstFB fb=(LaborRoomAreaMstFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		LaborRoomAreaMstUTL.getEssentails(fb, request);
		
		return mapping.findForward("NEW");
	}

	//Saving the Data
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		LaborRoomAreaMstFB fb=(LaborRoomAreaMstFB)form;
		LaborRoomAreaMstUTL.saveLaborRoomAreaMst(fb,request);
		fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}	
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		LaborRoomAreaMstFB fb= (LaborRoomAreaMstFB)form;
		WebUTIL.refreshTransState(request);
		LaborRoomAreaMstUTL.getEssentails(fb, request);
		LaborRoomAreaMstUTL.getModifyDetail(fb, request);
		
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		LaborRoomAreaMstFB fb = (LaborRoomAreaMstFB)form;
		boolean flag=LaborRoomAreaMstUTL.saveModifyDetail(fb,request);
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
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("LIST");		 	   	
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		LaborRoomAreaMstFB fb= (LaborRoomAreaMstFB)form;
		LaborRoomAreaMstUTL.getModifyDetail(fb, request);
		return mapping.findForward("NEW");
	}
}

