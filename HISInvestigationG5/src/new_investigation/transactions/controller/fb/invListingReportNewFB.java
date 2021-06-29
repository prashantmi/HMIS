package new_investigation.transactions.controller.fb;

import org.apache.struts.action.ActionForm;

public class invListingReportNewFB extends ActionForm{
	private String labCode;
	private String labName;
	private String groupName;
	private String testName;
	private String sampleCode;
	private String sampleName;
	private String priority;
	private String reqStatus;
	private String status;
	private String sample_num;
	private String puk_fromreqno;
	private String pat_fromreqno;
	private String patType;
	private String age_gender;
	private String wardbed_no;
	private String advised_by;
	private String deptCode;
	private String deptName;
	private String p_from_date;
	private String p_to_date;
	private String p_deptcode;
	private String p_pattype;
	private String p_status;
	private String p_hospital_code;
	private String p_samplecode;
	private String p_labcode;
	private String p_groupname;
	private String fromDate="";
	private String hmode;
	private String lab_num;
	private String req_date;

	/* Added By PrashantMi */
    private String isRadioDignoProcess;
	private String startIndex;
	private String totalRow;
	private String rowLimit;

	private String wardName;
	private String searchDateType;

	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}


	public String getSearchDateType() {
		return searchDateType;
	}
	public void setSearchDateType(String searchDateType) {
		this.searchDateType = searchDateType;
	}
	
	public String getRowLimit() {
		return rowLimit;
	}
	public void setRowLimit(String rowLimit) {
		this.rowLimit = rowLimit;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(String totalRow) {
		this.totalRow = totalRow;
	}

	public String getIsRadioDignoProcess() {
		return isRadioDignoProcess;
	}
	public void setIsRadioDignoProcess(String isRadioDignoProcess) {
		this.isRadioDignoProcess = isRadioDignoProcess;
	}

	public String getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	public String getLab_num() {
		return lab_num;
	}
	public void setLab_num(String lab_num) {
		this.lab_num = lab_num;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	private String toDate="";

	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getSampleName() {
		return sampleName;
	}
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSample_num() {
		return sample_num;
	}
	public void setSample_num(String sample_num) {
		this.sample_num = sample_num;
	}
	public String getPuk_fromreqno() {
		return puk_fromreqno;
	}
	public void setPuk_fromreqno(String puk_fromreqno) {
		this.puk_fromreqno = puk_fromreqno;
	}
	public String getPat_fromreqno() {
		return pat_fromreqno;
	}
	public void setPat_fromreqno(String pat_fromreqno) {
		this.pat_fromreqno = pat_fromreqno;
	}
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
	}
	public String getAge_gender() {
		return age_gender;
	}
	public void setAge_gender(String age_gender) {
		this.age_gender = age_gender;
	}
	public String getWardbed_no() {
		return wardbed_no;
	}
	public void setWardbed_no(String wardbed_no) {
		this.wardbed_no = wardbed_no;
	}
	public String getAdvised_by() {
		return advised_by;
	}
	public void setAdvised_by(String advised_by) {
		this.advised_by = advised_by;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getP_from_date() {
		return p_from_date;
	}
	public void setP_from_date(String p_from_date) {
		this.p_from_date = p_from_date;
	}
	public String getP_to_date() {
		return p_to_date;
	}
	public void setP_to_date(String p_to_date) {
		this.p_to_date = p_to_date;
	}
	public String getP_deptcode() {
		return p_deptcode;
	}
	public void setP_deptcode(String p_deptcode) {
		this.p_deptcode = p_deptcode;
	}
	public String getP_pattype() {
		return p_pattype;
	}
	public void setP_pattype(String p_pattype) {
		this.p_pattype = p_pattype;
	}
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public String getP_hospital_code() {
		return p_hospital_code;
	}
	public void setP_hospital_code(String p_hospital_code) {
		this.p_hospital_code = p_hospital_code;
	}
	public String getP_samplecode() {
		return p_samplecode;
	}
	public void setP_samplecode(String p_samplecode) {
		this.p_samplecode = p_samplecode;
	}
	public String getP_labcode() {
		return p_labcode;
	}
	public void setP_labcode(String p_labcode) {
		this.p_labcode = p_labcode;
	}
	public String getP_groupname() {
		return p_groupname;
	}
	public void setP_groupname(String p_groupname) {
		this.p_groupname = p_groupname;
	}
}
