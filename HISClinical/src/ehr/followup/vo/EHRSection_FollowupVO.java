package ehr.followup.vo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




import hisglobal.utility.HelperMethods;
import hisglobal.vo.ValueObject;

public class EHRSection_FollowupVO extends ValueObject {

	private String selectedSectionData;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String episodeVisitSlNo;
	private String episodeActionCode;
	private String episodeAction;
	private String visitNotes;
	private String episodeNextVisitDate;
	private String episodeNextVisitDeptCode;
	private String episodeNextVisitDept;
	private String episodeIsOpen;
	private String episodeSummary;
	private String empNo;
	private String empName;
	private String seatId;
	private String entryDate;
	private String isValid;
	private String episodeNextVisitDeptUnitCode;
	private String episodeNextVisitDeptUnit;
	private String hospitalCode;
	private String systemIPAddress;
	private String nextVisitCriteria;
	private String nextVisitDuration;
	private String episodeKeywords;
	private String episodeKeywordID;
	private String visitReason;

	private String patAptNo;	  
	private String patAptSlot;
	private String patAptQueueNO;
	private String isAppointment;
	
	
	
	private String isUnitAppointmentBased;
	private String patNextAptNo;	
	private String patNextAptSlot;
	private String patNextAptQueueNo;
	
	
	private String snomdPTVisitReason;
	private String snomdCIdVisitReason;
	
	private String snomdPTVisitNotes;
	private String snomdCIdVisitNotes;
	
	private String snomdPTKeywords;
	private String snomdCIdKeywords;
	private String keywords;
	private String snomdPTEpisodeSummary;
	private String snomdCIdEpisodeSummary;
	
	
	private String diagonisticTypeCode;
	private String diagnosticDesc;
	private String complainDtl;
	private String remarks;
	private String episodeActionDate;
	private String episodeActionTime;
	private String departmentCode;
	private String wardCode;
	private String patRefGnctdHospitalCode;
	private String patRefHospitalName;
	
	private String episodeCloseDate="";
	
	
	private String episodeType;
	private String episodeTypeCode;
	private String admissionNo;
	private String episodeDate;
	
	private String episodeCloseType;
	
	
	private String nextVisitDateFlag;
	private String patStatus;
	private String dischargeRemarksDisabled;
    private String profileStatus;
    private String sysDate="";
	
	private String patAdmNo;
	private String dischargeRemarks;
	
	private String revokeRemarks;
	
	private String requestById;
	private String requestByName;
	private String requestByEmpDept;
	
	//Added by Vasu on 13.March.2019
	private String dischargePreparedById;
	private String dischargePreparedByDept;
	private String dischargePreparedByName;
	
	private String dischargeApprovedById;
	private String dischargeApprovedByDept;
	private String dischargeApprovedByName;

private String isClosedPreviously;
	
	private String isAutoClose;	// New Field
	
	private String department;

	private String patIsOld;

	private String departmentUnit;
	private String departmentUnitCode;
	private String room;
	private String roomCode;
	private String queNo;
	private String ward;

	private String episodeVisitType;
	private String episodeVisitTypeCode;

	private String episodeTime;

	private String episodeNextVisitType;
	private String episodeReferAccept;
	private String episodeReferAcceptDate;
	private String episodeReferCode;
	private String episodeStartDate;
	private String deptUnitIsOnDuty;
	private String visitedToday;
	private String deptUnitIsClosed;
	private String patAmountCollected;
	private String episodeName;
	private String docSeatID;

	private String mlcNo;
	private String complainDetail;

	private String gdt_entry_date;

	private String isConfirmed;


	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String isReferred;

	private String fileNo;
	private String fileNoView;
	private String lastVisitDate;
	private String renewalRequired;
	private String isGeneral;
	
	private String expiryDate;
	private String episodeExpiryDate;
	private String specifyExpiry;
	private String renewalType;
	private String validUpto;
	private String isCardPrint;
	private String diagnosisCodeType;

	private String unitWorkingDays;
	private String disclaimer;
	private String ifExists;
	private String departmentUnitType;
	private String secCatExpDate;
	private String disasterId; 


	private String unitConsultant;

	private String oldCrNo;

	private String oldRegDate;
    private String isOtherUnitWorking;
    private String isEpisodeRenewed;
    private String oldExpiryDate;
    private String filePrintSetting;
	private String cardPrintSetting;
	private String onRequestVisit;
	private String referFromDepartment;
	private String renewalAmount;
	private String strMctsNo;
	private String hospitalName;
	
	private String patVisitReason;
	private String treatmentValidUpTo; 
	
	private String tariffId;
	private String billNo;
	private String patAgeWithUnit;
	private String isForceful;
	
	private String opdRenewalRequired;
	private String splRenewalRequired;
	private String emgRenewalRequired;
	private String isCardPrintedToday;
	
	private String strRegFlag;

	private String isReprint;
	
	private String mlcFlag;
	private String isMlcWithin24hrs;
	
	private String visitDate;
	
	private String creditBillFlag;
	private String creditLetterRefNo;
	private String creditLetterDate;

	private String patAptId;
	private String patAptStatus;
	
	private String patActualAmount;
	private String receiptNo;
	private String serialNo;
	


	private String slNo;
	private String episodeKeyword;
	
	private String lastModifyDate;
	private String lastModifiedSeatID;

	private String isCloseVo;
	private String username;
	private String isSetFOLLOWUP;
	
	//Added by Vasu on 18.DEC.2018 for Discharge FollowUP
	
	private String nextVisitDate;
	private String requestType;
	private String dischargeStatus;
	private String nextVisitDays;
	private String isDead;
	private String adviceBy;
	private String dischargeStatusName;
	
	private String dischargePreparedDate;
	private String dischargeDate;
	
	public JsonObject getJSONObj()
	{
		JSONPart jsonPart = new JSONPart();
		HelperMethods.populate(jsonPart, this);
		
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String temp = gson.toJson(jsonPart);
		//System.out.println(temp);
		JsonElement jsonElem = parser.parse(temp);
		JsonObject jsonObj = (JsonObject)jsonElem;
		return jsonObj;
	}
	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getEpisodeKeyword()
	{
		return episodeKeyword;
	}

	public void setEpisodeKeyword(String episodeKeyword)
	{
		this.episodeKeyword = episodeKeyword;
	}

	
	public String getLastModifyDate()
	{
		return lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate)
	{
		this.lastModifyDate = lastModifyDate;
	}

	public String getLastModifiedSeatID()
	{
		return lastModifiedSeatID;
	}

	public void setLastModifiedSeatID(String lastModifiedSeatID)
	{
		this.lastModifiedSeatID = lastModifiedSeatID;
	}
	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	

	public String getEpisodeCloseType()
	{
		return episodeCloseType;
	}

	public void setEpisodeCloseType(String episodeCloseType)
	{
		this.episodeCloseType = episodeCloseType;
	}



	public String getEpisodeType()
	{
		return episodeType;
	}

	public void setEpisodeType(String episodeType)
	{
		this.episodeType = episodeType;
	}

	public String getEpisodeTypeCode()
	{
		return episodeTypeCode;
	}

	public void setEpisodeTypeCode(String episodeTypeCode)
	{
		this.episodeTypeCode = episodeTypeCode;
	}



	public String getEpisodeDate()
	{
		return episodeDate;
	}

	public void setEpisodeDate(String episodeDate)
	{
		this.episodeDate = episodeDate;
	}

	public String getIsClosedPreviously() {
		return isClosedPreviously;
	}

	public void setIsClosedPreviously(String isClosedPreviously) {
		this.isClosedPreviously = isClosedPreviously;
	}

	public String getIsAutoClose()
	{
		return isAutoClose;
	}

	public void setIsAutoClose(String isAutoClose)
	{
		this.isAutoClose = isAutoClose;
	}


	public String getPatCrNo()
	{
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
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

	public String getEpisodeVisitSlNo()
	{
		return episodeVisitSlNo;
	}

	public void setEpisodeVisitSlNo(String episodeVisitSlNo)
	{
		this.episodeVisitSlNo = episodeVisitSlNo;
	}

	public String getEpisodeActionCode()
	{
		return episodeActionCode;
	}

	public void setEpisodeActionCode(String episodeActionCode)
	{
		this.episodeActionCode = episodeActionCode;
	}

	public String getEpisodeAction()
	{
		return episodeAction;
	}

	public void setEpisodeAction(String episodeAction)
	{
		this.episodeAction = episodeAction;
	}

	public String getVisitNotes()
	{
		return visitNotes;
	}

	public void setVisitNotes(String visitNotes)
	{
		this.visitNotes = visitNotes;
	}

	public String getEpisodeNextVisitDate()
	{
		return episodeNextVisitDate;
	}

	public void setEpisodeNextVisitDate(String episodeNextVisitDate)
	{
		this.episodeNextVisitDate = episodeNextVisitDate;
	}

	public String getEpisodeNextVisitDeptCode()
	{
		return episodeNextVisitDeptCode;
	}

	public void setEpisodeNextVisitDeptCode(String episodeNextVisitDeptCode)
	{
		this.episodeNextVisitDeptCode = episodeNextVisitDeptCode;
	}

	public String getEpisodeNextVisitDept()
	{
		return episodeNextVisitDept;
	}

	public void setEpisodeNextVisitDept(String episodeNextVisitDept)
	{
		this.episodeNextVisitDept = episodeNextVisitDept;
	}

	public String getEpisodeIsOpen()
	{
		return episodeIsOpen;
	}

	public void setEpisodeIsOpen(String episodeIsOpen)
	{
		this.episodeIsOpen = episodeIsOpen;
	}

	public String getEpisodeSummary()
	{
		return episodeSummary;
	}

	public void setEpisodeSummary(String episodeSummary)
	{
		this.episodeSummary = episodeSummary;
	}

	public String getEmpNo()
	{
		return empNo;
	}

	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getEpisodeNextVisitDeptUnitCode()
	{
		return episodeNextVisitDeptUnitCode;
	}

	public void setEpisodeNextVisitDeptUnitCode(String episodeNextVisitDeptUnitCode)
	{
		this.episodeNextVisitDeptUnitCode = episodeNextVisitDeptUnitCode;
	}

	public String getEpisodeNextVisitDeptUnit()
	{
		return episodeNextVisitDeptUnit;
	}

	public void setEpisodeNextVisitDeptUnit(String episodeNextVisitDeptUnit)
	{
		this.episodeNextVisitDeptUnit = episodeNextVisitDeptUnit;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getSystemIPAddress()
	{
		return systemIPAddress;
	}

	public void setSystemIPAddress(String systemIPAddress)
	{
		this.systemIPAddress = systemIPAddress;
	}

	public String getNextVisitCriteria()
	{
		return nextVisitCriteria;
	}

	public void setNextVisitCriteria(String nextVisitCriteria)
	{
		this.nextVisitCriteria = nextVisitCriteria;
	}

	public String getNextVisitDuration()
	{
		return nextVisitDuration;
	}

	public void setNextVisitDuration(String nextVisitDuration)
	{
		this.nextVisitDuration = nextVisitDuration;
	}

	public String getEpisodeKeywords()
	{
		return episodeKeywords;
	}

	public void setEpisodeKeywords(String episodeKeywords)
	{
		this.episodeKeywords = episodeKeywords;
	}

	public String getEpisodeKeywordID() {
		return episodeKeywordID;
	}

	public void setEpisodeKeywordID(String episodeKeywordID) {
		this.episodeKeywordID = episodeKeywordID;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
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

	public String getIsUnitAppointmentBased() {
		return isUnitAppointmentBased;
	}

	public void setIsUnitAppointmentBased(String isUnitAppointmentBased) {
		this.isUnitAppointmentBased = isUnitAppointmentBased;
	}

	public String getPatNextAptNo() {
		return patNextAptNo;
	}

	public void setPatNextAptNo(String patNextAptNo) {
		this.patNextAptNo = patNextAptNo;
	}

	public String getPatNextAptSlot() {
		return patNextAptSlot;
	}

	public void setPatNextAptSlot(String patNextAptSlot) {
		this.patNextAptSlot = patNextAptSlot;
	}

	public String getPatNextAptQueueNo() {
		return patNextAptQueueNo;
	}

	public void setPatNextAptQueueNo(String patNextAptQueueNo) {
		this.patNextAptQueueNo = patNextAptQueueNo;
	}

	public String getSnomdPTVisitReason() {
		return snomdPTVisitReason;
	}

	public void setSnomdPTVisitReason(String snomdPTVisitReason) {
		this.snomdPTVisitReason = snomdPTVisitReason;
	}

	public String getSnomdCIdVisitReason() {
		return snomdCIdVisitReason;
	}

	public void setSnomdCIdVisitReason(String snomdCIdVisitReason) {
		this.snomdCIdVisitReason = snomdCIdVisitReason;
	}

	public String getSnomdPTVisitNotes() {
		return snomdPTVisitNotes;
	}

	public void setSnomdPTVisitNotes(String snomdPTVisitNotes) {
		this.snomdPTVisitNotes = snomdPTVisitNotes;
	}

	public String getSnomdCIdVisitNotes() {
		return snomdCIdVisitNotes;
	}

	public void setSnomdCIdVisitNotes(String snomdCIdVisitNotes) {
		this.snomdCIdVisitNotes = snomdCIdVisitNotes;
	}

	public String getSnomdPTKeywords() {
		return snomdPTKeywords;
	}

	public void setSnomdPTKeywords(String snomdPTKeywords) {
		this.snomdPTKeywords = snomdPTKeywords;
	}

	public String getSnomdCIdKeywords() {
		return snomdCIdKeywords;
	}

	public void setSnomdCIdKeywords(String snomdCIdKeywords) {
		this.snomdCIdKeywords = snomdCIdKeywords;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSnomdPTEpisodeSummary() {
		return snomdPTEpisodeSummary;
	}

	public void setSnomdPTEpisodeSummary(String snomdPTEpisodeSummary) {
		this.snomdPTEpisodeSummary = snomdPTEpisodeSummary;
	}

	public String getSnomdCIdEpisodeSummary() {
		return snomdCIdEpisodeSummary;
	}

	public void setSnomdCIdEpisodeSummary(String snomdCIdEpisodeSummary) {
		this.snomdCIdEpisodeSummary = snomdCIdEpisodeSummary;
	}
	
	

	public String getComplainDtl()
	{
		return complainDtl;
	}

	public void setComplainDtl(String complainDtl)
	{
		this.complainDtl = complainDtl;
	}



	public String getDiagonisticTypeCode()
	{
		return diagonisticTypeCode;
	}

	public void setDiagonisticTypeCode(String diagonisticTypeCode)
	{
		this.diagonisticTypeCode = diagonisticTypeCode;
	}



	public String getPatRefGnctdHospitalCode()
	{
		return patRefGnctdHospitalCode;
	}

	public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode)
	{
		this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
	}

	public String getPatRefHospitalName()
	{
		return patRefHospitalName;
	}

	public void setPatRefHospitalName(String patRefHospitalName)
	{
		this.patRefHospitalName = patRefHospitalName;
	}




	public String getEpisodeActionDate()
	{
		return episodeActionDate;
	}

	public void setEpisodeActionDate(String episodeActionDate)
	{
		this.episodeActionDate = episodeActionDate;
	}

	public String getEpisodeActionTime()
	{
		return episodeActionTime;
	}

	public void setEpisodeActionTime(String episodeActionTime)
	{
		this.episodeActionTime = episodeActionTime;
	}

	public String getDiagnosticDesc()
	{
		return diagnosticDesc;
	}

	public void setDiagnosticDesc(String diagnosticDesc)
	{
		this.diagnosticDesc = diagnosticDesc;
	}



	

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getRenewalAmount() {
		return renewalAmount;
	}

	public void setRenewalAmount(String renewalAmount) {
		this.renewalAmount = renewalAmount;
	}
	public String getOnRequestVisit() {
		return onRequestVisit;
	}

	public void setOnRequestVisit(String onRequestVisit) {
		this.onRequestVisit = onRequestVisit;
	}

	public String getOldRegDate() {
		return oldRegDate;
	}

	public void setOldRegDate(String oldRegDate) {
		this.oldRegDate = oldRegDate;
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

	public String getIsOtherUnitWorking() {
		return isOtherUnitWorking;
	}

	public void setIsOtherUnitWorking(String isOtherUnitWorking) {
		this.isOtherUnitWorking = isOtherUnitWorking;
	}

	public String getIsEpisodeRenewed() {
		return isEpisodeRenewed;
	}

	public void setIsEpisodeRenewed(String isEpisodeRenewed) {
		this.isEpisodeRenewed = isEpisodeRenewed;
	}

	public String getOldExpiryDate() {
		return oldExpiryDate;
	}

	public void setOldExpiryDate(String oldExpiryDate) {
		this.oldExpiryDate = oldExpiryDate;
	}

	public String getOldCrNo() {
		return oldCrNo;
	}

	public void setOldCrNo(String oldCrNo) {
		this.oldCrNo = oldCrNo;
	}

	public String getDisasterId() {
		return disasterId;
	}

	public void setDisasterId(String disasterId) {
		this.disasterId = disasterId;
	}

	public String getSecCatExpDate() {
		return secCatExpDate;
	}

	public void setSecCatExpDate(String secCatExpDate) {
		this.secCatExpDate = secCatExpDate;
	}	

	public String getDepartmentUnitType() {
		return departmentUnitType;
	}

	public void setDepartmentUnitType(String departmentUnitType) {
		this.departmentUnitType = departmentUnitType;
	}


	public String getIsCardPrint()
	{
		return isCardPrint;
	}

	public void setIsCardPrint(String isCardPrint)
	{
		this.isCardPrint = isCardPrint;
	}

	public String getValidUpto()
	{
		return validUpto;
	}

	public void setValidUpto(String validUpto)
	{
		this.validUpto = validUpto;
	}

	/**
	 * @return Returns the renewalType.
	 */
	public String getRenewalType()
	{
		return renewalType;
	}

	/**
	 * @param renewalType The renewalType to set.
	 */
	public void setRenewalType(String renewalType)
	{
		this.renewalType = renewalType;
	}

	/**
	 * @return Returns the specifyExpiry.
	 */
	public String getSpecifyExpiry()
	{
		return specifyExpiry;
	}

	/**
	 * @param specifyExpiry The specifyExpiry to set.
	 */
	public void setSpecifyExpiry(String specifyExpiry)
	{
		this.specifyExpiry = specifyExpiry;
	}

	public String getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public String getEpisodeExpiryDate() {
		return episodeExpiryDate;
	}

	public void setEpisodeExpiryDate(String episodeExpiryDate) {
		this.episodeExpiryDate = episodeExpiryDate;
	}

	/**
	 * Retrieves systemIPAddress.
	 * 


	/**
	 * Retrieves isGeneral.
	 * 
	 * @return Value of isGeneral.
	 */
	public String getIsGeneral()
	{
		return isGeneral;
	}

	/**
	 * Sets isGeneral.
	 * 
	 * @param isGeneral
	 */
	public void setIsGeneral(String isGeneral)
	{
		this.isGeneral = isGeneral;
	}

	/**
	 * Retrieves renewalRequired.
	 * 
	 * @return Value of renewalRequired.
	 */
	public String getRenewalRequired()
	{
		return renewalRequired;
	}

	/**
	 * Sets renewalRequired.
	 * 
	 * @param renewalRequired
	 */
	public void setRenewalRequired(String renewalRequired)
	{
		this.renewalRequired = renewalRequired;
	}

	/**
	 * Retrieves lastVisitDate.
	 * 
	 * @return Value of lastVisitDate.
	 */
	public String getLastVisitDate()
	{
		return lastVisitDate;
	}

	/**
	 * Sets lastVisitDate.
	 * 
	 * @param lastVisitDate
	 */
	public void setLastVisitDate(String lastVisitDate)
	{
		this.lastVisitDate = lastVisitDate;
	}

	/**
	 * Retrieves remarks.
	 * 
	 * @return Value of remarks.
	 */
	public String getRemarks()
	{
		return remarks;
	}

	/**
	 * Retrieves isReferred.
	 * 
	 * @return Value of isReferred.
	 */
	public String getFileNo()
	{
		return fileNo;
	}

	/**
	 * Sets fileNo.
	 * 
	 * @param fileNo
	 */
	public void setFileNo(String fileNo)
	{
		this.fileNo = fileNo;
	}

	/**
	 * Sets remarks.
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
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
	 * Sets isReferred.
	 * 
	 * @param isReferred
	 */
	public void setIsReferred(String isReferred)
	{
		this.isReferred = isReferred;
	}

	/**
	 * Retrieves deptUnitIsClosed.
	 * 
	 * @return Value of deptUnitIsClosed.
	 */
	public String getDeptUnitIsClosed()
	{
		return deptUnitIsClosed;
	}

	/**
	 * Sets deptUnitIsClosed.
	 * 
	 * @param deptUnitIsClosed
	 */
	public void setDeptUnitIsClosed(String deptUnitIsClosed)
	{
		this.deptUnitIsClosed = deptUnitIsClosed;
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
	 * Retrieves patSecondaryCat.
	 * 
	 * @return Value of patSecondaryCat.
	 */
	public String getPatSecondaryCat()
	{
		return patSecondaryCat;
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
	 * Retrieves patSecondaryCatCode.
	 * 
	 * @return Value of patSecondaryCatCode.
	 */
	public String getPatSecondaryCatCode()
	{
		return patSecondaryCatCode;
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
	 * Retrieves complainDetail.
	 * 
	 * @return Value of complainDetail.
	 */
	public String getComplainDetail()
	{
		return complainDetail;
	}

	/**
	 * Sets complainDetail.
	 * 
	 * @param complainDetail
	 */
	public void setComplainDetail(String complainDetail)
	{
		this.complainDetail = complainDetail;
	}

	public String getEpisodeReferCode() {
		return episodeReferCode;
	}

	public void setEpisodeReferCode(String episodeReferCode) {
		this.episodeReferCode = episodeReferCode;
	}

	public String getEpisodeStartDate() {
		return episodeStartDate;
	}

	public void setEpisodeStartDate(String episodeStartDate) {
		this.episodeStartDate = episodeStartDate;
	}

	/**
	 * Retrieves deptUnitIsOnDuty.
	 * 
	 * @return Value of deptUnitIsOnDuty.
	 */
	public String getDeptUnitIsOnDuty()
	{
		return deptUnitIsOnDuty;
	}

	/**
	 * Sets deptUnitIsOnDuty.
	 * 
	 * @param deptUnitIsOnDuty
	 */
	public void setDeptUnitIsOnDuty(String deptUnitIsOnDuty)
	{
		this.deptUnitIsOnDuty = deptUnitIsOnDuty;
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
	 * Sets patIsOld.
	 * 
	 * @param patIsOld
	 */
	public void setPatIsOld(String patIsOld)
	{
		this.patIsOld = patIsOld;
	}

	

	/**
	 * Retrieves episodeNextVisitType.
	 * 
	 * @return Value of episodeNextVisitType.
	 */
	public String getEpisodeNextVisitType()
	{
		return episodeNextVisitType;
	}

	/**
	 * Sets episodeNextVisitType.
	 * 
	 * @param episodeNextVisitType
	 */
	public void setEpisodeNextVisitType(String episodeNextVisitType)
	{
		this.episodeNextVisitType = episodeNextVisitType;
	}

	/**
	 * Retrieves episodeTime.
	 * 
	 * @return Value of episodeTime.
	 */
	public String getEpisodeTime()
	{
		return episodeTime;
	}

	/**
	 * Sets episodeTime.
	 * 
	 * @param episodeTime
	 */
	public void setEpisodeTime(String episodeTime)
	{
		this.episodeTime = episodeTime;
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

	/**
	 * Retrieves episodeVisitTypeCode.
	 * 
	 * @return Value of episodeVisitTypeCode.
	 */
	public String getEpisodeVisitTypeCode()
	{
		return episodeVisitTypeCode;
	}

	/**
	 * Sets episodeVisitTypeCode.
	 * 
	 * @param episodeVisitTypeCode
	 */
	public void setEpisodeVisitTypeCode(String episodeVisitTypeCode)
	{
		this.episodeVisitTypeCode = episodeVisitTypeCode;
	}

	/**
	 * Retrieves isConfirmed.
	 * 
	 * @return Value of isConfirmed.
	 */
	public String getIsConfirmed()
	{
		return isConfirmed;
	}

	/**
	 * Sets isConfirmed.
	 * 
	 * @param isConfirmed
	 */
	public void setIsConfirmed(String isConfirmed)
	{
		this.isConfirmed = isConfirmed;
	}

	/**
	 * Retrieves mlcNo.
	 * 
	 * @return Value of mlcNo.
	 */
	public String getMlcNo()
	{
		return mlcNo;
	}

	/**
	 * Sets mlcNo.
	 * 
	 * @param mlcNo
	 */
	public void setMlcNo(String mlcNo)
	{
		this.mlcNo = mlcNo;
	}

	public String getGdt_entry_date() {
		return gdt_entry_date;
	}

	public void setGdt_entry_date(String gdt_entry_date) {
		this.gdt_entry_date = gdt_entry_date;
	}



	/**
	 * Retrieves ward.
	 * 
	 * @return Value of ward.
	 */
	public String getWard()
	{
		return ward;
	}

	/**
	 * Sets ward.
	 * 
	 * @param ward
	 */
	public void setWard(String ward)
	{
		this.ward = ward;
	}

	/**
	 * Retrieves wardCode.
	 * 
	 * @return Value of wardCode.
	 */
	public String getWardCode()
	{
		return wardCode;
	}

	/**
	 * Sets wardCode.
	 * 
	 * @param wardCode
	 */
	public void setWardCode(String wardCode)
	{
		this.wardCode = wardCode;
	}

	/**
	 * Retrieves episodeReferAccept.
	 * 
	 * @return Value of episodeReferAccept.
	 */
	public String getEpisodeReferAccept()
	{
		return episodeReferAccept;
	}

	/**
	 * Sets episodeReferAccept.
	 * 
	 * @param episodeReferAccept
	 */
	public void setEpisodeReferAccept(String episodeReferAccept)
	{
		this.episodeReferAccept = episodeReferAccept;
	}

	/**
	 * Retrieves episodeReferAcceptDate.
	 * 
	 * @return Value of episodeReferAcceptDate.
	 */
	public String getEpisodeReferAcceptDate()
	{
		return episodeReferAcceptDate;
	}

	/**
	 * Sets episodeReferAcceptDate.
	 * 
	 * @param episodeReferAcceptDate
	 */
	public void setEpisodeReferAcceptDate(String episodeReferAcceptDate)
	{
		this.episodeReferAcceptDate = episodeReferAcceptDate;
	}

	/**
	 * Retrieves visitedToday.
	 * 
	 * @return Value of visitedToday.
	 */
	public String getVisitedToday()
	{
		return visitedToday;
	}

	/**
	 * Sets visitedToday.
	 * 
	 * @param visitedToday
	 */
	public void setVisitedToday(String visitedToday)
	{
		this.visitedToday = visitedToday;
	}

	public String getPatAmountCollected()
	{
		return patAmountCollected;
	}

	public void setPatAmountCollected(String patAmountCollected)
	{
		this.patAmountCollected = patAmountCollected;
	}

	public String getFileNoView() {
		return fileNoView;
	}

	public void setFileNoView(String fileNoView) {
		this.fileNoView = fileNoView;
	}

	public String getDiagnosisCodeType() {
		return diagnosisCodeType;
	}

	public void setDiagnosisCodeType(String diagnosisCodeType) {
		this.diagnosisCodeType = diagnosisCodeType;
	}

	public String getEpisodeCloseDate() {
		return episodeCloseDate;
	}

	public void setEpisodeCloseDate(String episodeCloseDate) {
		this.episodeCloseDate = episodeCloseDate;
	}

	public String getUnitWorkingDays() {
		return unitWorkingDays;
	}

	public void setUnitWorkingDays(String unitWorkingDays) {
		this.unitWorkingDays = unitWorkingDays;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getIfExists() {
		return ifExists;
	}

	public void setIfExists(String ifExists) {
		this.ifExists = ifExists;
	}

	

	public String getUnitConsultant() {
		return unitConsultant;
	}

	public void setUnitConsultant(String unitConsultant) {
		this.unitConsultant = unitConsultant;
	}

	public String getEpisodeName()
	{
		return episodeName;
	}

	public void setEpisodeName(String episodeName)
	{
		this.episodeName = episodeName;
	}

	public String getDocSeatID()
	{
		return docSeatID;
	}

	public void setDocSeatID(String docSeatID)
	{
		this.docSeatID = docSeatID;
	}





	public String getReferFromDepartment() {
		return referFromDepartment;
	}

	public void setReferFromDepartment(String referFromDepartment) {
		this.referFromDepartment = referFromDepartment;
	}

	public String getPatVisitReason() {
		return patVisitReason;
	}

	public void setPatVisitReason(String patVisitReason) {
		this.patVisitReason = patVisitReason;
	}
	public String getTreatmentValidUpTo() {
		return treatmentValidUpTo;
	}

	public void setTreatmentValidUpTo(String treatmentValidUpTo) {
		this.treatmentValidUpTo = treatmentValidUpTo;
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

	public String getIsForceful() {
		return isForceful;
	}

	public void setIsForceful(String isForceful) {
		this.isForceful = isForceful;
	}

	public String getPatAgeWithUnit() {
		return patAgeWithUnit;
	}

	public void setPatAgeWithUnit(String patAgeWithUnit) {
		this.patAgeWithUnit = patAgeWithUnit;
	}

	public String getOpdRenewalRequired() {
		return opdRenewalRequired;
	}

	public void setOpdRenewalRequired(String opdRenewalRequired) {
		this.opdRenewalRequired = opdRenewalRequired;
	}

	public String getSplRenewalRequired() {
		return splRenewalRequired;
	}

	public void setSplRenewalRequired(String splRenewalRequired) {
		this.splRenewalRequired = splRenewalRequired;
	}

	public String getEmgRenewalRequired() {
		return emgRenewalRequired;
	}

	public void setEmgRenewalRequired(String emgRenewalRequired) {
		this.emgRenewalRequired = emgRenewalRequired;
	}

	public String getIsReprint() {
		return isReprint;
	}

	public void setIsReprint(String isReprint) {
		this.isReprint = isReprint;
	}


	public String getStrRegFlag() {
		return strRegFlag;
	}

	public void setStrRegFlag(String strRegFlag) {
		this.strRegFlag = strRegFlag;
	}

	public String getMlcFlag() {
		return mlcFlag;
	}

	public void setMlcFlag(String mlcFlag) {
		this.mlcFlag = mlcFlag;
	}

	public String getIsCardPrintedToday() {
		return isCardPrintedToday;
	}

	public void setIsCardPrintedToday(String isCardPrintedToday) {
		this.isCardPrintedToday = isCardPrintedToday;
	}

	public String getIsMlcWithin24hrs() {
		return isMlcWithin24hrs;
	}

	public void setIsMlcWithin24hrs(String isMlcWithin24hrs) {
		this.isMlcWithin24hrs = isMlcWithin24hrs;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getCreditBillFlag() {
		return creditBillFlag;
	}

	public void setCreditBillFlag(String creditBillFlag) {
		this.creditBillFlag = creditBillFlag;
	}

	public String getCreditLetterRefNo() {
		return creditLetterRefNo;
	}

	public void setCreditLetterRefNo(String creditLetterRefNo) {
		this.creditLetterRefNo = creditLetterRefNo;
	}

	public String getCreditLetterDate() {
		return creditLetterDate;
	}

	public void setCreditLetterDate(String creditLetterDate) {
		this.creditLetterDate = creditLetterDate;
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





	public String getPatActualAmount() {
		return patActualAmount;
	}

	public void setPatActualAmount(String patActualAmount) {
		this.patActualAmount = patActualAmount;
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

	public String getStrMctsNo() {
		return strMctsNo;
	}

	public void setStrMctsNo(String strMctsNo) {
		this.strMctsNo = strMctsNo;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getIsCloseVo() {
		return isCloseVo;
	}

	public void setIsCloseVo(String isCloseVo) {
		this.isCloseVo = isCloseVo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSelectedSectionData() {
		return selectedSectionData;
	}

	public void setSelectedSectionData(String selectedSectionData) {
		this.selectedSectionData = selectedSectionData;
	}
	
	
	public String getIsSetFOLLOWUP() {
		return isSetFOLLOWUP;
	}
	public void setIsSetFOLLOWUP(String isSetFOLLOWUP) {
		this.isSetFOLLOWUP = isSetFOLLOWUP;
	}


	public class JSONPart
	{
		private String patCrNo;
		private String episodeCode;
		private String episodeVisitNo;
		private String episodeVisitSlNo;
		private String episodeActionCode;
		private String episodeAction;
		private String visitNotes;
		private String episodeNextVisitDate;
		private String episodeNextVisitDeptCode;
		private String episodeNextVisitDept;
		private String episodeIsOpen;
		private String episodeSummary;
		private String empNo;
		private String empName;
		private String seatId;
		private String entryDate;
		private String isValid;
		private String episodeNextVisitDeptUnitCode;
		private String episodeNextVisitDeptUnit;
		private String hospitalCode;
		private String systemIPAddress;
		private String nextVisitCriteria;
		private String nextVisitDuration;
		private String episodeKeywords;
		private String episodeKeywordID;
		private String visitReason;

		private String patAptNo;	  
		private String patAptSlot;
		private String patAptQueueNO;
		private String isAppointment;
		
		
		
		private String isUnitAppointmentBased;
		private String patNextAptNo;	
		private String patNextAptSlot;
		private String patNextAptQueueNo;
		
		
		private String snomdPTVisitReason;
		private String snomdCIdVisitReason;
		
		private String snomdPTVisitNotes;
		private String snomdCIdVisitNotes;
		
		private String snomdPTKeywords;
		private String snomdCIdKeywords;
		private String keywords;
		private String snomdPTEpisodeSummary;
		private String snomdCIdEpisodeSummary;
		
		
		private String diagonisticTypeCode;
		private String diagnosticDesc;
		private String complainDtl;
		private String remarks;
		private String episodeActionDate;
		private String episodeActionTime;
		private String departmentCode;
		private String wardCode;
		private String patRefGnctdHospitalCode;
		private String patRefHospitalName;
		
		private String episodeCloseDate="";
		
		
		private String episodeType;
		private String episodeTypeCode;
		private String admissionNo;
		private String episodeDate;
		
		private String episodeCloseType;

	private String isClosedPreviously;
		
		private String isAutoClose;	// New Field
		
		private String department;

		private String patIsOld;

		private String departmentUnit;
		private String departmentUnitCode;
		private String room;
		private String roomCode;
		private String queNo;
		private String ward;

		private String episodeVisitType;
		private String episodeVisitTypeCode;

		private String episodeTime;

		private String episodeNextVisitType;
		private String episodeReferAccept;
		private String episodeReferAcceptDate;
		private String episodeReferCode;
		private String episodeStartDate;
		private String deptUnitIsOnDuty;
		private String visitedToday;
		private String deptUnitIsClosed;
		private String patAmountCollected;
		private String episodeName;
		private String docSeatID;

		private String mlcNo;
		private String complainDetail;

		private String gdt_entry_date;

		private String isConfirmed;


		private String patSecondaryCat;
		private String patSecondaryCatCode;
		private String patPrimaryCat;
		private String patPrimaryCatCode;
		private String isReferred;

		private String fileNo;
		private String fileNoView;
		private String lastVisitDate;
		private String renewalRequired;
		private String isGeneral;
		
		private String expiryDate;
		private String episodeExpiryDate;
		private String specifyExpiry;
		private String renewalType;
		private String validUpto;
		private String isCardPrint;
		private String diagnosisCodeType;

		private String unitWorkingDays;
		private String disclaimer;
		private String ifExists;
		private String departmentUnitType;
		private String secCatExpDate;
		private String disasterId; 


		private String unitConsultant;

		private String oldCrNo;

		private String oldRegDate;
	    private String isOtherUnitWorking;
	    private String isEpisodeRenewed;
	    private String oldExpiryDate;
	    private String filePrintSetting;
		private String cardPrintSetting;
		private String onRequestVisit;
		private String referFromDepartment;
		private String renewalAmount;
		private String strMctsNo;
		private String hospitalName;
		
		private String patVisitReason;
		private String treatmentValidUpTo; 
		
		private String tariffId;
		private String billNo;
		private String patAgeWithUnit;
		private String isForceful;
		
		private String opdRenewalRequired;
		private String splRenewalRequired;
		private String emgRenewalRequired;
		private String isCardPrintedToday;
		
		private String strRegFlag;

		private String isReprint;
		
		private String mlcFlag;
		private String isMlcWithin24hrs;
		
		private String visitDate;
		
		private String creditBillFlag;
		private String creditLetterRefNo;
		private String creditLetterDate;

		private String patAptId;
		private String patAptStatus;
		
		private String patActualAmount;
		private String receiptNo;
		private String serialNo;
		


		private String slNo;
		private String episodeKeyword;
		
		private String lastModifyDate;
		private String lastModifiedSeatID;

		private String isCloseVo;
		private String username;
		public String getPatCrNo() {
			return patCrNo;
		}
		public void setPatCrNo(String patCrNo) {
			this.patCrNo = patCrNo;
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
		public String getEpisodeVisitSlNo() {
			return episodeVisitSlNo;
		}
		public void setEpisodeVisitSlNo(String episodeVisitSlNo) {
			this.episodeVisitSlNo = episodeVisitSlNo;
		}
		public String getEpisodeActionCode() {
			return episodeActionCode;
		}
		public void setEpisodeActionCode(String episodeActionCode) {
			this.episodeActionCode = episodeActionCode;
		}
		public String getEpisodeAction() {
			return episodeAction;
		}
		public void setEpisodeAction(String episodeAction) {
			this.episodeAction = episodeAction;
		}
		public String getVisitNotes() {
			return visitNotes;
		}
		public void setVisitNotes(String visitNotes) {
			this.visitNotes = visitNotes;
		}
		public String getEpisodeNextVisitDate() {
			return episodeNextVisitDate;
		}
		public void setEpisodeNextVisitDate(String episodeNextVisitDate) {
			this.episodeNextVisitDate = episodeNextVisitDate;
		}
		public String getEpisodeNextVisitDeptCode() {
			return episodeNextVisitDeptCode;
		}
		public void setEpisodeNextVisitDeptCode(String episodeNextVisitDeptCode) {
			this.episodeNextVisitDeptCode = episodeNextVisitDeptCode;
		}
		public String getEpisodeNextVisitDept() {
			return episodeNextVisitDept;
		}
		public void setEpisodeNextVisitDept(String episodeNextVisitDept) {
			this.episodeNextVisitDept = episodeNextVisitDept;
		}
		public String getEpisodeIsOpen() {
			return episodeIsOpen;
		}
		public void setEpisodeIsOpen(String episodeIsOpen) {
			this.episodeIsOpen = episodeIsOpen;
		}
		public String getEpisodeSummary() {
			return episodeSummary;
		}
		public void setEpisodeSummary(String episodeSummary) {
			this.episodeSummary = episodeSummary;
		}
		public String getEmpNo() {
			return empNo;
		}
		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getSeatId() {
			return seatId;
		}
		public void setSeatId(String seatId) {
			this.seatId = seatId;
		}
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		public String getIsValid() {
			return isValid;
		}
		public void setIsValid(String isValid) {
			this.isValid = isValid;
		}
		public String getEpisodeNextVisitDeptUnitCode() {
			return episodeNextVisitDeptUnitCode;
		}
		public void setEpisodeNextVisitDeptUnitCode(String episodeNextVisitDeptUnitCode) {
			this.episodeNextVisitDeptUnitCode = episodeNextVisitDeptUnitCode;
		}
		public String getEpisodeNextVisitDeptUnit() {
			return episodeNextVisitDeptUnit;
		}
		public void setEpisodeNextVisitDeptUnit(String episodeNextVisitDeptUnit) {
			this.episodeNextVisitDeptUnit = episodeNextVisitDeptUnit;
		}
		public String getHospitalCode() {
			return hospitalCode;
		}
		public void setHospitalCode(String hospitalCode) {
			this.hospitalCode = hospitalCode;
		}
		public String getSystemIPAddress() {
			return systemIPAddress;
		}
		public void setSystemIPAddress(String systemIPAddress) {
			this.systemIPAddress = systemIPAddress;
		}
		public String getNextVisitCriteria() {
			return nextVisitCriteria;
		}
		public void setNextVisitCriteria(String nextVisitCriteria) {
			this.nextVisitCriteria = nextVisitCriteria;
		}
		public String getNextVisitDuration() {
			return nextVisitDuration;
		}
		public void setNextVisitDuration(String nextVisitDuration) {
			this.nextVisitDuration = nextVisitDuration;
		}
		public String getEpisodeKeywords() {
			return episodeKeywords;
		}
		public void setEpisodeKeywords(String episodeKeywords) {
			this.episodeKeywords = episodeKeywords;
		}
		public String getEpisodeKeywordID() {
			return episodeKeywordID;
		}
		public void setEpisodeKeywordID(String episodeKeywordID) {
			this.episodeKeywordID = episodeKeywordID;
		}
		public String getVisitReason() {
			return visitReason;
		}
		public void setVisitReason(String visitReason) {
			this.visitReason = visitReason;
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
		public String getIsUnitAppointmentBased() {
			return isUnitAppointmentBased;
		}
		public void setIsUnitAppointmentBased(String isUnitAppointmentBased) {
			this.isUnitAppointmentBased = isUnitAppointmentBased;
		}
		public String getPatNextAptNo() {
			return patNextAptNo;
		}
		public void setPatNextAptNo(String patNextAptNo) {
			this.patNextAptNo = patNextAptNo;
		}
		public String getPatNextAptSlot() {
			return patNextAptSlot;
		}
		public void setPatNextAptSlot(String patNextAptSlot) {
			this.patNextAptSlot = patNextAptSlot;
		}
		public String getPatNextAptQueueNo() {
			return patNextAptQueueNo;
		}
		public void setPatNextAptQueueNo(String patNextAptQueueNo) {
			this.patNextAptQueueNo = patNextAptQueueNo;
		}
		public String getSnomdPTVisitReason() {
			return snomdPTVisitReason;
		}
		public void setSnomdPTVisitReason(String snomdPTVisitReason) {
			this.snomdPTVisitReason = snomdPTVisitReason;
		}
		public String getSnomdCIdVisitReason() {
			return snomdCIdVisitReason;
		}
		public void setSnomdCIdVisitReason(String snomdCIdVisitReason) {
			this.snomdCIdVisitReason = snomdCIdVisitReason;
		}
		public String getSnomdPTVisitNotes() {
			return snomdPTVisitNotes;
		}
		public void setSnomdPTVisitNotes(String snomdPTVisitNotes) {
			this.snomdPTVisitNotes = snomdPTVisitNotes;
		}
		public String getSnomdCIdVisitNotes() {
			return snomdCIdVisitNotes;
		}
		public void setSnomdCIdVisitNotes(String snomdCIdVisitNotes) {
			this.snomdCIdVisitNotes = snomdCIdVisitNotes;
		}
		public String getSnomdPTKeywords() {
			return snomdPTKeywords;
		}
		public void setSnomdPTKeywords(String snomdPTKeywords) {
			this.snomdPTKeywords = snomdPTKeywords;
		}
		public String getSnomdCIdKeywords() {
			return snomdCIdKeywords;
		}
		public void setSnomdCIdKeywords(String snomdCIdKeywords) {
			this.snomdCIdKeywords = snomdCIdKeywords;
		}
		public String getKeywords() {
			return keywords;
		}
		public void setKeywords(String keywords) {
			this.keywords = keywords;
		}
		public String getSnomdPTEpisodeSummary() {
			return snomdPTEpisodeSummary;
		}
		public void setSnomdPTEpisodeSummary(String snomdPTEpisodeSummary) {
			this.snomdPTEpisodeSummary = snomdPTEpisodeSummary;
		}
		public String getSnomdCIdEpisodeSummary() {
			return snomdCIdEpisodeSummary;
		}
		public void setSnomdCIdEpisodeSummary(String snomdCIdEpisodeSummary) {
			this.snomdCIdEpisodeSummary = snomdCIdEpisodeSummary;
		}
		public String getDiagonisticTypeCode() {
			return diagonisticTypeCode;
		}
		public void setDiagonisticTypeCode(String diagonisticTypeCode) {
			this.diagonisticTypeCode = diagonisticTypeCode;
		}
		public String getDiagnosticDesc() {
			return diagnosticDesc;
		}
		public void setDiagnosticDesc(String diagnosticDesc) {
			this.diagnosticDesc = diagnosticDesc;
		}
		public String getComplainDtl() {
			return complainDtl;
		}
		public void setComplainDtl(String complainDtl) {
			this.complainDtl = complainDtl;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getEpisodeActionDate() {
			return episodeActionDate;
		}
		public void setEpisodeActionDate(String episodeActionDate) {
			this.episodeActionDate = episodeActionDate;
		}
		public String getEpisodeActionTime() {
			return episodeActionTime;
		}
		public void setEpisodeActionTime(String episodeActionTime) {
			this.episodeActionTime = episodeActionTime;
		}
		public String getDepartmentCode() {
			return departmentCode;
		}
		public void setDepartmentCode(String departmentCode) {
			this.departmentCode = departmentCode;
		}
		public String getWardCode() {
			return wardCode;
		}
		public void setWardCode(String wardCode) {
			this.wardCode = wardCode;
		}
		public String getPatRefGnctdHospitalCode() {
			return patRefGnctdHospitalCode;
		}
		public void setPatRefGnctdHospitalCode(String patRefGnctdHospitalCode) {
			this.patRefGnctdHospitalCode = patRefGnctdHospitalCode;
		}
		public String getPatRefHospitalName() {
			return patRefHospitalName;
		}
		public void setPatRefHospitalName(String patRefHospitalName) {
			this.patRefHospitalName = patRefHospitalName;
		}
		public String getEpisodeCloseDate() {
			return episodeCloseDate;
		}
		public void setEpisodeCloseDate(String episodeCloseDate) {
			this.episodeCloseDate = episodeCloseDate;
		}
		public String getEpisodeType() {
			return episodeType;
		}
		public void setEpisodeType(String episodeType) {
			this.episodeType = episodeType;
		}
		public String getEpisodeTypeCode() {
			return episodeTypeCode;
		}
		public void setEpisodeTypeCode(String episodeTypeCode) {
			this.episodeTypeCode = episodeTypeCode;
		}
		public String getAdmissionNo() {
			return admissionNo;
		}
		public void setAdmissionNo(String admissionNo) {
			this.admissionNo = admissionNo;
		}
		public String getEpisodeDate() {
			return episodeDate;
		}
		public void setEpisodeDate(String episodeDate) {
			this.episodeDate = episodeDate;
		}
		public String getEpisodeCloseType() {
			return episodeCloseType;
		}
		public void setEpisodeCloseType(String episodeCloseType) {
			this.episodeCloseType = episodeCloseType;
		}
		public String getIsClosedPreviously() {
			return isClosedPreviously;
		}
		public void setIsClosedPreviously(String isClosedPreviously) {
			this.isClosedPreviously = isClosedPreviously;
		}
		public String getIsAutoClose() {
			return isAutoClose;
		}
		public void setIsAutoClose(String isAutoClose) {
			this.isAutoClose = isAutoClose;
		}
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public String getPatIsOld() {
			return patIsOld;
		}
		public void setPatIsOld(String patIsOld) {
			this.patIsOld = patIsOld;
		}
		public String getDepartmentUnit() {
			return departmentUnit;
		}
		public void setDepartmentUnit(String departmentUnit) {
			this.departmentUnit = departmentUnit;
		}
		public String getDepartmentUnitCode() {
			return departmentUnitCode;
		}
		public void setDepartmentUnitCode(String departmentUnitCode) {
			this.departmentUnitCode = departmentUnitCode;
		}
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		public String getRoomCode() {
			return roomCode;
		}
		public void setRoomCode(String roomCode) {
			this.roomCode = roomCode;
		}
		public String getQueNo() {
			return queNo;
		}
		public void setQueNo(String queNo) {
			this.queNo = queNo;
		}
		public String getWard() {
			return ward;
		}
		public void setWard(String ward) {
			this.ward = ward;
		}
		public String getEpisodeVisitType() {
			return episodeVisitType;
		}
		public void setEpisodeVisitType(String episodeVisitType) {
			this.episodeVisitType = episodeVisitType;
		}
		public String getEpisodeVisitTypeCode() {
			return episodeVisitTypeCode;
		}
		public void setEpisodeVisitTypeCode(String episodeVisitTypeCode) {
			this.episodeVisitTypeCode = episodeVisitTypeCode;
		}
		public String getEpisodeTime() {
			return episodeTime;
		}
		public void setEpisodeTime(String episodeTime) {
			this.episodeTime = episodeTime;
		}
		public String getEpisodeNextVisitType() {
			return episodeNextVisitType;
		}
		public void setEpisodeNextVisitType(String episodeNextVisitType) {
			this.episodeNextVisitType = episodeNextVisitType;
		}
		public String getEpisodeReferAccept() {
			return episodeReferAccept;
		}
		public void setEpisodeReferAccept(String episodeReferAccept) {
			this.episodeReferAccept = episodeReferAccept;
		}
		public String getEpisodeReferAcceptDate() {
			return episodeReferAcceptDate;
		}
		public void setEpisodeReferAcceptDate(String episodeReferAcceptDate) {
			this.episodeReferAcceptDate = episodeReferAcceptDate;
		}
		public String getEpisodeReferCode() {
			return episodeReferCode;
		}
		public void setEpisodeReferCode(String episodeReferCode) {
			this.episodeReferCode = episodeReferCode;
		}
		public String getEpisodeStartDate() {
			return episodeStartDate;
		}
		public void setEpisodeStartDate(String episodeStartDate) {
			this.episodeStartDate = episodeStartDate;
		}
		public String getDeptUnitIsOnDuty() {
			return deptUnitIsOnDuty;
		}
		public void setDeptUnitIsOnDuty(String deptUnitIsOnDuty) {
			this.deptUnitIsOnDuty = deptUnitIsOnDuty;
		}
		public String getVisitedToday() {
			return visitedToday;
		}
		public void setVisitedToday(String visitedToday) {
			this.visitedToday = visitedToday;
		}
		public String getDeptUnitIsClosed() {
			return deptUnitIsClosed;
		}
		public void setDeptUnitIsClosed(String deptUnitIsClosed) {
			this.deptUnitIsClosed = deptUnitIsClosed;
		}
		public String getPatAmountCollected() {
			return patAmountCollected;
		}
		public void setPatAmountCollected(String patAmountCollected) {
			this.patAmountCollected = patAmountCollected;
		}
		public String getEpisodeName() {
			return episodeName;
		}
		public void setEpisodeName(String episodeName) {
			this.episodeName = episodeName;
		}
		public String getDocSeatID() {
			return docSeatID;
		}
		public void setDocSeatID(String docSeatID) {
			this.docSeatID = docSeatID;
		}
		public String getMlcNo() {
			return mlcNo;
		}
		public void setMlcNo(String mlcNo) {
			this.mlcNo = mlcNo;
		}
		public String getComplainDetail() {
			return complainDetail;
		}
		public void setComplainDetail(String complainDetail) {
			this.complainDetail = complainDetail;
		}
		public String getGdt_entry_date() {
			return gdt_entry_date;
		}
		public void setGdt_entry_date(String gdt_entry_date) {
			this.gdt_entry_date = gdt_entry_date;
		}
		public String getIsConfirmed() {
			return isConfirmed;
		}
		public void setIsConfirmed(String isConfirmed) {
			this.isConfirmed = isConfirmed;
		}
		public String getPatSecondaryCat() {
			return patSecondaryCat;
		}
		public void setPatSecondaryCat(String patSecondaryCat) {
			this.patSecondaryCat = patSecondaryCat;
		}
		public String getPatSecondaryCatCode() {
			return patSecondaryCatCode;
		}
		public void setPatSecondaryCatCode(String patSecondaryCatCode) {
			this.patSecondaryCatCode = patSecondaryCatCode;
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
		public String getIsReferred() {
			return isReferred;
		}
		public void setIsReferred(String isReferred) {
			this.isReferred = isReferred;
		}
		public String getFileNo() {
			return fileNo;
		}
		public void setFileNo(String fileNo) {
			this.fileNo = fileNo;
		}
		public String getFileNoView() {
			return fileNoView;
		}
		public void setFileNoView(String fileNoView) {
			this.fileNoView = fileNoView;
		}
		public String getLastVisitDate() {
			return lastVisitDate;
		}
		public void setLastVisitDate(String lastVisitDate) {
			this.lastVisitDate = lastVisitDate;
		}
		public String getRenewalRequired() {
			return renewalRequired;
		}
		public void setRenewalRequired(String renewalRequired) {
			this.renewalRequired = renewalRequired;
		}
		public String getIsGeneral() {
			return isGeneral;
		}
		public void setIsGeneral(String isGeneral) {
			this.isGeneral = isGeneral;
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
		public String getSpecifyExpiry() {
			return specifyExpiry;
		}
		public void setSpecifyExpiry(String specifyExpiry) {
			this.specifyExpiry = specifyExpiry;
		}
		public String getRenewalType() {
			return renewalType;
		}
		public void setRenewalType(String renewalType) {
			this.renewalType = renewalType;
		}
		public String getValidUpto() {
			return validUpto;
		}
		public void setValidUpto(String validUpto) {
			this.validUpto = validUpto;
		}
		public String getIsCardPrint() {
			return isCardPrint;
		}
		public void setIsCardPrint(String isCardPrint) {
			this.isCardPrint = isCardPrint;
		}
		public String getDiagnosisCodeType() {
			return diagnosisCodeType;
		}
		public void setDiagnosisCodeType(String diagnosisCodeType) {
			this.diagnosisCodeType = diagnosisCodeType;
		}
		public String getUnitWorkingDays() {
			return unitWorkingDays;
		}
		public void setUnitWorkingDays(String unitWorkingDays) {
			this.unitWorkingDays = unitWorkingDays;
		}
		public String getDisclaimer() {
			return disclaimer;
		}
		public void setDisclaimer(String disclaimer) {
			this.disclaimer = disclaimer;
		}
		public String getIfExists() {
			return ifExists;
		}
		public void setIfExists(String ifExists) {
			this.ifExists = ifExists;
		}
		public String getDepartmentUnitType() {
			return departmentUnitType;
		}
		public void setDepartmentUnitType(String departmentUnitType) {
			this.departmentUnitType = departmentUnitType;
		}
		public String getSecCatExpDate() {
			return secCatExpDate;
		}
		public void setSecCatExpDate(String secCatExpDate) {
			this.secCatExpDate = secCatExpDate;
		}
		public String getDisasterId() {
			return disasterId;
		}
		public void setDisasterId(String disasterId) {
			this.disasterId = disasterId;
		}
		public String getUnitConsultant() {
			return unitConsultant;
		}
		public void setUnitConsultant(String unitConsultant) {
			this.unitConsultant = unitConsultant;
		}
		public String getOldCrNo() {
			return oldCrNo;
		}
		public void setOldCrNo(String oldCrNo) {
			this.oldCrNo = oldCrNo;
		}
		public String getOldRegDate() {
			return oldRegDate;
		}
		public void setOldRegDate(String oldRegDate) {
			this.oldRegDate = oldRegDate;
		}
		public String getIsOtherUnitWorking() {
			return isOtherUnitWorking;
		}
		public void setIsOtherUnitWorking(String isOtherUnitWorking) {
			this.isOtherUnitWorking = isOtherUnitWorking;
		}
		public String getIsEpisodeRenewed() {
			return isEpisodeRenewed;
		}
		public void setIsEpisodeRenewed(String isEpisodeRenewed) {
			this.isEpisodeRenewed = isEpisodeRenewed;
		}
		public String getOldExpiryDate() {
			return oldExpiryDate;
		}
		public void setOldExpiryDate(String oldExpiryDate) {
			this.oldExpiryDate = oldExpiryDate;
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
		public String getOnRequestVisit() {
			return onRequestVisit;
		}
		public void setOnRequestVisit(String onRequestVisit) {
			this.onRequestVisit = onRequestVisit;
		}
		public String getReferFromDepartment() {
			return referFromDepartment;
		}
		public void setReferFromDepartment(String referFromDepartment) {
			this.referFromDepartment = referFromDepartment;
		}
		public String getRenewalAmount() {
			return renewalAmount;
		}
		public void setRenewalAmount(String renewalAmount) {
			this.renewalAmount = renewalAmount;
		}
		public String getStrMctsNo() {
			return strMctsNo;
		}
		public void setStrMctsNo(String strMctsNo) {
			this.strMctsNo = strMctsNo;
		}
		public String getHospitalName() {
			return hospitalName;
		}
		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}
		public String getPatVisitReason() {
			return patVisitReason;
		}
		public void setPatVisitReason(String patVisitReason) {
			this.patVisitReason = patVisitReason;
		}
		public String getTreatmentValidUpTo() {
			return treatmentValidUpTo;
		}
		public void setTreatmentValidUpTo(String treatmentValidUpTo) {
			this.treatmentValidUpTo = treatmentValidUpTo;
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
		public String getPatAgeWithUnit() {
			return patAgeWithUnit;
		}
		public void setPatAgeWithUnit(String patAgeWithUnit) {
			this.patAgeWithUnit = patAgeWithUnit;
		}
		public String getIsForceful() {
			return isForceful;
		}
		public void setIsForceful(String isForceful) {
			this.isForceful = isForceful;
		}
		public String getOpdRenewalRequired() {
			return opdRenewalRequired;
		}
		public void setOpdRenewalRequired(String opdRenewalRequired) {
			this.opdRenewalRequired = opdRenewalRequired;
		}
		public String getSplRenewalRequired() {
			return splRenewalRequired;
		}
		public void setSplRenewalRequired(String splRenewalRequired) {
			this.splRenewalRequired = splRenewalRequired;
		}
		public String getEmgRenewalRequired() {
			return emgRenewalRequired;
		}
		public void setEmgRenewalRequired(String emgRenewalRequired) {
			this.emgRenewalRequired = emgRenewalRequired;
		}
		public String getIsCardPrintedToday() {
			return isCardPrintedToday;
		}
		public void setIsCardPrintedToday(String isCardPrintedToday) {
			this.isCardPrintedToday = isCardPrintedToday;
		}
		public String getStrRegFlag() {
			return strRegFlag;
		}
		public void setStrRegFlag(String strRegFlag) {
			this.strRegFlag = strRegFlag;
		}
		public String getIsReprint() {
			return isReprint;
		}
		public void setIsReprint(String isReprint) {
			this.isReprint = isReprint;
		}
		public String getMlcFlag() {
			return mlcFlag;
		}
		public void setMlcFlag(String mlcFlag) {
			this.mlcFlag = mlcFlag;
		}
		public String getIsMlcWithin24hrs() {
			return isMlcWithin24hrs;
		}
		public void setIsMlcWithin24hrs(String isMlcWithin24hrs) {
			this.isMlcWithin24hrs = isMlcWithin24hrs;
		}
		public String getVisitDate() {
			return visitDate;
		}
		public void setVisitDate(String visitDate) {
			this.visitDate = visitDate;
		}
		public String getCreditBillFlag() {
			return creditBillFlag;
		}
		public void setCreditBillFlag(String creditBillFlag) {
			this.creditBillFlag = creditBillFlag;
		}
		public String getCreditLetterRefNo() {
			return creditLetterRefNo;
		}
		public void setCreditLetterRefNo(String creditLetterRefNo) {
			this.creditLetterRefNo = creditLetterRefNo;
		}
		public String getCreditLetterDate() {
			return creditLetterDate;
		}
		public void setCreditLetterDate(String creditLetterDate) {
			this.creditLetterDate = creditLetterDate;
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
		public String getPatActualAmount() {
			return patActualAmount;
		}
		public void setPatActualAmount(String patActualAmount) {
			this.patActualAmount = patActualAmount;
		}
		public String getReceiptNo() {
			return receiptNo;
		}
		public void setReceiptNo(String receiptNo) {
			this.receiptNo = receiptNo;
		}
		public String getSerialNo() {
			return serialNo;
		}
		public void setSerialNo(String serialNo) {
			this.serialNo = serialNo;
		}
		public String getSlNo() {
			return slNo;
		}
		public void setSlNo(String slNo) {
			this.slNo = slNo;
		}
		public String getEpisodeKeyword() {
			return episodeKeyword;
		}
		public void setEpisodeKeyword(String episodeKeyword) {
			this.episodeKeyword = episodeKeyword;
		}
		public String getLastModifyDate() {
			return lastModifyDate;
		}
		public void setLastModifyDate(String lastModifyDate) {
			this.lastModifyDate = lastModifyDate;
		}
		public String getLastModifiedSeatID() {
			return lastModifiedSeatID;
		}
		public void setLastModifiedSeatID(String lastModifiedSeatID) {
			this.lastModifiedSeatID = lastModifiedSeatID;
		}
		public String getIsCloseVo() {
			return isCloseVo;
		}
		public void setIsCloseVo(String isCloseVo) {
			this.isCloseVo = isCloseVo;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
	}


	public String getNextVisitDate() {
		return nextVisitDate;
	}
	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public String getNextVisitDays() {
		return nextVisitDays;
	}
	public void setNextVisitDays(String nextVisitDays) {
		this.nextVisitDays = nextVisitDays;
	}
	public String getIsDead() {
		return isDead;
	}
	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
	public String getAdviceBy() {
		return adviceBy;
	}
	public void setAdviceBy(String adviceBy) {
		this.adviceBy = adviceBy;
	}
	public String getNextVisitDateFlag() {
		return nextVisitDateFlag;
	}
	public void setNextVisitDateFlag(String nextVisitDateFlag) {
		this.nextVisitDateFlag = nextVisitDateFlag;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getDischargeRemarksDisabled() {
		return dischargeRemarksDisabled;
	}
	public void setDischargeRemarksDisabled(String dischargeRemarksDisabled) {
		this.dischargeRemarksDisabled = dischargeRemarksDisabled;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getDischargeRemarks() {
		return dischargeRemarks;
	}
	public void setDischargeRemarks(String dischargeRemarks) {
		this.dischargeRemarks = dischargeRemarks;
	}
	public String getRevokeRemarks() {
		return revokeRemarks;
	}
	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}
	public String getRequestById() {
		return requestById;
	}
	public void setRequestById(String requestById) {
		this.requestById = requestById;
	}
	public String getRequestByName() {
		return requestByName;
	}
	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}
	public String getRequestByEmpDept() {
		return requestByEmpDept;
	}
	public void setRequestByEmpDept(String requestByEmpDept) {
		this.requestByEmpDept = requestByEmpDept;
	}
	public String getDischargeStatusName() {
		return dischargeStatusName;
	}
	public void setDischargeStatusName(String dischargeStatusName) {
		this.dischargeStatusName = dischargeStatusName;
	}
	public String getDischargePreparedById() {
		return dischargePreparedById;
	}
	public void setDischargePreparedById(String dischargePreparedById) {
		this.dischargePreparedById = dischargePreparedById;
	}
	public String getDischargePreparedByDept() {
		return dischargePreparedByDept;
	}
	public void setDischargePreparedByDept(String dischargePreparedByDept) {
		this.dischargePreparedByDept = dischargePreparedByDept;
	}
	public String getDischargePreparedByName() {
		return dischargePreparedByName;
	}
	public void setDischargePreparedByName(String dischargePreparedByName) {
		this.dischargePreparedByName = dischargePreparedByName;
	}
	public String getDischargeApprovedById() {
		return dischargeApprovedById;
	}
	public void setDischargeApprovedById(String dischargeApprovedById) {
		this.dischargeApprovedById = dischargeApprovedById;
	}
	public String getDischargeApprovedByDept() {
		return dischargeApprovedByDept;
	}
	public void setDischargeApprovedByDept(String dischargeApprovedByDept) {
		this.dischargeApprovedByDept = dischargeApprovedByDept;
	}
	public String getDischargeApprovedByName() {
		return dischargeApprovedByName;
	}
	public void setDischargeApprovedByName(String dischargeApprovedByName) {
		this.dischargeApprovedByName = dischargeApprovedByName;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getDischargePreparedDate() {
		return dischargePreparedDate;
	}
	public void setDischargePreparedDate(String dischargePreparedDate) {
		this.dischargePreparedDate = dischargePreparedDate;
	}


}
