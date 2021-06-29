package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class StockEntryOfBloodFB extends CRNoFB 
{
	private String hmode;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String bloodBankName;
	private String requisitionNo;
	private String[] batchNo;
	private String[] tubingNo;
	private String[] expiryDate;
	private String[] bloodBagNo;
	private String[] bloodComponentID;
	private String[] bloodAbo;
	private String[] rh;
	private String[] volume;
	private String bloodBankAddr;
	private String contactNo;
	private String componentCombo;
	private String aboCombo;
	private String rhCombo;
	private String numberOfRow;
	private String patDeathStatus;
	
	public String getPatDeathStatus() {
		return patDeathStatus;
	}

	public void setPatDeathStatus(String patDeathStatus) {
		this.patDeathStatus = patDeathStatus;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.requisitionNo="";
		this.bloodComponentID=null;
		this.bloodAbo=null;
		this.rh=null;
	}
	
	public String getNumberOfRow() {
		return numberOfRow;
	}

	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}

	public String getComponentCombo() {
		return componentCombo;
	}

	public void setComponentCombo(String componentCombo) {
		this.componentCombo = componentCombo;
	}

	public String getAboCombo() {
		return aboCombo;
	}

	public void setAboCombo(String aboCombo) {
		this.aboCombo = aboCombo;
	}

	public String getRhCombo() {
		return rhCombo;
	}

	public void setRhCombo(String rhCombo) {
		this.rhCombo = rhCombo;
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
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

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getBloodBankAddr() {
		return bloodBankAddr;
	}

	public void setBloodBankAddr(String bloodBankAddr) {
		this.bloodBankAddr = bloodBankAddr;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String[] getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String[] expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String[] getBloodBagNo() {
		return bloodBagNo;
	}

	public void setBloodBagNo(String[] bloodBagNo) {
		this.bloodBagNo = bloodBagNo;
	}

	public String[] getBloodComponentID() {
		return bloodComponentID;
	}

	public void setBloodComponentID(String[] bloodComponentID) {
		this.bloodComponentID = bloodComponentID;
	}

	public String[] getBloodAbo() {
		return bloodAbo;
	}

	public void setBloodAbo(String[] bloodAbo) {
		this.bloodAbo = bloodAbo;
	}

	public String[] getRh() {
		return rh;
	}

	public void setRh(String[] rh) {
		this.rh = rh;
	}

	public String[] getVolume() {
		return volume;
	}

	public void setVolume(String[] volume) {
		this.volume = volume;
	}

	public String getRequisitionNo() {
		return requisitionNo;
	}

	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}

	public String[] getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String[] batchNo) {
		this.batchNo = batchNo;
	}

	public String[] getTubingNo() {
		return tubingNo;
	}

	public void setTubingNo(String[] tubingNo) {
		this.tubingNo = tubingNo;
	}

	
}
