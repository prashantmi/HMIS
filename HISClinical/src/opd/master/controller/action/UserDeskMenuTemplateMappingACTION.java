package opd.master.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.master.controller.fb.UserDeskMenuTemplateMappingFB;
import opd.master.controller.util.UserDeskMenuTemplateMappingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UserDeskMenuTemplateMappingACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	// On Cancelling
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		return mapping.findForward("CANCEL");
	}
	
	// Getting Started the Master
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb=(UserDeskMenuTemplateMappingFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);

		fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE);
		UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	// On Change of Addition Mode
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		UserDeskMenuTemplateMappingUTIL.getEssential(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Desk Type
	public ActionForward GETDESK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb=(UserDeskMenuTemplateMappingFB) form;	
		UserDeskMenuTemplateMappingUTIL.getDesk(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Desk in Addition Mode "Unit-Wise"
	public ActionForward SHOWMAPPEDUNITS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb=(UserDeskMenuTemplateMappingFB) form;		
		UserDeskMenuTemplateMappingUTIL.getMappedUnits(fb,request);
		return mapping.findForward("NEW");
	}
	
	// On Selection of Desk in Addition Mode "Unit Seat-Wise"
	public ActionForward SHOWMAPPEDUNITSFORUNITSEAT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb=(UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getMappedUnitsForUnitSeat(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Unit in Addition Mode "Unit Seat-Wise"
	public ActionForward GETSEATBYUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getSeatListByUnit(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Desk in Addition Mode "Unit Ward-Wise"
	public ActionForward SHOWMAPPEDUNITSFORWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb=(UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getMappedUnitsForWard(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Unit in Addition Mode "Unit Ward-Wise"
	public ActionForward GETWARDBYUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getWardListByUnit(fb,request);
		return mapping.findForward("NEW");
	}
	
	// On Selection of Desk in Addition Mode "Unit Ward Seat-Wise"
	public ActionForward SHOWMAPPEDUNITSFORUNITSEATWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb=(UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getMappedUnitsForUnitSeatWard(fb,request);
		return mapping.findForward("NEW");
	}

	// On Selection of Unit in Addition Mode "Unit Ward Seat-Wise"
	public ActionForward GETWARDBYUNITFORUWS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getWardListByUnitForUWS(fb,request);
		return mapping.findForward("NEW");
	}
	
	// On Selection of Ward in Addition Mode "Unit Ward Seat-Wise"
	public ActionForward GETSEATBYUNITNWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getSeatListByUnitNWard(fb,request);
		return mapping.findForward("NEW");
	}

	// For Getting Template View On Last Selection of Different Addition Modes
	public ActionForward TEMPLATEBYDESK(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;				
		UserDeskMenuTemplateMappingUTIL.getTemplateByDeskType(fb,request);
		return mapping.findForward("NEW");
		
		/*if(fb.getHmode().equals("ADDNONMAPPEDUNITS"))---------
		{
			UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);
			fb.setHmode("TEMPLATEBYDESK");
			fb.setTempMode("TEMPLATEBYDESK");
			return mapping.findForward("ADD");
		}*/
		
		/*if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
		{
			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb,request);
			return mapping.findForward("ADDWARD");
		}
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))
		{
			if(fb.getTempMode().equals("ADDBYDESKID"))
				fb.setHmode(fb.getTempMode());
			//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
			return mapping.findForward("NEW");
		}
		else
			return mapping.findForward("NEW");*/
	}

	// On Selection of Desk Menu
	public ActionForward GETTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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

		/*if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))--------
		{	
			fb.setIsGoPressed(OpdConfig.STEP1);
			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb, request);
			//UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb, request);		
			return mapping.findForward("ADDWARD");
		}
		
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
		{	
			fb.setIsGoPressed(OpdConfig.STEP2);
			UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb, request);
			UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb, request);		
			return mapping.findForward("ADDUNITSEAT");
		}

		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
		{	
			fb.setIsGoPressed(OpdConfig.STEP2);
			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb, request);
			UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb, request);		
			return mapping.findForward("ADDUNITSEATWARD");
		}*/
		else
			return mapping.findForward("NEW");
	}

	// On Adding Template-Desk Menu Mapping "+" Button
	public ActionForward ADDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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

		/*if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))----------
		{
			return mapping.findForward("NEW");
		}		
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
		{	
			//UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);		
			return mapping.findForward("UNITWISE");
		}
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
			return mapping.findForward("UNITSEATWISE");
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
			return mapping.findForward("UNITWARDWISE");
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
			return mapping.findForward("UNITWARDSEATWISE");
		/*fb.reset(mapping, request);
		if(fb.getHmode().equals("TEMPLATEBYDESK"))
		{
			UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);
			return mapping.findForward("ADD");
		}
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
		{
			fb.setIsGoPressed(OpdConfig.STEP1);
			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb,request);
			return mapping.findForward("ADDWARD");
		}
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
		{
			fb.setIsGoPressed(OpdConfig.STEP2);
			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb,request);
			UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb,request);
			return mapping.findForward("ADDUNITSEATWARD");
		}
		
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
		{
			fb.setIsGoPressed(OpdConfig.STEP1);
			UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);
			
			return mapping.findForward("ADD");
		}*/
		else
			return mapping.findForward("NEW");
	}	

	// On Deleting Template-Desk Menu Mapping "-" Button
	public ActionForward DELETEROW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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

		/*if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE))------------
		{
			return mapping.findForward("NEW");
		}		
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
		{	
			//UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);		
			return mapping.findForward("UNITWISE");
		}
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
			return mapping.findForward("UNITSEATWISE");
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
			return mapping.findForward("UNITWARDWISE");
		else if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
			return mapping.findForward("UNITWARDSEATWISE");
		/*if(fb.getHmode().equals("TEMPLATEBYDESK"))
		{
			UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);
			return mapping.findForward("ADD");
		}
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE))
		{

			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb,request);
			return mapping.findForward("ADDWARD");
		}

		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE))
		{

			UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb,request);
			UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb,request);
			return mapping.findForward("ADDUNITSEATWARD");
		}
		
		if(fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
		{

			UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);
			
			return mapping.findForward("ADDUNITSEAT");
		}*/		
		else
			return mapping.findForward("NEW");
	}
	
	/*public ActionForward DELETEROWFORMODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.deleteRowForDeskId(fb,request);
		fb.setHmode(fb.getTempMode());
		//fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}*/
	
	// On Adding Template in Addition Mode Desk-Wise
	public ActionForward ADDBYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);		
		return mapping.findForward("NEW");
	}

	// On Modification of Template Mapping
	public ActionForward MODIFYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.modifyForDeskId(fb,request);
		//fb.setTempMode(fb.getHmode());-------
		return mapping.findForward("NEW");
	}
	
	// On Deletion of Template Mapping
	public ActionForward DELBYDESKID(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
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
	
	// Adding Non Mapped Unit in Unit Seat-Wise Mode
	public ActionForward ADDNONMAPPEDUNITSFORUNITSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		//fb.setIsGoPressed(OpdConfig.STEP0);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		UserDeskMenuTemplateMappingUTIL.getDeptAndUnit(fb,request);
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("UNITSEATWISE");
	}

	// On Selction of Units in Adding in Unit Seat-Wise Mode
	public ActionForward SELECTSEATS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		//fb.setIsGoPressed(OpdConfig.STEP1);
		//UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb, request);
		UserDeskMenuTemplateMappingUTIL.getGroupList(fb, request);
		return mapping.findForward("UNITSEATWISE");
	}

	// On Selction of Seats in Adding in Unit Seat-Wise Mode
	public ActionForward GETMENUSTEMPLATEFORUNITSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplatesToAddInUnitSeatWise(fb, request);
		//fb.setIsGoPressed(OpdConfig.STEP2);
		//UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("UNITSEATWISE");
	}
	
	// Saving Template Mapped Records in Unit Seat-Wise Addition Mode
	public ActionForward SAVEBYUNITSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		if(UserDeskMenuTemplateMappingUTIL.saveForUnitSeat(fb,request))
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
	
	// Adding Non Mapped Unit in Unit Ward-Wise Mode
	public ActionForward ADDNONMAPPEDUNITSFORWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getDeptAndUnit(fb,request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		//fb.setTempMode(fb.getHmode());
		//fb.setIsGoPressed(OpdConfig.STEP0);
		return mapping.findForward("UNITWARDWISE");
	}
	
	// On Selction of Unit in Adding in Unit Ward-Wise Mode
	public ActionForward GETWARDSFORUNITWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		//fb.setIsGoPressed(OpdConfig.STEP0);
		UserDeskMenuTemplateMappingUTIL.getWardInUnitWardWiseMode(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		return mapping.findForward("UNITWARDWISE");
	}

	// On Selction of Wards in Adding in Unit Ward-Wise Mode
	public ActionForward GETMENUSTEMPLATEFORWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplatesToAddInUnitWardWise(fb, request);		
		//fb.setIsGoPressed(OpdConfig.STEP1);
		//UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getGroupList(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("UNITWARDWISE");
	}
	
	// Saving Template Mapped Records in Unit Ward-Wise Addition Mode
	public ActionForward SAVEBYUNITWARDWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{

		validateToken(request,response);
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		if(UserDeskMenuTemplateMappingUTIL.saveForUnitWardWise(fb,request))
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
	
	// Adding Non Mapped Unit in Unit Ward Seat-Wise Mode
	public ActionForward ADDNONMAPPEDUNITSFORUNITSEATWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getDeptAndUnit(fb,request);		
		//fb.setIsGoPressed(OpdConfig.STEP0);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);		
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("UNITWARDSEATWISE");
	}
	
	// On Selction of Unit in Adding in Unit Ward Seat-Wise Mode
	public ActionForward GETWARDSFORUNITWARDSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getWardInUnitWardWiseMode(fb, request);
		//fb.setIsGoPressed(OpdConfig.STEP0);
		//UserDeskMenuTemplateMappingUTIL.getWardInUnitWardSeatWiseMode(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		return mapping.findForward("UNITWARDSEATWISE");
	}

	// On Selction of Wards in Adding in Unit Ward Seat-Wise Mode
	public ActionForward SELECTSEATSFORUNITSEATWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getGroupList(fb, request);
		//fb.setIsGoPressed(OpdConfig.STEP1);
		//UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getGroupList(fb, request);
		return mapping.findForward("UNITWARDSEATWISE");
	}
	
	// On Selction of Seats in Adding in Unit Ward Seat-Wise Mode
	public ActionForward GETMENUSTEMPLATEFORUNITSEATWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplatesToAddInUnitWardSeatWise(fb, request);		
		//fb.setIsGoPressed(OpdConfig.STEP2);
		//UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb, request);
		//UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		//fb.setTempMode(fb.getHmode());
		return mapping.findForward("UNITWARDSEATWISE");
	}
	
	// Saving Template Mapped Records in Unit Ward-Wise Addition Mode
	public ActionForward SAVEBYUNITWARDSEATWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		generateToken(request);
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		if(UserDeskMenuTemplateMappingUTIL.saveForUnitWardSeatWise(fb,request))
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
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public ActionForward GROUPSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setIsGoPressed(OpdConfig.STEP1);
		UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb, request);
		UserDeskMenuTemplateMappingUTIL.setSourceSeats(fb, request);
		
	//	UserDeskMenuTemplateMappingUTIL.getTemplateByDeskType(fb, request);
		return mapping.findForward("ADDUNITSEAT");
	}
	
	public ActionForward GETDESKBYUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setDeskId("");
		UserDeskMenuTemplateMappingUTIL.getDeskListByUnit(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVEBYUNIT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.saveForUnit(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVEBYUNITWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.saveForUnitWard(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVEBYUNITWARDSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		//UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		//UserDeskMenuTemplateMappingUTIL.saveForUnitWardSeat(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDESKBYUNITNSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setDeskId("");
		UserDeskMenuTemplateMappingUTIL.getDeskListByUnitNSeat(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDESKBYWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setDeskId("");
		UserDeskMenuTemplateMappingUTIL.getDeskListByUnitNWard(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDESKBYUNITNWARDNSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setDeskId("");
		UserDeskMenuTemplateMappingUTIL.getDeskListByUnitNWardNSeat(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTEMPLATEFORMODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplateByTemplateCategory(fb,request);
		fb.setHmode(fb.getTempMode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETTEMPLATEFORUNITSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		UserDeskMenuTemplateMappingUTIL.getTemplateByTemplateCategory(fb,request);
		UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb,request);
		UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb,request);
		return mapping.findForward("ADDUNITSEAT");
		
	}
	
	
	
	
	public ActionForward GROUPSEATFORUNITWARDSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setIsGoPressed(OpdConfig.STEP1);
		UserDeskMenuTemplateMappingUTIL.getSelectedWards(fb, request);
		UserDeskMenuTemplateMappingUTIL.setSourceSeats(fb, request);
		
	//	UserDeskMenuTemplateMappingUTIL.getTemplateByDeskType(fb, request);
		return mapping.findForward("ADDUNITSEATWARD");
	}
	
	
	public ActionForward GETMENUSFORUNITSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMappingFB fb = (UserDeskMenuTemplateMappingFB) form;
		fb.setIsGoPressed(OpdConfig.STEP1);
		UserDeskMenuTemplateMappingUTIL.getSelectedUnits(fb, request);
		UserDeskMenuTemplateMappingUTIL.getSelectedSeats(fb, request);
		UserDeskMenuTemplateMappingUTIL.getMenuNTemplateToAdd(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("ADDUNITSEAT");
	}
	*/
	
}
