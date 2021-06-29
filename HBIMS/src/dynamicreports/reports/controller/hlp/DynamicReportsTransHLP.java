/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransHLP.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.controller.hlp;

import hisglobal.utility.HisUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.rowset.WebRowSet;

import mms.transactions.dao.ExportRecordsTransDAO;

import org.apache.commons.lang.math.NumberUtils;

import dynamicreports.reports.controller.fb.DynamicReportsTransFB;
import dynamicreports.reports.dao.DynamicReportsTransDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransHLP.
 */
public class DynamicReportsTransHLP {

	/**
	 * Gets the report proc hidden values.
	 * 
	 * @param ws
	 *            the ws
	 * @return the report proc hidden values
	 * @throws Exception
	 *             the exception
	 */
	public static String getReportProcHiddenValues(WebRowSet ws)
			throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {

			sb.append("<div style='display:none;' ><table > ");
			sb.append("<tr> ");
			sb.append("<td > ");

			if (ws != null) {

				while (ws.next()) {

					sb.append("<input type='hidden' name='strTemplateProcDisplayName' value= '"
							+ ws.getString(1) + "'> ");
					sb.append("<input type='hidden' name='strTemplateProcIsComboDepend' value= '"
							+ ws.getString(2) + "'> ");
					sb.append("<input type='hidden' name='strTemplateProcName' value= '"
							+ ws.getString(3) + "'> ");
					sb.append("<input type='hidden' name='strTemplateProcIsLast' value= '"
							+ ws.getString(4) + "'> ");
					sb.append("<input type='hidden' name='strTemplateProcIsColBase' value= '"
							+ ws.getString(5) + "'> ");

					sb.append("<input type='hidden' name='strTemplateProcId' value= '"
							+ ws.getString(6) + "'> ");

					sb.append("<input type='hidden' name='strReportHeaderTypeId' value= '"
							+ ws.getString(7) + "'> ");
					sb.append("<input type='hidden' name='strReportHeaderParamReq' value= '"
							+ ws.getString(8) + "'> ");
					sb.append("<input type='hidden' name='strReportHeaderParamType' value= '"
							+ ws.getString(9) + "'> ");
					sb.append("<input type='hidden' name='strReportHeaderParamId' value= '"
							+ ws.getString(10) + "'> ");

				}
			}

			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("</table></div>");

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the template in param details.
	 * 
	 * @param ws
	 *            the ws
	 * @param strHosptialCode
	 *            the str hosptial code
	 * @return the template in param details
	 * @throws Exception
	 *             the exception
	 */
	
	public static String getTemplateInParamDetails_BS(WebRowSet ws,
			String strHosptialCode,String seatid) throws Exception {

		StringBuffer sb = new StringBuffer("");

		int count = 0;
		int comboCount = 0;
		try {

			if (ws != null && ws.size() > 0) {

				while (ws.next()) {

					count = count + 1;

					if (ws.getString(3).trim().equals("1")) {

						sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

						sb.append("<tr>");
						sb.append("<td class='LABEL' width='50%' >");
						sb.append(ws.getString(1));

						sb.append("<input type='hidden' name='strInParamDisplayName' value='"
								+ ws.getString(1) + "'>");

						sb.append("<input type='hidden' name='strInParamDisplayValue' value='"
								+ ws.getString(2) + "'>");

						sb.append("<input type='hidden' name='strInParamCompType' value='1'>");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4) + "'> </td>");
						sb.append("<td class='CONTROL' width='50%' >");
						sb.append("");
						sb.append("<input type='text' class='txtFldMax' name='strInConstantValue' value='"
								+ ws.getString(2)
								+ "'> <input type='hidden' name='strInCompType' value='1'> <input type='hidden' name='strInValueDetails' value='"
								+ ws.getString(6) + "'></td>");
						sb.append("</tr>");

						sb.append("</table>");

					} else if (ws.getString(3).trim().equals("2")) {


						sb.append("<div class='row rowFlex reFlex'>");
						sb.append("<div class='col-sm-3'></div>");
						sb.append("<div class='col-sm-3'>");
						sb.append(ws.getString(1));

						sb.append("<input type='hidden' name='strInParamDisplayName' value='"
								+ ws.getString(1) + "'>");

						sb.append("<input type='hidden' name='strInParamDisplayValue' value='"
								+ ws.getString(2) + "'>");

						sb.append("<input type='hidden' name='strInParamCompType' value='2'>");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4)
								+ "'> <input type='hidden' name='strInParamComboVal' value='0'> </div>");
						sb.append("<div class='col-sm-3'>");

						if (ws.getString(5).toUpperCase().contains("SELECT ")
								&& ws.getString(5).toUpperCase()
										.contains("FROM ")) {

							WebRowSet ws1 = null;

							if (ws.getString(5).contains("#")) {

								Pattern p = Pattern.compile("[#][1-9][#]");
								Matcher m = p.matcher(ws.getString(5));

								
								ws1 = ExportRecordsTransDAO.getResultSetForQuery(m.replaceAll("0"),strHosptialCode,seatid);

							} else {

								ws1 = ExportRecordsTransDAO
										.getResultSetForQuery(ws.getString(5),
												strHosptialCode,seatid);

							}

							sb.append(" <div id='comboDiv"
									+ (comboCount)
									+ "'><select name='strInConstantValue' onchange='fetchCombos("
									+ comboCount + ");'  class='browser-default custom-select'>");
							sb.append(new HisUtil("dynamicreports",
									"DynamicReportsTransHLP").getOptionValue(
									ws1, "0", "0^All", true));
							sb.append("</select></div> <input type='hidden' name='strInCompType' value='2'> <input type='hidden' name='strInParamId' value='"
									+ ws.getString(6)
									+ "'>  <input type='hidden' name='strInValueDetails' value='"
									+ ws.getString(6) + "'> ");
							

							comboCount++;

						} else {

							String content[] = ws.getString(5).split("#");

							sb.append("<div id='comboDiv"
									+ (comboCount)
									+ "'><select name='strInConstantValue' onchange='fetchCombos("
									+ comboCount + ");' class='browser-default custom-select'>");
							for (int i = 0; i < content.length; i++) {

								String optionVals[] = content[i].replace("^",
										"#").split("#");

								sb.append(
										"<option value='" + optionVals[0]
												+ "'>").append(optionVals[1])
										.append("</option>");

							}
							sb.append("</select></div> <input type='hidden' name='strInCompType' value='2'> <input type='hidden' name='strInParamId' value='"
									+ ws.getString(6)
									+ "'> <input type='hidden' name='strInValueDetails' value='"
									+ ws.getString(6) + "'> ");
							

							comboCount++;

						}
						sb.append("</div>");
						sb.append("<div class='col-sm-3'></div>");
						sb.append("</div>");

						
					} else if (ws.getString(3).trim().equals("3")) {

						sb.append("<div class='row rowFlex reFlex'>");
						sb.append("<div class='col-sm-3'></div>");
						sb.append("<div class='col-sm-3'>");
						sb.append(ws.getString(1));

						sb.append("<input type='hidden' name='strInParamDisplayName' value='"
								+ ws.getString(1) + "'>");

						sb.append("<input type='hidden' name='strInParamDisplayValue' value='"
								+ ws.getString(2) + "'>");

						sb.append("<input type='hidden' name='strInParamCompType' value='3'>");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4) + "'> </div>");
						sb.append("<div class='col-sm-3'>");

						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd-MMM-yyyy");
						String ctDate = sdf.format(new Date());
						sb.append("<input type='date' id='datePicker' class='form-control' name='strInConstantValueX'>");
						sb.append("<input type='hidden' name='strInConstantValue' id='strInConstantValue"
										+ count
										+ "' value=''> <input type='hidden' name='strInCompType' value='3'> <input type='hidden' name='strInValueDetails' value='"
										+ ws.getString(6) + "'> </div>");

						

						
					} else {

						sb.append("<div style='display:none'>");
						sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

						sb.append("<tr>");
						sb.append("<td class='multiControl' >");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4) + "'>");

						sb.append(" <input type='hidden' name='strInCompType' value='0'> <input type='hidden' name='strInConstantValue' value='"
								+ ws.getString(2)
								+ "'> <input type='hidden' name='strInValueDetails' value='0'> ");

						sb.append("</td></tr>");

						sb.append("</table></div>");

					}
				
					sb.append("<div class='col-sm-3'></div>");
					sb.append("</div>");
				}

				sb.append("</table>");

			} else {

				sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiControl'  colspan='4' style='color:red'>No Detail(s) Available");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");

			}

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}
	
	
	
	public static String getTemplateInParamDetails(WebRowSet ws,
			String strHosptialCode,String seatid) throws Exception {

		StringBuffer sb = new StringBuffer("");

		int count = 0;
		int comboCount = 0;

		sb.append("<div class='line'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
		sb.append("<tr>");
		sb.append("<td colspan='2'>Report Input Parameters ");
		sb.append("</td>");
		sb.append("</tr>");
		/*
		 * sb.append("<tr>");
		 * sb.append("<td class='multiLabel' width='50%'>Parameter Name");
		 * sb.append("</td>");
		 * sb.append("<td class='multiLabel' width='50%'>Parameter Value");
		 * sb.append("</td>"); sb.append("</tr>");
		 */
		sb.append("</table></div>");
		try {

			if (ws != null && ws.size() > 0) {

				while (ws.next()) {

					count = count + 1;

					if (ws.getString(3).trim().equals("1")) {

						sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

						sb.append("<tr>");
						sb.append("<td class='LABEL' width='50%' >");
						sb.append(ws.getString(1));

						sb.append("<input type='hidden' name='strInParamDisplayName' value='"
								+ ws.getString(1) + "'>");

						sb.append("<input type='hidden' name='strInParamDisplayValue' value='"
								+ ws.getString(2) + "'>");

						sb.append("<input type='hidden' name='strInParamCompType' value='1'>");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4) + "'> </td>");
						sb.append("<td class='CONTROL' width='50%' >");
						sb.append("");
						sb.append("<input type='text' class='txtFldMax' name='strInConstantValue' value='"
								+ ws.getString(2)
								+ "'> <input type='hidden' name='strInCompType' value='1'> <input type='hidden' name='strInValueDetails' value='"
								+ ws.getString(6) + "'></td>");
						sb.append("</tr>");

						sb.append("</table>");

					} else if (ws.getString(3).trim().equals("2")) {

						sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

						sb.append("<tr>");
						sb.append("<td class='LABEL' width='50%' >");
						sb.append(ws.getString(1));

						sb.append("<input type='hidden' name='strInParamDisplayName' value='"
								+ ws.getString(1) + "'>");

						sb.append("<input type='hidden' name='strInParamDisplayValue' value='"
								+ ws.getString(2) + "'>");

						sb.append("<input type='hidden' name='strInParamCompType' value='2'>");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4)
								+ "'> <input type='hidden' name='strInParamComboVal' value='0'> </td>");
						sb.append("<td class='CONTROL' width='50%' >");

						if (ws.getString(5).toUpperCase().contains("SELECT ")
								&& ws.getString(5).toUpperCase()
										.contains("FROM ")) {

							WebRowSet ws1 = null;

							if (ws.getString(5).contains("#")) {

								Pattern p = Pattern.compile("[#][1-9][#]");
								Matcher m = p.matcher(ws.getString(5));

								
								ws1 = ExportRecordsTransDAO.getResultSetForQuery(m.replaceAll("0"),strHosptialCode,seatid);

							} else {

								ws1 = ExportRecordsTransDAO
										.getResultSetForQuery(ws.getString(5),
												strHosptialCode,seatid);

							}

							sb.append(" <div id='comboDiv"
									+ (comboCount)
									+ "'><select name='strInConstantValue' onchange='fetchCombos("
									+ comboCount + ");'  class='comboMax'>");
							sb.append(new HisUtil("dynamicreports",
									"DynamicReportsTransHLP").getOptionValue(
									ws1, "0", "0^All", true));
							sb.append("</select></div> <input type='hidden' name='strInCompType' value='2'> <input type='hidden' name='strInParamId' value='"
									+ ws.getString(6)
									+ "'>  <input type='hidden' name='strInValueDetails' value='"
									+ ws.getString(6) + "'> ");
							sb.append("</td>");

							comboCount++;

						} else {

							String content[] = ws.getString(5).split("#");

							sb.append("<div id='comboDiv"
									+ (comboCount)
									+ "'><select name='strInConstantValue' onchange='fetchCombos("
									+ comboCount + ");' class='comboMax'>");
							for (int i = 0; i < content.length; i++) {

								String optionVals[] = content[i].replace("^",
										"#").split("#");

								sb.append(
										"<option value='" + optionVals[0]
												+ "'>").append(optionVals[1])
										.append("</option>");

							}
							sb.append("</select></div> <input type='hidden' name='strInCompType' value='2'> <input type='hidden' name='strInParamId' value='"
									+ ws.getString(6)
									+ "'> <input type='hidden' name='strInValueDetails' value='"
									+ ws.getString(6) + "'> ");
							sb.append("</td>");

							comboCount++;

						}

						sb.append("</tr>");

						sb.append("</table>");

					} else if (ws.getString(3).trim().equals("3")) {

						sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

						sb.append("<tr>");
						sb.append("<td class='LABEL' width='50%' >");
						sb.append(ws.getString(1));

						sb.append("<input type='hidden' name='strInParamDisplayName' value='"
								+ ws.getString(1) + "'>");

						sb.append("<input type='hidden' name='strInParamDisplayValue' value='"
								+ ws.getString(2) + "'>");

						sb.append("<input type='hidden' name='strInParamCompType' value='3'>");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4) + "'> </td>");
						sb.append("<td class='CONTROL' width='50%' >");

						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd-MMM-yyyy");
						String ctDate = sdf.format(new Date());

						sb.append(
								HisUtil.getDatePicker("strInConstantValueX",
										ctDate, true))
								.append("<input type='hidden' name='strInConstantValue' id='strInConstantValue"
										+ count
										+ "' value=''> <input type='hidden' name='strInCompType' value='3'> <input type='hidden' name='strInValueDetails' value='"
										+ ws.getString(6) + "'> </td>");

						sb.append("</tr>");

						sb.append("</table>");

					} else {

						sb.append("<div style='display:none'>");
						sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

						sb.append("<tr>");
						sb.append("<td class='multiControl' >");

						sb.append("<input type='hidden' name='strInParamName' value='"
								+ ws.getString(4) + "'>");

						sb.append(" <input type='hidden' name='strInCompType' value='0'> <input type='hidden' name='strInConstantValue' value='"
								+ ws.getString(2)
								+ "'> <input type='hidden' name='strInValueDetails' value='0'> ");

						sb.append("</td></tr>");

						sb.append("</table></div>");

					}

				}

				sb.append("</table>");

			} else {

				sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiControl'  colspan='4' style='color:red'>No Detail(s) Available");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");

			}

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the template out param details.
	 * 
	 * @param ws
	 *            the ws
	 * @return the template out param details
	 * @throws Exception
	 *             the exception
	 */
	public static String getTemplateOutParamDetails(WebRowSet ws)
			throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {

			if (ws != null && ws.size() > 0) {

				sb.append("<div style='display:none;' ><table > ");

				while (ws.next()) {

					sb.append("<tr> ");
					sb.append("<td > ");

					sb.append("<input type='hidden' name='strOutColDisplayName' value= '"
							+ ws.getString(1) + "'> ");
					sb.append("<input type='hidden' name='strOutColWidth' value= '"
							+ ws.getString(2) + "'> ");
					sb.append("<input type='hidden' name='strOutColIsGroupBy' value= '"
							+ ws.getString(3) + "'> ");
					sb.append("<input type='hidden' name='strOutColIsOrderBy' value= '"
							+ ws.getString(4) + "'> ");
					sb.append("<input type='hidden' name='strOutColIsGrandTotal' value= '"
							+ ws.getString(5) + "'> ");
					sb.append("<input type='hidden' name='strOutColIndex' value= '"
							+ ws.getString(6) + "'> ");
					sb.append("<input type='hidden' name='strOutColActualIndex' value= '"
							+ ws.getString(7) + "'> ");
					sb.append("<input type='hidden' name='strOutColIsHyperLink' value= '"
							+ ws.getString(8) + "'> ");

					sb.append("<input type='hidden' name='strOutColumnAlign' value= '"
							+ ws.getString(9) + "'> ");

					sb.append("</td> ");
					sb.append("</tr> ");

				}

				sb.append("</table></div>");

			}

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the template column param details.
	 * 
	 * @param ws
	 *            the ws
	 * @return the template column param details
	 * @throws Exception
	 *             the exception
	 */
	public static String getTemplateColumnParamDetails(WebRowSet ws)
			throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {

			if (ws != null && ws.size() > 0) {

				sb.append("<div style='display:none;' ><table > ");

				while (ws.next()) {

					sb.append("<tr> ");
					sb.append("<td > ");

					sb.append("<input type='hidden' name='strColumnDisplayName' value= '"
							+ ws.getString(1) + "'> ");
					sb.append("<input type='hidden' name='strColumnPrifix' value= '"
							+ ws.getString(2) + "'> ");
					sb.append("<input type='hidden' name='strColumnFormula' value= '"
							+ ws.getString(3) + "'> ");
					sb.append("<input type='hidden' name='strColumnSuffix' value= '"
							+ ws.getString(4) + "'> ");

					sb.append("<input type='hidden' name='strOutColumnAlign' value= '"
							+ ws.getString(5) + "'> ");

					sb.append("</td> ");
					sb.append("</tr> ");

				}

				sb.append("</table></div>");

			}

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the combo details.
	 * 
	 * @param strTemplateId
	 *            the str template id
	 * @param strParamIds
	 *            the str param ids
	 * @param strComboValues
	 *            the str combo values
	 * @param strCurrentComboId
	 *            the str current combo id
	 * @param strHosptialCode
	 *            the str hosptial code
	 * @return the combo details
	 * @throws Exception
	 *             the exception
	 */
	public static String getComboDetails(String strTemplateId,
			String strParamIds, String strComboValues,
			String strCurrentComboId, String strHosptialCode) throws Exception {

		StringBuffer sb = new StringBuffer("");

		String[] strParamIdValues = strParamIds.split("@");
		String[] strComboValueArray = strComboValues.split("@");

		int ctComboId = Integer.parseInt(strCurrentComboId) + 1;

		try {

			sb.append(strCurrentComboId + "@@" + strParamIdValues.length + "@@");

			for (int i = ctComboId; i < strParamIdValues.length; i++) {

				String strParamArray[] = strParamIdValues[i].replace("^", "#")
						.split("#");

				String strComboQuery = DynamicReportsTransDAO.getComboQuery(
						strTemplateId, strParamArray[0], strParamArray[1],
						strHosptialCode);

				if (strComboQuery.toUpperCase().contains("SELECT ")
						&& strComboQuery.toUpperCase().contains("FROM ")) {

					if (strComboQuery.contains("#")) {

						Pattern p = Pattern.compile("[#][1-9][#]");
						Matcher m = p.matcher(strComboQuery);

						while (strComboQuery
								.charAt(strComboQuery.indexOf("#") + 2) == '#') {

							char indexChar = strComboQuery.charAt(strComboQuery
									.indexOf("#") + 1);

							int indexValue = Integer.parseInt(String
									.valueOf(indexChar)) - 1;

							strComboQuery = m
									.replaceFirst(strComboValueArray[indexValue]);

							m = p.matcher(strComboQuery);

						}

					}

					WebRowSet ws1 = DynamicReportsTransDAO
							.getResultSetForQuery(strComboQuery,
									strHosptialCode);

					sb.append("<select class='comboMax' onchange='fetchCombos("
							+ i
							+ ");' name='strInConstantValue' tabindex='1' onkeydown='return onPressingEnter(this,event)'>");
					sb.append(new HisUtil("ExportRecordsTransHLP",
							"ExportRecordsTransHLP.getTemplateDetails")
							.getOptionValue(ws1, strComboValueArray[i],
									"0^All", true));
					sb.append("</select>@@");

				} else {
					String content[] = strComboQuery.split("#");

					sb.append("<select class='comboMax' onchange='fetchCombos("
							+ i
							+ ");' name='strInConstantValue' tabindex='1' onkeydown='return onPressingEnter(this,event)'>");
					for (int k = 0; k < content.length; k++) {

						String optionVals[] = content[k].replace("^", "#")
								.split("#");

						if (optionVals[0].equals(strComboValueArray[i])) {

							sb.append(
									"<option selected='selected' value='"
											+ optionVals[0] + "'>")
									.append(optionVals[1]).append("</option>");

						} else {

							sb.append("<option value='" + optionVals[0] + "'>")
									.append(optionVals[1]).append("</option>");

						}

					}
					sb.append("</select>@@");

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return sb.toString();

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
	 * Gets the group by headers.
	 * 
	 * @param strGroupName
	 *            the str group name
	 * @param strGroupValue
	 *            the str group value
	 * @return the group by headers
	 */
	private static String getGroupByHeaders(String[] strGroupName,
			String[] strGroupValue) {
		String strGroupHeader = "";

		if (strGroupName != null && strGroupName.length > 0) {

			String strName = strGroupName[0];
			String strValue = strGroupValue[0];

			for (int i = 1; i < strGroupName.length; i++) {

				strName = strName + " / " + strGroupName[i];
				strValue = strValue + " / " + strGroupValue[i];

			}

			strGroupHeader = strName + " :: " + strValue;

		}

		return strGroupHeader;
	}

	/**
	 * Export row based html.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param ws
	 *            the ws
	 * @param isHeaderFooterReq
	 *            the is header footer req
	 * @param isInParamReq
	 *            the is in param req
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	@SuppressWarnings("deprecation")
	public static String exportRowBasedHTML(DynamicReportsTransFB formBean,
			WebRowSet ws, String isHeaderFooterReq, String isInParamReq)
			throws Exception {

		String strReportName = formBean.getStrReportDisplayName();

		String[] strParamName = formBean.getStrInParamName();
		String[] strXlsColumnName = formBean.getStrOutColDisplayName();
		String strFooter = "Computer Generated Report";
		String[] isGrandTotal = formBean.getStrOutColIsGrandTotal();
		String[] columnIndex = formBean.getStrOutColIndex();
		String[] actColumnIndex = formBean.getStrOutColActualIndex();
		String[] strInParamNames = formBean.getStrInParamDisplayName();
		String[] strInParamValues = formBean.getStrInParamDisplayValue();

		String[] strAlign = formBean.getStrOutColumnAlign();

		String[] strInParamIds = formBean.getStrInValueDetails();

		String[] strOutColWidth = formBean.getStrOutColWidth();

		String[] strInComboVal = formBean.getStrInParamComboVal();
		String[] strCompType = formBean.getStrInParamCompType();

		// String strExportType = formBean.getStrExportType();
		String[] strIsGroupBy = formBean.getStrOutColIsGroupBy();

		String[] strIsHyperLink = formBean.getStrOutColIsHyperLink();

		String strIsBorder = formBean.getStrReportBorderReq();

		String strTableWidth = formBean.getStrReportWidth();

		String strInParamHiddenValues = "";

		String strInParamIdHiddenValues = "";

		String strGroupHeaderString = "";

		String strGroupHeaderStringTemp = "";

		if (formBean.getStrReportWidthUnit().equals("1")) {

			strTableWidth = strTableWidth + "%";

		} else {

			strTableWidth = strTableWidth + "px";

		}

		StringBuffer sb = null;

		StringBuffer sb1 = null;

		String strUnitName[] = null;
		double strUnitTotal[] = null;

		int comboCount = 0;

		if (isGrandTotal.length > 0) {

			strUnitName = new String[isGrandTotal.length];
			strUnitTotal = new double[isGrandTotal.length];

		}

		boolean fUnitFlag = false;

		double[] dTotal = new double[isGrandTotal.length];

		NumberFormat formatter = new DecimalFormat("############.##");

		/*
		 * Color Codes
		 * 
		 * Header / Footer : #FAC090 Group Header : #FFEBD5 Group Total :
		 * #B3D9FF Alternate Color : #D5E1E6
		 */

		String strHeaderColor = "#FFFFFF";
		String strFooterColor = "#FFFFFF";
		String strGrpHeaderColor = "#FFFFFF";
		String strGrpTotalColor = "#FFFFFF";
		String strAlternateColor = "#FFFFFF";

		String strReportColors = "";

		try {

			strReportColors = DynamicReportsTransDAO.getReportColors(formBean
					.getStrHospitalCode());

			if (strReportColors != null && strReportColors.trim().length() > 0) {

				String[] strColorCodes = strReportColors.replace("^", "@")
						.split("@");

				if (strColorCodes.length == 5) {
									
					strHeaderColor = strColorCodes[0];
					strFooterColor = strColorCodes[1];
					strGrpHeaderColor = strColorCodes[2];
					strGrpTotalColor = strColorCodes[3];
					strAlternateColor = strColorCodes[4];

				}
			}

			
			sb = new StringBuffer("");
			sb1 = new StringBuffer("");

		
			if (strInParamIds != null)
				for (int i = 0; i < strInParamIds.length; i++) {

					if (i == 0) {

						if (strInParamIds[i].contains("^")) {

							strInParamIdHiddenValues = strInParamIds[i]
									.replace("^", "#").split("#")[1];

						}

					} else {

						if (strInParamIds[i].contains("^")) {

							if (strInParamIdHiddenValues.trim().length() == 0) {

								strInParamIdHiddenValues = strInParamIds[i]
										.replace("^", "#").split("#")[1];

							} else {

								strInParamIdHiddenValues = strInParamIdHiddenValues
										+ "^"
										+ strInParamIds[i].replace("^", "#")
												.split("#")[1];

							}

						}

					}

				}

			if (isHeaderFooterReq.equals("1")) {

				sb.append("<table align='center' border='0' cellspacing='0' cellpadding='0' height='69' width='"
						+ strTableWidth + "'> ");
				sb.append("<tr><td colspan='"
						+ strParamName.length
						+ "' width='20%' align='right'><div align='right'>Report Date and Time : "
						+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
		//						.format(new Date()) + "</font></div></td></tr>");
						.format(new Date()) + "</div></td></tr>");
			if(!formBean.getStrExportType().equalsIgnoreCase("pdf"))
				if (formBean.getStrReportHeaderHtmlContent() != null
						&& !formBean.getStrReportHeaderHtmlContent().trim()
								.equals("")
						&& formBean.getStrReportHeaderHtmlContent().trim()
								.length() != 0) {

					sb.append("<tr><td colspan='" + strParamName.length + "'> "
							+ formBean.getStrReportHeaderHtmlContent()
							+ " </td>");
					sb.append("</tr> ");

					}

				if (formBean.getStrHeader() != null)
					for (int j = 0; j < formBean.getStrHeader().length; j++) {

						if (formBean.getStrHeader()[j] != null
								&& !formBean.getStrHeader()[j].trim()
										.equals("")
								&& formBean.getStrHeader()[j].trim().length() > 0) {

							if (j == 0) {

								sb.append("<tr><td colspan='"
										+ strParamName.length
										+ "'  align='center'><div align='center'> <b> ");
								sb.append(formBean.getStrHeader()[j]);
								//sb.append("</font></b></div></td>");
								sb.append("</b></div></td>");
								sb.append("</tr> ");

							} else {

								sb.append("<tr><td colspan='"
										+ strParamName.length
										+ "'  align='center'><div align='center'> <b> ");
								sb.append(formBean.getStrHeader()[j]);
								//sb.append("</font></b></div></td>");
								sb.append("</b></div></td>");
								sb.append("</tr> ");

							}

						}

					}

				sb.append("</table>");

				sb.append("<table align='center' cellpadding='1' cellspacing='1' border='0' width='"
						+ strTableWidth + "'>");

				sb.append("<tr>");
				sb.append("<td colspan='"
						+ strParamName.length
						+ "' align='center' nowrap><div align='center'> <b> ");
				sb.append(strReportName);
				//sb.append("</font></b></div></td>");
				sb.append("</b></div></td>");
				sb.append("</tr>");
				sb.append("</table>");

			}

			if (isInParamReq.equals("1")) {

				// In Parameter Names and values
				if (strInParamNames != null && strInParamNames.length > 0) {

					sb.append("<div align='center'><table align='center' border='0' cellspacing='0' cellpadding='0' height='69' width='"
							+ strTableWidth + "'> ");
					sb.append("<tr>");
					for (int i = 0; i < strInParamNames.length; i++) {

						if (i == 0) {
							strInParamHiddenValues = strInParamValues[i];

						} else {

							strInParamHiddenValues = strInParamHiddenValues
									+ "^" + strInParamValues[i];

						}

						//sb.append("<td width='40%'></td><td width='60%' align='left' nowrap><div align='left'>  ");
						sb.append("<td width='' colspan='' align='center' nowrap><div align='center'>  ");
						sb.append(strInParamNames[i]
								+ " : <b> <input type='hidden' name='strInParamDisplayName0' value='"
								+ strInParamNames[i] + "' ></input>");

						if (strCompType[i].equals("2")) {

							sb.append(strInComboVal[comboCount]).append(
									"<input type='hidden' name='strInParamDisplayValue0' value='"
											+ strInComboVal[comboCount]
											+ "' ></input>");

							comboCount++;

						} else {

							sb.append(strInParamValues[i]).append(
									"<input type='hidden' name='strInParamDisplayValue0' value='"
											+ strInParamValues[i]
											+ "' ></input>");

						}

						sb.append(" </b></div></td>");//sb.append(" </b></font></div></td>");

						

					}
					sb.append("</tr>");
					sb.append("</table> </div>  ");

				}

			}

			int grpLen = 0;
			int actualLen = strXlsColumnName.length;

			if (strIsGroupBy != null && strIsGroupBy.length > 0) {

				for (int j = 0; j < strIsGroupBy.length; j++) {

					if (strIsGroupBy[j].equals("1")) {

						grpLen++;

					}

				}

			}

			if (grpLen > 0) {

				actualLen = strXlsColumnName.length - grpLen;

				if (strIsBorder.equals("1")) {

					sb.append("<table align='center' cellpadding='1' cellspacing='1'  style='border-collapse: collapse; border: medium none;' border='1'   width='"
							+ strTableWidth + "'>");

				} else {

					sb.append("<table align='center' cellpadding='1' cellspacing='1' border='0' width='"
							+ strTableWidth + "'>");

				}

				
				
				sb.append("<tr>");

				for (int i = grpLen; i < strXlsColumnName.length; i++) {

					sb.append(
							"<td  class='" + strHeaderColor + "'   align='center'  width='"
									+ strOutColWidth[i]
									+ "%' nowrap><b>  ")
							.append(strXlsColumnName[Integer
									.parseInt(columnIndex[i]) - 1])
							.append("</b></td>");

				}

			} else {

				if (strIsBorder.equals("1")) {

					sb.append("<table align='center' cellpadding='1' cellspacing='1'  style='border-collapse: collapse; border: medium none;' border='1'   width='"
							+ strTableWidth + "'>");

				} else {

					sb.append("<table align='center' cellpadding='1' cellspacing='1' border='0' width='"
							+ strTableWidth + "'>");

				}

				
				
				sb.append("<tr>");

				for (int i = 0; i < strXlsColumnName.length; i++) {

					sb.append(
							"<td  class='" + strHeaderColor + "'  align='center'   width='"
									+ strOutColWidth[i]
									+ "%' nowrap><b>  ")
							.append(strXlsColumnName[Integer
									.parseInt(columnIndex[i]) - 1])
							.append("</b></td>");//.append("</font></b></td>");

				}
			}

			sb.append("</tr>");

			

			int count = 0;

			int rowCount = 0;

			int colCount = 0;

			String strLastColumnName = "";

			String strOutPramValuesHidden = "";

			if (ws != null && ws.size() > 0) {

				colCount = ws.getMetaData().getColumnCount();

				strLastColumnName = ws.getMetaData().getColumnName(colCount);

				String tempColumnName[] = new String[grpLen];

				boolean tempFlag[] = new boolean[grpLen];

				boolean tempGrpTotal[] = new boolean[grpLen];

				double[] grpTotalValues = new double[colCount];

				String sbTemp = "";

				for (int m = 0; m < tempColumnName.length; m++) {
					tempColumnName[m] = "";

					tempFlag[m] = false;
					tempGrpTotal[m] = false;

				}

				while (ws.next()) {

					rowCount++;

					if (formBean.getStrReportTypeId().equals("3"))
						for (int i = 1; i <= colCount; i++) {

							if (i == 1) {

								strOutPramValuesHidden = ws.getString(i);

							} else {

								strOutPramValuesHidden = strOutPramValuesHidden
										+ "@@@" + ws.getString(i);

							}

						}

					if (grpLen > 0) {

						String[] strParamHeading = new String[grpLen];
						String[] strParamValues = new String[grpLen];

						for (int k = 0; k < grpLen; k++) {

							String strParamValue = ws.getString(Integer
									.parseInt(actColumnIndex[k]));

							if (strParamValue == null)
								strParamValue = "NA";

							if (strParamValue != null
									&& strParamValue.trim().length() == 0)
								strParamValue = "NA";

							strParamHeading[k] = strXlsColumnName[Integer
									.parseInt(columnIndex[k]) - 1];
							strParamValues[k] = strParamValue;

							if (!strParamValue.trim().equalsIgnoreCase(
									tempColumnName[k])) {

								tempFlag[k] = true;
								tempColumnName[k] = strParamValue;

							} else {
								tempFlag[k] = false;
							}

							if (isGrandTotal[k].equals("1")) {

								tempGrpTotal[k] = true;

							} else {

								tempGrpTotal[k] = false;

							}

						}

						for (int k = 0; k < grpLen; k++) {

							if (tempFlag[k] == true) {

								if (tempGrpTotal[k] == true)
									if (count != 0) {

										sb.append("<tr>");

										for (int i = grpLen; i < isGrandTotal.length; i++) {

											if (isGrandTotal[i].equals("1")
													&& grpTotalValues[i] > 0) {

												sb.append("<td align='"
														+ strAlign[i]
														+ "' nowrap><b>");

												if (grpTotalValues[i] == 0) {

													sb.append(grpTotalValues[i]);

												} else {

													sb.append(formatter
															.format(new BigDecimal(
																	String.valueOf(grpTotalValues[i]))));

												}

												if (strUnitName[i] != null) {

													sb.append(" ").append(
															strUnitName[i]);

												}

												sb.append("</b></td>");

												grpTotalValues[i] = 0;

											} else {

												sb.append("<td width='"
														+ strOutColWidth[i]
														+ "%' align='"
														+ strAlign[i]
														+ "' nowrap> </td>");
											}

										}
										sb.append("</tr>");

									} else {

										count = count + 1;

									}

								tempFlag[k] = false;

							}

						}

						strGroupHeaderString = getGroupByHeaders(
								strParamHeading, strParamValues);

						if (!strGroupHeaderString.trim().equals(
								strGroupHeaderStringTemp)) {

							sbTemp = "<tr bgcolor='" + strGrpHeaderColor
									+ "'><td align='left' colspan='"
									+ actualLen + "' nowrap>";

							if (count != 0)
								sbTemp = sbTemp
										+ "<div style='page-break-before: always'></div>";

							sbTemp = sbTemp
									+ "<b>"
									+ strGroupHeaderString
									+ "</b></td></tr>";

							sb.append(sbTemp);

							sbTemp = "";

						}

						strGroupHeaderStringTemp = strGroupHeaderString;

					}

					if (strLastColumnName.equals("COLOR_VAL")) {

						String strColor = ws.getString(colCount);

						if (strColor == null || strColor.trim().length() == 0) {

							if (rowCount % 2 == 0) {

								strColor = "white";

							} else {

								strColor = strAlternateColor;

							}

						}

						sb.append("<tr bgcolor='" + strColor + "'>");

					} else {

						if (rowCount % 2 == 0) {

							sb.append("<tr>");

						} else {

							sb.append("<tr bgcolor='" + strAlternateColor
									+ "'>");

						}

					}

					for (int i = grpLen; i < strXlsColumnName.length; i++) {

						String strParamValue = ws.getString(Integer
								.parseInt(actColumnIndex[i]));

						if (strParamValue != null
								&& NumberUtils.isNumber(strParamValue.trim()
										.replace(" ", "#").split("#")[0])) {

							sb.append("<td align='"
									+ strAlign[i]
									+ "' width='"
									+ strOutColWidth[i]
									+ "%' nowrap>");

							if (strIsHyperLink != null
									&& strIsHyperLink[i] != null
									&& strIsHyperLink[i].equals("1")) {

								sb.append("<div style='color:blue;cursor:pointer;' onclick='getNextLevelReport(\""
										+ rowCount
										+ "\" , \""
										+ formBean.getnCurrentLevel()
										+ "\" , \""
										+ strXlsColumnName[i]
										+ "^"
										+ strParamValue
										+ "\" );' >"
										+ strParamValue + "</div>");

							} else {
								sb.append(strParamValue);
							}

							sb.append("</td>");

						} else {

							sb.append("<td width='"
									+ strOutColWidth[i]
									+ "%' align='"
									+ strAlign[i]
									+ "' nowrap >     ");

							if (strIsHyperLink != null
									&& strIsHyperLink[i] != null
									&& strIsHyperLink[i].equals("1")) {

								sb.append("<div style='color:blue;cursor:pointer;' onclick='getNextLevelReport(\""
										+ rowCount
										+ "\" , \""
										+ formBean.getnCurrentLevel()
										+ "\" , \""
										+ strXlsColumnName[i]
										+ "^"
										+ strParamValue
										+ "\" );' >"
										+ strParamValue + "</div>");

							} else {
								sb.append(strParamValue);
							}

							sb.append("</td>");

						}

						// Whether total is required
						if (Integer.valueOf(isGrandTotal[i]) == 1) {

							// whether value is numeric
							if (NumberUtils.isNumber(strParamValue)) {
								// if value is numeric find total
								dTotal[i] = dTotal[i]
										+ Double.parseDouble(strParamValue);

								grpTotalValues[i] = grpTotalValues[i]
										+ Double.parseDouble(strParamValue);

							} else {
								// if value is not numeric set the total as -1

								dTotal[i] = -1;

								grpTotalValues[i] = -1;

								if (strParamValue.trim().replace(" ", "#")
										.contains("#")) {

									String[] strVals = strParamValue.trim()
											.replace(" ", "#").split("#");

									if (strVals.length == 2) {

										if (NumberUtils.isNumber(strVals[0])
												&& !NumberUtils
														.isNumber(strVals[1])) {

											if (!fUnitFlag)
												strUnitName[i] = strVals[1];

											if (strUnitName[i]
													.equalsIgnoreCase(strVals[1])) {

												strUnitTotal[i] = strUnitTotal[i]
														+ Float.parseFloat(strVals[0]);

												dTotal[i] = strUnitTotal[i];

												grpTotalValues[i] = strUnitTotal[i];

											} else {

												strUnitName[i] = "";
											}

										}

									}

								}

							}

						} else {
							// if total is not required set the value as -1
							dTotal[i] = -1;

						}

					}

					sb1.append("<input type='hidden' id='strOutPramValuesHidden"
							+ formBean.getnCurrentLevel()
							+ ""
							+ rowCount
							+ "' name='strOutPramValuesHidden' value='"
							+ strOutPramValuesHidden + "'> </input>");

					sb.append("</tr>");

					fUnitFlag = true;

				}

				for (int k = 0; k < grpLen; k++) {

					if (tempGrpTotal[k] == true) {

						sb.append("<tr bgcolor='" + strGrpTotalColor + "'>");

						for (int i = grpLen; i < isGrandTotal.length; i++) {

							if (isGrandTotal[i].equals("1")
									&& grpTotalValues[i] > 0) {

								sb.append("<td align='"
										+ strAlign[i]
										+ "' nowrap><b>");

								if (grpTotalValues[i] == 0) {

									sb.append(grpTotalValues[i]);

								} else {
									sb.append(formatter.format(new BigDecimal(
											String.valueOf(grpTotalValues[i]))));
								}

								if (strUnitName[i] != null) {

									sb.append(" ").append(strUnitName[i]);

								}

								sb.append("</b></td>");

								grpTotalValues[i] = 0;

							} else {

								sb.append("<td width='" + strOutColWidth[i]
										+ "%' align='" + strAlign[i]
										+ "' nowrap> </td>");
							}

						}
						sb.append("</tr>");

					}
				}

				sb.append("<tr>");
				sb.append("<td colspan='"
						+ actualLen
						+ "' align='center'>"
						+ sb1.toString()
						+ "<input type='hidden' name='strInParamHiddenValues' id='strInParamHiddenValues"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ strInParamHiddenValues
						+ "' /><input type='hidden' name='strInParamIdHiddenValues' id='strInParamIdHiddenValues"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ strInParamIdHiddenValues
						+ "' /><input type='hidden' name='strTemplateProcName' id='strTemplateProcName"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrTemplateProcName()[formBean
								.getnCurrentLevel()]
						+ "'  /><input type='hidden' name='strTemplateProcDisplayName' id='strTemplateProcDisplayName"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrTemplateProcDisplayName()[formBean
								.getnCurrentLevel()]
						+ "'  /><input type='hidden' name='strTemplateProcId' id='strTemplateProcId"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrTemplateProcId()[formBean
								.getnCurrentLevel()]
						+ "' /><input type='hidden' name='strReportTypeId'  id='strReportTypeId'   value='"
						+ formBean.getStrReportTypeId()
						+ "' /><input type='hidden' name='strReportTemplateId' id='strReportTemplateId'  value='"
						+ formBean.getStrReportTemplateId()
						+ "' />  <input type='hidden' name='strReportWidth' id='strReportWidth' value='"
						+ formBean.getStrReportWidth()
						+ "' /> <input type='hidden' id='strReportWidthUnit' name='strReportWidthUnit' value = '"
						+ formBean.getStrReportWidthUnit()
						+ "'/> <input type='hidden' id='strReportBorderReq' name='strReportBorderReq'  value = '"
						+ formBean.getStrReportBorderReq() + "'/> </td>");
				sb.append("</tr>");

				StringBuffer grandTotalInWordsRow = new StringBuffer("");

				HisUtil hisUtil = new HisUtil("Dynamic Reports",
						"DynamicReportsTransHLP");

				sb.append("<tr>");

				grandTotalInWordsRow.append("<tr>");

				boolean flag = false;

				for (int i = grpLen; i < isGrandTotal.length; i++) {

					if (isGrandTotal[i].equals("1")) {

						sb.append("<td  class='" + strFooterColor + "'  align='"
								+ strAlign[i]
								+ "'><b>");
						grandTotalInWordsRow
								.append("<td class='" + strFooterColor + "' align='"
										+ strAlign[i]
										+ "'>");

						if (dTotal[i] == 0) {

							sb.append(dTotal[i]);

						} else {

							sb.append(formatter.format(new BigDecimal(String
									.valueOf(dTotal[i]))));

						}

						if (strUnitName[i] != null) {

							sb.append(" ").append(strUnitName[i]);

						} else {

							grandTotalInWordsRow.append(hisUtil
									.toInitCap(hisUtil.getNumbertoWords(formatter
											.format(new BigDecimal(String
													.valueOf(dTotal[i]))))));

						}

						sb.append("</b></td>");

						grandTotalInWordsRow.append("</td>");

						flag = true;

					} else {

						sb.append("<td class='" + strFooterColor + "' width='" + strOutColWidth[i]
								+ "%' align='" + strAlign[i] + "'> </td>");

						grandTotalInWordsRow.append("<td class='" + strFooterColor + "' width='"
								+ strOutColWidth[i] + "%' align='"
								+ strAlign[i] + "'> </td>");

					}

				}
				sb.append("</tr>");
				sb.append(grandTotalInWordsRow.append("</tr>"));

				if (flag) {

				}

			} else {
				sb.append("<tr>");
				sb.append("<td colspan='"
						+ actualLen
						+ "' ><div align='center'><b><center>No Record(s) Available</center></b></div></td>");
				sb.append("</tr>");
			}

			if (isHeaderFooterReq.equals("1")) {

				if (strFooter.trim().length() > 0) {

					sb.append("<tr>");
					sb.append("<td colspan='"
							+ actualLen
							+ "' align='center'><div align='center'> <b> ");
					sb.append(strFooter);
					sb.append("</b></div></td>");
					sb.append("</tr>");

				}

			}

			sb.append("</table>");

			// sb.append("</td></tr></table>");

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Export col based html.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param ws
	 *            the ws
	 * @param isHeaderFooterReq
	 *            the is header footer req
	 * @param isInParamReq
	 *            the is in param req
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String exportColBasedHTML(DynamicReportsTransFB formBean,
			WebRowSet ws, String isHeaderFooterReq, String isInParamReq)
			throws Exception {

		String strReportName = formBean.getStrReportDisplayName();

		String strFooter = "Computer Generated Report";

		String[] strInParamNames = formBean.getStrInParamDisplayName();
		String[] strInParamValues = formBean.getStrInParamDisplayValue();
		String[] strInComboVal = formBean.getStrInParamComboVal();
		String[] strCompType = formBean.getStrInParamCompType();

		String[] strOutCol = formBean.getStrOutColDisplayName();

		String[] strAlign = formBean.getStrOutColumnAlign();

		String[] strColumnDisplayName = formBean.getStrColumnDisplayName();
		String[] strColumnPrifix = formBean.getStrColumnPrifix();
		String[] strColumnFormula = formBean.getStrColumnFormula();
		String[] strColumnSuffix = formBean.getStrColumnSuffix();

		Map<String, String> map = new HashMap<String, String>();

		ws.beforeFirst();
		if (ws != null && ws.next()) {

			for (int k = 1; k <= strOutCol.length; k++) {

				map.put(getAlphaFromNumbers(k), ws.getString(k));

			}

		}

		// String strExportType = formBean.getStrExportType();

		String strIsBorder = formBean.getStrReportBorderReq();

		String strTableWidth = formBean.getStrReportWidth();

		if (formBean.getStrReportWidthUnit().equals("1")) {

			strTableWidth = strTableWidth + "%";

		} else {

			strTableWidth = strTableWidth + "px";

		}

		StringBuffer sb = null;

		int comboCount = 0;

		try {

			// ResourceBundle res =
			// ResourceBundle.getBundle("global.rptProperties");

			sb = new StringBuffer("");

			

			if (isHeaderFooterReq.equals("1")) {

				sb.append("<table align='center' border='0' cellspacing='0px' cellpadding='0px' height='69' width='"
						+ strTableWidth + "'> ");
				sb.append("<tr><td colspan='2' width='20%' align='right'><div align='right'>Report Date and Time : "
						+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
								.format(new Date()) + "</div></td></tr>");
				if (formBean.getStrReportHeaderHtmlContent() != null
						&& !formBean.getStrReportHeaderHtmlContent().trim()
								.equals("")
						&& formBean.getStrReportHeaderHtmlContent().trim()
								.length() != 0) {

					sb.append("<tr><td> "
							+ formBean.getStrReportHeaderHtmlContent()
							+ " </td>");
					sb.append("</tr> ");

				}

				if (formBean.getStrHeader() != null)
					for (int j = 0; j < formBean.getStrHeader().length; j++) {

						if (formBean.getStrHeader()[j] != null
								&& !formBean.getStrHeader()[j].trim()
										.equals("")
								&& formBean.getStrHeader()[j].trim().length() > 0) {

							if (j == 0) {

								sb.append("<tr><td align='center'><div align='center'> <b> ");
								sb.append(formBean.getStrHeader()[j]);
								sb.append("</b></div></td>");
								sb.append("</tr> ");

							} else {

								sb.append("<tr><td  align='center'><div align='center'> <b> ");
								sb.append(formBean.getStrHeader()[j]);
								sb.append("</b></div></td>");
								sb.append("</tr> ");

							}

						}

					}
				sb.append("</table> <br/> ");

				sb.append("<br/><table align='center' cellpadding='1px' cellspacing='1px' border='0' width='"
						+ strTableWidth + "'>");

				sb.append("<tr>");
				sb.append("<td colspan='2' align='center'><div align='center'> <b> ");
				sb.append(strReportName);
				sb.append("</b></div></td>");
				sb.append("</tr>");
				sb.append("</table> <br/>");

			}

			if (isInParamReq.equals("1")) {

				// In Parameter Names and values

				if (strInParamNames != null && strInParamNames.length > 0) {

					sb.append("<div align='center'><table align='center' border='0' cellspacing='0px' cellpadding='0px' height='69' width='"
							+ strTableWidth + "'> ");

					for (int i = 0; i < strInParamNames.length; i = i++) {

						sb.append("<tr>");

						sb.append("<td width='=40%'></td><td width='60%' align='left'><div align='left'>  ");
						sb.append(strInParamNames[i] + " : <b> ");

						if (strCompType[i].equals("2")) {

							sb.append(strInComboVal[comboCount]);

							comboCount++;

						} else {

							sb.append(strInParamValues[i]);

						}

						sb.append(" </b></div></td>");

						/*
						 * if (strInParamNames.length > i + 1) {
						 * 
						 * sb.append(
						 * "<td width='10%'></td><td width='40%' align='left'><div align='left'>  "
						 * ); sb.append(strInParamNames[i + 1] + " : <b> ");
						 * 
						 * if (strCompType[i + 1].equals("2")) {
						 * 
						 * sb.append(strInComboVal[comboCount]);
						 * 
						 * comboCount++;
						 * 
						 * } else {
						 * 
						 * sb.append(strInParamValues[i + 1]);
						 * 
						 * }
						 * 
						 * sb.append(" </b></font></div></td>");
						 * 
						 * } else {
						 * 
						 * sb.append(
						 * "<td width='10%'></td><td width='40%' align='left'><div align='left'> <b> "
						 * ); sb.append(" </font></b></div></td>");
						 * 
						 * }
						 */

						sb.append("</tr>");

					}

					sb.append("</table> </div> <br></br> ");

				}

			}

			if (strIsBorder.equals("1")) {

				sb.append("<table align='center' cellpadding='1px' cellspacing='1px'  style='border-collapse: collapse; border: medium none;' border='1'   width='"
						+ strTableWidth + "'>");

			} else {

				sb.append("<table align='center' cellpadding='1px' cellspacing='1px' border='0' width='"
						+ strTableWidth + "'>");

			}

			// sb.append("<tr><td align='center' colspan='2' ><br/> </td></tr>");

			if (strColumnDisplayName != null)
				for (int j = 0; j < strColumnDisplayName.length; j++) {

					sb.append("<tr>");

					sb.append("<td width='50%' align='" + strAlign[j] + "' >")
							.append(strColumnDisplayName[j]);
					sb.append("</td>");

					sb.append("<td width='50%' align='" + strAlign[j] + "' >");

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

						if (strColumnPrifix[j] != null
								&& strColumnPrifix[j].trim().length() > 0
								&& !strColumnPrifix[j].equals("null")) {
							sb.append(strColumnPrifix[j]).append(" ");
						}

						sb.append(DynamicReportsTransDAO
								.execFormula(strColumnFormula[j]));

						if (strColumnSuffix[j] != null
								&& strColumnSuffix[j].trim().length() > 0
								&& !strColumnSuffix[j].equals("null")) {
							sb.append(" ").append(strColumnSuffix[j])
									.append(" ");
						}

					}

					sb.append("</td>");

					sb.append("</tr>");

				}

			if (isHeaderFooterReq.equals("1")) {
				if (strFooter.trim().length() > 0) {

					sb.append("<tr>");
					sb.append("<td colspan='2' align='center'><div align='center'> <b> ");
					sb.append(strFooter);
					sb.append("</font></b></div></td>");
					sb.append("</tr>");

				}
			}

			sb.append("</table>");

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Export drill down html.
	 * 
	 * @param formBean
	 *            the form bean
	 * @param ws
	 *            the ws
	 * @param isHeaderFooterReq
	 *            the is header footer req
	 * @param isInParamReq
	 *            the is in param req
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String exportDrillDownHTML(DynamicReportsTransFB formBean,
			WebRowSet ws, String isHeaderFooterReq, String isInParamReq)
			throws Exception {

		String strReportName = formBean.getStrReportDisplayName();

		String[] strParamName = formBean.getStrInParamName();
		String[] strXlsColumnName = formBean.getStrOutColDisplayName();
		String strFooter = "Computer Generated Report";
		String[] isGrandTotal = formBean.getStrOutColIsGrandTotal();
		String[] columnIndex = formBean.getStrOutColIndex();
		String[] actColumnIndex = formBean.getStrOutColActualIndex();
		String[] strInParamNames = formBean.getStrInParamDisplayName();
		String[] strInParamValues = formBean.getStrInParamDisplayValue();

		String[] strAlign = formBean.getStrOutColumnAlign();

		String[] strOutColWidth = formBean.getStrOutColWidth();

		// String[] strInComboVal = formBean.getStrInParamComboVal();
		// String[] strCompType = formBean.getStrInParamCompType();

		// String strExportType = formBean.getStrExportType();
		String[] strIsGroupBy = formBean.getStrOutColIsGroupBy();

		String[] strIsHyperLink = formBean.getStrOutColIsHyperLink();

		String strIsBorder = formBean.getStrReportBorderReq();

		String strTableWidth = formBean.getStrReportWidth();

		NumberFormat formatter = new DecimalFormat("############.##");

		String strGroupHeaderString = "";

		String strGroupHeaderStringTemp = "";

		if (formBean.getStrReportWidthUnit().equals("1")) {

			strTableWidth = strTableWidth + "%";

		} else {

			strTableWidth = strTableWidth + "px";

		}

		StringBuffer sb = null;

		StringBuffer sb1 = null;

		String strUnitName[] = null;
		double strUnitTotal[] = null;

		int GrandTotalLen = 0;

		// int comboCount = 0;

		if (isGrandTotal != null && isGrandTotal.length > 0) {

			GrandTotalLen = isGrandTotal.length;

			strUnitName = new String[GrandTotalLen];
			strUnitTotal = new double[GrandTotalLen];

		}

		boolean fUnitFlag = false;

		double[] dTotal = new double[GrandTotalLen];

		/*
		 * Color Codes
		 * 
		 * Header / Footer : #FAC090 Group Header : #FFEBD5 Group Total :
		 * #B3D9FF Alternate Color : #D5E1E6
		 */

		String strHeaderColor = "#FFFFFF";
		String strFooterColor = "#FFFFFF";
		String strGrpHeaderColor = "#FFFFFF";
		String strGrpTotalColor = "#FFFFFF";
		String strAlternateColor = "#FFFFFF";

		String strReportColors = "";

		try {

			strReportColors = DynamicReportsTransDAO.getReportColors(formBean
					.getStrHospitalCode());

			if (strReportColors != null && strReportColors.trim().length() > 0) {

				String[] strColorCodes = strReportColors.replace("^", "@")
						.split("@");

				if (strColorCodes.length == 5) {

					
					
					strHeaderColor = strColorCodes[0];
					strFooterColor = strColorCodes[1];
					strGrpHeaderColor = strColorCodes[2];
					strGrpTotalColor = strColorCodes[3];
					strAlternateColor = strColorCodes[4];

				}

			}

			// ResourceBundle res =
			// ResourceBundle.getBundle("global.rptProperties");

			sb = new StringBuffer("");
			sb1 = new StringBuffer("");

			sb.append("<table width='100%'><tr><td><div id='levelId"
					+ formBean.getnCurrentLevel() + "'>");

			if (formBean.getnCurrentLevel() == 1) {

				sb.append("<div id='id2' class='hidecontrol' align='right'>");
				sb.append("<img src='../../hisglobal/images/printer_symbol.gif' title='Click here to Print the Report' style='cursor: pointer;' id='printId' onclick='printData(\""
						+ formBean.getnCurrentLevel()
						+ "\");' onkeypress='printData(\""
						+ formBean.getnCurrentLevel() + "\");'>");
				sb.append("<img src='../../hisglobal/images/stop.png' title='Click here to Close the Popup' style='cursor: pointer;' onclick='hidepopup();' onkeypress='hidepopup();'>");
				sb.append("</div>");

			} else {

				sb.append("<div id='id2' class='hidecontrol' align='right'>");
				sb.append("<img src='../../hisglobal/images/printer_symbol.gif' title='Click here to Print the Report' style='cursor: pointer;' id='printId' onclick='printData(\""
						+ formBean.getnCurrentLevel()
						+ "\");' onkeypress='printData(\""
						+ formBean.getnCurrentLevel() + "\");'>");
				sb.append("<img src='../../hisglobal/images/arrdouble-left.png' title='Click here to Go Back' style='cursor: pointer;' onclick='backpopup(\""
						+ (formBean.getnCurrentLevel() - 1)
						+ "\");' onkeypress='backpopup(\""
						+ (formBean.getnCurrentLevel() - 1) + "\");'>");
				sb.append("<img src='../../hisglobal/images/stop.png' title='Click here to Close the Popup' style='cursor: pointer;' onclick='hidepopup();' onkeypress='hidepopup();'>");
				sb.append("</div>");

			}

			if (isHeaderFooterReq.equals("1")) {

				sb.append("<table align='center' border='0' cellspacing='0px' cellpadding='0px' height='69' width='"
						+ strTableWidth + "'> ");
				sb.append("<tr><td colspan='"
						+ strParamName.length
						+ "' width='20%' align='right'><div align='right'>Report Date and Time : "
						+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
								.format(new Date()) + "</font></div></td></tr>");
				if (formBean.getStrReportHeaderHtmlContent() != null
						&& !formBean.getStrReportHeaderHtmlContent().trim()
								.equals("")
						&& formBean.getStrReportHeaderHtmlContent().trim()
								.length() != 0) {

					sb.append("<tr><td colspan='" + strParamName.length + "'> "
							+ formBean.getStrReportHeaderHtmlContent()
							+ " </td>");
					sb.append("</tr> ");

				}

				if (formBean.getStrHeader() != null)
					for (int j = 0; j < formBean.getStrHeader().length; j++) {

						if (formBean.getStrHeader()[j] != null
								&& !formBean.getStrHeader()[j].trim()
										.equals("")
								&& formBean.getStrHeader()[j].trim().length() > 0) {

							if (j == 0) {

								sb.append("<tr><td colspan='"
										+ strParamName.length
										+ "'  align='center'><div align='center'> <b> ");
								sb.append(formBean.getStrHeader()[j]);
								sb.append("</font></b></div></td>");
								sb.append("</tr> ");

							} else {

								sb.append("<tr><td colspan='" 
										+ strParamName.length
										+ "'  align='center'><div align='center'> <b> ");
								sb.append(formBean.getStrHeader()[j]);
								sb.append("</font></b></div></td>");
								sb.append("</tr> ");

							}

						}

					}

				sb.append("</table> <br/> ");

				sb.append("<br/><table align='center' cellpadding='1px' cellspacing='1px' border='0' width='"
						+ strTableWidth + "'>");

				sb.append("<tr>");
				sb.append("<td colspan='"
						+ strParamName.length
						+ "' align='center'><div align='center'> <b> ");
				sb.append(strReportName);
				sb.append("</font></b></div></td>");
				sb.append("</tr>");
				sb.append("</table> <br/>");

			}

			if (isInParamReq.equals("1")) {

				// In Parameter Names and values

				if (strInParamNames != null && strInParamNames.length > 0) {

					sb.append("<div align='center'><table align='center' border='0' cellspacing='0px' cellpadding='0px' height='69' width='"
							+ strTableWidth + "'> ");

					for (int i = 0; i < strInParamNames.length; i++) {

						sb.append("<tr>");

						sb.append("<td width='40%'></td><td width='60%' align='left'><div align='left'>  ");
						sb.append(strInParamNames[i]
								+ " : <b><input type='hidden' name='strInParamDisplayName"
								+ formBean.getnCurrentLevel() + "' value='"
								+ strInParamNames[i] + "' ></input>");

					

						sb.append(strInParamValues[i]).append(
								"<input type='hidden' name='strInParamDisplayValue"
										+ formBean.getnCurrentLevel()
										+ "' value='" + strInParamValues[i]
										+ "' ></input>");
						;

						// }

						sb.append(" </b></font></div></td>");

						

						sb.append("</tr>");

					}

					sb.append("</table> </div> <br></br> ");

				}

			}

			int grpLen = 0;
			int actualLen = strXlsColumnName.length;

			if (strIsGroupBy != null && strIsGroupBy.length > 0) {

				for (int j = 0; j < strIsGroupBy.length; j++) {

					if (strIsGroupBy[j].equals("1")) {

						grpLen++;

					}

				}

			}

			if (grpLen > 0) {

				actualLen = strXlsColumnName.length - grpLen;

				if (strIsBorder.equals("1")) {

					sb.append("<table align='center' cellpadding='1px' cellspacing='1px'  style='border-collapse: collapse; border: medium none;' border='1'   width='"
							+ strTableWidth + "'>");

				} else {

					sb.append("<table align='center' cellpadding='1px' cellspacing='1px' border='0' width='"
							+ strTableWidth + "'>");

				}

				/*
				 * sb.append("<tr><td align='center' colspan='" + actualLen +
				 * "' ><br/> </td></tr>");
				 */
				sb.append("<tr>");
				sb.append("<td colspan='"
						+ actualLen
						+ "' align='center' ></td>");
				sb.append("</tr>");

				sb.append("<tr>");

				for (int i = grpLen; i < strXlsColumnName.length; i++) {

					sb.append(
							"<td   class='" + strHeaderColor + "'  align='center'  width='"
									+ strOutColWidth[i]
									+ "%'  ><b>  ")
							.append(strXlsColumnName[Integer
									.parseInt(columnIndex[i]) - 1])
							.append("</font></b></td>");

				}

			} else {

				if (strIsBorder.equals("1")) {

					sb.append("<table align='center' cellpadding='1px' cellspacing='1px'  style='border-collapse: collapse; border: medium none;' border='1'   width='"
							+ strTableWidth + "'>");

				} else {

					sb.append("<table align='center' cellpadding='1px' cellspacing='1px' border='0' width='"
							+ strTableWidth + "'>");

				}

				
				sb.append("<tr>");

				for (int i = 0; i < strXlsColumnName.length; i++) {

					sb.append(
							"<td  class='" + strHeaderColor + "'  align='center' width='"
									+ strOutColWidth[i]
									+ "%' ><b>  ")
							.append(strXlsColumnName[Integer
									.parseInt(columnIndex[i]) - 1])
							.append("</font></b></td>");

				}
			}

			sb.append("</tr>");

			

			int count = 0;

			int rowCount = 0;

			int colCount = 0;

			String strLastColumnName = "";

			String strOutPramValuesHidden = "";

			if (ws != null && ws.size() > 0) {

				colCount = ws.getMetaData().getColumnCount();

				strLastColumnName = ws.getMetaData().getColumnName(colCount);

				String tempColumnName[] = new String[grpLen];

				boolean tempFlag[] = new boolean[grpLen];

				boolean tempGrpTotal[] = new boolean[grpLen];

				double[] grpTotalValues = new double[colCount];

				String sbTemp = "";

				for (int m = 0; m < tempColumnName.length; m++) {
					tempColumnName[m] = "";

					tempFlag[m] = false;
					tempGrpTotal[m] = false;

				}

				while (ws.next()) {

					rowCount++;

					if (formBean.getStrReportTypeId().equals("3"))
						for (int i = 1; i <= colCount; i++) {

							if (i == 1) {

								strOutPramValuesHidden = ws.getString(i);

							} else {

								strOutPramValuesHidden = strOutPramValuesHidden
										+ "@@@" + ws.getString(i);

							}

						}

					if (grpLen > 0) {

						String[] strParamHeading = new String[grpLen];
						String[] strParamValues = new String[grpLen];

						for (int k = 0; k < grpLen; k++) {

							String strParamValue = ws.getString(Integer
									.parseInt(actColumnIndex[k]));

							if (strParamValue == null)
								strParamValue = "NA";

							if (strParamValue != null
									&& strParamValue.trim().length() == 0)
								strParamValue = "NA";

							strParamHeading[k] = strXlsColumnName[Integer
									.parseInt(columnIndex[k]) - 1];
							strParamValues[k] = strParamValue;

							if (!strParamValue.trim().equalsIgnoreCase(
									tempColumnName[k])) {

								tempFlag[k] = true;
								tempColumnName[k] = strParamValue;

							} else {
								tempFlag[k] = false;
							}

							if (isGrandTotal[k].equals("1")) {

								tempGrpTotal[k] = true;

							} else {

								tempGrpTotal[k] = false;

							}

						}

						for (int k = 0; k < grpLen; k++) {

							if (tempFlag[k] == true) {

								if (tempGrpTotal[k] == true)
									if (count != 0) {

										sb.append("<tr bgcolor='"
												+ strGrpTotalColor + "'>");

										for (int i = grpLen; i < isGrandTotal.length; i++) {

											if (isGrandTotal[i].equals("1")
													&& grpTotalValues[i] > 0) {

												sb.append("<td align='"
														+ strAlign[i]
														+ "'><b>");

												if (grpTotalValues[i] == 0) {

													sb.append(grpTotalValues[i]);

												} else {

													sb.append(formatter
															.format(new BigDecimal(
																	String.valueOf(grpTotalValues[i]))));

												}

												if (strUnitName[i] != null) {

													sb.append(" ").append(
															strUnitName[i]);

												}

												sb.append("</font></b></td>");

												grpTotalValues[i] = 0;

											} else {

												sb.append("<td width='"
														+ strOutColWidth[i]
														+ "%' align='"
														+ strAlign[i]
														+ "'> </td>");
											}

										}
										sb.append("</tr>");

									} else {

										count = count + 1;

									}

								tempFlag[k] = false;

							}

						}

						strGroupHeaderString = getGroupByHeaders(
								strParamHeading, strParamValues);

						if (!strGroupHeaderString.trim().equals(
								strGroupHeaderStringTemp)) {

							sbTemp = "<tr bgcolor='" + strGrpHeaderColor
									+ "'><td align='left' colspan='"
									+ actualLen + "' >";

							if (count != 0)
								sbTemp = sbTemp
										+ "<div style='page-break-before: always'></div>";

							sbTemp = sbTemp
									+ "<b>"
									+ strGroupHeaderString
									+ "</font></b></td></tr>";

							sb.append(sbTemp);

							sbTemp = "";

						}

						strGroupHeaderStringTemp = strGroupHeaderString;

					}

					if (strLastColumnName.equals("COLOR_VAL")) {

						String strColor = ws.getString(colCount);

						if (strColor == null || strColor.trim().length() == 0) {

							if (rowCount % 2 == 0) {

								strColor = "white";

							} else {

								strColor = strAlternateColor;

							}

						}

						sb.append("<tr bgcolor='" + strColor + "'>");

					} else {

						if (rowCount % 2 == 0) {

							sb.append("<tr>");

						} else {

							sb.append("<tr bgcolor='" + strAlternateColor
									+ "'>");

						}

					}

					for (int i = grpLen; i < strXlsColumnName.length; i++) {

						String strParamValue = ws.getString(Integer
								.parseInt(actColumnIndex[i]));

						if (strParamValue != null
								&& NumberUtils.isNumber(strParamValue.trim()
										.replace(" ", "#").split("#")[0])) {

							sb.append("<td align='"
									+ strAlign[i]
									+ "' width='"
									+ strOutColWidth[i]
									+ "%' >");

							if (strIsHyperLink != null
									&& strIsHyperLink[i] != null
									&& strIsHyperLink[i].equals("1")) {

								sb.append("<div style='color:blue;cursor:pointer;' onclick='getNextLevelReport(\""
										+ rowCount
										+ "\" , \""
										+ formBean.getnCurrentLevel()
										+ "\" , \""
										+ strXlsColumnName[i]
										+ "^"
										+ strParamValue
										+ "\" );' >"
										+ strParamValue + "</div>");

							} else {
								sb.append(strParamValue);
							}

							sb.append("</font></td>");

						} else {

							sb.append("<td width='"
									+ strOutColWidth[i]
									+ "%' align='"
									+ strAlign[i]
									+ "' >     ");

							if (strIsHyperLink != null
									&& strIsHyperLink[i] != null
									&& strIsHyperLink[i].equals("1")) {

								sb.append("<div style='color:blue;cursor:pointer;' onclick='getNextLevelReport(\""
										+ rowCount
										+ "\" , \""
										+ formBean.getnCurrentLevel()
										+ "\" , \""
										+ strXlsColumnName[i]
										+ "^"
										+ strParamValue
										+ "\" );' >"
										+ strParamValue + "</div>");

							} else {
								sb.append(strParamValue);
							}

							sb.append("</font></td>");

						}

						// Whether total is required
						if (Integer.valueOf(isGrandTotal[i]) == 1) {

							// whether value is numeric
							if (NumberUtils.isNumber(strParamValue)) {
								// if value is numeric find total
								dTotal[i] = dTotal[i]
										+ Double.parseDouble(strParamValue);

								grpTotalValues[i] = grpTotalValues[i]
										+ Double.parseDouble(strParamValue);

							} else {
								// if value is not numeric set the total as -1

								dTotal[i] = -1;

								grpTotalValues[i] = -1;

								if (strParamValue.trim().replace(" ", "#")
										.contains("#")) {

									String[] strVals = strParamValue.trim()
											.replace(" ", "#").split("#");

									if (strVals.length == 2) {

										if (NumberUtils.isNumber(strVals[0])
												&& !NumberUtils
														.isNumber(strVals[1])) {

											if (!fUnitFlag)
												strUnitName[i] = strVals[1];

											if (strUnitName[i]
													.equalsIgnoreCase(strVals[1])) {

												strUnitTotal[i] = strUnitTotal[i]
														+ Float.parseFloat(strVals[0]);

												dTotal[i] = strUnitTotal[i];

												grpTotalValues[i] = strUnitTotal[i];

											} else {

												strUnitName[i] = "";
											}

										}

									}

								}

							}

						} else {
							// if total is not required set the value as -1
							dTotal[i] = -1;

						}

					}

					sb1.append("<input type='hidden' id='strOutPramValuesHidden"
							+ formBean.getnCurrentLevel()
							+ ""
							+ rowCount
							+ "' name='strOutPramValuesHidden' value='"
							+ strOutPramValuesHidden.replace("#", "^") + "' />");

					sb.append("</tr>");

					fUnitFlag = true;

				}

				for (int k = 0; k < grpLen; k++) {

					if (tempGrpTotal[k] == true) {

						sb.append("<tr>");

						for (int i = grpLen; i < isGrandTotal.length; i++) {

							if (isGrandTotal[i].equals("1")
									&& grpTotalValues[i] > 0) {

								sb.append("<td align='"
										+ strAlign[i]
										+ "'><b>");

								if (grpTotalValues[i] == 0) {

									sb.append(grpTotalValues[i]);

								} else {

									sb.append(formatter.format(new BigDecimal(
											String.valueOf(grpTotalValues[i]))));

								}

								if (strUnitName[i] != null) {

									sb.append(" ").append(strUnitName[i]);

								}

								sb.append("</font></b></td>");

								grpTotalValues[i] = 0;

							} else {

								sb.append("<td width='" + strOutColWidth[i]
										+ "%' align='" + strAlign[i]
										+ "'> </td>");
							}

						}
						sb.append("</tr>");

					}
				}

				sb.append("<tr>");
				sb.append("<td colspan='"
						+ actualLen
						+ "' align='center' >"
						+ sb1.toString()
						+ "<input type='hidden' name='strInParamHiddenValues' id='strInParamHiddenValues"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrInParamHiddenValues()
						+ "' /><input type='hidden' name='strInParamIdHiddenValues' id='strInParamIdHiddenValues"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrInParamIdHiddenValues()
						+ "' /><input type='hidden' name='strTemplateProcName' id='strTemplateProcName"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrTemplateProcName()[formBean
								.getnCurrentLevel()]
						+ "'  /><input type='hidden' name='strTemplateProcDisplayName' id='strTemplateProcDisplayName"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrTemplateProcDisplayName()[formBean
								.getnCurrentLevel()]
						+ "'  /><input type='hidden' name='strTemplateProcId' id='strTemplateProcId"
						+ formBean.getnCurrentLevel()
						+ "' value='"
						+ formBean.getStrTemplateProcId()[formBean
								.getnCurrentLevel()]
						+ "' /><input type='hidden' name='strReportTypeId'  id='strReportTypeId'   value='"
						+ formBean.getStrReportTypeId()
						+ "' /><input type='hidden' name='strReportTemplateId' id='strReportTemplateId'  value='"
						+ formBean.getStrReportTemplateId()
						+ "' />  <input type='hidden' name='strReportWidth' id='strReportWidth' value='"
						+ formBean.getStrReportWidth()
						+ "' /> <input type='hidden' id='strReportWidthUnit' name='strReportWidthUnit' value = '"
						+ formBean.getStrReportWidthUnit()
						+ "'/> <input type='hidden' id='strReportBorderReq' name='strReportBorderReq'  value = '"
						+ formBean.getStrReportBorderReq() + "'/> </td>");
				sb.append("</tr>");

				StringBuffer grandTotalInWordsRow = new StringBuffer("");

				HisUtil hisUtil = new HisUtil("Dynamic Reports",
						"DynamicReportsTransHLP");

				sb.append("<tr>");

				grandTotalInWordsRow.append("<tr >");

				boolean flag = false;

				for (int i = grpLen; i < isGrandTotal.length; i++) {

					if (isGrandTotal[i].equals("1")) {

						sb.append("<td  class='" + strFooterColor + "' align='"
								+ strAlign[i]
								+ "'><b>");
						grandTotalInWordsRow
								.append("<td class='" + strFooterColor + "' align='"
										+ strAlign[i]
										+ "'>");

						if (dTotal[i] == 0) {

							sb.append(dTotal[i]);

						} else {

							sb.append(formatter.format(new BigDecimal(String
									.valueOf(dTotal[i]))));

						}

						if (strUnitName[i] != null) {

							sb.append(" ").append(strUnitName[i]);

						} else {

							grandTotalInWordsRow.append(hisUtil
									.toInitCap(hisUtil.getAmountStr(formatter
											.format(new BigDecimal(String
													.valueOf(dTotal[i]))))));

						}

						sb.append("</font></b></td>");

						grandTotalInWordsRow.append("</font></td>");

						flag = true;

					} else {

						sb.append("<td class='" + strFooterColor + "' width='" + strOutColWidth[i]
								+ "%' align='" + strAlign[i] + "'> </td>");

						grandTotalInWordsRow.append("<td class='" + strFooterColor + "' width='"
								+ strOutColWidth[i] + "%' align='"
								+ strAlign[i] + "'> </td>");

					}

				}
				sb.append("</tr>");
				sb.append(grandTotalInWordsRow.append("</tr>"));

				if (flag) {

					

				}

			} else {
				sb.append("<tr>");
				sb.append("<td class='" + strFooterColor + "' colspan='"
						+ actualLen
						+ "' ><div align='center'><b><center>No Record(s) Available</center></b></div><br/></td>");
				sb.append("</tr>");
			}

			if (isHeaderFooterReq.equals("1")) {

				if (strFooter.trim().length() > 0) {

					sb.append("<tr>");
					sb.append("<td colspan='"
							+ actualLen
							+ "' align='center'><div align='center'> <b> ");
					sb.append(strFooter);
					sb.append("</font></b></div></td>");
					sb.append("</tr>");

				}

			}

			sb.append("</table><br><br>");

			

			sb.append("</div></td></tr></table>");

		} catch (Exception e) {

			e.printStackTrace();

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the no data screen.
	 * 
	 * @param formBean
	 *            the form bean
	 * @return the no data screen
	 */
	public static String getNoDataScreen(DynamicReportsTransFB formBean) {
		StringBuffer br = new StringBuffer();
		String strTableWidth = formBean.getStrReportWidth();
		if (formBean.getStrReportWidthUnit().equals("1")) {

			strTableWidth = strTableWidth + "%";

		} else {

			strTableWidth = strTableWidth + "px";

		}

		br.append("<table align='center' cellpadding='1px' cellspacing='1px'  style='border-collapse: collapse; border: medium none;' border='1'   width='"
				+ strTableWidth + "'>");
		br.append("<tr>");
		br.append("<td class='multiControl' style=\"text-align:center;\"><font color='red'><b>No Detail(s) Available</b></font></td></tr>");
		br.append("<img src='../../hisglobal/images/stop.png' title='Click here to Close the Popup' style='cursor: pointer;' onclick='hidepopup();' onkeypress='hidepopup();'>");
		br.append("</table>");

		return br.toString();
	}

}
