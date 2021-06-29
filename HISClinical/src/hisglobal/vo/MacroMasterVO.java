package hisglobal.vo;

public class MacroMasterVO extends ValueObject
{
	private String hmode;
	private String hospitalcode;
	private String chk[];
	private String isActive;
	private String processID;
	private String macroHeader;
	private String macroDesc;
	private String controls[];
	private String processName;
	private String slNo;
	private String macroID;
	private String length;
	
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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