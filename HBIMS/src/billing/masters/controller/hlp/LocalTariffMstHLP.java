package billing.masters.controller.hlp;

import hisglobal.TransferObjectFactory;

import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;
import billing.masters.controller.fb.LocalTariffMstFB;
import billing.masters.dao.LocalTariffMstDAO;
import billing.masters.vo.LocalTariffMstVO;

public class LocalTariffMstHLP {

	/*
	 * for Hospital Services,sent to the VO class from where it is called on add
	 * page.
	 */

	/*
	 * public static String showDefault(VOTariffMst vo) throws Exception {
	 * StringBuffer br = new StringBuffer(); WebRowSet wb = null; try {
	 * br.append("<table class='TABLEWIDTH' border='0' align='center'>"); wb =
	 * DAOTariffMst.defaultValue(); int i = 0; while (wb.next()) { br.append("<TR>");
	 * br .append("<TD WIDTH='8%' CLASS='multiControl'><INPUT TYPE='CHECKBOX'
	 * NAME='chargeTypeId' value='" + wb.getString(1) + "'
	 * onClick='changeDefaultData(" + i + ");'></TD>"); br.append("<TD WIDTH='16%' CLASS='multiControl'>" +
	 * wb.getString(2) + "</TD>"); br .append("<TD WIDTH='14%' CLASS='multiControl'><INPUT
	 * TYPE='TEXT' CLASS='txtFldSmall' NAME='rate' maxlength='9'
	 * onkeypress='return validateData(event,7);' disabled></TD>"); br
	 * .append("<TD WIDTH='14%' CLASS='multiControl'><INPUT TYPE='TEXT'
	 * CLASS='txtFldSmall' NAME='actualCost' maxlength='9' onkeypress='return
	 * validateData(event,7);' disabled></TD>"); br .append("<TD WIDTH='14%' CLASS='multiControl'><INPUT
	 * TYPE='TEXT' CLASS='txtFldSmall' NAME='maxDisc' maxlength='4'
	 * onkeypress='return validateData(event,7);' disabled></TD>"); br
	 * .append("<TD WIDTH='14%' CLASS='multiControl'><INPUT TYPE='TEXT'
	 * CLASS='txtFldSmall' NAME='serviceTax' maxlength='4' onkeypress='return
	 * validateData(event,7);' disabled></TD>"); br.append("<TD WIDTH='20%' CLASS='multiControl'>" +
	 * HisUtil.getDatePicker("effectiveFromDfltDet", vo.getCurrentDate(),
	 * String.valueOf(i + 1), false) + "</TD>"); br.append("</TR>"); i++; }
	 * br.append("</table>"); } catch (Exception e) { e.printStackTrace();
	 * throw new Exception("billing.HLPtariffMst.showDefault()"+ e
	 * .getMessage()); } return br.toString(); }
	 */

	/*
	 * to get default data.
	 */
	public static String getDefaultData(LocalTariffMstFB fb, String mode)throws Exception
	{
		StringBuffer br = new StringBuffer();
		boolean flag = false;
		WebRowSet wb = null;
		
		BillConfigUtil bcu = null;
		
		
		boolean fReadOnly =false;
		int k = 0;
		try 
		{
		 // System.out.println("Insdie Method One");	
		  bcu = new BillConfigUtil(fb.getStrHospitalCode());
			
			try
			{
		  fReadOnly = fb.getChk()[0].replace("@", "#").replace("$", "#").split("#")[2].equals("0");
			}
		
			catch(Exception e)
			{
				
			}
			br.append("<table class='TABLEWIDTH' border='0' align='center' cellpadding='1px' cellspacing='1px'>");
			LocalTariffMstVO vo=new LocalTariffMstVO();
			vo = (LocalTariffMstVO) TransferObjectFactory.populateData("billing.masters.vo.LocalTariffMstVO", fb);
			vo.setStrHospitalCode(fb.getStrHospitalCode());
			vo.setChargeTypeId(fb.getChargeTypeId());
			wb = LocalTariffMstDAO.defaultValue(vo);
			String[] str1 = new String[wb.size()];
			String[] str2 = new String[wb.size()];
			while (wb.next()) 
			{
				str1[k] = wb.getString(1);
				 //System.out.println("SBLNUM_CHARGETYPE_ID::wb.getString(1):::"+wb.getString(1));
				str2[k] = wb.getString(2);
				 //System.out.println("SBLSTR_CHARGETYPE_NAME:::wb.getString(2):::"+wb.getString(2));
				k++;
			}
			for (int i = 0; i < str1.length; i++) 
			{
				// if data is not null then retrieve that data.
				//System.out.println("Charge Type Id:::"+vo.getChargeTypeId());
				
				if (vo.getChargeTypeId() != null) 
				{
					for (int j = 0; j < vo.getChargeTypeId().length; j++) 
					{
						
						
						if (vo.getChargeTypeId()[j].equals(str1[i])) 
						{
							flag = true;
							br.append("<TR>");

							if (mode.equals("0")) 
							{

								
								br.append("<TD WIDTH='20%' CLASS='multiControl'>"+str2[i] +"<INPUT TYPE='hidden' NAME='chargeTypeId' value='"+vo.getChargeTypeId()[j]+"'> </TD>");
								br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+" NAME='rate' maxlength='9' value='0' onkeypress='return validateData(event,7);'></TD>");
								br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' maxlength='9' NAME='actualCost' value='0'  onkeypress='return validateData(event,7);' ></TD>");
							
								if(str1[i].equals("1"))
								{	
									
									if(bcu.getOpdServiceTax().equals("0")){
										
										br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly'  NAME='serviceTax' maxlength='6' value='0'  onkeypress='return validateData(event,7);'></TD>");
										
									}else{
										
										br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"   NAME='serviceTax' maxlength='6' value='0'  onkeypress='return validateData(event,7);'></TD>");
										
									}
									
								  br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsOpdVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
								}
								else
								{
									if(str1[i].equals("2"))
									{
										if(bcu.getIpdServiceTax().equals("0")){
											
											br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly'  NAME='serviceTax' maxlength='6' value='0'  onkeypress='return validateData(event,7);'></TD>");
											
										}else{
											
											br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"   NAME='serviceTax' maxlength='6' value='0'  onkeypress='return validateData(event,7);'></TD>");
										}
										
										br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsIpdVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
									}
									else
									{
										
										if(bcu.getEmergencyServiceTax().equals("0")){
											
											br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly'  NAME='serviceTax' maxlength='6' value='0'  onkeypress='return validateData(event,7);'></TD>");
											
										}else{
											br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"   NAME='serviceTax' maxlength='6' value='0'  onkeypress='return validateData(event,7);'></TD>");
										}
										
										br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsEmergVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
									}	
								}	

							} 
						    else 
							   if(mode.equals("1"))
							   {

								br
										.append("<INPUT TYPE='hidden' NAME='chargeTypeId' value='"
												+ vo.getChargeTypeId()[j]
												+ "' >"); /*
															 * onClick='changeDefaultData(" +
															 * i + ");'>");
															 */
								br
										.append("<TD WIDTH='20%' CLASS='multiControl'>"
												+ str2[i] + "</TD>");
								br
										.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='rate' maxlength='9' value='"
												+ vo.getRate()[j]
												+ "' onkeypress='return validateData(event,7);'></TD>");
								br
										.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' maxlength='9' NAME='actualCost' value='"
												+ vo.getActualCost()[j]
												+ "' onkeypress='return validateData(event,7);' ></TD>");
							
							
								/*
								System.out.println("str1:::::"+str1[i]);
								System.out.println("Opd Visisble::1::"+vo.getStrIsOpdVisible());
								System.out.println("Ipd Visisble::1::"+vo.getStrIsIpdVisible());
								System.out.println("Eme Visisble::1::"+vo.getStrIsEmergVisible());
								*/
								if(str1[i].equals("1"))
								{
									if(bcu.getOpdServiceTax().equals("0")){
										
										br
										.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly' NAME='serviceTax' maxlength='6' value='"
												+ vo.getServiceTax()[j]
												+ "'  onkeypress='return validateData(event,7);'></TD>");
										
									}else{
										
										br
										.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' maxlength='6' value='"
												+ vo.getServiceTax()[j]
												+ "'  onkeypress='return validateData(event,7);'></TD>");
									}
									
									if(vo.getStrIsOpdVisible().equals("1") )
								        br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsOpdVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
									else
										br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsOpdVisible' value='0' onclick='checkUpdateMode(this);' ></TD>");	
								}
								else
								{
									if(str1[i].equals("2"))
									{
										
										if(bcu.getIpdServiceTax().equals("0")){
											
											br
											.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly' NAME='serviceTax' maxlength='6' value='"
													+ vo.getServiceTax()[j]
													+ "'  onkeypress='return validateData(event,7);'></TD>");
											
										}else{
											
											br
											.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' maxlength='6' value='"
													+ vo.getServiceTax()[j]
													+ "'  onkeypress='return validateData(event,7);'></TD>");
										}
										
										
										
										
										if(vo.getStrIsIpdVisible().equals("1") )
										   br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsIpdVisible' value='1' onclick='checkUpdateMode(this);' checked ></TD>");
										else
											br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsIpdVisible' value='0' onclick='checkUpdateMode(this);'  ></TD>");	
										
									}
									else
									{
										
										if(bcu.getEmergencyServiceTax().equals("0")){
											
											br
											.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly' NAME='serviceTax' maxlength='6' value='"
													+ vo.getServiceTax()[j]
													+ "'  onkeypress='return validateData(event,7);'></TD>");
											
										}else{
											
											br
											.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' maxlength='6' value='"
													+ vo.getServiceTax()[j]
													+ "'  onkeypress='return validateData(event,7);'></TD>");
										}
										
										
										
										if(vo.getStrIsEmergVisible().equals("1"))
										   br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsEmergVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
										else
											br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsEmergVisible' value='0' onclick='checkUpdateMode(this);' ></TD>");	
									}	
								}	
								

							}
							else
							{
								
								br
								.append("<INPUT TYPE='hidden' NAME='chargeTypeId' value='"
										+ vo.getChargeTypeId()[j]
										+ "' >"); /*
													 * onClick='changeDefaultData(" +
													 * i + ");'>");
													 */
						br
								.append("<TD WIDTH='20%' CLASS='multiControl'>"
										+ str2[i] + "</TD>");
						br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='rate' maxlength='9' disabled='disabled' value='"
										+ vo.getRate()[j]
										+ "' onkeypress='return validateData(event,7);'></TD>");
						br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' maxlength='9' NAME='actualCost' disabled='disabled' value='"
										+ vo.getActualCost()[j]
										+ "' onkeypress='return validateData(event,7);' ></TD>");
					
						
						/*
						System.out.println("Opd Visisble::2::"+vo.getStrIsOpdVisible());
						System.out.println("Ipd Visisble::2::"+vo.getStrIsIpdVisible());
						System.out.println("Eme Visisble::2::"+vo.getStrIsEmergVisible());
						*/
						if(str1[i].equals("1"))
						{	
							
							if(bcu.getOpdServiceTax().equals("0")){
								
								br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly' NAME='serviceTax' maxlength='6' disabled='disabled' value='"
										+ vo.getServiceTax()[j]
										+ "'  onkeypress='return validateData(event,7);'></TD>");
								
							}else{
								
								br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' maxlength='6' disabled='disabled' value='"
										+ vo.getServiceTax()[j]
										+ "'  onkeypress='return validateData(event,7);'></TD>");
							}
							
							if(vo.getStrIsOpdVisible().equals("1") )
						        br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsOpdVisible' value='1' onclick='checkUpdateMode(this);' checked='true' disabled></TD>");
							else
								br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsOpdVisible' value='0' onclick='checkUpdateMode(this);' ></TD>");	
						}
						else
						{
							if(str1[i].equals("2"))
							{
								

								if(bcu.getIpdServiceTax().equals("0")){
									
									br
									.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly' NAME='serviceTax' maxlength='6' disabled='disabled' value='"
											+ vo.getServiceTax()[j]
											+ "'  onkeypress='return validateData(event,7);'></TD>");
									
								}else{
									
									br
									.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' maxlength='6' disabled='disabled' value='"
											+ vo.getServiceTax()[j]
											+ "'  onkeypress='return validateData(event,7);'></TD>");
								}
								
								if(vo.getStrIsIpdVisible().equals("1") )
								   br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsIpdVisible' value='1' onclick='checkUpdateMode(this);' checked='true' disabled ></TD>");
								else
									br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsIpdVisible' value='0' onclick='checkUpdateMode(this);'  ></TD>");	
								
							}
							else
							{
								

								if(bcu.getEmergencyServiceTax().equals("0")){
									
									br
									.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly' NAME='serviceTax' maxlength='6' disabled='disabled' value='"
											+ vo.getServiceTax()[j]
											+ "'  onkeypress='return validateData(event,7);'></TD>");
									
								}else{
									
									br
									.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' maxlength='6' disabled='disabled' value='"
											+ vo.getServiceTax()[j]
											+ "'  onkeypress='return validateData(event,7);'></TD>");
								}
								
								if(vo.getStrIsEmergVisible().equals("1"))
								   br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsEmergVisible' value='1' onclick='checkUpdateMode(this);' checked='true' disabled></TD>");
								else
									br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsEmergVisible' value='0' onclick='checkUpdateMode(this);' ></TD>");	
							}	
						}	
						

								
						}

							br.append("</TR>");
							break;
						} else {
							flag = false;
						}
					}
				}
				// checks null condition and execute with default values.
				if (vo.getChargeTypeId() == null || flag == false) 
				{
					//System.out.println("When Chg Type Id Is Null:::::");
					br.append("<TR>");
					br
							.append("<INPUT TYPE='hidden' NAME='chargeTypeId' value='"
									+ str1[i] + "' >"); /*
														 * onClick='changeDefaultData(" +
														 * i + ");'>");
														 */
					br.append("<TD WIDTH='20%' CLASS='multiControl'>" + str2[i]
							+ "</TD>");
					br
							.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='rate' value='0' maxlength='9' onkeypress='return validateData(event,7);' ></TD>");
					br
							.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' value='0' NAME='actualCost' maxlength='9' onkeypress='return validateData(event,7);' ></TD>");
					/*
					 * br .append("<TD WIDTH='14%' CLASS='multiControl'><INPUT
					 * TYPE='TEXT' CLASS='txtFldSmall' NAME='maxDisc'
					 * maxlength='4' disabled></TD>");
					 */
					/*
					 * br.append("<TD WIDTH='20%' CLASS='multiControl'>" +
					 * HisUtil.getDatePicker("effectiveFromDfltDet",
					 * "",String.valueOf(i + 1), false) + "</TD>");
					 */
					if(str1[i].equals("1"))
					{	
						
						if(bcu.getOpdServiceTax().equals("0")){
							
							br
							.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly'  NAME='serviceTax' value='0'  maxlength='6' onkeypress='return validateData(event,7);'></TD>");
				
							
						}else{
							br
							.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' value='0'  maxlength='6' onkeypress='return validateData(event,7);'></TD>");
				
						}
						
					  br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsOpdVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
					}
					else
					{
						if(str1[i].equals("2"))
						{
							
							if(bcu.getIpdServiceTax().equals("0")){
								
								br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly'  NAME='serviceTax' value='0'  maxlength='6' onkeypress='return validateData(event,7);'></TD>");
					
								
							}else{
								br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' value='0'  maxlength='6' onkeypress='return validateData(event,7);'></TD>");
					
							}
							
							br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsIpdVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
						}
						else
						{
							
							if(bcu.getEmergencyServiceTax().equals("0")){
								
								br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' readonly='readonly'  NAME='serviceTax' value='0'  maxlength='6' onkeypress='return validateData(event,7);'></TD>");
					
								
							}else{
								br
								.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='TEXT' CLASS='txtFldSmall' "+(fReadOnly?"readonly":"")+"  NAME='serviceTax' value='0'  maxlength='6' onkeypress='return validateData(event,7);'></TD>");
					
							}
							
							br.append("<TD WIDTH='20%' CLASS='multiControl'><INPUT TYPE='checkbox' NAME='strIsEmergVisible' value='1' onclick='checkUpdateMode(this);' checked></TD>");
						}	
					}	
					br.append("</TR>");
				}
			}

			br.append("</table>");
		}
		catch (Exception e) 
		{
	        e.printStackTrace(); 
			throw new Exception("billing.HLPtariffMst.getDefaultData()"
					+ e.getMessage());
		}
		 //TransferObjectFactory.populateData(fb, voObj);
		//System.out.println("Return String:::::::::::::"+br.toString());
		return br.toString();
	}

	/*
	 * to get multi row data.
	 */
	public String getMultiRow(LocalTariffMstFB fb, int index) throws Exception {

		StringBuffer sBuffer = new StringBuffer("");
		int len = 0;
		//LocalTariffMstfb fb = new LocalTariffMstfb();
		int total = fb.getTotalRows();
		String rows = "";
		rows = Integer.toString(total);
		//String arr[] = null;
		//boolean fReadOnly=true;
		try {
			boolean fReadOnly = fb.getChk()[0].replace("@", "#").replace("$", "#").split("#")[2].equals("0");
			if (rows.equals("0")) {
			
				// condition.  
				fb.setTotalRows(len);
			} // set Total Length.}
			
			if (total > 0) {
				
				if(fb.getStrDataMode().equals("1")){
				
				for (int i = 1; i <= total; i++) {
					
				//	arr = fb.getDepartment();
					
					fb.setTempDept(fb.getDepartment()[i - 1]);
					sBuffer.append("<div id=\'id" + index + "-" + i + "'  >");
					sBuffer
							.append("<table CLASS='TABLEWIDTH' align='center'  border='0'> <tr><td width='55%' class='multiControl'><select  "+(fReadOnly?"disabled":"")+"   name='department'");
					sBuffer.append("id='department" + index + "-" + i + "'>");
				
					sBuffer.append(fb.getAddDepartmentCombo());
					sBuffer.append("</select></td>");
					sBuffer.append("<td width='35%' class='multiControl'>");
					sBuffer
							.append("<input type='text' "+(fReadOnly?"readonly":"")+"   name='share' class='txtFldSmall' id='share"
									+ index
									+ "-"
									+ i
									+ "' value='"
									+ fb.getShare()[i - 1]
									+ "' maxlength='4'>");
					/*
					 * sBuffer.append("<td width='30%' class='multiControl'>" +
					 * HisUtil.getDatePicker("effectiveFromContbDet", fb
					 * .getEffectiveFromContbDet()[i - 1], index + "-" + i,
					 * false));
					 */
					sBuffer
							.append("<td width='10%' class='multiControl'><img src='../../hisglobal/images/minus.gif'");
					sBuffer.append(" onClick=\"deleteRow('" + index + "-" + i
							+ "','" + index + "','0');\"></td></tr></table>");

					sBuffer.append("</div>");
				}
				
				
				
				sBuffer
						.append(" <input type='hidden' name='mIndex' value='"+total+"' > ");
				// total=total+1;
				sBuffer.append(" <input type='hidden' name='mLength' value='"
						+ total + "' > ");
				
				}else{
					
					for (int i = 1; i <= total; i++) {
						
					//	arr = fb.getDepartment();
						
						fb.setTempDept(fb.getDepartment()[i - 1]);
						sBuffer.append("<div id=\'id" + index + "-" + i + "'  >");
						sBuffer
								.append("<table CLASS='TABLEWIDTH' align='center' border='0'> <tr><td width='30%' class='multiControl'><select disabled='disabled' name='department'");
						sBuffer.append("id='department" + index + "-" + i + "'>");
					
						sBuffer.append(fb.getAddDepartmentCombo());
						sBuffer.append("</select></td>");
						sBuffer.append("<td width='30%' class='multiControl'>");
						sBuffer
								.append("<input type='text' disabled='disabled' name='share' class='txtFldSmall' id='share"
										+ index
										+ "-"
										+ i
										+ "' value='"
										+ fb.getShare()[i - 1]
										+ "' maxlength='4'>");
					
						sBuffer
								.append("</tr></table>");

						sBuffer.append("</div>");
					}
					
					
				}
				
				fb.setTempDept("0");
			}
		} catch (Exception e) {
	 
			throw new Exception("billing.HLPTariffMst.getMultiRow()"
					+ e.getMessage());
		}
		System.out.println("MultiRow result from Helper Class::::::"+sBuffer.toString());
		return sBuffer.toString();
	}
}
