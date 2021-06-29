package bmed.reports.controller.action;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bmed.reports.controller.util.ListOfCompletedComplaintsUTIL;
import bmed.reports.controller.fb.ListOfCompletedComplaintsFB;

/**
 * @author   Amit kr
 * Creation Date:- 21-June-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project) List of Completed Complaints Request Reports
 * 
 */
public class ListOfCompletedComplaintsACTION extends DispatchAction 
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
	public ActionForward unspecified(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException, SQLException {

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
	public ActionForward INITDETAIL(ActionMapping mapping_p, ActionForm form_p,	HttpServletRequest request_p, HttpServletResponse response_p)
		throws HisException, SQLException 
	{
		String target="index";
		ListOfCompletedComplaintsFB formBean = (ListOfCompletedComplaintsFB) form_p;
		ListOfCompletedComplaintsUTIL.setInitDtl(formBean, request_p);
		return mapping_p.findForward(target);

	}
	
	/**
	 * To Show Report
	 * 
	 * @param mapping_p the	ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response_p the HttpServletResponse
	 * 
	 * @throws HisException
	 * @throws SQLException
	 */
	public void SHOWRPT(ActionMapping mapping_p, ActionForm form_p,	HttpServletRequest request_p, HttpServletResponse response_p)
			throws HisException, SQLException 
	{
		ListOfCompletedComplaintsFB formBean = (ListOfCompletedComplaintsFB) form_p;
		formBean.setStrHospCode(request_p.getSession().getAttribute("HOSPITAL_CODE").toString());
		ListOfCompletedComplaintsUTIL.showReport(formBean, request_p, response_p);
	}
	
	/**
	 * To Cancel 
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
	public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p, HttpServletRequest request_p, HttpServletResponse response_p)
	 {
	  ActionForward acFwd = new ActionForward();
	  acFwd.setPath("/hisglobal/initPage.jsp");
	  acFwd.setContextRelative(true);
	  return acFwd;
	 }
}
