package billing.transactions;

import hisglobal.utility.HisUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.sql.rowset.WebRowSet;

public class AddServicesIPDTransHLP {

	public static String getCompulsaryChargesView(WebRowSet ws , String strStartDate , String strEndDate , String strWardType) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try{
			
			if(ws != null && ws.size() >0){
				
				int count = 1;
				
				int qty = dateDifference(strStartDate, strEndDate);
				
				if(qty == 0){
					qty = 1;
				}
				
				
				
				sb.append(" <table class='TABLEWIDTH' align='center' cellspacing ='1px'> ");
				sb.append(" <tr> ");
				sb.append("  <td class='multiLabel' width='5%'>#</td> ");
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
				sb.append(" <td class='multiControl' width='5%'><input type='checkbox' checked='checked' value='"+strWardType+"' name='strCompChargeCheck' id='strCompChargeCheck"+strWardType+count+"' onclick='manageCompalsaryChargesTariff(this , \""+strWardType+count+"\");'> </td> ");
				sb.append(" <td class='multiControl' width='10%'>"+strEndDate+"</td> ");
				sb.append(" <td class='multiControl' width='35%'><input type='hidden' name='strOfflineTariffName' id='strOfflineTariffName"+strWardType+count+"' value='"+strTariffName+"' > <input type='hidden' name='strOfflineTariffDetailsHidden' id='strOfflineTariffDetailsHidden"+strWardType+count+"' value='"+strId+"' >"+strTariffName+"</td> ");
				sb.append(" <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffRateUnit' id='strOfflineTariffRateUnit"+strWardType+count+"' value='"+strCost+"' > "+strCost+"/"+strUnitName+" </td> ");
			    sb.append(" <td class='multiControl' width='10%'><input type='text' name='strOfflineTariffQty' id='strOfflineTariffQty"+strWardType+count+"' value='"+qty+"' class='txtFldMin' maxlength='8' onkeyup='calcOffLineTariffNetAmount(\""+strWardType+count+"\");' ></td> ");
			    sb.append(" <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffUnit' id='strOfflineTariffUnit"+strWardType+count+"' value='"+strUnitId+"'>"+strUnitName+"</td> ");
				sb.append("  <td class='multiControl' width='10%'><input type='hidden' name='strOfflineTariffServiceTax' id='strOfflineTariffServiceTax"+strWardType+count+"' value='"+strServiceTax+"'><input type='hidden' name='strOfflineTariffServiceTaxAmtVal' id='strOfflineTariffServiceTaxAmtVal"+strWardType+count+"' value='"+strServiceTaxAmt+"'><input type='hidden' name='strOfflineActualTariffAmtVal' id='strOfflineActualTariffAmtVal"+strWardType+count+"' value='"+strActualTariffCostValue+"'>"+strServiceTaxAmt+"</td> ");
				sb.append(" <td class='multiControl' style='font-weight: bold'  width='10%'><input type='hidden' name='strOfflineTariffNetAmount' id='strOfflineTariffNetAmount"+strWardType+count+"' value='"+strTariffNetCostValue+"'><div id='strOfflineTariffNetAmountDivId"+strWardType+count+"'>"+strTariffNetCostValue+"</div></td> ");
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
	
	
	public static String getPreviousAddServicesView(WebRowSet ws ) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try{
			
			sb.append(" <table class='TABLEWIDTH' align='center' cellspacing ='1px'> ");
			sb.append(" <tr> ");
			sb.append(" <td class='multiLabel' width='25%' > Ward Type</td> ");
			sb.append("<td class='multiLabel' width='25%' > Total Amount</td> ");
			sb.append("<td class='multiLabel' width='50%' > #</td> ");
			sb.append("</tr> ");
  
			if(ws != null && ws.size() > 0){
				
				while(ws.next()){
					
					sb.append(" <tr> ");
					sb.append("<td class='multiControl' width='25%' >"+ws.getString(2)+"</td> ");
					sb.append("<td class='multiControl' width='25%' >"+HisUtil.getAmountWithDecimal(ws.getString(3), 2) +"</td> ");
					sb.append("<td class='multiControl' width='50%' > &nbsp;&nbsp; <a style='color: blue;cursor:pointer;cursor:hand' onclick='getPreviousView(this , \""+ws.getString(1)+"\" , \""+ws.getString(4)+"\" );' title='Click Here to View the Previous Tariff Details' >View</a> "); 
					sb.append("&nbsp;&nbsp; <a style='color: blue;cursor:pointer;cursor:hand' title='Click Here to Delete the Tariffs' onclick='deletePreviousDtls(this , \""+ws.getString(1)+"\" , \""+ws.getString(4)+"\" );' >Delete</a> ");
					sb.append("</td> ");
					sb.append("</tr> ");
										
				}
								
			}else{
				
				sb.append(" <tr> ");
				sb.append("<td class='multiControl' colspan='3' style='color:red;font-weight:bold;' >No Details Available</td> ");
				sb.append("</tr> ");
				
			}
			
			
			sb.append("  </table> ");
			
			
			
		}catch(Exception e){
			
			throw e;
		}
		
	
		return sb.toString();
		
	}
	
}
