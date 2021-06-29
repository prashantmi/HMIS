package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class BirthRegistrationUploadFB extends ActionForm
{
	private String hmode;
	private String selectedChild;
	private String registrtionNo;
	private String remarks;
	private String relativeName;
	private String relativeAddress;
	private String relativeCode;
	private String entryMode;
	private String handoverDateTime;
	private String isHandoverTo;
	private String statusCode;
	private String isPrint;
	private String patCrNo;
	
	//search
	private String searchChildCrNo;
	private String searchMomCrNo;
	private String searchMomFName;
	private String searchMomMName;
	private String searchMomLName;
	private String searchChildDob;
	private String searchType;
	private String isFromSearch;
	private String selectedCrNo;
	
	
	public String getSelectedCrNo() {
		return selectedCrNo;
	}
	public void setSelectedCrNo(String selectedCrNo) {
		this.selectedCrNo = selectedCrNo;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getIsHandoverTo() {
		return isHandoverTo;
	}
	public void setIsHandoverTo(String isHandoverTo) {
		this.isHandoverTo = isHandoverTo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSelectedChild() {
		return selectedChild;
	}
	public void setSelectedChild(String selectedChild) {
		this.selectedChild = selectedChild;
	}
	public String getRegistrtionNo() {
		return registrtionNo;
	}
	public void setRegistrtionNo(String registrtionNo) {
		this.registrtionNo = registrtionNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getHandoverDateTime() {
		return handoverDateTime;
	}
	public void setHandoverDateTime(String handoverDateTime) {
		this.handoverDateTime = handoverDateTime;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getSearchChildCrNo() {
		return searchChildCrNo;
	}
	public void setSearchChildCrNo(String searchChildCrNo) {
		this.searchChildCrNo = searchChildCrNo;
	}
	public String getSearchMomCrNo() {
		return searchMomCrNo;
	}
	public void setSearchMomCrNo(String searchMomCrNo) {
		this.searchMomCrNo = searchMomCrNo;
	}
	public String getSearchMomFName() {
		return searchMomFName;
	}
	public void setSearchMomFName(String searchMomFName) {
		this.searchMomFName = searchMomFName;
	}
	public String getSearchMomMName() {
		return searchMomMName;
	}
	public void setSearchMomMName(String searchMomMName) {
		this.searchMomMName = searchMomMName;
	}
	public String getSearchMomLName() {
		return searchMomLName;
	}
	public void setSearchMomLName(String searchMomLName) {
		this.searchMomLName = searchMomLName;
	}
	public String getSearchChildDob() {
		return searchChildDob;
	}
	public void setSearchChildDob(String searchChildDob) {
		this.searchChildDob = searchChildDob;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getIsFromSearch() {
		return isFromSearch;
	}
	public void setIsFromSearch(String isFromSearch) {
		this.isFromSearch = isFromSearch;
	}
	
}
