package vo.registration;

public class PatientModificationAuditVO 
{
	private AddressVO patAddress = new AddressVO();
	private String patCrNo;
	private String patMaritalStatus;
	private String patMaritalStatusCode;
	private String patReligion;
	private String patReligionCode;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patFirstNameInMultiLang;
	private String patMiddleNameInMultiLang;
	private String patLastNameInMultiLang;
	private String patGender;
	private String patGenderCode;
	private String patAge;
	private String patAgeUnit;
	private String patAgeWithUnit;
	private String patDOB;
	private String patIsUrban;
	private String patMonthlyIncome;
	private String isActualDob;//if (age provided) isActualDob=0 else isActualDob=1
	private String prevCrNo;
	private String patIdNo;
	private String patIdMark1;
	private String patIdMark2;
	private String patNationalityCode;
	private String patNationality;
	private String isUnknown;
	private String seatId;
	private String systemIPAddress;//IP address of the system from which the details are entered
	private String patMotherName;
	private String patHusbandName;
	private String patNationalId;
	private String isImageUploaded;
	private String patBloodGroup;
	private String isNewBorn = "0";
	private String patMotherCrNo;
	private String patGuardianName;
	private String patEducation;
	private String patEducationCode;
	private String patOccupation;
	private String patFatherOccupation;
	private String patHusbandOccupation;
	private String patNickName;
	private String patCardNo;
	private String verificationDocumentId;
	private String isBroughtDead;
	private String ipAddress;
	private String patOccupationDesc;
	private String requestBy;
	private String requestRelation;
	private String requestByName;
	private String requestByAddress;
	private String deathDateTime;
	private String patCasteCode;
	private String patCaste;
	private String isDuplicate="";
	private String patBirthPlace;
	private String patDocType;
	private String patCatCardNo;
	private String patAadharNo;
	private String patSplVulnerability;
	private String patIsDead;
	private String strHospCode;
	private String patAppellationCode;
	private String suffixCode;
	private String patNameType;



	public String getPatSplVulnerability() {
		return patSplVulnerability;
	}
	public void setPatSplVulnerability(String patSplVulnerability) {
		this.patSplVulnerability = patSplVulnerability;
	}
	public String getPatIsDead() {
		return patIsDead;
	}
	public void setPatIsDead(String patIsDead) {
		this.patIsDead = patIsDead;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	

	public String getPatAppellationCode() {
		return patAppellationCode;
	}
	public void setPatAppellationCode(String patAppellationCode) {
		this.patAppellationCode = patAppellationCode;
	}
	public String getSuffixCode() {
		return suffixCode;
	}
	public void setSuffixCode(String suffixCode) {
		this.suffixCode = suffixCode;
	}
	public String getPatAddressLine1() {
		return getPatAddress().getPatAddressLine1();
	}
	public String getPatSubLocality1() {
		return getPatAddress().getPatSubLocality1();
	}
	public String getPatSubLocality2() {
		return getPatAddress().getPatSubLocality2();
	}
	public String getPatIsLocal() {
		return getPatAddress().getPatIsLocal();
	}

	public String getPatNameType() {
		return patNameType;
	}
	public void setPatNameType(String patNameType) {
		this.patNameType = patNameType;
	}

	public String getPatVerificationStatus() {
		return getPatAddress().getPatVerificationStatus();
	}
	public String getPatCasteCode() {
		return patCasteCode;
	}
	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
	}
	public String getPatCaste() {
		return patCaste;
	}
	public void setPatCaste(String patCaste) {
		this.patCaste = patCaste;
	}


	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getRequestRelation() {
		return requestRelation;
	}

	public void setRequestRelation(String requestRelation) {
		this.requestRelation = requestRelation;
	}

	public String getRequestByName() {
		return requestByName;
	}

	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}

	
	public String getRequestByAddress() {
		return requestByAddress;
	}

	public void setRequestByAddress(String requestByAddress) {
		this.requestByAddress = requestByAddress;
	}

	public String getIsBroughtDead()
	{
		return isBroughtDead;
	}

	public void setIsBroughtDead(String isBroughtDead)
	{
		this.isBroughtDead = isBroughtDead;
	}

	public String getVerificationDocumentId()
	{
		return verificationDocumentId;
	}

	public void setVerificationDocumentId(String verificationDocumentId)
	{
		this.verificationDocumentId = verificationDocumentId;
	}



	public String getPatCardNo()
	{
		return patCardNo;
	}

	public void setPatCardNo(String patCardNo)
	{
		this.patCardNo = patCardNo;
	}

	public String getPatFatherOccupation()
	{
		return patFatherOccupation;
	}

	public void setPatFatherOccupation(String patFatherOccupation)
	{
		this.patFatherOccupation = patFatherOccupation;
	}

	public String getPatHusbandOccupation()
	{
		return patHusbandOccupation;
	}

	public void setPatHusbandOccupation(String patHusbandOccupation)
	{
		this.patHusbandOccupation = patHusbandOccupation;
	}

	public String getPatNickName()
	{
		return patNickName;
	}

	public void setPatNickName(String patNickName)
	{
		this.patNickName = patNickName;
	}

	public String getPatOccupation()
	{
		return patOccupation;
	}

	public void setPatOccupation(String patOccupation)
	{
		this.patOccupation = patOccupation;
	}




	public String getPatMotherName()
	{
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName)
	{
		this.patMotherName = patMotherName;
	}

	public String getPatNationalId()
	{
		return patNationalId;
	}

	public void setPatNationalId(String patNationalId)
	{
		this.patNationalId = patNationalId;
	}

	public String getIsImageUploaded()
	{
		return isImageUploaded;
	}

	public void setIsImageUploaded(String isImageUploaded)
	{
		this.isImageUploaded = isImageUploaded;
	}



	/**
	 * Sets systemIPAddress.
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	/**
	 * Retrieves systemIPAddress.
	 * @return Value of systemIPAddress.	
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}





	/**
	 * Sets prevCrNo.
	 * @param prevCrNo
	 */
	public void setPrevCrNo(String prevCrNo)
	{
		this.prevCrNo = prevCrNo;
	}

	/**
	 * Retrieves prevCrNo.
	 * @return Value of prevCrNo.	
	 */
	public String getPrevCrNo()
	{
		return prevCrNo;
	}


	/**
	 * Sets patReligionCode.
	 * @param patReligionCode
	 */
	public void setPatReligionCode(String patReligionCode)
	{
		this.patReligionCode = patReligionCode;
	}

	/**
	 * Retrieves patReligionCode.
	 * @return Value of patReligionCode.	
	 */
	public String getPatReligionCode()
	{
		return patReligionCode;
	}

	/**
	 * Sets patReligion.
	 * @param patReligion
	 */
	public void setPatReligion(String patReligion)
	{
		this.patReligion = patReligion;
	}

	/**
	 * Retrieves patReligion.
	 * @return Value of patReligion.	
	 */
	public String getPatReligion()
	{
		return patReligion;
	}






	/**
	 * Sets patMonthlyIncome.
	 * @param patMonthlyIncome
	 */
	public void setPatMonthlyIncome(String patMonthlyIncome)
	{
		this.patMonthlyIncome = patMonthlyIncome;
	}

	/**
	 * Retrieves patMonthlyIncome.
	 * @return Value of patMonthlyIncome.	
	 */
	public String getPatMonthlyIncome()
	{
		return patMonthlyIncome;
	}

	/**
	 * Sets patMomName.
	 * @param patMomName
	 */
	/*public void setPatMomName( String patMomName )
	{
		this.patMomName = patMomName;
	}*/

	/**
	 * Retrieves patMomName.
	 * @return Value of patMomName.	
	 */
	/*public String getPatMomName( )
	{
		return patMomName;
	}
	 */
	/**
	 * Sets patMiddleName.
	 * @param patMiddleName
	 */
	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	/**
	 * Retrieves patMiddleName.
	 * @return Value of patMiddleName.	
	 */
	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	/**
	 * Sets patMaritalStatusCode.
	 * @param patMaritalStatusCode
	 */
	public void setPatMaritalStatusCode(String patMaritalStatusCode)
	{
		this.patMaritalStatusCode = patMaritalStatusCode;
	}

	/**
	 * Retrieves patMaritalStatusCode.
	 * @return Value of patMaritalStatusCode.	
	 */
	public String getPatMaritalStatusCode()
	{
		return patMaritalStatusCode;
	}

	/**
	 * Sets patMaritalStatusCode.
	 * @param patMaritalStatusCode
	 */
	public void setPatMaritalStatus(String patMaritalStatus)
	{
		this.patMaritalStatus = patMaritalStatus;
	}

	/**
	 * Retrieves patMaritalStatusCode.
	 * @return Value of patMaritalStatusCode.	
	 */
	public String getPatMaritalStatus()
	{
		return patMaritalStatus;
	}

	/**
	 * Sets patLastName.
	 * @param patLastName
	 */
	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	/**
	 * Retrieves patLastName.
	 * @return Value of patLastName.	
	 */
	public String getPatLastName()
	{
		return patLastName;
	}

	public String getPatFirstNameInMultiLang() {
		return patFirstNameInMultiLang;
	}
	public void setPatFirstNameInMultiLang(String patFirstNameInMultiLang) {
		this.patFirstNameInMultiLang = patFirstNameInMultiLang;
	}
	public String getPatMiddleNameInMultiLang() {
		return patMiddleNameInMultiLang;
	}
	public void setPatMiddleNameInMultiLang(String patMiddleNameInMultiLang) {
		this.patMiddleNameInMultiLang = patMiddleNameInMultiLang;
	}
	public String getPatLastNameInMultiLang() {
		return patLastNameInMultiLang;
	}
	public void setPatLastNameInMultiLang(String patLastNameInMultiLang) {
		this.patLastNameInMultiLang = patLastNameInMultiLang;
	}
	/**
	 * Sets patIsUrban.
	 * @param patIsUrban
	 */
	public void setPatIsUrban(String patIsUrban)
	{
		this.patIsUrban = patIsUrban;
	}

	/**
	 * Retrieves patIsUrban.
	 * @return Value of patIsUrban.	
	 */
	public String getPatIsUrban()
	{
		return patIsUrban;
	}

	/**
	 * Sets patIdNo.
	 * @param patIdNo
	 */
	public void setPatIdNo(String patIdNo)
	{
		this.patIdNo = patIdNo;
	}

	/**
	 * Retrieves patIdNo.
	 * @return Value of patIdNo.	
	 */
	public String getPatIdNo()
	{
		return patIdNo;
	}

	/**
	 * Sets patGuardianName.
	 * @param patGuardianName
	 */
	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	/**
	 * Retrieves patGuardianName.
	 * @return Value of patGuardianName.	
	 */
	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	/**
	 * Sets patGenderCode.
	 * @param patGenderCode
	 */
	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}

	/**
	 * Retrieves patGenderCode.
	 * @return Value of patGenderCode.	
	 */
	public String getPatGenderCode()
	{
		return patGenderCode;
	}

	/**
	 * Sets patGender.
	 * @param patGender
	 */
	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	/**
	 * Retrieves patGender.
	 * @return Value of patGender.	
	 */
	public String getPatGender()
	{
		return patGender;
	}

	public String getPatEducation() {
		return patEducation;
	}
	public void setPatEducation(String patEducation) {
		this.patEducation = patEducation;
	}
	public String getPatEducationCode() {
		return patEducationCode;
	}
	public void setPatEducationCode(String patEducationCode) {
		this.patEducationCode = patEducationCode;
	}
	/**
	 * Sets patFirstName.
	 * @param patFirstName
	 */
	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	/**
	 * Retrieves patFirstName.
	 * @return Value of patFirstName.	
	 */
	public String getPatFirstName()
	{
		return patFirstName;
	}

	/**
	 * Sets patDOB.
	 * @param patDOB
	 */
	
	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}

	public String getPatAgeWithUnit() {
		return patAgeWithUnit;
	}
	public void setPatAgeWithUnit(String patAgeWithUnit) {
		this.patAgeWithUnit = patAgeWithUnit;
	}
	/**
	 * Retrieves patDOB.
	 * @return Value of patDOB.	
	 */
	public String getPatDOB()
	{
		return patDOB;
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
	 * Retrieves patCrNo.
	 * @return Value of patCrNo.	
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}


	/**
	 * Retrieves patAddress.
	 * @return Value of patAddress.	
	 */
	public AddressVO getPatAddress()
	{
		return patAddress;
	}

	/**
	 * Sets patAddress.
	 * @param patAddress
	 */
	public void setPatAddress(AddressVO patAddress)
	{
		this.patAddress = patAddress;
	}
	



	/**
	 * Sets isUnknown.
	 * @param isUnknown
	 */
	public void setIsUnknown(String isUnknown)
	{
		this.isUnknown = isUnknown;
	}

	/**
	 * Retrieves isUnknown.
	 * @return Value of isUnknown.	
	 */
	public String getIsUnknown()
	{
		return isUnknown;
	}



	/**
	 * Sets isActualDob.
	 * @param isActualDob
	 */
	public void setIsActualDob(String isActualDob)
	{
		this.isActualDob = isActualDob;
	}

	/**
	 * Retrieves isActualDob.
	 * @return Value of isActualDob.	
	 */
	public String getIsActualDob()
	{
		return isActualDob;
	}



	/**
	 * Sets departmentCode.
	 * @param departmentCode
	 */
	/*public void setDepartmentCode( String departmentCode )
	{
		this.departmentCode = departmentCode;
	}*/

	/**
	 * Retrieves departmentCode.
	 * @return Value of departmentCode.	
	 */
	/*public String getDepartmentCode( )
	{
		return departmentCode;
	}*/

	/**
	 * Sets department.
	 * @param department
	 */
	/*public void setDepartment( String department )
	{
		this.department = department;
	}*/

	/**
	 * Retrieves department.
	 * @return Value of department.	
	 */
	/*public String getDepartment( )
	{
		return department;
	}*/

	//	 populate addressVO
	/**
	 * Retrieves isAddressDelhi.
	 * @return Value of isAddressDelhi.	
	 */
	public String getIsAddressDelhi()
	{
		return getPatAddress().getIsAddressDelhi();
	}

	/**
	 * Sets isAddressDelhi.
	 * @param isAddressDelhi
	 */
	public void setIsAddressDelhi(String isAddressDelhi)
	{
		this.getPatAddress().setIsAddressDelhi(isAddressDelhi);
	}

	/**
	 * Retrieves isCurrentAddress.
	 * @return Value of isCurrentAddress.	
	 */
	public String getIsCurrentAddress()
	{
		return getPatAddress().getIsCurrentAddress();
	}

	/**
	 * Sets isCurrentAddress.
	 * @param isCurrentAddress
	 */
	public void setIsCurrentAddress(String isCurrentAddress)
	{
		this.getPatAddress().setIsCurrentAddress(isCurrentAddress);
	}

	/**
	 * Retrieves patAddCity.
	 * @return Value of patAddCity.	
	 */
	public String getPatAddCity()
	{
		return getPatAddress().getPatAddCity();
	}

	/**
	 * Sets patAddCity.
	 * @param patAddCity
	 */
	public void setPatAddCity(String patAddCity)
	{
		this.getPatAddress().setPatAddCity(patAddCity);
	}

	/**
	 * Retrieves patAddCityLoc.
	 * @return Value of patAddCityLoc.	
	 */
	public String getPatAddCityLoc()
	{
		return getPatAddress().getPatAddCityLoc();
	}

	/**
	 * Sets patAddCityLoc.
	 * @param patAddCityLoc
	 */
	public void setPatAddCityLoc(String patAddCityLoc)
	{
		this.getPatAddress().setPatAddCityLoc(patAddCityLoc);
	}

	/**
	 * Retrieves patAddCityLocCode.
	 * @return Value of patAddCityLocCode.	
	 */
	public String getPatAddCityLocCode()
	{
		return getPatAddress().getPatAddCityLocCode();
	}

	/**
	 * Sets patAddCityLocCode.
	 * @param patAddCityLocCode
	 */
	public void setPatAddCityLocCode(String patAddCityLocCode)
	{
		this.getPatAddress().setPatAddCityLocCode(patAddCityLocCode);
	}

	/**
	 * Retrieves patAddContactNo.
	 * @return Value of patAddContactNo.	
	 */
	public String getPatAddContactNo()
	{
		return getPatAddress().getPatAddContactNo();
	}

	/**
	 * Sets patAddContactNo.
	 * @param patAddContactNo
	 */
	public void setPatAddContactNo(String patAddContactNo)
	{
		this.getPatAddress().setPatAddContactNo(patAddContactNo);
	}

	/**
	 * Retrieves patAddCountry.
	 * @return Value of patAddCountry.	
	 */
	public String getPatAddCountry()
	{
		return getPatAddress().getPatAddCountry();
	}

	/**
	 * Sets patAddCountry.
	 * @param patAddCountry
	 */
	public void setPatAddCountry(String patAddCountry)
	{
		this.getPatAddress().setPatAddCountry(patAddCountry);
	}

	/**
	 * Retrieves patAddCountryCode.
	 * @return Value of patAddCountryCode.	
	 */
	public String getPatAddCountryCode()
	{
		return getPatAddress().getPatAddCountryCode();
	}

	/**
	 * Sets patAddCountryCode.
	 * @param patAddCountryCode
	 */
	public void setPatAddCountryCode(String patAddCountryCode)
	{
		this.getPatAddress().setPatAddCountryCode(patAddCountryCode);
	}

	/**
	 * Retrieves patAddDistrict.
	 * @return Value of patAddDistrict.	
	 */
	public String getPatAddDistrict()
	{
		return getPatAddress().getPatAddDistrict();
	}

	/**
	 * Sets patAddDistrict.
	 * @param patAddDistrict
	 */
	public void setPatAddDistrict(String patAddDistrict)
	{
		this.getPatAddress().setPatAddDistrict(patAddDistrict);
	}
	
	public String getPatAddDistrictCode()
	{
		return getPatAddress().getPatAddDistrictCode();
	}
	
	public void setPatAddDistrictCode(String patAddDistrictCode)
	{
		this.getPatAddress().setPatAddDistrictCode(patAddDistrictCode);
	}

	/**
	 * Retrieves patAddHNo.
	 * @return Value of patAddHNo.	
	 */
	public String getPatAddHNo()
	{
		return getPatAddress().getPatAddHNo();
	}

	/**
	 * Sets patAddHNo.
	 * @param patAddHNo
	 */
	public void setPatAddHNo(String patAddHNo)
	{
		this.getPatAddress().setPatAddHNo(patAddHNo);
	}

	/**
	 * Retrieves patAddPIN.
	 * @return Value of patAddPIN.	
	 */
	public String getPatAddPIN()
	{
		return getPatAddress().getPatAddPIN();
	}

	/**
	 * Sets patAddPIN.
	 * @param patAddPIN
	 */
	public void setPatAddPIN(String patAddPIN)
	{
		this.getPatAddress().setPatAddPIN(patAddPIN);
	}

	/**
	 * Retrieves patAddState.
	 * @return Value of patAddState.	
	 */
	public String getPatAddState()
	{
		return getPatAddress().getPatAddState();
	}

	public String getPatAddStateName()
	{
		return getPatAddress().getPatAddStateName();
	}

	/**
	 * Sets patAddState.
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.getPatAddress().setPatAddState(patAddState);
	}

	public void setPatAddStateName(String patAddStateName)
	{
		this.getPatAddress().setPatAddStateName(patAddStateName);
	}

	/**
	 * Retrieves patAddStateCode.
	 * @return Value of patAddStateCode.	
	 */
	public String getPatAddStateCode()
	{
		return getPatAddress().getPatAddStateCode();
	}

	/**
	 * Sets patAddStateCode.
	 * @param patAddStateCode
	 */
	public void setPatAddStateCode(String patAddStateCode)
	{
		this.getPatAddress().setPatAddStateCode(patAddStateCode);
	}

	/**
	 * Retrieves patAddStreet.
	 * @return Value of patAddStreet.	
	 */

	public String getPatAddStreet()
	{
		return getPatAddress().getPatAddStreet();
	}

	/**
	 * Sets patAddStreet.
	 * @param patAddStreet
	 */
	public void setPatAddStreet(String patAddStreet)
	{
		this.getPatAddress().setPatAddStreet(patAddStreet);
	}
	
	public String getStrPatAddressTehsil() {
		return this.getPatAddress().getStrPatAddressTehsil();
	}

	public void setStrPatAddressTehsil(String strPatAddressTehsil) {
		this.getPatAddress().setStrPatAddressTehsil(strPatAddressTehsil);
	}
	/**
	 * Retrieves patAddType.
	 * @return Value of patAddType.	
	 */
	public String getPatAddType()
	{
		return getPatAddress().getPatAddType();
	}

	/**
	 * Sets patAddType.
	 * @param patAddType
	 */
	public void setPatAddType(String patAddType)
	{
		this.getPatAddress().setPatAddType(patAddType);
	}

	/**
	 * Retrieves patAddTypeCode.
	 * @return Value of patAddTypeCode.	
	 */
	public String getPatAddTypeCode()
	{
		return getPatAddress().getPatAddTypeCode();
	}

	/**
	 * Sets patAddTypeCode.
	 * @param patAddTypeCode
	 */
	public void setPatAddTypeCode(String patAddTypeCode)
	{
		this.getPatAddress().setPatAddTypeCode(patAddTypeCode);
	}

	/**
	 * Retrieves currentDepartment.
	 * @return Value of currentDepartment.	
	 */
	/*public String getCurrentDepartment() {
		return currentDepartment;
	}*/

	/**
	 * Sets currentDepartment.
	 * @param currentDepartment
	 */
	/*public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}*/

	/**
	 * Retrieves currentDepartmentCode.
	 * @return Value of currentDepartmentCode.	
	 */
	/*public String getCurrentDepartmentCode() {
		return currentDepartmentCode;
	}*/

	/**
	 * Sets currentDepartmentCode.
	 * @param currentDepartmentCode
	 */
	/*public void setCurrentDepartmentCode(String currentDepartmentCode) {
		this.currentDepartmentCode = currentDepartmentCode;
	}*/





	/**
	 * Retrieves isLock.
	 * @return Value of isLock.	
	 */
	/*public String getIsLock() {
		return isLock;
	}*/

	/**
	 * Sets isLock.
	 * @param isLock
	 */
	/*public void setIsLock(String isLock) {
		this.isLock = isLock;
	}*/

	/**
	 * Retrieves isLockReason.
	 * @return Value of isLockReason.	
	 */
	/*public String getIsLockReason() {
		return isLockReason;
	}*/

	/**
	 * Sets isLockReason.
	 * @param isLockReason
	 */
	/*public void setIsLockReason(String isLockReason) {
		this.isLockReason = isLockReason;
	}*/

	/**
	 * Retrieves isNProgram.
	 * @return Value of isNProgram.	
	 */
	/*public String getIsNProgram() {
		return isNProgram;
	}*/

	/**
	 * Sets isNProgram.
	 * @param isNProgram
	 */
	/*public void setIsNProgram(String isNProgram) {
		this.isNProgram = isNProgram;
	}*/

	/**
	 * Retrieves patAge.
	 * @return Value of patAge.	
	 */
	public String getPatAge()
	{
		return patAge;
	}

	/**
	 * Sets patAge.
	 * @param patAge
	 */
	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	/**
	 * Retrieves patAgeUnit.
	 * @return Value of patAgeUnit.	
	 */
	public String getPatAgeUnit()
	{
		return patAgeUnit;
	}

	/**
	 * Sets patAgeUnit.
	 * @param patAgeUnit
	 */
	public void setPatAgeUnit(String patAgeUnit)
	{
		this.patAgeUnit = patAgeUnit;
	}

	/**
	 * Retrieves patIdMark.
	 * @return Value of patIdMark.	
	 */
	/*	public String getPatIdMark() {
			return patIdMark;
		}

	 *//**
	 * Sets patIdMark.
	 * @param patIdMark
	 */
	/*
			public void setPatIdMark(String patIdMark) {
				this.patIdMark = patIdMark;
			}
	 */
	/**
	 * Retrieves patSalutationCode.
	 * @return Value of patSalutationCode.	
	 */
	/*public String getPatSalutationCode() {
		return patSalutationCode;
	}*/

	/**
	 * Sets patSalutationCode.
	 * @param patSalutationCode
	 */
	/*public void setPatSalutationCode(String patSalutationCode) {
		this.patSalutationCode = patSalutationCode;
	}*/

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
	 * Retrieves patNationality.
	 * @return Value of patNationality.	
	 */
	public String getPatNationality()
	{
		return patNationality;
	}

	/**
	 * Sets patNationality.
	 * @param patNationality
	 */
	public void setPatNationality(String patNationality)
	{
		this.patNationality = patNationality;
	}

	/**
	 * Retrieves patNationalityCode.
	 * @return Value of patNationalityCode.	
	 */
	public String getPatNationalityCode()
	{
		return patNationalityCode;
	}

	/**
	 * Sets patNationalityCode.
	 * @param patNationalityCode
	 */
	public void setPatNationalityCode(String patNationalityCode)
	{
		this.patNationalityCode = patNationalityCode;
	}

	public String getPatHusbandName()
	{
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName)
	{
		this.patHusbandName = patHusbandName;
	}



	public String getIsNewBorn()
	{
		return isNewBorn;
	}

	public void setIsNewBorn(String isNewBorn)
	{
		this.isNewBorn = isNewBorn;
	}

	public String getPatBloodGroup()
	{
		return patBloodGroup;
	}

	public void setPatBloodGroup(String patBloodGroup)
	{
		this.patBloodGroup = patBloodGroup;
	}

	public String getPatMotherCrNo()
	{
		return patMotherCrNo;
	}

	public void setPatMotherCrNo(String patMotherCrNo)
	{
		this.patMotherCrNo = patMotherCrNo;
	}


	public String getPatIdMark1()
	{
		return patIdMark1;
	}

	public void setPatIdMark1(String patIdMark1)
	{
		this.patIdMark1 = patIdMark1;
	}

	public String getPatIdMark2()
	{
		return patIdMark2;
	}

	public void setPatIdMark2(String patIdMark2)
	{
		this.patIdMark2 = patIdMark2;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getPatOccupationDesc() {
		return patOccupationDesc;
	}

	public void setPatOccupationDesc(String patOccupationDesc) {
		this.patOccupationDesc = patOccupationDesc;
	}


	public String getDeathDateTime() {
		return deathDateTime;
	}

	public void setDeathDateTime(String deathDateTime) {
		this.deathDateTime = deathDateTime;
	}

	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getPatBirthPlace() {
		return patBirthPlace;
	}
	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}
	public String getPatDocType() {
		return patDocType;
	}
	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}
	public String getPatCatCardNo() {
		return patCatCardNo;
	}
	public void setPatCatCardNo(String patCatCardNo) {
		this.patCatCardNo = patCatCardNo;
	}
	public String getPatAadharNo() {
		return patAadharNo;
	}
	public void setPatAadharNo(String patAadharNo) {
		this.patAadharNo = patAadharNo;
	}

	public String getPatAddLandMarks() {
		return getPatAddress().getPatAddLandMarks();
	}
	public String getPatAddPhoneOwner() {
		return getPatAddress().getPatAddPhoneOwner();
	}
	public String getPatAddMobileNo(){
		return getPatAddress().getPatAddMobileNo();
	}
	public void setPatAddMobileNo(String patAddMobileNo){
		patAddress.setPatAddMobileNo(patAddMobileNo);
	}
	public String getPatAddPhoneNo()
	{
		return getPatAddress().getPatAddPhoneNo();
	}
	public String getPatAddEmailId()
	{
		return getPatAddress().getPatAddEmailId();
	}
	public void setPatAddEmailId(String patAddEmailId){
		patAddress.setPatAddEmailId(patAddEmailId);
	}
	
	public void setPatAddLandMarks(String patAddLandMarks){
		patAddress.setPatAddLandMarks(patAddLandMarks);
	}



}
