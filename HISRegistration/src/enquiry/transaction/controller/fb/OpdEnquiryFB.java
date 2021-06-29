package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdEnquiryFB extends ActionForm {

	
	private String gnum_dept_code;
	private String hgnum_deptunitcode;
	private String hopnum_day_of_week;
	private String hopnum_week_of_month;
	private String hmode;
	private String headOfUnit;
	private String deptName;
	private String unitName;
	private String timing;
	private String isDirectCall;
	private String hospitalCode;
	private String hospitalName;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.setDeptName("");
		this.setGnum_dept_code("");
		this.setHeadOfUnit("");
		this.setHgnum_deptunitcode("");
		this.setHopnum_day_of_week("");
		this.setHopnum_week_of_month("");
		this.setTiming("");
		this.setUnitName("");
		
	}
	
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getHeadOfUnit() {
		return headOfUnit;
	}
	public void setHeadOfUnit(String headOfUnit) {
		this.headOfUnit = headOfUnit;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getGnum_dept_code() {
		return gnum_dept_code;
	}
	public void setGnum_dept_code(String gnum_dept_code) {
		this.gnum_dept_code = gnum_dept_code;
	}
	public String getHgnum_deptunitcode() {
		return hgnum_deptunitcode;
	}
	public void setHgnum_deptunitcode(String hgnum_deptunitcode) {
		this.hgnum_deptunitcode = hgnum_deptunitcode;
	}
	public String getHopnum_day_of_week() {
		return hopnum_day_of_week;
	}
	public void setHopnum_day_of_week(String hopnum_day_of_week) {
		this.hopnum_day_of_week = hopnum_day_of_week;
	}
	public String getHopnum_week_of_month() {
		return hopnum_week_of_month;
	}
	public void setHopnum_week_of_month(String hopnum_week_of_month) {
		this.hopnum_week_of_month = hopnum_week_of_month;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
}
