package hisglobal.vo;

public class Service_Req_dtlVO extends ValueObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private String procCode; 
	private String procedureName;
	private String chk;
	private String[] intervalString;
	private String visitNo;
	private String[] paraId;
	private String[] paraValue;
	private String	 index;
	private String[] serviceProvided;
	private String visit;
	private String rejectionReason;
	private String reqViewStatus;
	private String[] serviceTimeHr=null;
	private String[] serviceTimeMin=null;
	private String[] appDate=null;
	private String[] appTimeHr=null;
	private String[] appTimeMin=null;
	private String appNo="";
	private String[] appStatus=null;
	private String strConfirm="";
	private String fromDate="";
	private String toDate="";
	private String strFlag="";
	private String strReason="";
	private String strPatAgeSex="";
	private String strBatchNo="";
	private String strItemAvailableQty="";
	private String strItemConsumedQty="";
	private String strItemQtyUnit="";
	private String strItemId="";
	private String strItemName="";
	private String strItemQtyUnitId="";
	private String strItemBrandId="";
	private String strItemTypeId="";
	private String strItemCatNo="";
	private String strStoreId="";
	private String strStoreName="";
	private String strSlNo="";
	private String strTempCatIdAll="";
	private String strTempItemTypeIdAll="";
	private String strTempItemIdAll="";
	private String strTempQtyAll="";
	private String strTempQtyUnitAll="";
	private String strTempItemBrandIdAll="";
	private String strTempSlNoAll="";
	private String strTempBatchNoAll="";
	private String strTempItemAvailableQtyAll="";
	private String consumption_Date="";
	private String procedureCodeAll;
	private String strStatus="";
	private String strIsServRaisedInSysdate="";
	private String strErrMsg="";//to show error msg..added by Neha Sharma..
	private String serviceStatus;//1 prev availed from some other hosp..0 unavailed..
	private String[] serviceCodes;//array of service codes..
	private String[] serviceStatusArr;
	private String errMsgType="0"; //0 no error..2 error..
	
	public String getProcedureCodeAll() {
		return procedureCodeAll;
	}
	public void setProcedureCodeAll(String procedureCodeAll) {
		this.procedureCodeAll = procedureCodeAll;
	}
	public String getStrTempCatIdAll() {
		return strTempCatIdAll;
	}
	public void setStrTempCatIdAll(String strTempCatIdAll) {
		this.strTempCatIdAll = strTempCatIdAll;
	}
	public String getStrTempItemTypeIdAll() {
		return strTempItemTypeIdAll;
	}
	public void setStrTempItemTypeIdAll(String strTempItemTypeIdAll) {
		this.strTempItemTypeIdAll = strTempItemTypeIdAll;
	}
	public String getStrTempItemIdAll() {
		return strTempItemIdAll;
	}
	public void setStrTempItemIdAll(String strTempItemIdAll) {
		this.strTempItemIdAll = strTempItemIdAll;
	}
	public String getStrTempQtyAll() {
		return strTempQtyAll;
	}
	public void setStrTempQtyAll(String strTempQtyAll) {
		this.strTempQtyAll = strTempQtyAll;
	}
	public String getStrTempQtyUnitAll() {
		return strTempQtyUnitAll;
	}
	public void setStrTempQtyUnitAll(String strTempQtyUnitAll) {
		this.strTempQtyUnitAll = strTempQtyUnitAll;
	}
	public String getStrTempItemBrandIdAll() {
		return strTempItemBrandIdAll;
	}
	public void setStrTempItemBrandIdAll(String strTempItemBrandIdAll) {
		this.strTempItemBrandIdAll = strTempItemBrandIdAll;
	}
	public String getStrTempSlNoAll() {
		return strTempSlNoAll;
	}
	public void setStrTempSlNoAll(String strTempSlNoAll) {
		this.strTempSlNoAll = strTempSlNoAll;
	}
	public String getStrTempBatchNoAll() {
		return strTempBatchNoAll;
	}
	public void setStrTempBatchNoAll(String strTempBatchNoAll) {
		this.strTempBatchNoAll = strTempBatchNoAll;
	}
	public String getStrTempItemAvailableQtyAll() {
		return strTempItemAvailableQtyAll;
	}
	public void setStrTempItemAvailableQtyAll(String strTempItemAvailableQtyAll) {
		this.strTempItemAvailableQtyAll = strTempItemAvailableQtyAll;
	}
	public String getStrSlNo() {
		return strSlNo;
	}
	public void setStrSlNo(String strSlNo) {
		this.strSlNo = strSlNo;
	}
	public String getStrPatAgeSex() {
		return strPatAgeSex;
	}
	public void setStrPatAgeSex(String strPatAgeSex) {
		this.strPatAgeSex = strPatAgeSex;
	}
	public String getStrReason() {
		return strReason;
	}
	public void setStrReason(String strReason) {
		this.strReason = strReason;
	}
	public String getStrFlag() {
		return strFlag;
	}
	public void setStrFlag(String strFlag) {
		this.strFlag = strFlag;
	}
	public String getStrConfirm() {
		return strConfirm;
	}
	public void setStrConfirm(String strConfirm) {
		this.strConfirm = strConfirm;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	public String getVisit() {
		return visit;
	}
	public void setVisit(String visit) {
		this.visit = visit;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getReqViewStatus() {
		return reqViewStatus;
	}
	public void setReqViewStatus(String reqViewStatus) {
		this.reqViewStatus = reqViewStatus;
	}
	public String[] getServiceTimeHr() {
		return serviceTimeHr;
	}
	public void setServiceTimeHr(String[] serviceTimeHr) {
		this.serviceTimeHr = serviceTimeHr;
	}
	public String[] getServiceTimeMin() {
		return serviceTimeMin;
	}
	public void setServiceTimeMin(String[] serviceTimeMin) {
		this.serviceTimeMin = serviceTimeMin;
	}
	public String[] getAppDate() {
		return appDate;
	}
	public void setAppDate(String[] appDate) {
		this.appDate = appDate;
	}
	public String[] getAppTimeHr() {
		return appTimeHr;
	}
	public void setAppTimeHr(String[] appTimeHr) {
		this.appTimeHr = appTimeHr;
	}
	public String[] getAppTimeMin() {
		return appTimeMin;
	}
	public void setAppTimeMin(String[] appTimeMin) {
		this.appTimeMin = appTimeMin;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String[] getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String[] appStatus) {
		this.appStatus = appStatus;
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
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getProcCode() {
		return procCode;
	}
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	public String getStrBatchNo() {
		return strBatchNo;
	}
	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}
	public String getStrItemAvailableQty() {
		return strItemAvailableQty;
	}
	public void setStrItemAvailableQty(String strItemAvailableQty) {
		this.strItemAvailableQty = strItemAvailableQty;
	}
	public String getStrItemConsumedQty() {
		return strItemConsumedQty;
	}
	public void setStrItemConsumedQty(String strItemConsumedQty) {
		this.strItemConsumedQty = strItemConsumedQty;
	}
	public String getStrItemQtyUnit() {
		return strItemQtyUnit;
	}
	public void setStrItemQtyUnit(String strItemQtyUnit) {
		this.strItemQtyUnit = strItemQtyUnit;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrItemName() {
		return strItemName;
	}
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}
	public String getStrItemQtyUnitId() {
		return strItemQtyUnitId;
	}
	public void setStrItemQtyUnitId(String strItemQtyUnitId) {
		this.strItemQtyUnitId = strItemQtyUnitId;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public String getStrItemTypeId() {
		return strItemTypeId;
	}
	public void setStrItemTypeId(String strItemTypeId) {
		this.strItemTypeId = strItemTypeId;
	}
	public String getStrItemCatNo() {
		return strItemCatNo;
	}
	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getConsumption_Date() {
		return consumption_Date;
	}
	public void setConsumption_Date(String consumption_Date) {
		this.consumption_Date = consumption_Date;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrIsServRaisedInSysdate() {
		return strIsServRaisedInSysdate;
	}
	public void setStrIsServRaisedInSysdate(String strIsServRaisedInSysdate) {
		this.strIsServRaisedInSysdate = strIsServRaisedInSysdate;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String[] getServiceCodes() {
		return serviceCodes;
	}
	public void setServiceCodes(String[] serviceCodes) {
		this.serviceCodes = serviceCodes;
	}
	public String[] getServiceStatusArr() {
		return serviceStatusArr;
	}
	public void setServiceStatusArr(String[] serviceStatusArr) {
		this.serviceStatusArr = serviceStatusArr;
	}
	public String getErrMsgType() {
		return errMsgType;
	}
	public void setErrMsgType(String errMsgType) {
		this.errMsgType = errMsgType;
	}
	
}
