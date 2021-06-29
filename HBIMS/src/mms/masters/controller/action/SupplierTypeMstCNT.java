/**
 * 
 */
package mms.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.masters.controller.data.SupplierTypeMstDATA;
import mms.masters.controller.fb.SupplierTypeMstFB;
import mms.masters.controller.utl.SupplierTypeMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * Developer : Tanvi Sappal
 * Create Date : 26/Oct/2009
 * Process Name : Supplier Type Master
 * Version : 1.1
 * Modify By/Date : 
 */

public class SupplierTypeMstCNT extends GenericController {
	
	/** The strtarget. */
	String strtarget = "";

	/**
	 * calls super class constructor.
	 */
	public SupplierTypeMstCNT() {
		super(new SupplierTypeMstUTL(), "/masters/SupplierTypeMstCNT");
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
			throws HisException, SQLException {

		strtarget = "add";
		SupplierTypeMstFB fb = (SupplierTypeMstFB) form;
		SupplierTypeMstDATA.initialAdd(fb, request);
		return mapping.findForward(strtarget);

	}

	/**
	 * To add data.
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

		SupplierTypeMstFB fb = (SupplierTypeMstFB) form;
		SupplierTypeMstDATA.insertRecord(fb, request);

		return this.ADD(mapping, form, request, response);
	}

	/**
	 * forwards control to the modify page.
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

		SupplierTypeMstFB fb = (SupplierTypeMstFB) form;
		SupplierTypeMstDATA.modifyRecord(fb, request);
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}

	/**
	 * To modify data.
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
			throws HisException, SQLException {
		boolean retValue = false;

		SupplierTypeMstFB fb = (SupplierTypeMstFB) form;
		retValue = SupplierTypeMstDATA.updateRecord(fb, request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);
	}

}
