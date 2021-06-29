package mms.transactions.controller.hlp;

import hisglobal.exceptions.HisException;

import javax.sql.rowset.WebRowSet;

import mms.transactions.controller.fb.TransferDemandReqTransFB;

public class TransferDemandReqTransHLP 
{
	public static String getDrugDetails(WebRowSet wb,TransferDemandReqTransFB formBean,String index) 
	{
		StringBuffer br = new StringBuffer("");
		try 
		{			
			if (wb.size() != 0) 
			{
				
				br.append("<table width='400' border='0' bgcolor='#6097BC' cellspacing ='1' cellpGenerateing='1'>");
				br.append("<tr class='HEADER'>");
				br.append("<th align='right' colspan=4><img  style='cursor:pointer;cursor:pointer' title='To Close PopUp Window' src='../../hisglobal/images/popUp_cancel.JPG'");
				br.append("onClick='hideDrugDetails(\"drugDtlId\",\""+index+"\");'></th>");
				br.append("</tr>");
				br.append("</table>");
				br.append("<table width='400' border='0' cellspacing='1' cellpadding='1'>");
				br.append("<tr>");
				br.append("<td colspan='1' class='multiRPTLabel'>Batch No.</td>");
				br.append("<td colspan='1' class='multiRPTLabel'>Exp. Date</td>");
				br.append("<td colspan='1' class='multiRPTLabel'>Transfer Qty.</td>");
				br.append("<td colspan='1' class='multiRPTLabel'>Rec Qty.</td>");
				br.append("</tr>");				
				int nTmpI = 0;
				while (wb.next()) 
				{
					br.append("<tr>");
					br.append("<td       colspan='1' class='multiPOControl'>");
					br.append(wb.getString(1));
					br.append("</td><td  colspan='1' class='multiPOControl'>");
					br.append(wb.getString(2));
					br.append("</td><td  colspan='1' class='multiPOControl'>");
					br.append(wb.getString(3));
					br.append("</td><td  colspan='1' class='multiPOControl'>");
					br.append(wb.getString(4));
					br.append("</td></tr>");
					nTmpI++;
				}
				br.append("<tr class='FOOTER'>");
				br.append("<th align='right' colspan=4></th>");
				br.append("</tr>");
				br.append("</table>");
			} 
			else 
			{
				br.append("<tr>");
				br.append("<td colspan='4'  CLASS='multiControl' ><DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED TRANSFER NO </div></TD>");
				br.append("</tr>");
			}
		} catch (Exception _e) 
		{
			_e.printStackTrace();
			formBean.setStrMsgString("TransferDemandReqTransHLP.getDrugDetails() --> "+ _e.getMessage());
			formBean.setStrMsgType("1");
		}
		return br.toString();
	}
	

	public static String getTransferDetails(TransferDemandReqTransFB formBean) {
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 50;
		final int PAGE_PER_BLOCK = 50;
		int count = 0;
		WebRowSet ws = null;
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;

		try {

			ws = formBean.getWbTransferOrderDetail();

			if (ws != null && ws.size() != 0)
			{
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
						sb.append("<a name='pg1' id='pg1"
								+ i
								+ "'  STYLE='cursor:pointer;cursor:pointer;' class='pg-normal' title='Record Indexing' onClick='GetIndex1(\""
								+ i + "\",\"" + totalLayer + "\")'>"
								+ (i + start - 1) + "</a>|&nbsp;");
					}
					sb.append("</td></tr>");
					sb.append("</table>");

					sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
					sb.append("<tr>");
					sb.append("<td      width='20%' class='multiRPTLabel'>Transfer No");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Transfer Date");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Transfer Qty.");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Received Qty.");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Received Date.");
					sb.append("</td></tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) {
						if (i <= totalLayer) {

							if (i == 1) {
								sb.append("<div id='DivId1").append(i)
										.append("' style='display:block'>");
							} else {
								sb.append("<div id='DivId1").append(i)
										.append("' style='display:none'>");
							}

							sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
							for (int j = 0; j < REC_PER_PAGE; j++) {
								/*
								 * 1- Transfer No 2- Transfer Date 3- Transfer
								 * Qty with Unit Name 4- Recived Date 5- Recived
								 * Qty with Unit Name 6- Store ID
								 */
								if (ws.next()) 
								{
									String transNo = (ws.getString(1) == null || ws
											.getString(1).equals("")) ? "0"
											: ws.getString(1);
									String transDate = (ws.getString(2) == null || ws
											.getString(2).equals("")) ? "NA"
											: ws.getString(2);

									sb.append("<input type='hidden' name='strTransferNo' id='strTransferNo"+count+"' value='"+transNo+"'>");
								    sb.append("<input type='hidden' name='strTransferStoreId' id='strTransferStoreId"+count+"' value='"+ws.getString(6)+"'>");
									sb.append("<tr>");
									sb.append("<td width='20%' class='multiPOControl'>");
									sb.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+transNo+"</a> &nbsp;");
									sb.append("<div class='popup' id='drugDtlId");
									sb.append(count);
									sb.append("' style='display:none'>");
									sb.append("</div>");
									sb.append("</td><td width='20%' class='multiPOControl'>");
									sb.append(transDate);					
									sb.append("</td><td width='20%' class='multiPOControl'>");
									sb.append(ws.getString(3));
									sb.append("</td><td width='20%' class='multiPOControl'>");
									sb.append(ws.getString(5));
									sb.append("</td><td width='20%' class='multiPOControl'>");
									if(ws.getString(4)!="" || !ws.getString(4).equals(""))
									{	
									    sb.append(ws.getString(4));
									}
									else
									{
										sb.append("NA");
									}
									sb.append("</td></tr>");
								} else {
									break;
								}
								count++;
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							sb.append("<div id='DivId1").append(i)
									.append("' style='display:none'>");

							sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
							for (int k = 0; k < reminder; k++) {
								/*
								 * 1- Transfer No 2- Transfer Date 3- Transfer
								 * Qty with Unit Name 4- Recived Date 5- Recived
								 * Qty with Unit Name 6- Store ID
								 */
								ws.next();
								String transNo = (ws.getString(1) == null || ws
										.getString(1).equals("")) ? "0" : ws
										.getString(1);
								String transDate = (ws.getString(2) == null || ws
										.getString(2).equals("")) ? "NA" : ws
										.getString(2);

								sb.append("<input type='hidden' name='strTransferNo' id='strTransferNo"+count+"' value='"+transNo+"'>");
							    sb.append("<input type='hidden' name='strTransferStoreId' id='strTransferStoreId"+count+"' value='"+ws.getString(6)+"'>");
								sb.append("<tr>");
								sb.append("<td width='20%' class='multiPOControl'>");
								sb.append("<a STYLE='cursor:pointer;cursor:hand;color:blue' name='strDrugName' id='strDrugName"+count+"' onClick='drugDtl(this,\""+count+"\");'>"+transNo+"</a> &nbsp;");
								sb.append("<div class='popup' id='drugDtlId");
								sb.append(count);
								sb.append("' style='display:none'>");
								sb.append("</div>");
								sb.append("</td><td width='20%' class='multiPOControl'>");
								sb.append(transDate);					
								sb.append("</td><td width='20%' class='multiPOControl'>");
								sb.append(ws.getString(3));
								sb.append("</td><td width='20%' class='multiPOControl'>");
								sb.append(ws.getString(5));
								sb.append("</td><td width='20%' class='multiPOControl'>");
								if(ws.getString(4)!="" || !ws.getString(4).equals(""))
								{	
								    sb.append(ws.getString(4));
								}
								else
								{
									sb.append("NA");
								}
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
				}
			else 
			{
					sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
					sb.append("<tr>");
					sb.append("<td      width='20%' class='multiRPTLabel'>Transfer No");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Transfer Date");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Transfer Qty.");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Received Qty.");
					sb.append("</td><td width='20%' class='multiRPTLabel'>Received Date.");
					sb.append("</td></tr>");
					sb.append("</table>");
					sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
					sb.append("<tr>");
					sb.append("<td class='multiControl'><font color='red'>No Record Found</font></td>");
					sb.append("</tr>");
					sb.append("</table>");
				}
			

		} catch (Exception e) {
			e.printStackTrace();
			new HisException("Transfer Order Details",
					"TransferDemandReqTransHLP.getTransferOrderDetails()-->",
					e.getMessage());
		}
		// System.out.println("sb"+sb);
		return sb.toString();
	}

	public static String getTransferOrderDetails(
			TransferDemandReqTransFB formBean) {
		StringBuffer sb = new StringBuffer("");
		int start = 1;
		final int REC_PER_PAGE = 50;
		final int PAGE_PER_BLOCK = 50;
		int count = 0;
		WebRowSet ws = null;
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;

		try {

			ws = formBean.getWbTransferOrderDetail();

			if (ws != null && ws.size() > 0)
			{
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

				sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
				sb.append("<tr>");
				sb.append("<td width='5%' class='multiRPTLabel'>#");
				sb.append("</td><td width='15%' class='multiRPTLabel'>Order No");
				sb.append("</td><td width='20%' class='multiRPTLabel'>Order Date");
				sb.append("</td><td width='20%' class='multiRPTLabel'>Order To");
				sb.append("</td><td width='10%' class='multiRPTLabel'>Order Qty.");
				sb.append("</td><td width='15%' class='multiRPTLabel'>Transfer Qty.");
				sb.append("</td><td width='15%' class='multiRPTLabel'>Acknowledge Qty.");
				sb.append("</td></tr>");
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

						sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
						for (int j = 0; j < REC_PER_PAGE; j++) {
							/*
							 * 1- Order No 2- Order Date 3- Demand Store Name 4-
							 * Order Qty with Unit Name 5- Transfer Qty with
							 * Unit Name 6- Ack Qty with Unit Name 7-Hidden
							 * Value ReqNo||'^'||Transfer Store
							 * ID||'^'||Transfer Req No||'^'||Transfer Date
							 * ||'^'||Transfer remarks||'^'||Ack
							 * Date||'^'||ACK_REMARKS||'^'||STATUS||'^'||Demand
							 * Store ID
							 */
							if (ws.next()) {
								sb.append("<tr>");
								sb.append("<td width='5%' class='multiPOControl'>");
								sb.append("<input type='hidden' name='strHiddenValue' id='strHiddenValue"
										+ count
										+ "' value='"
										+ ws.getString(7)
										+ "'>");
								sb.append("<input type='radio' name='strDOrderNo' onclick='disOldDate(this,"
										+ count + ")' >");
								sb.append("</td><td width='15%' class='multiPOControl'>");
								sb.append(ws.getString(1));
								sb.append("</td><td width='20%' class='multiPOControl'>");
								sb.append(ws.getString(2));
								sb.append("</td><td width='20%' class='multiPOControl'>");
								sb.append(ws.getString(3));
								sb.append("</td><td width='10%' class='multiPOControl'>");
								sb.append(ws.getString(4));
								sb.append("</td><td width='15%' class='multiPOControl'>");
								sb.append(ws.getString(5));
								sb.append("</td><td width='15%' class='multiPOControl'>");
								sb.append(ws.getString(6));
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

						sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
						for (int k = 0; k < reminder; k++) {
							ws.next();
							sb.append("<tr>");
							sb.append("<td width='5%' class='multiPOControl'>");
							sb.append("<input type='hidden' name='strHiddenValue' id='strHiddenValue"
									+ count
									+ "' value='"
									+ ws.getString(7)
									+ "'>");
							sb.append("<input type='radio' name='strDOrderNo' onclick='disOldDate(this,"
									+ count + ")' >");
							sb.append("</td><td width='15%' class='multiPOControl'>");
							sb.append(ws.getString(1));
							sb.append("</td><td width='20%' class='multiPOControl'>");
							sb.append(ws.getString(2));
							sb.append("</td><td width='20%' class='multiPOControl'>");
							sb.append(ws.getString(3));
							sb.append("</td><td width='10%' class='multiPOControl'>");
							sb.append(ws.getString(4));
							sb.append("</td><td width='15%' class='multiPOControl'>");
							sb.append(ws.getString(5));
							sb.append("</td><td width='15%' class='multiPOControl'>");
							sb.append(ws.getString(6));
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
			} 
			else 
			{
				sb.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px' bgcolor='black'>");
				sb.append("<tr>");
				sb.append("<td width='5%' class='multiRPTLabel'>#");
				sb.append("</td><td width='15%' class='multiRPTLabel'>Order No");
				sb.append("</td><td width='20%' class='multiRPTLabel'>Order Date");
				sb.append("</td><td width='20%' class='multiRPTLabel'>Order To");
				sb.append("</td><td width='10%' class='multiRPTLabel'>Order Qty.");
				sb.append("</td><td width='15%' class='multiRPTLabel'>Transfer Qty.");
				sb.append("</td><td width='15%' class='multiRPTLabel'>Acknowledge Qty.");
				sb.append("</td></tr>");
				sb.append("</table>");
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black' cellpadding='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiControl'><font color='red'>No Record Found</font></td>");
				sb.append("</tr>");
				sb.append("</table>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			new HisException("Transfer Order Details",
					"TransferDemandReqTransHLP.getTransferOrderDetails()-->",
					e.getMessage());
		}
		// System.out.println("sb"+sb);
		return sb.toString();
	}

}
