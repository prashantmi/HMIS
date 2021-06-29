package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import bmed.masters.controller.data.HemConfigMstDATA;
import bmed.masters.controller.fb.HemConfigMstFB;

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
	 *  Module:- BMED(HIS Project)
	 */
	public class HemConfigMstCNT extends CSRFGardTokenAction  
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
		generateToken(request_p);
		HemConfigMstFB fb = (HemConfigMstFB) form_p;
		HemConfigMstDATA.getRecord(fb, request_p);
		
		return mapping_p.findForward("NEW");
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
			HttpServletRequest request_p, HttpServletResponse response)	throws HisException, Exception
	{
		validateToken(request_p,response);
		//boolean bReturnValue = false;

		HemConfigMstFB fb = (HemConfigMstFB) form_p;
		//bReturnValue = HemConfigMstDATA.saveRecord(fb, request_p);
		HemConfigMstDATA.saveRecord(fb, request_p);
		return this.unspecified(mapping_p, form_p, request_p, response);
	}
	
			
}
