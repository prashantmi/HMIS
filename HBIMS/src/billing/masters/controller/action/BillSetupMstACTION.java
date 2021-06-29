

/*
 * Author: Pawan Kumar B N
 * 
 * Process Name: BillSetup Master A
 * 
 * Created on: 16-09-2011
 */




package billing.masters.controller.action;

import hisglobal.exceptions.HisException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import hisglobal.presentation.CSRFGardTokenAction;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import billing.BillConfigUtil;
import billing.masters.controller.data.BillSetupMstDATA;
import billing.masters.controller.fb.BillSetupMstFB;

public class BillSetupMstACTION extends CSRFGardTokenAction {

	public BillSetupMstACTION() {

	}

	/**
	 * forwards control to the generalsetup_bill.jsp Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());

		data.setGenPropValues(formBean);
		String target = "general";

		return mapping.findForward(target);
	}

	/**
	 * forwards control to the generalsetup_bill.jsp Page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward object with target
	 * @throws HisException
	 * @throws SQLException
	 */
	public ActionForward general(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
	
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		formBean.setStrGroupId(BillConfigUtil.GROUP_ID_GENERAL_CHARGES);
		
		data.setGenPropValues(formBean);
		String target = "general";

		return mapping.findForward(target);
	}

	public ActionForward opd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
				formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		data.setOpdValues(formBean);
		String target = "opd";

		return mapping.findForward(target);
	}

	public ActionForward ipd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		data.setIpdComplCharge(formBean);
		String target = "ipd";

		return mapping.findForward(target);
	}

	public ActionForward emergency(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		data.setEmgValues(formBean);
		String target = "emergency";

		return mapping.findForward(target);
	}

	public ActionForward billFormat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.setBillFormatValues(formBean);
		String target = "billFormat";

		return mapping.findForward(target);
	}
	
	public ActionForward surcharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		//generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
				formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
				formBean.setDefsurlim(BillConfigUtil.DEFAULT_SURCHARGE_LIMIT);
		data.setSurchargeValues(formBean);
		String target = "surcharge";

		return mapping.findForward(target);
	}

	public ActionForward jobs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.setJobsValues(formBean);
		String target = "jobs";

		return mapping.findForward(target);
	}

	public ActionForward compulsoryCharge(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
								
		data.setIpdComplCharge(formBean);
		

		String target = "compulsoryCharge";

		return mapping.findForward(target);
	}

	public ActionForward bedCalculationRule(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.setIpdBedCalc(formBean);
		formBean.setIpdBedCalcWardValues(data.getIpdBedCalcWardValues(formBean));
		String target = "bedCalculationRule";

		return mapping.findForward(target);
	}

	public ActionForward generalIpd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		generateToken(request);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		//formBean.setStrIpdFreeCategoryValue(data.getStrIpdFreeCategoryValue(formBean));
		data.setIpdGen(formBean);
		String target = "generalIpd";

		return mapping.findForward(target);
	}

	public ActionForward EMGTARIFFVALUES(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		String val = (String) request.getParameter("grpName");
		String comboValues = null;
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		formBean.setEmgGrpTmpName(val);

		comboValues = data.getEmgTariffValues(formBean);
		formBean.setEmgTariffValues(comboValues);

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Bill Setup", "DAOBillSetup.EMGTARIFFVALUES()", e
					.getMessage());
		}

		return null;
	}

	public ActionForward IPDTARIFFVALUES(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		String val = (String) request.getParameter("grpName");
		String comboValues = null;
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		formBean.setIpdGrpTmpName(val);

		comboValues = data.getIpdComplChrgTariffValues(formBean);

		formBean.setIpdComplChrgTariffValues(comboValues);

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Bill Setup", "DAOBillSetup.IPDTARIFFVALUES()", e
					.getMessage());
		}

		return null;
	}

	public ActionForward IPDSECTARIFFVALUES(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		String val = (String) request.getParameter("grpName");
		String comboValues = null;
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		formBean.setIpdSecGrpTmpName(val);

		comboValues = data.getIpdComplChrgSecTariffValues(formBean);
		formBean.setIpdComplChrgSecTariffValuesI(comboValues);

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Bill Setup", "DAOBillSetup.IPDSECTARIFFVALUES()",
					e.getMessage());
		}

		return null;
	}

	public ActionForward IPDTHIRDTARIFFVALUES(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, SQLException {

		String val = (String) request.getParameter("grpName");
		String comboValues = null;
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		formBean.setIpdThirdGrpTmpName(val);

		comboValues = data.getIpdComplChrgThirdTariffValues(formBean);
		formBean.setIpdComplChrgThirdTariffValues(comboValues);

		try {

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(comboValues);
		} catch (IOException e) {

			new HisException("Bill Setup",
					"DAOBillSetup.IPDTHIRDTARIFFVALUES()", e.getMessage());
		}

		return null;
	}

	public ActionForward GENINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception 
	{
		validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		data.genInsert(formBean);

		return this.general(mapping, form, request, response);
	}
	
	public ActionForward SURINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception 
	{
		//validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID").toString());
		formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
		data.surInsert(formBean);

		return this.surcharge(mapping, form, request, response);
	}

	public ActionForward OPDINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {

		validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.opdInsert(formBean);
		return this.opd(mapping, form, request, response);
	}

	public ActionForward EMGINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {

		validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
		
		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
				.toString());

		data.emgInsert(formBean);

		return this.emergency(mapping, form, request, response);
	}

	public ActionForward JOBINSERT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {

		validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.jobsInsert(formBean);

		return this.jobs(mapping, form, request, response);
	}

	public ActionForward BILLFORMATINSERT(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws HisException, Exception {

		validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
		
		formBean.setStrSeatId(request.getSession().getAttribute(
		"SEATID").toString());

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.billFormatInsert(formBean);

		return this.billFormat(mapping, form, request, response);
	}

	public ActionForward IPDBEDCALC(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, SQLException {

		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());

		data.ipdBedCalcInsert(formBean);

		return this.bedCalculationRule(mapping, form, request, response);
	}

	public ActionForward IPDCOMPCHRG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, SQLException {
		//validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		formBean.setStrSeatId(request.getSession().getAttribute(
		"SEATID").toString());
		data.ipdComplChargeInsert(formBean);

		return this.compulsoryCharge(mapping, form, request, response);
	}

	public ActionForward IPDGENERAL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws HisException, Exception {

		validateToken(request, response);
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		BillSetupMstDATA data = new BillSetupMstDATA();
		
		formBean.setStrSeatId(request.getSession().getAttribute(
		"SEATID").toString());

		formBean.setStrHospitalCode(request.getSession().getAttribute(
				"HOSPITAL_CODE").toString());
		
		data.ipdGenInsert(formBean);

		return this.generalIpd(mapping, form, request, response);
	}
	/**
	 * populates CONSUMABLE CHARGES TARIFF COMBO  using AJAX.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward CONSUMABLECHARGESTARIFFCOMBO(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BillSetupMstFB formBean = (BillSetupMstFB) form;
		try {
			BillSetupMstDATA data = new BillSetupMstDATA();
			String str = request.getParameter("strConsumableChargesGroupId");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			formBean.setStrConsumableChargesGroupId(str);
			formBean.setStrHospitalCode(request.getSession().getAttribute("HOSPITAL_CODE").toString());
			response.setHeader("Cache-Control", "no-cache");
			out.println(data.getConsumableChargesTariffCombo(formBean));
		} catch (Exception e) {

			new HisException("Billing", "CNTBillSetupMst->CONSUMABLECHARGESTARIFFCOMBO()", e.getMessage());

		}
		return null;
	}
	
	public ActionForward INITIALPAGE(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/*ActionForward acFwd = new ActionForward();
		acFwd.setPath("/hisglobal/initPage.jsp");
		acFwd.setContextRelative(true);
		return acFwd;*/
		return mapping.findForward("initPage");
	}

}
