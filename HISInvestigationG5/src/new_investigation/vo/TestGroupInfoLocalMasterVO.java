package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class TestGroupInfoLocalMasterVO extends ValueObject 
{

	private String groupCode;
	private String testCode;
	private String remarks;
	private String labCode;
	private String hospitalCode;
	private String checkLocal;
	private String globalTemplate;
	private String isActive;
	private String testName;
	private String testSeqNo;



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
	public String getTestSeqNo() {
		return testSeqNo;
	}
	public void setTestSeqNo(String testSeqNo) {
		this.testSeqNo = testSeqNo;
	}
	
	}

