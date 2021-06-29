package mortuary.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class SampleResultEntryFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String postmortemId;
	private String extLabId;
	private String result;
	private String itemCode;
	private String receiveRemarks[];
	private String labTestRemrks;
	private String selectedRequestId;
	private String selectedReturnedItem[];
	private String labTestResults[];
	private String labTestId[];
	private String finalResult;
	private String requestId;
	private String labTestName[];
	
	//Search
	private String searchFName;
	private String searchMName;
	private String searchLName;
	private String deathDate;
	private String selectedPostmortemNo;
	
	
	public String getSelectedRequestId() {
		return selectedRequestId;
	}
	public void setSelectedRequestId(String selectedRequestId) {
		this.selectedRequestId = selectedRequestId;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getExtLabId() {
		return extLabId;
	}
	public void setExtLabId(String extLabId) {
		this.extLabId = extLabId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getLabTestRemrks() {
		return labTestRemrks;
	}
	public void setLabTestRemrks(String labTestRemrks) {
		this.labTestRemrks = labTestRemrks;
	}
	public String[] getSelectedReturnedItem() {
		return selectedReturnedItem;
	}
	public void setSelectedReturnedItem(String[] selectedReturnedItem) {
		this.selectedReturnedItem = selectedReturnedItem;
	}
	public String[] getReceiveRemarks() {
		return receiveRemarks;
	}
	public void setReceiveRemarks(String[] receiveRemarks) {
		this.receiveRemarks = receiveRemarks;
	}
	public String[] getLabTestResults() {
		return labTestResults;
	}
	public void setLabTestResults(String[] labTestResults) {
		this.labTestResults = labTestResults;
	}
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public void setLabTestId(String[] labTestId) {
		this.labTestId = labTestId;
	}
	public String[] getLabTestId() {
		return labTestId;
	}
	public String[] getLabTestName() {
		return labTestName;
	}
	public void setLabTestName(String[] labTestName) {
		this.labTestName = labTestName;
	}
	public String getSearchFName() {
		return searchFName;
	}
	public void setSearchFName(String searchFName) {
		this.searchFName = searchFName;
	}
	public String getSearchMName() {
		return searchMName;
	}
	public void setSearchMName(String searchMName) {
		this.searchMName = searchMName;
	}
	public String getSearchLName() {
		return searchLName;
	}
	public void setSearchLName(String searchLName) {
		this.searchLName = searchLName;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getSelectedPostmortemNo() {
		return selectedPostmortemNo;
	}
	public void setSelectedPostmortemNo(String selectedPostmortemNo) {
		this.selectedPostmortemNo = selectedPostmortemNo;
	}
}
