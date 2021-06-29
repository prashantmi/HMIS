package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class BillReconcileTransHLP {

	/**
	 * To get bill details
	 * 
	 * @param vo
	 * @return
	 */
	public static String getBillDetails(BillReconcileTransVO vo) {
		// String s=vo.getHspServ();
		StringBuffer sBuffer = new StringBuffer("");
		WebRowSet wb = null;

		try {

			wb = vo.getBillDtlList();

			sBuffer
					.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' cellpadding='1px' align='center'>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'>#</td>");
			sBuffer.append("<td width='19%' class='multiLabel'>Bill No.</td>");
			sBuffer.append("<td width='19%' class='multiLabel'>Bill Date</td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'>Hospital Service</td>");
			sBuffer.append("<td width='21%' class='multiLabel'>Bill Type</td>");
			sBuffer.append(" <td width='17%' class='multiLabel'>Bill Amt</td>");
			sBuffer.append("</tr>");
			
			if(wb != null && wb.size() > 0){
			while (wb.next()) {

				sBuffer
						.append("<tr><td width='5%' class='multiLabel'><input type='radio' name='strBillDetailsValue' value='"
								+ wb.getString(6)
								+ "' onClick='groupCheck(this);'></td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer.append(wb.getString(1));
				sBuffer.append("</td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer.append(wb.getString(2));
				sBuffer.append("</td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer.append(wb.getString(3));
				sBuffer.append("</td>");
				sBuffer.append("<td width='21%' class='multiControl'>");
				sBuffer.append(wb.getString(4));
				sBuffer.append("</td>");
				sBuffer.append("<td width='17%' class='multiControl'>");
				sBuffer.append(wb.getString(5));
				sBuffer.append("</td></tr>");
			}
			}else{
				sBuffer.append("<tr><td colspan='6' class='multiControl' style='color:red;font-weight : bold' >No Reconcile Bill(s) Available</td>");
			}
			sBuffer.append("</table>");
		} catch (Exception e) {

			new HisException("Bill Reconciliation Transaction",
					"BillReconcileTransHLP.getBillDetails()-->", e.getMessage());
		}

		return sBuffer.toString();
	}

	public static String getPkgLst(BillReconcileTransVO vo) {

		WebRowSet wb = vo.getBServiceDtls();

		StringBuffer sBuffer = new StringBuffer("");
		try {
			String PkgName = "";
			sBuffer
					.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' cellpadding='1px' align='center'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Package Details</td></tr>");
			sBuffer
					.append("<tr><td width='5%' class='multiLabel'><div align='center'></div></td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'><div align='center'>Package Name</div></td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'><div align='center'>Discount/unit</div></td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'><div align='center'>Service Tax</div></td>");
			sBuffer
					.append("<td width='19%' class='multiLabel'><div align='center'>Refund Type</div></td>");
			sBuffer
					.append(" <td width='19%' class='multiLabel'><div align='center'>Penalty(%)</div></td>");
			sBuffer.append("</tr>");
			for (int i = 0; wb.next(); i++) {
				String temp[] = (wb.getString(8)).replace('^', '#').split("#");
				PkgName = PkgName + "^" + wb.getString(1);
				sBuffer.append("<input type='hidden' name='divVal' value='"
						+ (i + 1) + "'>");
				sBuffer
						.append("<tr><td width='5%' class='multiLabel'><input type='checkbox' name='c' value="
								+ wb.getString(8)
								+ " onClick='pack(this,"
								+ i
								+ ");'></td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer.append(wb.getString(1));
				sBuffer.append("</td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer.append(temp[8]);
				sBuffer.append("</td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer.append(temp[7]);
				sBuffer.append("</td>");
				sBuffer.append("<td width='19%' class='multiControl'>");
				sBuffer
						.append("<select name='pkg_refundType' onChange='pkg_penaltyCalc(this,"
								+ i
								+ ");'><option value='1'>Hospital Choice</option><option value='2'>Patient Choice</option></select>");
				sBuffer.append("</td>");
				sBuffer.append("<td width='15%' class='multiControl'>");
				sBuffer
						.append("<input type='textbox' class='txtFldMin' name='pkg_penalty' value='0'  readonly>");
				sBuffer.append("</td>");
				sBuffer.append("</tr>");
			}
			sBuffer.append("</table>");
		} catch (Exception e) {
			new HisException("Bill Reconcile Trans",
					"BillReconcileTransHLP.getPkgLst()-->", e.getMessage());
		}
		return sBuffer.toString();
	}

	/**
	 * To get Group details in IPD Bill (consolidated details) section
	 * 
	 * @param vo
	 * @return
	 */
	public static String getFinalSettlementDtlsList(BillReconcileTransVO vo) {

		WebRowSet wb = null;
		StringBuffer sBuffer = new StringBuffer("");

		try {
			// String GrpName = "";
			wb = vo.getBServiceDtls();

			sBuffer
					.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' cellpadding='1px' align='center'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Final Settlement (Consolidated Details)</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'>S.No.</td>");
			sBuffer
					.append("<td width='29%' class='multiLabel'>Group Name</td>");
			sBuffer
					.append("<td width='28%' class='multiLabel'>Total Amt.</td>");
			sBuffer
					.append("<td width='28%' class='multiLabel'>Reconcile Amt.</td>");
			sBuffer.append("<td width='10%' class='multiLabel'>Details</td>");
			sBuffer.append("</tr></table>");

			if(wb != null & wb.size() > 0){
			for (int i = 0; wb.next(); i++) {
				// System.out.println("grp id--->"+wb.getString(7));
				// int k = i + 1;
				// GrpName = GrpName + "^" + wb.getString(1);
				sBuffer
						.append("<input type='hidden' name='divOpenFlag' id='divOpenFlag"
								+ (i + 1) + "' value='0'>");
				
				sBuffer
				.append("<input type='hidden' name='strGroupListLen' id='strGroupListLen"
						+ (i + 1) + "' value='0'>");
				
				// having tariff details corresponding to group
				// [trfHiddenValue@qty@unit@cost#trfHiddentValue1...]
				
				sBuffer
						.append("<input type='hidden' name='strGroupId' id='strGroupId"
								+ (i + 1)
								+ "' value='"
								+ wb.getString(7)
								+ "'>");

				sBuffer
						.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
				sBuffer.append("<tr><td width='5%' class='multiLabel'>"
						+ (i + 1) + "</td>");
				sBuffer.append("<td width='29%' class='multiControl'>");
				sBuffer.append(wb.getString(1));
				sBuffer.append("</td>");
				sBuffer.append("<td width='28%' class='multiControl'>");
				sBuffer.append(wb.getString(6));
				sBuffer.append("</td>");
				sBuffer.append("<td width='28%' class='multiControl'>");
				sBuffer
						.append("<input type='hidden' name='strBillTariffRefundAmount' id='strBillTariffRefundAmount"
								+ (i + 1) + "' value='0.0'>");
				sBuffer.append("<div id='billTrfAmtDivId" + (i + 1)
						+ "'>0.0</div>");
				sBuffer.append("</td>");

				sBuffer
						.append("<td width='10%' class='multiControl'><img style='cursor:pointer;cursor:hand;' "
								+ "src='../../hisglobal/images/viewDetails.gif' title='click here for group details' "
								+ "align='absmiddle' onClick='callMe("
								+ (i + 1) + ");'/></td>");

				/*
				 * sBuffer .append("<td width='10%' class='multiControl'><input
				 * type='button' onClick='callMe(" + (i + 1) + ");'></td>");
				 */
				sBuffer.append("</tr></table>");
				/*
				 * sBuffer.append("<div id='p" + (i+1) + "'
				 * style='display:none'></div>");
				 */
				sBuffer.append("<div id='pt" + (i + 1)
						+ "' style='display:none'></div>");
			}
			// for checking which div is open
			sBuffer.append("<input type='hidden' name='flag' value='0'>");
			sBuffer
					.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
			sBuffer
					.append("<tr><td width='85%' class='LABEL'>Total Amount (<img src='../../hisglobal/images/INR.png'>)-></td>");
			sBuffer.append("<td  class='multiControl' width='15%'>");
			sBuffer
					.append("<input type='hidden' class='txtFldNormal' name='strReconcileTariffAmount' value='0'>"
							+ "<div id='totalRecTrfAmtDivId'>0.0</div>");

			sBuffer.append("</td></tr>");
			sBuffer.append("</table>");
			
			}else{
				
				sBuffer
				.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' align='center'>");
				sBuffer.append("<tr><td colspan='6' class='multiControl' style='color : red; font-weight:bold'>");
				sBuffer.append("No Final Settlement Details Available</td></tr>");
				sBuffer.append("</table>");
				
			}
			
			
		} catch (Exception e) {
			new HisException("Bill Reconciliation Transaction",
					"BillReconcileTransHLP.getFinalSettlementDtlsList()-->", e.getMessage());
		} finally {
			if (wb != null) {
				try {
					wb.close();
				} catch (Exception e) {
				}

				wb = null;
			}
		}

		return sBuffer.toString();
	}

	/**
	 * To get group service details for IPD Bill (Final Settlement)
	 * 
	 * @param vo
	 * @param strValmode
	 * @return
	 */
	public static String getFinalSettlementDtlsView(BillReconcileTransVO vo, String index) {

		// System.out.println("getFSetDtls ");

		HisUtil util = null;
		WebRowSet wb = null;
		StringBuffer sBuffer = null;
		int divIndx = 0;
		String uniqueIndex = "";

		// String temp[] = strValmode.replace("^", "#").split("#");
		try {

			divIndx = Integer.parseInt(index);
			sBuffer = new StringBuffer("");

			util = new HisUtil("Billing",
					"BillReconcileTransHLP.getTariffDetails();");

			wb = vo.getFServiceDtls();

			int pop_id = 2 + divIndx;

			/*
			 * sBuffer.append("<input type='hidden' name='recVal' value=" +
			 * wb.size() + ">");
			 */
			sBuffer
					.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#000000' cellspacing='1px' cellpadding='1px'>");
			sBuffer
					.append("<tr><td width='5%' class='multiLabel'><div align='center'></div></td>");
			sBuffer
					.append("<td width='20%' class='multiLabel'><div align='center'>Tariff Name</div></td>");
			sBuffer
					.append("<td width='20%' class='multiLabel'><div align='center'>Req No./Date</div></td>");
			sBuffer
					.append("<td width='10%' class='multiLabel'><div align='center'>Balance Qty</div></td>");
			sBuffer
					.append("<td width='15%' class='multiLabel'><div align='center'>Reconcile Qty</div></td>");
			sBuffer
					.append("<td width='15%' class='multiLabel'><div align='center'>Unit</div></td>");
			sBuffer
					.append("<td width='15%' class='multiLabel'><div align='center'>Reconcile Amt(Rs)</div></td>");
			sBuffer.append("</tr></table>");

			if(wb.size() > 0){
			
			for (int i = 0; wb.next(); i++) {
				String strtemp[] = (wb.getString(10)).replace("^", "#").split(
						"#");

				vo.setStrTariffUnitTempId(strtemp[14]); // qty's unit id

				uniqueIndex = divIndx + "-" + (i + 1);
				/*
				 * sBuffer.append("<input type='hidden' name='refundVal'
				 * value='" + wb.getString(10) + "'>");
				 */

				sBuffer
						.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#000000' cellspacing='1px' cellpadding='1px'>");
				sBuffer
						.append("<tr><td width='5%' class='multiLabel'><input type='checkbox' name='strBillTariffVal' id='strBillTariffVal"
								+ uniqueIndex
								+ "' value=\""
								+ wb.getString(10)+"^"+wb.getString(5)
								+ "\" onClick='ipdTrfChk(this,\""
								+ uniqueIndex
								+ "\",\"" + wb.getString(5) + "\");'></td>");

				sBuffer.append("<td width='20%' class='multiControl'>");
							
				sBuffer
						.append("<a STYLE='CURSOR:POINTER; color:blue' name='strBillTariffName"
								+ "' id='trfName"
								+ uniqueIndex
								+ "' value='"+wb.getString(4)+"' onClick='myFunc(this,\""
								+ wb.getString(10)
								+ "\" ,"
								+ pop_id
								+ ");'>"
								+ wb.getString(4) + "</a>");

				sBuffer.append("</td>");
				sBuffer.append("<td width='20%' class='multiControl'>");
				sBuffer.append(strtemp[0] + "/" + wb.getString(1));
				sBuffer.append("</td>");
				sBuffer.append("<td width='10%' class='multiControl'>");
				sBuffer.append(wb.getString(5));
				sBuffer.append("</td>");
				sBuffer.append("<td width='15%' class='multiControl'>");
				sBuffer
						.append("<input type='textbox' class='txtFldMin' name='strBillTariffRefund' id='strBillTariffRefund"
								+ uniqueIndex
								+ "' value="
								+ 0
								+ " maxlength='3' onkeypress='return validateData(event,5);' onKeyUp='ipdTrfQty(\""
								+ uniqueIndex
								+ "\",\""
								+ wb.getString(10)
								+ "\");' disabled>  ");
				sBuffer.append("</td>");
				sBuffer.append("<td width='15%' class='multiControl'>");
				sBuffer.append(" <select class='comboMin' name='strBillTariffUnit" 
						+ "' id='strBillTariffUnit" + uniqueIndex
						+ "' onChange='ipdTrfQty(\"" + uniqueIndex + "\",\""
						+ wb.getString(10) + "\");' disabled>");

				// to get unit data
				BillReconcileTransDAO.unit(vo);

				// System.out.println("unit val : "+
				// vo.getStrTariffUnitTempId());

				if (vo.getTariffUnitList() != null
						&& vo.getTariffUnitList().size() > 0){
					sBuffer.append(util.getOptionValue(vo.getTariffUnitList(),
							strtemp[14], "", false));
				}else{
					sBuffer.append("<option id = 0>Select Unit</option>");
				}
				
				sBuffer.append("</select> ");
				sBuffer.append("</td>");
				sBuffer.append("<td width='15%' class='multiControl'>");
				sBuffer.append("<input type='hidden' name='reconcileCost"
						+ divIndx + "' id='reconcileCost" + uniqueIndex
						+ "' value='0.0'>");
				sBuffer.append("<div id = 'reconcileCostDivId" + uniqueIndex
						+ "'>0.0</div>");
				sBuffer.append("</td>");
				sBuffer.append("</tr></table>");

				vo.getTariffUnitList().beforeFirst();

				sBuffer
				.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#000000' cellspacing='1px' cellpadding='1px'>");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='6' class='multiLabel'>");
		sBuffer.append("<div align='center'>");
		sBuffer.append("<img src='../../hisglobal/images/btn-add.png' "
				+ "style='cursor:pointer;cursor:hand;'"
				+ "name='ok' onclick='ipdTrfSelOk(" + divIndx + ");'> &nbsp; ");
		sBuffer.append("<img src='../../hisglobal/images/Cancel.gif' "
				+ "style='cursor:pointer;cursor:hand;' "
				+ "name='can' onclick='ipdTrfSelCan(" + divIndx + ");'>");

		sBuffer.append("</div></td>");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
				
			}
			}else{
				
				sBuffer
				.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#000000' cellspacing='1px' cellpadding='1px'>");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='6' class='multiControl'><font color='red'>No Record(s) Available</font></td></tr></table>");
		
		sBuffer
		.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='#000000' cellspacing='1px' cellpadding='1px'>");
		sBuffer.append("<tr>");
		sBuffer.append("<td colspan='6' class='multiLabel'>");
		sBuffer.append("<div align='center'>");
		/*sBuffer.append("<img src='../../hisglobal/images/btn-add.png' "
		+ "style='cursor:pointer;cursor:hand;'"
		+ "name='ok' onclick='ipdTrfSelOk(" + divIndx + ");'> &nbsp; ");*/
		sBuffer.append("<img src='../../hisglobal/images/Cancel.gif' "
		+ "style='cursor:pointer;cursor:hand;' "
		+ "name='can' onclick='ipdTrfSelCan(" + divIndx + ");'>");

		sBuffer.append("</div></td>");
		sBuffer.append("</tr>");
		sBuffer.append("</table>");
		
			}
			

			sBuffer.append("@@"+wb.size());
			
			
		} catch (Exception e) {
			new HisException("Bill Reconciliation Transaction",
					"BillReconcileTransHLP.getBillDetails()-->", e.getMessage());
		} finally {
			util = null;
			if (wb != null) {
				try {
					wb.close();
					wb = null;
				} catch (Exception e) {
				}
			}
		}
		
		
		
		return sBuffer.toString();
	}

	/*
	 * public static String getTariffDetails(BillReconcileTransVO vo) {
	 * StringBuffer sBuffer = new StringBuffer(""); BillReconcileTransBO bo= new
	 * BillReconcileTransBO();
	 * 
	 * WebRowSet wb = vo.getBServiceDtls();
	 * 
	 * try{ String app_id=""; sBuffer.append("<table width='85%' border='0'
	 * cellspacing='1px' align='center'>"); sBuffer.append("<tr><td colspan='9' class='TITLE'>Tariff
	 * Details</td></tr>"); sBuffer.append("<tr><td width='5%' class='multiLabel'><div
	 * align='center'></div></td>"); sBuffer.append("<td width='15%' class='multiLabel'><div
	 * align='center'>Tarriff Name</div></td>"); sBuffer.append("<td width='20%' class='multiLabel'><div
	 * align='center'>Non-Processed Qty</div></td>"); sBuffer.append("<td width='20%' class='multiLabel'><div
	 * align='center'>Reconcile Qty</div></td>"); sBuffer.append("<td width='20%' class='multiLabel'><div
	 * align='center'>Unit</div></td>"); sBuffer.append("<td width='20%' class='multiLabel'><div
	 * align='center'>Reconcile Cost</div></td>"); sBuffer.append("</tr></table>");
	 * for(int i=0;wb.next();i++) { app_id=wb.getString(8); String
	 * temp[]=app_id.replace('^', '#').split("#"); //
	 * System.out.println("unit_id"+temp[2]); bo.unit(vo,temp[2]); //
	 * System.out.println("remain qtyVal:"+temp[1]); String TariffName=
	 * wb.getString(1); sBuffer.append("<input type='hidden' name='lnkVal'
	 * value='"+app_id+"'>"); sBuffer.append("<table width='85%' border='0'
	 * cellspacing='1px' align='center'>"); sBuffer.append("<tr><td width='5%' class='multiLabel'><input
	 * type='checkbox' name='trfVal' value='"+app_id+"'
	 * onClick='trfChk("+i+","+-1+");');'></td>"); sBuffer.append("<td width='15%' class='multiControl'>");
	 * sBuffer.append("<a name='trfName' value='"+app_id+"' href='#'
	 * onClick='myFunc(this,"+i+","+1+");'>"+TariffName+"</a>");
	 * sBuffer.append("</td>"); sBuffer.append("<td width='20%' class='multiControl'>");
	 * sBuffer.append(wb.getString(2)); sBuffer.append("</td>");
	 * sBuffer.append("<td width='20%' class='multiControl'>");
	 * sBuffer.append("<input type='textbox' class='txtFldMin'
	 * name='reconcileQty' value='0' disabled='disabled'>"); sBuffer.append("</td>");
	 * sBuffer.append("<td width='20%' class='multiControl'>");
	 * sBuffer.append("<select name='unit' onChange=''
	 * disabled='disabled'>"+vo.getStrUnitId()+"</select>"); sBuffer.append("</td>");
	 * sBuffer.append("<td width='20%' class='multiControl'>");
	 * sBuffer.append("<input type='textbox' class='txtFldMin'
	 * name='reconcileAmt' value='' disabled='disabled'> "); sBuffer.append("</td></tr></table>"); }
	 * sBuffer.append("<table width='85%' border='0' cellspacing='1px'
	 * align='center'>"); sBuffer.append("<tr><td width='80%' class='LABEL' ><font
	 * color='red'>Gross Reconciled Amount-></font></td>"); sBuffer.append("<td  class='multiControl'>");
	 * sBuffer.append("<input type='textbox' class='txtFldMax'
	 * name='trf_grossRefund' value='0' disabled='disabled'>"); sBuffer.append("</td></tr>");
	 * sBuffer.append("</table>"); } catch(Exception e) { new
	 * HisException("Bill Reconciliation
	 * Transaction","BillReconcileTransHLP.getTariffDetails()-->",e.getMessage()); }
	 * return sBuffer.toString(); }
	 */

	/**
	 * This function is used to display the tariff details in case of OPD/IPD
	 * service bill
	 */
	public static String getTariffDtlsList(BillReconcileTransVO voObj)
			throws Exception {

		StringBuffer sBuffer = new StringBuffer("");
		HisUtil util = null;
		WebRowSet tariffDetails = null;

		String app_id = "";
		String strUnitId = "";
		int indxTrf = 1;

		try {

			tariffDetails = voObj.getBServiceDtls();
			util = new HisUtil("BillReconcileTransHLP", "getTariffDetails();");

			sBuffer
					.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px' cellpadding='1px'>");
			sBuffer
					.append("<tr><td colspan='9' class='TITLE'>Tariff Details</td></tr>");
			sBuffer.append("<tr><td width='5%' class='multiLabel'></td>");
			sBuffer
					.append("<td width='30%' class='multiLabel'>Tariff Name</td>");
			sBuffer
					.append("<td width='15%' class='multiLabel'>Balance Qty</td>");

			sBuffer
					.append(" <td width='15%' class='multiLabel'>Reconcile Qty</td>");
			sBuffer.append(" <td width='15%' class='multiLabel'>Unit</td>");
			sBuffer
					.append(" <td width='25%' class='multiLabel'>Reconcile Cost(in Rs)</td>");

			sBuffer.append("</tr></table>");

			// hidden field having tariff detail data which will be used while
			// inserting the data
		
			if (tariffDetails != null && tariffDetails.size() > 0) {

				while (tariffDetails.next()) {

					String TariffName = tariffDetails.getString(1);

					app_id = tariffDetails.getString(8)+"^"+tariffDetails.getString(2); // hidden value in
															// procedure with balance Qty

					strUnitId = app_id.replace("^", "#").split("#")[2]; 

					sBuffer
							.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
					
					
					sBuffer.append("<tr><td width='5%' class='multiLabel'>"
							+ "<input type='checkbox' name='strBillTariffVal'"
							+ "id='strBillTariffVal" + indxTrf + "' "
							+ "value='" + app_id + "' "
							+ "onClick='initReconcileTariff(this,\"" + indxTrf
							+ "\",\"" + tariffDetails.getString(2)
							+ "\");'></td>");
					sBuffer.append("<td width='30%' class='multiControl'>");
					sBuffer
							.append("<a name='strBillTariffName' id='strBillTariffName"
									+ indxTrf
									+ "' STYLE='CURSOR:POINTER; color:blue' value='"
									+ app_id
									+ "' onClick='myFunc(this,\""
									+ app_id
									+ "\",\"1\");'>"
									+ TariffName
									+ "</a>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='15%' class='multiControl'>");
					sBuffer.append(tariffDetails.getString(2));
					sBuffer.append("</td>");

					sBuffer.append("<td width='15%' class='multiControl'>");

					sBuffer
							.append("<input type='textbox' class='txtFldMin' name='strBillTariffRefund' id='strBillTariffRefund"
									+ indxTrf
									+ "' value='0' onkeypress='return validateData(event,5);'  disabled='disabled' onkeyup='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='15%' class='multiControl'>");

					sBuffer
							.append("<select class='comboMin' name='strBillTariffUnit' id='strBillTariffUnit"
									+ indxTrf
									+ "' disabled='disabled' onChange='calcBillTariffRefundAmt(\""
									+ indxTrf + "\",\"" + app_id + "\");'> ");

					voObj.setStrTariffUnitTempId(strUnitId);

					// to get unit data
					BillReconcileTransDAO.unit(voObj);

					if (voObj.getTariffUnitList() != null
							&& (voObj.getTariffUnitList()).size() > 0) {
						sBuffer.append(util.getOptionValue(voObj
								.getTariffUnitList(), strUnitId, "", false));
					} else {
						sBuffer.append("<option id = '0'>Select Unit</option>");
					}
					sBuffer.append("</select>");
					sBuffer.append("</td>");
					sBuffer.append("<td width='25%' class='multiControl'>");

					sBuffer.append("<div id='billTrfAmtDivId"+ indxTrf + "'>0.0</div>");
					
					sBuffer
					.append("<input type='hidden' name='strBillTariffRefundDiscountAmount' id='strBillTariffRefundDiscountAmount"
							+ indxTrf
							+ "' value='0'>");
					
					
					sBuffer
					.append("<input type='hidden' name='strBillTariffRefundServiceTaxAmount' id='strBillTariffRefundServiceTaxAmount"
							+ indxTrf
							+ "' value='0'>");
					
					
					
					sBuffer
							.append("<input type='hidden' name='strBillTariffRefundAmount' id='strBillTariffRefundAmount"
									+ indxTrf
									+ "' value='0'></td></tr></table>");

					indxTrf++;

					voObj.getTariffUnitList().beforeFirst();

				}

				sBuffer
						.append("<table class='TABLEWIDTH' border='0' cellspacing='1px' cellpadding='1px' align='center'>");
				sBuffer
						.append("<tr><td width='85%' class='LABEL' >Total Amount</td>");

				sBuffer.append("<td  class='CONTROL'>");
				sBuffer
						.append("<input type='hidden' class='txtFldNormal' name='strReconcileTariffAmount' id = 'strReconcileTariffAmount' value='0.0'>");
				sBuffer
						.append("<b><div id='totalRecTrfAmtDivId'>0.00</div></b>");
				sBuffer.append("</td></tr>");

				sBuffer.append("</table>");

			} else {

				sBuffer
				.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px' cellpadding='1px'>");
		sBuffer
				.append("<tr><td colspan='9' class='multiControl' style='color:red;font-weight:bold'>No Tariff(s) Details Available</td></tr>");
		
				
			}

		} catch (Exception e) {
			voObj.setStrMsgType("1");
			voObj.setStrMsgString(e.getMessage());

			throw new Exception("Bill Reconciliation"
					+ "BillReconcileTransHLP.getTariffDetails()-->"
					+ e.getMessage());
		} finally {
			if (tariffDetails != null) {
				tariffDetails.close();
				tariffDetails = null;
			}
			util = null;
		}

		return sBuffer.toString();

	}

	public static String popUpShw(BillReconcileTransVO vo, String mode) {
		StringBuffer sb = null;
		String strValmode = vo.getStrValmode();
		String temp = "";
		String val[] = strValmode.replace('^', '#').split("#");
		String strRatePerUnit = "";

		try {
			sb = new StringBuffer("");

			if (mode.equals("1")) {
				if (val[9].equals("2")) {
					temp = "Percentage";
					strRatePerUnit = val[3];
				} else {
					temp = "Fixed";
					strRatePerUnit = val[3];
				}
				sb
						.append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");

				sb
						.append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");

				sb
						.append("<td align='right'><img src='../../hisglobal/images/stop.png' style='cursor: hand; cursor: pointer' align='middle' onclick='hide_popup_menu(\"menu1\");'> </td></tr>");

				sb.append("</table> ");

				sb
						.append("<table width='400' align='center' cellspacing='1px' cellpadding='1px'>");

				sb.append("<tr>");

				sb
						.append("<td class='multiLabel' width='20%'>Rate(Rs)/Unit</td>");

				sb.append("<td class='multiControl' width='20%'>"
						+ strRatePerUnit + " / " + val[12]

						+ "</td>");

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

				sb.append("<table width='400' align='center' cellspacing='1px' cellpadding='1px'>");

				sb.append("<tr class='FOOTER'>");

				sb.append("<td colspan='3'>&nbsp;</td>");

				sb.append("</tr></table>");

			} else {

				if (val[8].equals("0")) {
					temp = "Percentage";
					strRatePerUnit = val[12];
				} else {
					temp = "Fixed";
					strRatePerUnit = val[12];
				}
				sb
						.append("<table width='400' align='center' cellpadding='0' cellspacing='0'>");

				sb
						.append("<tr class='HEADER' align='left'><td>&nbsp;Refund Cost Calculation Details</td>");

				sb
						.append("<td align='right'><img src='../../hisglobal/images/stop.png' align='middle' onclick='hide_popup_menu(\"menu1\");'> </td></tr>");

				sb.append("</table> ");

				sb
						.append("<table width='400' align='center' cellspacing='1px'>");

				sb.append("<tr>");

				sb
						.append("<td class='multiLabel' width='20%'>Rate(Rs)/Unit</td>");

				sb.append("<td class='multiControl' width='20%'>"
						+ strRatePerUnit + " / " + val[21]

						+ "</td>");

				sb
						.append("<td class='multiLabel' width='20%'>Discount(Rs)</td>");

				sb.append("<td class='multiControl' width='20%'>" + val[7]

				+ "</td>");

				sb.append("</tr>");

				sb.append("<tr>");

				sb
						.append("<td class='multiLabel' width='20%'>Discount Type</td>");

				sb.append("<td class='multiControl' width='20%'>" + temp
						+ "</td>");

				sb
						.append("<td class='multiLabel' width='20%'>Service Tax(%)</td>");

				sb.append("<td class='multiControl' width='20%'>" + val[20]

				+ "</td>");

				sb.append("</tr>");

				sb.append("</table>");

				sb.append("<table width='400' align='center'>");

				sb.append("<tr class='FOOTER'>");

				sb.append("<td colspan='3'>&nbsp;</td>");

				sb.append("</tr></table>");

			}

		} catch (Exception e) {
			vo.setStrMsgString("BillReconcileTransHLP.popUpShw() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");

		}
		return sb.toString();

	}

}
