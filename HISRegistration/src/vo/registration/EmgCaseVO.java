package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 05-May-2014
 */

public class EmgCaseVO extends ValueObject{
	
	private String strEmgCaseCode;
	private String strEmgCaseDesc;
	private String strIsMlcRequired;

	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	private String caseType;
	private String strOldEmgCaseDesc;

	
	public void reset()
	{
		strEmgCaseCode="";
		strEmgCaseDesc="";
		strIsValid="1";
	}	
	
	
	public String getStrEmgCaseCode() {
		return strEmgCaseCode;
	}
	public void setStrEmgCaseCode(String strEmgCaseCode) {
		this.strEmgCaseCode = strEmgCaseCode;
	}
	public String getStrEmgCaseDesc() {
		return strEmgCaseDesc;
	}
	public void setStrEmgCaseDesc(String strEmgCaseDesc) {
		this.strEmgCaseDesc = strEmgCaseDesc;
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
	public String getStrOldEmgCaseDesc() {
		return strOldEmgCaseDesc;
	}
	public void setStrOldEmgCaseDesc(String strOldEmgCaseDesc) {
		this.strOldEmgCaseDesc = strOldEmgCaseDesc;
	}


	public String getStrIsMlcRequired() {
		return strIsMlcRequired;
	}


	public void setStrIsMlcRequired(String strIsMlcRequired) {
		this.strIsMlcRequired = strIsMlcRequired;
	}

	public String getCaseType() {
		
		return caseType;
	}
	public void setCaseType(String caseType){
		
		this.caseType=caseType;
	}
	
}
