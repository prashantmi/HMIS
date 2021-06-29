package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateArchivalFB extends ActionForm
{
	private String hmode;
	private String recordType;
	private String recordTypeName;
	private String mrdCode;
	private String rackId;
	private String shelfId;
	private String recordId;
	private String mrdRecordId;
	private String putBy;
	private String[] selectedRecord;
	private String tempSelectedChk;
	private String rackInfo;
	private String deskmode;
	private String  strCheckMode;//added by swati sagar on date:10-05-2019
	private String  strCrNo;//added by swati sagar on date:10-05-2019	
	private String wardCodeNew;//added by swati sagar on date:10-05-2019
	private String clearmsg;//added by swati sagar on date:10-05-2019
	private String patAdmNo;//added by swati sagar on date:10-05-2019
	
	public String getRackInfo() {
		return rackInfo;
	}
	public void setRackInfo(String rackInfo) {
		this.rackInfo = rackInfo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
	}
	public String[] getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(String[] selectedRecord) {
		this.selectedRecord = selectedRecord;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRackId("");
		this.setShelfId("");
		this.setPutBy("");
	}
	public String getTempSelectedChk() {
		return tempSelectedChk;
	}
	public void setTempSelectedChk(String tempSelectedChk) {
		this.tempSelectedChk = tempSelectedChk;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getDeskmode() {
		return deskmode;
	}
	public void setDeskmode(String deskmode) {
		this.deskmode = deskmode;
	}
	public String getStrCheckMode() {
		return strCheckMode;
	}
	public void setStrCheckMode(String strCheckMode) {
		this.strCheckMode = strCheckMode;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getWardCodeNew() {
		return wardCodeNew;
	}
	public void setWardCodeNew(String wardCodeNew) {
		this.wardCodeNew = wardCodeNew;
	}
	public String getClearmsg() {
		return clearmsg;
	}
	public void setClearmsg(String clearmsg) {
		this.clearmsg = clearmsg;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
}
