package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class TestGroupInfoMasterVO extends ValueObject 
{

	private String groupCode;
	private String testCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;
	private String checkLocal;

	private String isActive;
	private String hmode;
	private String isDynamicResult;
	private String isDynamicTemplate;
	private String printingTemplateName;
	private String printingTemplateCode;
	private String globalTemplate;
	private String testSeqNo;
	private String testName;
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

	
	
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getGlobalTemplate() {
		return globalTemplate;
	}
	public void setGlobalTemplate(String globalTemplate) {
		this.globalTemplate = globalTemplate;
	}
	public String getTestSeqNo() {
		return testSeqNo;
	}
	public void setTestSeqNo(String testSeqNo) {
		this.testSeqNo = testSeqNo;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getUserGroupCode() {
		return userGroupCode;
	}
	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}
	
	
	}

