package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InvTestSampleMstFB extends ActionForm
{

	private String hmode;
	private String chk[];

	private String testCode;
	private String testName;
	private String sampleCode;
	private String sampleName;
	private String uomCode;
	private String containerCode;
	private String sampleQuantity;
	private String defaultSample;
	private String defaultTrue;
	private String selectedChk;

	private String isActive;

	public String getIsActive() {
		return isActive;
	}



	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.sampleCode = "-1";
		this.uomCode = "-1";
		this.containerCode = "-1";
		this.defaultSample = "0";
		this.sampleQuantity = "";

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

	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}



	public String getTestCode() {
		return testCode;
	}



	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}



	public String getSampleCode() {
		return sampleCode;
	}



	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}



	public String getUomCode() {
		return uomCode;
	}



	public void setUomCode(String uomCode) {
		this.uomCode = uomCode;
	}



	public String getContainerCode() {
		return containerCode;
	}



	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}



	public String getSampleQuantity() {
		return sampleQuantity;
	}



	public void setSampleQuantity(String sampleQuantity) {
		this.sampleQuantity = sampleQuantity;
	}



	public String getDefaultSample() {
		return defaultSample;
	}



	public void setDefaultSample(String defaultSample) {
		this.defaultSample = defaultSample;
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



	public String getDefaultTrue() {
		return defaultTrue;
	}



	public void setDefaultTrue(String defaultTrue) {
		this.defaultTrue = defaultTrue;
	}



}
