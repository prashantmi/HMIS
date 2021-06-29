package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.EquipmentTestParameterMstDATA;
import bmed.masters.controller.fb.EquipmentTestParameterMstFB;
import bmed.masters.controller.utl.EquipmentTestParameterMstUTL;

/**
 * @author Arun VR Creation Date:- 07-Aug-2012 Modifying Date:- Used For:- Team
 *         Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestParameterMstCNT extends GenericController {

	/** The strTarget. */
	String strTarget = "";

	/**
	 * calls super class constructor.
	 */
	public EquipmentTestParameterMstCNT() {
		super(new EquipmentTestParameterMstUTL(),
				"/masters/EquipmentTestParameterMstCNT");
	}

	/**
	 * forwards control to the ADD page.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		strTarget = "add";
		EquipmentTestParameterMstFB fb = (EquipmentTestParameterMstFB) form;
		EquipmentTestParameterMstDATA.initializeAdd(fb, request);
		return mapping.findForward(strTarget);

	}

	/**
	 * Get Engg Item Sub Type Combo using Ajax.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the null
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward GETPARAMETERS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		strTarget = "add";
		EquipmentTestParameterMstFB fb = (EquipmentTestParameterMstFB) form;
		EquipmentTestParameterMstDATA.getParameterCmb(fb, request, response);

		// return mapping.findForward(strTarget);

		return this.ADD(mapping, form, request, response);

	}

	/**
	 * To Save data.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws Exception
	 *             the exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward SAVE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		validateToken(request,response);
		EquipmentTestParameterMstFB fb = (EquipmentTestParameterMstFB) form;
		EquipmentTestParameterMstDATA.insertRecord(fb, request);

		return this.ADD(mapping, form, request, response);
	}

	/**
	 * Forwards control to the modify page.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		EquipmentTestParameterMstFB fb = (EquipmentTestParameterMstFB) form;
		EquipmentTestParameterMstDATA.modifyRecord(fb, request);
		strTarget = "modify";
		return mapping.findForward(strTarget);
	}

	/**
	 * To Update data.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {
		boolean bReturnValue = false;
		validateToken(request,response);
		EquipmentTestParameterMstFB fb = (EquipmentTestParameterMstFB) form;
		bReturnValue = EquipmentTestParameterMstDATA.updateRecord(fb, request);

		if (bReturnValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);
	}

}
