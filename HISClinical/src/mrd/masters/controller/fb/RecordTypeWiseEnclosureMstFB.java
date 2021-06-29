package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RecordTypeWiseEnclosureMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String serialNo;
	private String controls[];
	
	private String enclosureId;
	private String recordTypeId;
	private String recordTypeName;
	private String[] isCompulsory;
	private String remarks;
	private String enclosure;
	private String hospitalCode;
	private String isValid;
	private String hiddenControl;
	private String[] enclosureID;
	private String[] selectedEnclosureID;
	private String concatedIndex;
	private String[] hiddenEncId;
	private String[] hiddenEnclosure;
	
	
	public String getHiddenControl() {
		return hiddenControl;
	}

	public void setHiddenControl(String hiddenControl) {
		this.hiddenControl = hiddenControl;
	}

	public RecordTypeWiseEnclosureMstFB()
	{
		controls=new String[1];
	}
		
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void reset(ActionMapping mapping,HttpServletRequest _request)
	{
		this.enclosure="";
		//this.recordTypeId="-1";
		this.remarks="";
	//	this.isCompulsory="-1";
	
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getEnclosureId() {
		return enclosureId;
	}

	public void setEnclosureId(String enclosureId) {
		this.enclosureId = enclosureId;
	}



	public String getRecordTypeId() {
		return recordTypeId;
	}

	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public String[] getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(String[] isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	public String getConcatedIndex() {
		return concatedIndex;
	}

	public void setConcatedIndex(String concatedIndex) {
		this.concatedIndex = concatedIndex;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String[] getEnclosureID() {
		return enclosureID;
	}

	public void setEnclosureID(String[] enclosureID) {
		this.enclosureID = enclosureID;
	}

	public String[] getSelectedEnclosureID() {
		return selectedEnclosureID;
	}

	public void setSelectedEnclosureID(String[] selectedEnclosureID) {
		this.selectedEnclosureID = selectedEnclosureID;
	}

	public String[] getHiddenEncId() {
		return hiddenEncId;
	}

	public void setHiddenEncId(String[] hiddenEncId) {
		this.hiddenEncId = hiddenEncId;
	}

	public String[] getHiddenEnclosure() {
		return hiddenEnclosure;
	}

	public void setHiddenEnclosure(String[] hiddenEnclosure) {
		this.hiddenEnclosure = hiddenEnclosure;
	}
	

}
