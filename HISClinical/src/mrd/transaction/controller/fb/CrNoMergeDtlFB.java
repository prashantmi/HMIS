package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CrNoMergeDtlFB extends CRNoFB
{
	private String patCrNo;
	private String patMainCrNo;
	private String patNotUsedCrNo;
	private String isMerged;
	private String reason;
	private String remarks;
	private String hmode;
	private String hiddenNotUsedCRNo[];
	private String deleteCrNo;
	private String revokedCrNo;
	private String crNoToRetrieve[];
	private String concatedCrNo;
	private String hiddenMergedCRNo[];
	
	////////////search
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String hrgnum_id_no;
	private String hrgstr_contact_no;
	private String hrgstr_national_id;
	
	
	public String[] getCrNoToRetrieve() {
		return crNoToRetrieve;
	}
	public void setCrNoToRetrieve(String[] crNoToRetrieve) {
		this.crNoToRetrieve = crNoToRetrieve;
	}
	public String getDeleteCrNo() {
		return deleteCrNo;
	}
	public void setDeleteCrNo(String deleteCrNo) {
		this.deleteCrNo = deleteCrNo;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatMainCrNo() {
		return patMainCrNo;
	}
	public void setPatMainCrNo(String patMainCrNo) {
		this.patMainCrNo = patMainCrNo;
	}
	public String getPatNotUsedCrNo() {
		return patNotUsedCrNo;
	}
	public void setPatNotUsedCrNo(String patNotUsedCrNo) {
		this.patNotUsedCrNo = patNotUsedCrNo;
	}
	public String getIsMerged() {
		return isMerged;
	}
	public void setIsMerged(String isMerged) {
		this.isMerged = isMerged;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String[] getHiddenNotUsedCRNo() {
		return hiddenNotUsedCRNo;
	}
	public void setHiddenNotUsedCRNo(String[] hiddenNotUsedCRNo) {
		this.hiddenNotUsedCRNo = hiddenNotUsedCRNo;
	}
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
	public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	public String getHrgnum_id_no() {
		return hrgnum_id_no;
	}
	public void setHrgnum_id_no(String hrgnum_id_no) {
		this.hrgnum_id_no = hrgnum_id_no;
	}
	public String getHrgstr_contact_no() {
		return hrgstr_contact_no;
	}
	public void setHrgstr_contact_no(String hrgstr_contact_no) {
		this.hrgstr_contact_no = hrgstr_contact_no;
	}
	public String getHrgstr_national_id() {
		return hrgstr_national_id;
	}
	public void setHrgstr_national_id(String hrgstr_national_id) {
		this.hrgstr_national_id = hrgstr_national_id;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setHrgstr_fname("");
		this.setHrgstr_mname("");
		this.setHrgstr_lname("");
		this.setHrgnum_id_no("");
		this.setHrgstr_contact_no("");
		this.setHrgstr_national_id("");
	}
	public String getConcatedCrNo() {
		return concatedCrNo;
	}
	public void setConcatedCrNo(String concatedCrNo) {
		this.concatedCrNo = concatedCrNo;
	}
	public String[] getHiddenMergedCRNo() {
		return hiddenMergedCRNo;
	}
	public void setHiddenMergedCRNo(String[] hiddenMergedCRNo) {
		this.hiddenMergedCRNo = hiddenMergedCRNo;
	}
	public String getRevokedCrNo() {
		return revokedCrNo;
	}
	public void setRevokedCrNo(String revokedCrNo) {
		this.revokedCrNo = revokedCrNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
