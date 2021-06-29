package dutyroster.reports.controller.fb;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmpDailyWorkReportFB extends ActionForm {

	private String workingDate;
	private String rosterMainCategory;
	private String rosterCategory;
	private String rosterId;
	private String areaTypeCode;
	private String areaCode;
	private String empId;
	private String empName;
	private String designationName;
	

	private String hmode;
	private String hospitalCode;
		

	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		 this.workingDate="";
		 this.rosterMainCategory="";
		 this.rosterCategory="";
		 this.rosterId="";
		 this.areaTypeCode="";
		 this.areaCode="";
		 this.empId="";
		 this.empName="";
		 this.designationName="";
		 this.hmode="";
		 this.hospitalCode="";
				
	}



	public String getWorkingDate() {
		return workingDate;
	}



	public void setWorkingDate(String workingDate) {
		this.workingDate = workingDate;
	}



	public String getRosterMainCategory() {
		return rosterMainCategory;
	}



	public void setRosterMainCategory(String rosterMainCategory) {
		this.rosterMainCategory = rosterMainCategory;
	}



	public String getRosterCategory() {
		return rosterCategory;
	}



	public void setRosterCategory(String rosterCategory) {
		this.rosterCategory = rosterCategory;
	}



	public String getRosterId() {
		return rosterId;
	}



	public void setRosterId(String rosterId) {
		this.rosterId = rosterId;
	}



	public String getAreaTypeCode() {
		return areaTypeCode;
	}



	public void setAreaTypeCode(String areaTypeCode) {
		this.areaTypeCode = areaTypeCode;
	}



	public String getAreaCode() {
		return areaCode;
	}



	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}



	public String getEmpId() {
		return empId;
	}



	public void setEmpId(String empId) {
		this.empId = empId;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getDesignationName() {
		return designationName;
	}



	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}



	public String getHmode() {
		return hmode;
	}



	public void setHmode(String hmode) {
		this.hmode = hmode;
	}



	public String getHospitalCode() {
		return hospitalCode;
	}



	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	


	

		
}
