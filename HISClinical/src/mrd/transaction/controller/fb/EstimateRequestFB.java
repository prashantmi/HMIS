package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

public class EstimateRequestFB extends ActionForm {

	private String patCrNo;
	private String episodeCode;
	private String isDirectCall;
	private String departmentUnitCode;
	private String remarks;
	private String advisedBy;
	private String tarrifId;
	private String episodeVisitNo;
	private String admissionNo;
	private String hmode;
	private String wardCode;
	private String tariffName;
	private String requestedDate;
	private String requestStatus;
	private String patCatcode;
	private String strCategoryCode;
	
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setTarrifId("");
		this.setRemarks("");
		this.setAdvisedBy("-1");
	}	
	public String getPatCrNo() {	
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAdvisedBy() {
		return advisedBy;
	}
	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}
	public String getTarrifId() {
		return tarrifId;
	}
	public void setTarrifId(String tarrifId) {
		this.tarrifId = tarrifId;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	public String getRequestedDate() {
		return requestedDate;
	}
	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getPatCatcode() {
		return patCatcode;
	}
	public void setPatCatcode(String patCatcode) {
		this.patCatcode = patCatcode;
	}
	public String getStrCategoryCode() {
		return strCategoryCode;
	}
	public void setStrCategoryCode(String strCategoryCode) {
		this.strCategoryCode = strCategoryCode;
	}

}
