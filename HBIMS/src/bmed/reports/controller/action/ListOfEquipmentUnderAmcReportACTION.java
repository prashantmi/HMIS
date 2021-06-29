package bmed.reports.controller.action;

import hisglobal.exceptions.HisException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bmed.reports.controller.util.ListOfEquipmentUnderAmcReportUTIL;
import bmed.reports.controller.fb.ListOfEquipmentUnderAmcReportFB;

/**
 * Developer : Vivek Aggarwal
 * Version : 1.0
 * Date : 21-June-	2011
 *
 */
public class ListOfEquipmentUnderAmcReportACTION extends DispatchAction 
{
	
	/**
	 * Unspecified method
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward unspecified(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)	throws HisException, SQLException 
	{
		return this.INITDETAIL(mapping_p, form_p, request_p, response_p);

	}
	
	/**
	 *	This function is used forward control to main  page
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	 
	public ActionForward INITDETAIL(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)	throws HisException, SQLException
	{
	String target="main";
	ListOfEquipmentUnderAmcReportFB formBean = (ListOfEquipmentUnderAmcReportFB) form_p;
	ListOfEquipmentUnderAmcReportUTIL.setInitDtl(formBean, request_p);
	return mapping_p.findForward(target);

	}
	
	/**
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public void SHOWRPT(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)	throws HisException, SQLException
	{
		ListOfEquipmentUnderAmcReportFB formBean = (ListOfEquipmentUnderAmcReportFB) form_p;
		formBean.setStrHospCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		ListOfEquipmentUnderAmcReportUTIL.showReport(formBean, request_p, response_p);
	}
	
	/**
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @return	ActionForward
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p,HttpServletRequest request_p, HttpServletResponse response_p)	throws HisException, SQLException
	 {
	  ActionForward acFwd = new ActionForward();
	  acFwd.setPath("/hisglobal/initPage.jsp");
	  acFwd.setContextRelative(true);
	  return acFwd;
	 }
}
