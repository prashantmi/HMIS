package opd.master.controller.fb;

import hisglobal.vo.DepartmentIcdMasterVO;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class DepartmentIcdMasterFB extends ActionForm {
	
	private String departmentCode;
	private String departmentUnitCode;
	private String diseaseCode;
	private String icdSubgroupCode;
	private String icdGroupCode;
	private String seatID;
	private String entryDate;
	private String isValid;
	private String choice;
	private String hmode;
	private String icdType;
	private String selectedCheckbox[];
	private String selectedRecord[];
	public static String recordPerPage="25";
	public static String pagesPerBlock="8";
	public static String pageString;
	public static String currentPageNo;
	public static String currentblock;
	public List displayList;
	public DepartmentIcdMasterVO displayVO[];
	public String searchKey;
	public String searchType;
	private String valueChoice;
	private String numberOfSelection="0";
	private String controls[];
	
	
	public DepartmentIcdMasterFB()
	{
		this.controls = new String[1];
	}
	
	
	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getNumberOfSelection() {
		return numberOfSelection;
	}

	public void setNumberOfSelection(String numberOfSelection) {
		this.numberOfSelection = numberOfSelection;
	}

	public String getValueChoice() {
		return valueChoice;
	}

	public void setValueChoice(String valueChoice) {
		this.valueChoice = valueChoice;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setSelectedCheckbox(String[] selectedCheckbox) {
		this.selectedCheckbox = selectedCheckbox;
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getIcdType() {
		return icdType;
	}
	public void setIcdType(String icdType) {
		this.icdType = icdType;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getIcdGroupCode() {
		return icdGroupCode;
	}
	public void setIcdGroupCode(String icdGroupCode) {
		this.icdGroupCode = icdGroupCode;
	}
	public String getIcdSubgroupCode() {
		return icdSubgroupCode;
	}
	public void setIcdSubgroupCode(String icdSubgroupCode) {
		this.icdSubgroupCode = icdSubgroupCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	public String[] getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(String[] selectedRecord) {
		this.selectedRecord = selectedRecord;
	}
	
	public String getPagesPerBlock() {
		return pagesPerBlock;
	}
	public void setPagesPerBlock(String pagesPerBlock) {
		DepartmentIcdMasterFB.pagesPerBlock = pagesPerBlock;
	}
	public String getPageString() {
		return pageString;
	}
	public void setPageString(String pageString) {
		DepartmentIcdMasterFB.pageString = pageString;
	}
	public String getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(String recordPerPage) {
		DepartmentIcdMasterFB.recordPerPage = recordPerPage;
	}
	public String getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(String currentblock) {
		DepartmentIcdMasterFB.currentblock = currentblock;
	}
	public String getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(String currentPageNo) {
		DepartmentIcdMasterFB.currentPageNo = currentPageNo;
	}
	public List getDisplayList() {
		return displayList;
	}
	public void setDisplayList(List displayList) {
		this.displayList = displayList;
	}

	public String[] getSelectedCheckbox() {
		return selectedCheckbox;
	}

	public DepartmentIcdMasterVO[] getDisplayVO() {
		return displayVO;
	}

	public void setDisplayVO(DepartmentIcdMasterVO[] displayVO) {
		this.displayVO = displayVO;
	}

	

}
