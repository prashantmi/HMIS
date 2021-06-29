/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportTemplateMstCNT.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dynamicreports.masters.controller.data.DynamicReportTemplateMstDATA;
import dynamicreports.masters.controller.fb.DynamicReportTemplateMstFB;
import dynamicreports.masters.controller.utl.DynamicReportTemplateMstUTL;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportTemplateMstCNT.
 */
public class DynamicReportTemplateMstCNT extends GenericController {

	/** The strtarget. */
	String strtarget = "";

	/**
	 * calls super class constructor.
	 */

	public DynamicReportTemplateMstCNT() {
		super(new DynamicReportTemplateMstUTL(),
				"masters/DynamicReportTemplateMstCNT");
	}

	/**
	 * Forwards Control to the ADD Page.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws Exception
	 *             the exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		strtarget = "add";
		DynamicReportTemplateMstFB formBean = (DynamicReportTemplateMstFB) form;

		DynamicReportTemplateMstDATA.initialAdd(formBean, request);
		return mapping.findForward(strtarget);
	}

	/**
	 * To Save Data in Database & return Control Back to List Page.
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
		DynamicReportTemplateMstFB formBean = (DynamicReportTemplateMstFB) form;
		DynamicReportTemplateMstDATA.insertRecord(formBean, request);
		return this.ADD(mapping, formBean, request, response);
	}

	/**
	 * forwards control to the modify page.
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

		DynamicReportTemplateMstFB formBean = (DynamicReportTemplateMstFB) form;

		DynamicReportTemplateMstDATA.modifyRecord(formBean, request);
		strtarget = "modify";
		return mapping.findForward(strtarget);
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
			throws HisException, SQLException {

		boolean retValue = false;
		DynamicReportTemplateMstFB formBean = (DynamicReportTemplateMstFB) form;

		retValue = DynamicReportTemplateMstDATA.updateRecord(formBean, request);
		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);
	}

}
