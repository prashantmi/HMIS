package emr.dataentry.spp.presentation.fb;

import org.apache.struts.action.ActionForm;

public class ClinicalSummaryEntryFB extends ActionForm {
	private String mode;
	private String patCrNo;
	private String patFirstName;
	private String patMiddleName;
	private String patLastName;
	private String patGender;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String queNo;
	private String patGenderAge;
	private String departmentUnitCode;
	private String hmode;
	private String orderBy;
	private String episodeNextVisitDate;
	private String episodeIsOpen;
	private String patChoice;
	private String selectedPatCrNo;
	private String roomCode;
	private String showRommList;
	private String visitType;
	private String hospitalCode;
	private String isCasualty;
	private String remarks[];
	
	private String departmentCode;
	private String episodeCode;
	
	private String snomedCIdRemarks[]; // snomed integration
	private String snomedPTRemarks[];
	private String deptUnit;
	private String visitDate;
	private String visitReason;
	private String snomdPTRemarks;
	private String snomdCIdRemarks;

	private String diagnosisCount="0";
	private String advisedBy;
	private String episodeVisitNo;
	private String clinicalDocumentMode;
	private String isIpdFlag;
	private String goFlag;
	private String afterGo;
	private String errorMessage;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getQueNo() {
		return queNo;
	}
	public void setQueNo(String queNo) {
		this.queNo = queNo;
	}
	public String getPatGenderAge() {
		return patGenderAge;
	}
	public void setPatGenderAge(String patGenderAge) {
		this.patGenderAge = patGenderAge;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getEpisodeNextVisitDate() {
		return episodeNextVisitDate;
	}
	public void setEpisodeNextVisitDate(String episodeNextVisitDate) {
		this.episodeNextVisitDate = episodeNextVisitDate;
	}
	public String getEpisodeIsOpen() {
		return episodeIsOpen;
	}
	public void setEpisodeIsOpen(String episodeIsOpen) {
		this.episodeIsOpen = episodeIsOpen;
	}
	public String getPatChoice() {
		return patChoice;
	}
	public void setPatChoice(String patChoice) {
		this.patChoice = patChoice;
	}
	public String getSelectedPatCrNo() {
		return selectedPatCrNo;
	}
	public void setSelectedPatCrNo(String selectedPatCrNo) {
		this.selectedPatCrNo = selectedPatCrNo;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getShowRommList() {
		return showRommList;
	}
	public void setShowRommList(String showRommList) {
		this.showRommList = showRommList;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsCasualty() {
		return isCasualty;
	}
	public void setIsCasualty(String isCasualty) {
		this.isCasualty = isCasualty;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String[] getSnomedCIdRemarks() {
		return snomedCIdRemarks;
	}
	public void setSnomedCIdRemarks(String[] snomedCIdRemarks) {
		this.snomedCIdRemarks = snomedCIdRemarks;
	}
	public String[] getSnomedPTRemarks() {
		return snomedPTRemarks;
	}
	public void setSnomedPTRemarks(String[] snomedPTRemarks) {
		this.snomedPTRemarks = snomedPTRemarks;
	}
	public String getDeptUnit() {
		return deptUnit;
	}
	public void setDeptUnit(String deptUnit) {
		this.deptUnit = deptUnit;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public String getSnomdPTRemarks() {
		return snomdPTRemarks;
	}
	public void setSnomdPTRemarks(String snomdPTRemarks) {
		this.snomdPTRemarks = snomdPTRemarks;
	}
	public String getSnomdCIdRemarks() {
		return snomdCIdRemarks;
	}
	public void setSnomdCIdRemarks(String snomdCIdRemarks) {
		this.snomdCIdRemarks = snomdCIdRemarks;
	}
	public String getDiagnosisCount() {
		return diagnosisCount;
	}
	public void setDiagnosisCount(String diagnosisCount) {
		this.diagnosisCount = diagnosisCount;
	}
	public String getAdvisedBy() {
		return advisedBy;
	}
	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getClinicalDocumentMode() {
		return clinicalDocumentMode;
	}
	public void setClinicalDocumentMode(String clinicalDocumentMode) {
		this.clinicalDocumentMode = clinicalDocumentMode;
	}
	public String getIsIpdFlag() {
		return isIpdFlag;
	}
	public void setIsIpdFlag(String isIpdFlag) {
		this.isIpdFlag = isIpdFlag;
	}
	public String getGoFlag() {
		return goFlag;
	}
	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}
	public String getAfterGo() {
		return afterGo;
	}
	public void setAfterGo(String afterGo) {
		this.afterGo = afterGo;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
