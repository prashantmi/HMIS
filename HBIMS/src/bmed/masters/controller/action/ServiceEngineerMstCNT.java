package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.ServiceEngineerMstDATA;
import bmed.masters.controller.fb.ServiceEngineerMstFB;
import bmed.masters.controller.utl.ServiceEngineerMstUTL;

/**
 * @author   Amit kr
 * Creation Date:- 11-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ServiceEngineerMstCNT extends GenericController {

	/** The strtarget. */
	String strTarget;

	/**
	 * calls super class constructor.
	 */
	public ServiceEngineerMstCNT() 
	{
		super(new ServiceEngineerMstUTL(), "/masters/ServiceEngineerMstCNT");

	}
	
	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		strTarget = "add";
		ServiceEngineerMstFB fb = (ServiceEngineerMstFB) form;
		ServiceEngineerMstDATA.getAddPageComponent(fb, request);
		return mapping.findForward(strTarget);

	}
	/**
	 * To SAVE data in Table.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception the exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request,response);
		ServiceEngineerMstFB fb = (ServiceEngineerMstFB) form;
		ServiceEngineerMstDATA.insertRecord(fb, request);

		return this.ADD(mapping, form, request, response);

	}
	
	/**
	 * is used to forward control to modify page.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException 
	{
		generateToken(request);
		ServiceEngineerMstFB formBean = (ServiceEngineerMstFB) form;
		//formBean.setStrGetView("0");
		ServiceEngineerMstDATA.modify(request, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);
	}

	
	/**
	 * UPDATE.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		validateToken(request,response);
		boolean retValue = false;
		ServiceEngineerMstFB formBean = (ServiceEngineerMstFB) form;
		retValue = ServiceEngineerMstDATA.updateRecord(formBean,request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);

	}
	
	
}
