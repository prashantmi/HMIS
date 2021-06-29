package vo.registration;

import hisglobal.vo.ValueObject;

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
	private String patMlcStatusCode;
	private String isValid;
	private String serialNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patFirstNameInMultiLang;
	private String patMiddleNameInMultiLang;
	private String patLastNameInMultiLang;
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
	private String patStatusCode;
	private String patPrimaryCatCode;
	
	private String isActualDob;
	private String patDocType;
	private String patAppellationCode;
	private String patIdMark1;
	private String suffixCode;
	private String patNameType;
	private String patMonthlyIncome;
	private String patIdNo;
	private String patOccupation;
	private String patAge;
	private String patNickName;
	private String patNickNameInMultiLang;
	private String patIsUrban;
	private String patCardNo;
	private String patIdMark2;
	private String patBirthPlace;
	private String prevCrNo;
	private String patIsDead;
	private String patCasteCode;
	private String patEducation;
	private String patEducationCode;
	
	private String referDoctorCode;
	private String referDocotorRemarks;
	private String mlcType;
	private String mlcBookNo;
	private String mlcPageNo;
	private String diagnosis;
	private String isFit;
	private String fitDateTime;
	private String handoverDateTime;
	private String handoverOffName;
	private String handoverOffDesg;
	private String handoverOffBadgeNo;
	private String fitnessStatus;
	private String patMlcNo;
	private String episodeReferAccept;
	
	private String registerDate;
	private String finalOpinion;
	private String provisionalOpinion;
	private String mlcStatus;
	
	private String departmentUnit;
	
	///private String mlcTypeCode;
	///private String injuryNatureCode;
	///private String typeOfWeapon;
	///private String injurySize;
	///private String injuryDepth;
	///private String injurySide;
	///private String burnPercentage;
	///private String poisonRemarks;
	
	
	public String getEpisodeReferAccept() {
		return episodeReferAccept;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getFinalOpinion() {
		return finalOpinion;
	}

	public void setFinalOpinion(String finalOpinion) {
		this.finalOpinion = finalOpinion;
	}

	public String getProvisionalOpinion() {
		return provisionalOpinion;
	}

	public void setProvisionalOpinion(String provisionalOpinion) {
		this.provisionalOpinion = provisionalOpinion;
	}

	public String getMlcStatus() {
		return mlcStatus;
	}

	public void setMlcStatus(String mlcStatus) {
		this.mlcStatus = mlcStatus;
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
	public String getPatStatusCode()
	{
		return patStatusCode;
	}

	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getIsActualDob() {
		return isActualDob;
	}

	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}

	public String getPatDocType() {
		return patDocType;
	}

	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}

	public String getPatAppellationCode() {
		return patAppellationCode;
	}

	public void setPatAppellationCode(String patAppellationCode) {
		this.patAppellationCode = patAppellationCode;
	}

	public String getPatIdMark1() {
		return patIdMark1;
	}

	public void setPatIdMark1(String patIdMark1) {
		this.patIdMark1 = patIdMark1;
	}

	public String getSuffixCode() {
		return suffixCode;
	}

	public void setSuffixCode(String suffixCode) {
		this.suffixCode = suffixCode;
	}

	public String getPatNameType() {
		return patNameType;
	}

	public void setPatNameType(String patNameType) {
		this.patNameType = patNameType;
	}

	public String getPatMonthlyIncome() {
		return patMonthlyIncome;
	}

	public void setPatMonthlyIncome(String patMonthlyIncome) {
		this.patMonthlyIncome = patMonthlyIncome;
	}

	public String getPatIdNo() {
		return patIdNo;
	}

	public void setPatIdNo(String patIdNo) {
		this.patIdNo = patIdNo;
	}

	public String getPatOccupation() {
		return patOccupation;
	}

	public void setPatOccupation(String patOccupation) {
		this.patOccupation = patOccupation;
	}

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatNickName() {
		return patNickName;
	}

	public void setPatNickName(String patNickName) {
		this.patNickName = patNickName;
	}

	public String getPatNickNameInMultiLang() {
		return patNickNameInMultiLang;
	}

	public void setPatNickNameInMultiLang(String patNickNameInMultiLang) {
		this.patNickNameInMultiLang = patNickNameInMultiLang;
	}
	public String getPatIsLocal() {
		return getPatAddress().getPatIsLocal();
	}
	public String getPatIsUrban() {
		return patIsUrban;
	}

	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}

	public String getPatCardNo() {
		return patCardNo;
	}

	public void setPatCardNo(String patCardNo) {
		this.patCardNo = patCardNo;
	}

	public String getPatIdMark2() {
		return patIdMark2;
	}

	public void setPatIdMark2(String patIdMark2) {
		this.patIdMark2 = patIdMark2;
	}

	public String getPatBirthPlace() {
		return patBirthPlace;
	}

	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}

	public String getPrevCrNo() {
		return prevCrNo;
	}

	public void setPrevCrNo(String prevCrNo) {
		this.prevCrNo = prevCrNo;
	}

	public String getPatIsDead() {
		return patIsDead;
	}

	public void setPatIsDead(String patIsDead) {
		this.patIsDead = patIsDead;
	}

	public String getPatCasteCode() {
		return patCasteCode;
	}

	public void setPatCasteCode(String patCasteCode) {
		this.patCasteCode = patCasteCode;
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

	public String getPatMlcStatusCode() {
		return patMlcStatusCode;
	}

	public void setPatMlcStatusCode(String patMlcStatusCode) {
		this.patMlcStatusCode = patMlcStatusCode;
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

	public String getMlcBookNo() {
		return mlcBookNo;
	}

	public void setMlcBookNo(String mlcBookNo) {
		this.mlcBookNo = mlcBookNo;
	}

	public String getMlcPageNo() {
		return mlcPageNo;
	}

	public void setMlcPageNo(String mlcPageNo) {
		this.mlcPageNo = mlcPageNo;
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

	public String getDepartmentUnit() {
		return departmentUnit;
	}

	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}

	/*public String getPoliceDesignation() {
		return policeDesignation;
	}

	public void setPoliceDesignation(String policeDesignation) {
		this.policeDesignation = policeDesignation;
	}*/

}
