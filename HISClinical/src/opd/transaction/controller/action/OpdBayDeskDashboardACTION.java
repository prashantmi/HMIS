package opd.transaction.controller.action;

import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.DoctorDeskFB;
import opd.transaction.controller.util.OpdBayDeskDashboardUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OpdBayDeskDashboardACTION extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return NEW(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("OpdBayDeskDashboardACTION.NEW()");
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
		objFB.setRoomCode(arr[4]);

		System.out.println("episodeCode  :"+objFB.getEpisodeCode());
		System.out.println("episodeVisitNo  :"+objFB.getEpisodeVisitNo());
		System.out.println("patCrNo  :"+objFB.getPatCrNo());	
		
		OpdBayDeskDashboardUTIL.getDeskPatDtl(objFB, objRequest_p, objResponse_p, objMapping_p);

		return objMapping_p.findForward("HEADER");
	}
	  
	public ActionForward AJX_G_PATIENTS_VISIT_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("OpdBayDeskDashboardACTION.AJX_G_PATIENTS_VISIT_SUMMARY()");
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		System.out.println("episodeCode  :"+doctorDeskFB.getEpisodeCode());
		System.out.println("episodeVisitNo  :"+doctorDeskFB.getEpisodeVisitNo());
		System.out.println("patCrNo  :"+doctorDeskFB.getPatCrNo());		
		StringBuffer strBuff = OpdBayDeskDashboardUTIL.AJX_G_PATIENTS_VISIT_SUMMARY(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}
	public ActionForward VISIT_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("OpdBayDeskDashboardACTION.VISIT_DETAIL()");	
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		OpdBayDeskDashboardUTIL.VISITDETAIL(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("VISITDETAIL");
	}
	public ActionForward DESK_PATIENT_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("OpdBayDeskDashboardACTION.DESK_PATIENT_DETAIL()");	
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		OpdBayDeskDashboardUTIL.getDeskPatDtl(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("VISITDETAIL");
	}
	
}
