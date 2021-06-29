package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.ComplaintEscalationMstDATA;
import bmed.masters.controller.fb.ComplaintEscalationMstFB;
import bmed.masters.controller.utl.ComplaintEscalationMstUTL;

/**
 * @author   Amit Kr
 * Creation Date:- 11-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class ComplaintEscalationMstCNT extends GenericController {

	/** The strtarget. */
	String strTarget;

	/**
	 * calls super class constructor.
	 */
	public ComplaintEscalationMstCNT() 
	{
		super(new ComplaintEscalationMstUTL(), "/masters/ComplaintEscalationMstCNT");

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
			
	{
		generateToken(request);

		strTarget = "add";
		ComplaintEscalationMstFB fb = (ComplaintEscalationMstFB) form;
		ComplaintEscalationMstDATA.getAddPageComponent(fb, request);
		return mapping.findForward(strTarget);

	}
	
	/**
	 * Invoked by Ajax Functions and sets the required Item Name Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward EMPINFO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		
		ComplaintEscalationMstFB formBean = (ComplaintEscalationMstFB)form;
		ComplaintEscalationMstDATA.getEmpInfo(formBean, request, response);
		return null;
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
			HttpServletRequest request, HttpServletResponse response) throws Exception
			{
		
		validateToken(request,response);
		ComplaintEscalationMstFB fb = (ComplaintEscalationMstFB) form;
		ComplaintEscalationMstDATA.insertRecord(fb, request);

		return this.ADD(mapping, form, request, response);

	}
	
	/**
	 * Mode is used to forward control to modify page.
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
		ComplaintEscalationMstFB formBean = (ComplaintEscalationMstFB) form;
		//formBean.setStrGetView("0");
		ComplaintEscalationMstDATA.modify(request, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);
	}

	
	/**
	 * Mode is used to UPDATE record in database.
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
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException 
	{
		validateToken(request,response);
		boolean retValue = false;
		ComplaintEscalationMstFB formBean = (ComplaintEscalationMstFB) form;
		retValue = ComplaintEscalationMstDATA.updateRecord(formBean,request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);

	}
	
	
	
}
