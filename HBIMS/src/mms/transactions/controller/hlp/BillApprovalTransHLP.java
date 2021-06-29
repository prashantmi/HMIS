package mms.transactions.controller.hlp;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.sql.rowset.WebRowSet;

import mms.transactions.bo.BillApprovalTransBO;
import mms.transactions.vo.BillApprovalTransVO;

/**
 * 
 * @author dell
 *
 */
public class BillApprovalTransHLP {
	
	public static String getScheduleDetails(BillApprovalTransVO vo) throws HisException
	 {
		StringBuffer sBuffer = new StringBuffer("");
		WebRowSet wb=null;
		int count=0;
		 try 
		 {
			 wb=vo.getStrScheduleDtlsWS(); 
			 //System.out.println("ScheduleWsSize->"+wb.size());
			if(wb.size() != 0)
			{	
				 //wb.beforeFirst();
				
				 //System.out.println("ScheduleWsSize->"+wb.size());
				 sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='black'>");   
				 sBuffer.append("<td width='5%' colspan='1' class='multiLabel'></td>");  
				 sBuffer.append("<td width='23.75%' colspan='1' class='multiLabel'><div align='center'>Challan No.</div></td>");
	             sBuffer.append("<td width='23.75%' colspan='1' class='multiLabel'><div align='center'>Schedule No.</div></td>");  
	  	         sBuffer.append("<td width='23.75%' colspan='1' class='multiLabel'><div align='center'>Schedule Date</div></td>");
	  	         sBuffer.append("<td width='23.75%' colspan='1' class='multiLabel'><div align='center'>Schedule Type</div></td>");
	             sBuffer.append("</tr>");
                 while(wb.next())
    			 {		     
                	 count++;
    	                	String strScheduleNo = wb.getString(1);
    	  					String strScheduleDate = wb.getString(2);
    	  				//	String strScheduleType = wb.getString(3);
    	  					String strScheduleTypeName = wb.getString(4);
    	  					String strPeneltyLateSchduleWise=wb.getString(5);
    	  					String strPenltyRejejectedSchduleWise=wb.getString(6);
    	  					String strChallanNo=wb.getString(7);
    	  					//String  strScheduleChk = strScheduleNo+"^"+strScheduleType;
    	  					sBuffer.append("<tr>");
    	  					sBuffer.append("<td width='5%' colspan='1' class='multiControl'><input type='checkbox' name='strScheduleChk' value='"+strScheduleNo+"'></td>");
    	  					sBuffer.append("<td width='23.75%' colspan='1' class='multiControl'>"+strChallanNo+"</td>");
    	  					sBuffer.append("<td width='23.75%' colspan='1' class='multiControl'>"+strScheduleNo+"</td>");
    	  					sBuffer.append("<td width='23.75%' colspan='1' class='multiControl'>"+strScheduleDate+"</td>");
    	  					sBuffer.append("<td width='23.75%' colspan='1' class='multiControl'>"+strScheduleTypeName);
    	  					sBuffer.append("<input type='hidden' name='strPeneltyLateSchduleWise' value='"+strPeneltyLateSchduleWise);
    	  					sBuffer.append("' /><input type='hidden' name='strPenltyRejejectedSchduleWise' value='"+strPenltyRejejectedSchduleWise+"' /></td>");
    	  					sBuffer.append("</tr>");	
    	  		}
                sBuffer.append("<tr><td class='multiControl' width='100%' colspan='5'><img align='middle' name='compile' style='cursor: pointer; ' title='To click for Records' src='../../hisglobal/images/Compile.png' onclick='scheduleCheckCompile();'/></td></tr>");
               
                sBuffer.append("</table>");
			}
			
		 }
		 catch(Exception e)
		 {
			 new HisException("Bill Approval Transaction","BillApprovalViewTransHLP.getScheduleDetails()-->",e.getMessage());
	     }
	    return sBuffer.toString();
	 	}
	
	
	public static String getScheduleItemDetails(BillApprovalTransVO vo)
	 {
		StringBuffer sBuffer = new StringBuffer("");
	//	int count=0;
		double strTotCost=0;
		WebRowSet wb=null;
		BillApprovalTransBO bo=null;
		double strScheduleCost =0;
		String scheduleBudget ="0.00";
		String Cost ="0.00";
		String selScheduleNo[]=null;
		 try 
		 {
			 
			     bo=new BillApprovalTransBO();
			     NumberFormat formatter = new DecimalFormat("############.##");  
				    				
				 selScheduleNo=vo.getStrSelScheduleNos().replace('^', '#').split("#");
				
				 for(int i=0 , stopI = selScheduleNo.length ;i<stopI;i++)
				 {
				    vo.setStrScheduleNo(selScheduleNo[i]);
					strScheduleCost =0;
					bo.getScheduleItemDtls(vo);
					wb=vo.getStrScheduleItemDtlsWS(); 
					String scheduleSelPlusIdDIV="scheduleSelPlusId"+i;
					String scheduleSelMinusIdDIV="scheduleSelMinusId"+i;
					String selectedScheduleNoDIV="selectedScheduleNoDIV"+i;
					wb.beforeFirst();
					sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>"); 
					sBuffer.append("<tr><td width='5%'class='TITLE' colspan='1'>");
					sBuffer.append("<div id='"+scheduleSelPlusIdDIV+"' align='center' style='display:none;'>");
					sBuffer.append("<img src=\"../../hisglobal/images/plus.gif\" title='Show Details' onClick=\"view1('scheduleSelPlusId"+i+"','scheduleSelMinusId"+i+"','selectedScheduleNoDIV"+i+"');\" style='cursor: pointer; '/>");
					sBuffer.append("</div>");  
					sBuffer.append("<div id='"+scheduleSelMinusIdDIV+"' style='display:block;' align='center'>");
					sBuffer.append("<img src=\"../../hisglobal/images/minus.gif\" title='Close Details' onClick=\"view2('scheduleSelPlusId"+i+"','scheduleSelMinusId"+i+"','selectedScheduleNoDIV"+i+"');\"  style='cursor: pointer; '/></div></td>");
					sBuffer.append("<td width='95%' class='TITLE' colspan='5' width='95%'> <div id='' style='color:blue;'>Schedule No :: "+selScheduleNo[i]+"</div></td>");  
					sBuffer.append("</tr></table>"); 
					sBuffer.append("<div id='"+selectedScheduleNoDIV+"'>");
					sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>"); 
	  	            sBuffer.append("<tr><td width='35%' colspan='2' class='multiLabel'><div align='center'>Item name</div></td>");
	  	            sBuffer.append("<td width='15%' colspan='1' class='multiLabel'><div align='center'>Rate/Unit</div></td>");
	  	            sBuffer.append("<td width='15%' colspan='1' class='multiLabel'><div align='center'>Accepted Qty</div></td>");
	  	            sBuffer.append("<td width='15%' colspan='1' class='multiLabel'><div align='center'>Tax(%)</div></td>");
	                sBuffer.append("<td width='20%' colspan='1' class='multiLabel'><div align='center'>Cost</div></td>");
	                sBuffer.append("</tr>");
				    while(wb.next())
				    {		     
				    	String strItemName = wb.getString(3);
	  					String strRateUnit = wb.getString(4)+"/"+wb.getString(6);
	  					String strAccQty = wb.getString(7)+" "+wb.getString(9);
	  					String strTax = wb.getString(10);
	  					double strCost =Double.parseDouble(wb.getString(11));
	  					Cost = formatter.format(new BigDecimal(strCost));
	  						  					
	  					sBuffer.append("<tr>");
	  					sBuffer.append("<td width='35%' colspan='2' class='multiControl'>"+strItemName+"</td>");
	  					sBuffer.append("<td width='15%' colspan='1' class='multiControl'>"+strRateUnit+"</td>");
	  					sBuffer.append("<td width='15%' colspan='1' class='multiControl'>"+strAccQty+"</td>");
	  					sBuffer.append("<td width='15%' colspan='1' class='multiControl'>"+strTax+"</td>");
	  					sBuffer.append("<td width='20%' colspan='1' class='multiControl'>"+Cost+"</td>");
	  					sBuffer.append("</tr>");
	  					
	  					strTotCost=strTotCost+strCost;
	  					
	  					strScheduleCost = strScheduleCost+strCost;
	  					 scheduleBudget = formatter.format(new BigDecimal(strScheduleCost));
	  				}
				    sBuffer.append("<input type='hidden' name='strScheduleNoArrH' id='strScheduleNo"+i+"' value='"+selScheduleNo[i]+"'/>");
				    sBuffer.append("<input type='hidden' name='strScheduleCostArrH' id='strScheduleCost"+i+"' value='"+scheduleBudget+"'/>");
				    sBuffer.append("</table></div>");
				} 
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");  
				sBuffer.append("<td class='LABEL' colspan='4' width='80%'>Total Schedule Item Cost</td>"); 
				sBuffer.append("<td class='CONTROL' colspan='1' width='20%'><div id='strTotalItemCostDIV' style='color: blue;font-weight:bold;align:center;'>"+formatter.format(new BigDecimal(strTotCost))+"</div><input type='hidden' name='strTotalItemCost' value='"+formatter.format(new BigDecimal(strTotCost))+"' /></td>");
				sBuffer.append("</table>");
				
				vo.setStrTotalItemCost(String.valueOf(formatter.format(new BigDecimal(strTotCost))));
	  
		 }
		 catch(Exception e)
		 {
			 new HisException("Bill Approval Transaction","BillApprovalTransHLP.getScheduleItemDetails()-->",e.getMessage());
	     }
	    return sBuffer.toString();
	 	}
	
	public static String getOtherDetails(BillApprovalTransVO vo) throws HisException
	 {
		StringBuffer sb = new StringBuffer("");
		HisUtil util=null;
		BillApprovalTransBO bo=null;
		String waiveOffApprovedBy="";
		try {
			util=new HisUtil("MMS","Bill Approval Transaction");
			bo=new BillApprovalTransBO();
			
			//System.out.println("sizeApprovedBy->"+vo.getStrWaiveOffApprovedByWS().size());
			
		//	float overallTax=Float.parseFloat(vo.getStrTotalItemCost())*Float.parseFloat(vo.getStrOverallPOTax())/100;
		//	float advanceTaken=Float.parseFloat(vo.getStrAdvanceTaken());
		//	float advanceAdjusted=Float.parseFloat(vo.getStrAdvanceAdjusted());
			float balAdvance=Float.parseFloat(vo.getStrAdvanceTaken())-Float.parseFloat(vo.getStrAdvanceAdjusted());
			float penaltyImposed=Float.parseFloat(vo.getStrNetPenalty());	
			
			sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
	       // sb.append("<tr><td class='TITLE' colspan='5'>Other Cost Details</td></tr>");
            sb.append("<tr><td class='LABEL' colspan='2' width='25%'>Overall Tax(<font color='blue'>"+vo.getStrOverallPOTax()+"%</font>)<input type='hidden' name='strOverallPOTax' value='"+vo.getStrOverallPOTax()+"' /></td>");
            sb.append("<td class='CONTROL' colspan='1' width='25%'><div id='overallTaxDIV'>0</div></td>");
            sb.append("<td class='LABEL' colspan='1' width='25%'>Balance Advance</td>");
            sb.append("<td class='CONTROL' colspan='1' width='25%'><input type='hidden' name='strBalanceAdvance' value='"+balAdvance+"' /><a style='color: blue;cursor: pointer; ' onClick='openBalAdvanceDtl(this);'>"+balAdvance+"</a></td></tr>");
            sb.append("<tr><td class='LABEL' colspan='2' width='25%'>Penalty Imposed</td>");
            sb.append("<td class='CONTROL' colspan='1' width='25%'><div id='peneltyDivId'>"+penaltyImposed+"</div><input type='hidden' name='strNetPenalty' value='"+penaltyImposed+"'/></td>");
           // if(penaltyImposed>0)
               sb.append("<td class='LABEL' colspan='1' width='25%' ><input type='checkbox' name='strWaiveOffChk' onClick='waiveOffdtl(this);'></td>");
           // else
             //  sb.append("<td class='LABEL' colspan='1' width='25%' ><input type='checkbox' name='strWaiveOffChk' disabled></td>"); 	
            sb.append("<td class='CONTROL' colspan='1' width='25%'>Waive Off</td>");
            sb.append("</tr>");
            sb.append("</table>"); 
          //  if(penaltyImposed>0)
            {
            	bo.getWaiveOffApprovedBy(vo);
            	waiveOffApprovedBy=util.getOptionValue(vo.getStrWaiveOffApprovedByWS(), "0", "0^Select Value", false);
            	sb.append("<div id='waiveOffDtlDIV' style='display:none'>"); 
	            sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
	            sb.append("<tr><td class='TITLE' colspan='5'>Waive Off Details</td></tr>");
	            sb.append("<tr><td class='LABEL' colspan='2' width='25%'><font color='red'>*</font>Waive Off Amt.</td>");
	            sb.append("<td class='CONTROL' colspan='1' width='25%'><input type='text' name='strWaiveOffAmt' class='txtFldNormal' value='0' onKeyPress='return validateData(event,5);' onKeyUp='calculateNetCost();'></td>");
	            sb.append("<td class='LABEL' colspan='1' width='25%'><font color='red'>*</font>Approved By</td>");
	            sb.append("<td class='CONTROL' colspan='1' width='25%'><select name='strWaiveOffApprovedBy'>"+waiveOffApprovedBy+"</select></td></tr>");
	            sb.append("<tr><td class='LABEL' colspan='2' width='25%'><font color='red'>*</font>Approved Date</td>");
	            sb.append("<td class='CONTROL' colspan='3' width='75%'>"+getDatePicker("strWaiveOffApprovedDate", vo.getStrCurrentDate(), true)+"</td>");
	            sb.append("</table></div>"); 
            }
            sb.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
            sb.append("<tr><td class='LABEL' colspan='1' width='25%'><font color='red'>*</font>Advance Adjusted</td>");
            sb.append("<td class='CONTROL' colspan='1' width='25%'><input type='text' name='strAdvanceAdjusted' class='txtFldNormal' value='0' onKeyPress='return validateData(event,5);' onKeyUp='calculateNetCost();'></td>");
            sb.append("<td class='LABEL' colspan='1' width='25%'>Net Cost</td>");
            sb.append("<td class='CONTROL' colspan='1' width='25%'><input type='hidden' name='strNetItemCost' value='0' /><div id='netCostDIV' style='color: red;font-weight:bold'>0.0</div></td></tr>");
            sb.append("</table>"); 
		 
		}
		 catch(Exception e)
		 {
			 new HisException("Bill Approval Transaction","BillApprovalViewTransHLP.getReceiptDetails()-->",e.getMessage());
	     }
	    return sb.toString();
	 	}
	
	public static String getDatePicker(String fieldName, String dateValue, boolean readOnly)
    {
        String dateString = "";
        StringBuilder strBuffer = new StringBuilder(500);
        strBuffer.append((new StringBuilder(" <input size='9%' type=\"text\" name=\"")).append(fieldName).append("\" id=\"").append(fieldName).append("\" ").toString());
        if(readOnly)
            strBuffer.append(" readonly = \"false\" ");
        strBuffer.append((new StringBuilder(" value='")).append(dateValue).append("'>").toString());
        strBuffer.append((new StringBuilder(" <img src=\"../../hisglobal/images/imgDatepicker.png\" id=\"")).append(fieldName).append("1\" style=\"cursor: pointer; border: 1px solid red;\" title=\"Date selector\" ").toString());
        strBuffer.append(" onmouseover=\"Calendar.datePickerAjax(event,document.forms[0]."+fieldName+"),this.style.background='red';\" onmouseout=\"this.style.background=''\"> \n");
      /*  strBuffer.append("<script event=\"Click()\" language=\"JavaScript\"> \"+Calendar.setup({ ");
        strBuffer.append((new StringBuilder(" inputField : \"")).append(fieldName).append("\",ifFormat : \"%d-%b-%Y\",button : \"").append(fieldName).append("1\",singleClick : true\n").toString());
        strBuffer.append("})\";</script>\n");*/
        dateString = strBuffer.toString();
        strBuffer = null;
        return dateString;
    }
	
	public static String getPONoSearchListDetails(BillApprovalTransVO vo)
	throws SQLException {
	StringBuffer br = new StringBuffer();
	int i = 0;
	WebRowSet wb=null; 
	try {
		wb=vo.getStrPOSearchDetailsWs();
		//System.out.println("wsSizePopUp->"+wb.size());
		if (wb.size() != 0) {
			br.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
			br.append("<TR>");
			br.append("<TD WIDTH='5%' CLASS='multiLabel' colspan='1'></TD>");
			br.append("<TD WIDTH='25%' CLASS='multiLabel' colspan='1'>PO No.</TD>");
			br.append("<TD WIDTH='20%' CLASS='multiLabel' colspan='1'>PO Date</TD>");
			br.append("<TD WIDTH='25%' CLASS='multiLabel' colspan='1'>PO Type</TD>");
			br.append("<TD WIDTH='25%' CLASS='multiLabel' colspan='1'>Supplier Name</TD>");
			br.append("<TR>");
			while (wb.next()) {
				String strPOSearchDtlChkVal=wb.getString(1)+"^"+wb.getString(2)+"^"+wb.getString(3)+"^"+wb.getString(5)+"^"+wb.getString(9)+"^"+wb.getString(7);
				br.append("<TR>");
				br.append("<TD WIDTH='5%'  CLASS='multiControl' colspan='1'><input type='radio' name ='strPOSearchDtlChk' id='strItemDetailsChk"+i+"' value='"+strPOSearchDtlChkVal+"' onClick='poNoSel(this);'/> </TD>");
				br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(1) + "</TD>");
				br.append("<TD WIDTH='20%' CLASS='multiControl' colspan='1'>"+ wb.getString(2) + "</TD>");
				br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(4) + "</TD>");
				br.append("<TD WIDTH='25%' CLASS='multiControl' colspan='1'>"+ wb.getString(6) + "</TD>");
				br.append("</TR>");
				i++;
			}
			br.append("</table>");
		} else {
			br.append("<table class='TABLEWIDTH' align='center'cellpadding='1px' cellspacing='1px'>");
			br.append("<TR>");
			br.append("<TD WIDTH='100%' CLASS='multiControl' colspan='5'>"
					+ "<font color='red' size='2'>No Record Found</font></TD>");
			br.append("</TR>");
			br.append("</table>");
		}
	
	} catch (Exception e) {
	
	}
	
	return br.toString();
}
	public static String createPeneltyDtl(WebRowSet wb1,String strLatePenelty,String strRejectedPenelty)
	{ 
		StringBuffer br = new StringBuffer();
		
			try{
				
				br.append("<table width='400'>\n");
				br.append("<tr class='HEADER'>\n");
				
				br.append("<td align='left' width='90%'>Penelty Details</td>");
				br.append("<td align='center' width='10%'><img  style='cursor:pointer;cursor:pointer' src='../../hisglobal/images/popUp_cancel.JPG'");
				br.append("onClick='closePeneltyItemDtl();' title='Click Here To Close Popup'></td>");
				br.append("</tr>\n");
				br.append("</table>");
				br.append("<table width='400' cellspacing='1px' cellpadding='1px'>\n");
				br.append("<tr>\n");
				br.append("<td class='LABEL' width='25%'>\n");
				br.append("Late Supply Penelty");
				br.append("</td>");
				br.append("<td class='CONTROL'  >\n");
				br.append(strLatePenelty);
				br.append("</td>");
				br.append("</tr>\n");
				br.append("<td class='LABEL'  width='25%'>\n");
				br.append("Rejected/Breakage Penelty");
				br.append("</td>");
				if(wb1.size()==0){
					br.append("<td class='CONTROL' >\n");
					br.append(strRejectedPenelty);
					br.append("</td>");
				}else{
						br.append("<td class='CONTROL' ><a style='color: blue;cursor: pointer; ' onClick='showPeneltyItemDtl();' title='Click Here to View Items Details'>\n");
						br.append(strRejectedPenelty+"</a></td>");
				
				}
				
				br.append("</tr>\n");
				br.append("</table>");
				if(wb1!=null ||wb1.size()!=0){
					br.append("<div id='itemDtl' style='display:none'>");
					br.append("<table width='400' cellspacing='1px' cellpadding='1px'>\n");
					br.append("<tr>\n");
					br.append("<td class='TITLE' colspan='4'>Breakage/Rejected Item Details</td>");
					br.append("</tr>\n");
					br.append("<tr>\n");
					br.append("<td class='multiLabel'>Item Name</td>\n");
					br.append("<td 'class= 'multiLabel'>Batch No.</td>\n");
					br.append("<td 'class='multiLabel'>Rejected Qty</td>\n");
					br.append("<td 'class='multiLabel'>Breakage Qty</td>\n");
					br.append("</tr>\n");
					
					while(wb1.next())
					{
						br.append("<tr>\n");
						br.append("<td class='multiControl'>"+wb1.getString(1)+"</td>\n");
						br.append("<td class= 'multiControl'>"+wb1.getString(2)+"</td>\n");
						br.append("<td class='multiControl'>"+wb1.getString(3)+"</td>\n");
						br.append("<td class='multiControl'>"+wb1.getString(4)+"</td>\n");
						br.append("</tr>\n");
					}
					br.append("</table>");
					br.append("</div>");
				}
				br.append("<table width='400' cellspacing='1px' cellpadding='1px'>\n");
				br.append("<tr class='FOOTER'>");
				br.append("<td colspan='2' align ='center'></td>");
				br.append("</tr>");
				br.append("</table>");
		
		}catch(Exception e){
			try{
				
				throw new Exception("BillApprovalTransHLP.createPeneltyDtl--->"+e.getMessage());
			}
			catch(Exception e1)
			{}
		}
		finally
		{
			wb1=null;
			
		}
	return br.toString();
	}


}



