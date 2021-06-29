package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class PatientDeathDetailFB extends CRNoFB
{
	private String hmode;
	private String isDirectCall;
	private String patMlcNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String patAdmNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String wardCode;
	private String deathDate;
	private String deathTime;
	private String deathTimeHr;
	private String deathTimeMin;
	private String bedCode;
	private String roomCode;
	private String attConsultantId;
	private String consultantRemarks;
	private String isBroughtDead;
	private String bodyHandoverTo;
	private String bodyHandoverDateTime;
	private String onSetDate;
	private String immediateCause1;
	private String immediateCause2;
	private String immediateCause3;
	private String relativeName;
	private String relativeAddress;
	private String relativeCode;
	private String antecedentCause;
	private String otherCause;
	private String isPregnant;
	private String isDelivery;
	private String injuryNatureCode;
	private String injuryTypeCode;
	private String injuryRemarks;
	private String episodeDate;
	private String deathMannerCode;
	private String verificationDate;
	private String verificationTime;
	private String handoverBy;
	private String officerName;
	private String officerDesignation;
	private String officerBadgeNo;
	private String isHandoverTo;
	private String isClient;
	private String patGender;
	private String patAge;
	private String handoverDate;
	private String handoverTimeHr;
	private String handoverTimeMin;
	private String verificationTimeHr;
	private String verificationTimeMin;
	private String sysDate="";
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String hiddenOnSetDate;
	private String hiddenRecentVisitDate;
	private String isMlc;
	private String isDeathAccidental;
	private String pregnancyRemarks;
	
	
	private String normalBodyHandover;
	private String mlcBodyHandover;
	private String isBodyHandoverToMortuary;
	
	private String isPrintCertificate;
	private String noOfCopies;
	private String certificateHandoverTo;
	private String deathCertificateId;
	private String deathCertificateDesc;
	private String isReceiptTaken;
	private String recordStatus;
	private String printFlag;
	
	private String injuryNatureName;
	private String injuryTypeName;
	private String deathMannerName;
	private String wardName;
	private String patName;
	private String deceasedAddress;
	private String patAgeUnits;
	private String husbandName;
	private String patAdmDate;
	private String daysInterval;
	private String deskType;
	private String isNewNatal;
	private String treatmentToDate;
	private String isInpatient;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.setDeathDate("");
		this.setDeathTimeHr("");
		this.setDeathTimeMin("");
		this.setDeathTime("");
		this.setDeathMannerCode("");
		this.setOnSetDate("");
		this.setImmediateCause1("");
		this.setImmediateCause2("");
		this.setImmediateCause3("");
		this.setAntecedentCause("");
		this.setOtherCause("");
		this.setIsPregnant("");
		this.setIsDelivery("");
		this.setIsHandoverTo("");
		this.setBodyHandoverTo("");
		this.setRelativeAddress("");
		this.setRelativeCode("");
		this.setRelativeName("");
		this.setOfficerBadgeNo("");
		this.setOfficerDesignation("");
		this.setOfficerName("");
		this.setNormalBodyHandover("");
		this.setMlcBodyHandover("");
		this.setIsClient("");
		this.setVerificationTimeHr("");
		this.setVerificationTimeMin("");
		this.setHandoverTimeHr("");
		this.setHandoverTimeMin("");
		this.setConsultantRemarks("");
		this.setInjuryNatureCode("");
		this.setInjuryTypeCode("");
		this.setInjuryRemarks("");
		this.setIsInpatient("");
	}

	public String getInjuryNatureName() {
		return injuryNatureName;
	}
	public void setInjuryNatureName(String injuryNatureName) {
		this.injuryNatureName = injuryNatureName;
	}
	public String getInjuryTypeName() {
		return injuryTypeName;
	}
	public void setInjuryTypeName(String injuryTypeName) {
		this.injuryTypeName = injuryTypeName;
	}
	public String getDeathMannerName() {
		return deathMannerName;
	}
	public void setDeathMannerName(String deathMannerName) {
		this.deathMannerName = deathMannerName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getDeathCertificateId() {
		return deathCertificateId;
	}
	public void setDeathCertificateId(String deathCertificateId) {
		this.deathCertificateId = deathCertificateId;
	}
	public String getDeathCertificateDesc() {
		return deathCertificateDesc;
	}
	public void setDeathCertificateDesc(String deathCertificateDesc) {
		this.deathCertificateDesc = deathCertificateDesc;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getDeathTime() {
		return deathTime;
	}
	public void setDeathTime(String deathTime) {
		this.deathTime = deathTime;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getAttConsultantId() {
		return attConsultantId;
	}
	public void setAttConsultantId(String attConsultantId) {
		this.attConsultantId = attConsultantId;
	}
	public String getConsultantRemarks() {
		return consultantRemarks;
	}
	public void setConsultantRemarks(String consultantRemarks) {
		this.consultantRemarks = consultantRemarks;
	}
	public String getIsBroughtDead() {
		return isBroughtDead;
	}
	public void setIsBroughtDead(String isBroughtDead) {
		this.isBroughtDead = isBroughtDead;
	}
	public String getBodyHandoverTo() {
		return bodyHandoverTo;
	}
	public void setBodyHandoverTo(String bodyHandoverTo) {
		this.bodyHandoverTo = bodyHandoverTo;
	}
	public String getBodyHandoverDateTime() {
		return bodyHandoverDateTime;
	}
	public void setBodyHandoverDateTime(String bodyHandoverDateTime) {
		this.bodyHandoverDateTime = bodyHandoverDateTime;
	}
	public String getOnSetDate() {
		return onSetDate;
	}
	public void setOnSetDate(String onSetDate) {
		this.onSetDate = onSetDate;
	}
	public String getImmediateCause1() {
		return immediateCause1;
	}
	public void setImmediateCause1(String immediateCause1) {
		this.immediateCause1 = immediateCause1;
	}
	public String getImmediateCause2() {
		return immediateCause2;
	}
	public void setImmediateCause2(String immediateCause2) {
		this.immediateCause2 = immediateCause2;
	}
	public String getImmediateCause3() {
		return immediateCause3;
	}
	public void setImmediateCause3(String immediateCause3) {
		this.immediateCause3 = immediateCause3;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getAntecedentCause() {
		return antecedentCause;
	}
	public void setAntecedentCause(String antecedentCause) {
		this.antecedentCause = antecedentCause;
	}
	public String getOtherCause() {
		return otherCause;
	}
	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}
	public String getIsPregnant() {
		return isPregnant;
	}
	public void setIsPregnant(String isPregnant) {
		this.isPregnant = isPregnant;
	}
	public String getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}
	public String getInjuryNatureCode() {
		return injuryNatureCode;
	}
	public void setInjuryNatureCode(String injuryNatureCode) {
		this.injuryNatureCode = injuryNatureCode;
	}
	public String getInjuryTypeCode() {
		return injuryTypeCode;
	}
	public void setInjuryTypeCode(String injuryTypeCode) {
		this.injuryTypeCode = injuryTypeCode;
	}
	public String getInjuryRemarks() {
		return injuryRemarks;
	}
	public void setInjuryRemarks(String injuryRemarks) {
		this.injuryRemarks = injuryRemarks;
	}
	public String getEpisodeDate() {
		return episodeDate;
	}
	public void setEpisodeDate(String episodeDate) {
		this.episodeDate = episodeDate;
	}
	public String getDeathMannerCode() {
		return deathMannerCode;
	}
	public void setDeathMannerCode(String deathMannerCode) {
		this.deathMannerCode = deathMannerCode;
	}
	public String getVerificationDate() {
		return verificationDate;
	}
	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}
	public String getVerificationTime() {
		return verificationTime;
	}
	public void setVerificationTime(String verificationTime) {
		this.verificationTime = verificationTime;
	}
	public String getHandoverBy() {
		return handoverBy;
	}
	public void setHandoverBy(String handoverBy) {
		this.handoverBy = handoverBy;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfficerDesignation() {
		return officerDesignation;
	}
	public void setOfficerDesignation(String officerDesignation) {
		this.officerDesignation = officerDesignation;
	}
	public String getOfficerBadgeNo() {
		return officerBadgeNo;
	}
	public void setOfficerBadgeNo(String officerBadgeNo) {
		this.officerBadgeNo = officerBadgeNo;
	}
	public String getNormalBodyHandover() {
		return normalBodyHandover;
	}
	public void setNormalBodyHandover(String normalBodyHandover) {
		this.normalBodyHandover = normalBodyHandover;
	}
	public String getMlcBodyHandover() {
		return mlcBodyHandover;
	}
	public void setMlcBodyHandover(String mlcBodyHandover) {
		this.mlcBodyHandover = mlcBodyHandover;
	}
	public String getDeathTimeHr() {
		return deathTimeHr;
	}
	public void setDeathTimeHr(String deathTimeHr) {
		this.deathTimeHr = deathTimeHr;
	}
	public String getDeathTimeMin() {
		return deathTimeMin;
	}
	public void setDeathTimeMin(String deathTimeMin) {
		this.deathTimeMin = deathTimeMin;
	}
	public String getIsHandoverTo() {
		return isHandoverTo;
	}
	public void setIsHandoverTo(String isHandoverTo) {
		this.isHandoverTo = isHandoverTo;
	}
	public String getIsClient() {
		return isClient;
	}
	public void setIsClient(String isClient) {
		this.isClient = isClient;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	
	
	public String getHandoverDate() {
		return handoverDate;
	}
	public void setHandoverDate(String handoverDate) {
		this.handoverDate = handoverDate;
	}
	public String getHandoverTimeHr() {
		return handoverTimeHr;
	}
	public void setHandoverTimeHr(String handoverTimeHr) {
		this.handoverTimeHr = handoverTimeHr;
	}
	public String getHandoverTimeMin() {
		return handoverTimeMin;
	}
	public void setHandoverTimeMin(String handoverTimeMin) {
		this.handoverTimeMin = handoverTimeMin;
	}
	public String getVerificationTimeHr() {
		return verificationTimeHr;
	}
	public void setVerificationTimeHr(String verificationTimeHr) {
		this.verificationTimeHr = verificationTimeHr;
	}
	public String getVerificationTimeMin() {
		return verificationTimeMin;
	}
	public void setVerificationTimeMin(String verificationTimeMin) {
		this.verificationTimeMin = verificationTimeMin;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getHiddenOnSetDate() {
		return hiddenOnSetDate;
	}
	public void setHiddenOnSetDate(String hiddenOnSetDate) {
		this.hiddenOnSetDate = hiddenOnSetDate;
	}
	public String getHiddenRecentVisitDate() {
		return hiddenRecentVisitDate;
	}
	public void setHiddenRecentVisitDate(String hiddenRecentVisitDate) {
		this.hiddenRecentVisitDate = hiddenRecentVisitDate;
	}
	public String getIsBodyHandoverToMortuary() {
		return isBodyHandoverToMortuary;
	}
	public void setIsBodyHandoverToMortuary(String isBodyHandoverToMortuary) {
		this.isBodyHandoverToMortuary = isBodyHandoverToMortuary;
	}
	public String getIsMlc() {
		return isMlc;
	}
	public void setIsMlc(String isMlc) {
		this.isMlc = isMlc;
	}
	public String getIsDeathAccidental() {
		return isDeathAccidental;
	}
	public void setIsDeathAccidental(String isDeathAccidental) {
		this.isDeathAccidental = isDeathAccidental;
	}
	public String getPregnancyRemarks() {
		return pregnancyRemarks;
	}
	public void setPregnancyRemarks(String pregnancyRemarks) {
		this.pregnancyRemarks = pregnancyRemarks;
	}
	public String getIsPrintCertificate() {
		return isPrintCertificate;
	}
	public void setIsPrintCertificate(String isPrintCertificate) {
		this.isPrintCertificate = isPrintCertificate;
	}
	public String getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(String noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	public String getCertificateHandoverTo() {
		return certificateHandoverTo;
	}
	public void setCertificateHandoverTo(String certificateHandoverTo) {
		this.certificateHandoverTo = certificateHandoverTo;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getIsReceiptTaken() {
		return isReceiptTaken;
	}
	public void setIsReceiptTaken(String isReceiptTaken) {
		this.isReceiptTaken = isReceiptTaken;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	public String getDeceasedAddress() {
		return deceasedAddress;
	}
	public void setDeceasedAddress(String deceasedAddress) {
		this.deceasedAddress = deceasedAddress;
	}
	public String getPatAgeUnits() {
		return patAgeUnits;
	}
	public void setPatAgeUnits(String patAgeUnits) {
		this.patAgeUnits = patAgeUnits;
	}
	public String getHusbandName() {
		return husbandName;
	}
	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}
	public String getPatAdmDate() {
		return patAdmDate;
	}
	public void setPatAdmDate(String patAdmDate) {
		this.patAdmDate = patAdmDate;
	}
	public String getDaysInterval() {
		return daysInterval;
	}
	public void setDaysInterval(String daysInterval) {
		this.daysInterval = daysInterval;
	}
	public String getDeskType() {
		return deskType;
	}
	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}
	public String getIsNewNatal() {
		return isNewNatal;
	}
	public void setIsNewNatal(String isNewNatal) {
		this.isNewNatal = isNewNatal;
	}
	public String getTreatmentToDate() {
		return treatmentToDate;
	}
	public void setTreatmentToDate(String treatmentToDate) {
		this.treatmentToDate = treatmentToDate;
	}
	public String getIsInpatient() {
		return isInpatient;
	}
	public void setIsInpatient(String isInpatient) {
		this.isInpatient = isInpatient;
	}
	
	
	
}
