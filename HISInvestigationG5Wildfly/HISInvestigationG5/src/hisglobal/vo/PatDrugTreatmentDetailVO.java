package hisglobal.vo;

public class PatDrugTreatmentDetailVO extends ValueObject
{
	private String patCrNo;
	private String episodeCode;
	private String serialNo;
	private String admissionNo;
	private String episodeVisitNo;
	private String departmentUnitCode;
	private String drugId;
	private String drugName;
	private String doseId;
	private String doseName;
	private String frequencyId;
	private String days;
	private String startDate;
	private String endDate;
	private String empNo;
	private String rxContinue;
	private String remarks;

	private String entryDate;
	private String seatId;
	private String isValid;
	private String hospitalCode;
	private String date;
	private String rxContinueFlag;
	
	private String sysDate;
	
	private String requirmentTimeHrs;
	private String requirmentTimeMin;
	private String requirmentTime;
	private String deskType;
	private String revokeStatus;
	private String selRevokeStartDate;
	
	private String isEmptyStomach;
	private String isEmptyStomachName;
	
	private String drugRouteId;
	private String drugRouteName;
	
	private String frequencyName;
	private String drugConsentStatus;
	private String todayVisitFlag;
	
	private String doseQty;
	private String issueQty;
	private String requiredQty;
	private String changeStartDate;
	private String quantity;
	private String startDay;
	
	private String intakeDays;
	private String rxContinueDesc;
	private String doseTime;
	private String emptyStomatchDesc;
	private String empName;
	
	private String opdIpdFlag;
	
	
	//Adding Revoke Remarks by Pawan Kumar B N on 19-Feb-2013
	private String revokeRemarks;
	
	public String getOpdIpdFlag() {
		return opdIpdFlag;
	}

	public void setOpdIpdFlag(String opdIpdFlag) {
		this.opdIpdFlag = opdIpdFlag;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmptyStomatchDesc() {
		return emptyStomatchDesc;
	}

	public void setEmptyStomatchDesc(String emptyStomatchDesc) {
		this.emptyStomatchDesc = emptyStomatchDesc;
	}

	public String getDoseTime() {
		return doseTime;
	}

	public void setDoseTime(String doseTime) {
		this.doseTime = doseTime;
	}

	public String getRxContinueDesc() {
		return rxContinueDesc;
	}

	public void setRxContinueDesc(String rxContinueDesc) {
		this.rxContinueDesc = rxContinueDesc;
	}

	public String getIntakeDays() {
		return intakeDays;
	}

	public void setIntakeDays(String intakeDays) {
		this.intakeDays = intakeDays;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getChangeStartDate() {
		return changeStartDate;
	}

	public void setChangeStartDate(String changeStartDate) {
		this.changeStartDate = changeStartDate;
	}

	public String getDoseQty() {
		return doseQty;
	}

	public void setDoseQty(String doseQty) {
		this.doseQty = doseQty;
	}

	public String getIssueQty() {
		return issueQty;
	}

	public void setIssueQty(String issueQty) {
		this.issueQty = issueQty;
	}

	public String getRequiredQty() {
		return requiredQty;
	}

	public void setRequiredQty(String requiredQty) {
		this.requiredQty = requiredQty;
	}

	public String getTodayVisitFlag() {
		return todayVisitFlag;
	}

	public void setTodayVisitFlag(String todayVisitFlag) {
		this.todayVisitFlag = todayVisitFlag;
	}

	public String getDrugConsentStatus() {
		return drugConsentStatus;
	}

	public void setDrugConsentStatus(String drugConsentStatus) {
		this.drugConsentStatus = drugConsentStatus;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public String getDrugRouteId() {
		return drugRouteId;
	}

	public void setDrugRouteId(String drugRouteId) {
		this.drugRouteId = drugRouteId;
	}

	public String getIsEmptyStomach() {
		return isEmptyStomach;
	}

	public void setIsEmptyStomach(String isEmptyStomach) {
		this.isEmptyStomach = isEmptyStomach;
	}

	public String getSelRevokeStartDate() {
		return selRevokeStartDate;
	}

	public void setSelRevokeStartDate(String selRevokeStartDate) {
		this.selRevokeStartDate = selRevokeStartDate;
	}

	public String getRevokeStatus() {
		return revokeStatus;
	}

	public void setRevokeStatus(String revokeStatus) {
		this.revokeStatus = revokeStatus;
	}

	public String getDeskType() {
		return deskType;
	}

	public void setDeskType(String deskType) {
		this.deskType = deskType;
	}

	public String getRxContinueFlag() {
		return rxContinueFlag;
	}

	public void setRxContinueFlag(String rxContinueFlag) {
		this.rxContinueFlag = rxContinueFlag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public PatDrugTreatmentDetailVO()
	{
		this.patCrNo="";
		this.episodeCode="";
		this.serialNo="";
		this.admissionNo="";
		this.episodeVisitNo="";
		this.drugId="";
		this.drugName="";
		this.doseId="";
		this.doseName="";
		this.frequencyId="";
		this.days="";
		this.startDate="";
		this.endDate="";
		this.empNo="";
		this.rxContinue="";
		this.remarks="";

		this.entryDate="";
		this.seatId="";
		this.isValid="";
		this.hospitalCode="";
		
		this.drugRouteId="";
		this.drugRouteName="";
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

	public String getSerialNo()
	{
		return serialNo;
	}

	public void setSerialNo(String serialNo)
	{
		this.serialNo = serialNo;
	}

	public String getAdmissionNo()
	{
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo)
	{
		this.admissionNo = admissionNo;
	}

	public String getEpisodeVisitNo()
	{
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo)
	{
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getDrugId()
	{
		return drugId;
	}

	public void setDrugId(String drugId)
	{
		this.drugId = drugId;
	}

	public String getDrugName()
	{
		return drugName;
	}

	public void setDrugName(String drugName)
	{
		this.drugName = drugName;
	}

	public String getDoseId()
	{
		return doseId;
	}

	public void setDoseId(String doseId)
	{
		this.doseId = doseId;
	}

	public String getFrequencyId()
	{
		return frequencyId;
	}

	public void setFrequencyId(String frequencyId)
	{
		this.frequencyId = frequencyId;
	}

	public String getDays()
	{
		return days;
	}

	public void setDays(String days)
	{
		this.days = days;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getEmpNo()
	{
		return empNo;
	}

	public void setEmpNo(String empNo)
	{
		this.empNo = empNo;
	}

	public String getRxContinue()
	{
		return rxContinue;
	}

	public void setRxContinue(String rxContinue)
	{
		this.rxContinue = rxContinue;
	}

	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getDoseName()
	{
		return doseName;
	}

	public void setDoseName(String doseName)
	{
		this.doseName = doseName;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getRequirmentTimeHrs() {
		return requirmentTimeHrs;
	}

	public void setRequirmentTimeHrs(String requirmentTimeHrs) {
		this.requirmentTimeHrs = requirmentTimeHrs;
	}

	public String getRequirmentTimeMin() {
		return requirmentTimeMin;
	}

	public void setRequirmentTimeMin(String requirmentTimeMin) {
		this.requirmentTimeMin = requirmentTimeMin;
	}

	public String getRequirmentTime() {
		return requirmentTime;
	}

	public void setRequirmentTime(String requirmentTime) {
		this.requirmentTime = requirmentTime;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getDrugRouteName() {
		return drugRouteName;
	}

	public void setDrugRouteName(String drugRouteName) {
		this.drugRouteName = drugRouteName;
	}

	public String getIsEmptyStomachName() {
		return isEmptyStomachName;
	}

	public void setIsEmptyStomachName(String isEmptyStomachName) {
		this.isEmptyStomachName = isEmptyStomachName;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getRevokeRemarks() {
		return revokeRemarks;
	}

	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}

}
