package inpatient.masters.controller.action;

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

import inpatient.InpatientConfig;
import inpatient.masters.controller.*;
import inpatient.masters.controller.fb.UnitInvParaMappingMstFB;
import inpatient.masters.controller.util.unitInvParaMappingMstUTL;

public class UnitInvParaMappingMstACT extends CSRFGardTokenAction
{
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping,request);

		unitInvParaMappingMstUTL.setEssential(fb,request);
		
		return mapping.findForward("NEW");
	}

	public ActionForward LIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("LIST");
	}
	
	public ActionForward CHANGEMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
	{
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB) form;
		unitInvParaMappingMstUTL.setEssential(fb,request);
		fb.setUnitId("-1");
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;
		WebUTIL.refreshTransState(request);

		Status status=new Status();
		fb.reset(mapping,request);

		ControllerUTIL.setSysdate(request);
		status.add(Status.NEW);

		unitInvParaMappingMstUTL.setEssential(fb,request);
			
		WebUTIL.setStatus(request,status);
		return mapping.findForward("NEW");
	}

	//Function to save the records
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;

		ControllerUTIL.setSysdate(request);

		boolean saveFlag = false;
		
		if (fb.getAdditionMode().equals(InpatientConfig.UNITWISE_MODE))//For Unit Wise
		{
			saveFlag = 		unitInvParaMappingMstUTL.saveUnitWise(fb, request);
		}
		if (fb.getAdditionMode().equals(InpatientConfig.UNITWARD_WISE))//For UnitWard Wise
		{
			saveFlag = 		unitInvParaMappingMstUTL.saveUnitWardWise(fb, request);
		}
		
		if(saveFlag)
		{
			Status status=new Status();
			
			WebUTIL.refreshTransState(request);
			fb.reset(mapping,request);
			ControllerUTIL.setSysdate(request);
			
			unitInvParaMappingMstUTL.setEssential(fb,request);
			
			status.add(Status.NEW,"Data Saved Successfully","");
			fb.setHmode("ADD");
			WebUTIL.setStatus(request,status);
		}
		return mapping.findForward("SAME");
}

	//Function to fetch the reocrds during modify
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;

		WebUTIL.refreshTransState(request);

		
		unitInvParaMappingMstUTL.fetchData(fb, request);
		unitInvParaMappingMstUTL.setEssentialForModify(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETWARD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;

		ControllerUTIL.setSysdate(request);
		fb.setIsGoPressed(InpatientConfig.STEP1);
		unitInvParaMappingMstUTL.getWardListByUnits(fb,request);
		
		return mapping.findForward("SAME");
	}

	//Function to save the updated records
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;
		
		boolean modifySaveFlag = false;
		
		if(fb.getWardCode()==null)
		{
			modifySaveFlag = unitInvParaMappingMstUTL.saveUnitWiseForModify(fb, request);
		}
		else
		{
			modifySaveFlag = unitInvParaMappingMstUTL.saveUnitWardWiseForModify(fb, request);
		}
		return mapping.findForward("LIST");
	}
	
	//Function to view the record
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		UnitInvParaMappingMstFB fb = (UnitInvParaMappingMstFB)form;
		Status status=new Status();

		WebUTIL.refreshTransState(request);
		status.add(Status.INPROCESS,"","");

		unitInvParaMappingMstUTL.fetchData(fb, request);
		unitInvParaMappingMstUTL.setEssentialForModify(fb,request);
		
		WebUTIL.setStatus(request,status);
		return mapping.findForward("NEW");
	}
}
