package vo.registration;

/**
 * @author s.singaravelan
 * DATE : 30-Dec-2013
 */

public class UnitVO {
	
	private String strDeptCode;
	private String strDeptUnitCode;

	private String strUnitCode;
	private String strUnitName;
	private String strDeptName;

	private String strUnitLocCode;
	private String strEmpCode;
	private String strIsGeneral;
	private String strTemplateId;
	private String strDiagnosisType;

	private String strLocCode;
	private String strUnitDays;
	private String strEpiDefCloseDays;

	private String strIsRefer;	
	private String strIsAppointment;	
	private String strIsUnit;
	
	private String strIsExpiry;
	private String strExpiryDay;
	private String strExpiryMonth;
	private String strRenewalType;
	private String strCardPrintSetup;

	private String strRemarks;
	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldUnitName;
	private String strMobileNo;  //Added by warish 22-aug-2017
	
	/*Added For	:- Setup Max Visit Walk-in
	 * By		:- Raj Kumar
	 * On		:- 20/09/2018
	 * 
	 * 
	 * */
	private String strLowerAgeLimit;
	private String strMaxWalkinReg;
	private String  strMaxWalkinFolloUp;
	private String strMaxWalkinPortReg;
	private String strMaxWalkinPortFollowUP;


	
	public void reset()
	{
		strIsUnit="0";
		strUnitName="";
		strEmpCode="-1";
		strIsGeneral="-1";
		strDiagnosisType="-1";
		strUnitLocCode="-1";
		strEpiDefCloseDays="";
		strIsExpiry="0";
		strExpiryDay="";
		strExpiryMonth="";
		strIsRefer="0";
		strCardPrintSetup="1";
		strMobileNo="";
		strMaxWalkinPortFollowUP="";
		strMaxWalkinPortReg="";
		strLowerAgeLimit="";
		strMaxWalkinReg="";
		strMaxWalkinFolloUp="";
		
	}
	
	public String getStrLowerAgeLimit() {
		return strLowerAgeLimit;
	}


	public void setStrLowerAgeLimit(String strLowerAgeLimit) {
		this.strLowerAgeLimit = strLowerAgeLimit;
	}


	public String getStrMaxWalkinReg() {
		return strMaxWalkinReg;
	}


	public void setStrMaxWalkinReg(String strMaxWalkinReg) {
		this.strMaxWalkinReg = strMaxWalkinReg;
	}


	public String getStrMaxWalkinFolloUp() {
		return strMaxWalkinFolloUp;
	}


	public void setStrMaxWalkinFolloUp(String strMaxWalkinFolloUp) {
		this.strMaxWalkinFolloUp = strMaxWalkinFolloUp;
	}


	public String getStrMaxWalkinPortReg() {
		return strMaxWalkinPortReg;
	}


	public void setStrMaxWalkinPortReg(String strMaxWalkinPortReg) {
		this.strMaxWalkinPortReg = strMaxWalkinPortReg;
	}


	public String getStrMaxWalkinPortFollowUP() {
		return strMaxWalkinPortFollowUP;
	}


	public void setStrMaxWalkinPortFollowUP(String strMaxWalkinPortFollowUP) {
		this.strMaxWalkinPortFollowUP = strMaxWalkinPortFollowUP;
	}


	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrUnitLocCode() {
		return strUnitLocCode;
	}
	public void setStrUnitLocCode(String strUnitLocCode) {
		this.strUnitLocCode = strUnitLocCode;
	}
	public String getStrEmpCode() {
		return strEmpCode;
	}
	public void setStrEmpCode(String strEmpCode) {
		this.strEmpCode = strEmpCode;
	}
	public String getStrIsGeneral() {
		return strIsGeneral;
	}
	public void setStrIsGeneral(String strIsGeneral) {
		this.strIsGeneral = strIsGeneral;
	}
	public String getStrTemplateId() {
		return strTemplateId;
	}
	public void setStrTemplateId(String strTemplateId) {
		this.strTemplateId = strTemplateId;
	}
	public String getStrDiagnosisType() {
		return strDiagnosisType;
	}
	public void setStrDiagnosisType(String strDiagnosisType) {
		this.strDiagnosisType = strDiagnosisType;
	}
	public String getStrLocCode() {
		return strLocCode;
	}
	public void setStrLocCode(String strLocCode) {
		this.strLocCode = strLocCode;
	}
	public String getStrUnitDays() {
		return strUnitDays;
	}
	public void setStrUnitDays(String strUnitDays) {
		this.strUnitDays = strUnitDays;
	}
	public String getStrEpiDefCloseDays() {
		return strEpiDefCloseDays;
	}
	public void setStrEpiDefCloseDays(String strEpiDefCloseDays) {
		this.strEpiDefCloseDays = strEpiDefCloseDays;
	}
	public String getStrIsRefer() {
		return strIsRefer;
	}
	public void setStrIsRefer(String strIsRefer) {
		this.strIsRefer = strIsRefer;
	}
	public String getStrIsAppointment() {
		return strIsAppointment;
	}


	public void setStrIsAppointment(String strIsAppointment) {
		this.strIsAppointment = strIsAppointment;
	}


	public String getStrIsExpiry() {
		return strIsExpiry;
	}
	public void setStrIsExpiry(String strIsExpiry) {
		this.strIsExpiry = strIsExpiry;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}
	public void setStrErrMsg(String string) {
		// TODO Auto-generated method stub
		
	}
	public String getStrIsUnit() {
		return strIsUnit;
	}
	public void setStrIsUnit(String strIsUnit) {
		this.strIsUnit = strIsUnit;
	}
	public String getStrExpiryDay() {
		return strExpiryDay;
	}
	public void setStrExpiryDay(String strExpiryDay) {
		this.strExpiryDay = strExpiryDay;
	}
	public String getStrExpiryMonth() {
		return strExpiryMonth;
	}
	public void setStrExpiryMonth(String strExpiryMonth) {
		this.strExpiryMonth = strExpiryMonth;
	}
	public String getStrRenewalType() {
		return strRenewalType;
	}
	public void setStrRenewalType(String strRenewalType) {
		this.strRenewalType = strRenewalType;
	}


	public String getStrOldUnitName() {
		return strOldUnitName;
	}


	public void setStrOldUnitName(String strOldUnitName) {
		this.strOldUnitName = strOldUnitName;
	}


	public String getStrCardPrintSetup() {
		return strCardPrintSetup;
	}


	public void setStrCardPrintSetup(String strCardPrintSetup) {
		this.strCardPrintSetup = strCardPrintSetup;
	}
	
	public String getStrMobileNo() {
		return strMobileNo;
	}


	public void setStrMobileNo(String strMobileNo) {
		this.strMobileNo = strMobileNo;
	}
	
}
