package billing.transactions;

import hisglobal.exceptions.HisException;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

public class BillPrintTransHLP {

	public static String getOnlineRequestDtls(WebRowSet wb) {

		StringBuffer sb = new StringBuffer();

		try {

			if (wb != null && wb.size() > 0) {
				try {

					sb
							.append("<table class='TABLEWIDTH' align='center' border='0'> ");

					sb
							.append("<tr><td colspan='6' class='TITLE'>OnLine Request</td></tr>");

					sb.append("<tr><td width='5%' class='multiLabel'></td>");
					sb.append("<td width='16%' class='multiLabel'>Req No</td>");
					sb
							.append("<td width='16%' class='multiLabel'>Req Date</td>");
					sb
							.append("<td width='16%' class='multiLabel'>Dept/Ward</td>");
					sb
							.append("<td width='16%' class='multiLabel'>Hospital Service</td>");
					sb
							.append("<td width='16%' class='multiLabel'>Request Type</td></tr>");

					for (int i = 0; wb.next(); i++) {
						// Appended Value is REQ_NO +"@"+ SBLNUM_BSERVICE_ID
						// +"@"+HBLNUM_PATACCOUNT_NO+"@"+Request Type;
						String strChk = wb.getString(1) + "^" + wb.getString(3)
								+ "^" + wb.getString(4) + "^" + wb.getString(5);

						String strReqNo = wb.getString(1);
						String strReqDate = wb.getString(2);
						String strHospitalService = wb.getString(6);
						String strReqType = wb.getString(7);
						String strDeptName = wb.getString(8);
						

						if (strReqNo == null)
							strReqNo = "";
						if (strReqDate == null)
							strReqDate = "";
						if (strHospitalService == null)
							strHospitalService = "";
						if (strReqType == null)
							strReqType = "";
						if (strDeptName == null)
							strDeptName = "";

						sb
								.append("<tr><td width='5%' class='multiLabel'><input type='radio' style='cursor:pointer;cursor:hand' name='requestOption' id='strChk_values' value=\""
										+ strChk
										+ "\"  onClick='groupCheck(this);'");
						if (i == 0) {
							sb.append("checked></td>");
						} else {
							sb.append("</td>");
						}
						sb.append("<td width='16%' class='multiControl'>");
						sb.append(strReqNo);
						sb.append("</td>");
						sb.append("<td width='16%' class='multiControl'>");
						sb.append(strReqDate);
						sb.append("</td>");
						sb.append("<td width='16%' class='multiControl'>");
						sb.append(strDeptName);
						sb.append("</td>");
						sb.append("<td width='16%' class='multiControl'>");
						sb.append(strHospitalService);
						sb.append("</td>");
						sb.append("<td width='16%' class='multiControl'>");
						sb.append(strReqType);
						sb.append("</td></tr>");
					}
					sb.append(" </table> ");
				} catch (SQLException e) {

					new HisException("Discount Approval Transaction",
							"OnLineReqDtl Tag.doStartTag() -->", e.getMessage());
				}
			} else {

				sb
						.append("<table class='TABLEWIDTH' align='center' border='0'> ");
				sb
						.append("<tr><td colspan='6' class='TITLE'>OnLine Request</td></tr>");
				sb
						.append("<tr><td colspan='6' width='5%' class='multiControl'><font style='color:red'>Request Not Available</font></td>");
				sb.append("</td></tr></table>");

			}
			// } else {

			// sb.append("<input type='hidden' name='tmpMsg' value=" + tmp
			// + ">");

			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new HisException("Discount Approval Transaction",
					"OnLineReqDtl Tag.doStartTag() -->", e.getMessage());

		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (Exception e) {
				}
				wb = null;
			}
		}

		return sb.toString();
	}

	
	public static String getPatientHeaderView(){
		
		StringBuffer sb = new StringBuffer("");
		
		sb.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
		sb.append("<tr><td width='5%' class='TITLE' align='center'>	");
		sb.append("<div id='plusonLineId' style='display: none'> ");
		sb.append("<img style='cursor: pointer;cursor: hand' src='../../hisglobal/images/plus.gif' "); 
		sb.append(" name='plusonLine'  onclick='showDetails();' /> </div> ");
		sb.append("<div id='minusonLineId'><img style='cursor:pointer;cursor:hand' ");
		sb.append("src='../../hisglobal/images/minus.gif' name='minusonLine' onclick='hideDetails();'> ");
		sb.append("</div> </td> <td colspan='7' class='TITLE'>Patient Details</td> </tr></table> ");
		
		return sb.toString();
		
	}
	
}
