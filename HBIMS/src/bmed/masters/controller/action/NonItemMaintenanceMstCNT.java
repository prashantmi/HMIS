package bmed.masters.controller.action;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.ItemMaintenanceMstDATA;
import bmed.masters.controller.data.NonItemMaintenanceMstDATA;
import bmed.masters.controller.fb.ItemMaintenanceMstFB;
import bmed.masters.controller.fb.NonItemMaintenanceMstFB;
import bmed.masters.controller.utl.NonItemMaintenanceMstUTL;

/**
 * @author Aritra Kumar Dhawa Creation Date:- 11-jan-2011. Modifying
 *         Date:-11/Jan/2011. Used For:- Action Class for Non Item Maintenance
 *         Master Process. Team Lead By:- A S Cheema. Module:- BMED(HIS Project)
 * 
 */

public class NonItemMaintenanceMstCNT extends GenericController {

	/** The strtarget. */
	String strtarget = "";

	/**
	 * Invoke Util Constructor to bring list page.
	 */
	public NonItemMaintenanceMstCNT() // Constructor for DrugMstCNT
	{
		super(new NonItemMaintenanceMstUTL(),
				"/masters/NonItemMaintenanceMstCNT");
	}

	/**
	 * is used to forward control to add page.
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
	public ActionForward initializeAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		/*
		 * Save a new transaction token in the user's current session, creating
		 * a new session if necessary.
		 */
		saveToken(request);

		NonItemMaintenanceMstFB formBean = (NonItemMaintenanceMstFB) form;

		NonItemMaintenanceMstDATA.initializeAdd(request, formBean);
		strtarget = "add";
		return mapping.findForward(strtarget);

	}

	/**
	 * GETUPLOADEDFILE method is used to get  Upload File   by using 
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
	
	public ActionForward GETUPLOADEDFILE(ActionMapping mapping_p, ActionForm form_p,
			HttpServletRequest request_p, HttpServletResponse response_p)
	throws HisException 
    {		
		NonItemMaintenanceMstFB formBean_p = (NonItemMaintenanceMstFB)form_p;
		NonItemMaintenanceMstDATA.getUploadedFile(formBean_p, request_p, response_p);
		return null;
	}
	
	/**
	 * INSERT.
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
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NonItemMaintenanceMstFB formBean = (NonItemMaintenanceMstFB) form;
		/*
		 * The method isTokenValid return true if there is a transaction token
		 * stored in the user's current session, and the value submitted as a
		 * request parameter with this action matches it.
		 */
		if (isTokenValid(request, true)) {

			 NonItemMaintenanceMstDATA.insert(request, formBean);

		} else {
			formBean.setStrWarningMsg("Duplicate Form Submission!");
		}

		return this.initializeAdd(mapping, form, request, response);

	}

	/**
	 * is used to forward control to modify page.
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
	public ActionForward MODIFY(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		generateToken(request);
		NonItemMaintenanceMstFB formBean = (NonItemMaintenanceMstFB) form;
		NonItemMaintenanceMstDATA.initializeModify(request, formBean);
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}

	/**
	 * UPDATE.
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
	 * @throws Exception 
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		validateToken(request,response);
		boolean retValue = false;
		
		NonItemMaintenanceMstFB formBean = (NonItemMaintenanceMstFB) form;
		retValue = NonItemMaintenanceMstDATA.update(request, formBean);

		if (retValue) {
			return this.LIST(mapping, form, request, response);
		}
			
		else {
			return this.MODIFY(mapping, form, request, response);
		}
			

	}

	/**
	 * Used to process ajax request for BlockCodeOptions, on change building
	 * code.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
//	public ActionForward getBlockCodeOptions(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		NonItemMaintenanceMstDATA.getBlockCodeOptions(request, response);
//		return null;
//	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
//	public ActionForward getFloorIdOptions(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		NonItemMaintenanceMstDATA.getFloorIdOptions(request, response);
//		return null;
//	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
//	public ActionForward getRoomIdOptions(ActionMapping mapping,
//			ActionForm form, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		NonItemMaintenanceMstDATA.getRoomIdOptions(request, response);
//		return null;
//	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getTaskOptions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NonItemMaintenanceMstDATA.getTaskOptions(request, response);
		return null;
	}
	
	
	public ActionForward getMaintenanceOptions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		NonItemMaintenanceMstDATA.getMaintenanceOptions(request, response);
		return null;
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		generateToken(request);
		NonItemMaintenanceMstFB formBean = (NonItemMaintenanceMstFB) form;
		NonItemMaintenanceMstDATA.initializeView(request, formBean);
		strtarget = "view";
		return mapping.findForward(strtarget);
	} 

}
