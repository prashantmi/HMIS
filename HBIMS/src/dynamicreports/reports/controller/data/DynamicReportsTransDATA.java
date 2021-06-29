/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransDATA.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import org.apache.commons.lang.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dynamicreports.HtmlToPdfConvertor;
import dynamicreports.reports.bo.DynamicReportsTransBO;
import dynamicreports.reports.controller.fb.DynamicReportsTransFB;
import dynamicreports.reports.controller.hlp.DynamicReportsTransHLP;
import dynamicreports.reports.dao.DynamicReportsTransDAO;
import dynamicreports.reports.vo.DynamicReportsTransVO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransDATA.
 */
public class DynamicReportsTransDATA {

	/**
	 * Inits the.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 */
	public static void init(DynamicReportsTransFB formBean,
			HttpServletRequest request) {

		DynamicReportsTransBO bo = null;
		DynamicReportsTransVO vo = null;

		HisUtil util = null;

		String strmsgText = "";

		try {

			vo = new DynamicReportsTransVO();
			bo = new DynamicReportsTransBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			vo.setStrHospitalCode(hosCode);

			bo.init(vo);

			util = new HisUtil("dynamicreports", "DynamicReportsTransDATA");

			if (vo.getWsReportTemplateValues() != null
					&& vo.getWsReportTemplateValues().size() > 0) {

				formBean.setStrReportTemplateValues(util.getOptionValue(
						vo.getWsReportTemplateValues(),
						formBean.getStrReportTemplateId(), "0^Select Value",
						true, false));

			} else {
				formBean.setStrReportTemplateValues("<option value='0'>Select Value</option>");
			}

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

		} catch (Exception e) {
			strmsgText = "DynamicReportsTransDATA.init(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->init()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

			eObj = null;
		} finally {
			bo = null;
			vo = null;
			util = null;
		}

	}

	/*
	 * public static void getReportList(DynamicReportsTransFB formBean,
	 * HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * DynamicReportsTransBO bo = null; DynamicReportsTransVO vo = null;
	 * 
	 * HisUtil util = null;
	 * 
	 * String strReportList = "";
	 * 
	 * String strmsgText = "";
	 * 
	 * try {
	 * 
	 * vo = new DynamicReportsTransVO(); bo = new DynamicReportsTransBO();
	 * 
	 * String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
	 * .toString();
	 * 
	 * vo.setStrHospitalCode(hosCode);
	 * 
	 * vo.setStrReportTypeId(formBean.getStrReportTypeId());
	 * 
	 * bo.getReportList(vo);
	 * 
	 * util = new HisUtil("dynamicreports", "DynamicReportsTransDATA");
	 * 
	 * if (vo.getStrMsgType().equals("1")) { throw new
	 * Exception(vo.getStrMsgString()); }
	 * 
	 * else { if (vo.getWsReportTemplateValues() != null ||
	 * vo.getWsReportTemplateValues().size() > 0) { strReportList =
	 * util.getOptionValue( vo.getWsReportTemplateValues(), "0",
	 * "0^Select Value", true); } else { strReportList =
	 * "<option value='0'>Select Value</option>";
	 * 
	 * }
	 * 
	 * try { response.setHeader("Cache-Control", "no-cache");
	 * response.getWriter().print(strReportList);
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 * 
	 * } catch (Exception e) {
	 * 
	 * // e.printStackTrace(); strmsgText =
	 * "DynamicReportsTransDATA.getReportList(vo) --> " + e.getMessage();
	 * HisException eObj = new HisException("mms",
	 * "DynamicReportsTransDATA->getReportList()", strmsgText); try {
	 * response.setHeader("Cache-Control", "no-cache");
	 * response.getWriter().print( "ERROR#### Application Error [ERROR ID : " +
	 * eObj.getErrorID() + "],Contact System Administrator! ");
	 * 
	 * } catch (Exception e1) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } eObj = null; } finally { bo = null; vo = null;
	 * util = null; } }
	 */
	/**
	 * Gets the proc and in out param dtls.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the proc and in out param dtls
	 */
	public static void getProcAndInOutParamDtls(DynamicReportsTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportsTransBO bo = null;
		DynamicReportsTransVO vo = null;

		String strReportList = "";

		String strmsgText = "";

		try {

			vo = new DynamicReportsTransVO();
			bo = new DynamicReportsTransBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid=request.getSession().getAttribute("SEATID")
			.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);

			vo.setStrReportTypeId(formBean.getStrReportTypeId());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			bo.getProcAndInOutParamDtls(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				if (formBean.getStrReportTypeId().equals("2")) {

					strReportList = DynamicReportsTransHLP
							.getReportProcHiddenValues(vo
									.getWsReportProcValues())
							+ DynamicReportsTransHLP.getTemplateInParamDetails(
									vo.getWsReportInParamValues(),
									vo.getStrHospitalCode(),vo.getStrSeatId())
							+ DynamicReportsTransHLP
									.getTemplateOutParamDetails(vo
											.getWsReportOutParamValues())
							+ DynamicReportsTransHLP
									.getTemplateColumnParamDetails(vo
											.getWsReportColParamValues());

				} else {

					strReportList = DynamicReportsTransHLP
							.getReportProcHiddenValues(vo
									.getWsReportProcValues())
							+ DynamicReportsTransHLP.getTemplateInParamDetails(
									vo.getWsReportInParamValues(),
									vo.getStrHospitalCode(),vo.getStrSeatId())
							+ DynamicReportsTransHLP
									.getTemplateOutParamDetails(vo
											.getWsReportOutParamValues());

				}

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strReportList);

				} catch (Exception e) {
					strmsgText = "DynamicReportsTransDATA.getProcAndInOutParamDtls(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException(
							"dynamicreports",
							"DynamicReportsTransDATA->getProcAndInOutParamDtls()",
							strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");
				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportsTransDATA.getProcAndInOutParamDtls(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->getProcAndInOutParamDtls()",
					strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportsTransDATA.getProcAndInOutParamDtls(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportsTransDATA->getProcAndInOutParamDtls()",
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

	
	public static void getProcAndInOutParamDtls_BS(DynamicReportsTransFB formBean,
			HttpServletRequest request, HttpServletResponse response) {

		DynamicReportsTransBO bo = null;
		DynamicReportsTransVO vo = null;

		String strReportList = "";

		String strmsgText = "";

		try {

			vo = new DynamicReportsTransVO();
			bo = new DynamicReportsTransBO();

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();
			String seatid=request.getSession().getAttribute("SEATID")
			.toString();

			vo.setStrHospitalCode(hosCode);
			vo.setStrSeatId(seatid);

			vo.setStrReportTypeId(formBean.getStrReportTypeId());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			bo.getProcAndInOutParamDtls(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			else {

				if (formBean.getStrReportTypeId().equals("2")) {

					strReportList = DynamicReportsTransHLP
							.getReportProcHiddenValues(vo
									.getWsReportProcValues())
							+ DynamicReportsTransHLP.getTemplateInParamDetails_BS(
									vo.getWsReportInParamValues(),
									vo.getStrHospitalCode(),vo.getStrSeatId())
							+ DynamicReportsTransHLP
									.getTemplateOutParamDetails(vo
											.getWsReportOutParamValues())
							+ DynamicReportsTransHLP
									.getTemplateColumnParamDetails(vo
											.getWsReportColParamValues());

				} else {

					strReportList = DynamicReportsTransHLP
							.getReportProcHiddenValues(vo
									.getWsReportProcValues())
							+ DynamicReportsTransHLP.getTemplateInParamDetails_BS(
									vo.getWsReportInParamValues(),
									vo.getStrHospitalCode(),vo.getStrSeatId())
							+ DynamicReportsTransHLP
									.getTemplateOutParamDetails(vo
											.getWsReportOutParamValues());

				}

				try {
					response.setHeader("Cache-Control", "no-cache");
					response.getWriter().print(strReportList);

				} catch (Exception e) {
					strmsgText = "DynamicReportsTransDATA.getProcAndInOutParamDtls(vo) --> "
							+ e.getMessage();
					HisException eObj = new HisException(
							"dynamicreports",
							"DynamicReportsTransDATA->getProcAndInOutParamDtls()",
							strmsgText);
					formBean.setStrErrMsg("Application Error [ERROR ID : "
							+ eObj.getErrorID()
							+ "],Contact System Administrator! ");
				}
			}

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportsTransDATA.getProcAndInOutParamDtls(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->getProcAndInOutParamDtls()",
					strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportsTransDATA.getProcAndInOutParamDtls(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportsTransDATA->getProcAndInOutParamDtls()",
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
	 * Gets the combo dtls.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param formBean
	 *            the form bean
	 * @return the combo dtls
	 */
	public static void getComboDtls(HttpServletRequest request,
			HttpServletResponse response, DynamicReportsTransFB formBean) {

		String strComboDtls = "";

		String strmsgText = "";

		try {

			String hosCode = request.getSession().getAttribute("HOSPITAL_CODE")
					.toString();

			// vo.setStrReportTypeId(formBean.getStrReportTypeId());

			String strParamIds = formBean.getStrParamId();
			String strComboValues = formBean.getStrComboValues();
			String strCurrentComboId = formBean.getStrCtCombo();

			strComboDtls = DynamicReportsTransHLP.getComboDetails(
					formBean.getStrReportTemplateId(), strParamIds,
					strComboValues, strCurrentComboId, hosCode);

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strComboDtls);

		} catch (Exception e) {

			// e.printStackTrace();
			strmsgText = "DynamicReportsTransDATA.getComboDtls(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->getComboDtls()", strmsgText);
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().print(
						"ERROR#### Application Error [ERROR ID : "
								+ eObj.getErrorID()
								+ "],Contact System Administrator! ");

			} catch (Exception e1) {
				strmsgText = "DynamicReportsTransDATA.getComboDtls(vo) --> "
						+ e1.getMessage();
				eObj = new HisException("dynamicreports",
						"DynamicReportsTransDATA->getComboDtls()", strmsgText);
				formBean.setStrErrMsg("Application Error [ERROR ID : "
						+ eObj.getErrorID()
						+ "],Contact System Administrator! ");
			}
			eObj = null;
		}
	}

	/**
	 * Generate records.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param formBean
	 *            the form bean
	 */
	public static void generateRecords(HttpServletRequest request,
			HttpServletResponse response, DynamicReportsTransFB formBean) {

		String strmsgText = "";

		String strProcMid = "";
		String strProcStart = "";
		String strProcCall = "";

		DynamicReportsTransBO bo = null;
		DynamicReportsTransVO vo = null;

		Map<String, String> firstParamValuesMap = new HashMap<String, String>();

		try {

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			vo = new DynamicReportsTransVO();
			bo = new DynamicReportsTransBO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrReportTypeId(formBean.getStrReportTypeId());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId());

			strProcStart = "{ call "

			+ formBean.getStrTemplateProcName()[formBean.getnCurrentLevel()]
					+ "( ";

			for (int i = 0, stopI = formBean.getStrInParamName().length + 1; i <= stopI; i++) {

				if (i < formBean.getStrInParamName().length
						&& formBean.getStrInValueDetails()[i] != null
						&& !formBean.getStrInValueDetails()[i].equals("0"))
					firstParamValuesMap.put(formBean.getStrInValueDetails()[i]
							.replace("^", "#").split("#")[1], formBean
							.getStrInConstantValue()[i]);

				if (i == 0) {
					strProcMid = strProcMid + "? ";
				} else {
					strProcMid = strProcMid + " , ? ";
				}

			}

			String strTemplateName = formBean.getStrReportDisplayName();

			strTemplateName = strTemplateName.replace(" ", "_");

			strProcCall = strProcStart + strProcMid + ") }";

			vo.setStrCallProcedure(strProcCall);

			vo.setStrInParamName(formBean.getStrInParamName());
			vo.setStrInConstantValue(formBean.getStrInConstantValue());
			vo.setStrOrderString(formBean.getStrOrderString().replaceAll("#",
					","));

			vo.setStrReportHeaderParamId(formBean.getStrReportHeaderParamId()[formBean
					.getnCurrentLevel()]);
			vo.setStrReportHeaderTypeId(formBean.getStrReportHeaderTypeId()[formBean
					.getnCurrentLevel()]);
			vo.setStrReportHeaderParamReq(formBean.getStrReportHeaderParamReq()[formBean
					.getnCurrentLevel()]);
			vo.setStrReportHeaderParamType(formBean
					.getStrReportHeaderParamType()[formBean.getnCurrentLevel()]);

			if (vo.getStrReportHeaderParamReq() != null
					&& vo.getStrReportHeaderParamReq().equals("1")) {

				vo.setStrReportHeaderParamValue(firstParamValuesMap.get(vo
						.getStrReportHeaderParamId()));

			}

			bo.generateRecords(vo);

			if (vo.getStrMsgType().equals("1")) {
				throw new Exception(vo.getStrMsgString());
			}

			formBean.setStrReportHeaderHtmlContent(vo
					.getStrReportHeaderHtmlContent());
			formBean.setStrHeader(vo.getStrHeader());

			String strExportContents = "";

			if (formBean.getStrExportType().equalsIgnoreCase("html")) {

				if (formBean.getStrReportTypeId().equals("2")) {

					strExportContents = DynamicReportsTransHLP
							.exportColBasedHTML(formBean,
									vo.getWsGeneratedDatas(), "1", "1");

				} else {

					strExportContents = DynamicReportsTransHLP
							.exportRowBasedHTML(formBean,
									vo.getWsGeneratedDatas(), "1", "1");
				}

				// response.setHeader("Content-Disposition", "filename="+
				// strTemplateName + ".html");

				strExportContents = "<html><body>" + strExportContents
						+ "</body></html>";

				formBean.setStrReportContents(strExportContents);

				// response.getOutputStream().write(strExportContents.getBytes());

			} else if (formBean.getStrExportType().equalsIgnoreCase("pdf")) {

				if (formBean.getStrReportTypeId().equals("2")) {

					strExportContents = DynamicReportsTransHLP
							.exportColBasedHTML(formBean,
									vo.getWsGeneratedDatas(), "1", "1");

				} else {

					strExportContents = DynamicReportsTransHLP
							.exportRowBasedHTML(formBean,
									vo.getWsGeneratedDatas(), "1", "1");
				}

				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + strTemplateName + ".pdf");

				strExportContents = "<html><body>"
						+ strExportContents.replaceAll("&", "and")
						+ "</body></html>";

				HtmlToPdfConvertor.convertHtmlToPDFDirect(response,
						strExportContents, strTemplateName, strProcCall);

			} else {

				HSSFWorkbook workBook = null;

				if (formBean.getStrReportTypeId().equals("2")) {

					workBook = createColBaseXLS(formBean,
							vo.getWsGeneratedDatas());

				} else {

					workBook = createRowBaseXLS(formBean,
							vo.getWsGeneratedDatas());

				}

				response.setContentType("application/xls");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + strTemplateName + ".xls");

				workBook.write(response.getOutputStream());

			}

		} catch (Exception e) {

			strmsgText = "DynamicReportsTransDATA.generateRecords(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->generateRecords()", strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

		}

	}

	/**
	 * Generate merge records.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param formBean
	 *            the form bean
	 */
	public static void generateMergeRecords(HttpServletRequest request,
			HttpServletResponse response, DynamicReportsTransFB formBean) {

		String strProcMid = "";
		String strProcStart = "";
		String strProcCall = "";

		DynamicReportsTransBO bo = null;
		DynamicReportsTransVO vo = null;

		Map<String, String> firstParamValuesMap = new HashMap<String, String>();

		try {

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			vo = new DynamicReportsTransVO();
			bo = new DynamicReportsTransBO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrReportTypeId(formBean.getStrReportTypeId());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[0]);

			String strExportContents = "";

			String isHeaderFooterReq = "0";
			String isInParamReq = "0";

			String strTemplateName = formBean.getStrReportDisplayName();

			strTemplateName = strTemplateName.replace(" ", "_");

			if (formBean.getStrTemplateProcName() != null) {

				for (int j = 0; j < formBean.getStrTemplateProcName().length; j++) {

					if (j == 0) {

						strProcStart = "{ call "

						+ formBean.getStrTemplateProcName()[j] + "( ";

						for (int i = 0, stopI = formBean.getStrInParamName().length + 1; i <= stopI; i++) {

							if (i < formBean.getStrInParamName().length
									&& formBean.getStrInValueDetails()[i] != null
									&& !formBean.getStrInValueDetails()[i]
											.equals("0"))
								firstParamValuesMap
										.put(formBean.getStrInValueDetails()[i]
												.replace("^", "#").split("#")[1],
												formBean.getStrInConstantValue()[i]);

							if (i == 0) {
								strProcMid = strProcMid + "? ";
							} else {
								strProcMid = strProcMid + " , ? ";
							}

						}

						strProcCall = strProcStart + strProcMid + ") }";

						vo.setStrCallProcedure(strProcCall);

						vo.setStrInParamName(formBean.getStrInParamName());
						vo.setStrInConstantValue(formBean
								.getStrInConstantValue());
						vo.setStrOrderString(formBean.getStrOrderString()
								.replaceAll("#", ","));

						vo.setStrReportHeaderParamId(formBean
								.getStrReportHeaderParamId()[formBean
								.getnCurrentLevel()]);
						vo.setStrReportHeaderTypeId(formBean
								.getStrReportHeaderTypeId()[formBean
								.getnCurrentLevel()]);
						vo.setStrReportHeaderParamReq(formBean
								.getStrReportHeaderParamReq()[formBean
								.getnCurrentLevel()]);
						vo.setStrReportHeaderParamType(formBean
								.getStrReportHeaderParamType()[formBean
								.getnCurrentLevel()]);

						if (vo.getStrReportHeaderParamReq() != null
								&& vo.getStrReportHeaderParamReq().equals("1")) {

							vo.setStrReportHeaderParamValue(firstParamValuesMap
									.get(vo.getStrReportHeaderParamId()));

						}

						bo.generateRecords(vo);

						if (vo.getStrMsgType().equals("1")) {
							throw new Exception(vo.getStrMsgString());
						}

						formBean.setStrReportHeaderHtmlContent(vo
								.getStrReportHeaderHtmlContent());
						formBean.setStrHeader(vo.getStrHeader());

						isHeaderFooterReq = "1";
						isInParamReq = "1";

					} else {

						isHeaderFooterReq = "0";
						isInParamReq = "0";

						vo.setStrReportIsColBased(formBean
								.getStrTemplateProcIsColBase()[j]);
						vo.setStrProcId(formBean.getStrTemplateProcId()[j]);

						bo.getProcNextLevelParamDtls(vo);

						WebRowSet nextLevelOutParamWs = vo
								.getWsReportOutParamValues();

						if (nextLevelOutParamWs != null
								&& nextLevelOutParamWs.size() > 0) {

							String strOutCol[] = new String[nextLevelOutParamWs
									.size()];
							String isGrandTotal[] = new String[nextLevelOutParamWs
									.size()];
							String columnIndex[] = new String[nextLevelOutParamWs
									.size()];
							String actColumnIndex[] = new String[nextLevelOutParamWs
									.size()];
							String strOutColWidth[] = new String[nextLevelOutParamWs
									.size()];
							String strIsGroupBy[] = new String[nextLevelOutParamWs
									.size()];

							String strAlign[] = new String[nextLevelOutParamWs
									.size()];
							int count = 0;

							while (nextLevelOutParamWs.next()) {

								strOutCol[count] = nextLevelOutParamWs
										.getString(1);
								isGrandTotal[count] = nextLevelOutParamWs
										.getString(5);
								columnIndex[count] = nextLevelOutParamWs
										.getString(6);
								actColumnIndex[count] = nextLevelOutParamWs
										.getString(7);
								strOutColWidth[count] = nextLevelOutParamWs
										.getString(2);
								strIsGroupBy[count] = nextLevelOutParamWs
										.getString(3);

								strAlign[count] = nextLevelOutParamWs
										.getString(10);

								count++;

							}

							formBean.setStrOutColDisplayName(strOutCol);
							formBean.setStrOutColIsGrandTotal(isGrandTotal);
							formBean.setStrOutColIndex(columnIndex);
							formBean.setStrOutColActualIndex(actColumnIndex);
							formBean.setStrOutColWidth(strOutColWidth);
							formBean.setStrOutColIsGroupBy(strIsGroupBy);
							formBean.setStrOutColumnAlign(strAlign);

						}

						WebRowSet nextLevelColParamWs = vo
								.getWsReportColParamValues();

						if (nextLevelColParamWs != null
								&& nextLevelColParamWs.size() > 0) {

							String strColumnDisplayName[] = new String[nextLevelColParamWs
									.size()];
							String strColumnPrifix[] = new String[nextLevelColParamWs
									.size()];
							String strColumnFormula[] = new String[nextLevelColParamWs
									.size()];
							String strColumnSuffix[] = new String[nextLevelColParamWs
									.size()];

							String strAlign[] = new String[nextLevelColParamWs
									.size()];

							int count = 0;

							while (nextLevelColParamWs.next()) {

								strColumnDisplayName[count] = nextLevelColParamWs
										.getString(1);
								strColumnPrifix[count] = nextLevelColParamWs
										.getString(2);
								strColumnFormula[count] = nextLevelColParamWs
										.getString(3);
								strColumnSuffix[count] = nextLevelColParamWs
										.getString(4);

								strAlign[count] = nextLevelColParamWs
										.getString(5);

								count++;
							}

							formBean.setStrColumnDisplayName(strColumnDisplayName);
							formBean.setStrColumnPrifix(strColumnPrifix);
							formBean.setStrColumnFormula(strColumnFormula);
							formBean.setStrColumnSuffix(strColumnSuffix);
							formBean.setStrOutColumnAlign(strAlign);
						}

						strProcCall = "";
						strProcMid = "";

						strProcStart = "{ call "

						+ formBean.getStrTemplateProcName()[j] + "( ";

						WebRowSet nextLevelInParamWs = vo
								.getWsReportInParamValues();

						if (nextLevelInParamWs != null
								&& nextLevelInParamWs.size() > 0) {

							String strInParamName[] = new String[nextLevelInParamWs
									.size()];
							String strInConstantValue[] = new String[nextLevelInParamWs
									.size()];

							int count = 0;

							while (nextLevelInParamWs.next()) {

								if (firstParamValuesMap.get(nextLevelInParamWs
										.getString(7)) != null) {

									strInParamName[count] = nextLevelInParamWs
											.getString(4);
									strInConstantValue[count] = firstParamValuesMap
											.get(nextLevelInParamWs
													.getString(7));

								} else {

									strInParamName[count] = nextLevelInParamWs
											.getString(4);
									strInConstantValue[count] = nextLevelInParamWs
											.getString(2);

								}

								if (count == 0) {
									strProcMid = strProcMid + "? ";
								} else {
									strProcMid = strProcMid + " , ? ";
								}

								count++;

							}

							vo.setStrInParamName(strInParamName);
							vo.setStrInConstantValue(strInConstantValue);

							strProcMid = strProcMid + " , ? , ? ";

						}

						strProcCall = strProcStart + strProcMid + ") }";

						vo.setStrCallProcedure(strProcCall);

						bo.generateRecords(vo);

					}

					if (formBean.getStrTemplateProcIsColBase()[j].equals("0")) {

						strExportContents = strExportContents
								+ " </br> "
								+ DynamicReportsTransHLP.exportRowBasedHTML(
										formBean, vo.getWsGeneratedDatas(),
										isHeaderFooterReq, isInParamReq);

					} else {

						strExportContents = strExportContents
								+ " </br> "
								+ DynamicReportsTransHLP.exportColBasedHTML(
										formBean, vo.getWsGeneratedDatas(),
										isHeaderFooterReq, isInParamReq);

					}

				}

				if (formBean.getStrExportType().equalsIgnoreCase("html")) {

					response.setHeader("Content-Disposition", "filename="
							+ strTemplateName + ".html");

					// response.setHeader("Content-Disposition", "filename="+
					// strTemplateName + ".html");

					strExportContents = "<html><body>" + strExportContents
							+ "</body></html>";

					formBean.setStrReportContents(strExportContents);

				} else if (formBean.getStrExportType().equalsIgnoreCase("pdf")) {

					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + strTemplateName + ".pdf");

					strExportContents = "<html><body>"
							+ strExportContents.replaceAll("&", "and")
							+ "</body></html>";

					HtmlToPdfConvertor.convertHtmlToPDFDirect(response,
							strExportContents);

				}

			}

		} catch (Exception e) {

			String strmsgText = "DynamicReportsTransDATA.generateMergeRecords(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->generateMergeRecords()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

		}

	}

	/**
	 * Creates the row base xls.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param ws
	 *            the ws
	 * @return the HSSF workbook
	 * @throws Exception
	 *             the exception
	 */
	private static HSSFWorkbook createRowBaseXLS(
			DynamicReportsTransFB formBean, WebRowSet ws) throws Exception {

		String[] strHeader = formBean.getStrOutColDisplayName();
		String[] columnIndex = formBean.getStrOutColIndex();
		String[] actColumnIndex = formBean.getStrOutColActualIndex();
		String[] isGrandTotal = formBean.getStrOutColIsGrandTotal();
		String[] strInComboVal = formBean.getStrInParamComboVal();
		String[] strCompType = formBean.getStrInParamCompType();

		String[] strInParamNames = formBean.getStrInParamDisplayName();
		String[] strInParamValues = formBean.getStrInParamDisplayValue();

		HSSFWorkbook dataWorkbook = null;
		HSSFSheet dataSheet = null;

		int rowCount = 0;
		int comboCount = 0;

		double[] dTotal = new double[strHeader.length];

		try {

			dataWorkbook = new HSSFWorkbook();
			dataSheet = dataWorkbook.createSheet("Sheet 1");

			if (strInParamNames != null && strInParamNames.length > 0) {

				HSSFRow newRow = dataSheet.createRow((short) rowCount);
				rowCount = rowCount + 1;

				for (int i = 0; i < strInParamNames.length; i++) {

					if (!strCompType[i].trim().equals("2")) {

						newRow.createCell((short) i).setCellValue(
								strInParamNames[i] + " : "
										+ strInParamValues[i]);

					} else {

						newRow.createCell((short) i).setCellValue(
								strInParamNames[i] + " : "
										+ strInComboVal[comboCount]);

						comboCount++;

					}

				}

			}

			if (strHeader != null && strHeader.length > 0) {

				HSSFRow headerRow = dataSheet.createRow((short) rowCount);

				rowCount = rowCount + 1;

				for (int i = 0, stopI = strHeader.length; i < stopI; i++) {

					headerRow.createCell((short) i).setCellValue(
							strHeader[(Integer.parseInt(columnIndex[i]) - 1)]);

				}

			}

			if (ws != null && ws.size() > 0) {

				while (ws.next()) {

					HSSFRow newRow = dataSheet.createRow((short) rowCount);
					rowCount = rowCount + 1;

					for (int i = 0, stopI = strHeader.length; i < stopI; i++) {

						String strVal = ws.getString(Integer
								.parseInt(actColumnIndex[i]));

						if (NumberUtils.isNumber(strVal)) {

							HSSFCell cell = newRow.createCell((short) i);
							cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cell.setCellValue(strVal);//cell.setCellValue(Float.valueOf(strVal));

							dTotal[i] = dTotal[i] + Double.valueOf(strVal);

						} else {

							newRow.createCell((short) i).setCellValue(strVal);

							dTotal[i] = 0;

						}

					}

				}

			}

			if (isGrandTotal != null) {

				HSSFRow newRow = dataSheet.createRow((short) rowCount);

				for (int i = 0, stopI = isGrandTotal.length; i < stopI; i++) {

					if (Integer.valueOf(isGrandTotal[i]) == 1) {

						HSSFCell cell = newRow.createCell((short) i);
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(dTotal[i]);

					} else {

						newRow.createCell((short) i).setCellValue("");
					}

				}
			}
		} catch (Exception e) {
			throw e;
		}

		return dataWorkbook;
	}

	/**
	 * Gets the alpha from numbers.
	 * 
	 * @param number
	 *            the number
	 * @return the alpha from numbers
	 * @throws Exception
	 *             the exception
	 */
	private static String getAlphaFromNumbers(int number) throws Exception {
		String converted = "";

		if (number <= 0) {
			throw new Exception("Invalid Number");
		}

		number = number - 1;

		// Repeatedly divide the number by 26 and convert the
		// remainder into the appropriate letter.
		while (number >= 0) {
			int remainder = number % 26;
			converted = (char) (remainder + 'A') + converted;
			number = (number / 26) - 1;
		}

		return converted;
	}

	/**
	 * Creates the col base xls.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param ws
	 *            the ws
	 * @return the HSSF workbook
	 * @throws Exception
	 *             the exception
	 */
	private static HSSFWorkbook createColBaseXLS(
			DynamicReportsTransFB formBean, WebRowSet ws) throws Exception {

		String[] strInParamNames = formBean.getStrInParamDisplayName();
		String[] strInParamValues = formBean.getStrInParamDisplayValue();
		String[] strInComboVal = formBean.getStrInParamComboVal();
		String[] strCompType = formBean.getStrInParamCompType();

		String[] strOutCol = formBean.getStrOutColDisplayName();

		String[] strColumnDisplayName = formBean.getStrColumnDisplayName();
		String[] strColumnPrifix = formBean.getStrColumnPrifix();
		String[] strColumnFormula = formBean.getStrColumnFormula();
		String[] strColumnSuffix = formBean.getStrColumnSuffix();

		Map<String, String> map = new HashMap<String, String>();

		HSSFWorkbook dataWorkbook = null;
		HSSFSheet dataSheet = null;

		int rowCount = 0;
		int comboCount = 0;

		try {

			if (ws != null && ws.next()) {

				for (int k = 1; k <= strOutCol.length; k++) {

					map.put(getAlphaFromNumbers(k), ws.getString(k));

				}

			}

			dataWorkbook = new HSSFWorkbook();
			dataSheet = dataWorkbook.createSheet("Sheet 1");

			if (strInParamNames != null && strInParamNames.length > 0) {

				HSSFRow newRow = dataSheet.createRow((short) rowCount);
				rowCount = rowCount + 1;

				for (int i = 0; i < strInParamNames.length; i++) {

					if (!strCompType[i].trim().equals("2")) {

						newRow.createCell((short) i).setCellValue(
								strInParamNames[i] + " : "
										+ strInParamValues[i]);

					} else {

						newRow.createCell((short) i).setCellValue(
								strInParamNames[i] + " : "
										+ strInComboVal[comboCount]);

						comboCount++;

					}

				}

			}

			if (strColumnDisplayName != null)
				for (int j = 0; j < strColumnDisplayName.length; j++) {

					HSSFRow newRow = dataSheet.createRow((short) rowCount);
					rowCount = rowCount + 1;

					newRow.createCell((short) 0).setCellValue(
							strColumnDisplayName[j]);

					if (strColumnFormula[j].contains("#")) {

						Pattern p = Pattern.compile("[#][A-Z][#]");
						Matcher m = p.matcher(strColumnFormula[j]);

						while (strColumnFormula[j].charAt(strColumnFormula[j]
								.indexOf("#") + 2) == '#') {

							char indexChar = strColumnFormula[j]
									.charAt(strColumnFormula[j].indexOf("#") + 1);

							strColumnFormula[j] = m.replaceFirst(map.get(String
									.valueOf(indexChar)));

							m = p.matcher(strColumnFormula[j]);

						}

						String strContent = "";

						if (strColumnPrifix[j].trim().length() > 0
								&& !strColumnPrifix[j].equals("null")) {
							strContent = strContent + strColumnPrifix[j] + " ";
						}

						strContent = strContent
								+ DynamicReportsTransDAO
										.execFormula(strColumnFormula[j]) + " ";

						if (strColumnSuffix[j].trim().length() > 0
								&& !strColumnSuffix[j].equals("null")) {

							strContent = strContent + strColumnSuffix[j];

						}

						newRow.createCell((short) 1).setCellValue(strContent);

					}

				}

		} catch (Exception e) {
			throw e;
		}

		return dataWorkbook;
	}

	/**
	 * Generate drill down records.
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param formBean
	 *            the form bean
	 */
	public static void generateDrillDownRecords(HttpServletRequest request,
			HttpServletResponse response, DynamicReportsTransFB formBean) {

		String strProcMid = "";
		String strProcStart = "";
		String strProcCall = "";

		DynamicReportsTransBO bo = null;
		DynamicReportsTransVO vo = null;

		Map<String, String> firstParamValuesMap = new HashMap<String, String>();
		String strExportContents = "";
		try {

			formBean.setStrHospitalCode(request.getSession()
					.getAttribute("HOSPITAL_CODE").toString());
			formBean.setStrSeatId(request.getSession().getAttribute("SEATID")
					.toString());

			String strLevel = request.getParameter("strLevel").toString();

			String[] strParamDisplayName = null;
			String[] strParamDisplayValue = null;

			if (request.getParameter("strInParamDisplayNameObjVal") != null) {

				strParamDisplayName = request
						.getParameter("strInParamDisplayNameObjVal").toString()
						.replace("^", "#").split("#");
				strParamDisplayValue = request
						.getParameter("strInParamDisplayValueObjVal")
						.toString().replace("^", "#").split("#");
			}

			if (strLevel.trim().length() > 0) {

				int nCurrentLevel = Integer.parseInt(strLevel) + 1;

				formBean.setnCurrentLevel(nCurrentLevel);

			}

			vo = new DynamicReportsTransVO();
			bo = new DynamicReportsTransBO();

			vo.setStrHospitalCode(formBean.getStrHospitalCode());
			vo.setStrSeatId(formBean.getStrSeatId());
			vo.setStrReportTypeId(formBean.getStrReportTypeId());
			vo.setStrReportTemplateId(formBean.getStrReportTemplateId()
					.replace("^", "#").split("#")[0]);

			vo.setStrReportIsColBased("0");

			if (formBean.getStrTemplateProcName() != null) {

				vo.setStrProcId(formBean.getStrTemplateProcId()[0]);

				bo.getDrillDownProcDtls(vo);

				WebRowSet nextLevelProcWs = vo.getWsReportProcValues();

				if (nextLevelProcWs != null && nextLevelProcWs.size() > 0) {

					String strTemplateProcDisplayName[] = new String[nextLevelProcWs
							.size()];
					String strTemplateProcName[] = new String[nextLevelProcWs
							.size()];
					String strTemplateProcId[] = new String[nextLevelProcWs
							.size()];
					String strReportHeaderTypeId[] = new String[nextLevelProcWs
							.size()];

					String strReportHeaderParamReq[] = new String[nextLevelProcWs
							.size()];
					String strReportHeaderParamType[] = new String[nextLevelProcWs
							.size()];
					String strReportHeaderParamId[] = new String[nextLevelProcWs
							.size()];

					int count = 0;

					while (nextLevelProcWs.next()) {

						strTemplateProcDisplayName[count] = nextLevelProcWs
								.getString(1);
						strTemplateProcName[count] = nextLevelProcWs
								.getString(3);
						strTemplateProcId[count] = nextLevelProcWs.getString(6);

						strReportHeaderTypeId[count] = nextLevelProcWs
								.getString(7);
						strReportHeaderParamReq[count] = nextLevelProcWs
								.getString(8);
						strReportHeaderParamType[count] = nextLevelProcWs
								.getString(9);
						strReportHeaderParamId[count] = nextLevelProcWs
								.getString(10);

						count++;
					}

					formBean.setStrTemplateProcDisplayName(strTemplateProcDisplayName);
					formBean.setStrTemplateProcName(strTemplateProcName);
					formBean.setStrTemplateProcId(strTemplateProcId);

					formBean.setStrReportHeaderTypeId(strReportHeaderTypeId);
					formBean.setStrReportHeaderParamReq(strReportHeaderParamReq);
					formBean.setStrReportHeaderParamType(strReportHeaderParamType);
					formBean.setStrReportHeaderParamId(strReportHeaderParamId);

				}

				formBean.setStrReportDisplayName(formBean
						.getStrTemplateProcDisplayName()[formBean
						.getnCurrentLevel()]);

				vo.setStrProcId(formBean.getStrTemplateProcId()[formBean
						.getnCurrentLevel()]);

				bo.getProcNextLevelParamDtls(vo);

				WebRowSet nextLevelOutParamWs = vo.getWsReportOutParamValues();

				if (nextLevelOutParamWs != null
						&& nextLevelOutParamWs.size() > 0) {

					String strOutCol[] = new String[nextLevelOutParamWs.size()];
					String isGrandTotal[] = new String[nextLevelOutParamWs
							.size()];
					String columnIndex[] = new String[nextLevelOutParamWs
							.size()];
					String actColumnIndex[] = new String[nextLevelOutParamWs
							.size()];
					String strOutColWidth[] = new String[nextLevelOutParamWs
							.size()];
					String strIsGroupBy[] = new String[nextLevelOutParamWs
							.size()];
					String strIsHyperLink[] = new String[nextLevelOutParamWs
							.size()];
					String strIsHyperLinkValue[] = new String[nextLevelOutParamWs
							.size()];

					String strAlign[] = new String[nextLevelOutParamWs.size()];

					String strOrderString = "1";

					int count = 0;

					while (nextLevelOutParamWs.next()) {

						strOutCol[count] = nextLevelOutParamWs.getString(1);
						isGrandTotal[count] = nextLevelOutParamWs.getString(5);
						columnIndex[count] = nextLevelOutParamWs.getString(6);
						actColumnIndex[count] = nextLevelOutParamWs
								.getString(7);
						strOutColWidth[count] = nextLevelOutParamWs
								.getString(2);
						strIsGroupBy[count] = nextLevelOutParamWs.getString(3);
						strIsHyperLink[count] = nextLevelOutParamWs
								.getString(8);
						strIsHyperLinkValue[count] = nextLevelOutParamWs
								.getString(9);

						strAlign[count] = nextLevelOutParamWs.getString(10);

						if (strIsGroupBy[count].equals("1")) {

							strOrderString = strOrderString + "#"
									+ actColumnIndex[count];

						}

						count++;

					}

					formBean.setStrOutColDisplayName(strOutCol);
					formBean.setStrOutColIsGrandTotal(isGrandTotal);
					formBean.setStrOutColIndex(columnIndex);
					formBean.setStrOutColActualIndex(actColumnIndex);
					formBean.setStrOutColWidth(strOutColWidth);
					formBean.setStrOutColIsGroupBy(strIsGroupBy);
					formBean.setStrOutColIsHyperLink(strIsHyperLink);
					formBean.setStrOutColIsHyperValue(strIsHyperLinkValue);
					formBean.setStrOutColumnAlign(strAlign);

					// formBean.setStrOrderString(strOrderString);

					vo.setStrOrderString(strOrderString);

				}

				if (formBean.getStrInParamHiddenValues() != null) {

					String[] strInParamHidden = formBean
							.getStrInParamHiddenValues().replace("^", "#")
							.split("#");

					String[] strInParamIdHidden = formBean
							.getStrInParamIdHiddenValues().replace("^", "#")
							.split("#");

					for (int i = 0; i < strInParamHidden.length; i++) {

						firstParamValuesMap.put(strInParamIdHidden[i],
								strInParamHidden[i]);

					}

				}

				strProcCall = "";
				strProcMid = "";

				strProcStart = "{ call "

						+ formBean.getStrTemplateProcName()[formBean
								.getnCurrentLevel()] + "( ";

				WebRowSet nextLevelInParamWs = vo.getWsReportInParamValues();

				if (nextLevelInParamWs != null && nextLevelInParamWs.size() > 0) {

					String strInParamName[] = new String[nextLevelInParamWs
							.size()];
					String strInConstantValue[] = new String[nextLevelInParamWs
							.size()];

					int count = 0;

					while (nextLevelInParamWs.next()) {

						String strInOutParam = nextLevelInParamWs.getString(8);

						// if in out parameter value is not null or not empty
						if (strInOutParam != null
								&& strInOutParam.trim().length() > 0
								&& !strInOutParam.trim().equals("0")) {
							// if in out param value is 1 >> in then get in.
							if (strInOutParam.equals("1")) {

								// get the parameter serial no. value and get
								// the
								// corresponding value from the map if exists
								// use that value as procedure input parameter

								if (firstParamValuesMap.get(nextLevelInParamWs
										.getString(7)) != null) {

									strInParamName[count] = nextLevelInParamWs
											.getString(4);
									strInConstantValue[count] = firstParamValuesMap
											.get(nextLevelInParamWs
													.getString(7));

								} else { // if value not available in the map
											// then use the constant value from
											// table.

									strInParamName[count] = nextLevelInParamWs
											.getString(4);
									strInConstantValue[count] = nextLevelInParamWs
											.getString(2);

								}

							} else { // in out param value must be out (hyper
										// link data)

								// if the out param value index is not null or
								// not empty
								if (nextLevelInParamWs.getString(7) != null
										&& nextLevelInParamWs.getString(7)
												.trim().length() > 0
										&& !nextLevelInParamWs.getString(7)
												.trim().equals("0")) {

									strInParamName[count] = nextLevelInParamWs
											.getString(4);

									// the hidden out parameter value appended
									// strings split it.
									String[] strOutParamValues = formBean
											.getStrOutPramValuesHidden().split(
													"@@@");

									// get the index of the minus 1 and get the
									// corresponding value from hidden value
									// array.
									strInConstantValue[count] = strOutParamValues[(Integer
											.parseInt(nextLevelInParamWs
													.getString(7)) - 1)];

								} else { // if out parameter is zero or empty
											// then use the constant value from
											// table.

									strInParamName[count] = nextLevelInParamWs
											.getString(4);
									strInConstantValue[count] = nextLevelInParamWs
											.getString(2);

								}

							}

						} else { // if in or out param vaiue is empty then use
									// the default value.

							strInParamName[count] = nextLevelInParamWs
									.getString(4);

							strInConstantValue[count] = nextLevelInParamWs
									.getString(2);

						}

						if (count == 0) {
							strProcMid = strProcMid + "? ";
						} else {
							strProcMid = strProcMid + " , ? ";
						}

						count++;

					}

					vo.setStrInParamName(strInParamName);
					vo.setStrInConstantValue(strInConstantValue);

					formBean.setStrInParamName(strInParamName);
					formBean.setStrInConstantValue(strInConstantValue);

					strProcMid = strProcMid + " , ? , ? ";

				}

				strProcCall = strProcStart + strProcMid + ") }";

				vo.setStrCallProcedure(strProcCall);

				vo.setStrReportHeaderParamId(formBean
						.getStrReportHeaderParamId()[formBean
						.getnCurrentLevel()]);
				vo.setStrReportHeaderTypeId(formBean.getStrReportHeaderTypeId()[formBean
						.getnCurrentLevel()]);
				vo.setStrReportHeaderParamReq(formBean
						.getStrReportHeaderParamReq()[formBean
						.getnCurrentLevel()]);
				vo.setStrReportHeaderParamType(formBean
						.getStrReportHeaderParamType()[formBean
						.getnCurrentLevel()]);

				if (vo.getStrReportHeaderParamReq() != null
						&& vo.getStrReportHeaderParamReq().equals("1")) {

					if (vo.getStrReportHeaderParamType().equals("1")) {

						vo.setStrReportHeaderParamValue(firstParamValuesMap
								.get(vo.getStrReportHeaderParamId()));

					} else {

						String[] strOutParamValues = formBean
								.getStrOutPramValuesHidden().split("@@@");

						vo.setStrReportHeaderParamValue(strOutParamValues[(Integer
								.parseInt(vo.getStrReportHeaderParamId()) - 1)]);

					}

				}

				bo.generateRecords(vo);

				if (vo.getStrMsgType().equals("1")) {

					throw new Exception(vo.getStrMsgString());
				}

				formBean.setStrReportHeaderHtmlContent(vo
						.getStrReportHeaderHtmlContent());
				formBean.setStrHeader(vo.getStrHeader());

			}

			formBean.setStrInParamDisplayName(strParamDisplayName);
			formBean.setStrInParamDisplayValue(strParamDisplayValue);

			strExportContents = strExportContents
					+ "</br>"
					+ DynamicReportsTransHLP.exportDrillDownHTML(formBean,
							vo.getWsGeneratedDatas(), "1", "1");

			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(strExportContents);

		} catch (Exception e) {
			strExportContents = strExportContents + " </br> "
					+ DynamicReportsTransHLP.getNoDataScreen(formBean);

			response.setHeader("Cache-Control", "no-cache");
			try {
				response.getWriter().print(strExportContents);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();

			String strmsgText = "DynamicReportsTransDATA.generateDrillDownRecords(vo) --> "
					+ e.getMessage();
			HisException eObj = new HisException("dynamicreports",
					"DynamicReportsTransDATA->generateDrillDownRecords()",
					strmsgText);
			formBean.setStrErrMsg("Application Error [ERROR ID : "
					+ eObj.getErrorID() + "],Contact System Administrator! ");

		}

	}

}
