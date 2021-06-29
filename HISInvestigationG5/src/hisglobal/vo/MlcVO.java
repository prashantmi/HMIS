package hisglobal.vo;

/**
 * MlcVO is the class that specifies getters and setters for all the identifiers
 * which are used for retrieving and inserting data in the DB tables. 
 * @author AHIS
 */
public class MlcVO extends ValueObject
{

	private AddressVO patAddress = new AddressVO();
	private String patCrNo;
	private String mlcNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String caseNo;
	private String policeName;
	private String policeDesignation;
	private String policeStation;
	private String officerIncharge;
	private String mlcDate;
	private String cmoCode;
	private String isTransfer;
	private String seatId;
	private String entryDate;
	private String isBroughtDead;
	private String patCondition;
	private String doctorName;
	private String mlcRemark;
	private String isValid;
	private String serialNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patDOB;
	private String patGender;
	private String patGenderCode;
	private String patMaritalStatus;
	private String patMaritalStatusCode;
	private String patReligion;
	private String patReligionCode;
	private String patGuardianName;
	//private String patMomName;// in case of new born child   
	private String patMotherName;
	private String isReferred;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;
	private String patRefDoctor;
	private String isUnknown;
	private String systemIPAddress;//IP address of the system from which the details are entered
	private String batchNo;
	private String broughtByName;
	private String broughtByAddress;
	private String broughtByPhone;
	private String patNationalityCode;
	private String patNationality;
	private String patRefGnctdHospitalCrno;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private byte[] imageFile;
	private String imageFileName;
	private String fileSystemStoragePath;
	private String isImageUploaded;
	private String patHusbandName;
	private String patNationalId;
	private String mlcTime;
	private String injuryTypeCode;
	private String patStatusCode;
	private String injuryNatureCode;
	private String referDoctorCode;
	private String referDocotorRemarks;
	private String mlcType;
	private String mlrBookNo;
	private String mlrPageNo;
	private String diagnosis;
	private String isFit;
	private String fitDateTime;
	private String handoverDateTime;
	private String handoverOffName;
	private String handoverOffDesg;
	private String handoverOffBadgeNo;
	private String bookNo;
	private String pageNo;
	private String fitnessStatus;
	private String patMlcNo;
	private String episodeReferAccept;
	private String typeOfWeapon;
	private String 	injurySize;
	private String injuryDepth;
	private String injurySide;
	private String burnPercentage;
	private String poisonRemarks;
	private String ageOfInjury;

	
	public String getEpisodeReferAccept() {
		return episodeReferAccept;
	}

	public void setEpisodeReferAccept(String episodeReferAccept) {
		this.episodeReferAccept = episodeReferAccept;
	}

	public String getPatMlcNo() {
		return patMlcNo;
	}

	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}

	public String getFitnessStatus() {
		return fitnessStatus;
	}

	public void setFitnessStatus(String fitnessStatus) {
		this.fitnessStatus = fitnessStatus;
	}

	public String getInjuryNatureCode() {
		return injuryNatureCode;
	}

	public void setInjuryNatureCode(String injuryNatureCode) {
		this.injuryNatureCode = injuryNatureCode;
	}

	public String getReferDoctorCode() {
		return referDoctorCode;
	}

	public void setReferDoctorCode(String referDoctorCode) {
		this.referDoctorCode = referDoctorCode;
	}

	public String getReferDocotorRemarks() {
		return referDocotorRemarks;
	}

	public void setReferDocotorRemarks(String referDocotorRemarks) {
		this.referDocotorRemarks = referDocotorRemarks;
	}

	public String getMlcType() {
		return mlcType;
	}

	public void setMlcType(String mlcType) {
		this.mlcType = mlcType;
	}

	public String getMlcTime()
	{
		return mlcTime;
	}

	public void setMlcTime(String mlcTime)
	{
		this.mlcTime = mlcTime;
	}

	public String getInjuryTypeCode()
	{
		return injuryTypeCode;
	}

	public void setInjuryTypeCode(String injuryTypeCode)
	{
		this.injuryTypeCode = injuryTypeCode;
	}

	public String getPatStatusCode()
	{
		return patStatusCode;
	}

	public void setPatStatusCode(String patStatusCode)
	{
		this.patStatusCode = patStatusCode;
	}

	public String getPatHusbandName()
	{
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName)
	{
		this.patHusbandName = patHusbandName;
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

	public String getFileSystemStoragePath()
	{
		return fileSystemStoragePath;
	}

	public void setFileSystemStoragePath(String fileSysteamStoragePath)
	{
		this.fileSystemStoragePath = fileSysteamStoragePath;
	}

	public byte[] getImageFile()
	{
		return imageFile;
	}

	public void setImageFile(byte[] imageFile)
	{
		this.imageFile = imageFile;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setBatchNo(String batchNo)
	{
		this.batchNo = batchNo;
	}

	public String getBroughtByAddress()
	{
		return broughtByAddress;
	}

	public void setBroughtByAddress(String broughtByAddress)
	{
		this.broughtByAddress = broughtByAddress;
	}

	public String getBroughtByName()
	{
		return broughtByName;
	}

	public void setBroughtByName(String broughtByName)
	{
		this.broughtByName = broughtByName;
	}

	public String getBroughtByPhone()
	{
		return broughtByPhone;
	}

	public void setBroughtByPhone(String broughtByPhone)
	{
		this.broughtByPhone = broughtByPhone;
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
	 * Sets patRefHospitalName.
	 * @param patRefHospitalName
	 */
	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	/**
	 * Retrieves patRefHospitalName.
	 * @return Value of patRefHospitalName.	
	 */
	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	/**
	 * Sets patRefGnctdHospitalCode.
	 * @param patRefGnctdHospitalCode
	 */
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	/**
	 * Retrieves patRefGnctdHospitalCode.
	 * @return Value of patRefGnctdHospitalCode.	
	 */
	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	/**
	 * Sets episodeCode.
	 * @param episodeCode
	 */
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	/**
	 * Retrieves episodeCode.
	 * @return Value of episodeCode.	
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
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
	 * Sets patRefDoctor.
	 * @param patRefDoctor
	 */
	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}

	/**
	 * Retrieves patRefDoctor.
	 * @return Value of patRefDoctor.	
	 */
	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}

	/**
	 * Sets patMomName.
	 * @param patMomName
	 */

	/**
	 * Retrieves patMomName.
	 * @return Value of patMomName.	
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
	public hisglobal.vo.AddressVO getPatAddress()
	{
		return patAddress;
	}

	/**
	 * Sets patAddress.
	 * @param patAddress
	 */
	public void setPatAddress(hisglobal.vo.AddressVO patAddress)
	{
		this.patAddress = patAddress;
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
	 * Retrieves isValid.
	 * @return Value of isValid.	
	 */
	public String getIsValid()
	{
		return isValid;
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
	 * Sets isReferred.
	 * @param isReferred
	 */
	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}

	/**
	 * Retrieves isReferred.
	 * @return Value of isReferred.	
	 */
	public String getIsReferred()
	{
		return isReferred;
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
	 * Retrieves entryDate.
	 * @return Value of entryDate.	
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Retrieves caseNo.
	 * @return Value of caseNo.	
	 */
	public String getCaseNo()
	{
		return caseNo;
	}

	/**
	 * Sets caseNo.
	 * @param caseNo
	 */
	public void setCaseNo(String caseNo)
	{
		this.caseNo = caseNo;
	}

	/**
	 * Retrieves cmoCode.
	 * @return Value of cmoCode.	
	 */
	public String getCmoCode()
	{
		return cmoCode;
	}

	/**
	 * Sets cmoCode.
	 * @param cmoCode
	 */
	public void setCmoCode(String cmoCode)
	{
		this.cmoCode = cmoCode;
	}

	/**
	 * Retrieves doctorName.
	 * @return Value of doctorName.	
	 */
	public String getDoctorName()
	{
		return doctorName;
	}

	/**
	 * Sets doctorName.
	 * @param doctorName
	 */
	public void setDoctorName(String doctorName)
	{
		this.doctorName = doctorName;
	}

	/**
	 * Retrieves isBroughtDead.
	 * @return Value of isBroughtDead.	
	 */
	public String getIsBroughtDead()
	{
		return isBroughtDead;
	}

	/**
	 * Sets isBroughtDead.
	 * @param isBroughtDead
	 */
	public void setIsBroughtDead(String isBroughtDead)
	{
		this.isBroughtDead = isBroughtDead;
	}

	/**
	 * Retrieves isTransfer.
	 * @return Value of isTransfer.	
	 */
	public String getIsTransfer()
	{
		return isTransfer;
	}

	/**
	 * Sets isTransfer.
	 * @param isTransfer
	 */
	public void setIsTransfer(String isTransfer)
	{
		this.isTransfer = isTransfer;
	}

	/**
	 * Retrieves mlcDate.
	 * @return Value of mlcDate.	
	 */
	public String getMlcDate()
	{
		return mlcDate;
	}

	/**
	 * Sets mlcDate.
	 * @param mlcDate
	 */
	public void setMlcDate(String mlcDate)
	{
		this.mlcDate = mlcDate;
	}

	/**
	 * Retrieves mlcNo.
	 * @return Value of mlcNo.	
	 */
	public String getMlcNo()
	{
		return mlcNo;
	}

	/**
	 * Sets mlcNo.
	 * @param mlcNo
	 */
	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	/**
	 * Retrieves mlcRemark.
	 * @return Value of mlcRemark.	
	 */
	public String getMlcRemark()
	{
		return mlcRemark;
	}

	/**
	 * Sets mlcRemark.
	 * @param mlcRemark
	 */
	public void setMlcRemark(String mlcRemark)
	{
		this.mlcRemark = mlcRemark;
	}

	/**
	 * Retrieves officerIncharge.
	 * @return Value of officerIncharge.	
	 */
	public String getOfficerIncharge()
	{
		return officerIncharge;
	}

	/**
	 * Sets officerIncharge.
	 * @param officerIncharge
	 */
	public void setOfficerIncharge(String officerIncharge)
	{
		this.officerIncharge = officerIncharge;
	}

	/**
	 * Retrieves patCondition.
	 * @return Value of patCondition.	
	 */
	public String getPatCondition()
	{
		return patCondition;
	}

	/**
	 * Sets patCondition.
	 * @param patCondition
	 */
	public void setPatCondition(String patCondition)
	{
		this.patCondition = patCondition;
	}

	

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * Retrieves policeName.
	 * @return Value of policeName.	
	 */
	public String getPoliceName()
	{
		return policeName;
	}

	/**
	 * Sets policeName.
	 * @param policeName
	 */
	public void setPoliceName(String policeName)
	{
		this.policeName = policeName;
	}

	/**
	 * Retrieves policeStation.
	 * @return Value of policeStation.	
	 */
	public String getPoliceStation()
	{
		return policeStation;
	}

	/**
	 * Sets policeStation.
	 * @param policeStation
	 */
	public void setPoliceStation(String policeStation)
	{
		this.policeStation = policeStation;
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

	/**
	 * Sets patAddState.
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.getPatAddress().setPatAddState(patAddState);
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

	/**
	 * Retrieves patRefGnctdHospitalCrno.
	 * @return Value of patRefGnctdHospitalCrno.	
	 */
	public String getPatRefGnctdHospitalCrno()
	{
		return patRefGnctdHospitalCrno;
	}

	/**
	 * Sets patRefGnctdHospitalCrno.
	 * @param patRefGnctdHospitalCrno
	 */
	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno)
	{
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	/**
	 * Retrieves patRefGnctdHospitalDept.
	 * @return Value of patRefGnctdHospitalDept.	
	 */
	public String getPatRefGnctdHospitalDept()
	{
		return patRefGnctdHospitalDept;
	}

	/**
	 * Sets patRefGnctdHospitalDept.
	 * @param patRefGnctdHospitalDept
	 */
	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept)
	{
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	/**
	 * Retrieves patRefGnctdHospitalDeptUnit.
	 * @return Value of patRefGnctdHospitalDeptUnit.	
	 */
	public String getPatRefGnctdHospitalDeptUnit()
	{
		return patRefGnctdHospitalDeptUnit;
	}

	/**
	 * Sets patRefGnctdHospitalDeptUnit.
	 * @param patRefGnctdHospitalDeptUnit
	 */
	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit)
	{
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	public String getImageFileName()
	{
		return imageFileName;
	}

	public void setImageFileName(String imageFileName)
	{
		this.imageFileName = imageFileName;
	}

	public String getPatMotherName()
	{
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName)
	{
		this.patMotherName = patMotherName;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getMlrBookNo() {
		return mlrBookNo;
	}

	public void setMlrBookNo(String mlrBookNo) {
		this.mlrBookNo = mlrBookNo;
	}

	public String getMlrPageNo() {
		return mlrPageNo;
	}

	public void setMlrPageNo(String mlrPageNo) {
		this.mlrPageNo = mlrPageNo;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getIsFit() {
		return isFit;
	}

	public void setIsFit(String isFit) {
		this.isFit = isFit;
	}

	public String getFitDateTime() {
		return fitDateTime;
	}

	public void setFitDateTime(String fitDateTime) {
		this.fitDateTime = fitDateTime;
	}

	public String getHandoverDateTime() {
		return handoverDateTime;
	}

	public void setHandoverDateTime(String handoverDateTime) {
		this.handoverDateTime = handoverDateTime;
	}

	public String getHandoverOffName() {
		return handoverOffName;
	}

	public void setHandoverOffName(String handoverOffName) {
		this.handoverOffName = handoverOffName;
	}

	public String getHandoverOffDesg() {
		return handoverOffDesg;
	}

	public void setHandoverOffDesg(String handoverOffDesg) {
		this.handoverOffDesg = handoverOffDesg;
	}

	public String getHandoverOffBadgeNo() {
		return handoverOffBadgeNo;
	}

	public void setHandoverOffBadgeNo(String handoverOffBadgeNo) {
		this.handoverOffBadgeNo = handoverOffBadgeNo;
	}

	public String getPoliceDesignation() {
		return policeDesignation;
	}

	public void setPoliceDesignation(String policeDesignation) {
		this.policeDesignation = policeDesignation;
	}

	public String getTypeOfWeapon() {
		return typeOfWeapon;
	}

	public void setTypeOfWeapon(String typeOfWeapon) {
		this.typeOfWeapon = typeOfWeapon;
	}

	public String getInjurySize() {
		return injurySize;
	}

	public void setInjurySize(String injurySize) {
		this.injurySize = injurySize;
	}

	public String getInjuryDepth() {
		return injuryDepth;
	}

	public void setInjuryDepth(String injuryDepth) {
		this.injuryDepth = injuryDepth;
	}

	public String getInjurySide() {
		return injurySide;
	}

	public void setInjurySide(String injurySide) {
		this.injurySide = injurySide;
	}

	public String getBurnPercentage() {
		return burnPercentage;
	}

	public void setBurnPercentage(String burnPercentage) {
		this.burnPercentage = burnPercentage;
	}

	public String getPoisonRemarks() {
		return poisonRemarks;
	}

	public void setPoisonRemarks(String poisonRemarks) {
		this.poisonRemarks = poisonRemarks;
	}

	public String getAgeOfInjury() {
		return ageOfInjury;
	}

	public void setAgeOfInjury(String ageOfInjury) {
		this.ageOfInjury = ageOfInjury;
	}

	/*public String getPoliceDesignation() {
		return policeDesignation;
	}

	public void setPoliceDesignation(String policeDesignation) {
		this.policeDesignation = policeDesignation;
	}*/

}
