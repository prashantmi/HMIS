package mms.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mms.masters.controller.data.ItemMstDATA;
import mms.masters.controller.fb.ItemMstFB;
import mms.masters.controller.utl.ItemMstBSUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


// TODO: Auto-generated Javadoc
/**
 * Developer : Tanvi Sappal Version : 1.0 Date : 15/May/2009
 */
public class ItemMstBSCNT extends GenericController {

	/** The strtarget. */
	String strtarget = "";

	/**
	 * Invoke Util Constructor to bring list page.
	 */
	public ItemMstBSCNT() {
		super(new ItemMstBSUTL(), "/masters/ItemMstBSCNT");
	}

	/**
	 * is used to forward control to add page.
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
		generateToken(request);
		// saveToken(request);
		ItemMstFB formBean = (ItemMstFB) form;
		ItemMstDATA.initAdd(request, formBean);
		strtarget = "add";
		return mapping.findForward(strtarget);

	}

	/**
	 * is used to forward control to add page.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		validateToken(request, response);
		// saveToken(request);
		ItemMstFB formBean = (ItemMstFB) form;
		ItemMstDATA.insert(formBean, request);
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
		generateToken(request);
		ItemMstFB formBean = (ItemMstFB) form;
		ItemMstDATA.modifyRecord(formBean, request);
		strtarget = "modify";
		return mapping.findForward(strtarget);
	}

	/**
	 * To Update data.
	 * 
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * @throws Exception 
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        validateToken(request, response);
		boolean retValue = false;

		ItemMstFB formBean = (ItemMstFB) form;
		retValue = ItemMstDATA.updateRecord(formBean, request);

		if (retValue)
			return this.LIST(mapping, form, request, response);
		else
			return this.MODIFY(mapping, form, request, response);
	}

	/**
	 * This function is used to open view page.
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
	public ActionForward VIEW(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {
		generateToken(request);
		ItemMstFB formBean = (ItemMstFB) form;
		ItemMstDATA.view(request, formBean);
		strtarget = "view";
		return mapping.findForward(strtarget);
	}
	/**
	 * This function is used to open view page.
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
	
	public ActionForward GETUPLOADEDFILE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException 
	{
		ItemMstFB formBean = (ItemMstFB) form;
		ItemMstDATA.getUploadedFile(formBean, request, response);
		return null;
	}
	
	
	public ActionForward ConsumeCombo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ItemMstFB fbmst = (ItemMstFB) form;
		ItemMstDATA data = new ItemMstDATA();
		try 
		{
			String strGenericValue = request.getParameter("value");
			String temp[]=strGenericValue.replace("^","#").split("#");
			fbmst.setStrItemBrandId(temp[0]);
			fbmst.setStrInventoryUnitId(temp[1]);
			fbmst.setStrGenericItem(temp[3]);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getconsumeCombo(fbmst));
		} 
		catch (Exception e) 
		{
			new HisException("Billing", "CNTChargeMst->GRPCMB()", e.getMessage());
		}

		return null;
	}
	

}
