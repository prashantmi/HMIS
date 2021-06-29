package mms.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.masters.controller.data.IndentAuthorizationMstDATA;
import mms.masters.controller.fb.IndentAuthorizationMstFB;
import mms.masters.controller.utl.IndentAuthorizationMstUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class IndentAuthorizationMstCNT.
 */
public class IndentAuthorizationMstCNT extends GenericController {
	
	/** The strtarget. */
	String strtarget;

	/**
	 * calls super class constructor.
	 */
	public IndentAuthorizationMstCNT() {

		super(new IndentAuthorizationMstUTL(),
				"/masters/IndentAuthorizationMstCNT");
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

		IndentAuthorizationMstFB formBean = (IndentAuthorizationMstFB) form;

		IndentAuthorizationMstDATA.initialAdd(formBean, request);
		strtarget = "add";
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

		IndentAuthorizationMstFB fb = (IndentAuthorizationMstFB) form;
		IndentAuthorizationMstDATA.insertRecord(fb, request);

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

		IndentAuthorizationMstFB fb = (IndentAuthorizationMstFB) form;
		IndentAuthorizationMstDATA.modifyRecord(fb, request);
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}

	/**
	 * To update data.
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

		IndentAuthorizationMstFB fb = (IndentAuthorizationMstFB) form;
		retValue = IndentAuthorizationMstDATA.updateRecord(fb, request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, fb, request, response);

	}
}
