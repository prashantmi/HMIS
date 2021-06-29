package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 23-Dec-2013
 * Modified By: Raj Kumar
 * On: 18/09/2018
 * 
 */

public class DepartmentVO extends ValueObject{
	
	private String strDeptCode;
	private String strDeptName;
	private String strDeptType;
	private String strHodCode;
	private String strDeptShortName;
	private String strDeptLocCode;
	private String strAgeLimit;
	private String strGenderCode;
	private String strRemarks;
	private String strHospitalCode;

	private String strDeptNameGlobal;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	private String strOldDeptName;
	private String strLowerAgeLimit;
	private String strMaxWalkinReg;
	private String  strMaxWalkinFolloUp;
	private String strMaxWalkinPortReg;
	private String strMaxWalkinPortFollowUP;
	private String strIsCappingAllowed;

	


	public void reset()
	{
		strDeptCode="";
		strDeptName="";
		strDeptType="-1";
		strHodCode="";
		strDeptShortName="";
		strDeptLocCode="";
		strAgeLimit="";
		strGenderCode="";
		strRemarks="";
		strLowerAgeLimit="";
		strMaxWalkinReg="";
		strMaxWalkinFolloUp="";
		strMaxWalkinPortReg="";
		strMaxWalkinPortFollowUP="";
		
	}
	

	public String getStrIsCappingAllowed() {
		return strIsCappingAllowed;
	}



	public void setStrIsCappingAllowed(String strIsCappingAllowed) {
		this.strIsCappingAllowed = strIsCappingAllowed;
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

	public String getStrLowerAgeLimit() {
		return strLowerAgeLimit;
	}

	public void setStrLowerAgeLimit(String strLowerAgeLimit) {
		this.strLowerAgeLimit = strLowerAgeLimit;
	}
	
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrDeptType() {
		return strDeptType;
	}
	public void setStrDeptType(String strDeptType) {
		this.strDeptType = strDeptType;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrHodCode() {
		return strHodCode;
	}
	public void setStrHodCode(String strHodCode) {
		this.strHodCode = strHodCode;
	}
	public String getStrDeptShortName() {
		return strDeptShortName;
	}
	public void setStrDeptShortName(String strDeptShortName) {
		this.strDeptShortName = strDeptShortName;
	}
	public String getStrDeptLocCode() {
		return strDeptLocCode;
	}
	public void setStrDeptLocCode(String strDeptLocCode) {
		this.strDeptLocCode = strDeptLocCode;
	}
	public String getStrAgeLimit() {
		return strAgeLimit;
	}
	
	public void setStrAgeLimit(String strAgeLimit) {
		this.strAgeLimit = strAgeLimit;
	}
	public String getStrGenderCode() {
		return strGenderCode;
	}
	public void setStrGenderCode(String strGenderCode) {
		this.strGenderCode = strGenderCode;
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

	public String getStrDeptNameGlobal() {
		return strDeptNameGlobal;
	}

	public void setStrDeptNameGlobal(String strDeptNameGlobal) {
		this.strDeptNameGlobal = strDeptNameGlobal;
	}

	public String getStrOldDeptName() {
		return strOldDeptName;
	}

	public void setStrOldDeptName(String strOldDeptName) {
		this.strOldDeptName = strOldDeptName;
	}
	
	
	
}
