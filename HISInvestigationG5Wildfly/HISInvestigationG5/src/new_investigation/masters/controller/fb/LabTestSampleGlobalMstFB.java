/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE/SYSTEM GLOBAL MASTER
 ## Purpose						        : This master is used for mapping the Sample to labs and test globally i.e. on hospital code 100
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.masters.controller.fb;

import org.apache.struts.action.ActionForm;

public class LabTestSampleGlobalMstFB extends ActionForm {
	
	private String labCode;
	private String testCode;
	private String sampleQty;
	private String sampleCode;
	private String containerCode;
	private String entryDate;
	private String uomCode;
	private String sampleCollRemarks;
	private String isDefaultSample;
	private String testType;
	private String hmode;
	private String labName;
	private String testName;
	private String sampleName;
	private String containerName;
	private String uomName;
	private String chk[];
	private String selectedChk;
	public String getLabCode() {
		return labCode;
	}

	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getSampleQty() {
		return sampleQty;
	}

	public void setSampleQty(String sampleQty) {
		this.sampleQty = sampleQty;
	}

	public String getSampleCode() {
		return sampleCode;
	}

	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}

	public String getContainerCode() {
		return containerCode;
	}

	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getUomCode() {
		return uomCode;
	}

	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}

	public String getSampleCollRemarks() {
		return sampleCollRemarks;
	}

	public void setSampleCollRemarks(String sampleCollRemarks) {
		this.sampleCollRemarks = sampleCollRemarks;
	}

	public String getIsDefaultSample() {
		return isDefaultSample;
	}

	public void setIsDefaultSample(String isDefaultSample) {
		this.isDefaultSample = isDefaultSample;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getSampleName() {
		return sampleName;
	}

	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getSelectedChk() {
		return selectedChk;
	}

	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	

}
