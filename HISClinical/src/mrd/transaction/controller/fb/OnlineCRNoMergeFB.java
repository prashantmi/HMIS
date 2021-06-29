package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OnlineCRNoMergeFB extends ActionForm
{
	private String patMainCrNo;
	private String patNotUsedCrNo;
	private String isMerged;
	private String reason;
	private String remarks;
	private String hmode;
	private String crNoToRetrieve[];
	private String isMainCrNo[];
	
	
	
	////////////search
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String hrgnum_id_no;
	private String hrgstr_contact_no;
	private String hrgstr_national_id;
	private String gnum_gender_code;
	private String hrgstr_father_name;
	private String hbnum_bldgrp_code;
	private String gnum_city_loc_code;
	
	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}
	public String getHrgstr_father_name() {
		return hrgstr_father_name;
	}
	public void setHrgstr_father_name(String hrgstr_father_name) {
		this.hrgstr_father_name = hrgstr_father_name;
	}
	public String getHbnum_bldgrp_code() {
		return hbnum_bldgrp_code;
	}
	public void setHbnum_bldgrp_code(String hbnum_bldgrp_code) {
		this.hbnum_bldgrp_code = hbnum_bldgrp_code;
	}
	public String getGnum_city_loc_code() {
		return gnum_city_loc_code;
	}
	public void setGnum_city_loc_code(String gnum_city_loc_code) {
		this.gnum_city_loc_code = gnum_city_loc_code;
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
	public String[] getCrNoToRetrieve() {
		return crNoToRetrieve;
	}
	public void setCrNoToRetrieve(String[] crNoToRetrieve) {
		this.crNoToRetrieve = crNoToRetrieve;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setHrgstr_fname("");
		this.setHrgstr_mname("");
		this.setHrgstr_lname("");
		this.setHrgnum_id_no("");
		this.setHrgstr_contact_no("");
		this.setHrgstr_national_id("");
		this.setHrgstr_father_name("");
		this.setGnum_gender_code("");
		this.setGnum_city_loc_code("");
		
	}
	public String[] getIsMainCrNo() {
		return isMainCrNo;
	}
	public void setIsMainCrNo(String[] isMainCrNo) {
		this.isMainCrNo = isMainCrNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
