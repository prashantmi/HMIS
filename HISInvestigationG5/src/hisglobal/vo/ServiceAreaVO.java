package hisglobal.vo;

public class ServiceAreaVO extends ValueObject
{
	private String 		deptCode;
	private String 		deptName;
	private String 		serviceAreaCode;
	private String 		serviceAreaName;
	private String[] 	procedureCode;
	private String 		procedureName;
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
	private String		serviceToProvided;
	private String[]	serviceProvided;
	private String		msg;
	private String 		reqStatusCode;
	private String 		ageToGiveVaccine;
	private String 		dateChk;
	private String 		cardHeader;
	private String 		cardFooter;

	private String[] rescheduleAction ; 
	private String     tempChk="1"; 
    private String     _reqStatus;
    private String     proc_Code;
    private String     strSittingDate;
    private String     strSittingTime;
    private String 		strServiceDate;
    private String strProcCategory="0";//0 non immunization based 1 immunization based..used in jsp..

    private String serviceCode;
    
    
	public String getCardHeader() {
		return cardHeader;
	}
	public void setCardHeader(String cardHeader) {
		this.cardHeader = cardHeader;
	}
	public String getCardFooter() {
		return cardFooter;
	}
	public void setCardFooter(String cardFooter) {
		this.cardFooter = cardFooter;
	}
	public String[] getRescheduleAction() {
		return rescheduleAction;
	}
	public void setRescheduleAction(String[] rescheduleAction) {
		this.rescheduleAction = rescheduleAction;
	}
	public String getTempChk() {
		return tempChk;
	}
	public void setTempChk(String tempChk) {
		this.tempChk = tempChk;
	}
	public String get_reqStatus() {
		return _reqStatus;
	}
	public void set_reqStatus(String status) {
		_reqStatus = status;
	}
	public String getProc_Code() {
		return proc_Code;
	}
	public void setProc_Code(String proc_Code) {
		this.proc_Code = proc_Code;
	}
	public String getDateChk() {
		return dateChk;
	}
	public void setDateChk(String dateChk) {
		this.dateChk = dateChk;
	}
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
	public String[] getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String[] procedureCode) {
		this.procedureCode = procedureCode;
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
	public String getNoOfSitting() {
		return noOfSitting;
	}
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
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String[] getIntervalString() {
		return intervalString;
	}
	public void setIntervalString(String[] intervalString) {
		this.intervalString = intervalString;
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
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getDtService() {
		return dtService;
	}
	public void setDtService(String dtService) {
		this.dtService = dtService;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getScheduleStatus() {
		return scheduleStatus;
	}
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getServiceReqChoice() {
		return serviceReqChoice;
	}
	public void setServiceReqChoice(String serviceReqChoice) {
		this.serviceReqChoice = serviceReqChoice;
	}
	public String getServiceToProvided() {
		return serviceToProvided;
	}
	public void setServiceToProvided(String serviceToProvided) {
		this.serviceToProvided = serviceToProvided;
	}
	public String[] getServiceProvided() {
		return serviceProvided;
	}
	public void setServiceProvided(String[] serviceProvided) {
		this.serviceProvided = serviceProvided;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReqStatusCode() {
		return reqStatusCode;
	}
	public void setReqStatusCode(String reqStatusCode) {
		this.reqStatusCode = reqStatusCode;
	}
	public String getAgeToGiveVaccine() {
		return ageToGiveVaccine;
	}
	public void setAgeToGiveVaccine(String ageToGiveVaccine) {
		this.ageToGiveVaccine = ageToGiveVaccine;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStrSittingDate() {
		return strSittingDate;
	}
	public void setStrSittingDate(String strSittingDate) {
		this.strSittingDate = strSittingDate;
	}
	public String getStrSittingTime() {
		return strSittingTime;
	}
	public void setStrSittingTime(String strSittingTime) {
		this.strSittingTime = strSittingTime;
	}
	public String getStrServiceDate() {
		return strServiceDate;
	}
	public void setStrServiceDate(String strServiceDate) {
		this.strServiceDate = strServiceDate;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getStrProcCategory() {
		return strProcCategory;
	}
	public void setStrProcCategory(String strProcCategory) {
		this.strProcCategory = strProcCategory;
	}
	
}
