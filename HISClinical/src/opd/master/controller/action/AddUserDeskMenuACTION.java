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

import com.lowagie.text.Document;

import opd.OpdConfig;
import opd.master.controller.fb.UserDeskMenuMasterFB;
import opd.master.controller.fb.UserDeskUnitWardMappingMasterFB;
import opd.master.controller.util.AddUserDeskMenuMasterUTIL;
import opd.master.controller.util.AddUserDeskUnitWardMappingMasterUTIL;

/**
 * @author dell
 *
 */
public class AddUserDeskMenuACTION extends CSRFGardTokenAction
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
		
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		AddUserDeskMenuMasterUTIL.setEssential(fb, request);
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
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		WebUTIL.refreshTransState(request);
		String deskType = fb.getDeskType();
		fb.reset(mapping, request);
		fb.setDeskType(deskType);
		AddUserDeskMenuMasterUTIL.setEssential(fb, request);
		fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
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
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		fb.setIsGoPressed(OpdConfig.YES);
		AddUserDeskMenuMasterUTIL.setSelectedData(fb, request);
		AddUserDeskMenuMasterUTIL.getGroupList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GROUPSEAT(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		//fb.setIsGoPressed(OpdConfig.YES);
		fb.setIsGoPressed(OpdConfig.CHOICE_YES);
     // Changed By Chetan according to Global Mapping Concept.
	//	AddUserDeskMenuMasterUTIL.setSelectedData(fb, request);
		AddUserDeskMenuMasterUTIL.setSourceSeats(fb, request);
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
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		ControllerUTIL.setSysdate(request);
		boolean saveFlag = false;
		/*if (fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE))
		{
			saveFlag = AddUserDeskMenuMasterUTIL.AddDesktoSeatsUnitWise(fb, request);
		}*/
		if (fb.getAdditionMode().equals(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE))
		{
			saveFlag = AddUserDeskMenuMasterUTIL.addDeskToUnitWise(fb, request);
		}
		if (saveFlag)
		{
			Status status = new Status();
			status.add(Status.NEW, "Desk Successully Added To Seats", "");
			WebUTIL.refreshTransState(request);
			fb.reset(mapping, request);

			AddUserDeskMenuMasterUTIL.setEssential(fb, request);

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
	public ActionForward MAPPINGTYPE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{   
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		fb.setIsGoPressed(OpdConfig.CHOICE_YES);
		AddUserDeskMenuMasterUTIL.getDetailsToMap(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		fb.setDeptId("-1");
		return mapping.findForward("NEW");
	}
	public ActionForward UNITWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuMasterFB fb = (UserDeskMenuMasterFB) form;
		//fb.setIsGoPressed(OpdConfig.NO);
		fb.setIsGoPressed(OpdConfig.CHOICE_YES);
		AddUserDeskMenuMasterUTIL.getWardInUnitWardWiseMode(fb, request);
		return mapping.findForward("NEW");
	}



}
