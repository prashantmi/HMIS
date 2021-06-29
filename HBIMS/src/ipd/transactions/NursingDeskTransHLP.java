package ipd.transactions;

import hisglobal.utility.HisUtil;
import ipd.IpdConfigUtil;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.rowset.WebRowSet;

public class NursingDeskTransHLP {

	private static ResourceBundle hisProp = ResourceBundle.getBundle("ipd.hisIpdProperties");
	public static String transPatCSS = hisProp.getString("TRANSFER_PATIENT");
	public static String outsidePatCSS = hisProp.getString("OUTSIDE_PATIENT");

	// hlp function for listing admission details on the basis of
	// department,department unit
	// ward,room

	public static String getAdmitDetail(NursingDeskTransVO VO) throws Exception 
	{
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospitalCode());
		String strbed = "";
		String strunit = "";
		HisUtil util = null;
		util = new HisUtil("IPD", "NursingDeskTransHLP");

		NursingDeskTransBO BO = new NursingDeskTransBO();
		BO.getAdmitDetail(VO);

		// if there is error
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" NursingDeskTransHLP.getAdmitDetail() --> "+ strMsg);
		}

		WebRowSet ws = VO.getStrAdmitDetailWs();


		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();

		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='3%'>#</td> ");
		sBuffer.append("<td class='multiLabel' width ='10%'>Adm. No. </td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%'>CR No.</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='15%'>Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='8%' >Age/Sex</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='16%' >Adm. Date/Time</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Unit</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Consultant Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='14%'><font color='red'>*</font>Bed</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='14%'>Sharable Bed</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='11%'><img src='../../hisglobal/images/Bed_.gif'  " +
				"style='cursor:hand;cursor:pointer;'  title ='Check Bed Status'  data-toggle='modal' href='#myModal' id='modellink' onClick ='openPopup("
				+ "\"BEDSTATUS\"" + ");'></td> ");

		if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
		{
			sBuffer.append("<td CLASS='multiLabel' width ='11%'>");
			//sBuffer.append(" <img src='../../hisglobal/images/Bed_.gif' style='cursor:pointer;' title ='Check Bed Status' onClick ='openPopup(\"BEDSTATUS\")'>");			
			sBuffer.append(" </td> ");
		}
		
		sBuffer.append(" </tr>");
		/* sBuffer.append(" </table>"); */

		String tempStrBed = "";
		String tempStrUnit = "";
		String tempConsultantName = "";
		String strConsultantCode="";
		// bed detail
		//tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), "","0^Select Value", false);
		if(VO.getStrUnitWs().size()==1)
		tempStrUnit = util.getOptionValue(VO.getStrUnitWs(), "","", false);
		else
		tempStrUnit = util.getOptionValue(VO.getStrUnitWs(), "","0^Select Value", false);
		
		VO.setStrunitproperty(tempStrUnit);
		if (ws != null && ws.size() != 0)   
		{
			try 
			{
				int i = 0;
				while (ws.next())
				{
					//strbed = tempStrBed;
					strunit = tempStrUnit;
					i++;
					String stradmno = ws.getString(1);
					String strcrno = ws.getString(2);
					String strname = ws.getString(3);
					String stragesex = ws.getString(4);
					String strbedname = ws.getString(5);
					
					String strtransinflag = ws.getString(7);
					String strmoveno = ws.getString(8);
					String strbedno = ws.getString(6);
					VO.setStrPreviousOccupiedbed(strbedno);
					String strunitcode = ws.getString(15);
					String strroomcode = ws.getString(16);
					String strunitname = ws.getString(19);
					String strAdmDate = ws.getString(14);
					VO.setDepartmentName(ws.getString(17));
					VO.setWardName(ws.getString(18));
					String bed_details=ws.getString(19);
					String ward_dbloccup="0"; //ws.getString(20);
					if (stradmno == null)
						stradmno = "";
					if (strcrno == null)
						strcrno = "";
					if (strname == null)
						strname = " ";
					if (stragesex == null)
						stragesex = "";
					if (strbedno == null || strbedno.equals("null"))
 						strbedno = "";
					/*if (!strbedno.equals("") && !strbedno.equals("0")) 
					{
						strbed = "<option value = '0'>Select Value</option>";
						strbed += "<option value = \"" + strbedno + "\">"+ strbedname + "</option>";
					}
					else
					{
						strbed = "<option value = '0'>Select Value</option>";
					}*/
					if (!strunitcode.equals("") && !strunitcode.equals("0")) 
					{
						strunit += "<option value = '0'>Select Value</option>";
						strunit += "<option value = \"" + strunitcode + "\" selected>"+ strunitname + "</option>";
						VO.setStrUnit(strunitcode);
						VO.setStrRoom(strroomcode);
						BO.bed(VO);
						BO.setConsultantName(VO);
						tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);			
						tempConsultantName = util.getOptionValue(VO.getConsultantWS(),VO.getStrConsultantCode() ,"0^Select Value", false);
						if (!strbedno.equals("") && !strbedno.equals("0")) 
						{
							strbed = "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strbed += "<option value = \"" + strbedno + "\" selected>"+ strbedname + "</option>";
						}
						else
						{
							strbed = tempStrBed;
						//}
					}
						if(VO.getStrConsultantCode().equals("")  && VO.getStrConsultantCode().equals("0"))
						{
							strConsultantCode= "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strConsultantCode += "<option value = \"" + VO.getStrConsultantCode() + "\" selected>"+ VO.getStrConsultantName() + "</option>";
						}else{
							strConsultantCode=tempConsultantName;
						}
					}
					else
					{
						if(VO.getStrUnitWs().size()==1)
						{
							VO.getStrUnitWs().beforeFirst();
							if(VO.getStrUnitWs().next())
							{
								VO.setStrUnit(VO.getStrUnitWs().getString(1));
							}
							VO.setStrRoom(strroomcode);
							BO.bed(VO);
							BO.setConsultantName(VO);
							tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);
							tempConsultantName = util.getOptionValue(VO.getConsultantWS(),VO.getStrConsultantCode() ,"0^Select Value", false);
						}
						else
						{
							tempStrBed = "<option value = '0'>Select Value</option>";
							tempConsultantName= "<option value = '0'>Select Value</option>";
						}
						strbed = tempStrBed;
						strConsultantCode=tempConsultantName;
					}
					boolean fPrintAllRow = false;
					if (VO.getStrCrNo() == null || VO.getStrCrNo().equals("")) 
					{
						fPrintAllRow = true;
					}
					if (fPrintAllRow || strcrno.equals(VO.getStrCrNo())) 
					{
						if (strtransinflag.equals("2")) //Transfer,Intransit Cancel
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1) 
							{//only single record
								sBuffer.append("<td   bgcolor='"
												+ transPatCSS
												+ "'  width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'  ></td> ");
							} 
							else 
							{
								sBuffer.append("<td  bgcolor='"
												+ transPatCSS
												+ "' width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td  bgcolor='"
											+ transPatCSS
											+ "' width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
											+ stradmno+"</font> "
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strname
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "' width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stragesex
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "' width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strAdmDate
									+ "</font></td> ");
							// sBuffer.append(" <td bgcolor='"+transPatCSS+"'
							// width ='11%' nowrap>"+"</td> ");
							// sBuffer.append(" <td CLASS='multiControl' width
							// ='5%'>"+strbed+"<input type ='hidden' name
							// ='strhbed' </td> ");
							//if (i == 1) {
							/* Unit Addition By Amit Kumar Ateria*/
							sBuffer.append("<td bgcolor='"+transPatCSS+"' width ='12%'><div><select name = 'strUnit' class='comboMin' title=''  id = 'strunit"+ i+ "' onchange=\"funbed(this,'strunit"+ i+ "','divbed"+ i+ "','strbed"+ i+ "')\">"+strunit+"</select>");
							sBuffer.append("<td   width ='12%'><div id='consultantDiv"+i+"'><select name = 'strConsultantCode' class='comboMin' title=''  id = 'strConsultantCodeId"+ i+ "' onchange=''>"+tempConsultantName+"</select>");
								sBuffer.append(" <td  bgcolor='"
												+ transPatCSS
												+ "'  width ='13%'><div id='divbed"+ i+ "'><select name = 'strbed'  class = 'comboMin'  id = 'strbed"+i+"'>"
											//	+ i
												//+ "' >"
												+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname+ "' id =  'strbedname" +i+ "''><input type='hidden' name='bedvalue' value='"+i+"'>"
												+ "</div></td> "
														+ "<td><input type='checkbox' id='sharableChkid"+i+"' name='sharableChk' value='0' onclick='sharableChkBox(this,"+i+");'></td>");
														
							/*} else {
								sBuffer
										.append(" <td   bgcolor='"
												+ transPatCSS
												+ "' width ='16%'><select name = 'strbed' disabled ='disabled' class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select> <input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname" + i
												+ "''></td> ");

							}*/
								
					      /*sBuffer.append(" <td  bgcolor='" + transPatCSS
										+ "' width ='11%' nowrap >");*/
								
						  /*sBuffer.append("<div id='plusId-"+i+"' ><img src='../../hisglobal/images/plus.gif' name='plus'" +
										" style='cursor: pointer;'   title = 'Sharable Occupied Bed Details' " +
										"onClick ='showSharableBed(this,"
										+ i + ");'> </div>");
						  sBuffer.append("<div id='minusId-"+i+"' style='display: none' ><img src='../../hisglobal/images/minus.gif' name='minus'" +
										" style='cursor: pointer;'   title = 'Vacant Bed Details' " +
										"onClick ='hideSharableBed(this,"
										+ i + ");'> </div>");*/

							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1"))
							{
								/*sBuffer.append(" <td  bgcolor='" + transPatCSS
										+ "' width ='11%'  >");
								sBuffer.append("<img src='../../hisglobal/images/Belongings_1.png' " +
											" style='cursor:hand;cursor:pointer;'   title = 'Belonging Details' " +
											"onClick ='beforedisplayBelogingPopup(this,"
											+ i + ");'> "); */
							}
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								
								//Discuss About it.
								/*sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;'    title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");*/
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						} 
						else if (strtransinflag.equals("8")) 
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1) 
							{//only single record
								sBuffer.append("<td  bgcolor='"
												+ outsidePatCSS
												+ "' width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'  ></td> ");
							} 
							else 
							{
								sBuffer.append("<td  bgcolor='"
												+ outsidePatCSS
												+ "' width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'  ><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td  bgcolor='"
											+ outsidePatCSS
											+ "' width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "'  width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strname
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "' width ='11%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stragesex
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "' width ='11%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strAdmDate
									+ "</font></td> ");
							// sBuffer.append(" <td CLASS='multiControl' width
							// ='5%'>"+strbed+"<input type ='hidden' name
							// ='strhbed' </td> ");
							//if (i == 1) {

								sBuffer.append(" <td   bgcolor='"
												+ outsidePatCSS
												+ "' width ='16%'><select name = 'strbed'  class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname" + i
												+ "''><input type='hidden' name='bedvalue' value='"+i+"'> </td> ");
							/*} else {
								sBuffer
										.append(" <td   bgcolor='"
												+ outsidePatCSS
												+ "' width ='16%'><select name = 'strbed' disabled ='disabled' class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select> <input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname" + i
												+ "''></td> ");

							}*/
								
								
							  /*sBuffer.append(" <td  bgcolor='" + transPatCSS
											+ "' width ='11%' nowrap >");*/
									
							  /*sBuffer.append("<div id='plusId-"+i+"' ><img src='../../hisglobal/images/plus.gif' name='plus'" +
											" style='cursor: pointer;'   title = 'Sharable Occupied Bed Details' " +
											"onClick ='showSharableBed(this,"
											+ i + ");'> </div>");
							  sBuffer.append("<div id='minusId-"+i+"' style='display: none' ><img src='../../hisglobal/images/minus.gif' name='minus'" +
											" style='cursor: pointer;'   title = 'Vacant Bed Details' " +
											"onClick ='hideSharableBed(this,"
											+ i + ");'> </div>");*/	

							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1"))
							{
								/*sBuffer.append(" <td  bgcolor='" + outsidePatCSS
										+ "' width ='11%'  >");
								sBuffer.append("<img src='../../hisglobal/images/Belongings_1.png' " +
											" style='cursor:hand;cursor:pointer;'   title = 'Belonging Details' onClick ='beforedisplayBelogingPopup(this,"
											+ i + ");'> ");*/
							}
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						} 
						else //Acceptance
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1)//only single record 
							{
								//sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
								sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'></td> ");
							} 
							else 
							{
								sBuffer.append("<td  width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td   width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno
									+ "</font></td> ");
							sBuffer.append(" <td   width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strname + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ stragesex + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strAdmDate + "</font></td> ");
							// sBuffer.append(" <td CLASS='multiControl' width
							// ='5%'>"+strbed+"<input type ='hidden' name
							// ='strhbed' </td> ");
					//		if (i == 1) {
								/* Unit Addition By Amit Kumar Ateria*/
								sBuffer.append("<td   width ='12%'><div><select name = 'strUnit' class='comboMin' title=''  id = 'strunit"+ i+ "' onchange=\"funbed(this,'strunit"+ i+ "','divbed"+ i+ "','strbed"+ i+ "');\">"+strunit+"</select>");
								sBuffer.append("<td   width ='12%'><div id='consultantDiv"+i+"'><select name = 'strConsultantCode' class='comboMin' title=''  id = 'strConsultantCodeId"+ i+ "' onchange=''>"+tempConsultantName+"</select>");
								sBuffer.append("<input type ='hidden' name ='hiddenBedName' value = '"+ strbedname+ "' id =  'strbedname"+ i+ "''></div></td> ");
							
								sBuffer.append("<td   width ='13%'><div id='divbed"+ i+ "'><select name = 'strbed'  class = 'comboMin' title='_div_popup_cntl' id = 'strbed"+ i+ "' >"+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname"
												+ i
												+ "''><input type='hidden' name='bedvalue' value='"+i+"'></div></td>"
												+ "<td><input type='checkbox' id='sharableChkid"+i+"' name='sharableChk' value='0' onclick='sharableChkBox(this,"+i+");'></td>");
						//	} else {
						//		sBuffer
						/*				.append(" <td   width ='16%'><select name = 'strbed' disabled ='disabled' class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select> <input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname"
												+ i
												+ "''></td> ");

							}*/
								
							  /*sBuffer.append(" <td  bgcolor='" + transPatCSS
											+ "' width ='11%' nowrap >");*/
									
							  /*sBuffer.append("<div id='plusId-"+i+"' ><img src='../../hisglobal/images/plus.gif' name='plus'" +
											" style='cursor: pointer;'   title = 'Sharable Occupied Bed Details' " +
											"onClick ='showSharableBed(this,"
											+ i + ");'> </div>");
							  sBuffer.append("<div id='minusId-"+i+"' style='display: none' ><img src='../../hisglobal/images/minus.gif' name='minus'" +
											" style='cursor: pointer;'   title = 'Vacant Bed Details' " +
											"onClick ='hideSharableBed(this,"
											+ i + ");'> </div>");*/	

							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1"))
							{
								/*sBuffer.append(" <td  width ='11%'  >");
								sBuffer.append("<img src='../../hisglobal/images/Belongings_1.png'  style='cursor:hand;cursor:pointer;' " +
											"  title = 'Belonging Details' onClick ='beforedisplayBelogingPopup(this,"
											+ i + ");'> ");*/
							}
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								/*sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");*/
								
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append("</tr> ");
						}
						sBuffer.append("<tr><td><input type='hidden' id='idvalue' value='"+i+"'></td></tr>");
					}
				}
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				e.printStackTrace();
				VO.setStrMsgString("NursingDeskTransDAO.checklistdetail() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("NursingDeskTransHLP.getAdmitDetail() -->"
						+ e.getMessage());
			}

		} 
		else 
		{

			sBuffer
					.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
			sBuffer.append("<tr class ='multiControl'> ");
			sBuffer
					.append("<td colspan='7' align='center'> <b><font color='red'>No Record Available</font></b> </td>");
			sBuffer.append(" </tr> ");
			sBuffer.append("</table > ");

		}

		return sBuffer.toString();
	}
	
	
	
	/*****IN CASE OF ACCEPTANCE NO NEED TO SET BED AND UNIT INITIALLY *******/
	public static String getAdmitDetailIPDACCEPTANCE(NursingDeskTransVO VO) throws Exception 
	{
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospitalCode());
		String strbed = "";
		String strunit = "";
		HisUtil util = null;
		util = new HisUtil("IPD", "NursingDeskTransHLP");

		NursingDeskTransBO BO = new NursingDeskTransBO();
		BO.getAdmitDetailIPD(VO);

		// if there is error
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" NursingDeskTransHLP.getAdmitDetail() --> "+ strMsg);
		}

		WebRowSet ws = VO.getStrAdmitDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();

		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='3%'>#</td> ");
		sBuffer.append("<td class='multiLabel' width ='10%'>Adm. No. </td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%'>CR No.</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='15%'>Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='8%' >Age/Sex</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='16%' >Adm. Date/Time</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Unit</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Consultant Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='14%'><font color='red'>*</font>Bed</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='14%'>Sharable Bed</td> ");

		/*sBuffer.append("<td CLASS='multiLabel' width ='11%'><img src='../../hisglobal/images/Bed_.gif'  " +
				"style='cursor:hand;cursor:pointer;'  title ='Check Bed Status'  onClick ='openPopupIPD("
				+ "\"BEDSTATUS\"" + ");'></td> ");*/
		sBuffer.append("<td CLASS='multiLabel' width ='11%'><img src='../../hisglobal/images/Bed_.gif'  " +
				"style='cursor:hand;cursor:pointer;'  title ='Check Bed Status'  data-toggle='modal' href='#myModal' id='modellink' onClick ='openPopupIPD("
				+ "\"BEDSTATUS\"" + ");'></td> ");

		if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
		{
			sBuffer.append("<td CLASS='multiLabel' width ='11%'>");
			sBuffer.append(" </td> ");
		}
		
		sBuffer.append(" </tr>");


		String tempStrBed = "",dblOccup="0";
		String tempConsultantName= "";
		String strConsultantCode="";
		String tempStrUnit = "";
		
		VO.setStrunitproperty(tempStrUnit);
		if (ws != null && ws.size() != 0) 
		{
			try 
			{
				int i = 0;
				while (ws.next())
				{
				
					
					i++;
					String stradmno = ws.getString(1);
					String strcrno = ws.getString(2);
					String strname = ws.getString(3);
					String stragesex = ws.getString(4);
					String strbedname = ws.getString(5);
					String strbedno = ws.getString(6);
					VO.setStrPreviousOccupiedbed(strbedno);
					String strtransinflag = ws.getString(7);
					String strmoveno = ws.getString(8);
					
					String strAdmDate = ws.getString(14);
					String strunitcode = ws.getString(15);
					String strroomcode = ws.getString(16);
					VO.setDepartmentName(ws.getString(17));
					String strunitname = ws.getString(19);
					String consultid = ws.getString(20);
					VO.setWardName(ws.getString(18));
					String bed_details=ws.getString(23);
					VO.setWardDblOcuup(ws.getString(24));
					VO.setStrConsultantCode(consultid);
					System.out.println("consultid"+consultid);
					System.out.println("VO.getStrConsultantCode()"+VO.getStrConsultantCode());
					
					if(VO.getStrUnitWs().size()==1)
							tempStrUnit = util.getOptionValue(VO.getStrUnitWs(), "","", false);
								else
							tempStrUnit = util.getOptionValue(VO.getStrUnitWs(), strunitcode,"0^Select Value", false);
					
					strunit = tempStrUnit;
								
					if(!VO.getWardDblOcuup().equals("2"))
					{
						if(!bed_details.equals("0^0^0^0^0^0"))
						{
							strbedno=bed_details.replace("^", "#").split("#")[5];
							
						if(VO.getStrDepartment().equals(bed_details.replace("^", "#").split("#")[1]))
						{
							if(VO.getStrWard().equals(bed_details.replace("^", "#").split("#")[3]))
							{
								VO.setBedNO(strbedno);
								dblOccup="2";
								VO.getStrUnitWs().beforeFirst();
								strunit=util.getOptionValue(VO.getStrUnitWs(), bed_details.replace("^", "#").split("#")[2],"0^Select Value", false);

							}
						}
							
							
							
						}
							
					}else
					{
							/*if(VO.getStrUnitWs().size()==1)
								strunit = util.getOptionValue(VO.getStrUnitWs(), "","", false);
							else
								strunit = util.getOptionValue(VO.getStrUnitWs(), "","0^Select Value", false);*/
						
						VO.setBedNO("0");
					}
						
					
					if (stradmno == null)
						stradmno = "";
					if (strcrno == null)
						strcrno = "";
					if (strname == null)
						strname = " ";
					if (stragesex == null)
						stragesex = "";
					if (strbedno == null || strbedno.equals("null"))
						strbedno = "";
			
					/*if (!strunitcode.equals("") && !strunitcode.equals("0")) 
					{
						strunit = "<option value = '0'>Select Value</option>";
						strunit += "<option value = \"" + strunitcode + "\">"+ strunitname + "</option>";
						VO.setStrUnit(strunitcode);
						VO.setStrRoom(strroomcode);
						BO.bed(VO);
						tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);						
						if (!strbedno.equals("") && !strbedno.equals("0")) 
						{
							strbed = "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strbed += "<option value = \"" + strbedno + "\" >"+ strbedname + "</option>";
						}
						else
						{
							strbed = tempStrBed;
						//}
					     }
					}*/
					if (!strunitcode.equals("") && !strunitcode.equals("0")) 
					{
						
							//strunit += "<option value = '0'>Select Value</option>";
						//	strunit += "<option value = \"" + strunitcode + "\" selected>"+ strunitname + "</option>";
						
						
					
						
					
						
						VO.setStrUnit(strunitcode);
						VO.setStrRoom(strroomcode);
						VO.setSharableChk("1");
						VO.getSharableChk();  //for all vaccents beds without sharable flag ontransit
						BO.bed(VO);
						BO.setConsultantName(VO);
						VO.setStrConsultantCode(consultid);
						tempConsultantName = util.getOptionValue(VO.getConsultantWS(),VO.getStrConsultantCode() ,"0^Select Value", false);
						
						tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);	
						
						if (!strbedno.equals("") && !strbedno.equals("0")) 
						{
							strbed = "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strbed += "<option value = \"" + strbedno + "\" selected>"+ strbedname + "</option>";
						}
						else
						{
							strbed = tempStrBed;
						//}
					}
						if(VO.getStrConsultantCode().equals("")  && VO.getStrConsultantCode().equals("0"))
						{
							strConsultantCode= "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strConsultantCode += "<option value = \"" + VO.getStrConsultantCode() + "\" selected>"+ VO.getStrConsultantName() + "</option>";
						}else{
							strConsultantCode=tempConsultantName;
						}
					}
					else
					{
						if(VO.getStrUnitWs().size() >0)
						{
							VO.getStrUnitWs().beforeFirst();
							if(VO.getStrUnitWs().next())
							{
								VO.setStrUnit(VO.getStrUnitWs().getString(1));
							}
							VO.setStrRoom(strroomcode);
							BO.bed(VO);
							BO.setConsultantName(VO);
							VO.setStrConsultantCode(consultid);
							/*tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);*/
							
							
							tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);	
							tempConsultantName = util.getOptionValue(VO.getConsultantWS(),VO.getStrConsultantCode() ,"0^Select Value", false);
						}
						else
						{
							tempStrBed = "<option value = '0'>Select Value</option>";
							tempConsultantName = "<option value = '0'>Select Value</option>";
						}
						strbed = tempStrBed;
					}
					
					System.out.println("tempConsultantName"+tempConsultantName);
					
				 /*if(VO.getStrUnitWs().size()==1)
				 {
							VO.getStrUnitWs().beforeFirst();
							if(VO.getStrUnitWs().next())
							{
								VO.setStrUnit(VO.getStrUnitWs().getString(1));
							}
							VO.setStrRoom(strroomcode);
							BO.bed(VO);
							//BO.setConsultantName(VO);
							tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);
							
				 }
				 else
				 {
							tempStrBed = "<option value = '0'>Select Value</option>";
							//tempConsultantName = "<option value = '0'>Select Value</option>";
				 }
				  		strbed = tempStrBed;
				  		BO.setConsultantName(VO);
				  		tempConsultantName=util.getOptionValue(VO.getConsultantWS(),consultid ,"0^Select Value", false);*/
					boolean fPrintAllRow = false;
					if (VO.getStrCrNo() == null || VO.getStrCrNo().equals("")) 
					{
						fPrintAllRow = true;
					}
					if (fPrintAllRow || strcrno.equals(VO.getStrCrNo())) 
					{
						if (strtransinflag.equals("2")) //Transfer,Intransit Cancel
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1) 
							{//only single record
								sBuffer.append("<td   bgcolor='"
												+ transPatCSS
												+ "'  width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'  ></td> ");
							} 
							else 
							{
								sBuffer.append("<td  bgcolor='"
												+ transPatCSS
												+ "' width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td  bgcolor='"
											+ transPatCSS
											+ "' width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
											+ stradmno+"</font> "
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strname
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "' width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stragesex
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "' width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strAdmDate
									+ "</font></td> ");
							sBuffer.append("<td bgcolor='"+transPatCSS+"' width ='12%'><div><select name = 'strUnit' class='comboMin' title=''  id = 'strunit"+ i+ "' onchange=\"funbedIPD(this,'strunit"+ i+ "','divbed"+ i+ "','strbed"+ i+ "');\">"+strunit+"</select>");
							sBuffer.append("<td   width ='12%'><div id='consultantDiv"+i+"'><select name = 'strConsultantCode' class='comboMin' title=''  id = 'strConsultantCodeId"+ i+ "' onchange=''>"+tempConsultantName+"</select>");
						    sBuffer.append(" <td  bgcolor='"
												+ transPatCSS
												+ "'  width ='13%'><div id='divbed"+ i+ "'><select name = 'strbed'  class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname+ "' id =  'strbedname" +i+ "''><input type='hidden' name='bedvalue' value='"+i+"'></div></td> "
		    									+ "<td><input type='checkbox' id='sharableChkid"+i+"' name='sharableChk' value='0' onclick='sharableChkBoxIpd(this,"+i+");'></td>");

						    					
							
							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						} 
						else if (strtransinflag.equals("8")) 
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1) 
							{//only single record
								sBuffer.append("<td  bgcolor='"
												+ outsidePatCSS
												+ "' width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'  ></td> ");
							} 
							else 
							{
								sBuffer.append("<td  bgcolor='"
												+ outsidePatCSS
												+ "' width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'  ><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td  bgcolor='"
											+ outsidePatCSS
											+ "' width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "'  width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strname
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "' width ='11%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stragesex
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "' width ='11%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strAdmDate
									+ "</font></td> ");
						

								sBuffer.append(" <td   bgcolor='"
												+ outsidePatCSS
												+ "' width ='16%'><select name = 'strbed'  class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname" + i
												+ "''><input type='hidden' name='bedvalue' value='"+i+"'> </td> "
		    									+ "<td><input type='checkbox' id='sharableChkid"+i+"' name='sharableChk' value='0' onclick='sharableChkBoxIpd(this,"+i+");'></td>");

							
							
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						} 
						else //Acceptance
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1)//only single record 
							{
								sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'></td> ");
							} 
							else 
							{
								sBuffer.append("<td  width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td   width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno
									+ "</font></td> ");
							sBuffer.append(" <td   width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strname + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ stragesex + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strAdmDate + "</font></td> ");
				
								sBuffer.append("<td   width ='12%'><div><select name = 'strUnit' class='comboMin' title=''  id = 'strunit"+ i+ "' onchange=\"funbedIPD(this,'strunit"+ i+ "','divbed"+ i+ "','strbed"+ i+ "');\">"+strunit+"</select>");
								sBuffer.append("<td   width ='12%'><div id='consultantDiv"+i+"'><select name = 'strConsultantCode' class='comboMin' title=''  id = 'strConsultantCodeId"+ i+ "' onchange=''>"+tempConsultantName+"</select>");
								sBuffer.append("<input type ='hidden' name ='hiddenBedName' value = '"+ strbedname+ "' id =  'strbedname"+ i+ "''></div></td> ");
							
								sBuffer.append("<td   width ='13%'><div id='divbed"+ i+ "'><select name = 'strbed'  class = 'comboMin' title='_div_popup_cntl' id = 'strbed"+ i+ "' >"+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname"
												+ i
												+ "''><input type='hidden' name='bedvalue' value='"+i+"'></div></td> "
		    									+ "<td><input type='checkbox' id='sharableChkid"+i+"' name='sharableChk' value='0' onclick='sharableChkBoxIpd(this,"+i+");'></td>");
		    					

							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						}
						sBuffer.append("<tr><td><input type='hidden' id='idvalue' value='"+i+"'></td>"
								+ "<td><input type='hidden' id='wardDblOcuup' value='"+dblOccup+"'></td></tr>");

					}
					
				}
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				VO.setStrMsgString("NursingDeskTransDAO.checklistdetail() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("NursingDeskTransHLP.getAdmitDetail() -->"
						+ e.getMessage());
			}

		} 
		else 
		{

			sBuffer
					.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
			sBuffer.append("<tr class ='multiControl'> ");
			sBuffer
					.append("<td colspan='7' align='center'> <b><font color='red'>No Record Available</font></b> </td>");
			sBuffer.append(" </tr> ");
			sBuffer.append("</table > ");

		}

		return sBuffer.toString();
	}
	
	
	
	public static String getAdmitDetailIPD(NursingDeskTransVO VO) throws Exception 
	{
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospitalCode());
		String strbed = "",dblOccup="0";
		String strunit = "";
		HisUtil util = null;
		util = new HisUtil("IPD", "NursingDeskTransHLP");

		NursingDeskTransBO BO = new NursingDeskTransBO();
		BO.getAdmitDetailIPD(VO);

		// if there is error	
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" NursingDeskTransHLP.getAdmitDetail() --> "+ strMsg);
		}

		WebRowSet ws = VO.getStrAdmitDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();

		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='3%'>#</td> ");
		sBuffer.append("<td class='multiLabel' width ='10%'>Adm. No. </td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%'>CR No.</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='15%'>Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='8%' >Age/Sex</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='16%' >Adm. Date/Time</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Unit</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%' ><font color='red'>*</font>Consultant Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='14%'><font color='red'>*</font>Bed</td> ");
		/*sBuffer.append("<td CLASS='multiLabel' width ='11%'><img src='../../hisglobal/images/Bed_.gif'  " +
				"style='cursor:hand;cursor:pointer;'  title ='Check Bed Status'  onClick ='openPopupIPD("
				+ "\"BEDSTATUS\"" + ");'></td> ");*/
		sBuffer.append("<td CLASS='multiLabel' width ='11%'><img src='../../hisglobal/images/Bed_.gif'  " +
				"style='cursor:hand;cursor:pointer;'  title ='Check Bed Status'  data-toggle='modal' href='#myModal' id='modellink' onClick ='openPopupIPD("
				+ "\"BEDSTATUS\"" + ");'></td> ");

		if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
		{
			sBuffer.append("<td CLASS='multiLabel' width ='11%'>");
			sBuffer.append(" </td> ");
		}
		
		sBuffer.append(" </tr>");


		String tempStrBed = "";
		String tempConsultantName="";
		String strConsultantCode="";
		String tempStrUnit = "";
		if(VO.getStrUnitWs().size()==1)
		tempStrUnit = util.getOptionValue(VO.getStrUnitWs(), "","", false);
		else
		tempStrUnit = util.getOptionValue(VO.getStrUnitWs(), "","0^Select Value", false);
		VO.setStrunitproperty(tempStrUnit);
		if (ws != null && ws.size() != 0) 
		{
			try 
			{
				int i = 0;
				while (ws.next())
				{
				
					strunit = tempStrUnit;
					i++;
					String stradmno = ws.getString(1);
					String strcrno = ws.getString(2);
					String strname = ws.getString(3);
					String stragesex = ws.getString(4);
					String strbedname = ws.getString(5);
					String strbedno = ws.getString(6);
					VO.setStrPreviousOccupiedbed(strbedno);
					String strtransinflag = ws.getString(7);
					String strmoveno = ws.getString(8);
					
					String strAdmDate = ws.getString(14);
					String strunitcode = ws.getString(15);
					String strroomcode = ws.getString(16);
					VO.setDepartmentName(ws.getString(17));
					String strunitname = ws.getString(19);
					VO.setWardName(ws.getString(18));
					String occupied_flg=ws.getString(21);
					
					String bed_details=ws.getString(23);
					VO.setWardDblOcuup(ws.getString(24));
					
					
					
					if(VO.getWardDblOcuup().equals("2"))
					{
						if(!bed_details.equals("0^0^0^0^0^0"))
						{
								strbedno=bed_details.replace("^", "#").split("#")[5];
								VO.setBedNO(strbedno);
								VO.setStrWard(bed_details.replace("^", "#").split("#")[3]);
								VO.setStrUnit(bed_details.replace("^", "#").split("#")[3]);
								dblOccup="2";
								VO.getStrUnitWs().beforeFirst();
								strunit=util.getOptionValue(VO.getStrUnitWs(),bed_details.replace("^", "#").split("#")[2],"0^Select Value", false);						
						}
							
					}else
						VO.setBedNO("0");
				
					
					if (stradmno == null)
						stradmno = "";
					if (strcrno == null)
						strcrno = "";
					if (strname == null)
						strname = " ";
					if (stragesex == null)
						stragesex = "";
					if (strbedno == null || strbedno.equals("null"))
						strbedno = "";
					if(occupied_flg.equals("1")){
						strbedname = "";
						strbedno = "";
						
					}
			
					if (!strunitcode.equals("") && !strunitcode.equals("0")) 
					{
						strunit = "<option value = '0'>Select Value</option>";
						strunit += "<option value = \"" + strunitcode + "\" selected>"+ strunitname + "</option>";
						VO.setStrUnit(strunitcode);
						VO.setStrRoom(strroomcode);
						VO.setSharableChk("1");
						VO.getSharableChk();  //for all vaccents beds without sharable flag ontransit
						BO.bed(VO);
						BO.setConsultantName(VO);
						tempConsultantName = util.getOptionValue(VO.getConsultantWS(),VO.getStrConsultantCode() ,"0^Select Value", false);
						tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);						
						if (!strbedno.equals("") && !strbedno.equals("0")) 
						{
							strbed = "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strbed += "<option value = \"" + strbedno + "\" selected>"+ strbedname + "</option>";
						}
						else
						{
							strbed = tempStrBed;
						//}
					}
						if(VO.getStrConsultantCode().equals("")  && VO.getStrConsultantCode().equals("0"))
						{
							strConsultantCode= "<option value = '0' style='background-color: #0000ff'>Select Value</option>";
							strConsultantCode += "<option value = \"" + VO.getStrConsultantCode() + "\" selected>"+ VO.getStrConsultantName() + "</option>";
						}else{
							strConsultantCode=tempConsultantName;
						}
					}
					else
					{
						if(VO.getStrUnitWs().size()>0)
						{
							VO.getStrUnitWs().beforeFirst();
							if(VO.getStrUnitWs().next())
							{
								VO.setStrUnit(VO.getStrUnitWs().getString(1));
							}
							VO.setStrRoom(strroomcode);
							BO.bed(VO);
							BO.setConsultantName(VO);
							tempStrBed = util.getOptionValue(VO.getStrBedDetailWS(), strbedno,"0^Select Value", false);
							tempConsultantName = util.getOptionValue(VO.getConsultantWS(),VO.getStrConsultantCode() ,"0^Select Value", false);
						}
						else
						{
							tempStrBed = "<option value = '0'>Select Value</option>";
							tempConsultantName = "<option value = '0'>Select Value</option>";
						}
						strbed = tempStrBed;
					}
					boolean fPrintAllRow = false;
					if (VO.getStrCrNo() == null || VO.getStrCrNo().equals("")) 
					{
						fPrintAllRow = true;
					}
					if (fPrintAllRow || strcrno.equals(VO.getStrCrNo())) 
					{
						if (strtransinflag.equals("2")) //Transfer,Intransit Cancel
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1) 
							{//only single record
								sBuffer.append("<td   bgcolor='"
												+ transPatCSS
												+ "'  width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(22)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'  ></td> ");
							} 
							else 
							{
								sBuffer.append("<td  bgcolor='"
												+ transPatCSS
												+ "' width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(22)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td  bgcolor='"
											+ transPatCSS
											+ "' width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
											+ stradmno+"</font> "
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strname
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "' width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stragesex
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + transPatCSS
									+ "' width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strAdmDate
									+ "</font></td> ");
							sBuffer.append("<td bgcolor='"+transPatCSS+"' width ='12%'><div><select name = 'strUnit' class='comboMin' title=''  id = 'strunit"+ i+ "' onchange=\"funbedIPD(this,'strunit"+ i+ "','divbed"+ i+ "','strbed"+ i+ "');\">"+strunit+"</select>");
							sBuffer.append("<td   width ='12%'><div id='consultantDiv"+i+"'><select name = 'strConsultantCode' class='comboMin' title=''  id = 'strConsultantCodeId"+ i+ "' onchange=''>"+tempConsultantName+"</select>");
						    sBuffer.append(" <td  bgcolor='"
												+ transPatCSS
												+ "'  width ='13%'><div id='divbed"+ i+ "'><select name = 'strbed'  class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname+ "' id =  'strbedname" +i+ "''><input type='hidden' name='bedvalue' value='"+i+"'></div></td> ");
							sBuffer.append("<td><input type='hidden' id='cancelidvalue' value='"+i+"'></td>");



							
							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						} 
						else if (strtransinflag.equals("8")) 
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1) 
							{//only single record
								sBuffer.append("<td  bgcolor='"
												+ outsidePatCSS
												+ "' width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'  ></td> ");
							} 
							else 
							{
								sBuffer.append("<td  bgcolor='"
												+ outsidePatCSS
												+ "' width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'  ><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td  bgcolor='"
											+ outsidePatCSS
											+ "' width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "'  width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno + "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "'  width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strname
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "' width ='11%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + stragesex
									+ "</font></td> ");
							sBuffer.append(" <td  bgcolor='" + outsidePatCSS
									+ "' width ='11%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strAdmDate
									+ "</font></td> ");
						

								sBuffer.append(" <td   bgcolor='"
												+ outsidePatCSS
												+ "' width ='16%'><select name = 'strbed'  class = 'comboMin'  id = 'strbed"
												+ i
												+ "' >"
												+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname" + i
												+ "''><input type='hidden' name='bedvalue' value='"+i+"'> </td> ");
							

							
							
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						} 
						else //Acceptance
						{
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1)//only single record 
							{
								sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(22)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'></td> ");
							} 
							else 
							{
								sBuffer.append("<td  width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)+"^"+ws.getString(22)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td   width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno
									+ "</font></td> ");
							sBuffer.append(" <td   width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strname + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ stragesex + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strAdmDate + "</font></td> ");
				
								sBuffer.append("<td   width ='12%'><div><select name = 'strUnit' class='comboMin' title=''  id = 'strunit"+ i+ "' onchange=\"funbedIPD(this,'strunit"+ i+ "','divbed"+ i+ "','strbed"+ i+ "');\">"+strunit+"</select>");
								sBuffer.append("<td   width ='12%'><div id='consultantDiv"+i+"'><select name = 'strConsultantCode' class='comboMin' title=''  id = 'strConsultantCodeId"+ i+ "' onchange=''>"+tempConsultantName+"</select>");
								sBuffer.append("<input type ='hidden' name ='hiddenBedName' value = '"+ strbedname+ "' id =  'strbedname"+ i+ "''></div></td> ");
							
								sBuffer.append("<td   width ='13%'><div id='divbed"+ i+ "'><select name = 'strbed'  class = 'comboMin' title='_div_popup_cntl' id = 'strbed"+ i+ "' >"+ strbed
												+ " </select><input type ='hidden' name ='hiddenBedName' value = '"
												+ strbedname
												+ "' id =  'strbedname"
												+ i
												+ "''><input type='hidden' name='bedvalue' value='"+i+"'></div></td> ");
					

							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						}
								sBuffer.append("<tr><td><input type='hidden' id='idvalue' value='"+i+"'></td>"
								+ "<td><input type='hidden' id='wardDblOcuup' value='"+dblOccup+"'></td></tr>");
					}
				}
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				VO.setStrMsgString("NursingDeskTransDAO.checklistdetail() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("NursingDeskTransHLP.getAdmitDetail() -->"
						+ e.getMessage());
			}

		} 
		else 
		{

			sBuffer
					.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
			sBuffer.append("<tr class ='multiControl'> ");
			sBuffer
					.append("<td colspan='7' align='center'> <b><font color='red'>No Record Available</font></b> </td>");
			sBuffer.append(" </tr> ");
			sBuffer.append("</table > ");

		}

		return sBuffer.toString();
	}

	// hlp function for listing check list detail

	public static String getChecklistDetail(NursingDeskTransVO VO)
			throws SQLException, Exception {

		// HisUtil util = new HisUtil("IPD", "NursingDeskTransHLP");

		NursingDeskTransBO BO = new NursingDeskTransBO();
		// NursingDeskTransVO VO = new NursingDeskTransVO();
		BO.getChecklistDetail(VO);
		if (VO.getStrMsgType().equals("1")) {

			String strMsg = VO.getStrMsgString();

			VO.setStrMsgString(" NursingDeskTransHLP.getAdmitDetail() --> "
					+ strMsg);
		}
		WebRowSet ws = VO.getStrChecklistWs();

		StringBuffer sBuffer = new StringBuffer("");

		ws.beforeFirst();

		sBuffer
				.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='5%'>&nbsp;</td> ");
		sBuffer.append("<td class='multiLabel' width ='45%'>Name  </td> ");
		sBuffer.append("<td class='multiLabel' width ='25%'>Remark  </td> ");
		sBuffer.append("<td class='multiLabel' width ='20%'>Default Value  </td> ");
		sBuffer.append(" </tr></table>");

		if (ws != null) {

			try {

				int i = 0;
				while (ws.next()) {

					i++;
					String strchklist = ws.getString(1);
					String strchklistname = ws.getString(2);
					String strChkListUnit = ws.getString(3);
					if (strchklist == null)
						strchklist = "";
					if (strchklistname == null)
						strchklistname = "";
					String onkeypress="";
					if(ws.getString(5).equals("1"))
					{
						onkeypress="return validateData(event,18)";
					}
					if(ws.getString(5).equals("2"))
					{
						onkeypress="return validateData(event,5)";
					}

					sBuffer
							.append("<table class='TABLEWIDTH' align ='center' cellspacing ='1px' cellpadding='-1px' > ");
					sBuffer.append("<tr> ");
					sBuffer
							.append("<td class='multiControl' width ='5%'><input type ='checkbox' name='strchklistCode' value =''  id ='strchklistCode"
									+ i
									+ "'  onClick ='callnursechk(this,"
									+ i
									+ ");'>"
									+ "<input type ='hidden' name='hstrchklistCode' value ='"
									+ strchklist
									+ "'  id = 'strchklist"
									+ i
									+ "'></td> ");
					sBuffer.append("<td class='multiControl' width ='45%'>"
							+ strchklistname + " </td> ");
					sBuffer.append("<td class='CONTROL' width ='25%'><input type = 'text' name = 'strChecklistRemark' disabled = 'disabled'  value =''  " +
							"id ='strChecklistRemark"+i+"' class='txtFldMin' maxlength='10' onkeypress='"+onkeypress+"'>"+strChkListUnit+"</td>");											
					sBuffer
					.append("<td class='CONTROL' width ='20%'>"+ ws.getString(4) + "</td> ");
					sBuffer.append(" </tr> ");
				}
				sBuffer.append("<tr>");
				sBuffer
						.append(" <td  colspan='4' align='CENTER'><img src='../../hisglobal/images/btn-ok.png'  onClick ='closeCheckList();' style='cursor:pointer;' />"
								+ " <img src='../../hisglobal/images/btn-ccl.png' onClick ='cancelCheckList();' style='cursor:pointer;' /> ");
				sBuffer.append(" </td> ");
				sBuffer.append("</tr> ");
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				VO
						.setStrMsgString("NursingDeskTransHLP.getChecklistDetail(NursingDeskTransVO) --> "
								+ e.getMessage());
				VO.setStrMsgType("1");
				throw new SQLException(
						"NursingDeskTransHLP.getChecklistDetail() -->"
								+ e.getMessage());

			}

		}
		return sBuffer.toString();
	}

	/**
	 * This function used to develop grid on bed details pop up window that
	 * contains bed details with their respective status
	 * 
	 * @param vo
	 * @return
	 */
	public static String getBedDetails(NursingDeskTransVO vo)
			throws SQLException, Exception {
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws = vo.getStrBedDetailWS();
		// String
		int size = 4;
		int count = 1;
		try {
			sb
					.append("<br><table class ='TABLEWIDTH' align='center' border='5' bordercolor='#9DAFC6' cellspacing='1px'>");
			sb.append("<tr>");
			// do
			// {
			int i = 0;
			while (ws.next()) {
				// for(int i=0;i<size;i++)
				// {

				// while(count<=size)
				{
					String temp[] = ws.getString(1).replace("^", "#").split("#");
					sb.append("<input type='hidden' id='" + temp[0]+ "' name='bedCode' value='" + temp[1] + "'>");
					sb.append(getCSS(ws.getString(2), temp[0]));
					/*
					 * boolean chk=ws.next();
					 * 
					 * if(chk==false) { sb.append("</tr>"); break; }
					 */
					i++;
					// count++;

				}
				if (count == size) {
					sb.append("</tr>");
					count = 1;
					sb.append("<tr>");
				} else
					count++;

				// }ws

			}
			ws.beforeFirst();
			// }while(true);

		}

		catch (Exception e) {
			// e.printStackTrace();
			vo.setStrMsgString("NursingDeskTransHLP.getBedDetails() --> "
					+ e.getMessage());
			vo.setStrMsgType("1");
			throw new SQLException("NursingDeskTransHLP.getBedDetails() -->"
					+ e.getMessage());
		} finally {
			ws = null;
		}
		sb.append("</table>");
		sb.append("<br><table align='center'>");
		sb
				.append("<tr><td bgcolor='#FFA980' width='10'></td><td class='multiLabel' width='60'>Blocked</td>");
		sb.append("<td width='10'></td>");
		sb.append("<td bgcolor='#CB5C5C' width='10'></td>");
		sb.append("<td class='multiLabel' width='60'>Occupied</td>");
		sb.append("<td width='10'></td>");
		sb.append("<td bgcolor='#8CE3E3' width='10'></td>");
		sb
				.append("<td class='multiLabel' width='60'>Booked</td><td width='10'></td>");
		sb.append("<td bgcolor='#88F78D' width='10'></td>\n");
		sb.append("<td class='multiLabel' width='60'>Vacant</td>");
		sb.append("<td width='10'></td><td bgcolor='#FEEB9E' width='10'></td>");
		sb
				.append("	<td class='multiLabel' width='60'>Vacant But Dirty</td>	</tr>");
		sb.append("</table>");

		return sb.toString();
	}

	/**
	 * This function invoke makeCss() of this class
	 * 
	 * @param bedName
	 * @param status
	 * @return String
	 */
	public static String getCSS(String bedName, String status) {
		int strBedStatusCode = 0;
		strBedStatusCode = Integer.parseInt(status);
		return makeCss(bedName, strBedStatusCode);
	}

	/**
	 * This function decide css of bed at run time on the basis of bed status
	 * code
	 * 
	 * @param bedName
	 * @param bedStatusCode
	 * @return String
	 */
	public static String makeCss(String bedName, int bedStatusCode) 
	{
		String css = "";
		switch (bedStatusCode) {
		case 10:
			css = "<td bgcolor='#9EFEA6' width='5%'><input style='background-color:#9EFEA6;;width: 70px' "
					+ "type='button' name='bedDetails' disabled value='"
					+ bedName + "' onClick='getBedname(this)'></td>";
			break;

		case 11:
			css = "<td bgcolor='#FEEB9E' width='5%'><input style='background-color:#FEEB9E;;width: 70px' "
					+ "type='button' name='bedDetails' disabled value='"
					+ bedName + "'></td>";
			break;

		case 12:
			css = "<td bgcolor='#CB5C5C' width='5%'><input style='background-color:#CB5C5C;;width: 70px' "
					+ "type='button' name='bedDetails' disabled value='"
					+ bedName + "'></td>";
			break;
		case 13:
			css = "<td bgcolor='#FFA980' width='5%'><input style='background-color:#FFA980;;width: 70px' "
					+ "type='button' name='bedDetails' disabled value='"
					+ bedName + "'></td>";
			break;
		case 14:
			css = "<td bgcolor='#8CE3E3' width='5%'><input style='background-color:#8CE3E3;;width: 70px' "
					+ "type='button' name='bedDetails' disabled value='"
					+ bedName + "'></td>";
			break;
		}
		return css;
	}
	
	public static String getAdmitDetailNr(NursingDeskTransVO VO) throws Exception 
	{
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospitalCode());
		HisUtil util = null;
		util = new HisUtil("IPD", "NursingDeskTransHLP");

		NursingDeskTransBO BO = new NursingDeskTransBO();
		BO.getAdmitDetailIPDNr(VO);

		// if there is error
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" NursingDeskTransHLP.getAdmitDetail() --> "+ strMsg);
		}

		WebRowSet ws = VO.getStrAdmitDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();

		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='3%'>#</td> ");
		sBuffer.append("<td class='multiLabel' width ='10%'>Adm. No. </td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%'>CR No.</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='15%'>Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='8%' >Age/Sex</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='16%' >Adm. Date/Time</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='25%' ><font color='red'>*</font>Remarks</td> ");
		

	
		
		sBuffer.append(" </tr>");
		/* sBuffer.append(" </table>"); */

		
		if (ws != null && ws.size() != 0) 
		{
			try 
			{
				int i = 0;
				while (ws.next())
				{
					//strbed = tempStrBed;
					
					i++;
					String stradmno = ws.getString(1);
					String strcrno = ws.getString(2);
					String strname = ws.getString(3);
					String stragesex = ws.getString(4);
					String strbedname = ws.getString(5);
					String strRemarks;
					String strtransinflag = ws.getString(7);
					String strmoveno = ws.getString(8);
					String strbedno = ws.getString(6);
					VO.setStrPreviousOccupiedbed(strbedno);
					String strunitcode = ws.getString(15);
					String strroomcode = ws.getString(16);
					String strunitname = ws.getString(19);
					String strAdmDate = ws.getString(14);
					VO.setDepartmentName(ws.getString(17));
					VO.setWardName(ws.getString(18));
					VO.setTimelt(ws.getString(24));
					if (stradmno == null)
						stradmno = "";
					if (strcrno == null)
						strcrno = "";
					if (strname == null)
						strname = " ";
					if (stragesex == null)
						stragesex = "";
					if (strbedno == null || strbedno.equals("null"))
						strbedno = "";
					/*if (!strbedno.equals("") && !strbedno.equals("0")) 
					{
						strbed = "<option value = '0'>Select Value</option>";
						strbed += "<option value = \"" + strbedno + "\">"+ strbedname + "</option>";
					}
					else
					{
						strbed = "<option value = '0'>Select Value</option>";
					}*/
					
					boolean fPrintAllRow = false;
					if (VO.getStrCrNo() == null || VO.getStrCrNo().equals("")) 
					{
						fPrintAllRow = true;
					}
					if (fPrintAllRow || strcrno.equals(VO.getStrCrNo())) 
					{
					
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1)//only single record 
							{
								//sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
								sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'></td> ");
							} 
							else 
							{
								sBuffer.append("<td  width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td   width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno
									+ "</font></td> ");
							sBuffer.append(" <td   width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strname + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ stragesex + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strAdmDate + "</font></td> ");
							
							sBuffer.append("<td   width ='25%'><input type='text' name='strRemarks' value=''>");
								

							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1"))
							{
								/*sBuffer.append(" <td  width ='11%'  >");
								sBuffer.append("<img src='../../hisglobal/images/Belongings_1.png'  style='cursor:hand;cursor:pointer;' " +
											"  title = 'Belonging Details' onClick ='beforedisplayBelogingPopup(this,"
											+ i + ");'> ");*/
							}
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								/*sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");*/
								
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
							sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						}
					}
				
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				VO.setStrMsgString("NursingDeskTransDAO.checklistdetail() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("NursingDeskTransHLP.getAdmitDetailNr() -->"
						+ e.getMessage());
			}

		} 
		else 
		{

			sBuffer
					.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
			sBuffer.append("<tr class ='multiControl'> ");
			sBuffer
					.append("<td colspan='7' align='center'> <b><font color='red'>No Record Available</font></b> </td>");
			sBuffer.append(" </tr> ");
			sBuffer.append("</table > ");

		}

		return sBuffer.toString();
	}
	public static String getAdmitDetailNrLeave(NursingDeskTransVO VO) throws Exception 
	{
		IpdConfigUtil ipdUtil = new IpdConfigUtil(VO.getStrHospitalCode());
		HisUtil util = null;
		util = new HisUtil("IPD", "NursingDeskTransHLP");

		NursingDeskTransBO BO = new NursingDeskTransBO();
		BO.getAdmitDetailIPDLeave(VO);

		// if there is error
		if (VO.getStrMsgType().equals("1")) 
		{
			String strMsg = VO.getStrMsgString();
			VO.setStrMsgString(" NursingDeskTransHLP.getAdmitDetail() --> "+ strMsg);
		}

		WebRowSet ws = VO.getStrAdmitDetailWs();

		StringBuffer sBuffer = new StringBuffer("");
		ws.beforeFirst();

		sBuffer.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
		sBuffer.append("<tr> ");
		sBuffer.append("<td class='multiLabel' width ='3%'>#</td> ");
		sBuffer.append("<td class='multiLabel' width ='10%'>Adm. No. </td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='12%'>CR No.</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='15%'>Name</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='8%' >Age/Sex</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='16%' >Adm. Date/Time</td> ");
		sBuffer.append("<td CLASS='multiLabel' width ='25%' ><font color='red'>*</font>Remarks</td> ");
		

	
		
		sBuffer.append(" </tr>");
		/* sBuffer.append(" </table>"); */

		
		if (ws != null && ws.size() != 0) 
		{
			try 
			{
				int i = 0;
				while (ws.next())
				{
					//strbed = tempStrBed;
					
					i++;
					String stradmno = ws.getString(1);
					String strcrno = ws.getString(2);
					String strname = ws.getString(3);
					String stragesex = ws.getString(4);
					String strbedname = ws.getString(5);
					String strRemarks;
					String strtransinflag = ws.getString(7);
					String strmoveno = ws.getString(8);
					String strbedno = ws.getString(6);
					VO.setStrPreviousOccupiedbed(strbedno);
					String strunitcode = ws.getString(15);
					String strroomcode = ws.getString(16);
					String strunitname = ws.getString(19);
					String strAdmDate = ws.getString(14);
					VO.setDepartmentName(ws.getString(17));
					VO.setWardName(ws.getString(18));
					if (stradmno == null)
						stradmno = "";
					if (strcrno == null)
						strcrno = "";
					if (strname == null)
						strname = " ";
					if (stragesex == null)
						stragesex = "";
					if (strbedno == null || strbedno.equals("null"))
						strbedno = "";
					/*if (!strbedno.equals("") && !strbedno.equals("0")) 
					{
						strbed = "<option value = '0'>Select Value</option>";
						strbed += "<option value = \"" + strbedno + "\">"+ strbedname + "</option>";
					}
					else
					{
						strbed = "<option value = '0'>Select Value</option>";
					}*/
					
					boolean fPrintAllRow = false;
					if (VO.getStrCrNo() == null || VO.getStrCrNo().equals("")) 
					{
						fPrintAllRow = true;
					}
					if (fPrintAllRow || strcrno.equals(VO.getStrCrNo())) 
					{
					
							sBuffer.append("<tr class ='multiControl'> ");
							if (i == 1)//only single record 
							{
								//sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
								sBuffer.append("<td    width ='3%'><input type ='radio' checked='checked' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i + "\");'></td> ");
							} 
							else 
							{
								sBuffer.append("<td  width ='3%'><input type ='radio' name = 'strchk'  value ='"
												+ strcrno+"^"+ws.getString(15)+"^"+ws.getString(16)
												+ "' id = 'strchk"
												+ i
												+ "'  onClick = 'callchk(this,\""
												+ i
												+ "\");'><input type ='hidden' name = 'stroldradio' value = ''  id = 'stroldradio"
												+ i + "'></td> ");
							}
							sBuffer.append("<td width ='13%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\"> "
											+ stradmno+"</font>"
											+ "<input type ='hidden' name = 'hstrcrno' value = '"
											+ strcrno
											+ "'  id = 'strcrno"
											+ i
											+ "'><input type ='hidden' name = 'hstradmno' value = '"
											+ stradmno + "'  id = 'stradmno"
											+ i + "''> ");
							sBuffer.append("<input type ='hidden' name = 'strhtransinflg' value = '"
											+ strtransinflag
											+ "'  id = 'strhtransinflg"
											+ i
											+ "'><input type ='hidden' name = 'strhmno' value = '"
											+ strmoveno
											+ "'  id = 'strhmno"
											+ i + "''> </td> ");
							sBuffer.append(" <td   width ='16%'><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">" + strcrno
									+ "</font></td> ");
							sBuffer.append(" <td   width ='16%'  ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strname + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ stragesex + "</font></td> ");
							sBuffer.append(" <td  width ='8%' ><font color=\"#000000\"  size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
									+ strAdmDate + "</font></td> ");
							
							sBuffer.append("<td   width ='25%'><input type='text' name='strRemarks' value=''>");
								

							
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1"))
							{
								/*sBuffer.append(" <td  width ='11%'  >");
								sBuffer.append("<img src='../../hisglobal/images/Belongings_1.png'  style='cursor:hand;cursor:pointer;' " +
											"  title = 'Belonging Details' onClick ='beforedisplayBelogingPopup(this,"
											+ i + ");'> ");*/
							}
							if(ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
								/*sBuffer.append(" <img src='../../hisglobal/images/Check_List.png' style='cursor:hand;cursor:pointer;' " +
											"   title = 'Nurse Check List'  onClick ='openPopupajax(\"CHECKLIST\","
											+ i + ");'>");*/
								
							}
							if(ipdUtil.getStrBelongingRequired().equals("1") || ipdUtil.getStrIssueItemRequired().equals("1") || ipdUtil.getStrNurseChecklistMandatory().equals("1"))
							{
							sBuffer.append(" </td> ");
							}
							sBuffer.append(" </tr> ");
						}
					}
				
				sBuffer.append("</table > ");

			} catch (SQLException e) {
				VO.setStrMsgString("NursingDeskTransDAO.checklistdetail() --> "
						+ e.getMessage());
				VO.setStrMsgType("1");
				throw new Exception("NursingDeskTransHLP.getAdmitDetailNr() -->"
						+ e.getMessage());
			}

		} 
		else 
		{

			sBuffer
					.append("<table class ='TABLEWIDTH' align ='center' cellspacing ='1px'> ");
			sBuffer.append("<tr class ='multiControl'> ");
			sBuffer
					.append("<td colspan='7' align='center'> <b><font color='red'>No Record Available</font></b> </td>");
			sBuffer.append(" </tr> ");
			sBuffer.append("</table > ");

		}

		return sBuffer.toString();
	}
	
}
