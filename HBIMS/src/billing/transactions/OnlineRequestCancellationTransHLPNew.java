package billing.transactions;

import javax.sql.rowset.WebRowSet;

/**
 * @author Anshul Jindal
 * This file is HLP file
 * This file is a part of screen eg; PatientDetail, PopUp. 
 * The HTML code is written in this file.
 */
public class OnlineRequestCancellationTransHLPNew {

	String popUpData = "";

	static int i = 0;

	/**
	 * @param vo
	 * @return
	 */
	public static String onlineRequestDetails(OnlineRequestCancellationTransVO vo) 
	{
		WebRowSet wb = null;
		String temp[] = new String[25];
	//	String temp2[] = new String[2];
		int i = 0;
		StringBuffer br = null;
		try 
		{
			wb = vo.getStrOnlineReqDtlWs();

			br = new StringBuffer();

			br.append("<table class='table'>");
			br.append("<thead> ");
			/*br.append("<tr> ");
			br.append("<td colspan='6'><b> Online Request </b></td>");

			br.append("</tr>");*/
			
			br.append("<tr>");
			br
					.append(" <input type='hidden' name='noOfRows' id='noOfRows' value="
							+ wb.size() + " >");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>#</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Request No.</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Request Date</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Department/Ward</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Hospital Service</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Request Type</font></th>");
			br.append("</tr>");
			br.append("</thead> ");
			br.append("<tbody> ");

			if(wb.size()==0)
			{
				
				br.append("<DIV class='errMsg' align='center'> NO RECORD FOUND FOR ENTERED DATA </div>");

			}
			while (wb.next())
			{
				temp = wb.getString(20).replace('^', '#').split("#");
		//		temp2 = wb.getString(1).replace('/', '#').split("#");
				br.append("<TR>");

				br.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ "<INPUT TYPE='CHECKBOX' name='chk' id='chk" + i
						+ "' " + "VALUE='" + wb.getString(20) + "'</font></TD>");

				br.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'><a  class='btn btn-info viewbill'  onClick='showPopUp(this,\""+i+"\");'>" + wb.getString(1) + "</a>");

				br.append(" <input type='hidden' name='hideNo' id='hideNo" + i
						+ "' value=" + wb.getString(1) + " >");
				br.append(" <input type='hidden' name='flag' id='flag" + i
						+ "' value=" + "0" + " >");

				br.append("</font></TD><TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(2) + "</font></TD>");

				br.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(17) + "/" + temp[17] + "</TD>");

				br.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(15) + "</font></TD>");

				br.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(16) + "</font></TD>");

				br.append("</TR>");
				i++;
			}
			br.append("</tbody> ");
			br.append("</table>");

		} 
		catch (Exception e) 
		{
			vo.setStrMsgString("OnlineRequestCancellationTransHLP.onlineRequestDetails() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		finally 
		{
			if (wb != null)
				wb = null;
		}

		return br.toString();
	}

	/**
	 * @param vo
	 * @param index
	 * @return
	 */
	public static String getPopUpInfo(OnlineRequestCancellationTransVO vo,
			String index) {

		WebRowSet wb = null;
		String temp[] = new String[25];
		String discountTypeid = " ";
		String discountType = "";
		StringBuffer br = null;
		StringBuffer br1 = null;

		try {
			wb = vo.getStrPopUpWs();

			br = new StringBuffer();
			br1 = new StringBuffer();

			br.append("<table width='500' cellpadding='0' cellspacing='0'>");

			br
					.append("<tr class='HEADER' align='left'><td>&nbsp;Tariff Details</td> ");
			br
					.append("<td align='right'><img src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"popup\");'> </td></tr>");

			br.append("</tr>");
			br.append("</table> ");

			br.append("<table width='500' align='left'>");

			br.append("<tr>");

			br
					.append("<td class='multiLabel' WIDTH='25%' align='CENTER'>Tariff Name</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Unbilled Qty</td>");

			br
					.append("<td class='multiLabel' WIDTH='15%' align='CENTER'>Discount/Unit</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Approved By</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Approved Date</td>");

			br.append("</tr>");

			if(wb.size()==0)
			{
				br.append("<TR>");
				br.append("<TD colspan='5' CLASS='multiControl' >"
						+ "<DIV class='errMsg' align='center'> NO RECORD FOUND</div> " + "</TD>");

				br.append("</TR>");
			}
			while (wb.next()) {

				temp = wb.getString(23).replace('^', '#').split("#");
				discountTypeid = wb.getString(15);

				if (discountTypeid.equals("1")) {
					discountType = "fixed";
				} else if (discountTypeid.equals("2")) {
					discountType = " %";
				} else {
				}

				br.append("<TR>");

				br.append("<TD  WIDTH='20%' CLASS='multiControl'>"
						+ wb.getString(7) + "</TD>");

				br.append("<TD WIDTH='10%' CLASS='multiControl'>"
						+ wb.getString(11) + " " + wb.getString(18) + "</TD>");

				br.append("<TD WIDTH='10%' CLASS='multiControl' >"
						+ wb.getString(14) + " " + discountType + "</TD>");

				br.append("<TD WIDTH='40%' CLASS='multiControl' >" + temp[2]
						+ "</TD>");

				br.append("<TD WIDTH='20%' CLASS='multiControl' >" + temp[0]
						+ "</TD>");

				br.append("</TR>");

				br1.append(wb.getString(7) + "^" + wb.getString(11) + " "
						+ wb.getString(18) + "^" + wb.getString(14) + "^"
						+ temp[2] + "^" + temp[0] + "^" + discountTypeid + "^");

				i++;
			}

			br.append("</table>");
			br.append("@");
			br.append(br1);
			br.append("@");
			br.append(index);

		} catch (Exception e) {
			vo
					.setStrMsgString("OnlineRequestCancellationTransHLP.onlineRequestDetails() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (wb != null)
				wb = null;
		}
		return br.toString();

	}

	/**
	 * @param vo
	 * @return
	 */
	public static String getPopUpInfo2(OnlineRequestCancellationTransVO vo) {

		StringBuffer br = null;
		String data = "";
		String discountType = "";
		String temp[] = new String[20];

		try {

			data = vo.getStrPopUpData();
			/*
			 * data=data.concat("^anshul"); System.out.println("data-->"+data);
			 */
			temp = data.replace('^', '#').split("#");
			// System.out.println("temp length-->" + temp.length);

			br = new StringBuffer();

			br.append("<table width='400' cellpadding='0' cellspacing='0'>");

			br
					.append("<tr class='HEADER' align='left'><td>&nbsp;Tariff Details</td> ");
			br
					.append("<td align='right'><img src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"popup\");'> </td></tr>");

			br.append("</tr>");
			br.append("</table> ");

			br.append("<table width='400' align='left'>");

			br.append("<tr>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Tariff Name</td>");

			br
					.append("<td class='multiLabel' WIDTH='10%' align='CENTER'>Unbilled Quantity</td>");

			br
					.append("<td class='multiLabel' WIDTH='10%' align='CENTER'>Discount/Unit</td>");

			br
					.append("<td class='multiLabel' WIDTH='40%' align='CENTER'>Approved By</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Approved Date</td>");

			br.append("</tr>");
			if (temp.length > 1) {
				for (int j = 0; j < (temp.length); j = j + 6) {
					if (temp[j + 5].equals("1")) {
						discountType = "fixed";
					} else if (temp[j + 5].equals("2")) {
						discountType = " %";
					} else {
					}
					br.append("<TR>");

					br.append("<TD  WIDTH='20%' CLASS='multiControl'>"
							+ temp[j + 0] + "</TD>");

					br.append("<TD WIDTH='10%' CLASS='multiControl'>"
							+ temp[j + 1] + "</TD>");

					br.append("<TD WIDTH='10%' CLASS='multiControl' >"
							+ temp[j + 2] + " " + discountType + "</TD>");

					br.append("<TD WIDTH='40%' CLASS='multiControl' >"
							+ temp[j + 3] + "</TD>");

					br.append("<TD WIDTH='20%' CLASS='multiControl' >"
							+ temp[j + 4] + "</TD>");

					br.append("</TR>");
				}
			}
			br.append("</table>");

		} catch (Exception e) {
			vo
					.setStrMsgString("OnlineRequestCancellationTransHLP.onlineRequestDetails() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		}
		return br.toString();
	}
}
