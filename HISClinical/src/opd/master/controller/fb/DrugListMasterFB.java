package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DrugListMasterFB extends ActionForm
{
	private String hmode;
	private String drugListName;
	private String diseaseCode;
	private String remark;
	private String chk[];
	private String drugListId;
	private String isValid;
	private String slNo;
	
	public String getSlNo() {
		return slNo;
	}



	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}



	public String getIsValid() {
		return isValid;
	}



	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}



	public String getDrugListId() {
		return drugListId;
	}



	public void setDrugListId(String drugListId) {
		this.drugListId = drugListId;
	}



	public String[] getChk() {
		return chk;
	}



	public void setChk(String[] chk) {
		this.chk = chk;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.drugListName="";
		this.diseaseCode="";
		this.remark="";
	}

	

	public String getDrugListName() {
		return drugListName;
	}

	public void setDrugListName(String drugListName) {
		this.drugListName = drugListName;
	}

	public String getDiseaseCode() {
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
}
