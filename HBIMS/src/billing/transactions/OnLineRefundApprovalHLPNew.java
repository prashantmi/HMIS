package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class OnLineRefundApprovalHLPNew {

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

					
					br.append("<input type='hidden' name='TotalLayer'  value='" + totalPLayer + "'>");
					br.append("<input type='hidden' name='RecordShowOnPage'  value='" + REC_PER_PAGE + "'>");
					br.append("<div>");				
					
					br.append("<div style='overflow-x:auto;'>");
					br.append("<table class='table'>");
					br.append("<thead>");
					br.append("<tr><th>Request No");
					br.append("</th><th>Request date");
					br.append("</th><th>CR No");
					br.append("</th><th>Patient Name");
					br.append("</th><th>Hospital Service");
					br.append("</th></tr>");
					br.append("</thead>");
					br.append("<tbody>");

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
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(1));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(2));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(3));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(4));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(5));
								br.append("</td>");
								br.append("</tr>");

								//}

							}
							
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
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(1));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(2));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(3));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(4));
								br.append("</td>");
								br.append("<td class='"+strApplyClass+"'>");
								br.append(wb.getString(5));
								br.append("</td>");
								br.append("</tr>");

							}
							br.append("</div>");

						}
					}
					br.append("</tbody>");
					br.append("</table>");
					br.append("</div>");				
					br.append("<hr>");
					br.append("<div class='row rowFlex reFlex'>");
					br.append("<div class='float-rigt col-sm-12' align='right'>");
					br.append("<label><font color = 'red'>*Record Rejected By Auditors</font></label>");
					br.append("</div>");
					br.append("</div>");
					br.append("</div>");

				} else {

					

					br.append("<div id='showNewPriceBidDiv' style='display:block;'>");
					br.append("<div class='alert alert-danger alert-dismissible fade show'>No Refund Request Found!!!....</div>");
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

					br.append("<input type='hidden' name='TotalLayer'  value='" + totalPLayer + "'>");
					br.append("<input type='hidden' name='RecordShowOnPage'  value='" + REC_PER_PAGE + "'>");
					
					br.append("<div style='overflow-x:auto;'>");
					br.append("<table class='table'>");
					br.append("<thead>");
					br.append("<tr><th >#");
					br.append("<th>Request No.");
					br.append("</th><th>Request Date");
					br.append("</th><th>CR No.");
					br.append("</th><th>Patient Name");
					br.append("</th><th>Hospital Service");
					br.append("</th></tr>");
					br.append("</thead>");
					br.append("<tbody>");
					br.append("<input type='hidden' name='strTariffValue' id='strTariffValue' onclick='' value='" + 0
							+ "'>");

					for (int i = 1; i <= totalPLayer; i++) {
						if (i <= totalPLayer) {

							if (i == 1) {
								br.append("<div id='DivId2" + i
										+ "' style='display:block'>");
							} else {
								br.append("<div id='DivId2" + i
										+ "' style='display:none'>");
							}

							
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

									  br.append("<td><input type='radio' name='radioButton' id='radioButton"
													+ nTmpI
													+ "' value=\""
													+ wb.getString(6)
													+ "\"  onClick='setValue(this);'></td>");

						        	}
									else
									{
									  br.append("<td><input type='radio' name='radioButton' id='radioButton"
												+ nTmpI
												+ "' value=\""
												+ wb.getString(6)
												+ "\"  onClick='setValue(this);'></td>");
										
									}
									if(wb.getString(6).replace("^", "#").split("#")[9].equals("0"))
									{
											br
											.append("<td>");
									br.append(wb.getString(1));
									br.append("</td>");
	
									br
											.append("<td>");
									br.append(wb.getString(2));
									br.append("</td>");
	
									br
											.append("<td>");
									br.append(wb.getString(3));
									br.append("</td>");
	
									br
											.append("<td>");
									br.append(wb.getString(4));
									br.append("</td>");
	
									br
											.append("<td>");
									br.append(wb.getString(5));
									br.append("</td>");
									}
									else
									{
									br
											.append("<td>");
									br.append(wb.getString(1));
									br.append("</td>");

									br
											.append("<td>");
									br.append(wb.getString(2));
									br.append("</td>");

									br
											.append("<td>");
									br.append(wb.getString(3));
									br.append("</td>");

									br
											.append("<td>");
									br.append(wb.getString(4));
									br.append("</td>");

									br
											.append("<td>");
									br.append(wb.getString(5));
									br.append("</td>");
									}

									br.append("</tr>");



							}
							br.append("</div>");

						} else {

							if (i == 1) {
								br.append("<div id='DivId2" + i
										+ "' style='display:block'>");
							} else {
								br.append("<div id='DivId2" + i
										+ "' style='display:none'>");
							}

							
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
										.append("<td><input type='radio' name='radioButton' id='radioButton"
												+ nTmpI
												+ "' value=\""
												+ wb.getString(6)
												+ "\"  onClick='setValue(this);'></td>");
								if(wb.getString(6).replace("^", "#").split("#")[9].equals("0"))
								{
								br
										.append("<td>");
								br.append(wb.getString(1));
								br.append("</td>");

								br
										.append("<td>");
								br.append(wb.getString(2));
								br.append("</td>");

								br
										.append("<td>");
								br.append(wb.getString(3));
								br.append("</td>");

								br
										.append("<td>");
								br.append(wb.getString(4));
								br.append("</td>");

								br
										.append("<td>");
								br.append(wb.getString(5));
								br.append("</td>");
								}
								else
								{
										br
										.append("<td>");
								br.append(wb.getString(1));
								br.append("</td>");
	
								br
										.append("<td>");
								br.append(wb.getString(2));
								br.append("</td>");
	
								br
										.append("<td>");
								br.append(wb.getString(3));
								br.append("</td>");
	
								br
										.append("<td>");
								br.append(wb.getString(4));
								br.append("</td>");
	
								br
										.append("<td>");
								br.append(wb.getString(5));
								br.append("</td>");
								}

								br.append("</tr>");

							}
							br.append("</div>");

						}

					}
					br.append("</tbody>");
					br.append("</table>");
					br.append("</div>");

				} else {

					

					br.append("<div id='showNewPriceBidDiv1' style='display:block;'>");
					br.append("<div class='alert alert-danger alert-dismissible fade show'>No Refund Request Found!!!....</div>");
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
			br.append("<div class='row rowFlex reFlex rowpadding'>");
			br.append("<div class='col-sm-5' style='padding: 0;'>");
			br.append("<fieldset  style='height: 100%;'>");
			br.append("<legend class='legendHeader' id='nonPrintableLegend'>Patient Details </legend>");
			br.append("<input type='hidden' name='strReqNo' id='strReqNo' value='" + tmpVal[0] + "'>");
			br.append("<div class='row rowFlex reFlex'>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("Request No.");
			br.append("</div>");
			br.append("<div class='col-sm-3' style='padding-left: 0px;'>");
			br.append("" + tmpVal[0] + "");
			br.append("</div>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("Request Date");
			br.append("</div>");
			br.append("<div class='col-sm-3' style='padding-left: 0px;'>");
			br.append("" + tmpVal[1] + "");
			br.append("</div>");
			br.append("</div>");
			
			br.append("<div class='row rowFlex reFlex rowpadding'>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("CR No.");
			br.append("</div>");
			br.append("<div class='col-sm-3' style='padding-left: 0px;'>");
			br.append("" + tmpVal[2] + "<input name='strCrNoVal' type='hidden' value='"+ tmpVal[2] + "'>");
			br.append("</div>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("Patient Name");
			br.append("</div>");
			br.append("<div class='col-sm-3' style='padding-left: 0px;'>");
			br.append("" + tmpVal[3] + "");
			br.append("</div>");
			br.append("</div>");
			
			br.append("<div class='row rowFlex reFlex rowpadding'>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("Hospital Services");
			br.append("</div>");
			br.append("<div class='col-sm-9' style='padding-left: 0px;'>");
			br.append("" + tmpVal[4] + "");
			
			br.append("<input type='hidden' name='strTempRefundReason' value='"+ tmpVal[5] + "'>");	
			br.append("</div>");
			br.append("</div>");
			
			br.append("<div class='row rowFlex reFlex rowpadding'>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("Receipt No.");
			br.append("</div>");
			br.append("<div class='col-sm-3' style='padding-left: 0px;'>");
			br.append("" + tmpVal[6] + "");
			br.append("</div>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("Counter Name");
			br.append("</div>");
			br.append("<div class='col-sm-3' style='padding-left: 0px;'>");
			br.append("" + tmpVal[7] + "");	
			br.append("</div>");
			br.append("</div>");
			
			br.append("<div class='row rowFlex reFlex rowpadding'>");
			br.append("<div class='col-sm-3' align='right'>");
			br.append("User");
			br.append("</div>");
			br.append("<div class='col-sm-9' style='padding-left: 0px;'>");
			br.append("" + strUsrName + "");
			br.append("</div>");
			br.append("</div>");
			
			br.append("</fieldset>");
			br.append("</div>");
			

			if (wb != null) 
			{
				if (wb.size() != 0) 
				{
					br.append("<div class='col-sm-7' style='padding: 0;'>");
					br.append("<fieldset  style='height: 100%;'>");
					br.append("<legend class='legendHeader' id='nonPrintableLegend'> Tariff Details</legend>");
					br.append("<div id='tariffDivId' style='display: block'>");
					br.append("<div style='overflow-x:auto;'>");
					br.append("<table class='table'>");
					br.append("<thead>");
					br.append("<tr><th>#</th>");
					br.append("<th>Tariff Name");
					br.append("</th><th>Rate/Unit");
					br.append("</th><th>Request Qty");
					br.append("</th><th>Approved Qty");
					br.append("</th><th>Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)");
					br.append("</th></tr>");
					br.append("</thead>");
					br.append("<tbody>");

								for (int k = 0; wb.next(); k++) 
							{
								//br.append("<input type='hidden' name='strRequestQty' id='strRequestQty"+ k+ "' value='"+ wb.getString(3) + "'>");
								br.append("<input type='hidden' name='strHiddenVal' id='strHiddenVal"+ k+ "' value='"+ wb.getString(4) + "'>");
								br.append("<tr>");
								br.append("<td style=\"width: 1%;\"><input type='checkbox' name='tariffId' id='tariffId"+ k+ "' value='"+ 0 + "'  onClick=\"Check(this,'"+k+"');\" ></td>");
								br.append("<td style=\"width: 40%;\">");
								br.append(wb.getString(1));
								br.append("</td>");
								br.append("<td style=\"width: 7%;\">");
								br.append(wb.getString(2));
								br.append("</td>");						
								br.append("<td style=\"width: 12%;\"><input type='hidden' name='strRequestQty' value='"+wb.getString(3)+"' id='strRequestQty"+k+"' >");
								br.append(wb.getString(3));
								br.append("</td>");
								br.append("<td style=\"width: 13%;\">");
								br.append("<input name='strApprovedQty'  id='strApprovedQty"+ k+ "'  type='text' class='form-control smalltextbox' value='"+wb.getString(3)+"' maxlength='5' onKeyPress='return validateData(event,5);'   onKeyUp='Check(this,\""+k+"\");' onpaste='Check(this,\""+k+"\");' disabled>");	
								br.append("</td>");
								br.append("<td style=\"width: 7%;\">");
								br.append("<div id='costDivId"+ k+ "'>0.00</div>");
								br.append("<input type='hidden' name='strRefundCost' id='strRefundCost"+ k+ "' value='"+ 0.00 + "'>");
								br.append("</td>");
								br.append("</tr>");
							}
							br.append("</tbody>");
							br.append("</table>");
							br.append("</div>");
							br.append("<hr>");
							br.append("<div class='row rowFlex reFlex'>");
							br.append("<div class='float-right col-sm-2' align='right'>");
							br.append("<font color='red'>*Total Cost:</font>");
							br.append("</div>");
							br.append("<div class='float-right col-sm-10' align='right'>");
							br.append("<font color='red'><div id='totalExpAmtDivId' style='font-weight:bold'>0.00<input name='strTotalExpAmt' id='strTotalExpAmt' type='hidden' class='txtFldMin' value='"
											+ 0.00 + "' disabled='disabled'></div></font>");
							br.append("</div>");
							br.append("</div>");
							br.append("</div>");
							br.append("</fieldset>");
							br.append("</div>");
					}
					else
	                {
						
	
						br.append("<div class='col-sm-7' style='padding: 0;'>");
						br.append("<fieldset  style='height: 100%;'>");
						br.append("<legend class='legendHeader' id='nonPrintableLegend'> Tariff Details</legend>");
						br.append("<div id='tariffDivId' style='display: block'>");
						br.append("<div class='alert alert-danger alert-dismissible fade show'>No Tariff Details Found!!!....</div>");
						br.append("</div></div></fieldset></div>");
						
	
					}
			}
			else 
			{
				br.append("<div id='tariffDivId' style='display: block'>");
				br.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>");
				br.append("<td colspan='5'  CLASS='multiControl' ><DIV class='errMsg' align='center'>No Previous Validated Details !!! </div></TD>");
				br.append("</table>");
				br.append("</div>");

			}
			br.append("</div>");
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
