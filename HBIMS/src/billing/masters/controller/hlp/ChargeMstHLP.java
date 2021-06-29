package billing.masters.controller.hlp;
/* Charge Master HLP
 * author: Debashis Sardar
 * Created on : 06-Sep-2011
 */
import hisglobal.TransferObjectFactory;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;


import billing.masters.controller.fb.ChargeMstFB;
import billing.masters.dao.ChargeMstDAO;
import billing.masters.vo.ChargeMstVO;


public class ChargeMstHLP {

	/*
	 * to get package data.
	 */
	public String getDataFrmPack(ChargeMstVO vo, String str) throws Exception 
	{
		WebRowSet wb = null;
		StringBuffer sb = new StringBuffer();
		try 
		{			
			wb = ChargeMstDAO.getDataOnPackage(str, vo);			
			if (wb.size() != 0) 
			{
				sb.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
				int nj = 1;
				while (wb.next()) 
				{
					sb.append("<tr>");
					sb.append("<td class='multiControl' width='15%'><input type='hidden' name='strPackTrfId' value='"+ wb.getString(1)+ "'>"+ wb.getString(2)+ "</td>");
					sb.append("<td class='multiControl' width='15%'><input type='hidden' name='strPackQty' value='"+ wb.getString(3)+ "'>"+ wb.getString(3)+ "</td>");
					sb.append("<td class='multiControl' width='15%'><input type='hidden' name='strPackUnitId' value='"+ wb.getString(4)+ "'>"+ wb.getString(5)+ "</td>");
					sb.append("<td class='multiControl' width='15%' style='display: none;><input type='text' name='strPackProcCost' value='0' id='strPackProcCost"+ nj+ "' value='' class='txtFldMin' onkeyup='enterAmount(\""+ nj+ "\");' onkeypress='return validateData(event,7);'></td>");
					sb.append("<td class='multiControl' width='15%'><input type='text' name='strPackTrfCost' value='0' id='strPackTrfCost"+ nj+ "' value='' class='txtFldMin' onkeyup='enterAmount(\""+ nj+ "\");' onkeypress='return validateData(event,7);'></td>");
					sb.append("<td class='multiControl' width='15%'><input type='text' name='strPackTotal' value='0' id='strPackTotal"+ nj+ "' value='' readonly='readonly' class='txtFldMin'></td>");
					sb.append("<td class='multiControl' width='10%'><input type='checkbox' name='strRefundable' id='strRefundable"+ nj + "' value='0' ></td>");
					sb.append("</tr>");

					nj = nj + 1;
				}
				sb.append("</table>");
				sb.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb.append("<td width='75%' class='LABEL'> Grand Total");
				sb.append("</td>");
				sb.append("<td width='25%' class='CONTROL'>");
				sb.append("<input type='text' class='txtFldNormal' name='strPackGrandTotalCost' value='0.00' readonly='readonly'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("HLPChargeMst.getDataFrmPack() --> "+ e.getMessage());
		}

		return sb.toString();
	}

	/*
	 * to get multi row data.
	 */
	public String getMRowData(ChargeMstVO vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
			sb.append("<TABLE CLASS='TABLEWIDTH' BORDER='0' ALIGN='center'>");
			if ((vo.getStrProductCost() != null) && (vo.getStrProductCost().length > 0)) 
			{				
				for (int ni = 0; ni < vo.getStrProductCost().length; ni++) 
				{
					vo.setStrTempCategory(vo.getStrPatientCategoryMod());
					sb.append("<TR><TD class='multiControl' width='20%'><select  disabled='disabled' >");
					sb.append(vo.getPatientCategory());
					sb.append("</select></TD><TD class='multiControl' width='16%' style='display: none'>");
					sb.append("<input class='txtFldSmall' type='text' name='strProductCost' id='strProductCost"
									+ ni+ "' maxlength='9' value='"+ vo.getStrProductCost()[ni]+ "' onkeypress=\"return validateData(event,7);\" onkeyup='calcTotalAmount(\""
									+ ni + "\");'> ");
					sb.append("</TD><TD class='multiControl' width='16%'>");
					sb	.append("<input class='txtFldSmall' type='text' name='strTariffCost' id='strTariffCost"
									+ ni+ "' maxlength='9' value='"+ vo.getStrTariffCost()[ni]+ "' onkeypress=\"return validateData(event,7);\" onkeyup='calcTotalAmount(\""
									+ ni + "\");'>");
					sb.append("</TD><TD class='multiControl' width='16%'>");
					sb.append("<input class='txtFldSmall' type='text' name='strTotalCost' id='strTotalCost"
									+ ni+ "' maxlength='9' value='"+ vo.getStrTotalCost()[ni]+ "' readonly='readonly' onkeypress=\"return validateData(event,7);\">");
					sb.append("</TD><TD class='multiControl' width='16%'>");

					sb.append("<input type='checkbox' name='strIsAdvance' value='"+ vo.getStrIsAdvance()[ni] + "' ");
					if (vo.getStrIsAdvance()[ni].equals("1")) 
					{
						sb.append(" checked = 'checked' ");
					}

					sb.append(">");
					sb.append("</TD><TD class='multiControl' width='16%'");

					sb.append("<input type='checkbox' name='strIsRefundable' value='"
									+ vo.getStrIsRefundable()[ni] + "'");
					if (vo.getStrIsRefundable()[ni].equals("1")) 
					{
						sb.append(" checked = 'checked' ");
					}
					sb.append(">");
					sb.append("</TD></TR>");
				}
			}
			sb.append("</TABLE>");
		} catch (Exception e) {
	 
			throw new Exception("HLPChargeMst.getMRowData --> "
					+ e.getMessage());
		}
		
		return sb.toString();
	}

	public static String getPreviousData(ChargeMstFB fb) throws Exception 
	{
		WebRowSet wb = null;
		StringBuffer sb = new StringBuffer();
		
		ChargeMstVO vo = null;
		
		try 
		{
			vo = new ChargeMstVO();
			vo = (ChargeMstVO) TransferObjectFactory.populateData("billing.masters.vo.ChargeMstVO", fb);
			
			wb = ChargeMstDAO.getPreviousData(vo);
			fb = (ChargeMstFB) TransferObjectFactory.populateData("billing.masters.controller.fb.ChargeMstFB", vo);
			
			if (wb.size() != 0) 
			{
				sb.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb.append("<td class='multiLabel' width='20%'>Patient Category </td>");
				sb.append("<td class='multiLabel' width='14%' style='display: none'>Procedure Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb.append("<td class='multiLabel' width='14%'>Tariff Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb.append("<td class='multiLabel' width='14%'>Total Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
				sb.append("<td class='multiLabel' width='10%'>IsAdvance</td>");
				sb.append("<td class='multiLabel' width='10%'>IsRefundable</td>");
				sb.append("<td class='multiLabel' width='18%'>Validity</td>");
				sb.append("</tr>");

				while (wb.next()) 
				{
					sb.append("<tr>");
					sb.append("<td class='multiControl' width='20%'> <input type='hidden' name='strPatCatHidden' value='"+ wb.getString(1)+ "'>"+ wb.getString(2)+ "</td>");
					sb.append("<td class='multiControl' width='14%' style='display: none'>"+ wb.getString(3) + "</td >");
					sb.append("<td class='multiControl' width='14%'>"+ wb.getString(4) + "</td >");
					sb.append("<td class='multiControl' width='14%'>"+ wb.getString(5) + "</td >");
					sb.append("<td class='multiControl' width='10%'>"+ wb.getString(6) + "</td >");
					sb.append("<td class='multiControl' width='10%'>"+ wb.getString(7) + "</td >");
					sb.append("<td class='multiControl' width='10%'>"+ wb.getString(8) + "</td>");
					sb.append("</tr>");
				}
				sb.append("</table>");
			} 
			else 
			{
				sb.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'><tr>");
				sb.append("<td class='multiControl' width='10%'><font color='red'>No Previous Data Available</font></td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		} 
		catch (Exception e) 
		{
			throw new Exception("HLPChargeMst.getPreviousData() --> "+ e.getMessage());
		}

		return sb.toString();
	}
	
	public static String getModifyData(ChargeMstFB fb) 
	{
		WebRowSet wb = null;
		StringBuffer sb = new StringBuffer();
		boolean start=true;
		String strPervService="";
		int setWidth=20;
		int nTmpI=0;
		
		try 
		{
        	wb = fb.getWbModifyData();
			if (wb.size() != 0) 
			{
				wb.beforeFirst();
				while (wb.next()) 
				{
					if(!strPervService.equals(wb.getString(1)))
					{
						if(!start)
						{
							sb.append("</table>");
							sb.append("</div>");
						}
						
						sb.append("<table class=\"TABLEWIDTH\" align=\"center\" cellpadding=\"1px\" cellspacing=\"1px\">");
						sb.append("<tr>");
						sb.append("<td class=\"TITLE\" colspan=\"5\">");
						sb.append("<div style=\"display: none;\" id=\"divPreviousDataPlus"+wb.getString(1)+"ID\" align=\"left\"><img onkeypress=\"onPressingEnter(this,event)\" tabindex=\"1\" src=\"../../hisglobal/images/plus.gif\" title=\"Show Previous Data Details\" onclick=\"showDiv('divPreviousDataMinus"+wb.getString(1)+"ID'),hideDiv('divPreviousDataPlus"+wb.getString(1)+"ID'),showDiv('divPreviousDataDetails"+wb.getString(1)+"Id')\" style=\"cursor: pointer;\"> ");
						if(wb.getInt(1)==1)
							sb.append("Service Type: OPD");
						else if(wb.getInt(1)==2)
							sb.append("Service Type: IPD");
						else if(wb.getInt(1)==3)
							sb.append("Service Type: Emergency");
						sb.append("</div>");
						sb.append("<div style=\"display: block;\" id=\"divPreviousDataMinus"+wb.getString(1)+"ID\"  align=\"left\"><img onkeypress=\"onPressingEnter(this,event)\" tabindex=\"1\" src=\"../../hisglobal/images/minus.gif\" title=\"Hide Previous Data Details\" onclick=\"hideDiv('divPreviousDataMinus"+wb.getString(1)+"ID'),hideDiv('divPreviousDataDetails"+wb.getString(1)+"Id'),showDiv('divPreviousDataPlus"+wb.getString(1)+"ID');\" style=\"cursor: pointer;\"> ");
						if(wb.getInt(1)==1)
							sb.append("Service Type: OPD");
						else if(wb.getInt(1)==2)
							sb.append("Service Type: IPD");
						else if(wb.getInt(1)==3)
							sb.append("Service Type: Emergency");
						
						
						sb.append(" <select name=strCostType><option value='1' style='display: none'>Procedure</option><option value='2'>Tariff</option></select>");
						sb.append(" <input name=strHike onkeypress='return validateData(event,7);'  type=text class=txtFldSmall>");
						sb.append(" <select name=strHikeType><option value=1>%age</option><option value=2>Fixed</option></select>");
						sb.append(" <select name=strPriceType><option value=1>Hike</option><option value=2>Down</option></select>");
						sb.append(" <input type=button value=Apply name=apply onclick='calcAll(this)'>");
						sb.append(" <input type=button value=Reset name=reset onclick='resetAll(this)'>");
						
						sb.append("</div>");
						sb.append("</td>");
						sb.append("</tr>");
						sb.append("</table>");
						
						
						sb.append("<div id=\"divPreviousDataDetails"+wb.getString(1)+"Id\" >");				
						sb.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
						sb.append("<tr>");
						sb.append("<td class='multiLabel' width='5%'>#</td>");
						if(wb.getInt(1)==2){
							sb.append("<td class='multiLabel' width='10%'>Ward Type </td>");
							setWidth=10;
						}else{
							setWidth = 20;
						}
						sb.append("<td class='multiLabel' width='"+setWidth+"%'>Patient Category </td>");
						sb.append("<td class='multiLabel' width='15%' style='display: none'>Procedure Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
						sb.append("<td class='multiLabel' width='10%'>Tariff Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
						sb.append("<td class='multiLabel' width='10%'>Total Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>");
						sb.append("<td class='multiLabel' width='10%'>Is Advance</td>");
						sb.append("<td class='multiLabel' width='10%'>Is Refundable</td>");
						sb.append("<td class='multiLabel' width='20%'>Effective From</td>");
						sb.append("</tr>");
					}
					sb.append("<tr>");
					sb.append("<td class='multiControl'>");
					sb.append("<input name='strCheckBox' value='");
					sb.append(wb.getString(7));
					sb.append("^");
					sb.append(wb.getString(8));
					sb.append("^");
					sb.append(Float.parseFloat(wb.getString(8))+Float.parseFloat(wb.getString(7)));
					sb.append("^");
					sb.append(wb.getString(13));
					sb.append("^");
					sb.append(wb.getString(23));
					sb.append("' onclick='clickCheckBox(this)' type='checkbox'>");
					sb.append("</td>");
					if(wb.getInt(1)==2){
						sb.append("<td class='multiControl'>");
						sb.append(wb.getString(19));
						sb.append(" </td>");
					}
					
					sb.append("<td class='multiControl'>");
					sb.append("<input type=hidden disabled name=strPatCatArr value='"+wb.getString(3)+"'>");
					sb.append("<input type=hidden disabled name=strPKTariffArr value='"+wb.getString(2)+"'>");
					sb.append("<input type=hidden disabled name=strPKPatCatArr value='"+wb.getString(3)+"'>");
					sb.append("<input type=hidden disabled name=strPkChargeTypeArr value='"+wb.getString(1)+"'>");
					sb.append("<input type=hidden disabled name=strPKIpdChargeTypeArr value='"+wb.getString(4)+"'>");
					sb.append("<input type=hidden disabled name=strPKChargeSlNoArr value='"+wb.getString(5)+"'>");
					sb.append("<input type=hidden disabled name=strUnitIdArr value='"+wb.getString(22)+"'>");
					if("PRIVATE".equalsIgnoreCase(wb.getString(19))){
						sb.append("<input type=hidden disabled name=strWardTypeArr value='1'>");
					} else if("GENERAL".equalsIgnoreCase(wb.getString(19))){
						sb.append("<input type=hidden disabled name=strWardTypeArr value='2'>");
					} else {
						sb.append("<input type=hidden disabled name=strWardTypeArr value=''>");
					}
					sb.append(wb.getString(21));
					sb.append("</td>");

					sb.append("<td class='multiControl' style='display: none'>");
					sb.append("<input class='txtFldSmall' disabled name='strProductCostArr' maxlength='9' value='");
					sb.append(wb.getString(7));
					sb.append("' onkeypress='return validateData(event,7);' onkeyup='calculateArr(this);' type='text'>");
					sb.append("</td>");
					
					sb.append("<td class='multiControl'>");
					sb.append("<input class='txtFldSmall' disabled name='strTariffCostArr' maxlength='9' value='");
					sb.append(wb.getString(8));
					sb.append("' onkeypress='return validateData(event,7);' onkeyup='calculateArr(this);' type='text'>");
					sb.append("</td>");
					
					sb.append("<td class='multiControl'>");
					sb.append("<input class='txtFldSmall' disabled name='strTotalCostArr' readonly maxlength='9' value='");
					sb.append(Float.parseFloat(wb.getString(8))+Float.parseFloat(wb.getString(7)));
					sb.append("' type='text'>");
					sb.append("</td>");
					
					
					sb.append("<td class='multiControl'>");
					sb.append("<input name='strIsAdvanceArr' disabled value='1' onclick='setCheck(this)' ");
					if(wb.getInt(11)==1)
						sb.append("checked='checked'");
					sb.append(" type='checkbox'><input disabled type=hidden name='strAdvArr' value='");
					if(wb.getInt(11)==1)
						sb.append("1");
					else
						sb.append("0");
					sb.append("'>");
					sb.append("</td>");
					
					sb.append("<td class='multiControl'>");
					sb.append("<input name='strIsRefundArr' disabled value='1'  onclick='setCheck(this)' ");
					if(wb.getInt(12)==1)
						sb.append("checked='checked'");
					sb.append(" type='checkbox'><input disabled type=hidden name='strRefundArr' value='");
					if(wb.getInt(12)==1)
						sb.append("1");
					else
						sb.append("0");
					sb.append("'>");
					sb.append("</td>");
					
					sb.append("<td class='multiControl'>");
					sb.append("<div style='display:none' id='divAEffDate");
					sb.append(nTmpI);
					sb.append("'>");
					sb.append(HisUtil.getDatePicker("strFromDateArr", wb.getString(13), true));
					sb.append("</div>");
					sb.append("<div id='divEffDate");
					sb.append(nTmpI++);
					sb.append("'>");
					sb.append(wb.getString(13));
					sb.append("</div>");
					sb.append("</td>");
					
					sb.append("</tr>");
					start = false;
					strPervService = wb.getString(1);
				}
				sb.append("</table>");
				sb.append("</div>");
			} else {
				sb.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'><tr>");
				sb.append("<td class='multiControl' width='10%'><font color='red'>No Previous Data Available</font></td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
			
		} catch (Exception e) 
		{
	 
		}
		
		return sb.toString();
	}

	public static void getDataFrmPackModify(ChargeMstVO vo) throws Exception {
		WebRowSet wb = null;
		StringBuffer sb = new StringBuffer();
		try {
			
			wb = vo.getPackageDetails();
			
			if (wb.size() != 0) {

				
				sb
						.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
				int nj = 1;
				float total = 0;
				while (wb.next()) {

					sb.append("<tr>");
					sb
							.append("<td class='multiControl' width='15%'><input type='hidden' name='strPackTrfId' value='"
									+ wb.getString(1)
									+ "'>"
									+ wb.getString(2)
									+ "</td>");
					sb
							.append("<td class='multiControl' width='15%'><input type='hidden' name='strPackQty' value='"
									+ wb.getString(3)
									+ "'>"
									+ wb.getString(3)
									+ "</td>");
					sb
							.append("<td class='multiControl' width='15%'><input type='hidden' name='strPackUnitId' value='"
									+ wb.getString(4)
									+ "'>"
									+ wb.getString(5)
									+ "</td>");
					sb
							.append("<td class='multiControl' width='15%'><input type='text' name='strPackProcCost' id='strPackProcCost"
									+ nj
									+ "' value='"
									+ wb.getString(6)
									+ "' class='txtFldMin' onkeyup='enterAmount(\""
									+ nj
									+ "\");' onkeypress='return validateData(event,7);'></td>");
					sb
							.append("<td class='multiControl' width='15%'><input type='text' name='strPackTrfCost' id='strPackTrfCost"
									+ nj
									+ "' value='"
									+ wb.getString(7)
									+ "' class='txtFldMin'onkeyup='enterAmount(\""
									+ nj
									+ "\");' onkeypress='return validateData(event,7);'></td>");
					sb
							.append("<td class='multiControl' width='15%'><input type='text' name='strPackTotal' id='strPackTotal"
									+ nj
									+ "' value='"
									+ wb.getString(8)
									+ "' readonly='readonly' class='txtFldMin'></td>");
					
					System.out.println("::::::::::::::::::::::::BEFORE TOTAL Value:::::::::::::::::::====="+total);

					total = total + Float.parseFloat(wb.getString(8));
					System.out.println("::::::::::::::::::::::::TOTAL:::::::::::::::::::====="+total);
					System.out.println("::::::::::::::::::::::::PackTotal:::::::::::::::::::====="+wb.getString(8));
					if (wb.getString(9).equals("1")) {
						sb
								.append("<td class='multiControl' width='10%'><input type='checkbox' name='strRefundable' checked='checked' id='strRefundable"
										+ nj + "' value='1' ></td>");
					} else {
						sb
								.append("<td class='multiControl' width='10%'><input type='checkbox' name='strRefundable' id='strRefundable"
										+ nj + "' value='1' ></td>");
					}
					sb.append("</tr>");

					nj = nj + 1;
				}
				sb.append("</table>");
				sb
						.append("<table class='TABLEWIDTH' border='0' align='center' cellspacing='1px'>");
				sb.append("<tr>");
				sb.append("<td width='75%' class='LABEL'> Grand Total");
				sb.append("</td>");
				sb.append("<td width='25%' class='multiControl'>");
				sb
						.append("<input type='text' class='txtFldNormal' name='strPackGrandTotalCost' value='"
								+ total + "' readonly='readonly'>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
			}
		} catch (Exception e) {

			throw new Exception("HLPChargeMst.getDataFrmPack() --> "
					+ e.getMessage());
		}
		vo.setStrPackageDetails(sb.toString());
		ChargeMstFB fb = (ChargeMstFB) TransferObjectFactory.populateData(
				"billing.masters.controller.fb.ChargeMstFB", vo);
		 HelperMethods.populate(fb,vo);
		
	}
}
