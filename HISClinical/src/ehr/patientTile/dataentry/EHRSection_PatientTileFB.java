package ehr.patientTile.dataentry;

import registration.controller.fb.CRNoFB;

public class EHRSection_PatientTileFB extends CRNoFB
{
	private String patAdmNo;
	// private String patName;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patAge;
	// private String patAgeUnit;
	private String patGender;
	private String patGenderCode;
	private String admAdvNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String admDateTime;
	private String registerDate;
	private String admStatusCode;
	private String hmode;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String patRegCat;
	private String patRegCatCode;
	private String patSpouceName;
	private String patFatHusbandName;
	private String mlcNo;
	private String isDead;
	private String wardCode;
	private String bedCode;
	private String wardName;
	private String bedName;
	private String roomCode;
	private String room;
	private String departmentName;
	private String departmentUnitName;
	private String departmentCode;
	private String departmentUnitCode;
	private String consultantID;
	private String consultantName;
	private String patGuardianName;
	private String isBroughtDead;
	private String isIpd;
	private String ipdRoomCode;
	private String ipdRoomName;
	private String isNewBorn;
	private String patMotherCrNo;
	private String patStatusCode;

	private String patIsPregnant;
	private String patGestationWeek;
	private String patAdmittedDays;

	private String patFatherName;
	private String patMotherName;
	private String patAddDistrict;
	private String patCatcode;
	private String patCat;
	private String patAddContactNo;
	private String patSpouseName;
	private String patOccupation;
	private String patientAddress;
	private String patCompleteAddress;
	
	//added for discharge tile composition
	private String dischargedOn;
	private String dischargeType;
	private String admittedOn;
	private String consultantId;
	private String patCompleteAddressForDischargeSummary;
	
	
	public String getPatStatusCode()
	{
		return patStatusCode;
	}

	public void setPatStatusCode(String patStatusCode)
	{
		this.patStatusCode = patStatusCode;
	}

	public String getIsIpd()
	{
		return isIpd;
	}

	public void setIsIpd(String isIpd)
	{
		this.isIpd = isIpd;
	}

	public String getIsBroughtDead()
	{
		return isBroughtDead;
	}

	public void setIsBroughtDead(String isBroughtDead)
	{
		this.isBroughtDead = isBroughtDead;
	}

	public String getPatGuardianName()
	{
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName)
	{
		this.patGuardianName = patGuardianName;
	}

	public String getRoomCode()
	{
		return roomCode;
	}

	public void setRoomCode(String roomCode)
	{
		this.roomCode = roomCode;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}

	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	public String getDepartmentUnitName()
	{
		return departmentUnitName;
	}

	public void setDepartmentUnitName(String departmentUnitName)
	{
		this.departmentUnitName = departmentUnitName;
	}

	public String getDepartmentCode()
	{
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode)
	{
		this.departmentCode = departmentCode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getMlcNo()
	{
		return mlcNo;
	}

	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public String getIsDead()
	{
		return isDead;
	}

	public void setIsDead(String isDead)
	{
		this.isDead = isDead;
	}

	public String getPatRegCatCode()
	{
		return patRegCatCode;
	}

	public void setPatRegCatCode(String patRegCatCode)
	{
		this.patRegCatCode = patRegCatCode;
	}

	public String getPatAdmNo()
	{
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo)
	{
		this.patAdmNo = patAdmNo;
	}

	public String getPatAge()
	{
		return patAge;
	}

	public void setPatAge(String patAge)
	{
		this.patAge = patAge;
	}

	public String getPatGender()
	{
		return patGender;
	}

	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	public String getAdmAdvNo()
	{
		return admAdvNo;
	}

	public void setAdmAdvNo(String admAdvNo)
	{
		this.admAdvNo = admAdvNo;
	}

	public String getEpisodeCode()
	{
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode)
	{
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getAdmDateTime()
	{
		return admDateTime;
	}

	public void setAdmDateTime(String admDateTime)
	{
		this.admDateTime = admDateTime;
	}

	public String getAdmStatusCode()
	{
		return admStatusCode;
	}

	public void setAdmStatusCode(String admStatusCode)
	{
		this.admStatusCode = admStatusCode;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getPatSpouceName()
	{
		return patSpouceName;
	}

	public void setPatSpouceName(String patSpouceName)
	{
		this.patSpouceName = patSpouceName;
	}

	public String getPatFatHusbandName()
	{
		return patFatHusbandName;
	}

	public void setPatFatHusbandName(String patFatHusbandName)
	{
		this.patFatHusbandName = patFatHusbandName;
	}

	public String getPatFirstName()
	{
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
	}

	public String getPatLastName()
	{
		return patLastName;
	}

	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	public String getPatGenderCode()
	{
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode)
	{
		this.patGenderCode = patGenderCode;
	}

	public String getPatPrimaryCat()
	{
		return patPrimaryCat;
	}

	public void setPatPrimaryCat(String patPrimaryCat)
	{
		this.patPrimaryCat = patPrimaryCat;
	}

	public String getPatPrimaryCatCode()
	{
		return patPrimaryCatCode;
	}

	public void setPatPrimaryCatCode(String patPrimaryCatCode)
	{
		this.patPrimaryCatCode = patPrimaryCatCode;
	}

	public String getPatRegCat()
	{
		return patRegCat;
	}

	public void setPatRegCat(String patRegCat)
	{
		this.patRegCat = patRegCat;
	}

	public String getWardName()
	{
		return wardName;
	}

	public void setWardName(String wardName)
	{
		this.wardName = wardName;
	}

	public String getConsultantID()
	{
		return consultantID;
	}

	public void setConsultantID(String consultantID)
	{
		this.consultantID = consultantID;
	}

	public String getConsultantName()
	{
		return consultantName;
	}

	public void setConsultantName(String consultantName)
	{
		this.consultantName = consultantName;
	}

	public String getWardCode()
	{
		return wardCode;
	}

	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	public String getBedCode()
	{
		return bedCode;
	}

	public void setBedCode(String bedCode)
	{
		this.bedCode = bedCode;
	}

	public String getBedName()
	{
		return bedName;
	}

	public void setBedName(String bedName)
	{
		this.bedName = bedName;
	}

	public String getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}

	public String getIpdRoomCode()
	{
		return ipdRoomCode;
	}

	public void setIpdRoomCode(String ipdRoomCode)
	{
		this.ipdRoomCode = ipdRoomCode;
	}

	public String getIpdRoomName()
	{
		return ipdRoomName;
	}

	public void setIpdRoomName(String ipdRoomName)
	{
		this.ipdRoomName = ipdRoomName;
	}

	public String getIsNewBorn()
	{
		return isNewBorn;
	}

	public void setIsNewBorn(String isNewBorn)
	{
		this.isNewBorn = isNewBorn;
	}

	public String getPatMotherCrNo()
	{
		return patMotherCrNo;
	}

	public void setPatMotherCrNo(String patMotherCrNo)
	{
		this.patMotherCrNo = patMotherCrNo;
	}

	public String getPatIsPregnant()
	{
		return patIsPregnant;
	}

	public void setPatIsPregnant(String patIsPregnant)
	{
		this.patIsPregnant = patIsPregnant;
	}

	public String getPatGestationWeek()
	{
		return patGestationWeek;
	}

	public void setPatGestationWeek(String patGestationWeek)
	{
		this.patGestationWeek = patGestationWeek;
	}

	public String getPatAdmittedDays()
	{
		return patAdmittedDays;
	}

	public void setPatAdmittedDays(String patAdmittedDays)
	{
		this.patAdmittedDays = patAdmittedDays;
	}

	public String getPatFatherName() {
		return patFatherName;
	}

	public void setPatFatherName(String patFatherName) {
		this.patFatherName = patFatherName;
	}

	public String getPatMotherName() {
		return patMotherName;
	}

	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}

	public String getPatAddDistrict() {
		return patAddDistrict;
	}

	public void setPatAddDistrict(String patAddDistrict) {
		this.patAddDistrict = patAddDistrict;
	}

	public String getPatCatcode() {
		return patCatcode;
	}

	public void setPatCatcode(String patCatcode) {
		this.patCatcode = patCatcode;
	}

	public String getPatCat() {
		return patCat;
	}

	public void setPatCat(String patCat) {
		this.patCat = patCat;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDischargedOn() {
		return dischargedOn;
	}

	public void setDischargedOn(String dischargedOn) {
		this.dischargedOn = dischargedOn;
	}

	public String getDischargeType() {
		return dischargeType;
	}

	public void setDischargeType(String dischargeType) {
		this.dischargeType = dischargeType;
	}

	public String getAdmittedOn() {
		return admittedOn;
	}

	public void setAdmittedOn(String admittedOn) {
		this.admittedOn = admittedOn;
	}

	public String getConsultantId() {
		return consultantId;
	}

	public void setConsultantId(String consultantId) {
		this.consultantId = consultantId;
	}

	public String getPatAddContactNo() {
		return patAddContactNo;
	}

	public void setPatAddContactNo(String patAddContactNo) {
		this.patAddContactNo = patAddContactNo;
	}

	public String getPatSpouseName() {
		return patSpouseName;
	}

	public void setPatSpouseName(String patSpouseName) {
		this.patSpouseName = patSpouseName;
	}

	public String getPatOccupation() {
		return patOccupation;
	}

	public void setPatOccupation(String patOccupation) {
		this.patOccupation = patOccupation;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getPatCompleteAddress() {
		return patCompleteAddress;
	}

	public void setPatCompleteAddress(String patCompleteAddress) {
		this.patCompleteAddress = patCompleteAddress;
	}

	public String getPatCompleteAddressForDischargeSummary() {
		return patCompleteAddressForDischargeSummary;
	}

	public void setPatCompleteAddressForDischargeSummary(
			String patCompleteAddressForDischargeSummary) {
		this.patCompleteAddressForDischargeSummary = patCompleteAddressForDischargeSummary;
	}

	

}
