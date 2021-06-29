package bmed.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bmed.masters.controller.data.ItemMaintenanceMstDATA;
import bmed.masters.controller.fb.ItemMaintenanceMstFB;
import bmed.masters.controller.utl.ItemMaintenanceMstUTL;

/**
 * @author Amit kr Creation Date:- 11-jan-2011 Modifying Date:- Used For:- Team
 *         Lead By:- Module:- BMED(HIS Project)
 * 
 */
public class ItemMaintenanceMstCNT extends GenericController {

	/** The strtarget. */
	String strTarget;

	/**
	 * calls super class constructor.
	 */
	public ItemMaintenanceMstCNT() {
		super(new ItemMaintenanceMstUTL(), "/masters/ItemMaintenanceMstCNT");

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
		ItemMaintenanceMstFB fb = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getAddPageComponent(fb, request);
		return mapping.findForward(strTarget);

	}

	/**
	 * Invoked by Ajax Functions get Item Name Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GETITEMNAME(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getItemNameCmb(formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions get Batch No Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GETSTOCKDTL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getStockDtl(formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions get Batch No Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GETWARRNTYMAINTENANCE(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getWarrantyMaintenanceDtl(formBean, request,
				response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions get Item Serial No on Basis of Batch
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward MAINTENANCECMB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getMaintenanceCmb(formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions and get Item Serial No on Basis of Item .
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ITEMSLNOBASISOFITEMNO(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getItemSlNoCmbBasisOfItem(formBean, request,
				response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions get Engg. Item Sub-Type on Basis of Engg. Type.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward GETENGGITEMSUBTYPE(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getEnggItemSubTypeOnBasisOfEnggItemType(
				formBean, request, response);
		return null;
	}

	/**
	 * Invoked by Ajax Functions get List of Avalaible Task list.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward LEFTTASKLIST(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.getLeftListBoxValue(formBean, request, response);
		return null;
	}

	/**
	 * GETUPLOADEDFILE method is used to get Upload File by using
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

	public ActionForward GETUPLOADEDFILE(ActionMapping mapping_p,
			ActionForm form_p, HttpServletRequest request_p,
			HttpServletResponse response_p) throws HisException {
		ItemMaintenanceMstFB formBean_p = (ItemMaintenanceMstFB) form_p;
		ItemMaintenanceMstDATA.getUploadedFile(formBean_p, request_p,
				response_p);
		return null;
	}

	/**
	 * To SAVE data in Table.
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
		ItemMaintenanceMstFB fb = (ItemMaintenanceMstFB) form;
		ItemMaintenanceMstDATA.insertRecord(fb, request);

		return this.ADD(mapping, form, request, response);

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
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		formBean.setStrGetView("0");
		ItemMaintenanceMstDATA.modify(request, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);
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
	 * 
	 * @throws HisException
	 *             the his exception
	 * @throws SQLException
	 *             the SQL exception
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {
		validateToken(request,response);
		boolean retValue = false;
		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		retValue = ItemMaintenanceMstDATA.updateRecord(formBean, request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);

	}

	/**
	 * View page.
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
	 */
	public ActionForward viewPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		generateToken(request);
		strTarget = "viewPage";
		ItemMaintenanceMstFB formBean = (ItemMaintenanceMstFB) form;
		formBean.setStrGetView("1");
		ItemMaintenanceMstDATA.modify(request, formBean);
		return mapping.findForward(strTarget);
	}

}
