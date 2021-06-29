package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class machineMstFB extends ActionForm
{


	private String hmode;
	private String chk[];
	private String checklistPrevious;
	private String machineName;
	private String remarks;
	private String machineCode;
	private String isActive;
	private String selectedChk;
	private String status;
	private String format;
	private String commFlag;
	private String locationCode;
	private String validationType;
	private String archivalDays;
	private String chkCommPort[];
	private String defaultMode[];
	private String baudRate[];
	private String byteSize[];
	private String parity[];
	private String stopBit[];
	private String readTimeOut[];
	private String writeTimeOut[];
	private String flowControl[];
	private String flowParity[];
	private String machineType;

	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.machineName="";
		this.locationCode="-1";
		this.commFlag="0";
		this.archivalDays="3";
		this.validationType="2";
		this.chkCommPort=null;
		this.remarks="";
		this.defaultMode=null;
		this.baudRate=null;
		this.byteSize=null;
		this.parity=null;
		this.readTimeOut=null;
		this.writeTimeOut=null;
		this.stopBit=null;
		this.flowControl=null;
		this.flowParity=null;
	}




	public String getHmode()
	{
		return hmode;
	}
	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}
	public String[] getChk()
	{
		return chk;
	}
	public void setChk(String[] chk)
	{
		this.chk = chk;
	}
	public String getChecklistPrevious()
	{
		return checklistPrevious;
	}
	public void setChecklistPrevious(String checklistPrevious)
	{
		this.checklistPrevious = checklistPrevious;
	}



	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}



	public String getMachineName() {
		return machineName;
	}



	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}



	public String getMachineCode() {
		return machineCode;
	}



	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getFormat() {
		return format;
	}



	public void setFormat(String format) {
		this.format = format;
	}



	public String getCommFlag() {
		return commFlag;
	}



	public void setCommFlag(String commFlag) {
		this.commFlag = commFlag;
	}



	public String getLocationCode() {
		return locationCode;
	}



	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}



	public String getValidationType() {
		return validationType;
	}



	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}



	public String getArchivalDays() {
		return archivalDays;
	}



	public void setArchivalDays(String archivalDays) {
		this.archivalDays = archivalDays;
	}






	public String[] getDefaultMode() {
		return defaultMode;
	}



	public void setDefaultMode(String[] defaultModep) {
		this.defaultMode = defaultModep;
	}



	public String[] getBaudRate() {
		return baudRate;
	}



	public void setBaudRate(String[] baudRate) {
		this.baudRate = baudRate;
	}



	public String[] getByteSize() {
		return byteSize;
	}



	public void setByteSize(String[] byteSize) {
		this.byteSize = byteSize;
	}



	public String[] getParity() {
		return parity;
	}



	public void setParity(String[] parity) {
		this.parity = parity;
	}



	public String[] getStopBit() {
		return stopBit;
	}



	public void setStopBit(String[] stopBit) {
		this.stopBit = stopBit;
	}



	public String[] getReadTimeOut() {
		return readTimeOut;
	}



	public void setReadTimeOut(String[] readTimeOut) {
		this.readTimeOut = readTimeOut;
	}



	public String[] getWriteTimeOut() {
		return writeTimeOut;
	}



	public void setWriteTimeOut(String[] writeTimeOut) {
		this.writeTimeOut = writeTimeOut;
	}



	public String[] getFlowControl() {
		return flowControl;
	}



	public void setFlowControl(String[] flowControl) {
		this.flowControl = flowControl;
	}



	public String[] getFlowParity() {
		return flowParity;
	}



	public void setFlowParity(String[] flowParity) {
		this.flowParity = flowParity;
	}



	public String[] getchkCommPort() {
		return chkCommPort;
	}



	public void setchkCommPort(String[] chkCommPort) {
		this.chkCommPort = chkCommPort;
	}



	public String getMachineType() {
		return machineType;
	}



	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}



	public String[] getChkCommPort() {
		return chkCommPort;
	}



	public void setChkCommPort(String[] chkCommPort) {
		this.chkCommPort = chkCommPort;
	}


	
	

}
