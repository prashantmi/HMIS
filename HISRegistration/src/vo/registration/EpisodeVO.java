package vo.registration;

import hisglobal.vo.ValueObject;

/**
 * EpisodeVO is the class that specifies getters and setters for all the identifiers which are used for retrieving and
 * inserting data in the DB tables.
 * 
 * @author AHIS
 */
public class EpisodeVO extends ValueObject
{
	private String department;
	private String patCrNo;
	private String patIsOld;
	private String departmentCode;
	private String departmentUnit;
	private String departmentUnitCode;
	private String room;
	private String mlcDetail;
	private String roomCode;
	private String queNo;
	private String ward;
	private String wardCode;
	private String episodeCode;
	private String episodeVisitNo;
	private String episodeVisitType;
	private String episodeVisitTypeCode;
	private String episodeType;
	private String episodeTypeCode;
	private String episodeAction;
	private String episodeActionCode;
	private String episodeDate;
	private String episodeTime;
	private String episodeIsOpen;
	private String episodeNextVisitDate;
	private String episodeNextVisitDept;
	private String episodeNextVisitDeptCode;
	private String episodeNextVisitDeptUnit;
	private String episodeNextVisitDeptUnitCode;
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
	private String hospitalCode;	

	private String admissionNo;
	private String mlcNo;
	private String complainDetail;
	private String entryDate;
	private String gdt_entry_date;
	private String seatId;
	private String isValid;
	private String isConfirmed;
	private String empNo;
	private String empName;

	private String patSecondaryCat;
	private String patSecondaryCatCode;
	private String patPrimaryCat;
	private String patPrimaryCatCode;
	private String isReferred;
	private String remarks;
	private String fileNo;
	private String fileNoView;
	private String lastVisitDate;
	private String renewalRequired;
	private String isGeneral;
	private String systemIPAddress;// IP address of the system from which the details are entered
	private String expiryDate;
	private String episodeExpiryDate;
	private String specifyExpiry;
	private String renewalType;
	private String validUpto;
	private String isCardPrint;
	private String diagnosisCodeType;
	private String episodeCloseDate;
	private String unitWorkingDays;
	private String disclaimer;
	private String ifExists;
	private String departmentUnitType;
	private String secCatExpDate;
	private String disasterId; 

	// From Episode Summary
	private String visitNotes;
	private String nextVisitCriteria;
	private String nextVisitDuration;
	private String unitConsultant;

	private String oldCrNo;
	private String episodeSummary;
	private String oldRegDate;
    private String isOtherUnitWorking;
    private String isEpisodeRenewed;
    private String oldExpiryDate;
    private String filePrintSetting;
	private String cardPrintSetting;
	private String onRequestVisit;
	private String referFromDepartment;
	private String renewalAmount;
	
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
	private String patAptNo;	
	private String patAptSlot;
	private String patAptQueueNO;
	private String isAppointment;
	private String patActualAmount;
	private String receiptNo;
	private String serialNo;
	
	//added by Mukund on 15.07.2016
	private String refundAllowed;
	
	//Added by Vasu on 23.04.18
	private String treatmentCategory;
	private String applicableServices;
	private String creditLimit;
	
	private String isAmbulatory; //Added by Vasu on 03.May.18
	private String isTrauma;//Added by Vasu on 05.June.2018
	
	private String snomdPTVisitReason; //Added by Garima for UNit Wise Visit
	private String arrPatVisitReason; //Added by Garima for UNit Wise Visit
	private String arrsnomdPTVisitReason;
	private String arrsnomdCIdVisitReason;
	private String paymentModeCode;
	private String paymentModeCodeRefId;
	
	public String getArrsnomdPTVisitReason() {
		return arrsnomdPTVisitReason;
	}
	public void setArrsnomdPTVisitReason(String arrsnomdPTVisitReason) {
		this.arrsnomdPTVisitReason = arrsnomdPTVisitReason;
	}
	public String getArrsnomdCIdVisitReason() {
		return arrsnomdCIdVisitReason;
	}
	public void setArrsnomdCIdVisitReason(String arrsnomdCIdVisitReason) {
		this.arrsnomdCIdVisitReason = arrsnomdCIdVisitReason;
	}
	public String getArrPatVisitReason() {
		return arrPatVisitReason;
	}

	public void setArrPatVisitReason(String arrPatVisitReason) {
		this.arrPatVisitReason = arrPatVisitReason;
	}

	public String getSnomdPTVisitReason() {
		return snomdPTVisitReason;
	}

	public void setSnomdPTVisitReason(String snomdPTVisitReason) {
		this.snomdPTVisitReason = snomdPTVisitReason;
	}

	private String snomdCIdVisitReason; //Added by Garima for UNit Wise Visit
	
	public String getSnomdCIdVisitReason() {
		return snomdCIdVisitReason;
	}

	public void setSnomdCIdVisitReason(String snomdCIdVisitReason) {
		this.snomdCIdVisitReason = snomdCIdVisitReason;
	}

	public String getRefundAllowed() {
		return refundAllowed;
	}

	public void setRefundAllowed(String refundAllowed) {
		this.refundAllowed = refundAllowed;
	}
	//End
	
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
	 * @return Value of systemIPAddress.
	 */
	public String getSystemIPAddress()
	{
		return systemIPAddress;
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
	public void setEpisodeType(String episodeType)
	{
		this.episodeType = episodeType;
	}

	/**
	 * Retrieves episodeType.
	 * 
	 * @return Value of episodeType.
	 */
	public String getEpisodeType()
	{
		return episodeType;
	}

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
	 * Retrieves admissionNo.
	 * 
	 * @return Value of admissionNo.
	 */
	public String getAdmissionNo()
	{
		return admissionNo;
	}

	/**
	 * Sets admissionNo.
	 * 
	 * @param admissionNo
	 */
	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
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
	 * Retrieves empName.
	 * 
	 * @return Value of empName.
	 */
	public String getEmpName()
	{
		return empName;
	}

	/**
	 * Sets empName.
	 * 
	 * @param empName
	 */
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	/**
	 * Retrieves empNo.
	 * 
	 * @return Value of empNo.
	 */
	public String getEmpNo()
	{
		return empNo;
	}

	/**
	 * Sets empNo.
	 * 
	 * @param empNo
	 */
	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}

	/**
	 * Retrieves episodeAction.
	 * 
	 * @return Value of episodeAction.
	 */
	public String getEpisodeAction()
	{
		return episodeAction;
	}

	/**
	 * Sets episodeAction.
	 * 
	 * @param episodeAction
	 */
	public void setEpisodeAction(String episodeAction)
	{
		this.episodeAction = episodeAction;
	}

	/**
	 * Retrieves episodeActionCode.
	 * 
	 * @return Value of episodeActionCode.
	 */
	public String getEpisodeActionCode()
	{
		return episodeActionCode;
	}

	/**
	 * Sets episodeActionCode.
	 * 
	 * @param episodeActionCode
	 */
	public void setEpisodeActionCode(String episodeActionCode)
	{
		this.episodeActionCode = episodeActionCode;
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
	 * Retrieves episodeIsOpen.
	 * 
	 * @return Value of episodeIsOpen.
	 */
	public String getEpisodeIsOpen()
	{
		return episodeIsOpen;
	}

	/**
	 * Sets episodeIsOpen.
	 * 
	 * @param episodeIsOpen
	 */
	public void setEpisodeIsOpen(String episodeIsOpen)
	{
		this.episodeIsOpen = episodeIsOpen;
	}

	/**
	 * Retrieves episodeNextVisitDate.
	 * 
	 * @return Value of episodeNextVisitDate.
	 */
	public String getEpisodeNextVisitDate()
	{
		return episodeNextVisitDate;
	}

	/**
	 * Sets episodeNextVisitDate.
	 * 
	 * @param episodeNextVisitDate
	 */
	public void setEpisodeNextVisitDate(String episodeNextVisitDate)
	{
		this.episodeNextVisitDate = episodeNextVisitDate;
	}

	/**
	 * Retrieves episodeNextVisitDept.
	 * 
	 * @return Value of episodeNextVisitDept.
	 */
	public String getEpisodeNextVisitDept()
	{
		return episodeNextVisitDept;
	}

	/**
	 * Sets episodeNextVisitDept.
	 * 
	 * @param episodeNextVisitDept
	 */
	public void setEpisodeNextVisitDept(String episodeNextVisitDept)
	{
		this.episodeNextVisitDept = episodeNextVisitDept;
	}

	/**
	 * Retrieves episodeNextVisitDeptCode.
	 * 
	 * @return Value of episodeNextVisitDeptCode.
	 */
	public String getEpisodeNextVisitDeptCode()
	{
		return episodeNextVisitDeptCode;
	}

	/**
	 * Sets episodeNextVisitDeptCode.
	 * 
	 * @param episodeNextVisitDeptCode
	 */
	public void setEpisodeNextVisitDeptCode(String episodeNextVisitDeptCode)
	{
		this.episodeNextVisitDeptCode = episodeNextVisitDeptCode;
	}

	/**
	 * Retrieves episodeNextVisitDeptUnit.
	 * 
	 * @return Value of episodeNextVisitDeptUnit.
	 */
	public String getEpisodeNextVisitDeptUnit()
	{
		return episodeNextVisitDeptUnit;
	}

	/**
	 * Sets episodeNextVisitDeptUnit.
	 * 
	 * @param episodeNextVisitDeptUnit
	 */
	public void setEpisodeNextVisitDeptUnit(String episodeNextVisitDeptUnit)
	{
		this.episodeNextVisitDeptUnit = episodeNextVisitDeptUnit;
	}

	/**
	 * Retrieves episodeNextVisitDeptUnitCode.
	 * 
	 * @return Value of episodeNextVisitDeptUnitCode.
	 */
	public String getEpisodeNextVisitDeptUnitCode()
	{
		return episodeNextVisitDeptUnitCode;
	}

	/**
	 * Sets episodeNextVisitDeptUnitCode.
	 * 
	 * @param episodeNextVisitDeptUnitCode
	 */
	public void setEpisodeNextVisitDeptUnitCode(String episodeNextVisitDeptUnitCode)
	{
		this.episodeNextVisitDeptUnitCode = episodeNextVisitDeptUnitCode;
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

	public String getVisitNotes()
	{
		return visitNotes;
	}

	public void setVisitNotes(String visitNotes)
	{
		this.visitNotes = visitNotes;
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

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getEpisodeSummary()
	{
		return episodeSummary;
	}

	public void setEpisodeSummary(String episodeSummary)
	{
		this.episodeSummary = episodeSummary;
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

	public String getTreatmentCategory() {
		return treatmentCategory;
	}

	public void setTreatmentCategory(String treatmentCategory) {
		this.treatmentCategory = treatmentCategory;
	}

	public String getApplicableServices() {
		return applicableServices;
	}

	public void setApplicableServices(String applicableServices) {
		this.applicableServices = applicableServices;
	}

	public String getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getIsAmbulatory() {
		return isAmbulatory;
	}

	public void setIsAmbulatory(String isAmbulatory) {
		this.isAmbulatory = isAmbulatory;
	}

	public String getIsTrauma() {
		return isTrauma;
	}

	public void setIsTrauma(String isTrauma) {
		this.isTrauma = isTrauma;
	}
	public String getMlcDetail() {
		return mlcDetail;
	}
	public void setMlcDetail(String mlcDetail) {
		this.mlcDetail = mlcDetail;
	}
	public String getPaymentModeCode() {
		return paymentModeCode;
	}
	public void setPaymentModeCode(String paymentModeCode) {
		this.paymentModeCode = paymentModeCode;
	}
	public String getPaymentModeCodeRefId() {
		return paymentModeCodeRefId;
	}
	public void setPaymentModeCodeRefId(String paymentModeCodeRefId) {
		this.paymentModeCodeRefId = paymentModeCodeRefId;
	}
}
