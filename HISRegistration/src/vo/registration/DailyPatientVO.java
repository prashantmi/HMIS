package vo.registration;

import hisglobal.hisconfig.Config;
import hisglobal.vo.ValueObject;

import java.util.Comparator;

/**
 * DailyPatientVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class DailyPatientVO extends ValueObject implements Comparable, Comparator
{
	private AddressVO patAddress = new AddressVO();
	private String patCrNo;
	private String serialNo;
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
	private String patDOB;
	private String patAge;
	private String patIsUrban;
	private String patMonthlyIncome;
	private String isReferred;
	private String isActualDob;// if (age provided) isActualDob=0 else isActualDob=1
	private String prevCrNo;
	private String patIdNo;
	private String patStatus;
	private String patStatusCode;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String patRegCat;
	private String patRegCatCode;
	private String patNationalityCode;
	private String patNationality;
	private String entryDate;
	private String isUnknown;
	private String isMLC;
	private String registerDate;
	private String seatId;
	private String isValid;
	private String systemIPAddress;// IP address of the system from which the details are entered
	private String patMotherName;
	private String patHusbandName;
	private String patNationalId;
	private String patBloodGroup;
	private String isNewBorn = "0";
	private String patMotherCrNo;
	private String registrationType;
	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String patAmountCollected;
	private String patGuardianName;
	private String patIsOld;
	private String department;
	private String departmentCode;
	private String departmentUnit;
	private String departmentUnitCode;
	private String room;
	private String roomCode;
	private String queNo;
	private String patientAllowed;
	private String episodeCode;// for HRGNUM_EPISODE_NO
	private String episodeVisitNo;// for HRGNUM_VISIT_NO
	private String episodeTypeCode;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;
	private String patRefDoctor;
	private String episodeDate;
	private String patRefGnctdHospitalCrno;
	private String patRefGnctdHospitalDept;
	private String patRefGnctdHospitalDeptUnit;
	private String episodeVisitType;
	private String patBloodGroupCode;
	private String departmentUnitType;
	
	private String patAgeUnit;
	private String patAgeWithUnit;
	
	private String patOccupation;
	private String patFatherOccupation;
	private String patHusbandOccupation;
	private String patNickName;
	private String patCardNo;
	private String patGenderAge;
	private String orederBy;
	private String sortType;
	private String fileNo;
	private String fileNoView;
	private String patIdMark1;
	private String patIdMark2;
	private String isFree;
	private String isBroughtDead;

	private String colorCode;
	private String colorCodeOrder;
	private String isConfirmed;
	private String unitWorkingDays;
	private String roomUsability;
	private String isNewFileRequired;
	private String disasterId;
	private String triageDuration;
	private String triageDurationHours;
	
	private String patName;
	private String displayQueno;
	private String chnageToRoomCode;
	private String unitConsultant;
	private String oldCrNo;
	private String oldRegDate;
	private String fileNoPrint;
	private String filePrintSetting;
	private String cardPrintSetting;
	private String patCasteCode="";
	private String patCaste;
	
	private String patCatCardNo;
	private String patAadharNo;
	private String patVisitReason;

	private String patSplVulnerability;
	private String patIsDead;
	private String strHospCode;
	private String patPrimaryCatValid;
	private String patGeneralExpDate;
	private String patSpecialExpDate;
	private String patCasualityExpDate;
	private String patAppellationCode;
	private String suffixCode;
	
	
	private String patBirthPlace;
	private String patIsMerged;
	private String patDocType;
	
	private String patNameType;
	private String patEducation;
	private String patEducationCode;
	
	private String wardCode;
	private String episodeIsOpen;
	private String isCardPrint;
	private String episodeReferCode;
	private String expiryDate;
	private String episodeExpiryDate;
	private String episodeStartDate;
	private String lastVisitDate;
	private String tariffId;
	private String billNo;
	private String changeToDeptUnitCode;
	private String changeToRoomCode;
	private String changeType;
	
	private String admissionNo;
	private String treatmentValidUpTo; 
	
	private String roundRobinUnitFlag;
	private String roundRobinRoomFlag;
	private String isForceful;
	private String patCatShortName;
	
	private String mlcFlag;
	private String isImageUploaded;
	
	private String patAptId;
	private String patAptStatus;
	private String patAptNo;	
	private String patAptSlot;
	private String patAptQueueNO;
	private String isAppointment;
	
	private String letterType;
	private String creditLimit;
    
	private String isAmbulatory;//Added by Vasu on 03.May.18
	
	private String broughtByConsultant;
	
	public String getLetterType() {
		return letterType;
	}

	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
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

	public String getOldRegDate() {
		return oldRegDate;
	}

	public void setOldRegDate(String oldRegDate) {
		this.oldRegDate = oldRegDate;
	}

	public String getFileNoPrint() {
		return fileNoPrint;
	}

	public void setFileNoPrint(String fileNoPrint) {
		this.fileNoPrint = fileNoPrint;
	}

	public String getOldCrNo() {
		return oldCrNo;
	}

	public void setOldCrNo(String oldCrNo) {
		this.oldCrNo = oldCrNo;
	}

	public String getChnageToRoomCode() {
		return chnageToRoomCode;
	}

	public void setChnageToRoomCode(String chnageToRoomCode) {
		this.chnageToRoomCode = chnageToRoomCode;
	}

	public String getDisplayQueno() {
		return displayQueno;
	}

	public void setDisplayQueno(String displayQueno) {
		this.displayQueno = displayQueno;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getTriageDuration() {
		return triageDuration;
	}

	public void setTriageDuration(String triageDuration) {
		this.triageDuration = triageDuration;
	}

	public String getDisasterId() {
		return disasterId;
	}

	public void setDisasterId(String disasterId) {
		this.disasterId = disasterId;
	}

	private String lastModifyDate;
	private String lastModifySeatId;	
	

	public String getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifySeatId() {
		return lastModifySeatId;
	}

	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}


	public String getIsNewFileRequired() {
		return isNewFileRequired;
	}

	public void setIsNewFileRequired(String isNewFileRequired) {
		this.isNewFileRequired = isNewFileRequired;
	}

	public String getRoomUsability() {
		return roomUsability;
	}

	public void setRoomUsability(String roomUsability) {
		this.roomUsability = roomUsability;
	}

	public String getUnitWorkingDays() {
		return unitWorkingDays;
	}

	public void setUnitWorkingDays(String unitWorkingDays) {
		this.unitWorkingDays = unitWorkingDays;
	}

	public String getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getColorCodeOrder()
	{
		return colorCodeOrder;
	}

	public void setColorCodeOrder(String colorCodeOrder)
	{
		this.colorCodeOrder = colorCodeOrder;
	}

	public String getColorCode()
	{
		return colorCode;
	}

	public void setColorCode(String colorCode)
	{
		this.colorCode = colorCode;
	}

	public String getIsBroughtDead()
	{
		return isBroughtDead;
	}

	public void setIsBroughtDead(String isBroughtDead)
	{
		this.isBroughtDead = isBroughtDead;
	}

	public String getIsFree()
	{
		return isFree;
	}

	public void setIsFree(String isFree)
	{
		this.isFree = isFree;
	}

	public String getFileNo()
	{
		return fileNo;
	}

	public void setFileNo(String fileNo)
	{
		this.fileNo = fileNo;
	}

	public String getOrederBy()
	{
		return orederBy;
	}

	public void setOrederBy(String orederBy)
	{
		this.orederBy = orederBy;
	}

	public String getPatGenderAge()
	{
		return patGenderAge;
	}

	public void setPatGenderAge(String patGenderAge)
	{
		this.patGenderAge = patGenderAge;
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

	/**
	 * Sets systemIPAddress.
	 * 
	 * @param systemIPAddress
	 */
	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	/**
	 * Retrieves systemIPAddress.
	 * 
	 * @return Value of systemIPAddress.
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	/**
	 * Sets patRefHospitalName.
	 * 
	 * @param patRefHospitalName
	 */
	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}

	/**
	 * Retrieves patRefHospitalName.
	 * 
	 * @return Value of patRefHospitalName.
	 */
	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	/**
	 * Sets patRefGnctdHospitalCode.
	 * 
	 * @param patRefGnctdHospitalCode
	 */
	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	/**
	 * Retrieves patRefGnctdHospitalCode.
	 * 
	 * @return Value of patRefGnctdHospitalCode.
	 */
	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	/**
	 * Sets patCatcode.
	 * 
	 * @param patCatcode
	 */
	/*
	 * public void setPatCatcode( String patCatcode ) { this.patCatcode = patCatcode; }
	 */

	/**
	 * Retrieves patCatcode.
	 * 
	 * @return Value of patCatcode.
	 */
	/*
	 * public String getPatCatcode( ) { return patCatcode; }
	 */

	/**
	 * Sets patCat.
	 * 
	 * @param patCat
	 */
	/*
	 * public void setPatCat( String patCat ) { this.patCat = patCat; }
	 */

	/**
	 * Retrieves patCat.
	 * 
	 * @return Value of patCat.
	 */
	/*
	 * public String getPatCat( ) { return patCat; }
	 */

	/**
	 * Sets episodeCode.
	 * 
	 * @param episodeCode
	 */
	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	/**
	 * Retrieves episodeCode.
	 * 
	 * @return Value of episodeCode.
	 */
	public String getEpisodeCode()
	{
		return episodeCode;
	}

	/**
	 * Sets emergencyType.
	 * 
	 * @param emergencyType
	 */
	/*
	 * public void setEmergencyType( String emergencyType ) { this.emergencyType = emergencyType; }
	 */

	/**
	 * Retrieves emergencyType.
	 * 
	 * @return Value of emergencyType.
	 */
	/*
	 * public String getEmergencyType( ) { return emergencyType; }
	 */

	/**
	 * Sets patStatus.
	 * 
	 * @param patStatus
	 */
	public void setPatStatus(String patStatus)
	{
		this.patStatus = patStatus;
	}

	/**
	 * Retrieves patStatus.
	 * 
	 * @return Value of patStatus.
	 */
	public String getPatStatus()
	{
		return patStatus;
	}

	/**
	 * Sets patRegCat.
	 * 
	 * @param patRegCat
	 */
	public void setPatRegCat(String patRegCat)
	{
		this.patRegCat = patRegCat;
	}

	/**
	 * Retrieves patRegCat.
	 * 
	 * @return Value of patRegCat.
	 */
	public String getPatRegCat()
	{
		return patRegCat;
	}

	/**
	 * Sets roomCode.
	 * 
	 * @param roomCode
	 */
	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	/**
	 * Retrieves roomCode.
	 * 
	 * @return Value of roomCode.
	 */
	public String getRoomCode()
	{
		return roomCode;
	}

	/**
	 * Sets room.
	 * 
	 * @param room
	 */
	public void setRoom(String room)
	{
		this.room = room;
	}

	/**
	 * Retrieves room.
	 * 
	 * @return Value of room.
	 */
	public String getRoom()
	{
		return room;
	}

	/**
	 * Sets registerDate.
	 * 
	 * @param registerDate
	 */
	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}

	/**
	 * Retrieves registerDate.
	 * 
	 * @return Value of registerDate.
	 */
	public String getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * Sets queNo.
	 * 
	 * @param queNo
	 */
	public void setQueNo(String queNo)
	{
		this.queNo = queNo;
	}

	/**
	 * Retrieves queNo.
	 * 
	 * @return Value of queNo.
	 */
	public String getQueNo()
	{
		return queNo;
	}

	/**
	 * Sets prevCrNo.
	 * 
	 * @param prevCrNo
	 */
	public void setPrevCrNo(String prevCrNo)
	{
		this.prevCrNo = prevCrNo;
	}

	/**
	 * Retrieves prevCrNo.
	 * 
	 * @return Value of prevCrNo.
	 */
	public String getPrevCrNo()
	{
		return prevCrNo;
	}

	/**
	 * Sets patStatusCode.
	 * 
	 * @param patStatusCode
	 */
	public void setPatStatusCode(String patStatusCode)
	{
		this.patStatusCode = patStatusCode;
	}

	/**
	 * Retrieves patStatusCode.
	 * 
	 * @return Value of patStatusCode.
	 */
	public String getPatStatusCode()
	{
		return patStatusCode;
	}

	/**
	 * Sets patSecondaryCatCode.
	 * 
	 * @param patSecondaryCatCode
	 */
	public void setPatSecondaryCatCode(String patSecondaryCatCode)
	{
		this.patSecondaryCatCode = patSecondaryCatCode;
	}

	/**
	 * Retrieves patSecondaryCatCode.
	 * 
	 * @return Value of patSecondaryCatCode.
	 */
	public String getPatSecondaryCatCode()
	{
		return patSecondaryCatCode;
	}

	/**
	 * Sets patSecondaryCat.
	 * 
	 * @param patSecondaryCat
	 */
	public void setPatSecondaryCat(String patSecondaryCat)
	{
		this.patSecondaryCat = patSecondaryCat;
	}

	/**
	 * Retrieves patSecondaryCat.
	 * 
	 * @return Value of patSecondaryCat.
	 */
	public String getPatSecondaryCat()
	{
		return patSecondaryCat;
	}

	/**
	 * Sets patReligionCode.
	 * 
	 * @param patReligionCode
	 */
	public void setPatReligionCode(String patReligionCode)
	{
		this.patReligionCode = patReligionCode;
	}

	/**
	 * Retrieves patReligionCode.
	 * 
	 * @return Value of patReligionCode.
	 */
	public String getPatReligionCode()
	{
		return patReligionCode;
	}

	/**
	 * Sets patReligion.
	 * 
	 * @param patReligion
	 */
	public void setPatReligion(String patReligion)
	{
		this.patReligion = patReligion;
	}

	/**
	 * Retrieves patReligion.
	 * 
	 * @return Value of patReligion.
	 */
	public String getPatReligion()
	{
		return patReligion;
	}

	/**
	 * Sets patRegCatCode.
	 * 
	 * @param patRegCatCode
	 */
	public void setPatRegCatCode(String patRegCatCode)
	{
		this.patRegCatCode = patRegCatCode;
	}

	/**
	 * Retrieves patRegCatCode.
	 * 
	 * @return Value of patRegCatCode.
	 */
	public String getPatRegCatCode()
	{
		return patRegCatCode;
	}

	/**
	 * Sets patRefDoctor.
	 * 
	 * @param patRefDoctor
	 */
	public void setPatRefDoctor(String patRefDoctor)
	{
		this.patRefDoctor = patRefDoctor;
	}

	/**
	 * Retrieves patRefDoctor.
	 * 
	 * @return Value of patRefDoctor.
	 */
	public String getPatRefDoctor()
	{
		return patRefDoctor;
	}

	/**
	 * Sets patPrimaryCatCode.
	 * 
	 * @param patPrimaryCatCode
	 */
	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	/**
	 * Retrieves patPrimaryCatCode.
	 * 
	 * @return Value of patPrimaryCatCode.
	 */
	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	/**
	 * Sets patPrimaryCat.
	 * 
	 * @param patPrimaryCat
	 */
	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	/**
	 * Retrieves patPrimaryCat.
	 * 
	 * @return Value of patPrimaryCat.
	 */
	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	/**
	 * Sets patMonthlyIncome.
	 * 
	 * @param patMonthlyIncome
	 */
	public void setPatMonthlyIncome(String patMonthlyIncome)
	{
		this.patMonthlyIncome = patMonthlyIncome;
	}

	/**
	 * Retrieves patMonthlyIncome.
	 * 
	 * @return Value of patMonthlyIncome.
	 */
	public String getPatMonthlyIncome()
	{
		return patMonthlyIncome;
	}

	/**
	 * Sets patMomName.
	 * 
	 * @param patMomName
	 */
	public void setPatMotherName(String patMotherName)
	{
		this.patMotherName = patMotherName;
	}

	/**
	 * Retrieves patMomName.
	 * 
	 * @return Value of patMomName.
	 */
	public String getPatMotherName()
	{
		return patMotherName;
	}

	/**
	 * Sets patMiddleName.
	 * 
	 * @param patMiddleName
	 */
	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	/**
	 * Retrieves patMiddleName.
	 * 
	 * @return Value of patMiddleName.
	 */
	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	/**
	 * Sets patMaritalStatusCode.
	 * 
	 * @param patMaritalStatusCode
	 */
	public void setPatMaritalStatusCode(String patMaritalStatusCode)
	{
		this.patMaritalStatusCode = patMaritalStatusCode;
	}

	/**
	 * Retrieves patMaritalStatusCode.
	 * 
	 * @return Value of patMaritalStatusCode.
	 */
	public String getPatMaritalStatusCode()
	{
		return patMaritalStatusCode;
	}

	/**
	 * Sets patMaritalStatusCode.
	 * 
	 * @param patMaritalStatusCode
	 */
	public void setPatMaritalStatus(String patMaritalStatus)
	{
		this.patMaritalStatus = patMaritalStatus;
	}

	/**
	 * Retrieves patMaritalStatusCode.
	 * 
	 * @return Value of patMaritalStatusCode.
	 */
	public String getPatMaritalStatus()
	{
		return patMaritalStatus;
	}

	/**
	 * Sets patLastName.
	 * 
	 * @param patLastName
	 */
	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	/**
	 * Retrieves patLastName.
	 * 
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
	 * 
	 * @param patIsUrban
	 */
	public void setPatIsUrban(String patIsUrban)
	{
		this.patIsUrban = patIsUrban;
	}

	/**
	 * Retrieves patIsUrban.
	 * 
	 * @return Value of patIsUrban.
	 */
	public String getPatIsUrban()
	{
		return patIsUrban;
	}

	/**
	 * Sets patIsOld.
	 * 
	 * @param patIsOld
	 */
	public void setPatIsOld(String patIsOld)
	{
		this.patIsOld = patIsOld;
	}

	/**
	 * Retrieves patIsOld.
	 * 
	 * @return Value of patIsOld.
	 */
	public String getPatIsOld()
	{
		return patIsOld;
	}

	/**
	 * Sets patientAllowed.
	 * 
	 * @param patientAllowed
	 */
	public void setPatientAllowed(String patientAllowed)
	{
		this.patientAllowed = patientAllowed;
	}

	/**
	 * Retrieves patientAllowed.
	 * 
	 * @return Value of patientAllowed.
	 */
	public String getPatientAllowed()
	{
		return patientAllowed;
	}

	/**
	 * Sets patIdNo.
	 * 
	 * @param patIdNo
	 */
	public void setPatIdNo(String patIdNo)
	{
		this.patIdNo = patIdNo;
	}

	/**
	 * Retrieves patIdNo.
	 * 
	 * @return Value of patIdNo.
	 */
	public String getPatIdNo()
	{
		return patIdNo;
	}

	/**
	 * Sets patGuardianName.
	 * 
	 * @param patGuardianName
	 */
	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	/**
	 * Retrieves patGuardianName.
	 * 
	 * @return Value of patGuardianName.
	 */
	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	/**
	 * Sets patGenderCode.
	 * 
	 * @param patGenderCode
	 */
	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}

	/**
	 * Retrieves patGenderCode.
	 * 
	 * @return Value of patGenderCode.
	 */
	public String getPatGenderCode()
	{
		return patGenderCode;
	}

	/**
	 * Sets patGender.
	 * 
	 * @param patGender
	 */
	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	/**
	 * Retrieves patGender.
	 * 
	 * @return Value of patGender.
	 */
	public String getPatGender()
	{
		return patGender;
	}

	/**
	 * Sets patFirstName.
	 * 
	 * @param patFirstName
	 */
	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	/**
	 * Retrieves patFirstName.
	 * 
	 * @return Value of patFirstName.
	 */
	public String getPatFirstName()
	{
		return patFirstName;
	}

	/**
	 * Sets patDOB.
	 * 
	 * @param patDOB
	 */
	public void setPatDOB(String patDOB)
	{
		this.patDOB = patDOB;
	}

	/**
	 * Retrieves patDOB.
	 * 
	 * @return Value of patDOB.
	 */
	public String getPatDOB()
	{
		return patDOB;
	}

	/**
	 * Sets patCrNo.
	 * 
	 * @param patCrNo
	 */
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	/**
	 * Retrieves patCrNo.
	 * 
	 * @return Value of patCrNo.
	 */
	public String getPatCrNo()
	{
		return patCrNo;
	}

	/**
	 * Sets patAmountCollected.
	 * 
	 * @param patAmountCollected
	 */
	public void setPatAmountCollected(String patAmountCollected)
	{
		this.patAmountCollected = patAmountCollected;
	}

	/**
	 * Retrieves patAmountCollected.
	 * 
	 * @return Value of patAmountCollected.
	 */
	public String getPatAmountCollected()
	{
		return patAmountCollected;
	}

	/**
	 * Retrieves patAddress.
	 * 
	 * @return Value of patAddress.
	 */
	public vo.registration.AddressVO getPatAddress()
	{
		return patAddress;
	}

	/**
	 * Sets patAddress.
	 * 
	 * @param patAddress
	 */
	public void setPatAddress(vo.registration.AddressVO patAddress)
	{
		this.patAddress = patAddress;
	}

	/**
	 * Sets isValid.
	 * 
	 * @param isValid
	 */
	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	/**
	 * Retrieves isValid.
	 * 
	 * @return Value of isValid.
	 */
	public String getIsValid()
	{
		return isValid;
	}

	/**
	 * Sets isUnknown.
	 * 
	 * @param isUnknown
	 */
	public void setIsUnknown(String isUnknown)
	{
		this.isUnknown = isUnknown;
	}

	/**
	 * Retrieves isUnknown.
	 * 
	 * @return Value of isUnknown.
	 */
	public String getIsUnknown()
	{
		return isUnknown;
	}

	/**
	 * Sets isReferred.
	 * 
	 * @param isReferred
	 */
	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}

	/**
	 * Retrieves isReferred.
	 * 
	 * @return Value of isReferred.
	 */
	public String getIsReferred()
	{
		return isReferred;
	}

	/**
	 * Sets isMLC.
	 * 
	 * @param isMLC
	 */
	public void setIsMLC(String isMLC)
	{
		this.isMLC = isMLC;
	}

	/**
	 * Retrieves isMLC.
	 * 
	 * @return Value of isMLC.
	 */
	public String getIsMLC()
	{
		return isMLC;
	}

	/**
	 * Sets isActualDob.
	 * 
	 * @param isActualDob
	 */
	public void setIsActualDob(String isActualDob)
	{
		this.isActualDob = isActualDob;
	}

	/**
	 * Retrieves isActualDob.
	 * 
	 * @return Value of isActualDob.
	 */
	public String getIsActualDob()
	{
		return isActualDob;
	}


	/**
	 * Sets episodeVisitNo.
	 * 
	 * @param episodeVisitNo
	 */
	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	/**
	 * Retrieves episodeVisitNo.
	 * 
	 * @return Value of episodeVisitNo.
	 */
	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	/**
	 * Sets episodeTypeCode.
	 * 
	 * @param episodeTypeCode
	 */
	public void setEpisodeTypeCode(String episodeTypeCode)
	{
		this.episodeTypeCode = episodeTypeCode;
	}

	/**
	 * Retrieves episodeTypeCode.
	 * 
	 * @return Value of episodeTypeCode.
	 */
	public String getEpisodeTypeCode()
	{
		return episodeTypeCode;
	}

	/**
	 * Sets episodeType.
	 * 
	 * @param episodeType
	 */
	/*
	 * public void setEpisodeType( String episodeType ) { this.episodeType = episodeType; }
	 */

	/**
	 * Retrieves episodeType.
	 * 
	 * @return Value of episodeType.
	 */
	/*
	 * public String getEpisodeType( ) { return episodeType; }
	 */

	/**
	 * Sets episodeDate.
	 * 
	 * @param episodeDate
	 */
	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	/**
	 * Retrieves episodeDate.
	 * 
	 * @return Value of episodeDate.
	 */
	public String getEpisodeDate()
	{
		return episodeDate;
	}

	/**
	 * Sets entryDate.
	 * 
	 * @param entryDate
	 */
	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	/**
	 * Retrieves entryDate.
	 * 
	 * @return Value of entryDate.
	 */
	public String getEntryDate()
	{
		return entryDate;
	}

	/**
	 * Sets departmentUnitCode.
	 * 
	 * @param departmentUnitCode
	 */
	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	/**
	 * Retrieves departmentUnitCode.
	 * 
	 * @return Value of departmentUnitCode.
	 */
	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	/**
	 * Sets departmentUnit.
	 * 
	 * @param departmentUnit
	 */
	public void setDepartmentUnit(String departmentUnit)
	{
		this.departmentUnit = departmentUnit;
	}

	/**
	 * Retrieves departmentUnit.
	 * 
	 * @return Value of departmentUnit.
	 */
	public String getDepartmentUnit()
	{
		return departmentUnit;
	}

	/**
	 * Sets departmentCode.
	 * 
	 * @param departmentCode
	 */
	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	/**
	 * Retrieves departmentCode.
	 * 
	 * @return Value of departmentCode.
	 */
	public String getDepartmentCode()
	{
		return departmentCode;
	}

	/**
	 * Sets department.
	 * 
	 * @param department
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * Retrieves department.
	 * 
	 * @return Value of department.
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * Retrieves serialNo.
	 * 
	 * @return Value of serialNo.
	 */
	public String getSerialNo()
	{
		return serialNo;
	}

	/**
	 * Sets serialNo.
	 * 
	 * @param serialNo
	 */
	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	// populate addressVO

	/**
	 * Retrieves isAddressDelhi.
	 * 
	 * @return Value of isAddressDelhi.
	 */

	public String getIsAddressDelhi()
	{
		return getPatAddress().getIsAddressDelhi();
	}

	/**
	 * Sets isAddressDelhi.
	 * 
	 * @param isAddressDelhi
	 */
	public void setIsAddressDelhi(String isAddressDelhi)
	{
		this.getPatAddress().setIsAddressDelhi(isAddressDelhi);
	}

	/**
	 * Retrieves isCurrentAddress.
	 * 
	 * @return Value of isCurrentAddress.
	 */

	public String getIsCurrentAddress()
	{
		return getPatAddress().getIsCurrentAddress();
	}

	/**
	 * Sets isCurrentAddress.
	 * 
	 * @param isCurrentAddress
	 */
	public void setIsCurrentAddress(String isCurrentAddress)
	{
		this.getPatAddress().setIsCurrentAddress(isCurrentAddress);
	}

	/**
	 * Retrieves patAddCity.
	 * 
	 * @return Value of patAddCity.
	 */
	public String getPatAddCity()
	{
		return getPatAddress().getPatAddCity();
	}

	/**
	 * Sets patAddCity.
	 * 
	 * @param patAddCity
	 */
	public void setPatAddCity(String patAddCity)
	{
		this.getPatAddress().setPatAddCity(patAddCity);
	}

	/**
	 * Retrieves patAddCityLoc.
	 * 
	 * @return Value of patAddCityLoc.
	 */
	public String getPatAddCityLoc()
	{
		return getPatAddress().getPatAddCityLoc();
	}

	/**
	 * Sets patAddCityLoc.
	 * 
	 * @param patAddCityLoc
	 */
	public void setPatAddCityLoc(String patAddCityLoc)
	{
		this.getPatAddress().setPatAddCityLoc(patAddCityLoc);
	}

	/**
	 * Retrieves patAddCityLocCode.
	 * 
	 * @return Value of patAddCityLocCode.
	 */
	public String getPatAddCityLocCode()
	{
		return getPatAddress().getPatAddCityLocCode();
	}

	/**
	 * Sets patAddCityLocCode.
	 * 
	 * @param patAddCityLocCode
	 */
	public void setPatAddCityLocCode(String patAddCityLocCode)
	{
		this.getPatAddress().setPatAddCityLocCode(patAddCityLocCode);
	}

	/**
	 * Retrieves patAddContactNo.
	 * 
	 * @return Value of patAddContactNo.
	 */
	public String getPatAddContactNo()
	{
		return getPatAddress().getPatAddContactNo();
	}

	/**
	 * Sets patAddContactNo.
	 * 
	 * @param patAddContactNo
	 */
	public void setPatAddContactNo(String patAddContactNo)
	{
		this.getPatAddress().setPatAddContactNo(patAddContactNo);
	}

	/**
	 * Retrieves patAddCountry.
	 * 
	 * @return Value of patAddCountry.
	 */
	public String getPatAddCountry()
	{
		return getPatAddress().getPatAddCountry();
	}

	/**
	 * Sets patAddCountry.
	 * 
	 * @param patAddCountry
	 */
	public void setPatAddCountry(String patAddCountry)
	{
		this.getPatAddress().setPatAddCountry(patAddCountry);
	}

	/**
	 * Retrieves patAddCountryCode.
	 * 
	 * @return Value of patAddCountryCode.
	 */
	public String getPatAddCountryCode()
	{
		return getPatAddress().getPatAddCountryCode();
	}

	/**
	 * Sets patAddCountryCode.
	 * 
	 * @param patAddCountryCode
	 */
	public void setPatAddCountryCode(String patAddCountryCode)
	{
		this.getPatAddress().setPatAddCountryCode(patAddCountryCode);
	}

	/**
	 * Retrieves patAddDistrict.
	 * 
	 * @return Value of patAddDistrict.
	 */
	public String getPatAddDistrict()
	{
		return getPatAddress().getPatAddDistrict();
	}

	/**
	 * Sets patAddDistrict.
	 * 
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
	 * 
	 * @return Value of patAddHNo.
	 */
	public String getPatAddHNo()
	{
		return getPatAddress().getPatAddHNo();
	}

	/**
	 * Sets patAddHNo.
	 * 
	 * @param patAddHNo
	 */
	public void setPatAddHNo(String patAddHNo)
	{
		this.getPatAddress().setPatAddHNo(patAddHNo);
	}

	/**
	 * Retrieves patAddPIN.
	 * 
	 * @return Value of patAddPIN.
	 */
	public String getPatAddPIN()
	{
		return getPatAddress().getPatAddPIN();
	}

	/**
	 * Sets patAddPIN.
	 * 
	 * @param patAddPIN
	 */
	public void setPatAddPIN(String patAddPIN)
	{
		this.getPatAddress().setPatAddPIN(patAddPIN);
	}

	/**
	 * Retrieves patAddState.
	 * 
	 * @return Value of patAddState.
	 */
	public String getPatAddState()
	{
		return getPatAddress().getPatAddState();
	}

	/**
	 * Sets patAddState.
	 * 
	 * @param patAddState
	 */
	public void setPatAddState(String patAddState)
	{
		this.getPatAddress().setPatAddState(patAddState);
	}

	/**
	 * Retrieves patAddStateCode.
	 * 
	 * @return Value of patAddStateCode.
	 */
	public String getPatAddStateCode()
	{
		return getPatAddress().getPatAddStateCode();
	}

	public String getPatAddStateName()
	{
		return getPatAddress().getPatAddStateName();
	}

	/**
	 * Sets patAddStateCode.
	 * 
	 * @param patAddStateCode
	 */
	public void setPatAddStateName(String patAddStateName)
	{
		this.getPatAddress().setPatAddStateName(patAddStateName);
	}

	public void setPatAddStateCode(String patAddStateCode)
	{
		this.getPatAddress().setPatAddStateCode(patAddStateCode);
	}

	/**
	 * Retrieves patAddStreet.
	 * 
	 * @return Value of patAddStreet.
	 */

	public String getPatAddStreet()
	{
		return getPatAddress().getPatAddStreet();
	}

	/**
	 * Sets patAddStreet.
	 * 
	 * @param patAddStreet
	 */
	public void setPatAddStreet(String patAddStreet)
	{
		this.getPatAddress().setPatAddStreet(patAddStreet);
	}

	/**
	 * Retrieves patAddType.
	 * 
	 * @return Value of patAddType.
	 */
	public String getPatAddType()
	{
		return getPatAddress().getPatAddType();
	}

	/**
	 * Sets patAddType.
	 * 
	 * @param patAddType
	 */
	public void setPatAddType(String patAddType)
	{
		this.getPatAddress().setPatAddType(patAddType);
	}

	/**
	 * Retrieves patAddTypeCode.
	 * 
	 * @return Value of patAddTypeCode.
	 */
	public String getPatAddTypeCode()
	{
		return getPatAddress().getPatAddTypeCode();
	}

	/**
	 * Sets patAddTypeCode.
	 * 
	 * @param patAddTypeCode
	 */
	public void setPatAddTypeCode(String patAddTypeCode)
	{
		this.getPatAddress().setPatAddTypeCode(patAddTypeCode);
	}

	/**
	 * Retrieves isRefferInOut.
	 * 
	 * @return Value of isRefferInOut.
	 */
	/*
	 * public String getIsRefferInOut() { return isRefferInOut; }
	 */

	/**
	 * Sets isRefferInOut.
	 * 
	 * @param isRefferInOut
	 */
	/*
	 * public void setIsRefferInOut(String isRefferInOut) { this.isRefferInOut = isRefferInOut; }
	 */

	/**
	 * Retrieves seatId.
	 * 
	 * @return Value of seatId.
	 */
	public String getSeatId()
	{
		return seatId;
	}

	/**
	 * Sets seatId.
	 * 
	 * @param seatId
	 */
	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	/**
	 * Retrieves patNationality.
	 * 
	 * @return Value of patNationality.
	 */
	public String getPatNationality()
	{
		return patNationality;
	}

	/**
	 * Sets patNationality.
	 * 
	 * @param patNationality
	 */
	public void setPatNationality(String patNationality)
	{
		this.patNationality = patNationality;
	}

	/**
	 * Retrieves patNationalityCode.
	 * 
	 * @return Value of patNationalityCode.
	 */
	public String getPatNationalityCode()
	{
		return patNationalityCode;
	}

	/**
	 * Sets patNationalityCode.
	 * 
	 * @param patNationalityCode
	 */
	public void setPatNationalityCode(String patNationalityCode)
	{
		this.patNationalityCode = patNationalityCode;
	}

	/**
	 * Retrieves patRefGnctdHospitalCrno.
	 * 
	 * @return Value of patRefGnctdHospitalCrno.
	 */
	public String getPatRefGnctdHospitalCrno()
	{
		return patRefGnctdHospitalCrno;
	}

	/**
	 * Sets patRefGnctdHospitalCrno.
	 * 
	 * @param patRefGnctdHospitalCrno
	 */
	public void setPatRefGnctdHospitalCrno(String patRefGnctdHospitalCrno)
	{
		this.patRefGnctdHospitalCrno = patRefGnctdHospitalCrno;
	}

	/**
	 * Retrieves patRefGnctdHospitalDept.
	 * 
	 * @return Value of patRefGnctdHospitalDept.
	 */
	public String getPatRefGnctdHospitalDept()
	{
		return patRefGnctdHospitalDept;
	}

	/**
	 * Sets patRefGnctdHospitalDept.
	 * 
	 * @param patRefGnctdHospitalDept
	 */
	public void setPatRefGnctdHospitalDept(String patRefGnctdHospitalDept)
	{
		this.patRefGnctdHospitalDept = patRefGnctdHospitalDept;
	}

	/**
	 * Retrieves patRefGnctdHospitalDeptUnit.
	 * 
	 * @return Value of patRefGnctdHospitalDeptUnit.
	 */
	public String getPatRefGnctdHospitalDeptUnit()
	{
		return patRefGnctdHospitalDeptUnit;
	}

	/**
	 * Sets patRefGnctdHospitalDeptUnit.
	 * 
	 * @param patRefGnctdHospitalDeptUnit
	 */
	public void setPatRefGnctdHospitalDeptUnit(String patRefGnctdHospitalDeptUnit)
	{
		this.patRefGnctdHospitalDeptUnit = patRefGnctdHospitalDeptUnit;
	}

	/**
	 * Retrieves episodeVisitType.
	 * 
	 * @return Value of episodeVisitType.
	 */
	public String getEpisodeVisitType()
	{
		return episodeVisitType;
	}

	/**
	 * Sets episodeVisitType.
	 * 
	 * @param episodeVisitType
	 */
	public void setEpisodeVisitType(String episodeVisitType)
	{
		this.episodeVisitType = episodeVisitType;
	}

	public String getRegistrationType()
	{
		return registrationType;
	}

	public void setRegistrationType(String registrationType)
	{
		this.registrationType = registrationType;
	}

	public int compareByCrNo(DailyPatientVO dailyPatientVO)
	{
		int flag = 0;
		if (Long.parseLong(this.getPatCrNo()) == Long.parseLong(dailyPatientVO.getPatCrNo()))
		{
			flag = 0;
		}
		else
		{
			if (Long.parseLong(this.getPatCrNo()) < Long.parseLong(dailyPatientVO.getPatCrNo())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = -1;
			}
			else
			{
				if (Long.parseLong(this.getPatCrNo()) < Long.parseLong(dailyPatientVO.getPatCrNo())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = 1;
				}

			}

			if (Long.parseLong(this.getPatCrNo()) > Long.parseLong(dailyPatientVO.getPatCrNo())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = 1;
			}
			else
			{
				if (Long.parseLong(this.getPatCrNo()) > Long.parseLong(dailyPatientVO.getPatCrNo())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = -1;
				}
			}
		}

		return flag;
	}

	public int compareByQueueNo(DailyPatientVO dailyPatientVO)
	{
		int flag = 0;
		if (Long.parseLong(this.getQueNo()) == Long.parseLong(dailyPatientVO.getQueNo()))
		{
			flag = 0;
		}
		else
		{
			if (Long.parseLong(this.getQueNo()) < Long.parseLong(dailyPatientVO.getQueNo())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = -1;
			}
			else
			{
				if (Long.parseLong(this.getQueNo()) < Long.parseLong(dailyPatientVO.getQueNo())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = 1;
				}

			}

			if (Long.parseLong(this.getQueNo()) > Long.parseLong(dailyPatientVO.getQueNo())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = 1;
			}
			else
			{
				if (Long.parseLong(this.getQueNo()) > Long.parseLong(dailyPatientVO.getQueNo())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = -1;
				}
			}
		}

		return flag;
	}

	public int compareByName(DailyPatientVO dailyPatientVO)
	{
		int flag = 0;
		String name1 = this.getPatFirstName() + this.getPatMiddleName() + this.getPatLastName();
		String name2 = dailyPatientVO.getPatFirstName() + dailyPatientVO.getPatMiddleName() + dailyPatientVO.getPatLastName();
		if (dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC)) flag = name1.compareTo(name2);
		else if (dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
		{
			int check = name1.compareTo(name2);
			if (check == 0)
			{
				flag = 0;
			}
			else
			{
				if (check == 1) flag = -1;
				else
				{
					flag = 1;
				}
			}

		}

		return flag;

	}

	public int compareByPatientCategory(DailyPatientVO dailyPatientVO)
	{
		int flag = 0;
		if (Long.parseLong(this.getPatPrimaryCatCode()) == Long.parseLong(dailyPatientVO.getPatPrimaryCatCode()))
		{
			flag = 0;
		}
		else
		{
			if (Long.parseLong(this.getPatPrimaryCatCode()) < Long.parseLong(dailyPatientVO.getPatPrimaryCatCode())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = -1;
			}
			else
			{
				if (Long.parseLong(this.getPatPrimaryCatCode()) < Long.parseLong(dailyPatientVO.getPatPrimaryCatCode())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = 1;
				}

			}

			if (Long.parseLong(this.getPatPrimaryCatCode()) > Long.parseLong(dailyPatientVO.getPatPrimaryCatCode())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = 1;
			}
			else
			{
				if (Long.parseLong(this.getPatPrimaryCatCode()) > Long.parseLong(dailyPatientVO.getPatPrimaryCatCode())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = -1;
				}
			}
		}

		return flag;
	}

	public int compareByColorCode(DailyPatientVO _vo)
	{
		if (_vo.getSortType().equals(Config.SORT_TYPE_ASC)) 
			return Integer.parseInt(this.colorCodeOrder) - Integer.parseInt(_vo.colorCodeOrder);
		else return -Integer.parseInt(this.colorCodeOrder) + Integer.parseInt(_vo.colorCodeOrder);
	}

	public int compareByTriageDuration(DailyPatientVO dailyPatientVO)
	{
		int flag = 0;
		if (Long.parseLong(this.getTriageDurationHours()) == Long.parseLong(dailyPatientVO.getTriageDurationHours()))
		{
			flag = 0;
		}
		else
		{
			if (Long.parseLong(this.getTriageDurationHours()) < Long.parseLong(dailyPatientVO.getTriageDurationHours())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = -1;
			}
			else
			{
				if (Long.parseLong(this.getTriageDurationHours()) < Long.parseLong(dailyPatientVO.getTriageDurationHours())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = 1;
				}

			}

			if (Long.parseLong(this.getTriageDurationHours()) > Long.parseLong(dailyPatientVO.getTriageDurationHours())
					&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_ASC))
			{
				flag = 1;
			}
			else
			{
				if (Long.parseLong(this.getTriageDurationHours()) > Long.parseLong(dailyPatientVO.getTriageDurationHours())
						&& dailyPatientVO.getSortType().equals(Config.SORT_TYPE_DSC))
				{
					flag = -1;
				}
			}
		}

		return flag;
	}

	public int compareTo(Object o)
	{
		int flag = 0;
		DailyPatientVO dailyPatientVO = (DailyPatientVO) o;
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_QUEUE_NO)) flag = compareByQueueNo(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_CR_NO)) flag = compareByCrNo(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_NAME)) flag = compareByName(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY)) flag = compareByPatientCategory(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_COLOR_CODE)) flag = compareByColorCode(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_TRIAGE_DURATION)) flag = compareByTriageDuration(dailyPatientVO);		
		return flag;
	}

	public int compare(Object o1, Object o2)
	{
		int flag = 0;
		DailyPatientVO dailyPatientVO = (DailyPatientVO) o2;
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_QUEUE_NO)) flag = compareByQueueNo(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_CR_NO)) flag = compareByCrNo(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_NAME)) flag = compareByName(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY)) flag = compareByPatientCategory(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_COLOR_CODE)) flag = compareByColorCode(dailyPatientVO);
		if (dailyPatientVO.getOrederBy().equals(Config.OPD_PATIENT_OREDRE_BY_TRIAGE_DURATION)) flag = compareByTriageDuration(dailyPatientVO);
		return flag;
	}

	public String getSortType()
	{
		return sortType;
	}

	public void setSortType(String sortType)
	{
		this.sortType = sortType;
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

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getFileNoView() {
		return fileNoView;
	}

	public void setFileNoView(String fileNoView) {
		this.fileNoView = fileNoView;
	}

	public String getPatBloodGroupCode() {
		return patBloodGroupCode;
	}

	public void setPatBloodGroupCode(String patBloodGroupCode) {
		this.patBloodGroupCode = patBloodGroupCode;
	}

	public String getDepartmentUnitType() {
		return departmentUnitType;
	}

	public void setDepartmentUnitType(String departmentUnitType) {
		this.departmentUnitType = departmentUnitType;
	}

	public String getTriageDurationHours()
	{
		return triageDurationHours;
	}

	public void setTriageDurationHours(String triageDurationHours)
	{
		this.triageDurationHours = triageDurationHours;
	}

	public String getUnitConsultant() {
		return unitConsultant;
	}

	public void setUnitConsultant(String unitConsultant) {
		this.unitConsultant = unitConsultant;
	}

	public String getFilePrintSetting() {
		return filePrintSetting;
	}

	public void setFilePrintSetting(String filePrintSetting) {
		this.filePrintSetting = filePrintSetting;
	}

	public String getCardPrintSetting() {
		return cardPrintSetting;
	}

	public void setCardPrintSetting(String cardPrintSetting) {
		this.cardPrintSetting = cardPrintSetting;
	}

	public String getPatAgeUnit() {
		return patAgeUnit;
	}

	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}

	public String getPatAgeWithUnit() {
		return patAgeWithUnit;
	}

	public void setPatAgeWithUnit(String patAgeWithUnit) {
		this.patAgeWithUnit = patAgeWithUnit;
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

	public String getPatVisitReason() {
		return patVisitReason;
	}

	public void setPatVisitReason(String patVisitReason) {
		this.patVisitReason = patVisitReason;
	}

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

	public String getPatPrimaryCatValid() {
		return patPrimaryCatValid;
	}

	public void setPatPrimaryCatValid(String patPrimaryCatValid) {
		this.patPrimaryCatValid = patPrimaryCatValid;
	}

	public String getPatGeneralExpDate() {
		return patGeneralExpDate;
	}

	public void setPatGeneralExpDate(String patGeneralExpDate) {
		this.patGeneralExpDate = patGeneralExpDate;
	}

	public String getPatSpecialExpDate() {
		return patSpecialExpDate;
	}

	public void setPatSpecialExpDate(String patSpecialExpDate) {
		this.patSpecialExpDate = patSpecialExpDate;
	}

	public String getPatCasualityExpDate() {
		return patCasualityExpDate;
	}

	public void setPatCasualityExpDate(String patCasualityExpDate) {
		this.patCasualityExpDate = patCasualityExpDate;
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

	public String getPatBirthPlace() {
		return patBirthPlace;
	}

	public void setPatBirthPlace(String patBirthPlace) {
		this.patBirthPlace = patBirthPlace;
	}

	public String getPatIsMerged() {
		return patIsMerged;
	}

	public void setPatIsMerged(String patIsMerged) {
		this.patIsMerged = patIsMerged;
	}

	public String getPatDocType() {
		return patDocType;
	}

	public void setPatDocType(String patDocType) {
		this.patDocType = patDocType;
	}

	public String getPatNameType() {
		return patNameType;
	}

	public void setPatNameType(String patNameType) {
		this.patNameType = patNameType;
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

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getIsCardPrint() {
		return isCardPrint;
	}

	public void setIsCardPrint(String isCardPrint) {
		this.isCardPrint = isCardPrint;
	}

	public String getEpisodeReferCode() {
		return episodeReferCode;
	}

	public void setEpisodeReferCode(String episodeReferCode) {
		this.episodeReferCode = episodeReferCode;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getEpisodeExpiryDate() {
		return episodeExpiryDate;
	}

	public void setEpisodeExpiryDate(String episodeExpiryDate) {
		this.episodeExpiryDate = episodeExpiryDate;
	}

	public String getEpisodeStartDate() {
		return episodeStartDate;
	}

	public void setEpisodeStartDate(String episodeStartDate) {
		this.episodeStartDate = episodeStartDate;
	}

	public String getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public String getTariffId() {
		return tariffId;
	}

	public void setTariffId(String tariffId) {
		this.tariffId = tariffId;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getChangeToDeptUnitCode() {
		return changeToDeptUnitCode;
	}

	public void setChangeToDeptUnitCode(String changeToDeptUnitCode) {
		this.changeToDeptUnitCode = changeToDeptUnitCode;
	}

	
	public String getChangeToRoomCode() {
		return changeToRoomCode;
	}

	public void setChangeToRoomCode(String changeToRoomCode) {
		this.changeToRoomCode = changeToRoomCode;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getTreatmentValidUpTo() {
		return treatmentValidUpTo;
	}

	public void setTreatmentValidUpTo(String treatmentValidUpTo) {
		this.treatmentValidUpTo = treatmentValidUpTo;
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
	public String getPatAddPhoneNo()
	{
		return getPatAddress().getPatAddPhoneNo();
	}
	public String getPatAddEmailId()
	{
		return getPatAddress().getPatAddEmailId();
	}
	public String getPatIsLocal() {
		return getPatAddress().getPatIsLocal();
	}
	public String getRoundRobinUnitFlag() {
		return roundRobinUnitFlag;
	}

	public void setRoundRobinUnitFlag(String roundRobinUnitFlag) {
		this.roundRobinUnitFlag = roundRobinUnitFlag;
	}

	public String getRoundRobinRoomFlag() {
		return roundRobinRoomFlag;
	}

	public void setRoundRobinRoomFlag(String roundRobinRoomFlag) {
		this.roundRobinRoomFlag = roundRobinRoomFlag;
	}

	public String getIsForceful() {
		return isForceful;
	}

	public void setIsForceful(String isForceful) {
		this.isForceful = isForceful;
	}

	public String getPatCatShortName() {
		return patCatShortName;
	}

	public void setPatCatShortName(String patCatShortName) {
		this.patCatShortName = patCatShortName;
	}

	public String getMlcFlag() {
		return mlcFlag;
	}

	public void setMlcFlag(String mlcFlag) {
		this.mlcFlag = mlcFlag;
	}

	public String getIsImageUploaded() {
		return isImageUploaded;
	}

	public void setIsImageUploaded(String isImageUploaded) {
		this.isImageUploaded = isImageUploaded;
	}

	public String getPatAptId() {
		return patAptId;
	}

	public void setPatAptId(String patAptId) {
		this.patAptId = patAptId;
	}

	public String getPatAptStatus() {
		return patAptStatus;
	}

	public void setPatAptStatus(String patAptStatus) {
		this.patAptStatus = patAptStatus;
	}

	public String getPatAptNo() {
		return patAptNo;
	}

	public void setPatAptNo(String patAptNo) {
		this.patAptNo = patAptNo;
	}

	public String getPatAptSlot() {
		return patAptSlot;
	}

	public void setPatAptSlot(String patAptSlot) {
		this.patAptSlot = patAptSlot;
	}

	public String getPatAptQueueNO() {
		return patAptQueueNO;
	}

	public void setPatAptQueueNO(String patAptQueueNO) {
		this.patAptQueueNO = patAptQueueNO;
	}

	public String getIsAppointment() {
		return isAppointment;
	}

	public void setIsAppointment(String isAppointment) {
		this.isAppointment = isAppointment;
	}

	public String getIsAmbulatory() {
		return isAmbulatory;
	}

	public void setIsAmbulatory(String isAmbulatory) {
		this.isAmbulatory = isAmbulatory;
	}

	public String getBroughtByConsultant() {
		return broughtByConsultant;
	}

	public void setBroughtByConsultant(String broughtByConsultant) {
		this.broughtByConsultant = broughtByConsultant;
	}
	


}
