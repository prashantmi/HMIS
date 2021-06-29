/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstHLP.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.hlp;

import java.sql.ResultSetMetaData;

import javax.sql.rowset.WebRowSet;

import dynamicreports.masters.controller.fb.DynamicReportParamMstFB;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstHLP.
 */
public class DynamicReportParamMstHLP {

	/**
	 * Gets the proc parameter list.
	 * 
	 * @param ws
	 *            the ws
	 * @return the proc parameter list
	 * @throws Exception
	 *             the exception
	 */
	public static String getProcParameterList(WebRowSet ws) throws Exception {

		StringBuffer sb = new StringBuffer("");
		StringBuffer sb1 = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='4'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='30%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'><font color='red'>*</font>Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'><font color='red'>*</font>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Type");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {
				while (ws.next()) {

					String strColumnName = ws.getString(1);

					count = count + 1;

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='30%' >");

						sb.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='25%' >");
						sb.append(
								"<input type='text' name='strInDisplayName' id='strInDisplayNameDivId")
								.append(count)
								.append("'  class='txtFldMax' maxlength='250' value='' onkeypress='return validateData(event,18);' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >");
						sb.append(
								"<input type='text' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='0' maxlength='25'   onkeypress='return validateData(event,8);' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='25%' >");
						sb.append(
								"<select name='strParamCompType' id='strParamCompType")
								.append(count)
								.append("' class='comboNormal' onChange='getQuery(this , \"")
								.append(count)
								.append("\");'><option value='0'>Fixed</option><option value='1'>Text Box</option><option value='2'>Combo Box</option><option value='3'>Date Picker</option></select>");
						sb.append(
								"<input type='hidden' name='strComboQuery' id='strComboQuery")
								.append(count).append("' value=\"\"></td>");
						sb.append("</tr>");

					} else {

						sb1.append("<div style='display:none;'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb1.append("<tr>");
						sb1.append("<td class='multiControl' width='30%' >");

						sb1.append(
								"<input type='hidden' name='strProcInColumnDtls' value='")
								.append(strColumnName).append("' >");
						sb1.append(strColumnName);

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='25%' >");
						sb1.append(
								"<input type='hidden' name='strInDisplayName' id='strInDisplayNameDivId")
								.append(count)
								.append("'   value='" + strColumnName + "'  >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >");
						sb1.append(
								"<input type='hidden' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count).append("'   value='0'  >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='25%' >");
						sb1.append(
								"<input type='hidden' name='strParamCompType' id='strParamCompType")
								.append(count).append("' value='0' >");
						sb1.append(
								"<input type='hidden' name='strComboQuery' id='strComboQuery")
								.append(count)
								.append("' value=\"\"> <input type='hidden' name='strIsGroupOrderBy' value='1'>  </td>");
						sb1.append("</tr>");
						sb1.append("</table></div>");

					}

				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='4'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

			sb.append(sb1.toString());

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the proc parameter list view.
	 * 
	 * @param formBean
	 *            the form bean
	 * @return the proc parameter list view
	 * @throws Exception
	 *             the exception
	 */
	public static String getProcParameterListView(
			DynamicReportParamMstFB formBean) throws Exception {

		String strProcInColumnDtls[] = formBean.getStrProcInColumnDtls();
		String strInConstantValue[] = formBean.getStrInConstantValue();
		String strParamCompType[] = formBean.getStrParamCompType();
		String strComboQuery[] = formBean.getStrComboQuery();
		String strInDisplayName[] = formBean.getStrInDisplayName();

		StringBuffer sb = new StringBuffer("");
		StringBuffer sb1 = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='4'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='30%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Type");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (strProcInColumnDtls != null && strProcInColumnDtls.length > 0) {
				for (int i = 0; i < strProcInColumnDtls.length; i++) {

					String strColumnName = strProcInColumnDtls[i];

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='30%' >");

						sb.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='25%' >")
								.append(strInDisplayName[i]);
						sb.append(
								"<input type='hidden' name='strInDisplayName' value='")
								.append(strInDisplayName[i]).append("'>");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >")
								.append(strInConstantValue[i]);
						sb.append(
								"<input type='hidden' name='strInConstantValue' ")
								.append("' value='")
								.append(strInConstantValue[i]).append("' >");
						sb.append("</td>");

						String strParamTypeName = "";

						if (strParamCompType[i].equals("0")) {

							strParamTypeName = "Fixed";

						} else if (strParamCompType[i].equals("1")) {
							strParamTypeName = "Text Box";
						} else if (strParamCompType[i].equals("2")) {

							strParamTypeName = "Combo Box";

							if (strComboQuery[i].trim().length() > 0) {

								strParamTypeName = "<a href='#' title='"
										+ strComboQuery[i] + "' >"
										+ strParamTypeName + "</a>";

							}

						} else {
							strParamTypeName = "Date Picker";
						}

						sb.append("<td class='multiControl' width='25%' >")
								.append(strParamTypeName);

						sb.append(
								"<input type='hidden' name='strParamCompType' ")
								.append("' value='")
								.append(strParamCompType[i]).append("' >");
						sb.append(
								"<input type='hidden' name='strComboQuery' id='strComboQuery")
								.append(count)
								.append("' value=\"" + strComboQuery[i]
										+ "\"></td>");
						sb.append("</tr>");

					} else {

						sb1.append("<div style='display:none;'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb1.append("<tr>");
						sb1.append("<td class='multiControl' width='30%' >");

						sb1.append(
								"<input type='hidden' name='strProcInColumnDtls' value='")
								.append(strColumnName).append("' >");
						sb1.append(strColumnName);

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='25%' >");
						sb1.append(
								"<input type='hidden' name='strInDisplayName' id='strInDisplayNameDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='"
										+ strColumnName
										+ "' onkeypress='return validateData(event,17);' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >");
						sb1.append(
								"<input type='hidden' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='0'   onkeypress='return validateData(event,9);' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='25%' >");
						sb1.append(
								"<input type='hidden' name='strParamCompType' ")
								.append("' value='")
								.append(strParamCompType[i]).append("' >");
						sb1.append(
								"<input type='hidden' name='strComboQuery' id='strComboQuery")
								.append(count).append("' value=\"\"> </td>");
						sb1.append("</tr>");
						sb1.append("</table></div>");

					}

				}

			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='4'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

			sb.append(sb1.toString());

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the view in parameter.
	 * 
	 * @param ws
	 *            the ws
	 * @return the view in parameter
	 * @throws Exception
	 *             the exception
	 */
	public static String getViewInParameter(WebRowSet ws) throws Exception {

		StringBuffer sb = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' width='700px' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='4'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='30%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Type");
			sb.append("</td> ");
			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {
				while (ws.next()) {

					String strColumnName = ws.getString(4);

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='30%' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='25%' >")
								.append(ws.getString(1));
						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >")
								.append(ws.getString(2));
						sb.append("</td>");

						String strParamTypeName = "";

						String strParamCompType = ws.getString(3);

						if (strParamCompType.equals("0")) {

							strParamTypeName = "Fixed";

						} else if (strParamCompType.equals("1")) {
							strParamTypeName = "Text Box";
						} else if (strParamCompType.equals("2")) {

							strParamTypeName = "Combo Box";

							if (ws.getString(5) != null
									&& ws.getString(3).trim().length() > 0) {

								strParamTypeName = "<a style='color:blue;cursor: pointer;' title='"
										+ ws.getString(5)
										+ "' >"
										+ strParamTypeName + "</a>";

							}

						} else {
							strParamTypeName = "Date Picker";
						}

						sb.append("<td class='multiControl' width='25%' >")
								.append(strParamTypeName);

						sb.append("</td>");
						sb.append("</tr>");

					}
				}

			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='4'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the view out param values.
	 * 
	 * @param ws
	 *            the ws
	 * @return the view out param values
	 * @throws Exception
	 *             the exception
	 */
	public static String getViewOutParamValues(WebRowSet ws) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {

			sb.append("<table border='0' width='700px' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='10'>Report Column Details ");
			sb.append("</td> ");
			sb.append("</tr> ");

			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='20%'>Column Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'>Index ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'>Actual Index ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='15%'>Column Width ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Hyperlink ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Total ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Group By ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Order By ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='15%'> Align ");
			sb.append("</td> ");
			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {

				while (ws.next()) {

					sb.append("<tr> ");
					sb.append("<td class='multiControl' width='20%'>").append(
							ws.getString(1));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='20%'>").append(
							ws.getString(2));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							ws.getString(3));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							ws.getString(4));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='15%'>").append(
							ws.getString(5));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							ws.getString(6));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							ws.getString(7));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							ws.getString(8));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							ws.getString(9));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='15%'>").append(
							ws.getString(10));
					sb.append("</td> ");
					sb.append("</tr> ");

				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='10'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the view col param values.
	 * 
	 * @param ws
	 *            the ws
	 * @return the view col param values
	 * @throws Exception
	 *             the exception
	 */
	public static String getViewColParamValues(WebRowSet ws) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {

			sb.append("<table border='0' width='700px' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='5'>Report Column Details ");
			sb.append("</td> ");
			sb.append("</tr> ");

			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='30%'>Column Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Prifix ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Formula ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Suffix ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='15%'>Align ");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {

				while (ws.next()) {

					sb.append("<tr> ");
					sb.append("<td class='multiControl' width='30%'>").append(
							ws.getString(1));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='20%'>").append(
							ws.getString(2));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='25%'>").append(
							ws.getString(3));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='20%'>").append(
							ws.getString(4));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='15%'>").append(
							ws.getString(5));
					sb.append("</td> ");

					sb.append("</tr> ");

				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='5'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the proc column list view.
	 * 
	 * @param ws
	 *            the ws
	 * @return the proc column list view
	 * @throws Exception
	 *             the exception
	 */
	public static String getProcColumnListView(WebRowSet ws) throws Exception {

		ResultSetMetaData rsmd = ws.getMetaData();

		StringBuffer sb = new StringBuffer("");

		int colCount = rsmd.getColumnCount();

		int colWidth = (int) Math.floor((100 / colCount));

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='9'>Report Column Details ");
			sb.append("</td> ");
			sb.append("</tr> ");

			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='5%'>Required ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Column Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'><font color='red'>*</font>Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'><font color='red'>*</font>Index ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='15%'><font color='red'>*</font>Column Width(%) ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Total ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Group By ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Order By ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='10%'> Align ");
			sb.append("</td> ");
			sb.append("</tr> ");

			if (colCount > 0) {

				for (int i = 1; i <= colCount; i++) {

					String strColumnName = rsmd.getColumnName(i);

					if (!strColumnName.trim().equalsIgnoreCase("COLOR_VAL")) {

						sb.append("<tr> ");
						sb.append("<td class='multiControl' width='5%'> ");

						sb.append("<div id='reqDivId"
								+ i
								+ "' ><input type='checkbox' name='strColumnRequired' value='1'  id='strColumnRequired"
								+ i
								+ "'  checked='checked' onclick='enableValues(this, \""
								+ i
								+ "\" );' ></div><div id='tickIconDivId"
								+ i
								+ "' style='display:none;'><img src='../../hisglobal/images/check.png'></div>");

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='20%'> ");
						sb.append(
								"<input type='hidden' name='strOutColumnName' id='strOutColumnName"
										+ i + "' value='")
								.append(strColumnName).append("' >");
						sb.append(strColumnName);

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='30%'> ");
						sb.append("<input type='text' name='strOutColumnDisplayName' maxlength='250' id='strOutColumnDisplayName"
								+ i
								+ "'  class='txtFldMax'   onkeypress='return validateData(event,17);' >");
						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'> ");

						sb.append(
								"<input type='hidden' name='strOutColumnActualIndex' id='strOutColumnActualIndex"
										+ i + "' value='").append(i)
								.append("' >");

						sb.append("<div id='colIndexDivId" + i + "' >");
						sb.append("<input type='text' name='strOutColumnIndex' maxlength='2' id='strOutColumnIndex"
								+ i
								+ "' value='"
								+ i
								+ "'  class='txtFldMin'   onkeypress='return validateData(event,5);' >");

						sb.append("</div><div id='colDisplayDivId" + i
								+ "' style='display:none;'>" + i + "</div>");

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='20%'> ");

						sb.append("<input type='text' value='"
								+ colWidth
								+ "' name='strOutColumnWidth' maxlength='3' id='strOutColumnWidth"
								+ i
								+ "'  class='txtFldMin'   onkeypress='return validateData(event,5);' >");

						sb.append("</td> ");

						sb.append("<td class='multiControl' width='5%'> ");
						sb.append("<input type='checkbox' name='strTotalRequired' id='strTotalRequired"
								+ i
								+ "' value='1' onclick='hiddenCheck(this , \"strTotalRequiredValue"
								+ i + "\" );' >");
						sb.append("<input type='hidden' name='strTotalRequiredValue' id='strTotalRequiredValue"
								+ i + "' value='0' >");
						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'>");
						sb.append("<input type='checkbox' name='strGroupByRequired' id='strGroupByRequired"
								+ i
								+ "' value='1' onclick='changeGroupByStatus(this , \""
								+ i + "\");' >");

						sb.append("<input type='hidden' name='strGroupByRequiredValue' id='strGroupByRequiredValue"
								+ i + "' value='0' >");

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'> ");
						sb.append("<input type='checkbox' name='strOrderByRequired' id='strOrderByRequired"
								+ i
								+ "' value='1' onclick='changeOrderByStatus(this , \""
								+ i
								+ "\"),hiddenCheck(this , \"strOrderByRequiredValue"
								+ i + "\" );' >");

						sb.append("<input type='hidden' name='strOrderByRequiredValue' id='strOrderByRequiredValue"
								+ i + "' value='0' >");

						sb.append("<input type='hidden' name='strGrpReqStatus' id='strGrpReqStatus"
								+ i + "' value='1' >");

						sb.append("</td> ");

						sb.append("<td class='multiControl' width='10%'> ");
						sb.append("<select name='strOutColumnAlign' id='strOutColumnAlign"
								+ i
								+ "' class='comboSmall' ><option selected='selected' value='left'>Left</option><option value='center'>Center</option><option value='right'>Right</option></select> ");
						sb.append("</td> ");
						sb.append("</tr> ");

					}
				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='8'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='9'>");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("</table>");

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the col based proc column list view.
	 * 
	 * @param ws
	 *            the ws
	 * @return the col based proc column list view
	 * @throws Exception
	 *             the exception
	 */
	public static String getColBasedProcColumnListView(WebRowSet ws)
			throws Exception {

		ResultSetMetaData rsmd = ws.getMetaData();

		StringBuffer sb = new StringBuffer("");

		int colCount = rsmd.getColumnCount();

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");

			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='3'>Report Column Details ");
			sb.append("</td> ");
			sb.append("</tr> ");

			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='50%'>Column Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Index ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='25%'>Formula Component Name");
			sb.append("</td> ");
			sb.append("</tr> ");

			if (colCount > 0) {

				for (int i = 1; i <= colCount; i++) {

					String strColumnName = rsmd.getColumnName(i);

					sb.append("<tr> ");
					sb.append("<td class='multiControl' width='50%'>");
					sb.append(
							"<input type='hidden' name='strOutColumnName' id='strOutColumnName"
									+ i + "' value='").append(strColumnName)
							.append("' >");
					sb.append(strColumnName);
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='25%'>");

					sb.append(
							"<input type='hidden' name='strOutColumnActualIndex' id='strOutColumnActualIndex"
									+ i + "' value='").append(i).append("' >")
							.append(i);

					sb.append("</td> ");
					sb.append("<td class='multiControl' width='25%'>");

					sb.append(
							"<input type='hidden' name='strOutColumnDisplayName' maxlength='250' id='strOutColumnDisplayName"
									+ i + "'  value='")
							.append(getAlphaFromNumbers(i)).append("' >");

					sb.append("#" + getAlphaFromNumbers(i) + "#");
					sb.append("</td> ");
					sb.append("</tr> ");

				}

			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='8'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

		} catch (Exception e) {

			throw e;

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
	 * Gets the pre rpt dtls view.
	 * 
	 * @param ws
	 *            the ws
	 * @return the pre rpt dtls view
	 * @throws Exception
	 *             the exception
	 */
	public static String getPreRptDtlsView(WebRowSet ws) throws Exception {

		StringBuffer sb = new StringBuffer("");
		String isLast = "0";
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='6'>Report Details ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='5%'>Level ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'>Report Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'>Procedure Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='10%'>Report Type ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='10%'>Combo Dependent ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='15%'>Action");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {

				int size = ws.size();

				while (ws.next()) {

					count = count + 1;

					sb.append("<tr> ");
					sb.append("<td class='multiControl' width='5%'>").append(
							count);
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='30%'>").append(
							ws.getString(1));
					sb.append("</td> ");
					sb.append("<td class='multiControl' width='30%'>").append(
							ws.getString(2));
					sb.append("</td> ");

					sb.append("<td class='multiControl' width='10%'>");

					if (ws.getString(4).equals("1")) {

						sb.append("Column Based");

					} else {
						sb.append("Row Based");
					}

					sb.append("</td> ");

					sb.append("<td class='multiControl' width='10%'>");

					if (ws.getString(7).equals("1")) {

						sb.append("Yes");

					} else {
						sb.append("No");
					}

					sb.append("</td> ");

					sb.append("<td class='multiControl' width='15%'>");

					sb.append("<a onclick='viewReport(\""
							+ ws.getString(5)
							+ "\" , \""
							+ ws.getString(6)
							+ "\", \""
							+ ws.getString(1)
							+ "\"  , \""
							+ ws.getString(4)
							+ "\");' style='cursor: pointer;color:blue'>View</a>");

					if (count == size) {
						sb.append(" | <a onclick='cancelReport(\""
								+ ws.getString(5)
								+ "\" , \""
								+ ws.getString(6)
								+ "\");' style='cursor: pointer;color:blue'>Cancel</a> ");
					}

					sb.append("</td> ");

					sb.append("</tr> ");

					if (!isLast.equals("1") && ws.getString(3).equals("1")) {

						isLast = "1";

					}

				}

				sb.append("<tr class='TITLE'> ");
				sb.append("<td colspan='6'>");
				sb.append("</td> ");
				sb.append("</tr> ");

			} else {

				return "";

			}

			sb.append("</table>");

		} catch (Exception e) {

			throw e;

		}

		return sb.append("@@").append(isLast).toString();

	}

	/**
	 * Gets the merge pre proc parameter list.
	 * 
	 * @param ws
	 *            the ws
	 * @param comboValue
	 *            the combo value
	 * @return the merge pre proc parameter list
	 * @throws Exception
	 *             the exception
	 */
	public static String getMergePreProcParameterList(WebRowSet ws,
			String comboValue) throws Exception {

		StringBuffer sb = new StringBuffer("");
		StringBuffer sb1 = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='3'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='40%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'><font color='red'>*</font>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'>Mapping Parameters");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {
				while (ws.next()) {

					String strColumnName = ws.getString(1);

					count = count + 1;

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='40%' >");

						sb.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='30%' >");
						sb.append(
								"<input type='text' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='0' maxlength='25'   onkeypress='return validateData(event,8);' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='30%' >");
						sb.append("<select name='strPreInParamValues' id='strPreInParamValues"
								+ count
								+ "' class='comboNormal' onchange='setDefaultValues(this, \""
								+ count + "\")' >");
						sb.append(comboValue);
						sb.append("</select> <input name='strInDisplayName' id='strInDisplayName"
								+ count
								+ "' value='"
								+ strColumnName
								+ "' type='hidden'> <input name='strPreInParamId' id='strPreInParamId"
								+ count + "' value='' type='hidden'>  ");

						sb.append("</td>");

						sb.append("</tr>");

					} else {

						sb1.append("<div style='display:none;'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb1.append("<tr>");
						sb1.append("<td class='multiControl' width='40%' >");

						sb1.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb1.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='30%' >");
						sb1.append(
								"<input type='hidden' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='0' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='30%' >");
						sb1.append(
								"<input type='hidden' name='strPreInParamValues' id='strPreInParamValues")
								.append(count)
								.append("'  class='txtFldNormal' value='0' > <input name='strInDisplayName' value='"
										+ strColumnName + "' type='hidden'> ");

						sb1.append("</td>");

						sb1.append("</tr>");

						sb1.append("</table></div>");

					}

				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='3'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

			sb.append(sb1.toString());

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the merge proc parameter list view.
	 * 
	 * @param formBean
	 *            the form bean
	 * @return the merge proc parameter list view
	 * @throws Exception
	 *             the exception
	 */
	public static String getMergeProcParameterListView(
			DynamicReportParamMstFB formBean) throws Exception {

		String strProcInColumnDtls[] = formBean.getStrProcInColumnDtls();
		String strInConstantValue[] = formBean.getStrInConstantValue();
		String strPreInParamValues[] = formBean.getStrPreInParamValues();
		String strInDisplayNames[] = formBean.getStrInDisplayName();

		StringBuffer sb = new StringBuffer("");
		StringBuffer sb1 = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='4'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='40%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='30%'>Mapping Parameters");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (strProcInColumnDtls != null && strProcInColumnDtls.length > 0) {
				for (int i = 0; i < strProcInColumnDtls.length; i++) {

					String strColumnName = strProcInColumnDtls[i];

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='40%' >");

						sb.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='30%' >")
								.append(strInConstantValue[i]);
						sb.append(
								"<input type='hidden' name='strInConstantValue' ")
								.append(" value='")
								.append(strInConstantValue[i]).append("' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='30%' >")
								.append(strPreInParamValues[i]
										.replace("^", "#").split("#").length == 4 ? strPreInParamValues[i]
										.replace("^", "#").split("#")[3] : "--");
						sb.append(
								"<input type='hidden' name='strPreInParamValues' ")
								.append(" value='")
								.append(strPreInParamValues[i])
								.append("' >  <input name='strInDisplayName' value='"
										+ strInDisplayNames[i]
										+ "' type='hidden'>  <input name='strParamCompType' value='0' type='hidden'>  <input name='strComboQuery' value='' type='hidden'>  ");
						sb.append("</td>");

						sb.append("</tr>");

					} else {

						sb1.append("<div style='display:none;'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb1.append("<tr>");
						sb1.append("<td class='multiControl' width='40%' >");

						sb1.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb1.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='30%' >")
								.append(strInConstantValue[i]);
						sb1.append(
								"<input type='hidden' name='strInConstantValue' ")
								.append(" value='")
								.append(strInConstantValue[i]).append("' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='30%' >")
								.append(strPreInParamValues[i]
										.replace("^", "#").split("#").length == 3 ? strPreInParamValues[i]
										.replace("^", "#").split("#")[2] : "--");
						sb1.append(
								"<input type='hidden' name='strPreInParamValues' ")
								.append(" value='")
								.append(strPreInParamValues[i])
								.append("' >  <input name='strInDisplayName' value='"
										+ strInDisplayNames[i]
										+ "' type='hidden'>   <input name='strParamCompType' value='0' type='hidden'>  <input name='strComboQuery' value='' type='hidden'> ");
						sb1.append("</td>");

						sb1.append("</tr>");
						sb1.append("</table></div>");

					}

				}

			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='4'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

			sb.append(sb1.toString());

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the drill down proc column list view.
	 * 
	 * @param ws
	 *            the ws
	 * @param strIsLast
	 *            the str is last
	 * @return the drill down proc column list view
	 * @throws Exception
	 *             the exception
	 */
	public static String getDrillDownProcColumnListView(WebRowSet ws,
			String strIsLast) throws Exception {

		ResultSetMetaData rsmd = ws.getMetaData();

		StringBuffer sb = new StringBuffer("");

		int colCount = rsmd.getColumnCount();

		int colWidth = (int) Math.floor((100 / colCount));

		try {

			if (strIsLast == null)
				strIsLast = "0";

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='11'>Report Column Details ");
			sb.append("</td> ");
			sb.append("</tr> ");

			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='5%'>Required ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'>Actual Index ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Column Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'><font color='red'>*</font>Display Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'><font color='red'>*</font>Index ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='10%'><font color='red'>*</font>Column Width(%) ");
			sb.append("</td> ");

			if (strIsLast.equals("1")) {

				sb.append("<td class='multiLabel' width='5%' style='display:none;' > Hyperlink ");
				sb.append("</td> ");

			} else {

				sb.append("<td class='multiLabel' width='5%'> Hyperlink ");
				sb.append("</td> ");

			}

			sb.append("<td class='multiLabel' width='5%'> Total ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Group By ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='5%'> Order By ");
			sb.append("</td> ");

			sb.append("<td class='multiLabel' width='10%'> Align ");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (colCount > 0) {

				for (int i = 1; i <= colCount; i++) {

					String strColumnName = rsmd.getColumnName(i);

					if (!strColumnName.equalsIgnoreCase("HIDDEN_VAL")
							&& !strColumnName.equalsIgnoreCase("HIDDEN_VALUE")) {

						sb.append("<tr> ");

						sb.append("<td class='multiControl' width='5%'> ");

						sb.append("<div id='reqDivId"
								+ i
								+ "' ><input type='checkbox' name='strColumnRequired' value='1'  id='strColumnRequired"
								+ i
								+ "'  checked='checked' onclick='enableValues(this, \""
								+ i
								+ "\" );' ></div><div id='tickIconDivId"
								+ i
								+ "' style='display:none;'><img src='../../hisglobal/images/check.png'></div>");

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'> ")
								.append(i);
						sb.append("</td> ");
						sb.append("<td class='multiControl' width='20%'> ");
						sb.append(
								"<input type='hidden' name='strOutColumnName' id='strOutColumnName"
										+ i + "' value='")
								.append(strColumnName.toUpperCase())
								.append("' >");
						sb.append(strColumnName);

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='25%'> ");
						sb.append("<input type='text' name='strOutColumnDisplayName' maxlength='250' id='strOutColumnDisplayName"
								+ i
								+ "'  class='txtFldMax'   onkeypress='return validateData(event,17);' >");
						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'> ");

						sb.append(
								"<input type='hidden' name='strOutColumnActualIndex' id='strOutColumnActualIndex"
										+ i + "' value='").append(i)
								.append("' >");

						sb.append("<div id='colIndexDivId" + i + "' >");
						sb.append("<input type='text' name='strOutColumnIndex' maxlength='2' id='strOutColumnIndex"
								+ i
								+ "' value='"
								+ i
								+ "'  class='txtFldMin'   onkeypress='return validateData(event,5);' >");

						sb.append("</div><div id='colDisplayDivId" + i
								+ "' style='display:none;'>" + i + "</div>");

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='10%'> ");

						sb.append("<input type='text' value='"
								+ colWidth
								+ "' name='strOutColumnWidth' maxlength='3' id='strOutColumnWidth"
								+ i
								+ "'  class='txtFldMin'   onkeypress='return validateData(event,5);' >");

						sb.append("</td> ");

						if (strIsLast.equals("1")) {

							sb.append("<td class='multiControl' width='5%' style='display:none;' > ");
							sb.append("<input type='checkbox' name='strHyperlinkRequired' id='strHyperlinkRequired"
									+ i
									+ "' value='1' onclick='hiddenCheck(this , \"strHyperlinkRequiredValue"
									+ i
									+ "\"  ),hyperlinkMappingValue(this , \""
									+ i + "\" , \"" + colCount + "\");'>");
							sb.append("<input type='hidden' name='strHyperlinkRequiredValue' id='strHyperlinkRequiredValue"
									+ i
									+ "' value='0' > <input type='hidden' name='strHyperlinkMappingValue' id='strHyperlinkMappingValue"
									+ i + "' value='0'> ");
							sb.append("</td> ");
							/*
							 * sb.append(
							 * "<td class='multiControl' width='10%' style='display:none;'> "
							 * ).append(
							 * "<select name='strHyperlinkMappingValue' id='strHyperlinkMappingValue"
							 * +i+"' class='comboSmall' disabled='disabled' >").
							 * append
							 * (getIndexCombo(colCount)).append("<select>");
							 * sb.append("</td> ");
							 */

						} else {

							sb.append("<td class='multiControl' width='5%' > ");
							sb.append("<input type='checkbox' name='strHyperlinkRequired' id='strHyperlinkRequired"
									+ i
									+ "' value='1' onclick='hiddenCheck(this , \"strHyperlinkRequiredValue"
									+ i
									+ "\" ),hyperlinkMappingValue(this , \""
									+ i + "\" , \"" + colCount + "\");'>");
							sb.append("<input type='hidden' name='strHyperlinkRequiredValue' id='strHyperlinkRequiredValue"
									+ i
									+ "' value='0' > <input type='hidden' name='strHyperlinkMappingValue' id='strHyperlinkMappingValue"
									+ i + "' value='0'>");
							sb.append("</td> ");
							/*
							 * sb.append("<td class='multiControl' width='10%'> "
							 * ).append(
							 * "<select name='strHyperlinkMappingValue' id='strHyperlinkMappingValue"
							 * +i+"' class='comboSmall' disabled='disabled' >").
							 * append
							 * (getIndexCombo(colCount)).append("<select>");
							 * sb.append("</td> ");
							 */

						}

						sb.append("<td class='multiControl' width='5%'> ");
						sb.append("<input type='checkbox' name='strTotalRequired' id='strTotalRequired"
								+ i
								+ "' value='1' onclick='hiddenCheck(this , \"strTotalRequiredValue"
								+ i + "\" );' >");
						sb.append("<input type='hidden' name='strTotalRequiredValue' id='strTotalRequiredValue"
								+ i + "' value='0' >");
						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'>");
						sb.append("<input type='checkbox' name='strGroupByRequired' id='strGroupByRequired"
								+ i
								+ "' value='1' onclick='changeGroupByStatus(this , \""
								+ i + "\");' >");

						sb.append("<input type='hidden' name='strGroupByRequiredValue' id='strGroupByRequiredValue"
								+ i + "' value='0' >");

						sb.append("</td> ");
						sb.append("<td class='multiControl' width='5%'> ");
						sb.append("<input type='checkbox' name='strOrderByRequired' id='strOrderByRequired"
								+ i
								+ "' value='1' onclick='changeOrderByStatus(this , \""
								+ i
								+ "\"),hiddenCheck(this , \"strOrderByRequiredValue"
								+ i + "\" );' >");

						sb.append("<input type='hidden' name='strOrderByRequiredValue' id='strOrderByRequiredValue"
								+ i + "' value='0' >");

						sb.append("<input type='hidden' name='strGrpReqStatus' id='strGrpReqStatus"
								+ i + "' value='1' >");

						sb.append("</td> ");

						sb.append("<td class='multiControl' width='10%'> ");
						sb.append("<select name='strOutColumnAlign' id='strOutColumnAlign"
								+ i
								+ "' class='comboSmall' ><option selected='selected' value='left'>Left</option><option value='center'>Center</option><option value='right'>Right</option></select> ");
						sb.append("</td> ");

						sb.append("</tr> ");

					} else {

						sb.append("<tr style='display:none;'> ");
						sb.append("<td class='multiControl' colspan='8'>")
								.append("<input type='hidden' name='strIsHidden' value='1'>");
						sb.append("</td> ");
						sb.append("</tr> ");

					}

				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='8'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='12'> ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("</table>");

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the drill down pre proc parameter list.
	 * 
	 * @param ws
	 *            the ws
	 * @return the drill down pre proc parameter list
	 * @throws Exception
	 *             the exception
	 */
	public static String getDrillDownPreProcParameterList(WebRowSet ws)
			throws Exception {

		StringBuffer sb = new StringBuffer("");
		StringBuffer sb1 = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='4'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='30%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'><font color='red'>*</font>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Parameter Type");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Mapping Parameters");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (ws != null && ws.size() > 0) {
				while (ws.next()) {

					String strColumnName = ws.getString(1);

					count = count + 1;

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='30%' >");

						sb.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >");
						sb.append(
								"<input type='text' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='0' maxlength='25'   onkeypress='return validateData(event,8);' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >");
						sb.append("<select name='strInParamType' id='strInParamType"
								+ count
								+ "' class='comboNormal' onchange='getPreInParamValues(this, \""
								+ count + "\")' >");
						sb.append("<option value='0'>Select Value</option><option value='1'>In</option><option value='2'>Out</option>  ");
						sb.append("</select> ");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >");
						sb.append("<div id='strPreInParamValuesDivId"
								+ count
								+ "'><select name='strPreInParamValues' id='strPreInParamValues"
								+ count
								+ "' class='comboNormal' onchange='setDefaultValues(this, \""
								+ count + "\");' >");
						sb.append("<option value='0'>Select Value</option>");
						sb.append("</select> </div> <input name='strInDisplayName' id='strInDisplayName"
								+ count
								+ "' value='"
								+ strColumnName
								+ "' type='hidden'> ");

						sb.append("</td>");

						sb.append("</tr>");

					} else {

						sb1.append("<div style='display:none;'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb1.append("<tr>");
						sb1.append("<td class='multiControl' width='30%' >");

						sb1.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb1.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >");
						sb1.append(
								"<input type='hidden' name='strInConstantValue' id='strInConstantValueDivId")
								.append(count)
								.append("'  class='txtFldNormal' value='0' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >");
						sb1.append(
								"<input type='hidden' name='strInParamType' id='strInParamType")
								.append(count)
								.append("'  class='txtFldNormal' value='0' > ");

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >");
						sb1.append(
								"<input type='hidden' name='strPreInParamValues' id='strPreInParamValues")
								.append(count)
								.append("'  class='txtFldNormal' value='0' > <input name='strInDisplayName' value='"
										+ strColumnName + "' type='hidden'> ");

						sb1.append("</td>");

						sb1.append("</tr>");

						sb1.append("</table></div>");

					}

				}
			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='4'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

			sb.append(sb1.toString());

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/**
	 * Gets the drill down pre proc parameter list view.
	 * 
	 * @param formBean
	 *            the form bean
	 * @return the drill down pre proc parameter list view
	 * @throws Exception
	 *             the exception
	 */
	public static String getDrillDownPreProcParameterListView(
			DynamicReportParamMstFB formBean) throws Exception {

		String strProcInColumnDtls[] = formBean.getStrProcInColumnDtls();
		String strInConstantValue[] = formBean.getStrInConstantValue();
		String strPreInParamValues[] = formBean.getStrPreInParamValues();
		String strInParamType[] = formBean.getStrInParamType();

		StringBuffer sb = new StringBuffer("");
		StringBuffer sb1 = new StringBuffer("");
		int count = 0;

		try {

			sb.append("<table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
			sb.append("<tr class='TITLE'> ");
			sb.append("<td colspan='4'>Report Parameters ");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			sb.append("<td class='multiLabel' width='30%'>Parameter Name ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Default Values ");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Parameter Type");
			sb.append("</td> ");
			sb.append("<td class='multiLabel' width='20%'>Mapping Parameters");
			sb.append("</td> ");

			sb.append("</tr> ");

			if (strProcInColumnDtls != null && strProcInColumnDtls.length > 0) {
				for (int i = 0; i < strProcInColumnDtls.length; i++) {

					String strColumnName = strProcInColumnDtls[i];

					if (!strColumnName.trim().equalsIgnoreCase("SEAT_ID")
							&& !strColumnName.trim().equalsIgnoreCase(
									"HOSP_CODE")
							&& !strColumnName.trim().equalsIgnoreCase(
									"GROUPORDERBY")) {

						sb.append("<tr>");
						sb.append("<td class='multiControl' width='30%' >");

						sb.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >")
								.append(strInConstantValue[i]);
						sb.append(
								"<input type='hidden' name='strInConstantValue' ")
								.append(" value='")
								.append(strInConstantValue[i]).append("' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >")
								.append(strInParamType[i].equals("1") ? "In"
										: "Out");
						sb.append("<input type='hidden' name='strInParamType' ")
								.append(" value='").append(strInParamType[i])
								.append("' >");
						sb.append("</td>");

						sb.append("<td class='multiControl' width='20%' >")
								.append(strPreInParamValues[i]
										.replace("^", "#").split("#").length == 4 ? strPreInParamValues[i]
										.replace("^", "#").split("#")[3] : "--");
						sb.append(
								"<input type='hidden' name='strPreInParamValues' ")
								.append(" value='")
								.append(strPreInParamValues[i])
								.append("' >  <input name='strInDisplayName' value='"
										+ strProcInColumnDtls[i]
										+ "' type='hidden'>  <input name='strParamCompType' value='0' type='hidden'>  <input name='strComboQuery' value='' type='hidden'>  ");
						sb.append("</td>");

						sb.append("</tr>");

					} else {

						sb1.append("<div style='display:none;'><table border='0' class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
						sb1.append("<tr>");
						sb1.append("<td class='multiControl' width='30%' >");

						sb1.append(
								"<input type='hidden' name='strProcInColumnDtls' id='strProcInColumnDtls"
										+ count + "' value='")
								.append(strColumnName).append("' >");
						sb1.append("<div id='colNameDivId" + count + "'>")
								.append(strColumnName).append("</div>");

						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >")
								.append(strInConstantValue[i]);
						sb1.append(
								"<input type='hidden' name='strInConstantValue' ")
								.append(" value='")
								.append(strInConstantValue[i]).append("' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >")
								.append(strInConstantValue[i]);
						sb1.append(
								"<input type='hidden' name='strInParamType' ")
								.append(" value='").append(strInParamType[i])
								.append("' >");
						sb1.append("</td>");

						sb1.append("<td class='multiControl' width='20%' >")
								.append(strPreInParamValues[i]
										.replace("^", "#").split("#").length == 3 ? strPreInParamValues[i]
										.replace("^", "#").split("#")[2] : "--");
						sb1.append(
								"<input type='hidden' name='strPreInParamValues' ")
								.append(" value='")
								.append(strPreInParamValues[i])
								.append("' >  <input name='strInDisplayName' value='"
										+ strProcInColumnDtls[i]
										+ "' type='hidden'>   <input name='strParamCompType' value='0' type='hidden'>  <input name='strComboQuery' value='' type='hidden'> ");
						sb1.append("</td>");

						sb1.append("</tr>");
						sb1.append("</table></div>");

					}

				}

			} else {

				sb.append("<tr> ");
				sb.append("<td class='multiControl' colspan='4'> <font color='red' size='2'>No Details Available</font>");
				sb.append("</td> ");
				sb.append("</tr> ");

			}

			sb.append("</table>");

			sb.append(sb1.toString());

		} catch (Exception e) {

			throw e;

		}

		return sb.toString();

	}

	/*
	 * private static String getIndexCombo(int maxVal){
	 * 
	 * StringBuffer sb = new StringBuffer("");
	 * 
	 * if(maxVal > 0){
	 * 
	 * sb.append("<option selected='selected' value='0' >Select Value</option>");
	 * 
	 * for (int i = 1; i <= maxVal; i++) {
	 * 
	 * sb.append("<option value='"+i+"' >"+i+"</option>");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }else{
	 * 
	 * sb.append("<option selected='selected' value='0' >Select Value</option>");
	 * 
	 * }
	 * 
	 * 
	 * return sb.toString();
	 * 
	 * }
	 */
}
