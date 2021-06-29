/*Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:22-Nov-2014 

*/

package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import registration.RegistrationConfig;

public class EstimateCertificateIssuetFB extends ActionForm {
	private String hmode;
	private String selectRecord;
	private String tariffName;
	private String requestedDate;
	private String patCrNo;
	private String patFullName;
	private String advisedBy;
	private String givenBy;
	private String relationCode;
	private String relativeName;
	private String relativeAddr;
	private String relativeContactNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String tarrifId;
	private String certificateId;
	private String bedname;
	private String patwardname;
	private String admissionNo;
	private String deptName;
	private String unitName;
	private String billNo;
	private String deptUnitCode;
	private String wardCode;
	private String startIdx;
	private String endIdx;
	private int currentPage=1;
	private String strHiddenPatDtl;
	private String patFatherName;
	private String sysDate="";
	private String hospAdd;
	private String hospName;
	private String docName;
	private String docDesig;
	private String docDept;
	private String hospAdd1;
	private String get_emp_name;
	private String episodeStartDate;
	private String admNo;
	private String admDate;
	private String disDate;
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setGivenBy("-1");
		this.setRelationCode("-1");
		this.setRelativeAddr("");
		this.setRelativeName("");
		this.setRelativeContactNo("");
	}	

	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getSelectRecord() {
		return selectRecord;
	}

	public void setSelectRecord(String selectRecord) {
		this.selectRecord = selectRecord;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		this.requestedDate = requestedDate;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getPatFullName() {
		return patFullName;
	}

	public void setPatFullName(String patFullName) {
		this.patFullName = patFullName;
	}

	public String getAdvisedBy() {
		return advisedBy;
	}

	public void setAdvisedBy(String advisedBy) {
		this.advisedBy = advisedBy;
	}

	public String getGivenBy() {
		return givenBy;
	}

	public void setGivenBy(String givenBy) {
		this.givenBy = givenBy;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getRelativeName() {
		return relativeName;
	}

	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}

	public String getRelativeAddr() {
		return relativeAddr;
	}

	public void setRelativeAddr(String relativeAddr) {
		this.relativeAddr = relativeAddr;
	}

	public String getRelativeContactNo() {
		return relativeContactNo;
	}

	public void setRelativeContactNo(String relativeContactNo) {
		this.relativeContactNo = relativeContactNo;
	}

	public String getEpisodeCode() {
		return episodeCode;
	}

	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}

	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}

	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}

	public String getTarrifId() {
		return tarrifId;
	}

	public void setTarrifId(String tarrifId) {
		this.tarrifId = tarrifId;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}


	public String getBillNo() {
		return billNo;
	}


	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}


	public String getDeptUnitCode() {
		return deptUnitCode;
	}


	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}


	public String getWardCode() {
		return wardCode;
	}


	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String getStartIdx() {
		return startIdx;
	}


	public void setStartIdx(String startIdx) {
		this.startIdx = startIdx;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getEndIdx() {
		return endIdx;
	}


	public void setEndIdx(String endIdx) {
		this.endIdx = endIdx;
	}


	public String getStrHiddenPatDtl() {
		return strHiddenPatDtl;
	}


	public void setStrHiddenPatDtl(String strHiddenPatDtl) {
		this.strHiddenPatDtl = strHiddenPatDtl;
	}


	public String getPatFatherName() {
		return patFatherName;
	}


	public void setPatFatherName(String patFatherName) {
		this.patFatherName = patFatherName;
	}


	public String getSysDate() {
		return sysDate;
	}


	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}


	public String getHospAdd() {
		return hospAdd;
	}


	public void setHospAdd(String hospAdd) {
		this.hospAdd = hospAdd;
	}


	public String getBedname() {
		return bedname;
	}


	public void setBedname(String bedname) {
		this.bedname = bedname;
	}


	public String getPatwardname() {
		return patwardname;
	}


	public void setPatwardname(String patwardname) {
		this.patwardname = patwardname;
	}


	public String getAdmissionNo() {
		return admissionNo;
	}


	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getHospName() {
		return hospName;
	}


	public void setHospName(String hospName) {
		this.hospName = hospName;
	}


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getDocDesig() {
		return docDesig;
	}


	public void setDocDesig(String docDesig) {
		this.docDesig = docDesig;
	}


	public String getDocDept() {
		return docDept;
	}


	public void setDocDept(String docDept) {
		this.docDept = docDept;
	}


	public String getHospAdd1() {
		return hospAdd1;
	}


	public void setHospAdd1(String hospAdd1) {
		this.hospAdd1 = hospAdd1;
	}


	public String getGet_emp_name() {
		return get_emp_name;
	}


	public void setGet_emp_name(String get_emp_name) {
		this.get_emp_name = get_emp_name;
	}


	public String getEpisodeStartDate() {
		return episodeStartDate;
	}


	public void setEpisodeStartDate(String episodeStartDate) {
		this.episodeStartDate = episodeStartDate;
	}


	public String getAdmNo() {
		return admNo;
	}


	public void setAdmNo(String admNo) {
		this.admNo = admNo;
	}


	public String getAdmDate() {
		return admDate;
	}


	public void setAdmDate(String admDate) {
		this.admDate = admDate;
	}


	public String getDisDate() {
		return disDate;
	}


	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}
	
	

}
