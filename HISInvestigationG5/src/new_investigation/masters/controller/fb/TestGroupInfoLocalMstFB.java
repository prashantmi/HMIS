package new_investigation.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TestGroupInfoLocalMstFB extends ActionForm
{
	
	
	private	String[] unmappedList;
	private	String[] mappedList;

	private String groupCode;
	private String hmode;
	private String chk[];
	private String testCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;
	private String selectedChk;
	private String checkLocal;
	private String isActive;

	private String isDynamicResult;
	private String isDynamicTemplate;
	private String printingTemplateName;
	private String printingTemplateCode;
	private String globalTemplate;
	private String testName;
	private String testSeqNo[];
	private String numberOfRow;
	private String userGroupCode;
	
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

		this.groupCode="-1";
		this.labCode="-1";
		this.remarks="";
		this.testCode="-1";
		this.isDynamicTemplate="0";
		this.isDynamicResult="0";
		this.printingTemplateCode="-1";
		this.globalTemplate="1";
		this.userGroupCode="";
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



	

	public String getHospitalCode() {
		return hospitalCode;
	}



	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}



	public String getSelectedChk() {
		return selectedChk;
	}



	public void setSelectedChk(String selectedChk) {
		this.selectedChk = selectedChk;
	}




	public String getLabCode() {
		return labCode;
	}



	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}



	public String[] getUnmappedList() {
		return unmappedList;
	}



	public void setUnmappedList(String[] unmappedList) {
		this.unmappedList = unmappedList;
	}



	public String[] getMappedList() {
		return mappedList;
	}



	public void setMappedList(String[] mappedList) {
		this.mappedList = mappedList;
	}



	public String getGroupCode() {
		return groupCode;
	}



	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}



	public String getTestCode() {
		return testCode;
	}



	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}



	public String getCheckLocal() {
		return checkLocal;
	}



	public void setCheckLocal(String checkLocal) {
		this.checkLocal = checkLocal;
	}



	public String getIsDynamicResult() {
		return isDynamicResult;
	}



	public void setIsDynamicResult(String isDynamicResult) {
		this.isDynamicResult = isDynamicResult;
	}



	public String getIsDynamicTemplate() {
		return isDynamicTemplate;
	}



	public void setIsDynamicTemplate(String isDynamicTemplate) {
		this.isDynamicTemplate = isDynamicTemplate;
	}



	public String getPrintingTemplateName() {
		return printingTemplateName;
	}



	public void setPrintingTemplateName(String printingTemplateName) {
		this.printingTemplateName = printingTemplateName;
	}



	public String getPrintingTemplateCode() {
		return printingTemplateCode;
	}



	public void setPrintingTemplateCode(String printingTemplateCode) {
		this.printingTemplateCode = printingTemplateCode;
	}



	public String getGlobalTemplate() {
		return globalTemplate;
	}



	public void setGlobalTemplate(String globalTemplate) {
		this.globalTemplate = globalTemplate;
	}



	public String getTestName() {
		return testName;
	}



	public void setTestName(String testName) {
		this.testName = testName;
	}







	public String getNumberOfRow() {
		return numberOfRow;
	}



	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}



	public String[] getTestSeqNo() {
		return testSeqNo;
	}



	public void setTestSeqNo(String[] testSeqNo) {
		this.testSeqNo = testSeqNo;
	}



	public String getUserGroupCode() {
		return userGroupCode;
	}



	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}



}
