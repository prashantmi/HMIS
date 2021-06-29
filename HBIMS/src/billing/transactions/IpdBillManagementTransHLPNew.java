package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

public class IpdBillManagementTransHLPNew{

	public static String getViewBillParticularsList(IpdBillManagementTransFB formBean) throws Exception 
	{
		StringBuffer sb = new StringBuffer("");

		sb.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing ='1px'>");
	    sb.append("<tr><td class='TITLE' colspan='8' >Tariff Details</td> </tr>");
		sb.append("<tr><td width='5%' class='multiLabel'>SNo</td>");
		sb.append("<td width='20%' class='multiLabel'>Particulars</td>");
		sb.append("<td width='15%' class='multiLabel'>Total Amt(<img src='../../hisglobal/images/INR.png'>)</td>");
		sb.append("<td width='10%' class='multiLabel'>Discount Amt(<img src='../../hisglobal/images/INR.png'>)</td>");
		//sb.append("<td width='10%' class='multiLabel'>Penalty(<img src='../../hisglobal/images/INR.png'>)</td>");
		sb.append("<td width='15%' class='multiLabel'>Service Tax Amt(<img src='../../hisglobal/images/INR.png'>)</td>");
		sb.append("<td width='15%' class='multiLabel'>Net Amt(<img src='../../hisglobal/images/INR.png'>)</td>");
		sb.append("<td width='10%' class='multiLabel'>Details</td></tr></table>");

		WebRowSet ws = formBean.getStrParticularDtlsListWs();
		
		String strParticular = null;
		String strTotalAmt = null;
		String strDisAmt = null;
		String strPenelty = null;
		String strSeviceTaxAmt = null;
		String strNetAmt = null;
		int i = 0;
		
		double dblNetAmtTotal = 0;
		
		if(ws != null && ws.size() > 0)
		{
			
			while(ws.next())
			{
				strParticular = ws.getString(1);
				strTotalAmt = ws.getString(2);
				strDisAmt = ws.getString(4);
				strPenelty = ws.getString(5);
				strSeviceTaxAmt = ws.getString(3);
				strNetAmt = ws.getString(6);
	
				if (strParticular == null || strParticular.equals(""))
					strParticular = "-----";
				if (strTotalAmt == null || strTotalAmt.equals(""))
					strTotalAmt = "0";
				if (strDisAmt == null || strDisAmt.equals(""))
					strDisAmt = "0";
				if (strPenelty == null || strPenelty.equals(""))
					strPenelty = "0";
				if (strSeviceTaxAmt == null || strSeviceTaxAmt.equals(""))
					strSeviceTaxAmt = "0";
				
							
				dblNetAmtTotal = dblNetAmtTotal + Double.parseDouble(strNetAmt);
				
				sb.append("<table class='TABLEWIDTH' align='center' border='0' cellpadding='1px' cellspacing ='1px'>");
				sb.append("<tr>");
				sb.append("<td width='5%' class='multiControl'><b>");
				sb.append(i + 1);
				sb.append("</b></td>");
				sb.append("<td width='20%' class='multiControl'>");
				sb.append(strParticular);
				sb.append("</td>");
				sb.append("<td width='15%' class='multiControl'>");
				sb.append(HisUtil.getAmountWithDecimal(Double.parseDouble(strTotalAmt),2));
				sb.append("</td>");
				sb.append("<td width='10%' class='multiControl'>");
				sb.append(HisUtil.getAmountWithDecimal(Double.parseDouble(strDisAmt),2));
				sb.append("</td>");
/*				sb.append("<td width='10%' class='multiControl'>");
				sb.append(HisUtil.getAmountWithDecimal(Double.parseDouble(strPenelty),2));
				sb.append("</td>");*/
				sb.append("<td width='15%' class='multiControl'>");
				sb.append(HisUtil.getAmountWithDecimal(Double.parseDouble(strSeviceTaxAmt),2));
				sb.append("</td>");
				sb.append("<td width='15%' class='multiControl'>");
				sb.append(HisUtil.getAmountWithDecimal(Double.parseDouble(strNetAmt),2));
				sb.append("</td>");
				sb.append("<td width='10%' class='multiControl'>");
				sb.append("<input type='hidden' name='flagConfig' id='flagConfig"+ i + "' value='" + 0 + "'>");
				sb.append("<img bgColor='white' value='"+ i+ "' src='../../hisglobal/images/viewDetails.gif' style='cursor:hand;cursor:pointer;' onClick='getParticularsDetails("+ ws.getString(7) + "," + formBean.getStrAccountNo()+ "," + i + ");'>");
				sb.append("</td></tr></table>");
				sb.append("<div id='particularDtlsId" + i + "' style='display:none'></div>");
				i = i + 1;
			}			
			sb.append("<table class='TABLEWIDTH' align='center'   bgcolor='#000000' border='0' cellpadding='1px' cellspacing ='1px'>");
			sb.append("<tr>");
			sb.append("<td colspan='2' width='25%' CLASS='LABEL'><div align='left'>All Amounts Are Rounded Off</div></TD>");
			sb.append("<td colspan='2' width='50%' CLASS='LABEL' >Total Net Amount </TD>");
			sb.append("<td colspan='2' width='25%' CLASS='CONTROL' >&nbsp;&nbsp;<b>"+ HisUtil.getAmountWithDecimal(dblNetAmtTotal, 2) +"</b></TD>");
			sb.append("</tr>");
			sb.append("</table>");			
		}
		else
		{			
			sb.append("<table class='TABLEWIDTH' align='center'   bgcolor='#000000' border='0' cellpadding='1px' cellspacing ='1px'>");
			sb.append("<tr>");
			sb.append("<td colspan='6'  CLASS='multiControl' >"+ "<div class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED CR NO </div>"+ "</TD>");
			sb.append("</tr>");
			sb.append("</table>");			
		}
		
		return sb.toString();
}
	

	public static String getCompulsaryChargesView(WebRowSet ws , String strStartDate , String strEndDate , IpdBillManagementTransVO vo ) throws Exception 
	{
		StringBuffer sb = new StringBuffer("");
		try
		{			
			int count = 0;			
			if(ws != null && ws.size() >0)
			{				
				count = 1;				
				int qty = 0;
				int qty1 = dateDifference(strStartDate, strEndDate);
				qty = qty1 + 1;				
				
				sb.append("<table class='TABLEWIDTH' align='center' cellspacing ='1px'> ");
				sb.append("<tr> ");
				sb.append("<td class='multiLabel' width='5%'><input type='checkbox' checked='checked' name='strCompChargeMainCheck' id='strCompChargeMainCheck' onclick='checkAllCompCharges(this, \"strCompChargeCheck\" , \""+count+"\");'></td> ");
				sb.append("<td class='multiLabel' width='10%'>Date</td> ");
				sb.append("<td class='multiLabel' width='25%'>Tariff Name</td> ");
				sb.append("<td class='multiLabel' width='10%'>Rate/Unit</td> ");
				sb.append("<td class='multiLabel' width='10%'>Qty.</td> ");
				sb.append("<td class='multiLabel' width='10%'>Unit</td> ");
			    sb.append("<td class='multiLabel' width='10%'>Ser. Tax(%)</td> ");
				sb.append("<td class='multiLabel' width='10%'>Total Amt.(Rs)</td> ");
				sb.append("</tr> ");
				
				while(ws.next())
				{				
					String strId = ws.getString(1);
					String strTariffName = ws.getString(2);	
					if(strTariffName.toUpperCase().contains("ADMISSION CHARGE") || strTariffName.toUpperCase().contains("GATE PASS CHARGES") || strTariffName.substring(strTariffName.lastIndexOf("-")).toUpperCase().contains("GTPS"))
					{						
						qty = 1;						
					}
					else
					{						
						qty = qty1 + 1;						
					}								
					String strTariffDtls[] = strId.replace("^", "#").split("#");
					
		//			String strTariffId = strTariffDtls[0];
		//			String strGrpId  = strTariffDtls[1];
		//			String strProcedureCost  = strTariffDtls[2];
					String strTariffCost  = strTariffDtls[3];
					String strCost  = strTariffDtls[4];
					String strUnitId  = strTariffDtls[5];
					String strUnitBaseVal  = strTariffDtls[6];
		//			String strIsPackage  = strTariffDtls[7];
		//			String strIsAdvance  = strTariffDtls[8];
		//			String strIsRefund  = strTariffDtls[9];
		//			String strDefaultRate  = strTariffDtls[10];
					String strTariffActualCost  = strTariffDtls[11];
		//			String strDiscount   = strTariffDtls[12];
					String strServiceTax  = strTariffDtls[13];
					String strUnitName  = strTariffDtls[14];
		//			String strServiceId  = strTariffDtls[15];
		//			String strGTariffId  = strTariffDtls[16];
					
					
					float strTariffCostValue = 	qty * ( Float.parseFloat(strTariffCost) / Float.parseFloat(strUnitBaseVal) ); 					
					float strActualTariffCostValue = qty * ( Float.parseFloat(strTariffActualCost) / Float.parseFloat(strUnitBaseVal) );					
					float strServiceTaxAmt = strTariffCostValue * (Float.parseFloat(strServiceTax) / 100 );					
					float strTariffNetCostValue = strTariffCostValue + strServiceTaxAmt;			
					
			    sb.append("<tr> ");
				sb.append("<td class='multiControl' width='5%'><input type='checkbox' checked='checked' name='strCompChargeCheck' id='strCompChargeCheck"+count+"' onclick='manageCompalsaryChargesTariff(this , \""+count+"\");'> </td> ");
				sb.append("<td class='multiControl' width='10%'>"+strStartDate+"<input type='hidden' name='strOfflineTariffDate' id='strOfflineTariffDate"+count+"' value='"+strStartDate+"'></td> ");
				sb.append("<td class='CONTROL' width='35%'>&nbsp;<input type='hidden' name='strOfflineTariffName' id='strOfflineTariffName"+count+"' value='"+strTariffName+"' > <input type='hidden' name='strOfflineTariffDetailsHidden' id='strOfflineTariffDetailsHidden"+count+"' value='"+strId+"^1' >"+strTariffName+"</td> ");
				sb.append("<td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffRateUnit' id='strOfflineTariffRateUnit"+count+"' value='"+strCost+"' > "+strCost+"/"+strUnitName+" </td> ");
			 	sb.append("<td class='multiControl' width='10%'><input type='text' name='strOfflineTariffQty' id='strOfflineTariffQty"+count+"' value='"+qty+"' class='txtFldMin' maxlength='8' onkeyup='checkQtyValue(\""+count+"\");' ></td> ");
				sb.append("<td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffUnit' id='strOfflineTariffUnit"+count+"' value='"+strUnitId+"'>"+strUnitName+"</td> ");
				sb.append(" <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffServiceTax' id='strOfflineTariffServiceTax"+count+"' value='"+strServiceTax+"'><input type='hidden' name='strOfflineTariffServiceTaxAmtVal' id='strOfflineTariffServiceTaxAmtVal"+count+"' value='"+strServiceTaxAmt+"'><input type='hidden' name='strOfflineActualTariffAmtVal' id='strOfflineActualTariffAmtVal"+count+"' value='"+strActualTariffCostValue+"'>"+strServiceTaxAmt+"</td> ");
				sb.append("<td class='multiControl' style='font-weight: bold'  width='10%'><input type='hidden' name='strOfflineTariffNetAmount' id='strOfflineTariffNetAmount"+count+"' value='"+strTariffNetCostValue+"'><div id='strOfflineTariffNetAmountDivId"+count+"'>"+strTariffNetCostValue+"</div><input type='hidden' name='strPriority' id='strPriority"+count+"' value=1><input type='hidden' name='strDiscount' id='strDiscount"+count+"' value=0><input type='hidden' name='strDiscountType' id='strDiscountType"+count+"' value=0><input type='hidden' name='strDiscountAmt' id='strDiscountAmt"+count+"' value=0.00></td> ");
				sb.append("</tr> ");
				
				count = count + 1;				
				}
				
				sb.append("</table> ");				
				vo.setNCompChargeCount(count);				
			}
			else
			{
				sb.append("<table class='TABLEWIDTH' align='center'   bgcolor='#000000' border='0' cellpadding='1px' cellspacing ='1px'>");
				sb.append("<tr>");
				sb.append("<td colspan='8'  CLASS='multiControl' ><div class='errMsg' align='center'> No Record Available </div></TD>");

				sb.append("</tr>");
				sb.append("</table>");			
			}			
		}
		catch(Exception e)
		{			
			throw e;
		}	
	
		return sb.toString();		
	}
	
	
	
	public static String getSpecialChargesView(WebRowSet ws , String strStartDate , String strEndDate , IpdBillManagementTransVO vo ) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try{
			
			int count = 0;
			
			if(ws != null && ws.size() >0){
				
				count = vo.getNCompChargeCount();
				
				int qty = 0;
				int qty1 = dateDifference(strStartDate, strEndDate);
				 qty = qty1 + 1;
				
				
				
				sb.append(" <table class='TABLEWIDTH' align='center' cellspacing ='1px'> ");
				sb.append(" <tr> ");
				sb.append("  <td class='multiLabel' width='5%'><input type='checkbox' checked='checked' name='strCompChargeMainCheck' id='strCompChargeMainCheck' onclick='checkAllCompCharges(this, \"strSpecialChargeCheck\" , \""+count+"\");'></td> ");
				sb.append("  <td class='multiLabel' width='10%'>Date</td> ");
				sb.append("  <td class='multiLabel' width='25%'>Tariff Name</td> ");
				sb.append("  <td class='multiLabel' width='10%'>Rate/Unit</td> ");
				sb.append(" <td class='multiLabel' width='10%'>Qty.</td> ");
				sb.append("  <td class='multiLabel' width='10%'>Unit</td> ");
			    sb.append("  <td class='multiLabel' width='10%'>Ser. Tax(%)</td> ");
				sb.append("  <td class='multiLabel' width='10%'>Total Amt.(Rs)</td> ");
				sb.append("  </tr> ");
				
				while(ws.next()){
				
					String strId = ws.getString(1);
					String strTariffName = ws.getString(2);
					
					
					if(strTariffName.toUpperCase().contains("ADMISSION CHARGE")){
						
						qty = 1;
						
					}else{
						
						qty = qty1 + 1;
						
					}
					
								
					String strTariffDtls[] = strId.replace("^", "#").split("#");
					
		//			String strTariffId = strTariffDtls[0];
		//			String strGrpId  = strTariffDtls[1];
		//			String strProcedureCost  = strTariffDtls[2];
					String strTariffCost  = strTariffDtls[3];
					String strCost  = strTariffDtls[4];
					String strUnitId  = strTariffDtls[5];
					String strUnitBaseVal  = strTariffDtls[6];
		//			String strIsPackage  = strTariffDtls[7];
		//			String strIsAdvance  = strTariffDtls[8];
		//			String strIsRefund  = strTariffDtls[9];
		//			String strDefaultRate  = strTariffDtls[10];
					String strTariffActualCost  = strTariffDtls[11];
		//			String strDiscount   = strTariffDtls[12];
					String strServiceTax  = strTariffDtls[13];
					String strUnitName  = strTariffDtls[14];
		//			String strServiceId  = strTariffDtls[15];
		//			String strGTariffId  = strTariffDtls[16];
					
					
					float strTariffCostValue = 	qty * ( Float.parseFloat(strTariffCost) / Float.parseFloat(strUnitBaseVal) );  
					
					float strActualTariffCostValue = qty * ( Float.parseFloat(strTariffActualCost) / Float.parseFloat(strUnitBaseVal) );
					
					float strServiceTaxAmt = strTariffCostValue * (Float.parseFloat(strServiceTax) / 100 );
					
					float strTariffNetCostValue = strTariffCostValue + strServiceTaxAmt;
					
					
			    sb.append("  <tr> ");
				sb.append(" <td class='multiControl' width='5%'><input type='checkbox' checked='checked' name='strSpecialChargeCheck' id='strSpecialChargeCheck"+count+"' onclick='manageCompalsaryChargesTariff(this , \""+count+"\");'> </td> ");
				sb.append(" <td class='multiControl' width='10%'>"+strStartDate+"</td> ");
				sb.append(" <td class='CONTROL' width='35%'>&nbsp;<input type='hidden' name='strOfflineTariffName' id='strOfflineTariffName"+count+"' value='"+strTariffName+"' > <input type='hidden' name='strOfflineTariffDetailsHidden' id='strOfflineTariffDetailsHidden"+count+"' value='"+strId+"^2' >"+strTariffName+"</td> ");
				sb.append(" <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffRateUnit' id='strOfflineTariffRateUnit"+count+"' value='"+strCost+"' > "+strCost+"/"+strUnitName+" </td> ");
			 	sb.append(" <td class='multiControl' width='10%'><input type='text' name='strOfflineTariffQty' id='strOfflineTariffQty"+count+"' value='"+qty+"' class='txtFldMin' maxlength='8' onkeyup='checkQtyValue(\""+count+"\");' ></td> ");
				sb.append(" <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffUnit' id='strOfflineTariffUnit"+count+"' value='"+strUnitId+"'>"+strUnitName+"</td> ");
				sb.append("  <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffServiceTax' id='strOfflineTariffServiceTax"+count+"' value='"+strServiceTax+"'><input type='hidden' name='strOfflineTariffServiceTaxAmtVal' id='strOfflineTariffServiceTaxAmtVal"+count+"' value='"+strServiceTaxAmt+"'><input type='hidden' name='strOfflineActualTariffAmtVal' id='strOfflineActualTariffAmtVal"+count+"' value='"+strActualTariffCostValue+"'>"+strServiceTaxAmt+"</td> ");
				sb.append(" <td class='multiControl' style='font-weight: bold'  width='10%'><input type='hidden' name='strOfflineTariffNetAmount' id='strOfflineTariffNetAmount"+count+"' value='"+strTariffNetCostValue+"'><div id='strOfflineTariffNetAmountDivId"+count+"'>"+strTariffNetCostValue+"</div></td> ");
				sb.append(" </tr> ");
				
				count = count + 1;
				
				}
				
				sb.append("  </table> ");
				
				
				
			}else{
				

				sb
				.append("<table class='TABLEWIDTH' align='center'   bgcolor='#000000' border='0' cellpadding='1px' cellspacing ='1px'>");
		sb.append("<tr>");
		sb
				.append("<td colspan='8'  CLASS='multiControl' >"
						+ "<div class='errMsg' align='center'> No Record Available </div>"
						+ "</TD>");

		sb.append("</tr>");
		sb.append("</table>");
				
				
				
			}
			
	
			
		}catch(Exception e){
			
			throw e;
		}
		
	
		return sb.toString();
		
	}
	
	
	
	private static int dateDifference(String strStartDate , String strEndDate) throws Exception{
		
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime( sdf.parse(strStartDate) );
		cal2.setTime( sdf.parse(strEndDate) );
		
			long milliSec1 = cal1.getTimeInMillis();
			long milliSec2 = cal2.getTimeInMillis();
			
			long diff = milliSec2 - milliSec1;
			
			long diffDays = diff / (24*60*60*1000);	
		
			return (int) diffDays;
		
		}catch(Exception e){
			throw e;
		}
	}
	
	
	public static String getPreviousDtlsView(WebRowSet ws ) throws Exception 
	{
		StringBuffer sb = new StringBuffer("");
		try
		{			
				int count = 0;			  
				sb.append("<table width='600' align='center' cellpadding='1px' cellspacing ='1px'> ");
				sb.append("<tr> ");
				sb.append("<td class='multiLabel' width='5%'>#</td> ");
				sb.append("<td class='multiLabel' width='20%'>Ward </td> ");
				sb.append("<td class='multiLabel' width='45%'>Tariff Name</td> ");
				sb.append("<td class='multiLabel' width='15%'>Qty.</td> ");
				sb.append("<td class='multiLabel' width='25%'>Date</td> ");
				sb.append("<td class='multiLabel' width='15%'>Rate</td> ");
				sb.append("<td class='multiLabel' width='15%'>Amt.(Rs)</td> ");
				sb.append("</tr> ");
				
				
				if(ws != null && ws.size() > 0)
				{				
					while(ws.next())
					{
						sb.append("<tr> ");						
						if(ws.getString(7).equals("0"))
						{							
							sb.append("<td class='multiControl' width='5%'><input type='checkbox' onchange='deleteButtonVisibility(this);' name='strPreviousCheck' id='strPreviousCheck"+count+"' value='"+ws.getString(1)+"##"+ws.getString(10)+"##"+ws.getString(8)+"' ></td> ");
						}
						else
						{							
							sb.append("<td class='multiControl' width='5%'><img onkeypress='onPressingEnter(this,event)' style='cursor: pointer;' onmouseover='Tip(\""+"Online Tariff"+"\",SHADOW,true)' onmouseout='UnTip()' src='../../hisglobal/images/info.png' align='middle'></td> ");
						}										
						sb.append("<td class='multiControl' width='15%'>"+ws.getString(3)+"</td> ");
						sb.append("<td class='CONTROL' width='50%'>&nbsp;"+ws.getString(4)+"</td> ");
						sb.append("<td class='multiControl' width='15%'  id='txtbox"+count+"'>"+ws.getString(5)+"</td> ");
						sb.append("<td class='multiControl' width='35%' id='txtbox1"+count+"'>"+ws.getString(9)+"</td> ");
						sb.append("<td class='multiControl' width='15%' id='txtbox2"+count+"'>"+ws.getString(8)+"</td> ");
						sb.append("<td class='multiControl' width='15%'>"+HisUtil.getAmountWithDecimal(ws.getString(6), 2)+"</td> ");
						sb.append("</tr> ");						
						
					count = count + 1;				
					}				
					sb.append("</table> ");				
				}
				else
				{
					sb.append("<tr>");
					sb.append("<td colspan='5'  CLASS='multiControl' >"+ "<div class='errMsg' align='center'> No Record Available </div>"+ "</TD>");
					sb.append("</tr>");
					sb.append("</table>");
				}						
		}
		catch(Exception e)
		{			
			throw e;
		}	
		return sb.toString();		
	}
	
public static String getAllTrfListJSON(IpdBillManagementTransVO voObj)
{
	StringBuffer data = new StringBuffer("");
	StringBuffer sb = new StringBuffer("");	
	WebRowSet ws = voObj.getAllTrfListWS();
	int sl=0;
	int index=0;
	sb.append("<div  style='overflow-x:auto;'>");
	sb.append("<table class='tableBodyScroll' id='TABLEALLTARIFFHLP'> ");
	sb.append("<thead > ");
	sb.append("<tr> ");
	sb.append("<th style='width:3%;'>#</th> ");
	sb.append("<th style='width:18%;'>Group Name</th> ");
	sb.append("<th style='width:5%;display:none;'>E/N</th> ");		
	sb.append("<th style='width:6%;'>Chg Code</th> ");
	sb.append("<th style='width:18%;'>Tariff Name</th> ");
	sb.append("<th style='width:5%;'>Chg Qty.</th> ");
	sb.append("<th style='width:5%;'>Rate(Rs.)</th> ");
	sb.append("<th style='width:5%;'>Disc./Unit</th> ");		
	sb.append("<th style='width:6%;'>Disc.Type</th> ");
	sb.append("<th style='width:6%;'>Disc. Amt.</th> ");
	sb.append("<th style='width:8%;'>Chg Amt.(Rs)</th> ");
	sb.append("<th style='width:9%;'>Date</th> ");
	sb.append("<th style='width:5%;'>Type</th> ");
	sb.append("<th style='width:3%;'>#</th> ");
	sb.append("</tr> ");
	sb.append("</thead> ");
	sb.append("<body> ");
	//sb.append("<div class='scroll-table'>");
	//sb.append("<table class='table' > ");
	try{
	if(ws != null && ws.size() > 0)
	{	
		

		while(ws.next())
		{
			//sb.append("<tr> ");
				int idx=index++;
				/*if(ws.size()==1)
					data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'}");
				else
				{
					if(ws.isLast())
						data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'}");
					else
						data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'},");
				}*/
				String date[]=ws.getString(7).replace("-","#").split("#");
				String trId="TRALLTARIFFHLP"+idx;
				sb.append("<tr id='"+trId+"'> ");
				sb.append("<td style='width:3%;'><input type='text'  tabindex='1' name='slNo'      id='slNo"+idx+"'  	   onkeypress='moveUpDown(event,this,"+idx+");'  onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ ++sl +"' readonly></td> ");
				sb.append("<td style='width:17%;'><input type='text' tabindex='1' name='groupName' id='groupName"+idx+"' onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(1)+"' readonly></td> ");
				if(ws.getString(15).equalsIgnoreCase("1"))
				sb.append("<td style='width:5%;display:none;'><select name='strPriority'   tabindex='1' id='strPriority"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' onchange='calcOffLineTariffNetAmountNew(this,"+idx+");'><option value='1' selected>N</option><option value='2'>E</option></select></td> ");			
				else
				sb.append("<td style='width:5%;display:none;'><select name='strPriority'   tabindex='1' id='strPriority"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' onchange='calcOffLineTariffNetAmountNew(this,"+idx+");'><option value='1'>N</option><option value='2' selected>E</option></select></td> ");
				//System.out.println("ws.getString(12)"+ws.getString(12));
				if(ws.getString(12).equalsIgnoreCase("1")) //If Online Tariff Dont Allow Edit Tariff Code
				{	//System.out.println("id============");
					sb.append("<td style='width:6%;'><input type='text' tabindex='1' name='trfCode'   id='trfCode"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(2)+"' readonly></td> ");
				}else{ //If Offline Tariff Allow Edit Tariff Code
					//System.out.println("else==========");
					sb.append("<td style='width:6%;'><input type='text' tabindex='1' name='trfCode'   id='trfCode"+idx+"'  onchange='setCurrentRowTrfCode(this,"+idx+")' onkeypress=' return validateData(event,8);' onkeyup=\"getTariffByCodeNew(this , event,"+idx+");moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(2)+"'>");
				}
				sb.append("<label name='trfCodeLabel'   id='trfCodeLabel"+idx+"' style='display:none;'>"+ws.getString(2)+"</label></td> ");
				if(ws.getString(12).equalsIgnoreCase("1")) //If Online Tariff Dont Allow Edit Tariff Code
				sb.append("<td style='width:18%;'><input type='text' tabindex='1' name='strOfflineTariffName'   id='strOfflineTariffName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(3)+"' readonly></td> ");
				else
				sb.append("<td style='width:18%;'><input type='text' tabindex='1' name='strOfflineTariffName'   id='strOfflineTariffName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(3)+"' ></td> ");	
				sb.append("<td style='width:5%;'><input type='text' tabindex='1' name='strOfflineTariffQty' 	id='strOfflineTariffQty"+idx+"' maxlength='3' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(5)+"' autocomplete='off'></td> ");
				
				if(ws.getString(16).equalsIgnoreCase("1"))
				sb.append("<td style='width:5%;'><input type='text' tabindex='1' name='strOfflineTariffRateUnit'  maxlength='7' id='strOfflineTariffRateUnit"+idx+"' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(4)+"' autocomplete='off'></td> ");
				else
				sb.append("<td style='width:5%;'><input type='text' tabindex='1' name='strOfflineTariffRateUnit'  maxlength='7' id='strOfflineTariffRateUnit"+idx+"' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(4)+"' autocomplete='off' readonly></td> ");	
				
				sb.append("<td style='width:5%;'><input type='text' tabindex='1' name='strDiscount' id='strDiscount"+idx+"' maxlength='5' class='form-form-control' value='"+ws.getString(9)+"' onkeypress='return validateData(event,7);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');onEntDiscount("+idx+");\" onblur=\"calActualDisAmt("+idx+");\" onkeydown=\"checkFirstKey(event)\" autocomplete='off'/></td>");
				sb.append("<td style='width:5%;'><select tabindex='1' name='strDiscountType' id='strDiscountType"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" onchange='onEntDiscount("+idx+");' value='"+ws.getString(11)+"' class='form-form-control'><option value='1'>Fix</option><option value='2'>%</option></select></td>");
				sb.append("<td style='width:7%;'><input type='text' tabindex='1' name='strDiscountAmt' id='strDiscountAmt"+idx+"' value='"+ws.getString(10)+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' readonly/></td>");
					
				sb.append("<td style='width:7%;'><input type='text' tabindex='1' name='strOfflineTariffNetAmount' id='strOfflineTariffNetAmount"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(6)+"' readonly></td> ");
				sb.append("<td style='width:8%;'>");
				sb.append("<input type='text' name='trfDateDD' maxlength='2'  tabindex='1' id='trfDateDD"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+date[0]+"-"+date[1]+"-"+date[2]+"' autocomplete='off'>");
				/*sb.append("<input type='text' name='trfDateMM' maxlength='2'  tabindex='1' id='trfDateMM"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+date[1]+"' autocomplete='off'>-");
				sb.append("<input type='text' name='trfDateYYYY' maxlength='4' tabindex='1' id='trfDateYYYY"+idx+"' onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+date[2]+"' autocomplete='off'></td>");
				*/sb.append("<td style='width:6%;'><input type='text'  tabindex='1' name='servType'      id='servType"+idx+"'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(13)+"' readonly></td>");
				sb.append("<td style='width:3%;'><img style='cursor: hand; cursor: pointer' tabindex='1' src='../../hisglobal/images/minus.gif' name='tariffMinus' onclick=\"deleteRowClick(this,"+idx+",'"+trId+"')\">");
				sb.append("<input type='hidden' name='deleteFlag' value='0' id='deleteFlag"+idx+"'>");//Deleted Flag=2 Means-New Row,1 Means -Deleted Row,0-Means No Change, 3 Means- Modify Data
				sb.append("<input type='hidden' name='strOfflineTariffDetailsHidden' value='"+ws.getString(8)+"' id='strOfflineTariffDetailsHidden"+idx+"'>");
				sb.append("<input type='hidden' name='strOfflineTempTariffRateUnit' value='"+ws.getString(4)+"' id='strOfflineTempTariffRateUnit"+idx+"'>");			
				sb.append("<input type='hidden' name='strOfflineTariffUnit' value='"+BillConfigUtil.DEFAULT_UNIT_ID_EACH+"' id='strOfflineTariffUnit"+idx+"'>");
				sb.append("<input type='hidden' name='strOfflineTariffDate' value='' id='strOfflineTariffDate"+idx+"'>");
				sb.append("<input type='hidden' name='strOfflineTariffServiceTax' value='0' id='strOfflineTariffServiceTax"+idx+"'>");
				sb.append("<input type='hidden' name='strOfflineTariffServiceTaxAmtVal' value='0' id='strOfflineTariffServiceTaxAmtVal"+idx+"'>");
				sb.append("<input type='hidden' name='strOfflineActualTariffAmtVal' value='"+ws.getString(4)+"' id='strOfflineActualTariffAmtVal"+idx+"'>");
				sb.append("<input type='hidden' name='strServTypeId' value='"+ws.getString(12)+"' id='strServTypeId"+idx+"'>");
				sb.append("<input type='hidden' name='strPrevReqNo' value='"+ws.getString(14)+"' id='strPrevReqNo"+idx+"'>");
				sb.append("<input type='hidden' name='strOrigActAmt' value='"+ws.getString(6)+"' id='strOrigActAmt"+idx+"'>");
				sb.append("<input type='hidden' name='strDisActAmt' value='"+ws.getString(10)+"' id='strDisActAmt"+idx+"'>");
				sb.append("<input type='hidden' name='strDisActAmtTmp' value='"+ws.getString(10)+"' id='strDisActAmtTmp"+idx+"'>");
				sb.append("</td></tr>");
				
							
			}
				
	}
	sb.append("</tbody>");
	sb.append("</table>");		
	sb.append("</div>");		

	//sb.append("</div>");
	voObj.setStrAllTrfHLP(sb.toString());
		voObj.setStrNumRows(Integer.toString(index));
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return data.toString();
}


public static String getTrfDtlsByCode(IpdBillManagementTransVO voObj) throws Exception 
{
	StringBuffer sb = new StringBuffer("");	
	WebRowSet ws =voObj.getOfflineTariffList();
	int index;
	//System.out.println("Integer.parseInt(voObj.getStrNumRows())"+Integer.parseInt(voObj.getStrNumRows()));
	//System.out.println("Integer.parseInt(voObj.getStrCurrindex())"+Integer.parseInt(voObj.getStrCurrindex()));
	if(voObj.getStrCurrindex().equals("-1"))
		index=Integer.parseInt(voObj.getStrNumRows());
	else
		index=Integer.parseInt(voObj.getStrCurrindex());
	//System.out.println("index"+index);
	int sl=index;
	HisUtil util=new HisUtil("Billing", "IPDBillManagamentTransHLP");
	if(ws != null && ws.size() > 0)
	{	
		while(ws.next())
		{
			int idx;
			if(voObj.getStrCurrindex().equals("-1"))
				idx=index++;
			else
				idx=index;
			//System.out.println("idx"+idx);
			//System.out.println("ws 1"+ws.getString(1));
			//System.out.println("ws 2"+ws.getString(2));
			String strTrfCode;
			String strTrfNameWithCode[];
		    String strTrfName;
			String ws1=ws.getString(1)+"^1";  // Appended to match the length of serial no. of patient account service used for deletion
			String ws1Arr[]=ws1.replace("^","#").split("#");
			if(!ws1Arr[1].equals("107"))
			{
				strTrfNameWithCode=ws.getString(2).replace("-","#").split("#");
				strTrfName=strTrfNameWithCode[1];
			}
			else
			{
				strTrfNameWithCode=ws.getString(2).replace("[","#").split("#");
			    strTrfName=strTrfNameWithCode[0];
			}
			String strGroupName=ws1Arr[19];
			String strUndefined=ws1Arr[17];
			if(ws1Arr[1].equals("107"))
			    strTrfCode=ws1Arr[18];
			else
				strTrfCode=strTrfNameWithCode[0].replace("(","").replace(")", "").trim() ;
			String strRate=ws1Arr[2];
			String netAmt=HisUtil.getAmountWithDecimal(Double.parseDouble(strRate)*1, 2);
			
			String dt=util.getASDate("");
			String[] date=dt.replace("/","#").split("#");
			System.out.println("strTrfCode======"+strTrfCode);
			System.out.println("strTrfName======"+strTrfName);
			//1240003^124^20.00^20.00^20.00^1701^1^0^0^1^20.00^20.00^0^0^No.^1^10003^0^0^Biochemistry
			//TRFCODE^GRPCODE^RATE^RATE^RATE^UNIT^UNITBASEVAL^ISPKG^HBLNUM_IS_ADVANCE||'^'|| HBLNUM_IS_REFUNDABLE^DEFAULTCOST^ACTCOST^DISCAMT^SERVTAX^UNITNAME^SERVICEID^GSTRTRF^UNDEFCHARGE^CONSMCHARGE^GRPNAME
						
			String trId="TRALLTARIFFHLP"+idx;
			/*sb.append("<tr id='"+trId+"'> ");
			sb.append("<td class='trfTd' width='2%'><input type='text'  tabindex='1' name='slNo'      id='slNo"+idx+"'      onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ ++sl +"' readonly></td> ");
			sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='groupName' id='groupName"+idx+"' onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+strGroupName+"' readonly></td> ");
			sb.append("<td class='trfTd' width='3%'><select name='en'   tabindex='1' id='en"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts'><option value='1'>N</option><option value='2'>E</option></select></td> ");			
			sb.append("<td class='trfTd' width='13%'><input type='text' tabindex='1' name='trfCode'   id='trfCode"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+strTrfCode+"' readonly></td> ");
			sb.append("<td class='trfTd' width='25%'><input type='text' tabindex='1' name='trfName'   id='trfName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+strTrfName+"' readonly></td> ");
			sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='qty' 		 id='qty"+idx+"' 	   onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='1'></td> ");
			sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='rate'       id='rate"+idx+"' 	   onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+strRate+"'></td> ");			
			sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='netAmt'     id='netAmt"+idx+"'    onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+netAmt+"' readonly></td> ");
			sb.append("<td class='trfTd' width='10%'>");
			sb.append("<input type='text' name='trfDateDD' maxlength='2'  tabindex='1' id='trfDateDD"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-textsmall' value='"+date[0]+"'>-");
			sb.append("<input type='text' name='trfDateMM' maxlength='2'  tabindex='1' id='trfDateMM"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-textsmall' value='"+date[1]+"'>-");
			sb.append("<input type='text' name='trfDateYYYY' maxlength='4' tabindex='1' id='trfDateYYYY"+idx+"' onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-textsmallYr' value='"+date[2]+"'></td>");
			sb.append("<td class='trfTd' width='5%'><img style='cursor: hand; cursor: pointer' tabindex='1' src='../../hisglobal/images/minus.gif' name='tariffMinus' onclick=\"deleteRowClick(this,"+idx+",'"+trId+"')\">");
			sb.append("<input type='hidden' name='deleteFlag' value='0' id='deleteFlag"+idx+"'></td> ");
			sb.append("</tr>");
			
			*/
			
			sb.append("<tr id='"+trId+"' > ");
			sb.append("<td style='width:3%;'><input type='text'  tabindex='1' name='slNo'      id='slNo"+idx+"'  	   onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ ++sl +"' readonly></td> ");
			sb.append("<td style='width:17%;'><input type='text' tabindex='1' name='groupName' id='groupName"+idx+"' onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+strGroupName+"' readonly></td> ");
			sb.append("<td style='display:none;'><select name='strPriority'   tabindex='1' id='strPriority"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' onchange='calcOffLineTariffNetAmountNew(this,"+idx+");'><option value='1'>N</option><option value='2'>E</option></select></td> ");			
			sb.append("<td style='width:6%;'><input type='text' tabindex='1' name='trfCode'   id='trfCode"+idx+"'   onkeypress='return validateData(event,8);' onkeyup=\"getTariffByCodeNew(this , event,"+idx+");moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+strTrfCode+"' readonly></td> ");
			if(strUndefined.equals("1"))
				sb.append("<td style=\"width:18%;\"><input type='text' tabindex='1' name='strOfflineTariffName'   id='strOfflineTariffName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+strTrfName+"'></td> ");
			else
				sb.append("<td style=\"width:18%;\"><input type='text' tabindex='1' name='strOfflineTariffName'   id='strOfflineTariffName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+strTrfName+"' readonly></td> ");
			sb.append("<td style=\"width:5%;\"><input type='text' tabindex='1' name='strOfflineTariffQty' 	id='strOfflineTariffQty"+idx+"' maxlength='3' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='1' autocomplete='off'></td> ");
			if(strUndefined.equals("1"))
			sb.append("<td style='width:5%;'><input type='text' tabindex='1' name='strOfflineTariffRateUnit'  maxlength='7' id='strOfflineTariffRateUnit"+idx+"' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+strRate+"' autocomplete='off' ></td> ");
			else
				sb.append("<td style='width:5%'><input type='text' tabindex='1' name='strOfflineTariffRateUnit'  maxlength='7' id='strOfflineTariffRateUnit"+idx+"' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+strRate+"' autocomplete='off' readonly></td> ");	
			sb.append("<td style=\"width:5%;\"><input type='text' tabindex='1' name='strDiscount' id='strDiscount"+idx+"' maxlength='5' class='form-form-control' value='0.00' onkeypress='return validateData(event,7);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');onEntDiscount("+idx+");\" onblur=\"calActualDisAmt("+idx+");\" onkeydown=\"checkFirstKey(event)\" autocomplete='off'/></td>");
			sb.append("<td style=\"width:5%;\"><select tabindex='1' name='strDiscountType' id='strDiscountType"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" onchange='onEntDiscount("+idx+");' value='1' class='form-form-control'><option value='1'>Fix</option><option value='2'>%</option></select></td>");
			sb.append("<td style=\"width:7%;\"><input type='text' tabindex='1' name='strDiscountAmt' id='strDiscountAmt"+idx+"' value='0.00' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' readonly/></td>");
				
			sb.append("<td style=\"width:7%;\"><input type='text' tabindex='1' name='strOfflineTariffNetAmount' id='strOfflineTariffNetAmount"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+netAmt+"' readonly></td> ");
			sb.append("<td style=\"width:8%;\">");
			sb.append("<input type='text' name='trfDateDD' maxlength='2'  tabindex='1' id='trfDateDD"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+date[0]+"-"+date[1]+"-"+date[2]+"' autocomplete='off'>");
			/*sb.append("<input type='text' name='trfDateMM' maxlength='2'  tabindex='1' id='trfDateMM"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+date[1]+"' autocomplete='off'>-");
			sb.append("<input type='text' name='trfDateYYYY' maxlength='4' tabindex='1' id='trfDateYYYY"+idx+"' onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+date[2]+"' autocomplete='off'></td>");
			*/sb.append("<td style=\"width:6%;\"><input type='text'  tabindex='1' name='servType'      id='servType"+idx+"'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='Offline' readonly></td>");
			sb.append("<td style=\"width:3%;\"><img style='cursor: hand; cursor: pointer' tabindex='1' src='../../hisglobal/images/minus.gif' name='tariffMinus' onclick=\"deleteRowClick(this,"+idx+",'"+trId+"')\">");
			sb.append("<input type='hidden' name='deleteFlag' value='2' id='deleteFlag"+idx+"'>");//Deleted Flag=2 Means-New Row,1 Means -Deleted Row,0-Means No Change, 3 Means- Modify Data
			sb.append("<input type='hidden' name='strOfflineTariffDetailsHidden' value='"+ws1+"' id='strOfflineTariffDetailsHidden"+idx+"'>");
			sb.append("<input type='hidden' name='strOfflineTempTariffRateUnit' value='"+strRate+"' id='strOfflineTempTariffRateUnit"+idx+"'>");			
			sb.append("<input type='hidden' name='strOfflineTariffUnit' value='"+BillConfigUtil.DEFAULT_UNIT_ID_EACH+"' id='strOfflineTariffUnit"+idx+"'>");
			sb.append("<input type='hidden' name='strOfflineTariffDate' value='' id='strOfflineTariffDate"+idx+"'>");
			sb.append("<input type='hidden' name='strOfflineTariffServiceTax' value='0' id='strOfflineTariffServiceTax"+idx+"'>");
			sb.append("<input type='hidden' name='strOfflineTariffServiceTaxAmtVal' value='0' id='strOfflineTariffServiceTaxAmtVal"+idx+"'>");
			sb.append("<input type='hidden' name='strOfflineActualTariffAmtVal' value='"+strRate+"' id='strOfflineActualTariffAmtVal"+idx+"'>");
			sb.append("<input type='hidden' name='strServTypeId' value='0' id='strServTypeId"+idx+"'>");
			sb.append("<input type='hidden' name='strPrevReqNo' value='0' id='strPrevReqNo"+idx+"'>");
			sb.append("<input type='hidden' name='strOrigActAmt' value='"+strRate+"' id='strOrigActAmt"+idx+"'>");
			sb.append("<input type='hidden' name='strDisActAmt' value='0' id='strDisActAmt"+idx+"'>");
			sb.append("<input type='hidden' name='strDisActAmtTmp' value='0' id='strDisActAmtTmp"+idx+"'>");
			sb.append("</td></tr>");
			
			
			//voObj.setStrAllTrfHLP(sb.toString());			
		}
		
	}
	else
	{
		sb.append("0");
	}
		
		return sb.toString();
	}

	public static String getPrevBedTransfer(IpdBillManagementTransVO voObj) throws Exception 
	{
		StringBuffer sb = new StringBuffer("");	
		WebRowSet ws = voObj.getPrevBedTrf();
		int sl=0;
		int index=0;
		
		if(ws != null && ws.size() > 0)
		{	
			sb.append("<div style='overflow-x:auto;'>");
			sb.append("<table class='table'> ");
			sb.append("<thead> ");
			sb.append("<tr> ");
			sb.append("<th style='text-align:left'>Mov No</th> ");
			sb.append("<th style='text-align:left'>Department</th> ");
			sb.append("<th style='text-align:left'>Unit</th> ");		
			sb.append("<th style='text-align:left'>Ward</th> ");
			sb.append("<th style='text-align:left'>Consultant</th> ");
			sb.append("<th style='text-align:left'>In Date</th> ");
			sb.append("<th style='text-align:left'>Out date</th> ");		
			sb.append("<th style='text-align:left'>Type</th> ");
			sb.append("</tr> ");
			sb.append("</thead> ");
			sb.append("<tbody id='TABLEBEDTRANSFERHLP'> ");
			//sb.append("<div>");
			//sb.append("<table width='95%' align='center' cellpadding='0' cellspacing ='0' > ");
			
			while(ws.next())
			{
					int idx=index++;
								
					String trId="TRBEDTRANSFERHLP"+idx;
					sb.append("<tr> ");
					sb.append("<td><input type='text'  tabindex='1' name='strMovNo'   id='strMovNo"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ ++sl +"' readonly></td> ");
					sb.append("<td><input type='text' tabindex='1' name='deptName'   id='deptName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(3)+"' readonly></td> ");
					sb.append("<td><input type='text' tabindex='1' name='unitName'   id='unitName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(5)+"' readonly></td>  ");			
					sb.append("<td><input type='text' tabindex='1' name='wardName'   id='wardName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(7)+"' readonly></td> ");
					//sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='roomName'   id='roomName"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(9)+"' readonly></td> ");
					//sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='bedName'    id='bedName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(11)+"' readonly></td> ");
					sb.append("<td><input type='text' tabindex='1' name='docName'    id='docName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(18)+"' readonly></td> ");
					sb.append("<td><input type='text' tabindex='1' name='strInDate'  id='strInDate"+idx+"' onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(12)+"' readonly></td> ");
					sb.append("<td><input type='text' tabindex='1' name='strOutDate' id='strOutDate"+idx+"' onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(13)+"' readonly></td>");
					sb.append("<td><input type='text'  tabindex='1' name='trfType'    id='trfType"+idx+"' 	 onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow1(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='form-form-control' value='"+ws.getString(14)+"' readonly/>");
					sb.append("<input type='hidden' name='strTransferDeptCode' id='strTransferDeptCode"+idx+"' value='"+ws.getString(2)+"'>");
					sb.append("<input type='hidden' name='strTransferWardCode' id='strTransferWardCode"+idx+"' value='"+ws.getString(6)+"'>");
					sb.append("<input type='hidden' name='strTransferChargeType' id='strTransferChargeType"+idx+"' value='"+ws.getString(17)+"'>");
					if(IpdConfigUtil.AUTO_BED_CHARGES_JOB.equals("OFF"))
					{
						//if(ws.getString(1).equals("1") || ws.getString(13).equals(""))//If Mov No =1 or Out Date Null Then Save Corresponding Data into Bed Transfer
							sb.append("<input type='hidden' id='strSaveFlag"+idx+"' name='strSaveFlag' value='1'>");
						//else
							//sb.append("<input type='hidden' id='strSaveFlag"+idx+"' name='strSaveFlag' value='0'>");
					}
					else
						sb.append("<input type='hidden' id='strSaveFlag"+idx+"' name='strSaveFlag' value='0'>");
					
					sb.append("<input type='hidden' id='deleteFlag"+idx+"' name='deleteFlag' value='0'>");
					sb.append("<input type='hidden' id='adtOnlineFlag"+idx+"' name='adtOnlineFlag' value='1'>");//DATA FETECHED FROM ADT MODULE
					sb.append("<input type='hidden' id='strIsDoubleOc"+idx+"' name='strIsDoubleOc' value='"+ws.getString(19)+"'>");
					sb.append("<input type='hidden' id='strConsultant"+idx+"' name='strConsultant' value='"+ws.getString(20)+"'>");
					sb.append("<input type='hidden' id='strDblOccDate"+idx+"' name='strDblOccDate' value='"+ws.getString(13)+"'>");
					sb.append("<input type='hidden' id='strUnitCode"+idx+"' name='strUnitCode' value='"+ws.getString(4)+"'>");
					sb.append("<input type='hidden' id='strNewBabyBed"+idx+"' name='strNewBabyBed' value='"+ws.getString(21)+"'>");
					sb.append("<input type='hidden'  id='BlkTime"+idx+"' value='"+ws.getString(22)+"'>");
					sb.append("<input type='hidden'  id='outFlg"+idx+"' value='"+ws.getString(23)+"'>");
					
					voObj.setStrLastStratDate(ws.getString(15));
					voObj.setStrLastEndDate(ws.getString(16));
					voObj.setStrLastDept(ws.getString(2));
					voObj.setStrLastwardTypr(ws.getString(6));
					voObj.setStrLastchargetype(ws.getString(17));
					if(ws.getString(13).equalsIgnoreCase(""))
					{
						voObj.setStrchkvalue("1");
						
						
					}
					sb.append("</td></tr>");
					
								
				}
			        sb.append("</tbody>");	
					sb.append("</table>");		
				    sb.append("</div>");
			
		}
		return sb.toString();
	}

	public static String admissionList(IpdBillManagementTransVO vo)
	 {
	      StringBuffer sBuffer = new StringBuffer();
	      WebRowSet ws=vo.getAdmissionListWS();
	      
	      try
	      {       
	    	      int count = 0;	
	    	      sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>"); 
	              sBuffer.append("<tr><td  width='100%' colspan='5' class='TITLE'><div align='left'>Patient Admission List</div></td></tr>");
	              sBuffer.append("<tr>" );
	              sBuffer.append("<td  width='5%' class='LABEL'><div align='center'>#</div></td>");
	              sBuffer.append("<td  width='20%' class='LABEL'><div align='center'>Admission No.</div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>Admission Date/Time</div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>Discharge Date/Time</div></td>");
	              sBuffer.append("<td  width='25%' class='LABEL'><div align='center'>No Dues No.</div></td>");
	              sBuffer.append("</tr></table>");	              
	              sBuffer.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");	              
	             
	              if(ws.size()>0)
	              {
		              while (ws.next()) 
		              {		            	 
		            	  sBuffer.append("<tr>");
		            	  //System.out.println("ws.getString(1)"+ws.getString(1));
		            	  /*if(vo.getStrAddmissionNo().equals(ws.getString(1)))
		            		  sBuffer.append("<td  width='5%' class='CONTROL'><div align='center'><input type='radio' checked name='chk' id='chk"+count+"' value='"+ws.getString(1)+'^'+ws.getString(2)+'^'+ws.getString(3)+'^'+ws.getString(4)+'^'+ws.getString(5)+'^'+ws.getString(6)+'^'+ws.getString(7)+'^'+ws.getString(8)+'^'+ws.getString(9)+'^'+ws.getString(9)+'^'+ws.getString(10)+"' onclick='getgoDetails(this);' tabindex='1'></div></td>");
		            	  else*/
		            		  sBuffer.append("<td  width='5%' class='CONTROL'><div align='center'><input type='radio' tabindex='1' name='chk' id='chk"+count+"' value='"+ws.getString(1)+'^'+ws.getString(2)+'^'+ws.getString(3)+'^'+ws.getString(4)+'^'+ws.getString(5)+'^'+ws.getString(6)+'^'+ws.getString(7)+'^'+ws.getString(8)+'^'+ws.getString(9)+'^'+ws.getString(10)+"' onclick='getgoDetails(this);'></div></td>");
		            	  
			              
			              sBuffer.append("<td  width='20%' class='CONTROL'><div align='center'>"+ws.getString(1)+"</div></td>");
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='center'>"+ws.getString(2)+"</div></td>");
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='center'>"+ws.getString(3)+"</div></td>");
			              sBuffer.append("<td  width='25%' class='CONTROL'><div align='center'>"+(ws.getString(10).equals("0")?"":ws.getString(10))+"</div></td>");
			              sBuffer.append("</tr>");
			              vo.setStrAddmissionNo(ws.getString(1));
			              vo.setStrAdmissionDate(ws.getString(2));
			              vo.setStrDischargeDate(ws.getString(3));
			              vo.setPrintFlag(ws.getString(4));
			              vo.setStrAccountNo(ws.getString(5));
			              vo.setStrCrNo(ws.getString(6));
			              vo.setStrTreatmentCategory(ws.getString(7));
			              vo.setStrDeptId(ws.getString(8));
			              vo.setStrWardCode(ws.getString(9));
			              vo.setStrBillNo(ws.getString(10));
			              count = count + 1;//System.out.println("ws.getString(5)"+vo.getStrAccountNo());
		              }
	              }
	              else
	              {
	            	  sBuffer.append("<tr>");
		              sBuffer.append("<td  width='25%' class='CONTROL'><div align='left'><font color='red'>No Record Found</font></div></td>");
		              sBuffer.append("</tr>");
		              vo.setPrintFlag("0");
	              }
	              sBuffer.append("</table>");	              
		     }
			 catch(Exception e)
			 {
				 new HisException("Billing","IpdBillManagementTransHLPNew.admissionList()-->",e.getMessage());
		     }
		     return sBuffer.toString();
	 }
	
	
	
	public static String getCompusloryChargesDtlListJSON(IpdBillManagementTransVO voObj)
	{
		StringBuffer data = new StringBuffer("");
		StringBuffer sb = new StringBuffer("");	
		//WebRowSet ws2 = voObj.getAllTrfListWS();
		WebRowSet ws = voObj.getStrTariffNameCombo();
		CompulsoryChargesByConsultantVO vo= new CompulsoryChargesByConsultantVO();
		int sl=0;
		int index=0;
		
		sb.append("<div align='center'><table width='95%' align='center' cellpadding='0' cellspacing ='0'> ");
		sb.append("<tr> ");
		/*sb.append("<td class='multiLabel' align='center' width='3%'>#</td> ");*/
		sb.append("<td class='multiLabel' align='center' width='25%'>Date</td> ");
		sb.append("<td class='multiLabel' align='center' width='30%'>Charge</td> ");
		sb.append("<td class='multiLabel' align='center' width='40%'>Category</td> ");
		sb.append("<td class='multiLabel' align='center' width='2%'></td> ");
		sb.append("</tr> ");
		sb.append("</table></div> ");
		sb.append("<div align='center'>");
		sb.append("<table width='95%' align='center' cellpadding='0' cellspacing ='0' id='TABLEALLTARIFFHLP'> ");
		try{
			
			String strTariffCmbo= "";
			
			
			
			/*if (ws2 != null) 
			{
				if(ws2.size() != 0)
				{
					//ws.next();
					if (ws2!= null && ws2.size() > 0) 
					{
						strTariffCmbo= new HisUtil("Billing", "HLPBilling").getOptionValue(ws2, "", "", false);
						//sb.append("<option value='0'>Select Value</option>");
						//sb.append("</select>");
						
					} 
					else 
					{
						strTariffCmbo="<option value='0'>Select Value</option>";
						//sb.append("</select>");
					}				
				} 
			
			}*/
			
		if(ws != null && ws.size() > 0)
		{	
			

			while(ws.next())
			{
				sb.append("<tr> ");
				
					/*if(ws.size()==1)
						data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'}");
					else
					{
						if(ws.isLast())
							data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'}");
						else
							data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'},");
					}*/
					//String date[]=ws.getString(7).replace("-","#").split("#");
					String cmb="";
					
					vo.setGrpid(ws.getString(2));
					vo.setStrCrNo(voObj.getStrCrNo());
					vo.setStrHospitalCode(voObj.getStrHospitalCode());
					CompulsoryChargesByConsultantBO compbo =new CompulsoryChargesByConsultantBO();
					compbo.getDefaultCompulsoryTariff(vo);
					
					String deftrf= vo.getStrDefaultCompTariffId();
					
					if(ws.getString(4)!=null && !ws.getString(4).equalsIgnoreCase("") && deftrf!=null)
					{
					
					int idx=index++;
					
					String Trfcmb[]= ws.getString(4).split(",");
					
					 cmb= "<select name='strStrTariffCode' id='strStrTariffCode"+idx+"' onchange='getTariff("+idx+");' style='width:225px;' >"; //<option value='0'>Select Value</option>
					//sb.append("</select>");
					if(Trfcmb.length>1)
					{
						for(int i=0;i<Trfcmb.length;i++)
						{
							String val[]=Trfcmb[i].split("@");
							{
								if(deftrf.equalsIgnoreCase(val[0]))
									cmb= cmb + "<option value='"+val[0]+"' selected>"+val[1]+"</option>";
								else
							cmb= cmb + "<option value='"+val[0]+"'>"+val[1]+"</option>";
							}
						}
					}
					else
					{
						String val[]=ws.getString(4).split("@");
						cmb= cmb + "<option value='"+val[0]+"' selected>"+val[1]+"</option>";
						
					}
					cmb=cmb+"</select>";
					
							/*else
					{
						 cmb="NO TARIFF MAPPED FOR CHARGE TYPE";
					}*/
					
					String trId="TRALLTARIFFHLP"+idx;
					sb.append("<tr id='"+trId+"'> ");
					/*sb.append("<td class='trfTd' width='2%'><div align='center'>"+idx+"</div></td> ");*/
					sb.append("<td class='trfTd' width='25%'><div align='center'>"+ws.getString(1)+"</div></td> ");
					
					sb.append("<input type='hidden' name='strGrpId' value='"+ws.getString(2)+"' id='strGrpId"+idx+"'>");
					
					sb.append("<td class='trfTd' width='30%'><div align='center'>"+ws.getString(3)+"</div></td> ");
					sb.append("<input type='hidden' name='strGroupName' value='"+ws.getString(3)+"' id='strGroupName"+idx+"'>");
					
					sb.append("<td class='trfTd' width='40%'><div align='center'>"+cmb+"</td> ");
					sb.append("<input type='hidden' name='strCompulsoryTariffCode' value='' id='strCompulsoryTariffCode"+idx+"'>"); 
					sb.append("<input type='hidden' name='strDefaultCompTariff' value='"+deftrf+"' id='strDefaultCompTariff"+idx+"'>");
					
					sb.append("<td class='trfTd' width='3%'><div align='center'></div>");
					sb.append("</td></tr>");
					}
								
				}
			
			sb.append("<input type='hidden' name='strNumRows' value='"+index+"'>");
					
		}
		sb.append("</table>");		
		sb.append("</div>");
		voObj.setStrAllTrfHLP(sb.toString());
			voObj.setStrNumRows(Integer.toString(index));
			vo.setStrNumRows(Integer.toString(index));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data.toString();
	}

	


	
	public static String getCompusloryChargesDtlListJSON1(IpdBillManagementTransVO voObj)
	{
		StringBuffer data = new StringBuffer("");
		StringBuffer sb = new StringBuffer("");	
		WebRowSet ws = voObj.getAllTrfListWS();
		WebRowSet ws2 = voObj.getStrTariffNameCombo();
		int sl=0;
		int index=0;
		
		sb.append("<table width='95%' align='center' cellpadding='0' cellspacing ='0'> ");
		sb.append("<tr> ");
		sb.append("<td class='multiLabel' align='left' width='2%'>#</td> ");
		sb.append("<td class='multiLabel' align='left' width='10%'>Date(DD-MM-YYYY)</td> ");
		sb.append("<td class='multiLabel' align='left' width='12%'>Group Name</td> ");
	  //sb.append("<td class='multiLabel' width='3%'>E/N</td> ");		
		sb.append("<td class='multiLabel' align='left' width='10%'>Compulsory Tariff</td> ");
		/*sb.append("<td class='multiLabel' width='18%'>Tariff Name</td> ");*/
		sb.append("<td class='multiLabel' align='left' width='10%'>Chg Qty.</td> ");
		sb.append("<td class='multiLabel' align='left' width='10%'>Rate(Rs.)</td> ");
		//sb.append("<td class='multiLabel' width='4%'>Disc./Unit</td> ");		
		//sb.append("<td class='multiLabel' width='4%'>Disc. Type</td> ");
		//sb.append("<td class='multiLabel' width='3%'>Disc. Amt.</td> ");
		sb.append("<td class='multiLabel' align='left' width='7%'>Chg Amt.(Rs)</td> ");
		//sb.append("<td class='multiLabel' width='10%'>Date(DD-MM-YYYY)</td> ");
		//sb.append("<td class='multiLabel' width='5%'>Type</td> ");
		sb.append("<td class='multiLabel' align='left' width='2%'>#</td> ");
		sb.append("</tr> ");
		sb.append("</table> ");
		sb.append("<div class='scroll-table'>");
		sb.append("<table width='95%' align='center' cellpadding='0' cellspacing ='0' id='TABLEALLTARIFFHLP'> ");
		try{
			
			String strTariffCmbo= "";
			if (ws2 != null) 
			{
				if(ws2.size() != 0)
				{
					//ws.next();
					if (ws2!= null && ws2.size() > 0) 
					{
						strTariffCmbo= new HisUtil("Billing", "HLPBilling").getOptionValue(ws2, "", "", false);
						//sb.append("<option value='0'>Select Value</option>");
						//sb.append("</select>");
						
					} 
					else 
					{
						strTariffCmbo="<option value='0'>Select Value</option>";
						//sb.append("</select>");
					}				
				} 
			
			}
			
		if(ws != null && ws.size() > 0)
		{	
			

			while(ws.next())
			{
				sb.append("<tr> ");
					int idx=index++;
					/*if(ws.size()==1)
						data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'}");
					else
					{
						if(ws.isLast())
							data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'}");
						else
							data.append("{'groupCode': '"+ws.getString(1)+"','trfCode': '"+ws.getString(2)+"','trfName': '"+ws.getString(3)+"','rate': '"+ws.getString(4)+"','qty': '"+ws.getString(5)+"', 'netAmt': '"+ws.getString(6)+"','trfDate': '"+ws.getString(7)+"'},");
					}*/
					String date[]=ws.getString(7).replace("-","#").split("#");
					String trId="TRALLTARIFFHLP"+idx;
					sb.append("<tr id='"+trId+"' clas='style_x'> ");
					sb.append("<td class='trfTd' width='2%'><input type='text'  tabindex='1' name='slNo'      id='slNo"+idx+"'  	   onkeypress='moveUpDown(event,this,"+idx+");'  onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ ++sl +"' readonly></td> ");
					sb.append("<td class='trfTd' width='10%'>");
					sb.append("<input type='text' name='trfDateDD' maxlength='2'  tabindex='1' id='trfDateDD"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='editor-textsmall' value='"+date[0]+"' autocomplete='off'>-");
					sb.append("<input type='text' name='trfDateMM' maxlength='2'  tabindex='1' id='trfDateMM"+idx+"'   onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='editor-textsmall' value='"+date[1]+"' autocomplete='off'>-");
					sb.append("<input type='text' name='trfDateYYYY' maxlength='4' tabindex='1' id='trfDateYYYY"+idx+"' onkeypress='return validateData(event,5);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');setRequestDate("+idx+");\" onkeydown=\"checkFirstKey(event)\" class='editor-textsmallYr' value='"+date[2]+"' autocomplete='off'></td>");
					sb.append("<td class='trfTd' width='12%'><input type='text' tabindex='1' name='groupName' id='groupName"+idx+"' onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(1)+"' readonly></td> ");
				
					
					
					
					/*	if(ws.getString(15).equalsIgnoreCase("1"))
					sb.append("<td class='trfTd' width='3%'><select name='strPriority'   tabindex='1' id='strPriority"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' onchange='calcOffLineTariffNetAmountNew(this,"+idx+");'><option value='1' selected>N</option><option value='2'>E</option></select></td> ");			
					else
					sb.append("<td class='trfTd' width='3%'><select name='strPriority'   tabindex='1' id='strPriority"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' onchange='calcOffLineTariffNetAmountNew(this,"+idx+");'><option value='1'>N</option><option value='2' selected>E</option></select></td> ");
				*/	//System.out.println("ws.getString(12)"+ws.getString(12));
				/*	if(ws.getString(12).equalsIgnoreCase("1")) //If Online Tariff Dont Allow Edit Tariff Code
					{	//System.out.println("id============");
						sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='trfCode'   id='trfCode"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(2)+"' readonly></td> ");
					}else{ //If Offline Tariff Allow Edit Tariff Code
						//System.out.println("else==========");
						sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='trfCode'   id='trfCode"+idx+"'  onchange='setCurrentRowTrfCode(this,"+idx+")' onkeypress=' return validateData(event,8);' onkeyup=\"getTariffByCodeNew(this , event,"+idx+");moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(2)+"'>");
					}*/
				//	sb.append("<label name='trfCodeLabel'   id='trfCodeLabel"+idx+"' style='display:none;'>"+ws.getString(2)+"</label></td> ");
					
					
					/*if(ws.getString(12).equalsIgnoreCase("1")) //If Online Tariff Dont Allow Edit Tariff Code
					sb.append("<td class='trfTd' width='18%'><input type='text' tabindex='1' name='strOfflineTariffName'   id='strOfflineTariffName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(3)+"' readonly></td> ");
					else
					sb.append("<td class='trfTd' width='18%'><input type='text' tabindex='1' name='strOfflineTariffName'   id='strOfflineTariffName"+idx+"'   onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(3)+"' ></td> ");	
					*/
					
					sb.append("<td class='trfTd' width='10%'><select name='strOfflineTariffName'  id='strOfflineTariffName"+idx+"'> ");
					sb.append(strTariffCmbo);
					sb.append("</select></td>");
					
					sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='strOfflineTariffQty' 	id='strOfflineTariffQty"+idx+"' maxlength='3' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(5)+"' autocomplete='off'></td> ");
					
					if(ws.getString(16).equalsIgnoreCase("1"))
					sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='strOfflineTariffRateUnit'  maxlength='7' id='strOfflineTariffRateUnit"+idx+"' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(4)+"' autocomplete='off'></td> ");
					else
					sb.append("<td class='trfTd' width='10%'><input type='text' tabindex='1' name='strOfflineTariffRateUnit'  maxlength='7' id='strOfflineTariffRateUnit"+idx+"' onkeypress='return validateData(event,5);'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');calcOffLineTariffNetAmountNew(this,"+idx+");\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(4)+"' autocomplete='off' readonly></td> ");	
					
					//sb.append("<td class='trfTd' width='4%'><input type='text' tabindex='1' name='strDiscount' id='strDiscount"+idx+"' maxlength='5' class='editor-texts' value='"+ws.getString(9)+"' onkeypress='return validateData(event,7);' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"');onEntDiscount("+idx+");\" onblur=\"calActualDisAmt("+idx+");\" onkeydown=\"checkFirstKey(event)\" autocomplete='off'/></td>");
					//sb.append("<td class='trfTd' width='4%'><select tabindex='1' name='strDiscountType' id='strDiscountType"+idx+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" onchange='onEntDiscount("+idx+");' value='"+ws.getString(11)+"' class='editor-texts'><option value='1'>Fix</option><option value='2'>%</option></select></td>");
					//sb.append("<td class='trfTd' width='3%'><input type='text' tabindex='1' name='strDiscountAmt' id='strDiscountAmt"+idx+"' value='"+ws.getString(10)+"' onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' readonly/></td>");
						
					sb.append("<td class='trfTd' width='7%'><input type='text' tabindex='1' name='strOfflineTariffNetAmount' id='strOfflineTariffNetAmount"+idx+"'  onkeypress='moveUpDown(event,this,"+idx+")' onkeyup=\"deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(6)+"' readonly></td> ");
					//sb.append("<td class='trfTd' width='5%'><input type='text'  tabindex='1' name='servType'      id='servType"+idx+"'  onkeyup=\"moveUpDown(event,this,"+idx+");deleteRow(event,this,"+idx+",'"+trId+"')\" onkeydown=\"checkFirstKey(event)\" class='editor-texts' value='"+ws.getString(13)+"' readonly></td>");
					sb.append("<td class='trfTd' width='2%'><img style='cursor: hand; cursor: pointer' tabindex='1' src='../../hisglobal/images/minus.gif' name='tariffMinus' onclick=\"deleteRowClick(this,"+idx+",'"+trId+"')\">");
					sb.append("<input type='hidden' name='deleteFlag' value='0' id='deleteFlag"+idx+"'>");//Deleted Flag=2 Means-New Row,1 Means -Deleted Row,0-Means No Change, 3 Means- Modify Data
					sb.append("<input type='hidden' name='strOfflineTariffDetailsHidden' value='"+ws.getString(8)+"' id='strOfflineTariffDetailsHidden"+idx+"'>");
					sb.append("<input type='hidden' name='strOfflineTempTariffRateUnit' value='"+ws.getString(4)+"' id='strOfflineTempTariffRateUnit"+idx+"'>");			
					sb.append("<input type='hidden' name='strOfflineTariffUnit' value='"+BillConfigUtil.DEFAULT_UNIT_ID_EACH+"' id='strOfflineTariffUnit"+idx+"'>");
					sb.append("<input type='hidden' name='strOfflineTariffDate' value='' id='strOfflineTariffDate"+idx+"'>");
					sb.append("<input type='hidden' name='strOfflineTariffServiceTax' value='0' id='strOfflineTariffServiceTax"+idx+"'>");
					sb.append("<input type='hidden' name='strOfflineTariffServiceTaxAmtVal' value='0' id='strOfflineTariffServiceTaxAmtVal"+idx+"'>");
					sb.append("<input type='hidden' name='strOfflineActualTariffAmtVal' value='"+ws.getString(4)+"' id='strOfflineActualTariffAmtVal"+idx+"'>");
					sb.append("<input type='hidden' name='strServTypeId' value='"+ws.getString(12)+"' id='strServTypeId"+idx+"'>");
					sb.append("<input type='hidden' name='strPrevReqNo' value='"+ws.getString(14)+"' id='strPrevReqNo"+idx+"'>");
					sb.append("<input type='hidden' name='strOrigActAmt' value='"+ws.getString(6)+"' id='strOrigActAmt"+idx+"'>");
					sb.append("<input type='hidden' name='strDisActAmt' value='"+ws.getString(10)+"' id='strDisActAmt"+idx+"'>");
					sb.append("<input type='hidden' name='strDisActAmtTmp' value='"+ws.getString(10)+"' id='strDisActAmtTmp"+idx+"'>");
					sb.append("</td></tr>");
					
								
				}
					
		}
		sb.append("</table>");		
		sb.append("</div>");
		voObj.setStrAllTrfHLP(sb.toString());
			voObj.setStrNumRows(Integer.toString(index));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data.toString();
	}
	
	
}


