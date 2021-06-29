package billing.transactions;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

/**
 * @author Anshul Jindal
 * 
 */
public class BillingCancellationTransHLPNew {

	String popUpData = "";

	static int i = 0;

	/**
	 * This method is used to show a block of BILL DETAILS on jsp page by using
	 * the WebRowSet of billdetails
	 * 
	 * @param vo
	 * @return
	 */
	public static String billDetails(BillingCancellationTransVO vo) 
	{
		WebRowSet wb = null;
		int i = 0;
		StringBuffer br = null;
		String temp[] = new String[9];// to store values of hidden field

		try {
			wb = vo.getStrBillDtlWs();

			br = new StringBuffer();
			br.append("<div style='overflow-x:auto;'>");
			br.append("<table class='table'>");
			br.append("<thead> ");
			/*br.append("<tr> ");
			br.append("<td>Receipt Details </td>");

			br.append("</tr>");*/
			br.append("<tr>");
			br.append(" <input type='hidden' name='noOfRows' id='noOfRows' value='"+ wb.size() + "' >");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>#</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Receipt No.</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Receipt Date</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Hospital Service</font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Receipt Type </font></th>");
			br.append("<th><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>Receipt Amount(<img src='../../hisglobal/images/INR.png'>)</font></th>");
			br.append("</tr>");
			br.append("</thead> ");
			br.append("<tbody> ");
			if(wb.size()==0)
			{
				
				br.append("<div class='errMsg' align='center'> NO RECORD FOUND FOR ENTERED DATA / DAY END PERFORMED </div>");

				
			}
			while (wb.next())

			{
				temp = wb.getString(6).replace('^', '#').split("#");

				br.append("<tr>");

				br.append("<td ><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ "<input type='checkbox' name='chk' id='chk" + i
						+ "' " + "value='" + wb.getString(6) + "' ></font></td>");

				br
						.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'> <a id='viewBill' class='btn btn-info viewbill'  onClick='showPopUp(this,\""
								+ i + "\");'>" + wb.getString(1) + "</a>");

				br.append(" <input type='hidden' name='billNo' id='billNo" + i
						+ "' value='" + temp[0] + "' >"); // trans no=bill no.
				br.append(" <input type='hidden' name='patAccNo' id='patAccNo"
						+ i + "' value='" + temp[4] + "' >"); // patient account
				// no.
				br.append(" <input type='hidden' name='flag' id='flag" + i
						+ "' value=" + "0" + " >");

				br.append("</font></TD><TD ><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(2) + "</font></TD>");

				br.append("<TD ><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(3) + "</font></TD>");

				br.append("<TD><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'>"
						+ wb.getString(4) + "</font></TD>");

				br.append("<TD ><font color='#000000' style='font-size: 14px;' size='2' face='Helvetica Neue, Helvetica, Arial, sans-serif'><a class='btn btn-info viewbill'>"
						+ wb.getString(5) + "<a></font></TD>");

				br.append("</TR>");
				i++;
			}
			br.append("</tbody>");
			
			br.append("</table></div>");

		} catch (Exception e) {
			vo.setStrMsgString("BillingCancellationTransHLP.billDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (wb != null)
				wb = null;
		}
		return br.toString();

	}
    
	public static String CrbillDetails(BillingCancellationTransVO vo) 
	{
		WebRowSet wb = null;
		int i = 0;
		StringBuffer br = null;
		String temp[] = new String[9];// to store values of hidden field

		try {
			wb = vo.getStrBillDtlWs();

			br = new StringBuffer();

			br.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

			br.append("<tr class='HEADER'> ");
			br.append("<td colspan='6'>Receipt Details </td>");

			br.append("</tr>");
			br.append("<tr>");
			br
					.append(" <input type='hidden' name='noOfRows' id='noOfRows' value='"
							+ wb.size() + "' >");

			br.append("<td class='multiLabel' WIDTH='2%' align='CENTER'></td>");

			br
					.append("<td class='multiLabel' WIDTH='12%' align='CENTER'>Receipt No.</td>");

			br
					.append("<td class='multiLabel' WIDTH='12%' align='CENTER'>Receipt Date</td>");

			br
					.append("<td class='multiLabel' WIDTH='12%' align='CENTER'>Hospital Service</td>");

			br
					.append("<td class='multiLabel' WIDTH='12%' align='CENTER'>Receipt Type </td>");

			br
					.append("<td class='multiLabel' WIDTH='12%' align='CENTER'>Receipt Amount(<img src='../../hisglobal/images/INR.png'>)</td>");
			br.append("</tr>");

			if(wb.size()==0)
			{
				br.append("<tr>");
				br.append("<td colspan='6' class='multiControl' >"
						+ "<div class='errMsg' align='center'> NO RECORD FOUND FOR ENTERED DATA / DAY END PERFORMED </div>" + "</td>");

				br.append("</tr>");
			}
			while (wb.next())

			{
				temp = wb.getString(6).replace('^', '#').split("#");

				br.append("<tr>");

				br.append("<td WIDTH='2%' class='multiLabel' >"
						+ "<input type='radio' name='crchk' id='crchk" + i
						+ "' " + "value='" + wb.getString(6) + "' onClick='groupCheck(this);'></td>");

				br
						.append("<TD  WIDTH='12%' CLASS='multiControl' > <a STYLE='cursor:pointer;cursor:hand;color:blue'  onClick='showPopUp(this,\""
								+ i + "\");'>" + wb.getString(1) + "</a>");

				br.append(" <input type='hidden' name='billNo' id='billNo" + i
						+ "' value='" + temp[0] + "' >"); // trans no=bill no.
				br.append(" <input type='hidden' name='patAccNo' id='patAccNo"
						+ i + "' value='" + temp[4] + "' >"); // patient account
				// no.
				br.append(" <input type='hidden' name='flag' id='flag" + i
						+ "' value=" + "0" + " >");

				br.append("</TD><TD WIDTH='12%' CLASS='multiControl'>"
						+ wb.getString(2) + "</TD>");

				br.append("<TD WIDTH='13%' CLASS='multiControl' >"
						+ wb.getString(3) + "</TD>");

				br.append("<TD WIDTH='12%' CLASS='multiControl' >"
						+ wb.getString(4) + "</TD>");

				br.append("<TD WIDTH='12%' CLASS='multiControl' >"
						+ wb.getString(5) + "</TD>");

				br.append("</TR>");
				i++;
			}

			br.append("</table>");

		} catch (Exception e) {
			vo.setStrMsgString("BillingCancellationTransHLP.CrbillDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		} finally {
			if (wb != null)
				wb = null;
		}
		return br.toString();

	}
	/**
	 * This method is used to show a PopUp (ON CLICK OF A BILL NO.)of Bill
	 * SERVICE DETAILS by using the WebRowSet of Bill SERVICE DETAILS
	 * 
	 * @param vo
	 * @param index
	 * @return
	 */
	public static String getPopUpInfo1(BillingCancellationTransVO vo,
			String index) {

		WebRowSet wb = null;
		//String temp[] = new String[20];
		StringBuffer br = null;
		StringBuffer br1 = null;
		String grpName = "";
		String trfName = "";
		String rateUnit = "";
		String billQty = "";
		String netAmt = "";

		try {

			wb = vo.getStrPopUpWs();

			br = new StringBuffer();
			br1 = new StringBuffer();

			br.append("<table width='500' cellpadding='1px' cellspacing='1px'>");

			br
					.append("<tr class='HEADER' align='left'><td>&nbsp;Tariff Details</td> ");
			br
					.append("<td align='right'><img src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"popup\");'> </td></tr>");

			br.append("</tr>");
			br.append("</table> ");

			br.append("<table width='500' align='left'>");

			br.append("<tr>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Group Name</td>");

			br
					.append("<td class='multiLabel' WIDTH='30%' align='CENTER'>Tariff Name</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Rate/Unit</td>");

			br
					.append("<td class='multiLabel' WIDTH='10%' align='CENTER'> Qty</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Net Amt(<img src='../../hisglobal/images/INR.png'>)</td>");

			br.append("</tr>");

			if(wb != null && wb.size()==0)
			{
				br.append("<TR>");
				br.append("<TD colspan='5'  CLASS='multiControl' >"
						+ "<DIV class='errMsg' align='center'> NO RECORD FOUND</div> " + "</TD>");

				br.append("</TR>");
			}
			
			
			if(wb != null){
				wb.beforeFirst();
				
			while (wb.next())

			{
				// if(vo.getStrPatAccNo().equals("0")){
				grpName = wb.getString(4);
				trfName = wb.getString(1);
				rateUnit = wb.getString(3);
				billQty = wb.getString(2);
				//temp = wb.getString(8).replace('^', '#').split("#");
				//netAmt = temp[15];
				
				if(vo.getStrReceiptType().equals("6")){
					
					netAmt = wb.getString(5);
					
				}else{
					
					netAmt = wb.getString(8);
					
				}
				
				

				br.append("<TR>");

				br.append("<TD  WIDTH='20%' CLASS='multiControl'>" + grpName
						+ "</TD>");

				br.append("<TD WIDTH='30%' CLASS='multiControl'>" + trfName
						+ "</TD>");

				br.append("<TD WIDTH='20%' CLASS='multiControl' >" + rateUnit
						+ "</TD>");

				br.append("<TD WIDTH='10%' CLASS='multiControl' >" + billQty
						+ "</TD>");

				br.append("<TD WIDTH='20%' CLASS='multiControl' >" + netAmt
						+ "</TD>");

				br.append("</TR>");

				br1.append(grpName + "^" + trfName + "^" + rateUnit + "^"
						+ billQty + "^" + netAmt + "^");

				i++;
			}

			br.append("</table>");
			br.append("@");
			br.append(br1);
			br.append("@");
			br.append(index);

			}
			
		} catch (Exception e) {
			vo
					.setStrMsgString("BillingCancellationTransHLP.getPopUpInfo() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		}
		return br.toString();

	}

	/**
	 * This method is used to show a PopUp (ON CLICK OF A BILL NO. AGAIN)of Bill
	 * SERVICE DETAILS by using the WebRowSet
	 * 
	 * @param vo
	 * @return
	 */
	public static String getPopUpData(BillingCancellationTransVO vo) {

		StringBuffer br = null;
		String data = "";
		String temp[] = new String[20];

		try {

			data = vo.getStrPopUpData();

			temp = data.replace('^', '#').split("#");
			// System.out.println("temp length-->" + temp.length);

			br = new StringBuffer();

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
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Group Name</td>");

			br
					.append("<td class='multiLabel' WIDTH='30%' align='CENTER'>Tariff Name</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Rate/Unit</td>");

			br
					.append("<td class='multiLabel' WIDTH='10%' align='CENTER'> Qty</td>");

			br
					.append("<td class='multiLabel' WIDTH='20%' align='CENTER'>Net Amount(<img src='../../hisglobal/images/INR.png'>)</td>");

			br.append("</tr>");
			if (temp.length > 1) {
				for (int j = 0; j <= (temp.length / 2); j = j + 5) {

					br.append("<TR>");

					br.append("<TD  WIDTH='20%' CLASS='multiControl'>"
							+ temp[j + 0] + "</TD>");

					br.append("<TD WIDTH='30%' CLASS='multiControl'>"
							+ temp[j + 1] + "</TD>");

					br.append("<TD WIDTH='20%' CLASS='multiControl' >"
							+ temp[j + 2] + "</TD>");

					br.append("<TD WIDTH='5%' CLASS='multiControl' >"
							+ temp[j + 3] + "</TD>");

					br.append("<TD WIDTH='10%' CLASS='multiControl' >"
							+ temp[j + 4] + "</TD>");

					br.append("</TR>");
				}
			}

			br.append("</table>");

		} catch (Exception e) {
			vo
					.setStrMsgString("BillingCancellationTransHLP.getPopUpData() --> "
							+ e.getMessage());
			vo.setStrMsgType("1");

		}

		return br.toString();

	}
	public static String getTariffDetails(BillingCancellationTransVO vo)
	 {
	    StringBuffer sBuffer = new StringBuffer("");
	    WebRowSet wb = null;
	    BillingCancellationTransBO bo = null;
		try{
			//System.out.println("hlp getTariffDetails");
			  bo = new  BillingCancellationTransBO();
			 String app_id="";
			 String rate1 = "0";
   		 String rateBaseVal = "0";
   		 String disUnit = "0";
   		 String disType = "0";
   		 String servTax = "0";
			 String remQty = "0";
   		 
			 wb = vo.getStrTrfDtls();
			 
			
			// int size=wb.size();
			// System.out.println("in hlp getTariffDetails vo.trf dtls size-"+size);
			String unitCmb="";
			          sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");       
	 	              sBuffer.append("<tr><td colspan='5' class='TITLE'>Tariff Details</td></tr>");
	                  sBuffer.append("<tr><td width='10%' class='multiLabel'><div align='center'></div></td>");
                     sBuffer.append("<td width='25%' class='multiLabel'><div align='center'>Tariff Name</div></td>");
                     //new field
                     sBuffer.append("<td width='35%' class='multiLabel'><div align='center'>Credit Letter No/Date/Amount</div></td>");
                     sBuffer.append("<td width='15%' class='multiLabel'><div align='center'>Unprocessed Bill Qty</div></td>");
                     
	  	              //sBuffer.append("<td width='17%' class='multiLabel'><div align='center'>Refund Type</div></td>");
	  	              //sBuffer.append("<td width='5%' class='multiLabel'><div align='center'>Penalty (%)</div></td>");
	                  //sBuffer.append(" <td width='5%' class='multiLabel'><div align='center'>Refund Qty</div></td>");
	                  //sBuffer.append(" <td width='10%' class='multiLabel'><div align='center'>Unit</div></td>");
	                  sBuffer.append(" <td width='15%' class='multiLabel'><div align='center'>Tariff Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</div></td>");
	                  
                     /*
                     sBuffer.append("<td width='15%' class='multiLabel'><div align='center'>Unprocessed Qty</div></td>");
	  	              sBuffer.append("<td width='22%' class='multiLabel'><div align='center'>Refund Type</div></td>");
	  	              sBuffer.append("<td width='5%' class='multiLabel'><div align='center'>Penalty (%)</div></td>");
	                  sBuffer.append(" <td width='5%' class='multiLabel'><div align='center'>Refund Qty</div></td>");
	                  sBuffer.append(" <td width='15%' class='multiLabel'><div align='center'>Unit</div></td>");
	                  sBuffer.append(" <td width='15%' class='multiLabel'><div align='center'>Refund Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</div></td>");
*/
	                  
	                  sBuffer.append("</tr></table>");
	                 if(wb!=null && wb.size()>0)
	     			 {
		                  sBuffer.append("<input type='hidden' name='noOfRows' id='noOfRows' value='"+wb.size()+"' >");
		                  for(int i=0;wb.next();i++)
		                  {
		                	  	        	    	
		                	     app_id        = wb.getString(8); //hidden value
		                	    
		                	     String temp[] = app_id.replace('^','#').split("#");
		                	     
		                	     
		                	     //vo.setStrUnit(temp[2]);
		                	     /*if(temp[2]== null||temp[2].equals("")||temp[2].equals("0"))
		                	     {               
		                	    	 vo.setStrUnit("0");
		                	     }
		                	     else
		                	     {
		                	    	 vo.setStrUnit(temp[2]);
		                	     }	*/
		                	     //unit data for unit combo
		                	     //bo.getUnitCombo(vo);
		                	     //unitCmb =  vo.getStrUnitCmbVal();
		                	     //System.out.println("vo.getStrUnit()"+vo.getStrUnit());
		                	    // unitCmb =BillConfigUtil.getDefaultUnitComboWithBaseValue(vo.getStrUnit()+"^1");
		                	     
		                	     String is_pack = temp[14];
		           
		                	     String qty[] = wb.getString(2).split(" "); //remaine qty unit name
		                	     Double netAmt=Double.parseDouble((wb.getString(3).split("/")[0]))*Double.parseDouble((qty[0]));
		                	     String TariffName=	wb.getString(1);
					    		 
					    		 sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
					    		 sBuffer.append("<input type='hidden' name='lnkVal' value='"+app_id+"' >");
					    		 sBuffer.append("<input type='hidden' name='isPack' value='"+is_pack+"' >");
					    		 //sBuffer.append("<input type='hidden' name='penalty' id='penalty"+i+"'  value='"+vo.getStrPenalty()+"' >");
					    		 
	//				    		 String rate1 = temp[3];
	//				    		 String rateBaseVal = temp[13];
	//				    		 String disUnit = temp[8];
	//				    		 String disType = temp[9];
	//				    		 String servTax = temp[7];
					    		 
					    		 
					    		 if(temp[3]== null||temp[3].equals(""))
		                	     {               
					    			  rate1 = "0";
		                	     }
		                	     else
		                	     {
		                	    	  rate1 = temp[3];
		                	     }
					    		// String rate1 = temp[3];
					    		//  rateBaseVal = temp[13];
					    		 if(temp[13]== null||temp[13].equals("0")||temp[13].equals(""))
		                	     {               
					    			 rateBaseVal = "0";
		                	     }
		                	     else
		                	     {
		                	    	 rateBaseVal = temp[13];
		                	     }
					    		 
					    		 if(temp[8]== null||temp[8].equals("0"))
		                	     {               
					    			 disUnit = "0";
		                	     }
		                	     else
		                	     {
		                	    	 disUnit = temp[8];
		                	     }
					    		 if(temp[9]== null||temp[9].equals("0")||temp[9].trim().length() == 0)
		                	     {               
					    			 disType = "0";
		                	     }
		                	     else
		                	     {
		                	    	 disType = temp[9];
		                	     }
					    		 if(temp[7]== null||temp[7].equals("0")||temp[7].equals(""))
		                	     {               
					    			 servTax = "0";
		                	     }
		                	     else
		                	     {
		                	    	 servTax = temp[7];
		                	     }
					    		 
					    		 //remained qty
					    		 if(temp[1]== null || temp[1].equals("")) {
					    			 remQty = "0";
					    		 }
					    		 else {
					    			 remQty = temp[1];
					    		 }
					    		 
					    		 sBuffer.append("<input type='hidden' name='chkHidd' id='chkHidd"+i+"' value='0'>");	
					    		 sBuffer.append("<tr><td width='10%' class='multiLabel'><input type='checkbox' name='cehk' id='cehk"+i+"' value=\""+app_id+"\" onClick=\"calTotalRefundAmt(this);\"></td>"); 
					    	   			    		  
								 sBuffer.append("<td width='25%' class='multiControl'>");
								 sBuffer.append("<a name='trfName' value='"+app_id+"' STYLE='CURSOR:POINTER; color:blue'>"+TariffName+"</a>");
								 sBuffer.append("</td>");
								 
								 //it has to be given name later on
								 //credit details
								 sBuffer.append("<input type='hidden' name='clNo' id='clNo"+i+"' value="+wb.getString(9)+">");
								 sBuffer.append("<input type='hidden' name='clDate' id='clDate"+i+"' value="+wb.getString(10)+">");
								 sBuffer.append("<input type='hidden' name='clPath' id='clPath"+i+"' value="+wb.getString(13)+">");
								 sBuffer.append("<input type='hidden' name='clientNo' id='clientNo"+i+"' value="+wb.getString(14)+">");
								 sBuffer.append("<input type='hidden' name='amtByPat' id='amtByPat"+i+"' value="+wb.getString(12)+">");
								 sBuffer.append("<input type='hidden' name='amtByClient' id='amtByClient"+i+"' value="+wb.getString(11)+">");
								 sBuffer.append("<input type='hidden' name='rate' id='rate"+i+"' value="+wb.getString(3).split("/")[0]+">");
								 
								 sBuffer.append("<td width='35%' class='multiControl' >"+wb.getString(9)+"/"+wb.getString(10)+"/"+wb.getString(11)+"</td>");
								 
								 sBuffer.append("<td width='15%' class='multiControl' name='unProcessedQty'>"+wb.getString(2)+"</td>");
								
								// System.out.println("rateBaseVal--"+rateBaseVal);
								 
								 /*sBuffer.append("<td width='17%' class='multiControl'>");
								 sBuffer.append("<select name='refundType' id='refundType"+i+"' onChange=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\"><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select>");  
								 sBuffer.append("</td>");
								 
								 sBuffer.append("<td width='5%' class='multiControl' >");
								 sBuffer.append("<input type='textbox' class='txtFldMin' name='trf_penalty' id='trf_penalty"+i+"' value='0' readonly>");	 
								 sBuffer.append("</td>");
								 
								 sBuffer.append("<td width='5%' class='multiControl'>");
								 sBuffer.append("<input type='textbox' class='txtFldMin' name='refundQty' id='qty"+i+"' value='0'  maxlength='3' onkeypress='return validateData(event,5);'  onKeyUp=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\" readonly>");	 
								 sBuffer.append("<input type='hidden'  name='HidRefundQty' id='HidRefundQty"+i+"' value='"+qty[0]+"'>");
								 
								 sBuffer.append("</td>");*/
								 
								/* sBuffer.append("<td width='10%' class='multiControl'><div name='unit' id='unitcmb'"+i+">");
								 if(unitCmb==null)
									 sBuffer.append("<select name='strUnitId' id='unit"+i+"' class='comboMin'  onChange=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\" ><option value='0'>Select Value</optoin></select> ");
								 else
									 sBuffer.append("<select name='strUnitId' id='unit"+i+"' class='comboMin'  onChange=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\">"+unitCmb+"</select>");
								 
								 sBuffer.append("</div></td>");*/
								 
								 sBuffer.append("<td width='15%' class='multiControl' style='font-weight:bold'>");
								 sBuffer.append("<div id='refCostDiv"+i+"' style='display:block'>");
								 sBuffer.append("<input type='hidden' class='txtFldMin' name='refundAmt' value='"+netAmt+"' id = 'refundAmt"+i+"'  readonly>"
								 		+ "<input type='hidden' name='strService' value='"+vo.getStrService()+"'></div>");
								 sBuffer.append("<div id='refCost"+i+"'>"+netAmt+"</div></td>");
								 sBuffer.append("</table>"); 
		                  }  
		                  sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");    
		                  sBuffer.append("<tr><td  colspan='6' width='86%' class='LABEL' ><font color='red'>Net Cancelled Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</font></td>");
		                  sBuffer.append("<td width='13%' class='multiControl' style='color:red;font-weight:bold'>");
						  sBuffer.append("<input type='hidden' class='txtFldNormal' name='trf_grossRefund' id='trf_grossRefund' value='0.00'  readonly><div id='netRefCost'>0.00</div></td>");
						  sBuffer.append("<td  class='multiControl'>");
						  sBuffer.append("</td></tr>");
		                  sBuffer.append("</table>");
	     			 }
	                 else
	                 {
	                	 sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");       
		 	             sBuffer.append("<tr><td colspan='9'><div align='center'><font color='red'><b>No Tariff Details Found/Credit Bill Can Not Be Cancelled</b></font></div></td></tr>"); 
	                 }
				 
	     }
		 catch(Exception e)
		 {
			e.printStackTrace();
			 vo.setStrMsgString("BillingCancellationTransHLP.getTariffDetails() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
		
	     }
		// System.out.println("sBuffer.toString()-->"+sBuffer.toString());
	     return sBuffer.toString();
		
	 }
}
