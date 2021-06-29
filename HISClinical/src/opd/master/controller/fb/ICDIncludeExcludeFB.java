package opd.master.controller.fb;

import javax.servlet.http.HttpServletRequest;

import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ICDIncludeExcludeFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;
	private String controls[];

	private String diseaseCode;
	private String hospitalCode;
	private String slNo;
	private String icdSubgroupCode;
	private String disease;
	private String parentCode;
	private String recordType;
	private String seatId;
	private String entryDate;
	private String isCommon;
	private String diseaseRemark;
	private String isValid;
	private String mainDisease;
	private String diseaseTypeCode;

	private String icdGroupCode;
	private String icdGroup;
	private String icdSubgroup;
	private String diseaseTypeName;
	
	private String isDiseaseDetail;
	
	public ICDIncludeExcludeFB()
	{
		this.chk = new String[2];
		this.controls = new String[3];
		this.isActive = "";

		this.slNo = "";
		this.icdGroupCode = "";
		this.icdGroup = "";
		this.icdSubgroupCode = "";
		this.icdSubgroup = "";
		this.diseaseCode = "";
		this.disease = "";
		this.parentCode = "";
		this.recordType = "";
		this.isCommon = "";
		this.diseaseRemark = "";
		this.mainDisease = "";
		this.diseaseTypeCode = "";
		this.diseaseTypeName = "";
		this.diseaseTypeCode = "";
		
		this.isDiseaseDetail = OpdConfig.NO;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.isActive = "";

		this.slNo = "";
		this.disease = "";
		this.parentCode = "";
		this.recordType = "";
		this.isCommon = "";
		this.diseaseRemark = "";
		this.diseaseTypeCode = "";
		this.diseaseTypeName = "";
		this.diseaseTypeCode = "";
		
		this.isDiseaseDetail = OpdConfig.NO;
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

	public String getDiseaseCode()
	{
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode)
	{
		this.diseaseCode = diseaseCode;
	}

	public String getHospitalCode()
	{
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode)
	{
		this.hospitalCode = hospitalCode;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getIcdSubgroupCode()
	{
		return icdSubgroupCode;
	}

	public void setIcdSubgroupCode(String icdSubgroupCode)
	{
		this.icdSubgroupCode = icdSubgroupCode;
	}

	public String getDisease()
	{
		return disease;
	}

	public void setDisease(String disease)
	{
		this.disease = disease;
	}

	public String getParentCode()
	{
		return parentCode;
	}

	public void setParentCode(String parentCode)
	{
		this.parentCode = parentCode;
	}

	public String getRecordType()
	{
		return recordType;
	}

	public void setRecordType(String recordType)
	{
		this.recordType = recordType;
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

	public String getIsCommon()
	{
		return isCommon;
	}

	public void setIsCommon(String isCommon)
	{
		this.isCommon = isCommon;
	}

	public String getDiseaseRemark()
	{
		return diseaseRemark;
	}

	public void setDiseaseRemark(String diseaseRemark)
	{
		this.diseaseRemark = diseaseRemark;
	}

	public String getIsValid()
	{
		return isValid;
	}

	public void setIsValid(String isValid)
	{
		this.isValid = isValid;
	}

	public String getMainDisease()
	{
		return mainDisease;
	}

	public void setMainDisease(String mainDisease)
	{
		this.mainDisease = mainDisease;
	}

	public String getDiseaseTypeCode()
	{
		return diseaseTypeCode;
	}

	public void setDiseaseTypeCode(String diseaseTypeCode)
	{
		this.diseaseTypeCode = diseaseTypeCode;
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

	public String getIcdSubgroup()
	{
		return icdSubgroup;
	}

	public void setIcdSubgroup(String icdSubgroup)
	{
		this.icdSubgroup = icdSubgroup;
	}

	public String getIsDiseaseDetail()
	{
		return isDiseaseDetail;
	}

	public void setIsDiseaseDetail(String isDiseaseDetail)
	{
		this.isDiseaseDetail = isDiseaseDetail;
	}

	public String getDiseaseTypeName()
	{
		return diseaseTypeName;
	}

	public void setDiseaseTypeName(String diseaseTypeName)
	{
		this.diseaseTypeName = diseaseTypeName;
	}

	public String[] getControls()
	{
		return controls;
	}

	public void setControls(String[] controls)
	{
		this.controls = controls;
	}

}
