package mrd.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class RecordTypeCheckListMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String slNo;
	private String controls[];
	private String recordTypeName;
	private String recordTypeId;
	private String checkListMode;
	
	private String[] checkListID;
	private String[] selectedCheckListID;
	private String[] isCompulsory;
	private String concatedIndex;
	
	
	public String getConcatedIndex() {
		return concatedIndex;
	}

	public void setConcatedIndex(String concatedIndex) {
		this.concatedIndex = concatedIndex;
	}

	

	public String getCheckListMode() {
		return checkListMode;
	}

	public void setCheckListMode(String checkListMode) {
		this.checkListMode = checkListMode;
	}

	

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public RecordTypeCheckListMstFB()
	{
		this.controls=new String[1];
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String[] getCheckListID() {
		return checkListID;
	}

	public void setCheckListID(String[] checkListID) {
		this.checkListID = checkListID;
	}

	public String[] getSelectedCheckListID() {
		return selectedCheckListID;
	}

	public void setSelectedCheckListID(String[] selectedCheckListID) {
		this.selectedCheckListID = selectedCheckListID;
	}

	public String[] getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(String[] isCompulsory) {
		this.isCompulsory = isCompulsory;
	}
}
