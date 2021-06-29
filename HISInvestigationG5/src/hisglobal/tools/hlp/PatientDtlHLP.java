package hisglobal.tools.hlp;

import javax.sql.rowset.WebRowSet;

public class PatientDtlHLP {

	public static String patientDtl(String strCrNo, boolean fAddress)
			throws Exception {
		GlobalVO voObj = new GlobalVO();
		PatientDtlBO boObj = new PatientDtlBO();
		StringBuffer sb = new StringBuffer("");
		voObj.setStrValue1(strCrNo);
		String temp[] = null;
		String strPatientStatus = " ";
		WebRowSet ws = null;

		sb.append("");

		try {
			boObj.getPatientDetails(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			ws = voObj.getGblWs1();
			if (ws != null && ws.size() > 0) {
				if (ws.next()) {
					String strAgeAndSex = ws.getString(2);
					String strPatientName = ws.getString(3);
					String strFatherOrHusbandName = ws.getString(4);
					String strSpouseName = ws.getString(5);
					String strReligion = ws.getString(6);
					String strCategoryName = ws.getString(7);
					String strCategoryCode = ws.getString(8);
					String strAddress = ws.getString(9);
					String strMlcNo = ws.getString(10);
					String strHiddenValue = ws.getString(11); // Is MLC or Not
																// at 4th
																// Position
					String[] id = strHiddenValue.split("\\^");

					if (strAgeAndSex == null)
						strAgeAndSex = "----";
					if (strPatientName == null)
						strPatientName = "----";
					if (strFatherOrHusbandName == null)
						strFatherOrHusbandName = "----";
					if (strSpouseName == null)
						strSpouseName = "----";
					if (strReligion == null)
						strReligion = "----";
					if (strCategoryName == null)
						strCategoryName = "----";
					if (strCategoryCode == null)
						strCategoryCode = "----";
					if (strAddress == null)
						strAddress = "----";
					if (strHiddenValue == null)
						strHiddenValue = "----";
					if (strMlcNo == null)
						strMlcNo = "-----";
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");

					sb.append("<tr><td width='25%' class='LABEL'>Name</td>");
					sb.append("<td colspan='3' width='75%' class='CONTROL'> ");
					sb.append(strPatientName);
					sb.append("</td></tr>");
					sb
							.append("<tr><td width='25%' class='LABEL'>Patient Category</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strCategoryName);
					sb
							.append("<input type='hidden' name='strCatgoryCode' value='"
									+ strCategoryCode + "'></td>");
					sb.append("<td width='25%' class='LABEL'>Age/Gender</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strAgeAndSex);
					sb.append("</td></tr>");
					sb
							.append("<tr><td width='25%' class='LABEL'>Father Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strFatherOrHusbandName);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>Spouse Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strSpouseName);
					sb.append("</td>");
					sb.append("</tr>");
					if (id[4].equals("1")) {
						sb.append("<tr>");
						sb.append("<td width='25%' class='LABEL'>MLC NO</td>");
						sb
								.append("<td colspan='3' width='75%' class='CONTROL'>");
						sb.append("<font color='red'>");
						sb.append(strMlcNo);
						sb.append("</font>");
						sb.append("</td></tr>");
					}
					sb.append("<tr><td>");
					sb
							.append("<input type='hidden' name='strPatientDtlHidVal' value='"
									+ strHiddenValue + "'></td></tr>");
					temp = strHiddenValue.replace("^", "#").split("#");
					strPatientStatus = temp[2];
					sb.append("</td></tr>");
					sb
							.append("<input type='hidden' name='strPatientStatus' id='strPatientStatus' value='"
									+ strPatientStatus + "'></td></tr>");

					if (fAddress) {
						sb.append("<tr>");
						sb.append("<td width='25%' class='LABEL'>Address</td>");
						sb
								.append("<td colspan='3' width='75%' class='CONTROL'>");
						sb.append(strAddress);
						sb.append("</td></tr>");
					}

					sb.append("</table>");
				}
			} else {

				throw new Exception("Invalid CR. No.");

			}
		} catch (Exception e) {
			throw new Exception("PatientDtlHLP-->patientDtl-->"
					+ e.getMessage());
		} finally {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}

		// sb.append("#"+strPatientStatus+"#");
		return sb.toString();
	}

	private static String compactPatientDtl(String strCrNo, boolean fAddress)
			throws Exception {
		WebRowSet ws = null;
		GlobalVO voObj = new GlobalVO();
		PatientDtlBO boObj = new PatientDtlBO();
		StringBuffer sb = new StringBuffer("");

		voObj.setStrValue1(strCrNo);
		sb.append("");

		try {
			boObj.getPatientDetails(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			ws = voObj.getGblWs1();

			if (ws != null && ws.size() > 0) {
				if (ws.next()) {
					String strAgeAndSex = ws.getString(2);
					String strPatientName = ws.getString(3);
					String strFatherOrHusbandName = ws.getString(4);
					String strSpouseName = ws.getString(5);
					String strReligion = ws.getString(6);
					String strCategoryName = ws.getString(7);
					String strCategoryCode = ws.getString(8);
					String strAddress = ws.getString(9);
					String strMlcNo = ws.getString(10);
					String strHiddenValue = ws.getString(11); // Is MLC or Not
																// at 4th
																// Position
					String[] id = strHiddenValue.split("\\^");
					
					String strPatStatus = "";

					if (id[2].equals("11"))
						strPatStatus = "Admitted";
					if (id[2].equals("12"))
						strPatStatus = "OPD";
					if (id[2].equals("13"))
						strPatStatus = "Dead";

					String strAgeInMonths = ws.getString(12);
					
					if (strAgeAndSex == null)
						strAgeAndSex = "----";
					if (strPatientName == null)
						strPatientName = "----";
					if (strFatherOrHusbandName == null)
						strFatherOrHusbandName = "----";
					if (strSpouseName == null)
						strSpouseName = "----";
					if (strReligion == null)
						strReligion = "----";
					if (strCategoryName == null)
						strCategoryName = "----";
					if (strCategoryCode == null)
						strCategoryCode = "----";
					if (strAddress == null)
						strAddress = "----";
					if (strHiddenValue == null)
						strHiddenValue = "----";
					if (strMlcNo == null)
						strMlcNo = "-----";

					sb.append("<script type='text/javascript'>  ");
					sb.append(" function showPatDetails(divId){  ");
					sb
							.append(" document.getElementById(divId).style.display='block';  ");
					sb
							.append(" document.getElementById('minusId'+divId).style.display='block';  ");
					sb
							.append(" document.getElementById('plusId'+divId).style.display='none';	}  ");

					sb.append("  function hidePatDetails(divId){  ");
					sb
							.append(" document.getElementById(divId).style.display='none';  ");
					sb
							.append("	document.getElementById('minusId'+divId).style.display='none';  ");
					sb
							.append(" document.getElementById('plusId'+divId).style.display='block'; }  ");
					sb.append("	</script>  ");

					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'> <tr><td>");
					sb
							.append("<table width='100%' align='center' cellpadding='0' cellspacing='0'> ");
					sb.append("<tr class='HEADER'> ");
					sb.append("<td width='20'> ");
					sb
							.append("<div id='plusIdonLineGblPatId' style='display:block'> ");
					sb
							.append("<img src='../../hisglobal/images/plus.gif'  name='plusonLine' style='cursor:hand;cursor:pointer' align='middle' onclick='showPatDetails(\"onLineGblPatId\");' /></div> ");
					sb
							.append("<div id='minusIdonLineGblPatId' style='display:none'><img src='../../hisglobal/images/minus.gif' style='cursor:hand;cursor:pointer' name='minusonLine' onclick='hidePatDetails(\"onLineGblPatId\");'></div> ");
					sb.append("</td> ");
					sb.append("<td colspan='2'> &nbsp;Patient Name : ");
					sb.append(strPatientName);
					sb.append(" </td> ");
					sb.append(" <td > <div align='right'>");
					sb.append("Status : " + strPatStatus + "&nbsp;");
					sb.append(" </div> </td> ");
					sb.append("</tr> ");
					sb.append("</table> ");

					sb.append(" </td> ");
					sb.append("</tr> ");
					sb.append("</table> ");

					sb
							.append("<div id='onLineGblPatId' style='display: none'>");
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					sb
							.append("<tr><td width='25%' class='LABEL'>Patient Category</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strCategoryName);
					sb
							.append("<input type='hidden' name='strCatgoryCode' value='"
									+ strCategoryCode + "'><input type='hidden' name='strAgeInMonths' value='"
									+ strAgeInMonths + "'></td>");
					sb.append("<td width='25%' class='LABEL'>Age/Gender</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strAgeAndSex);
					sb.append("</td></tr>");
					sb
							.append("<tr><td width='25%' class='LABEL'>Father Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strFatherOrHusbandName);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>Spouse Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strSpouseName);
					sb.append("</td>");
					sb.append("</tr>");
				
					if (id[4].equals("1")) {
						sb.append("<tr>");
						sb.append("<td width='25%' class='LABEL'>MLC NO</td>");
						sb
								.append("<td colspan='3' width='75%' class='CONTROL'>");
						sb.append("<font color='red'>");
						sb.append(strMlcNo);
						sb.append("</font>");
						sb.append("</td></tr>");
					}

					sb
							.append("<input type='hidden' name='strPatientDtlHidVal' value='"
									+ strHiddenValue + "'></td></tr>");
					if (fAddress) {
						sb.append("<tr>");
						sb.append("<td width='25%' class='LABEL'>Address</td>");
						sb
								.append("<td colspan='3' width='75%' class='CONTROL'>");
						sb.append(strAddress);
						sb.append("</td></tr>");
					}

					sb.append("</table>");
				}
			}else{
				
				throw new Exception("Invalid CR. No.");
				
			}
		} catch (Exception e) {
			throw new Exception("PatientDtlHLP-->compactPatientDtl-->"
					+ e.getMessage());
		} finally {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}

		return sb.toString();
	}

	public static String admissionDtl(String strCrNo, String strHospitalCode)
			throws Exception {

		WebRowSet ws = null;
		GlobalVO voObj = new GlobalVO();
		PatientDtlBO boObj = new PatientDtlBO();
		StringBuffer sb = new StringBuffer("");

		voObj.setStrValue1(strCrNo);
		voObj.setStrValue2(strHospitalCode);
		sb.append(" ");

		try {
			boObj.getAdmissionDetails(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			ws = voObj.getGblWs1();
			if (ws != null && ws.size() > 0) {
				if (ws.next()) {

					String strDeptName = ws.getString(1);
					String strUnitName = ws.getString(2);
					String strWardName = ws.getString(3);
					String strRoomName = ws.getString(4);
					String strBedName = ws.getString(5);
					String strTreatmentCategoryName = ws.getString(6);
					String strConsultantName = ws.getString(7);
					String strNewBornBabyFlag = ws.getString(8);
					String strHiddenValue = ws.getString(9);

					String strTemp[] = strHiddenValue.replace("^", "#").split(
							"#");

					String strAdmissionNo = strTemp[0];
					String strEpisodeCode = strTemp[1];
					String strAdmissionDate = strTemp[3];
					String strMotherAdmNo = strTemp[11];
					String strMLCNo = strTemp[12];

					if (strDeptName == null)
						strDeptName = "";
					if (strUnitName == null)
						strUnitName = "";
					if (strWardName == null)
						strWardName = "";
					if (strRoomName == null)
						strRoomName = "";
					if (strBedName == null)
						strBedName = "";
					if (strTreatmentCategoryName == null)
						strTreatmentCategoryName = "";
					if (strConsultantName == null)
						strConsultantName = "";
					if (strNewBornBabyFlag == null)
						strNewBornBabyFlag = "";
					if (strHiddenValue == null)
						strHiddenValue = "";
					if (strAdmissionNo == null)
						strAdmissionNo = "";
					if (strEpisodeCode == null)
						strEpisodeCode = "";
					if (strAdmissionDate == null)
						strAdmissionDate = "";
					if (strMotherAdmNo == null)
						strMotherAdmNo = "";
					if (strMLCNo == null)
						strMLCNo = "";
					sb
							.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
					sb.append("<tr><td width='25%' class='LABEL'>");
					sb.append("Adm No");
					sb.append("</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strAdmissionNo);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>");
					sb.append("Adm Date/Time");
					sb.append("</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strAdmissionDate);
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td width='25%' class='LABEL'>");
					sb.append("Dept / Unit");
					sb.append("</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strDeptName).append(" / ").append(strUnitName);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>");
					sb.append("Ward / Room / Bed");
					sb.append("</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strWardName).append(" / ").append(strRoomName)
							.append(" / ").append(strBedName);
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td width='25%' class='LABEL'>");
					sb.append("Treatment Category");
					sb.append("</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strTreatmentCategoryName);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>");
					sb.append("Consultant Name");
					sb.append("</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strConsultantName);
					sb.append("</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb
							.append("<input type='hidden' name='strAdmissionDtlHidVal' value='"
									+ strHiddenValue + "'>");
					sb.append("</td>");
					sb.append("</tr>");

					sb.append("</table>");
				}
			}
		} catch (Exception e) {
			throw new Exception("PatientDtlHLP-->admissionDtl-->"
					+ e.getMessage());
		} finally {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}

		return sb.toString();
	}

	// //used
	public static String patientWithAdmissionDtl(String strCrNo,
			String strHospitalCode, boolean fAddress) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {
			sb.append(PatientDtlHLP.patientDtl(strCrNo, fAddress));
			sb.append(PatientDtlHLP.admissionDtl(strCrNo, strHospitalCode));
		} catch (Exception e) {
			throw new Exception("PatientDtlHLP-->patientWithAdmissionDtl-->"
					+ e.getMessage());
		}

		return sb.toString();
	}
	


	public static String compactPatientWithAdmissionDtl(String strCrNo,
			String strHospitalCode, boolean fAddress) throws Exception {

		StringBuffer sb = new StringBuffer("");

		try {

			sb.append(PatientDtlHLP.compactPatientDtl(strCrNo, fAddress));

			sb.append(PatientDtlHLP.admissionDtl(strCrNo, strHospitalCode));

			sb.append("</div>");

		} catch (Exception e) {
			throw new Exception(
					"PatientDtlHLP-->compactPatientWithAdmissionDtl-->"
							+ e.getMessage());

		}

		return sb.toString();
	}

	public static String patientDtlBillRePrint(String strCrNo, boolean fAddress)
			throws Exception {
		WebRowSet ws = null;
		GlobalVO voObj = new GlobalVO();
		PatientDtlBO boObj = new PatientDtlBO();
		StringBuffer sb = new StringBuffer("");
		voObj.setStrValue1(strCrNo);

		sb.append("");

		try {
			boObj.getPatientDetails(voObj);
			if (voObj.getStrMsgType().equals("1")) {
				throw new Exception(voObj.getStrMsgString());
			}

			ws = voObj.getGblWs1();
			if (ws != null && ws.size() > 0) {
				if (ws.next()) {
					String strAgeAndSex = ws.getString(2);
					String strPatientName = ws.getString(3);
					String strFatherOrHusbandName = ws.getString(4);
					String strSpouseName = ws.getString(5);
					String strReligion = ws.getString(6);
					String strCategoryName = ws.getString(7);
					String strCategoryCode = ws.getString(8);
					String strAddress = ws.getString(9);
					String strMlcNo = ws.getString(10);
					String strHiddenValue = ws.getString(11); // Is MLC or Not
																// at 4th
																// Position
					String[] id = strHiddenValue.replace("^", "#").split("#");

					if (strAgeAndSex == null)
						strAgeAndSex = "----";
					if (strPatientName == null)
						strPatientName = "----";
					if (strFatherOrHusbandName == null)
						strFatherOrHusbandName = "----";
					if (strReligion == null)
						strReligion = "----";
					if (strCategoryName == null)
						strCategoryName = "----";
					if (strCategoryCode == null)
						strCategoryCode = "----";
					if (strAddress == null)
						strAddress = "----";
					if (strHiddenValue == null)
						strHiddenValue = "----";
					if (strMlcNo == null)
						strMlcNo = "-----";

					if (strSpouseName == null)
						strSpouseName = "-----";

					sb
							.append("<table width='100%' border='0' cellspacing='1px' align='center' ><tr><td width='25%' class='LABEL'>Name</td>");
					sb.append("<td colspan='3' width='25%' class='CONTROL'> ");
					sb.append(strPatientName);
					sb.append("</td></tr>");
					sb
							.append("<tr><td width='25%' class='LABEL'>Patient Category</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strCategoryName);
					sb
							.append("<input type='hidden' name='strCatgoryCode' value='"
									+ strCategoryCode + "'></td>");
					sb.append("<td width='25%' class='LABEL'>Age/Gender</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strAgeAndSex);
					sb.append("</td></tr>");
					sb
							.append("<tr><td width='25%' class='LABEL'>Father Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strFatherOrHusbandName);
					sb.append("</td>");
					sb.append("<td width='25%' class='LABEL'>Spouse Name</td>");
					sb.append("<td width='25%' class='CONTROL'>");
					sb.append(strSpouseName);
					if (id[4].equals("1")) {
						sb.append("<tr>");
						sb.append("<td width='25%' class='LABEL'>MLC NO</td>");
						sb
								.append("<td colspan='3' width='25%' class='CONTROL'>");
						sb.append("<font color='red'>");
						sb.append(strMlcNo);
						sb.append("</font>");
						sb.append("</td></tr>");
					}

					sb
							.append("<input type='hidden' name='strPatientDtlHidVal' value='"
									+ strHiddenValue + "'></td></tr>");
					if (fAddress) {
						sb.append("<tr>");
						sb.append("<td width='25%' class='LABEL'>Address</td>");
						sb
								.append("<td colspan='3' width='25%' class='CONTROL'>");
						sb.append(strAddress);
						sb.append("</td></tr>");
					}

					sb.append("</table>");
				}
			}
		} catch (Exception e) {
			throw new Exception("PatientDtlHLP-->admissionDtl-->"
					+ e.getMessage());
		} finally {
			if (ws != null) {
				ws.close();
				ws = null;
			}
		}

		return sb.toString();
	}
	
	//added by shefali on 26-Aug-2014 for patient treatment detail in issu eto patient
	public static String patientTreatmentDtl(String strCrNo,String strHospitalCode)
	throws Exception 
	{
		GlobalVO voObj = new GlobalVO();
		PatientDtlBO boObj = new PatientDtlBO();
		StringBuffer str = new StringBuffer("");
		voObj.setStrValue1(strCrNo);
		voObj.setStrValue2(strHospitalCode);
		WebRowSet ws = null;
		
		str.append("");
		
		try 
		{
			boObj.getPatientTreatmentDetails(voObj);
			if (voObj.getStrMsgType().equals("1"))
			{
				throw new Exception(voObj.getStrMsgString());
			}
		
			ws = voObj.getGblWs2();
			
			System.out.println("ws:: HLp"+ws.size());
			if (ws != null && ws.size() > 0)
			{
				str.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
				str.append("<tr>");
				str.append("<td width='20%' class='LABEL' style='text-align:center'>Drug Name</td>");
				str.append("<td width='20%' class='LABEL' style='text-align:center'>Dose Name</td>");
				str.append("<td width='20%' class='LABEL' style='text-align:center'>Frequency</td>");
				str.append("<td width='20%' class='LABEL' style='text-align:center'>Start Date</td>");
				str.append("<td width='20%' class='LABEL' style='text-align:center'>End Date</td>");
				str.append("</tr>");
				
			    while (ws.next()) 
			    {
					String strdrugName = ws.getString(8);
					String strDoseName = ws.getString(10);
					String strFrequency = ws.getString(24);
					String strStartDate = ws.getString(13);
					String strEndDate = ws.getString(14);
				
					if (strdrugName == null)
						strdrugName = "----";
					if (strDoseName == null)
						strDoseName = "----";
					if (strFrequency == null)
						strFrequency = "----";
					if (strStartDate == null)
						strStartDate = "----";
					if (strEndDate == null)
						strEndDate = "----";
				
					str.append("<tr>");
					str.append("<td width='20%' class='multiControl'>  ");
					str.append(strdrugName);
					str.append("</td><td width='20%' class='multiControl'>");
					str.append(strDoseName);
					str.append("</td><td width='20%' class='multiControl'>");
					str.append(strFrequency);
					str.append("</td><td width='20%' class='multiControl'>");
					str.append(strStartDate);
					str.append("</td><td width='20%' class='multiControl'>");
					str.append(strEndDate);
					str.append("</td width='20%' class='multiControl'>");
					str.append("</tr>");
		
				}
			
				str.append("</table>");
			} 
			else 
			{
				str.append("<table class='TABLEWIDTH' align='center' cellpadding='1px' cellspacing='1px'>");
				str.append("<tr><td class='multiControl' colspan='4'><font color='red'>No record Found</font></td></tr></table>");
			} 
		}
		catch (Exception e) 
		{
			throw new Exception("PatientDtlHLP-->patientTreatmentDtl-->"+ e.getMessage());
		} 
		finally
		{
			if (ws != null) 
			{
				ws.close();
				ws = null;
		  	}
		}
		return str.toString();
	}
	
}
