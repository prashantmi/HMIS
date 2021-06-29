package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.DoctorDeskFB;
import opd.transaction.controller.util.OpdBayDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

//public class DeskHeaderACTION extends Action 
public class OpdBayDeskHeaderACTION extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		return HEADER(objMapping_p,objForm_p,objRequest_p,objResponse_p);
	}
	
	public ActionForward HEADER(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) throws Exception
	{
		System.out.println("DoctorDeskHeaderACTION.execute()");
		
		DoctorDeskFB objFB = (DoctorDeskFB) objForm_p;
		String deskType = (String) objRequest_p.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		//if(deskType.equals("12")){objFB.setDeskType("1");}
		objFB.setDeskType(deskType);
		return objMapping_p.findForward("HEADER");
	}
	
	public ActionForward AJX_G_PATIENTS_COUNT(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		System.out.println("DeskHeaderACTION.AJX_G_PATIENTS_COUNT()");
		DoctorDeskFB objFB = (DoctorDeskFB) objForm_p;
		objRequest_p.setAttribute("strProcMode", "2");
		StringBuffer strBuff= OpdBayDeskUTIL.getOpdPatientCount(objFB, objRequest_p, objResponse_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
}
