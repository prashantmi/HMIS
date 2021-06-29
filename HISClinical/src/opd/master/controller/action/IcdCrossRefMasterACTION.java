package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.IcdCrossRefMasterFB;
import opd.master.controller.util.IcdCrossRefMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IcdCrossRefMasterACTION extends CSRFGardTokenAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward SAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		Status objStatus = new Status();
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request, objStatus);
		if(fb.getPageFlag().equals("ADD_PAGE"))
			return mapping.findForward("ADD");
		else if(fb.getPageFlag().equals("MODIFY_PAGE"))
			return mapping.findForward("MODIFY");
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;

		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		IcdCrossRefMasterUTIL.getInitializeIndexTerm(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		Status objStatus = new Status();
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("ADD");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;

		IcdCrossRefMasterUTIL.getModifyPageComponent(fb, request);
		return mapping.findForward("MODIFY");
	}

	/*
	 * Populating the Icd Cross Ref Modifier level One
	 */
	public ActionForward SETINDEXVALUES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTerms(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifier(fb, request);
		return mapping.findForward("NEW");
	}

	// Add Page
	public ActionForward SETREFMODIFIER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		if (fb.getPageFlag().equals("ADD_PAGE"))
		{
			IcdCrossRefMasterUTIL.getInitializeRefModifier(fb, request);
			return mapping.findForward("ADD");
		}
		else if (fb.getPageFlag().equals("MODIFY_PAGE"))
		{
			IcdCrossRefMasterUTIL.getInitializeRefModifier(fb, request);
			return mapping.findForward("MODIFY");

		}
		else
			return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERTWO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierTwo(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERTHREE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierThree(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERFOUR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierFour(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERFIVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierFive(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERSIX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierSix(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERSEVEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierSeven(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIEREIGHT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierEight(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page
	public ActionForward SETMODIFIERNINE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		IcdCrossRefMasterUTIL.getInitializeModifierNine(fb, request);
		return mapping.findForward("NEW");
	}

	// View Page setting see term for Modifier level 9
	public ActionForward SETSEETERMS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;
		IcdCrossRefMasterUTIL.getSeeTermsForModi(fb, request);
		return mapping.findForward("NEW");
	}

	// To Save date on Add Page
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);

		IcdCrossRefMasterFB fb = (IcdCrossRefMasterFB) form;

		IcdCrossRefMasterUTIL.saveRecord(fb, request);
		fb.setRefIndexCode("");
		fb.setSeeTermRadio("0");
		return this.ADD(mapping, form, request, response);
	}
}
