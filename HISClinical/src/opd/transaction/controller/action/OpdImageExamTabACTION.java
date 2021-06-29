package opd.transaction.controller.action;

/**
 * @author  CDAC
 */

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdImageExamTabFB;
import opd.transaction.controller.util.OpdImageExamTabUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdImageExamTabACTION extends CSRFGardTokenAction
{
	
	// the default action called when a page is loaded for the first time
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	// Setting Essentials for New Page
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		OpdImageExamTabUTIL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	// Starting Image Editor
	public ActionForward GETEDITOR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.getEditorEssentials(fb, request,response);
		//OpdImageExamTabUTIL.createEntryForImage(fb, request);
		return mapping.findForward("NEW");
	}

	// Saving OPD Patient Image
	public ActionForward SAVEIMAGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		/*validateToken(request,response);
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		
		if(OpdImageExamTabUTIL.saveOpdPatientImage(fb, request))
		{
			OpdImageExamTabUTIL.setEssentials(fb, request);
			Status status = new Status();
			status.add(Status.TRANSINPROCESS, "Image Saved Successfully", "");
			WebUTIL.setStatus(request, status);
		}
		return mapping.findForward("NEW");*/
		
		//Added By VASU on 21-AUG-2017
		//validateToken(request,response);
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.saveOpdPatientImage(fb, request);
		OpdImageExamTabUTIL.setEssentials(fb, request);
		fb.setNoOfImages("0");
		//return mapping.findForward("NEW");
		return mapping.findForward("NEW");
		//End VASU 
	}

	// Cancelling The Image Save Process
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
		
		/*OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		Status status = new Status();
		ControllerUTIL.setSysdate(request);
		status.add(Status.TRANSINPROCESS);
		OpdImageExamTabUTIL.removeEntryForImage(fb, request);
		WebUTIL.setStatus(request, status);
		fb.setHmode("NEW");
		return mapping.findForward("NEW");*/
	}

	// Starting Image Editor with Old Image to Edit
	public ActionForward GETOLDEDITOR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.getSetOldEditorEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	// Modifying OPD Patient Image
	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		
		if(OpdImageExamTabUTIL.modifyOpdPatientImage(fb, request))
		{
			OpdImageExamTabUTIL.setEssentials(fb, request);
			Status status = new Status();
			status.add(Status.TRANSINPROCESS, "Image Saved Successfully", "");
			WebUTIL.setStatus(request, status);
		}
		return mapping.findForward("NEW");
	}

	// View Image
	public ActionForward VIEWIMAGELOG(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.setImageLog(fb, request,response);
		return mapping.findForward("VIEW");
	}

	// View Previous Image
	public ActionForward VIEWPREVLOG(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.setPreviousImageLog(fb, request);
		return mapping.findForward("VIEW");
	}

	// View Next Image
	public ActionForward VIEWNEXTLOG(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OpdImageExamTabFB fb = (OpdImageExamTabFB) form;
		OpdImageExamTabUTIL.setNextImageLog(fb, request);
		return mapping.findForward("VIEW");
	}
}
