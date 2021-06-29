
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	OPD(Ipd Doctor Desk)
## Process/Database Object Name	:	IPD Nursing Desk
## Purpose						:	Action File for getting Essentials data for IPD Nursing Desk
## Date of Creation				: 	16-December-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package opd.transaction.controller.action;

import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.DoctorDeskFB;
import opd.transaction.controller.util.DoctorDeskDashboardUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IpdNursingDeskDashboardACTION extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return NEW(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("IpdNursingDeskDashboardACTION.NEW()");
		DoctorDeskFB objFB = (DoctorDeskFB) objForm_p;
		String deskType = (String) objRequest_p.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		String listKey = (String) objRequest_p.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LIST_KEY);
		objFB.setDeskType(deskType);
		objFB.setSelListItemKey(listKey);
		
		String arr[] = listKey.split("@");
		
		if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))
		{
			objFB.setPatCrNo(arr[0]);
			objFB.setEpisodeCode(arr[1]);
			objFB.setEpisodeVisitNo(arr[2]);
			objFB.setDepartmentUnitCode(arr[3]);
			objFB.setWardCode(arr[4]);
			objFB.setPatAdmNo(arr[5]);
			objFB.setRoomCode(arr[6]);
		}
		else if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NON_ACPT_DOCTOR_DESK))
		{
			objFB.setPatCrNo(arr[0]);
			objFB.setEpisodeCode(arr[1]);
			objFB.setEpisodeVisitNo(arr[2]);
			objFB.setDepartmentUnitCode(arr[3]);
			objFB.setWardCode(arr[4]);
			objFB.setPatAdmNo(arr[5]);
			objFB.setRoomCode(arr[6]);
			objFB.setPatMovNo(arr[7]);
		}
		else if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_IN_TRANSIT_DOCTOR_DESK))
		{
			objFB.setPatCrNo(arr[0]);
			objFB.setEpisodeCode(arr[1]);
			objFB.setEpisodeVisitNo(arr[2]);
			objFB.setDepartmentUnitCode(arr[3]);
			objFB.setWardCode(arr[4]);
			objFB.setPatAdmNo(arr[5]);
			objFB.setRoomCode(arr[6]);
			objFB.setPatMovNo(arr[7]);
		}
		else if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_ON_LEAVE_DOCTOR_DESK))
		{
			objFB.setPatCrNo(arr[0]);
			objFB.setEpisodeCode(arr[1]);
			objFB.setEpisodeVisitNo(arr[2]);
			objFB.setDepartmentUnitCode(arr[3]);
			objFB.setWardCode(arr[4]);
			objFB.setPatAdmNo(arr[5]);
			objFB.setRoomCode(arr[6]);
			objFB.setPatMovNo(arr[8]);
		}
		

		System.out.println("CrNo -- episodeCode -- VisitNo -- DeptUnitCode -- wardcode -- admNo -- RoomCode :-  :"+listKey);
		DoctorDeskDashboardUTIL.getDeskPatDtl(objFB, objRequest_p, objResponse_p, objMapping_p);

		return objMapping_p.findForward(deskType);
	}
	
	public ActionForward ADMISSION_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("IpdNursingDeskDashboardACTION.ADMISSION_DETAIL()");
		return objMapping_p.findForward("ADMISSIONDETAIL");
	}
	
}
