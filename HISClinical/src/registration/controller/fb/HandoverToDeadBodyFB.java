package registration.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;

public class HandoverToDeadBodyFB extends CRNoFB{

	private String hmode;
	private String patMlcNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String bodyHandoverTo;
	private String bodyHandoverDateTime;
	private String relativeName;
	private String relativeAddress;
	private String relativeCode;
	private String handoverBy;
	private String officerName;
	private String officerDesignation;
	private String officerBadgeNo;
	private String isHandoverTo;
	private String handoverDate;
	private String handoverTimeHr;
	private String handoverTimeMin;
	private String isPrintCertificate;
	private String noOfCopies;
	private String certificateHandoverTo;
	private String deathCertificateId;
	private String deathCertificateDesc;
	private String isReceiptTaken;
	private String recordStatus;
	private String printFlag;
	private String isMlc;
	private String sysDate="";
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String verificationDate;
	private String departmentUnitCode;
	private String deceasedAddress;
	private String patAgeUnits;
	private String patAdmDate;
	
	
	private String wardName;
	private String deathDate;
	private String patAdmNo;
	private String patName;
	private String husbandName;
	private String patGender;
	private String patAge;
	private String daysInterval;
	private String immediateCause1;
	private String immediateCause2;
	private String otherCause;
	private String deathMannerName;
	private String injuryTypeName;
	private String injuryNatureName;
	private String injuryRemarks;
	private String injuryNatureCode;
	private String isPregnant;
	private String isDelivery;
	
	private String flagForPrint;
	private String remarks;
	private String chkPatCrNo;
	private String relationName;
	private int currentPage;
	private String startIndex;
	private String endIndex;
	private String deskType;
	private String isInpatient;
	private String onSetDate;
	private String deathTime;

	
	public String getDeathTime() {
		return deathTime;
	}


	public void setDeathTime(String deathTime) {
		this.deathTime = deathTime;
	}


	public String getOnSetDate() {
		return onSetDate;
	}


	public void setOnSetDate(String onSetDate) {
		this.onSetDate = onSetDate;
	}


	public String getDeskType() {
		return deskType;
	}


	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}


	public HandoverToDeadBodyFB()
	{
		this.currentPage=1;
	}
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.setIsHandoverTo("");
		this.setBodyHandoverTo("");
		this.setRelativeAddress("");
		this.setRelativeCode("");
		this.setRelativeName("");
		this.setOfficerBadgeNo("");
		this.setOfficerDesignation("");
		this.setOfficerName("");
		this.setHandoverTimeHr("");
		this.setHandoverTimeMin("");
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getIsHandoverTo() {
		return isHandoverTo;
	}
	public void setIsHandoverTo(String isHandoverTo) {
		this.isHandoverTo = isHandoverTo;
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
	public String getIsReceiptTaken() {
		return isReceiptTaken;
	}
	public void setIsReceiptTaken(String isReceiptTaken) {
		this.isReceiptTaken = isReceiptTaken;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getIsMlc() {
		return isMlc;
	}

	public void setIsMlc(String isMlc) {
		this.isMlc = isMlc;
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

	public String getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
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

	public String getPatAdmDate() {
		return patAdmDate;
	}

	public void setPatAdmDate(String patAdmDate) {
		this.patAdmDate = patAdmDate;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getHusbandName() {
		return husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
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

	public String getDaysInterval() {
		return daysInterval;
	}

	public void setDaysInterval(String daysInterval) {
		this.daysInterval = daysInterval;
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

	public String getOtherCause() {
		return otherCause;
	}

	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}

	public String getDeathMannerName() {
		return deathMannerName;
	}

	public void setDeathMannerName(String deathMannerName) {
		this.deathMannerName = deathMannerName;
	}

	public String getInjuryTypeName() {
		return injuryTypeName;
	}

	public void setInjuryTypeName(String injuryTypeName) {
		this.injuryTypeName = injuryTypeName;
	}

	public String getInjuryNatureName() {
		return injuryNatureName;
	}

	public void setInjuryNatureName(String injuryNatureName) {
		this.injuryNatureName = injuryNatureName;
	}

	public String getInjuryRemarks() {
		return injuryRemarks;
	}

	public void setInjuryRemarks(String injuryRemarks) {
		this.injuryRemarks = injuryRemarks;
	}

	public String getInjuryNatureCode() {
		return injuryNatureCode;
	}

	public void setInjuryNatureCode(String injuryNatureCode) {
		this.injuryNatureCode = injuryNatureCode;
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

	public String getFlagForPrint() {
		return flagForPrint;
	}

	public void setFlagForPrint(String flagForPrint) {
		this.flagForPrint = flagForPrint;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getChkPatCrNo() {
		return chkPatCrNo;
	}

	public void setChkPatCrNo(String chkPatCrNo) {
		this.chkPatCrNo = chkPatCrNo;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}


	public String getEndIndex() {
		return endIndex;
	}


	public void setEndIndex(String endIndex) {
		this.endIndex = endIndex;
	}


	public String getIsInpatient() {
		return isInpatient;
	}


	public void setIsInpatient(String isInpatient) {
		this.isInpatient = isInpatient;
	}

	
	
	
	
}
