package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DiseaseSiteFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;
	private String controls[];

	private String diseaseSiteId;
	private String hospitalCode;
	private String diseaseSite;
	private String isValid;
	private String icdSiteCode;
	private String icdCodeType;
	private String seatId;
	private String entryDate;
	private String icdSubgroupDiseaseCode;

	private String icdGroupCode;
	private String icdGroup;
	private String icdSubgroupCode;
	private String icdSubgroup;
	private String diseaseCode;
	private String disease;

	public DiseaseSiteFB()
	{
		this.diseaseSiteId = "";
		this.hospitalCode = "";
		this.diseaseSite = "";
		this.isValid = "";
		this.icdSiteCode = "";
		this.icdCodeType = OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED;
		this.seatId = "";
		this.entryDate = "";
		this.icdSubgroupDiseaseCode = "";
		
		this.icdGroupCode = "";
		this.icdGroup = "";
		this.icdSubgroupCode = "";
		this.icdSubgroup = "";
		this.diseaseCode = "";
		this.disease = "";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.diseaseSiteId = "";
		this.hospitalCode = "";
		this.diseaseSite = "";
		this.isValid = "";
		this.icdSiteCode = "";
		this.icdCodeType = OpdConfig.ICD_DISEASE_SITE_CODETYPE_NOT_REQUIRED;
		this.seatId = "";
		this.entryDate = "";
		this.icdSubgroupDiseaseCode = "";
		
		this.icdGroupCode = "";
		this.icdGroup = "";
		this.icdSubgroupCode = "";
		this.icdSubgroup = "";
		this.diseaseCode = "";
		this.disease = "";
	}

	public String getHmode()
	{
		return hmode;
	}

	public void setHmode(String hmode)
	{
		this.hmode = hmode;
	}

	public String[] getChk()
	{
		return chk;
	}

	public void setChk(String[] chk)
	{
		this.chk = chk;
	}

	public String getIsActive()
	{
		return isActive;
	}

	public void setIsActive(String isActive)
	{
		this.isActive = isActive;
	}

	public String[] getControls()
	{
		return controls;
	}

	public void setControls(String[] controls)
	{
		this.controls = controls;
	}

	public String getDiseaseSiteId()
	{
		return diseaseSiteId;
	}

	public void setDiseaseSiteId(String diseaseSiteId)
	{
		this.diseaseSiteId = diseaseSiteId;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getDiseaseSite()
	{
		return diseaseSite;
	}

	public void setDiseaseSite(String diseaseSite)
	{
		this.diseaseSite = diseaseSite;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getIcdSiteCode()
	{
		return icdSiteCode;
	}

	public void setIcdSiteCode(String icdSiteCode)
	{
		this.icdSiteCode = icdSiteCode;
	}

	public String getIcdCodeType()
	{
		return icdCodeType;
	}

	public void setIcdCodeType(String icdCodeType)
	{
		this.icdCodeType = icdCodeType;
	}

	public String getSeatId()
	{
		return seatId;
	}

	public void setSeatId(String seatId)
	{
		this.seatId = seatId;
	}

	public String getEntryDate()
	{
		return entryDate;
	}

	public void setEntryDate(String entryDate)
	{
		this.entryDate = entryDate;
	}

	public String getIcdSubgroupDiseaseCode()
	{
		return icdSubgroupDiseaseCode;
	}

	public void setIcdSubgroupDiseaseCode(String icdSubgroupDiseaseCode)
	{
		this.icdSubgroupDiseaseCode = icdSubgroupDiseaseCode;
	}

	public String getIcdGroupCode()
	{
		return icdGroupCode;
	}

	public void setIcdGroupCode(String icdGroupCode)
	{
		this.icdGroupCode = icdGroupCode;
	}

	public String getIcdGroup()
	{
		return icdGroup;
	}

	public void setIcdGroup(String icdGroup)
	{
		this.icdGroup = icdGroup;
	}

	public String getIcdSubgroupCode()
	{
		return icdSubgroupCode;
	}

	public void setIcdSubgroupCode(String icdSubgroupCode)
	{
		this.icdSubgroupCode = icdSubgroupCode;
	}

	public String getIcdSubgroup()
	{
		return icdSubgroup;
	}

	public void setIcdSubgroup(String icdSubgroup)
	{
		this.icdSubgroup = icdSubgroup;
	}

	public String getDiseaseCode()
	{
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode)
	{
		this.diseaseCode = diseaseCode;
	}

	public String getDisease()
	{
		return disease;
	}

	public void setDisease(String disease)
	{
		this.disease = disease;
	}
}
