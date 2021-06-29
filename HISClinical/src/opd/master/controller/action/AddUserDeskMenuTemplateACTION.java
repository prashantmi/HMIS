package opd.master.controller.action;

/**
 * @author  CDAC
 */

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
import opd.master.controller.fb.UserDeskMenuTemplateMasterFB;
import opd.master.controller.util.AddUserDeskMenuTemplateMasterUTIL;

public class AddUserDeskMenuTemplateACTION extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
//		/generateToken(request);
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);

		AddUserDeskMenuTemplateMasterUTIL.setEssential(fb,request);
		
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

	//This function lists all the desks corresponding to null seat id
	public ActionForward GETDESKSWITHOUTSEAT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedUnitsData(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskListByUnits(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward GETWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedUnitsDataForWard(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getWardListByUnits(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward GETWARDFORWARDSEATWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedUnitsDataForWardSeat(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getWardListForWardSeatWise(fb,request);
		
		return mapping.findForward("SAME");
	}
		
	//This function lists all the menu templates based on selected units and desk
	public ActionForward GETDESKMENU(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedDeskData(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskMenuTemplates(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	//This function lists all the menu templates based only on the selected desk
	public ActionForward GETALLDESKMENU(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedDeskDataForDeskWise(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskMenuTemplates(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	//This function lists all the menu templates based on selected unit,seat and desk
	public ActionForward GETDESKMENUWITHSEAT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedDeskDataWithSeat(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskMenuTemplates(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward GETDESKMENUFORWARDWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedDeskDataWithWard(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskMenuTemplates(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward GETDESKMENUFORWARDSEATWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedDeskDataWithWardSeat(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskMenuTemplates(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB) form;
	
		AddUserDeskMenuTemplateMasterUTIL.setEssential(fb,request);
	
		fb.setDeptId("-1");
		fb.setHmode("ADD");
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;
		WebUTIL.refreshTransState(request);

		Status status=new Status();
		fb.reset(mapping,request);

		ControllerUTIL.setSysdate(request);
		status.add(Status.NEW);

		AddUserDeskMenuTemplateMasterUTIL.setEssential(fb,request);
		fb.setAdditionMode(OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE);
		
		WebUTIL.setStatus(request,status);
		return mapping.findForward("NEW");
	}

	//This function is used to get the seats depending upon the selected units
	public ActionForward GETSEATS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedUnitsDataForSeatWise(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.setSourceSeats(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward GETSEATSFORWARDSEATWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedWard(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.setSeats(fb,request);
		
		return mapping.findForward("SAME");
	}

	//This function is used to get the desk based on selected unit and seat
	public ActionForward GETDESKS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedSeatsData(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskListBySeatsnUnits(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	public ActionForward GETDESKSFORWARDWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedWardsData(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskListByWards(fb,request);
		
		return mapping.findForward("SAME");
	}

	public ActionForward GETDESKSFORWARDSEATWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		AddUserDeskMenuTemplateMasterUTIL.setSelectedSeat(fb,request);
		AddUserDeskMenuTemplateMasterUTIL.getDeskListByWardsSeat(fb,request);
		
		return mapping.findForward("SAME");
	}
	
	//Function to save the records
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		boolean saveFlag = false;
		
		if (fb.getIsGoPressed().equals(OpdConfig.STEP3))//For Unit Seat Wise
		{
			saveFlag = AddUserDeskMenuTemplateMasterUTIL.addTemplateToMenusDeskSeatWise(fb, request);
		}
		if (fb.getIsGoPressed().equals(OpdConfig.STEP2))//For unit Wise
		{
			saveFlag = AddUserDeskMenuTemplateMasterUTIL.addTemplateToMenusDeskUnitsWise(fb, request);
		}
		if (fb.getIsGoPressed().equals(OpdConfig.STEP1))//For desk wise
		{
			saveFlag = AddUserDeskMenuTemplateMasterUTIL.addTemplateToMenusDesk(fb, request);
		}
		if(saveFlag)
		{
			Status status=new Status();
			
			WebUTIL.refreshTransState(request);
			fb.reset(mapping,request);
			ControllerUTIL.setSysdate(request);
			
			AddUserDeskMenuTemplateMasterUTIL.setEssential(fb,request);
			
			status.add(Status.NEW,"Templates Successully Added To Desks","");
			fb.setHmode("ADD");
			WebUTIL.setStatus(request,status);
		}
		return mapping.findForward("SAME");
	}
	
	public ActionForward SAVEWARDWISE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		ControllerUTIL.setSysdate(request);

		boolean saveFlag = false;
		
		if (fb.getIsGoPressed().equals(OpdConfig.STEP3))//For Unit Ward Wise
		{
			saveFlag = AddUserDeskMenuTemplateMasterUTIL.addTemplateToMenusDeskWardWise(fb, request);
		}
		if (fb.getIsGoPressed().equals(OpdConfig.STEP4))//For unitWardSeat Wise
		{
			saveFlag = AddUserDeskMenuTemplateMasterUTIL.addTemplateToMenusDeskWardSeatWise(fb, request);
		}
	
		if(saveFlag)
		{
			Status status=new Status();
			
			WebUTIL.refreshTransState(request);
			fb.reset(mapping,request);
			ControllerUTIL.setSysdate(request);
			
			AddUserDeskMenuTemplateMasterUTIL.setEssential(fb,request);
			
			status.add(Status.NEW,"Templates Successully Added To Desks","");
			fb.setHmode("ADD");
			WebUTIL.setStatus(request,status);
		}
		return mapping.findForward("SAME");
	}
	
	//Function to fetch the reocrds during modify
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;

		WebUTIL.refreshTransState(request);

		//boolean modifyFlag = false;
		//modifyFlag = 
		AddUserDeskMenuTemplateMasterUTIL.fetchUserDeskMenuTemplateRecordData(fb, request);
		
		return mapping.findForward("NEW");
	}
	

	//Function to save the updated records
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;
		
		//boolean modifySaveFlag = false;
		//modifySaveFlag = 
		AddUserDeskMenuTemplateMasterUTIL.AddTemplateToMenusDesk(fb, request);
	
		return mapping.findForward("LIST");
	}
	
	//Function to view the record
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UserDeskMenuTemplateMasterFB fb = (UserDeskMenuTemplateMasterFB)form;
		Status status=new Status();

		WebUTIL.refreshTransState(request);
		status.add(Status.INPROCESS,"","");

		AddUserDeskMenuTemplateMasterUTIL.fetchUserDeskMenuTemplateRecordData(fb,request);
		
		WebUTIL.setStatus(request,status);
		return mapping.findForward("NEW");
	}
}
