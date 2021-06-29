package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DoctorWardRoundDetailFB extends ActionForm
{

	private String roundBy;
	private String roundDate;
	private String roundTime;
	private String roundType;
	private String hmode;
	private String roundInHr;
	private String roundInMin;
	private String patCrNo;
	private String wardCode;
	private String nextRoundDate;
	private String nextRoundInHr;
	private String nextRoundInMin;
	private String sysDate;
	private String unitCode;
	private String[] selectedPatCrNo;
	private String selectCheck;
	private String patientName;
	private String onCallDetailFlag;
	private String sysHr;
	private String sysMin;
	private String deskMenuId;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.roundBy="-1";
		this.roundInHr="";
		this.roundInMin="";
		this.roundDate="";
		this.roundType="-1";
		this.nextRoundDate="";
		this.nextRoundInHr="";
		this.nextRoundInMin="";
	}
	
	public String getRoundBy() {
		return roundBy;
	}
	public void setRoundBy(String roundBy) {
		this.roundBy = roundBy;
	}
	public String getRoundDate() {
		return roundDate;
	}
	public void setRoundDate(String roundDate) {
		this.roundDate = roundDate;
	}
	public String getRoundTime() {
		return roundTime;
	}
	public void setRoundTime(String roundTime) {
		this.roundTime = roundTime;
	}
	public String getRoundType() {
		return roundType;
	}
	public void setRoundType(String roundType) {
		this.roundType = roundType;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getRoundInHr() {
		return roundInHr;
	}
	public void setRoundInHr(String roundInHr) {
		this.roundInHr = roundInHr;
	}
	public String getRoundInMin() {
		return roundInMin;
	}
	public void setRoundInMin(String roundInMin) {
		this.roundInMin = roundInMin;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getNextRoundDate() {
		return nextRoundDate;
	}
	public void setNextRoundDate(String nextRoundDate) {
		this.nextRoundDate = nextRoundDate;
	}

	public String getNextRoundInHr() {
		return nextRoundInHr;
	}

	public void setNextRoundInHr(String nextRoundInHr) {
		this.nextRoundInHr = nextRoundInHr;
	}

	public String getNextRoundInMin() {
		return nextRoundInMin;
	}

	public void setNextRoundInMin(String nextRoundInMin) {
		this.nextRoundInMin = nextRoundInMin;
	}

	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getSelectCheck() {
		return selectCheck;
	}

	public void setSelectCheck(String selectCheck) {
		this.selectCheck = selectCheck;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getOnCallDetailFlag() {
		return onCallDetailFlag;
	}

	public void setOnCallDetailFlag(String onCallDetailFlag) {
		this.onCallDetailFlag = onCallDetailFlag;
	}

	public void setSelectedPatCrNo(String[] selectedPatCrNo) {
		this.selectedPatCrNo = selectedPatCrNo;
	}

	public String[] getSelectedPatCrNo() {
		return selectedPatCrNo;
	}



	public String getSysHr() {
		return sysHr;
	}

	public void setSysHr(String sysHr) {
		this.sysHr = sysHr;
	}

	public String getSysMin() {
		return sysMin;
	}

	public void setSysMin(String sysMin) {
		this.sysMin = sysMin;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	
	
	
	
}
