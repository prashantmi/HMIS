package mms.masters.controller.action;


import hisglobal.exceptions.HisException;

import mms.masters.controller.data.EmployeeDetailMstDATA;
import mms.masters.controller.data.SmsMobileSetupMstDATA;
import mms.masters.controller.fb.EmployeeDetailMstFB;
import mms.masters.controller.fb.SmsMobileSetupMstFB;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


	/**
	 * @author Vivek Aggarwal  
	 * Creation Date:- 12-April-2011 
	 * Modifying Date:- 19-April-2011
	 *  Module:- MMS(HIS Project)
	 */
	public class SmsMobileSetupMstCNT extends DispatchAction  
	{

		
		/**
		 * Forwards control to the Hem Configuration Page.
		 * 
		 * @param mapping_p the ActionMapping
		 * @param form_p the ActionForm
		 * @param request_p the HttpServletRequest
		 * @param response the HttpServletResponse
		 * 
		 * @return the action forward
		 * 
		 * @throws HisException the his exception
		 * @throws SQLException the SQL exception
		 */
	public ActionForward  unspecified(ActionMapping mapping_p, ActionForm form_p, HttpServletRequest request_p, HttpServletResponse response) throws HisException, SQLException 
	{
		SmsMobileSetupMstFB fb = (SmsMobileSetupMstFB) form_p;
		SmsMobileSetupMstDATA.getRecord(fb, request_p);
		
		return mapping_p.findForward("mainPage");
	}
	
	
	 /**
	 * Get Salutation Combo using Ajax. 
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the null
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward GETMOBILENOS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		SmsMobileSetupMstFB fb = (SmsMobileSetupMstFB) form;
		SmsMobileSetupMstDATA.getMobileNos(fb, request,response);
		
		return unspecified(mapping, fb, request, response);
		
	}
	
	
	/**
	 * forwards control to the Home Page.
	 * 
	 * @param mapping_p the ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response the HttpServletResponse
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception	 
	 */

	public ActionForward CANCEL(ActionMapping mapping_p, ActionForm form_p,	HttpServletRequest request_p, HttpServletResponse response) 
	{
		ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;
	}
	
	/**
	 * To Save data.
	 * 
	 * @param mapping_p the ActionMapping
	 * @param form_p the ActionForm
	 * @param request_p the HttpServletRequest
	 * @param response the HttpServletResponse
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward SAVE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response)	throws HisException, SQLException
	{
		//boolean bReturnValue = false;

		SmsMobileSetupMstFB fb = (SmsMobileSetupMstFB) form_p;
		//bReturnValue = SmsMobileSetupMstDATA.saveRecord(fb, request_p);
		SmsMobileSetupMstDATA.saveRecord(fb, request_p);
		return this.unspecified(mapping_p, form_p, request_p, response);
	}
	
			
}
