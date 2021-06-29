/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstDATA.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import dynamicreports.masters.bo.DynamicReportParamMstBO;
import dynamicreports.masters.controller.fb.DynamicReportParamMstFB;
import dynamicreports.masters.controller.hlp.DynamicReportParamMstHLP;
import dynamicreports.masters.vo.DynamicReportParamMstVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstDATA.
 */
public class DynamicReportParamMstDATA {

	/**
	 * Inits the.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void init(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		HisUtil util = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			bo.init(vo);

			util = new HisUtil("dynamicreports", "DynamicReportParamMstDATA");

			if (vo.getWsReportTypeValues() != null
					&& vo.getWsReportTypeValues().size() > 0) {

				formBean.setStrReportTypeValues(util.getOptionValue(
						vo.getWsReportTypeValues(), "0", "0^Select Value",
						false));

			} else {
				formBean.setStrReportTypeValues("<option value='0'>Select Value</option>");
			}

			if (vo.getWsProcedureValues() != null
					&& vo.getWsProcedureValues().size() > 0) {

				formBean.setStrProcedureValues(util.getOptionValue(
						vo.getWsProcedureValues(), "0", "0^Select Value", false));

			} else {
				formBean.setStrProcedureValues("<option value='0'>Select Value</option>");
			}

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "DynamicReportParamMstDATA.init(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->init()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			util = null;
		}

	}

	/**
	 * Gets the report list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the report list
	 */
	public static void getReportList(DynamicReportParamMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		HisUtil util = null;

		String strReportList = "";

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			vo.setStrReportTypeId(formBean.getStrReportTypeId());

			bo.getReportList(vo);

			util = new HisUtil("dynamicreports", "DynamicReportParamMstDATA");

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {
				if (vo.getWsReportValues() != null
						|| vo.getWsReportValues().size() > 0) {
					strReportList = util.getOptionValue(vo.getWsReportValues(),
							"0", "0^Select Value", true);
				} else {
					strReportList = "<option value='0'>Select Value</option>";

				}

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strReportList);

				} catch (Exception e) {
					strmsgText = "DynamicReportParamMstDATA.getReportList(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException("dynamicreports",
							"DynamicReportParamMstDATA->init()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");
				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.getReportList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->getReportList()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportParamMstDATA.getReportList(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportParamMstDATA->getReportList()",
						strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;
			util = null;
		}
	}

	/**
	 * Gets the proc in param list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the proc in param list
	 */
	public static void getProcInParamList(DynamicReportParamMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strProcParamDetails = "";

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			bo.getProcInParamList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				strProcParamDetails = DynamicReportParamMstHLP
						.getProcParameterList(vo.getWsProcInParam());

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strProcParamDetails);

				} catch (Exception e) {
					strmsgText = "DynamicReportParamMstDATA.getProcInParamList(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException("dynamicreports",
							"DynamicReportParamMstDATA->init()", strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.getProcInParamList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->getProcInParamList()",
					strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportParamMstDATA.getProcInParamList(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportParamMstDATA->init()", strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * Gets the drill down proc pre in param list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the drill down proc pre in param list
	 */
	public static void getDrillDownProcPreInParamList(
			DynamicReportParamMstFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strProcParamDetails = "";

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			bo.getDrillDownProcInParamList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				strProcParamDetails = DynamicReportParamMstHLP
						.getDrillDownPreProcParameterList(vo.getWsProcInParam());

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strProcParamDetails);

				} catch (Exception e) {
					strmsgText = "DynamicReportParamMstDATA.getDrillDownProcPreInParamList(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException(
							"dynamicreports",
							"DynamicReportParamMstDATA->getDrillDownProcPreInParamList()",
							strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.getDrillDownProcPreInParamList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException(
					"dynamicreports",
					"DynamicReportParamMstDATA->getDrillDownProcPreInParamList()",
					strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportParamMstDATA.getReportList(vo) --> "
						+ e1.getMessage();
				eObj = new HisException(
						"dynamicreports",
						"DynamicReportParamMstDATA->getDrillDownProcPreInParamList()",
						strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * Gets the merge proc pre in param list.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the merge proc pre in param list
	 */
	public static void getMergeProcPreInParamList(
			DynamicReportParamMstFB formBean, HttpServletRequest request,
			HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strProcParamDetails = "";

		String strPreProcInParam = "";

		HisUtil util = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			util = new HisUtil("dynamicreports", "DynamicReportParamMstDATA");

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			bo.getMergeProcInParamList(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				if (vo.getWsPreInParams() != null
						|| vo.getWsPreInParams().size() > 0) {
					strPreProcInParam = util.getOptionValue(
							vo.getWsPreInParams(), "0", "0^Select Value", true);
				} else {
					strPreProcInParam = "<option value='0'>Select Value</option>";

				}

				strProcParamDetails = DynamicReportParamMstHLP
						.getMergePreProcParameterList(vo.getWsProcInParam(),
								strPreProcInParam);

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strProcParamDetails);

				} catch (Exception e) {
					strmsgText = "DynamicReportParamMstDATA.getMergeProcPreInParamList(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException(
							"dynamicreports",
							"DynamicReportParamMstDATA->getMergeProcPreInParamList()",
							strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.getMergeProcPreInParamList(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->getMergeProcPreInParamList()",
					strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportParamMstDATA.getMergeProcPreInParamList(vo) --> "
						+ e1.getMessage();
				eObj = new HisException(
						"dynamicreports",
						"DynamicReportParamMstDATA->getMergeProcPreInParamList()",
						strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * Gets the pre report dtls.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the pre report dtls
	 */
	public static void getPreReportDtls(DynamicReportParamMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strPreRptDtls = "";

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			bo.getPreRptDtls(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				strPreRptDtls = DynamicReportParamMstHLP.getPreRptDtlsView(vo
						.getWsPreRptDtls());

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strPreRptDtls);

				} catch (Exception e) {
					strmsgText = "DynamicReportParamMstDATA.getPreReportDtls(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException("dynamicreports",
							"DynamicReportParamMstDATA->getPreReportDtls()",
							strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");

				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.getPreReportDtls(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->getPreReportDtls()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportParamMstDATA.getPreReportDtls(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportParamMstDATA->getPreReportDtls()",
						strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * Row based report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void rowBasedReport(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			String[] strReportDtls = formBean.getStrReportTemplateId()
					.replace("^", "#").split("#");

			formBean.setStrReportDisplayName(strReportDtls[1]);
			formBean.setStrReportWidth(strReportDtls[2]);
			formBean.setStrReportHeaderTypeId(strReportDtls[3]);
			formBean.setStrReportHeaderTypeName(strReportDtls[4]);
			formBean.setStrReportHeaderParamReq(strReportDtls[5]);
			formBean.setStrReportBorderReq(strReportDtls[6]);

			formBean.setStrProcedureName(formBean.getStrProcedureName()
					.replace("^", "."));

			vo.setStrReportTemplateId(strReportDtls[0]);
			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());

			bo.getProcOutParamList(vo);

			formBean.setStrInParamValues(createComboValues(formBean
					.getStrProcInColumnDtls()));

			formBean.setStrProcParameterListView(DynamicReportParamMstHLP
					.getProcParameterListView(formBean)
					+ DynamicReportParamMstHLP.getProcColumnListView(vo
							.getWsProcOutParam()));

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {

			e.printStackTrace();

			strmsgText = "DynamicReportParamMstDATA.rowBasedReport(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->rowBasedReport()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Col based report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void colBasedReport(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			String[] strReportDtls = formBean.getStrReportTemplateId()
					.replace("^", "#").split("#");

			formBean.setStrReportDisplayName(strReportDtls[1]);
			formBean.setStrReportWidth(strReportDtls[2]);
			formBean.setStrReportHeaderTypeId(strReportDtls[3]);
			formBean.setStrReportHeaderTypeName(strReportDtls[4]);
			formBean.setStrReportHeaderParamReq(strReportDtls[5]);
			formBean.setStrReportBorderReq(strReportDtls[6]);

			formBean.setStrProcedureName(formBean.getStrProcedureName()
					.replace("^", "."));

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());

			bo.getProcOutParamList(vo);

			formBean.setStrInParamValues(createComboValues(formBean
					.getStrProcInColumnDtls()));

			formBean.setStrProcParameterListView(DynamicReportParamMstHLP
					.getProcParameterListView(formBean)
					+ DynamicReportParamMstHLP.getColBasedProcColumnListView(vo
							.getWsProcOutParam()));

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {

			strmsgText = "DynamicReportParamMstDATA.colBasedReport(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->colBasedReport()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Drill down report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void drillDownReport(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			String[] strReportDtls = formBean.getStrReportTemplateId()
					.replace("^", "#").split("#");

			formBean.setStrReportDisplayName(strReportDtls[1]);
			formBean.setStrReportWidth(strReportDtls[2]);
			formBean.setStrReportHeaderTypeId(strReportDtls[3]);
			formBean.setStrReportHeaderTypeName(strReportDtls[4]);
			formBean.setStrReportHeaderParamReq(strReportDtls[5]);
			formBean.setStrReportBorderReq(strReportDtls[6]);

			formBean.setStrProcedureName(formBean.getStrProcedureName()
					.replace("^", "."));

			vo.setStrReportTemplateId(strReportDtls[0]);

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());

			bo.getProcOutParamList(vo);

			formBean.setStrInParamValues(createComboValues(formBean
					.getStrProcInColumnDtls()));
			formBean.setStrOutParamValues(createPreviousOutParamCombo(vo
					.getWsHeaderMappingParamValues()));

			if (formBean.getStrIsMergeIntermediate().equals("0")) {

				// formBean.setStrInParamValues(createComboValues(formBean.getStrProcInColumnDtls()
				// ));

				formBean.setStrProcParameterListView(DynamicReportParamMstHLP
						.getProcParameterListView(formBean)
						+ DynamicReportParamMstHLP
								.getDrillDownProcColumnListView(
										vo.getWsProcOutParam(),
										formBean.getStrIsLast()));

			} else {

				// formBean.setStrOutParamValues(createPreviousOutParamCombo(vo.getWsHeaderMappingParamValues()));

				formBean.setStrProcParameterListView(DynamicReportParamMstHLP
						.getDrillDownPreProcParameterListView(formBean)
						+ DynamicReportParamMstHLP
								.getDrillDownProcColumnListView(
										vo.getWsProcOutParam(),
										formBean.getStrIsLast()));

			}

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {

			strmsgText = "DynamicReportParamMstDATA.drillDownReport(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->drillDownReport()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Gets the proc in out param combo.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the proc in out param combo
	 */
	public static void getProcInOutParamCombo(DynamicReportParamMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strProcParamDetails = "";
		HisUtil util = null;
		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());
			vo.setStrParamTypeId(formBean.getStrParamTypeId());

			bo.getProcInOutParamList(vo);

			util = new HisUtil("dynamicreports", "DynamicReportParamMstDATA");

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				if (vo.getWsPreInParams() != null
						&& vo.getWsPreInParams().size() > 0) {

					strProcParamDetails = util.getOptionValue(
							vo.getWsPreInParams(), "0", "0^Select Value", true);

				} else {
					strProcParamDetails = "<option value='0'>Select Value</option>";
				}

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strProcParamDetails);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.getProcInOutParamCombo(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->getProcInOutParamCombo()",
					strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportParamMstDATA.getProcInOutParamCombo(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportParamMstDATA->getProcInOutParamCombo()",
						strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * Merge report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void mergeReport(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			String[] strReportDtls = formBean.getStrReportTemplateId()
					.replace("^", "#").split("#");

			formBean.setStrReportDisplayName(strReportDtls[1]);
			formBean.setStrReportWidth(strReportDtls[2]);
			formBean.setStrReportHeaderTypeId(strReportDtls[3]);
			formBean.setStrReportHeaderTypeName(strReportDtls[4]);
			formBean.setStrReportHeaderParamReq(strReportDtls[5]);
			formBean.setStrReportBorderReq(strReportDtls[6]);

			formBean.setStrProcedureName(formBean.getStrProcedureName()
					.replace("^", "."));

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());

			bo.getProcOutParamList(vo);

			if (formBean.getStrIsMergeIntermediate().equals("0")) {

				formBean.setStrInParamValues(createComboValues(formBean
						.getStrProcInColumnDtls()));

				if (formBean.getStrIsColumnBaseRpt().equals("0")) {

					formBean.setStrProcParameterListView(DynamicReportParamMstHLP
							.getProcParameterListView(formBean)
							+ DynamicReportParamMstHLP.getProcColumnListView(vo
									.getWsProcOutParam()));

				} else {

					formBean.setStrProcParameterListView(DynamicReportParamMstHLP
							.getProcParameterListView(formBean)
							+ DynamicReportParamMstHLP
									.getColBasedProcColumnListView(vo
											.getWsProcOutParam()));

				}

			} else {

				if (formBean.getStrIsColumnBaseRpt().equals("0")) {

					formBean.setStrProcParameterListView(DynamicReportParamMstHLP
							.getMergeProcParameterListView(formBean)
							+ DynamicReportParamMstHLP.getProcColumnListView(vo
									.getWsProcOutParam()));

				} else {

					formBean.setStrProcParameterListView(DynamicReportParamMstHLP
							.getMergeProcParameterListView(formBean)
							+ DynamicReportParamMstHLP
									.getColBasedProcColumnListView(vo
											.getWsProcOutParam()));

				}

			}
			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {

			// e.printStackTrace();

			strmsgText = "DynamicReportParamMstDATA.mergeReport(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->mergeReport()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Save row based rpt data.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void saveRowBasedRptData(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatId = request.getSession().getAttribute("SEATID")
					.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);

			// Report and procedure details
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[0]);
			vo.setStrReportName(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[1]);
			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrIsCombo(formBean.getStrIsCombo());

			// In parameters
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInDisplayName(formBean.getStrInDisplayName());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());
			vo.setStrParamCompType(formBean.getStrParamCompType());
			vo.setStrComboQuery(formBean.getStrComboQuery());

			// Out Parameters
			vo.setStrColumnRequired(formBean.getStrColumnRequired());
			vo.setStrOutColumnName(formBean.getStrOutColumnName());
			vo.setStrOutColumnDisplayName(formBean.getStrOutColumnDisplayName());
			vo.setStrOutColumnActualIndex(formBean.getStrOutColumnActualIndex());
			vo.setStrOutColumnIndex(formBean.getStrOutColumnIndex());
			vo.setStrOutColumnWidth(formBean.getStrOutColumnWidth());

			vo.setStrTotalRequiredValue(formBean.getStrTotalRequiredValue());
			vo.setStrGroupByRequiredValue(formBean.getStrGroupByRequiredValue());
			vo.setStrOrderByRequiredValue(formBean.getStrOrderByRequiredValue());

			vo.setStrOutColumnAlign(formBean.getStrOutColumnAlign());

			vo.setStrReportHeaderParamId(formBean.getStrReportHeaderParamId());
			vo.setStrReportHeaderParamReq(formBean.getStrReportHeaderParamReq());
			vo.setStrReportHeaderParamType(formBean
					.getStrReportHeaderParamType());
			vo.setStrReportHeaderTypeId(formBean.getStrReportHeaderTypeId());

			bo.saveRowBasedRptData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "DynamicReportParamMstDATA.saveRowBasedRptData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->saveRowBasedRptData()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Save col based rpt data.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void saveColBasedRptData(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatId = request.getSession().getAttribute("SEATID")
					.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);

			// Report and procedure details
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[0]);
			vo.setStrReportName(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[1]);
			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrIsCombo(formBean.getStrIsCombo());

			// In parameters
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInDisplayName(formBean.getStrInDisplayName());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());
			vo.setStrParamCompType(formBean.getStrParamCompType());
			vo.setStrComboQuery(formBean.getStrComboQuery());

			// Out Parameters
			vo.setStrOutColumnName(formBean.getStrOutColumnName());
			vo.setStrOutColumnDisplayName(formBean.getStrOutColumnDisplayName());
			vo.setStrOutColumnActualIndex(formBean.getStrOutColumnActualIndex());
			vo.setStrOutColumnIndex(formBean.getStrOutColumnActualIndex());

			vo.setStrOutColumnAlign(formBean.getStrOutColumnAlign());

			// Column Parameters with Formula
			vo.setStrColumnDisplayName(formBean.getStrColumnDisplayName());
			vo.setStrColumnPrefix(formBean.getStrColumnPrefix());
			vo.setStrColumnFormula(formBean.getStrColumnFormula());
			vo.setStrColumnSuffix(formBean.getStrColumnSuffix());

			vo.setStrReportHeaderParamId(formBean.getStrReportHeaderParamId());
			vo.setStrReportHeaderParamReq(formBean.getStrReportHeaderParamReq());
			vo.setStrReportHeaderParamType(formBean
					.getStrReportHeaderParamType());
			vo.setStrReportHeaderTypeId(formBean.getStrReportHeaderTypeId());

			bo.saveColBasedRptData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "DynamicReportParamMstDATA.saveColBasedRptData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->saveColBasedRptData()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Save drill down rpt data.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void saveDrillDownRptData(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatId = request.getSession().getAttribute("SEATID")
					.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);

			// Report and procedure details
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[0]);
			vo.setStrReportName(formBean.getStrReportLevelDisplayName());
			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrIsCombo(formBean.getStrIsCombo());
			vo.setStrIsLast(formBean.getStrIsLast());
			// In parameters
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInDisplayName(formBean.getStrInDisplayName());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());
			vo.setStrComboQuery(formBean.getStrComboQuery());
			vo.setStrParamCompType(formBean.getStrParamCompType());
			vo.setStrInParamType(formBean.getStrInParamType());
			vo.setStrPreInParamValues(formBean.getStrPreInParamValues());

			// Out Parameters
			vo.setStrColumnRequired(formBean.getStrColumnRequired());
			vo.setStrOutColumnName(formBean.getStrOutColumnName());
			vo.setStrOutColumnDisplayName(formBean.getStrOutColumnDisplayName());
			vo.setStrOutColumnActualIndex(formBean.getStrOutColumnActualIndex());
			vo.setStrOutColumnIndex(formBean.getStrOutColumnIndex());
			vo.setStrOutColumnWidth(formBean.getStrOutColumnWidth());

			vo.setStrOutColumnAlign(formBean.getStrOutColumnAlign());

			vo.setStrTotalRequiredValue(formBean.getStrTotalRequiredValue());
			vo.setStrGroupByRequiredValue(formBean.getStrGroupByRequiredValue());
			vo.setStrOrderByRequiredValue(formBean.getStrOrderByRequiredValue());
			vo.setStrHyperlinkRequiredValue(formBean
					.getStrHyperlinkRequiredValue());
			vo.setStrHyperlinkMappingValue(formBean
					.getStrHyperlinkMappingValue());

			vo.setStrReportHeaderParamId(formBean.getStrReportHeaderParamId());
			vo.setStrReportHeaderParamReq(formBean.getStrReportHeaderParamReq());
			vo.setStrReportHeaderParamType(formBean
					.getStrReportHeaderParamType());
			vo.setStrReportHeaderTypeId(formBean.getStrReportHeaderTypeId());
			vo.setStrReportTypeId(vo.getStrReportTypeId());

			bo.saveDrillDownRptData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "DynamicReportParamMstDATA.saveDrillDownRptData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->saveDrillDownRptData()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Save merge rpt data.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void saveMergeRptData(DynamicReportParamMstFB formBean,
			HttpServletRequest request) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatId = request.getSession().getAttribute("SEATID")
					.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);

			// Report and procedure details
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[0]);

			vo.setStrReportLevelDisplayName(formBean
					.getStrReportLevelDisplayName());

			vo.setStrReportName(formBean.getStrReportLevelDisplayName());

			vo.setStrProcedureName(formBean.getStrProcedureName());
			vo.setStrIsCombo(formBean.getStrIsCombo());
			vo.setStrIsLast(formBean.getStrIsLast());

			// In parameters
			vo.setStrProcInColumnDtls(formBean.getStrProcInColumnDtls());
			vo.setStrInDisplayName(formBean.getStrInDisplayName());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());
			vo.setStrParamCompType(formBean.getStrParamCompType());
			vo.setStrComboQuery(formBean.getStrComboQuery());
			vo.setStrPreInParamValues(formBean.getStrPreInParamValues());

			vo.setStrOutColumnAlign(formBean.getStrOutColumnAlign());

			if (formBean.getStrIsColumnBaseRpt().equals("0")) {

				// Out Parameters
				vo.setStrColumnRequired(formBean.getStrColumnRequired());
				vo.setStrOutColumnName(formBean.getStrOutColumnName());
				vo.setStrOutColumnDisplayName(formBean
						.getStrOutColumnDisplayName());
				vo.setStrOutColumnActualIndex(formBean
						.getStrOutColumnActualIndex());
				vo.setStrOutColumnIndex(formBean.getStrOutColumnIndex());
				vo.setStrOutColumnWidth(formBean.getStrOutColumnWidth());

				vo.setStrTotalRequiredValue(formBean.getStrTotalRequiredValue());
				vo.setStrGroupByRequiredValue(formBean
						.getStrGroupByRequiredValue());
				vo.setStrOrderByRequiredValue(formBean
						.getStrOrderByRequiredValue());

			} else {

				// Out Parameters
				vo.setStrOutColumnName(formBean.getStrOutColumnName());
				vo.setStrOutColumnDisplayName(formBean
						.getStrOutColumnDisplayName());
				vo.setStrOutColumnActualIndex(formBean
						.getStrOutColumnActualIndex());
				vo.setStrOutColumnIndex(formBean.getStrOutColumnActualIndex());

				// Column Parameters with Formula
				vo.setStrColumnDisplayName(formBean.getStrColumnDisplayName());
				vo.setStrColumnPrefix(formBean.getStrColumnPrefix());
				vo.setStrColumnFormula(formBean.getStrColumnFormula());
				vo.setStrColumnSuffix(formBean.getStrColumnSuffix());

			}

			vo.setStrReportHeaderParamId(formBean.getStrReportHeaderParamId());
			vo.setStrReportHeaderParamReq(formBean.getStrReportHeaderParamReq());
			vo.setStrReportHeaderParamType(formBean
					.getStrReportHeaderParamType());
			vo.setStrReportHeaderTypeId(formBean.getStrReportHeaderTypeId());

			vo.setStrIsColumnBaseRpt(formBean.getStrIsColumnBaseRpt());

			bo.saveMergeRptData(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "DynamicReportParamMstDATA.saveMergeRptData(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->saveMergeRptData()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}

	}

	/**
	 * Cancel report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void cancelReport(DynamicReportParamMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatId = request.getSession().getAttribute("SEATID")
					.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);

			// Report and procedure details
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());
			vo.setStrReportProcId(formBean.getStrReportProcId());

			bo.cancelReport(vo);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print("");

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.cancelReport(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->cancelReport()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * View report.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public static void viewReport(DynamicReportParamMstFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportParamMstBO bo = null;
		DynamicReportParamMstVO vo = null;

		String strmsgText = "";

		String strViewContent = "<table border='0' width='700px' align='center' cellpadding='1px' cellspacing='1px'><tr class='HEADER'> <td width='95%' >"
				+ formBean.getStrReportDisplayName()
				+ "</td><td width='5%'>&nbsp;&nbsp;&nbsp;&nbsp;<img src='../../hisglobal/images/stop.png' style='cursor: pointer;' onclick='hide_popup(\"viewContentPopupDivId\");' align='middle'></td></tr></table>";

		try {

			vo = new DynamicReportParamMstVO();
			bo = new DynamicReportParamMstBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatId = request.getSession().getAttribute("SEATID")
					.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatId);

			// Report and procedure details
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());
			vo.setStrReportProcId(formBean.getStrReportProcId());

			if (formBean.getStrReportTypeId().equals("0")) {

				bo.rowBasedView(vo);

				strViewContent = strViewContent
						+ DynamicReportParamMstHLP.getViewInParameter(vo
								.getWsInParamView())
						+ DynamicReportParamMstHLP.getViewOutParamValues(vo
								.getWsOutParamView());

			} else {

				bo.colBasedView(vo);

				strViewContent = strViewContent
						+ DynamicReportParamMstHLP.getViewInParameter(vo
								.getWsInParamView())
						+
						// DynamicReportParamMstHLP.getColBasedProcColumnListView(vo.getWsProcOutParam()
						// , "0")+
						DynamicReportParamMstHLP.getViewColParamValues(vo
								.getWsColParamView());

			}

			strViewContent = strViewContent
					+ "<table width='700px' align='center' border='0' cellpadding='1px' cellspacing='1px'><tr class='FOOTER'><td ></td></tr></table>";

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strViewContent);

		} catch (Exception e) {

			e.printStackTrace();
			strmsgText = "DynamicReportParamMstDATA.viewReport(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportParamMstDATA->viewReport()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			eObj = null;
		} finally {
			bo = null;
			vo = null;

		}
	}

	/**
	 * Creates the combo values.
	 * 
	 * @param displays
	 *            the displays
	 * @return the string
	 */
	private static String createComboValues(String[] displays) {

		StringBuffer strComboContent = new StringBuffer();

		strComboContent.append("<option value='0' >Select Value</option>");

		if (displays != null && displays.length > 0) {

			int values = 10;

			for (int i = 0; i < displays.length; i++) {

				strComboContent.append("<option value='" + values + "' >")
						.append(displays[i]).append("</option>");
				values++;
			}

		}

		return strComboContent.toString();
	}

	/**
	 * Creates the previous out param combo.
	 * 
	 * @param preOutParamWs
	 *            the pre out param ws
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	private static String createPreviousOutParamCombo(WebRowSet preOutParamWs)
			throws Exception {

		StringBuffer strComboContent = new StringBuffer();

		strComboContent.append("<option value='0' >Select Value</option>");
		try {
			if (preOutParamWs != null && preOutParamWs.size() > 0) {

				while (preOutParamWs.next()) {

					strComboContent
							.append("<option value='"
									+ preOutParamWs.getString(1) + "' >")
							.append(preOutParamWs.getString(2))
							.append("</option>");

				}

			}

		} catch (Exception e) {
			throw e;
		}

		return strComboContent.toString();
	}

}
