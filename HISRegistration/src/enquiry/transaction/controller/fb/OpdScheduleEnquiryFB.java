package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpdScheduleEnquiryFB extends ActionForm {
	

	private String isDirectCall;
	
	private String department;
	private String departmentCode;
	private String departmentUnitCode;
	private String departmentUnit;
	private String hmode;
	private String hod;
	private String hou;
	private String searchText;
	private String list;
	private int currentPageSpecialClinic=1;
	private int currentPageDept=1;
	private int currentPage=1;
	private String flag="false";
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.currentPageSpecialClinic=1;
		this.currentPageDept=1;
		this.currentPage=1;
		this.searchText="";
		this.departmentCode="";
		this.departmentUnitCode="";
		this.flag="false";
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

	public String getDepartmentUnit() {
		return departmentUnit;
	}

	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getHou() {
		return hou;
	}

	public void setHou(String hou) {
		this.hou = hou;
	}

	public int getCurrentPageSpecialClinic() {
		return currentPageSpecialClinic;
	}

	public void setCurrentPageSpecialClinic(int currentPageSpecialClinic) {
		this.currentPageSpecialClinic = currentPageSpecialClinic;
	}

	public int getCurrentPageDept() {
		return currentPageDept;
	}

	public void setCurrentPageDept(int currentPageDept) {
		this.currentPageDept = currentPageDept;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
