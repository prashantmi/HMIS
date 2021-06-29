package bmed.masters.controller.action;
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import bmed.masters.controller.data.ExpertiseMstDATA;
import bmed.masters.controller.fb.ExpertiseMstFB;
import bmed.masters.controller.utl.ExpertiseMstUTL;

/**
 * @author Arun VR  
 * Creation Date:- 20-jan-2011 
 * Modifying Date:- 
 * Used For:-Expertise Master
 * Team Lead By:- Amit Kumar
 * Module:- BMED(HIS Project) 
 */
public class ExpertiseMstCNT extends GenericController{

	/** The strtarget. */
	String strtarget;

	public ExpertiseMstCNT() 
	{
		super(new ExpertiseMstUTL(), "/masters/ExpertiseMstCNT");

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

		ExpertiseMstFB formBean = (ExpertiseMstFB) form;

		ExpertiseMstDATA.getAddPageComponent(request, formBean);

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
		ExpertiseMstFB fb = (ExpertiseMstFB) form;
		ExpertiseMstDATA.InsertRecord(fb, request);

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
	throws HisException, SQLException {
		generateToken(request);
		ExpertiseMstFB formBean = (ExpertiseMstFB) form;
		formBean.setStrGetView("0");
		ExpertiseMstDATA.modify(request, formBean);
		strtarget = "modify";
		return mapping.findForward(strtarget);
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
		ExpertiseMstFB formBean = (ExpertiseMstFB) form;
		retValue = ExpertiseMstDATA.updateRecord(formBean,request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);

	}



}
