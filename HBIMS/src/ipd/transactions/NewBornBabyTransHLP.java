package ipd.transactions;


import java.util.ResourceBundle;

import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import java.util.ArrayList;
import java.util.List;

public class NewBornBabyTransHLP {
/**
 * This function used to develop grid on bed details pop up window 
 * that contains bed details with their respective status
 * @param vo
 * @return
 */
	public static String getBedDetails(NewBornBabyTransVO vo)
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws=vo.getBedDetailWS();
		int size=4;
		int count=1;
		try
		{
			sb.append("<br><table align='center' border='5' bordercolor='#9DAFC6' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<input type='hidden' name='strVacantBed' value="+vo.getStrVacantBed()+">");
			//do
			//{
				int i=0;
				ws.next();
				while(i<ws.size())
				{
				//for(int i=0;i<size;i++)
				//{
					while(count<=size)
					{
						String temp[]=ws.getString(1).replace("^", "#").split("#");
						sb.append("<input type='hidden' id='"+temp[0]+"'name='bedCode' value='"+temp[1]+"'>");
						sb.append(getCSS(temp[0],ws.getString(2)));
						boolean chk=ws.next();
						if(chk==false)
						{
							sb.append("</tr>");
							break;
						}
						i++;
						count++;
					}
					if(count>4)
					{
						sb.append("</tr>");
						count=1;
						sb.append("<tr>");
					}
				//}
				}
				ws.beforeFirst();	
			//}while(true);
		}
		catch(Exception e)
		{
			vo.setStrMsgString("NewBornBabyTransHLP.getBedDetails() --> "+ e.getMessage());
			vo.setStrMsgType("1");
		}
		sb.append("</table>");
		sb.append("<br><table align='center'>");
		sb.append("<tr><td bgcolor='#FFA980' width='10'></td><td class='multiLabel' width='60'>Blocked</td>");
  		sb.append("<td width='10'></td>");
  		sb.append("<td bgcolor='#CB5C5C' width='10'></td>");
  		sb.append("<td class='multiLabel' width='60'>Occupied</td>");
  		sb.append("<td width='10'></td>");
		sb.append("<td bgcolor='#8CE3E3' width='10'></td>");
  		sb.append("<td class='multiLabel' width='60'>Booked</td><td width='10'></td>");
  		sb.append("<td bgcolor='#88F78D' width='10'></td>\n");
  		sb.append("<td class='multiLabel' width='60'>Vacant</td>");
  		sb.append("<td width='10'></td><td bgcolor='#FEEB9E' width='10'></td>");
  		sb.append("	<td class='multiLabel' width='60'>Vacant But Dirty</td>	</tr>");
  		sb.append("</table>");
		return sb.toString();
	}
	/**
	 * This function invoke makeCss() of this class
	 * @param bedName
	 * @param status 
	 * @return String
	 */
	public static String getCSS(String bedName,String status)
	{
		int strBedStatusCode=0;
		strBedStatusCode=Integer.parseInt(status);
		return  makeCss(bedName,strBedStatusCode);
	}
	/**
	 * This function decide css of bed at run time on the basis of bed status code
	 * @param bedName
	 * @param bedStatusCode
	 * @return String
	 */
	public static String makeCss(String bedName,int bedStatusCode)
	{
		String css="";
		switch(bedStatusCode)
		{
			case 10:
				css="<td bgcolor='#9EFEA6' width='5%'><input style='background-color:#9EFEA6;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"' onClick='getBedname(this)'></td>" ;
				break;
					
			case 11:
				css="<td bgcolor='#FEEB9E' width='5%'><input style='background-color:#FEEB9E;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
						
			case 12:
				css="<td bgcolor='#CB5C5C' width='5%'><input style='background-color:#CB5C5C;;width: 70px' " +
						"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
			case 13:
				css="<td bgcolor='#FFA980' width='5%'><input style='background-color:#FFA980;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
			case 14:
				css="<td bgcolor='#8CE3E3' width='5%'><input style='background-color:#8CE3E3;;width: 70px' " +
				"type='button' name='bedDetails' disabled value='"+
				bedName+"'></td>" ;
				break;
		}
		return css;
	}
	/**
	 * This function is used to generate html(New Modification for address) at runtime.
	 * @param vo
	 * @return
	 */
	public static String getPatientDetailModi(NewBornBabyTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		String strPhoneNo=vo.getStrPhoneNo();
		String strMobileNo=vo.getStrMobileNo();
		String strCity=vo.getStrCity();
		String strState=vo.getStrState().trim();
		String strStateName=vo.getStrStateName();
		//String strDistrict=vo.getStrDistrict();
		String strDistrictName=vo.getStrDistrictName();
		String strCountry="";
		String strDistrict=vo.getStrDistrict().trim();
		String strPinCode=vo.getStrPinCode();
		String strMotherTreatmentCateg="";
		String strGender="0";
		String strCurrentDate=vo.getStrCurrentDate();
		String strBabyName="B/O "+vo.getStrMotherName();
		String strTehsil="";
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		if(strHouseNo.equals(" "))
		{
			strHouseNo="";
		}
		if(strStreet.equals(" "))
		{
			strStreet="";
		}
		if(strPhoneNo.equals(" "))
		{
			strPhoneNo="";
		}
		if(strMobileNo.equals(" "))
		{
			strMobileNo="";
		}
		if(strCity.equals(" "))
		{
			strCity="";
		}
		if(strDistrict.equals(" "))
		{
			strDistrict="";
		}
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),
						vo.getStrTreatmentCategoryCode()+"^"+vo.getSetStrMotherCatgrp(),"0^Select Value", false);
			strGender=util.getOptionValue(vo.getGenderWS(),"","0^Select Value", false);
			//System.out.println("caste"+vo.getStrPatientCaste()+"ajay");
			strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste().replaceAll(" ", ""),"0^Select Value", false);

			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			
			strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
/*			if(vo.getStrCountryCode().equals("IND")){
					vo.setStrDistrict(vo.getStrDistrictCode());
			}*/
					
			sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Number Of Children</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='3'>");
			
			List<String> al=new ArrayList<String>();
			al.add("1");
			al.add("Single");
			al.add("2");
			al.add("Twin");
			al.add("3");
			al.add("Triplet");
			al.add("4");
			al.add("Quadruplet");
			al.add("5");
			al.add("Pentaplet");
			al.add("6");
			al.add("Hexaplet");
			al.add("7");
			al.add("Septaplet");
			al.add("8");
			al.add("Octaplet");
			al.add("9");
			al.add("Nonaplet");
			String option="";
			
			
			//Data available mother already given birth to multiple babies then disable the combo 
			//and set the number of babies
			if(!vo.getStrNumberOfChildrenBorn().equals("0"))
			
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' class='comboNormal' disabled='disabled'>");
				option=util.getOptionValue(al, vo.getStrNumberOfChildrenBorn(), "");
				sBuffer.append(option+"</select>");
			}
			else// New Mother. No multiple babies. Set default to Single
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' class='comboNormal'>");
				option=util.getOptionValue(al, "1", "");
				sBuffer.append(option+"</select>");
			}
			
			/*
			sBuffer.append("<option value='1' selected='selected'>Single</option>");
			sBuffer.append("<option value='2'>Double</option>");
			sBuffer.append("<option value='3'>Triple</option>");
			sBuffer.append("<option value='4'>Quadruple</option>");
			sBuffer.append("<option value='5'>Pentuple</option>");
			sBuffer.append("<option value='6'>Hextuple</option>");
			sBuffer.append("<option value='7'>Septuple</option>");
			sBuffer.append("<option value='8'>Octuple</option>");
			sBuffer.append("<option value='9'>Nonuple</option></select>"); */
			
			
			sBuffer.append("<input name='strNumberOfChildren' value='1' type='hidden'>");			
			sBuffer.append("</td></tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Baby Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strBabyName' value='"
							+ strBabyName+ "'>\n"+strBabyName+"</td>");
			sBuffer.append("<td width='25%' class='LABEL'>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMotherTreatmentCateg' " );
			sBuffer.append("class='comboNormal' onChange='getChargeValue();'>");
			sBuffer.append(strMotherTreatmentCateg);
			sBuffer.append("</select></td></tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Id Mark1</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append("<input type='text' class='txtFldMax' name='strIdMark1' maxlength='12' " );
			sBuffer.append("onkeypress='return validateData(event,4);'\n");
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL'>Id Mark2</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append("<input type='text' class='txtFldMax' name='strIdMark2' maxlength='12' " );
			sBuffer.append("onkeypress='return validateData(event,4);' >\n");
			sBuffer.append("</td></tr>\n");
			
			sBuffer.append("<td width='25%' class='LABEL'>Date Of Birth</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div id='birthDateId'>");
			sBuffer.append(HisUtil.getDatePicker("strDob", strCurrentDate, true));
			sBuffer.append("</div><div id='birthDateOnlineId'></div></td>");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Birth Time</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div id='birthTimeId'>");
			sBuffer.append("<input type='text' class='txtFldSmall' name='strBirthHour' maxlength='2' " );
			sBuffer.append("onkeypress='return validateData(event,5);' onkeyup='hour(this,event),focus(this)' " );
			sBuffer.append("onblur='moveToNextBox(this,event)';><b>:</b>\n");
			sBuffer.append("<input type='text' class='txtFldSmall' name='strBirthMin' maxlength='2' " );
			sBuffer.append("onkeypress='return validateData(event,5);' onkeyup='min(this,event);' onblur='moveToNextBox(this,event)'><b>:</b>\n");
			sBuffer.append("<select class='comboSmall' name='strAmPm'><option value='1'>AM</option><option value='2'>PM</option>\n");
			sBuffer.append("</select></div><div id='birthTimeOnlineId'></div></td>");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Gender</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div id='birthGenderOnlineId'></div>" );
			sBuffer.append("<div id='birthGenderId'><select name='strGender' class='comboNormal'>");
			sBuffer.append(strGender);
			sBuffer.append("</div></td><td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion' class='comboNormal'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Father Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='textbox' name='strFatherName' value='"+strFatherName+  "' nowrap='nowrap'></td>\n");
			
			sBuffer.append("<td width='25%' class='LABEL'>Patient Caste</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strPatientCaste' tabindex='1' class='comboNormal'>\n");
			sBuffer.append(strPatientCaste);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td  colspan='4' class='TITLE'>Address");
			sBuffer.append("</td></tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' value='"
							+strHouseNo+"' class='txtFldNormal' maxlength='15'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Street/Mohallah</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strStreet' value='"
						+strStreet+ "' class='txtFldMax' maxlength='30'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>City/Village</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strCity' value='"
							+strCity+ "' class='txtFldMax' maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='districtTextBoxDiv'><input type='text' name='strDistrictName' value='"
						+strDistrictName+ "' class='txtFldMax'  maxlength='30' onkeypress='return validateData(event,4);'></div><div id='districtSelectBoxDiv'><select name='strDistrict'"+
			"' class='comboNormal' onchange='onchangeDistrict();' id='districtComboDiv'>\n");
			sBuffer.append(strDistrict);
			sBuffer.append("</select></div>\n");
			sBuffer.append("</td></tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mandal</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='30' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='stateTextBoxDiv'><input type='text' name='strStateName' id='strStateId' value='"+strStateName+"'/></div><div id='stateComboDiv'><select name='strState" +
			"' class='comboNormal' onchange='onchangeState()' id='stateComboDiv'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></div></td>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry" +
			"' class='comboNormal'  onchange='onchangeCountry()'>\n");
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<script>onchangeCountry();</script>");
			if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
					{
						sBuffer.append("<tr style='display:none;'>\n");
						sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:</td>\n");				
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("</tr>\n");
					}
					else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
							   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
					{
								sBuffer.append("<tr>\n");
								sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:</td>\n");				
								sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
								sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pincode:</div></td>\n");
								sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></div></td>\n");
								sBuffer.append("</tr>\n");
					}
					else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
							   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
					{
								sBuffer.append("<tr>\n");
								sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
								sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
								sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'><font color='red'>*</font>Mobile No:</div></td>\n");				
								sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></div></td>\n");
								sBuffer.append("</tr>\n");
					}
					else
					{
						sBuffer.append("<tr>\n");
						sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:</td>\n");				
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("</tr>\n");
					}
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPhoneNo' value='"
			+strPhoneNo+"' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			/*sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL' colspan='1'>Address1</td>");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='3'><input type='text' name='strAddress' value='"
							+ strAddress + "' class='txtFldBig'></td>");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL' colspan='1'>Address2</td>");
			sBuffer.append("<td class='CONTROL' colspan='3'><input type='text' name='strAddress2' value='"
							+"" + "' class='txtFldBig'></td>");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>City</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strCity' value='"
					+ strCity + "' class='txtFldNormal'>");
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL'>State</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strState" +
					"' class='comboNormal'>");
			sBuffer.append(strState);
			sBuffer.append("</select> </td>");*/
			sBuffer.append("</tr></table>\n");
			
		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("NewBornBabyTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
	
	public static String onlineBabyBornEntery(NewBornBabyTransVO _NewBornBabyTransVO){
		StringBuffer sb = new StringBuffer("");
		NewBornBabyTransBO objNewBornBabyTransBO = null;
		WebRowSet ws = null;
		try{
			objNewBornBabyTransBO = new NewBornBabyTransBO();
			
			objNewBornBabyTransBO.onlineBabyBornEntery(_NewBornBabyTransVO);
			
			
			sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td width='5%'class='TITLE'>");
			sb.append("<div id='plusOnlineId' align='left' style='display: none;'><img ");
			sb.append("src='../../hisglobal/images/plus.gif' onClick='showOnline();'") ;
			sb.append("style='cursor: pointer;' /> New Born Details</div> ");
			sb.append("<div id='minusOnlineId' style='display: block;' align='left'><img ");
			sb.append("src='../../hisglobal/images/minus.gif' onClick='hideOnline();' ");
			sb.append("style='cursor: pointer;' /> New Born Details</div>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			sb.append("<div id='onlineDetailsId'>");
			sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			sb.append("<tr>");
			sb.append("<td width='5%'class='multiLabel'>#");
			sb.append("</td>");
			sb.append("<td width='35%'class='multiLabel'>Birth Date/ Time");
			sb.append("</td>");
			sb.append("<td width='30%'class='multiLabel'>Gender");
			sb.append("</td>");
			sb.append("<td width='30%'class='multiLabel'>Birth Nature");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			ws=_NewBornBabyTransVO.getOnlineBabyBornWS();
			
			sb.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
			boolean fFirstFlag=true;
			while(ws!=null && ws.size()>0 && ws.next()){
				if(fFirstFlag)
				{
					sb.append("<tr ><td class='multiLabel' width='5%'>");
					sb.append("<input type='radio' onclick='selectOnlineBabyList(this)' checked name='strOnlineBaby' value='");
				}
				else
				{
					sb.append("<tr ><td class='multiLabel' width='5%'>");
					sb.append("<input type='radio' onclick='selectOnlineBabyList(this)' name='strOnlineBaby' value='");
				}
				fFirstFlag = false;
				sb.append(ws.getString(4));
				sb.append("^");
				sb.append(ws.getString(5));
				sb.append("^");
				sb.append(ws.getString(9));
				sb.append("^");
				sb.append(ws.getString(10));
				sb.append("^");
				sb.append(ws.getString(11));
				sb.append("'></td>");
				sb.append("<td class='multiControl' width='35%'>");
				sb.append(ws.getString(5));
				sb.append("</td>");
				sb.append("<td class='multiControl' width='30%'>");
				sb.append(ws.getString(9));
				sb.append("</td>");
				sb.append("<td class='multiControl' width='30%'>");
				//sb.append(ws.getString(6));
				sb.append("Still Birth");//1 Means Still birth,2 means Live birth,3 means still birth dead
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("</div>");
		}		
		catch(Exception e)
		{
			_NewBornBabyTransVO.setStrMsgString("NewBornBabyTransHLP.onlineBabyBornEntery()--> "+ e.getMessage());
			_NewBornBabyTransVO.setStrMsgType("1");
		}
		return sb.toString();
	}
	
	public static String getPatDtlModi(NewBornBabyTransVO vo)
	{
		
		StringBuffer sBuffer = new StringBuffer("");
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		bo.setAdmittedBabyCountModi(vo);
		String strFatherName=vo.getStrFatherName();
		String strReligion="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strStreet=vo.getStrStreet().trim();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strCity=vo.getStrCity().trim();
		String strState=vo.getStrState();
		String strDistrict=vo.getStrDistrict();
		String strCountry="";
		String strPinCode=vo.getStrPinCode().trim();
		String strMotherTreatmentCateg="";
		String strGender="0";
		String strCurrentDate=vo.getStrCurrentDate();
		String strCurrentDateTime=vo.getStrCurrentDateTime();
		String strBabyName="B/O "+vo.getStrMotherName();
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
			
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),
						vo.getStrTreatmentCategoryCode()+"^"+vo.getSetStrMotherCatgrp(),"0^Select Value", false);
			strGender=util.getOptionValue(vo.getGenderWS(),"","0^Select Value", false);
			
			/*sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Number Of Children</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='2'>");*/
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Number Of Children</label>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			List<String> al=new ArrayList<String>();
			al.add("1");
			al.add("Single");
			al.add("2");
			al.add("Twin");
			al.add("3");
			al.add("Triplet");
			al.add("4");
			al.add("Quadruplet");
			al.add("5");
			al.add("Pentaplet");
			al.add("6");
			al.add("Hexaplet");
			al.add("7");
			al.add("Septaplet");
			al.add("8");
			al.add("Octaplet");
			al.add("9");
			al.add("Nonaplet");
			String option="";
			
			System.out.println("vo.getStrNumberOfChildrenBorn()"+vo.getStrNumberOfChildrenBorn());
			//Data available mother already given birth to multiple babies then disable the combo 
			//and set the number of babies
			if(!vo.getStrNumberOfChildrenBorn().equals("0"))
			
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' id='strNumberOfChildrenBornCmb' onchange='checkNumOfBabies()' class='browser-default custom-select' >");
				option=util.getOptionValue(al, vo.getStrNumberOfChildrenBorn(), "");
				sBuffer.append(option+"</select>");
			}
			else// New Mother. No multiple babies. Set default to Single
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' class='browser-default custom-select'>");
				option=util.getOptionValue(al, "1", "");
				sBuffer.append(option+"</select>");
			}
			
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'></div>\n");
			sBuffer.append("<div class='col-sm-4'>Number Of Baby Delivered (currently entered)</div>\n");
			sBuffer.append("<div class='col-sm-2'><a name='strNumberOfChildren' onclick='showAdmittedBabyDetails()'  value="+vo.getBabyCountWhoseDetailIsEntered()+"style='cursor: pointer; color: rgb(6,69,173);'><b><u>"+vo.getBabyCountWhoseDetailIsEntered()+"</u></b>");		// to be converted to link buttion	
			sBuffer.append("</div>");
			
						}
		catch(Exception e)
		{
			
			vo.setStrMsgString("NewBornBabyTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
	public static String getPatDtlModi_BS(NewBornBabyTransVO vo)
	{
		
		StringBuffer sBuffer = new StringBuffer("");
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		bo.setAdmittedBabyCountModi(vo);
		String strFatherName=vo.getStrFatherName();
		String strReligion="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strStreet=vo.getStrStreet().trim();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strCity=vo.getStrCity().trim();
		String strState=vo.getStrState();
		String strDistrict=vo.getStrDistrict();
		String strCountry="";
		String strPinCode=vo.getStrPinCode().trim();
		String strMotherTreatmentCateg="";
		String strGender="0";
		String strCurrentDate=vo.getStrCurrentDate();
		String strCurrentDateTime=vo.getStrCurrentDateTime();
		String strBabyName="B/O "+vo.getStrMotherName();
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
			
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),
						vo.getStrTreatmentCategoryCode()+"^"+vo.getSetStrMotherCatgrp(),"0^Select Value", false);
			strGender=util.getOptionValue(vo.getGenderWS(),"","0^Select Value", false);
			
			/*sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Number Of Children</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='2'>");*/
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Number Of Children</label>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			List<String> al=new ArrayList<String>();
			al.add("1");
			al.add("Single");
			al.add("2");
			al.add("Twin");
			al.add("3");
			al.add("Triplet");
			al.add("4");
			al.add("Quadruplet");
			al.add("5");
			al.add("Pentaplet");
			al.add("6");
			al.add("Hexaplet");
			al.add("7");
			al.add("Septaplet");
			al.add("8");
			al.add("Octaplet");
			al.add("9");
			al.add("Nonaplet");
			String option="";
			
			System.out.println("vo.getStrNumberOfChildrenBorn()"+vo.getStrNumberOfChildrenBorn());
			//Data available mother already given birth to multiple babies then disable the combo 
			//and set the number of babies
			if(!vo.getStrNumberOfChildrenBorn().equals("0"))
			
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' id='strNumberOfChildrenBornCmb' onchange='checkNumOfBabies()' class='browser-default custom-select' >");
				option=util.getOptionValue(al, vo.getStrNumberOfChildrenBorn(), "");
				sBuffer.append(option+"</select>");
			}
			else// New Mother. No multiple babies. Set default to Single
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' class='browser-default custom-select'>");
				option=util.getOptionValue(al, "1", "");
				sBuffer.append(option+"</select>");
			}
			
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'></div>\n");
			sBuffer.append("<div class='col-sm-4'>Number Of Baby Delivered (currently entered)</div>\n");
			sBuffer.append("<div class='col-sm-2'><a name='strNumberOfChildren' onclick='showAdmittedBabyDetails()'  value="+vo.getBabyCountWhoseDetailIsEntered()+"style='cursor: pointer; color: rgb(6,69,173);'><b><u>"+vo.getBabyCountWhoseDetailIsEntered()+"</u></b>");		// to be converted to link buttion	
			sBuffer.append("</div>");
			
						}
		catch(Exception e)
		{
			
			vo.setStrMsgString("NewBornBabyTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
	public static String getAdmittedBabyDtlModi(NewBornBabyTransVO vo)
	{
		
		StringBuffer sBuffer = new StringBuffer("");
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		bo.setAdmittedBabyDetailsModi(vo);
			
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");

			sBuffer.append("<div class='popup'>");
			sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
						
			WebRowSet ws = vo.getAdmittedBabyDetails();
			
			
			
			sBuffer.append("<tr class='HEADER'>\n");
			sBuffer.append("<td width='100%' colspan='4' align='center'><b><u>Admitted Baby Details</u></b></td>");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL' align='center'>Admission No.</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' align='center'>Admission Date</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' align='center'>CR No.</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' align='center'>Name (Gender)</td>\n");
			sBuffer.append("</tr>");
			
			if(ws !=null)
			{
				// such that it works even if a single record is fetch....
				do
				{
					sBuffer.append("<tr>\n");
					sBuffer.append("<td width='25%' class='CONTROL' align='center'>");
					sBuffer.append(ws.getString(1));
					sBuffer.append("</td>");
					
					sBuffer.append("<td width='25%' class='CONTROL' align='center'>");
					sBuffer.append(ws.getString(2));
					sBuffer.append("</td>");
					
					sBuffer.append("<td width='25%' class='CONTROL' align='center'>");
					sBuffer.append(ws.getString(3));
					sBuffer.append("</td>");
					
					sBuffer.append("<td width='25%' class='CONTROL' align='center'>");
					sBuffer.append(ws.getString(4));
					sBuffer.append("</td>");
					
					sBuffer.append("</tr>");
				}
				while(ws !=null && ws.size()>0 && ws.next());
			}
			
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' colspan='4'><center><img src='../../hisglobal/images/btn-ccl.png' onClick='cancelPage();' style='cursor: pointer;'/></center></td>\n");
			sBuffer.append("</td>");
			
			sBuffer.append("</table>");
			sBuffer.append("</div>");
			
						
		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("NewBornBabyTransHLP.getAdmittedBabyDtlModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
	
	public static String getPatientDetailModi_BS(NewBornBabyTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		NewBornBabyTransBO bo=new NewBornBabyTransBO();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		String strPhoneNo=vo.getStrPhoneNo();
		String strMobileNo=vo.getStrMobileNo();
		String strCity=vo.getStrCity();
		String strState=vo.getStrState().trim();
		String strStateName=vo.getStrStateName();
		//String strDistrict=vo.getStrDistrict();
		String strDistrictName=vo.getStrDistrictName();
		String strCountry="";
		String strDistrict=vo.getStrDistrict().trim();
		String strPinCode=vo.getStrPinCode();
		String strMotherTreatmentCateg="";
		String strGender="0";
		String strCurrentDate=vo.getStrCurrentDate();
		String strBabyName="B/O "+vo.getStrMotherName();
		String strTehsil="";
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		if(strHouseNo.equals(" "))
		{
			strHouseNo="";
		}
		if(strStreet.equals(" "))
		{
			strStreet="";
		}
		if(strPhoneNo.equals(" "))
		{
			strPhoneNo="";
		}
		if(strMobileNo.equals(" "))
		{
			strMobileNo="";
		}
		if(strCity.equals(" "))
		{
			strCity="";
		}
		if(strDistrict.equals(" "))
		{
			strDistrict="";
		}
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Transaction","PatientAdmissionDATA");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),
						vo.getStrTreatmentCategoryCode()+"^"+vo.getSetStrMotherCatgrp(),"0^Select Value", false);
			strGender=util.getOptionValue(vo.getGenderWS(),"","0^Select Value", false);
			//System.out.println("caste"+vo.getStrPatientCaste()+"ajay");
			strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste().replaceAll(" ", ""),"0^Select Value", false);

			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			
			strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
/*			if(vo.getStrCountryCode().equals("IND")){
					vo.setStrDistrict(vo.getStrDistrictCode());
			}*/
					
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Number Of Children</label>");
			sBuffer.append("</div>");
			sBuffer.append("<div class='col-sm-2'>");
			
			
			
			List<String> al=new ArrayList<String>();
			al.add("1");
			al.add("Single");
			al.add("2");
			al.add("Twin");
			al.add("3");
			al.add("Triplet");
			al.add("4");
			al.add("Quadruplet");
			al.add("5");
			al.add("Pentaplet");
			al.add("6");
			al.add("Hexaplet");
			al.add("7");
			al.add("Septaplet");
			al.add("8");
			al.add("Octaplet");
			al.add("9");
			al.add("Nonaplet");
			String option="";
			
			
			//Data available mother already given birth to multiple babies then disable the combo 
			//and set the number of babies
			if(!vo.getStrNumberOfChildrenBorn().equals("0"))
			
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' class='browser-default custom-select' disabled='disabled'>");
				option=util.getOptionValue(al, vo.getStrNumberOfChildrenBorn(), "");
				sBuffer.append(option+"</select>");
			}
			else// New Mother. No multiple babies. Set default to Single
			{
				sBuffer.append("<select name='strNumberOfChildrenBorn' class='browser-default custom-select'>");
				option=util.getOptionValue(al, "1", "");
				sBuffer.append(option+"</select>");
			}
			
			sBuffer.append("<input name='strNumberOfChildren' value='1' type='hidden'></div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Baby Name</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='hidden' name='strBabyName' value='" + strBabyName+ "'><label>"+strBabyName+"</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Gender<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<select name='strGender' class='browser-default custom-select'>'"+strGender+"'</select>");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Date Of Birth & Birth Time <font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<div id='birthDateId'>");
			sBuffer.append("<input type='text'  id='datetimepicker1' name='datetime' class='form-control' >");
			sBuffer.append("</div></div>");
			
			
			
			/*sBuffer.append("<div class='col-sm-1'>");
			sBuffer.append("<label>Birth Time<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-1'>");
			sBuffer.append("<input id='timepicker' class='form-control'/>");
			sBuffer.append("</div>");*/
			sBuffer.append("</div>");

			/*sBuffer.append("<font color='red'>*</font>Birth Time");
			sBuffer.append("<div id='birthTimeId'>");
			sBuffer.append("<input type='text' class='form-control' name='strBirthHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event),focus(this)' onblur='moveToNextBox(this,event)';><b>:</b>");
			sBuffer.append("<input type='text' class='form-control' name='strBirthMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);' onblur='moveToNextBox(this,event)'><b>:</b>\n");
			sBuffer.append("<select class='browser-default custom-select' name='strAmPm'><option value='1'>AM</option><option value='2'>PM</option>");
			sBuffer.append("</select>");
			sBuffer.append("</div>");
			sBuffer.append("<div id='birthDateOnlineId'></div><div id='birthTimeOnlineId'></div>");
			*/
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Treatment Category</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<select name='strMotherTreatmentCateg' class='browser-default custom-select' onChange='getChargeValue();'>"+strMotherTreatmentCateg+"</select>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Id Mark1</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' class='form-control' name='strIdMark1'  onkeypress='return validateData(event,4);'>");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Id Mark2</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' class='form-control' name='strIdMark2'  onkeypress='return validateData(event,4);' >");
			sBuffer.append("</div>");
			sBuffer.append("</div>");

			
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Father Name<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text'class='form-control' name='strFatherName' value='"+strFatherName+"' nowrap='nowrap'>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Patient Caste</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<select name='strPatientCaste' tabindex='1' class='browser-default custom-select'>\n");
			sBuffer.append(strPatientCaste);
			sBuffer.append("</select>");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Religion</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<select name='strReligion' class='browser-default custom-select'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select>\n");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Address House No.</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strHouseNo' value='"+strHouseNo+"' class='form-control' >");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Street/Mohallah</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strStreet' value='"+strStreet+ "' class='form-control'>\n");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>City/Village<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strCity' value='"+strCity+ "' class='form-control' onkeypress='return validateData(event,4);'>\n");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Country<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='hidden' name='strCountryName' value=''/><select name='strCountry" +"' class='browser-default custom-select'  onchange='onchangeCountry()'>\n");
			sBuffer.append(strCountry);
			sBuffer.append("</select>\n");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>State<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<div style='display:none;' id='stateTextBoxDiv'><input type='text' name='strStateName' class='form-control'></div><div id='stateComboDiv'><select name='strState" +"' class='browser-default custom-select' onchange='onchangeState()' id='stateComboDiv'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></div>\n");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>District</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<div style='display:none;' id='districtTextBoxDiv'><input type='text' name='strDistrictName'  class='form-control'  maxlength='30' onkeypress='return validateData(event,4);'></div><div id='districtSelectBoxDiv'><select name='strDistrict'"+
		            "' class='browser-default custom-select' onchange='onchangeDistrict();' id='districtComboDiv'>\n");
		    sBuffer.append(strDistrict);
		    sBuffer.append("</select></div>\n");
		    sBuffer.append("</div>");
			sBuffer.append("</div>");
			

			/*sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>LandMark</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strTehsil' value='"+ strTehsil +"' class='form-control'  tabindex='1' onkeypress='return validateData(event,4);'>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Street/Mohallah</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strStreet' value='"+strStreet+ "' class='form-control'>\n");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>City/Village<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strCity' value='"+strCity+ "' class='form-control' onkeypress='return validateData(event,4);'>\n");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
		
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mandal</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='30' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");*/
			
			
			sBuffer.append("<div class='row rowFlex reFlex'>");
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Pincode</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strPinCode' value='"+strPinCode+"' class='form-control'  onkeypress='return validateData(event,5);'>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Mobile No<font color='red'>*</font></label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strMobileNo' value='"+strMobileNo+"' class='form-control' onkeypress='return validateData(event,5);'>\n");
			sBuffer.append("</div>");

			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<label>Phone No</label>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-sm-2'>");
			sBuffer.append("<input type='text' name='strPhoneNo' value='"+strPhoneNo+"' class='form-control' maxlength='13' onkeypress='return validateData(event,5);'>\n");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
		
			/*sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mandal</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='30' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
		*/
			sBuffer.append("<script>onchangeCountry();</script>");
			
			
			
			/*if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
					{
						sBuffer.append("<tr style='display:none;'>\n");
						sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:</td>\n");				
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("</tr>\n");
					}
					else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
							   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
					{
								sBuffer.append("<tr>\n");
								sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:</td>\n");				
								sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
								sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pincode:</div></td>\n");
								sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></div></td>\n");
								sBuffer.append("</tr>\n");
					}
					else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
							   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
					{
								sBuffer.append("<tr>\n");
								sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
								sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
								sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'><font color='red'>*</font>Mobile No:</div></td>\n");				
								sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></div></td>\n");
								sBuffer.append("</tr>\n");
					}
					else
					{
						sBuffer.append("<tr>\n");
						sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+strPinCode+"' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:</td>\n");				
						sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+strMobileNo+"' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
						sBuffer.append("</tr>\n");
					}
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPhoneNo' value='"
			+strPhoneNo+"' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");*/
			/*sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL' colspan='1'>Address1</td>");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='3'><input type='text' name='strAddress' value='"
							+ strAddress + "' class='txtFldBig'></td>");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL' colspan='1'>Address2</td>");
			sBuffer.append("<td class='CONTROL' colspan='3'><input type='text' name='strAddress2' value='"
							+"" + "' class='txtFldBig'></td>");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>City</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strCity' value='"
					+ strCity + "' class='txtFldNormal'>");
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL'>State</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strState" +
					"' class='comboNormal'>");
			sBuffer.append(strState);
			sBuffer.append("</select> </td>");*/
			sBuffer.append("</tr></table>\n");
			
		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("NewBornBabyTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
}
