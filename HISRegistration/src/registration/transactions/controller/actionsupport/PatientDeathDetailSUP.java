package registration.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class PatientDeathDetailSUP extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware, ServletContextAware
{
	protected HttpServletRequest objRequest;
	protected HttpServletResponse objResponse;
	protected ServletContext objContext;
	protected Map mapSesion;
	
	protected String afterGo;
	protected String strNormalMsg;
	protected String strWarningMsg;
	protected String strErrorMessage;
	
	//////////////////////////////////////
	
	protected String patCrNo;
	protected String patMlcNo;
	protected String episodeCode;
	protected String episodeVisitNo;
	protected String patAdmNo;
	protected String departmentCode;
	protected String departmentUnitCode;
	protected String wardCode;
	protected String deathDate;
	protected String deathTime;
	protected String deathTimeHr;
	protected String deathTimeMin;
	protected String bedCode;
	protected String roomCode;
	protected String attConsultantId;
	protected String consultantRemarks;
	protected String isBroughtDead;
	protected String bodyHandoverTo;
	protected String bodyHandoverDateTime;
	protected String onSetDate;
	protected String immediateCause1;
	protected String immediateCause2;
	protected String immediateCause3;
	protected String relativeName;
	protected String relativeAddress;
	protected String relativeCode;
	protected String antecedentCause;
	protected String otherCause;
	protected String isPregnant;
	protected String isDelivery;
	protected String injuryNatureCode;
	protected String injuryTypeCode;
	protected String injuryRemarks;
	protected String episodeDate;
	protected String deathMannerCode;
	protected String verificationDate;
	protected String verificationTime;
	protected String handoverBy;
	protected String officerName;
	protected String officerDesignation;
	protected String officerBadgeNo;
	protected String isHandoverTo;
	protected String isClient;
	protected String patGender;
	protected String patAge;
	protected String handoverDate;
	protected String handoverTimeHr;
	protected String handoverTimeMin;
	protected String verificationTimeHr;
	protected String verificationTimeMin;
	protected String sysDate="";
	protected String hiddenTimeHr;
	protected String hiddenTimeMin;
	protected String hiddenOnSetDate;
	protected String hiddenRecentVisitDate;
	protected String isMlc;
	protected String isDeathAccidental;
	protected String pregnancyRemarks;
	
	
	protected String normalBodyHandover;
	protected String mlcBodyHandover;
	protected String isBodyHandoverToMortuary;
	
	protected String isPrintCertificate;
	protected String noOfCopies;
	protected String certificateHandoverTo;
	protected String deathCertificateId;
	protected String deathCertificateDesc;
	protected String isReceiptTaken;
	protected String recordStatus;
	protected String printFlag;
	
	protected String injuryNatureName;
	protected String mlcTypeName;
	protected String deathMannerName;
	protected String wardName;
	protected String patName;
	protected String deceasedAddress;
	protected String patAgeUnits;
	protected String husbandName;
	protected String patAdmDate;
	protected String daysInterval;
	protected String isNewNatal;
	protected String treatmentToDate;
	protected String isInpatient;
	
	protected String isDesk;
	protected String otherHospitalFlag;
	
	protected String sDate;
	protected String sHr;
	protected String sMin;
	protected String snomdPTimmediateCause1;
	protected String snomdPTimmediateCause2;
	protected String snomdPTimmediateCause3;
	protected String snomdPTOtherCause;
	protected String snomdCIdimmediateCause1;
	protected String snomdCIdimmediateCause2;
	protected String snomdCIdimmediateCause3;
	protected String snomdCIdOtherCause;
	protected String snomdCIdDeathManner;
	protected String isSnomedServiceOn;
	
	public HttpServletRequest getObjRequest() {
		return objRequest;
	}
	public void setObjRequest(HttpServletRequest objRequest) {
		this.objRequest = objRequest;
	}
	public HttpServletResponse getObjResponse() {
		return objResponse;
	}
	public void setObjResponse(HttpServletResponse objResponse) {
		this.objResponse = objResponse;
	}
	public ServletContext getObjContext() {
		return objContext;
	}
	public void setObjContext(ServletContext objContext) {
		this.objContext = objContext;
	}
	public Map getMapSesion() {
		return mapSesion;
	}
	public void setMapSesion(Map mapSesion) {
		this.mapSesion = mapSesion;
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override 
	public void setServletRequest(HttpServletRequest request)
	{
		this.objRequest = request;
	}

	@Override 
	public void setServletResponse(HttpServletResponse response)
	{
		this.objResponse = response;
	}
	@Override 
	public void setSession(Map sessionMap)
	{
		this.mapSesion = sessionMap;
	}
	
	public String getAfterGo() {
		return afterGo;
	}
	public void setAfterGo(String afterGo) {
		this.afterGo = afterGo;
	}
	
	
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrErrorMessage() {
		return strErrorMessage;
	}
	public void setStrErrorMessage(String strErrorMessage) {
		this.strErrorMessage = strErrorMessage;
	}
	
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getIsBodyHandoverToMortuary() {
		return isBodyHandoverToMortuary;
	}
	public void setIsBodyHandoverToMortuary(String isBodyHandoverToMortuary) {
		this.isBodyHandoverToMortuary = isBodyHandoverToMortuary;
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
	public String getInjuryNatureName() {
		return injuryNatureName;
	}
	public void setInjuryNatureName(String injuryNatureName) {
		this.injuryNatureName = injuryNatureName;
	}
	public String getMlcTypeName() {
		return mlcTypeName;
	}
	public void setMlcTypeName(String mlcTypeName) {
		this.mlcTypeName = mlcTypeName;
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
	
	public void reset()
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
	 /* ## 	Modification Log							
	 	##		Modify Date				:12thDec'14 
	 	##		Reason	(CR/PRS)		:Bug fix 7727
	 	##		Modify By				:Sheeldarshi 
	*/
		this.setAttConsultantId("");
		//End:Sheeldarshi
	}
	
	public void clearMessageField(){
		this.setStrNormalMsg("");
		this.setStrErrorMessage("");
		this.setStrWarningMsg("");
		//this.setPrint("");
		//this.setSaveSuccessful("");
	}
	public String getIsDesk() {
		return isDesk;
	}
	public void setIsDesk(String isDesk) {
		this.isDesk = isDesk;
	}
	public String getOtherHospitalFlag() {
		return otherHospitalFlag;
	}
	public void setOtherHospitalFlag(String otherHospitalFlag) {
		this.otherHospitalFlag = otherHospitalFlag;
	}
	public String getSnomdPTimmediateCause1() {
		return snomdPTimmediateCause1;
	}
	public void setSnomdPTimmediateCause1(String snomdPTimmediateCause1) {
		this.snomdPTimmediateCause1 = snomdPTimmediateCause1;
	}
	public String getSnomdPTimmediateCause2() {
		return snomdPTimmediateCause2;
	}
	public void setSnomdPTimmediateCause2(String snomdPTimmediateCause2) {
		this.snomdPTimmediateCause2 = snomdPTimmediateCause2;
	}
	public String getSnomdPTimmediateCause3() {
		return snomdPTimmediateCause3;
	}
	public void setSnomdPTimmediateCause3(String snomdPTimmediateCause3) {
		this.snomdPTimmediateCause3 = snomdPTimmediateCause3;
	}
	public String getSnomdPTOtherCause() {
		return snomdPTOtherCause;
	}
	public void setSnomdPTOtherCause(String snomdPTOtherCause) {
		this.snomdPTOtherCause = snomdPTOtherCause;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String getsHr() {
		return sHr;
	}
	public void setsHr(String sHr) {
		this.sHr = sHr;
	}
	public String getsMin() {
		return sMin;
	}
	public void setsMin(String sMin) {
		this.sMin = sMin;
	}
	public String getSnomdCIdimmediateCause1() {
		return snomdCIdimmediateCause1;
	}
	public void setSnomdCIdimmediateCause1(String snomdCIdimmediateCause1) {
		this.snomdCIdimmediateCause1 = snomdCIdimmediateCause1;
	}
	public String getSnomdCIdimmediateCause2() {
		return snomdCIdimmediateCause2;
	}
	public void setSnomdCIdimmediateCause2(String snomdCIdimmediateCause2) {
		this.snomdCIdimmediateCause2 = snomdCIdimmediateCause2;
	}
	public String getSnomdCIdimmediateCause3() {
		return snomdCIdimmediateCause3;
	}
	public void setSnomdCIdimmediateCause3(String snomdCIdimmediateCause3) {
		this.snomdCIdimmediateCause3 = snomdCIdimmediateCause3;
	}
	public String getSnomdCIdOtherCause() {
		return snomdCIdOtherCause;
	}
	public void setSnomdCIdOtherCause(String snomdCIdOtherCause) {
		this.snomdCIdOtherCause = snomdCIdOtherCause;
	}
	public String getSnomdCIdDeathManner() {
		return snomdCIdDeathManner;
	}
	public void setSnomdCIdDeathManner(String snomdCIdDeathManner) {
		this.snomdCIdDeathManner = snomdCIdDeathManner;
	}
	public String getIsSnomedServiceOn() {
		return isSnomedServiceOn;
	}
	public void setIsSnomedServiceOn(String isSnomedServiceOn) {
		this.isSnomedServiceOn = isSnomedServiceOn;
	}
	
}
