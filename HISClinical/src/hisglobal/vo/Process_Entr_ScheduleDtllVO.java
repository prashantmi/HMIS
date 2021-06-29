package hisglobal.vo;

public class Process_Entr_ScheduleDtllVO extends ValueObject
{
	private String serviceAreaCode;
	private String serviceAreaName;
	private String Code;
	private String serviceName;
	private String serviceReqNo;
	private String patCrNo;
	private String noOfSitting;
	private String serviceDate1;
	private String req_status;
	private String remark;
	private String patName;
	private String episodeCode;// for HRGNUM_EPISODE_NO
	private String episodeVisitNo;// for HRGNUM_VISIT_NO
	private String serviceCode;
	private String[] remarks;// for service Schedule remarks
	private String msg;
	private String[] serviceDate; //Used in Offline,Online Service Requisition
	private String[] serviceDt;  // Used in OfflineAcceptance
	private String deptCode;
	private String[] procedureCode;
	private String chk;
	private String[] intervalString;
	private String visitNo;
	private String visit;
	private String[] paraId;
	private String[] paraValue;
	private String	 index;
	private String[] serviceProvided;
	private String parameterId;
	private String parameterValue;
	private String templateId;
	private String[] chkb;
	private String[] pendingVisitNo;
	private String wholeChkb;
	private String serviceToGiven;
//	private String serviceStatus;//1 prev availed from some other hosp..0 unavailed..
	private String[] serviceCodes;//array of service codes..
	
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
	public String getServiceName()
	{
		return serviceName;
	}
	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}
	public String getServiceReqNo()
	{
		return serviceReqNo;
	}
	public void setServiceReqNo(String serviceReqNo)
	{
		this.serviceReqNo = serviceReqNo;
	}
	public String getPatCrNo()
	{
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo)
	{
		this.patCrNo = patCrNo;
	}
	public String getNoOfSitting()
	{
		return noOfSitting;
	}
	public void setNoOfSitting(String noOfSitting)
	{
		this.noOfSitting = noOfSitting;
	}
	public String getServiceDate1() {
		return serviceDate1;
	}
	public void setServiceDate1(String serviceDate1) {
		this.serviceDate1 = serviceDate1;
	}
	public String[] getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String[] serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getReq_status()
	{
		return req_status;
	}
	public void setReq_status(String req_status)
	{
		this.req_status = req_status;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getPatName()
	{
		return patName;
	}
	public void setPatName(String patName)
	{
		this.patName = patName;
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
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String[] getRemarks() {
		return remarks;
	}
	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String[] getServiceDt() {
		return serviceDt;
	}
	public void setServiceDt(String[] serviceDt) {
		this.serviceDt = serviceDt;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String[] getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String[] procedureCode) {
		this.procedureCode = procedureCode;
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
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String[] getParaId() {
		return paraId;
	}
	public void setParaId(String[] paraId) {
		this.paraId = paraId;
	}
	public String[] getParaValue() {
		return paraValue;
	}
	public void setParaValue(String[] paraValue) {
		this.paraValue = paraValue;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String[] getServiceProvided() {
		return serviceProvided;
	}
	public void setServiceProvided(String[] serviceProvided) {
		this.serviceProvided = serviceProvided;
	}
	public String getParameterId() {
		return parameterId;
	}
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getVisit() {
		return visit;
	}
	public void setVisit(String visit) {
		this.visit = visit;
	}
	public String[] getChkb() {
		return chkb;
	}
	public void setChkb(String[] chkb) {
		this.chkb = chkb;
	}
	public String[] getPendingVisitNo() {
		return pendingVisitNo;
	}
	public void setPendingVisitNo(String[] pendingVisitNo) {
		this.pendingVisitNo = pendingVisitNo;
	}
	public String getWholeChkb() {
		return wholeChkb;
	}
	public void setWholeChkb(String wholeChkb) {
		this.wholeChkb = wholeChkb;
	}
	public String getServiceToGiven() {
		return serviceToGiven;
	}
	public void setServiceToGiven(String serviceToGiven) {
		this.serviceToGiven = serviceToGiven;
	}
	public String[] getServiceCodes() {
		return serviceCodes;
	}
	public void setServiceCodes(String[] serviceCodes) {
		this.serviceCodes = serviceCodes;
	}
}
