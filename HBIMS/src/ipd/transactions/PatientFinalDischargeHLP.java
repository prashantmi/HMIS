package ipd.transactions;

import javax.sql.rowset.WebRowSet;

import ipd.IpdConfigUtil;
import ipd.IpdTransConfig;
import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class PatientFinalDischargeHLP
{

	public static String DATE_FORMAT_NOWwt = "dd-MMM-yyyy/HH:mm:ss";
	public static String DATE_FORMAT_NOW = "dd-MMM-yyyy";

	public static String now(String frmt)
	{
		HisUtil util = null;
		String a = "";
		util = new HisUtil("transaction", "PatientFinalDischargeHLP");
		try
		{
			a = util.getASDate(frmt);
		}
		catch (Exception e)
		{
			new HisException("Patient Final Discharge Trans", "PatientFinalDischargeHLP.now()-->", e.getMessage());
		}
		return a;
	}

	public static String getPatientDischargeParameter(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		PatientFinalDischargeBO bo = null;

		try
		{
			sBuffer = new StringBuffer("");
			bo = new PatientFinalDischargeBO();
			bo.getPatientDischargeParameter(_patientFinalDischargeVO);
			for (int tmpI = 0; tmpI < _patientFinalDischargeVO.getStrComponentID().length; tmpI++)
			{
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>" + _patientFinalDischargeVO.getStrComponentName()[tmpI] + "</td>");
				sBuffer
						.append("<td width='25%' class='CONTROL'><textarea  onkeydown='maxLength(this,500)' onkeyup='maxLength(this,500)' name='strPatientDisParamDetails'></textarea></td>");
				sBuffer.append("<td colspan='2' width='50%' class='CONTROL'><input type='hidden' name='strComponentID' value='"
						+ _patientFinalDischargeVO.getStrComponentID()[tmpI] + "'></td>");
				sBuffer.append("</tr></table>");
			}
			if (_patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(_patientFinalDischargeVO.getStrMsgString());
		}
		catch (Exception e)
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getPatientDischargeParameter() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		return sBuffer.toString();
	}

	// MLC Details
	public static String getMLCDetails_onLine(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		try
		{
			sBuffer = new StringBuffer("");
			/*
			 * _patientFinalDischargeVO.setStrApprovByMLC("Ajay Gupta"); //delete
			 * _patientFinalDischargeVO.setStrPoliceInfrmDtMLC("12-Jan-2009"); //delete
			 * _patientFinalDischargeVO.setStrApprovDtMLC("10-Jan-2009"); //delete
			 */sBuffer.append("<table class='TABLEWIDTH' align='center' bgcolor='orange' border='0' cellspacing='1px'><tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Approved By(C.M.O)</td>");
			sBuffer.append("<td width='75%' colspan='3' class='CONTROL'><font color='blue'>" + _patientFinalDischargeVO.getStrApprovByMLC()
					+ "</font></td></tr>");
			sBuffer.append("<tr><td width='25%' class='LABEL'>Police Informed date</td>");
			sBuffer
					.append("<td width='25%' class='CONTROL'><font color='blue'>" + _patientFinalDischargeVO.getStrPoliceInfrmDtMLC()
							+ "</font></td>");
			sBuffer.append("<td width='25%' class='LABEL'>Approval Date(C.M.O)</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><font color='blue'>" + _patientFinalDischargeVO.getStrApprovDtMLC() + "</font></td>");
			sBuffer.append("</tr></table>");
		}
		catch (Exception e)
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getMLCDetails_onLine() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		return sBuffer.toString();
	}

	public static String getMLCDetails_offLine(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		HisUtil hisutil = null;
		String str2 = "";
		try
		{
			sBuffer = new StringBuffer("");
			hisutil = new HisUtil("transaction", "PatientFinalDischargeHLP");
			str2 = hisutil.getOptionValue(_patientFinalDischargeVO.getStrApprovByWS_MLC(), "0", "0^Select value", true);
			// System.out.println("str2MLC->"+str2);
			sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Approved By(C.M.O)</td>");
			sBuffer.append("<td width='75%' colspan='3' class='CONTROL'><select name='strApprovByMLC' class='comboNormal' title='List of C.M.O'>"
					+ str2 + "</select></td></tr>");
			sBuffer.append("<tr><td width='25%' class='LABEL'>Approval Date(C.M.O)</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>" + getDatePicker("strApprovDtMLC", now(DATE_FORMAT_NOW), true) + "</td>");
			sBuffer.append("<td width='25%' class='LABEL'>Police Informed date</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>" + getDatePicker("strPoliceInfrmDtMLC", now(DATE_FORMAT_NOW), true) + "</td></tr>");
			sBuffer.append("<tr><td width='25%' class='LABEL'>Remarks By(C.M.O)</td>");
			sBuffer.append("<td width='75%' colspan='3' class='CONTROL'><textarea rows='3' name='strApprovRmkMLC'></textarea></td>");
			sBuffer.append("</tr></table>");
		}
		catch (Exception e)
		{
			// System.out.println("e->"+e.getMessage());
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getMLCDetails_offLine() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		return sBuffer.toString();
	}

	// end
	// OPD Visit Dtls
	public static String getOPD_VisitDetails_onLine(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		try
		{
			sBuffer = new StringBuffer("");
			/*
			 * _patientFinalDischargeVO.setStrNxtVisitOPD("14-Feb-2009");//delete
			 * _patientFinalDischargeVO.setStrRoomOPD("Room 11");//delete
			 */sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' bgcolor='orange' cellspacing='1px'><tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Next OPD Visit Date</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><font color='blue'>" + _patientFinalDischargeVO.getStrNxtVisitOPD() + "</font></td>");
			sBuffer.append("<td width='25%' class='LABEL'>Room No.</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><font color='blue'>" + _patientFinalDischargeVO.getStrRoomOPD() + "</font></td>");
			sBuffer.append("</tr></table>");
		}
		catch (Exception e)
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getOPD_VisitDetails_onLine() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		return sBuffer.toString();
	}

	public static String getOPD_VisitDetails_offLine(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		HisUtil hisutil = null;
		String str2 = "";
		try
		{
			sBuffer = new StringBuffer("");
			hisutil = new HisUtil("transaction", "PatientFinalDischargeHLP");
			str2 = hisutil.getOptionValue(_patientFinalDischargeVO.getStrRoomOPDWS(), "0", "0^Select value", true);
			sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
			sBuffer.append("<td width='25%' class='LABEL'>Next OPD Visit Date</td>");
			sBuffer.append("<td width='25%' class='CONTROL'>" + getDatePicker("strNxtVisitOPD", "", true) + "</td>");
			sBuffer.append("<td width='25%' class='LABEL'>Room No.</td>");
			sBuffer.append("<td width='25%' class='CONTROL'><select name='strRoomOPD' class='comboNormal' title='List of OPD Rooms'>" + str2
					+ "</select></td>");
			sBuffer.append("</tr></table>");
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getOPD_VisitDetails_offLine() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		return sBuffer.toString();
	}

	public static String getAbsconded(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		HisUtil hisutil = null;
		String str2 = "";
		try
		{
			IpdConfigUtil config = new IpdConfigUtil(_patientFinalDischargeVO.getStrHospitalCode());
			sBuffer = new StringBuffer("");
			hisutil = new HisUtil("transaction", "PatientFinalDischargeHLP");
			str2 = hisutil.getOptionValue(_patientFinalDischargeVO.getStrRoomOPDWS(), "0", "0^Select value", true);
			if (config.getStrDischargeProcessType().equals("1"))
			{// online
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Next OPD Visit Date</td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrNxtVisitOPD() + "</td>");
				sBuffer.append("<td width='25%' class='LABEL'>Room No.</td>");
				sBuffer.append("<td width='25%' class='CONTROL'></td>");
				sBuffer.append("</tr></table>");
			}
			else
			{// offline
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Next OPD Visit Date</td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + getDatePicker("strNxtVisitOPD", "", true) + "</td>");
				sBuffer.append("<td width='25%' class='LABEL'>Room No.</td>");
				sBuffer.append("<td width='25%' class='CONTROL'><select name='strRoomOPD' class='comboNormal' title='List of OPD Rooms'>" + str2
						+ "</select></td>");
				sBuffer.append("</tr></table>");
			}
		}

		catch (Exception e)
		{
			// e.printStackTrace();
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getOPD_VisitDetails_offLine() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		return sBuffer.toString();
	}

	public static String getLAMA(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		
		try
		{
			IpdConfigUtil config = new IpdConfigUtil(_patientFinalDischargeVO.getStrHospitalCode());
			sBuffer = new StringBuffer("");
			String strConsentTemplateId=config.getStrConsentTemplateId();
			if (config.getStrDischargeProcessType().equals("1"))	// online
			{
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Date & Time</td>");
				sBuffer.append("<td width='25%' class='CONTROL'><font color='blue'>" + now(DATE_FORMAT_NOWwt) + "</font></td>");
				sBuffer.append("<td width='25%' class='LABEL'><font size='2' color='red'>*</font>Remarks</td>");
				sBuffer.append("<td width='25%' class='CONTROL'></td>");
				sBuffer.append("</tr></table>");
			}
			else	// offline
			{
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL' colspan='1'><a class='roll-link' onclick=\"openLAMAConsent('"+strConsentTemplateId+"')\" STYLE='cursor:pointer;color:#E93A30'><span data-title='Consent Required'>Consent Required</span></a></td>");
				sBuffer.append("<td width='75%' class='CONTROL' colspan='3'><input type='checkbox' value='1' checked name='strConsentRequired' onclick='consnetDetails(this)'></td>");
				sBuffer.append("</tr>");
				sBuffer.append("<tr id='consentDiv'>");
				sBuffer.append("<td width='25%' class='LABEL'>Consent Signed By</td>");
				sBuffer.append("<td width='25%' class='CONTROL'><input type='radio' value='1' checked name='strConsentSignedBy' onclick='showConsentRelativeDiv(this)'>Patient<input type='radio' value='2' name='strConsentSignedBy' onclick='showConsentRelativeDiv(this)'>Relative</td>");
				sBuffer.append("<td width='25%' class='LABEL'><div id='consentSignDiv1' style='display:none;'><font size='1' color='red'>*</font>Consent Signing Relative Name</div></td>");
				sBuffer.append("<td width='25%' class='CONTROL'><div id='consentSignDiv2' style='display:none;'><input type='textbox' name='strConsentSignedByRelativeName' maxlength='50'></div></td>");
				sBuffer.append("</tr>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL' colspan='1'><font size='1' color='red'>*</font>Remarks</td>");
				sBuffer.append("<td width='75%' class='CONTROL' colspan='3'><textarea name='strRsn' cols='20' rows=''></textarea></td>");
				sBuffer.append("</tr></table>");
			}
		}

		catch (Exception e)
		{
			// e.printStackTrace();
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getLAMA() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		sBuffer.append("^0");
		return sBuffer.toString();
	}

	public static String getTransfered(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		WebRowSet ws = null;
		PatientFinalDischargeBO patientFinalDischargeBO = null;
		try
		{
			patientFinalDischargeBO = new PatientFinalDischargeBO();
			patientFinalDischargeBO.getHospitalList(_patientFinalDischargeVO);
			ws = _patientFinalDischargeVO.getHospitalListWs();
			IpdConfigUtil config = new IpdConfigUtil(_patientFinalDischargeVO.getStrHospitalCode());
			HisUtil util = new HisUtil("PatientFinalDischargeHLP", "getTransfered");
			sBuffer = new StringBuffer("");
			if (config.getStrDischargeProcessType().equals("1"))
			{// online
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Transfered Summary</td>");
				sBuffer.append("<td width='25%' class='CONTROL'></td>");
				sBuffer.append("<td width='25%' class='LABEL'>Transfer To</td>");
				sBuffer.append("<td width='25%' class='CONTROL'></td>");
				sBuffer.append("</tr></table>");
			}
			else
			{// offline
				sBuffer.append("<table class='TABLEWIDTH' align='center' border='0' cellspacing='1px'><tr>");
				sBuffer.append("<td width='25%' class='LABEL'><font size='2' color='red'>*</font>Transfered Summary</td>");
				sBuffer.append("<td width='25%' class='CONTROL'><textarea name='strTransferedSummary' cols='20' rows=''></textarea></td>");
				sBuffer.append("<td width='25%' class='LABEL'><font size='2' color='red'>*</font>Transfer To</td>");
				if (IpdTransConfig.getComboTransferToDischarge().equals("1"))
				{
					sBuffer.append("<td width='25%' class='CONTROL'><select name='strTransferTo'>");
					sBuffer.append(util.getOptionValue(ws, "0", "Select Value", false));
					sBuffer.append("</select></td>");
				}
				else sBuffer.append("<td width='25%' class='CONTROL'><input type='text' name='strTransferTo'></td>");
				sBuffer.append("</tr></table>");
			}
		}

		catch (Exception e)
		{
			// e.printStackTrace();
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getTransfered() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		sBuffer.append("^0");
		return sBuffer.toString();
	}

	/** *for testing .changes to be made in jar** */
	public static String getDatePicker(String fieldName, String dateValue, boolean readOnly)
	{
		String dateString = "";
		StringBuilder strBuffer = new StringBuilder(500);
		strBuffer.append((new StringBuilder(" <input size='9%' type=\"text\" name=\"")).append(fieldName).append("\" id=\"").append(fieldName)
				.append("\" ").toString());
		if (readOnly) strBuffer.append(" readonly = \"false\" ");
		strBuffer.append((new StringBuilder(" value='")).append(dateValue).append("'>").toString());
		strBuffer.append((new StringBuilder(" <img src=\"../../hisglobal/images/imgDatepicker.png\" id=\"")).append(fieldName).append(
				"1\" style=\"cursor: pointer; border: 1px solid red;\" title=\"Date selector\" ").toString());
		strBuffer.append(" onmouseover=\"Calendar.datePickerAjax(event,document.forms[0]." + fieldName
				+ "),this.style.background='red';\" onmouseout=\"this.style.background=''\"> \n");
		/*
		 * strBuffer.append("<script event=\"Click()\" language=\"JavaScript\"> \"+Calendar.setup({ ");
		 * strBuffer.append((new StringBuilder(" inputField : \"")).append(fieldName).append("\",ifFormat :
		 * \"%d-%b-%Y\",button : \"").append(fieldName).append("1\",singleClick : true\n").toString());
		 * strBuffer.append("})\";</script>\n");
		 */
		dateString = strBuffer.toString();
		strBuffer = null;
		return dateString;
	}
    
	

	// end

	public static String getPatientDeathDetails(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		PatientFinalDischargeBO bo = null;
		try
		{
			sBuffer = new StringBuffer("");
			bo = new PatientFinalDischargeBO();
			bo.getPatientDeathDetails(_patientFinalDischargeVO);
			if (_patientFinalDischargeVO.getStrIsDead().equals("true"))
			{
				sBuffer.append("<div id='deathDetails' style='display: block'>");
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Death Date And Time </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathDateAndTime() + " </td>");
				sBuffer.append("<td width='25%' class='LABEL'>Description of Immediate Cause of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathCauseIM() + "</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Antecedent Cause of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathCauseAN() + " </td>");
				sBuffer.append("<td width='25%' class='LABEL'>Manner Of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathManner());
				sBuffer.append("<input type='hidden' name='strDeathMannerID' value='" + _patientFinalDischargeVO.getStrDeathMannerID() + "'></td>");
				sBuffer.append("</tr></table></div>");
				/*
				 * sBuffer.append("<tr>"); sBuffer.append("<td class='LABEL' width='23%'>Is Shift To Mortuary</td>");
				 * sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='27%'>"); sBuffer.append("<input
				 * name='strIsSiftToMortuary' value='1' onclick='shiftMortuaryClk(this);' type='radio'>Yes");
				 * sBuffer.append("<input name='strIsSiftToMortuary' value='0' checked='checked'
				 * onclick='shiftMortuaryClk(this);' type='radio'>No</td>"); sBuffer.append("</tr></table><div
				 * id='isSiftMortID' style='display: none;'><table class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
				 * sBuffer.append("<td class='LABEL' width='25%'>Shift date</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append(HisUtil.getDatePicker("strShiftDate", "", true)); sBuffer.append("</td>");
				 * sBuffer.append("<td class='LABEL' width='25%'>Shift Time</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append("<input class='txtFldSmall' name='strShiftHour' maxlength='2' onkeypress='return
				 * validateData(event,5);' onkeyup='hour(this,event)' type='text'><b>:</b>"); sBuffer.append("<input
				 * class='txtFldSmall' name='strShiftMin' maxlength='2' onkeypress='return validateData(event,5);'
				 * onkeyup='min(this,event)' type='text'><b>:</b>"); sBuffer.append("<input class='txtFldSmall'
				 * name='strShiftSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)'
				 * type='text'>"); sBuffer.append("<select class='comboSmall' name='strShiftAmPm'>"); sBuffer.append("<option
				 * value='1'>AM</option>"); sBuffer.append("<option value='2'>PM</option>"); sBuffer.append("</select>");
				 * sBuffer.append("</td></tr></table></div><div id='isNotSiftMortID' style='display: block;'><table
				 * class='TABLEWIDTH' align='center' cellspacing='1'><tr>"); sBuffer.append("<td class='LABEL' width='25%'><font
				 * color='red'>*</font>Body Handover To</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append("<input type='text' name='strHandoverTo'>"); sBuffer.append("</td>"); sBuffer.append("<td class='LABEL' width='25%'><font
				 * color='red'>*</font>Date Of Handover</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append(HisUtil.getDatePicker("strHandoverDate", "", true)); sBuffer.append("</td></tr><tr>");
				 * sBuffer.append("<td class='LABEL' width='25%'><font color='red'>*</font>Handover Time</td>");
				 * sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='75%'>"); sBuffer.append("<input
				 * class='txtFldSmall' name='strHandoverHour' maxlength='2' onkeypress='return validateData(event,5);'
				 * onkeyup='hour(this,event)' type='text'><b>:</b>"); sBuffer.append("<input class='txtFldSmall'
				 * name='strHandoverMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event)'
				 * type='text'><b>:</b>"); sBuffer.append("<input class='txtFldSmall' name='strHandoverSec' maxlength='2'
				 * onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>"); sBuffer.append("<select
				 * class='comboSmall' name='strHandoverAmPm'>"); sBuffer.append("<option value='1'>AM</option>");
				 * sBuffer.append("<option value='2'>PM</option>"); sBuffer.append("</select>"); sBuffer.append("</td></tr></table></div></div>");
				 */
			}
			else
			{
				bo.getStrDeathMannerComboValues(_patientFinalDischargeVO);
				bo.getStrDeathCauseComboValues(_patientFinalDischargeVO);
				bo.getGender(_patientFinalDischargeVO);
				sBuffer.append("<div id='deathDetails' style='display: none'>");
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Date Of Death </td>");
				sBuffer.append("<td width='27%' class='CONTROL'>" + HisUtil.getDatePicker("strDeathDate", "", true) + "</td>");
				sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Onset Date Of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + HisUtil.getDatePicker("strOnsetDeathDate", "", true) + "</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Time Of Death</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strDeathHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strDeathMin)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strDeathMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strDeathSec)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strDeathSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strDeathAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Onset Time Of Death</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strOnsetDeathHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strOnsetDeathMin)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strOnsetDeathMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strOnsetDeathSec)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strOnsetDeathSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strOnsetDeathAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Immediate Cause Of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><select name='strDeathCauseID' class='comboNormal'>"
						+ _patientFinalDischargeVO.getStrDeathCauseComboValues() + "</select></td>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Manner Of Death </td>");
				sBuffer.append("<td width='27%' class='CONTROL'><select name='strDeathMannerID' class='comboNormal'>"
						+ _patientFinalDischargeVO.getStrDeathMannerComboValues() + "</select></td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='23%' class='LABEL'>Description Of Immediate Cause Of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strDescpCauseDeath'></textarea></td>");
				sBuffer.append("<td width='23%' class='LABEL'>Antecedent Cause Of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strAntecedentCauseDeath'></textarea></td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='23%' class='LABEL'>Injury Details</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strInjuryDetail'></textarea></td>");
				//sBuffer.append("<td width='23%' class='LABEL'>Description Of Other Significant Conditions Contributing To Death</td>");
				sBuffer.append("<td width='23%' class='LABEL'>Reason of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strInjuryDetail1'></textarea></td>");
				sBuffer.append("</tr>");
				if (_patientFinalDischargeVO.getStrIsFemale().equals("true"))
				{
					sBuffer.append("<tr>");
					sBuffer.append("<td width='23%' class='LABEL'>Is Pregnant</td>");
					sBuffer.append("<td class='CONTROL' colspan='3'>");
					sBuffer.append("<input name='strIsPregnant' value='1' onclick='isPregnantClick(this);' type='radio'>Yes");
					sBuffer.append("<input name='strIsPregnant' value='0' checked='checked' onclick='isPregnantClick(this);' type='radio'>No</td>");
					sBuffer
							.append("</tr></table><div  id='isDeliveryID' style='display: none;'><table  class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
					sBuffer.append("<td width='23%' class='LABEL'>Is there Delivery</td>");
					sBuffer.append("<td colspan='3' class='CONTROL'>");
					sBuffer.append("<input name='strIsDelivery' value='1' type='radio'>Yes");
					sBuffer.append("<input name='strIsDelivery' value='0' type='radio'>No</td>");
					sBuffer.append("</tr></table></div><table  class='TABLEWIDTH' align='center' cellspacing='1'>");
				}
				sBuffer.append("<tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Verify Date</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append(HisUtil.getDatePicker("strVerifyDate", "", true));
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Verify Time</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strVerifyHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strVerifyMin);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strVerifyMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strVerifySec);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strVerifySec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strVerifyAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td></tr><tr>");
				sBuffer.append("<td class='LABEL' width='23%'>Is Shift To Mortuary</td>");
				sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='27%'>");
				sBuffer.append("<input name='strIsSiftToMortuary' value='1' onclick='shiftMortuaryClk(this);' type='radio'>Yes");
				sBuffer
						.append("<input name='strIsSiftToMortuary' value='0' checked='checked' onclick='shiftMortuaryClk(this);' type='radio'>No</td>");
				sBuffer
						.append("</tr></table><div  id='isSiftMortID' style='display: none;'><table  class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
				sBuffer.append("<td class='LABEL' width='23%'>Shift date</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append(HisUtil.getDatePicker("strShiftDate", "", true));
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'>Shift Time</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strShiftHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strShiftMin);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strShiftMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strShiftSec);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strShiftSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strShiftAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer
						.append("</td></tr></table></div><div  id='isNotSiftMortID' style='display: block;'><table  class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Body Handover To</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append("<input type='text' name='strHandoverTo'>");
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Date Of Handover</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append(HisUtil.getDatePicker("strHandoverDate", "", true));
				sBuffer.append("</td></tr><tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Handover Time</td>");
				sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='77%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strHandoverHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strHandoverMin);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strHandoverMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strHandoverSec);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strHandoverSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strHandoverAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td></tr></table></div></div>");
			}
			if (_patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(_patientFinalDischargeVO.getStrMsgString());
		}
		catch (Exception e)
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getPatientDeathDetails() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		finally
		{
			bo = null;
		}
		return sBuffer.toString();
	}
	
	public static String getPatientAbscondedDetails(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		try
		{
				sBuffer = new StringBuffer("");
				sBuffer.append("<div id='abscondedDetails'>");
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Absconded Date</td>");
				sBuffer.append("<td width='27%' class='CONTROL'>" + HisUtil.getDatePicker("strAbscondedDate", "", true) + "</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Absconded Time</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append("<input class='txtFldSmall' name='strAbscondedHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strAbscondedMin)' type='text'><b>:</b>");
				sBuffer.append("<input class='txtFldSmall' name='strAbscondedMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min1(this,event);autotab(this,document.forms[0].strAbscondedSec)' type='text'><b>:</b>");
				sBuffer.append("<input class='txtFldSmall' name='strAbscondedSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec1(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strAbscondedAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td>");
				sBuffer.append("</tr>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='23%' class='LABEL'>Height Of Patient(Ft.)</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><input type='text' name='strPatHeight' maxlength='50' class='txtFldNormal'></td>");
				sBuffer.append("<td width='23%' class='LABEL'>Color Of Patient</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><input type='text' name='strPatColor' maxlength='50' class='txtFldNormal'></td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='23%' class='LABEL'>Mark of Identification</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><input type='text' name='strPatIdentifyMark' maxlength='100' class='txtFldMax'></td>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>When Seen Last in Ward</td>");
				sBuffer.append("<td width='27%' class='CONTROL'>" + HisUtil.getDatePicker("strLastSeenDate", "", true) + "</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'></font>Wearing Hospital Clothes</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><input type='radio' name='strIsPatWearHospClothes' id='r1' value='0' checked onclick='showClothesTextArea(this)'>No&nbsp;<input type='radio' name='strIsPatWearHospClothes' id='r2' value='1' onclick='showClothesTextArea(this)'>Yes</td>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'></font>Details of Hospital Clothes</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strClothesDetails'></textarea></td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("</td></tr></table></div></div>");			
		}
		catch (Exception e)
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getPatientDeathDetails() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		finally
		{			
		}
		return sBuffer.toString();
	}
	public static String getPatientDeathDetails_BS(PatientFinalDischargeVO _patientFinalDischargeVO)
	{
		StringBuffer sBuffer = null;
		PatientFinalDischargeBO bo = null;
		try
		{
			sBuffer = new StringBuffer("");
			bo = new PatientFinalDischargeBO();
			bo.getPatientDeathDetails(_patientFinalDischargeVO);
			if (_patientFinalDischargeVO.getStrIsDead().equals("true"))
			{
				sBuffer.append("<div id='deathDetails' style='display: block'>");
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Death Date And Time </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathDateAndTime() + " </td>");
				sBuffer.append("<td width='25%' class='LABEL'>Description of Immediate Cause of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathCauseIM() + "</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='25%' class='LABEL'>Antecedent Cause of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathCauseAN() + " </td>");
				sBuffer.append("<td width='25%' class='LABEL'>Manner Of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + _patientFinalDischargeVO.getStrDeathManner());
				sBuffer.append("<input type='hidden' name='strDeathMannerID' value='" + _patientFinalDischargeVO.getStrDeathMannerID() + "'></td>");
				sBuffer.append("</tr></table></div>");
				/*
				 * sBuffer.append("<tr>"); sBuffer.append("<td class='LABEL' width='23%'>Is Shift To Mortuary</td>");
				 * sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='27%'>"); sBuffer.append("<input
				 * name='strIsSiftToMortuary' value='1' onclick='shiftMortuaryClk(this);' type='radio'>Yes");
				 * sBuffer.append("<input name='strIsSiftToMortuary' value='0' checked='checked'
				 * onclick='shiftMortuaryClk(this);' type='radio'>No</td>"); sBuffer.append("</tr></table><div
				 * id='isSiftMortID' style='display: none;'><table class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
				 * sBuffer.append("<td class='LABEL' width='25%'>Shift date</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append(HisUtil.getDatePicker("strShiftDate", "", true)); sBuffer.append("</td>");
				 * sBuffer.append("<td class='LABEL' width='25%'>Shift Time</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append("<input class='txtFldSmall' name='strShiftHour' maxlength='2' onkeypress='return
				 * validateData(event,5);' onkeyup='hour(this,event)' type='text'><b>:</b>"); sBuffer.append("<input
				 * class='txtFldSmall' name='strShiftMin' maxlength='2' onkeypress='return validateData(event,5);'
				 * onkeyup='min(this,event)' type='text'><b>:</b>"); sBuffer.append("<input class='txtFldSmall'
				 * name='strShiftSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)'
				 * type='text'>"); sBuffer.append("<select class='comboSmall' name='strShiftAmPm'>"); sBuffer.append("<option
				 * value='1'>AM</option>"); sBuffer.append("<option value='2'>PM</option>"); sBuffer.append("</select>");
				 * sBuffer.append("</td></tr></table></div><div id='isNotSiftMortID' style='display: block;'><table
				 * class='TABLEWIDTH' align='center' cellspacing='1'><tr>"); sBuffer.append("<td class='LABEL' width='25%'><font
				 * color='red'>*</font>Body Handover To</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append("<input type='text' name='strHandoverTo'>"); sBuffer.append("</td>"); sBuffer.append("<td class='LABEL' width='25%'><font
				 * color='red'>*</font>Date Of Handover</td>"); sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='25%'>");
				 * sBuffer.append(HisUtil.getDatePicker("strHandoverDate", "", true)); sBuffer.append("</td></tr><tr>");
				 * sBuffer.append("<td class='LABEL' width='25%'><font color='red'>*</font>Handover Time</td>");
				 * sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='75%'>"); sBuffer.append("<input
				 * class='txtFldSmall' name='strHandoverHour' maxlength='2' onkeypress='return validateData(event,5);'
				 * onkeyup='hour(this,event)' type='text'><b>:</b>"); sBuffer.append("<input class='txtFldSmall'
				 * name='strHandoverMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event)'
				 * type='text'><b>:</b>"); sBuffer.append("<input class='txtFldSmall' name='strHandoverSec' maxlength='2'
				 * onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>"); sBuffer.append("<select
				 * class='comboSmall' name='strHandoverAmPm'>"); sBuffer.append("<option value='1'>AM</option>");
				 * sBuffer.append("<option value='2'>PM</option>"); sBuffer.append("</select>"); sBuffer.append("</td></tr></table></div></div>");
				 */
			}
			else
			{
				bo.getStrDeathMannerComboValues(_patientFinalDischargeVO);
				bo.getStrDeathCauseComboValues(_patientFinalDischargeVO);
				bo.getGender(_patientFinalDischargeVO);
				sBuffer.append("<div id='deathDetails' style='display: none'>");
				sBuffer.append("<table class='TABLEWIDTH' align='center' cellspacing='1px'>");
				sBuffer.append("<tr>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Date Of Death </td>");
				sBuffer.append("<td width='27%' class='CONTROL'>" + HisUtil.getDatePicker("strDeathDate", "", true) + "</td>");
				sBuffer.append("<td width='25%' class='LABEL'><font color='red'>*</font>Onset Date Of Death </td>");
				sBuffer.append("<td width='25%' class='CONTROL'>" + HisUtil.getDatePicker("strOnsetDeathDate", "", true) + "</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Time Of Death</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strDeathHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strDeathMin)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strDeathMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strDeathSec)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strDeathSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strDeathAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Onset Time Of Death</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strOnsetDeathHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strOnsetDeathMin)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strOnsetDeathMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strOnsetDeathSec)' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strOnsetDeathSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strOnsetDeathAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Immediate Cause Of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><select name='strDeathCauseID' class='comboNormal'>"
						+ _patientFinalDischargeVO.getStrDeathCauseComboValues() + "</select></td>");
				sBuffer.append("<td width='23%' class='LABEL'><font color='red'>*</font>Manner Of Death </td>");
				sBuffer.append("<td width='27%' class='CONTROL'><select name='strDeathMannerID' class='comboNormal'>"
						+ _patientFinalDischargeVO.getStrDeathMannerComboValues() + "</select></td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='23%' class='LABEL'>Description Of Immediate Cause Of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strDescpCauseDeath'></textarea></td>");
				sBuffer.append("<td width='23%' class='LABEL'>Antecedent Cause Of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strAntecedentCauseDeath'></textarea></td>");
				sBuffer.append("</tr><tr>");
				sBuffer.append("<td width='23%' class='LABEL'>Injury Details</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strInjuryDetail'></textarea></td>");
				//sBuffer.append("<td width='23%' class='LABEL'>Description Of Other Significant Conditions Contributing To Death</td>");
				sBuffer.append("<td width='23%' class='LABEL'>Reason of Death</td>");
				sBuffer.append("<td width='27%' class='CONTROL'><textarea rows='2' name='strInjuryDetail1'></textarea></td>");
				sBuffer.append("</tr>");
				if (_patientFinalDischargeVO.getStrIsFemale().equals("true"))
				{
					sBuffer.append("<tr>");
					sBuffer.append("<td width='23%' class='LABEL'>Is Pregnant</td>");
					sBuffer.append("<td class='CONTROL' colspan='3'>");
					sBuffer.append("<input name='strIsPregnant' value='1' onclick='isPregnantClick(this);' type='radio'>Yes");
					sBuffer.append("<input name='strIsPregnant' value='0' checked='checked' onclick='isPregnantClick(this);' type='radio'>No</td>");
					sBuffer
							.append("</tr></table><div  id='isDeliveryID' style='display: none;'><table  class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
					sBuffer.append("<td width='23%' class='LABEL'>Is there Delivery</td>");
					sBuffer.append("<td colspan='3' class='CONTROL'>");
					sBuffer.append("<input name='strIsDelivery' value='1' type='radio'>Yes");
					sBuffer.append("<input name='strIsDelivery' value='0' type='radio'>No</td>");
					sBuffer.append("</tr></table></div><table  class='TABLEWIDTH' align='center' cellspacing='1'>");
				}
				sBuffer.append("<tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Verify Date</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append(HisUtil.getDatePicker("strVerifyDate", "", true));
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Verify Time</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strVerifyHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strVerifyMin);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strVerifyMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strVerifySec);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strVerifySec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strVerifyAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td></tr><tr>");
				sBuffer.append("<td class='LABEL' width='23%'>Is Shift To Mortuary</td>");
				sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='27%'>");
				sBuffer.append("<input name='strIsSiftToMortuary' value='1' onclick='shiftMortuaryClk(this);' type='radio'>Yes");
				sBuffer
						.append("<input name='strIsSiftToMortuary' value='0' checked='checked' onclick='shiftMortuaryClk(this);' type='radio'>No</td>");
				sBuffer
						.append("</tr></table><div  id='isSiftMortID' style='display: none;'><table  class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
				sBuffer.append("<td class='LABEL' width='23%'>Shift date</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append(HisUtil.getDatePicker("strShiftDate", "", true));
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'>Shift Time</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strShiftHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strShiftMin);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strShiftMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strShiftSec);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strShiftSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strShiftAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer
						.append("</td></tr></table></div><div  id='isNotSiftMortID' style='display: block;'><table  class='TABLEWIDTH' align='center' cellspacing='1'><tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Body Handover To</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append("<input type='text' name='strHandoverTo'>");
				sBuffer.append("</td>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Date Of Handover</td>");
				sBuffer.append("<td class='CONTROL' nowrap='nowrap' width='27%'>");
				sBuffer.append(HisUtil.getDatePicker("strHandoverDate", "", true));
				sBuffer.append("</td></tr><tr>");
				sBuffer.append("<td class='LABEL' width='23%'><font color='red'>*</font>Handover Time</td>");
				sBuffer.append("<td class='CONTROL' colspan='3' nowrap='nowrap' width='77%'>");
				sBuffer
						.append("<input class='txtFldSmall' name='strHandoverHour' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='hour(this,event);autotab(this,document.forms[0].strHandoverMin);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strHandoverMin' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='min(this,event);autotab(this,document.forms[0].strHandoverSec);' type='text'><b>:</b>");
				sBuffer
						.append("<input class='txtFldSmall' name='strHandoverSec' maxlength='2' onkeypress='return validateData(event,5);' onkeyup='sec(this,event)' type='text'>");
				sBuffer.append("<select class='comboSmall' name='strHandoverAmPm'>");
				sBuffer.append("<option value='1'>AM</option>");
				sBuffer.append("<option value='2'>PM</option>");
				sBuffer.append("</select>");
				sBuffer.append("</td></tr></table></div></div>");
			}
			if (_patientFinalDischargeVO.getStrMsgType().equals("1")) throw new Exception(_patientFinalDischargeVO.getStrMsgString());
		}
		catch (Exception e)
		{
			_patientFinalDischargeVO.setStrMsgString("PatientFinalDischargeHLP.getPatientDeathDetails() --> " + e.getMessage());
			_patientFinalDischargeVO.setStrMsgType("1");
		}
		finally
		{
			bo = null;
		}
		return sBuffer.toString();
	}

}
