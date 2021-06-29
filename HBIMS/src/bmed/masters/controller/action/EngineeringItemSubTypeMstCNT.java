package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.EngineeringItemSubTypeMstDATA;
import bmed.masters.controller.fb.EngineeringItemSubTypeMstFB;
import bmed.masters.controller.utl.EngineeringItemSubTypeMstUTL;
/**
 * @author Arun V.R  
 * Creation Date:- 11-jan-2011 
 * Modifying Date:- 21-jan-2011
 * Used For:-Expertise Master
 * Team Lead By:- Amit Kumar
 * Module:- BMED(HIS Project)
 * 
 */
public class EngineeringItemSubTypeMstCNT extends GenericController {

	/** The strtarget. */
	String strtarget;

	/**
	 * calls super class constructor.
	 */
	public EngineeringItemSubTypeMstCNT() 
	{
		super(new EngineeringItemSubTypeMstUTL(), "/masters/EngineeringItemSubTypeMstCNT");

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
	 * @throws Exception the exception
	 * @throws SQLException the SQL exception
	 */

	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception, SQLException {
		generateToken(request);
		strtarget = "add";

		EngineeringItemSubTypeMstFB formBean = (EngineeringItemSubTypeMstFB) form;

		EngineeringItemSubTypeMstDATA.getAddPageComponent(request, formBean);

		return mapping.findForward(strtarget);
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
		EngineeringItemSubTypeMstFB fb = (EngineeringItemSubTypeMstFB) form;
		EngineeringItemSubTypeMstDATA.InsertRecord(fb, request);

		return this.ADD(mapping, form, request, response);

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
	 * 
	 * @throws HisException the his exception
	 * @throws SQLException the SQL exception
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws HisException, Exception 
	{
		validateToken(request,response);
		boolean retValue = false;
		EngineeringItemSubTypeMstFB formBean = (EngineeringItemSubTypeMstFB) form;
		retValue = EngineeringItemSubTypeMstDATA.updateRecord(formBean,request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);

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
	throws HisException, SQLException {
		generateToken(request);
		EngineeringItemSubTypeMstFB formBean = (EngineeringItemSubTypeMstFB) form;
		formBean.setStrGetView("0");
		EngineeringItemSubTypeMstDATA.modify(request, formBean);
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}




}
