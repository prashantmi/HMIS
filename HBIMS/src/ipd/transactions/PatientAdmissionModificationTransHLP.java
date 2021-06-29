package ipd.transactions;



import ipd.IpdConfigUtil;

import java.util.ResourceBundle;

import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class  PatientAdmissionModificationTransHLP {
/**
 * This function used to develop grid on bed details pop up window that contains bed details with their respective status
 * @param vo
 * @return
 */
	public static String getBedDetails(PatientAdmissionModificationTransVO vo)
	{
		StringBuffer sb = new StringBuffer("");
		WebRowSet ws=vo.getBedDetailWS();
		//String 
		int size=4;
		int count=1;
		try
		{
			sb.append("<br><table width='75%' align='center' border='5' bordercolor='#9DAFC6' cellspacing='1px'>");
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
			vo.setStrMsgString("PatientAdmissionTransHLP.getBedDetails() --> "
					+ e.getMessage());
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
	public static String getPatientDetailModi(PatientAdmissionModificationTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		StringBuffer sBufferdDeptUnit = new StringBuffer("");
		StringBuffer sBufferEmgAddress=new StringBuffer("");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospCode());
		PatientAdmissionModificationTransBO bo=new PatientAdmissionModificationTransBO();
		bo.setPatientAddModi(vo);
		String strAdmCharge=vo.getStrAdmissionChargeValue();
		String strFatherName=vo.getStrFatherName().trim();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strIdMark=vo.getStrIdMark()==null?"":vo.getStrIdMark().trim();
		String strStreet=vo.getStrStreet().trim();
		String stateName=vo.getStrStateName();
		String districtName=vo.getStrDistrict();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strMobileNo=vo.getStrMobileNo().trim();
		String strCity=vo.getStrCity().trim();
		String strTehsil="";
		String cityLocation="";
		if(vo.getStrCityLocation()!=null)
			cityLocation=vo.getStrCityLocation();
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		String strMaritalStatus="";
		String relationDtls="";
		String relationDtls1="";
		String strState=vo.getStrState().trim();
		String strDistrict=vo.getStrDistrict()==null?"":vo.getStrDistrict().trim();
		String strCountry="";
		String strPinCode=vo.getStrPinCode().trim();
		//	String strPatientName=vo.getStrPatientName().trim();
		String  strSpouseName=vo.getStrSpouseName().trim();
		String strMotherTreatmentCateg="",strAdmissionTypeValues="",strReliefFundValues="";
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		//System.out.println("caste is "+vo.getStrPatientCaste()+" "+vo.getStrSecondPersonRelationCode());
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Modification Transaction","PatientAdmissionDATA");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste().trim(),"0^Select Value", false);
			strMaritalStatus=util.getOptionValue(vo.getWrsMaritalStatus(),vo.getStrMaritalStatus(),"0^Select Value", false);
			
			if(!vo.getStrFirstPersonRelationCode().equals(null) || !vo.getStrFirstPersonRelationCode().equals(""))
			relationDtls=util.getOptionValue(vo.getStrRelationWs(),vo.getStrFirstPersonRelationCode(),"0^Select Value", false);
			else
			relationDtls=util.getOptionValue(vo.getStrRelationWs(),"","0^Select Value", false);
			
			PatientAdmissionModificationTransDAO.setRelation(vo);
			if(!vo.getStrSecondPersonName().equals(null))
			relationDtls1=util.getOptionValue(vo.getStrRelationWs(),vo.getStrSecondPersonRelationCode(),"0^Select Value", false);
			else
			relationDtls1=util.getOptionValue(vo.getStrRelationWs(),"","0^Select Value", false);
			
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),"0^Select Value", false);
			if(vo.getWrsAdmissionTypeValues()!=null && vo.getWrsAdmissionTypeValues().size()>0){
				strAdmissionTypeValues=util.getOptionValue(vo.getWrsAdmissionTypeValues(), vo.getStrAdmissionType(),"0^Select Value", false);
			}else{
				strAdmissionTypeValues="<option value='0'>Select Value</option>";
			}
			if(vo.getWrsReliefFundValues()!=null && vo.getWrsReliefFundValues().size()>0){
				strReliefFundValues=util.getOptionValue(vo.getWrsReliefFundValues(), vo.getStrReliefFund(), "0^Select Value", false);
			}else{
				strReliefFundValues="<option value='0'>Select Value</option>";
			}
			sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			/*sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red' size='1px'>*</font>Father Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strFatherName' value='"+strFatherName+  "' class='txtFldMax' nowrap='nowrap' readonly='readonly' onkeypress='return validateData(event,4);' maxlength='30'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Spouse Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strSpouseName' value='"+strSpouseName+  "' class='txtFldMax' nowrap='nowrap' readonly='readonly' onkeypress='return validateData(event,4);' maxlength='60'></td>\n");
			sBuffer.append("</tr>");*/
			/*sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMotherTreatmentCateg' class='comboNormal'>");
			sBuffer.append(strMotherTreatmentCateg);
			sBuffer.append("</select></td>");*/
			/*sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' value='"+ strHouseNo + "' class='txtFldNormal' maxlength='15'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Street/Mohallah</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strStreet' value='"+ strStreet + "' class='txtFldMax' maxlength='30'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>City</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strCity' value='"+ strCity + "' class='txtFldMax' maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Tehsil</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='30' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");	
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"+ strDistrict + "' class='txtFldMax'  maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strStateName' value=''/><select name='strState' class='comboNormal' onchange='onchangeState()'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry' class='comboNormal' onchange='onchangeCountry()'>\n");
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Phone No.:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='1'><input type='text' name='strPhoneNo' value='"
			+ strPhoneNo +"' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion' class='comboNormal'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Patient Caste</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strPatientCaste' tabindex='1' class='comboNormal'>\n");
			sBuffer.append(strPatientCaste);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Marital Status</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMaritalStatus' tabindex='1' class='comboNormal'>\n");
			sBuffer.append(strMaritalStatus);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("<td width='25%' class='LABEL'></td>");
			sBuffer.append("<td width='25%' class='CONTROL'></td>\n");
			sBuffer.append("</tr>\n");*/
			String readOnly="";
			if(vo.getStrPatIsUnknown().equals("1"))
				readOnly="readonly";
			
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' tabindex='0' value='"+ strHouseNo + "' class='txtFldNormal' maxlength='20' "+readOnly+"></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Street/Mohallah</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strStreet' value='"+ strStreet + "' class='txtFldMax' maxlength='60' "+readOnly+"></td>\n");
			sBuffer.append("</tr>\n");
			
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Location</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='0'  name='strCityLocation' value='"+ cityLocation + "' class='txtFldMax' maxlength='60 onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>City/Village</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text'   name='strCity' value='"+ strCity + "' class='txtFldMax' maxlength='60' tabindex='1' onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
			sBuffer.append("</tr>\n");
			
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Landmark</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' tabindex='0'value='"+ strTehsil + "' class='txtFldMax' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			//sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"+ strDistrict + "' class='txtFldMax'  maxlength='30'  onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='districtTextBoxDiv'><input type='text' id='strDistrictId' name='strDistrictName' value='"+districtName+"'></div><div id='districtSelectBoxDiv'><select name='strDistrict'  class='comboNormal' onclick='onchangeDistrict();' >\n");
			sBuffer.append(strDistrict);
			sBuffer.append("</select></div></td>\n");
			sBuffer.append("</tr>");
			
			sBuffer.append("<tr>");
			sBuffer.append("<td class='LABEL'  ><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='stateTextBoxDiv'><input type='text'  tabindex='1' id='strStateId' name='strStateName' value='"+stateName+"'></div><div id='stateComboDiv'><select name='strState' class='comboNormal'  onchange='onchangeState()' tabindex='1'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></div></td>\n");
			sBuffer.append("<td class='LABEL' ><font color='red'>*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry' class='comboNormal' tabindex='1'  onchange='onchangeCountry()'>\n");					
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>");
			sBuffer.append("</tr>\n");
			
			sBuffer.append("<script>onchangeCountry();</script>");
			
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strPhoneNo' value='"+ strPhoneNo + "' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+"></td>");
			sBuffer.append("<td width='25%' class='LABEL'>Marital Status</td>");
			sBuffer.append("<td width='25%' class='CONTROL'  ><select name='strMaritalStatus'  class='comboNormal'>\n");
			sBuffer.append(strMaritalStatus);
			sBuffer.append("</select> </td>");
			sBuffer.append("</tr>");
			
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion'  class='comboNormal'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>");
			sBuffer.append("<td width='25%' class='LABEL'>Patient Caste</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strPatientCaste'  class='comboNormal'>\n");
			sBuffer.append(strPatientCaste);
			sBuffer.append("</select> </td>");
			
			sBuffer.append("</tr>\n");
			
			if(vo.getStrAdmissionChargeAtCounter().equals("1"))
			{
				sBuffer.append("</tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Admission Charge</td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("Charges Not Defined For Patient category");
				else
					sBuffer.append(strAdmCharge+" <img src='/HBIMS/hisglobal/images/INR.png'>");	
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='LABEL'></td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				sBuffer.append("</td>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='0'/>");
				else
					sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='"+strAdmCharge+"'/>");	
				
				sBuffer.append("<tr>");
			}

			
			sBuffer.append("<script>onchangeCountry();</script>");
			if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
			   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
			{
				sBuffer.append("<tr style='display:none;'>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strMobileNo' value='"
				+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
			{
				sBuffer.append("<tr>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'  ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></div></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' tabindex='1'name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
			{
				sBuffer.append("<tr>\n");			
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pin Code:</div></td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
				
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else
			{
				sBuffer.append("<tr>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1'name='strMobileNo' value='"
				+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
		
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
			sBuffer.append("</table>\n");
			
			
			
			if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strDeptCode' tabindex='1'class='comboNormal'  onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Unit</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'>");
				sBufferdDeptUnit.append("<select name='strDeptUnitCode' tabindex='1'class='comboNormal'  onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrUnitValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select  tabindex='1'name='strTreatmentCategoryCode' class='comboNormal' disabled>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");			
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL'  ><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'  ><font color='red'>*</font>Room</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'>");
				sBufferdDeptUnit.append("<select name='strRoomCode' tabindex='1' class='comboNormal' >");
				sBufferdDeptUnit.append(vo.getStrRoomValue());
				sBufferdDeptUnit.append("</select></div></td></tr>");
				
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
				//sBufferdDeptUnit.append(strBedValues);
				sBufferdDeptUnit.append("</select></td>\n");
				
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
				
				sBufferdDeptUnit.append("<tr>");
				
				/*////Relief Fund Values
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				//identification textbox
				//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				//sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' value='"+strIdMark+"' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");
			}
			else if((ipdConfigUtil.getStrUnitNameReq().equals("1"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'><input type='hidden' name='strRoomCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Unit</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'>");
				sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal'  tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrUnitValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' tabindex='1' disabled>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL' tabindex='1'><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("</td></tr>");
				
				sBufferdDeptUnit.append("<tr>");
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				sBufferdDeptUnit.append("<tr>");
				////Relief Fund Values
			/*	sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				/*sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				sBufferdDeptUnit.append("</tr>");*/
				sBufferdDeptUnit.append("</table>\n");
			}
			
			else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("1")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'><input type='hidden' name='strDeptUnitCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><selecttabindex='1' name='strTreatmentCategoryCode' class='comboNormal' disabled>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1' width='25%' class='LABEL' '><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'  ><font color='red'>*</font>Room</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'>");
				sBufferdDeptUnit.append("<select name='strRoomCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrRoomValue());
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("</td></tr>");
				////bed
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
				//sBufferdDeptUnit.append(strBedValues);
				sBufferdDeptUnit.append("</select></td>\n");
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
				
				//sBufferdDeptUnit.append("<tr>");
				////Relief Fund Values
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				//sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				//sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");
				
			}
			else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'><input type='hidden'tabindex='1' name='strDeptUnitCode' value='0'/></div>");
				sBufferdDeptUnit.append("<div id='RoomId'><input type='hidden' name='strRoomCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' tabindex='1' class='comboNormal' disabled>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL' ><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</td></tr>");
				sBufferdDeptUnit.append("<tr>");
				////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				////Relief Fund Values
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
			/*	sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				sBufferdDeptUnit.append("</tr>");*/
				sBufferdDeptUnit.append("</table>\n");
				
				
				sBufferEmgAddress.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'>I Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstPersonName'  tabindex='1' value='"+vo.getStrFirstPersonName()+"' maxlength='90' onkeypress='return validateData(event,4);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL' ><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strFirstPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' tabindex='1' name='strEmgAddress1' maxlength='300' >");
				sBufferEmgAddress.append(vo.getStrEmgAddress1());
				sBufferEmgAddress.append("</textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text'  name='strFirstpersonphone' value='"+vo.getStrFirstpersonphone()+"' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='20%' class='LABEL'>II Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondPersonName' value='"+vo.getStrSecondPersonName()+"' maxlength='90' onkeypress='return validateData(event,4);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL' ><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strSecondPersonRelationCode' tabindex='1' class='comboNormal'  >");
				sBufferEmgAddress.append(relationDtls1);
				sBufferEmgAddress.append("</select></td>\n");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' tabindex='1' name='strEmgAddress2' maxlength='300' >");
				sBufferEmgAddress.append(vo.getStrEmgAddress2());
				sBufferEmgAddress.append("</textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text'tabindex='1' value='"+vo.getStrSecondpersonphone()+"'  name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='25%'  class='LABEL'>Remarks</td> ");
				sBufferEmgAddress.append("<td width='75%' colspan='6' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea rows='1' cols='16' name='strRemarks'></textarea></td>");
			    sBufferEmgAddress.append("</tr>");
				sBufferEmgAddress.append("</table>");
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString()+"^"+sBufferdDeptUnit.toString()+"^"+sBufferEmgAddress.toString();
	}
	/**
	 * This function is used to generate html(New Modification for address) at runtime.
	 * @param vo
	 * @return
	 */
	public static String getPatientDetailModi(SlipReprintTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		SlipReprintTransBO bo=new SlipReprintTransBO();
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName().trim();
		String strReligion="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strStreet=vo.getStrStreet().trim();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strMobileNo=vo.getStrMobileNo().trim();
		String strCity=vo.getStrCity().trim();
		String strState=vo.getStrState().trim();
		String strDistrict=vo.getStrDistrict()==null?"":vo.getStrDistrict().trim();
		String strCountry="";
		String strPinCode=vo.getStrPinCode().trim();
	//	String strPatientName=vo.getStrPatientName().trim();
		String  strSpouseName=vo.getStrSpouseName().trim();
		String strMotherTreatmentCateg="";
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		
		HisUtil util = null;
		try
		{
					util = new HisUtil("Patient Admission Modification Transaction",
			"PatientAdmissionDATA");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),
					"0^Select Value", false);
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),
					"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),
					"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),
					"0^Select Value", false);
			sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL' ><font color='red' size='1px'>*</font>Father Name</td>\n");
			sBuffer.append("<td width='25%' tabindex='1' class='CONTROL'><input type='text' name='strFatherName' value='"
							+strFatherName+  "' class='txtFldMax' readonly nowrap='nowrap' onkeypress='return validateData(event,4);' maxlength='30'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Spouse Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strSpouseName' readonly value='"
					+strSpouseName+  "' class='txtFldMax' nowrap='nowrap' onkeypress='return validateData(event,4);' maxlength='60'></td>\n");
		
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMotherTreatmentCateg' tabindex='1' class='comboNormal' disabled>");
			sBuffer.append(strMotherTreatmentCateg);
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion' tabindex='1' class='comboNormal' disabled>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' readonly value='"
							+ strHouseNo + "' class='txtFldNormal' maxlength='60'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Street</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strStreet' readonly value='"
						+ strStreet + "' class='txtFldMax' maxlength='60'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>City</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strCity' readonly value='"
							+ strCity + "' class='txtFldMax' maxlength='60' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' readonly value='"
						+ strDistrict + "' class='txtFldMax'  maxlength='60' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strStateName' disabled value=''/><select name='strState" +
			"' class='comboNormal' onchange='onchangeState()'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></td>\n");
			sBuffer.append("<td class='LABEL' ><font color='red' >*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden'tabindex='1' name='strCountryName' disabled value=''/><select name='strCountry" +
			"' class='comboNormal' onchange='onchangeCountry()'>\n");
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<script>onchangeState();onchangeCountry();</script>");
			sBuffer.append("<tr>\n");			
			sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' readonly value='"
			+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
			//sBuffer.append("<td width='25%' class='LABEL'></td>\n");
			//sBuffer.append("</tr>\n");
			//sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mobile No.:</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' readonly value='"
			+ strMobileNo + "' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No.:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='3'><input type='text' name='strPhoneNo' readonly value='"
			+ strPhoneNo +"' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);'></td>\n");
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
			//e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
	public static String getPatientDetailModi_Bootstrap(PatientAdmissionModificationTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		StringBuffer sBufferdDeptUnit = new StringBuffer("");
		StringBuffer sBufferEmgAddress=new StringBuffer("");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospCode());
		PatientAdmissionModificationTransBO bo=new PatientAdmissionModificationTransBO();
		bo.setPatientAddModi(vo);
		String strAdmCharge=vo.getStrAdmissionChargeValue();
		String strFatherName=vo.getStrFatherName().trim();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strIdMark=vo.getStrIdMark()==null?"":vo.getStrIdMark().trim();
		String strStreet=vo.getStrStreet().trim();
		String stateName=vo.getStrStateName();
		String districtName=vo.getStrDistrict();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strMobileNo=vo.getStrMobileNo().trim();
		String strCity=vo.getStrCity().trim();
		String strTehsil="";
		String cityLocation="";
		if(vo.getStrCityLocation()!=null)
			cityLocation=vo.getStrCityLocation();
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		String strMaritalStatus="";
		String relationDtls="";
		String relationDtls1="";
		String strState=vo.getStrState().trim();
		String strDistrict=vo.getStrDistrict()==null?"":vo.getStrDistrict().trim();
		String strCountry="";
		String strPinCode=vo.getStrPinCode().trim();
		//	String strPatientName=vo.getStrPatientName().trim();
		String  strSpouseName=vo.getStrSpouseName().trim();
		String strMotherTreatmentCateg="",strAdmissionTypeValues="",strReliefFundValues="";
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		//System.out.println("caste is "+vo.getStrPatientCaste()+" "+vo.getStrSecondPersonRelationCode());
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Modification Transaction","PatientAdmissionDATA");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste().trim(),"0^Select Value", false);
			strMaritalStatus=util.getOptionValue(vo.getWrsMaritalStatus(),vo.getStrMaritalStatus(),"0^Select Value", false);
			
			if(!vo.getStrFirstPersonRelationCode().equals(null) || !vo.getStrFirstPersonRelationCode().equals(""))
			relationDtls=util.getOptionValue(vo.getStrRelationWs(),vo.getStrFirstPersonRelationCode(),"0^Select Value", false);
			else
			relationDtls=util.getOptionValue(vo.getStrRelationWs(),"","0^Select Value", false);
			
			PatientAdmissionModificationTransDAO.setRelation(vo);
			if(!vo.getStrSecondPersonName().equals(null))
			relationDtls1=util.getOptionValue(vo.getStrRelationWs(),vo.getStrSecondPersonRelationCode(),"0^Select Value", false);
			else
			relationDtls1=util.getOptionValue(vo.getStrRelationWs(),"","0^Select Value", false);
			
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),"0^Select Value", false);
			if(vo.getWrsAdmissionTypeValues()!=null && vo.getWrsAdmissionTypeValues().size()>0){
				strAdmissionTypeValues=util.getOptionValue(vo.getWrsAdmissionTypeValues(), vo.getStrAdmissionType(),"0^Select Value", false);
			}else{
				strAdmissionTypeValues="<option value='0'>Select Value</option>";
			}
			if(vo.getWrsReliefFundValues()!=null && vo.getWrsReliefFundValues().size()>0){
				strReliefFundValues=util.getOptionValue(vo.getWrsReliefFundValues(), vo.getStrReliefFund(), "0^Select Value", false);
			}else{
				strReliefFundValues="<option value='0'>Select Value</option>";
			}
			//sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			/*sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red' size='1px'>*</font>Father Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strFatherName' value='"+strFatherName+  "' class='txtFldMax' nowrap='nowrap' readonly='readonly' onkeypress='return validateData(event,4);' maxlength='30'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Spouse Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strSpouseName' value='"+strSpouseName+  "' class='txtFldMax' nowrap='nowrap' readonly='readonly' onkeypress='return validateData(event,4);' maxlength='60'></td>\n");
			sBuffer.append("</tr>");*/
			/*sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMotherTreatmentCateg' class='comboNormal'>");
			sBuffer.append(strMotherTreatmentCateg);
			sBuffer.append("</select></td>");*/
			/*sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' value='"+ strHouseNo + "' class='txtFldNormal' maxlength='15'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Street/Mohallah</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strStreet' value='"+ strStreet + "' class='txtFldMax' maxlength='30'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>City</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strCity' value='"+ strCity + "' class='txtFldMax' maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Tehsil</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='30' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");	
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"+ strDistrict + "' class='txtFldMax'  maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strStateName' value=''/><select name='strState' class='comboNormal' onchange='onchangeState()'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry' class='comboNormal' onchange='onchangeCountry()'>\n");
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Phone No.:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL' colspan='1'><input type='text' name='strPhoneNo' value='"
			+ strPhoneNo +"' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion' class='comboNormal'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Patient Caste</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strPatientCaste' tabindex='1' class='comboNormal'>\n");
			sBuffer.append(strPatientCaste);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Marital Status</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMaritalStatus' tabindex='1' class='comboNormal'>\n");
			sBuffer.append(strMaritalStatus);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("<td width='25%' class='LABEL'></td>");
			sBuffer.append("<td width='25%' class='CONTROL'></td>\n");
			sBuffer.append("</tr>\n");*/
			String readOnly="";
			if(vo.getStrPatIsUnknown().equals("1"))
				readOnly="readonly";
			
			/*sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' tabindex='0' value='"+ strHouseNo + "' class='txtFldNormal' maxlength='20' "+readOnly+"></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Street/Mohallah</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strStreet' value='"+ strStreet + "' class='txtFldMax' maxlength='60' "+readOnly+"></td>\n");
			sBuffer.append("</tr>\n");
			
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Location</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='0'  name='strCityLocation' value='"+ cityLocation + "' class='txtFldMax' maxlength='60 onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>City/Village</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text'   name='strCity' value='"+ strCity + "' class='txtFldMax' maxlength='60' tabindex='1' onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
			sBuffer.append("</tr>\n");
			
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Landmark</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' tabindex='0'value='"+ strTehsil + "' class='txtFldMax' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			//sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"+ strDistrict + "' class='txtFldMax'  maxlength='30'  onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='districtTextBoxDiv'><input type='text' id='strDistrictId' name='strDistrictName' value='"+districtName+"'></div><div id='districtSelectBoxDiv'><select name='strDistrict'  class='comboNormal' onclick='onchangeDistrict();' >\n");
			sBuffer.append(strDistrict);
			sBuffer.append("</select></div></td>\n");
			sBuffer.append("</tr>");
			
			sBuffer.append("<tr>");
			sBuffer.append("<td class='LABEL'  ><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='stateTextBoxDiv'><input type='text'  tabindex='1' id='strStateId' name='strStateName' value='"+stateName+"'></div><div id='stateComboDiv'><select name='strState' class='comboNormal'  onchange='onchangeState()'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></div></td>\n");
			sBuffer.append("<td class='LABEL' ><font color='red'>*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry' class='comboNormal' tabindex='1'  onchange='onchangeCountry()'>\n");					
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>");
			sBuffer.append("</tr>\n");
			
			sBuffer.append("<script>onchangeCountry();</script>");
			
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strPhoneNo' value='"+ strPhoneNo + "' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+"></td>");
			sBuffer.append("<td width='25%' class='LABEL'>Marital Status</td>");
			sBuffer.append("<td width='25%' class='CONTROL'  ><select name='strMaritalStatus'  class='comboNormal'>\n");
			sBuffer.append(strMaritalStatus);
			sBuffer.append("</select> </td>");
			sBuffer.append("</tr>");
			
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion'  class='comboNormal'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>");
			sBuffer.append("<td width='25%' class='LABEL'>Patient Caste</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strPatientCaste'  class='comboNormal'>\n");
			sBuffer.append(strPatientCaste);
			sBuffer.append("</select> </td>");
			
			sBuffer.append("</tr>\n");
			*/
			
			/*
			
			<div class="col-md-12 lessPadding" id="tenderDtls">
			<fieldset class="col-md-12">
				<legend>Tender Detail(s) </legend>

				<div class="form-group col-md-3 col-sm-3">
					<font class="mandatory">*</font>
					<label for="">Contract From</label>
					

					<input id="hstdtContractFrmdate" name="hstdtContractFrmdate" class="form-control  hasDatepicker" placeholder="dd-Mmm-yyyy" type="text" value="23-Jul-2019" readonly="readonly">


				</div>

				<div class="form-group col-md-3 col-sm-3">
					<font class="mandatory">*</font>
					<label for="">Contract To</label>
					
					<input id="hstdtContractTodate" name="hstdtContractTodate" class="form-control  hasDatepicker" placeholder="dd-Mmm-yyyy" type="text" value="24-Jul-2019" readonly="readonly">


				</div>
				<div class="form-group col-md-3 col-sm-6 col-xs-12">

					<label for="hstnumContractQty">
							Contracted Qty</label>

					<input id="hstnumContractQty" name="hstnumContractQty" class="form-control " onkeypress="return validateData(event,5)" placeholder="Enter Contract Qty" type="text" value="" maxlength="16" data-bv-field="hstnumContractQty">

				<small style="display: none;" data-bv-validator="stringLength" data-bv-validator-for="hstnumContractQty" class="help-block">This value is not valid</small></div>

				<div class="form-group col-md-3  col-sm-6 col-xs-12">

					<label for="hstnumShelfLife">
						<font class="mandatory">*</font>Shelf Life in month(s)</label>

					<input id="hstnumShelfLife" name="hstnumShelfLife" class="form-control " onkeypress="return validateData(event,5)" placeholder="Enter Shelf Life" type="text" value="" maxlength="4" data-bv-field="hstnumShelfLife">

				<small style="display: none;" data-bv-validator="notEmpty" data-bv-validator-for="hstnumShelfLife" class="help-block">Please enter Shelf Life</small><small style="display: none;" data-bv-validator="between" data-bv-validator-for="hstnumShelfLife" class="help-block">Please enter a valid value</small><small style="display: none;" data-bv-validator="stringLength" data-bv-validator-for="hstnumShelfLife" class="help-block">This value is not valid</small></div>

		</div>
		
		*/
			sBuffer.append("<div class='' style=''>\n");
            sBuffer.append("<div class='row justify-content-center'>\n");
            sBuffer.append("<div class='col-md-12 '>\n");
            sBuffer.append("<div class='card'>\n");
            sBuffer.append("<article class='card-body'>\n");
			 sBuffer.append("<div class='row'>");
			 sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label>House No.</label>");
			 sBuffer.append("<input type='text' class='form-control' name='strHouseNo' value='"+ strHouseNo +"' tabindex='2' maxlength='20' "+readOnly+">");
             sBuffer.append("</div>");
			 sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label><font color='red'>*</font>Street/Mohallah</label>");
			 sBuffer.append("<input type='text' class='form-control' name='strStreet' value='"+ strStreet +"' tabindex='1' maxlength='60'  "+readOnly+">");
             sBuffer.append("</div>");
             sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label>Location</label>");
			 sBuffer.append("<input type='text' class='form-control' name='strCityLocation' value='"+ cityLocation +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             

			 sBuffer.append("<div class='row'>");
			 sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label><font color='red'>*</font>City/Village</label>");
			 sBuffer.append("<input type='text' class='form-control' name='strCity' value='"+ strCity +"' tabindex='1' maxlength='60' onkeypress='return validateData(event,4);' "+readOnly+">");
             sBuffer.append("</div>");
             sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label>Landmark</label>");
			 sBuffer.append("<input type='text' class='form-control' name='strTehsil' value='"+ strTehsil +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
             sBuffer.append("</div>");
             sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label>District</label>");
			 sBuffer.append("<div style='display:none;' id='districtTextBoxDiv'><input type='text' class='form-control' name='strDistrictName' value='"+districtName+"' / ></div>");
			 sBuffer.append("<div id='districtSelectBoxDiv'>");
			 sBuffer.append("<select name='strDistrictCode' id='strDistrictCode' class='browser-default custom-select' onclick='onchangeDistrict();' tabindex='2'>");
			 sBuffer.append(strDistrict);
			 sBuffer.append("</select>");                
			 sBuffer.append("</div>");
			 sBuffer.append("</div>");
             sBuffer.append("</div>");
			
             sBuffer.append("<div class='row'>");
			 sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label><font color='red'>*</font>State</label>");
			 sBuffer.append("<div style='display:none;' id='stateTextBoxDiv'><input type='text' class='form-control' name='strStateName' id='strStateId' value='"+stateName+"'/></div>");
			 sBuffer.append("<div id='stateComboDiv'><select name='strState' class='browser-default custom-select'  onchange='onchangeState()' tabindex='1'>");
			 sBuffer.append(strState);
			 sBuffer.append("</select></div>");  
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label><font color='red'>*</font>Country</label>");
			 sBuffer.append("<input type='hidden' name='strCountryName' value=''/>");
			 sBuffer.append("<select name='strCountry' tabindex='1' class='browser-default custom-select'onchange='onchangeCountry()'>");
			 sBuffer.append(strCountry);
			 sBuffer.append("</select>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='col-sm-4'>");
			 sBuffer.append("<label>Pin Code</label>");
			 sBuffer.append("<input class='form-control' type='text' name='strPinCode' value='"+ strPinCode + "' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+">");
			 sBuffer.append("</div>");
             sBuffer.append("</div>");
             
                 sBuffer.append("<div class='row'>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label>Phone No.</label><i class='fas fa-phone fa-flip-horizontal'></i>");
				 sBuffer.append("<input type='text' class='form-control' tabindex='2' name='strPhoneNo' value='"+ strPhoneNo +"' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+">");               
				 sBuffer.append("</div>");
                 sBuffer.append("<div class='col-sm-4' >");
                 sBuffer.append("<label id='ind'><font color='red'>*</font>Mobile No.</label><i class='fas fa-mobile-alt'></i>");
				 sBuffer.append("<input type='text' class='form-control' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+">");
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("</div>");
                 sBuffer.append("</div>");
                 
                 sBuffer.append("<script>onchangeCountry();</script>");
                 
                  sBuffer.append("<div class='row'>");
					 sBuffer.append("<div class='col-sm-4'>");
					 sBuffer.append("<label>Marital Status</label>");
					 sBuffer.append("<select class='browser-default custom-select' name='strMaritalStatus'  class='comboNormal'>");
					 sBuffer.append(strMaritalStatus);
					 sBuffer.append("</select>");   
					 sBuffer.append("</div>");
					 sBuffer.append("<div class='col-sm-4'>");
					 sBuffer.append("<label>Religion</label>");
					 sBuffer.append("<select class='browser-default custom-select' name='strReligion'>");
					 sBuffer.append(strReligion);
					 sBuffer.append("</select>");  
					 sBuffer.append("</div>");
					 sBuffer.append("<div class='col-sm-4'>");
					 sBuffer.append("<label>Patient Caste</label>");
					 sBuffer.append("<select class='browser-default custom-select' name='strPatientCaste'>");
					 sBuffer.append(strPatientCaste);
					 sBuffer.append("</select>");                
					 sBuffer.append("</div>");	 
					 sBuffer.append("</div>");

            
             
             
            
           
			if(vo.getStrAdmissionChargeAtCounter().equals("1"))
			{
				sBuffer.append("</tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Admission Charge</td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("Charges Not Defined For Patient category");
				else
					sBuffer.append(strAdmCharge+" <img src='/HBIMS/hisglobal/images/INR.png'>");	
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='LABEL'></td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				sBuffer.append("</td>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='0'/>");
				else
					sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='"+strAdmCharge+"'/>");	
				
				sBuffer.append("<tr>");
			}

			
			sBuffer.append("<script>onchangeCountry();</script>");
			if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
			   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
			{
				sBuffer.append("<tr style='display:none;'>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strMobileNo' value='"
				+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
			{
				sBuffer.append("<tr>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'  ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></div></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' tabindex='1'name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
			{
				sBuffer.append("<tr>\n");			
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pin Code:</div></td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
				
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else
			{
				/*sBuffer.append("<tr>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1'name='strMobileNo' value='"
				+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");*/
			}
		
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
			//sBuffer.append("</table>\n");
			
			sBuffer.append("</article>\n");
			 sBuffer.append("</div>\n");
           sBuffer.append("</div>\n");
           sBuffer.append("</div>\n");
           sBuffer.append("</div>\n");
			
			if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strDeptCode' tabindex='1'class='comboNormal'  onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Unit</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'>");
				sBufferdDeptUnit.append("<select name='strDeptUnitCode' tabindex='1'class='comboNormal'  onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrUnitValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select  tabindex='1'name='strTreatmentCategoryCode' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='form-control'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");			
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL'  ><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='form-control' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'  ><font color='red'>*</font>Room</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'>");
				sBufferdDeptUnit.append("<select name='strRoomCode' tabindex='1' class='comboNormal' >");
				sBufferdDeptUnit.append(vo.getStrRoomValue());
				sBufferdDeptUnit.append("</select></div></td></tr>");
				
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
				//sBufferdDeptUnit.append(strBedValues);
				sBufferdDeptUnit.append("</select></td>\n");
				
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
				
				sBufferdDeptUnit.append("<tr>");
				
				/*////Relief Fund Values
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				//identification textbox
				//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				//sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' value='"+strIdMark+"' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");
			}
			else if((ipdConfigUtil.getStrUnitNameReq().equals("1"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'><input type='hidden' name='strRoomCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Unit</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'>");
				sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal'  tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrUnitValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL' tabindex='1'><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("</td></tr>");
				
				sBufferdDeptUnit.append("<tr>");
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				sBufferdDeptUnit.append("<tr>");
				////Relief Fund Values
			/*	sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				/*sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				sBufferdDeptUnit.append("</tr>");*/
				sBufferdDeptUnit.append("</table>\n");
			}
			
			else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("1")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'><input type='hidden' name='strDeptUnitCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><selecttabindex='1' name='strTreatmentCategoryCode' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1' width='25%' class='LABEL' '><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'  ><font color='red'>*</font>Room</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'>");
				sBufferdDeptUnit.append("<select name='strRoomCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrRoomValue());
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("</td></tr>");
				////bed
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
				//sBufferdDeptUnit.append(strBedValues);
				sBufferdDeptUnit.append("</select></td>\n");
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
				
				//sBufferdDeptUnit.append("<tr>");
				////Relief Fund Values
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				//sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				//sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");
				
			}
			else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
			{
				/*sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'><input type='hidden'tabindex='1' name='strDeptUnitCode' value='0'/></div>");
				sBufferdDeptUnit.append("<div id='RoomId'><input type='hidden' name='strRoomCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' tabindex='1' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL' ><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</td></tr>");
				sBufferdDeptUnit.append("<tr>");
				////admission type
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");
				////Relief Fund Values
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");*/
				
                 sBufferdDeptUnit.append("<div class='row'>");
				 sBufferdDeptUnit.append("<div class='col-sm-4'>");
				 sBufferdDeptUnit.append("<label><font color='red'>*</font>Department</label>");
				 sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
				 sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='0' >");
				 sBufferdDeptUnit.append("<select class='browser-default custom-select' name='strDeptCode'  tabindex='1' onChange='getCombo(this);'>");
				 sBufferdDeptUnit.append(vo.getStrDeptValue());
				 sBufferdDeptUnit.append("</select>");
                 sBufferdDeptUnit.append("</div>");
				 sBufferdDeptUnit.append("<div class='col-sm-4'>");
				 sBufferdDeptUnit.append("<label><font color='red'>*</font>Consultant</label>");
				 sBufferdDeptUnit.append("<div id='consId'><select class='browser-default custom-select' name='strConsultantCode' tabindex='1'>");
				 sBufferdDeptUnit.append(vo.getStrConsultantValues());
				 sBufferdDeptUnit.append("</select></div>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='col-sm-4'>");
				 sBufferdDeptUnit.append("<label><font color='red'>*</font>Treatment/Patient Category</label>");
				 sBufferdDeptUnit.append("<div id='treatCat'><select  class='browser-default custom-select' name='strTreatmentCategoryCode' tabindex='1' onChange='getCombo(this);'>");
				 sBufferdDeptUnit.append(strMotherTreatmentCateg);
				 sBufferdDeptUnit.append("</select></div>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("</div>");

                 
               
                 sBufferdDeptUnit.append("<div class='row'>");	                      
        	     sBufferdDeptUnit.append("<div class='col-sm-4'>");
				 sBufferdDeptUnit.append("<label><font color='red'>*</font>Ward</label>");
				 sBufferdDeptUnit.append("<div id='WardId'><select class='browser-default custom-select' name='strWardCode' tabindex='1' >");
				 sBufferdDeptUnit.append(vo.getStrWardValue());
				 sBufferdDeptUnit.append("</select></div>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='col-sm-4'>");
				 sBufferdDeptUnit.append("<label>Case Sheet No.</label>");
				 sBufferdDeptUnit.append("<input type='text' class='form-control'>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='col-sm-4'>");
				 sBufferdDeptUnit.append("<label>Admission Type/Status</label>");
				 sBufferdDeptUnit.append("<select class='browser-default custom-select'><option>Normal</option> <option>Effective</option> <option>Emergency</option> <option selected=''>Normal</option>");
				 sBufferdDeptUnit.append("</select>");
                 sBufferdDeptUnit.append("</div>");
				 sBufferdDeptUnit.append("</div>");
                 
				
				
				/*sBufferEmgAddress.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'>I Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstPersonName'  tabindex='1'value='"+vo.getStrFirstPersonName()+"' maxlength='90' onkeypress='return validateData(event,4);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL' ><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strFirstPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' tabindex='1' name='strEmgAddress1' maxlength='300' >");
				sBufferEmgAddress.append(vo.getStrEmgAddress1());
				sBufferEmgAddress.append("</textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text'  name='strFirstpersonphone' value='"+vo.getStrFirstpersonphone()+"' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='20%' class='LABEL'>II Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondPersonName' value='"+vo.getStrSecondPersonName()+"' maxlength='90' onkeypress='return validateData(event,4);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL' ><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strSecondPersonRelationCode' tabindex='1' class='comboNormal'  >");
				sBufferEmgAddress.append(relationDtls1);
				sBufferEmgAddress.append("</select></td>\n");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' tabindex='1' name='strEmgAddress2' maxlength='300' >");
				sBufferEmgAddress.append(vo.getStrEmgAddress2());
				sBufferEmgAddress.append("</textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text'tabindex='1' value='"+vo.getStrSecondpersonphone()+"'  name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='25%'  class='LABEL'>Remarks</td> ");
				sBufferEmgAddress.append("<td width='75%' colspan='6' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea rows='1' cols='16' name='strRemarks'></textarea></td>");
			    sBufferEmgAddress.append("</tr>");
				sBufferEmgAddress.append("</table>");*/
				 
				 
				 sBufferEmgAddress.append("<div class='row'>");
				 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label>I Person Name</label>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strFirstPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+">");
                 sBufferEmgAddress.append("</div>");
				 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Relation</label>");
				 sBufferEmgAddress.append("<select class='form-control' name='strFirstPersonRelationCode' tabindex='1'>");
				 sBufferEmgAddress.append(relationDtls);
				 sBufferEmgAddress.append("</select>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Address</label>");
				 sBufferEmgAddress.append("<textarea  class='form-control' cols='9' rows='1' name='strEmgAddress1' maxlength='150' "+readOnly+"></textarea>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Phone</label>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strFirstpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("</div>");

                 sBufferEmgAddress.append("<div class='row'>");
				 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label>II Person Name</label>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strSecondPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+">");
                 sBufferEmgAddress.append("</div>");
				 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Relation</label>");
				 sBufferEmgAddress.append("<select class='form-control' name='strSecondPersonRelationCode' tabindex='1'>");
				 sBufferEmgAddress.append(relationDtls);
				 sBufferEmgAddress.append("</select>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Address</label>");
				 sBufferEmgAddress.append("<textarea  class='form-control' cols='9' rows='1' name='strEmgAddress2' maxlength='150' "+readOnly+"></textarea>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='col-sm-3'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Phone</label>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("</div>");
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString()+"^"+sBufferdDeptUnit.toString()+"^"+sBufferEmgAddress.toString();
	}
	
	public static String getPatientDetailModi_BootstrapGrouped(PatientAdmissionModificationTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		StringBuffer sBufferdDeptUnit = new StringBuffer("");
		StringBuffer sBufferEmgAddress=new StringBuffer("");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospCode());
		PatientAdmissionModificationTransBO bo=new PatientAdmissionModificationTransBO();
		bo.setPatientAddModi(vo);
		String strAdmCharge=vo.getStrAdmissionChargeValue();
		String strFatherName=vo.getStrFatherName().trim();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strIdMark=vo.getStrIdMark()==null?"":vo.getStrIdMark().trim();
		String strStreet=vo.getStrStreet().trim();
		String stateName=vo.getStrStateName();
		String districtName=vo.getStrDistrict();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strMobileNo=vo.getStrMobileNo().trim();
		String strCity=vo.getStrCity().trim();
		String strTehsil="";
		String cityLocation="";
		if(vo.getStrCityLocation()!=null)
			cityLocation=vo.getStrCityLocation();
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		String strMaritalStatus="";
		String relationDtls="";
		String relationDtls1="";
		String strState=vo.getStrState().trim();
		String strDistrict=vo.getStrDistrict()==null?"":vo.getStrDistrict().trim();
		String strCountry="";
		String strPinCode=vo.getStrPinCode().trim();
		//	String strPatientName=vo.getStrPatientName().trim();
		String  strSpouseName=vo.getStrSpouseName().trim();
		String strMotherTreatmentCateg="",strAdmissionTypeValues="",strReliefFundValues="";
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		//System.out.println("caste is "+vo.getStrPatientCaste()+" "+vo.getStrSecondPersonRelationCode());
		
		HisUtil util = null;
		try
		{
			util = new HisUtil("Patient Admission Modification Transaction","PatientAdmissionDATA");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
			strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste().trim(),"0^Select Value", false);
			strMaritalStatus=util.getOptionValue(vo.getWrsMaritalStatus(),vo.getStrMaritalStatus(),"0^Select Value", false);
			
			if(!vo.getStrFirstPersonRelationCode().equals(null) || !vo.getStrFirstPersonRelationCode().equals(""))
			relationDtls=util.getOptionValue(vo.getStrRelationWs(),vo.getStrFirstPersonRelationCode(),"0^Select Value", false);
			else
			relationDtls=util.getOptionValue(vo.getStrRelationWs(),"","0^Select Value", false);
			
			PatientAdmissionModificationTransDAO.setRelation(vo);
			if(!vo.getStrSecondPersonName().equals(null))
			relationDtls1=util.getOptionValue(vo.getStrRelationWs(),vo.getStrSecondPersonRelationCode(),"0^Select Value", false);
			else
			relationDtls1=util.getOptionValue(vo.getStrRelationWs(),"","0^Select Value", false);
			
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
			
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
			strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),"0^Select Value", false);
			if(vo.getWrsAdmissionTypeValues()!=null && vo.getWrsAdmissionTypeValues().size()>0){
				strAdmissionTypeValues=util.getOptionValue(vo.getWrsAdmissionTypeValues(), vo.getStrAdmissionType(),"0^Select Value", false);
			}else{
				strAdmissionTypeValues="<option value='0'>Select Value</option>";
			}
			if(vo.getWrsReliefFundValues()!=null && vo.getWrsReliefFundValues().size()>0){
				strReliefFundValues=util.getOptionValue(vo.getWrsReliefFundValues(), vo.getStrReliefFund(), "0^Select Value", false);
			}else{
				strReliefFundValues="<option value='0'>Select Value</option>";
			}
			
			String readOnly="";
			if(vo.getStrPatIsUnknown().equals("1"))
				readOnly="readonly";
			
			
			
			/*
			
			<div class="col-md-12 lessPadding" id="tenderDtls">
			<fieldset class="col-md-12">
				<legend>Tender Detail(s) </legend>

				<div class="form-group col-md-3 col-sm-3">
					<font class="mandatory">*</font>
					<label for="">Contract From</label>
					

					<input id="hstdtContractFrmdate" name="hstdtContractFrmdate" class="form-control  hasDatepicker" placeholder="dd-Mmm-yyyy" type="text" value="23-Jul-2019" readonly="readonly">


				</div>

				<div class="form-group col-md-3 col-sm-3">
					<font class="mandatory">*</font>
					<label for="">Contract To</label>
					
					<input id="hstdtContractTodate" name="hstdtContractTodate" class="form-control  hasDatepicker" placeholder="dd-Mmm-yyyy" type="text" value="24-Jul-2019" readonly="readonly">


				</div>
				<div class="form-group col-md-3 col-sm-6 col-xs-12">

					<label for="hstnumContractQty">
							Contracted Qty</label>

					<input id="hstnumContractQty" name="hstnumContractQty" class="form-control " onkeypress="return validateData(event,5)" placeholder="Enter Contract Qty" type="text" value="" maxlength="16" data-bv-field="hstnumContractQty">

				<small style="display: none;" data-bv-validator="stringLength" data-bv-validator-for="hstnumContractQty" class="help-block">This value is not valid</small></div>

				<div class="form-group col-md-3  col-sm-6 col-xs-12">

					<label for="hstnumShelfLife">
						<font class="mandatory">*</font>Shelf Life in month(s)</label>

					<input id="hstnumShelfLife" name="hstnumShelfLife" class="form-control " onkeypress="return validateData(event,5)" placeholder="Enter Shelf Life" type="text" value="" maxlength="4" data-bv-field="hstnumShelfLife">

				<small style="display: none;" data-bv-validator="notEmpty" data-bv-validator-for="hstnumShelfLife" class="help-block">Please enter Shelf Life</small><small style="display: none;" data-bv-validator="between" data-bv-validator-for="hstnumShelfLife" class="help-block">Please enter a valid value</small><small style="display: none;" data-bv-validator="stringLength" data-bv-validator-for="hstnumShelfLife" class="help-block">This value is not valid</small></div>

		</div>
		
		*/
			/*
			
			<div class="row rowFlex reFlex">
			<div class="col-sm-2" style="">Patient&nbsp;Name&nbsp;<font color="red">*</font>(First)</div>
			<div class="col-sm-2 input-group" style="">
				<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
				<input name="patFirstName" tabindex="1" maxlength="33" type="text" onkeyup="multilingualConversion(this,document.getElementById('patFirstNameInMultiLangId'));" class="form-control validatebox-text validatebox-invalid">
			</div>
		
			
			<div class="col-sm-2" style="">(Middle)</div>
			<div class="col-sm-2 input-group" style="">
			<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
					<input name="patMiddleName" tabindex="2" maxlength="33" type="text" onclick="changeTabIndex()" onkeyup="multilingualConversion(this,document.getElementById('patMiddleNameInMultiLangId'));" class="form-control validatebox-text">
			
			
			</div>
			<div class="col-sm-2" style="">(Last)</div>
			<div class="col-sm-2 input-group" style="">
			<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
			<input name="patLastName" tabindex="2" maxlength="33" type="text" onclick="changeTabIndex()" onkeyup="multilingualConversion(this,document.getElementById('patLastNameInMultiLangId'));" class="form-control validatebox-text">
							
			</div>
		</div>*/
		
			String divClass="col-sm-2";
			String divClassSm1="col-sm-1";
			String divClassSm2="col-sm-2";
			String rowClass="row rowFlex reFlex";
			 
			//sBuffer.append("<div class='' style=''>\n");
            sBuffer.append("<div class='"+rowClass+"'>\n");
            //sBuffer.append("<div class='col-md-12 lessPadding'>\n");
            
           // sBuffer.append("<div class='card'>\n");
            //sBuffer.append("<article class='card-body'>\n");
			 //sBuffer.append("<div class='row'>");*/
			
			 
			 
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>House No.</label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<input type='text' class='form-control' name='strHouseNo' value='"+ strHouseNo +"' tabindex='2' maxlength='20' "+readOnly+">");
             sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Street/Mohallah<font color='red'>*</font></label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<input type='text' class='form-control' name='strStreet' value='"+ strStreet +"' tabindex='1' maxlength='60'  "+readOnly+" required>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Location</label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<input type='text' class='form-control' name='strCityLocation' value='"+ cityLocation +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             

             sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>City/Village<font color='red'>*</font></label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<input type='text' class='form-control' name='strCity' value='"+ strCity +"' tabindex='1' maxlength='60' onkeypress='return validateData(event,4);' "+readOnly+" required>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Landmark</label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<input type='text' class='form-control' name='strTehsil' value='"+ strTehsil +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Pin Code</label>");
			 sBuffer.append("</div><div class='"+divClass+" input-group sm'>");
			 sBuffer.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon3'><i class='fas fa-map-marker-alt'></i></span></div><input aria-describedby='basic-addon3' class='form-control' type='text' name='strPinCode' value='"+ strPinCode + "' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+">");
			 sBuffer.append("</div>");
             sBuffer.append("</div>");
			
             sBuffer.append("<div class='"+rowClass+"'>\n");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Country<font color='red'>*</font></label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<input type='hidden' name='strCountryName' value='' required/>");
			 sBuffer.append("<select name='strCountry' tabindex='1' class='browser-default custom-select'onchange='onchangeCountry()' required>");
			 sBuffer.append(strCountry);
			 sBuffer.append("</select>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>State<font color='red'>*</font></label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<div style='display:none;' id='stateTextBoxDiv'><input type='text' class='form-control' name='strStateName' id='strStateId' value='"+stateName+"' required/></div>");
			 sBuffer.append("<div id='stateComboDiv'><select name='strState' class='browser-default custom-select'  onchange='onchangeState()' required tabindex='1'>");
			 sBuffer.append(strState);
			 sBuffer.append("</select></div>");  
			 sBuffer.append("</div>");
			 
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>District<font color='red'>*</font></label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<div style='display:none;' id='districtTextBoxDiv'><input type='text' class='form-control' name='strDistrictName' value='"+districtName+"' / ></div>");
			 sBuffer.append("<div id='districtSelectBoxDiv'>");
			 sBuffer.append("<select name='strDistrictCode' id='strDistrictCode' class='browser-default custom-select' onclick='onchangeDistrict();' tabindex='1'>");
			 sBuffer.append(strDistrict);
			 sBuffer.append("</select>");                
			 sBuffer.append("</div>");
			 sBuffer.append("</div>");
			 
             sBuffer.append("</div>");
             
         	 sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("Phone No.</label>");
			 sBuffer.append("</div><div class='"+divClass+" input-group sm'>");
			 sBuffer.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'><i class='fas fa-phone-alt'></i></span></div><input  aria-describedby='basic-addon1' type='text' class='form-control' tabindex='2' name='strPhoneNo' value='"+ strPhoneNo +"' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+">");               
			 sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"' >");
             sBuffer.append("<label id='ind'>Mobile No.<font color='red'>*</font></label>");
             sBuffer.append("</div><div class='"+divClass+" input-group sm'>");
			 sBuffer.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon2'><i class='fas fa-mobile-alt'></i></span></div><input aria-describedby='basic-addon2' type='text' class='form-control' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+" required>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("</div>");
             sBuffer.append("</div>");
                                  
             sBuffer.append("<script>onchangeCountry();</script>");
             
             sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
					 
			 sBuffer.append("<label>Marital Status</label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<select class='browser-default custom-select' name='strMaritalStatus'  class='comboNormal'>");
			 sBuffer.append(strMaritalStatus);
			 sBuffer.append("</select>");   
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Religion</label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<select class='browser-default custom-select' name='strReligion'>");
			 sBuffer.append(strReligion);
			 sBuffer.append("</select>");  
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Patient Caste</label>");
			 sBuffer.append("</div><div class='"+divClass+"'>");
			 sBuffer.append("<select class='browser-default custom-select' name='strPatientCaste'>");
			 sBuffer.append(strPatientCaste);
			 sBuffer.append("</select>");                
			 sBuffer.append("</div>");	 
			 sBuffer.append("</div>");
           
			/*if(vo.getStrAdmissionChargeAtCounter().equals("1"))
			{
				sBuffer.append("</tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Admission Charge</td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("Charges Not Defined For Patient category");
				else
					sBuffer.append(strAdmCharge+" <img src='/HBIMS/hisglobal/images/INR.png'>");	
				sBuffer.append("</td>");
				sBuffer.append("<td width='25%' class='LABEL'></td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				sBuffer.append("</td>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='0'/>");
				else
					sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='"+strAdmCharge+"'/>");	
				
				sBuffer.append("<tr>");
			}*/

			
			sBuffer.append("<script>onchangeCountry();</script>");
			if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
			   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
			{
				sBuffer.append("<tr style='display:none;'>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL' >Mobile No.:<label id='ind' style='display:none;'>(+91)</label><font color='red'>*</font></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strMobileNo' value='"
				+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
			{
				sBuffer.append("<tr>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'  >Mobile No.:<label id='ind' style='display:none;'>(+91)</label><font color='red'>*</font></div></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' tabindex='1'name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
					   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
			{
				sBuffer.append("<tr>\n");			
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'  >Mobile No.:<label id='ind' style='display:none;'>(+91)</label><font color='red'>*</font></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pin Code:</div></td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
				
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");
			}
			else
			{
				/*sBuffer.append("<tr>\n");			
				sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				//sBuffer.append("</tr>\n");
				//sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Mobile No.:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1'name='strMobileNo' value='"
				+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
				//Changed By Amit Kumar Ateria
				sBuffer.append("</tr>\n");*/
			}
		
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
			//sBuffer.append("</table>\n");
			
			//sBuffer.append("</article>\n");
			 //sBuffer.append("</div>\n");
           //sBuffer.append("</div>\n");
           //sBuffer.append("</div>\n");
           //sBuffer.append("</div>\n");
			
			if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  >Department<font color='red'>*</font></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strDeptCode' tabindex='1'class='comboNormal'  onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' >Unit<font color='red'>*</font></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'>");
				sBufferdDeptUnit.append("<select name='strDeptUnitCode' tabindex='1'class='comboNormal'  onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrUnitValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select  tabindex='1'name='strTreatmentCategoryCode' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='form-control'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");			
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL'  ><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='form-control' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'  ><font color='red'>*</font>Room</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'>");
				sBufferdDeptUnit.append("<select name='strRoomCode' tabindex='1' class='comboNormal' >");
				sBufferdDeptUnit.append(vo.getStrRoomValue());
				sBufferdDeptUnit.append("</select></div></td></tr>");
				
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
				//sBufferdDeptUnit.append(strBedValues);
				sBufferdDeptUnit.append("</select></td>\n");
				
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
				
				sBufferdDeptUnit.append("<tr>");
				
				/*////Relief Fund Values
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				//identification textbox
				//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				//sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' value='"+strIdMark+"' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");
			}
			else if((ipdConfigUtil.getStrUnitNameReq().equals("1"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'><input type='hidden' name='strRoomCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Unit</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'>");
				sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal'  tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrUnitValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL' tabindex='1'><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("</td></tr>");
				
				sBufferdDeptUnit.append("<tr>");
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				sBufferdDeptUnit.append("<tr>");
				////Relief Fund Values
			/*	sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				
				/*sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				sBufferdDeptUnit.append("</tr>");*/
				sBufferdDeptUnit.append("</table>\n");
			}
			
			else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("1")))
			{
				sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'><input type='hidden' name='strDeptUnitCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><selecttabindex='1' name='strTreatmentCategoryCode' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1' width='25%' class='LABEL' '><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'  ><font color='red'>*</font>Room</td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='RoomId'>");
				sBufferdDeptUnit.append("<select name='strRoomCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrRoomValue());
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
				sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
				sBufferdDeptUnit.append("</td></tr>");
				////bed
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
				//sBufferdDeptUnit.append(strBedValues);
				sBufferdDeptUnit.append("</select></td>\n");
			    ////admission type
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				sBufferdDeptUnit.append("</tr>");
				
				//sBufferdDeptUnit.append("<tr>");
				////Relief Fund Values
				/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");*/
				//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				//sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				//sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");
				
			}
			else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
			{
				/*sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>Department</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='UnitId'><input type='hidden'tabindex='1' name='strDeptUnitCode' value='0'/></div>");
				sBufferdDeptUnit.append("<div id='RoomId'><input type='hidden' name='strRoomCode' value='0'/></div>");
				sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(vo.getStrDeptValue());
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'  ><font color='red'>*</font>" +"Consultant</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
				sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
				sBufferdDeptUnit.append(vo.getStrConsultantValues());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</tr>\n");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' ><font color='red'>*</font>Treatment/Patient Category</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
				sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' tabindex='1' class='comboNormal' onChange='getCombo(this);'>");
				sBufferdDeptUnit.append(strMotherTreatmentCateg);
				sBufferdDeptUnit.append("</select></div></td>");
				sBufferdDeptUnit.append("<td colspan='1' class='LABEL' ><font color='red'>*</font>Ward</td>\n");
				sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
				sBufferdDeptUnit.append("<div id='WardId'>");
				sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' >");
				sBufferdDeptUnit.append(vo.getStrWardValue());
				sBufferdDeptUnit.append("</select></div></td>\n");
				sBufferdDeptUnit.append("</td></tr>");
				sBufferdDeptUnit.append("<tr>");
				////admission type
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Admission Type</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strAdmissionType' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strAdmissionTypeValues);
				sBufferdDeptUnit.append("</select></td>\n");
				////Relief Fund Values
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Relief Fund</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
				sBufferdDeptUnit.append("<select name='strReliefFund' class='comboNormal' tabindex='1' onChange=''>");
				sBufferdDeptUnit.append(strReliefFundValues);
				sBufferdDeptUnit.append("</select></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("<tr>");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark' value='"+vo.getStrIdMark()+"' class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
				sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
				sBufferdDeptUnit.append("</tr>");
				sBufferdDeptUnit.append("</table>\n");*/
				
				 sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
				 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<label>Department<font color='red'>*</font></label>");
				 sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
				 sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='0' >");
				 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<select class='browser-default custom-select' name='strDeptCode'  tabindex='1' onChange='getCombo(this);' required>");
				 sBufferdDeptUnit.append(vo.getStrDeptValue());
				 sBufferdDeptUnit.append("</select>");
                 sBufferdDeptUnit.append("</div>");
				 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<label>Consultant<font color='red'>*</font></label>");
				 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<div id='consId'><select class='browser-default custom-select' name='strConsultantCode' tabindex='1' required>");
				 sBufferdDeptUnit.append(vo.getStrConsultantValues());
				 sBufferdDeptUnit.append("</select></div>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<label>Patient Category<font color='red'>*</font></label>");
				 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<div id='treatCat'><select disabled class='browser-default custom-select' name='strTreatmentCategoryCode' tabindex='1' onChange='getCombo(this);' required>");
				 sBufferdDeptUnit.append(strMotherTreatmentCateg);
				 sBufferdDeptUnit.append("</select></div>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("</div>");

                 
               
                 sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
        	     sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<label>Ward<font color='red'>*</font></label>");
				 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<div id='WardId'><select class='browser-default custom-select' name='strWardCode' tabindex='1' required>");
				 sBufferdDeptUnit.append(vo.getStrWardValue());
				 sBufferdDeptUnit.append("</select></div>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<label>Case Sheet No.</label>");
				 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<input type='text' class='form-control' name='strCaseSheetNo' value='"+vo.getStrCaseSheetNo()+"' maxlength='15'>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<label>Admission Type</label>");
				 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
				 sBufferdDeptUnit.append("<select class='browser-default custom-select' name='strAdmissionType'>"+strAdmissionTypeValues+"");
				 sBufferdDeptUnit.append("</select>");
                 sBufferdDeptUnit.append("</div>");
				 sBufferdDeptUnit.append("</div>");
                 
				/* sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
        	     sBufferdDeptUnit.append("<div class='"+divClass+"'>");
				sBufferdDeptUnit.append("<button type='button' class='btn btn-info btn-sm' onclick='emgDiv();' data-toggle='collapse' data-target='#emgid' style='float:left;background: #48b1f2 !important;border:0px;'><span class='btn-label'><i class='fa fa-ambulance'></i></span>&nbsp;Emergency Contact&nbsp;</button>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
                 sBufferdDeptUnit.append("</div>");
                 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
                 sBufferdDeptUnit.append("</div>");
				 sBufferdDeptUnit.append("</div>");
				
				*/
				/*sBufferEmgAddress.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'>I Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstPersonName'  tabindex='1'value='"+vo.getStrFirstPersonName()+"' maxlength='90' onkeypress='return validateData(event,4);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL' ><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strFirstPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' tabindex='1' name='strEmgAddress1' maxlength='300' >");
				sBufferEmgAddress.append(vo.getStrEmgAddress1());
				sBufferEmgAddress.append("</textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text'  name='strFirstpersonphone' value='"+vo.getStrFirstpersonphone()+"' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='20%' class='LABEL'>II Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondPersonName' value='"+vo.getStrSecondPersonName()+"' maxlength='90' onkeypress='return validateData(event,4);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL' ><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strSecondPersonRelationCode' tabindex='1' class='comboNormal'  >");
				sBufferEmgAddress.append(relationDtls1);
				sBufferEmgAddress.append("</select></td>\n");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' tabindex='1' name='strEmgAddress2' maxlength='300' >");
				sBufferEmgAddress.append(vo.getStrEmgAddress2());
				sBufferEmgAddress.append("</textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL' ><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text'tabindex='1' value='"+vo.getStrSecondpersonphone()+"'  name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='25%'  class='LABEL'>Remarks</td> ");
				sBufferEmgAddress.append("<td width='75%' colspan='6' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea rows='1' cols='16' name='strRemarks'></textarea></td>");
			    sBufferEmgAddress.append("</tr>");
				sBufferEmgAddress.append("</table>");*/
				 
				 
				 
				 
				 /*sBufferEmgAddress.append("<div class='"+rowClass+"'>\n");
				 sBufferEmgAddress.append("<div class='"+divClass+"'>");
				 sBufferEmgAddress.append("<button type='button' class='btn btn-info btn-sm' onclick='emgDiv();' data-toggle='collapse' data-target='#emgid' style='float:left;background: #48b1f2 !important;border:0px;'><span class='btn-label'><i class='fa fa-ambulance'></i></span>&nbsp;Emergency Contact&nbsp;</button>");
				 sBufferEmgAddress.append("</div>");
				 sBufferEmgAddress.append("<div class='"+divClass+"'>");
				 sBufferEmgAddress.append("</div>");
				 sBufferEmgAddress.append("<div class='"+divClass+"'>");
				 sBufferEmgAddress.append("</div>");
				 sBufferEmgAddress.append("</div>");
				 */
				 sBufferEmgAddress.append("<div class='"+rowClass+"'>\n");
				 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label>Person Name(I)</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strFirstPersonName' value='"+vo.getStrFirstPersonName()+"' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+">");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Phone</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+" input-group'>");
				 sBufferEmgAddress.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'><i class='fas fa-phone-alt'></i></span></div>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strFirstpersonphone' value='"+vo.getStrFirstpersonphone()+"' maxlength='13' onkeypress='return validateData(event,5);'/>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Relation</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<select class='browser-default custom-select' name='strFirstPersonRelationCode' tabindex='1'>");
				 sBufferEmgAddress.append(relationDtls);
				 sBufferEmgAddress.append("</select>");
                 sBufferEmgAddress.append("</div>");      
                 sBufferEmgAddress.append("</div>");                 

                 /*sBufferEmgAddress.append("<div class='"+divClassSm1+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Address</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<textarea  class='form-control' cols='9' rows='1' name='strEmgAddress1' maxlength='150' "+readOnly+"></textarea>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("</div>");*/
                 
                 //sBufferEmgAddress.append("<div class='"+rowClass+"'>\n");
				 sBufferEmgAddress.append("<div class='"+rowClass+"'>\n");
                 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label>Person Name(II)</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strSecondPersonName' value='"+vo.getStrSecondPersonName()+"' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+">");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Phone</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+" input-group'>");
				 sBufferEmgAddress.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'><i class='fas fa-phone-alt'></i></span></div>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strSecondpersonphone'  value='"+vo.getStrSecondpersonphone()+"' maxlength='13' onkeypress='return validateData(event,5);'/>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Relation</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<select class='browser-default custom-select' name='strSecondPersonRelationCode' tabindex='1'>");
				 sBufferEmgAddress.append(relationDtls1);
				 sBufferEmgAddress.append("</select>");
                 sBufferEmgAddress.append("</div>");
                 
                 /*sBufferEmgAddress.append("<div class='"+divClassSm1+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Address</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<textarea  class='form-control' cols='9' rows='1' name='strEmgAddress2' maxlength='150' "+readOnly+"></textarea>");
                 sBufferEmgAddress.append("</div>");
                 sBufferEmgAddress.append("</div>");*/
			}
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionModificationTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString()+"^"+sBufferdDeptUnit.toString()+"^"+sBufferEmgAddress.toString();
	}
}
