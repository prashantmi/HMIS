/**
 * 
 */
package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 11-Feb-2013
 */
public class DisclaimerVO extends ValueObject{
	
	private String strDisclaimerCode;
	private String strDisclaimerType;
	private String strDeptCode;
	private String strUsabilityFlag;
	private String strDeptUnitCode;
	private String strDisclaimerDesc1;
	private String strDisclaimerDesc2;
	private String strDisclaimerDesc3;
	private String strIsDefault;
	private String strIsHeader;
	private String strAlignment;
	
	private String strGeneralDisclaimerDesc1;	
	private String strGeneralDisclaimerDesc2;
	private String strGeneralDisclaimerDesc3;
	private String strSpecialDisclaimerDesc1;	
	private String strSpecialDisclaimerDesc2;
	private String strSpecialDisclaimerDesc3;
	private String strCasualityDisclaimerDesc1;	
	private String strCasualityDisclaimerDesc2;
	private String strCasualityDisclaimerDesc3;

	private String strHospitalCode;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;	
	
	private String strDeptName;
	private String strUnitName;	
	private String strSelectDisclaimer;
	
	
	public void reset()
	{
		this.strDeptCode="-1";
		this.strDeptUnitCode="-1";
		this.strAlignment="-1";
		this.strIsHeader="0";
		this.strUsabilityFlag="-1";
		this.strDisclaimerDesc1="";
		this.strDisclaimerDesc2="";
		this.strDisclaimerDesc3="";
	}
	

	public String getStrDisclaimerCode() {
		return strDisclaimerCode;
	}
	public void setStrDisclaimerCode(String strDisclaimerCode) {
		this.strDisclaimerCode = strDisclaimerCode;
	}
	public String getStrDisclaimerType() {
		return strDisclaimerType;
	}
	public void setStrDisclaimerType(String strDisclaimerType) {
		this.strDisclaimerType = strDisclaimerType;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrUsabilityFlag() {
		return strUsabilityFlag;
	}
	public void setStrUsabilityFlag(String strUsabilityFlag) {
		this.strUsabilityFlag = strUsabilityFlag;
	}
	public String getStrDeptUnitCode() {
		return strDeptUnitCode;
	}
	public void setStrDeptUnitCode(String strDeptUnitCode) {
		this.strDeptUnitCode = strDeptUnitCode;
	}
	public String getStrDisclaimerDesc1() {
		return strDisclaimerDesc1;
	}
	public void setStrDisclaimerDesc1(String strDisclaimerDesc1) {
		this.strDisclaimerDesc1 = strDisclaimerDesc1;
	}
	public String getStrDisclaimerDesc2() {
		return strDisclaimerDesc2;
	}
	public void setStrDisclaimerDesc2(String strDisclaimerDesc2) {
		this.strDisclaimerDesc2 = strDisclaimerDesc2;
	}
	public String getStrDisclaimerDesc3() {
		return strDisclaimerDesc3;
	}
	public void setStrDisclaimerDesc3(String strDisclaimerDesc3) {
		this.strDisclaimerDesc3 = strDisclaimerDesc3;
	}
	public String getStrIsDefault() {
		return strIsDefault;
	}
	public void setStrIsDefault(String strIsDefault) {
		this.strIsDefault = strIsDefault;
	}
	public String getStrIsHeader() {
		return strIsHeader;
	}
	public void setStrIsHeader(String strIsHeader) {
		this.strIsHeader = strIsHeader;
	}
	public String getStrAlignment() {
		return strAlignment;
	}
	public void setStrAlignment(String strAlignment) {
		this.strAlignment = strAlignment;
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
	public String getStrGeneralDisclaimerDesc1() {
		return strGeneralDisclaimerDesc1;
	}
	public void setStrGeneralDisclaimerDesc1(String strGeneralDisclaimerDesc1) {
		this.strGeneralDisclaimerDesc1 = strGeneralDisclaimerDesc1;
	}
	public String getStrGeneralDisclaimerDesc2() {
		return strGeneralDisclaimerDesc2;
	}
	public void setStrGeneralDisclaimerDesc2(String strGeneralDisclaimerDesc2) {
		this.strGeneralDisclaimerDesc2 = strGeneralDisclaimerDesc2;
	}
	public String getStrGeneralDisclaimerDesc3() {
		return strGeneralDisclaimerDesc3;
	}
	public void setStrGeneralDisclaimerDesc3(String strGeneralDisclaimerDesc3) {
		this.strGeneralDisclaimerDesc3 = strGeneralDisclaimerDesc3;
	}
	public String getStrSpecialDisclaimerDesc1() {
		return strSpecialDisclaimerDesc1;
	}
	public void setStrSpecialDisclaimerDesc1(String strSpecialDisclaimerDesc1) {
		this.strSpecialDisclaimerDesc1 = strSpecialDisclaimerDesc1;
	}
	public String getStrSpecialDisclaimerDesc2() {
		return strSpecialDisclaimerDesc2;
	}
	public void setStrSpecialDisclaimerDesc2(String strSpecialDisclaimerDesc2) {
		this.strSpecialDisclaimerDesc2 = strSpecialDisclaimerDesc2;
	}
	public String getStrSpecialDisclaimerDesc3() {
		return strSpecialDisclaimerDesc3;
	}
	public void setStrSpecialDisclaimerDesc3(String strSpecialDisclaimerDesc3) {
		this.strSpecialDisclaimerDesc3 = strSpecialDisclaimerDesc3;
	}
	public String getStrCasualityDisclaimerDesc1() {
		return strCasualityDisclaimerDesc1;
	}
	public void setStrCasualityDisclaimerDesc1(String strCasualityDisclaimerDesc1) {
		this.strCasualityDisclaimerDesc1 = strCasualityDisclaimerDesc1;
	}
	public String getStrCasualityDisclaimerDesc2() {
		return strCasualityDisclaimerDesc2;
	}
	public void setStrCasualityDisclaimerDesc2(String strCasualityDisclaimerDesc2) {
		this.strCasualityDisclaimerDesc2 = strCasualityDisclaimerDesc2;
	}
	public String getStrCasualityDisclaimerDesc3() {
		return strCasualityDisclaimerDesc3;
	}
	public void setStrCasualityDisclaimerDesc3(String strCasualityDisclaimerDesc3) {
		this.strCasualityDisclaimerDesc3 = strCasualityDisclaimerDesc3;
	}


	public String getStrDeptName() {
		return strDeptName;
	}


	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}


	public String getStrUnitName() {
		return strUnitName;
	}


	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}


	public String getStrSelectDisclaimer() {
		return strSelectDisclaimer;
	}


	public void setStrSelectDisclaimer(String strSelectDisclaimer) {
		this.strSelectDisclaimer = strSelectDisclaimer;
	}

}
