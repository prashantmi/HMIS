package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DoctorCallBookFB extends ActionForm

{
	private String acceptSeatId;
	private String hospitalCode;
	private String isValid;
	private String raiseSeatId;
	private String callRemarks;
	private String doctorRemarks;
	private String callAckTime;
	private String callAttendTime;
	private String callDate;
	private String callRaiseTime;
	private String callType;
	private String docCallByPeonStatus;
	private String docCallByPhoneStatus;
	private String isDocCallByPeon;
	private String isDocCallbyPhone;
	private String wardCode;
	private String docCallByPeonRemarks;
	private String docCallByPhoneRemarks;
	private String patAdmnNo;
	private String callNo;
	private String patCrNo;
	private String selectedPatCrNo;
	private String empNo;
	private String peonEmpNo;
	private String raiseByEmpNo;
	private String hmode;
	private String[] chk;
	private String unitCode;
	private String callPriority;
	private String callPriorityName;
	private String consultantName;
	private String callDateFormat;
	private int currentPage;
	private String statusArray[];
	private String status;
	private String index;
	private String processId;
	private String macroHead;
	private String deskMenuId;
	
	public String getMacroHead() {
		return macroHead;
	}

	public void setMacroHead(String macroHead) {
		this.macroHead = macroHead;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getStatusArray() {
		return statusArray;
	}

	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}

	public DoctorCallBookFB(){
		this.currentPage=1;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getCallDateFormat() {
		return callDateFormat;
	}
	public void setCallDateFormat(String callDateFormat) {
		this.callDateFormat = callDateFormat;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getAcceptSeatId() {
		return acceptSeatId;
	}
	public void setAcceptSeatId(String acceptSeatId) {
		this.acceptSeatId = acceptSeatId;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getRaiseSeatId() {
		return raiseSeatId;
	}
	public void setRaiseSeatId(String raiseSeatId) {
		this.raiseSeatId = raiseSeatId;
	}
	public String getCallRemarks() {
		return callRemarks;
	}
	public void setCallRemarks(String callRemarks) {
		this.callRemarks = callRemarks;
	}
	public String getDoctorRemarks() {
		return doctorRemarks;
	}
	public void setDoctorRemarks(String doctorRemarks) {
		this.doctorRemarks = doctorRemarks;
	}
	public String getCallAckTime() {
		return callAckTime;
	}
	public void setCallAckTime(String callAckTime) {
		this.callAckTime = callAckTime;
	}
	public String getCallAttendTime() {
		return callAttendTime;
	}
	public void setCallAttendTime(String callAttendTime) {
		this.callAttendTime = callAttendTime;
	}
	public String getCallDate() {
		return callDate;
	}
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	public String getCallRaiseTime() {
		return callRaiseTime;
	}
	public void setCallRaiseTime(String callRaiseTime) {
		this.callRaiseTime = callRaiseTime;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getDocCallByPeonStatus() {
		return docCallByPeonStatus;
	}
	public void setDocCallByPeonStatus(String docCallByPeonStatus) {
		this.docCallByPeonStatus = docCallByPeonStatus;
	}
	public String getDocCallByPhoneStatus() {
		return docCallByPhoneStatus;
	}
	public void setDocCallByPhoneStatus(String docCallByPhoneStatus) {
		this.docCallByPhoneStatus = docCallByPhoneStatus;
	}
	public String getIsDocCallByPeon() {
		return isDocCallByPeon;
	}
	public void setIsDocCallByPeon(String isDocCallByPeon) {
		this.isDocCallByPeon = isDocCallByPeon;
	}
	public String getIsDocCallbyPhone() {
		return isDocCallbyPhone;
	}
	public void setIsDocCallbyPhone(String isDocCallbyPhone) {
		this.isDocCallbyPhone = isDocCallbyPhone;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getDocCallByPeonRemarks() {
		return docCallByPeonRemarks;
	}
	public void setDocCallByPeonRemarks(String docCallByPeonRemarks) {
		this.docCallByPeonRemarks = docCallByPeonRemarks;
	}
	public String getDocCallByPhoneRemarks() {
		return docCallByPhoneRemarks;
	}
	public void setDocCallByPhoneRemarks(String docCallByPhoneRemarks) {
		this.docCallByPhoneRemarks = docCallByPhoneRemarks;
	}
	
	public String getPatAdmnNo() {
		return patAdmnNo;
	}
	public void setPatAdmnNo(String patAdmnNo) {
		this.patAdmnNo = patAdmnNo;
	}
	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getPeonEmpNo() {
		return peonEmpNo;
	}
	public void setPeonEmpNo(String peonEmpNo) {
		this.peonEmpNo = peonEmpNo;
	}
	public String getRaiseByEmpNo() {
		return raiseByEmpNo;
	}
	public void setRaiseByEmpNo(String raiseByEmpNo) {
		this.raiseByEmpNo = raiseByEmpNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.isDocCallByPeon="off";
		this.isDocCallbyPhone="off";
		this.callRemarks="";
		this.docCallByPeonRemarks="";
		this.docCallByPhoneRemarks="";
		this.peonEmpNo="";
		this.empNo="-1";
		
		
	}
	public String getCallPriority() {
		return callPriority;
	}
	public void setCallPriority(String callPriority) {
		this.callPriority = callPriority;
	}
	public String getSelectedPatCrNo() {
		return selectedPatCrNo;
	}
	public void setSelectedPatCrNo(String selectedPatCrNo) {
		this.selectedPatCrNo = selectedPatCrNo;
	}
	public String getCallPriorityName() {
		return callPriorityName;
	}
	public void setCallPriorityName(String callPriorityName) {
		this.callPriorityName = callPriorityName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	

}
