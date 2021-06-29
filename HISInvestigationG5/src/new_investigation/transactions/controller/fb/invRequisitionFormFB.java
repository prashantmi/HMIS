package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class invRequisitionFormFB extends ActionForm {

private String hmode;
private String testCode;
private String testName;
private String labCode;
private String labName;
private String remarks;
private String status;
private String advice;
private String requisitionDNo;;
private String resultEntryTemplateValueWithHash;
private String reqformtestnames;
private String reqformtestcodes;



public String getHmode() {
	return hmode;
}
public void setHmode(String hmode) {
	this.hmode = hmode;
}
public String getTestCode() {
	return testCode;
}
public void setTestCode(String testCode) {
	this.testCode = testCode;
}
public String getTestName() {
	return testName;
}
public void setTestName(String testName) {
	this.testName = testName;
}
public String getLabCode() {
	return labCode;
}
public void setLabCode(String labCode) {
	this.labCode = labCode;
}
public String getLabName() {
	return labName;
}
public void setLabName(String labName) {
	this.labName = labName;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getAdvice() {
	return advice;
}
public void setAdvice(String advice) {
	this.advice = advice;
}
public String getRequisitionDNo() {
	return requisitionDNo;
}
public void setRequisitionDNo(String requisitionDNo) {
	this.requisitionDNo = requisitionDNo;
}
public String getResultEntryTemplateValueWithHash() {
	return resultEntryTemplateValueWithHash;
}
public void setResultEntryTemplateValueWithHash(
		String resultEntryTemplateValueWithHash) {
	this.resultEntryTemplateValueWithHash = resultEntryTemplateValueWithHash;
}
public String getReqformtestnames() {
	return reqformtestnames;
}
public void setReqformtestnames(String reqformtestnames) {
	this.reqformtestnames = reqformtestnames;
}
public String getReqformtestcodes() {
	return reqformtestcodes;
}
public void setReqformtestcodes(String reqformtestcodes) {
	this.reqformtestcodes = reqformtestcodes;
}



}
