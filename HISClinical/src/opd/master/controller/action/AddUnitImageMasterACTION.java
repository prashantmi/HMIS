package opd.master.controller.action;

/**
 * @author  CDAC
 */

import org.apache.struts.actions.DispatchAction;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import opd.master.controller.fb.OPDUnitImageMasterFB;
import opd.master.controller.util.AddUnitImageMasterUTIL;

public class AddUnitImageMasterACTION extends CSRFGardTokenAction
{

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OPDUnitImageMasterFB fb = (OPDUnitImageMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		AddUnitImageMasterUTIL.setEssential(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OPDUnitImageMasterFB fb = (OPDUnitImageMasterFB) form;
		if(AddUnitImageMasterUTIL.AddImageUnitWise(fb, request))
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);
			AddUnitImageMasterUTIL.setEssential(fb, request);
			Status status = new Status();
			status.add(Status.NEW, "Record Saved Successully", "");
			request.setAttribute(Config.STATUS_OBJECT, status);
		}
		return mapping.findForward("SAME");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OPDUnitImageMasterFB fb = (OPDUnitImageMasterFB) form;
		WebUTIL.refreshTransState(request);
		AddUnitImageMasterUTIL.fetchUnitNameRecordData(fb, request);
		AddUnitImageMasterUTIL.fetchSelectedImagesUnitWise(fb, request);
		return mapping.findForward("MODIFY");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OPDUnitImageMasterFB fb = (OPDUnitImageMasterFB) form;
		if(AddUnitImageMasterUTIL.ModifyAddImageUnitWise(fb, request))
			return mapping.findForward("LIST");
		else
			return mapping.findForward("MODIFY");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OPDUnitImageMasterFB fb = (OPDUnitImageMasterFB) form;
		WebUTIL.refreshTransState(request);
		AddUnitImageMasterUTIL.fetchUnitNameRecordData(fb, request);
		AddUnitImageMasterUTIL.getImageForView(fb, request);
		return mapping.findForward("MODIFY");
	}

}
