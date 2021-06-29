package mms.transactions.controller.hlp;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

public class TransferApprovalTransHLP {

	public static String getDemandRequestDetails(WebRowSet ws) {
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 5;
		final int PAGE_PER_BLOCK = 20;
		int count = 0;
		String strApplyClass = "";

		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;

		try {

			if (ws != null && ws.size() != 0) {
				// System.out.println("Hello1");
				int actualFetchRecord = ws.size();

				if (totalFetchRecord != actualFetchRecord) {
					totalFetchRecord = actualFetchRecord;
					totalRecordsToManipulate = actualFetchRecord;
				}
				int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
				int reminder = totalRecordsToManipulate % REC_PER_PAGE;
				if (reminder > 0)
					totalLayer = totalLayer + 1;

				sb.append("<input type='hidden' name='TotalLayer'  value='")
						.append(totalLayer).append("'>");
				sb.append(
						"<input type='hidden' name='RecordShowOnPage'  value='")
						.append(REC_PER_PAGE).append("'>");
				sb.append("<table class='TABLEWIDTH' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");
				sb.append("<tr>");
				sb.append("<td class='LABEL'>");
				for (int i = 1; i <= totalLayer; i++) {
					sb.append("<a name='pg' id='pg"
							+ i
							+ "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""
							+ i + "\",\"" + totalLayer + "\")'>"
							+ (i + start - 1) + "</a>|&nbsp;");
				}
				sb.append("</td></tr>");
				sb.append("</table>");
				sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' >");
			
				sb.append("<tr>");
				sb.append("<td width='5%' class='multiLabel'>#</td>");
				sb.append("<td width='10%' class='multiLabel'>DDW Name</td>");
				sb.append("<td width='10%' class='multiLabel'>Demand No.</td>");
				sb.append("<td width='20%' class='multiLabel'>Demand Date</td>");
				sb.append("<td width='40%' class='multiLabel'>Drug Name</td>");
				sb.append("<td width='15%' class='multiLabel'>Request Qty.</td>");
				sb.append("</tr>");
				sb.append("</table>");

				for (int i = 1; i <= totalLayer; i++) {
					if (i <= totalLayer) {

						if (i == 1) {
							sb.append("<div id='DivId").append(i)
									.append("' style='display:block'>");
						} else {
							sb.append("<div id='DivId").append(i)
									.append("' style='display:none'>");
						}

						sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");

						for (int j = 0; j < REC_PER_PAGE; j++) {

							if (ws.next()) {

								if (ws.getString(9).equals("0")) {

									strApplyClass = "Approved";

								} else {
									strApplyClass = "NotApproved";

								}

								if (ws.getString(9).equals("0")) {
									sb.append("<tr >");
									sb.append("<td width='5%' class='"
											+ strApplyClass + "'></td>");

								} else {
									sb.append("<tr>");
									sb.append("<td width='5%'  class='"
											+ strApplyClass
											+ "'><input type='radio' onclick='getTransferingDetails(this);' name='strDemandRequest' id='strDemandRequestRadioId"
											+ count + "' value='"
											+ ws.getString(6) + "^"
											+ ws.getString(7) + "^"
											+ ws.getString(8) + "^"
											+ ws.getString(2) + "'></td>");
								}

								sb.append("</td><td width='10%'  class='"
										+ strApplyClass + "'>");
								sb.append(ws.getString(1));
								sb.append("</td><td width='10%'  class='"
										+ strApplyClass + "'>");
								sb.append(ws.getString(2));
								sb.append("</td><td width='20%'  class='"
										+ strApplyClass + "'>");
								sb.append(ws.getString(3));
								sb.append("</td><td width='40%'  class='"
										+ strApplyClass + "'>");
								sb.append(ws.getString(4));
								sb.append("</td><td width='15%'  class='"
										+ strApplyClass + "'>");
								sb.append(ws.getString(5));
								sb.append("</td></tr>");

							} else {
								break;
							}
							count++;
						}
						sb.append("</table>");
						sb.append("</div>");

					} else {
						sb.append("<div id='DivId").append(i)
								.append("' style='display:none'>");

						sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'    cellpadding='1px'>");
						for (int k = 0; k < reminder; k++) {
							ws.next();

							if (ws.getString(9).equals("0")) {

								strApplyClass = "Approved";

							} else {
								strApplyClass = "NotApproved";

							}

							if (ws.getString(9).equals("0")) {
								sb.append("<tr >");
								sb.append("<td width='5%' class='"
										+ strApplyClass + "'></td>");

							} else {
								sb.append("<tr>");
								sb.append("<td width='5%'  class='"
										+ strApplyClass
										+ "'><input type='radio' onclick='getTransferingDetails(this);' name='strDemandRequest' id='strDemandRequestRadioId"
										+ count + "' value='"
										+ ws.getString(6) + "^"
										+ ws.getString(7) + "^"
										+ ws.getString(8) + "^"
										+ ws.getString(2) + "'></td>");
							}

							sb.append("</td><td width='10%'  class='"
									+ strApplyClass + "'>");
							sb.append(ws.getString(1));
							sb.append("</td><td width='10%'  class='"
									+ strApplyClass + "'>");
							sb.append(ws.getString(2));
							sb.append("</td><td width='20%'  class='"
									+ strApplyClass + "'>");
							sb.append(ws.getString(3));
							sb.append("</td><td width='40%'  class='"
									+ strApplyClass + "'>");
							sb.append(ws.getString(4));
							sb.append("</td><td width='15%'  class='"
									+ strApplyClass + "'>");
							sb.append(ws.getString(5));
							sb.append("</td></tr>");
							count++;

						}
						sb.append("</table>");
						sb.append("</div>");
					}
				}
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'  >");
				sb.append("<tr class='TITLE'>");
				sb.append("<td colspan='6' align='right' >The Records in Blue Color doesn't contain any Transfer Details</td>");
				sb.append("</tr>");
				sb.append("</table>");
			} else {
				sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'  >");
				sb.append("<tr>");
				sb.append("<td colspan='6' class='TITLE'>Demand Request Details</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td width='5%' class='multiLabel'>#</td>");
				sb.append("<td width='10%' class='multiLabel'>DDW Name</td>");
				sb.append("<td width='10%' class='multiLabel'>Demand No.</td>");
				sb.append("<td width='20%' class='multiLabel'>Demand Date</td>");
				sb.append("<td width='40%' class='multiLabel'>Drug Name</td>");
				sb.append("<td width='15%' class='multiLabel'>Request Qty.</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' cellpadding='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiControl'><font color='red'>No Record Found</font></td>");
				sb.append("</tr>");
				sb.append("</table>");

			}

		} catch (Exception e) {
			e.printStackTrace();
			new HisException("Transfer Approval Details",
					"TransferApprovalTransHLP.getDemandRequestDetails()-->",
					e.getMessage());
		}
		// System.out.println("sb"+sb);
		return sb.toString();
	}

	public static String getTransferingDetails(WebRowSet ws) {
		StringBuffer sb = new StringBuffer("");

		int count = 0;

		try {

			sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' >");
			sb.append("<tr>");
			sb.append("<td colspan='6' class='TITLE'>Transfering Details</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='5%' class='multiLabel'>#</td>");
			sb.append("<td width='30%' class='multiLabel'>DDW Name</td>");
			sb.append("<td width='15%' class='multiLabel'>Request No.</td>");
			sb.append("<td width='20%' class='multiLabel'>Request Date</td>");
			sb.append("<td width='15%' class='multiLabel'>Balance Qty.</td>");
			sb.append("<td width='15%' class='multiLabel'>Order Qty.</td>");
			sb.append("</tr>");
			sb.append("</table>");

			if (ws != null && ws.size() > 0) {

				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'   cellpadding='1px'>");

				while (ws.next()) {

					count = count + 1;

					sb.append("<tr>");
					sb.append("<td width='5%' class='multiControl'>");
					sb.append("<input type='checkbox' name='strTransferingDtls' id='strTransferingDtlsCheckId"
							+ count + "' value='"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(9)+"^"+ws.getString(2)+"' onclick='return enableTransferQty(this , "+count+" );' />");
					sb.append("</td> <td width='30%' class='multiControl'>");
					sb.append(ws.getString(1));
					sb.append("</td><td width='15%' class='multiControl'>");
					sb.append(ws.getString(2));
					sb.append("</td><td width='15%' class='multiControl'>");
					sb.append(ws.getString(3));
					sb.append("</td><td width='15%' class='multiControl'><span style='color:blue;cursor: pointer;' onclick='displayPopupDtls(this , \""
							+ ws.getString(5)
							+ " \" , \""
							+ ws.getString(6)
							+ " \");' >");
					sb.append(ws.getString(4));
					sb.append(
							"</span></td><td width='15%' class='multiControl'>")
							.append("<input type='text' disabled='disabled' name='strTransferOrderQty' autocomplete='off' onkeypress='return validateData(event,5);' id='strTransferOrderQty" 
									+ count
									+ "' class='txtFldMin' value='0' onkeyup='checkTransferTotalOrder(this , "+count+");' />");
					sb.append("</td></tr>");
				}

				sb.append("<tr>");
				sb.append("<td colspan='5' width='10%' class='LABEL'>Total Order Qty. </td>");
				sb.append("<td width='10%' class='multiControl'><div style='display:none;'><input type='text' class='txtFldMin' name='strTotalOrderQty' value='0'></div><div style='font-weight:bold' id='strTotalOrderQtyDivId'>0</div></td>");

				sb.append("</table>");
			} else {

				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'   cellpadding='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiControl'><font color='red'>No Record Found</font></td>");
				sb.append("</tr>");
				sb.append("</table>");

			}

		} catch (Exception e) {
			e.printStackTrace();
			new HisException("Transfer Approval Details",
					"TransferApprovalTransHLP.getTransferingDetails()-->",
					e.getMessage());
		}

		return sb.toString();
	}

	

	public static String getTransferingDetailsModify(WebRowSet ws) {
		StringBuffer sb = new StringBuffer("");

		String strTotal = "0";
		int count = 0;

		try {

			sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' >");
			sb.append("<tr>");
			sb.append("<td colspan='6' class='TITLE'>Transfering Details</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td width='5%' class='multiLabel'>#</td>");
			sb.append("<td width='30%' class='multiLabel'>DDW Name</td>");
			sb.append("<td width='15%' class='multiLabel'>Request No.</td>");
			sb.append("<td width='20%' class='multiLabel'>Request Date</td>");
			sb.append("<td width='15%' class='multiLabel'>Balance Qty.</td>");
			sb.append("<td width='15%' class='multiLabel'>Order Qty.</td>");
			sb.append("</tr>");
			sb.append("</table>");

			if (ws != null && ws.size() > 0) {

				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'   cellpadding='1px'>");

				while (ws.next()) {

					count = count + 1;

					if(count == 1){
						
						strTotal = ws.getString(9);
						
						sb.append("<tr>");
						sb.append("<td width='5%' class='multiControl'>");
						sb.append("<input type='checkbox' name='strTransferingDtls' checked='checked' id='strTransferingDtlsCheckId"
								+ count + "' value='"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(12)+"^"+ws.getString(2)+"' onclick='return enableTransferQty(this , "+count+" );' />");
						sb.append("</td> <td width='30%' class='multiControl'>");
						sb.append(ws.getString(1));
						sb.append("</td><td width='15%' class='multiControl'>");
						sb.append(ws.getString(2));
						sb.append("</td><td width='15%' class='multiControl'>");
						sb.append(ws.getString(3));
						sb.append("</td><td width='15%' class='multiControl'><span style='color:blue;cursor: pointer;' onclick='displayPopupDtls(this , \""
								+ ws.getString(5)
								+ " \" , \""
								+ ws.getString(6)
								+ " \");' >");
						sb.append(ws.getString(4));
						sb.append(
								"</span></td><td width='15%' class='multiControl'>")
								.append("<input type='text' name='strTransferOrderQty' autocomplete='off' onkeypress='return validateData(event,5);' id='strTransferOrderQty" 
										+ count
										+ "' class='txtFldMin' value='"+ws.getString(9)+"' onkeyup='checkTransferTotalOrderModify(this , "+count+");' />");
						sb.append("</td></tr>");
						
					}else{
						
						sb.append("<tr>");
						sb.append("<td width='5%' class='multiControl'>");
						sb.append("<input type='checkbox' name='strTransferingDtls' id='strTransferingDtlsCheckId"
								+ count + "' value='"+ws.getString(7)+"^"+ws.getString(8)+"^"+ws.getString(12)+"^"+ws.getString(2)+"' onclick='return enableTransferQty(this , "+count+" );' />");
						sb.append("</td> <td width='30%' class='multiControl'>");
						sb.append(ws.getString(1));
						sb.append("</td><td width='15%' class='multiControl'>");
						sb.append(ws.getString(2));
						sb.append("</td><td width='15%' class='multiControl'>");
						sb.append(ws.getString(3));
						sb.append("</td><td width='15%' class='multiControl'><span style='color:blue;cursor: pointer;' onclick='displayPopupDtls(this , \""
								+ ws.getString(5)
								+ " \" , \""
								+ ws.getString(6)
								+ " \");' >");
						sb.append(ws.getString(4));
						sb.append(
								"</span></td><td width='15%' class='multiControl'>")
								.append("<input type='text' disabled='disabled' name='strTransferOrderQty' autocomplete='off' onkeypress='return validateData(event,5);' id='strTransferOrderQty" 
										+ count
										+ "' class='txtFldMin' value='"+ws.getString(9)+"' onkeyup='checkTransferTotalOrderModify(this , "+count+");' />");
						sb.append("</td></tr>");
						
					}
					
					
				}

				sb.append("<tr>");
				sb.append("<td colspan='5' width='10%' class='LABEL'>Total Order Qty. </td>");
				sb.append("<td width='10%' class='multiControl'><div style='display:none;'><input type='text' class='txtFldMin' name='strTotalOrderQty' value='"+strTotal+"'></div><div style='font-weight:bold' id='strTotalOrderQtyDivId'>"+strTotal+"</div></td>");

				sb.append("</table>");
			} else {

				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'   cellpadding='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiControl'><font color='red'>No Record Found</font></td>");
				sb.append("</tr>");
				sb.append("</table>");

			}

		} catch (Exception e) {
			e.printStackTrace();
			new HisException("Transfer Approval Details",
					"TransferApprovalTransHLP.getTransferingDetails()-->",
					e.getMessage());
		}

		return sb.toString();
	}
	
}
