package bmed.masters.controller.action;

import hisglobal.masterutil.GenericController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.ServiceEngineerExpertiseMstDATA;
import bmed.masters.controller.fb.ServiceEngineerExpertiseMstFB;
import bmed.masters.controller.utl.ServiceEngineerExpertiseMstUTL;



/**
 * @author Aritra Kumar Dhawa Creation Date:- 11-jan-2011. Modifying
 *         Date:-11/Jan/2011. Used For:- Action Class for Non Item Maintenance
 *         Master Process. Team Lead By:- A S Cheema. Module:- BMED(HIS Project)
 * 
 */

public class ServiceEngineerExpertiseMstCNT extends GenericController {

	/** The strtarget. */
	String strtarget = "";

	/**
	 * Invoke Util Constructor to bring list page.
	 */
	public ServiceEngineerExpertiseMstCNT() // Constructor for DrugMstCNT
	{
		super(new ServiceEngineerExpertiseMstUTL(),
				"/masters/ServiceEngineerExpertiseMstCNT");
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
		generateToken(request);

		/*
		 * Save a new transaction token in the user's current session, creating
		 * a new session if necessary.
		 */
		saveToken(request);

		ServiceEngineerExpertiseMstFB formBean = (ServiceEngineerExpertiseMstFB) form;

		ServiceEngineerExpertiseMstDATA.initializeAdd(request, formBean);
		strtarget = "add";
		return mapping.findForward(strtarget);

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
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		validateToken(request,response);
		ServiceEngineerExpertiseMstFB formBean = (ServiceEngineerExpertiseMstFB) form;
		/*
		 * The method isTokenValid return true if there is a transaction token
		 * stored in the user's current session, and the value submitted as a
		 * request parameter with this action matches it.
		 */
		

			 ServiceEngineerExpertiseMstDATA.insert(request, formBean);

		

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
		ServiceEngineerExpertiseMstFB formBean = (ServiceEngineerExpertiseMstFB) form;
		ServiceEngineerExpertiseMstDATA.initializeModify(request, formBean);
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
		
		ServiceEngineerExpertiseMstFB formBean = (ServiceEngineerExpertiseMstFB) form;
		retValue = ServiceEngineerExpertiseMstDATA.update(request, formBean);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);

	} 

}
