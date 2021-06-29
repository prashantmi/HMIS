package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class MacroMasterVO extends ValueObject
{
	
	private String hospitalcode;
	private String chk[];
	private String isActive;
	private String processID;
	private String macroHeader;
	private String macroDesc;
	private String controls[];
	private String processName;
	
	public String getMacroCode() {
		return macroCode;
	}
	public void setMacroCode(String macroCode) {
		this.macroCode = macroCode;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getMacroText() {
		return macroText;
	}
	public void setMacroText(String macroText) {
		this.macroText = macroText;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	private String slNo;
	private String macroID;
	private String length;
	private String macroCode;
	private String labCode;
	private String seatId;
	private String macroText;
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	private String remarks;
	private String hospitalCode;
	
	public String getMacroID() {
		return macroID;
	}
	public void setMacroID(String macroID) {
		this.macroID = macroID;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public String getMacroHeader() {
		return macroHeader;
	}
	public void setMacroHeader(String macroHeader) {
		this.macroHeader = macroHeader;
	}
	public String getMacroDesc() {
		return macroDesc;
	}
	public void setMacroDesc(String macroDesc) {
		this.macroDesc = macroDesc;
	}
	public String[] getControls() {
		return controls;
	}
	public void setControls(String[] controls) {
		this.controls = controls;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}

	
}