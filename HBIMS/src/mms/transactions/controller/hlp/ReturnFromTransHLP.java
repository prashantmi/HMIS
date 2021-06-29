/**
 * 
 */
package mms.transactions.controller.hlp;

import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import mms.transactions.bo.ReturnFromTransBO;
import mms.transactions.vo.ReturnFromTransVO;

/**
 * Developer : Tanvi Sappal 
 * Version : 1.0 
 * Date : 24/April/2009
 * 
 */
public class ReturnFromTransHLP {

	public static String getItemDetails(String strItemCategoryNo,
			String strHospitalCode, WebRowSet wb, String strStoreId)
	 {
		StringBuffer sBuffer = new StringBuffer("");
		
	
		ReturnFromTransVO vo=null;
		ReturnFromTransBO bo= null;
		String strReturnUnitCombo = "";
		HisUtil hisutil = null;
		
		String strHiddenId = "";//total cost^unit id
		String unitId = "";
		String strBrdName = "";
		String strBtchSlNo = "";
		String strExpiryDt = "";
		String strBalQty = "";
	//	String totalCost = "";
		String rateUnit = "";
		String rate = "";
		String rateUnitId ="";
		String manufDate = "";
		String consumable = "";
		String issueUnit = "";
		String returnUnit = "";
		String baseValue = "";
		int i = 0;
		
		
		 try {
			 
			 bo = new ReturnFromTransBO();
			 vo = new ReturnFromTransVO();
			
			
			 hisutil = new HisUtil("MMS","ReturnFromTransHLP");
			 
			 if(wb.size() != 0){
			 
				 sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='#1277B5'>");       
	             sBuffer.append("<tr>");  
	             sBuffer.append("<td width='33%' class='multiRPTLabel'>Drug Name</td>");
	  	         sBuffer.append("<td width='13%' class='multiRPTLabel'>Batch</td>");
	  	         sBuffer.append("<td width='10%' class='multiRPTLabel'>Expiry Date</td>");
	  	         sBuffer.append("<td width='10%' class='multiRPTLabel'>Balance Qty</td>");
	             sBuffer.append("<td width='10%' class='multiRPTLabel'><font color='red'>*</font>Return Qty</td>");
	             sBuffer.append("<td width='13%' class='multiRPTLabel'><font color='red'>*</font>Return Qty Unit</td>");
	             sBuffer.append("<td width='10%' class='multiRPTLabel'>Cost(Rs.)</td>");
	             sBuffer.append("</tr>");
	             sBuffer.append("</table>");
				while(wb.next())
				{		     	
					
					
					strHiddenId = wb.getString(1); //item id^itemBrandId^groupId^subGroupId 
													//total cost^unit id^rate unit^rate^rateunitid^base value
													//^manufdate^consumable^issueUnit^returnUnit
						strBrdName = wb.getString(2);
						strBtchSlNo = wb.getString(3);
						strExpiryDt = wb.getString(4);
						strBalQty = wb.getString(5);
						
						String[] temp = strHiddenId.replace("^", "#").split("#");
						
					//	totalCost = temp[4];
						unitId = temp[5];
						rateUnit = temp[6];
						rate = temp[7];
						rateUnitId = temp[8];
						baseValue = temp[9];
						manufDate = temp[10];
						consumable = temp[11];
						issueUnit = temp[12];
						returnUnit = temp[13];
						
						vo.setStrIssueQtyUnitId(unitId);
						vo.setStrHospitalCode(strHospitalCode);
	  					
						vo.setStrIssueQtyUnitId(unitId);
						vo.setStrHospitalCode(strHospitalCode);
						
										  					
	  					if (vo.getStrMsgType().equals("1")) {
							throw new Exception(vo.getStrMsgString());
						}
	  					
	  					bo.getReturnQtyUnit(vo);
	  					
	  					if (vo.getReturnQtyUnitWS() != null	&& vo.getReturnQtyUnitWS().size() > 0) 
	  					{
	  						String strReturnQtyUnit ="0";
	  						if(vo.getReturnQtyUnitWS().next())
	  						{
	  							strReturnQtyUnit = vo.getReturnQtyUnitWS().getString(1);
	  						}
	  						else
	  						{
	  							strReturnQtyUnit="0";
	  						}
	  						
	  						vo.getReturnQtyUnitWS().beforeFirst();
	  						strReturnUnitCombo = hisutil.getOptionValue(vo.getReturnQtyUnitWS(), strReturnQtyUnit, "0^Select", true);
	  					}
	  					else 
	  					{
	  						strReturnUnitCombo = "<option value='0'>Select</option>";
	  					}
	  					
	  					
	  					if(strExpiryDt == null || strExpiryDt.equals("null") || strExpiryDt.equals(""))strExpiryDt = "---";
	  					if(strBrdName == null || strBrdName.equals("null") || strBrdName.equals("") )strBrdName = "---";
	  					if(strBtchSlNo == null || strBtchSlNo.equals("null") || strBtchSlNo.equals(""))strBtchSlNo = "---";
	  					if(strBalQty == null || strBalQty.equals("null") || strBalQty.equals(""))strBalQty = "---";
	  					
	  					sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='#1277B5'>");  
	  					sBuffer.append("<tr>");
	  					sBuffer.append("<td width='33%' class='multiPOControl' style='text-align:left'>"+strBrdName+""+
	  							"<input type='hidden' name='strItem' id='strItem" + i
		  							+"' value='"+ rateUnit+ '@'+ manufDate +'@'+ consumable +"'/></td>");
	  					
//	  					sBuffer.append("<td width='33%' class='multiPOControl'><a style='cursor:pointer;cursor:pointer;color:blue;text-align:left' title='Get Brand Details' " +
//	  							"onClick='openItemName(this,"+i+");'>" 
//	  							+ strBrdName +"</a>"+
//	  							"<input type='hidden' name='strItem' id='strItem" + i
//		  							+"' value='"+ rateUnit+ '@'+ manufDate +'@'+ consumable +"'/></td>");
	  							/*"<input type='checkbox' name='strItemDetailsChk' id='strItemDetailsChk"+
	  							+ i
								+ "' onclick='ClickCheckBox(this,\""
								+ i
								+ "\");' value= '"
								+ strHiddenId
								+ "^"
								+ strItemCategoryNo
								+ "^"
								+ strStoreId
								+ "' /> ");
	  					sBuffer.append(" <input type='hidden' name='flag' id='flag" + i
								+ "' value=" + "0" + " >");

	  					sBuffer.append("</td>");	
	  					
	  					sBuffer.append("<td width='15%' class='multiPOControl'>"
	  							+ strItmName +"</td>");*/
	  					sBuffer.append("<td width='13%' class='multiPOControl'>"
	  							+ strBtchSlNo +"</td>");
	  					sBuffer.append("<td width='10%' class='multiPOControl'>"
	  							+ strExpiryDt +"</td>");
	  					
	  					sBuffer.append("<td width='10%' class='multiPOControl' name='strBalanceQty' id='strBalanceQty"+i+"'>"+ strBalQty+"" +
	  									"<input type='hidden' name='balQty' id='balQty" + i
	  							+"' value='"+ issueUnit+ '@'+ returnUnit +"'/><input type='hidden' name='strBalQty' id='strBalQty" + i
	  							+"' value='"+ strBalQty+"'/></td>");
	  					
	  					
//	  					sBuffer.append("<td width='10%' class='multiPOControl' name='strBalanceQty' id='strBalanceQty"+i+"'>"
//	  							+"<a style='cursor:pointer;cursor:pointer;color:blue' onclick='openBalQty(this,"+i+");'>"+ strBalQty + "</a>" +
//	  									"<input type='hidden' name='balQty' id='balQty" + i
//	  							+"' value='"+ issueUnit+ '@'+ returnUnit +"'/><input type='hidden' name='strBalQty' id='strBalQty" + i
//	  							+"' value='"+ strBalQty+"'/></td>");	  					
	  					sBuffer.append("<td WIDTH='10%' CLASS='multiPOControl' >" +
	  							"<input type='text' maxlength='8' autocomplete='off' onkeypress='return validateData(event,7);', onkeyup='quantityUnitValue("+i+");'"  +
	  							"name='strReturnQty' id='strReturnQty"
								+ i
								+ "' class='txtFldMin' value=''> </td>");
	  					sBuffer.append("<td WIDTH='13%' CLASS='multiPOControl' >" +
	  							"<Select name='strReturnQtyUnitId' disabled='disabled' class='comboMin' id='strReturnQtyUnitId"   
								+ i 
								+ "' onchange='quantityUnitValue("+i+");'>"
								+ strReturnUnitCombo + "</select></td>");
	  					sBuffer.append("<td width='10%' class='multiPOControl'> <div id='strTotalCostDivId"+i+"'>0.00</div>" +
	  							"<input type='hidden' name='strTotalCost' id='strTotalCost" + i
	  							+"' value='0'/><input type='hidden' name='strRate' id='strRate" + i
	  							+"' value='"+ rate+"@"+ rateUnitId+"@"+baseValue+"'/><input type='hidden' name='strHidParamVal' id='strHidParamVal" + i
	  							+"' value='"+wb.getString(1)+"'/></td>");	  					
	  					sBuffer.append("</tr>");
	  					sBuffer.append("</table>");
	  					i++;  						
	  				}
					sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");  
					sBuffer.append("<tr>");
					sBuffer.append("<td width='90%' class='LABEL'>Net Cost(Rs):</td>");
					sBuffer.append("<td width='10%' class='CONTROL' style='color: red; font-weight: bold'><div id='strNetCostDivId' align='center'>0.00</div>");
					sBuffer.append("<input type='hidden' name='strNetCost' id='strNetCost' value='0'/></td>");
					sBuffer.append("</tr>");
					sBuffer.append("</table>");
			 }else
			 {
				    sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");  
					sBuffer.append("<tr>");
					sBuffer.append("<td width='100%' class='LABEL' style='text-align: center;color: red;'>No Record Available</td>");
					//sBuffer.append("<td width='10%' class='CONTROL' style='color: red; font-weight: bold'><div id='strNetCostDivId' align='center'>0.00</div>");
					sBuffer.append("</tr>");
					sBuffer.append("</table>");
			 }
	   
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
				return "ERROR";

	     }
	    return sBuffer.toString();
	 	}



public static String getItemDetailsNEW(String strItemCategoryNo,
		String strHospitalCode, WebRowSet wb, String strStoreId)
 {
	StringBuffer sBuffer = new StringBuffer("");
	

	ReturnFromTransVO vo=null;
	ReturnFromTransBO bo= null;
	String strReturnUnitCombo = "";
	HisUtil hisutil = null;
	
	String strHiddenId = "";//total cost^unit id
	String unitId = "";
	String strBrdName = "";
	String strBtchSlNo = "";
	String strExpiryDt = "";
	String strBalQty = "";
//	String totalCost = "";
	String rateUnit = "";
	String rate = "";
	String rateUnitId ="";
	String manufDate = "";
	String consumable = "";
	String issueUnit = "";
	String returnUnit = "";
	String baseValue = "";
	int i = 0;
	
	
	 try {
		 
		 bo = new ReturnFromTransBO();
		 vo = new ReturnFromTransVO();
		
		
		 hisutil = new HisUtil("MMS","ReturnFromTransHLP");
		 
		 if(wb.size() != 0){
		 
			 sBuffer.append("<div class='row rowFlex reFlex'>");
			 sBuffer.append("<div class='col-md-2 px-4'>Drug Name");sBuffer.append("</div>");
			 sBuffer.append("<div class='col-md-2'>Batch");sBuffer.append("</div>");
			 sBuffer.append("<div class='col-md-1'>Expiry Date");sBuffer.append("</div>");
			 sBuffer.append("<div class='col-md-2'>Balance Qty");sBuffer.append("</div>");
			 sBuffer.append("<div class='col-md-1'><font color='red'>*</font>Return Qty");sBuffer.append("</div>");
			 sBuffer.append("<div class='col-md-1'><font color='red'>*</font>Return Qty Unit");sBuffer.append("</div>");
			 sBuffer.append("<div class='col-md-1'>Cost(Rs.)");sBuffer.append("</div>");
			 sBuffer.append("</div>");
			 
			 
				/*
				 * sBuffer.
				 * append("<table class='TABLEWIDTH' align='center' cellspacing='1px' bgcolor='#1277B5'>"
				 * ); sBuffer.append("<tr>");
				 * sBuffer.append("<td width='33%' class='multiRPTLabel'></td>");
				 * sBuffer.append("<td width='13%' class='multiRPTLabel'></td>");
				 * sBuffer.append("<td width='10%' class='multiRPTLabel'></td>");
				 * sBuffer.append("<td width='10%' class='multiRPTLabel'></td>");
				 * sBuffer.append("<td width='10%' class='multiRPTLabel'></td>");
				 * sBuffer.append("<td width='13%' class='multiRPTLabel'></td>");
				 * sBuffer.append("<td width='10%' class='multiRPTLabel'></td>");
				 * sBuffer.append("</tr>"); sBuffer.append("</table>");
				 */
			while(wb.next())
			{		     	
				
				
				strHiddenId = wb.getString(1); //item id^itemBrandId^groupId^subGroupId 
												//total cost^unit id^rate unit^rate^rateunitid^base value
												//^manufdate^consumable^issueUnit^returnUnit
					strBrdName = wb.getString(2);
					strBtchSlNo = wb.getString(3);
					strExpiryDt = wb.getString(4);
					strBalQty = wb.getString(5);
					
					String[] temp = strHiddenId.replace("^", "#").split("#");
					
				//	totalCost = temp[4];
					unitId = temp[5];
					rateUnit = temp[6];
					rate = temp[7];
					rateUnitId = temp[8];
					baseValue = temp[9];
					manufDate = temp[10];
					consumable = temp[11];
					issueUnit = temp[12];
					returnUnit = temp[13];
					
					vo.setStrIssueQtyUnitId(unitId);
					vo.setStrHospitalCode(strHospitalCode);
  					
					vo.setStrIssueQtyUnitId(unitId);
					vo.setStrHospitalCode(strHospitalCode);
					
									  					
  					if (vo.getStrMsgType().equals("1")) {
						throw new Exception(vo.getStrMsgString());
					}
  					
  					bo.getReturnQtyUnit(vo);
  					
  					if (vo.getReturnQtyUnitWS() != null	&& vo.getReturnQtyUnitWS().size() > 0) 
  					{
  						String strReturnQtyUnit ="0";
  						if(vo.getReturnQtyUnitWS().next())
  						{
  							strReturnQtyUnit = vo.getReturnQtyUnitWS().getString(1);
  						}
  						else
  						{
  							strReturnQtyUnit="0";
  						}
  						
  						vo.getReturnQtyUnitWS().beforeFirst();
  						strReturnUnitCombo = hisutil.getOptionValue(vo.getReturnQtyUnitWS(), strReturnQtyUnit, "0^Select", true);
  					}
  					else 
  					{
  						strReturnUnitCombo = "<option value='0'>Select</option>";
  					}
  					
  					
  					if(strExpiryDt == null || strExpiryDt.equals("null") || strExpiryDt.equals(""))strExpiryDt = "---";
  					if(strBrdName == null || strBrdName.equals("null") || strBrdName.equals("") )strBrdName = "---";
  					if(strBtchSlNo == null || strBtchSlNo.equals("null") || strBtchSlNo.equals(""))strBtchSlNo = "---";
  					if(strBalQty == null || strBalQty.equals("null") || strBalQty.equals(""))strBalQty = "---";
  					
  					sBuffer.append("<div class='row rowFlex reFlex'>");
  					sBuffer.append("<div class='col-md-2 px-4'>");
  					
  					sBuffer.append(strBrdName+""+
  							"<input type='hidden' name='strItem' id='strItem" + i
	  							+"' value='"+ rateUnit+ '@'+ manufDate +'@'+ consumable +"'/>");

  					
  					sBuffer.append("</div>");
  					 sBuffer.append("<div class='col-md-2'>");
  					 	sBuffer.append(strBtchSlNo);
  					 sBuffer.append("</div>");
  					 sBuffer.append("<div class='col-md-1'>");
  					 	sBuffer.append(strExpiryDt);
  					 sBuffer.append("</div>");
  					 sBuffer.append("<div class='col-md-2' name='strBalanceQty' id='strBalanceQty'"+i+"'>'");
  					 		sBuffer.append(strBalQty+"<input type='hidden' name='balQty' id='balQty" + i
						+"' value='"+ issueUnit+ '@'+ returnUnit +"'/><input type='hidden' name='strBalQty' id='strBalQty" + i
						+"' value='"+ strBalQty+"'/></td>");
				
  					 sBuffer.append("</div>");
  					 sBuffer.append("<div class='col-md-1'>");
  					 	sBuffer.append(
  							"<input type='text' maxlength='8' autocomplete='off' onkeypress='return validateData(event,7);', onkeyup='quantityUnitValue("+i+");'"  +
  							"name='strReturnQty' id='strReturnQty"
							+ i
							+ "' class='form-control' value=''>");
  					
  					 sBuffer.append("</div>");
  					 sBuffer.append("<div class='col-md-1'>");
  					 		sBuffer.append(
  							"<Select name='strReturnQtyUnitId' disabled='disabled' class='browser-default custom-select' id='strReturnQtyUnitId"   
							+ i 
							+ "' onchange='quantityUnitValue("+i+");'>"
							+ strReturnUnitCombo + "</select>");
  					
  					 sBuffer.append("</div>");
  					 sBuffer.append("<div class='col-md-1'>Cost(Rs.)");
  					sBuffer.append("<div id='strTotalCostDivId"+i+"'>0.00</div>" +
  							"<input type='hidden' name='strTotalCost' id='strTotalCost" + i
  							+"' value='0'/><input type='hidden' name='strRate' id='strRate" + i
  							+"' value='"+ rate+"@"+ rateUnitId+"@"+baseValue+"'/><input type='hidden' name='strHidParamVal' id='strHidParamVal" + i
  							+"' value='"+wb.getString(1)+"'/>");	  					
  					
  					 sBuffer.append("</div>");
  					 
  					 
								
  					i++;  						
  				}
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			
			sBuffer.append("<div class='col-md-10'>Net Cost(Rs):");sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-md-2' style='color: red; font-weight: bold'><div id='strNetCostDivId' align='center'>0.00</div><input type='hidden' name='strNetCost' id='strNetCost' value='0'/>");sBuffer.append("</div>");
			
			sBuffer.append("</div>");
			
			
				
		 }else
		 {
			 sBuffer.append("<div class='row rowFlex reFlex'>");
			 sBuffer.append("<div class='col-md-12' style='text-align: center;color: red;'>No Record Available");sBuffer.append("</div>");
			 sBuffer.append("</div>");
				/*
				 * sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>"
				 * ); sBuffer.append("<tr>");
				 * sBuffer.append("<td width='100%' class='LABEL' ></td>"); //sBuffer.
				 * append("<td width='10%' class='CONTROL' style='color: red; font-weight: bold'><div id='strNetCostDivId' align='center'>0.00</div>"
				 * ); sBuffer.append("</tr>"); sBuffer.append("</table>");
				 */
		 }
   
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
			return "ERROR";

     }
    return sBuffer.toString();
 	}
}
