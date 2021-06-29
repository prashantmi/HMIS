package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.master.controller.fb.DrugRouteMstFB;
import opd.master.controller.util.DrugDoseMasterUTIL;
import opd.master.controller.util.DrugRouteMstUTIL;

public class DrugRouteMstACT extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		DrugRouteMstFB fb = (DrugRouteMstFB) form;
		WebUTIL.refreshTransState(request);
		// MacroMstUTIL.donationCompList(fb, request);
		DrugRouteMstUTIL.getItemTypeName(fb, request);
		DrugRouteMstUTIL.getDrugRouteInfo(fb, request) ;
		
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DrugRouteMstFB fb = (DrugRouteMstFB) form;
		if(DrugRouteMstUTIL.saveDrugRouteInfo(fb, request))
		{
			fb.reset(mapping, request);
		}
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		DrugRouteMstFB _fb = (DrugRouteMstFB) form;
		WebUTIL.refreshTransState(_request);
		DrugRouteMstUTIL.fetchDrugRouteInfo(_fb, _request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response) throws Exception
	{
		validateToken(_request,response);
		DrugRouteMstFB _fb = (DrugRouteMstFB) form;
		// DrugRouteMstUTIL.updateDonationCompInfo(_fb, _request);
		if(DrugRouteMstUTIL.saveModDrugRouteInfo(_fb, _request))
			return mapping.findForward("LIST");
		else
			return mapping.findForward("NEW");
	}

	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest _request, HttpServletResponse response)
	{
		DrugRouteMstFB _fb = (DrugRouteMstFB) form;
		WebUTIL.refreshTransState(_request);
		DrugRouteMstUTIL.fetchDrugRouteInfo(_fb, _request);
		return mapping.findForward("NEW");
	}
}
