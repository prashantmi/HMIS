package hisglobal.tools.tag;

import hisglobal.vo.ValueObject;

/**
 * GlobalAddressVO is the class that specifies getters and setters for all the identifiers
 * which are used for patient tile data in the DB tables. 
 * @author AHIS-G5
 * @Date 12-May-2014
 */
public class GlobalAddressVO extends ValueObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String patCrNo;
	private String serialNo;
	private String patAddType;
	private String patAddTypeCode;
	private String patAddCityLoc;
	private String patAddCityLocCode;
	private String patAddState;
	private String patAddStateCode;
	private String patAddCountry;
	private String patAddCountryCode;
	private String patAddHNo;
	private String patAddStreet;
	private String patAddDistrict;
	private String patAddDistrictCode;
	private String patAddCity;
	private String patAddPIN;
	private String patAddContactNo;
	private String isAddressDelhi;
	private String isCurrentAddress;
	private String isValid;
	private String seatId;
	private String entryDate;
	private String patAddStateName;
	private String patAddMobileNo;
	private String patAddFaxNo;
	private String patAddEmailId;
	private String patAddPhoneNo;
	private String patAddCityLocMstValue;
	private String patAddDistrictMstValue;
	private String patIsUrban;
	private String systemIPAddress;

	private String requestByAddress;
	private String strPatAddressTehsil;
	
	private String patAddLandMarks;
	private String patAddPhoneOwner;

	private String patAddressLine1;
	private String patSubLocality1;
	private String patSubLocality2;
	private String patVerificationStatus;
	private String patFBAddTypeCode;
	private String patIsLocal;
	
		
	
	public String getPatFBAddTypeCode() {
		return patFBAddTypeCode;
	}

	public void setPatFBAddTypeCode(String patFBAddTypeCode) {
		this.patFBAddTypeCode = patFBAddTypeCode;
	}

	public String getPatAddressLine1() {
		return patAddressLine1;
	}

	public void setPatAddressLine1(String patAddressLine1) {
		this.patAddressLine1 = patAddressLine1;
	}

	public String getPatSubLocality1() {
		return patSubLocality1;
	}

	public void setPatSubLocality1(String patSubLocality1) {
		this.patSubLocality1 = patSubLocality1;
	}

	public String getPatSubLocality2() {
		return patSubLocality2;
	}

	public void setPatSubLocality2(String patSubLocality2) {
		this.patSubLocality2 = patSubLocality2;
	}
	public String getPatVerificationStatus() {
		return patVerificationStatus;
	}

	public void setPatVerificationStatus(String patVerificationStatus) {
		this.patVerificationStatus = patVerificationStatus;
	}

	public String getRequestByAddress() {
		return requestByAddress;
	}

	public void setRequestByAddress(String requestByAddress) {
		this.requestByAddress = requestByAddress;
	}


	public String getSystemIPAddress() {
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress) {
		this.systemIPAddress = systemIPAddress;
	}

	public String getPatIsUrban() {
		return patIsUrban;
	}

	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}

	public String getPatAddMobileNo()
	{
		return patAddMobileNo;
	}

	public void setPatAddMobileNo(String patAddMobileNo)
	{
		this.patAddMobileNo = patAddMobileNo;
	}

	public String getPatAddFaxNo()
	{
		return patAddFaxNo;
	}

	public void setPatAddFaxNo(String patAddFaxNo)
	{
		this.patAddFaxNo = patAddFaxNo;
	}

	public String getPatAddEmailId()
	{
		return patAddEmailId;
	}

	public void setPatAddEmailId(String patAddEmailId)
	{
		this.patAddEmailId = patAddEmailId;
	}


	public String getPatAddStateName()
	{
		return patAddStateName;
	}

	public void setPatAddStateName(String patAddStateName)
	{
		this.patAddStateName = patAddStateName;
	}

	/**
	 * Retrieves patAddCityLoc.
	 * @return Value of patAddCityLoc.	
	 */
	public String getPatAddCityLoc()
	{
		return patAddCityLoc;
	}

	/**
	 * Sets patAddCityLoc.
	 * @param patAddCityLoc
	 */
	public void setPatAddCityLoc(String patAddCityLoc)
	{
		this.patAddCityLoc = patAddCityLoc;
	}

	/**
	 * Retrieves patAddCityLocCode.
	 * @return Value of patAddCityLocCode.	
	 */
	public String getPatAddCityLocCode()
	{
		return patAddCityLocCode;
	}

	/**
	 * Sets patAddCityLocCode.
	 * @param patAddCityLocCode
	 */
	public void setPatAddCityLocCode(String patAddCityLocCode)
	{
		this.patAddCityLocCode = patAddCityLocCode;
	}

	/**
	 * Sets isCurrentAddress.
	 * @param isCurrentAddress
	 */
	public void setIsCurrentAddress(String isCurrentAddress)
	{
		this.isCurrentAddress = isCurrentAddress;
	}

	/**
	 * Retrieves isCurrentAddress.
	 * @return Value of isCurrentAddress.	
	 */
	public String getIsCurrentAddress()
	{
		return this.isCurrentAddress;
	}

	/**
	 * Sets patAddType.
	 * @param patAddType
	 */
	public void setPatAddType(String patAddType)
	{
		this.patAddType = patAddType;
	}

	/**
	 * Retrieves patAddType.
	 * @return Value of patAddType.	
	 */
	public String getPatAddType()
	{
		return patAddType;
	}

	/**
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets entryDate.
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isValid.
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patCrNo.
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves seatId.
	 * @return Value of seatId.	
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Sets patAddContactNo.
	 * @param patAddContactNo
	 */
	public void setPatAddContactNo(String patAddContactNo)
	{
		this.patAddContactNo = patAddContactNo;
	}

	/**
	 * Sets patAddCountryCode.
	 * @param patAddCountryCode
	 */
	public void setPatAddCountryCode(String patAddCountryCode)
	{
		this.patAddCountryCode = patAddCountryCode;
	}

	/**
	 * Sets patAddStateCode.
	 * @param patAddStateCode
	 */
	public void setPatAddStateCode(String patAddStateCode)
	{
		this.patAddStateCode = patAddStateCode;
	}

	/**
	 * Retrieves patAddTypeCode.
	 * @return Value of patAddTypeCode.	
	 */
	public String getPatAddTypeCode()
	{
		return patAddTypeCode;
	}

	/**
	 * Sets patAddTypeCode.
	 * @param patAddTypeCode
	 */
	public void setPatAddTypeCode(String patAddTypeCode)
	{
		this.patAddTypeCode = patAddTypeCode;
	}

	/**
	 * Sets isAddressDelhi.
	 * @param isAddressDelhi
	 */
	public void setIsAddressDelhi(String isAddressDelhi)
	{
		this.isAddressDelhi = isAddressDelhi;
	}

	/**
	 * Sets patAddStreet.
	 * @param patAddStreet
	 */
	public void setPatAddStreet(String patAddStreet)
	{
		this.patAddStreet = patAddStreet;
	}

	/**
	 * Sets patAddState.
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.patAddState = patAddState;
	}

	/**
	 * Sets patAddPIN.
	 * @param patAddPIN
	 */
	public void setPatAddPIN(String patAddPIN)
	{
		this.patAddPIN = patAddPIN;
	}

	/**
	 * Sets patAddHNo.
	 * @param patAddHNo
	 */
	public void setPatAddHNo(String patAddHNo)
	{
		this.patAddHNo = patAddHNo;
	}

	/**
	 * Sets patAddDistrict.
	 * @param patAddDistrict
	 */
	public void setPatAddDistrict(String patAddDistrict)
	{
		this.patAddDistrict = patAddDistrict;
	}

	/**
	 * Sets patAddCountry.
	 * @param patAddCountry
	 */
	public void setPatAddCountry(String patAddCountry)
	{
		this.patAddCountry = patAddCountry;
	}

	/**
	 * Sets patAddCity.
	 * @param patAddCity
	 */
	public void setPatAddCity(String patAddCity)
	{
		this.patAddCity = patAddCity;
	}

	/**
	 * Retrieves patAddContactNo.
	 * @return Value of patAddContactNo.	
	 */
	public String getPatAddContactNo()
	{
		return patAddContactNo;
	}

	/**
	 * Retrieves patAddCountryCode.
	 * @return Value of patAddCountryCode.	
	 */
	public String getPatAddCountryCode()
	{
		return patAddCountryCode;
	}

	/**
	 * Retrieves patAddStateCode.
	 * @return Value of patAddStateCode.	
	 */
	public String getPatAddStateCode()
	{
		return patAddStateCode;
	}

	/**
	 * Retrieves isAddressDelhi.
	 * @return Value of isAddressDelhi.	
	 */
	public String getIsAddressDelhi()
	{
		return isAddressDelhi;
	}

	/**
	 * Retrieves patAddStreet.
	 * @return Value of patAddStreet.	
	 */
	public String getPatAddStreet()
	{
		return patAddStreet;
	}

	/**
	 * Retrieves patAddState.
	 * @return Value of patAddState.	
	 */
	public String getPatAddState()
	{
		return patAddState;
	}

	/**
	 * Retrieves patAddPIN.
	 * @return Value of patAddPIN.	
	 */
	public String getPatAddPIN()
	{
		return patAddPIN;
	}

	/**
	 * Retrieves patAddHNo.
	 * @return Value of patAddHNo.	
	 */
	public String getPatAddHNo()
	{
		return patAddHNo;
	}

	/**
	 * Retrieves patAddDistrict.
	 * @return Value of patAddDistrict.	
	 */
	public String getPatAddDistrict()
	{
		return patAddDistrict;
	}

	/**
	 * Retrieves patAddCountry.
	 * @return Value of patAddCountry.	
	 */
	public String getPatAddCountry()
	{
		return patAddCountry;
	}

	/**
	 * Retrieves patAddCity.
	 * @return Value of patAddCity.	
	 */
	public String getPatAddCity()
	{
		return patAddCity;
	}

	/**
	 * Retrieves serialNo.
	 * @return Value of serialNo.	
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * Sets serialNo.
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getPatAddPhoneNo()
	{
		return patAddPhoneNo;
	}

	public void setPatAddPhoneNo(String patAddPhoneNo)
	{
		this.patAddPhoneNo = patAddPhoneNo;
	}

	public String getPatAddDistrictCode() {
		return patAddDistrictCode;
	}

	public void setPatAddDistrictCode(String patAddDistrictCode) {
		this.patAddDistrictCode = patAddDistrictCode;
	}

	public String getPatAddCityLocMstValue() {
		return patAddCityLocMstValue;
	}

	public void setPatAddCityLocMstValue(String patAddCityLocMstValue) {
		this.patAddCityLocMstValue = patAddCityLocMstValue;
	}

	public String getPatAddDistrictMstValue() {
		return patAddDistrictMstValue;
	}

	public void setPatAddDistrictMstValue(String patAddDistrictMstValue) {
		this.patAddDistrictMstValue = patAddDistrictMstValue;
	}

	public String getStrPatAddressTehsil() {
		return strPatAddressTehsil;
	}

	public void setStrPatAddressTehsil(String strPatAddressTehsil) {
		this.strPatAddressTehsil = strPatAddressTehsil;
	}

	public String getPatAddLandMarks() {
		return patAddLandMarks;
	}

	public void setPatAddLandMarks(String patAddLandMarks) {
		this.patAddLandMarks = patAddLandMarks;
	}

	public String getPatAddPhoneOwner() {
		return patAddPhoneOwner;
	}

	public void setPatAddPhoneOwner(String patAddPhoneOwner) {
		this.patAddPhoneOwner = patAddPhoneOwner;
	}

	public String getPatIsLocal() {
		return patIsLocal;
	}

	public void setPatIsLocal(String patIsLocal) {
		this.patIsLocal = patIsLocal;
	}	
}