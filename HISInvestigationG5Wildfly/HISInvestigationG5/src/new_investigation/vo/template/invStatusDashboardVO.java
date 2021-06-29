package new_investigation.vo.template;

import hisglobal.vo.ValueObject;

public class invStatusDashboardVO extends ValueObject{
	private String no_of_req_raised;
	private String no_of_test_raised;
	private String no_of_group_test_raised;
	private String req_done_with_sam_col;
	private String req_pending_for_sam_col;
	private String test_done_with_sam_col;
	private String test_pending_for_sam_col;
	private String req_done_with_pkg_lis_gen;
	private String req_pending_for_pkg_lis_gen;
	private String test_done_with_pkg_list_gen;
	private String test_pending_for_pkg_list_gen;
	private String req_done_with_sam_acc;
	private String req_pending_for_sam_acc;
	private String test_done_with_sam_acc;
	private String test_pending_for_sam_acc;
	private String req_done_with_res_entry;
	private String req_pending_for_res_entry;
	private String test_done_with_res_entry;
	private String test_pending_for_res_entry;
	private String test_done_with_mach_prcs;
	private String test_pending_for_mach_prcs;
	private String req_done_with_validation;
	private String req_pending_for_validation;
	private String test_done_with_validation;
	private String test_pending_for_validation;
	private String req_done_with_revalidation;
	private String req_pending_for_revalidation;
	private String test_done_with_revalidation;
	private String test_pending_for_revalidation;
	
	private String sampleId;
	private String sampleNo;
	private String crno;
	private String sampleType;
	
	private String reqdno;
	private String testname;
	private String groupCode;
	private String valDate;
	private String reValDate;
	private String acceptDate;
	private String machineName;
	
	public String getReqdno() {
		return reqdno;
	}

	public void setReqdno(String reqdno) {
		this.reqdno = reqdno;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getValDate() {
		return valDate;
	}

	public void setValDate(String valDate) {
		this.valDate = valDate;
	}

	public String getReValDate() {
		return reValDate;
	}

	public void setReValDate(String reValDate) {
		this.reValDate = reValDate;
	}

	public String getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(String acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public String getSampleId() {
		return sampleId;
	}

	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}

	public String getSampleNo() {
		return sampleNo;
	}

	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}

	public String getCrno() {
		return crno;
	}

	public void setCrno(String crno) {
		this.crno = crno;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getNo_of_req_raised() {
		return no_of_req_raised;
	}
	
	public void setNo_of_req_raised(String no_of_req_raised) {
		this.no_of_req_raised = no_of_req_raised;
	}
	public String getNo_of_test_raised() {
		return no_of_test_raised;
	}
	public void setNo_of_test_raised(String no_of_test_raised) {
		this.no_of_test_raised = no_of_test_raised;
	}
	public String getNo_of_group_test_raised() {
		return no_of_group_test_raised;
	}
	public void setNo_of_group_test_raised(String no_of_group_test_raised) {
		this.no_of_group_test_raised = no_of_group_test_raised;
	}
	public String getReq_done_with_sam_col() {
		return req_done_with_sam_col;
	}
	public void setReq_done_with_sam_col(String req_done_with_sam_col) {
		this.req_done_with_sam_col = req_done_with_sam_col;
	}
	public String getReq_pending_for_sam_col() {
		return req_pending_for_sam_col;
	}
	public void setReq_pending_for_sam_col(String req_pending_for_sam_col) {
		this.req_pending_for_sam_col = req_pending_for_sam_col;
	}
	public String getTest_done_with_sam_col() {
		return test_done_with_sam_col;
	}
	public void setTest_done_with_sam_col(String test_done_with_sam_col) {
		this.test_done_with_sam_col = test_done_with_sam_col;
	}
	public String getTest_pending_for_sam_col() {
		return test_pending_for_sam_col;
	}
	public void setTest_pending_for_sam_col(String test_pending_for_sam_col) {
		this.test_pending_for_sam_col = test_pending_for_sam_col;
	}
	public String getReq_done_with_pkg_lis_gen() {
		return req_done_with_pkg_lis_gen;
	}
	public void setReq_done_with_pkg_lis_gen(String req_done_with_pkg_lis_gen) {
		this.req_done_with_pkg_lis_gen = req_done_with_pkg_lis_gen;
	}
	public String getReq_pending_for_pkg_lis_gen() {
		return req_pending_for_pkg_lis_gen;
	}
	public void setReq_pending_for_pkg_lis_gen(String req_pending_for_pkg_lis_gen) {
		this.req_pending_for_pkg_lis_gen = req_pending_for_pkg_lis_gen;
	}
	public String getTest_done_with_pkg_list_gen() {
		return test_done_with_pkg_list_gen;
	}
	public void setTest_done_with_pkg_list_gen(String test_done_with_pkg_list_gen) {
		this.test_done_with_pkg_list_gen = test_done_with_pkg_list_gen;
	}
	public String getTest_pending_for_pkg_list_gen() {
		return test_pending_for_pkg_list_gen;
	}
	public void setTest_pending_for_pkg_list_gen(
			String test_pending_for_pkg_list_gen) {
		this.test_pending_for_pkg_list_gen = test_pending_for_pkg_list_gen;
	}
	public String getReq_done_with_sam_acc() {
		return req_done_with_sam_acc;
	}
	public void setReq_done_with_sam_acc(String req_done_with_sam_acc) {
		this.req_done_with_sam_acc = req_done_with_sam_acc;
	}
	public String getReq_pending_for_sam_acc() {
		return req_pending_for_sam_acc;
	}
	public void setReq_pending_for_sam_acc(String req_pending_for_sam_acc) {
		this.req_pending_for_sam_acc = req_pending_for_sam_acc;
	}
	public String getTest_done_with_sam_acc() {
		return test_done_with_sam_acc;
	}
	public void setTest_done_with_sam_acc(String test_done_with_sam_acc) {
		this.test_done_with_sam_acc = test_done_with_sam_acc;
	}
	public String getTest_pending_for_sam_acc() {
		return test_pending_for_sam_acc;
	}
	public void setTest_pending_for_sam_acc(String test_pending_for_sam_acc) {
		this.test_pending_for_sam_acc = test_pending_for_sam_acc;
	}
	public String getReq_done_with_res_entry() {
		return req_done_with_res_entry;
	}
	public void setReq_done_with_res_entry(String req_done_with_res_entry) {
		this.req_done_with_res_entry = req_done_with_res_entry;
	}
	public String getReq_pending_for_res_entry() {
		return req_pending_for_res_entry;
	}
	public void setReq_pending_for_res_entry(String req_pending_for_res_entry) {
		this.req_pending_for_res_entry = req_pending_for_res_entry;
	}
	public String getTest_done_with_res_entry() {
		return test_done_with_res_entry;
	}
	public void setTest_done_with_res_entry(String test_done_with_res_entry) {
		this.test_done_with_res_entry = test_done_with_res_entry;
	}
	public String getTest_pending_for_res_entry() {
		return test_pending_for_res_entry;
	}
	public void setTest_pending_for_res_entry(String test_pending_for_res_entry) {
		this.test_pending_for_res_entry = test_pending_for_res_entry;
	}
	public String getTest_done_with_mach_prcs() {
		return test_done_with_mach_prcs;
	}
	public void setTest_done_with_mach_prcs(String test_done_with_mach_prcs) {
		this.test_done_with_mach_prcs = test_done_with_mach_prcs;
	}
	public String getTest_pending_for_mach_prcs() {
		return test_pending_for_mach_prcs;
	}
	public void setTest_pending_for_mach_prcs(String test_pending_for_mach_prcs) {
		this.test_pending_for_mach_prcs = test_pending_for_mach_prcs;
	}
	public String getReq_done_with_validation() {
		return req_done_with_validation;
	}
	public void setReq_done_with_validation(String req_done_with_validation) {
		this.req_done_with_validation = req_done_with_validation;
	}
	public String getReq_pending_for_validation() {
		return req_pending_for_validation;
	}
	public void setReq_pending_for_validation(String req_pending_for_validation) {
		this.req_pending_for_validation = req_pending_for_validation;
	}
	public String getTest_done_with_validation() {
		return test_done_with_validation;
	}
	public void setTest_done_with_validation(String test_done_with_validation) {
		this.test_done_with_validation = test_done_with_validation;
	}
	public String getTest_pending_for_validation() {
		return test_pending_for_validation;
	}
	public void setTest_pending_for_validation(String test_pending_for_validation) {
		this.test_pending_for_validation = test_pending_for_validation;
	}
	public String getReq_done_with_revalidation() {
		return req_done_with_revalidation;
	}
	public void setReq_done_with_revalidation(String req_done_with_revalidation) {
		this.req_done_with_revalidation = req_done_with_revalidation;
	}
	public String getReq_pending_for_revalidation() {
		return req_pending_for_revalidation;
	}
	public void setReq_pending_for_revalidation(String req_pending_for_revalidation) {
		this.req_pending_for_revalidation = req_pending_for_revalidation;
	}
	public String getTest_done_with_revalidation() {
		return test_done_with_revalidation;
	}
	public void setTest_done_with_revalidation(String test_done_with_revalidation) {
		this.test_done_with_revalidation = test_done_with_revalidation;
	}
	public String getTest_pending_for_revalidation() {
		return test_pending_for_revalidation;
	}
	public void setTest_pending_for_revalidation(
			String test_pending_for_revalidation) {
		this.test_pending_for_revalidation = test_pending_for_revalidation;
	}

}
