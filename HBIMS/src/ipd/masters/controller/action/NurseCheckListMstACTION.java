package ipd.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import ipd.masters.bo.NurseCheckListMstBO;
import ipd.masters.controller.util.NurseCheckListMstUTIL;
import ipd.masters.vo.NurseCheckListMstVO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class NurseCheckListMstACTION extends GenericController {

	public NurseCheckListMstACTION() {
		super(new NurseCheckListMstUTIL(), "/masters/CNTNurseCheckListMst");
	}

	/**
	 * forwards control to the Nurse Check List Master Add Page
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

		// saveToken(request);
		String strTarget = "add";
		NurseCheckListMstVO formBean = (NurseCheckListMstVO) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
		"HOSPITAL_CODE").toString());
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
		NurseCheckListMstVO formBean = (NurseCheckListMstVO) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
		"HOSPITAL_CODE").toString());
		String val = (String) request.getParameter("deptName");
		String comboValues = null;
		
		
		String[] deptCode = val.replace('^', '#').split("#");
		formBean.setStrDeptTempName(deptCode[0]);

		comboValues = formBean.getStrUnitValues();

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Ipd Master", "CNTNurseCheckListMst.UNITVALUES()",
					e.getMessage());
		}

		return null;
	}

	/**
	 * forwards control to the Nurse Check List Master Add Page with message based on the Insertion.
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

		NurseCheckListMstVO formBean = (NurseCheckListMstVO) form;
		NurseCheckListMstBO bo = new NurseCheckListMstBO();

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		bo.insert(formBean);

		String strTarget = "add";

		return mapping.findForward(strTarget);

	}

	/**
	 * forwards control to the Nurse Check List Master Modify Page
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
		NurseCheckListMstVO formBean = (NurseCheckListMstVO) form;
		NurseCheckListMstBO bo = new NurseCheckListMstBO();
		String strChk = request.getParameter("chk");
		formBean.setStrHospitalCode(request.getSession().getAttribute(
		"HOSPITAL_CODE").toString());
		bo.modify(strChk, formBean);
		strTarget = "modify";
		return mapping.findForward(strTarget);

	}

	/**
	 * forwards control to the Nurse Check List Master List Page or to Modify Page with Message based on the Update Process. 
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
		NurseCheckListMstVO formBean = (NurseCheckListMstVO) form;
		NurseCheckListMstBO bo = new NurseCheckListMstBO();

		formBean.setStrLastModSeatId(request.getSession()
				.getAttribute("SEATID").toString());

		String strChk = request.getParameter("chk");

		fReturnValue = bo.update(strChk, formBean);

		if (fReturnValue) {

			return this.LIST(mapping, form, request, response);
		} else {

			return this.MODIFY(mapping, form, request, response);
		}
		
	}

}
