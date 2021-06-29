package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class MachineTestParameterMasterVO extends ValueObject 
{
	private String machineCode;
	private String testCode;
	private String parameterName;
	private String parameterCode;
	private String machineName;
	private String machineParameterName;
	private String machineParameterCode;
	private String paraCount;
	
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineParameterName() {
		return machineParameterName;
	}
	public void setMachineParameterName(String machineParameterName) {
		this.machineParameterName = machineParameterName;
	}
	public String getMachineParameterCode() {
		return machineParameterCode;
	}
	public void setMachineParameterCode(String machineParameterCode) {
		this.machineParameterCode = machineParameterCode;
	}
	public String getParaCount() {
		return paraCount;
	}
	public void setParaCount(String paraCount) {
		this.paraCount = paraCount;
	}
	
}
