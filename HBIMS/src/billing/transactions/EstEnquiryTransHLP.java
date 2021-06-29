package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

public class EstEnquiryTransHLP {

	private static final String strColor = "red";
	private static final String defaultColor = "blue";

	public static String getWithoutCrNoOnLineTariffDetailsView(EstEnquiryTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		int trfIndex = 0;

		WebRowSet wsTariffDetails = voObj.getOnlineTariffDetails();

		try {

			if (wsTariffDetails != null) {

				sb
						.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb.append("<td class='TITLE' colspan='8'>Tariff Detail</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td class='multiLabel'></td>");
				sb.append("<td class='multiLabel'>Tariff Name</td>");
				sb
						.append("<td class='multiLabel'>Balance Qty.<input type='hidden' class='txtFldMin' name='strTotalTariffServiceTaxAmount' value='0' ></td>");
				sb
						.append("<td class='multiLabel'>Billed Qty.<input type='hidden' class='txtFldMin' name='strTotalTariffDiscountAmount' value='0' ></td></td>");
				sb
						.append("<td class='multiLabel'>Unit<input type='hidden' class='txtFldMin' name='strTotalTariffActualAmount' value='0' ></td></td>");
				sb.append("<td class='multiLabel'>Ser. Tax Amt.</td>");
				sb.append("<td class='multiLabel'>Discount Amt.</td>");
				sb.append("<td class='multiLabel'>Net Tariff Amt.</td>");
				sb.append("</tr>");

				while (wsTariffDetails.next()) {

					trfIndex = trfIndex + 1;

					String strGroupId = wsTariffDetails.getString(1);
					String strTariffId = wsTariffDetails.getString(2);
					String strChargeTypeId = wsTariffDetails.getString(3);
					String strGblTariffId = wsTariffDetails.getString(4);
					String strRateUnitId = wsTariffDetails.getString(5);
					String strApprovalId = wsTariffDetails.getString(6);
					// String strBserviceType = wsTariffDetails.getString(7);
					String strTariffName = wsTariffDetails.getString(7);

					// String strGroupName = wsTariffDetails.getString(9);
					String strUnitName = wsTariffDetails.getString(10);
					String strReqQty = wsTariffDetails.getString(11);
					String strBillQty = wsTariffDetails.getString(12);
					String strTariffRate = wsTariffDetails.getString(13);
					String strDisountUnit = wsTariffDetails.getString(14);
					String strDiscountType = wsTariffDetails.getString(15);
					String strQtyUnitName = wsTariffDetails.getString(18);
					String strQtyUnitId = wsTariffDetails.getString(19);
					String strIsPackage = wsTariffDetails.getString(20);
					String strServiceTax = wsTariffDetails.getString(21);
					String strPenelty = wsTariffDetails.getString(22);
					String strApprovalDtl = wsTariffDetails.getString(23);
					// String strQtyType = wsTariffDetails.getString(24);
					String strRateBaseVal = wsTariffDetails.getString(25);
					String strQtyBaseVal = wsTariffDetails.getString(26);
					String strNetCost = wsTariffDetails.getString(27);
					String strReceiptNo = wsTariffDetails.getString(28);
					String strActualRate = wsTariffDetails.getString(29);
					String strServiceId = wsTariffDetails.getString(30);
					// String strDisountType =
					// wsTariffDetails.getString(15);

					if (strGroupId == null)
						strGroupId = "";
					if (strTariffId == null)
						strTariffId = "";
					if (strChargeTypeId == null)
						strChargeTypeId = "";
					if (strGblTariffId == null)
						strGblTariffId = "";
					if (strRateUnitId == null)
						strRateUnitId = "";
					if (strApprovalId == null)
						strApprovalId = "";
					if (strTariffName == null)
						strTariffName = "";
					if (strReqQty == null)
						strReqQty = "0";
					if (strBillQty == null)
						strBillQty = "0";
					if (strTariffRate == null)
						strTariffRate = "0";
					if (strDisountUnit == null)
						strDisountUnit = "0";
					if (strRateBaseVal == null)
						strRateBaseVal = "";
					if (strQtyBaseVal == null)
						strQtyBaseVal = "";

					String strHiddenVal = strTariffId + "^" + strReqQty + "*"
							+ strQtyBaseVal + "^" + strQtyUnitId + "^"
							+ strTariffRate + "^" + strRateUnitId + "^"
							+ strGroupId + "^" + strActualRate + "^"
							+ strServiceTax + "^" + strDisountUnit + "^"
							+ strDiscountType + "^" + strGblTariffId + "^"
							+ strApprovalId + "^" + strUnitName + "^"
							+ strRateBaseVal + "^" + strIsPackage + "^"
							+ strNetCost + "^" + strPenelty + "^" + "0^"
							+ strReceiptNo + "^" + strApprovalDtl+"^"+strServiceId;

					sb.append("<tr>");
					sb
							.append("<td class='multiControl'><input type='checkbox' id='strTariffDetailsId"+trfIndex+"' name='strTariffDetailsId' value='");
					sb.append(strHiddenVal);

					sb.append("' onclick='setOnlineWithoutCrNoTariffDetails(this, \""
							+ trfIndex + "\")'></td>");
					sb
							.append("<td class='multiControl'>"
									+ strTariffName+"@"+ strTariffRate
									+ "/"
									+ strUnitName
									+ "<input type='hidden' class='txtFldMin' name='strTariffRate' id='strTariffRate"
							+ trfIndex
							+ "' value='"
							+ strTariffRate
							+ "' disabled='disabled'><input type='hidden' class='txtFldMin' name='strTariffActualAmt' id='strTariffActualAmt"
							+ trfIndex
							+ "' value='0' disabled='disabled'></td>");
					
					sb
							.append("<td class='multiControl'><input type='hidden' class='txtFldMin' name='strTariffReqQty' id='strTariffReqQty"
									+ trfIndex
									+ "' value='"
									+ strReqQty
									+ "' disabled='disabled'>"
									+ strReqQty
									+ "/" + strQtyUnitName + "</td>");
					sb
							.append("<td class='multiControl'><input type='text' class='txtFldMin' name='strTariffBilledQty' id='strTariffBilledQty"
									+ trfIndex
									+ "' onkeyup='setOnlineWithoutCrNoTariffDltsOnQtyChange(this, \""
									+ strHiddenVal
									+ "\", \""
									+ trfIndex
									+ "\");' value='0' disabled='disabled'></td>");

					sb.append("<td width='10%' class='multiControl'>");

					sb
							.append("<select class='comboMin'  onchange='setOnlineWithoutCrNoTariffDltsOnQtyUnitChange(this, \""
									+ strHiddenVal
									+ "\", \""
									+ trfIndex
									+ "\");'  name='strBillTariffUnit' id='strBillTariffUnit"
									+ trfIndex + "' disabled='disabled' > ");

					voObj.setStrOffLineTariffUnitTempId(strQtyUnitId);

					//EstEnquiryTransDAO.getOffLineTariffUnitList(voObj);
					sb.append(BillConfigUtil.getDefaultUnitComboWithBaseValue(strQtyUnitId + "^"+ strQtyBaseVal));

					/*if (voObj.getOfflineTariffUnit() != null
							&& voObj.getOfflineTariffUnit().size() > 0) {
						sb.append(new HisUtil("", "").getOptionValue(voObj
								.getOfflineTariffUnit(), strQtyUnitId + "^"
								+ strQtyBaseVal, "0^Select Value", false));
					} else {
						sb.append("<option value='0'>Select Value</option>");
					}*/

					sb.append("</select>");
					sb.append("</td>");

					sb
					.append("<td class='multiControl'><input type='hidden' class='txtFldMin' name='strTariffServiceTaxAmt' id='strTariffServiceTaxAmt"
							+ trfIndex
							+ "' value='0' disabled='disabled'><div id='strTariffServiceTaxAmtDivId"
							+ trfIndex + "'>0.00</div></td>");
					
					sb
							.append("<td class='multiControl'><input type='hidden' class='txtFldMin' name='strTariffDiscountAmt' id='strTariffDiscountAmt"
									+ trfIndex
									+ "' value='0' disabled='disabled'><table align='center'><tr><td class='multiControl'><div id='strTariffDiscountAmtDivId"
									+ trfIndex + "'>0.00</div></td><td class='multiControl'><img name='tariffDiscountConf' id='tariffDiscountConf"+trfIndex+"'");
					sb
					.append("style='cursor: hand; cursor: pointer' src='../../hisglobal/images/plus.gif' onClick='getOnLineWithoutCrNoTariffDiscountDetails(\""+trfIndex+"\",this);'>");
					sb
					.append("<input type='hidden' name='strTariffDiscountConfigDetails' value='' id='strTariffDiscountConfigDetails"+trfIndex+"' disabled='disabled'></td></tr></table></td>");
					sb
							.append("<td class='multiControl' style='font-weight:bold'><input type='hidden' class='txtFldMin' name='strTariffNetAmt' id='strTariffNetAmt"
									+ trfIndex
									+ "' value='0' disabled='disabled'><div id='strTariffNetAmtDivId"
									+ trfIndex + "'>0.00</div></td>");
					sb.append("</tr>");

				}
				sb.append("</table>");

			}else{
				
				sb
				.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
		sb.append("<tr>");
		sb.append("<td class='multiControl' colspan='8' style='color:red;font-weight:bold'>No Tariff(s) Detail Available</td>");
		sb.append("</tr>");
		sb.append("</table>");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getWithoutCrNoOnLineTariffDetailsView()-->", e
							.getMessage());
		}

		return sb.toString();
	}
	
	
	
	/**
	 * returns the required Off-line Client Details View (HTML) in String
	 * format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return - required Online Client Details View
	 */
	public static String getOffLinePackageDetailsView(
			EstEnquiryTransVO voObj) {

		StringBuffer sb = new StringBuffer("");
		String index = voObj.getStrOffLinePackageIndex();
		try {
			sb
					.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td class='multiLabel' width='3%'> </td>");
			sb.append("<td class='multiLabel' width='18%'>Tariff Name</td>");
			sb.append("<td class='multiLabel' width='8%'>Rate/Unit</td>");
			sb
					.append("<td class='multiLabel' width='8%'>Package Quantity</td>");
			sb.append("<td class='multiLabel' width='8%'>Quantity</td>");
			sb.append("<td class='multiLabel' width='8%'>Each Unit</td>");
			sb.append("<td class='multiLabel' width='10%'>Net Amount</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb
					.append("<td class='multiControl'><input type='checkbox' name='pkgTariffChk"
							+ index + "'/>");
			sb.append("</td>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("<td class='multiControl'/>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td colspan='6' class='LABEL'>Total </td>");
			sb.append("<td class='CONTROL'/>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td colspan='7' align='center'>");
			sb
					.append("<table border='0' width='100%' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb
					.append("<td align='right'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/add_tab.gif' onClick=''/></td>");
			sb
					.append("<td align='left'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/btn-ccl.png' onClick='hidePkgDtls(\""
							+ index + "\");'/></td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getonTariffClientDetailsView()-->",
					e.getMessage());
		}

		return sb.toString();
	}

	/**
	 * returns Off-Line Tariff Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return required Off-Line Tariff Details View.
	 * @throws Exception
	 */
	public static String getTariffDetails(EstEnquiryTransVO voObj)
			throws Exception {
		StringBuffer sBuffer = new StringBuffer("");
		WebRowSet tariffDetails = voObj.getOfflineBillTariffList();
		try {
			HisUtil util = new HisUtil("", "");
			String app_id = "";
			String strUnitId = "";
			String strBaseUnitVal = "";
			int indxTrf = 1;
			sBuffer
					.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Tariff Details</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'></td>");
			sBuffer
					.append("<td width='15%' class='multiLabel'>Tariff Name</td>");
			sBuffer
					.append("<td width='10%' class='multiLabel'>Balance Qty</td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'>Refund Type</td>");
			sBuffer
					.append("<td width='14%' class='multiLabel'>Penalty (%)</td>");
			sBuffer
					.append(" <td width='10%' class='multiLabel'>Refund Qty</td>");
			sBuffer.append(" <td width='10%' class='multiLabel'>Unit</td>");
			sBuffer
					.append(" <td width='10%' class='multiLabel'>Refund Cost(in Rs)");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalServiceTaxAmount' value='0' >");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalDiscountAmount' value='0' >");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalPenaltyAmount' value='0' >");
			sBuffer
					.append("<input type='hidden' name='strOfflineRefundTotalActualTariffAmount' value='0' >");
			sBuffer.append("</td>");
			sBuffer.append("</tr></table>");

			if (tariffDetails != null && tariffDetails.size() != 0) {

				while (tariffDetails.next()) {

					String TariffName = tariffDetails.getString(1);

					app_id = tariffDetails.getString(8) + "^"
							+ tariffDetails.getString(3);

					strUnitId = app_id.replace("^", "#").split("#")[4];

					strBaseUnitVal = app_id.replace("^", "#").split("#")[13];

					sBuffer
							.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
					sBuffer.append("<input type='hidden' name='lnkVal' value='"
							+ app_id + "'>");

					sBuffer
							.append("<tr><td width='5%' class='multiLabel'><input type='checkbox' name='strBillTariffVal'id='strBillTariffVal"
									+ indxTrf
									+ "' value='"
									+ app_id
									+ "' onClick='initBillTariff(this,\""
									+ indxTrf
									+ "\",\""
									+ tariffDetails.getString(2)
									+ "\");'></td>");
					sBuffer.append("<td width='15%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' name='strOfflineRefundServiceTaxAmount' id='strOfflineRefundServiceTaxAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundDiscountAmount' id='strOfflineRefundDiscountAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundPenaltyAmount' id='strOfflineRefundPenaltyAmount"
									+ indxTrf + "' value='0' >");
					sBuffer
							.append("<input type='hidden' name='strOfflineRefundActualTariffAmount' id='strOfflineRefundActualTariffAmount"
									+ indxTrf + "' value='0' >");

					sBuffer
							.append("<a name='strBillTariffName' id='strBillTariffName"
									+ indxTrf
									+ "' STYLE='CURSOR:POINTER; color:blue' value='"
									+ app_id
									+ "'  onClick='showTariffPopup(this,\""
									+ app_id + "\");'>" + TariffName + "</a>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");
					sBuffer.append(tariffDetails.getString(2));
					sBuffer.append("</td>");
					sBuffer.append("<td width='19%' class='multiControl'>");

					sBuffer
							.append("<select class='comboNormal' name='strBillTariffPenaltyType' id='strBillTariffPenaltyType"
									+ indxTrf
									+ "' disabled='disabled' onChange='setPenalty(this,"
									+ indxTrf
									+ "),calcBillTariffRefundAmt(\""
									+ indxTrf
									+ "\",\""
									+ app_id
									+ "\");'><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='14%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' class='txtFldMin' name='strBillTariffPenalty' id='strBillTariffPenalty"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);' ><div id='strBillTariffPenaltyDivId"
									+ indxTrf + "'>0</div>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<input type='text' class='txtFldMin' name='strBillTariffRefund' id='strBillTariffRefund"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);'  disabled='disabled' onkeyup='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<select class='comboMin' name='strBillTariffUnit' id='strBillTariffUnit"
									+ indxTrf
									+ "' disabled='disabled' onChange='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'> ");

					voObj.setStrOffLineTariffUnitTempId(strUnitId);

					/*EstEnquiryTransDAO.getOffLineTariffUnitList(voObj);

					// System.out.println("unit Val : "+strUnitId);

					sBuffer.append(util.getOptionValue(voObj
							.getOfflineTariffUnit(), strUnitId + "^"
							+ strBaseUnitVal, "", false));*/
					
					sBuffer.append(BillConfigUtil.getDefaultUnitComboWithBaseValue(strUnitId + "^"+ strBaseUnitVal));

					sBuffer.append("</select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='10%' class='multiControl'>");

					sBuffer
							.append("<input type='hidden' class='txtFldMin' disabled='disabled' name='strBillTariffRefundAmount' id='strBillTariffRefundAmount"
									+ indxTrf
									+ "' value='0' style='font-weight: bold'><div id='strBillTariffRefundAmountDivId"
									+ indxTrf
									+ "' style='font-weight:bold'>0.00</div></td> ");

					indxTrf++;

				}
				sBuffer.append("</tr></table>");
			} else {

				sBuffer
						.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'><tr><td colspan='9' class='multiControl' style='color:red;font-weight:bold'>No Tariff(s) Available</td></tr></table>");

			}

		} catch (Exception e) {

			throw new Exception("Cash Collection Trans"
					+ "CashCollectionTransHLP.getTariffDetails()-->"
					+ e.getMessage());
			
		}
		return sBuffer.toString();

	}

	
		
	
	/**
	 * returns Off-Line Bill Tariff Pop up Details View (HTML) in String Format.
	 * 
	 * @param voObj -
	 *            Value Object
	 * @return required Off-Line Bill Tariff Pop up Details View
	 */
	public static String getPopUp(EstEnquiryTransVO voObj) {
		StringBuffer sb = new StringBuffer("");
		String strValmode = voObj.getStrOffLineTariffDetailsHiddenValue();
		String temp = "";
		String val[] = strValmode.replace('^', '#').split("#");
		String strRatePerUnit = val[3] + "/" + val[12];

		try {
			if (strValmode != null) {
				if (val[9].equals("2")){
					
					temp = "Percentage";
				}else{
					
					temp = "Fixed";
				}
					

				sb
						.append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");
				sb
						.append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");
				sb
						.append("<td align='right'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"offlineBillTariffPopupDetailsDivId\");'> </td></tr>");
				sb.append("</table> ");
				sb
						.append("<table width='400' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb
						.append("<td class='multiLabel' width='20%'>Rate(Rs)/Unit</td>");
				sb.append("<td class='multiControl' width='20%'>"
						+ strRatePerUnit + "</td>");
				sb
						.append("<td class='multiLabel' width='20%'>Discount(Rs)</td>");
				sb.append("<td class='multiControl' width='20%'>" + val[8]
						+ "</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb
						.append("<td class='multiLabel' width='20%'>Discount Type</td>");
				sb.append("<td class='multiControl' width='20%'>" + temp
						+ "</td>");
				sb
						.append("<td class='multiLabel' width='20%'>Service Tax(%)</td>");
				sb.append("<td class='multiControl' width='20%'>" + val[7]
						+ "</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<table width='400' align='center'>");
				sb.append("<tr class='FOOTER'>");
				sb.append("<td colspan='3'>&nbsp;</td>");
				sb.append("</tr></table>");
			} else {
				sb.append("<table width='400' align='center'>");
				sb.append("<tr class='FOOTER'>");
				sb.append("<td colspan='3'>&nbsp;</td>");
				sb.append("</tr></table>");
			}
		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getPopUpDtl()-->", e.getMessage());
		}

		return sb.toString();

	}

	public static String getRefundTariffPopUp(EstEnquiryTransVO voObj) {
		StringBuffer sb = new StringBuffer("");

		String strValmode = voObj.getStrRefundTariffHiddenValue();

		String val[] = strValmode.replace('^', '#').split("#");

		String strRateUnit = val[3] + "/" + val[12];
		String strDiscountUnit = val[8];
		String strDiscountType = val[9];
		String strServiceTax = val[7];
		String strPenalty = val[16];

		if (strDiscountType.equals("2")) {
			strDiscountUnit = strDiscountUnit + " %";
		} else {
			strDiscountUnit = strDiscountUnit + " Fixed";
		}

		try {

			sb
					.append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");
			sb
					.append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");
			sb
					.append("<td align='right'><img  style='cursor: hand; cursor: pointer'    src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"refundTariffPopupDetailsDivId\");'> </td></tr>");
			sb.append("</table> ");
			sb.append("<table width='400' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb
					.append("<td class='multiLabel' width='25%'>Rate(Rs)/Unit</td><td class='multiControl'  width='25%'>"
							+ strRateUnit + "</td>");
			sb
					.append("<td class='multiLabel'  width='25%'>Discount/ Unit</td> <td class='multiControl'  width='25%'>"
							+ strDiscountUnit + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb
					.append("<td class='multiLabel'  width='25%'>Service Tax (%) </td> <td class='multiControl'  width='25%'>"
							+ strServiceTax + "</td>");
			sb
					.append("<td class='multiLabel'  width='25%'>Penalty (%)</td> <td class='multiControl'  width='25%'>"
							+ strPenalty + "</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='400' align='center' cellspacing='1px'>");
			sb.append("<tr class='FOOTER'>");
			sb.append("<td colspan='3'>&nbsp;</td>");
			sb.append("</tr></table>");
		} catch (Exception e) {

			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getPopUpDtl()-->", e.getMessage());
		}

		return sb.toString();

	}

	public static String getGuarantorDetails(EstEnquiryTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		String strGaurantorVals = voObj.getStrGuarantorHiddenVal().trim();
		
		String[] strGaurantorVal = strGaurantorVals.replace("^", "#")
				.split("#");

		String strContact = strGaurantorVal[2];
		
		if(strContact.length() < 2){
			strContact = " ";
		}
		
		String strAge = strGaurantorVal[3] ;
		
		if(strAge.trim().equals("0")){
			
			strAge = "/";
			
		}else if(strGaurantorVal[4].equals("1")){
			
			strAge = strAge + " Year(s)";
			
		}else if(strGaurantorVal[4].equals("2")) {
			
			strAge = strAge + " Month(s)";
			
		}else{
						
			strAge = strAge + " Day(s)";
		}
		
		
		String strGender = "/";
		
		
		if(strGaurantorVal[6].trim().equals("1")){
			
			strGender = "Male";
			
		}else if(strGaurantorVal[6].trim().equals("2")) {
			
			strGender = "Female";
			
		}else  if(strGaurantorVal[6].trim().equals("3")){
						
			strGender = "Ambiguous";
		}
		
		
		sb
				.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
		sb.append("<tr>");
		sb.append("<td colspan='4' class='TITLE'>Patient Details");
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb
				.append("<td width='25%' class='LABEL' >Previous CR No.</td>");
		 
		sb.append("<td width='75%' class='CONTROL' colspan='3'>"
				+ strGaurantorVal[7] + "</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb
				.append("<td width='25%' class='LABEL' >Patient Name</td>");
		sb
				.append("<td width='25%'  class='CONTROL'><input type='hidden' name='strGuarantorHiddenVal' value='"
						+ voObj.getStrGuarantorHiddenVal()
						+ "'>"
						+ strGaurantorVal[0] + "</td>");
		
		sb.append("<td width='25%' class='LABEL'>Contact No.</td>");
		sb.append("<td width='25%' class='CONTROL' >"
				+ strContact + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td width='25%' class='LABEL'>Patient Address");

		sb.append("<input type='hidden' name='xmlVal' id='xmlValId' value='"
				+ voObj.getStrOffLineRefundPenalty() + "'></td>");

		sb.append("<td width='75%' colspan='3' class='CONTROL'>"
				+ strGaurantorVal[1] + "</td>");
		sb.append("</tr>");
		
	
		sb.append("<tr> ");
		sb.append("<td width='25%'  class='LABEL'>Referring Physician</td> ");
		sb.append("<td width='75%' colspan='3' class='CONTROL'>"+strGaurantorVal[5]+"<input type='hidden' class='txtFldMax' name='strReferringPhysician' value='"+strGaurantorVal[5]+"'> </td> ");
		sb.append("</tr> ");
		sb.append("<tr> ");
		sb.append("<td width='25%' class='LABEL' >Age</td> ");
		sb.append("<td width='25%' class='CONTROL'> "+ strAge);
		
		sb.append("</td> ");
		sb.append("<td width='25%' class='LABEL' >Gender</td> ");
		sb.append("<td width='25%' class='CONTROL' >"+strGender+"");
		sb.append("</td> ");
		sb.append("</tr> ");
		sb
		.append("<td width='25%' class='LABEL' >Department </td>");
 
sb.append("<td width='75%' class='CONTROL' colspan='3'>"
		+ strGaurantorVal[9] + " <input type='hidden' name='strGarantorDepartmentId' value='"+ strGaurantorVal[8] +"'> </td>");
sb.append("</tr>");
		
		sb.append("</table>");

		return sb.toString();
	}

	public static String getBillListingView(EstEnquiryTransVO voObj) {

		StringBuffer sb = new StringBuffer("");

		WebRowSet ws = voObj.getBillSearchList();

		try {

			int start = Integer.parseInt(voObj.getStrBillFromRow());
			int actualBlockSet = Integer.parseInt(voObj.getStrBillCtBlockSet());

			final int REC_PER_PAGE = Integer.parseInt(voObj
					.getStrBillRowPerPage());
			final int PAGE_PER_BLOCK = 10;

			int totalFetchRecord = (REC_PER_PAGE * PAGE_PER_BLOCK) + 1;
			int totalRecordsToManipulate = totalFetchRecord - 1;

			int previous = 1;
			int next = 0;

			if (ws != null) {

				if (ws.size() != 0) {

					int actualFetchRecord = ws.size();

					if (actualBlockSet == 1)
						actualBlockSet = actualBlockSet + 1;

					next = ((actualBlockSet * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;
					previous = (((actualBlockSet - 2) * PAGE_PER_BLOCK) - PAGE_PER_BLOCK) + 1;

					if (totalFetchRecord != actualFetchRecord) {

						totalFetchRecord = actualFetchRecord;
						totalRecordsToManipulate = actualFetchRecord;
						next = 0;

					}

					int totalLayer = totalRecordsToManipulate / REC_PER_PAGE;
					int reminder = totalRecordsToManipulate % REC_PER_PAGE;

					if (reminder > 0)
						totalLayer = totalLayer + 1;

					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td class='LABEL'>&nbsp;");

					if (start != 1) {
						sb
								.append("<a STYLE='CURSOR:POINTER'  onClick='fetchPatientList(\""
										+ previous
										+ "\",\""
										+ (actualBlockSet - 1)
										+ "\");'> <FONT COLOR='"
										+ strColor
										+ "'> &lt;&lt; Previous</FONT> </a> &nbsp;");

					}
					for (int i = 1; i <= totalLayer; i++) {

						if (i == 1) {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ strColor + "' name='linId' id='linId" + i
									+ "' onClick='layerIndexNavigator(\"" + i
									+ "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));

						} else {
							sb.append("<a STYLE='CURSOR:POINTER; color:"
									+ defaultColor + "' name='linId' id='linId"
									+ i + "' onClick='layerIndexNavigator(\""
									+ i + "\",\"" + totalLayer + "\")'>");
							sb.append((i + start - 1));
						}
						sb.append("</a> &nbsp;");
					}

					if (next > 0)
						sb
								.append("<a STYLE='CURSOR:POINTER' onClick='fetchPatientList(\""
										+ next
										+ "\",\""
										+ (actualBlockSet + 1)
										+ "\");'> <FONT COLOR='"
										+ strColor
										+ "'> Next &gt;&gt;</FONT></a>");

					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					/*
					 * sb .append("<table width='100%'align='center'
					 * cellspacing='1px'>");
					 * 
					 * sb.append("<tr>"); sb.append("<td class='LABEL'>&nbsp;");
					 * 
					 * if (start != 1) { sb.append("<a href='#'
					 * onClick='fetchBillList(\"" + previous + "\",\"" +
					 * (actualBlockSet - 1) + "\");'> &lt;&lt; Previous</a>
					 * &nbsp;"); } for (int i = 1; i <= totalLayer; i++) {
					 * sb.append("<a href='#' onClick='layerIndexNavigator(\"" +
					 * i + "\",\"" + totalLayer + "\")'>" + (i + start - 1) + "</a>
					 * &nbsp;"); }
					 * 
					 * if (next > 0) sb.append("<a href='#'
					 * onClick='fetchBillList(\"" + next + "\",\"" +
					 * (actualBlockSet + 1) + "\");'> Next &gt;&gt;</a>");
					 * 
					 * sb.append("</td>"); sb.append("</tr>"); sb.append("</table>");
					 */
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");

					sb.append("<tr>");
					sb.append("<td width='5%'class='multiLabel'>");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Previous CR No.");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Patient Name");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Receipt No.");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Receipt Date");
					sb.append("</td>");
					sb.append("<td width='18%'class='multiLabel'>Receipt Amount");
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("</table>");

					for (int i = 1; i <= totalLayer; i++) {

						if (i < totalLayer
								|| (i == totalLayer && reminder == 0)) {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}

							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							for (int j = 0; j < REC_PER_PAGE; j++) {
								ws.next();
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strBillNo' value='");
								sb.append(ws.getString(2));
								sb.append("'></td>");
								sb
								.append("<td class='multiControl' width='18%'>");
						sb.append(ws.getString(5));
						sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(4));
								sb.append("</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						} else {
							if (i == 1) {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:block'>");
							} else {
								sb.append("<div id='tariffDivId" + i
										+ "' style='display:none'>");
							}
							sb
									.append("<table width='100%'align='center' cellspacing='1px'>");
							for (int k = 0; k < reminder; k++) {
								ws.next();
								sb
										.append("<tr ><td class='multiLabel' width='5%'><input type='radio' name='strBillNo' value='");
								sb.append(ws.getString(2));
								sb.append("'></td>");
								sb
								.append("<td class='multiControl' width='18%'>");
						sb.append(ws.getString(5));
						sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(1));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(2));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(3));
								sb.append("</td>");
								sb
										.append("<td class='multiControl' width='18%'>");
								sb.append(ws.getString(4));
								sb.append("</td>");
								sb.append("</tr>");
							}
							sb.append("</table>");
							sb.append("</div>");

						}

					}

					sb.append("</td>");
					sb.append("</'tr>");

					sb.append("</table>");

				} else {
					sb
							.append("<table width='100%'align='center' cellspacing='1px'>");
					sb
							.append("<tr><td class='multiControl'><font color='red'>No Matching Record Found</font></td></tr>");
					sb.append("</table>");
				}

			} else {

				throw new Exception("Patient List WebRowSet is Null");

			}

		} catch (Exception e) {
			new HisException("Cash Collection Trans",
					"CashCollectionTransHLP.getPatientListingView()-->", e
							.getMessage());
		}

		return sb.toString();

	}

}
