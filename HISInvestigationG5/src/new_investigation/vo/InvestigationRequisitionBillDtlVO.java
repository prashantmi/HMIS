package new_investigation.vo;

import java.util.List;

import hisglobal.vo.ValueObject;

public class InvestigationRequisitionBillDtlVO extends ValueObject {
 String requisitionNos="";
 String requisitionType="";
 List<String> tariffDetails;
 List<String> tariffQty;
 List<String> grouptariffDetails;
 List<String> grouptariffQty;
 String deptCode="";
 List<String> viewcode;

 private String tariffId;
 private String serviceId;
 private String billNo;
 private String consQty;
 
 private String raiseAdvise;
 
 List<String> reqdno;
 List<String> reqno;

 
 
public String getRequisitionNos() {
	return requisitionNos;
}
public void setRequisitionNos(String requisitionNos) {
	this.requisitionNos = requisitionNos;
}
public String getRequisitionType() {
	return requisitionType;
}
public void setRequisitionType(String requisitionType) {
	this.requisitionType = requisitionType;
}
public List<String> getTariffDetails() {
	return tariffDetails;
}
public void setTariffDetails(List<String> tariffDetails) {
	this.tariffDetails = tariffDetails;
}
public String getDeptCode() {
	return deptCode;
}
public void setDeptCode(String deptCode) {
	this.deptCode = deptCode;
}
public List<String> getGrouptariffDetails() {
	return grouptariffDetails;
}
public void setGrouptariffDetails(List<String> grouptariffDetails) {
	this.grouptariffDetails = grouptariffDetails;
}
public List<String> getTariffQty() {
	return tariffQty;
}
public void setTariffQty(List<String> tariffQty) {
	this.tariffQty = tariffQty;
}
public List<String> getGrouptariffQty() {
	return grouptariffQty;
}
public void setGrouptariffQty(List<String> grouptariffQty) {
	this.grouptariffQty = grouptariffQty;
}
public String getTariffId() {
	return tariffId;
}
public void setTariffId(String tariffId) {
	this.tariffId = tariffId;
}
public String getServiceId() {
	return serviceId;
}
public void setServiceId(String serviceId) {
	this.serviceId = serviceId;
}
public String getBillNo() {
	return billNo;
}
public void setBillNo(String billNo) {
	this.billNo = billNo;
}
public String getConsQty() {
	return consQty;
}
public void setConsQty(String consQty) {
	this.consQty = consQty;
}
public String getRaiseAdvise() {
	return raiseAdvise;
}
public void setRaiseAdvise(String raiseAdvise) {
	this.raiseAdvise = raiseAdvise;
}
public List<String> getViewcode() {
	return viewcode;
}
public void setViewcode(List<String> viewcode) {
	this.viewcode = viewcode;
}
public List<String> getReqdno() {
	return reqdno;
}
public void setReqdno(List<String> reqdno) {
	this.reqdno = reqdno;
}
public List<String> getReqno() {
	return reqno;
}
public void setReqno(List<String> reqno) {
	this.reqno = reqno;
}
 


}
