package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.DepartmentWardDiseaseMstBO;
import ipd.masters.controller.util.DepartmentWardDiseaseMstUTIL;
import ipd.masters.vo.DepartmentWardDiseaseMstVO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DepartmentWardDiseaseMstACTION extends GenericController {

	public DepartmentWardDiseaseMstACTION() {
		super(new DepartmentWardDiseaseMstUTIL(),
				"/masters/CNTDepartmentWardDiseaseMst");
	}

	/**
	 * forwards control to the DepartmentWardDisease Master Add Page
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
			throws HisException, SQLException {

		saveToken(request);
		String strTarget = "add";

		return mapping.findForward(strTarget);
	}

	/**
	 * Invoked by Ajax Functions and sets the required Unit Values.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return null
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward UNITVALUES(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		String val = (String) request.getParameter("deptName");

		String comboValues = null;
		DepartmentWardDiseaseMstVO formBean = (DepartmentWardDiseaseMstVO) form;
		String[] deptCode = val.replace('^', '#').split("#");

		formBean.setStrDepartment(deptCode[0]);
		String wardVal = (String) request.getParameter("wardVal");

		formBean.setStrDeptWardValue(wardVal);
		comboValues = formBean.getStrUnitValues();

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Ipd Master",
					"CNTDepartmentWardDiseaseMst.UNITVALUES()", e.getMessage());
		}

		return null;
	}

	/**
	 * forwards control to the DepartmentWardDisease Master Insert Page
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
			throws HisException, SQLException {
	
		if (isTokenValid(request)) {
		
			DepartmentWardDiseaseMstVO formBean = (DepartmentWardDiseaseMstVO) form;
			DepartmentWardDiseaseMstBO bo = new DepartmentWardDiseaseMstBO();
			
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			bo.insert(formBean);
			
			resetToken(request);
			return this.ADD(mapping, form, request, response);
		} else {
			return mapping.findForward("add");
		}
	}

	/**
	 * forwards control to the DepartmentWardDisease Master Modify Page
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
			throws HisException, SQLException {

		String strTarget = null;
		DepartmentWardDiseaseMstVO formBean = (DepartmentWardDiseaseMstVO) form;
		DepartmentWardDiseaseMstBO bo = new DepartmentWardDiseaseMstBO();
		String strChk = request.getParameter("chk");
		bo.modify(strChk, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);
	}

	/**
	 * forwards control to the DepartmentWardDisease Master Update Page
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
			throws HisException, SQLException {
		// System.out.println(" inside UPDATE");
		boolean fReturnValue = false;
		DepartmentWardDiseaseMstVO formBean = (DepartmentWardDiseaseMstVO) form;
		DepartmentWardDiseaseMstBO boMst = new DepartmentWardDiseaseMstBO();
		
		formBean.setStrLastModSeatId(request.getSession().getAttribute("SEATID").toString());
		
		String strChk = request.getParameter("chk");
		
		

		fReturnValue = boMst.update(strChk, formBean);

		if (fReturnValue) {

			return this.LIST(mapping, form, request, response);
		} else {

			return this.MODIFY(mapping, form, request, response);
		}
	}

}
