package mortuary.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SampleSendToExtLabFB extends ActionForm
{
	private String hmode;
	private String deceasedNo;
	private String postmortemId;
	private String extLabId;
	private String policeStation;
	private String dutyOffName;
	private String dutyOffDesignation;
	private String dutyOffBatchNo;
	private String result;
	private String itemCode;
	private String receiveRemarks;
	private String labTestId;
	private String labTestRemrks;
	private String selectedItem[];
	private String deleteIndex;
	private String checkedItem;
	private String hiddenLabTestId;
	private String hiddenLabTestName;
	private String postmortemType;
	

	public String getPostmortemType() {
		return postmortemType;
	}
	public void setPostmortemType(String postmortemType) {
		this.postmortemType = postmortemType;
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
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getDutyOffName() {
		return dutyOffName;
	}
	public void setDutyOffName(String dutyOffName) {
		this.dutyOffName = dutyOffName;
	}
	public String getDutyOffDesignation() {
		return dutyOffDesignation;
	}
	public void setDutyOffDesignation(String dutyOffDesignation) {
		this.dutyOffDesignation = dutyOffDesignation;
	}
	public String getDutyOffBatchNo() {
		return dutyOffBatchNo;
	}
	public void setDutyOffBatchNo(String dutyOffBatchNo) {
		this.dutyOffBatchNo = dutyOffBatchNo;
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
	public String getReceiveRemarks() {
		return receiveRemarks;
	}
	public void setReceiveRemarks(String receiveRemarks) {
		this.receiveRemarks = receiveRemarks;
	}
	public String getLabTestId() {
		return labTestId;
	}
	public void setLabTestId(String labTestId) {
		this.labTestId = labTestId;
	}
	public String[] getSelectedItem() {
		return selectedItem;
	}
	public void setSelectedItem(String[] selectedItem) {
		this.selectedItem = selectedItem;
	}
	public String getLabTestRemrks() {
		return labTestRemrks;
	}
	public void setLabTestRemrks(String labTestRemrks) {
		this.labTestRemrks = labTestRemrks;
	}
	public String getDeleteIndex() {
		return deleteIndex;
	}
	public void setDeleteIndex(String deleteIndex) {
		this.deleteIndex = deleteIndex;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setExtLabId("-1");
		this.setPoliceStation("");
		this.setDutyOffName("");
		this.setDutyOffDesignation("");
		this.setDutyOffBatchNo("");
		this.setCheckedItem("");
	}
	
	public void resetRowAdd(ActionMapping mapping,HttpServletRequest request)
	{
		this.setLabTestId("-1");
		this.setLabTestRemrks("");
	}
	public String getCheckedItem() {
		return checkedItem;
	}
	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}
	public String getHiddenLabTestId() {
		return hiddenLabTestId;
	}
	public void setHiddenLabTestId(String hiddenLabTestId) {
		this.hiddenLabTestId = hiddenLabTestId;
	}
	public String getHiddenLabTestName() {
		return hiddenLabTestName;
	}
	public void setHiddenLabTestName(String hiddenLabTestName) {
		this.hiddenLabTestName = hiddenLabTestName;
	}
}
