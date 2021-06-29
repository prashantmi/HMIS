package hisglobal.vo;

import hisglobal.vo.Test;

import java.util.List;

public class Requisition extends ValueObject
{
	private String requisitionNo;
	private String requisitionDate;
	private String raisingArea;
	private String remarks;
	private String requisitionType;
	private List<Test> workOrder;
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
	public String getRaisingArea() {
		return raisingArea;
	}
	public void setRaisingArea(String raisingArea) {
		this.raisingArea = raisingArea;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRequisitionType() {
		return requisitionType;
	}
	public void setRequisitionType(String requisitionType) {
		this.requisitionType = requisitionType;
	}
	public List<Test> getWorkOrder() {
		return workOrder;
	}
	public void setWorkOrder(List<Test> workOrder) {
		this.workOrder = workOrder;
	}    
}
