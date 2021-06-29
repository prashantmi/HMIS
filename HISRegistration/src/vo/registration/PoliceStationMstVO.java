package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * @author s.singaravelan
 * DATE : 07-May-2014
 */

public class PoliceStationMstVO extends ValueObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strPSCode;
	private String strPSShortName;
	private String strPSFullName;	

	private String strAddressLine1;
	private String strSubLocality1;
	private String strSubLocality2;
	private String strCity;
	private String strCityLoc;

	private String strDistrict;
	private String strDistrictCode;
	private String strStateCode;
	private String strCountryCode;

	private String strPinCode;
	private String strEmailId;
	private String strPhoneNo;
	private String strMobileNo;
	
	private String strIpAddress;	
	private String strIsDefault;
	
	private String strPSInchargeName;
	private String strPSConstableDesignation;	
	private String strPSConstableBadgeNo;	

	private String strHospitalCode;

	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	
	private String strOldPSFullName;

	
	public void reset()
	{
		strPSShortName="";
		strPSFullName="";
		strAddressLine1="";
		strSubLocality1="";
		strSubLocality2="";
		strCity="";
		strDistrict="";
		strDistrictCode="";
		strStateCode="";
		strPinCode="";
		strPhoneNo="";
		strMobileNo="";
		strIsDefault="";
		strPSInchargeName="";
		strPSConstableDesignation="";
		strPSConstableBadgeNo="";
		
	}	
	
	public String getStrPSCode() {
		return strPSCode;
	}
	public void setStrPSCode(String strPSCode) {
		this.strPSCode = strPSCode;
	}
	public String getStrPSShortName() {
		return strPSShortName;
	}
	public void setStrPSShortName(String strPSShortName) {
		this.strPSShortName = strPSShortName;
	}
	public String getStrPSFullName() {
		return strPSFullName;
	}
	public void setStrPSFullName(String strPSFullName) {
		this.strPSFullName = strPSFullName;
	}
	public String getStrAddressLine1() {
		return strAddressLine1;
	}
	public void setStrAddressLine1(String strAddressLine1) {
		this.strAddressLine1 = strAddressLine1;
	}
	public String getStrSubLocality1() {
		return strSubLocality1;
	}
	public void setStrSubLocality1(String strSubLocality1) {
		this.strSubLocality1 = strSubLocality1;
	}
	public String getStrSubLocality2() {
		return strSubLocality2;
	}
	public void setStrSubLocality2(String strSubLocality2) {
		this.strSubLocality2 = strSubLocality2;
	}
	public String getStrCity() {
		return strCity;
	}
	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}
	public String getStrCityLoc() {
		return strCityLoc;
	}
	public void setStrCityLoc(String strCityLoc) {
		this.strCityLoc = strCityLoc;
	}
	public String getStrDistrict() {
		return strDistrict;
	}
	public void setStrDistrict(String strDistrict) {
		this.strDistrict = strDistrict;
	}
	public String getStrDistrictCode() {
		return strDistrictCode;
	}
	public void setStrDistrictCode(String strDistrictCode) {
		this.strDistrictCode = strDistrictCode;
	}
	public String getStrStateCode() {
		return strStateCode;
	}
	public void setStrStateCode(String strStateCode) {
		this.strStateCode = strStateCode;
	}
	public String getStrCountryCode() {
		return strCountryCode;
	}
	public void setStrCountryCode(String strCountryCode) {
		this.strCountryCode = strCountryCode;
	}
	public String getStrPinCode() {
		return strPinCode;
	}
	public void setStrPinCode(String strPinCode) {
		this.strPinCode = strPinCode;
	}
	public String getStrEmailId() {
		return strEmailId;
	}
	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}
	public String getStrPhoneNo() {
		return strPhoneNo;
	}
	public void setStrPhoneNo(String strPhoneNo) {
		this.strPhoneNo = strPhoneNo;
	}
	public String getStrMobileNo() {
		return strMobileNo;
	}
	public void setStrMobileNo(String strMobileNo) {
		this.strMobileNo = strMobileNo;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	public String getStrIsDefault() {
		return strIsDefault;
	}
	public void setStrIsDefault(String strIsDefault) {
		this.strIsDefault = strIsDefault;
	}
	public String getStrPSInchargeName() {
		return strPSInchargeName;
	}
	public void setStrPSInchargeName(String strPSInchargeName) {
		this.strPSInchargeName = strPSInchargeName;
	}
	public String getStrPSConstableDesignation() {
		return strPSConstableDesignation;
	}
	public void setStrPSConstableDesignation(String strPSConstableDesignation) {
		this.strPSConstableDesignation = strPSConstableDesignation;
	}
	public String getStrPSConstableBadgeNo() {
		return strPSConstableBadgeNo;
	}
	public void setStrPSConstableBadgeNo(String strPSConstableBadgeNo) {
		this.strPSConstableBadgeNo = strPSConstableBadgeNo;
	}
	public String getStrOldPSFullName() {
		return strOldPSFullName;
	}
	public void setStrOldPSFullName(String strOldPSFullName) {
		this.strOldPSFullName = strOldPSFullName;
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
		
	
}
