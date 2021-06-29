package ipd.transactions;



import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

public class  SlipReprintTransHLP {
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
		PatientAdmissionModificationTransBO bo=new PatientAdmissionModificationTransBO();
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
			sBuffer.append("<td width='25%' class='LABEL'><font color='red' size='1px'>*</font>Father Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strFatherName' value='"
							+strFatherName+  "' class='txtFldMax' nowrap='nowrap' onkeypress='return validateData(event,4);' maxlength='30'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Spouse Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strSpouseName' value='"
					+strSpouseName+  "' class='txtFldMax' nowrap='nowrap' onkeypress='return validateData(event,4);' maxlength='60'></td>\n");
		
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strMotherTreatmentCateg' class='comboNormal'>");
			sBuffer.append(strMotherTreatmentCateg);
			sBuffer.append("</select></td>");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strReligion' class='comboNormal'>\n");
			sBuffer.append(strReligion);
			sBuffer.append("</select> </td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strHouseNo' value='"
							+ strHouseNo + "' class='txtFldNormal' maxlength='15'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Street</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strStreet' value='"
						+ strStreet + "' class='txtFldMax' maxlength='30'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>City</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strCity' value='"
							+ strCity + "' class='txtFldMax' maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strDistrict' value='"
						+ strDistrict + "' class='txtFldMax'  maxlength='30' onkeypress='return validateData(event,4);'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strStateName' value=''/><select name='strState" +
			"' class='comboNormal' onchange='onchangeState()'>\n");
			sBuffer.append(strState);
			sBuffer.append("</select></td>\n");
			sBuffer.append("<td class='LABEL'><font color='red'>*</font>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='hidden' name='strCountryName' value=''/><select name='strCountry" +
			"' class='comboNormal' onchange='onchangeCountry()'>\n");
			sBuffer.append(strCountry);
			sBuffer.append("</select></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<script>onchangeState();onchangeCountry();</script>");
			sBuffer.append("<tr>\n");
			
			sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPinCode' value='"
			+ strPinCode + "' class='txtFldNormal' maxlength='6' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mobile No:</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strMobileNo' value='"
			+ strMobileNo + "' class='txtFldMax' maxlength='12' onkeypress='return validateData(event,5);'></td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strPhoneNo' value='"
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
}
