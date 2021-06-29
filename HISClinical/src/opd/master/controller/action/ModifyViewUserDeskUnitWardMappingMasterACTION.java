package opd.master.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.UserDeskUnitWardMappingMasterFB;
import opd.master.controller.util.ModifyViewUserDeskUnitWardMappingMasterUTIL;

public class ModifyViewUserDeskUnitWardMappingMasterACTION extends CSRFGardTokenAction
{
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB)form;

		WebUTIL.refreshTransState(request);

		ModifyViewUserDeskUnitWardMappingMasterUTIL.fetchUserDeskRecordData(fb,request);
		ModifyViewUserDeskUnitWardMappingMasterUTIL.getDeskListByType(fb,request);
		
		return mapping.findForward("NEW");
	}

	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB)form;

		WebUTIL.refreshTransState(request);

		ModifyViewUserDeskUnitWardMappingMasterUTIL.fetchUserDeskRecordData(fb,request);
		
		return mapping.findForward("NEW");
	}

	public ActionForward LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB)form;

		ControllerUTIL.setSysdate(request);

		ModifyViewUserDeskUnitWardMappingMasterUTIL.UpdateUserDesk(fb,request);

		return mapping.findForward("LIST");
	}
}
