package ipd;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.rowset.WebRowSet;

public class HLPipd extends DAOIpd {

	private static final String strColor = "red";
	private static final String defaultColor = "blue";

	private static ResourceBundle hisProp = ResourceBundle
			.getBundle("ipd.hisIpdProperties");
	public static String blockedCSS = hisProp.getString("Blocked");
	public static String occupiedCSS = hisProp.getString("Occupied");
	public static String bookedCSS = hisProp.getString("Booked");
	public static String vacantCSS = hisProp.getString("Vacant");
	public static String vacantButDirtyCSS = hisProp
			.getString("Vacant_But_Dirty");
	public static String beds_per_row = hisProp.getString("Beds_per_Row");
	public static String bed_row_height = hisProp.getString("Bed_Row_Height");
	public static String default_height = hisProp.getString("Default_Height"); // default
	// popup
	// height
	// with
	// single
	// bed
	// row
	public static String default_height_adm = hisProp
			.getString("Default_Height_Admission");

	public HLPipd(String moduleName, String fileName) {
		super(moduleName, fileName);
		// TODO Auto-generated constructor stub
	}

	/**
	 * generate the tariff combo and set that in response
	 * 
	 * @param request
	 * @param response
	 */
	public void returnCombo(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		String str = "";
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String grp_ID = request.getParameter("group_Id");
			str = getComboData(grp_ID);
			out.print(str);
		} catch (Exception e) {
			new HisException("ipd", "HLPipd.returnCombo()", e.getMessage());
			// e.printStackTrace();
		}
	}

	/**
	 * called from returnCombo returns the tariff combo on the basis of group id
	 * 
	 * @param grp_ID
	 * @return tariff combo string
	 */
	public String getComboData(String grp_ID) {

		HisUtil hisutil = new HisUtil("ipd", "HLPipd");
		String comboName = "combo";
		String html = "";
		StringBuffer strBuffer = new StringBuffer(1000);
		try {
			String actionData = "id ='"
					+ comboName
					+ "#delIndex#' onChange=\"changeValue(this,'#delIndex#');\"";
			ArrayList<String> Al_List = new ArrayList<String>();
			Al_List = getComboList(grp_ID);
			// if (combo == 1){ //combo required
			if (actionData == "")
				strBuffer.append("<select name = " + comboName + " >");
			else
				strBuffer.append("<select name = " + comboName + " "
						+ actionData + ">");
			// }
			html = hisutil.getOptionValue(Al_List, "", "");
			strBuffer.append(html);
			strBuffer.append("</select>");
		} catch (Exception e) {
			new HisException("ipd", "HLPipd.getComboData()", e.getMessage());
		}
		return strBuffer.toString();
	}

	/**
	 * generates the combo option string called from ipdMultiRow2.jsp
	 * 
	 * @return combo option string
	 */
	public String GroupCombo() {

		HisUtil hisutil = new HisUtil("ipd", "HLPipd");
		String html = "";
		ArrayList<String> Al_List = new ArrayList<String>();
		try {

			Al_List = groupComboData();
			html = hisutil.getOptionValue(Al_List, "", "");

		} catch (Exception e) {
			new HisException("ipd", "HLPipd.GroupCombo()", e.getMessage());
		}
		return html;
	}

	/**
	 * Generate the tariff combo on the basis of group id 2
	 * 
	 * @param request
	 * @param response
	 */
	public void returnTariffCombo(
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {

		String grp_ID = "";
		String finalIndex = "";
		String html = "";
		ArrayList<String> Al_List = new ArrayList<String>();
		finalIndex = request.getParameter("finalIndex");
		StringBuffer strBuffer = new StringBuffer(1000);
		String comboName = "tariff";
		String actionData = "id ='" + comboName + "1-" + finalIndex
				+ "' onChange=\"fun('#delIndex#');\" ";
		HisUtil hisutil = new HisUtil("ipd", "HLPipd");
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			grp_ID = request.getParameter("group_Id");
			Al_List = tariffComboData(grp_ID);

			if (actionData == "")
				strBuffer.append("<select name = " + comboName + " >");
			else
				strBuffer.append("<select name = " + comboName + " "
						+ actionData + ">");

			html = hisutil.getOptionValue(Al_List, "", "");
			strBuffer.append(html);
			out.print(strBuffer.toString());
		} catch (Exception e) {
			new HisException("ipd", "HLPipd.returnTariffCombo()", e
					.getMessage());
		}

	}

	// //////////////////////Return Client Header Detail
	// String//////////////////////////
	public static String getHeaderDtl(String strchk) {
		StringBuffer sBuffer = new StringBuffer("");
		IpdVO voObj = new IpdVO();
		IpdBO boObj = new IpdBO();
		voObj.setStrValue1(strchk);
		try {
			boObj.getHeaderDetailsWS(voObj);
			WebRowSet ws = voObj.getGblWs1();
			// //////////////////////////////////////////////////////////

			if (ws.next()) {
				String strClientName = ws.getString(1);
				String strClientType = ws.getString(2);
				String strPaymentType = ws.getString(9);
				String strRegNo = ws.getString(3);
				String strAddress = ws.getString(5);
				String strContactPerson = ws.getString(4);
				String strContacNo = ws.getString(6);
				String strIsOPD = ws.getString(10);
				String strIsIPD = ws.getString(11);
				String strIsEME = ws.getString(12);
				String strClientID = ws.getString(13);
				// ///////////////////////////////////////////////////////////////

				sBuffer
						.append("<input type='hidden' name='strPaymentType' value="
								+ strPaymentType + ">");
				sBuffer.append("<input type='hidden' name='strClientID' value="
						+ strClientID + ">");

				sBuffer.append("<table width='100%' align='center'"
						+ " border='0'><tr>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Client Name</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text' name='strClientName' class='' maxlength ='15' id='strClientName' return validateData(event,4);>"
								+ strClientName + "</td>");
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Client Type</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text' name='strClientType' class='' id='strClinetType'  return validateData(event,5);>"
								+ strClientType + "</td>");
				sBuffer.append("</tr>");

				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Reg No:</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text' name='strRegNo' class='' maxlength ='15' id='strChequeNo' return validateData(event,5);>"
								+ strRegNo + "</td>");
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Contact Person</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text' name='strContactPerson' class='' id='strContactPerson' return validateData(event,4);>"
								+ strContactPerson + "</td>");
				sBuffer.append("</tr>");

				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Address:</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text' name='strAddress' class='' maxlength ='15' id='strAddress' return validateData(event,3);>"
								+ strAddress + "</td>");
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Contact No</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text'name='strContacNo' class='' id='strContacNo'  return validateData(event,2);>"
								+ strContacNo + "</td>");
				sBuffer.append("</tr>");

				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("Payment Type:</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text' name='strPaymentType' class='' maxlength ='15' id='strPaymentType' return validateData(event,3);>"
								+ strPaymentType + "</td>");
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='multiControl'>");
				sBuffer
						.append("<input type='text'name='' class='' id=''></td>");
				sBuffer.append("</tr>");

				sBuffer.append("</table>");

				sBuffer.append("<table width='100%' align='center'"
						+ " border='0'><tr>");
				sBuffer
						.append("<tr><td colspan='4' class='TITLE'>Client Covers</td></tr>");
				sBuffer.append("<tr><td colspan='4'>");
				sBuffer.append("<table width='100%' align='center'>");
				if (strIsOPD.trim().equals("1") && strIsIPD.trim().equals("0")
						&& strIsEME.trim().equals("0")) {
					sBuffer
							.append("<tr><td  class='LABEL'><input type='checkbox' name='strIsIPD' value='1' checked>OPD Covers</td>");
					sBuffer
							.append("<td  class='LABEL'><input type='checkbox' name='strIsOPD' value='0' >IPD Covers</td>");
					sBuffer
							.append("<td  class='LABEL'><input type='checkbox' name='strIsEME' value='0' >Emergency Covers</td>");
				}
				if (strIsOPD.trim().equals("0") && strIsIPD.trim().equals("1")
						&& strIsEME.trim().equals("0")) {
					sBuffer
							.append("<tr><td  class='LABEL'><input type='checkbox' name='strIsIPD' value='0'>OPD Covers</td>");
					sBuffer
							.append("<td  class='LABEL'><input type='checkbox' name='strIsOPD' value='1' checked>IPD Covers</td>");
					sBuffer
							.append("<td  class='LABEL'><input type='checkbox' name='strIsEME' value='0' >Emergency Covers</td>");

				}
				if (strIsOPD.trim().equals("0") && strIsIPD.trim().equals("0")
						&& strIsEME.trim().equals("1")) {
					sBuffer
							.append("<tr><td  class='LABEL'><input type='checkbox' name='strIsIPD' value='0' >OPD Covers</td>");
					sBuffer
							.append("<td  class='LABEL'><input type='checkbox' name='strIsOPD' value='0' >IPD Covers</td>");
					sBuffer
							.append("<td  class='LABEL'><input type='checkbox' name='strIsEME' value='1' checked>Emergency Covers</td>");

				}
				sBuffer.append("</tr>");
				sBuffer.append("</table>");
				sBuffer.append("</td></tr>");
				sBuffer.append("</table>");
			}
		} catch (Exception e) {
			new HisException("Client Verification Trans",
					"BillHeaderHLP.getHeader()-->", e.getMessage());
		}

		// System.out.println("IN HLP ----"+sBuffer.toString());
		return sBuffer.toString();

	}

	public static String getTariffChargeView(WebRowSet ws) {

		StringBuffer sb = new StringBuffer("");

		final int LMIT_VAL = 15;

		sb.append("<table width='100%'>");

		try {

			if (ws != null) {

				if (ws.size() != 0) {

					int noOfRecords = ws.size();

					int layerNo = noOfRecords / LMIT_VAL;
					int reminder = noOfRecords % LMIT_VAL;
					int totalLayer = layerNo;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<tr>");
					sb.append("<td class='TITLE'>&nbsp;");
					for (int i = 1; i <= totalLayer; i++) {
						sb.append("<a href='#' onClick='layerIndexNavigator(\""
								+ i + "\",\"" + totalLayer + "\")'>" + i
								+ "</a> &nbsp;");
					}
					sb.append("</td>");
					sb.append("</'tr>");
					sb.append("<tr>");
					sb.append("<td >");

					for (int i = 1; i <= totalLayer; i++) {

						if (i != totalLayer || totalLayer == 1) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb.append("<table width='100%' cellpadding='1'>");
							for (int j = 0; j < LMIT_VAL; j++) {
								ws.next();
								sb
										.append("<tr class='CONTROL'><td><input type='radio' name='strTariffVal' value='");
								sb.append(ws.getString(1));
								sb.append("' onClick='setTariffName(this,\""
										+ ws.getString(2) + "\");'> &nbsp; "
										+ ws.getString(2) + "");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb.append("<table width='100%' cellpadding='1'>");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb
										.append("<tr class='CONTROL'><td><input type='radio' name='strTariffVal' value='");
								sb.append(ws.getString(1));
								sb.append("' onClick='setTariffName(this,\""
										+ ws.getString(2) + "\");'> &nbsp; "
										+ ws.getString(2) + "");
							}
							sb.append("</table>");
							sb.append("</div>");
						}
					}
					sb.append("</td>");
					sb.append("</'tr>");
				} else {
					sb
							.append("<tr><td class='multiControl'><font color='red'>No Matching Record Found</font></td></tr>");
				}
			} else {
				throw new Exception("WebRowSet should not be null");
			}

		} catch (Exception e) {
			new HisException("Global TariffSearch",
					"ipd.HLPIpd.getTariffChargeView()-->", e.getMessage());
		}

		sb.append("</table>");

		return sb.toString();
	}
	
	
	public static String getMotherPatientListingView(IpdVO voObj) {

		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = voObj.getGblWs1();

		try {

			int start = Integer.parseInt(voObj.getStrValue4());
			int actualBlockSet = Integer.parseInt(voObj.getStrValue7());

			final int REC_PER_PAGE = Integer.parseInt(voObj.getStrValue6());
			final int PAGE_PER_BLOCK = 10;

			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;

			int previous = 1;
			int next = 0;
			int addHeight = 260;// deepak

			if (ws != null) {

				if (ws.size() != 0) {

					int actualFetchRecord = ws.size();

					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) {

						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;

					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					/*
					 * sb .append("<table width='100%'align='center'
					 * cellspacing='1px'>");
					 * 
					 * sb.append("<tr>"); sb.append("<td class='LABEL'>&nbsp;");
					 * 
					 * if (start != 1){ sb .append("<a href='#'
					 * onClick='fetchPatientList(\""+previous+"\",\""+(actualBlockSet -
					 * 1)+"\");'> &lt;&lt; Previous</a> &nbsp;"); } for (int i =
					 * 1; i <= totalLayer; i++) { sb.append("<a href='#'
					 * onClick='layerIndexNavigator(\"" + i + "\",\"" +
					 * totalLayer + "\")'>" + (i + start - 1) + "</a> &nbsp;"); }
					 * 
					 * if (next > 0 ) sb.append("<a href='#'
					 * onClick='fetchPatientList(\""+next+"\",\""+(actualBlockSet +
					 * 1)+"\");'> Next &gt;&gt;</a>");
					 * 
					 * sb.append("</td>"); sb.append("</tr>"); sb.append("</table>");
					 */

					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td class='LABEL'>&nbsp;");

					if (start != 1) {
						sb
								.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""
										+ previous
										+ "\",\""
										+ (actualBlockSet - 1)
										+ "\");'> <FONT COLOR='"
										+ strColor
										+ "'> &lt;&lt; Previous</FONT> </a> &nbsp;");

					}
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));

						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						}
						sb.append("</a> &nbsp;");
					}

					if (next > 0)
						sb
								.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""
										+ next
										+ "\",\""
										+ (actualBlockSet + 1)
										+ "\");'> <FONT COLOR='"
										+ strColor
										+ "'> Next &gt;&gt;</FONT></a>");

					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>#");
					sb.append("</td>");

					sb.append("<td width='25%'class='multiLabel'>CR No.");
					sb.append("</td>");
					sb.append("<td width='20%'class='multiLabel'>Admission No.");
					sb.append("</td>");
					sb.append("<td width='25%'class='multiLabel'>Mother Name");
					sb.append("</td>");
					sb.append("<td width='25%'class='multiLabel'>No. of Babies");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) {

						if (i < totalLayer
								|| (i == totalLayer && reminder == 0)) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							addHeight = 260;
							for (int j = 0; j < REC_PER_PAGE; j++) {

								ws.next();
								addHeight = addHeight + 50;// deepak
								// System.out.println("addHeight->"+addHeight);
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strCrNo' value='");
								sb.append(ws.getString(1));
								sb.append("'></td>");

								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");

								sb
										.append("<td class='multiControl' width='20%'>");
								sb.append(ws.getString(4));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							addHeight = 260;
							for (int k = 0; k < reminder; k++) {
								ws.next();
								addHeight = addHeight + 50;// deepak
								// System.out.println("addHeight->"+addHeight);
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strCrNo' value='");
								sb.append(ws.getString(1));
								sb.append("'></td>");

								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");

								sb
										.append("<td class='multiControl' width='20%'>");
								sb.append(ws.getString(4));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						}

					}

					sb.append("</td>");
					sb.append("</'tr>");

					sb.append("</table>");

				} else {
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb
							.append("<tr><td class='multiControl'><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}

			} else {

				throw new Exception("Mother Patient List WebRowSet is Null");

			}
			// System.out.println("addHeight->"+addHeight);
			sb.append("<input type='hidden' name='strWin_Resize' value='"
					+ addHeight + "'>");
			voObj.setStrWinResize(addHeight);
		} catch (Exception e) {
			new HisException("Global Patient Listing",
					"ipd.HLPIpd.getMotherPatientListingView()-->", e.getMessage());
		}

		return sb.toString();

	}
	
	
	
	public static String getMotherPatientListingViewModification(IpdVO voObj) {

		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = voObj.getGblWs1();

		try {

			int start = Integer.parseInt(voObj.getStrValue4());
			int actualBlockSet = Integer.parseInt(voObj.getStrValue7());

			final int REC_PER_PAGE = Integer.parseInt(voObj.getStrValue6());
			final int PAGE_PER_BLOCK = 10;

			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;

			int previous = 1;
			int next = 0;
			int addHeight = 260;// deepak

			if (ws != null) {

				if (ws.size() != 0) {

					int actualFetchRecord = ws.size();

					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) {

						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;

					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					/*
					 * sb .append("<table width='100%'align='center'
					 * cellspacing='1px'>");
					 * 
					 * sb.append("<tr>"); sb.append("<td class='LABEL'>&nbsp;");
					 * 
					 * if (start != 1){ sb .append("<a href='#'
					 * onClick='fetchPatientList(\""+previous+"\",\""+(actualBlockSet -
					 * 1)+"\");'> &lt;&lt; Previous</a> &nbsp;"); } for (int i =
					 * 1; i <= totalLayer; i++) { sb.append("<a href='#'
					 * onClick='layerIndexNavigator(\"" + i + "\",\"" +
					 * totalLayer + "\")'>" + (i + start - 1) + "</a> &nbsp;"); }
					 * 
					 * if (next > 0 ) sb.append("<a href='#'
					 * onClick='fetchPatientList(\""+next+"\",\""+(actualBlockSet +
					 * 1)+"\");'> Next &gt;&gt;</a>");
					 * 
					 * sb.append("</td>"); sb.append("</tr>"); sb.append("</table>");
					 */

					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td class='LABEL'>&nbsp;");

					if (start != 1) {
						sb
								.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""
										+ previous
										+ "\",\""
										+ (actualBlockSet - 1)
										+ "\");'> <FONT COLOR='"
										+ strColor
										+ "'> &lt;&lt; Previous</FONT> </a> &nbsp;");

					}
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));

						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						}
						sb.append("</a> &nbsp;");
					}

					if (next > 0)
						sb
								.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""
										+ next
										+ "\",\""
										+ (actualBlockSet + 1)
										+ "\");'> <FONT COLOR='"
										+ strColor
										+ "'> Next &gt;&gt;</FONT></a>");

					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>#");
					sb.append("</td>");

					sb.append("<td width='25%'class='multiLabel'>CR No.");
					sb.append("</td>");
					sb.append("<td width='20%'class='multiLabel'>Admission No.");
					sb.append("</td>");
					sb.append("<td width='25%'class='multiLabel'>Mother Name");
					sb.append("</td>");
					sb.append("<td width='25%'class='multiLabel'>No. of Babies");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) {

						if (i < totalLayer
								|| (i == totalLayer && reminder == 0)) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							addHeight = 260;
							for (int j = 0; j < REC_PER_PAGE; j++) {

								ws.next();
								addHeight = addHeight + 50;// deepak
								// System.out.println("addHeight->"+addHeight);
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strCrNomod' value='");
								sb.append(ws.getString(1));
								sb.append("'></td>");

								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");

								sb
										.append("<td class='multiControl' width='20%'>");
								sb.append(ws.getString(4));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							addHeight = 260;
							for (int k = 0; k < reminder; k++) {
								ws.next();
								addHeight = addHeight + 50;// deepak
								// System.out.println("addHeight->"+addHeight);
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strCrNomod' value='");
								sb.append(ws.getString(1));
								sb.append("'></td>");

								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");

								sb
										.append("<td class='multiControl' width='20%'>");
								sb.append(ws.getString(4));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='25%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						}

					}

					sb.append("</td>");
					sb.append("</'tr>");

					sb.append("</table>");
					

				} else {
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb
							.append("<tr><td class='multiControl'><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}

			} else {

				throw new Exception("Mother Patient List WebRowSet is Null");

			}
			// System.out.println("addHeight->"+addHeight);
			sb.append("<input type='hidden' name='strWin_Resize' value='"
					+ addHeight + "'>");
			voObj.setStrWinResize(addHeight);
		} catch (Exception e) {
			new HisException("Global Patient Listing",
					"ipd.HLPIpd.getMotherPatientListingView()-->", e.getMessage());
		}

		return sb.toString();

	}
	
	
	
	
	public static String getPatientListingView(IpdVO voObj) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = voObj.getGblWs1();

		try 
		{
			int start = Integer.parseInt(voObj.getStrValue4());
			int actualBlockSet = Integer.parseInt(voObj.getStrValue7());
			final int REC_PER_PAGE = Integer.parseInt(voObj.getStrValue6());
			final int PAGE_PER_BLOCK = 10;
			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;
			int previous = 1;
			int next = 0;
			int addHeight = 260;

			if (ws != null) 
			{
				if (ws.size() != 0) 
				{
					int actualFetchRecord = ws.size();
					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) 
					{
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;
					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table width='100%' align='center' cellspacing='0' '>");
					sb.append("<tr>");
					if (voObj.getStrValue1().equals("9"))
						sb.append("<td class='LABEL'><div align='left'><font color='green' size='1px'>List of Currently Admitted Non Dead,Billing Account Not Settled Patients</font></div></td>");
					else
						sb.append("<td class='LABEL'>&nbsp;</td>");
					sb.append("<td class='LABEL'>&nbsp;");

					if (start != 1) 
					{
						sb.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""+ previous+ "\",\""+ (actualBlockSet - 1)+ "\");'>");
						sb.append("<FONT COLOR='"+ strColor+ "'> &lt;&lt; Previous</FONT> </a> &nbsp;");
					}
					for (int i = 1; i <= totalLayer; i++) 
					{
						if (i == 1) 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ strColor + "' name='linId' id='linId" + i+ "' onClick='layerIndexNavigator(\"" + i+ "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						} 
						else 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ defaultColor + "' name='linId' id='linId"+ i + "' onClick='layerIndexNavigator(\""+ i + "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						}
						sb.append("</a> &nbsp;");
					}

					if (next > 0)
					{
						sb.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""+ next+ "\",\""+ (actualBlockSet + 1)+ "\");'>");
						sb.append("<FONT COLOR='"+ strColor+ "'> Next &gt;&gt;</FONT></a>");
					}

					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					sb.append("<table width='100%' align='center' cellspacing='0'>");
					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>#</td>");

					sb.append("<td width='15%'class='multiLabel'>CR No.</td>");					
					sb.append("<td width='23%'class='multiLabel'>Patient Name</td>");

					//StrValue1---1 ,2,3 Leave & 4 Adviced Patient List Used in Patient Admission 
					//& 5 Admitted Patient List Used in Final Discharge Process 
					//& 6 Mother List Used in New Born Baby Admission Process 
					//& 8 Admitted But Not Accepted Patient List Used in Admission Modification & Admission Cancellation Process
					//& 9 MMS Cuurent Admitted Patient,Account Not Settled,Not Dead
					
					if (voObj.getStrValue1().equals("1") || voObj.getStrValue1().equals("2") || voObj.getStrValue1().equals("3")) 
					{
						sb.append("<td width='18%'class='multiLabel'>Admission No.</td>");
						sb.append("<td width='21%'class='multiLabel'>Leave Date</td>");
					} 
					else if (voObj.getStrValue1().equals("4")) 
					{
						sb.append("<td width='18%'class='multiLabel'>Prop. Admission Date</td>");
						sb.append("<td width='21%'class='multiLabel'>Booking Date</td>");
					} 
					else if (voObj.getStrValue1().equals("9")) 
					{
						sb.append("<td width='15%'class='multiLabel'>Admission No.</td>");
						sb.append("<td width='20%'class='multiLabel'>Admission Date & Time</td>");						
					}
					else if (voObj.getStrValue1().equals("8")) 
					{
						sb.append("<td width='15%'class='multiLabel'>Admission No.</td>");
						sb.append("<td width='20%'class='multiLabel'>Admission Date & Time</td>");						
					}
					else 
					{
						sb.append("<td width='13%'class='multiLabel'>Admission No.</td>");
						sb.append("<td width='18%'class='multiLabel'>Admission Date & Time</td>");
						sb.append("<td width='8%'class='multiLabel'>Account Settlement Status</td>");
					}
					sb.append("</tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) 
					{
						if (i < totalLayer|| (i == totalLayer && reminder == 0)) 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:none'>");
							}

							sb.append("<table width='100%' align='center' cellspacing='0'>");
							addHeight = 260;
							for (int j = 0; j < REC_PER_PAGE; j++) 
							{
								ws.next();
								addHeight = addHeight + 50;
								sb.append("<tr><td class='multiLabel' width='5%'><input type='radio' name='strCrNo' value='");
								sb.append(ws.getString(1));
								sb.append("' onclick='setCrNo();'></td>");
								if (voObj.getStrValue1().equals("9")) 
								{
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='21%'><font color='blue'>"+ws.getString(4)+"</font></td>");
									
								}
								if (voObj.getStrValue1().equals("8")) 
								{
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='21%'><font color='blue'>"+ws.getString(4)+"</font></td>");
									
								}
								if (voObj.getStrValue1().equals("4")) 
								{
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='21%'><font color='blue'>"+ws.getString(4)+"</font></td>");
									
								}
								else if(voObj.getStrValue1().equals("5"))
								{
	                                if(ws.getString(5).equals("Yes"))
	                                {
	                                	sb.append("<td class='multiControlGreen' width='15%'>"+ws.getString(1)+"</td>");
	    								sb.append("<td class='multiControlSmallLeftGreen' width='23%'>"+ws.getString(2)+"</td>");
	    								sb.append("<td class='multiControlGreen' width='13%'>"+ws.getString(3)+"</td>");
	    								sb.append("<td class='multiControlGreen' width='18%'>"+ws.getString(4)+"</td>");
	    								sb.append("<td class='multiControlGreen' width='8%'>"+ws.getString(5)+"</td>");
	                                }
	                                else
	                                {
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='13%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(4)+"</td>");
									sb.append("<td class='multiControl' width='8%'>"+ws.getString(5)+"</td>");
	                                }
								}
								
							}
							sb.append("</table>");
							sb.append("</div>");
						} 
						else 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:none'>");
							}
							sb.append("<table width='100%' align='center' cellspacing='0'>");
							addHeight = 260;
							for (int k = 0; k < reminder; k++) 
							{
								ws.next();
								addHeight = addHeight + 50;
								sb.append("<tr><td class='multiLabel' width='5%'><input type='radio' name='strCrNo' value='");
								sb.append(ws.getString(1));
								sb.append("'></td>");
								if (voObj.getStrValue1().equals("9")) 
								{
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='20%'>"+ws.getString(4)+"</td>");
									
								}
								if (voObj.getStrValue1().equals("8")) 
								{
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='21%'><font color='blue'>"+ws.getString(4)+"</font></td>");
									
								}
								if (voObj.getStrValue1().equals("4")) 
								{
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='21%'><font color='blue'>"+ws.getString(4)+"</font></td>");
									
								}
								else if(voObj.getStrValue1().equals("5"))
								{
	                                if(ws.getString(5).equals("Yes"))
	                                {
	                                	sb.append("<td class='multiControlGreen' width='15%'>"+ws.getString(1)+"</td>");
	    								sb.append("<td class='multiControlSmallLeftGreen' width='23%'>"+ws.getString(2)+"</td>");
	    								sb.append("<td class='multiControlGreen' width='13%'>"+ws.getString(3)+"</td>");
	    								sb.append("<td class='multiControlGreen' width='18%'>"+ws.getString(4)+"</td>");
	    								sb.append("<td class='multiControlGreen' width='8%'>"+ws.getString(5)+"</td>");
	                                }
	                                else
	                                {
									sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
									sb.append("<td class='multiControlSmallLeft' width='23%'>"+ws.getString(2)+"</td>");
									sb.append("<td class='multiControl' width='13%'>"+ws.getString(3)+"</td>");
									sb.append("<td class='multiControl' width='18%'>"+ws.getString(4)+"</td>");
									sb.append("<td class='multiControl' width='8%'>"+ws.getString(5)+"</td>");
	                                }
								}
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");
						}
					}
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");
				} 
				else 
				{
					sb.append("<table width='100%' align='center' cellspacing='0'>");
					sb.append("<tr><td class='multiControl'><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}
			} 
			else 
			{
				throw new Exception("Patient List WebRowSet is Null");
			}
			// System.out.println("addHeight->"+addHeight);
			sb.append("<input type='hidden' name='strWin_Resize' value='"+ addHeight + "'>");
			voObj.setStrWinResize(addHeight);
		} 
		catch (Exception e) 
		{
			new HisException("Patient Listing","ipd.HLPIpd.getPatientListingView()-->", e.getMessage());
		}
		return sb.toString();
	}
	
	
	
	public static String getPatientListingView_BS(IpdVO voObj) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = voObj.getGblWs1();
		

		try 
		{
			int start = Integer.parseInt(voObj.getStrValue4());
			int actualBlockSet = Integer.parseInt(voObj.getStrValue7());
			final int REC_PER_PAGE = Integer.parseInt(voObj.getStrValue6());
			final int PAGE_PER_BLOCK = 10;
			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;
			int previous = 1;
			int next = 0;
			int addHeight = 260;

			if (ws != null) 
			{
				if (ws.size() != 0) 
				{
					int actualFetchRecord = ws.size();
					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) 
					{
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;
					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					if (voObj.getStrValue1().equals("9"))
						sb.append("");
					else
						sb.append("");
					sb.append("");

					/*if (start != 1) 
					{
						sb.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""+ previous+ "\",\""+ (actualBlockSet - 1)+ "\");'>");
						sb.append("<FONT COLOR='"+ strColor+ "'> &lt;&lt; Previous</FONT> </a> &nbsp;");
					}
					for (int i = 1; i <= totalLayer; i++) 
					{
						if (i == 1) 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ strColor + "' name='linId' id='linId" + i+ "' onClick='layerIndexNavigator(\"" + i+ "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						} 
						else 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ defaultColor + "' name='linId' id='linId"+ i + "' onClick='layerIndexNavigator(\""+ i + "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						}
						sb.append("</a> &nbsp;");
					}

					if (next > 0)
					{
						sb.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""+ next+ "\",\""+ (actualBlockSet + 1)+ "\");'>");
						sb.append("<FONT COLOR='"+ strColor+ "'> Next &gt;&gt;</FONT></a>");
					}
*/
					
					sb.append("<table id='dtable' class='table tdfont' style='line-height: 0.8;'>");
					sb.append("<thead>");
					sb.append("<tr>");
					sb.append("<th >#&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CR No.</th>");
					//sb.append("<th>CR No.</th>");
					sb.append("<th >Patient Name</th>");

					//StrValue1---1 ,2,3 Leave & 4 Adviced Patient List Used in Patient Admission 
					//& 5 Admitted Patient List Used in Final Discharge Process 
					//& 6 Mother List Used in New Born Baby Admission Process 
					//& 8 Admitted But Not Accepted Patient List Used in Admission Modification & Admission Cancellation Process
					//& 9 MMS Cuurent Admitted Patient,Account Not Settled,Not Dead
					
					if (voObj.getStrValue1().equals("1") || voObj.getStrValue1().equals("2") || voObj.getStrValue1().equals("3")) 
					{
						sb.append("<th>Admission No.</th>");
						sb.append("<th>Leave Date</th>");
					} 
					else if (voObj.getStrValue1().equals("4")) 
					{
						sb.append("<th>Prop. Admission Date</th>");
						sb.append("<th>Booking Date</th>");
					} 
					else if (voObj.getStrValue1().equals("9")) 
					{
						sb.append("<th>Admission No.</th>");
						sb.append("<th>Admission Date & Time</th>");
					}
					else if (voObj.getStrValue1().equals("8")) 
					{
						sb.append("<th>Admission No.</th>");
						sb.append("<th>Admission Date & Time</th>");
					}
					else 
					{
						sb.append("<th>Admission No.</th>");
						sb.append("<th>Admission Date & Time</th>");
						sb.append("<th>Account Settlement Status</th>");
					}
					sb.append("</tr>");
					sb.append("</thead>");
					
					sb.append("</tbody>");


					for (int i = 1; i <= totalLayer; i++) 
					{
						if (i < totalLayer|| (i == totalLayer && reminder == 0)) 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:none'>");
							}

							//sb.append("<table width='100%' align='center' cellspacing='0'>");
							addHeight = 260;
							for (int j = 0; j < REC_PER_PAGE; j++) 
							{
								ws.next();
								addHeight = addHeight + 50;
								sb.append("<tr><td><input type='radio' name='strCrNoBs' value='");
								sb.append(ws.getString(1));
								sb.append("'onclick='setCrNoModal();'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ws.getString(1)+"</td>");
								if (voObj.getStrValue1().equals("9")) 
								{
									sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</font></td>");
									
								}
								if (voObj.getStrValue1().equals("8")) 
								{
									//sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</font></td>");
									
								}
								if (voObj.getStrValue1().equals("4")) 
								{
									sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</font></td>");
									
								}
								else if(voObj.getStrValue1().equals("5"))
								{
	                                if(ws.getString(5).equals("Yes"))
	                                {
	                                	sb.append("<td>"+ws.getString(1)+"</td>");
	    								sb.append("<td>"+ws.getString(2)+"</td>");
	    								sb.append("<td>"+ws.getString(3)+"</td>");
	    								sb.append("<td>"+ws.getString(4)+"</td>");
	    								sb.append("<td>"+ws.getString(5)+"</td>");
	                                }
	                                else
	                                {
									sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</td>");
									sb.append("<td>"+ws.getString(5)+"</td>");
	                                }
								}
								
							}
							//sb.append("</table>");
							sb.append("</div>");
						} 
						else 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:none'>");
							}
							//sb.append("<table width='100%' align='center' cellspacing='0'>");
							addHeight = 260;
							for (int k = 0; k < reminder; k++) 
							{
								
								ws.next();
								addHeight = addHeight + 50;
								sb.append("<tr><td><input type='radio' name='strCrNoBs' value='");
								sb.append(ws.getString(1));
								sb.append("'onclick='setCrNoModal();'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ws.getString(1)+"</td>");
								if (voObj.getStrValue1().equals("9")) 
								{
									sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</td>");
								}
								if (voObj.getStrValue1().equals("8")) 
								{
									//sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</td>");
									
								}
								if (voObj.getStrValue1().equals("4")) 
								{
									sb.append("<td>"+ws.getString(1)+"</td>");
									sb.append("<td>"+ws.getString(2)+"</td>");
									sb.append("<td>"+ws.getString(3)+"</td>");
									sb.append("<td>"+ws.getString(4)+"</td>");
								}
								else if(voObj.getStrValue1().equals("5"))
								{
	                                if(ws.getString(5).equals("Yes"))
	                                {
										/* sb.append("<td>"+ws.getString(1)+"</td>"); */
	    								sb.append("<td>"+ws.getString(2)+"</td>");
	    								sb.append("<td>"+ws.getString(3)+"</td>");
	    								sb.append("<td>"+ws.getString(4)+"</td>");
	    								sb.append("<td>"+ws.getString(5)+"</td>");
	                                }
	                                else
	                                {
										/* sb.append("<td>"+ws.getString(1)+"</td>"); */
	    								sb.append("<td>"+ws.getString(2)+"</td>");
	    								sb.append("<td>"+ws.getString(3)+"</td>");
	    								sb.append("<td>"+ws.getString(4)+"</td>");
	    								sb.append("<td>"+ws.getString(5)+"</td>");
	                                }
								}
								
								sb.append("</tr>");
							}
							//sb.append("</table>");
							sb.append("</div>");
						}
					}
					
					sb.append("</tr>");
				sb.append("</table>");
				 
				} 
				else 
				{
					sb.append("<table width='100%' align='center' cellspacing='0'>");
					sb.append("<tr><td class='multiControl'><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
					
				}
			} 
			else 
			{
				throw new Exception("Patient List WebRowSet is Null");
			}
			// System.out.println("addHeight->"+addHeight);
			sb.append("<input type='hidden' name='strWin_Resize' value='"+ addHeight + "'>");
			voObj.setStrWinResize(addHeight);
		//	sb.append("<input id='arrowk' type='hidden' value='2'>");
			sb.append("<script>");
			sb.append("  modalDataTable();");
			sb.append("</script>");
		} 
		catch (Exception e) 
		{
			new HisException("Patient Listing","ipd.HLPIpd.getPatientListingView()-->", e.getMessage());
		}

		return sb.toString();
	}
	
	
	
	
	
	
	public static String getPatientListingViewModification(IpdVO voObj) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = voObj.getGblWs1();

		try 
		{
			int start = Integer.parseInt(voObj.getStrValue4());
			int actualBlockSet = Integer.parseInt(voObj.getStrValue7());
			final int REC_PER_PAGE = Integer.parseInt(voObj.getStrValue6());
			final int PAGE_PER_BLOCK = 10;
			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;
			int previous = 1;
			int next = 0;
			int addHeight = 260;

			if (ws != null) 
			{
				if (ws.size() != 0) 
				{
					int actualFetchRecord = ws.size();
					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;
					
					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) 
					{
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;
					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb.append("<table class='TABLEWIDTH' align='center' cellspacing='0' '>");
					sb.append("<tr>");
					sb.append("<td class='LABEL'>&nbsp;");

					if (start != 1) 
					{
						sb.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientListmodification(\""+ previous+ "\",\""+ (actualBlockSet - 1)+ "\");'>");
						sb.append("<FONT COLOR='" +strColor+ "'> &lt;&lt; Previous</FONT> </a> &nbsp;");
					}
					for (int i = 1; i <= totalLayer; i++) 
					{
						if (i == 1) 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ strColor + "' name='linId' id='linId" + i	+ "' onClick='layerIndexNavigator(\"" + i+ "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						} 
						else 
						{
							sb.append("<a STYLE='CURSOR:POINTER; color:"+ defaultColor + "' name='linId' id='linId"	+ i + "' onClick='layerIndexNavigator(\""+ i + "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						}
						sb.append("</a> &nbsp;");
					}
					if (next > 0)
					{
						sb.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientListmodification(\""+ next+ "\",\""+ (actualBlockSet + 1)+ "\");'>");
						sb.append("<FONT COLOR='"+ strColor	+ "'> Next &gt;&gt;</FONT></a>");
					}

					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					sb.append("<table class='TABLEWIDTH' align='center' cellspacing='0' '>");
					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>#</td>");

					sb.append("<td width='15%'class='multiLabel'>CR No.</td>");
					sb.append("<td width='23%'class='multiLabel'>Patient Name</td>");
					
					if (voObj.getStrValue1().equals("1") || voObj.getStrValue1().equals("2") || voObj.getStrValue1().equals("3")) 
					{
						sb.append("<td width='18%'class='multiLabel'>Admission No.</td>");
						sb.append("<td width='21%'class='multiLabel'>Leave Date</td>");						
					} 
					else if (voObj.getStrValue1().equals("4")) 
					{
						sb.append("<td width='18%'class='multiLabel'>Prop. Admission Date</td>");
						sb.append("<td width='21%'class='multiLabel'>Booking Date</td>");
					} 
					else 
					{
						sb.append("<td width='18%'class='multiLabel'>Admission No.</td>");
						sb.append("<td width='21%'class='multiLabel'>Admission Date & Time</td>");
					}
					sb.append("</tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) 
					{

						if (i < totalLayer || (i == totalLayer && reminder == 0)) 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:none'>");
							}

							sb.append("<table class='TABLEWIDTH' align='center' cellspacing='0' '>");
							addHeight = 260;
							for (int j = 0; j < REC_PER_PAGE; j++) 
							{
								ws.next();
								addHeight = addHeight + 50;
								sb.append("<tr ><td class='multiControl' width='5%'><input type='radio' name='strCrNomod' value='");
								sb.append(ws.getString(1));
								sb.append("' onclick='setCrData("+ws.getString(1)+");'></td>");

								sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
								sb.append("<td class='multiControl' width='23%'>"+ws.getString(2)+"</td>");
								sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
								sb.append("<td class='multiControl' width='21%'><font color='blue'>"+ws.getString(4)+"</font></td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						}
						else 
						{
							if (i == 1) 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:block'>");
							} 
							else 
							{
								sb.append("<div id='tariffDivId" + i+ "' style='display:none'>");
							}
							sb.append("<table class='TABLEWIDTH' align='center' cellspacing='0' '>");
							addHeight = 260;
							for (int k = 0; k < reminder; k++) 
							{
								ws.next();
								addHeight = addHeight + 50;
								sb.append("<tr ><td class='multiControl' width='5%'><input type='radio' name='strCrNomod' value='");
								sb.append(ws.getString(1));
								sb.append("' onclick='setCrData("+ws.getString(1)+");'></td>");

								sb.append("<td class='multiControl' width='15%'>"+ws.getString(1)+"</td>");
								sb.append("<td class='multiControl' width='23%'>"+ws.getString(2)+"</td>");
								sb.append("<td class='multiControl' width='18%'>"+ws.getString(3)+"</td>");
								sb.append("<td class='multiControl' width='21%'>"+ws.getString(4)+"</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");
						}
					}

					sb.append("</td>");
					sb.append("</tr>");

					sb.append("</table>");

				} 
				else 
				{
					sb.append("<table class='TABLEWIDTH' align='center' cellspacing='0' '>");
					sb.append("<tr><td class='multiControl'><font color='red'>No Record Found</font></td></tr>");
					sb.append("</table>");
				}
			} 
			else 
			{
				throw new Exception("Patient List WebRowSet is Null");
			}			
			voObj.setStrWinResize(addHeight);
		} 
		catch (Exception e) 
		{
			new HisException("ADT","ipd.HLPIpd.getPatientListingView()-->", e.getMessage());
		}

		return sb.toString();

	}

	/** ****bed Status view****** */
	public static String getBedDetails1(IpdVO vo) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = vo.getBedDetailWS();
		int size = Integer.parseInt(beds_per_row);
		int pix_row_height = Integer.parseInt(bed_row_height);
		int def_height = 0;
		int count = 1;
		int bedRows = 0;
		int newHeight = 0;
		int addPixl = 0;
		int wsSize = ws.size();

		/** *setting default window size according to parent process** */

		if (vo.getStrBedChartMode().equals("process_Other"))
			def_height = Integer.parseInt(default_height);
		else
			def_height = Integer.parseInt(default_height_adm);

		/** *************************end****************************** */

		if (size > 4 || size < 1)
			size = 4; // overrides global settings

		/** *** Logic for dynamic row-wise popup height manipulation **** */

		bedRows = wsSize / size;

		/** ****checks to see no of rows***** */
		if ((bedRows * size) < wsSize)
			bedRows = bedRows + 1;
		if (wsSize == 0)
			bedRows = 1;
		/** *************end**************** */
		addPixl = (bedRows - 1) * pix_row_height;
		newHeight = def_height + addPixl;

		vo.setStrWinResize(newHeight); // setting hidden field attribute on popup

		/** ***************************** end *************************** */

		try 
		{
			sb.append("");
			
			//sb.append("<table width='100%' align='center' border='0' bgcolor='black' cellspacing='0'>");
			sb.append("<input type='hidden' name='strWin_Resize' value='"+ newHeight + "'>");
			sb.append("<input type='hidden' name='strSelBed' value=''>");
			//sb.append("<tr><td width='95%' class='TITLE'><div align='center'><font size='2' color=''>Bed Status Chart</font></div></td>");
			//sb.append("<td width='5%' class='TITLE'><img style='cursor:pointer;' src='"+vo.getStrImagePath()+"/hisglobal/images/stop.png' title='Click to Close Bed Window' onClick='window.close();'></td></tr></table>");
			//sb.append("<td width='5%' class='TITLE'></td></tr></table>");
			//sb.append("<br><table width='85%' align='center' border='0' cellspacing='1px'>");
			//sb.append("<tr><td>");
			sb.append("<div class='row'><div class='col-sm-12'><div class='row'>");
			if (wsSize != 0)
			{
				while(ws.next())
				{
					if(count<=size)
					{
						String temp[] = ws.getString(1).replace("^","#").split("#");
						sb.append("<input type='hidden' id='" + temp[0]+ "'name='bedCode' value='"+ ws.getString(2) + "'>");
						sb.append(getCSS(ws.getString(2), temp[0], ws.getString(3), temp[1], vo));
						count++;
					}
					else
					{
						ws.previous();
						//sb.append("</div></div></div></td></tr>");
						//sb.append("<tr><td>");
						sb.append("</div></div></div>");
						sb.append("<div class='row'><div class='col-sm-12'><div class='row'>");
						count=1;
					}
				}
			}
			else
			{
				//sb.append("<td class='multiLabel'><font color='red' size='2' weight='bold'>No Bed(s)Available Currently</font></td></tr>");
			}
		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString("HLPipd.getBedDetails() -->"+ e.getMessage());
			vo.setStrMsgType("1");
		}
		//sb.append("</table>");
		sb.append("</div></div>");
		
		/**sb.append("<div id='bedStatHelp' style='display:block'><br>");
		sb.append("<table width='100%' align='center' border='0' cellspacing='1px' cellpadding='0px'>");
		sb.append("<tr><td width='5%' class='TITLE'>");
		sb.append("<div id='plusonLineIdH' style='display:block'>");
		sb.append("<img style='cursor: pointer; cursor: hand' src='../../hisglobal/images/plus.gif' name='plusonLineH' title='Show Details' onclick='showDetailsH();' ></div>");
		sb.append("<div id='minusonLineIdH' style='display:none'>");
		sb.append("<img style='cursor: pointer; cursor: hand' src='../../hisglobal/images/minus.gif' name='minusonLineH' title='Hide Details' onclick='hideDetailsH();' ></div></td>");
		sb.append("<td width='95%' class='TITLE'><div align='left'>&nbsp;&nbsp;Bed Color Help</div></td></tr></table>");
		sb.append("<div id='bedStatHelpDtl' style='display:none'>");
		sb.append("<table align='center' width='100%' bgcolor='grey' cellspacing='1px' cellpadding='0px'><tr>");**/
		
		/*sb.append("<td bgcolor='" + blockedCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Blocked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + occupiedCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Occupied</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + bookedCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Booked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + vacantCSS + "' width='10%'></td>\n");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Vacant</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + vacantButDirtyCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Vacant But Dirty</div></font></td>");*/
		
		
		/**sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedEmpty.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Vacant</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedOccupied.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Occupied</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedBlocked.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Blocked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedBooked.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Booked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedVacantDirty.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Vacant But Dirty</div></font></td>");
		
		sb.append("</tr></table></div>");**/
		return sb.toString();
	}

	public static String getCSS(String bedName, String status, String title,String bedCode, IpdVO vo) 
	{
		int strBedStatusCode = 0;
		String temp[] = status.replace("^", "#").split("#");
		strBedStatusCode = Integer.parseInt(temp[0]);
		return makeCss(bedName, strBedStatusCode, title, bedCode, vo);
	}

	/**
	 * This function decide css of bed at run time on the basis of bed status
	 * code
	 * 
	 * @param bedName
	 * @param bedStatusCode
	 * @return String onClick='getBedname(this)'
	 */
	public static String makeCss(String bedName, int bedStatusCode,String title, String bedCode, IpdVO vo) 
	{
		StringBuffer css = new StringBuffer("");
		//String str="<div class='col-xs-3'><div class='well'><h4 class='text-danger'><span class='label pull-right'>";
		String str="<div class='col-xs-3 col-md-3 col-sm-3'><div class='well'>";
		//String close="</span>"+bedName+"</h4></div></div>";
		String occupantDtl[]=title.toUpperCase().replace("-","#").split("#");
		String closeAval="<h6 class='text-success text-center'>"+bedName+"</h6><label class='aval text-center'>&nbsp;</label><p><label class='aval text-center'>AVAILABLE</label></p></div></div>";
		String closeNotAval="";
		if(occupantDtl.length>1)
			closeNotAval="<h6 class='text-danger text-center'>"+bedName+"</h6><hr style='border:1px outset Tomato'><label class='aval text-center'>OCCUPANT : "+occupantDtl[0]+"<p>"+occupantDtl[1]+"</p></label></div></div>";
		else
			closeNotAval="<h6 class='text-danger text-center'>"+bedName+"</h6><label class='aval text-center'><p></label><label class='aval text-center'>NOT AVAILABLE</label></p></div></div>";
		
		String[] strSplitTitle = title.replace(" | ", "#").split("#");
		switch (bedStatusCode) 
		{
		case 10:
			if (vo.getStrBedChartMode().equals("process_Other"))
				/*css = "<td class='bedbutton' bgcolor='" + vacantCSS+ "' width='25%' id='td"+ bedCode
						+ "' onmouseover='xstooltip_show(\"tooltip" + bedCode
						+ "\", this.id, 0,30);this.style.color=\"#C0C0C0\"' onmouseout='xstooltip_hide(this.id);this.style.color=\"#000000\"'><div id='" + bedName
						+ "' onclick='return selBed(this," + bedCode + ");'>"
						+ bedName + "</div></td>";*/
				css.append(str+"<img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedEmpty.png'>"+closeAval);
				//css.append(str+"<i class='fa fa-bed'  style='color: green;font-size: smaller;'></i>"+closeAval);
			//css = "<td width='25%' id='td"+ bedCode+ "'><img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedEmpty.png'><br><b>"+bedName+"</td>";
			else
				/*css = "<td class='bedbutton' bgcolor='" + vacantCSS
						+ "' width='25%' id='td" + bedCode
						+ "' onmouseover='xstooltip_show(\"tooltip" + bedCode
						+ "\", this.id, 0,30);this.style.color=\"#C0C0C0\"' onmouseout='xstooltip_hide(this.id);this.style.color=\"#000000\"'>" + bedName + "</td>";*/
				//css = "<td width='25%' id='td"+ bedCode+ "'><img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedEmpty.png'><br><b>"+bedName+"</td>";
				css.append(str+"<img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedEmpty.png'>"+closeAval);
				//css.append(str+"<i class='fa fa-bed'  style='color: green;font-size: smaller;'></i>"+closeAval);
			break;

		case 11:
			/*css = "<td class='bedbutton' bgcolor='" + vacantButDirtyCSS
					+ "' width='25%' title='" + title
					+ "' onmouseover='xstooltip_hide();this.style.color=\"#C0C0C0\"' onmouseout='xstooltip_hide(this.id);this.style.color=\"#000000\"'>" + bedName + "</td>";*/
			css.append(str+"<img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedVacantDirty.png'>"+closeNotAval);
			//css.append(str+"<i class='fa fa-bed'  style='color: orange;font-size: smaller;'></i>"+closeAval);
			//css = "<td width='25%' id='td"+ bedCode+ "' ><img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedVacantDirty.png'><br><b>"+bedName+"</td>";
			break;

		case 12:

			/*css = "<td class='bedbutton' bgcolor='" + occupiedCSS
					+ "' width='25%' title='" + title
					+ "' onmouseover='xstooltip_hide();this.style.color=\"#C0C0C0\"' onmouseout='xstooltip_hide(this.id);this.style.color=\"#000000\"'>" + bedName + "</td>";*/
			
			css.append(str+"<img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedOccupied.png'>"+closeNotAval);
			//css.append(str+"<i class='fa fa-bed'  style='color: red;font-size: smaller;'></i>"+closeAval);
			//css = "<td width='25%' id='td"+ bedCode+ "' ><img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedOccupied.png'><br><b>"+bedName+"</td>";
			break;

		case 13:
			String strOnClick = "";
			try 
			{
				if (strSplitTitle[0].equals(vo.getStrCRNo())) 
				{
					strOnClick = " onclick='return selBed(this," + bedCode+ ");'";
				}
			} 
			catch (Exception e) 
			{
			}
			/*css = "<td class='bedbutton' bgcolor='" + blockedCSS
					+ "' width='25%' title='" + title + "'" + strOnClick
					+ "  onmouseover='xstooltip_hide();this.style.color=\"#C0C0C0\"' onmouseout='xstooltip_hide(this.id);this.style.color=\"#000000\"'>" + bedName + "</td>";*/
			css.append(str+"<img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedBlocked.png'>"+closeNotAval);
			//css.append(str+"<i class='fa fa-bed'  style='color: orange;font-size: smaller;'></i>"+closeAval);
			//css = "<td width='25%' id='td"+ bedCode+ "' ><img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedBlocked.png'><br><b>"+bedName+"</td>";
			break;

		case 14:
			/*css = "<td class='bedbutton' bgcolor='" + bookedCSS
					+ "' width='25%' title='" + title
					+ "' onmouseover='xstooltip_hide();this.style.color=\"#C0C0C0\"' onmouseout='xstooltip_hide(this.id);this.style.color=\"#000000\"'>" + bedName + "</td>";*/
			css.append(str+"<img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedBooked.png'>"+closeNotAval);
			//css.append(str+"<i class='fa fa-bed'  style='color: orange;font-size: smaller;'></i>"+closeAval);
			//css = "<td width='25%' id='td"+ bedCode+ "' ><img onmouseover='xstooltip_show(\"tooltip" + bedCode+ "\", this.id, 0,30);this.className=\"bedImagesFocus\"' onmouseout='xstooltip_hide(this.id);this.className=\"bedImages\"' class='bedImages' src='../../hisglobal/images/BedBooked.png'><br><b>"+bedName+"</td>";
			break;
		}
		return css.toString();
	}

	/** ****bed Status view ends***** */

	public static String makeDivToolTip(String bedCode, IpdVO vo) {
		StringBuffer st = null;
		String[] strBedProperties = null;
		try {
			st = new StringBuffer();
			st.append("<div id='tooltip" + bedCode + "' class='xstooltip'>");
			st.append("<table border='0' align='center' cellpadding='0' cellspacing='0'>");
			st.append("<table style='border-width: 1px' bgcolor='black' ><tr class='HEADER'><td width='95%' nowrap='nowrap'>");
			st.append("Bed Properties</td>");
			//st.append("<td width='5%' bgcolor='#ffffff'>");
			//st.append("<img style='cursor: pointer;' src='"+vo.getStrImagePath()+"/hisglobal/images/stop.png'");
			//st.append(" title='Click to Close Bed Properties Popup' onclick='xstooltip_hide()'></td>");
			st.append("</tr><tr><td class='CONTROL' colspan='2'  nowrap='nowrap'>");
			strBedProperties = vo.getMapBedProperties().get(bedCode).replace("^", "#").split("#");
			for (int nTmp = 0; nTmp < strBedProperties.length; nTmp++) {
				st.append(strBedProperties[nTmp]);
				if (nTmp != strBedProperties.length - 1)
					st.append("</td></tr><tr><td class='CONTROL' colspan='2'>");
			}
			st.append("</tr></table></div>");
		} catch (Exception _exception) {
			// _exception.printStackTrace();
		}
		return st.toString();
	}

	public static String setBedProperties(IpdVO vo) {
		StringBuffer sb = null;
		try {
			sb = new StringBuffer();
			Object[] setBedCode = vo.getMapBedProperties().keySet().toArray();
			for (int nTmp = 0; nTmp < setBedCode.length; nTmp++) {
				sb.append(makeDivToolTip(setBedCode[nTmp].toString(), vo));
			}
		} catch (Exception _exception) {
		}
		// System.out.println("Print "+sb.toString());
		return sb.toString();
	}
	
	public static String bedstatusdasboard(IpdVO vo) 
	{
		WebRowSet ws = vo.getBedstatusDashWS();
		WebRowSet ws2 = vo.getBedValuesDashWS();
		WebRowSet ws3 = vo.getRoomWS();
		//WebRowSet ws4 = vo.getRoomBedPropWS();
		
		int wsSize = ws.size();
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		try 
		{
			
			
			
			if (wsSize != 0)
			{
				int i=0;
				sb.append("<div class='row text-center' id='wardStatSection'>");
				
				
				while(ws.next())
				{
					String wardCode=ws.getString(1);
					sb.append("<div class='col-sm-2' style='height: 180px;'><b>"+ws.getString(2)+"</b><br><a href='#"+ws.getString(1)+"' href1='#"+ws.getString(1)+"'><canvas id='myCanvas"+i+++"' limit='"+ws.getString(6)+"' width='120' height='120'></canvas></a></div>");
					if(ws2!=null && ws2.size()>0)
					{
						//System.out.println("1>>"+ws.getString(1));
						
						sb2.append("<div class='panel panel-primary' id='"+ws.getString(1)+"' style='box-shadow: 0 0 4px 2px grey; margin:10px 10px;'>");
						sb2.append("<div class='panel-heading' onclick='removeColapse(this)'>");
						sb2.append("<h4 style='display: inline;'>"+ws.getString(2)+"</h4>");
						sb2.append("<div style='float: right;'>");
						sb2.append("<h4 style='display: inline;'>Bed Strength: <b class='counter2'>"+ws.getString(3)+"</b>&nbsp;<i class='fa fa-bed' style='color: black'></i></h4>");
						sb2.append("<h4 style='display: inline;'>&nbsp;&nbsp;&nbsp;&nbsp;Occupied: <b class='counter2'>"+ws.getString(5)+"</b>&nbsp;<i class='fa fa-bed' style='color: red;'></i></h4>");
						sb2.append("<h4 style='display: inline;'>&nbsp;&nbsp;&nbsp;&nbsp;Vacant: <b class='counter2'>"+ws.getString(4)+"</b>&nbsp;<i class='fa fa-bed' style='color: #00FF00;'></i></h4>");					
						
						sb2.append("<h4 style='display: inline;'>&nbsp;&nbsp;&nbsp;&nbsp;Blocked: <b class='counter2'>"+ws.getString(7)+"</b>&nbsp;<i class='fa fa-bed' style='color: orange;'></i></h4>"); // added by manisha dt:13.11.18		
						sb2.append("<h4 style='display: inline;'>&nbsp;&nbsp;&nbsp;&nbsp;Booked: <b class='counter2'>"+ws.getString(8)+"</b>&nbsp;<i class='fa fa-bed' style='color: yellow;'></i></h4>");					
						sb2.append("<h4 style='display: inline;'>&nbsp;&nbsp;&nbsp;&nbsp;VacantButDirty: <b class='counter2'>"+ws.getString(9)+"</b>&nbsp;<i class='fa fa-bed' style='color: cyan;'></i></h4>");	//end	
						
						sb2.append("</div></div>");
						
						sb2.append("<div class='panel-body'>");
						if(ws3.size()!=0)
						{
							ws3.beforeFirst();
							while(ws3.next())
							{
								String wardCodeNew=ws3.getString(1);
								String roomCode=ws3.getString(2);
								if(ws3.getString(1).equals(ws.getString(1)))
								{
									sb2.append("<h4>"+ws3.getString(3)+"");
								
									
									String rp=ws3.getString(5);
									
									sb2.append("");
										if(!rp.equals(""))
										{
											sb2.append("&nbsp;&nbsp;");
											
												for( int pCount=0;pCount<rp.split(",").length;pCount++)
												{
													sb2.append("<div class='popupToast' >");
													sb2.append("&nbsp;<i class='fas fa-"+rp.split(",")[pCount]+"'></i>&nbsp;");
													sb2.append("<span class='popuptextToast'>"+rp.split(",")[pCount]+"</span>");
													sb2.append("</div>");
												}
												
										
										}
										
									sb2.append("</h4>");
									
									sb2.append("<div class='flex'>");
									
									ws2.beforeFirst();
									while(ws2.next())
									{
										String roomCodeNew=ws2.getString(2);
										//if(ws2.getString(1).equals(ws.getString(1)))
										if(ws2.getString(2).equals(ws3.getString(2)) && ws2.getString(1).equals(ws3.getString(1)))
										{
											
											//System.out.println("3>>"+ws2.getString(2));
											if(ws2.getString(4).equals("10"))//Available
											{
												sb2.append("<div class='flex1 available'>");
												sb2.append("<div class='panel-body' data-toggle='modal' data-target='#myModal'>");
												sb2.append("<p><b style='font-family: 'Orbitron', sans-serif;'>"+ws2.getString(3)+"</b></p>");
												sb2.append("<h1><i class='fa fa-bed' style='color: green;'></i></h1><p style='color: green;'>Available</p>");
												String bp=ws2.getString(8);
												
												sb2.append("<h6>");
													if(!bp.equals(""))
													{
														for( int pCount=0;pCount<bp.split(",").length;pCount++)
														{
															sb2.append("<div class='popupToast' >");
															sb2.append("<i class='fas fa-"+bp.split(",")[pCount]+"'></i>&nbsp;");
															sb2.append("<span class='popuptextToast container-fluid' >"+bp.split(",")[pCount]+"</span>");
															sb2.append("</div>");
														}
															
													}
													
												sb2.append("</h6>");	
												
													
												sb2.append("</div></div>");
											}
											else
											{
												sb2.append("<div class='flex1 unavailable'>");
												String wsData=ws2.getString(5);
												//System.out.println("wsData"+wsData);
												String patData[]=ws2.getString(5).split("#");
												StringBuffer patName=new StringBuffer();
												StringBuffer cr=new StringBuffer();
												StringBuffer crno=new StringBuffer();
												StringBuffer dt=new StringBuffer();
												StringBuffer dept=new StringBuffer();
				                                StringBuffer patientName=new StringBuffer();
												for(int x=0;x<=patData.length-1;x++)
												{
													String[] data=patData[x].replace("^","#").split("#");
													if(patData.length>1)
													{
														patientName.append(data[0]+"<br>");
														cr.append(data[1]+"<br>");
														
														patName.append(" ("+(x+1)+") "+data[0]);
														crno.append(" ("+(x+1)+") "+data[1]);
														dt.append(" ("+(x+1)+") "+data[2]);
														dept.append(" ("+(x+1)+") "+data[3]);
													}
													else
													{
														patientName.append(data[0]);
														cr.append(data[1]);

														patName.append(data[0]);
														crno.append(data[1]);
														dt.append(data[2]);
														dept.append(data[3]);
													}
											
													
												}
												//sb2.append("<div class='panel-body' patName='patient1' crNo='5155333513' addDate='25-06-2018' data-toggle='modal' data-target='#myModal2'>");
												sb2.append("<div class='panel-body' patDeptName='"+dept+"' patName='"+patName+"' patNameL='"+patName+"' crNo='"+crno+"' addDate='"+dt+"' data-toggle='modal' data-target='#myModal2'>");
												//sb2.append("<p style='display:none;' class='deptName'>"+dept+"</p>");
												sb2.append("<p><b style='font-family: 'Orbitron', sans-serif;' class='bedName'>"+ws2.getString(3)+"</b></p>");
												sb2.append("<input type='hidden' name='deptNameTile' value='"+dept+"'>");
												sb2.append("<p></b></p>");
												
												
												// for adding color codes to different bed status  - added by manisha gangwar dt : 13.11.18 
												// 13-Blocked, 14-Booked, 12-Occupied, 10-Vacant, 11-Vacant but dirty 
												String boxcolor=""; 
												if(ws2.getString(4).equals("13")) sb2.append("<h1><i class='fa fa-bed' style='color: orange'></i></h1>");
												else if(ws2.getString(4).equals("14")) sb2.append("<h1><i class='fa fa-bed' style='color: yellow'></i></h1>");
												else if(ws2.getString(4).equals("11")) sb2.append("<h1><i class='fa fa-bed' style='color: cyan'></i></h1>");
												else sb2.append("<h1><i class='fa fa-bed' style='color: red'></i></h1>");
												//System.out.println("boxcolor: "+ boxcolor);
												sb2.append("<p><b class='occup'> Occupant Details</b></p><hr style='border:1px outset Tomato'><p class='occupDetail'>"+cr+"</p><p class='occupDetail patName'>"+patientName+"</p>");
												sb2.append("</div></div>");
											}
										}							
										
									}
									sb2.append("</div>");
									
								}
									
							}
						}
						sb2.append("</div></div>");
						
						/*sb2.append("<div class='flex'>");
						
						ws2.beforeFirst();
						while(ws2.next())
						{
							if(ws2.getString(1).equals(ws.getString(1)))
							{
								if(ws2.getString(3).equals("10"))//Available
								{
									sb2.append("<div class='flex1 available'>");
									sb2.append("<div class='panel-body' data-toggle='modal' data-target='#myModal'>");
									sb2.append("<h1><i class='fa fa-bed' style='color: green;'></i></h1>");
									sb2.append("<p><b style='font-family: 'Orbitron', sans-serif;'>"+ws2.getString(2)+"</b></p>");
									sb2.append("</div></div>");
								}
								else
								{
									sb2.append("<div class='flex1 unavailable'>");
									sb2.append("<div class='panel-body' patName='patient1' crNo='5155333513' addDate='25-06-2018' data-toggle='modal' data-target='#myModal2'>");
									sb2.append("<h1><i class='fa fa-bed' style='color: red;'></i></h1>");
									sb2.append("<p><b style='font-family: 'Orbitron', sans-serif;'>"+ws2.getString(2)+"</b></p>");
									sb2.append("</div></div>");
								}
							}							
							
						}
						sb2.append("</div></div></div>");*/
					}
				}
				sb.append("</div>");
				
				/*
				sb.append("<hr><div class=\"container-fluid\" style=\"background: linear-gradient(to bottom, #337ab7, white)\">" + 
						"			  	" + 
						"			    <h2 id=\"wardHeader\" class=\"text-center\" style=\"letter-spacing: 4px;\">Wardwise Category</h2>" + 
						"		    </div>" + 
						"			<p class=\"text-center\" style=\"color: #337ab7\">*Select from above ward</p>" + 
						"			<table class=\"table table-striped\">" + 
						"				<thead>" + 
						"					<tr>" + 
						"						<th>" + 
						"							<h4>Department:</h4> </th><th><select name=\"department\" class=\"form-control\">" + 
						"				 				<option value=\"0\">All</option>" + 
						"				 				<!--  " + 
						"				 				<option value=\"1\">Cardiology</option>" + 
						"				 				<option value=\"2\">General Medicine</option>-->" + 
						"				 			</select>" + 
						"		 				</th>" + 
						"		 				<th>" + 
						"		 					<h4>Ward:</h4> </th><th><select name=\"ward\" class=\"form-control\" onchange=\"showWardChoice(this,event)\">" + 
						"				 				<option value=\"0\">All</option>" + 
						"				 				<!-- <option value=\"1\">VIP Ward</option>" + 
						"				 				<option value=\"2\">Private Ward</option>" + 
						"				 				<option value=\"3\">New Private Ward</option>	" + 
						"				 				<option value=\"4\">General Ward</option>" + 
						"				 				<option value=\"5\">Female Ward</option>-->" + 
						"				 			</select>" + 
						"		 				</th>" + 
						"		 				<th>" + 
						"		 					<h4>Availibity:</h4> </th><th><select name=\"ward\" class=\"form-control\" onchange=\"showAvailable(this,event)\">" + 
						"				 				<option value=\"0\">All</option>" + 
						"				 				<!-- <option value=\"2\">Available</option>-->" + 
						"				 			</select>" + 
						"		 				</th>" + 
						"					</tr>" + 
						"				</thead>" + 
						"			</table>");
				*/
				sb.append("<div id='wardSection'>");
				sb.append(sb2.toString());

				sb.append("</div>");
				
				
			}
			else
			{
				sb.append("<td class='multiLabel'><font color='red' size='2' weight='bold'>No Bed(s)Available Currently</font></td></tr>");
			}
		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString("HLPipd.bedstatusdasboard() -->"+ e.getMessage());
			vo.setStrMsgType("1");
		}
		return sb.toString();
	}
	public static String getBedDetails(IpdVO vo) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = vo.getBedDetailWS();
		int size = Integer.parseInt(beds_per_row);
		int pix_row_height = Integer.parseInt(bed_row_height);
		int def_height = 0;
		int count = 1;
		int bedRows = 0;
		int newHeight = 0;
		int addPixl = 0;
		int wsSize = ws.size();

		/** *setting default window size according to parent process** */

		if (vo.getStrBedChartMode().equals("process_Other"))
			def_height = Integer.parseInt(default_height);
		else
			def_height = Integer.parseInt(default_height_adm);

		/** *************************end****************************** */

		if (size > 4 || size < 1)
			size = 4; // overrides global settings

		/** *** Logic for dynamic row-wise popup height manipulation **** */

		bedRows = wsSize / size;

		/** ****checks to see no of rows***** */
		if ((bedRows * size) < wsSize)
			bedRows = bedRows + 1;
		if (wsSize == 0)
			bedRows = 1;
		/** *************end**************** */
		addPixl = (bedRows - 1) * pix_row_height;
		newHeight = def_height + addPixl;

		vo.setStrWinResize(newHeight); // setting hidden field attribute on popup

		/** ***************************** end *************************** */

		try 
		{
			sb.append("<div id='myModal' class='modal fade'><div class='modal-dialog'><div class='modal-content'><div class='modal-header'><button type='button' class='close' data-dismiss='modal' aria-hidden='true'>&times;</button><h5 class='modal-title'>Bed Status Dashboard</h5></div><div class='modal-body'>");
			
			//sb.append("<table width='100%' align='center' border='0' bgcolor='black' cellspacing='0'>");
			sb.append("<input type='hidden' name='strWin_Resize' value='"+ newHeight + "'>");
			sb.append("<input type='hidden' name='strSelBed' value=''>");
			//sb.append("<tr><td width='95%' class='TITLE'><div align='center'><font size='2' color=''>Bed Status Chart</font></div></td>");
			//sb.append("<td width='5%' class='TITLE'><img style='cursor:pointer;' src='"+vo.getStrImagePath()+"/hisglobal/images/stop.png' title='Click to Close Bed Window' onClick='window.close();'></td></tr></table>");
			//sb.append("<td width='5%' class='TITLE'></td></tr></table>");
			//sb.append("<br><table width='85%' align='center' border='0' cellspacing='1px'>");
			//sb.append("<tr><td>");
			sb.append("<div class='row'><div class='col-sm-12'><div class='row'>");
			if (wsSize != 0)
			{
				while(ws.next())
				{
					if(count<=size)
					{
						String temp[] = ws.getString(1).replace("^","#").split("#");
						sb.append("<input type='hidden' id='" + temp[0]+ "'name='bedCode' value='"+ ws.getString(2) + "'>");
						sb.append(getCSS(ws.getString(2), temp[0], ws.getString(3), temp[1], vo));
						count++;
					}
					else
					{
						ws.previous();
						//sb.append("</div></div></div></td></tr>");
						//sb.append("<tr><td>");
						sb.append("</div></div></div>");
						sb.append("<div class='row'><div class='col-sm-12'><div class='row'>");
						count=1;
					}
				}
			}
			else
			{
				//sb.append("<td class='multiLabel'><font color='red' size='2' weight='bold'>No Bed(s)Available Currently</font></td></tr>");
			}
		} 
		catch (Exception e) 
		{
			vo.setStrErrMsgString("HLPipd.getBedDetails() -->"+ e.getMessage());
			vo.setStrMsgType("1");
		}
		//sb.append("</table>");
		sb.append("</div></div></div>");
		
		/**sb.append("<div id='bedStatHelp' style='display:block'><br>");
		sb.append("<table width='100%' align='center' border='0' cellspacing='1px' cellpadding='0px'>");
		sb.append("<tr><td width='5%' class='TITLE'>");
		sb.append("<div id='plusonLineIdH' style='display:block'>");
		sb.append("<img style='cursor: pointer; cursor: hand' src='../../hisglobal/images/plus.gif' name='plusonLineH' title='Show Details' onclick='showDetailsH();' ></div>");
		sb.append("<div id='minusonLineIdH' style='display:none'>");
		sb.append("<img style='cursor: pointer; cursor: hand' src='../../hisglobal/images/minus.gif' name='minusonLineH' title='Hide Details' onclick='hideDetailsH();' ></div></td>");
		sb.append("<td width='95%' class='TITLE'><div align='left'>&nbsp;&nbsp;Bed Color Help</div></td></tr></table>");
		sb.append("<div id='bedStatHelpDtl' style='display:none'>");
		sb.append("<table align='center' width='100%' bgcolor='grey' cellspacing='1px' cellpadding='0px'><tr>");**/
		
		/*sb.append("<td bgcolor='" + blockedCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Blocked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + occupiedCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Occupied</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + bookedCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Booked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + vacantCSS + "' width='10%'></td>\n");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Vacant</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td bgcolor='" + vacantButDirtyCSS + "' width='10%'></td>");
		sb.append("<td class='multiLabel' width='60%'><font size='1' color='black'><div align='left'>Vacant But Dirty</div></font></td>");*/
		
		
		/**sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedEmpty.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Vacant</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedOccupied.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Occupied</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedBlocked.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Blocked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedBooked.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Booked</div></font></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='2%'><img class='bedImagesSmall' src='../../hisglobal/images/BedVacantDirty.png'></td>");
		sb.append("<td class='multiLabel' width='98%'><font size='1' color='black'><div align='left'>Vacant But Dirty</div></font></td>");
		
		sb.append("</tr></table></div>");**/
		return sb.toString();
	}
	
	public static String getPatientListingViewModification_BS(IpdVO voObj) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = voObj.getGblWs1();

		try 
		{
			int addHeight = 260;
			int i=0;

			if (ws != null) 
			{
				if (ws.size() != 0) 
				{
					sb.append("<table id='Datatable' class='table ' style='text-align:center' >");
					sb.append("<thead>");
					sb.append("<tr>");
					/*sb.append("<th width='1%'></th>");*/
					sb.append("<th width='3%' >CR No.</th>");
					sb.append("<th width='5%' style='text-align:left;' >Patient Name</th>");
					sb.append("<th width='1%'>Category</th>");
					
					if (voObj.getStrValue1().equals("1") || voObj.getStrValue1().equals("2") || voObj.getStrValue1().equals("3")) 
					{
						sb.append("<th width='18%'>Admission No.</th>");
						sb.append("<th width='21%'>Leave Date</th>");						
					} 
					else if (voObj.getStrValue1().equals("4")) 
					{
						sb.append("<th width='18%'class='multiLabel'>Prop. Admission Date</th>");
						sb.append("<th width='21%'class='multiLabel'>Booking Date</th>");
					} 
					else 
					{
						sb.append("<th width='5%'>Admission No.</th>");
						sb.append("<th width='3%'>Admission Date & Time</th>");
						/*sb.append("<th width='2%' style='text-align:right' >Edit</th>");
						sb.append("<th width='2%' style='text-align:right'>Cancel</th>");*/
						sb.append("<th width='1%'>Actions</th>");
					}
					sb.append("</tr>");
					sb.append("</thead>");

					sb.append("<tbody >");
					while(ws.next())
					{
						/*sb.append("<div id='admDataDivId" + i+ "' style='display:block'>");*/
						sb.append("<tr>");
						/*sb.append("<td  width='1%'></td>");*/
						sb.append("<td  width='3%' >"+ws.getString(1)+"</td>");
						sb.append("<td  width='5%' style='text-align:left;'>"+ws.getString(2)+"</td>");
						
						String arr[]={"46c6d3;","FF9999","f4d03f","FFCC99","2ecc71","3498db","9999FF","CC99FF","FF99FF","FF99CC","E0E0E0"};/*,"FF00FF","FF007F","808080",};*/
						
						if(i==10)
							i=0;
						sb.append("<td  width='1%'><label  style='background-color: #"+arr[i]+";color: #fff;border-radius: .15em;padding: 1px 5px;font-weight: 600;'>"+ws.getString(5)+"</label></td>");
						//sb.append("<td  width='2%' style='text-align:left;'><span class='label label-sm label-success'>"+ws.getString(5)+"</span></td>");
						
					
						sb.append("<td  width='5%'>"+ws.getString(3)+"</td>");
						sb.append("<td  width='3%'>"+ws.getString(4)+"</td>");
						/*sb.append("<td width='2%'><button type='button' class='float-right btn btn-outline-info' name='strCrNomod' style='padding: .175rem .35rem; line-height: 0.8'");
						sb.append(ws.getString(1));
						sb.append("' onclick='setCrData("+ws.getString(1)+");'><i title='Admission Modification' class='fas fa-pen-square'></i></button></td>");
						sb.append("<td width='2%'><button type='button' class='float-right btn btn-outline-info' style='padding: .175rem .35rem; line-height: 0.8;' onclick=openMenu('ADMCANCEL',"+ws.getString(1)+")><i title='Admission Cancel' class='fas fa-ban' style='color:red;'></i></button></td>");*/
						
						
						/*sb.append("<td width='1%'><button type='button' class='float-left btn btn-outline-info' name='strCrNomod' style='padding: .175rem .35rem; line-height: 0.8'");
						sb.append(ws.getString(1));
						sb.append("' onclick='setCrData("+ws.getString(1)+");'><i title='Admission Modification' class='fas fa-pen-square'></i></button>");
						sb.append("<button type='button' class='btn btn-outline-info' style='padding: .175rem .35rem; line-height: 0.8;' onclick=openMenu('ADMCANCEL',"+ws.getString(1)+")><i title='Admission Cancel' class='far fa-trash-alt' style='color:red;'></i></button>");*/
					
						
						
						sb.append("<td width='1%'>");
						sb.append("<div class='popupToast' >");
						sb.append("<button type='button' class='float-left btn btn-outline-info' name='strCrNomod' style='padding: .175rem .35rem; line-height: 0.8'");
						sb.append(ws.getString(1));
						sb.append("' onclick='setCrData("+ws.getString(1)+");'><i  class='fas fa-pen-square'></i></button>");
						sb.append("<span style='margin-left:-93px!important' class='popuptextToast'>Admission Modification</span>");
						sb.append("</div>");
						
						sb.append("<div class='popupToast' >");
						sb.append("<button type='button' class='btn btn-outline-info' style='padding: .175rem .35rem; line-height: 0.8;' onclick=openMenu('ADMCANCEL',"+ws.getString(1)+")><i title='Admission Cancel' class='far fa-trash-alt' style='color:red;'></i></button>");
						sb.append("<span class='popuptextToast'>Admission Cancellation</span>");
						sb.append("</div>");
						
						
						
						
						
					/*	sb.append("<td width='1%'>");
						sb.append("<div class='dropdown'>");
						sb.append(" <button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>");
						sb.append("   Dropdown button");
						sb.append("  </button>");
						sb.append("  <div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>");
						sb.append("    <a class='dropdown-item' href='#'>Action</a>");
						sb.append("    <a class='dropdown-item' href='#'>Another action</a>");
						sb.append("    <a class='dropdown-item' href='#'>Something else here</a>");
						sb.append("  </div>");
						sb.append("</div>");*/
						
						/*sb.append("<td width='2%'>");
						sb.append("<div class='dropdown' style=''>");
						sb.append("<button type='button' class='btn btn-outline-info btn-sm dropdown-toggle' data-toggle='dropdown'>");
						sb.append("Actions");
						sb.append("</button>");
						sb.append("<div class='dropdown-menu'>");
						sb.append("<a class='dropdown-item' href='#'>Modification</a>");
						sb.append("<div class='dropdown-divider'></div>");
						sb.append("<a class='dropdown-item' href='#'>Cancellation</a>");
						sb.append("</div>");
						sb.append("</div>");*/
						
						
						
						
						sb.append("</td>");
						sb.append("</tr>");						
						/*sb.append("</div>");*/
						i++;
					}
					sb.append("</tbody >");
					sb.append("</table>");
					
				} 
				else 
				{
					
					sb.append("<table id='Datatable' class='table ' style='text-align:center;height:1vw;' >");
					sb.append("<thead>");
					sb.append("<tr>");
					/*sb.append("<th width='1%'></th>");*/
					sb.append("<th width='3%' >No Record Found</th>");
					sb.append("</tr>");
					
					sb.append("</thead>");
					sb.append("<tbody>");
					sb.append("<tr><td></td>");					
					
					sb.append("</tr>");
					
					sb.append("</tbody>");
					sb.append("</table>");
					
					
					/*
					 * //sb.append("<div class='container'>"); sb.
					 * append("<div class='alert alert-danger' style='padding: .5rem 1.25rem; line-height: 0.8;'>"
					 * ); sb.append("<label>No Record Found</label>"); sb.append("</div>");
					 */
				//	sb.append("</div>");
				}
		
			} 
			else 
			{
				throw new Exception("Patient List WebRowSet is Null");
			}	
			
			
			
			voObj.setStrWinResize(addHeight);
			
		} 
		catch (Exception e) 
		{
			new HisException("ADT","ipd.HLPIpd.getPatientListingView()-->", e.getMessage());
		}

		return sb.toString();

	}

	public static String bedstatusdasboardToal(IpdVO voObj) {
		
		WebRowSet ws=null;
		int totalWards=0, totalBeds=0, totalVaccent=0, totalOccupied=0, totalBlocked=0, totalBooked=0, totalVacDirty=0;
		String temp="";
		String a="<font color='tomato'>",b="<font color='green'>",c="</font>";
		String e="Ward Strength:",f="Bed Strength:",g="Vacant:",h="Occupied:",i="Blocked:",j="Booked:",vbd="VacantButDirty:";
		String k="&nbsp;&nbsp;&nbsp;&nbsp;";
		String l="red'",m="green'",n="orange'",o="yellow'",p="cyan'",bl="black'";
		String q="style='color:";
		String r="<i class='fa fa-bed'",s="></i>";
		
		
		try{
			ws= voObj.getBedstatusDashWS();
			ws.beforeFirst();
			
			if(ws.size()!=0)
			{
				
				while(ws.next())
				{
					++totalWards;
					//totalWards+=ws.getInt(1);
					totalBeds+=ws.getInt(3);
					totalVaccent+=ws.getInt(4);
					totalOccupied+=ws.getInt(5);
					totalBlocked+=ws.getInt(7);
					totalBooked+=ws.getInt(8);
					totalVacDirty+=ws.getInt(9);
				
				}
			}	
			
		}catch(Exception ex){
			ex.printStackTrace();
			new HisException("ADT","ipd.HLPIpd.bedstatusdasboardToal()-->", ex.getMessage());
		}
		
		temp="<h3><i class='fa fa-home'></i>"+	
								b+ e +c+ 	a+ totalWards+c+	
				 k+		r+ q+bl +s+		b+ f +c+ 	a+ totalBeds		+c+		
				 k+ 	r+ q+m +s+		b+ g +c+ 	a+ totalVaccent		+c+	
				 k+ 	r+ q+l +s+		b+ h +c+ 	a+ totalOccupied	+c+	 
				 k+		r+ q+n +s+		b+ i +c+ 	a+ totalBlocked		+c+	
				 k+		r+ q+o +s+		b+ j +c+ 	a+ totalBooked		+c+	
				 k+		r+ q+p +s+		b+ vbd +c+ 	a+ totalVacDirty	+c+	
			"</h3>";
		
		//made pattern for loop in future
		
		return temp;
	}
	
	public static String jobtracking_hlp(IpdVO voObj) 
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = voObj.getGblWs1();

		try 
		{
			

			if (ws != null) 
			{
				if (ws.size() != 0) 
				{
					sb.append("<table id='dtable' class='table tdfont'>");
					sb.append("<thead>");
					sb.append("<tr>");
					sb.append("<th>Job Name</th>");					
					sb.append("<th>Start Date</th>");
					sb.append("<th>End Date</th>");
					sb.append("<th>Status</th>");
					//sb.append("<th>Duration</th>");
					sb.append("<th>Error</th>");
					sb.append("</tr>");
					sb.append("</thead>");
					
					sb.append("</tbody>");

					while(ws.next())
					{
						sb.append("<tr>");
						sb.append("<td width='15%'><div align='left'>"+ws.getString(1)+"</div></td>");
						sb.append("<td width='15%'>"+ws.getString(3)+"</td>");
						sb.append("<td width='15%'>"+ws.getString(4)+"</td>");
						if(ws.getString(2).equalsIgnoreCase("Failed"))
							sb.append("<td width='15%'><a class='btn btn-danger btn-sm fixed'>"+ws.getString(2)+"</a></td>");
						else
							sb.append("<td width='15%'><a class='btn btn-success btn-sm fixed'>"+ws.getString(2)+"</a></td>");
						
						//sb.append("<td width='20%'>"+ws.getString(5)+"</td>");
						sb.append("<td width='40%'><div align='left'>"+ws.getString(5)+"</div></td>");
						sb.append("</tr>");
					}					
					
					sb.append("</table>");				 
				} 
				else 
				{
					sb.append("<table width='100%' align='center' cellspacing='0'>");
					sb.append("<tr><td class='multiControl'><font color='red'>No Data Found. Check pgAgent</font></td></tr>");
					sb.append("</table>");
					
				}
			} 
			else 
			{
				throw new Exception("Data WebRowSet is Null");
			}
			// System.out.println("addHeight->"+addHeight);
			sb.append("<input type='hidden' name='strWin_Resize' value=''>");
			voObj.setStrWinResize(0);
		//	sb.append("<input id='arrowk' type='hidden' value='2'>");
			sb.append("<script>");
			sb.append("  modalDataTable();");
			sb.append("</script>");
		} 
		catch (Exception e) 
		{
			new HisException("Patient Listing","ipd.HLPIpd.getPatientListingView()-->", e.getMessage());
		}

		return sb.toString();
	}
}
