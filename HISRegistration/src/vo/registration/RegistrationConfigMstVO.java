package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * 
 */

public class RegistrationConfigMstVO extends ValueObject{
	
	private String strtempReg;
	private String strmodechoice;
	private String strcrossconsult;
	private String strappointmentbs;
	private String strduplicacyChk;
	private String stradharintegration;
	private String stremgRenewal;
	private String strNoOfHrs;
	private String strseniorCitizenCatCode;
	private String strseniorCitizenAgeLimit;
	private String strmobileSearch;
	private String strimgUploadMode;
	private String strconfigGroup;
	//private String strHl7Code;

	//private String strRemarks;
	private String strHospitalCode;
	private int temp;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	//private String strOldInstituteName;

	
	public void reset()
	{
		strtempReg="0";
		strcrossconsult="0";
		strappointmentbs="-1";
		strduplicacyChk="0";
		stradharintegration="";
		stremgRenewal="";
		strNoOfHrs="";
		strseniorCitizenCatCode="-1";
		strseniorCitizenAgeLimit="";
		strmobileSearch="";
		strimgUploadMode="";
		//strContactPerson="";
		//strRemarks="";		
	}

	public String getStrtempReg() {
		return strtempReg;
	}

	public void setStrtempReg(String strtempReg) {
		this.strtempReg = strtempReg;
	}
	public int getStrtemp() {
		return temp;
	}

	public void setStrtemp(int temp) {
		this.temp = temp;
	}

	
	public String getStrmodechoice() {
		return strmodechoice;
	}

	public void setStrmodechoice(String strmodechoice) {
		this.strmodechoice = strmodechoice;
	}

	public String getStrcrossconsult() {
		return strcrossconsult;
	}

	public void setStrcrossconsult(String strcrossconsult) {
		this.strcrossconsult = strcrossconsult;
	}

	public String getStrappointmentbs() {
		return strappointmentbs;
	}
	
	public void setStrappointmentbs(String strappointmentbs) {
		this.strappointmentbs = strappointmentbs;
	}

	public String getStrduplicacyChk() {
		return strduplicacyChk;
	}

	public void setStrduplicacyChk(String strduplicacyChk) {
		this.strduplicacyChk = strduplicacyChk;
	}

	public String getStradharintegration() {
		return stradharintegration;
	}

	public void setStradharintegration(String stradharintegration) {
		this.stradharintegration = stradharintegration;
	}

	public String getStremgRenewal() {
		return stremgRenewal;
	}

	public void setStremgRenewal(String stremgRenewal) {
		this.stremgRenewal = stremgRenewal;
	}

	public String getStrNoOfHrs() {
		return strNoOfHrs;
	}

	public void setStrNoOfHrs(String strNoOfHrs) {
		this.strNoOfHrs = strNoOfHrs;
	}

	public String getStrseniorCitizenCatCode() {
		return strseniorCitizenCatCode;
	}
	
	public void setStrseniorCitizenCatCode(String strseniorCitizenCatCode) {
		this.strseniorCitizenCatCode = strseniorCitizenCatCode;
	}

	public String getStrseniorCitizenAgeLimit() {
		return strseniorCitizenAgeLimit;
	}

	public void setStrseniorCitizenAgeLimit(String strseniorCitizenAgeLimit) {
		this.strseniorCitizenAgeLimit = strseniorCitizenAgeLimit;
	}

	public String getStrmobileSearch() {
		return strmobileSearch;
	}
	
	public void setStrmobileSearch(String strmobileSearch) {
		this.strmobileSearch = strmobileSearch;
	}
	
	public String getStrimgUploadMode() {
		return strimgUploadMode;
	}
	
	public void setStrimgUploadMode(String strimgUploadMode) {
		this.strimgUploadMode = strimgUploadMode;
	}
	
	/*public String getStrContactPerson() {
		return strContactPerson;
	}
	
	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	public String getStrHl7Code() {
		return strHl7Code;
	}
	
	public void setStrHl7Code(String strHl7Code) {
		this.strHl7Code = strHl7Code;
	}
	
	public String getStrRemarks() {
		return strRemarks;
	}
	
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}*/
	
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

	public void setStrconfigGroup(String strconfigGroup) {
		this.strconfigGroup = strconfigGroup;
	}
	
	public String getStrconfigGroup() {
		return strconfigGroup;
	}

	
}
