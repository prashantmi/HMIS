package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class InpatientBulletinDetailFB  extends CRNoFB{

	private String hmode;
	private String patStatusCode;
	private String patRemarks;	
	private String patCrNo;
	private String patRevoke;
	private String transactionMode;
	private String patStatusName;
	private String processId;
	private String macroHead;
	private String selectedIndex[];
	private String isDirectCall;
	private String sortOn;
	private String order;
	
	public String getSortOn() {
		return sortOn;
	}
	public void setSortOn(String sortOn) {
		this.sortOn = sortOn;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public void reset(ActionMapping mapping,HttpServletRequest request){
		
		super.reset(mapping, request);
		 this.setPatRemarks("");
		 this.setPatStatusCode("-1");
	     
	}
	public String getPatStatusName() {
		return patStatusName;
	}

	public void setPatStatusName(String patStatusName) {
		this.patStatusName = patStatusName;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getPatRevoke() {
		return patRevoke;
	}

	public void setPatRevoke(String patRevoke) {
		this.patRevoke = patRevoke;
	}

	public String getPatStatusCode() {
		return patStatusCode;
	}

	public void setPatStatusCode(String patStatusCode) {
		this.patStatusCode = patStatusCode;
	}

	public String getPatRemarks() {
		return patRemarks;
	}

	public void setPatRemarks(String patRemarks) {
		this.patRemarks = patRemarks;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getMacroHead() {
		return macroHead;
	}
	public void setMacroHead(String macroHead) {
		this.macroHead = macroHead;
	}

	public String[] getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String [] selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

}
