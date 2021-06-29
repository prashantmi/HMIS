package mms.masters.controller.action;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.masters.controller.data.InvoiceTypeMstDATA;

import mms.masters.controller.fb.InvoiceTypeMstFB;

import mms.masters.controller.utl.InvoiceTypeMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

// TODO: Auto-generated Javadoc
/**
 * The Class InvoiceTypeMstCNT.
 * 
 * @author manas meher
 */

public class InvoiceTypeMstCNT extends GenericController {

	/** The strtarget. */
	String strtarget = "";

	/**
	 * calls super class constructor.
	 */
	public InvoiceTypeMstCNT() // Constructor
	{
		super(new InvoiceTypeMstUTL(), "/masters/InvoiceTypeMstCNT");
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
		InvoiceTypeMstFB fb = (InvoiceTypeMstFB) form;
		InvoiceTypeMstDATA.initialAdd(fb, request);
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
		InvoiceTypeMstFB formBean = (InvoiceTypeMstFB) form;
		InvoiceTypeMstDATA.insertRecord(formBean, request);

		strtarget = "add";
		return mapping.findForward(strtarget);
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
		InvoiceTypeMstFB formBean = (InvoiceTypeMstFB) form;
		InvoiceTypeMstDATA.modifyRecord(formBean, request);

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
		{
			boolean retValue = false;
			InvoiceTypeMstFB formBean = (InvoiceTypeMstFB) form;
			retValue = InvoiceTypeMstDATA.updateRecord(formBean, request);
			if (retValue)
				return this.LIST(mapping, form, request, response);
			else
				return this.MODIFY(mapping, form, request, response);
		}
	}

}
