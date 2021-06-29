package hisglobal.vo;

public class PrintCardVO extends ValueObject
{
	private String 		deptCode;
	private String 		serviceAreaCode;
	private String[] 	procedureCode;
	private String 		serviceReqNo;
	private String 		patCrNo;
	private String 		patName;
	private String		noOfSitting;//Individual Sitting
	//private String[]	sittings;//Sitting for all patients
	private String 		remark;
	private String 		scheduleRemark;
	private String 		chk;
	private String[] 	intervalString;
	private String 		intervalDay;
	private String[] 	serviceDate;
	private String 		reqStatus;
	private String 		visitNo;
	private String 		dtService;
	private String[]	remarks;
	private String 		scheduleStatus;
	private String 		fromDate;
	private String 		toDate;
	private String 		serviceReqChoice;
	private String[]	serviceProvided;
	private String		serviceToProvided;
	private String 		patDOB;
	private String	 	patMotherName;
	private String 		add1;
	private String 		add2;
	private String 		add3;
	private String 		patGuardianName;
	private String 		patPrimaryCat;
	private String 		patGender;
	private String 		patAge;
	private String		patHusbandName;
	private String 		admissionNo;
	private String 		admissionDate;
	private String 		wardCode;
	private String 		wardName;
	private String 		roomNo;
	private String 		bedNo;
	private String 		episodeTypeCode;	
	private String 		episodeDate;
	private String 		visitType;
	private String 		isExpended;
	private String 		mlcDepartmentName;
	private String 		mlcUnitName;
	private String 		mlcNo;
	private String 		expiryDate;
	private String 		departmentCode;
	private String 		episodeCode;
	private String		departmentName;
	private String 		unitName;
	private String 		departmentUnitCode;
	private String 		episodeVisitNo;//for HRGNUM_VISIT_NO
	private String hospName;
	
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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
	public String getToDate() {
		return toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getDtService() {
		return dtService;
	}
	public void setDtService(String dtService) {
		this.dtService = dtService;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getIntervalDay() {
		return intervalDay;
	}
	public void setIntervalDay(String intervalDay) {
		this.intervalDay = intervalDay;
	}
	public String[] getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String[] serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String[] getIntervalString() {
		return intervalString;
	}
	public void setIntervalString(String[] intervalString) {
		this.intervalString = intervalString;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String[] getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String[] procedureCode) {
		this.procedureCode = procedureCode;
	}
	private String serviceAreaName;

	public String getDeptCode()
	{
		return deptCode;
	}
	public void setDeptCode(String deptCode)
	{
		this.deptCode = deptCode;
	}
	public String getServiceAreaCode()
	{
		return serviceAreaCode;
	}
	public void setServiceAreaCode(String serviceAreaCode)
	{
		this.serviceAreaCode = serviceAreaCode;
	}
	public String getServiceAreaName()
	{
		return serviceAreaName;
	}
	public void setServiceAreaName(String serviceAreaName)
	{
		this.serviceAreaName = serviceAreaName;
	}
	public String getServiceReqNo() {
		return serviceReqNo;
	}
	public void setServiceReqNo(String serviceReqNo) {
		this.serviceReqNo = serviceReqNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	/*public String[] getSittings() {
		return sittings;
	}*/
	public String getNoOfSitting() {
		return noOfSitting;
	}
	/*public void setSittings(String[] sittings) {
		this.sittings = sittings;
	}*/
	public void setNoOfSitting(String noOfSitting) {
		this.noOfSitting = noOfSitting;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScheduleRemark() {
		return scheduleRemark;
	}
	public void setScheduleRemark(String scheduleRemark) {
		this.scheduleRemark = scheduleRemark;
	}
	public String getServiceReqChoice() {
		return serviceReqChoice;
	}
	public void setServiceReqChoice(String serviceReqChoice) {
		this.serviceReqChoice = serviceReqChoice;
	}
	public String[] getServiceProvided() {
		return serviceProvided;
	}
	public void setServiceProvided(String[] serviceProvided) {
		this.serviceProvided = serviceProvided;
	}
	public String getServiceToProvided() {
		return serviceToProvided;
	}
	public void setServiceToProvided(String serviceToProvided) {
		this.serviceToProvided = serviceToProvided;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatMotherName() {
		return patMotherName;
	}
	public void setPatMotherName(String patMotherName) {
		this.patMotherName = patMotherName;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getAdd3() {
		return add3;
	}
	public void setAdd3(String add3) {
		this.add3 = add3;
	}
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
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
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getBedNo() {
		return bedNo;
	}
	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}
	public String getEpisodeTypeCode() {
		return episodeTypeCode;
	}
	public void setEpisodeTypeCode(String episodeTypeCode) {
		this.episodeTypeCode = episodeTypeCode;
	}
	public String getEpisodeDate() {
		return episodeDate;
	}
	public void setEpisodeDate(String episodeDate) {
		this.episodeDate = episodeDate;
	}
	public String getVisitType() {
		return visitType;
	}
	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}
	public String getIsExpended() {
		return isExpended;
	}
	public void setIsExpended(String isExpended) {
		this.isExpended = isExpended;
	}
	public String getMlcDepartmentName() {
		return mlcDepartmentName;
	}
	public void setMlcDepartmentName(String mlcDepartmentName) {
		this.mlcDepartmentName = mlcDepartmentName;
	}
	public String getMlcUnitName() {
		return mlcUnitName;
	}
	public void setMlcUnitName(String mlcUnitName) {
		this.mlcUnitName = mlcUnitName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
}
