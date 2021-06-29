
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	OPD(Ipd Doctor Desk)
## Process/Database Object Name	:	IPD Doctor Desk
## Purpose						:	Action File for getting Essentials data for IPD Doctor Desk
## Date of Creation				: 	10-December-2014
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

public class IpdDoctorDeskDashboardACTION extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return NEW(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("IpdDoctorDeskDashboardACTION.NEW()");
		DoctorDeskFB objFB = (DoctorDeskFB) objForm_p;
		String deskType = (String) objRequest_p.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		String listKey = (String) objRequest_p.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LIST_KEY);
		objFB.setDeskType(deskType);
		objFB.setSelListItemKey(listKey);
		
		String arr[] = listKey.split("@");

		objFB.setPatCrNo(arr[0]);
		objFB.setEpisodeCode(arr[1]);
		objFB.setEpisodeVisitNo(arr[2]);
		objFB.setDepartmentUnitCode(arr[3]);
		objFB.setWardCode(arr[4]);
		objFB.setPatAdmNo(arr[5]);
		objFB.setRoomCode(arr[6]);
		
		DoctorDeskDashboardUTIL.getDeskPatDtl(objFB, objRequest_p, objResponse_p, objMapping_p);

		return objMapping_p.findForward("HEADER");
	}
	public ActionForward ADMISSION_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("IpdDoctorDeskDashboardACTION.ADMISSION_DETAIL()");
		return objMapping_p.findForward("ADMISSIONDETAIL");
	}
}
