package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.tools.hlp.GlobalVO;
import hisglobal.tools.hlp.PatientDtlBO;
import hisglobal.utility.HisUtil;

import java.sql.SQLException;

import javax.sql.rowset.WebRowSet;

import mms.transactions.bo.IssueDeskTransBO;
import mms.transactions.vo.IssueDeskTransVO;

public class LFNoTransHLP {

	private static final String strColor = "red";
	private static final String defaultColor = "blue";

	/**
	 * returns the required Off-line Part Payment Details View (HTML) in String
	 * format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return - required Off-line Part Payment Details View
	 */
	public static String getOffLinePartPaymentDetailsView(
			LFNoTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		HisUtil util = new HisUtil("Cash Collection Trans",
				"CashCollectionTransHLP.getOffLinePartPaymentDetailsView()");

		String strApprovedBy = "";

		try {

			if (voObj.getOfflineApprovedByList() != null
					&& voObj.getOfflineApprovedByList().size() > 0) {

				voObj.getOfflineApprovedByList().beforeFirst();

				strApprovedBy = util.getOptionValue(voObj
						.getOfflineApprovedByList(), "0", "0^Select Value",
						false);
			} else {

				strApprovedBy = "<option value='0'>Select Value</option>";

			}

			String strRemarks = "";

			if (voObj.getOfflineRemarksList() != null
					&& voObj.getOfflineRemarksList().size() > 0) {

				voObj.getOfflineRemarksList().beforeFirst();

				strRemarks = util.getOptionValue(voObj.getOfflineRemarksList(),
						"", "", false);

				strRemarks = strRemarks + "<option value='0'>Others</option>";

			} else {

				strRemarks = "<option value='0' selected>Others</option>";

			}

			sb
					.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
			sb.append("<tr> ");
			sb.append("<td class='TITLE'>Part Payment Details</td> ");
			sb.append("</tr> ");
			sb.append("</table> ");
			
			//Modified by Garima Gotra for HIS_PGI_BILL_CR_1 on 17 Aug 2011
			
			sb
			.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr> ");

			sb
			.append("<td width='25%'class='LABEL'>Part Payment Amount:</td> ");

			if (!voObj.getStrOffLinePartPaymentAmount().equals("")) {

				sb
				.append("<td width='25%' class='CONTROL'><input type='hidden' name='strdummypartpayment' value='"
						+ voObj.getStrOffLinePartPaymentAmount()
						+ "'><input name='strPartpayment' tabindex='1' id='strPartpayment' type='text' class='txtFldMin' value='"
						+ voObj.getStrOffLinePartPaymentAmount()
						+ "' disabled='disabled' onkeyup='return setTotalRecAmt(this);' onkeypress='return validateData(event,7);'>&nbsp;Rs</td> ");
			} else {
				sb
				.append("<td width='25%' class='CONTROL'><input type='hidden' name='strdummypartpayment' value='0'><input name='strPartpayment' tabindex='1' id='strPartpayment' type='text' class='txtFldMin' value='0' disabled='disabled' onkeypress='return validateData(event,7);' onkeyup='return setTotalRecAmt(this);'>&nbsp;Rs</td> ");
			}

			//sb.append("<td width='50%'class='LABEL' > ");
			sb.append("<div id='id11' style='display:block'> ");
			//sb
			//.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			//sb.append("<tr>  ");
			sb.append("<td width='25%' class='LABEL'>Update:</td> ");
			sb
			.append("<td width='25%' class='CONTROL'><input type='checkbox' name='strChk_value' value='0' onClick='ftnTick();'></td> ");
			//sb.append("</tr> ");
			//sb.append("</table> ");
			sb.append("</div>");
			//sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("<tr> ");
			//sb.append("<td width='50%'class='LABEL' > ");
			//sb.append("<div id='id12' style='display:block'> ");
			//sb
			//.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			//sb.append("<tr>  ");
			sb.append("<td width='25%' class='LABEL'>Against Security:</td> ");
			sb
			.append("<td width='25%' class='CONTROL'><input type='checkbox' name='advSecFlag' value='0' onClick='ftnadvSecTick();'></td> ");
			//sb.append("</tr> ");
			//sb.append("</table> ");
            //sb.append("</div>");
			//sb.append("</td> ");

			//end of code added by Garima Gotra
			
			sb.append("<td width='25%' class='LABEL' >Part Payment Remarks ");
			sb.append("</td> ");			
			sb.append("<td width='25%' colspan='3' class='CONTROL'> ");
			sb.append("<input type='text' name='strRemarks' disabled='true' class='txtFldBig' maxlength='100' onkeypress='return validateDataWithSpecialChars(event,9,\".\");' >");
			sb.append("</td> ");
			sb.append("</tr> ");

			sb.append("</table> ");

		
			sb.append("<div id='combo' style='display:none'> ");
			sb
					.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
			sb.append("<tr> ");
			
			if(voObj.getStrIsApprovalRequired().equals("1")){
				
				sb
				.append("<td width='25%' class='LABEL'><font color='red'>*</font>Approval By</td> ");
				
			}else{
				
				sb
				.append("<td width='25%' class='LABEL'>Approval By</td> ");
				
			}
			
		
			
			
			
			sb.append("<td width='75%' colspan='3' class='CONTROL'> ");
			sb.append("<select name='strApprovedByCombo' class='comboBig'> ");
			sb.append(strApprovedBy);
			sb.append("</select> ");
			sb.append("</td> ");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='25%'  class='LABEL'>Remarks</td> ");
			sb.append("<td width='75%' colspan='3' class='CONTROL'> ");
			sb
					.append("<select name='dr' class='comboNormal' onChange='groupComboPartPayment();'> ");
			sb.append(strRemarks);
			sb.append("</select> ");
			sb
					.append("<input name='strRemarksCombo2' type='text' class='txtFldMax' value='"
							+ voObj.getStrOffLineRemarks()
							+ "' disabled='disabled'></td> ");
			sb.append("</tr> ");
			sb.append("</table> ");
			sb.append("</div> ");

		} catch (Exception e) {
			new HisException(
					"Cash Collection Trans",
					"CashCollectionTransHLP.getOffLinePartPaymentDetailsView()-->",
					e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * returns the required Off-line Advance Details View (HTML) in String
	 * format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return - required Off-line Advance Details View
	 */
	public static String getOffLineAdvanceDetailsView(
			LFNoTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		HisUtil util = new HisUtil("Cash Collection Trans",
				"CashCollectionTransHLP.getOffLineAdvanceDetailsView()");

		String strApprovedBy = "";

		try {

			if (voObj.getOfflineApprovedByList() != null
					&& voObj.getOfflineApprovedByList().size() > 0) {

				voObj.getOfflineApprovedByList().beforeFirst();

				strApprovedBy = util.getOptionValue(voObj
						.getOfflineApprovedByList(), "0", "0^Select Value",
						false);
			} else {

				strApprovedBy = "<option value='0'>Select Value</option>";

			}

			String strRemarks = "";

			if (voObj.getOfflineRemarksList() != null
					&& voObj.getOfflineRemarksList().size() > 0) {

				voObj.getOfflineRemarksList().beforeFirst();

				strRemarks = util.getOptionValue(voObj.getOfflineRemarksList(),
						"", "", false);

				strRemarks = strRemarks + "<option value='0'>Others</option>";

			} else {

				strRemarks = "<option value='0' selected>Others</option>";

			}

			sb
					.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
			sb.append("<tr> ");
			sb.append("<td class='TITLE'>Advance Details</td> ");
			sb.append("</tr> ");
			sb.append("</table> ");
			sb
					.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
			sb.append("<tr> ");
			sb.append("<td width='25%' class='LABEL'>Advance Amount:</td> ");

			if (!voObj.getStrOffLineAdvanceAmount().equals("")) {

				sb
						.append("<td width='25%' class='CONTROL'><input type='hidden' name='strdummypartpayment' value='"
								+ voObj.getStrOffLineAdvanceAmount()
								+ "'><input name='strPartpayment' tabindex='1'  id='strPartpayment' type='text' class='txtFldMin' value='"
								+ voObj.getStrOffLineAdvanceAmount()
								+ "' disabled='disabled' onkeyup='return setTotalRecAmt(this);' onkeypress='return validateData(event,7);' >&nbsp;Rs</td> ");

			} else {
				sb
						.append("<td width='25%' class='CONTROL'><input type='hidden' name='strdummypartpayment' value='0'><input name='strPartpayment' tabindex='1' id='strPartpayment' type='text' class='txtFldMin' value='0' disabled='disabled' onkeypress='return validateData(event,7);' onkeyup='return setTotalRecAmt(this);'>&nbsp;Rs</td> ");
			}

			sb.append("<td width='50%' class='LABEL' > ");
			sb.append("<div id='id11' style='display:block'> ");
			sb
					.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr>  ");
			sb.append("<td width='25%' class='LABEL'>Update:</td> ");
			sb
					.append("<td width='25%' class='CONTROL'><input type='checkbox' name='strChk_value' onClick='ftnTick();'></td> ");
			sb.append("</tr> ");
			sb.append("</table> ");
			sb.append("</div>");
			sb.append("</td> ");
			sb.append("</tr> ");
			sb.append("</table> ");
			sb.append("<div id='combo' style='display:none'> ");
			sb
					.append("<table class='TABLEWIDTH' align='center'  cellspacing='1px'>");
			sb.append("<tr> ");
			
	if(voObj.getStrIsApprovalRequired().equals("1")){
				
				sb
				.append("<td width='25%' class='LABEL'><font color='red'>*</font>Approval By</td> ");
				
			}else{
				
				sb
				.append("<td width='25%' class='LABEL'>Approval By</td> ");
				
			}		
			
			
			sb.append("<td width='50%' colspan='3' class='CONTROL'> ");
			sb.append("<select name='strApprovedByCombo' class='comboBig'> ");
			sb.append(strApprovedBy);
			sb.append("</select> ");
			sb.append("</td> ");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='25%'  class='LABEL'>Remarks</td> ");
			sb.append("<td width='75%' colspan='3' class='CONTROL'> ");
			sb
					.append("<select name='dr' class='comboNormal' onChange='groupComboPartPayment();'> ");
			sb.append(strRemarks);
			sb.append("</select> ");
			sb
					.append("<input name='strRemarksCombo2' type='text' class='txtFldMax' value='"
							+ voObj.getStrOffLineRemarks()
							+ "' disabled='disabled'></td> ");
			sb.append("</tr> ");
			sb.append("</table> ");
			sb.append("</div> ");

		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getOffLineAdvanceDetailsView()-->",
					e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * returns the required Online Client Details View (HTML) in String format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return - required Online Client Details View
	 */
	public static String getOffLineClientDetailsView(LFNoTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		WebRowSet wsClientDetails = voObj.getOfflineClientDetails();

		try {

			String strHidden = "";
			sb
					.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'> ");
			sb.append("<tr> ");
			sb.append("<td class='TITLE' width='20'> ");
			sb
					.append("<div id='plusoffLineClientDtlId' ><img  style='cursor: hand; cursor: pointer'    ");
			sb
					.append("	src='../../hisglobal/images/plus.gif' name='plusoffLineClientDtl' ");
			sb.append("align='middle'  ");
			sb
					.append("onclick='showCltDetails(\"offLineClientDtlId\");' /></div> ");
			sb
					.append("<div id='minusoffLineClientDtlId' style='display: none'><img  style='cursor: hand; cursor: pointer'    ");
			sb
					.append("src='../../hisglobal/images/minus.gif' name='minusoffLineClientDtl' ");
			// sb.append("width='10' height='10' ");
			sb
					.append("onclick='hideCltDetails(\"offLineClientDtlId\");'></div> ");
			sb.append("</td> ");
			sb.append("<td colspan='3' class='TITLE'>Client Details</td> ");
			sb.append("</tr> ");
			sb.append("</table> ");

			sb.append("<div id='offLineClientDtlId' style='display: none'>");
			sb.append("<table class='TABLEWIDTH' align='center'>");

			if (wsClientDetails != null && wsClientDetails.size() > 0) {
				if (wsClientDetails.next()) {

					String strClientName = wsClientDetails.getString(3);
					String strClientType = wsClientDetails.getString(4);
					String strApprovalNo = wsClientDetails.getString(5);
					String strSancAmount = wsClientDetails.getString(7);
					String strBalanceAmount = wsClientDetails.getString(9);
					String strRequestType = wsClientDetails.getString(14);
					String strPaidByClient = wsClientDetails.getString(18)
							.replace("^", "#").split("#")[2];

					strHidden = wsClientDetails.getString(18);

					if (strClientName == null)
						strClientName = "";
					if (strClientType == null)
						strClientType = "";
					if (strSancAmount == null)
						strSancAmount = "";
					// if(strClientName == null) strClientName = "";
					if (strBalanceAmount == null)
						strBalanceAmount = "";
					if (strApprovalNo == null)
						strApprovalNo = "";
					if (strRequestType == null)
						strRequestType = "";

					voObj.setStrOfflineClientName(strClientName);
					voObj.setStrOffLineClientType(strClientType);
					voObj.setStrOffLineSancAmount(strSancAmount);
					voObj.setStrOffLineBalanceAmount(strBalanceAmount);
					voObj.setStrOffLineApprovalNo(strApprovalNo);

					sb.append("<tr>");
					sb
							.append("<td width='25%' class='LABEL'>Client Name<input type='hidden' name='strOfflineClientDetails' value='"
									+ strClientName
									+ "^"
									+ strClientType
									+ "^"
									+ strSancAmount
									+ "^"
									+ strBalanceAmount
									+ "^" + strApprovalNo + "^"+strPaidByClient+"'></td>");
					sb.append("<td width='25%' class='CONTROL'>"
							+ strClientName + "</td>");
					sb.append("<td width='25%' class='LABEL'>Client Type</td>");
					sb
							.append("<td width='25%' class='CONTROL'>"
									+ strClientType
									+ " <input type='hidden' name='strOffLineClientType' value='"
									+ strClientType + "'></td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb
							.append("<td width='25%' class='LABEL'>Sanction Amount</td>");
					sb
							.append("<td width='25%' class='CONTROL'>"
									+ strSancAmount
									+ " <input type='hidden' name='strOffLineSancAmount' value='"
									+ strSancAmount + "'></td>");
					sb
							.append("<td width='25%' class='LABEL'>Approval No.</td>");
					sb
							.append("<td width='25%' class='CONTROL'>"
									+ strApprovalNo
									+ " <input type='hidden' name='strOffLineApprovalNo' value='"
									+ strApprovalNo + "'></td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb
							.append("<td width='25%' class='LABEL'>Balance Amount</td>");
					sb
							.append("<td  width='25%' class='CONTROL'>"
									+ strBalanceAmount
									+ " <input type='hidden' name='strOffLineBalanceAmount' value='"
									+ strBalanceAmount
									+ "'> <input type='hidden' name='strOffLineClientDetailsHidden' value='"
									+ strHidden + "'> </td>");
					sb
							.append("<td class='LABEL' width='25%'>Paid by Client</td>");
					sb.append("<td class='CONTROL'>" + strPaidByClient
							+ "</td>");
					sb.append("</tr>");
				}

				sb.append("</table>");
				sb.append("</div>");

			} else {
				sb = new StringBuffer("");
			}

		} catch (SQLException e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getonLineClientDetailsView()-->", e
							.getMessage());
		}

		return sb.toString();
	}

	public static String getOffLineOtherDetailsView(LFNoTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		String strAccNo = voObj.getStrOffLineAccountNo();
		String strSpecialWardId = voObj.getStrOffLineSpecialWard();
		String strAccTreatCat = voObj.getStrOffLineTreatmentCategory();
		String strAccChargeType = voObj.getStrOffLineWard();
		String strAccDept = voObj.getStrOffLineRaisingDepartment();

		if (strAccNo.length() > 1) {

			sb
					.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");

			sb.append("<tr>");

			sb.append("<td class='TITLE' width='20'> ");
			sb
					.append("<div id='plusoffLineOtherDtlId' ><img  style='cursor: hand; cursor: pointer'    ");
			sb
					.append("src='../../hisglobal/images/plus.gif' name='plusoffLineOtherDtl' ");
			sb.append("align='middle' ");
			sb
					.append("onclick='showCltDetails(\"offLineOtherDtlId\");' /></div> ");
			sb
					.append("<div id='minusoffLineOtherDtlId' style='display: none'><img  style='cursor: hand; cursor: pointer'    ");
			sb
					.append("src='../../hisglobal/images/minus.gif' name='minusoffLineOtherDtl' ");

			sb
					.append("onclick='hideCltDetails(\"offLineOtherDtlId\");'></div> ");
			sb.append("</td> ");
			sb.append("<td colspan='3' class='TITLE'>Other Details</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<div id='offLineOtherDtlId' style='display: none'>");
			sb
					.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr>");

			sb.append("<td width='50%' class='LABEL'>Account No.</td>");
			sb
					.append("<td width='50%' class='CONTROL'>"
							+ strAccNo
							+ " <input type='hidden' name='strOffLineAccountNo' value='"
							+ strAccNo + "'><input type='hidden' name='strSpecialWardId' value='"
							+ strSpecialWardId + "'> <input type='hidden' name='strAccTreatCat' value='"
							+ strAccTreatCat + "'><input type='hidden' name='strAccChargeType' value='"
							+ strAccChargeType + "'><input type='hidden' name='strAccDept' value='"
							+ strAccDept + "'>  </td>");

			sb.append("</tr>");

			sb.append("</table>");
			sb.append("</div>");

		}

		return sb.toString();
	}

	/*public static String getOffLineThirdPartyAmountDtlView() {

		StringBuffer sb = new StringBuffer("");

		sb
				.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
		sb.append("<tr> ");
		sb
				.append("<td width='60%' class='LABEL'>Max Benefit From Client (<img src='/HBIMS/hisglobal/images/INR.png'>)</td> ");
		sb
				.append("<td width='15%' class='CONTROL' style='font-weight: bold'> ");
		sb
				.append("<input type='hidden' name='strOfflineMaxClientBenefitAmount' id='strOfflineMaxClientBenefitAmount' value='0.00'> ");
		sb.append("<div id='offlineMaxClientBenefitDivId'>0.00</div> ");
		sb.append("</td> ");
		sb.append("</tr> ");
		sb.append("</table> ");
		sb
				.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> ");
		sb.append("<tr> ");
		sb
				.append("<td width='60%' class='LABEL'>Net Payable Amount By Patient (<img src='/HBIMS/hisglobal/images/INR.png'>)</td> ");
		sb
				.append("<td width='15%' class='CONTROL' style='font-weight: bold'> ");
		sb
				.append("<input type='hidden' name='strOfflinePatNetPayAmount' id='strOfflinePatNetPayAmount' value='0.00'> ");
		sb.append("<div id='offlinePatNetPayDivId'>0.00</div> ");
		sb.append("</td> ");
		sb.append("</tr> ");
		sb.append("</table> ");

		return sb.toString();
	}*/

	/**
	 * returns the required Off-line Client Details View (HTML) in String
	 * format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return - required Online Client Details View
	 */
	public static String getOffLinePackageDetailsView(
			LFNoTransVO voObj) {

		StringBuffer sb = new StringBuffer("");
		String index = voObj.getStrOffLinePackageIndex();
		try {
			sb
					.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td class='multiLabel' width='3%'> </td>");
			sb.append("<td class='multiLabel' width='18%'>Tariff Name</td>");
			sb.append("<td class='multiLabel' width='8%'>Rate/Unit</td>");
			sb
					.append("<td class='multiLabel' width='8%'>Package Quantity</td>");
			sb.append("<td class='multiLabel' width='8%'>Quantity</td>");
			sb.append("<td class='multiLabel' width='8%'>Each Unit</td>");
			sb.append("<td class='multiLabel' width='10%'>Net Amount</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb
					.append("<td class='multiControl'><input type='checkbox' name='pkgTariffChk"
							+ index + "'/>");
			sb.append("</td>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td colspan='6' class='LABEL'>Total </td>");
			sb.append("<td class='CONTROL'/>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td colspan='7' align='center'>");
			sb
					.append("<table border='0' width='100%' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb
					.append("<td align='right'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/add_tab.gif' onClick=''/></td>");
			sb
					.append("<td align='left'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/btn-ccl.png' onClick='hidePkgDtls(\""
							+ index + "\");'/></td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getonTariffClientDetailsView()-->",
					e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * returns the Required Off-Line Bill Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object.
	 * @return required Off-Line Bill Details View.
	 * @throws Exception
	 */
	public static String getBillDetails(LFNoTransVO voObj)
			throws Exception {

		WebRowSet billDetails = voObj.getOfflineBillList();
		StringBuffer sBuffer = new StringBuffer("");
		try {
			sBuffer
					.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Receipt Details</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'></td>");
			sBuffer.append("<td width='19%' class='multiLabel'>Receipt No.</td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'>Receipt Date </td>");
			sBuffer.append("<td width='19%' class='multiLabel'>Receipt Type</td>");
			sBuffer
					.append(" <td width='19%' class='multiLabel'>Receipt Amount</td>");
			sBuffer.append("</tr>");
			sBuffer
					.append("<input type='hidden' name='xmlVal' id='xmlValId' value='"
							+ voObj.getStrOffLineRefundPenalty() + "'>");

			if (billDetails != null && billDetails.size() > 0) {

				int i = 0;

				while (billDetails.next()) {
					String billno = billDetails.getString(1);

					String temp[] = (billDetails.getString(6))
							.replace("^", "#").split("#");
					String bNo_HpSrv = billno + "^" + billDetails.getString(3)
							+ "^" + temp[4];
					String billdate = billDetails.getString(2);

					if (i == 0) {
						voObj.setStrOffLineBillNumber(billno);

						//voObj.setStrOffLineAccountNo(temp[4]);
					}

					sBuffer
							.append("<input type='hidden' name='billNo_Id' value='"
									+ bNo_HpSrv + "'>");
					sBuffer.append("<tr><td width='5%' class='multiLabel'> ");

					if (i == 0) {
						sBuffer
								.append("<input type='radio' name='strOfflineRefundBillDetails' value='"
										+ billDetails.getString(6)
										+ "' onClick='getBillTariffDtls(this);' checked='checked'> ");
					} else {
						sBuffer
								.append("<input type='radio' name='strOfflineRefundBillDetails' value='"
										+ billDetails.getString(6)
										+ "' onClick='getBillTariffDtls(this);'> ");
					}
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billno);
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billdate);
					sBuffer.append("</td>");
					/*
					 * sBuffer.append("<td width='19%' class='multiControl'>");
					 * sBuffer.append(billDetails.getString(3));
					 * sBuffer.append("</td>");
					 */
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billDetails.getString(4));
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billDetails.getString(5));
					sBuffer.append("</td></tr>");
					temp = null;
					i++;
				}
			} else {

				sBuffer
						.append("<tr><td colspan='9' class='multiControl' style='color:red;font-weight:bold'>No Refund Receipts Available</td></tr>");
			}
			sBuffer.append("</table>");
		} catch (Exception e) {
			throw new Exception("Cash Collection Trans"
					+ "CashCollectionTransHLP.getBillDetails()-->"
					+ e.getMessage());
		}

		return sBuffer.toString();

	}
	
	
	/**
	 * returns the Required Off-Line Bill Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object.
	 * @return required Off-Line Bill Details View.
	 * @throws Exception
	 */
	public static String getOffLinePartPayRefundBillDetails(LFNoTransVO voObj)
			throws Exception {

		WebRowSet billDetails = voObj.getOfflineBillList();
		StringBuffer sBuffer = new StringBuffer("");
		try {
			sBuffer
					.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Receipt Details</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'></td>");
			sBuffer.append("<td width='19%' class='multiLabel'>Receipt No.</td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'>Receipt Date </td>");
			//sBuffer.append("<td width='19%' class='multiLabel'>Receipt Type</td>");
			sBuffer
					.append(" <td width='19%' class='multiLabel'>Receipt Amount</td>");
			sBuffer.append("</tr>");
			sBuffer
					.append("<input type='hidden' name='xmlVal' id='xmlValId' value='"
							+ voObj.getStrOffLineRefundPenalty() + "'>");

			if (billDetails != null && billDetails.size() > 0) {

				int i = 0;

				while (billDetails.next()) {
					String billno = billDetails.getString(1);

					String temp[] = (billDetails.getString(6))
							.replace("^", "#").split("#");
					String bNo_HpSrv = billno + "^" + billDetails.getString(3)
							+ "^" + temp[4];
					String billdate = billDetails.getString(2);

					if (i == 0) {
						voObj.setStrOffLineBillNumber(billno);

						//voObj.setStrOffLineAccountNo(temp[4]);
					}

					sBuffer
							.append("<input type='hidden' name='billNo_Id' value='"
									+ bNo_HpSrv + "'>");
					sBuffer.append("<tr><td width='5%' class='multiLabel'> ");

					if (i == 0) {
						sBuffer
								.append("<input type='radio' name='strOfflineRefundBillDetails' value='"
										+ billDetails.getString(6)
										+ "' onClick='getPartPayBillTariffDtls(this);' checked='checked'> ");
					} else {
						sBuffer
								.append("<input type='radio' name='strOfflineRefundBillDetails' value='"
										+ billDetails.getString(6)
										+ "' onClick='getPartPayBillTariffDtls(this);'> ");
					}
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billno);
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billdate);
					sBuffer.append("</td>");
					/*
					 * sBuffer.append("<td width='19%' class='multiControl'>");
					 * sBuffer.append(billDetails.getString(3));
					 * sBuffer.append("</td>");
					 */
					/*sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billDetails.getString(4));
					sBuffer.append("</td>");*/
					sBuffer.append("<td width='19%' class='multiControl'>");
					sBuffer.append(billDetails.getString(5));
					sBuffer.append("</td></tr>");
					temp = null;
					i++;
				}
			} else {

				sBuffer
						.append("<tr><td colspan='8' class='multiControl' style='color:red;font-weight:bold'>No Refund Receipts Available</td></tr>");
			}
			sBuffer.append("</table>");
		} catch (Exception e) {
			throw new Exception("Cash Collection Trans"
					+ "CashCollectionTransHLP.getBillDetails()-->"
					+ e.getMessage());
		}

		return sBuffer.toString();

	}
	

	/**
	 * returns Off-Line Tariff Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return required Off-Line Tariff Details View.
	 * @throws Exception
	 */
	public static String getTariffDetails(LFNoTransVO voObj) throws Exception 
	{
		StringBuffer sBuffer = new StringBuffer("");
		WebRowSet tariffDetails = voObj.getOfflineBillTariffList();
		
		try 
		{
			HisUtil util = new HisUtil("", "");
			String app_id = "";
			String strUnitId = "";
			String strBaseUnitVal = "";
			int indxTrf = 1;
			sBuffer.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
			sBuffer.append("<tr><td colspan='9' class='TITLE'>Tariff Details</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'></td>");
			sBuffer.append("<td width='15%' class='multiLabel'>Tariff Name</td>");
			sBuffer.append("<td width='10%' class='multiLabel'>Balance Qty</td>");
			sBuffer.append("<td width='19%' class='multiLabel'>Refund Type</td>");
			sBuffer.append("<td width='14%' class='multiLabel'>Penalty (%)</td>");
			sBuffer.append(" <td width='10%' class='multiLabel'>Refund Qty</td>");
			sBuffer.append(" <td width='10%' class='multiLabel'>Unit</td>");
			sBuffer.append(" <td width='10%' class='multiLabel'>Refund Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)");
			sBuffer.append("<input type='hidden' name='strOfflineRefundTotalServiceTaxAmount' value='0' >");
			sBuffer.append("<input type='hidden' name='strOfflineRefundTotalDiscountAmount' value='0' >");
			sBuffer.append("<input type='hidden' name='strOfflineRefundTotalPenaltyAmount' value='0' >");
			sBuffer.append("<input type='hidden' name='strOfflineRefundTotalActualTariffAmount' value='0' >");
			sBuffer.append("</td>");
			sBuffer.append("</tr></table>");

			if (tariffDetails != null && tariffDetails.size() != 0) 
			{
				while (tariffDetails.next()) 
				{
					String TariffName = tariffDetails.getString(1);
					app_id = tariffDetails.getString(8) + "^"+ tariffDetails.getString(3);
					strUnitId = app_id.replace("^", "#").split("#")[4];
					strBaseUnitVal = app_id.replace("^", "#").split("#")[13];
					sBuffer.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
					sBuffer.append("<input type='hidden' name='lnkVal' value='"	+ app_id + "'>");
					sBuffer.append("<tr><td width='5%' class='multiLabel'><input type='checkbox' name='strBillTariffVal'id='strBillTariffVal"+ indxTrf+ "' value='"+ app_id+ "' onClick='initBillTariff(this,\""+ indxTrf+ "\",\""+ tariffDetails.getString(2)+ "\");'></td>");
					sBuffer.append("<td width='15%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' name='strOfflineRefundServiceTaxAmount' id='strOfflineRefundServiceTaxAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundDiscountAmount' id='strOfflineRefundDiscountAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundPenaltyAmount' id='strOfflineRefundPenaltyAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundActualTariffAmount' id='strOfflineRefundActualTariffAmount"
									+ indxTrf + "' value='0' >");

					sBuffer
							.append("<a name='strBillTariffName' id='strBillTariffName"
									+ indxTrf
									+ "' STYLE='CURSOR:POINTER; color:blue' value='"
									+ app_id
									+ "'  onClick='showTariffPopup(this,\""
									+ app_id + "\");'>" + TariffName + "</a>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");
					sBuffer.append(tariffDetails.getString(2));
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");

					sBuffer
							.append("<select class='comboNormal' name='strBillTariffPenaltyType' id='strBillTariffPenaltyType"
									+ indxTrf
									+ "' disabled='disabled' onChange='setPenalty(this,"
									+ indxTrf
									+ "),calcBillTariffRefundAmt(\""
									+ indxTrf
									+ "\",\""
									+ app_id
									+ "\");'><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='14%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' class='txtFldMin' name='strBillTariffPenalty' id='strBillTariffPenalty"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);' ><div id='strBillTariffPenaltyDivId"
									+ indxTrf + "'>0</div>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<input type='text' class='txtFldMin' name='strBillTariffRefund' id='strBillTariffRefund"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);'  disabled='disabled' onkeyup='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<select class='comboMin' name='strBillTariffUnit' id='strBillTariffUnit"
									+ indxTrf
									+ "' disabled='disabled' onChange='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'> ");

					voObj.setStrOffLineTariffUnitTempId(strUnitId);

					LFNoTransDAO.getOffLineTariffUnitList(voObj);

					// System.out.println("unit Val : "+strUnitId);

					sBuffer.append(util.getOptionValue(voObj
							.getOfflineTariffUnit(), strUnitId + "^"
							+ strBaseUnitVal, "", false));

					sBuffer.append("</select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' class='txtFldMin' disabled='disabled' name='strBillTariffRefundAmount' id='strBillTariffRefundAmount"
									+ indxTrf
									+ "' value='0' style='font-weight: bold'><div id='strBillTariffRefundAmountDivId"
									+ indxTrf
									+ "' style='font-weight:bold'>0.00</div></td> ");

					indxTrf++;

				}
				sBuffer.append("</tr></table>");
			} else {

				sBuffer
						.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'><tr><td colspan='9' class='multiControl' style='color:red;font-weight:bold'>No Tariff(s) Available/Credit Category Refund Not Allowed</td></tr></table>");

			}

		} catch (Exception e) {

			throw new Exception("Cash Collection Trans"
					+ "CashCollectionTransHLP.getTariffDetails()-->"
					+ e.getMessage());
			
		}
		return sBuffer.toString();

	}

	
	/**
	 * returns Off-Line Tariff Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return required Off-Line Tariff Details View.
	 * @throws Exception
	 */
	public static String getPartPayTariffDetails(LFNoTransVO voObj)
			throws Exception {
		StringBuffer sBuffer = new StringBuffer("");
		WebRowSet tariffDetails = voObj.getOfflineBillTariffList();
		try {
			HisUtil util = new HisUtil("", "");
			String app_id = "";
			String strUnitId = "";
			String strBaseUnitVal = "";
			int indxTrf = 1;
			sBuffer
					.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Tariff Details</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'></td>");
			sBuffer
					.append("<td width='15%' class='multiLabel'>Tariff Name</td>");
			sBuffer
					.append("<td width='10%' class='multiLabel'>Balance Qty</td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'>Refund Type</td>");
			sBuffer
					.append("<td width='14%' class='multiLabel'>Penalty (%)</td>");
			sBuffer
					.append(" <td width='10%' class='multiLabel'>Refund Qty</td>");
			sBuffer.append(" <td width='10%' class='multiLabel'>Unit</td>");
			sBuffer
					.append(" <td width='10%' class='multiLabel'>Refund Cost(in Rs)");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalServiceTaxAmount' value='0' >");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalDiscountAmount' value='0' >");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalPenaltyAmount' value='0' >");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalActualTariffAmount' value='0' >");
			sBuffer.append("</td>");
			sBuffer.append("</tr></table>");

			if (tariffDetails != null && tariffDetails.size() != 0) {

				while (tariffDetails.next()) {

					String TariffName = tariffDetails.getString(1);

					app_id = tariffDetails.getString(8) + "^"
							+ tariffDetails.getString(3);

					strUnitId = app_id.replace("^", "#").split("#")[4];

					strBaseUnitVal = app_id.replace("^", "#").split("#")[13];

					sBuffer
							.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
					sBuffer.append("<input type='hidden' name='lnkVal' value='"
							+ app_id + "'>");

					sBuffer
							.append("<tr><td width='5%' class='multiLabel'><input type='checkbox' checked='checked'  name='strBillTariffVal'id='strBillTariffVal"
									+ indxTrf
									+ "' value='"
									+ app_id
									+ "' onClick='initBillTariff(this,\""
									+ indxTrf
									+ "\",\""
									+ tariffDetails.getString(2)
									+ "\");'></td>");
					sBuffer.append("<td width='15%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' name='strOfflineRefundServiceTaxAmount' id='strOfflineRefundServiceTaxAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundDiscountAmount' id='strOfflineRefundDiscountAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundPenaltyAmount' id='strOfflineRefundPenaltyAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundActualTariffAmount' id='strOfflineRefundActualTariffAmount"
									+ indxTrf + "' value='0' >");

					sBuffer
							.append("<a name='strBillTariffName' id='strBillTariffName"
									+ indxTrf
									+ "' STYLE='CURSOR:POINTER; color:blue' value='"
									+ app_id
									+ "'  onClick='showTariffPopup(this,\""
									+ app_id + "\");'>" + TariffName + "</a>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");
					sBuffer.append(tariffDetails.getString(2));
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");

					sBuffer
							.append("<select class='comboNormal' name='strBillTariffPenaltyType' id='strBillTariffPenaltyType"
									+ indxTrf
									+ "' disabled='disabled' onChange='setPenalty(this,"
									+ indxTrf
									+ "),calcBillTariffRefundAmt(\""
									+ indxTrf
									+ "\",\""
									+ app_id
									+ "\");'><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='14%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' class='txtFldMin' name='strBillTariffPenalty' id='strBillTariffPenalty"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);' ><div id='strBillTariffPenaltyDivId"
									+ indxTrf + "'>0</div>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<input type='text' class='txtFldMin' name='strBillTariffRefund' id='strBillTariffRefund"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);'  disabled='disabled' onkeyup='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<select class='comboMin' name='strBillTariffUnit' id='strBillTariffUnit"
									+ indxTrf
									+ "' disabled='disabled' onChange='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'> ");

					voObj.setStrOffLineTariffUnitTempId(strUnitId);

					LFNoTransDAO.getOffLineTariffUnitList(voObj);

					// System.out.println("unit Val : "+strUnitId);

					sBuffer.append(util.getOptionValue(voObj
							.getOfflineTariffUnit(), strUnitId + "^"
							+ strBaseUnitVal, "", false));

					sBuffer.append("</select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' class='txtFldMin' disabled='disabled' name='strBillTariffRefundAmount' id='strBillTariffRefundAmount"
									+ indxTrf
									+ "' value='0' style='font-weight: bold'><div id='strBillTariffRefundAmountDivId"
									+ indxTrf
									+ "' style='font-weight:bold'>0.00</div></td> ");

					indxTrf++;

				}
				sBuffer.append("</tr></table>");
			} else {

				sBuffer
						.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'><tr><td colspan='9' class='multiControl' style='color:red;font-weight:bold'>No Tariff(s) Available</td></tr></table>");

			}

		} catch (Exception e) {

			throw new Exception("Cash Collection Trans"
					+ "CashCollectionTransHLP.getTariffDetails()-->"
					+ e.getMessage());
			
		}
		return sBuffer.toString();

	}

	
	
	/**
	 * returns Off-Line Bill Tariff Pop up Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return required Off-Line Bill Tariff Pop up Details View
	 */
	public static String getPopUp(LFNoTransVO voObj) {
		StringBuffer sb = new StringBuffer("");
		String strValmode = voObj.getStrOffLineTariffDetailsHiddenValue();
		String temp = "";
		String val[] = strValmode.replace('^', '#').split("#");
		String strRatePerUnit = val[3] + "/" + val[12];

		try {
			if (strValmode != null) {
				if (val[9].equals("2")){
					
					temp = "Percentage";
				}else{
					
					temp = "Fixed";
				}
					

				sb
						.append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");
				sb
						.append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");
				sb
						.append("<td align='right'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"offlineBillTariffPopupDetailsDivId\");'> </td></tr>");
				sb.append("</table> ");
				sb
						.append("<table width='400' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb
						.append("<td class='multiLabel' width='20%'>Rate(Rs)/Unit</td>");
				sb.append("<td class='multiControl' width='20%'>"
						+ strRatePerUnit + "</td>");
				sb
						.append("<td class='multiLabel' width='20%'>Discount(Rs)</td>");
				sb.append("<td class='multiControl' width='20%'>" + val[8]
						+ "</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb
						.append("<td class='multiLabel' width='20%'>Discount Type</td>");
				sb.append("<td class='multiControl' width='20%'>" + temp
						+ "</td>");
				sb
						.append("<td class='multiLabel' width='20%'>Service Tax(%)</td>");
				sb.append("<td class='multiControl' width='20%'>" + val[7]
						+ "</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table width='400' align='center'>");
				sb.append("<tr class='FOOTER'>");
				sb.append("<td colspan='3'>&nbsp;</td>");
				sb.append("</tr></table>");
			} else {
				sb.append("<table width='400' align='center'>");
				sb.append("<tr class='FOOTER'>");
				sb.append("<td colspan='3'>&nbsp;</td>");
				sb.append("</tr></table>");
			}
		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getPopUpDtl()-->", e.getMessage());
		}

		return sb.toString();

	}
 

	public static String getBillListingView(LFNoTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = voObj.getBillSearchList();

		try {

			int start = Integer.parseInt(voObj.getStrBillFromRow());
			int actualBlockSet = Integer.parseInt(voObj.getStrBillCtBlockSet());

			final int REC_PER_PAGE = Integer.parseInt(voObj
					.getStrBillRowPerPage());
			final int PAGE_PER_BLOCK = 10;

			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;

			int previous = 1;
			int next = 0;

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

					/*
					 * sb .append("<table width='100%'align='center'
					 * cellspacing='1px'>");
					 * 
					 * sb.append("<tr>"); sb.append("<td class='LABEL'>&nbsp;");
					 * 
					 * if (start != 1) { sb.append("<a href='#'
					 * onClick='fetchBillList(\"" + previous + "\",\"" +
					 * (actualBlockSet - 1) + "\");'> &lt;&lt; Previous</a>
					 * &nbsp;"); } for (int i = 1; i <= totalLayer; i++) {
					 * sb.append("<a href='#' onClick='layerIndexNavigator(\"" +
					 * i + "\",\"" + totalLayer + "\")'>" + (i + start - 1) + "</a>
					 * &nbsp;"); }
					 * 
					 * if (next > 0) sb.append("<a href='#'
					 * onClick='fetchBillList(\"" + next + "\",\"" +
					 * (actualBlockSet + 1) + "\");'> Next &gt;&gt;</a>");
					 * 
					 * sb.append("</td>"); sb.append("</tr>"); sb.append("</table>");
					 */
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>");
					sb.append("</td>");
					sb
							.append("<td width='23%'class='multiLabel'>Patient Name");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Receipt No.");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Receipt Date");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Receipt Amount");
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
							for (int j = 0; j < REC_PER_PAGE; j++) {
								ws.next();
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strBillNo' value='");
								sb.append(ws.getString(2));
								sb.append("'></td>");
								sb
										.append("<td class='multiControl' width='23%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(4));
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
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strBillNo' value='");
								sb.append(ws.getString(2));
								sb.append("'></td>");
								sb
										.append("<td class='multiControl' width='23%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(4));
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

				throw new Exception("Patient List WebRowSet is Null");

			}

		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getPatientListingView()-->", e
							.getMessage());
		}

		return sb.toString();

	}
 
	/*public static String getOnLineReconcileTariffDetailsPopUp(
			LFNoTransVO voObj) {
		StringBuffer sb = new StringBuffer("");

		String strValmode = voObj.getStrOnLineReconcileTariffHiddenValue();

		String val[] = strValmode.replace('^', '#').split("#");

		String strTariffRatePerUnit = val[0];
		String strDisountUnit = val[1];
		String strServiceTax = val[3];
		if (val[2].equals("1")) {

			strDisountUnit = strDisountUnit + " Fixed";
		} else {
			strDisountUnit = strDisountUnit + " %";
		}

		try {

			sb
					.append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");
			sb
					.append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");
			sb
					.append("<td align='right'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"reconcilTariffPopupDetailsDivId\");'> </td></tr>");
			sb.append("</table> ");
			sb.append("<table width='400' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td class='multiLabel' width='20%'>Rate(Rs)/Unit</td>");
			sb.append("<td class='multiControl' width='20%'>"
					+ strTariffRatePerUnit + "</td>");
			sb.append("<td class='multiLabel' width='20%'>Discount(Rs)</td>");
			sb.append("<td class='multiControl' width='20%'>" + strDisountUnit
					+ "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class='multiLabel' width='20%'>Service Tax(%)</td>");
			sb.append("<td class='multiControl' width='20%'>" + strServiceTax
					+ "</td>");
			sb.append("<td class='multiLabel' colspan='2' width='20%'></td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			sb.append("<table width='400' align='center'>");
			sb.append("<tr class='FOOTER'>");
			sb.append("<td colspan='3'>&nbsp;</td>");
			sb.append("</tr></table>");
		} catch (Exception e) {

			new HisException(
					"Cash Collection Trans ",
					"CashCollectionTransHLP.getOnLineReconcileTariffDetailsPopUp()-->",
					e.getMessage());
		}

		return sb.toString();

	}
*/
	public static String getAdmissionCancellationDetails(
			LFNoTransVO voObj, String mode) {
		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = null;

		try {

			ws = voObj.getAdmissionCancellationDetails();

			if (ws.next()) {

				String strAccountNo = ws.getString(1);
				String strReceivedAmt = ws.getString(2);
				String strExpenceAmt = ws.getString(3);
				String strRefundAmt = ws.getString(4);
				String strBillNo = ws.getString(7);

				sb
						.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb
						.append("<td colspan='4' class='TITLE'>Refund Advance Details</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb
						.append("<td class='LABEL' width='25%'>Received Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb
						.append("<td class='CONTROL' width='25%' style='font-weight:bold'>"
								+ strReceivedAmt
								+ "<input type='hidden' name='strAdmissionCancellationReceivedAmount' value='"
								+ strReceivedAmt
								+ "'><input type='hidden' name='strBillNo' value='"
								+ strBillNo
								+ "'><input type='hidden' name='strRefundAdvancePaneltyAmt' value='0'></td>");
				sb
						.append("<td class='LABEL' width='25%'>Expense Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb
						.append("<td class='CONTROL' width='25%' style='font-weight:bold'>"
								+ strExpenceAmt
								+ "<input type='hidden' name='strAdmissionCancellationExpenseAmount' value='"
								+ strExpenceAmt + "'></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				if (mode.equals("2")) {
					sb.append("<td class='LABEL' width='25%'>Refund Type</td>");
					sb
							.append("<td class='CONTROL' width='25%'><select onchange='setAdmissionCancellationPenalty(this);' name='strAdmissionCancellationRefundType' ><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select></td>");
					sb.append("<td class='LABEL'  width='25%'>Penalty</td>");
					sb
							.append("<td class='CONTROL' width='25%' style='font-weight:bold'><input type='hidden' name='strAdmissionCancellationPenalty' value='0'><div id='strAdmissionCancellationPenaltyDivId'>0 %</div></td>");
				} else {
					sb.append("<td class='multiLabel'  width='25%'></td>");
					sb
							.append("<td class='CONTROL' width='25%' style='font-weight:bold'><input type='hidden' name='strAdmissionCancellationPenalty' value='0'><div id='strAdmissionCancellationPenaltyDivId'>0 %</div></td>");
					sb
							.append("<td class='CONTROL' colspan='3' width='20%'></td>");
				}
				sb.append("</tr>");
				sb.append("</table>");

				sb.append("^" + strRefundAmt + "^" + strAccountNo);

			}
		} catch (Exception e) {

			new HisException(
					"Cash Collection Trans ",
					"CashCollectionTransHLP.getAdmissionCancellationDetails()-->",
					e.getMessage());
		}

		return sb.toString();

	}
	
	
	public static String getPartPaymentCancellationDetails(
			LFNoTransVO voObj, String mode) {
		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = null;

		try {

			//ws = voObj.getAdmissionCancellationDetails();
			ws = voObj.getOfflineBillList();
			if (ws.next()) {

				String strReceivedAmt ="0";
				String strBillNo = voObj.getStrBillNo();
				String strExpenceAmt = "0";
				String strRefundAmt = "0";
				String strAccountNo="abc";
				//String strAccountNo = ws.getString(1);
				//String strReceivedAmt = ws.getString(2);
				//String strExpenceAmt = ws.getString(3);
				//String strRefundAmt = ws.getString(4);
				//String strBillNo = ws.getString(7);

				sb
						.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb
						.append("<td colspan='4' class='TITLE'>Refund PartPayment Details</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb
						.append("<td class='LABEL' width='25%'>Received Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb
						.append("<td class='CONTROL' width='25%' style='font-weight:bold'>"
								+ strReceivedAmt
								+ "<input type='hidden' name='strAdmissionCancellationReceivedAmount' value='"
								+ strReceivedAmt
								+ "'><input type='hidden' name='strBillNo' value='"
								+ strBillNo
								+ "'><input type='hidden' name='strRefundAdvancePaneltyAmt' value='0'></td>");
				sb
						.append("<td class='LABEL' width='25%'>Expense Amount (<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb
						.append("<td class='CONTROL' width='25%' style='font-weight:bold'>"
								+ strExpenceAmt
								+ "<input type='hidden' name='strAdmissionCancellationExpenseAmount' value='"
								+ strExpenceAmt + "'></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				if (mode.equals("2")) {
					sb.append("<td class='LABEL' width='25%'>Refund Type</td>");
					sb
							.append("<td class='CONTROL' width='25%'><select onchange='setAdmissionCancellationPenalty(this);' name='strAdmissionCancellationRefundType' ><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select></td>");
					sb.append("<td class='LABEL'  width='25%'>Penalty</td>");
					sb
							.append("<td class='CONTROL' width='25%' style='font-weight:bold'><input type='hidden' name='strAdmissionCancellationPenalty' value='0'><div id='strAdmissionCancellationPenaltyDivId'>0 %</div></td>");
				} else {
					sb.append("<td class='multiLabel'  width='25%'></td>");
					sb
							.append("<td class='CONTROL' width='25%' style='font-weight:bold'><input type='hidden' name='strAdmissionCancellationPenalty' value='0'><div id='strAdmissionCancellationPenaltyDivId'>0 %</div></td>");
					sb
							.append("<td class='CONTROL' colspan='3' width='20%'></td>");
				}
				sb.append("</tr>");
				sb.append("</table>");

				sb.append("^" + strRefundAmt + "^" + strAccountNo);

			}
		} catch (Exception e) {

			new HisException(
					"Cash Collection Trans ",
					"CashCollectionTransHLP.getPartPaymentCancellationDetails()-->",
					e.getMessage());
		}

		return sb.toString();

	}
	public static String getLFAccountSummary(WebRowSet wb,String status)
			throws SQLException {
		StringBuffer br = new StringBuffer();

	    String strLFANo = "";
		String strIssueNo = "";
		String strReqDate = "";
		String strGroupName = "";
		String strtrName = "";
		String strtraiffRate="";
		String strNetAmount="";
		String strtrfid="";
		String GrossAmont = "";
		int i = 0;
		//WebRowSet ws = wb;
		try 
		{
			/*br.append("<table class='TABLEWIDTH' align='center' cellpadding='1' cellspacing='1'>"); 
			br.append("<tr>");
			br.append("<td class='TITLE' colspan='2'>");
			br.append("<div id='' style='font-family: Arial, Helvetica, sans-serif;font-size:13px;'>&nbsp;Issue Details</div>");
			br.append("</td></tr></table>");
			*/
			if (wb.size() == 0) 
			{
				br
				.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
		br.append("<tr>");
		br.append("<td  CLASS='multiControl' colspan='5'><font color='red'>"
				+ "No Record Found" + "</font></td>");
		br.append("</tr>");
		br.append("</table> ");
		}
			else
			{
			br.append("<table class='TABLEWIDTH' align='center' bgcolor='#1c7bb2' cellpadding='1px' cellspacing='1px'>");
			br.append("<tr>");
			//br.append("<td class='multiRPTLabel' WIDTH='15%' align='CENTER'>LF No.</td>");
			br.append("<td class='multiRPTLabel' WIDTH='20%' align='CENTER'>Request No</td>");
			br.append("<td class='multiRPTLabel' WIDTH='20%' align='CENTER'>Request Date</td>");
			br.append("<td class='multiRPTLabel' WIDTH='20%' align='CENTER'>Group Name</td>");
			br.append("<td class='multiRPTLabel' WIDTH='10%' align='CENTER'>Tariff Name</td>");
			br.append("<td class='multiRPTLabel' WIDTH='15%' align='CENTER'>Tariff Rate</td>");
			//br.append("<td class='multiRPTLabel' WIDTH='10%' align='CENTER'>Bill Qty</td>");
			br.append("<td class='multiRPTLabel' WIDTH='15%' align='CENTER'>Amount(Rs.)</td>");
		//	br.append("<td class='multiRPTLabel' WIDTH='10%' align='CENTER'>#</td>");

			br.append("</tr>");
			if (wb.size() != 0) 
			{
				while (wb.next()) 
				{
					strLFANo  = wb.getString(1);
					System.out.println("strLFANo>>>>>>>>>>>>>"+strLFANo);
				}
				
			
			
			br.append("<tr>");	 
			br.append("<td WIDTH='95%' colspan='6' CLASS=''   align='left'><b><div  align='left'><font color='white'>LF No -"  + strLFANo + "</font></div></b></td></tr>");
		
			wb.beforeFirst();
			}
			else {
				br
						.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
				br.append("<tr>");
				br.append("<td  CLASS='multiControl' colspan='5'><font color='red'>"
						+ "No Record Found" + "</font></td>");
				br.append("</tr>");
				br.append("</table> ");
				
				
				
				
			}
			if (wb.size() != 0) 
			{
			
				while (wb.next()) 
				{
					
					strIssueNo = wb.getString(2);
					strReqDate = wb.getString(3);
					strGroupName  = wb.getString(4);
					strtrName   = wb.getString(5);
					strtraiffRate = wb.getString(6);
					strNetAmount = wb.getString(8);
					strtrfid= wb.getString(9);
					GrossAmont = wb.getString(10);
					System.out.println("strLFANo"+strLFANo);
					System.out.println("strIssueNo"+strIssueNo);
					System.out.println("strReqDate"+strReqDate);
					System.out.println("strGroupName"+strGroupName);
					System.out.println("strtrName"+strtrName);
					System.out.println("strNetAmount"+strNetAmount);
					System.out.println("strtrfid"+strtrfid);
//					br.append("<input type='hidden' name='strHlpIssueNo' id='strHlpIssueNo" +i+ "' value=" + wb.getString(2) + " />");
//					br.append("<input type='hidden' name='strHlpIndentNo' id='strHlpIndentNo" +i+ "' value=" + strLFANo + " />");
					br.append("<input type='hidden' name='strHlpIssueNo' id='strHlpIssueNo" +i+ "' value=" +strIssueNo + " />");
					br.append("<input type='hidden' name='strHlpIndentDate' id='strHlpIssueNDate" +i+ "' value=" +strReqDate + " />");
					br.append("<input type='hidden' name='strHlpCrNo' id='strHlpCrNo" +i+ "' value=" +strGroupName + " />");
					br.append("<input type='hidden' name='strHlpPatientName' id='strHlpPatientName" +i+ "' value=" + strtrName + " />");
					br.append("<input type='hidden' name='strHlpPatientName' id='strHlpPatientName" +i+ "' value=" +strtraiffRate + " />");
					br.append("<tr>");	
					br.append("<td WIDTH='15%' CLASS='multiPOControl' >"  + strIssueNo + "</td>");
					br.append("<td WIDTH='10%' CLASS='multiPOControl' >"  + strReqDate + "</td>");

					br.append("<td WIDTH='15%' CLASS='multiPOControl' >"	+strGroupName+ "</td>");
					br.append("<td WIDTH='20%' CLASS='multiPOControl' >"	+ strtrName + "</td>");
					br.append("<td WIDTH='20%' CLASS='multiPOControl' >"	+ strtraiffRate + "</td>");
                      if(strtrfid.equals("26")||strtrfid.equals("27") )
					br.append("<td WIDTH='20%' CLASS='multiPOControl' >"	+ strNetAmount + "Cr.</td>");
                      else  if(strtrfid.equals("10"))
                    br.append("<td WIDTH='20%' CLASS='multiPOControl' >"	+ strNetAmount + "Db.</td>");
                      else
                    	  br.append("<td WIDTH='20%' CLASS='multiPOControl' >"	+ strNetAmount + "</td>");
				//	br.append("<td WIDTH='10%' CLASS='multiPOControl' ><img src='../../hisglobal/images/search_icon1.gif' onclick='generateIssueReportFunc(this,\""+ i + "\");' style='cursor: pointer;' title='Print Issue Item Details'></td>");

					br.append("</tr>");
					i++;
				}
				br.append("<tr>");	 
				br.append("<td colspan='6' CLASS=''><b><div  align='right'><font color='white'>Current Balance :: " + GrossAmont + "<font></div></b></td></tr>");
				
			//	br.append("<td colspan='2'><b><div  align=''>"  + GrossAmont + "</div></b></td></tr>");
			
				br.append("</table> ");
				if(status.equals("2"))
				{
				br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px'");
				br.append("cellspacing='1px' style='display: block;'>");
				br.append("<tr><td width='95%' class='LABEL' align='right'>Net  Refundable Amount(cash)");
				br.append("(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				br.append("<td width='05%' class='CONTROL' style='font-weight: bold'>");
				br.append("<td class='CONTROL'  align='Left'><div id='totalRecAmtDivId'>"+GrossAmont+"</div></td></tr></table>");
				}
				
			
				
				System.out.println("br >>>>"+br);
			} else {
				br
						.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
				br.append("<tr>");
				br.append("<td  CLASS='multiControl' colspan='5'>"
						+ "No Record Found" + "</td>");
				br.append("</tr>");
				br.append("</table> ");
			}
			System.out.println("br>> "+br);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return "ERROR";

		}

		return br.toString();
	}
	
	public static String LFAccountDetail(LFNoTransVO vo)
			throws Exception {
		WebRowSet ws = null;
		GlobalVO voObj = new GlobalVO();
		PatientDtlBO boObj = new PatientDtlBO();
		StringBuffer sb = new StringBuffer("");

		//voObj.setStrValue1(strCrNo);
		/*formBean.setStrLFAccountNo(voObj.getStrLFAccountNo());
		formBean.setStrLFAccountOpenDate(voObj.getStrLFAccountOpenDate());
		formBean.setStrLFDepositedAmount(voObj.getStrLFDepositedAmount());
		formBean.setStrLFBalanceAmount(voObj.getStrLFBalanceAmount());
		formBean.setStrLFAccountStatus(voObj.getStrLFAccountStatus());
		if(voObj.getStrAccountStatus().equals("3")*/
		
		
		sb.append("");

		try {
								
					/*sb.append("<script type='text/javascript'>  ");
					sb.append(" function showPatDetails(divId){  ");
					sb
							.append(" document.getElementById(divId).style.display='block';  ");
					sb
							.append(" document.getElementById('minusId'+divId).style.display='block';  ");
					sb
							.append(" document.getElementById('plusId'+divId).style.display='none';	}  ");

					sb.append("  function hidePatDetails(divId){  ");
					sb
							.append(" document.getElementById(divId).style.display='none';  ");
					sb
							.append("	document.getElementById('minusId'+divId).style.display='none';  ");
					sb
							.append(" document.getElementById('plusId'+divId).style.display='block'; }  ");
					sb.append("	</script>  ");

					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> <tr><td>");
					sb
							.append("<table width='100%' align='center' cellpadding='0' cellspacing='0'> ");
					sb.append("<tr class='HEADER'> ");
					sb.append("<td width='20'> ");
					sb
							.append("<div id='plusIdonLineGblPatId' style='display:block'> ");
					sb
							.append("<img src='../../hisglobal/images/plus.gif'  name='plusonLine' style='cursor:hand;cursor:pointer' align='middle' onclick='showPatDetails(\"onLineGblPatId\");' /></div> ");
					sb
							.append("<div id='minusIdonLineGblPatId' style='display:none'><img src='../../hisglobal/images/minus.gif' style='cursor:hand;cursor:pointer' name='minusonLine' onclick='hidePatDetails(\"onLineGblPatId\");'></div> ");
					sb.append("</td> ");
					sb.append("<td colspan='2'> &nbsp;LF Account No : ");
					sb.append(vo.getStrLFAccountNo());
					sb.append(" </td> ");
					sb.append(" <td > <div align='right'>");
					sb.append("Status : " + vo.getStrLFAccountStatus() + "&nbsp;");
					sb.append(" </div> </td> ");
					sb.append("</tr> ");
					sb.append("</table> ");

					sb.append(" </td> ");
					sb.append("</tr> ");
					sb.append("</table> ");*/

					sb
							.append("<div id='onLineGblPatId' style='display: block'>");
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					sb.append("<tr><td width='25%' class='LABEL'>LF No.</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(vo.getStrLFAccountNo());
					sb.append("<td width='25%' class='LABEL'>Account Status</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(vo.getStrLFAccountStatus());
					sb.append("</td></tr>");
					
					sb.append("<tr><td width='25%' class='LABEL'>Account Open Date</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(vo.getStrLFAccountOpenDate());
					sb.append("<td width='25%' class='LABEL'>Account Balance</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(vo.getStrLFBalanceAmount());
					sb.append("</td></tr>");
					
					sb.append("</table>");
				}
			
		 catch (Exception e) {
			throw new Exception("LFNoHLP-->LFAccountDetail-->"
					+ e.getMessage());
		} 
		return sb.toString();
	}


}
