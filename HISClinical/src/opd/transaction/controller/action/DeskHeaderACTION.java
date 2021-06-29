package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.DoctorDeskFB;
import opd.transaction.controller.util.OpdDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

//public class DeskHeaderACTION extends Action 
public class DeskHeaderACTION extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return HEADER(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}
	
	public ActionForward HEADER(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		System.out.println(" Header ++++++ =====DeskHeaderACTION.execute()");
		return mapping.findForward("HEADER");
	}
	
	public ActionForward AJX_G_PATIENTS_COUNT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		System.out.println("DeskHeaderACTION.AJX_G_PATIENTS_COUNT()");
		System.out.println("inside ipd doctor desk");
		DoctorDeskFB objFB = (DoctorDeskFB) objForm_p;
		objRequest_p.setAttribute("strProcMode", "2");
		StringBuffer strBuff= OpdDeskUTIL.getPatientsCount(objFB, objRequest_p, objResponse_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
}
