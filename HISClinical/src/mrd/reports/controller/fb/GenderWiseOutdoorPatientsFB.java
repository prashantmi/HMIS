package mrd.reports.controller.fb;

import hisglobal.presentation.ReportFB;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class GenderWiseOutdoorPatientsFB extends ReportFB
{
	private String patCat;
	private String gender;
	private String label;
	private String maleCode;
	private String femaleCode;
	private String othersCode;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		super.reset(mapping,request);
		//this.setDepartmentCode("-1");
		this.setPatCat("%");	
		this.setGender("%");
		//this.setChoice(MrdConfig.DEPT_WISE);
		
	}

	public String getPatCat() {
		return patCat;
	}

	public void setPatCat(String patCat) {
		this.patCat = patCat;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaleCode() {
		return maleCode;
	}

	public void setMaleCode(String maleCode) {
		this.maleCode = maleCode;
	}

	public String getFemaleCode() {
		return femaleCode;
	}

	public void setFemaleCode(String femaleCode) {
		this.femaleCode = femaleCode;
	}

	public String getOthersCode() {
		return othersCode;
	}

	public void setOthersCode(String othersCode) {
		this.othersCode = othersCode;
	}

}
