/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.presentation.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UniPagePrescriptionFB extends ActionForm
{
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
	
	//Added by Dheeraj for Form data serialization
	private String pdfFileIn;
	private String pdfFileOut;
	private String htmlPreview;
	private String fileType;
	private String prescribedBy;
	private String previousPrescription;
	private String visitNo;
	
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
	private String documentType; //Added by Vasu on 30.Oct.2018
	private byte[] pdfFileData;//Added by Vasu on 05.Nov.2018
	private String profileId;
	private String wardCode;
	private String chkSelectedSections;
	private String isToggable;

	public String getSelectedPatCrNo()
	{
		return selectedPatCrNo;
	}

	public void setSelectedPatCrNo(String selectedPatCrNo)
	{
		this.selectedPatCrNo = selectedPatCrNo;
	}

	public String getEpisodeIsOpen()
	{
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen)
	{
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getEpisodeNextVisitDate()
	{
		return episodeNextVisitDate;
	}

	public void setEpisodeNextVisitDate(String episodeNextVisitDate)
	{
		this.episodeNextVisitDate = episodeNextVisitDate;
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String getDepartmentUnitCode()
	{
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode)
	{
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getPatGenderAge()
	{
		return patGenderAge;
	}

	public void setPatGenderAge(String patGenderAge)
	{
		this.patGenderAge = patGenderAge;
	}

	public String getMode()
	{
		return mode;
	}

	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}

	public String getPatFirstName()
	{
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName)
	{
		this.patFirstName = patFirstName;
	}

	public String getPatGender()
	{
		return patGender;
	}

	public void setPatGender(String patGender)
	{
		this.patGender = patGender;
	}

	public String getPatLastName()
	{
		return patLastName;
	}

	public void setPatLastName(String patLastName)
	{
		this.patLastName = patLastName;
	}

	public String getPatMiddleName()
	{
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName)
	{
		this.patMiddleName = patMiddleName;
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

	public String getQueNo()
	{
		return queNo;
	}

	public void setQueNo(String queNo)
	{
		this.queNo = queNo;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1)
	{
		this.setPatCrNo("");
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setPatGender("");
		this.setPatPrimaryCat("");
		this.setPatPrimaryCatCode("");
		this.setDepartmentUnitCode("");
		this.setOrderBy("");
		this.setMode("");
		this.setHmode("");
		this.setPatChoice("");
		this.setSnomedPTRemarks(null);
		this.setSnomedCIdRemarks(null);
		this.setRemarks(null);
		super.reset(arg0, arg1);
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getPatChoice()
	{
		return patChoice;
	}

	public void setPatChoice(String patChoice)
	{
		this.patChoice = patChoice;
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

	public String getGoFlag() {
		return goFlag;
	}

	public void setGoFlag(String goFlag) {
		this.goFlag = goFlag;
	}
	
	//Added by Dheeraj

	public String getPdfFileIn() {
		return pdfFileIn;
	}

	public void setPdfFileIn(String pdfFileIn) {
		this.pdfFileIn = pdfFileIn;
	}

	public String getPdfFileOut() {
		return pdfFileOut;
	}

	public void setPdfFileOut(String pdfFileOut) {
		this.pdfFileOut = pdfFileOut;
	}

	public String getHtmlPreview() {
		return htmlPreview;
	}

	public void setHtmlPreview(String htmlPreview) {
		this.htmlPreview = htmlPreview;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getPreviousPrescription() {
		return previousPrescription;
	}

	public void setPreviousPrescription(String previousPrescription) {
		this.previousPrescription = previousPrescription;
	}

	public String getPrescribedBy() {
		return prescribedBy;
	}

	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
	}

	public String getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public byte[] getPdfFileData() {
		return pdfFileData;
	}

	public void setPdfFileData(byte[] pdfFileData) {
		this.pdfFileData = pdfFileData;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getChkSelectedSections() {
		return chkSelectedSections;
	}

	public void setChkSelectedSections(String chkSelectedSections) {
		this.chkSelectedSections = chkSelectedSections;
	}

	public String getIsToggable() {
		return isToggable;
	}

	public void setIsToggable(String isToggable) {
		this.isToggable = isToggable;
	}	
	
	
}
