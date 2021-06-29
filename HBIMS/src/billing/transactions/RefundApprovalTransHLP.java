
package billing.transactions;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

public class RefundApprovalTransHLP{
	 
public static String getBillDetails(RefundApprovalTransVO vo)
{
	WebRowSet wb = null;
	 
	 StringBuffer sBuffer = new StringBuffer("");
		 try
		 {
			 if(vo.getHspServ()!=null)
			 {	 
			  String s=vo.getHspServ();
			  wb = vo.getStrBillDtls();
			          sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");       
	 	              sBuffer.append("<tr><td colspan='9' class='TITLE'>Receipt Details</td></tr>");
	                  sBuffer.append("<tr><td width='5%' class='multiLabel'><div align='center'></div></td>");
                      sBuffer.append("<td width='19%' class='multiLabel'><div align='center'>Receipt No.</div></td>");  
	  	              sBuffer.append("<td width='19%' class='multiLabel'><div align='center'>Receipt Date</div></td>");
	  	              sBuffer.append("<td width='19%' class='multiLabel'><div align='center'>Hospital Service</div></td>");
	  	              sBuffer.append("<td width='19%' class='multiLabel'><div align='center'>Receipt Type</div></td>");
	                  sBuffer.append(" <td width='19%' class='multiLabel'><div align='center'>Receipt Amt</div></td>");
	                  sBuffer.append("</tr>");
	                  sBuffer.append("<input type='hidden' name='xmlVal' value='"+s+"'>");
	                  for(int i=0;wb.next();i++)
	                  {
	                	     String billno=wb.getString(1);
	                	     String temp[]=(wb.getString(6)).replace("^","#").split("#");
	                	     String bNo_HpSrv=billno+"^"+wb.getString(3)+"^"+temp[4];
				    		 String billdate=	wb.getString(2);
							 sBuffer.append("<input type='hidden' name='billNo_Id' value=\""+bNo_HpSrv+"\">");
							 //sBuffer.append("<input type='hidden' name='strBillNo' value=\""+wb.getString(6)+"\">");
							 //sBuffer.append("<input type='hidden' name='strBillDate' value=\""+billdate+"\">");
							 /*vo.setStrBillNo(wb.getString(6));
							 vo.setStrBillDate(billdate);*/
							 sBuffer.append("<tr><td width='5%' class='multiLabel'><input type='radio' name='bServ_id' id='radio"+i+"' value=\""+wb.getString(6)+"\"  onClick='groupCheck(this);'></td>");
							 sBuffer.append("<td width='19%' class='multiControl'>");
							 sBuffer.append(billno);
							 sBuffer.append("</td>");
							 sBuffer.append("<td width='19%' class='multiControl'>");
							 sBuffer.append(billdate);
							 sBuffer.append("</td>");
							 sBuffer.append("<td width='19%' class='multiControl' name='hspServ'>");
							 sBuffer.append(wb.getString(3));
							 sBuffer.append("</td>");
							 sBuffer.append("<td width='19%' class='multiControl'>");
							 sBuffer.append(wb.getString(4));
							 sBuffer.append("</td>");
							 sBuffer.append("<td width='19%' class='multiControl'>");
							 sBuffer.append(wb.getString(5));
							 sBuffer.append("</td>");
							 sBuffer.append("<input type='hidden' name='PaymentModeflg' id='PaymentModeflg"+i+"' value='"+wb.getString(7)+"' >");
							 sBuffer.append("</tr>");
							
							 temp=null;
		              }     
	                  sBuffer.append("</table>");
			 }           
	     }
		 catch(Exception e)
		 {
			 vo.setStrMsgString("RefundApprovalTransHLP.getBillDetails() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
	     }
		 
	     return sBuffer.toString();
		
	 }
	
	
	
	 public static String getTariffDetails(RefundApprovalTransVO vo)
	 {
	    StringBuffer sBuffer = new StringBuffer("");
	    WebRowSet wb = null;
	    RefundApprovalTransBO bo = null;
		try{
			//System.out.println("hlp getTariffDetails");
			  bo = new  RefundApprovalTransBO();
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
	 	              sBuffer.append("<tr><td colspan='9' class='TITLE'>Tariff Details</td></tr>");
	                  sBuffer.append("<tr><td width='5%' class='multiLabel'><div align='center'></div></td>");
                      sBuffer.append("<td width='18%' class='multiLabel'><div align='center'>Tarriff Name</div></td>");
                      //new field
                      sBuffer.append("<td width='15%' class='multiLabel'><div align='center'>Credit Letter No/Date/Amount</div></td>");
                      sBuffer.append("<td width='10%' class='multiLabel'><div align='center'>Unprocessed Qty</div></td>");
	  	              sBuffer.append("<td width='17%' class='multiLabel'><div align='center'>Refund Type</div></td>");
	  	              sBuffer.append("<td width='5%' class='multiLabel'><div align='center'>Penalty (%)</div></td>");
	                  sBuffer.append(" <td width='5%' class='multiLabel'><div align='center'>Refund Qty</div></td>");
	                  sBuffer.append(" <td width='10%' class='multiLabel'><div align='center'>Unit</div></td>");
	                  sBuffer.append(" <td width='15%' class='multiLabel'><div align='center'>Refund Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</div></td>");
	                  
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
		                	     if(temp[2]== null||temp[2].equals("")||temp[2].equals("0"))
		                	     {               
		                	    	 vo.setStrUnit("0");
		                	     }
		                	     else
		                	     {
		                	    	 vo.setStrUnit(temp[2]);
		                	     }	
		                	     //unit data for unit combo
		                	     //bo.getUnitCombo(vo);
		                	     //unitCmb =  vo.getStrUnitCmbVal();
		                	     //System.out.println("vo.getStrUnit()"+vo.getStrUnit());
		                	     unitCmb =BillConfigUtil.getDefaultUnitComboWithBaseValue(vo.getStrUnit()+"^1");
		                	     
		                	     String is_pack = temp[14];
		                	     String qty[] = wb.getString(2).split(" "); //remaine qty unit name
		                	     String TariffName=	wb.getString(1);
					    		 
					    		 sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
					    		 sBuffer.append("<input type='hidden' name='lnkVal' value='"+app_id+"' >");
					    		 sBuffer.append("<input type='hidden' name='isPack' value='"+is_pack+"' >");
					    		 sBuffer.append("<input type='hidden' name='penalty' id='penalty"+i+"'  value='"+vo.getStrPenalty()+"' >");
					    		 
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
					    		 sBuffer.append("<tr><td width='5%' class='multiLabel'><input type='checkbox' name='chk' id='chk"+i+"' value=\""+app_id+"\" onClick=\"calRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\"></td>"); 
					    	   			    		  
								 sBuffer.append("<td width='18%' class='multiControl'>");
								 sBuffer.append("<a name='trfName' value='"+app_id+"' STYLE='CURSOR:POINTER; color:blue' onClick='myFunc(this,"+i+","+1+");'>"+TariffName+"</a>");
								 sBuffer.append("</td>");
								 
								 //it has to be given name later on
								 //credit details
								 sBuffer.append("<input type='hidden' name='clNo' id='clNo"+i+"' value="+wb.getString(9)+">");
								 sBuffer.append("<input type='hidden' name='clDate' id='clDate"+i+"' value="+wb.getString(10)+">");
								 sBuffer.append("<input type='hidden' name='clPath' id='clPath"+i+"' value="+wb.getString(13)+">");
								 sBuffer.append("<input type='hidden' name='clientNo' id='clientNo"+i+"' value="+wb.getString(14)+">");
								 sBuffer.append("<input type='hidden' name='amtByPat' id='amtByPat"+i+"' value="+wb.getString(12)+">");
								 sBuffer.append("<input type='hidden' name='amtByClient' id='amtByClient"+i+"' value="+wb.getString(11)+">");
								 
								 sBuffer.append("<td width='15%' class='multiControl' >"+wb.getString(9)+"/"+wb.getString(10)+"/"+wb.getString(11)+"</td>");
								 
								 sBuffer.append("<td width='10%' class='multiControl' name='unProcessedQty'>"+wb.getString(2)+"</td>");
								
								// System.out.println("rateBaseVal--"+rateBaseVal);
								 
								 sBuffer.append("<td width='17%' class='multiControl'>");
								 sBuffer.append("<select name='refundType' id='refundType"+i+"' onChange=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\"><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select>");  
								 sBuffer.append("</td>");
								 
								 sBuffer.append("<td width='5%' class='multiControl' >");
								 sBuffer.append("<input type='textbox' class='txtFldMin' name='trf_penalty' id='trf_penalty"+i+"' value='0' readonly>");	 
								 sBuffer.append("</td>");
								 
								 sBuffer.append("<td width='5%' class='multiControl'>");
								 sBuffer.append("<input type='textbox' class='txtFldMin' name='refundQty' id='qty"+i+"' value='0'  maxlength='3' onkeypress='return validateData(event,5);'  onKeyUp=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\" readonly>");	 
								 sBuffer.append("<input type='hidden'  name='HidRefundQty' id='HidRefundQty"+i+"' value='"+qty[0]+"'>");
								 
								 sBuffer.append("</td>");
								 
								 sBuffer.append("<td width='10%' class='multiControl'><div name='unit' id='unitcmb'"+i+">");
								 if(unitCmb==null)
									 sBuffer.append("<select name='strUnitId' id='unit"+i+"' class='comboMin'  onChange=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\" ><option value='0'>Select Value</optoin></select> ");
								 else
									 sBuffer.append("<select name='strUnitId' id='unit"+i+"' class='comboMin'  onChange=\"calTariffRefundCost("+i+","+is_pack+","+remQty+","+rate1+","+rateBaseVal+","+disUnit+","+disType+","+servTax+"," + temp[2] + ");\">"+unitCmb+"</select>");
								 
								 sBuffer.append("</div></td>");
								 
								 sBuffer.append("<td width='15%' class='multiControl' style='font-weight:bold'>");
								 sBuffer.append("<div id='refCostDiv"+i+"' style='display:block'>");
								 sBuffer.append("<input type='hidden' class='txtFldMin' name='refundAmt' value='"+0.00+"' id = 'refundAmt"+i+"'  readonly></div>");
								 sBuffer.append("<div id='refCost"+i+"'>0.00</div></td>");
								 sBuffer.append("</table>"); 
		                  }  
		                  sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");    
		                  sBuffer.append("<tr><td  colspan='6' width='86%' class='LABEL' ><font color='red'>Net Refund Amount(<img src='/HBIMS/hisglobal/images/INR.png'>)</font></td>");
		                  sBuffer.append("<td width='13%' class='multiControl' style='color:red;font-weight:bold'>");
						  sBuffer.append("<input type='hidden' class='txtFldNormal' name='trf_grossRefund' id='trf_grossRefund' value='0.00'  readonly><div id='netRefCost'>0.00</div></td>");
						  sBuffer.append("<td  class='multiControl'>");
						  sBuffer.append("</td></tr>");
		                  sBuffer.append("</table>");
	     			 }
	                 else
	                 {
	                	 sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");       
		 	             sBuffer.append("<tr><td colspan='9'><div align='center'><font color='red'><b>No Tariff Details Found/Credit Bill Can Not Be Refunded</b></font></div></td></tr>"); 
	                 }
				 
	     }
		 catch(Exception e)
		 {
			e.printStackTrace();
			 vo.setStrMsgString("RefundApprovalTransHLP.getTariffDetails() --> "
						+ e.getMessage());
				vo.setStrMsgType("1");
		
	     }
		// System.out.println("sBuffer.toString()-->"+sBuffer.toString());
	     return sBuffer.toString();
		
	 }
	 
	

	 public static String  popUpShw(RefundApprovalTransVO vo)
	 {
		 StringBuffer sb = null;
		    String strValmode = vo.getStrValmode();
	        String temp="";
	       // System.out.println("strValmode--"+strValmode);
	        String val[]=strValmode.replace('^', '#').split("#");
	        
	        String strRatePerUnit = "" ;
	        String strDisPerUnit = "" ;
	        String strServTax = "" ;
	        
	        try{
	        	  sb = new StringBuffer("");
	    		  vo = new RefundApprovalTransVO();
	    		 
	    		  if(val[0].equals("1")){
	    			  strRatePerUnit = val[4];
	    			  strDisPerUnit = val[9];
	    			  strServTax = val[8];
	    			  if (val[10].equals("1")){
	    				  
	    				  temp = "Fixed";
	    			  }else if(val[10].equals("2")){
	    				  
	    				  temp = "Percentage";
	    			  }
	                	  
	                
	    		  }
	    		  else{
	    			  strRatePerUnit = val[3];
	    			  strDisPerUnit = val[8];
	    			  strServTax = val[7];
	    			  if (val[9].equals("1")){
	    				  
	    				  temp = "Fixed";
	    			  }else if(val[9].equals("2")){
	    				  
	    				  temp = "Percentage";
	    			  }
	                
	    		  }


                sb

                            .append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");

                sb

                            .append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");

                sb

                            .append("<td align='right'><img src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"menu1\");'> </td></tr>");

                sb.append("</table> ");

                sb.append("<table width='400' align='center' cellspacing='1px'>");

                sb.append("<tr>");

                sb.append("<td class='multiLabel' width='20%'>Rate(Rs)/Unit</td>");

                sb.append("<td class='multiControl' width='20%'>" + strRatePerUnit +"/"+val[12]

                            + "</td>");

                sb.append("<td class='multiLabel' width='20%'>Discount(Rs)/Unit</td>");

                sb.append("<td class='multiControl' width='20%'>" + strDisPerUnit

                            + "</td>");

                sb.append("</tr>");

                sb.append("<tr>");

                sb.append("<td class='multiLabel' width='20%'>Discount Type</td>");

                sb.append("<td class='multiControl' width='20%'>" + temp + "</td>");

                sb.append("<td class='multiLabel' width='20%'>Service Tax</td>");

                sb.append("<td class='multiControl' width='20%'>" + strServTax+" %"

                            + "</td>");

                sb.append("</tr>");

                sb.append("</table>");

                sb.append("<table width='400' align='center'>");

                sb.append("<tr class='FOOTER'>");

                sb.append("<td colspan='3'>&nbsp;</td>");

                sb.append("</tr></table>");


	    	
			   }catch(Exception e){
				   vo.setStrMsgString("RefundApprovalTransHLP.popUpShw() --> "
							+ e.getMessage());
					vo.setStrMsgType("1");
					
			
			}
		return sb.toString();
		
	 }
	
	
}
	 