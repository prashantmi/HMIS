package investigationDesk.vo;

import hisglobal.vo.ValueObject;

public class Inv_RequisitionRaisingPatientVO extends ValueObject{
	private static final long serialVersionUID = -4294089437863920752L;
	private String patCRNo;
	private String patFirstName;
    private String patMiddleName;
	private String patLastName;
    private String patGender;
	private String patGenderCode;
    private String patAge;
    private String patAgeUnit;
    private String patDOB;
    private String isActualDob;
    private String patCategory;
	private String patCategoryCode;
   	private String patHusbandName;
	private String patGuardianName;
	private String patDepartment;
	//patientDtl table
	
	private String patStatus;
	private String patStatusCode;
	
	private String isCatExpired;
	
	private String patMobileNo;
	private String patAddress;
	
	//EpisodeVo Details
	private String patepisodecode;
	private String patvisitdate;
	private String departmentcode;
	private String patdeptunitcode;
	private String patdeptunit;
	private String department;
	private String room;
	private String patepisodetypecode;
	private String patvisittypecode;
	private String hospitalcode;
	private String patmlcno;
	private String diagnosis;
	private String patVisitNo;
	
	
	//Admission Detail VO
	private String admitteddepartmentcode;
	private String patadmissionno;
	private String admitteddepartmentname;
	private String patwardcode;
	private String patwardname;
	private String patroomno;
	private String patroomname;
	private String bedname;	
	private String consultantName;
	private String patAccNo;
	
	
	private String roomCode;
	private String bedCode;
	
	private String advisedByDocName;
	private String advisedByDocNo;
	private String admissionDate;
	
	//sample coll vo
	private String uomCode;
	private String containerCode;
	private String containerVolume;
	private String tempSampleNo;
	
	
	
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getAdvisedByDocName() {
		return advisedByDocName;
	}
	public void setAdvisedByDocName(String advisedByDocName) {
		this.advisedByDocName = advisedByDocName;
	}
	public String getAdvisedByDocNo() {
		return advisedByDocNo;
	}
	public void setAdvisedByDocNo(String advisedByDocNo) {
		this.advisedByDocNo = advisedByDocNo;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getPatDepartment() {
		return patDepartment;
	}
	public void setPatDepartment(String patDepartment) {
		this.patDepartment = patDepartment;
	}
	
	
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCrNo) {
		this.patCRNo = patCrNo;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatCategory() {
		return patCategory;
	}
	public void setPatCategory(String patCategory) {
		this.patCategory = patCategory;
	}
	public String getPatCategoryCode() {
		return patCategoryCode;
	}
	public void setPatCategoryCode(String patCategoryCode) {
		this.patCategoryCode = patCategoryCode;
	}
	public String getPatStatusCode() {
		return patStatusCode;
	}
	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}
	public String getIsCatExpired() {
		return isCatExpired;
	}
	public void setIsCatExpired(String isCatExpired) {
		this.isCatExpired = isCatExpired;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPatepisodecode() {
		return patepisodecode;
	}
	public void setPatepisodecode(String patepisodecode) {
		this.patepisodecode = patepisodecode;
	}
	public String getPatvisitdate() {
		return patvisitdate;
	}
	public void setPatvisitdate(String patvisitdate) {
		this.patvisitdate = patvisitdate;
	}
	public String getDepartmentcode() {
		return departmentcode;
	}
	public void setDepartmentcode(String departmentcode) {
		this.departmentcode = departmentcode;
	}
	public String getPatdeptunitcode() {
		return patdeptunitcode;
	}
	public void setPatdeptunitcode(String patdeptunitcode) {
		this.patdeptunitcode = patdeptunitcode;
	}
	public String getPatdeptunit() {
		return patdeptunit;
	}
	public void setPatdeptunit(String patdeptunit) {
		this.patdeptunit = patdeptunit;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getPatepisodetypecode() {
		return patepisodetypecode;
	}
	public void setPatepisodetypecode(String patepisodetypecode) {
		this.patepisodetypecode = patepisodetypecode;
	}
	public String getPatvisittypecode() {
		return patvisittypecode;
	}
	public void setPatvisittypecode(String patvisittypecode) {
		this.patvisittypecode = patvisittypecode;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String getPatmlcno() {
		return patmlcno;
	}
	public void setPatmlcno(String patmlcno) {
		this.patmlcno = patmlcno;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getAdmitteddepartmentcode() {
		return admitteddepartmentcode;
	}
	public void setAdmitteddepartmentcode(String admitteddepartmentcode) {
		this.admitteddepartmentcode = admitteddepartmentcode;
	}
	public String getPatadmissionno() {
		return patadmissionno;
	}
	public void setPatadmissionno(String patadmissionno) {
		this.patadmissionno = patadmissionno;
	}
	public String getAdmitteddepartmentname() {
		return admitteddepartmentname;
	}
	public void setAdmitteddepartmentname(String admitteddepartmentname) {
		this.admitteddepartmentname = admitteddepartmentname;
	}
	public String getPatwardcode() {
		return patwardcode;
	}
	public void setPatwardcode(String patwardcode) {
		this.patwardcode = patwardcode;
	}
	public String getPatwardname() {
		return patwardname;
	}
	public void setPatwardname(String patwardname) {
		this.patwardname = patwardname;
	}
	public String getPatroomno() {
		return patroomno;
	}
	public void setPatroomno(String patroomno) {
		this.patroomno = patroomno;
	}
	public String getPatroomname() {
		return patroomname;
	}
	public void setPatroomname(String patroomname) {
		this.patroomname = patroomname;
	}
	public String getBedname() {
		return bedname;
	}
	public void setBedname(String bedname) {
		this.bedname = bedname;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public String getPatVisitNo() {
		return patVisitNo;
	}
	public void setPatVisitNo(String patVisitNo) {
		this.patVisitNo = patVisitNo;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getPatMobileNo() {
		return patMobileNo;
	}
	public void setPatMobileNo(String patMobileNo) {
		this.patMobileNo = patMobileNo;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getPatAccNo() {
		return patAccNo;
	}
	public void setPatAccNo(String patAccNo) {
		this.patAccNo = patAccNo;
	}
	public String getUomCode() {
		return uomCode;
	}
	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	public String getContainerVolume() {
		return containerVolume;
	}
	public void setContainerVolume(String containerVolume) {
		this.containerVolume = containerVolume;
	}
	public String getTempSampleNo() {
		return tempSampleNo;
	}
	public void setTempSampleNo(String tempSampleNo) {
		this.tempSampleNo = tempSampleNo;
	}
	
}
