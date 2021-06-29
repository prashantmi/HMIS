package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class TestParaComboMasterVO extends ValueObject 
{
	private String testCode;
	private String testName;

	private String parameterCode;
	private String testparaSeq;
	private String testparaValue;

	private String setdefault;	

	private String isActive;
	private String numberOfRow;


	private String testParameterValue;
     
	private String paraType;

	



	public String getSetdefault() {
		return setdefault;
	}



	public void setSetdefault(String setdefault) {
		this.setdefault = setdefault;
	}
	
	public String getNumberOfRow() {
		return numberOfRow;
	}



	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}



	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
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



	public String getTestparaValue() {
		return testparaValue;
	}



	public void setTestparaValue(String testparaValue) {
		this.testparaValue = testparaValue;
	}

	
	

	public String getTestName() {
		return testName;
	}



	public void setTestName(String testName) {
		this.testName = testName;
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
