package ipd.transactions;



import javax.sql.rowset.WebRowSet;

public class  PatientAdmissionCancellationTransHLP {
/**
 * This function used to develop grid on bed details pop up window that contains bed details with their respective status
 * @param vo
 * @return
 */
	public static String getBedDetails(NewBornBabyTransVO vo)
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
			vo.setStrMsgString("PatientAdmissionCancellationTransHLP.getBedDetails() --> "
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
	public static String getPatientDetailModi(PatientAdmissionCancellationTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		PatientAdmissionCancellationTransBO bo=new PatientAdmissionCancellationTransBO();
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName();
	//	String strReligion="";
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		String strPhoneNo=vo.getStrPhoneNo();
		String strMobileNo=vo.getStrMobileNo();
		String strCity=vo.getStrCity();
	//	String strState=vo.getStrState();
		String strDistrict=vo.getStrDistrict();
	//	String strCountry="";
		String strPinCode=vo.getStrPinCode();
		//String strPatientName=vo.getStrPatientName();
	//	String strMotherTreatmentCateg="";
		String strSpouseName=vo.getStrSpouseName().trim();
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		
	//	HisUtil util = null;
		try
		{
			/*util = new HisUtil("Patient Admission Cancellation Transaction",
			"PatientAdmissionCancellationTransHLP");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),
					"0^Select Value", false);
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),
					"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),
					"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),
					"0^Select Value", false);*/
			sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Father Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
							+strFatherName+  "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='1'>Spouse name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
					+strSpouseName+  "</td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append(vo.getStrTreatmentCategoryName());
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append(vo.getStrReligoinName());
			sBuffer.append("</td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
							+ strHouseNo + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Street</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'>"
						+ strStreet + "</td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>City</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
							+ strCity + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'>"
						+ strDistrict + "</td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td class='LABEL'>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>\n");
			sBuffer.append(vo.getStrStateName());
			sBuffer.append("</td>\n");
			sBuffer.append("<td class='LABEL'>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>\n");
			sBuffer.append(vo.getStrCountryName());
			sBuffer.append("</td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			
			sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
			+ strPinCode + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mobile No:</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'>"
			+ strMobileNo + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
			+ strPhoneNo + "</td>\n");
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
			
			vo.setStrMsgString("PatientAdmissionCancellationTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
	
	public static String getPatientDetailModi_Bootstrap(PatientAdmissionCancellationTransVO vo)
	{
		StringBuffer sBuffer = new StringBuffer("");
		PatientAdmissionCancellationTransBO bo=new PatientAdmissionCancellationTransBO();
		bo.setPatientAddModi(vo);
		String strFatherName=vo.getStrFatherName();
	//	String strReligion="";
		String strHouseNo=vo.getStrHouseNo();
		String strStreet=vo.getStrStreet();
		String strPhoneNo=vo.getStrPhoneNo();
		String strMobileNo=vo.getStrMobileNo();
		String strCity=vo.getStrCity();
	//	String strState=vo.getStrState();
		String strDistrict=vo.getStrDistrict();
	//	String strCountry="";
		String strPinCode=vo.getStrPinCode();
		//String strPatientName=vo.getStrPatientName();
	//	String strMotherTreatmentCateg="";
		String strSpouseName=vo.getStrSpouseName().trim();
		if(strPinCode.equals("0"))
		{
			strPinCode="";
		}
		
	//	HisUtil util = null;
		try
		{
			/*util = new HisUtil("Patient Admission Cancellation Transaction",
			"PatientAdmissionCancellationTransHLP");
			strReligion=util.getOptionValue(vo.getReligionWs(),vo.getStrReligionCode(),
					"0^Select Value", false);
			strState=util.getOptionValue(vo.getStateWS(),  vo.getStrStateCode(),
					"0^Select Value", false);
			strCountry=util.getOptionValue(vo.getCountryWS(),  vo.getStrCountryCode(),
					"0^Select Value", false);
			strMotherTreatmentCateg = util.getOptionValue(vo.getTreatmentCategWS(),vo.getStrTreatmentCategoryCode(),
					"0^Select Value", false);*/
			
			
			/*sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Father Name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
							+strFatherName+  "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='1'>Spouse name</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
					+strSpouseName+  "</td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Treatment Category</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append(vo.getStrTreatmentCategoryName());
			sBuffer.append("</td>");
			sBuffer.append("<td width='25%' class='LABEL'>Religion</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>");
			sBuffer.append(vo.getStrReligoinName());
			sBuffer.append("</td>\n");
			sBuffer.append("</tr>");
			sBuffer.append("<tr>");
			sBuffer.append("<td width='25%' class='LABEL'>House No.</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
							+ strHouseNo + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Street</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'>"
						+ strStreet + "</td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>City</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
							+ strCity + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>District</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'>"
						+ strDistrict + "</td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td class='LABEL'>State</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>\n");
			sBuffer.append(vo.getStrStateName());
			sBuffer.append("</td>\n");
			sBuffer.append("<td class='LABEL'>Country</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>\n");
			sBuffer.append(vo.getStrCountryName());
			sBuffer.append("</td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			
			sBuffer.append("<td width='25%' class='LABEL'>Pincode:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
			+ strPinCode + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL' colspan='2'></td>\n");
			sBuffer.append("</tr>\n");
			sBuffer.append("<tr>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Mobile No:</td>\n");				
			sBuffer.append("<td width='25%' class='CONTROL'>"
			+ strMobileNo + "</td>\n");
			sBuffer.append("<td width='25%' class='LABEL'>Phone No:</td>\n");
			sBuffer.append("<td width='25%' class='CONTROL'>"
			+ strPhoneNo + "</td>\n");
			sBuffer.append("<tr>");
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
			sBuffer.append("</select> </td>");
			sBuffer.append("</tr></table>\n");*/
			
		
			 /*sBuffer.append("<div class='row'>");
			 sBuffer.append("<div class='col-sm-2' align='Right'>");
			 sBuffer.append("<div><b>Father Name</b></div>");
			 sBuffer.append("<div><b>Religion</b></div>");
			 sBuffer.append("<div><b>City</b></div>");
			 sBuffer.append("<div><b>Country</b></div>");
			 sBuffer.append("<div><b>Mobile No</b></div>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='col-sm-2' align='left' >");
			 sBuffer.append("<div>"+strFatherName+"</div>");
			 sBuffer.append("<div>"+vo.getStrReligoinName()+"</div>");
			 sBuffer.append("<div>"+strCity+"</div>");
			 sBuffer.append("<div>"+vo.getStrCountryName()+"</div>");
			 sBuffer.append("<div>"+strMobileNo+"</div>");
			 sBuffer.append("</div>");
			 
			 sBuffer.append("<div class='col-sm-2' align='Right'>");
			 sBuffer.append("<div><b>Spouse name</b></div>");
			 sBuffer.append("<div><b>House No</b></div>");
			 sBuffer.append("<div><b>District</b></div>");
			 sBuffer.append("<div><b>Pincode</b></div>");
			 sBuffer.append("<div><b>Phone No</b></div>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='col-sm-2' align='left'>");	
			 sBuffer.append("<div>"+strSpouseName+"</div>");
			 sBuffer.append("<div>"+strHouseNo+"</div>");
			 sBuffer.append("<div>"+strDistrict+"</div>");
			 sBuffer.append("<div>"+strPinCode+"</div>");
			 sBuffer.append("<div>"+strPhoneNo+"</div>");
			 sBuffer.append("</div>");	
			 
			 sBuffer.append("<div class='col-sm-2' align='Right'>");	
			 sBuffer.append("<div><b>Treatment Category</b></div>");
			 sBuffer.append("<div><b>Street</b></div>");
			 sBuffer.append("<div><b>State</b></div>");
			 sBuffer.append("<div></div>");
			 sBuffer.append("<div></div>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='col-sm-2' align='left' >");	
			 sBuffer.append("<div>"+vo.getStrTreatmentCategoryName()+"</div>");
			 sBuffer.append("<div>"+strStreet+"</div>");
			 sBuffer.append("<div>"+vo.getStrStateName()+"</div>");
			 sBuffer.append("<div></div>");
			 sBuffer.append("<div></div>");
			 sBuffer.append("</div>");	
			 sBuffer.append("</div>");
			*/
			
			/*sBuffer.append("<div class='row justify-content-center'>");
			sBuffer.append("<div class='col-md-11'>");
			sBuffer.append("<div class='card'>");*/
			String divClass="col-sm-2";
			String divClassSm1="col-sm-1";
			String divClassSm2="col-sm-2";
			String rowClass="row rowFlex reFlex";
			 
			sBuffer.append("<br>");
			 sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Father Name</label>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append(strFatherName);
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Spouse name</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(strSpouseName);
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Treatment Category</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(vo.getStrTreatmentCategoryName());
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             
             sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Religion</label>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append(vo.getStrReligoinName());
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>House No</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(strHouseNo);
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Street</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(strStreet);
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             
             sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>City</label>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append(strCity);
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>District</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(strDistrict);
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>State</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(vo.getStrStateName());
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             
             sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Country</label>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append(vo.getStrCountryName());
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Pincode</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(strPinCode);
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             
             sBuffer.append("<div class='"+rowClass+"'>\n");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Mobile No</label>");
			 sBuffer.append("</div>");
			 sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append(strMobileNo);
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
			 sBuffer.append("<label>Phone No</label>");
             sBuffer.append("</div>");
             sBuffer.append("<div class='"+divClass+"'>");
 			 sBuffer.append(strPhoneNo);
             sBuffer.append("</div>");
             sBuffer.append("</div>");
             
	       /* sBuffer.append("<div class='row'>");
			sBuffer.append("<div class='col-xs-12 col-sm-4'>");
			sBuffer.append("<div class='profile-user-info'>");
			sBuffer.append("<br>");

			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Father Name</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+strFatherName+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Religion</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+vo.getStrReligoinName()+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");

			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>City</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+strCity+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Country</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			 sBuffer.append("<div>"+vo.getStrCountryName()+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Mobile No</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			 sBuffer.append("<div>"+strMobileNo+"</div>");
			 sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-xs-12 col-sm-4'>");
			sBuffer.append("<div class='profile-user-info'>");
			sBuffer.append("<br>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Spouse name</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+strSpouseName+"</div>");
			
			 sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>House No</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+strHouseNo+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");

			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>District</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+strDistrict+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Pincode</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+strPinCode+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Phone No</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			 sBuffer.append("<div>"+strPhoneNo+"</div>");		
			 sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='col-xs-12 col-sm-4'>");
			sBuffer.append("<div class='profile-user-info'>");
			sBuffer.append("<br>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Treatment Category</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("<div>"+vo.getStrTreatmentCategoryName()+"</div>");
			 sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>Street</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			 sBuffer.append("<div>"+strStreet+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");

			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'><b>State</b></div>");
			sBuffer.append("<div class='profile-info-value'>");
			 sBuffer.append("<div>"+vo.getStrStateName()+"</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");*/
			
			/*sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'></div>");
			sBuffer.append("<div class='profile-info-value'>");
			sBuffer.append("</div>");
			sBuffer.append("</div>");
			
			sBuffer.append("<div class='profile-info-row'>");
			sBuffer.append("<div class='profile-info-name'></div>");
			sBuffer.append("<div class='profile-info-value'>");
			 sBuffer.append("</div>");
			sBuffer.append("</div>");
			*/
			/*sBuffer.append("</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div");
			*/
			
			/*sBuffer.append("</div>");
			sBuffer.append("</div>");
			sBuffer.append("</div");*/
		}
		catch(Exception e)
		{
			
			vo.setStrMsgString("PatientAdmissionCancellationTransHLP.getPatientDetailModi() --> "+e.getMessage());
			vo.setStrMsgType("1");
		}
				return sBuffer.toString();
	}
}
