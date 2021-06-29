package new_investigation.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PackingListGenerationFB extends ActionForm
{
	private String patCRNo;
	private String hmode;
	private String currentPageNo="1";
	private String currentblock="1";
	public String pageString;
	private String seatId;
	public String sampleAreaName;
	public String sampleAreaCode;
	public static final String recordPerPage="10";
	public static final String pagesPerBlock="10";
	
	private String patStatus;
	
	private String searchLabName;
	private String searchTestName;
	
	private int currentPage;
	private int startIndex;
	private int endIndex;
	
	
	private String labTestCodeArray;
	
	private String[] chkSamplePatient;
	
	
	
	
	private String numberOfRow;
	public String selectedCheckbox;
	private String fromDate;
	private String toDate;
	private String sysDate;
	private String billDetail;
	private String requisitionDNo;
	
	private String labName;
	private String testName;
	private String isConfidential;
	private String priorityCode;
	private String priority;
	private String requisitionDate;
	
	private String reqDtlStatus;
	
	private String sampleCode;
	private String sampleName;
	private String requisitionNo;
	private String groupCode;
	private String groupType;
	private String[] sampleQnty;
	private String[] defaultContainerCode;
	
	private String[] defaultUOMCode;
	private String defaultSampelCode;
	private String sampleNoConfiguration;
	
	private String reqHeaderStatus;
	
	private String episodeCode;
	
	private String reqType;
	
	private String patAdmNo;
	
	private String patAddress;
	
	private String patWardCode;
	
	private String patMobile;
	private String patEmail;
	
	private String patRoomCode;
	
	private String patBedCode;
	
	private String patMlcNo;
	
	private String patDeptName;
	
	private String patDeptCode;
	
	private String patDeptUnitCode;
	
	
	private String showStatus;
	
	private String[] sampleNo;
	
	private String strSampleNo;
	
	private String isSampleAreaSelected;
	
	private String isCancel;
	
	private String labCode;
	
	private String packingListGenerationType;
	private String tempFromDate;
	private String tempToDate;
	private String machineCode;
	private String machineCodeName;
	private String flag;
	private String flagforipddesk;
	private String WardCode;
	private String diagnosis;

	
	public String getWardCode() {
		return WardCode;
	}

	public void setWardCode(String wardCode) {
		WardCode = wardCode;
	}

	private String[] chkPackListData;
	
	public String[] getChkPackListData() {
		return chkPackListData;
	}

	public void setChkPackListData(String[] chkPackListData) {
		this.chkPackListData = chkPackListData;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.currentPage=1;
	
		 
	}
	
	public String getPatCRNo() {
		return patCRNo;
	}
	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public String getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(String currentblock) {
		this.currentblock = currentblock;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		this.pageString = pageString;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getSampleAreaName() {
		return sampleAreaName;
	}
	public void setSampleAreaName(String sampleAreaName) {
		this.sampleAreaName = sampleAreaName;
	}
	public String getSampleAreaCode() {
		return sampleAreaCode;
	}
	public void setSampleAreaCode(String sampleAreaCode) {
		this.sampleAreaCode = sampleAreaCode;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getSearchLabName() {
		return searchLabName;
	}
	public void setSearchLabName(String searchLabName) {
		this.searchLabName = searchLabName;
	}
	public String getSearchTestName() {
		return searchTestName;
	}
	public void setSearchTestName(String searchTestName) {
		this.searchTestName = searchTestName;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getLabTestCodeArray() {
		return labTestCodeArray;
	}
	public void setLabTestCodeArray(String labTestCodeArray) {
		this.labTestCodeArray = labTestCodeArray;
	}
	public String getNumberOfRow() {
		return numberOfRow;
	}
	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}
	
	public String getSelectedCheckbox() {
		return selectedCheckbox;
	}
	public void setSelectedCheckbox(String selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
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
	public static String getRecordperpage() {
		return recordPerPage;
	}
	public static String getPagesperblock() {
		return pagesPerBlock;
	}
	public String getShowStatus() {
		return showStatus;
	}
	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}
	public String getBillDetail() {
		return billDetail;
	}
	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}
	public String getRequisitionDNo() {
		return requisitionDNo;
	}
	public void setRequisitionDNo(String requisitionDNo) {
		this.requisitionDNo = requisitionDNo;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getIsConfidential() {
		return isConfidential;
	}
	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getReqDtlStatus() {
		return reqDtlStatus;
	}
	public void setReqDtlStatus(String reqDtlStatus) {
		this.reqDtlStatus = reqDtlStatus;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getRequisitionNo() {
		return requisitionNo;
	}
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getDefaultSampelCode() {
		return defaultSampelCode;
	}
	public void setDefaultSampelCode(String defaultSampelCode) {
		this.defaultSampelCode = defaultSampelCode;
	}
	public String getSampleNoConfiguration() {
		return sampleNoConfiguration;
	}
	public void setSampleNoConfiguration(String sampleNoConfiguration) {
		this.sampleNoConfiguration = sampleNoConfiguration;
	}
	public String getReqHeaderStatus() {
		return reqHeaderStatus;
	}
	public void setReqHeaderStatus(String reqHeaderStatus) {
		this.reqHeaderStatus = reqHeaderStatus;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getPatWardCode() {
		return patWardCode;
	}
	public void setPatWardCode(String patWardCode) {
		this.patWardCode = patWardCode;
	}
	public String getPatMobile() {
		return patMobile;
	}
	public void setPatMobile(String patMobile) {
		this.patMobile = patMobile;
	}
	public String getPatEmail() {
		return patEmail;
	}
	public void setPatEmail(String patEmail) {
		this.patEmail = patEmail;
	}
	public String getPatRoomCode() {
		return patRoomCode;
	}
	public void setPatRoomCode(String patRoomCode) {
		this.patRoomCode = patRoomCode;
	}
	public String getPatBedCode() {
		return patBedCode;
	}
	public void setPatBedCode(String patBedCode) {
		this.patBedCode = patBedCode;
	}
	public String getPatMlcNo() {
		return patMlcNo;
	}
	public void setPatMlcNo(String patMlcNo) {
		this.patMlcNo = patMlcNo;
	}
	public String getPatDeptName() {
		return patDeptName;
	}
	public void setPatDeptName(String patDeptName) {
		this.patDeptName = patDeptName;
	}
	public String getPatDeptCode() {
		return patDeptCode;
	}
	public void setPatDeptCode(String patDeptCode) {
		this.patDeptCode = patDeptCode;
	}
	public String getPatDeptUnitCode() {
		return patDeptUnitCode;
	}
	public void setPatDeptUnitCode(String patDeptUnitCode) {
		this.patDeptUnitCode = patDeptUnitCode;
	}
	public String[] getDefaultContainerCode() {
		return defaultContainerCode;
	}
	public void setDefaultContainerCode(String[] defaultContainerCode) {
		this.defaultContainerCode = defaultContainerCode;
	}
	public String[] getDefaultUOMCode() {
		return defaultUOMCode;
	}
	public void setDefaultUOMCode(String[] defaultUOMCode) {
		this.defaultUOMCode = defaultUOMCode;
	}
	public String[] getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String[] sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String[] getSampleQnty() {
		return sampleQnty;
	}
	public void setSampleQnty(String[] sampleQnty) {
		this.sampleQnty = sampleQnty;
	}
	public String getStrSampleNo() {
		return strSampleNo;
	}
	public void setStrSampleNo(String strSampleNo) {
		this.strSampleNo = strSampleNo;
	}
	public String getIsSampleAreaSelected() {
		return isSampleAreaSelected;
	}
	public void setIsSampleAreaSelected(String isSampleAreaSelected) {
		this.isSampleAreaSelected = isSampleAreaSelected;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String[] getChkSamplePatient() {
		return chkSamplePatient;
	}
	public void setChkSamplePatient(String[] chkSamplePatient) {
		this.chkSamplePatient = chkSamplePatient;
	}
	public String getPackingListGenerationType() {
		return packingListGenerationType;
	}
	public void setPackingListGenerationType(String packingListGenerationType) {
		this.packingListGenerationType = packingListGenerationType;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getTempFromDate() {
		return tempFromDate;
	}
	public void setTempFromDate(String tempFromDate) {
		this.tempFromDate = tempFromDate;
	}
	public String getTempToDate() {
		return tempToDate;
	}
	public void setTempToDate(String tempToDate) {
		this.tempToDate = tempToDate;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getMachineCodeName() {
		return machineCodeName;
	}

	public void setMachineCodeName(String machineCodeName) {
		this.machineCodeName = machineCodeName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlagforipddesk() {
		return flagforipddesk;
	}

	public void setFlagforipddesk(String flagforipddesk) {
		this.flagforipddesk = flagforipddesk;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	
}
