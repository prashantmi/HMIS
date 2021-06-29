


/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: Unit Master ACTION
 * 
 * Created on: 14-09-2011
 */



package billing.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.masters.controller.data.UnitMstDATA;
import billing.masters.controller.fb.UnitMstFB;
import billing.masters.controller.util.UnitMstBSUTL;




public class BSUnitMstACTION extends GenericController {

	public BSUnitMstACTION() {

		super(new UnitMstBSUTL(), "/masters/BSCNTUnitMst");
	}

	String strTarget = null;

	/**
	 * forwards control to the Unit Master Add Page
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
			throws Exception, SQLException {
		generateToken(request);
		String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
				.toString();
		String seatid = request.getSession().getAttribute("SEATID").toString();
		String strModuleId = request.getSession().getAttribute("strModuleId").toString();
		String strModuleName = request.getParameter("comboValue");

		UnitMstDATA data=new UnitMstDATA();
		UnitMstFB fb = (UnitMstFB) form;
		
		// fb.setStrUnitComboValues(fb.getStrUnitComboValues());

		fb.setStrHospitalCode(hosCode);
		fb.setStrSeatId(seatid);
		fb.setStrModuleVal(strModuleId);

		try {
			data.initialAdd(fb);
			fb.setStrModuleName(strModuleName);
			//data.modName(fb);
		} catch (Exception e) {
		}
		strTarget = "add";

		return mapping.findForward(strTarget);
	}

	/**
	 * invokes insert logic and forwards control to the Unit Master Add Page
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
			throws Exception, SQLException {
		validateToken(request, response);
		String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
				.toString();
		String seatid = request.getSession().getAttribute("SEATID").toString();
		String strModuleId = request.getSession().getAttribute("strModuleId")
				.toString();

		UnitMstDATA data=new UnitMstDATA();
		UnitMstFB fb = (UnitMstFB) form;

		fb.setStrHospitalCode(hosCode);
		fb.setStrSeatId(seatid);
		fb.setStrModuleVal(strModuleId);
		data.insert(fb);

		return this.ADD(mapping, form, request, response);

	}

	/**
	 * invoks modify logic and forwards control to the Unit Master Modify Page
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
			throws Exception, SQLException {
		generateToken(request);
		UnitMstDATA data=new UnitMstDATA();
		UnitMstFB fb = (UnitMstFB) form;
		String strChk = request.getParameter("chk");
		data.modify(strChk, fb);
		strTarget = "modify";

		return mapping.findForward(strTarget);
	}

	/**
	 * invokes update logic and forwards control to the Unit Master Add or
	 * Modify Page based on the return value
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
			throws Exception, SQLException {
		validateToken(request, response);
		String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
				.toString();
		String seatid = request.getSession().getAttribute("SEATID").toString();

		boolean returnValue = false;
		UnitMstDATA data=new UnitMstDATA();
		UnitMstFB fb = (UnitMstFB) form;
		String strChk = request.getParameter("chk");
		// fb.setStrLastModSeatId(request.getSession().getAttribute("SEATID").toString());
		fb.setStrHospitalCode(hosCode);
		fb.setStrSeatId(seatid);
		fb.setStrModuleVal(request.getSession().getAttribute(
				"strModuleId").toString());
		returnValue = data.update(strChk, fb);

		if (returnValue) {

			return this.LIST(mapping, form, request, response);
		} else {

			return this.MODIFY(mapping, form, request, response);
		}
	}

	/**
	 * invoked by the Ajax function and retrieves Unit Combo Values
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward BASEUNITVAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {

		String strModuleVal = (String) request.getParameter("modName");
		String comboValues = null;
		UnitMstFB fb = (UnitMstFB) form;
		fb.setStrModuleVal(strModuleVal);

		comboValues = fb.getStrUnitComboValues();

		try {
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Unit Master", "UnitMstACTION.BASEUNITVAL()", e
					.getMessage());
		}

		return null;
	}
}
