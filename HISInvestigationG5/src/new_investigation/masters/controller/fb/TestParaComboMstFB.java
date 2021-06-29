package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TestParaComboMstFB extends ActionForm
{
	private String hmode;
	private String chk[];
	
	private String testCode;
	private String testName;
	private String parameterCode;
	private String testparaSeq;
	private String testparaValue[];

	private String isActive[];
	private String numberOfRow;
	private String selectedChk;
	
	private String testParameterValue;
	private String paraType;

	private String setdefault;
	
	public String getSetdefault() {
		return setdefault;
	}



	public void setSetdefault(String setdefault) {
		this.setdefault = setdefault;
	}



	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.testCode="-1";
		this.parameterCode="-1";

		this.testparaValue=null;
		//this.setdefault=null;
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


	public String getNumberOfRow() {
		return numberOfRow;
	}



	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}



	public String[] getIsActive() {
		return isActive;
	}

	public void setIsActive(String[] isActive) {
		this.isActive = isActive;
	}

	

	public String getTestCode() {
		return testCode;
	}


	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}


	public String getParameterCode() {
		return parameterCode;
	}


	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}


	public String getTestparaSeq() {
		return testparaSeq;
	}


	public void setTestparaSeq(String testparaSeq) {
		this.testparaSeq = testparaSeq;
	}


	public String[] getTestparaValue() {
		return testparaValue;
	}


	public void setTestparaValue(String[] testparaValue) {
		this.testparaValue = testparaValue;
	}

	

	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getSelectedChk() {
		return selectedChk;
	}


	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}


	public String getTestParameterValue() {
		return testParameterValue;
	}


	public void setTestParameterValue(String testParameterValue) {
		this.testParameterValue = testParameterValue;
	}


	public String getParaType() {
		return paraType;
	}


	public void setParaType(String paraType) {
		this.paraType = paraType;
	}
	


}
