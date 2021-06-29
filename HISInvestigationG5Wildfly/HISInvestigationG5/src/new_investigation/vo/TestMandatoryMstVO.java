package new_investigation.vo;

import hisglobal.vo.ValueObject;

/*
## Copyright Information				: C-DAC, Noida  
## Project Name				       	: NIMS
## Name of Developer		 			: ANANT PATEL
## Module Name					        : INVESTIGATION
## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
## Purpose						        : This master is used for mapping test with mandatory globally i.e. on hospital code 100
## Date of Creation					:16-Mar-2015 
## Modification Log					:				
##		Modify Date				        :  
##		Reason	(CR/PRS)			    : 
##		Modify By				        : 
*/


public class TestMandatoryMstVO extends ValueObject {

	private String mandCode;
	private String testCode;
	private String mandatoryName;
	private String entryDate;
	
	private String selectedLab;
	private String templab;
	String[] leftList;
	String[] rightList;
	
	private String testName;
	private String count;
	
	public String getMandCode() {
		return mandCode;
	}
	public void setMandCode(String mandCode) {
		this.mandCode = mandCode;
	}
	
	public String getMandatoryName() {
		return mandatoryName;
	}
	public void setMandatoryName(String mandatoryName) {
		this.mandatoryName = mandatoryName;
	}
	
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSelectedLab() {
		return selectedLab;
	}
	public void setSelectedLab(String selectedLab) {
		this.selectedLab = selectedLab;
	}
	public String getTemplab() {
		return templab;
	}
	public void setTemplab(String templab) {
		this.templab = templab;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
	
}
