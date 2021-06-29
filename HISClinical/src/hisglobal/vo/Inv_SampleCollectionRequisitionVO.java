package hisglobal.vo;

import investigation.vo.Sample;
import investigation.vo.SampleTest;

import java.util.List;


public class Inv_SampleCollectionRequisitionVO extends ValueObject {
	
List<Sample> sampleObject;

String requisitionNo;
String requisitionDate;
private String labCode;
private String labName;
private String sessionNo;
private String errorMsg;
public List<Sample> getSampleObject() {
	return sampleObject;
}
public void setSampleObject(List<Sample> sampleObject) {
	this.sampleObject = sampleObject;
}
public String getRequisitionNo() {
	return requisitionNo;
}
public void setRequisitionNo(String requisitionNo) {
	this.requisitionNo = requisitionNo;
}
public String getRequisitionDate() {
	return requisitionDate;
}
public void setRequisitionDate(String requisitionDate) {
	this.requisitionDate = requisitionDate;
}

public String getLabName() {
	return labName;
}
public void setLabName(String labName) {
	this.labName = labName;
}
public String getLabCode() {
	return labCode;
}
public void setLabCode(String labCode) {
	this.labCode = labCode;
}
public String getSessionNo() {
	return sessionNo;
}
public void setSessionNo(String sessionNo) {
	this.sessionNo = sessionNo;
}
public String getErrorMsg() {
	return errorMsg;
}
public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}


}
