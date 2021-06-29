package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.DoctorDeskFB;
import opd.transaction.controller.util.DoctorDeskDashboardUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DoctorDeskDashboardACTION extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return NEW(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}
	
	public ActionForward NEW(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.execute()");
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
		
		DoctorDeskDashboardUTIL.getDeskPatDtl(objFB, objRequest_p, objResponse_p, objMapping_p);

		return objMapping_p.findForward("HEADER");
	}
	  
	public ActionForward AJX_G_PATIENTS_VISIT_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.AJX_G_PATIENTS_VISIT_SUMMARY()");
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		System.out.println("episodeCode  :"+doctorDeskFB.getEpisodeCode());
		System.out.println("episodeVisitNo  :"+doctorDeskFB.getEpisodeVisitNo());
		System.out.println("patCrNo  :"+doctorDeskFB.getPatCrNo());		
		StringBuffer strBuff = DoctorDeskDashboardUTIL.AJX_G_PATIENTS_VISIT_SUMMARY(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}
	public ActionForward VISIT_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.VISITDETAIL()");	
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		DoctorDeskDashboardUTIL.VISITDETAIL(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("VISITDETAIL");
	}
	public ActionForward DIAGNOSIS_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.DIAGNOSISDETAIL()");
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		DoctorDeskDashboardUTIL.DIAGNOSIS_DETAIL(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("DIAGNOSISDETAIL");
	}
	public ActionForward TREATMENT_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.TREATMENTDETAIL()");
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		DoctorDeskDashboardUTIL.TREATMENT_DETAIL(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("TREATMENTDETAIL");
	}
	public ActionForward MEDICAL_HISTORY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.MEDICALHISTORY()");
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		DoctorDeskDashboardUTIL.MEDICAL_HISTORY(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		//DoctorDeskDashboardUTIL.getEssentialsForTemplateHistory(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("MEDICALHISTORY");
	}
	
	public ActionForward INVESTIGATION_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.INVESTIGATIONDETAIL()");
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		DoctorDeskDashboardUTIL.INVESTIGATION_DETAIL(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("INVESTIGATIONDETAIL");
	}
	
	public ActionForward ADMISSION_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskDashboardACTION.ADMISSION_DETAIL()");
		return objMapping_p.findForward("ADMISSIONDETAIL");
	}
	/*public ActionForward DESK_PATIENT_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{	
		DoctorDeskFB doctorDeskFB = (DoctorDeskFB) objForm_p;
		DoctorDeskDashboardUTIL.getDeskPatDtl(doctorDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return objMapping_p.findForward("VISITDETAIL");
	}*/

	public ActionForward AJX_G_DESKMENU_DETAIL(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		System.out.println("DoctorDeskDashboardACTION.AJX_G_DESKMENU_DETAIL()");
		//DynamicDeskFB objFB = (DynamicDeskFB) objForm_p;
		DoctorDeskFB objFB = (DoctorDeskFB) objForm_p;
		StringBuffer strBuff = DoctorDeskDashboardUTIL.getDeskMenusdetails(objFB, objRequest_p, objResponse_p);
		//DynamicDeskUTIL.writeResponse(objResponse_p, strOutput);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
	
}
