package ipd.transactions;



import ipd.IpdConfigUtil;

import java.util.ResourceBundle;

import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

//import com.jscape.ftcl.a.v;

public class PatientAdmissionTransHLP {
	
	static String selectClass="browser-default custom-select";
	static String textClass="form-control";
/**
 * This function used to develop grid on bed details pop up window that contains bed details with their respective status
 * @param vo
 * @return
 */
	public static String getBedDetails(PatientAdmissionTransVO vo)
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
					
					if(count>size)
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
		sb.append("<tr><td bgcolor='#FFA980' width='10%'></td><td class='multiLabel' width='60'>Blocked</td>");
  		sb.append("<td width='10'></td>");
  		sb.append("<td bgcolor='#CB5C5C' width='10%'></td>");
  		sb.append("<td class='multiLabel' width='60%'>Occupied</td>");
  		sb.append("<td width='10%'></td>");
		sb.append("<td bgcolor='#8CE3E3' width='10%'></td>");
  		sb.append("<td class='multiLabel' width='60%'>Booked</td><td width='10'></td>");
  		sb.append("<td bgcolor='#88F78D' width='10%'></td>\n");
  		sb.append("<td class='multiLabel' width='60%'>Vacant</td>");
  		sb.append("<td width='10'></td><td bgcolor='#FEEB9E' width='10%'></td>");
  		sb.append("	<td class='multiLabel' width='60%'>Vacant But Dirty</td>	</tr>");
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
	 * @return string
	 * changes done on 27-04-2011 to show hide address details from adt_mandatory_info.properties file
	 */
	public static String getPatientDetailModi(PatientAdmissionTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		StringBuffer sBufferdDeptUnit = new StringBuffer("");
		StringBuffer sBufferEmgAddress=new StringBuffer("");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName().trim();
		String strAdmCharge=vo.getStrAdmissionChargeValue();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strStreet=vo.getStrStreet().trim();
		String stateName=vo.getStrStateName();
		String districtName=vo.getStrDistrict();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strMobileNo=vo.getStrMobileNo().trim();
		String strCity=vo.getStrCity().trim();
		String cityLocation="";
		String relationDtls="";
		if(vo.getStrCityLocation() != null)
		 cityLocation=vo.getStrCityLocation().trim();
		String strTehsil="";
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		String strMaritalStatus="";
		String strState=vo.getStrState().trim();
		String strDistrict=vo.getStrDistrict()!=null?vo.getStrDistrict().trim():"";
		String strCountry="";
		String strPinCode=vo.getStrPinCode();
		String  strSpouseName=vo.getStrSpouseName().trim();
		String strDeptValues="";
		String strWardValues=""; 
		String strReliefFundValues="";
		String strStateName="";
		String strDistrictName="";
		//String strUnitValues="";
		String strTreatmentCategValues="";
		String strConsultantValues="";
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospCode());
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		HisUtil util = null;
		try
		{				
				util = new HisUtil("Patient Admission Trans","PatientAdmissionDATA");
				strDeptValues = util.getOptionValue(vo.getDepartWS(), vo.getStrDeptCode(),"0^Select Value", false);
				strWardValues = util.getOptionValue(vo.getWardWS(), vo.getStrWardCode(),"0^Select Value", false);
				relationDtls  = util.getOptionValue(vo.getStrRelationWs(),  "","0^Select Value", false);
				/*if(vo.getWrsAdmissionTypeValues()!=null && vo.getWrsAdmissionTypeValues().size()>0){
					strAdmissionTypeValues=util.getOptionValue(vo.getWrsAdmissionTypeValues(), vo.getStrAdmissionType(),"0^Select Value", false);
				}else{
					strAdmissionTypeValues="<option value='0'>Select Value</option>";
				}*/
				
				if(vo.getWrsReliefFundValues()!=null && vo.getWrsReliefFundValues().size()>0){
					strReliefFundValues=util.getOptionValue(vo.getWrsReliefFundValues(), vo.getStrReliefFund(), "0^Select Value", false);
				}else{
					strReliefFundValues="<option value='0'>Select Value</option>";
				}
				
				
				strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
				strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste(),"0^Select Value", false);
				strMaritalStatus=util.getOptionValue(vo.getWrsMaritalStatus(),vo.getStrMaritalStatus(),"0^Select Value", false);
				strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
				
				if(vo.getStrCountryCode().equals("IND")){
				strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
				strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
				}else
				{
					strStateName = vo.getStrStateName();
					strDistrictName =vo.getStrDistrict();
				}
				
				strTreatmentCategValues=util.getOptionValue(vo.getTreatmentCategWS(), vo.getStrTreatmentCategoryCode(),"0^Select Value", false);
				strConsultantValues=util.getOptionValue(vo.getConsultantWS(), "","0^Select Value", false);
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
				if(vo.getStrIsAdmissionOnline().equals("1"))//Online Admission-Advice Raised
				{
					if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department/Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName()+"/"+vo.getStrUnitName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange='changeColor();'>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
		
						/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='"+vo.getStrRoom()+"' />");

						sBufferdDeptUnit.append("</table>\n");
					}
					else if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department/Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName()+"/"+vo.getStrUnitName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='-' />");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("</tr>");
					
						
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("</table>\n");
					}
					else if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("</td></tr>\n");
						////bed
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");

						sBufferdDeptUnit.append("</select></td>\n");
					  
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("</tr>");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='"+vo.getStrRoom()+"' />");
						sBufferdDeptUnit.append("</table>\n");
					}
					else if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department/Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName()+"/"+vo.getStrDeptUnitName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='-' />");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("</tr>");
					     
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("</table>\n");
					}
				}
				else//Offline Admission
				{
 					if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
					{
					sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
					sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='unitDetails()'>");
					sBufferdDeptUnit.append(strDeptValues);
					sBufferdDeptUnit.append("</select></td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Unit</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
					sBufferdDeptUnit.append("<div id='UnitId'>");
					sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal' tabindex='1' onChange='changeUnit();'>");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
					sBufferdDeptUnit.append("</select></div></td>\n");
					sBufferdDeptUnit.append("</tr>\n");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Adviced By Consultant</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
					sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='wardonUnit(this);'>");
					sBufferdDeptUnit.append(strConsultantValues);
					sBufferdDeptUnit.append("</select></div></td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
					sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges();'>");
					sBufferdDeptUnit.append(strTreatmentCategValues);
					sBufferdDeptUnit.append("</select></div></td>");
					sBufferdDeptUnit.append("</tr>\n");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td colspan='1' class='LABEL'><font color='red'>*</font>Ward</td>\n");
					sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
					sBufferdDeptUnit.append("<div id='WardId'>");
					//sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='roomDetails(this);getAdmissionCharges();'>");
					sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges();'>");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
					sBufferdDeptUnit.append("</select></div></td>\n");
					sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'>Room</td>\n");
					sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
					sBufferdDeptUnit.append("<div id='RoomId'>");
					sBufferdDeptUnit.append("<select name='strRoomCode' tabindex='1' class='comboNormal' >");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
					sBufferdDeptUnit.append("</select></div></td></tr>");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
					sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
    				sBufferdDeptUnit.append("</select></td>\n");
					
				
					
					
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
					sBufferdDeptUnit.append("</tr>");
					sBufferdDeptUnit.append("</table>\n");
					}
					else if((ipdConfigUtil.getStrUnitNameReq().equals("1"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='unitDetails()'>");
						sBufferdDeptUnit.append(strDeptValues);
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='UnitId'>");
						sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal'  tabindex='1' onChange='changeUnit();'>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Adviced By Consultant</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='wardonUnit(this);'>");
						sBufferdDeptUnit.append(strConsultantValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges()'>");
						sBufferdDeptUnit.append(strTreatmentCategValues);
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td colspan='1' class='LABEL'><font color='red'>*</font>Ward</td>\n");
						sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='WardId'>");
						sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges();' >");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
						sBufferdDeptUnit.append("</td></tr>");
						
						
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("</table>\n");
					}
					
					else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("1")))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
						sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='ConsultantDetails()'>");
						sBufferdDeptUnit.append(strDeptValues);
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Adviced By Consultant</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='wardonUnit(this);'>");
						sBufferdDeptUnit.append(strConsultantValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges()'>");
						sBufferdDeptUnit.append(strTreatmentCategValues);
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("<td colspan='1' width='25%' class='LABEL'><font color='red'>*</font>Ward</td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='WardId'>");
						//sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='roomDetails(this);getAdmissionCharges();'>");
						sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='roomDetails(this);'>");
						sBufferdDeptUnit.append(strWardValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'><font color='red'>*</font>Room</td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='RoomId'>");
						sBufferdDeptUnit.append("<select name='strRoomCode' class='comboNormal' tabindex='1' >");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
						sBufferdDeptUnit.append("</td></tr>");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></td>\n");
						
						
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("</tr>");
						sBufferdDeptUnit.append("</table>\n");
						
					}
 					//else
					else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='ConsultantDetails()'>");
						sBufferdDeptUnit.append(strDeptValues);
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Adviced By Consultant</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal' onChange='wardonUnit(this);'>");
						sBufferdDeptUnit.append(strConsultantValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges()'>");
						sBufferdDeptUnit.append(strTreatmentCategValues);
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("<td colspan='1' class='LABEL'><font color='red'>*</font>Ward</td>\n");
						sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='WardId'>");
						//sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges()'>");
						sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges();'>");
						sBufferdDeptUnit.append(strWardValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("</tr>\n");	
						sBufferdDeptUnit.append("<tr>");
						//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Case Sheet No.</td>\n");
						
					/*	Admission type
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'> Admission Type</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id=''><select name='strAdmissionTypeCode' tabindex='1' class='comboNormal'>");
						sBufferdDeptUnit.append(strAdmissionTypeValues);	
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</td>");
						*/
						
						//change the combo
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("</table>\n");
					}
				}		
				String readOnly="";
				if(vo.getStrPatIsUnknown().equals("1"))
					readOnly="readonly";
				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' value='"+ strHouseNo + "' tabindex='2' class='txtFldNormal' maxlength='20' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Street/Mohallah</td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strStreet' value='"+ strStreet + "' class='txtFldMax' maxlength='60'  "+readOnly+"></td>\n");
				sBuffer.append("</tr>\n");
				
				sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'>Location</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text'   name='strCityLocation' value='"+ cityLocation + "' class='txtFldMax' maxlength='60' tabindex='2' onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>City/Village</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text'   name='strCity' value='"+ strCity + "' class='txtFldMax' maxlength='60' tabindex='1' onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
				sBuffer.append("</tr>\n");
				
				sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'>Landmark</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
				//sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"+ strDistrict + "' class='txtFldMax'  maxlength='30'  onkeypress='return validateData(event,4);'></td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='districtTextBoxDiv'><input type='text' name='strDistrictName' value='"+districtName+"' / ></div><div id='districtSelectBoxDiv'><select name='strDistrictCode' id='strDistrictCode' class='comboNormal' onclick='onchangeDistrict();' tabindex='2'>\n");
				sBuffer.append(strDistrict);
				sBuffer.append("</select></div></td>\n");
				sBuffer.append("</tr>");
				
				sBuffer.append("<tr>");
				sBuffer.append("<td class='LABEL' ><font color='red'>*</font>State</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='stateTextBoxDiv'><input type='text' name='strStateName' id='strStateId' value='"+strStateName+"'/></div><div id='stateComboDiv'><select name='strState' class='comboNormal' tabindex='1' onchange='onchangeState()'>\n");
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
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='2' name='strPhoneNo' value='"+ strPhoneNo + "' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+"></td>");
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
				sBuffer.append("</tr>");
				//elzss
				if(vo.getStrAdmissionCharge().equals("1"))
				{
					sBuffer.append("</tr>");
					sBuffer.append("<td width='25%' class='LABEL'>Admission Charge</td>");
					sBuffer.append("<td width='25%' class='CONTROL'>");
				
				if(ipdConfigUtil.getStrAdmissionOnline().equals("1"))
				{
					if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("Charges Not Defined For Patient category");
					else
					sBuffer.append(strAdmCharge+" <img src='/HBIMS/hisglobal/images/INR.png'>");	
					sBuffer.append("</td>");
				}
				else
				{
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
				sBuffer.append(" <input type='text'  name='strAdmcharge' id='strAdmcharge' value='0' disabled='true'>" +" "+"<img src='/HBIMS/hisglobal/images/INR.png' >");	
				else
				sBuffer.append(" <input type='text'  name='strAdmcharge'  id ='strAdmcharge' value="+strAdmCharge+" disabled='true'>" + " "+ "<img src='/HBIMS/hisglobal/images/INR.png'>");	
				sBuffer.append("</td>");
				}
				
				
				sBuffer.append("<td width='25%' class='LABEL'></td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				sBuffer.append("</td>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
				sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='0'/>");
				else
				sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='"+strAdmCharge+"'/>");
				
				sBuffer.append("<tr>");
				}
				
				
				
				
				
				if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
				   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
				{
					sBuffer.append("<tr style='display:none;'>\n");
					sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' tabindex='1' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("</tr>\n");
				}
				else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
						   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
				{
							sBuffer.append("<tr>\n");
							sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
							sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
							sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pin Code:</div></td>\n");
							sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
							sBuffer.append("</tr>\n");
				}
				else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
						   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
				{
							sBuffer.append("<tr>\n");
							sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
							sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
							sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></div></td>\n");				
							sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strMobileNo'  tabindex='1' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
							sBuffer.append("</tr>\n");
				}
				else
				{
					sBuffer.append("<tr>\n");
					sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("</tr>\n");
				}
				
				sBuffer.append("</tr></table>\n");
				if(vo.getStrIsAdmissionOnline().equals("1")){
					sBufferdDeptUnit.append("<input type='hidden' name='strDeptCode' value='"+vo.getStrDeptCode()+"'>");
					sBufferdDeptUnit.append("<input type='hidden' name='strConsultantCode' value='"+vo.getStrConsultantCode()+"'>");
					sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrTreatmentCategoryCode()+"'>");
					sBufferdDeptUnit.append("<input type='hidden' name='strWardCode' value='"+vo.getStrWardCode()+"'/>");
					sBufferdDeptUnit.append("<input type='hidden' name='strWardName' value='"+vo.getStrWardName()+"'/>");
					sBufferdDeptUnit.append("<input type='hidden' name='strWard' value='"+vo.getStrWardName()+"'/>");
				}
				
				sBufferEmgAddress.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'>I Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+"/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strFirstPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' name='strEmgAddress1' maxlength='150' "+readOnly+"></textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL'><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstpersonphone' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+"/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='20%' class='LABEL'>II Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+"/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strSecondPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>\n");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' name='strEmgAddress2' maxlength='150' "+readOnly+"></textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL'><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				
				sBufferEmgAddress.append("</table>");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString()+"^"+sBufferdDeptUnit+"^"+sBufferEmgAddress;
	}
	
	public static String getPatientDetailModi_Bootstrap(PatientAdmissionTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		StringBuffer sBufferdDeptUnit = new StringBuffer("");
		StringBuffer sBufferEmgAddress=new StringBuffer("");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("ipd.adt_mandatory_info");
		PatientAdmissionTransBO bo=new PatientAdmissionTransBO();
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName().trim();
		String strAdmCharge=vo.getStrAdmissionChargeValue();
		String strReligion="",strPatientCaste="";
		String strHouseNo=vo.getStrHouseNo().trim();
		String strStreet=vo.getStrStreet().trim();
		String stateName=vo.getStrStateName();
		String districtName=vo.getStrDistrict();
		String strPhoneNo=vo.getStrPhoneNo().trim();
		String strMobileNo=vo.getStrMobileNo().trim();
		String strCity=vo.getStrCity().trim();
		String cityLocation="";
		String relationDtls="";
		if(vo.getStrCityLocation() != null)
		 cityLocation=vo.getStrCityLocation().trim();
		String strTehsil="";
		if(vo.getStrTehsil()!=null)
			strTehsil=vo.getStrTehsil().trim();
		String strMaritalStatus="";
		String strState=vo.getStrState().trim();
		String strDistrict=vo.getStrDistrict()!=null?vo.getStrDistrict().trim():"";
		String strCountry="";
		String strPinCode=vo.getStrPinCode();
		String  strSpouseName=vo.getStrSpouseName().trim();
		String strDeptValues="";
		String strWardValues=""; 
		String strReliefFundValues="";
		String strStateName="";
		String strDistrictName="";
		//String strUnitValues="";
		String strTreatmentCategValues="";
		String strConsultantValues="";
		
		String divClass="col-sm-2";
		String divClassSm1="col-sm-1";
		String divClassSm2="col-sm-2";
		String rowClass="row rowFlex reFlex";
		
		IpdConfigUtil ipdConfigUtil=new IpdConfigUtil(vo.getStrHospCode());
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		HisUtil util = null;
		try
		{				
				util = new HisUtil("Patient Admission Trans","PatientAdmissionDATA");
				strDeptValues = util.getOptionValue(vo.getDepartWS(), vo.getStrDeptCode(),"0^Select Value", false);
				strWardValues = util.getOptionValue(vo.getWardWS(), vo.getStrWardCode(),"0^Select Value", false);
				relationDtls  = util.getOptionValue(vo.getStrRelationWs(),  "","0^Select Value", false);
				/*if(vo.getWrsAdmissionTypeValues()!=null && vo.getWrsAdmissionTypeValues().size()>0){
					strAdmissionTypeValues=util.getOptionValue(vo.getWrsAdmissionTypeValues(), vo.getStrAdmissionType(),"0^Select Value", false);
				}else{
					strAdmissionTypeValues="<option value='0'>Select Value</option>";
				}*/
				
				if(vo.getWrsReliefFundValues()!=null && vo.getWrsReliefFundValues().size()>0){
					strReliefFundValues=util.getOptionValue(vo.getWrsReliefFundValues(), vo.getStrReliefFund(), "0^Select Value", false);
				}else{
					strReliefFundValues="<option value='0'>Select Value</option>";
				}
				
				
				strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),"0^Select Value", false);
				strPatientCaste=util.getOptionValue(vo.getWrsPatientCaste(),vo.getStrPatientCaste(),"0^Select Value", false);
				strMaritalStatus=util.getOptionValue(vo.getWrsMaritalStatus(),vo.getStrMaritalStatus(),"0^Select Value", false);
				strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),"0^Select Value", false);
				
				if(vo.getStrCountryCode().equals("IND")){
				strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),"0^Select Value", false);
				strDistrict=util.getOptionValue(vo.getDistrictWS(),  vo.getStrDistrictCode(),"0^Select Value", false);
				}else
				{
					strStateName = vo.getStrStateName();
					strDistrictName =vo.getStrDistrict();
				}
				
				strTreatmentCategValues=util.getOptionValue(vo.getTreatmentCategWS(), vo.getStrTreatmentCategoryCode(),"0^Select Value", false);
				strConsultantValues=util.getOptionValue(vo.getConsultantWS(), "","0^Select Value", false);
				//sBuffer.append("<table id='' class='dislay' style='wodth:100%'>\n");
				 
	             //sBuffer.append("<form class='form-inline'>\n");
	             
	             
	             
               if(vo.getStrIsAdmissionOnline().equals("1"))//Online Admission-Advice Raised
				{
					if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department/Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName()+"/"+vo.getStrUnitName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange='changeColor();'>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
		
						/*sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='"+vo.getStrRoom()+"' />");

						sBufferdDeptUnit.append("</table>\n");
					}
					else if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department/Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName()+"/"+vo.getStrUnitName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='-' />");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("</tr>");
					
						
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("</table>\n");
					}
					else if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrDeptName());
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");
						sBufferdDeptUnit.append("</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Advised By</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrConsultantName());
						sBufferdDeptUnit.append("</td></tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						//sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrPrimaryCategoryCode()+"' >");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Ward</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append(vo.getStrWardName());
						sBufferdDeptUnit.append("</td></tr>\n");
						////bed
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");

						sBufferdDeptUnit.append("</select></td>\n");
					  
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("</tr>");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='"+vo.getStrRoom()+"' />");
						sBufferdDeptUnit.append("</table>\n");
					}
					else if(ipdConfigUtil.getStrUnitNameReq().equals("0") && ipdConfigUtil.getStrRoomNoReq().equals("0"))
					{
						 sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Department</label>");
						 sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						 sBufferdDeptUnit.append("<input type='hidden' name='strRoom' value='' />");
						 
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append(vo.getStrDeptName());
						 
                         sBufferdDeptUnit.append("</div>");
                         
                         sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Unit</label>");
						 sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='"+vo.getStrDeptUnitCode()+"'>");						 
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append(vo.getStrDeptUnitName());						 
                         sBufferdDeptUnit.append("</div>");
                         
                         sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Ward</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<div id='WardId'>");
						 sBufferdDeptUnit.append(vo.getStrWardName());
						 sBufferdDeptUnit.append("</div>");
                         sBufferdDeptUnit.append("</div>");       				 	 
                         sBufferdDeptUnit.append("</div>");
                         
                         sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
                         sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Consultant</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<div id='consId'>");
						 sBufferdDeptUnit.append(vo.getStrConsultantName());
						 sBufferdDeptUnit.append("</div>");
                         sBufferdDeptUnit.append("</div>");                       
                         sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Treatment Category</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<div id='treatCat'>");
						 sBufferdDeptUnit.append(vo.getStrTreatmentCategoryName());
						 sBufferdDeptUnit.append("</div>");
                         sBufferdDeptUnit.append("</div>");                        					 
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Case Sheet No.</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<input type='text' class='"+textClass+"' tabindex='2'>");
                         sBufferdDeptUnit.append("</div>");
                         sBufferdDeptUnit.append("</div>");
                         
                         sBufferdDeptUnit.append("<div class='"+rowClass+"'  style='display:none;'>\n");
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Admission Type/Status</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<select class='"+selectClass+"' tabindex='2'><option>Normal</option> <option>Effective</option> <option>Emergency</option> <option selected=''>Normal</option>");
						 sBufferdDeptUnit.append("</select>");
                         sBufferdDeptUnit.append("</div>");
						 sBufferdDeptUnit.append("</div>");
						 
					}
				}
				else//Offline Admission
				{
 					if(ipdConfigUtil.getStrUnitNameReq().equals("1") && ipdConfigUtil.getStrRoomNoReq().equals("1"))
					{
					sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
					sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='unitDetails()'>");
					sBufferdDeptUnit.append(strDeptValues);
					sBufferdDeptUnit.append("</select></td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Unit</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
					sBufferdDeptUnit.append("<div id='UnitId'>");
					sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal' tabindex='1' onChange='changeUnit();'>");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
					sBufferdDeptUnit.append("</select></div></td>\n");
					sBufferdDeptUnit.append("</tr>\n");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Consultant</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
					sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
					sBufferdDeptUnit.append(strConsultantValues);
					sBufferdDeptUnit.append("</select></div></td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
					sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='wardDetails(this);'>");
					sBufferdDeptUnit.append(strTreatmentCategValues);
					sBufferdDeptUnit.append("</select></div></td>");
					sBufferdDeptUnit.append("</tr>\n");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td colspan='1' class='LABEL'><font color='red'>*</font>Ward</td>\n");
					sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
					sBufferdDeptUnit.append("<div id='WardId'>");
					sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='roomDetails(this);getAdmissionCharges();'>");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
					sBufferdDeptUnit.append("</select></div></td>\n");
					sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'>Room</td>\n");
					sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
					sBufferdDeptUnit.append("<div id='RoomId'>");
					sBufferdDeptUnit.append("<select name='strRoomCode' tabindex='1' class='comboNormal' >");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
					sBufferdDeptUnit.append("</select></div></td></tr>");
					sBufferdDeptUnit.append("<tr>");
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
					sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
					sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
    				sBufferdDeptUnit.append("</select></td>\n");
					
				
					
					
					sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
					sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
					sBufferdDeptUnit.append("</tr>");
					sBufferdDeptUnit.append("</table>\n");
					}
					else if((ipdConfigUtil.getStrUnitNameReq().equals("1"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='unitDetails()'>");
						sBufferdDeptUnit.append(strDeptValues);
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Unit</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='UnitId'>");
						sBufferdDeptUnit.append("<select name='strDeptUnitCode' class='comboNormal'  tabindex='1' onChange='changeUnit();'>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Consultant</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
						sBufferdDeptUnit.append(strConsultantValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='wardDetails(this)'>");
						sBufferdDeptUnit.append(strTreatmentCategValues);
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td colspan='1' class='LABEL'><font color='red'>*</font>Ward</td>\n");
						sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='WardId'>");
						sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1'  >");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
						sBufferdDeptUnit.append("</td></tr>");
						
						
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						sBufferdDeptUnit.append("</table>\n");
					}
					
					else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("1")))
					{
						sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
						sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='ConsultantDetails()'>");
						sBufferdDeptUnit.append(strDeptValues);
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Consultant</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
						sBufferdDeptUnit.append(strConsultantValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='wardDetails(this)'>");
						sBufferdDeptUnit.append(strTreatmentCategValues);
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("<td colspan='1' width='25%' class='LABEL'><font color='red'>*</font>Ward</td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='WardId'>");
						sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='roomDetails(this);getAdmissionCharges();'>");
						sBufferdDeptUnit.append(strWardValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'><font color='red'>*</font>Room</td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='RoomId'>");
						sBufferdDeptUnit.append("<select name='strRoomCode' class='comboNormal' tabindex='1' >");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("<td colspan='1'  class='LABEL'></td>\n");
						sBufferdDeptUnit.append("<td colspan='1'  class='CONTROL'>");
						sBufferdDeptUnit.append("</td></tr>");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Bed</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<select name='strBedCode' class='comboNormal' tabindex='1' onChange=''>");
						sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
						sBufferdDeptUnit.append("</select></td>\n");
						
						
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("</tr>");
						sBufferdDeptUnit.append("</table>\n");
						
					}
 					//else
					else if((ipdConfigUtil.getStrUnitNameReq().equals("0"))&& (ipdConfigUtil.getStrRoomNoReq().equals("0")))
					{
						/*sBufferdDeptUnit.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Department</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");
						sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
						sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						sBufferdDeptUnit.append("<select name='strDeptCode' class='comboNormal' tabindex='1' onChange='ConsultantDetails()'>");
						sBufferdDeptUnit.append(strDeptValues);
						sBufferdDeptUnit.append("</select></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>" +"Consultant</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id='consId'><select name='strConsultantCode' tabindex='1' class='comboNormal'>");
						sBufferdDeptUnit.append(strConsultantValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</tr>\n");
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'><font color='red'>*</font>Treatment Category</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>\n");
						sBufferdDeptUnit.append("<div id='treatCat'><select name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='wardDetails(this)'>");
						sBufferdDeptUnit.append(strTreatmentCategValues);
						sBufferdDeptUnit.append("</select></div></td>");
						sBufferdDeptUnit.append("<td colspan='1' class='LABEL'><font color='red'>*</font>Ward</td>\n");
						sBufferdDeptUnit.append("<td colspan='1' class='CONTROL'>");
						sBufferdDeptUnit.append("<div id='WardId'>");
						sBufferdDeptUnit.append("<select name='strWardCode' class='comboNormal' tabindex='1' onChange='getAdmissionCharges()'>");
						sBufferdDeptUnit.append(strWardValues);
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</td>");
						sBufferdDeptUnit.append("</tr>\n");	
						sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("</table>\n");
						*/
						
						 

			            
						 sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Department<font color='red'>*</font></label>");
						 sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
						 sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<select class='"+selectClass+"' name='strDeptCode' class='comboNormal' tabindex='1' onChange='ConsultantDetails()'>");
						 sBufferdDeptUnit.append(strDeptValues);
						 sBufferdDeptUnit.append("</select>");
                         sBufferdDeptUnit.append("</div>");
        				 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Consultant<font color='red'>*</font></label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<div id='consId'><select id='inputCons' class='"+selectClass+"' name='strConsultantCode' tabindex='1' onChange='wardonUnit(this);'>");
						 sBufferdDeptUnit.append(strConsultantValues);
						 sBufferdDeptUnit.append("</select></div>");
                         sBufferdDeptUnit.append("</div>");
                         sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Treatment Category<font color='red'>*</font></label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<div id='treatCat'><select  class='"+selectClass+"' name='strTreatmentCategoryCode' class='comboNormal' tabindex='1' onChange='wardDetails(this);getPayMode();'>"); 
					
						
						 sBufferdDeptUnit.append(strTreatmentCategValues);
						 sBufferdDeptUnit.append("</select></div>");
                         sBufferdDeptUnit.append("</div>");
                         sBufferdDeptUnit.append("</div>");

                         
                       
                         sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Ward<font color='red'>*</font></label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<div id='WardId'><select class='"+selectClass+"' name='strWardCode' class='comboNormal' tabindex='1' onChange='getBedFrmNursingDesk()'>");
						 sBufferdDeptUnit.append(strWardValues);
						 sBufferdDeptUnit.append("</select></div>");
                         sBufferdDeptUnit.append("</div>");
                         

                         
                         
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Case Sheet No.</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<input type='text' class='"+textClass+"' tabindex='2'>");
                         sBufferdDeptUnit.append("</div>");
                         
                         
                         
						 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<label>Admission Type/Status</label>");
						 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
						 sBufferdDeptUnit.append("<select class='"+selectClass+"' tabindex='2'><option>Normal</option> <option>Effective</option> <option>Emergency</option> <option selected=''>Normal</option>");
						 sBufferdDeptUnit.append("</select>");
                         sBufferdDeptUnit.append("</div>");
						 sBufferdDeptUnit.append("</div>");
                         
                    
                         

			
	                         
	                         
	                         sBufferdDeptUnit.append("<div class='"+rowClass+"'>\n");
							 sBufferdDeptUnit.append("<div class='"+divClass+"'>");
							 sBufferdDeptUnit.append("<label>Bed</label>");
							 sBufferdDeptUnit.append("<input type='hidden' name='strDeptUnitCode' value='0'/>");
							 sBufferdDeptUnit.append("<input type='hidden' name='strRoomCode' value='"+vo.getStrRoomCode()+"' />");
							 sBufferdDeptUnit.append("</div><div class='"+divClass+"'>");
							 sBufferdDeptUnit.append("<select class='"+selectClass+"' name='strBed' tabindex='2'>");
							 sBufferdDeptUnit.append("<option value='0'>Select Value</option>");
							 sBufferdDeptUnit.append("</select>");
	                         sBufferdDeptUnit.append("</div>");
						
					
                       

						//sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Case Sheet No.</td>\n");
						
					/*	Admission type
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'> Admission Type</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'>");		
						sBufferdDeptUnit.append("<div id=''><select name='strAdmissionTypeCode' tabindex='1' class='comboNormal'>");
						sBufferdDeptUnit.append(strAdmissionTypeValues);	
						sBufferdDeptUnit.append("</select></div></td>\n");
						sBufferdDeptUnit.append("</td>");
						*/
						
						//change the combo
						/*sBufferdDeptUnit.append("<tr>");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL'>Identification</td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='CONTROL'><input type='text' name='strIdMark'  class='txtFldMax' maxlength='150' tabindex='1' onkeypress='return validateData(event,4);'></td>\n");
						sBufferdDeptUnit.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
						sBufferdDeptUnit.append("</tr>");*/
						
					}
				}		
				String readOnly="";
				if(vo.getStrPatIsUnknown().equals("1"))
					readOnly="readonly";
				
				/*sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' value='"+ strHouseNo + "' tabindex='2' class='txtFldNormal' maxlength='20' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Street/Mohallah</td>\n");				
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='1' name='strStreet' value='"+ strStreet + "' class='txtFldMax' maxlength='60'  "+readOnly+"></td>\n");
				sBuffer.append("</tr>\n");*/
				
				
				
				
			/*	sBuffer.append("<td width='25%' class='LABEL'>Location</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text'   name='strCityLocation' value='"+ cityLocation + "' class='txtFldMax' maxlength='60' tabindex='2' onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>City/Village</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text'   name='strCity' value='"+ strCity + "' class='txtFldMax' maxlength='60' tabindex='1' onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
				sBuffer.append("</tr>\n");
               */
				
				/*sBuffer.append("<tr>\n");
				sBuffer.append("<td width='25%' class='LABEL'>Landmark</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTehsil' value='"+ strTehsil + "' class='txtFldMax' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+"></td>\n");
				sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
				//sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"+ strDistrict + "' class='txtFldMax'  maxlength='30'  onkeypress='return validateData(event,4);'></td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='districtTextBoxDiv'><input type='text' name='strDistrictName' value='"+districtName+"' / ></div><div id='districtSelectBoxDiv'>
				<select name='strDistrictCode' id='strDistrictCode' class='comboNormal' onclick='onchangeDistrict();' tabindex='2'>\n");
				sBuffer.append(strDistrict);
				sBuffer.append("</select></div></td>\n");
				sBuffer.append("</tr>");*/
				
				
               
			/*	
				sBuffer.append("<tr>");
				sBuffer.append("<td class='LABEL' ><font color='red'>*</font>State</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;' id='stateTextBoxDiv'><input type='text' name='strStateName' id='strStateId' value='"+strStateName+"'/></div><div id='stateComboDiv'><select name='strState' class='comboNormal' tabindex='1' onchange='onchangeState()'>\n");
				sBuffer.append(strState);
				sBuffer.append("</select></div></td>\n");
				sBuffer.append("<td class='LABEL' ><font color='red'>*</font>Country</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry' class='comboNormal' tabindex='1'  onchange='onchangeCountry()'>\n");					
				sBuffer.append(strCountry);
				sBuffer.append("</select></td>");
				sBuffer.append("</tr>\n");	
			
				sBuffer.append("<script>onchangeCountry();</script>");*/
				
				/*sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Phone No.</td>\n");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='text' tabindex='2' name='strPhoneNo' value='"+ strPhoneNo + "' class='txtFldMax' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+"></td>");
				sBuffer.append("<td width='25%' class='LABEL'>Marital Status</td>");
				sBuffer.append("<td width='25%' class='CONTROL'  ><select name='strMaritalStatus'  class='comboNormal'>\n");
				sBuffer.append(strMaritalStatus);
				sBuffer.append("</select> </td>");
				sBuffer.append("</tr>");
				*/
				/*sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
				sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion'  class='comboNormal'>\n");
				sBuffer.append(strReligion);
				sBuffer.append("</select> </td>");
				sBuffer.append("<td width='25%' class='LABEL'>Patient Caste</td>");
				sBuffer.append("<td width='25%' class='CONTROL'><select name='strPatientCaste'  class='comboNormal'>\n");
				sBuffer.append(strPatientCaste);
				sBuffer.append("</select> </td>");
				sBuffer.append("</tr>");*/
				
				
				
				 sBuffer.append("<div class='"+rowClass+"'>\n");
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>House No.</label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strHouseNo' value='"+ strHouseNo +"' tabindex='2' maxlength='20' "+readOnly+">");
	             sBuffer.append("</div>");
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>Street/Mohallah<font color='red'>*</font></label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strStreet' value='"+ strStreet +"' tabindex='1' maxlength='60'  "+readOnly+">");
	             sBuffer.append("</div>");
	             sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>Location</label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strCityLocation' value='"+ cityLocation +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
	             sBuffer.append("</div>");
				 sBuffer.append("</div>");
				 
				
				 sBuffer.append("<div class='"+rowClass+"'>\n");
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>City/Village<font color='red'>*</font></label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strCity' value='"+ strCity +"' tabindex='1' maxlength='60' onkeypress='return validateData(event,4);' "+readOnly+">");
	             sBuffer.append("</div>");
	             sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>Landmark</label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strTehsil' value='"+ strTehsil +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
	             sBuffer.append("</div>");
	             sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>Pin Code</label>");
				 sBuffer.append("</div><div class='"+divClass+" input-group sm'>");
				 sBuffer.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon3'><i class='fas fa-map-marker-alt'></i></span></div><input aria-describedby='basic-addon3' class='form-control' type='text' name='strPinCode' value='"+ strPinCode + "' maxlength='6' tabindex='2' onkeypress='return validateData(event,5);' "+readOnly+">");
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
				 sBuffer.append("<div id='stateComboDiv'><select name='strState' class='browser-default custom-select' tabindex='1' onchange='onchangeState()' required>");
				 sBuffer.append(strState);
				 sBuffer.append("</select></div>");  
				 sBuffer.append("</div>");
				 
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>District<font color='red'>*</font></label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<div style='display:none;' id='districtTextBoxDiv'><input type='text' class='form-control' tabindex='1' name='strDistrictName' value='"+districtName+"' / ></div>");
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
				 sBuffer.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon2'><i class='fas fa-mobile-alt'></i></span></div><input aria-describedby='basic-addon2' type='text' class='form-control' maxlength='10' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+" required>");
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("</div>");
	             sBuffer.append("</div>");
	                                  
	             sBuffer.append("<script>onchangeCountry();</script>");
	             
	             sBuffer.append("<div class='"+rowClass+"'>\n");
				 sBuffer.append("<div class='"+divClass+"'>");
						 
				 sBuffer.append("<label>Marital Status</label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<select class='browser-default custom-select' tabindex='2' name='strMaritalStatus'  class='comboNormal'>");
				 sBuffer.append(strMaritalStatus);
				 sBuffer.append("</select>");   
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>Religion</label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<select class='browser-default custom-select' tabindex='2' name='strReligion'>");
				 sBuffer.append(strReligion);
				 sBuffer.append("</select>");  
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='"+divClass+"'>");
				 sBuffer.append("<label>Patient Caste</label>");
				 sBuffer.append("</div><div class='"+divClass+"'>");
				 sBuffer.append("<select class='browser-default custom-select' tabindex='2' name='strPatientCaste'>");
				 sBuffer.append(strPatientCaste);
				 sBuffer.append("</select>");                
				 sBuffer.append("</div>");	 
				 sBuffer.append("</div>");
				 
				/* sBuffer.append("<div class='row'>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label><font color='red'>*</font>City/Village</label>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strCity' value='"+ strCity +"' tabindex='1' maxlength='60' onkeypress='return validateData(event,4);' "+readOnly+">");
                 sBuffer.append("</div>");
                 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label>Landmark</label>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strTehsil' value='"+ strTehsil +"' tabindex='2' maxlength='60'  onkeypress='return validateData(event,4);' "+readOnly+">");
                 sBuffer.append("</div>");
                 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label>District</label>");
				 sBuffer.append("<div style='display:none;' id='districtTextBoxDiv'><input type='text' class='"+textClass+"' name='strDistrictName' value='"+districtName+"' / ></div>");
				 sBuffer.append("<div id='districtSelectBoxDiv'>");
				 sBuffer.append("<select name='strDistrictCode' id='strDistrictCode' class='"+selectClass+"' onclick='onchangeDistrict();' tabindex='2'>");
				 sBuffer.append(strDistrict);
				 sBuffer.append("</select>");                
				 sBuffer.append("</div>");
				 sBuffer.append("</div>");
                 sBuffer.append("</div>");
				*/
              
                 
             /*    sBuffer.append("<div class='row'>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label><font color='red'>*</font>State</label>");
				 sBuffer.append("<div style='display:none;' id='stateTextBoxDiv'><input type='text' class='"+textClass+"' name='strStateName' id='strStateId' value='"+strStateName+"'/></div>");
				 sBuffer.append("<div id='stateComboDiv'><select name='strState' class='"+selectClass+"'  onchange='onchangeState()'>");
				 sBuffer.append(strState);
				 sBuffer.append("</select></div>");  
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label><font color='red'>*</font>Country</label>");
				 sBuffer.append("<input type='hidden' name='strCountryName' value=''/>");
				 sBuffer.append("<select name='strCountry' tabindex='1' class='"+selectClass+"' onchange='onchangeCountry()'>");
				 sBuffer.append(strCountry);
				 sBuffer.append("</select>");
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label>Pin Code</label>");
				 sBuffer.append("<input type='text' class='"+textClass+"' type='text' name='strPinCode' value='"+ strPinCode + "' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+">");
				 sBuffer.append("</div>");
                 sBuffer.append("</div>");
                 
                 
                 
                 sBuffer.append("<div class='row'>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("<label>Phone No.</label>");
				 sBuffer.append("<input type='text' class='"+textClass+"' tabindex='2' name='strPhoneNo' value='"+ strPhoneNo +"' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+">");               
				 sBuffer.append("</div>");
                 sBuffer.append("<div class='col-sm-4' >");
                 sBuffer.append("<label id='ind'><font color='red'>*</font>Mobile No</label>");
				 sBuffer.append("<input type='text' class='"+textClass+"' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+">");
				 sBuffer.append("</div>");
				 sBuffer.append("<div class='col-sm-4'>");
				 sBuffer.append("</div>");
                 sBuffer.append("</div>");*/
				
				
				
				
				
				
				//elzss
				if(vo.getStrAdmissionChargeAtCounter().equals("1"))
				{
					sBuffer.append("</tr>");
					sBuffer.append("<td width='25%' class='LABEL'>Admission Charge</td>");
					sBuffer.append("<td width='25%' class='CONTROL'>");
				
				if(ipdConfigUtil.getStrAdmissionOnline().equals("1"))
				{
					if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
					sBuffer.append("Charges Not Defined For Patient category");
					else
					sBuffer.append(strAdmCharge+" <img src='/HBIMS/hisglobal/images/INR.png'>");	
					sBuffer.append("</td>");
				}
				else
				{
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
				sBuffer.append(" <input type='text'  name='strAdmcharge' id='strAdmcharge' value='0' disabled='true'>" +" "+"<img src='/HBIMS/hisglobal/images/INR.png' >");	
				else
				sBuffer.append(" <input type='text'  name='strAdmcharge'  id ='strAdmcharge' value="+strAdmCharge+" disabled='true'>" + " "+ "<img src='/HBIMS/hisglobal/images/INR.png'>");	
				sBuffer.append("</td>");
				}
				
				
				sBuffer.append("<td width='25%' class='LABEL'></td>");
				sBuffer.append("<td width='25%' class='CONTROL'>");
				sBuffer.append("</td>");
				if(strAdmCharge == null || strAdmCharge.equals("") || strAdmCharge.equals("NA"))
				sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='0'/>");
				else
				sBuffer.append("<input type='hidden' name='strAdmissionChargeValue' value='"+strAdmCharge+"'/>");
				
				sBuffer.append("<tr>");
				}
				
				
				
				
				
				if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
				   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
				{
					sBuffer.append("<tr style='display:none;'>\n");
					sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' tabindex='2' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+ strMobileNo + "' class='txtFldMax' tabindex='1' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("</tr>\n");
				}
				else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1") &&
						   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0"))
				{
							sBuffer.append("<tr>\n");
							sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
							sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
							sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'>Pin Code:</div></td>\n");
							sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' tabindex='2' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
							sBuffer.append("</tr>\n");
				}
				else if(resourceBundle.getString("MOBILE_NO_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("0") &&
						   resourceBundle.getString("PINCODE_AT_NEWADMISSION_MODIFICATION_REQUIRED").equals("1"))
				{
							sBuffer.append("<tr>\n");
							sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
							sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' tabindex='2' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
							sBuffer.append("<td width='25%' class='LABEL'><div style='display:none;'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></div></td>\n");				
							sBuffer.append("<td width='25%' class='CONTROL'><div style='display:none;'><input type='text' name='strMobileNo'  tabindex='1' value='"+ strMobileNo + "' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></div></td>\n");
							sBuffer.append("</tr>\n");
				}
				else
				{
					/*sBuffer.append("<tr>\n");
					sBuffer.append("<td width='25%' class='LABEL'>Pin Code:</td>\n");
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Mobile No:<label id='ind' style='display:none;'>(+91)</label></td>\n");				
					sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"+ strMobileNo + "' tabindex='1' class='txtFldMax' maxlength='10' onkeypress='return validateData(event,5);' "+readOnly+"></td>\n");
					sBuffer.append("</tr>\n");*/
					
					
					 /*sBuffer.append("<div class='row'>");
					 sBuffer.append("<div class='col-sm-4'>");
					 sBuffer.append("<label>Marital Status</label>");
					 sBuffer.append("<select class='"+selectClass+"' name='strMaritalStatus'  class='comboNormal'>");
					 sBuffer.append(strMaritalStatus);
					 sBuffer.append("</select>");   
					 sBuffer.append("</div>");
					 sBuffer.append("<div class='col-sm-4'>");
					 sBuffer.append("<label>Religion</label>");
					 sBuffer.append("<select class='"+selectClass+"' name='strReligion'>");
					 sBuffer.append(strReligion);
					 sBuffer.append("</select>");  
					 sBuffer.append("</div>");
					 sBuffer.append("<div class='col-sm-4'>");
					 sBuffer.append("<label>Patient Caste</label>");
					 sBuffer.append("<select class='"+selectClass+"' name='strPatientCaste'>");
					 sBuffer.append(strPatientCaste);
					 sBuffer.append("</select>");                
					 sBuffer.append("</div>");	 
					 sBuffer.append("</div>");
*/				} 
				
				sBuffer.append("</tr></table>\n");
				if(vo.getStrIsAdmissionOnline().equals("1"))
				{
					sBufferdDeptUnit.append("<input type='hidden' name='strDeptCode' value='"+vo.getStrDeptCode()+"'>");
					sBufferdDeptUnit.append("<input type='hidden' name='strConsultantCode' value='"+vo.getStrConsultantCode()+"'>");
					sBufferdDeptUnit.append("<input type='hidden' name='strTreatmentCategoryCode' value='"+vo.getStrTreatmentCategoryCode()+"'>");
					sBufferdDeptUnit.append("<input type='hidden' name='strWardCode' value='"+vo.getStrWardCode()+"'/>");
					sBufferdDeptUnit.append("<input type='hidden' name='strWardName' value='"+vo.getStrWardName()+"'/>");
					sBufferdDeptUnit.append("<input type='hidden' name='strWard' value='"+vo.getStrWardName()+"'/>");
				}
				
				/*sBufferEmgAddress.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'>I Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+"/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strFirstPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' name='strEmgAddress1' maxlength='150' "+readOnly+"></textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL'><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strFirstpersonphone' maxlength='13' onkeypress='return validateData(event,5);' "+readOnly+"/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");
				
				sBufferEmgAddress.append("<tr>");
				sBufferEmgAddress.append("<td width='20%' class='LABEL'>II Person Name</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+"/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Relation</td>\n");
				sBufferEmgAddress.append("<td width='15%' class='CONTROL'>");
				sBufferEmgAddress.append("<select name='strSecondPersonRelationCode' class='comboNormal' tabindex='1' >");
				sBufferEmgAddress.append(relationDtls);
				sBufferEmgAddress.append("</select></td>\n");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='15%' class='LABEL'><font color='red'>**</font>Address</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<textarea   cols='9' rows='1' name='strEmgAddress2' maxlength='150' "+readOnly+"></textarea>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("<td width='10%' class='LABEL'><font color='red'>**</font>Phone</td>\n");
				sBufferEmgAddress.append("<td width='10%' class='CONTROL'>");
				sBufferEmgAddress.append("<input type='text' name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
				sBufferEmgAddress.append("</td>");
				sBufferEmgAddress.append("</tr>");*/
				
				
				// sBufferEmgAddress.append("<div class='container' style='line-height: 0.5; max-width: 1173px;'>\n");
	            /* sBufferEmgAddress.append("<div class='row justify-content-center'>\n");
	             sBufferEmgAddress.append("<div class='col-md-12'>\n");
	             sBufferEmgAddress.append("<div class='card'>\n");
	             sBufferEmgAddress.append("<article class='card-body'>\n");*/
	             //sBufferEmgAddress.append("<form>\n");
	             
				sBufferEmgAddress.append("<div class='"+rowClass+"'>\n");
				 sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label>Person Name(I)</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strFirstPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+">");
                sBufferEmgAddress.append("</div>");
                sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Phone</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+" input-group'>");
				 sBufferEmgAddress.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'><i class='fas fa-phone-alt'></i></span></div>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strFirstpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
                sBufferEmgAddress.append("</div>");
                sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Relation</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<select class='browser-default custom-select' name='strFirstPersonRelationCode' tabindex='1'>");
				 sBufferEmgAddress.append(relationDtls);
				 sBufferEmgAddress.append("</select>");
                sBufferEmgAddress.append("</div>");      
                sBufferEmgAddress.append("</div>"); 
                
                
                sBufferEmgAddress.append("<div class='"+rowClass+"'>\n");
                sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label>Person Name(II)</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strSecondPersonName' maxlength='25' onkeypress='return validateData(event,4);' "+readOnly+">");
                sBufferEmgAddress.append("</div>");
                sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Phone</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+" input-group'>");
				 sBufferEmgAddress.append("<div class='input-group-prepend'><span class='input-group-text' id='basic-addon1'><i class='fas fa-phone-alt'></i></span></div>");
				 sBufferEmgAddress.append("<input type='text' class='form-control' name='strSecondpersonphone' maxlength='13' onkeypress='return validateData(event,5);'/>");
                sBufferEmgAddress.append("</div>");
                sBufferEmgAddress.append("<div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<label><font color='red'>**</font>Relation</label>");
				 sBufferEmgAddress.append("</div><div class='"+divClassSm2+"'>");
				 sBufferEmgAddress.append("<select class='browser-default custom-select' name='strSecondPersonRelationCode' tabindex='1'>");
				 sBufferEmgAddress.append(relationDtls);
				 sBufferEmgAddress.append("</select>");
                sBufferEmgAddress.append("</div>");
                
      
                 
                 //sBufferEmgAddress.append("</form>\n");
				 /*sBufferEmgAddress.append("</article>\n");
				 sBufferEmgAddress.append("</div>\n");
	             sBufferEmgAddress.append("</div>\n");
	             sBufferEmgAddress.append("</div>\n");*/
	            // sBufferEmgAddress.append("</div>\n");

				
				/*sBufferEmgAddress.append("</tbody>");
				sBufferEmgAddress.append("</table>");*/
                
			     
			     //sBuffer.append("</form>\n");
				

				




		}
		catch(Exception e)
		{
			e.printStackTrace();
			vo.setStrMsgString("PatientAdmissionTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString()+"^"+sBufferdDeptUnit+"^"+sBufferEmgAddress;
	}
	/**
	 * This function is used to generate html(New Modification for address) at runtime.
	 * @param vo
	 * @return string
	 * changes done on 27-04-2011 to show hide address details from adt_mandatory_info.properties file
	 */
	
}
