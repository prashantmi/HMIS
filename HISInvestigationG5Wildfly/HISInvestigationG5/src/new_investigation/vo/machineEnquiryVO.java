package new_investigation.vo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.InvestigationConfig;
import new_investigation.vo.template.ResultEntryVO;

import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import hisglobal.vo.ValueObject;



public class machineEnquiryVO extends ValueObject
{
	                    
	private String seatId;
	
	private String patCrNo;
	private int startIndex;
	private int endIndex;
	private String resultArray;
	private String testCode;
	private String labCode;
	private String machineCode;
	private String record;
	private String fromDate="";
	private String toDate="";
	private String reqDNo;
	private String parameterCode;
	private String labNo;
	private String machineTestParameterCode;
	private String machineTestParameterName;
	private String machineResult;
	private String machineResultEntryDate;
	private String machineLabSampleNo;
	private String machineRecordId;
	private String machineReqId;
	private String reqDtlStatus;
	private String parentId;
	private String machineTestParameterParaCount;
	private String sampleStatus;
	private String resultStatus;
	private String machineSampleNo;
	private String testStatus;
	private String patName;
	private String testName;
	
	
	public String getReqDNo() {
		return reqDNo;
	}
	public void setReqDNo(String reqDNo) {
		this.reqDNo = reqDNo;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getResultArray() {
		return resultArray;
	}
	public void setResultArray(String resultArray) {
		this.resultArray = resultArray;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getLabNo() {
		return labNo;
	}
	public void setLabNo(String labNo) {
		this.labNo = labNo;
	}
	public String getMachineTestParameterCode() {
		return machineTestParameterCode;
	}
	public void setMachineTestParameterCode(String machineTestParameterCode) {
		this.machineTestParameterCode = machineTestParameterCode;
	}
	public String getMachineTestParameterName() {
		return machineTestParameterName;
	}
	public void setMachineTestParameterName(String machineTestParameterName) {
		this.machineTestParameterName = machineTestParameterName;
	}
	public String getMachineResult() {
		return machineResult;
	}
	public void setMachineResult(String machineResult) {
		this.machineResult = machineResult;
	}
	public String getMachineResultEntryDate() {
		return machineResultEntryDate;
	}
	public void setMachineResultEntryDate(String machineResultEntryDate) {
		this.machineResultEntryDate = machineResultEntryDate;
	}
	public String getMachineLabSampleNo() {
		return machineLabSampleNo;
	}
	public void setMachineLabSampleNo(String machineLabSampleNo) {
		this.machineLabSampleNo = machineLabSampleNo;
	}
	public String getMachineRecordId() {
		return machineRecordId;
	}
	public void setMachineRecordId(String machineRecordId) {
		this.machineRecordId = machineRecordId;
	}
	public String getMachineReqId() {
		return machineReqId;
	}
	public void setMachineReqId(String machineReqId) {
		this.machineReqId = machineReqId;
	}
	public String getReqDtlStatus() {
		return reqDtlStatus;
	}
	public void setReqDtlStatus(String reqDtlStatus) {
		this.reqDtlStatus = reqDtlStatus;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getmachineTestParameterParaCount() {
		return machineTestParameterParaCount;
	}
	public void setmachineTestParameterParaCount(
			String machineTestParameterParaCount) {
		this.machineTestParameterParaCount = machineTestParameterParaCount;
	}
	public String getSampleStatus() {
		return sampleStatus;
	}
	public void setSampleStatus(String sampleStatus) {
		this.sampleStatus = sampleStatus;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getMachineSampleNo() {
		return machineSampleNo;
	}
	public void setMachineSampleNo(String machineSampleNo) {
		this.machineSampleNo = machineSampleNo;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
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
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}

	
	


}

