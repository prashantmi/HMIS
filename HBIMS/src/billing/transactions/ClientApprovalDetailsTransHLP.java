package billing.transactions;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.sql.SQLException;

public class ClientApprovalDetailsTransHLP
{
	
	//used
	 public static String getViewApprovalDtl(ClientApprovalDetailsTransVO vo) 
		{
		 // Declaring Variables
		   
		       int legth = vo.getStrCltPatSlNum().length;
		   	   
	           StringBuffer sb = new StringBuffer("");
               sb.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>"); 
			   sb.append("<tr><td width='5%' class='multiLabel'>SNo</td>");
			   sb.append("<td width='20%' class='multiLabel'>Auth No</td>");
			   sb.append("<td width='15%' class='multiLabel'>Auth Date</td>");
			   sb.append("<td width='15%' class='multiLabel'>Expiry Date</td>");
			   sb.append("<td width='15%' class='multiLabel'>Sanction Amt(<img src='/AHIMS/hisglobal/images/INR.png'>)</td>");
			   try 
				 {
				  		for(int i=0;i<legth;i++)
	                    {
				  			
	     				    sb.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
							sb.append("<tr>");
							sb.append("<td width='5%' class='multiControl'>");
							sb.append(i+1);
							sb.append("</td>");
							sb.append("<td width='20%' class='multiControl'>");
							sb.append(vo.getStrAuthNumber()[i]);
							sb.append("</td>");
							sb.append("<td width='15%' class='multiControl'>");
							sb.append(vo.getStrAuthDate()[i]);
							sb.append("</td>");
							sb.append("<td width='15%' class='multiControl'>");
							sb.append(vo.getStrExpyDate()[i]);
							sb.append("</td>");
							sb.append("<td width='15%' class='multiControl'>");
							sb.append(vo.getStrCurrSancAmt()[i]);
							sb.append("</td>");
					   }
			 	}
	            catch (Exception e) 
	            {
					new HisException(
							"Client Approval Details Trans ",
							"ClientApprovalDetailsTransHLP.getViewApprovalDtl() -->",
							e.getMessage());
				}
			
			 
			return sb.toString();
		}

	public static String getTarrifDetailsView(
			ClientApprovalDetailsTransVO voObj, String tariffDetails, int index) {

		HisUtil util = null;

		StringBuffer sb = new StringBuffer("");

		String tVal = tariffDetails;

		util = new HisUtil("Admission Advice Trans", "AdmissionAdviceTransDATA");
		int nTotal = 0;
		try {

			String temp[] = tVal.replace('@', '#').split("#");

			nTotal = temp.length;

			for (int i = 1; i <= nTotal; i++) {

				String temp1[] = temp[i - 1].replace("|", "#").split("#");

				voObj.getStrTariffWs().beforeFirst();
				String comboValues = util.getOptionValue(
						voObj.getStrTariffWs(), temp1[0].trim(),
						"0^Select Value", false);

				sb.append("<table width='400'>");
				sb.append("<tr><td width='236' class='multiControl'>");
				sb.append("<div id='tariffOptionsId'>");
				sb
						.append("<select name='strTariffName' class='comboNormal' id='strTariffName"
								+ index + "-" + i + "'>");
				sb.append(comboValues);
				sb.append("</select>");
				sb.append("</div></td>");
				sb
						.append("<td width='170' class='multiControl'><input type='text' name='strTariffSancAmt' value='"
								+ temp1[1]
								+ "' onkeypress='return validateData(event,7);' class='txtFldNormal' id='strTariffSancAmt"
								+ index + "-" + i + "'>");
				sb.append("</td><td width='23' class='multiControl'>");
				sb
						.append("<img src='../../hisglobal/images/minus.gif' onClick=\"deleteRow('"
								+ index
								+ "-"
								+ i
								+ "','"
								+ index
								+ "','0');\"> </td>");
				sb.append("</tr>");
				sb.append("</table>");
				// sb.append("");

			}

		} catch (Exception e) {

			new HisException("Client Approval Details Trans",
					"ClientApprovalDetailsTransHLP.getTarrifDetailsView()-->",
					e.getMessage());
		}

		return sb.toString() + "@" + nTotal;
	}

	public static String getApprovalDetailsView(
			ClientApprovalDetailsTransVO voObj) {

		StringBuffer sBuffer = new StringBuffer("");

		boolean flag = true;

		try {

			sBuffer.append("<table width='100%'>");
			sBuffer.append("<tr>");
			sBuffer.append("<td class='multiLabel' width='10'>");
			sBuffer.append("</td>");
			sBuffer.append("<td class='multiLabel'>Authentication No.");
			sBuffer.append("</td>");
			sBuffer.append("<td class='multiLabel'>Authentication Date");
			sBuffer.append("</td>");
			sBuffer.append("<td class='multiLabel'>Sanction Amount");
			sBuffer.append("</td>");
			sBuffer.append("<td class='multiLabel'>Expiry Date");
			sBuffer.append("</td>");
			sBuffer.append("</tr>");

			if (voObj.getStrApprovalDetailsWs() != null) {

				while (voObj.getStrApprovalDetailsWs().next()) {

					sBuffer.append("<tr>");
					sBuffer.append("<td class='multiControl' width='10'>");
					sBuffer
							.append("<input type='radio' name='approvalDetailRadio' onClick='setApprovalDetails(this);' value='"
									+ voObj.getStrApprovalDetailsWs()
											.getString(1) + "'");
					if (flag) {
						sBuffer.append("checked='checked' >");

						String temp[] = voObj.getStrApprovalDetailsWs()
								.getString(1).replace("^", "#").split("#");

						voObj.setStrSanctionAmount(temp[1]);
						voObj.setStrAmountReceivedFromClient(temp[2]);
						voObj.setStrAmountReceivedFromPatient(temp[3]);
						voObj.setStrClientExpenseAmount(temp[4]);
						voObj.setStrRefundAmount(temp[5]);

					}
					sBuffer.append("</td>");
					sBuffer.append("<td class='multiControl'>");
					sBuffer
							.append(voObj.getStrApprovalDetailsWs()
									.getString(2));
					sBuffer.append("</td>");
					sBuffer.append("<td class='multiControl'>");
					sBuffer
							.append(voObj.getStrApprovalDetailsWs()
									.getString(3));
					sBuffer.append("</td>");
					sBuffer.append("<td class='multiControl'>");
					sBuffer
							.append(voObj.getStrApprovalDetailsWs()
									.getString(4));
					sBuffer.append("</td>");
					sBuffer.append("<td class='multiControl'>");
					sBuffer
							.append(voObj.getStrApprovalDetailsWs()
									.getString(5));
					sBuffer.append("</td>");
					sBuffer.append("</tr>");

					flag = false;
				}
			}
		} catch (SQLException e) {
			new HisException("Client Approval Details Trans",
					"ClientApprovalDetailsTransHLP.getApprovalDetailsView()", e
							.getMessage());
		}

		sBuffer.append("</table>");

		return sBuffer.toString();
	}

}
