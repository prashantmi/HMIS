package opd.icdsearchengine.vo;

import hisglobal.vo.ValueObject;

public class ResultDiseaseListVO extends ValueObject
{
		// Disease Detail
	private String diseaseCode;
	private String fullDiseaseName;
	private String recordType;
	private String haveChildren;
	private String slNo;

	
	
	
	
	
	
	
	
	private String strMainMode;
	private String strCurOption;
	private boolean strSearchStart;
	private String strSearchString;
	private String strSearchType;
	
	// Searching
	private String strIcdDiseaseName;
	private String strIcdCodeName;
	private String strIcdCodeMain;
	private String strIcdCodeSub;
	private String strRecordType;
	private String strDiseaseTypeCode;
	private String strChronicDiseaseCode;
	private String strOrderBy;

	
	
	
	
	
	
	
	
	private String hospitalCode;
	private String icdSubgroupCode;
	private String disease;
	private String parentCode;
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

	public String getDiseaseTypeName()
	{
		return diseaseTypeName;
	}

	public void setDiseaseTypeName(String diseaseTypeName)
	{
		this.diseaseTypeName = diseaseTypeName;
	}

	public String getFullDiseaseName()
	{
		return fullDiseaseName;
	}

	public void setFullDiseaseName(String fullDiseaseName)
	{
		this.fullDiseaseName = fullDiseaseName;
	}

	public String getHaveChildren()
	{
		return haveChildren;
	}

	public void setHaveChildren(String haveChildren)
	{
		this.haveChildren = haveChildren;
	}

	public String getStrMainMode()
	{
		return strMainMode;
	}

	public void setStrMainMode(String strMainMode)
	{
		this.strMainMode = strMainMode;
	}

	public String getStrCurOption()
	{
		return strCurOption;
	}

	public void setStrCurOption(String strCurOption)
	{
		this.strCurOption = strCurOption;
	}

	public boolean isStrSearchStart()
	{
		return strSearchStart;
	}

	public void setStrSearchStart(boolean searchStart)
	{
		strSearchStart = searchStart;
	}

	public String getStrSearchString()
	{
		return strSearchString;
	}

	public void setStrSearchString(String strSearchString)
	{
		this.strSearchString = strSearchString;
	}

	public String getStrSearchType()
	{
		return strSearchType;
	}

	public void setStrSearchType(String strSearchType)
	{
		this.strSearchType = strSearchType;
	}

	public String getStrIcdDiseaseName()
	{
		return strIcdDiseaseName;
	}

	public void setStrIcdDiseaseName(String strIcdDiseaseName)
	{
		this.strIcdDiseaseName = strIcdDiseaseName;
	}

	public String getStrIcdCodeName()
	{
		return strIcdCodeName;
	}

	public void setStrIcdCodeName(String strIcdCodeName)
	{
		this.strIcdCodeName = strIcdCodeName;
	}

	public String getStrIcdCodeMain()
	{
		return strIcdCodeMain;
	}

	public void setStrIcdCodeMain(String strIcdCodeMain)
	{
		this.strIcdCodeMain = strIcdCodeMain;
	}

	public String getStrIcdCodeSub()
	{
		return strIcdCodeSub;
	}

	public void setStrIcdCodeSub(String strIcdCodeSub)
	{
		this.strIcdCodeSub = strIcdCodeSub;
	}

	public String getStrRecordType()
	{
		return strRecordType;
	}

	public void setStrRecordType(String strRecordType)
	{
		this.strRecordType = strRecordType;
	}

	public String getStrDiseaseTypeCode()
	{
		return strDiseaseTypeCode;
	}

	public void setStrDiseaseTypeCode(String strDiseaseTypeCode)
	{
		this.strDiseaseTypeCode = strDiseaseTypeCode;
	}

	public String getStrChronicDiseaseCode()
	{
		return strChronicDiseaseCode;
	}

	public void setStrChronicDiseaseCode(String strChronicDiseaseCode)
	{
		this.strChronicDiseaseCode = strChronicDiseaseCode;
	}

	public String getStrOrderBy()
	{
		return strOrderBy;
	}

	public void setStrOrderBy(String strOrderBy)
	{
		this.strOrderBy = strOrderBy;
	}
	
	
	
}

