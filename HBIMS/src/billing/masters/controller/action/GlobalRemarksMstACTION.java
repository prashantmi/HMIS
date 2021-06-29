/* Global Remarks Master ACTION
 * author: Pawan Kumar B N
 * Created on : 26-Aug-2011
 */
package billing.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.masters.controller.data.GlobalRemarksMstDATA;
import billing.masters.controller.fb.GlobalRemarksMstFB;
import billing.masters.controller.util.GlobalRemarksMstUTL;
import billing.BillConfigUtil;

public class GlobalRemarksMstACTION extends GenericController
{

	String target = null;

	public GlobalRemarksMstACTION()
	{

		super(new GlobalRemarksMstUTL(), "/masters/CNTRemarksMst");
	}

	/**
	 * forwards control to the Remarks Master Add Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward ADD(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		generateToken(request);
		target = "add";
		return mapping.findForward(target);
	}

	/**
	 * invokes insert Logic and forwards control to the Remarks Master Add Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward INSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		validateToken(request, response);
		GlobalRemarksMstDATA data = new GlobalRemarksMstDATA();
		GlobalRemarksMstFB formBean = (GlobalRemarksMstFB) form;
		/*
		 * formBean.setStrHospitalCode(request.getSession().getAttribute(
		 * "HOSPITAL_CODE").toString());
		 */
		formBean.setStrHospitalCode(BillConfigUtil.SUPER_HOSPITAL_CODE);

		formBean.setStrValid(request.getSession().getAttribute("isvalid")
				.toString());
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		data.insertRecord(formBean);

		return this.ADD(mapping, form, request, response);

	}

	/**
	 * forwards control to the Remarks Master Modify Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		generateToken(request);
		GlobalRemarksMstDATA data = new GlobalRemarksMstDATA();
		GlobalRemarksMstFB formBean = (GlobalRemarksMstFB) form;
		String chk = request.getParameter("chk");
		formBean.setStrValid(request.getSession().getAttribute("isvalid")
				.toString());
		data.modifyRecord(chk, formBean);
		target = "modify";

		return mapping.findForward(target);
	}

	/**
	 * invokes update Logic and forwards control to the Remarks Master Add or
	 * Modify Page based on the Logic output
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward UPDATE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException
	{
		validateToken(request, response);
		boolean returnValue = false;
		GlobalRemarksMstDATA data = new GlobalRemarksMstDATA();
		GlobalRemarksMstFB formBean = (GlobalRemarksMstFB) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		formBean.setStrRemarksType(request.getSession().getAttribute("rmkType")
				.toString());

		String strChk = request.getParameter("chk");

		returnValue = data.updateRecord(strChk, formBean);

		if (returnValue)
		{

			return this.LIST(mapping, form, request, response);
		} else
		{

			return this.MODIFY(mapping, form, request, response);
		}
	}
}
