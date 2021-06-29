package inpatient.masters.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import inpatient.masters.controller.fb.ComplicationMasterFB;
import inpatient.masters.controller.util.ComplicationMstUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ComplicationMstACT extends CSRFGardTokenAction
{
	// Getting Essential Data For Add Page
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		ComplicationMasterFB fb = (ComplicationMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		ComplicationMstUTL.getCompType(fb, request);
		return mapping.findForward("NEW");
	}

	// Saving the Data
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ComplicationMasterFB fb = (ComplicationMasterFB) form;
		if(ComplicationMstUTL.saveCompMaster(fb, request))
		{
			fb.reset(mapping, request);
		}
		fb.setHmode("ADD");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse _response)
	{
		ComplicationMasterFB fb = (ComplicationMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.setTempMode(fb.getHmode());
		ComplicationMstUTL.getDataForModify(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		ComplicationMasterFB _fb = (ComplicationMasterFB) form;
		if(ComplicationMstUTL.saveModCompMaster(_fb, _request))
		{
			return mapping.findForward("LIST");
		}
		else
		{
			_fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ComplicationMasterFB fb = (ComplicationMasterFB) form;
		WebUTIL.refreshTransState(request);
		ComplicationMstUTL.getDataForModify(fb, request);

		return mapping.findForward("NEW");
	}

}
