package opd.master.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.ClinicalSectionCompMapFB;
import opd.master.controller.util.ClinicalSectionCompositionMappingUTIL;
import opd.master.controller.util.UserDeskMenuTemplateMappingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ClinicalSectionCompositionMappingACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	// On Cancelling
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		return mapping.findForward("CANCEL");
	}
	
	// Getting Started the Master
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		ClinicalSectionCompMapFB fb=(ClinicalSectionCompMapFB) form;
		WebUTIL.refreshTransState(request);
		ClinicalSectionCompositionMappingUTIL.getComposition(fb,request);
		
		//fb.reset(mapping, request);

		//fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE);
		//UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	// On Change of Addition Mode
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Composition Type
	public ActionForward GETCOMPOSITION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb=(ClinicalSectionCompMapFB) form;	
		ClinicalSectionCompositionMappingUTIL.getComposition(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Desk in Addition Mode "Unit-Wise"
	public ActionForward SHOWMAPPEDUNITS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb=(ClinicalSectionCompMapFB) form;		
		UserDeskMenuTemplateMappingUTIL.getMappedUnits(fb,request);
		return mapping.findForward("NEW");
	}
	
	// On Selection of Desk in Addition Mode "Unit Seat-Wise"
	public ActionForward SHOWMAPPEDUNITSFORUNITSEAT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb=(ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getMappedUnitsForUnitSeat(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Unit in Addition Mode "Unit Seat-Wise"
	public ActionForward GETSEATBYUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getSeatListByUnit(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Desk in Addition Mode "Unit Ward-Wise"
	public ActionForward SHOWMAPPEDUNITSFORWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb=(ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getMappedUnitsForWard(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Unit in Addition Mode "Unit Ward-Wise"
	public ActionForward GETWARDBYUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getWardListByUnit(fb,request);
		return mapping.findForward("NEW");
	}
	
	// On Selection of Desk in Addition Mode "Unit Ward Seat-Wise"
	public ActionForward SHOWMAPPEDUNITSFORUNITSEATWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb=(ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getMappedUnitsForUnitSeatWard(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Unit in Addition Mode "Unit Ward Seat-Wise"
	public ActionForward GETWARDBYUNITFORUWS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getWardListByUnitForUWS(fb,request);
		return mapping.findForward("NEW");
	}
	
	// On Selection of Ward in Addition Mode "Unit Ward Seat-Wise"
	public ActionForward GETSEATBYUNITNWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getSeatListByUnitNWard(fb,request);
		return mapping.findForward("NEW");
	}

	// For Getting Template View On Last Selection of Different Addition Modes
	public ActionForward TEMPLATEBYDESK(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;				
		UserDeskMenuTemplateMappingUTIL.getTemplateByDeskType(fb,request);
		return mapping.findForward("NEW");
		
	}

	// On Selection of Desk Menu
	public ActionForward GETTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplateByTemplateCategory(fb,request);
		//fb.setHmode(fb.getTempMode());//--------------
		
		if(fb.getIsModificationStart().equalsIgnoreCase(OpdConfig.NO))
		{
			if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))
				return mapping.findForward("NEW");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				return mapping.findForward("UNITWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				return mapping.findForward("UNITSEATWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				return mapping.findForward("UNITWARDWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				return mapping.findForward("UNITWARDSEATWISE");
			else
				return mapping.findForward("NEW");
		}

		
		else
			return mapping.findForward("NEW");
	}

	// On Adding Template-Desk Menu Mapping "+" Button
	public ActionForward ADDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.addRowForDeskId(fb,request);		
		//fb.setHmode(fb.getTempMode());//-------------
		
		if(fb.getIsModificationStart().equalsIgnoreCase(OpdConfig.NO))
		{
			if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))
				return mapping.findForward("NEW");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				return mapping.findForward("UNITWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				return mapping.findForward("UNITSEATWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				return mapping.findForward("UNITWARDWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				return mapping.findForward("UNITWARDSEATWISE");
			else
				return mapping.findForward("NEW");
		}

		else
			return mapping.findForward("NEW");
	}	

	// On Deleting Template-Desk Menu Mapping "-" Button
	public ActionForward DELETEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.deleteRowForDeskId(fb,request);
		//fb.setHmode(fb.getTempMode());//------------
		
		if(fb.getIsModificationStart().equalsIgnoreCase(OpdConfig.NO))
		{
			if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))
				return mapping.findForward("NEW");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
				return mapping.findForward("UNITWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
				return mapping.findForward("UNITSEATWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
				return mapping.findForward("UNITWARDWISE");
			else if(fb.getAdditionMode().equalsIgnoreCase(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
				return mapping.findForward("UNITWARDSEATWISE");
			else
				return mapping.findForward("NEW");
		}

			
		else
			return mapping.findForward("NEW");
	}
	
	// On Adding Template in Addition Mode Desk-Wise
	public ActionForward ADDBYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);		
		return mapping.findForward("NEW");
	}

	// On Modification of Template Mapping
	public ActionForward MODIFYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.modifyForDeskId(fb,request);
		//fb.setTempMode(fb.getHmode());-------
		return mapping.findForward("NEW");
	}
	
	// On Deletion of Template Mapping
	public ActionForward DELBYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		generateToken(request);
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		if(UserDeskMenuTemplateMappingUTIL.deleteForDeskId(fb,request))
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);
			UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);

			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Record Deleted Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);			
		}
		return mapping.findForward("NEW");
	}
	
	// Saving Template Mapped Records in Desk-Wise Addition Mode & Modifying
	public ActionForward SAVEBYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		generateToken(request);
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		if(UserDeskMenuTemplateMappingUTIL.saveForDeskId(fb,request))
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);
			UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);

			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);			
		}
		return mapping.findForward("NEW");
	}
	
	// Adding Non Mapped Unit in Unit-Wise Mode
	public ActionForward ADDNONMAPPEDUNITS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		//fb.setIsGoPressed(OpdConfig.STEP0);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		UserDeskMenuTemplateMappingUTIL.getDeptAndUnit(fb,request);
		//fb.setTempMode(fb.getHmode());
		//fb.setHmode("ADDBYDESKID");
		return mapping.findForward("UNITWISE");
	}
	
	// On Selction of Units in Adding in Unit-Wise Mode
	public ActionForward GETMENUSFORUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplatesToAddInUnitWise(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("UNITWISE");
	}
	
	// Saving Template Mapped Records in Unit-Wise Addition Mode
	public ActionForward SAVEFORUNITWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		generateToken(request);
		ClinicalSectionCompMapFB fb = (ClinicalSectionCompMapFB) form;
		if(UserDeskMenuTemplateMappingUTIL.saveForUnitWise(fb,request))
		{
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);
			UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);

			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);			
		}
		return mapping.findForward("NEW");
	}
	
}
