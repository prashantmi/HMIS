package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.DepartmentWardMstBO;
import ipd.masters.controller.util.DepartmentWardMstUTIL;
import ipd.masters.vo.DepartmentWardMstVO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DepartmentWardMstACTION extends GenericController {

	public DepartmentWardMstACTION() {
		super(new DepartmentWardMstUTIL(), "/masters/CNTDepartmentWardMst");
	}

	/**
	 * forwards control to the DepartmentWard Master Add Page
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
		DepartmentWardMstVO formBean = (DepartmentWardMstVO) form;
		String[] deptCode = val.replace('^', '#').split("#");

		formBean.setStrDeptTempName(deptCode[0]);
		String wardVal = (String) request.getParameter("wardVal");

		formBean.setStrDeptWardValue(wardVal);
		comboValues = formBean.getStrUnitValues();

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Ipd Master", "CNTDepartmentWardMst.UNITVALUES()",
					e.getMessage());
		}

		return null;
	}

	/**
	 * forwards control to the DepartmentWard Master Insert Page
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
		
			DepartmentWardMstVO formBean = (DepartmentWardMstVO) form;
			DepartmentWardMstBO bo = new DepartmentWardMstBO();
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
			
			bo.insert(formBean);
			resetToken(request);
			return this.ADD(mapping, form, request, response);
		} else {
			return mapping.findForward("add");
		}
	}

	/**
	 * forwards control to the DepartmentWard Master Modify Page
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
		DepartmentWardMstVO formBean = (DepartmentWardMstVO) form;
		DepartmentWardMstBO bo = new DepartmentWardMstBO();
		String strChk = request.getParameter("chk");
		bo.modify(strChk, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);
	}

	/**
	 * forwards control to the DepartmentWard Master Update Page
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

		boolean fReturnValue = false;
		DepartmentWardMstVO formBean = (DepartmentWardMstVO) form;
		DepartmentWardMstBO boMst = new DepartmentWardMstBO();
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
