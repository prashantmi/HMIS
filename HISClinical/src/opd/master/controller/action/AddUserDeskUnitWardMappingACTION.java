package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import opd.OpdConfig;
import opd.master.controller.fb.UserDeskUnitWardMappingMasterFB;
import opd.master.controller.util.AddUserDeskUnitWardMappingMasterUTIL;

/**
 * @author dell
 *
 */
/**
 * @author user
 *
 */
public class AddUserDeskUnitWardMappingACTION extends CSRFGardTokenAction
{
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		AddUserDeskUnitWardMappingMasterUTIL.setEssential(fb, request);
		return mapping.findForward("NEW");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward LIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);	
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		WebUTIL.refreshTransState(request);
		String deskType = fb.getDeskType();
		fb.reset(mapping, request);
		fb.setDeskType(deskType);
		AddUserDeskUnitWardMappingMasterUTIL.setEssential(fb, request);
		fb.setAdditionMode(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE);
		fb.setAdditionMode(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE);
		return mapping.findForward("NEW");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward SELECTSEATS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		fb.setIsGoPressed(OpdConfig.YES);
		AddUserDeskUnitWardMappingMasterUTIL.setSelectedData(fb, request);
		AddUserDeskUnitWardMappingMasterUTIL.getGroupList(fb, request);
		return mapping.findForward("NEW");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward GROUPSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		fb.setIsGoPressed(OpdConfig.YES);
		AddUserDeskUnitWardMappingMasterUTIL.setSelectedData(fb, request);
		AddUserDeskUnitWardMappingMasterUTIL.setSourceSeats(fb, request);
		return mapping.findForward("NEW");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward UNITWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		fb.setIsGoPressed(OpdConfig.NO);
		AddUserDeskUnitWardMappingMasterUTIL.getWardInUnitWardWiseMode(fb, request);
		return mapping.findForward("NEW");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward UNITWARDSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		fb.setIsGoPressed(OpdConfig.NO);
		AddUserDeskUnitWardMappingMasterUTIL.getWardInUnitWardWiseSeatMode(fb, request);
		return mapping.findForward("NEW");
	}



	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		ControllerUTIL.setSysdate(request);
		boolean saveFlag = false;
		
		
		if (fb.getAdditionMode().equals(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WISE))
		{
			saveFlag = AddUserDeskUnitWardMappingMasterUTIL.addDeskToUnitWise(fb, request);
		}
		if (fb.getAdditionMode().equals(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_WISE))
		{
			saveFlag = AddUserDeskUnitWardMappingMasterUTIL.addDeskToUnitWardWise(fb, request);
		}
		if (fb.getAdditionMode().equals(OpdConfig.USER_DESK_UNIT_WARD_MAPPING_ADDITION_MODE_UNITS_WARD_SEAT_WISE))
		{
			fb.setUnitId(request.getParameter("unitId"));
			saveFlag = AddUserDeskUnitWardMappingMasterUTIL.addDeskToUnitWardSeatWise(fb, request);
		}
		if (saveFlag)
		{
			Status status = new Status();
			status.add(Status.NEW, "Desk Successully Added To Seats", "");
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);

			AddUserDeskUnitWardMappingMasterUTIL.setEssential(fb, request);

			WebUTIL.setStatus(request, status);
		}
		return mapping.findForward("NEW");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskUnitWardMappingMasterFB fb = (UserDeskUnitWardMappingMasterFB) form;
		fb.setDeptId("-1");
		return mapping.findForward("NEW");
	}
}
