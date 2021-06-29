package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SummonAssigmentFB extends ActionForm
{
	private String hmode;
	private int currentPage;
	private String selectedSummonId;
	private String empName;
	private String empDesignation;
	private String empAge;
	private String empGenderCode;
	private String empAddress;
	private String empAgeType;
	private String assignFlag;
	private String otherAssignReason;
	private String selectedEmpId;
	
	private String str_first_name;
	private String str_middle_name;
	private String str_last_name;
	
	private String[] empIdArray;
	private String[] empNameArray;
	private String[] empDesigArray;
	private String[] empGenderCodeArray;
	private String[] empAddressArray;
	private String[] empAgeArray; 
	private String searchEmpName;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.selectedSummonId="";
		this.otherAssignReason="";
		this.assignFlag="";
	}

	public String getSearchEmpName() {
		return searchEmpName;
	}

	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}

	public String[] getEmpIdArray() {
		return empIdArray;
	}

	public void setEmpIdArray(String[] empIdArray) {
		this.empIdArray = empIdArray;
	}

	public String[] getEmpNameArray() {
		return empNameArray;
	}

	public void setEmpNameArray(String[] empNameArray) {
		this.empNameArray = empNameArray;
	}

	public String[] getEmpDesigArray() {
		return empDesigArray;
	}

	public void setEmpDesigArray(String[] empDesigArray) {
		this.empDesigArray = empDesigArray;
	}

	public String[] getEmpGenderCodeArray() {
		return empGenderCodeArray;
	}

	public void setEmpGenderCodeArray(String[] empGenderCodeArray) {
		this.empGenderCodeArray = empGenderCodeArray;
	}

	public String[] getEmpAddressArray() {
		return empAddressArray;
	}

	public void setEmpAddressArray(String[] empAddressArray) {
		this.empAddressArray = empAddressArray;
	}

	public String getSelectedEmpId() {
		return selectedEmpId;
	}

	public void setSelectedEmpId(String selectedEmpId) {
		this.selectedEmpId = selectedEmpId;
	}

	public String getOtherAssignReason() {
		return otherAssignReason;
	}

	public void setOtherAssignReason(String otherAssignReason) {
		this.otherAssignReason = otherAssignReason;
	}

	public String getAssignFlag() {
		return assignFlag;
	}

	public void setAssignFlag(String assignFlag) {
		this.assignFlag = assignFlag;
	}

	public String getEmpAgeType() {
		return empAgeType;
	}

	public void setEmpAgeType(String empAgeType) {
		this.empAgeType = empAgeType;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpAge() {
		return empAge;
	}

	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}

	public String getEmpGenderCode() {
		return empGenderCode;
	}

	public void setEmpGenderCode(String empGenderCode) {
		this.empGenderCode = empGenderCode;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSelectedSummonId() {
		return selectedSummonId;
	}

	public void setSelectedSummonId(String selectedSummonId) {
		this.selectedSummonId = selectedSummonId;
	}

	public SummonAssigmentFB()
	{
		this.currentPage=1;
	}

	public String[] getEmpAgeArray() {
		return empAgeArray;
	}

	public void setEmpAgeArray(String[] empAgeArray) {
		this.empAgeArray = empAgeArray;
	}

	public String getStr_first_name() {
		return str_first_name;
	}

	public void setStr_first_name(String str_first_name) {
		this.str_first_name = str_first_name;
	}

	public String getStr_middle_name() {
		return str_middle_name;
	}

	public void setStr_middle_name(String str_middle_name) {
		this.str_middle_name = str_middle_name;
	}

	public String getStr_last_name() {
		return str_last_name;
	}

	public void setStr_last_name(String str_last_name) {
		this.str_last_name = str_last_name;
	}
}
