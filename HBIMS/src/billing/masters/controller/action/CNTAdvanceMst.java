package billing.masters.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericController;
import hisglobal.masterutil.MasterInterface;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import billing.masters.bo.BOAdvanceMst;
import billing.masters.controller.data.AdvanceMstDATA;
import billing.masters.controller.fb.VOAdvanceMst;
import billing.masters.controller.util.UTLAdvanceMst;

public class CNTAdvanceMst extends GenericController {

	private String strTarget = null;

	public CNTAdvanceMst() {

		super(new UTLAdvanceMst(), "/masters/CNTAdvanceMst");

	}

	/**
	 * forwards control to the Advance Master Add Page
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
		generateToken(request);
		VOAdvanceMst vo = (VOAdvanceMst) form;
		vo.setStrHospitalCode(request.getSession()
				.getAttribute("HOSPITAL_CODE").toString());

		strTarget = "add";

		return mapping.findForward(strTarget);
	}

	/**
	 * invokes insert Logic and forwards forwards control to the Advance Master
	 * Add Page
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
			throws HisException, Exception {
		validateToken(request, response);
		BOAdvanceMst boMst = new BOAdvanceMst();
		VOAdvanceMst formBean = (VOAdvanceMst) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		boMst.insert(formBean);

		return this.ADD(mapping, form, request, response);

	}

	/**
	 * invokes modify Logic and forwards forwards control to the Advance Modify
	 * Page
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
		generateToken(request);
		BOAdvanceMst boMst = new BOAdvanceMst();
		VOAdvanceMst formBean = (VOAdvanceMst) form;
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strChk = request.getParameter("chk");
		boMst.modify(strChk, formBean);
		strTarget = "modify";

		return mapping.findForward(strTarget);
	}

	/**
	 * invokes update Logic and forwards forwards control to the Advance Add or
	 * Modify Page based on Logical Output
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
			throws HisException, Exception {
		validateToken(request, response);
		BOAdvanceMst boMst = new BOAdvanceMst();
		VOAdvanceMst formBean = (VOAdvanceMst) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrLastModSeatId(request.getSession()
				.getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String strChk = request.getParameter("chk");

		boMst.correctUpdateData(formBean, strChk);

		return this.LIST(mapping, form, request, response);
	}
	public ActionForward DELETEDATA(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		/*BOAdvanceMst boMst = new BOAdvanceMst();
		VOAdvanceMst formBean = (VOAdvanceMst) form;
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());
		formBean.setStrLastModSeatId(request.getSession()
				.getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		String[] strChk = request.getParameterValues("chk");

		boMst.deleteData(formBean, strChk);

		return this.LIST(mapping, form, request, response);*/
		
		
		MasterInterface masterObj = null;

		masterObj = this.getMasterObj();
		AdvanceMstDATA.DELETELIST(request, response, masterObj);
		
		return null;
	}

}
