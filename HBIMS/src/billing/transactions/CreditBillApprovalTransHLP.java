package billing.transactions;

import hisglobal.PopUp;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

//import purchase.global.controller.GlblPurchaseHLP;

public class CreditBillApprovalTransHLP {

	public static String getTariffDetails(WebRowSet wb,CreditBillApprovalTransVO vo) throws Exception 
	{
		//System.out.println("getTariffDetails bservice id is....................................."+vo.getStrBserviceId());
		StringBuffer sBuffer = new StringBuffer("");
		String costUnit = null;
		try 
		{
		
			
		 if (wb != null && wb.size() > 0) 
		  {
			 //for ipd format is different
				wb.next();
				if(vo.getStrBserviceId().equals("19")&& wb.size()== 1 && wb.getString(1).equalsIgnoreCase("100"))
				{
					//it is ipd advance deposit
					String app_id = "";

					sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
					sBuffer.append("<tr><td colspan='9' class='TITLE'>Credit Bill Details</td></tr>");
					//sBuffer.append("<tr><td width='5%' class='multiLabel'><div align='center'></div></td>");
					sBuffer.append("<tr>");
					sBuffer.append("<td width='25%' class='multiLabel'><div align='center'>Amount</div></td>");
					sBuffer.append("<td width=30% class='multiLabel'><div align='center'>Credit Letter No<font color='red'>*</font></div></td>");
					sBuffer.append("<td width=20% class='multiLabel'><div align='center'>Credit Letter Date<font color='red'>*</font></div></td>");
					sBuffer.append("<td width=20% class='multiLabel'><div align='center'>Upload File(PDF/JPEG)</div></td>");
					sBuffer.append("</tr>");
					sBuffer.append(" <input type='hidden' name='noOfRows' id='noOfRows' value="+ wb.size() + " >");
					
					wb.beforeFirst();
					
					for (int i = 0; wb.next(); i++) 
					{
						//System.out.println("group id is::"+wb.getString(1));
						String strTrfAmt = wb.getString(13); 
						String strReqQty = wb.getString(11);
						String strQtyBaseVal = wb.getString(26);
						String strDiscountUnit = wb.getString(14);
						if(strDiscountUnit == null) strDiscountUnit = "0";
						
						String strDiscountType = wb.getString(15);
						String strRateBaseVal = wb.getString(25);
						
						vo.setLastDis(strDiscountUnit);
		             	String TariffId = wb.getString(2);
		 				String ApprovalId = wb.getString(6);
						app_id = ApprovalId + "^" + TariffId;
//						System.out.println("app_id:::"+ApprovalId);
						String TariffName = wb.getString(7);
						String qtyUnit = wb.getString(18);
						
						String LstDis = strDiscountUnit;
			
						if(wb.getString(10) == null||wb.getString(10).equals("/"))
		       	        {               
							costUnit = "---";
		       	        }
		       	        else
		       	        {
		       	        	costUnit = wb.getString(10);
		       	        }
//						System.out.println("HBLNUM_GROUP_ID--->"+wb.getString(1));
//						System.out.println("HBLNUM_TARIFF_ID--->"+wb.getString(2));    //Tariff Id
//						System.out.println("HBLNUM_TARIFF_RATE--->"+wb.getString(3));
//						System.out.println("GSTR_TARIFF_ID--->"+wb.getString(4));
//						System.out.println("GNUM_RATE_UNIT_ID--->"+wb.getString(5));
//						System.out.println("HBLNUM_APPROVAL_DTL--->"+wb.getString(6));   //Approval Id
//						System.out.println("TARIFF NAME--->"+wb.getString(7));
//						System.out.println("EMPTY_VAL--->"+wb.getString(8));
//						System.out.println("GROUP_NAME--->"+wb.getString(9));
//						System.out.println("RatE_UNIT_NAME--->"+wb.getString(10));
//						System.out.println("REQ_QTY--->"+wb.getString(11));
//						System.out.println("HBLNUM_BILL_QTY--->"+wb.getString(12));
//						System.out.println("HBLNUM_TARIFF_RATE--->"+wb.getString(13));
//						System.out.println("HBLNUM_DIS_UNIT--->"+wb.getString(14));
//						System.out.println("HBLNUM_DIS_TYPE--->"+wb.getString(15));
//						System.out.println("eMPTY VASL--->"+wb.getString(16));
//						System.out.println("HRGNUM_PUK--->"+wb.getString(17));
//						System.out.println("QTY UNIT NAME--->"+wb.getString(18));
//						System.out.println("Qty uNIT--->"+wb.getString(19));
//						System.out.println("HBLNUM_IS_PACKAGE--->"+wb.getString(20));
//						System.out.println("HBLNUM_SERVCIE tAX--->"+wb.getString(21));
//						System.out.println("HBLNUM PEELTY--->"+wb.getString(22));
//						System.out.println("Approval Dtl--->"+wb.getString(23));
//						System.out.println("HBLNUM Qty Type--->"+wb.getString(24));
//						System.out.println("Rate Base Val--->"+wb.getString(25));
//						System.out.println("Qty Base Val--->"+wb.getString(26));
//						System.out.println("Net Cost--->"+wb.getString(27));
						
						String qtyUnitName = strReqQty + " " + qtyUnit;
						String costUnitName = strTrfAmt + "/" + costUnit;
						
						vo.setLastDis(LstDis);
						String qtyBaseValue = strRateBaseVal;
						
				
						sBuffer.append("<input type='hidden' name='hlp_opt_sel' id='hlp_opt_sel' value='" + vo.getHlp_opt_sel() + "'>");
						sBuffer.append("<input type='hidden' name='strHidValOnLineReqDis' id='strHidValOnLineReqDis' value='" + vo.getStrHidValOnLineReqDis() + "'>");
						sBuffer.append("<input type='hidden' name='hlpBServiceID' value='"+vo.getStrBserviceId()+"'>");
						sBuffer.append("<input type='hidden' name='strRateBaseValue' id='strRateBaseValue"+ i + "' value='" + wb.getString(25) + "'>");
						sBuffer.append("<input type='hidden' name='strTrfId' id='strTrfId"+ i + "' value='" + wb.getString(2) + "'>");
						sBuffer.append("<input type='hidden' name='strApprovDtl' id='strApprovDtl"+ i + "' value='" + wb.getString(23) + "'>");
						sBuffer.append("<input type='hidden' name='strApprovId' id='strApprovId"+ i + "' value='" + wb.getString(6) + "'>");
						sBuffer.append("<input type='hidden' name='strTariffId' id='strTariffId"+ i + "' value='" + wb.getString(2) + "'>");
						sBuffer.append("<input type='hidden' name='strDiscountUnit' id='strDiscountUnit"+ i + "' value='" + wb.getString(14) + "'>");
						sBuffer.append("<input type='hidden' name='strDiscountType' id='strDiscountType"+ i + "' value='" + strDiscountType + "'>");
						sBuffer.append("<input type='hidden' name='qtyBaseValue' id='qtyBaseValue"
										+ i + "' value='" + qtyBaseValue + "'> ");
						sBuffer.append("<input type='hidden' name='Cost' id='Cost" + i
										+ "' value='" + strTrfAmt + "'>");
						sBuffer.append("<input type='hidden' name='tariffCost' id='tariffCost" + i
								+ "' value='" + strTrfAmt + "'>");
						
						sBuffer.append("<input type='hidden' name='qty' id='qty" + i
										+ "' value='" + strReqQty + "'>");
						
						sBuffer.append("<input type='hidden' name='strhiddTrfAddDis' id='strhiddTrfAddDis"+ i + "' value='0'>");
						sBuffer.append("<tr><td width='5%' style='cursor:pointer;cursor:hand;display:none;'  class='multiLabel'>" +
										"<input type='checkbox' name='trfDisApproval' id='trfDisApproval"+i
										+ "' value='" + i + "' onClick='new_dis_add(this,"+i+","+app_id+");onEnterDiscount("+i+","+strTrfAmt+","+strReqQty+","+strQtyBaseVal+","+strDiscountUnit+","+strDiscountType+","+strRateBaseVal+");'></td>");
						
						sBuffer
								.append("<td width='25%' class='multiControl'>");
						sBuffer
								.append("<input type='textbox' class='txtFldMin' name='opd_discount' id='opd_discount"+i+"'value='"+wb.getString(13)+"'" +
										" maxlength='9' " +
										" onkeypress='return validateData(event,7);'>");
						sBuffer.append("</td>");
						
						//credit bill discount will always be fixed in ipd credit bill..thus 1..
						sBuffer.append("<input type='hidden' name='opd_discountType' id='opd_discountType"+ i + "' value='1'>");
						
						//prev disc hidden field cz this field is no longer required..
						// no prev disc could be accumulated..thus value always 0
						sBuffer.append("<input type='hidden' name='trfAdd_Dis' id='trfAdd_Dis"+ i + "' value='0'>");
						
						
					
						sBuffer.append("<td width='10%' class='multiControl' style='display:none;'>");
						sBuffer.append("<input type='hidden'  name='disAmt' id='disAmt"	+ i + "' value='0' >");
						sBuffer.append("<input type='hidden'  name='discGivenAmt' id='discGivenAmt"	+ i + "' value='0' >");
						sBuffer.append("<input type='hidden'  id='disAmtDivId"	+ i + "' value='0.00' >");
						//sBuffer.append("<div id='disAmtDivId"+i+"'>0.00</div>");//recheck..
						sBuffer.append("</td>");
						
						//Credit Bill No
						sBuffer.append("<td width='30%' class='multiControl'>");
						sBuffer.append("<input type='textbox' class='txtFldMin' name='strCreditLetterNo' id='strCreditLetterNo"+i+"'value='' " +
										" maxlength='50' " +
										" onkeypress='return validateData(event,3);' onFocus='setTotalWavedAmt();'>");
						sBuffer.append("</td>");
						
						//Credit Bill Date..
						sBuffer.append("<td width='20%' class='multiControl'>");
						sBuffer.append(HisUtil.getDatePickerMultiRowTLD("strCreditLetterDate", "", true,i));
						sBuffer.append("</td>");
						
						//Credit Bill Letter File Upload..
						sBuffer.append("<td width='20%' class='multiControl'>");
						
						//file type and size check are also put in javascript..
						sBuffer.append("<input type='file' name='uploadedFile["+i+"]' accept='image/*,application/pdf'>");
						//for 2MB upper file size limit..
						sBuffer.append("<input type='hidden' name='MAX_FILE_SIZE' value='2000000' />"); 
						//sBuffer.append("<input type='file' name='uploadedFile[1]' accept='image/*'>");
						
						sBuffer.append("</td>");
						
						sBuffer.append("</tr>");

					    }
					
					sBuffer.append(" </table> ");
					sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
					sBuffer.append("<tr><td width='85%' class='LABEL' style='font-weight:bold'>Total Waived Amount</td><td class='multiLabel' style='font-weight:bold'><div   id='totDisAmtDivId'>0.00</div></td><input type='hidden' name='totDisAmt' id='totDisAmt1' value=''></tr>");
					sBuffer.append(" </table> ");
				}
				else
				{
					wb.beforeFirst();
					//it is opd ..
					String app_id = "";

					sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
					sBuffer.append("<tr><td colspan='9' class='TITLE'>Credit Bill Details</td></tr>");
					sBuffer.append("<tr><td width='5%' class='multiLabel'><div align='center'></div></td>");
					sBuffer.append("<td width='16%' class='multiLabel'><div align='center'>Tariff Name</div></td>");
					sBuffer.append("<td width='10%' class='multiLabel'><div align='center'>Qty</div></td>");
					sBuffer.append("<td width='10%' class='multiLabel'><div align='center'>Rate/Unit</div></td>");
					//sBuffer.append("<td width='10%' class='multiLabel'><div align='center'>Last Discount/unit</div></td>");
					sBuffer.append(" <td width='12%' class='multiLabel'><div align='center'>Waiver(%)<font color='red'>*</font></div></td>");
					//sBuffer.append(" <td width='12%' class='multiLabel'><div align='center'>Discount Type</div></td>");
					//sBuffer.append("<td width='8%' class='multiLabel'><div align='center'>Add with Prev Amt</div></td>");
					sBuffer.append("<td width=10% class='multiLabel'><div align='center'>Waived Amt</div></td>");
					sBuffer.append("<td width=10% class='multiLabel'><div align='center'>Credit Letter No<font color='red'>*</font></div></td>");
					sBuffer.append("<td width=10% class='multiLabel'><div align='center'>Credit Letter Date<font color='red'>*</font></div></td>");
					sBuffer.append("<td width=10% class='multiLabel'><div align='center'>Upload File(PDF/JPEG)</div></td>");
					sBuffer.append("</tr>");
					sBuffer.append(" <input type='hidden' name='noOfRows' id='noOfRows' value="+ wb.size() + " >");

					for (int i = 0; wb.next(); i++) 
					{
						System.out.println("group id is::"+wb.getString(1));
						String strTrfAmt = wb.getString(13); 
						String strReqQty = wb.getString(11);
						String strQtyBaseVal = wb.getString(26);
						String strDiscountUnit = wb.getString(14);
						if(strDiscountUnit == null) strDiscountUnit = "0";
						
						String strDiscountType = wb.getString(15);
						String strRateBaseVal = wb.getString(25);
						
						vo.setLastDis(strDiscountUnit);
		             	String TariffId = wb.getString(2);
		 				String ApprovalId = wb.getString(6);
						app_id = ApprovalId + "^" + TariffId;
//						System.out.println("app_id:::"+ApprovalId);
						String TariffName = wb.getString(7);
						String qtyUnit = wb.getString(18);
						
						/*
						double Qty = Double.parseDouble(strReqQty)
						   - Double.parseDouble(wb.getString(12));
				    
		                double qty2 = Double.parseDouble(strTrfAmt);
			    			  			    
			            int Cost = (int) (qty2*Qty) ;
						*/
						
						String LstDis = strDiscountUnit;
						//String s = strDiscountType + "^" + LstDis;
						
			
						if(wb.getString(10) == null||wb.getString(10).equals("/"))
		       	        {               
							costUnit = "---";
		       	        }
		       	        else
		       	        {
		       	        	costUnit = wb.getString(10);
		       	        }
//						System.out.println("HBLNUM_GROUP_ID--->"+wb.getString(1));
//						System.out.println("HBLNUM_TARIFF_ID--->"+wb.getString(2));    //Tariff Id
//						System.out.println("HBLNUM_TARIFF_RATE--->"+wb.getString(3));
//						System.out.println("GSTR_TARIFF_ID--->"+wb.getString(4));
//						System.out.println("GNUM_RATE_UNIT_ID--->"+wb.getString(5));
//						System.out.println("HBLNUM_APPROVAL_DTL--->"+wb.getString(6));   //Approval Id
//						System.out.println("TARIFF NAME--->"+wb.getString(7));
//						System.out.println("EMPTY_VAL--->"+wb.getString(8));
//						System.out.println("GROUP_NAME--->"+wb.getString(9));
//						System.out.println("RatE_UNIT_NAME--->"+wb.getString(10));
//						System.out.println("REQ_QTY--->"+wb.getString(11));
//						System.out.println("HBLNUM_BILL_QTY--->"+wb.getString(12));
//						System.out.println("HBLNUM_TARIFF_RATE--->"+wb.getString(13));
//						System.out.println("HBLNUM_DIS_UNIT--->"+wb.getString(14));
//						System.out.println("HBLNUM_DIS_TYPE--->"+wb.getString(15));
//						System.out.println("eMPTY VASL--->"+wb.getString(16));
//						System.out.println("HRGNUM_PUK--->"+wb.getString(17));
//						System.out.println("QTY UNIT NAME--->"+wb.getString(18));
//						System.out.println("Qty uNIT--->"+wb.getString(19));
//						System.out.println("HBLNUM_IS_PACKAGE--->"+wb.getString(20));
//						System.out.println("HBLNUM_SERVCIE tAX--->"+wb.getString(21));
//						System.out.println("HBLNUM PEELTY--->"+wb.getString(22));
//						System.out.println("Approval Dtl--->"+wb.getString(23));
//						System.out.println("HBLNUM Qty Type--->"+wb.getString(24));
//						System.out.println("Rate Base Val--->"+wb.getString(25));
//						System.out.println("Qty Base Val--->"+wb.getString(26));
//						System.out.println("Net Cost--->"+wb.getString(27));
						
					//	String strApprovDtl = wb.getString(23);
						//System.out.println("strApprovalDtl:::"+strApprovDtl);
						//Appended Value->HBLDT_APPROVAL_DATE^PSTR_EMPLOYEE_NUMBER
						//               ^fun_emp_name^HBLNUM_USER_LEVEL^HBLSTR_REMARKS
					//	String[] id = strApprovDtl.split("\\^");  
						
						String qtyUnitName = strReqQty + " " + qtyUnit;
						String costUnitName = strTrfAmt + "/" + costUnit;
						
						vo.setLastDis(LstDis);
						String qtyBaseValue = strRateBaseVal;
						
				
						sBuffer.append("<input type='hidden' name='hlp_opt_sel' id='hlp_opt_sel' value='" + vo.getHlp_opt_sel() + "'>");
						sBuffer.append("<input type='hidden' name='strHidValOnLineReqDis' id='strHidValOnLineReqDis' value='" + vo.getStrHidValOnLineReqDis() + "'>");
						sBuffer.append("<input type='hidden' name='hlpBServiceID' value='"+vo.getStrBserviceId()+"'>");
						sBuffer.append("<input type='hidden' name='strRateBaseValue' id='strRateBaseValue"+ i + "' value='" + wb.getString(25) + "'>");
						
						//sBuffer.append("<input type='hidden' name='strPrevDisType' id='strPrevDisType"+ i + "' value='" + strDiscountType + "'>"); 
						sBuffer.append("<input type='hidden' name='strTrfId' id='strTrfId"+ i + "' value='" + wb.getString(2) + "'>");
						sBuffer.append("<input type='hidden' name='strApprovDtl' id='strApprovDtl"+ i + "' value='" + wb.getString(23) + "'>");
						sBuffer.append("<input type='hidden' name='strApprovId' id='strApprovId"+ i + "' value='" + wb.getString(6) + "'>");
						sBuffer.append("<input type='hidden' name='strTariffId' id='strTariffId"+ i + "' value='" + wb.getString(2) + "'>");
						
						sBuffer.append("<input type='hidden' name='strDiscountUnit' id='strDiscountUnit"+ i + "' value='" + wb.getString(14) + "'>");
						sBuffer.append("<input type='hidden' name='strDiscountType' id='strDiscountType"+ i + "' value='" + strDiscountType + "'>");
						//sBuffer.append("<input type='hidden' name='strTempDiscountUnit' id='strTempDiscountUnit"+ i + "' value='" + wb.getString(14) + "'>");
						
						//sBuffer.append("<input type='hidden' name='strTempCkhBox' id='strTempCkhBox"+i+ "' value='" + strDiscountType + "'>");
						
						/*
						sBuffer
								.append("<input type='hidden' name='discountType' id='discountType"
										+ i + "' value='" + strDiscountType + "'>");
						
						sBuffer
						.append("<input type='hidden' name='LstDisService' id='LstDisService"
								+ i + "' value='" + wb.getString(14) + "'>");
				*/		
						sBuffer
								.append("<input type='hidden' name='qtyBaseValue' id='qtyBaseValue"
										+ i + "' value='" + qtyBaseValue + "'> ");
						sBuffer
								.append("<input type='hidden' name='Cost' id='Cost" + i
										+ "' value='" + strTrfAmt + "'>");
						sBuffer
						.append("<input type='hidden' name='tariffCost' id='tariffCost" + i
								+ "' value='" + strTrfAmt + "'>");
						
						sBuffer
								.append("<input type='hidden' name='qty' id='qty" + i
										+ "' value='" + strReqQty + "'>");
						/*
						sBuffer
								.append("<input type='hidden' name='disTyp_Dis' id='disTyp_Dis"
										+ i + "' value='" + s + "'>");
						
						sBuffer
						.append("<input type='hidden' name='disTyp_Dis' id='disTyp_Dis"
								+ i + "' value='" + s + "'>");
						*/
						sBuffer.append("<input type='hidden' name='strhiddTrfAddDis' id='strhiddTrfAddDis"+ i + "' value='0'>");
						sBuffer.append("<tr><td width='5%' style='cursor:pointer;cursor:hand'  class='multiLabel'>" +
										"<input type='checkbox' name='trfDisApproval' id='trfDisApproval"+i
										+ "' value='" + i + "' onClick='new_dis_add(this,"+i+","+app_id+");onEnterDiscount("+i+","+strTrfAmt+","+strReqQty+","+strQtyBaseVal+","+strDiscountUnit+","+strDiscountType+","+strRateBaseVal+");'></td>");
						sBuffer
								.append("<td width='16%' class='multiControl'>");
						sBuffer
								.append(TariffName);
						sBuffer
								.append("</td>");
						sBuffer	
								.append("<td width='10%' class='multiControl'>");
						sBuffer
								.append(qtyUnitName);
						sBuffer	
								.append("</td>");
						sBuffer
								.append("<td width='10%' class='multiControl'>");
						sBuffer
								.append(costUnitName);
						sBuffer
								.append("</td>");
						/*sBuffer
								.append("<td width='12%' class='multiControl'>");
						
						/*sBuffer
								.append("<a name='lastDis' id='lastDis" + i
										+ "' value='" + LstDis
										+ "' STYLE='CURSOR:POINTER;CURSOR:HAND;color:blue'  onClick='myFunc1(this," + i + ");'>"
										+ LstDis + "</a>");*/
						
						          

				/*		if (strDiscountType.equals("2") && !strDiscountUnit.equals("0")) 
						{

						    sBuffer.append(" " + "<font color='red'>%</font>");

						}
						if (strDiscountType.equals("1") && !strDiscountUnit.equals("0"))
						{
							sBuffer.append("<font color='red'> Fixed</font>");				
						}	
							
						sBuffer
								.append("</td>");*/
						sBuffer
								.append("<td width='12%' class='multiControl'>");
						sBuffer
								.append("<input type='textbox' class='txtFldMin' name='opd_discount' id='opd_discount"+i+"'value='100' " +
										" maxlength='10' " +
										//" onKeyUp='clickCmb("+i+");'" +
										//" onKeyUp='DiscountCost("+i+ ");'" +
										" onKeyUp='onEnterDiscount("+i+","+strTrfAmt+","+strReqQty+","+strQtyBaseVal+","+strDiscountUnit+","+strDiscountType+","+strRateBaseVal+");'" + 
										" readOnly ='readOnly' onkeypress='return validateData(event,7);'>");
						sBuffer
								.append("</td>");
						
						//credit bill discount will always be in %..thus 2..
						sBuffer.append("<input type='hidden' name='opd_discountType' id='opd_discountType"+ i + "' value='2'>");
						
						//prev disc hidden field cz this field is no longer required..
						// no prev disc could be accumulated..thus value always 0
						sBuffer.append("<input type='hidden' name='trfAdd_Dis' id='trfAdd_Dis"+ i + "' value='0'>");
						
						
					/*	sBuffer.append("<td width='12%' class='multiControl'>");
						sBuffer.append("<select name='opd_discountType' id ='opd_discountType"+i+"'" +
										" onChange='onEnterDiscount("+i+","+strTrfAmt+","+strReqQty+","+strQtyBaseVal+","+strDiscountUnit+","+strDiscountType+","+strRateBaseVal+");'" + 
										" disabled='disabled' style='display:none'>" +
										" <option value='2'>Percentage</option></select>");
						sBuffer.append("</td>");
						sBuffer.append("<td width='8%' class='multiControl'>" +
										"<input type='checkbox'" +
										"name='trfAdd_Dis' id='trfAdd_Dis"+i+"'" +
										"value='0'  onClick= 'onEnterDiscount("+i+","+strTrfAmt+","+strReqQty+","+strQtyBaseVal+","+strDiscountUnit+","+strDiscountType+","+strRateBaseVal+");'" +
										"disabled='disabled' style='display:none'></td>");*/
						
						
						
						
						sBuffer
								.append("<td width='10%' class='multiControl'>");
						sBuffer
								.append("<input type='hidden'  name='disAmt' id='disAmt"
										+ i + "' value='0' >");
						sBuffer
						.append("<input type='hidden'  name='discGivenAmt' id='discGivenAmt"
								+ i + "' value='0' >");
						
						//sBuffer	.append("<input type='hidden' name='strIpdAndReqType' id='strIpdAndReqTypeID' value='"+wb.getString(33)+"'></td>");
						sBuffer
						.append("<div id='disAmtDivId"+i+"'>0.00</div>");
						sBuffer
								.append("</td>");
						
						//Credit Bill No
						sBuffer.append("<td width='12%' class='multiControl'>");
						sBuffer.append("<input type='textbox' class='txtFldMin' name='strCreditLetterNo' id='strCreditLetterNo"+i+"'value='' " +
										" maxlength='50' " +
										" onkeypress='return validateData(event,3);'>");
						sBuffer.append("</td>");
						
						//Credit Bill Date..
						sBuffer.append("<td width='12%' class='multiControl'>");
						sBuffer.append(HisUtil.getDatePickerMultiRowTLD("strCreditLetterDate", "", true,i));
						sBuffer.append("</td>");
						
						//Credit Bill Letter File Upload..
						sBuffer.append("<td width='12%' class='multiControl'>");
						sBuffer.append("<input type='file' name='uploadedFile["+i+"]' accept='image/*,application/pdf'>");
						//for 2MB upper file size limit..
						sBuffer.append("<input type='hidden' name='MAX_FILE_SIZE' value='2000000' />"); 
						//sBuffer.append("<input type='file' name='uploadedFile[1]' accept='image/*'>");
						
						sBuffer.append("</td>");
						
						sBuffer.append("</tr>");

					    }

						sBuffer
								.append(" </table> ");

						sBuffer
								.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
						sBuffer	.append("<tr><td width='85%' class='LABEL' style='font-weight:bold'>Total Waived Amount</td><td class='multiLabel' style='font-weight:bold'><div   id='totDisAmtDivId'>0.00</div></td><input type='hidden' name='totDisAmt' id='totDisAmt1' value=''></tr>");
					    sBuffer	.append(" </table> ");
				}
		
			////////////////////////////////// 
			
		  }
		
		 else
		   {
			 sBuffer.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>"); 
			 sBuffer.append("<tr>");
			 sBuffer.append("<td colspan='4'  CLASS='multiControl' >"
					+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED CR NO </div>" + "</TD>");

			 sBuffer.append("</tr>");
			 sBuffer.append("</table></div>");	
		   
		    }  
		
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
			throw new Exception("DiscountApprovalTransHLP.getTariffDetails()-->"+ e.getMessage());
		}
		
		return sBuffer.toString();
	}

	public static String getIPDBillSetlmnt(WebRowSet wb,CreditBillApprovalTransVO vo) 
	{
     	StringBuffer sBuffer = new StringBuffer("");
     	//String strApprovDtl = null;
	//	String strAPProvDate = null;
		//String strAppBy = null;
		//String strAppRmk = null;
	//	String costUnit = null;
		String strDiscountUnit = "0";
		
		try
		{
			if (wb != null) 
			{
			wb.next();
		//	String strReqQty = wb.getString(11);
//			System.out.println("HBLNUM_GROUP_ID--1->"+wb.getString(1));
//			System.out.println("HBLNUM_TARIFF_ID-2-->"+wb.getString(2));    //Tariff Id
//			System.out.println("HBLNUM_TARIFF_RATE--3->"+wb.getString(3));
//			System.out.println("GSTR_TARIFF_ID--4->"+wb.getString(4));
//			System.out.println("GNUM_RATE_UNIT_ID-5-->"+wb.getString(5));
//			System.out.println("HBLNUM_APPROVAL_DTL--6->"+wb.getString(6));   //Approval Id
//			System.out.println("TARIFF NAME--7->"+wb.getString(7));
//			System.out.println("EMPTY_VAL-8-->"+wb.getString(8));
//			System.out.println("GROUP_NAME--9->"+wb.getString(9));
//			System.out.println("RatE_UNIT_NAME-10-->"+wb.getString(10));
//			System.out.println("REQ_QTY-11-->"+wb.getString(11));
//			System.out.println("HBLNUM_BILL_QTY-12-->"+wb.getString(12));
//			System.out.println("HBLNUM_TARIFF_RATE-13-->"+wb.getString(13));
//			System.out.println("HBLNUM_DIS_UNIT(Last Discount)--14->"+wb.getString(14));
//			System.out.println("HBLNUM_DIS_TYPE--15->"+wb.getString(15));  // Prev Disc Type
//			System.out.println("EMPTY VASL--16->"+wb.getString(16));
//			System.out.println("HRGNUM_PUK-17-->"+wb.getString(17));
//			System.out.println("QTY UNIT NAME-18-->"+wb.getString(18));
//			System.out.println("Qty uNIT--19->"+wb.getString(19));
//			System.out.println("HBLNUM_IS_PACKAGE--20->"+wb.getString(20));
//			System.out.println("HBLNUM_SERVCIE tAX--21->"+wb.getString(21));
//			System.out.println("HBLNUM PEELTY--22->"+wb.getString(22));
//			System.out.println("Approval Dtl--23->"+wb.getString(23));
//			System.out.println("HBLNUM Qty Type-24-->"+wb.getString(24));
//			System.out.println("Rate Base Val--25->"+wb.getString(25));
//			System.out.println("Qty Base Val-26-->"+wb.getString(26));
//			System.out.println("Net Cost--27->"+wb.getString(27));
			
			String LstDis = wb.getString(14);
     		/*if(wb.getString(23)==null||wb.getString(23).equals(""))
			 {
				 strAPProvDate = "---";
				 strAppBy = "---";
				 strAppRmk = "---";
			 }	
			else
			{
				//Appended Value->HBLDT_APPROVAL_DATE^PSTR_EMPLOYEE_NUMBER
				//               ^fun_emp_name^HBLNUM_USER_LEVEL^HBLSTR_REMARKS
				 strApprovDtl = wb.getString(23);
				 String[] id = strApprovDtl.split("\\^");  
				 strAPProvDate = id[0];
				 strAppBy = id[2];
				 strAppRmk = id[4];
			}	*/
     		
     		strDiscountUnit = wb.getString(14);
     		if (strDiscountUnit == null || strDiscountUnit.equals(""))
     			strDiscountUnit = "0";
     		
     		/*
		    double Qty = Double.parseDouble(strReqQty)
						   - Double.parseDouble(wb.getString(12));
	   			    
		    double qty2 = Double.parseDouble(wb.getString(13));
			    			  			    
			    int Cost = (int) (qty2*Qty) ;
			    				*/
     		
				LstDis = wb.getString(14);
				
				//String s = wb.getString(15) + "^" + LstDis;
				
			//	String qtyUnit = wb.getString(18);
				
				
			/*	if(wb.getString(10) == null||wb.getString(10).equals("/"))
       	        {               
					costUnit = "---";
       	        }
       	        else
       	        {
       	        	costUnit = wb.getString(10);
       	         }*/
				
			    
				//String qtyUnitName = Qty + " " + qtyUnit;
			    
				//String costUnitName = Cost + "/" + costUnit;
				
				String qtyBaseValue = wb.getString(25);
				
			sBuffer.append("<input type='hidden' name='strDiscountUnit' id='strDiscountUnit' value='" + strDiscountUnit + "'>");
			sBuffer.append("<input type='hidden' name='strDiscountType' id='strDiscountType' value='" + wb.getString(15) + "'>");
			//sBuffer.append("<input type='hidden' name='strLstDisUnit' id='strLstDisUnit' value='" + wb.getString(14) + "'>");		
			sBuffer.append("<input type='hidden' name='hlp_opt_sel' id='hlp_opt_sel' value='" + vo.getHlp_opt_sel() + "'>");
			//sBuffer.append("<input type='hidden' name='strFinalTarifID' id='strFinalTarifID' value='" + Cost + "'>");
			sBuffer.append("<input type='hidden' name='Cost' id='Cost' value='" + wb.getString(31) + "'>");
			sBuffer.append("<input type='hidden' name='Qty' id='Qty' value='1'>");
			//sBuffer.append("<input type='hidden' name='LstDis' id='LstDis' value='" + LstDis + "'>");
			sBuffer.append("<input type='hidden' name='qtyUnit' id='qtyUnit' value='" + wb.getString(18) + "'>");
			sBuffer.append("<input type='hidden' name='costUnit' id='costUnit' value='" + wb.getString(10) + "'>");
			//sBuffer.append("<input type='hidden' name='qtyUnitName' id='qtyUnitName' value='" + qtyUnitName + "'>");
			//sBuffer.append("<input type='hidden' name='costUnitName' id='costUnitName' value='" + costUnitName + "'>");
			sBuffer.append("<input type='hidden' name='qtyBaseValue' id='qtyBaseValue' value='" + qtyBaseValue + "'>");
		    sBuffer.append("<input type='hidden' name='strRateBaseValue' id='strRateBaseValue' value='" + wb.getString(25) + "'>");
			//sBuffer.append("<input type='hidden' name='strPrevDisTypeFA' id='strPrevDisTypeFA' value='" + wb.getString(15) + "'>");
			sBuffer.append("<input type='hidden' name='strHidValOnLineReqDis' id='strHidValOnLineReqDis' value='" + vo.getStrHidValOnLineReqDis() + "'>");
			sBuffer.append("<input type='hidden' name='hlpBServiceID' value='"+vo.getStrBserviceId()+"'>");
			
			//sBuffer.append("<input type='hidden' name='approval_id' value='"+wb.getString(20)+"'>");
			sBuffer.append("<input type='hidden' name='tariff_id' value='"+wb.getString(2)+"'>");
			sBuffer.append("<input type='hidden' name='strApprovId' id='strApprovId' value='" + wb.getString(6) + "'>");
			//sBuffer.append("<input type='hidden' name='strTariffId' id='strTariffId' value='" + wb.getString(2) + "'>");
			sBuffer.append("<input type='hidden' name='strApprovDtl' id='strApprovDtl' value='"+wb.getString(23)+"'>");
			
			
			sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing ='1px'>");
			sBuffer.append("<tr><td colspan='7' class='TITLE'>Discount Details</td></tr>");
			sBuffer.append("<tr><td  width='25%' class='LABEL' ><div align='right'>Net Payable Amt.</div></td>");
			sBuffer.append("<td width='25%' class='CONTROL' >");
			sBuffer.append(wb.getString(31));
			sBuffer.append("</td>");
			sBuffer	.append("<td width='25%' class='LABEL' ><div align='right'>Last Discount Amt/unit</div></td>");
			sBuffer	.append("<td width='25%' class='CONTROL' >");
			sBuffer .append("<a name='lastDis' id='lastDis' STYLE='CURSOR:POINTER;color:blue'  value='" + LstDis + "'  onClick='tariffDtl(this);'>" +LstDis+"</a>");
                             //<a name='trfName' value='"+app_id+"' STYLE='CURSOR:POINTER;color:blue' onClick='myFunc();'></a>
			
			if (wb.getString(15).equals("2") && !strDiscountUnit.equals("0")) 
			{
				sBuffer.append("<font color='red'>%</font>");
			}
			else
			{
				if (wb.getString(15).equals("1") && !strDiscountUnit.equals("0"))
					sBuffer.append("<font color='red'> Fixed</font>");				
			}	
			
			sBuffer.append("</td></tr>");
			sBuffer	.append("<tr><td class='LABEL' ><div align='right'>Discount Amt/unit</div></td>");
			sBuffer	.append("<td width='25%' class='CONTROL'><input type='textbox' name='strIpdBillCurrDis' id='strIpdBillCurrDis' maxlength='10' value='0.00' onKeyUp='calDiscountAmtFinalAdjust(this);' onkeypress='return validateData(event,5);'></td>");
			sBuffer	.append("<td class='LABEL' ><div align='right'>Discount Type</div></td>");
			sBuffer	.append("<td width='25%' class='CONTROL'><select name='discountType' id='discountType' onChange='calDiscountAmtFinalAdjust(this);'><option value='1'>Fixed</option><option value='2'>% age</option></select>");
			sBuffer	.append("</td></tr>");
			
			sBuffer	.append("<tr><td width='25%' class='LABEL' > <div align='right'>");
			sBuffer
					.append("<input type='checkbox' name='checkbox' id='checkbox' style='cursor:pointer;cursor:hand' value='"+0+"'  onClick= 'calDiscountAmtFinalAdjust(this);'></div></td>");
			sBuffer.append("<input type='hidden' name='strhiddTrfFADis' id='strhiddTrfFADis' value='0'>");
			
			sBuffer
					.append("<td width='75%' colspan='3' class='CONTROL'><div align='left'>Whether user wants to accumulate the previous discount</div></td></tr>");
			sBuffer	.append("<tr><td class='LABEL' ><div align='right'>Net Discount Amt</div></td>");
			sBuffer	.append("<td width='25%' class='CONTROL'>");
			sBuffer.append("<div id='disAmtDivId'>0.00</div>");
			sBuffer	.append("<input type='hidden' name='strIpdBillFianlDis' id='strIpdBillFianlDis' value='0.00'>");
			sBuffer	.append("<input type='hidden' name='strIpdAndReqType' id='strIpdAndReqTypeID' value='"+wb.getString(33)+"'></td>");
			
			sBuffer	.append("<td class='LABEL' ></td>");
			sBuffer	.append("<td width='25%' class='CONTROL'>");
			sBuffer	.append("</td></tr>");
			sBuffer
					.append("</table>");
				
	     	}
			else
			   {
				sBuffer.append("<table class='TABLEWIDTH' align='center'  border='0' bgcolor='#000000' cellspacing ='1px'>"); 
				sBuffer.append("<tr>");
				sBuffer.append("<td colspan='4'  CLASS='multiControl' >"
						+ "<DIV class='errMsg' align='center'> NO RECORD FOUND FOR SELECTED CR NO </div>" + "</TD>");

				sBuffer.append("</tr>");
				sBuffer.append("</table></div>");	
			   // throw new Exception(voObj.getStrMsgString());
			    }  
			
		}		
	 catch (Exception e) 
	 {
			
			new HisException("Discount Approval Trans",
					"DiscountApprovalTransHLP.getIPDBillSetlmnt()-->", e
							.getMessage());
	 }
	 	
		return sBuffer.toString();
	}
	///////////////////////////old data////////////////////////
	

///////////////////////////////Hlp for popup///////////////////////////////////	

	public static String getPopUpDtl(WebRowSet ws)
	{
	 	StringBuffer sb = new StringBuffer("");
		int row = 2;
		int col = 3;
		String Caption = "Discount By" + "^" + "Discount Date" + "^" + "Discount Reason";
		try 
		{
			String strTemp = 7 + "^" + 1 + "^" + 5;
			sb.append(PopUp.getPopupHeader("Discount Details", "menu1"));
			sb.append(PopUp.getPopupBody(row, col, Caption, strTemp, ws));
			sb.append(PopUp.getPopupFooter());
			
		} 
		catch (Exception e) 
		{

			new HisException("Discount Approval Trans",
					"DiscountApprovalTrans.getPopUpDtl()-->", e.getMessage());
		}
		ws = null;
		return sb.toString();
		}
	}
