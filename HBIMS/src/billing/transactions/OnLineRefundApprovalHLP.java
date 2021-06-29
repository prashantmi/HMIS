package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class OnLineRefundApprovalHLP {

	public static String todayRefundApprovalDtls(OnLineRefundApprovalFB formBean) {

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		int start = 1;
		final int REC_PER_PAGE = 10;
		final int PAGE_PER_BLOCK = 10;
		String strApplyClass    = "";
	//	String nTmpI = "1";
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		try {
			wb = formBean.getTodayRefundApprovalDetails();
			/*
			 * 1. Request No 2. Request Date 3. CR No 4. Patient name 5.
			 * Hospital Service
			 * 
			 */

			if (wb != null) 
			{
				if (wb.size() != 0) 
				{
					int actualFetchRecord = wb.size();
                    
					if (totalFetchRecord != actualFetchRecord)
					{
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
					}
					int totalPLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;
					
					if (reminder > 0)
						totalPLayer = totalPLayer + 1;

					
					br.append("<input type='hidden' name='TotalLayer'  value='"
							+ totalPLayer + "'>");
					br
							.append("<input type='hidden' name='RecordShowOnPage'  value='"
									+ REC_PER_PAGE + "'>");

					br
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					br.append("<tr>");
					br.append("<td colspan='1' width='5%' class='TITLE' >");
					br
							.append("<div id='indentPlusID1' style='display: block;' align='center' >");
					br
							.append("<img src='../../hisglobal/images/plus.gif' onclick='showIndentData1();' style='cursor: pointer;' title='Show New Validated Details'></div>");
					br
							.append("<div id='indentMinusID1' style='display: none;' align='center'>");
					br
							.append("<img src='../../hisglobal/images/minus.gif' onclick='hideIndentData1();'	style='cursor: pointer;' title='Hide New Validated Details'></div>");
					br.append("</td>");

					br
							.append("<td  colspan='4' width='95%' class='TITLE'>Today Approved List</td>");

					br.append("</tr>");
					br.append("</table>");

					br
							.append("<div id='showNewPriceBidDiv' style='display:none;'>");

					br
							.append("<table class='TABLEWIDTH' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");

					br.append("<tr>");
					br.append("<td class='LABEL'>");

					for (int i = 1; i <= totalPLayer; i++)
					{
						br
								.append("<a name='pg' id='pg"
										+ i
										+ "'  STYLE='cursor:pointer;cursor:hand;' class='pg-normal' title='Record Indexing' onClick='GetIndex(\""
										+ i + "\",\"" + totalPLayer + "\")'>"
										+ (i + start - 1) + "</a>|&nbsp;");
					}
					br.append("</td></tr>");

					br.append("</table>");

					br.append("</table>");

					br
							.append("<table class='TABLEWIDTH' align='center'  bgcolor='#000000'  border='0' cellpadding='1px' cellspacing ='1px'>");
					br
							.append("<tr><td width='20%' class='multiLabel'>Request No");
					br
							.append("</td><td width='20%' class='multiLabel'>Request date");
					br.append("</td><td width='20%' class='multiLabel'>CR No");
					br
							.append("</td><td width='20%' class='multiLabel'>Patient Name");
					br
							.append("</td><td width='20%' class='multiLabel'>Hospital Service");
					br.append("</td></tr>");
					br.append("</table>");

					for (int i = 1; i <= totalPLayer; i++) 
					{
						
						if (i <= totalPLayer) 
						{
							

							if (i == 1)
							{
								br.append("<div id='DivId" + i	+ "' style='display:block'>");
							} 
							else
							{
								br.append("<div id='DivId" + i	+ "' style='display:none'>");
							}

							br
									.append("<table class='TABLEWIDTH' align='center' bgcolor='#CC9966'   border='0' cellpadding='1px' cellspacing ='1px'>");
							
							for (int j = 0; (j < REC_PER_PAGE) && wb.next() ; j++) 
							{
						//		nTmpI = i + "" + j;
								//if (wb.next())
								//{
								   
								   if(wb.getString(6).equals("1")) 
			  				       {
			  				    	 strApplyClass = "Approved";				    	   
			  				       }
			  				       else
			  				       {
			  				    	 strApplyClass = "NotApproved";    				    	   
			  				       }
								

									br.append("<tr>");

									br
											.append("<td width='20%' class='"+strApplyClass+"'>");
									br.append(wb.getString(1));
									br.append("</td>");

									br
											.append("<td width='20%' class='"+strApplyClass+"'>");
									br.append(wb.getString(2));
									br.append("</td>");

									br
											.append("<td width='20%' class='"+strApplyClass+"'>");
									br.append(wb.getString(3));
									br.append("</td>");

									br
											.append("<td width='20%' class='"+strApplyClass+"'>");
									br.append(wb.getString(4));
									br.append("</td>");

									br
											.append("<td width='20%' class='"+strApplyClass+"'>");
									br.append(wb.getString(5));
									br.append("</td>");

									br.append("</tr>");

								//}

							}
							br.append("</table>");
							br.append("</div>");

						} 
						else 
						{
                           
							if (i == 1) 
							{
								br.append("<div id='DivId" + i      +"' style='display:block'>");
							} 
							else 
							{
								br.append("<div id='DivId" + i		+ "' style='display:none'>");
							}

							br
									.append("<table class='TABLEWIDTH' align='center'  bgcolor='#CC9966'  border='0' cellpadding='1px' cellspacing ='1px'>");

							for (int k = 0; k < reminder; k++) 
							{
								wb.next();
						//		nTmpI = i + "" + k;
								
								   if(wb.getString(6).equals("1")) 
			  				       {
			  				    	 strApplyClass = "Approved";				    	   
			  				       }
			  				       else
			  				       {
			  				    	 strApplyClass = "NotApproved";    				    	   
			  				       }
								 
								br.append("<tr>");

								br
										.append("<td width='20%' class='"+strApplyClass+"'>");
								br.append(wb.getString(1));
								br.append("</td>");

								br
										.append("<td width='20%' class='"+strApplyClass+"'>");
								br.append(wb.getString(2));
								br.append("</td>");

								br
										.append("<td width='20%' class='"+strApplyClass+"'>");
								br.append(wb.getString(3));
								br.append("</td>");

								br
										.append("<td width='20%' class='"+strApplyClass+"'>");
								br.append(wb.getString(4));
								br.append("</td>");

								br
										.append("<td width='20%' class='"+strApplyClass+"'>");
								br.append(wb.getString(5));
								br.append("</td>");

								br.append("</tr>");

							}
							br.append("</table>");
							br.append("</div>");

						}
					}
					
					br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					br.append("<tr>");
					br.append("<td WIDTH='25%' align='left' CLASS='LABEL' colspan='5'><font color = 'red'><b>*Record Rejected By Auditors</b></font></td>");
					br.append("</tr>");
					br.append("</table>");
					br.append("</div>");

				} else {

					br
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					br.append("<tr>");
					br.append("<td colspan='1' width='5%' class='TITLE' >");
					br
							.append("<div id='indentPlusID1' style='display: none;' align='center' >");
					br
							.append("<img src='../../hisglobal/images/plus.gif' onclick='showIndentData1();' style='cursor: pointer;' title='Show New Validated Details'></div>");
					br
							.append("<div id='indentMinusID1' style='display: block;' align='center'>");
					br
							.append("<img src='../../hisglobal/images/minus.gif' onclick='hideIndentData1();'	style='cursor: pointer;' title='Hide New Validated Details'></div>");
					br.append("</td>");

					br
							.append("<td  colspan='4' width='95%' class='TITLE'>Today Approved List</td>");

					br.append("</tr>");
					br.append("</table>");

					br
							.append("<div id='showNewPriceBidDiv' style='display:block;'>");

					br
							.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br
							.append("<TD WIDTH='25%' align='center' CLASS='multiControl' colspan='5'><font color = 'red'>"
									+ "No Record Found!!!" + "</font></TD>");
					br.append("</TR>");
					br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
					br.append("</div>");

				}
			}

			else {
				br
						.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");

				br
						.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");

				br.append("</table>");

			}
		} catch (Exception _e) {
			_e.printStackTrace();
			formBean
					.setStrMsg("TechCompStatValidTransHLP.getIndentDetails() --> "
							+ _e.getMessage());

		}
		return br.toString();
	}

	public static String refundRequestDtls(OnLineRefundApprovalFB formBean) {

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		int start = 1;
		final int REC_PER_PAGE = 10;
		final int PAGE_PER_BLOCK = 10;
		String nTmpI = "1";
		int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
		int totalRecordsToManipulate = totalFetchRecord - 1;
		try {
			wb = formBean.getRefundRequestDetails();
			/*
			 * 1. Request No 2. Request Date 3. CR No 4. Patient name 5.
			 * Hospital Service
			 * 
			 */

			if (wb != null) {
				if (wb.size() != 0) {
					int actualFetchRecord = wb.size();

					if (totalFetchRecord != actualFetchRecord) {
						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
					}
					int totalPLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;
					if (reminder > 0)
						totalPLayer = totalPLayer + 1;

					br.append("<input type='hidden' name='TotalLayer'  value='"
							+ totalPLayer + "'>");
					br
							.append("<input type='hidden' name='RecordShowOnPage'  value='"
									+ REC_PER_PAGE + "'>");

					br
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					br.append("<tr>");
					br.append("<td colspan='1' width='5%' class='TITLE' >");
					br
							.append("<div id='indentPlusID11' style='display: none;' align='center' >");
					br
							.append("<img src='../../hisglobal/images/plus.gif' onclick='showIndentData11();' style='cursor: pointer;' title='Show New Validated Details'></div>");
					br
							.append("<div id='indentMinusID11' style='display: block;' align='center'>");
					br
							.append("<img src='../../hisglobal/images/minus.gif' onclick='hideIndentData11();'	style='cursor: pointer;' title='Hide New Validated Details'></div>");
					br.append("</td>");

					br
							.append("<td  colspan='3' width='85%' class='TITLE'>Refund Request</td>");
					br.append("<td  colspan='1' width='10%' class='TITLE'> <input type='button' name='refreshbutton' value='Refresh'  onclick='getRefundRequestDtls();' /> </td>");

					br.append("</tr>");
					br.append("</table>");

					br
							.append("<div id='showNewPriceBidDiv1' style='display:block;'>");

					br
							.append("<table class='TABLEWIDTH' align='center'  border='0' cellpadding='1px' cellspacing ='1px'>");

					br.append("<tr>");
					br.append("<td class='LABEL'>");

					for (int i = 1; i <= totalPLayer; i++) {
						br
								.append("<a name='mg' id='mg"
										+ i
										+ "'  STYLE='cursor:pointer;cursor:hand;' class='pg-normal' title='Record Indexing' onClick='GetIndex2(\""
										+ i + "\",\"" + totalPLayer + "\")'>"
										+ (i + start - 1) + "</a>|&nbsp;");
					}
					br.append("</td></tr>");

					br.append("</table>");

					br.append("</table>");

					br
							.append("<table class='TABLEWIDTH' align='center' bgcolor='#CC9966'  border='0' cellpadding='1px' cellspacing ='1px'>");

					br.append("<tr>");

					br.append("</tr>");
					br.append("</table>");

					br
							.append("<table class='TABLEWIDTH' align='center'  bgcolor='#000000'  border='0' cellpadding='1px' cellspacing ='1px'>");
					br.append("<tr><td width='5%' class='multiLabel'>#");
					br.append("<td width='10%' class='multiLabel'>Request No.");
					br
							.append("</td><td width='25%' class='multiLabel'>Request Date");
					br.append("</td><td width='15%' class='multiLabel'>CR No.");
					br
							.append("</td><td width='20%' class='multiLabel'>Patient Name");
					br
							.append("</td><td width='20%' class='multiLabel'>Hospital Service");
					br.append("</td></tr>");
					br.append("</table>");
					br
							.append("<input type='hidden' name='strTariffValue' id='strTariffValue' onclick='' value='"
									+ 0 + "'>");

					for (int i = 1; i <= totalPLayer; i++) {
						if (i <= totalPLayer) {

							if (i == 1) {
								br.append("<div id='DivId2" + i
										+ "' style='display:block'>");
							} else {
								br.append("<div id='DivId2" + i
										+ "' style='display:none'>");
							}

							br
									.append("<table class='TABLEWIDTH' align='center' bgcolor='#CC9966'   border='0' cellpadding='1px' cellspacing ='1px'>");
							
							for (int j = 0; (j < REC_PER_PAGE) && wb.next() ; j++) 
							{
								nTmpI = i + "" + j;
									br
											.append("<input type='hidden' name='strAllPrevRcdPk' id='strAllPrevRcdPk"
													+ nTmpI
													+ "' value='");
									br.append(wb.getString(6));
									br.append("'>");

									br.append("<tr>");
									
									if(j==0)
						        	{

									  br.append("<td width='5%' class='multiLabel'><input type='radio' name='radioButton' id='radioButton"
													+ nTmpI
													+ "' value=\""
													+ wb.getString(6)
													+ "\"  onClick='setValue(this);'></td>");

						        	}
									else
									{
									  br.append("<td width='5%' class='multiLabel'><input type='radio' name='radioButton' id='radioButton"
												+ nTmpI
												+ "' value=\""
												+ wb.getString(6)
												+ "\"  onClick='setValue(this);'></td>");
										
									}
									if(wb.getString(6).replace("^", "#").split("#")[9].equals("0"))
									{
											br
											.append("<td width='10%' class='multiControl'>");
									br.append(wb.getString(1));
									br.append("</td>");
	
									br
											.append("<td width='25%' class='multiControl'>");
									br.append(wb.getString(2));
									br.append("</td>");
	
									br
											.append("<td width='15%' class='multiControl'>");
									br.append(wb.getString(3));
									br.append("</td>");
	
									br
											.append("<td width='20%' class='multiControl'>");
									br.append(wb.getString(4));
									br.append("</td>");
	
									br
											.append("<td width='20%' class='multiControl'>");
									br.append(wb.getString(5));
									br.append("</td>");
									}
									else
									{
									br
											.append("<td width='10%' class='multiControlRed'>");
									br.append(wb.getString(1));
									br.append("</td>");

									br
											.append("<td width='25%' class='multiControlRed'>");
									br.append(wb.getString(2));
									br.append("</td>");

									br
											.append("<td width='15%' class='multiControlRed'>");
									br.append(wb.getString(3));
									br.append("</td>");

									br
											.append("<td width='20%' class='multiControlRed'>");
									br.append(wb.getString(4));
									br.append("</td>");

									br
											.append("<td width='20%' class='multiControlRed'>");
									br.append(wb.getString(5));
									br.append("</td>");
									}

									br.append("</tr>");



							}
							br.append("</table>");
							br.append("</div>");

						} else {

							if (i == 1) {
								br.append("<div id='DivId2" + i
										+ "' style='display:block'>");
							} else {
								br.append("<div id='DivId2" + i
										+ "' style='display:none'>");
							}

							br
									.append("<table class='TABLEWIDTH' align='center'  bgcolor='#CC9966'  border='0' cellpadding='1px' cellspacing ='1px'>");

							for (int k = 0; k < reminder; k++) {
								wb.next();
								nTmpI = i + "" + k;
								br
										.append("<input type='hidden' name='strAllPrevRcdPk' id='strAllPrevRcdPk"
												+ nTmpI
												+ "' title='To Compile Selected Request' onclick='' value='");
								br.append(wb.getString(6));
								br.append("'>");
								br.append("<tr>");

								br
										.append("<td width='5%' class='multiLabel'><input type='radio' name='radioButton' id='radioButton"
												+ nTmpI
												+ "' value=\""
												+ wb.getString(6)
												+ "\"  onClick='setValue(this);'></td>");
								if(wb.getString(6).replace("^", "#").split("#")[9].equals("0"))
								{
								br
										.append("<td width='20%' class='multiControl'>");
								br.append(wb.getString(1));
								br.append("</td>");

								br
										.append("<td width='15%' class='multiControl'>");
								br.append(wb.getString(2));
								br.append("</td>");

								br
										.append("<td width='20%' class='multiControl'>");
								br.append(wb.getString(3));
								br.append("</td>");

								br
										.append("<td width='20%' class='multiControl'>");
								br.append(wb.getString(4));
								br.append("</td>");

								br
										.append("<td width='20%' class='multiControl'>");
								br.append(wb.getString(5));
								br.append("</td>");
								}
								else
								{
										br
										.append("<td width='20%' class='multiControlRed'>");
								br.append(wb.getString(1));
								br.append("</td>");
	
								br
										.append("<td width='15%' class='multiControlRed'>");
								br.append(wb.getString(2));
								br.append("</td>");
	
								br
										.append("<td width='20%' class='multiControlRed'>");
								br.append(wb.getString(3));
								br.append("</td>");
	
								br
										.append("<td width='20%' class='multiControlRed'>");
								br.append(wb.getString(4));
								br.append("</td>");
	
								br
										.append("<td width='20%' class='multiControlRed'>");
								br.append(wb.getString(5));
								br.append("</td>");
								}

								br.append("</tr>");

							}
							br.append("</table>");
							br.append("</div>");

						}

					}
					br.append("</div>");

				} else {

					br
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					br.append("<tr>");
					br.append("<td colspan='1' width='5%' class='TITLE' >");
					br
							.append("<div id='indentPlusID11' style='display: none;' align='center' >");
					br
							.append("<img src='../../hisglobal/images/plus.gif' onclick='showIndentData11();' style='cursor: pointer;' title='Show New Validated Details'></div>");
					br
							.append("<div id='indentMinusID11' style='display: block;' align='center'>");
					br
							.append("<img src='../../hisglobal/images/minus.gif' onclick='hideIndentData11();'	style='cursor: pointer;' title='Hide New Validated Details'></div>");
					br.append("</td>");

					br
							.append("<td  colspan='4' width='95%' class='TITLE'>Refund Request</td>");

					br.append("</tr>");
					br.append("</table>");

					br
							.append("<div id='showNewPriceBidDiv1' style='display:block;'>");

					br
							.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
					br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("<TR>");
					br
							.append("<TD WIDTH='25%' align='center' CLASS='multiControl' colspan='5'><font color = 'red'>"
									+ "No Refund Request Found!!!" + "</font></TD>");
					br.append("</TR>");
					br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
					br.append("</table>");
					br.append("</div>");

				}
			}

			else {
				br
						.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");

				br
						.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");

				br.append("</table>");

			}
		} catch (Exception _e) {
			_e.printStackTrace();
			formBean
					.setStrMsg("TechCompStatValidTransHLP.getIndentDetails() --> "
							+ _e.getMessage());

		}
		return br.toString();
	}

	public static String tarrifDtls(OnLineRefundApprovalFB formBean,
			String concatValue) {

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		String[] tmpVal;
		String strUsrName = "";
		try {
			wb = formBean.getRefundTariffDetails();
			/*
			 * 1. Request No 2. Request Date 3. CR No 4. Patient name 5.
			 * Hospital Service
			 * 
			 */

			
			tmpVal = concatValue.replace('^', '#').split("#");
			
			if(tmpVal[8].length() > 0){
				
				strUsrName = tmpVal[8];
				
			}else{
				strUsrName = "/";
			}
			
			br.append("<input type='hidden' name='strReqNo' id='strReqNo' value='"+  tmpVal[0] + "'>");
			
			br
					.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
			
			br.append("<tr>");
			br.append("<td width='25%' class='LABEL'>Request No.</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[0]
					+ "</td>");
			br
					.append("<td width='25%' class='LABEL'>Request Date</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[1]
					+ "</td>");
			br.append("</tr>");
			br.append("<tr>");
			br.append("<td width='25%' class='LABEL'>CR NO.</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[2]
					+ "<input name='strCrNoVal' type='hidden' value='"+tmpVal[2]+"'></td>");
			br
					.append("<td width='25%' class='LABEL'>Patient Name</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[3]
					+ "</td>");
			br.append("</tr>");
			br.append("<tr>");
			br
					.append("<td width='25%' class='LABEL'>Hospital Services</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[4]
					+ "</td>");
			br
			.append("<td width='25%' class='LABEL'>Refund Reason</td>");
	br.append("<td width='25%' class='CONTROL'><input type='hidden' name='strTempRefundReason' value='"+tmpVal[5]+"'>" + tmpVal[5]
			+ "</td>");
			br.append("</tr>");
			
			br.append("<tr>");
			br.append("<td width='25%' class='LABEL'>Receipt No.</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[6]
					+ "</td>");
			br
					.append("<td width='25%' class='LABEL'>Counter Name</td>");
			br.append("<td width='25%' class='CONTROL'>" + tmpVal[7]
					+ "</td>");
			br.append("</tr>");
			 
			br.append("<tr>");
			br.append("<td width='25%' class='LABEL'>User</td>");
			br.append("<td width='75%'colspan='3' class='CONTROL'>" + strUsrName
					+ "</td>");
			 
			br.append("</tr>");
			 
			
			br.append("</table>");

			
			if (wb != null) 
			{
				if (wb.size() != 0) 
				{
					
					br.append("<div id='tariffDivId' style='display: block'>");
									
					br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					br.append("<tr>");
					br.append("<td  colspan='4' width='100%' class='TITLE'>Tariff Details</td>");
					br.append("</tr>");
					br.append("</table>");

					
					br.append("<table class='TABLEWIDTH' align='center'  bgcolor='#000000'  border='0' cellpadding='1px' cellspacing ='1px'>");
					br.append("<tr><td width='5%' class='multiLabel'>#");
					br.append("<td width='25%' class='multiLabel'>Tariff Name");
					br.append("</td><td width='15%' class='multiLabel'>Rate/Unit");
					br.append("</td><td width='20%' class='multiLabel'>Request Qty");
					br.append("</td><td width='20%' class='multiLabel'>Approved Qty");
					br.append("</td><td width='20%' class='multiLabel'>Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)");
					br.append("</td></tr>");
					br.append("</table>");

					
							

							br.append("<table class='TABLEWIDTH' align='center'  bgcolor='#CC9966'  border='0' cellpadding='1px' cellspacing ='1px'>");

							for (int k = 0; wb.next(); k++) 
							{
								//br.append("<input type='hidden' name='strRequestQty' id='strRequestQty"+ k+ "' value='"+ wb.getString(3) + "'>");
								br.append("<input type='hidden' name='strHiddenVal' id='strHiddenVal"+ k+ "' value='"+ wb.getString(4) + "'>");

								br.append("<tr>");

								br.append("<td width='5%' class='multiLabel'><input type='checkbox' name='tariffId' id='tariffId"+ k+ "' value='"+ 0 + "'  onClick='Check(this,\""+k+"\");'' ></td>");

								br.append("<td width='25%' class='multiControl'>");
								br.append(wb.getString(1));
								br.append("</td>");

								br.append("<td width='15%' class='multiControl'>");
								br.append(wb.getString(2));
								br.append("</td>");
								
								br.append("<td width='20%' class='multiControl'><input type='hidden' name='strRequestQty' value='"+wb.getString(3)+"' id='strRequestQty"+k+"' >");
								br.append(wb.getString(3));
								br.append("</td>");

								br.append("<td width='20%' class='multiControl'>");
								br.append("<input name='strApprovedQty'  id='strApprovedQty"+ k+ "'  type='text' class='txtFldMin' value='"+wb.getString(3)+"' maxlength='5' onKeyPress='return validateData(event,5);'   onKeyUp='Check(this,\""+k+"\");' onpaste='Check(this,\""+k+"\");' disabled>");	
								br.append("</td>");

								br.append("<td width='20%' class='multiControl'>");
								br.append("<div id='costDivId"+ k+ "'>0.00</div>");
								br.append("<input type='hidden' name='strRefundCost' id='strRefundCost"+ k+ "' value='"+ 0.00 + "'>");
								br.append("</td>");

								br.append("</tr>");
							}
						br.append("</table>");
						br.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing ='1px'>");
						br.append("<tr><td width='80%' class='LABEL'><font color='red'>*</font>Total Cost:</td>");
						br.append("<td width='20%' align='center' class='multiControl'>");
						br.append("<div id='totalExpAmtDivId' style='font-weight:bold'>0.00<input name='strTotalExpAmt' id='strTotalExpAmt' type='hidden' class='txtFldMin' value='"
										+ 0.00 + "' disabled='disabled'>&nbsp;<img src='/HBIMS/hisglobal/images/INR.png'></div>");
						br.append("</td></tr></table>");
						br.append("</div>");
					}
					else
	                {
						br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
						br.append("<tr>");
						br.append("<td colspan='1' width='5%' class='TITLE' >");
						br.append("<div id='indentPlusID12' style='display: none;' align='center' >");
						br.append("<img src='../../hisglobal/images/plus.gif' onclick='showIndentData12();' style='cursor: pointer;' title='Show New Validated Details'></div>");
						br.append("<div id='indentMinusID12' style='display: block;' align='center'>");
						br.append("<img src='../../hisglobal/images/minus.gif' onclick='hideIndentData12();'	style='cursor: pointer;' title='Hide New Validated Details'></div>");
						br.append("</td>");
						br.append("<td  colspan='4' width='95%' class='TITLE'>Tariff Details</td>");
						br.append("</tr>");
						br.append("</table>");
	
						br.append("<div id='showNewPriceBidDiv12' style='display:block;'>");
	
						br.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
						br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
						br.append("<TR>");
						br
								.append("<TD WIDTH='25%' align='center' CLASS='multiControl' colspan='5'><font color = 'red'>"
										+ "No Tariff Details Found for Selected Request No!!!" + "</font></TD>");
						br.append("</TR>");
						br.append("<tr><td colspan='5' bgcolor='black'></td></tr>");
						br.append("</table>");
						br.append("</div>");
	
					}
			}
			else 
			{
				br.append("<div id='tariffDivId' style='display: block'>");
				br
						.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");

				br
						.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");

				br.append("</table>");
				br.append("</div>");

			}
		} 
		catch (Exception _e) 
		{
			_e.printStackTrace();
			formBean
					.setStrMsg("TechCompStatValidTransHLP.getIndentDetails() --> "+ _e.getMessage());

		}
		
		return br.toString();
	}

}
