package opd.icdsearchengine.vo;

import hisglobal.vo.ValueObject;

public class ICDDiseaseDetailVO extends ValueObject
{
	private String diseaseCode;
	private String recordType;
	private String slNo;
	private String fullDiseaseName;
	private String mainDisease;

	private String haveSynonyms;
	private String haveIncludes;
	private String haveExcludes;
	private String haveChronicDiseases;
	private String haveSubGroups;
	private String haveChildren;

	private String[] arrStrSynonymCodes;
	private String[] arrStrSynonyms;
	private String[] arrStrIncludeCodes;
	private String[] arrStrIncludes;
	private String diseaseTypeCode;
	private String diseaseTypeName;
	private String[] arrStrExcludeCodes;
	private String[] arrStrExcludes;
	private String[] arrStrChronicDiseaseCodes;
	private String[] arrStrChronicDiseases;
	private String diseaseRemark;

	// More
	private String icdGroupCode;
	private String icdGroup;
	private String[] icdSubgroupCode;
	private String[] icdSubgroup;
	private String[] icdSubdiseaseCodes;
	private String[] icdSubdiseases;
	private String parentCode;
	private String parentDisease;

	public ICDDiseaseDetailVO()
	{
		this.diseaseCode="";
		this.recordType="";
		this.slNo="";
		this.fullDiseaseName="";
		this.mainDisease="";

		this.haveSynonyms="";
		this.haveIncludes="";
		this.haveExcludes="";
		this.haveChronicDiseases="";
		this.haveSubGroups="";
		this.haveChildren="";

		this.arrStrSynonymCodes = new String[0];
		this.arrStrSynonyms=new String[0];
		this.arrStrIncludeCodes=new String[0];
		this.arrStrIncludes=new String[0];
		this.diseaseTypeCode="";
		this.diseaseTypeName="";
		this.arrStrExcludeCodes=new String[0];
		this.arrStrExcludes=new String[0];
		this.arrStrChronicDiseaseCodes=new String[0];
		this.arrStrChronicDiseases=new String[0];
		this.diseaseRemark="";

		// More
		this.icdGroupCode="";
		this.icdGroup="";
		this.icdSubgroupCode=new String[0];
		this.icdSubgroup=new String[0];
		this.icdSubdiseaseCodes=new String[0];
		this.icdSubdiseases=new String[0];
		this.parentCode="";
		this.parentDisease="";
		
	}
	
	public String getDiseaseCode()
	{
		return diseaseCode;
	}

	public void setDiseaseCode(String diseaseCode)
	{
		this.diseaseCode = diseaseCode;
	}

	public String getRecordType()
	{
		return recordType;
	}

	public void setRecordType(String recordType)
	{
		this.recordType = recordType;
	}

	public String getSlNo()
	{
		return slNo;
	}

	public void setSlNo(String slNo)
	{
		this.slNo = slNo;
	}

	public String getMainDisease()
	{
		return mainDisease;
	}

	public void setMainDisease(String mainDisease)
	{
		this.mainDisease = mainDisease;
	}

	public String[] getArrStrSynonymCodes()
	{
		return arrStrSynonymCodes;
	}

	public void setArrStrSynonymCodes(String[] arrStrSynonymCodes)
	{
		this.arrStrSynonymCodes = arrStrSynonymCodes;
	}

	public String[] getArrStrSynonyms()
	{
		return arrStrSynonyms;
	}

	public void setArrStrSynonyms(String[] arrStrSynonyms)
	{
		this.arrStrSynonyms = arrStrSynonyms;
	}

	public String[] getArrStrIncludeCodes()
	{
		return arrStrIncludeCodes;
	}

	public void setArrStrIncludeCodes(String[] arrStrIncludeCodes)
	{
		this.arrStrIncludeCodes = arrStrIncludeCodes;
	}

	public String[] getArrStrIncludes()
	{
		return arrStrIncludes;
	}

	public void setArrStrIncludes(String[] arrStrIncludes)
	{
		this.arrStrIncludes = arrStrIncludes;
	}

	public String getDiseaseTypeCode()
	{
		return diseaseTypeCode;
	}

	public void setDiseaseTypeCode(String diseaseTypeCode)
	{
		this.diseaseTypeCode = diseaseTypeCode;
	}

	public String getDiseaseTypeName()
	{
		return diseaseTypeName;
	}

	public void setDiseaseTypeName(String diseaseTypeName)
	{
		this.diseaseTypeName = diseaseTypeName;
	}

	public String[] getArrStrExcludeCodes()
	{
		return arrStrExcludeCodes;
	}

	public void setArrStrExcludeCodes(String[] arrStrExcludeCodes)
	{
		this.arrStrExcludeCodes = arrStrExcludeCodes;
	}

	public String[] getArrStrExcludes()
	{
		return arrStrExcludes;
	}

	public void setArrStrExcludes(String[] arrStrExcludes)
	{
		this.arrStrExcludes = arrStrExcludes;
	}

	public String[] getArrStrChronicDiseaseCodes()
	{
		return arrStrChronicDiseaseCodes;
	}

	public void setArrStrChronicDiseaseCodes(String[] arrStrChronicDiseaseCodes)
	{
		this.arrStrChronicDiseaseCodes = arrStrChronicDiseaseCodes;
	}

	public String[] getArrStrChronicDiseases()
	{
		return arrStrChronicDiseases;
	}

	public void setArrStrChronicDiseases(String[] arrStrChronicDiseases)
	{
		this.arrStrChronicDiseases = arrStrChronicDiseases;
	}

	public String getDiseaseRemark()
	{
		return diseaseRemark;
	}

	public void setDiseaseRemark(String diseaseRemark)
	{
		this.diseaseRemark = diseaseRemark;
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

	public String[] getIcdSubgroupCode()
	{
		return icdSubgroupCode;
	}

	public void setIcdSubgroupCode(String[] icdSubgroupCode)
	{
		this.icdSubgroupCode = icdSubgroupCode;
	}

	public String[] getIcdSubgroup()
	{
		return icdSubgroup;
	}

	public void setIcdSubgroup(String[] icdSubgroup)
	{
		this.icdSubgroup = icdSubgroup;
	}

	public String getHaveChildren()
	{
		return haveChildren;
	}

	public void setHaveChildren(String haveChildren)
	{
		this.haveChildren = haveChildren;
	}

	public String[] getIcdSubdiseaseCodes()
	{
		return icdSubdiseaseCodes;
	}

	public void setIcdSubdiseaseCodes(String[] icdSubdiseaseCodes)
	{
		this.icdSubdiseaseCodes = icdSubdiseaseCodes;
	}

	public String[] getIcdSubdiseases()
	{
		return icdSubdiseases;
	}

	public void setIcdSubdiseases(String[] icdSubdiseases)
	{
		this.icdSubdiseases = icdSubdiseases;
	}

	public String getParentCode()
	{
		return parentCode;
	}

	public void setParentCode(String parentCode)
	{
		this.parentCode = parentCode;
	}

	public String getFullDiseaseName()
	{
		return fullDiseaseName;
	}

	public void setFullDiseaseName(String fullDiseaseName)
	{
		this.fullDiseaseName = fullDiseaseName;
	}

	public String getHaveSynonyms()
	{
		return haveSynonyms;
	}

	public void setHaveSynonyms(String haveSynonyms)
	{
		this.haveSynonyms = haveSynonyms;
	}

	public String getHaveIncludes()
	{
		return haveIncludes;
	}

	public void setHaveIncludes(String haveIncludes)
	{
		this.haveIncludes = haveIncludes;
	}

	public String getHaveExcludes()
	{
		return haveExcludes;
	}

	public void setHaveExcludes(String haveExcludes)
	{
		this.haveExcludes = haveExcludes;
	}

	public String getHaveChronicDiseases()
	{
		return haveChronicDiseases;
	}

	public void setHaveChronicDiseases(String haveChronicDiseases)
	{
		this.haveChronicDiseases = haveChronicDiseases;
	}

	public String getHaveSubGroups()
	{
		return haveSubGroups;
	}

	public void setHaveSubGroups(String haveSubGroups)
	{
		this.haveSubGroups = haveSubGroups;
	}

	public String getParentDisease()
	{
		return parentDisease;
	}

	public void setParentDisease(String parentDisease)
	{
		this.parentDisease = parentDisease;
	}
}
