package ipd.reports;

import org.apache.struts.action.ActionForm;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

public class DailyDischargeRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strUserRemarks = "0";
	private String strCase = null;
	private String strErrMsg = "";
	private String strCurrentDate = "";
	private String strHospitalCode = "";
	private String strDeptValues = "";
	private String strSeatId="";
	/*private String strUnitValues = "";
	private String strWardValues = "";*/
    private String StrHospNameValues="";
	private String strIsTimeRequired = "0"; 
	private String strCurrentTime = ""; 
	private String strFromTime= ""; 
	private String strToTime = "";
	
	private String strDischargeType="";
	

	private String strCtDate = null;
	
	private String strRadioBean="";
	private int strRadioBeanSize=0;
	private String strRadioCheckValue="";

	public String getStrRadioCheckValue() {
		return strRadioCheckValue;
	}

	public void setStrRadioCheckValue(String strRadioCheckValue) {
		this.strRadioCheckValue = strRadioCheckValue;
	}

	

	public String getStrCase() {
		return strCase;
	}

	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrUnitCode() {
		return strUnitCode;
	}

	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}

	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	public String getStrReportFormat() {
		return strReportFormat;
	}

	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}

	public String getStrDeptValues() {
		return strDeptValues;
	}

	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}

	/*public String getStrUnitValues() {
		return strUnitValues;
	}

	public void setStrUnitValues(String strUnitValues) {
		this.strUnitValues = strUnitValues;
	}

	public String getStrWardValues() {
		return strWardValues;
	}

	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}*/

	public String getStrCtDate() {
		HisUtil util = new HisUtil("Daily Discharge", "DailyDischargeRptFB");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		 } catch (Exception e) {
			new HisException("IPD Module", "Daily Discharge",
					"DailyDischargeRptFB.getStrCtDate()-->" + e.getMessage());
	     }
	      return strCtDate;
	   }
		
	
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrIsFooter() {
		return strIsFooter;
	}

	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}

	public String getStrReportId() {
		return strReportId;
	}

	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	public String getStrUserRemarks() {
		return strUserRemarks;
	}

	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrIsTimeRequired() {
		return strIsTimeRequired;
	}

	public void setStrIsTimeRequired(String strIsTimeRequired) {
		this.strIsTimeRequired = strIsTimeRequired;
	}

	public String getStrCurrentTime() {
		return strCurrentTime;
	}

	public void setStrCurrentTime(String strCurrentTime) {
		this.strCurrentTime = strCurrentTime;
	}

	public String getStrFromTime() {
		return strFromTime;
	}

	public void setStrFromTime(String strFromTime) {
		this.strFromTime = strFromTime;
	}

	public String getStrToTime() {
		return strToTime;
	}

	public void setStrToTime(String strToTime) {
		this.strToTime = strToTime;
	}

	public String getStrHospNameValues() {
		return StrHospNameValues;
	}

	public void setStrHospNameValues(String strHospNameValues) {
		StrHospNameValues = strHospNameValues;
	}

	public String getStrDischargeType() {
		return strDischargeType;
	}

	public void setStrDischargeType(String strDischargeType) {
		this.strDischargeType = strDischargeType;
	}

	public String getStrRadioBean() {
		return strRadioBean;
	}

	public void setStrRadioBean(String strRadioBean) {
		this.strRadioBean = strRadioBean;
	}

	public int getStrRadioBeanSize() {
		return strRadioBeanSize;
	}

	public void setStrRadioBeanSize(int strRadioBeanSize) {
		this.strRadioBeanSize = strRadioBeanSize;
	}

}
