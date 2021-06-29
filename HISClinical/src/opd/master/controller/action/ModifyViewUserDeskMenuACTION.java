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

import opd.master.controller.fb.UserDeskMenuMasterFB;
import opd.master.controller.util.ModifyViewUserDeskMenuMasterUTIL;

public class ModifyViewUserDeskMenuACTION extends CSRFGardTokenAction
{
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB)form;

		WebUTIL.refreshTransState(request);

		ModifyViewUserDeskMenuMasterUTIL.fetchUserDeskRecordData(fb,request);
		ModifyViewUserDeskMenuMasterUTIL.getDeskListByType(fb,request);
		
		return mapping.findForward("NEW");
	}

	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB)form;

		WebUTIL.refreshTransState(request);

		ModifyViewUserDeskMenuMasterUTIL.fetchUserDeskRecordData(fb,request);
		
		
		return mapping.findForward("NEW");
	}

	public ActionForward LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB)form;

		ControllerUTIL.setSysdate(request);

		ModifyViewUserDeskMenuMasterUTIL.UpdateUserDesk(fb,request);

		return mapping.findForward("LIST");
	}
}
