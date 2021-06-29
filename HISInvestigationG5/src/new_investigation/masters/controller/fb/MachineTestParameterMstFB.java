package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MachineTestParameterMstFB extends ActionForm
{
	
	private String machineCode;
	private String testCode;
	private String hmode;
	private String parameterName[];
	private String parameterCode[];
	private String machineName;
	private String machineParameterName[];
	private String machineParameterCode[];
	private String noOfParameter;
	private String chk[];
	private String selectedChk;
	
	public String getNoOfParameter() {
		return noOfParameter;
	}
	public void setNoOfParameter(String noOfParameter) {
		this.noOfParameter = noOfParameter;
	}
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String[] getParameterName() {
		return parameterName;
	}
	public void setParameterName(String[] parameterName) {
		this.parameterName = parameterName;
	}

	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String[] getMachineParameterName() {
		return machineParameterName;
	}
	public void setMachineParameterName(String[] machineParameterName) {
		this.machineParameterName = machineParameterName;
	}
	public String[] getMachineParameterCode() {
		return machineParameterCode;
	}
	public void setMachineParameterCode(String[] machineParameterCode) {
		this.machineParameterCode = machineParameterCode;
	}
	
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.machineCode="-1";
		this.testCode="-1";
		//this.parameterCode="-1";

		//this.testparaValue=null;
		//this.parameterName=null;
		//this.machineParameterName=null;
		//this.machineParameterCode=null;
	}
	public String[] getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String[] parameterCode) {
		this.parameterCode = parameterCode;
	}


	public String getSelectedChk() {
		return selectedChk;
	}


	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}
	
	
}
