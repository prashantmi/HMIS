package opd.icdsearchengine.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ICDEngineDiseaseDetailFB extends ActionForm
{
	private String strDetailMode;	// NEW, SHOW, PREV, NEXT
	private String curIndex;
	private boolean strIsNext;
	private boolean strIsPrev;
	
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
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.strIsNext = false;
		this.strIsPrev = false;
	}

	public String getStrDetailMode()
	{
		return strDetailMode;
	}

	public void setStrDetailMode(String strDetailMode)
	{
		this.strDetailMode = strDetailMode;
	}

	public String getCurIndex()
	{
		return curIndex;
	}

	public void setCurIndex(String curIndex)
	{
		this.curIndex = curIndex;
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

	public String getFullDiseaseName()
	{
		return fullDiseaseName;
	}

	public void setFullDiseaseName(String fullDiseaseName)
	{
		this.fullDiseaseName = fullDiseaseName;
	}

	public String getMainDisease()
	{
		return mainDisease;
	}

	public void setMainDisease(String mainDisease)
	{
		this.mainDisease = mainDisease;
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

	public String getHaveChildren()
	{
		return haveChildren;
	}

	public void setHaveChildren(String haveChildren)
	{
		this.haveChildren = haveChildren;
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

	public String getParentDisease()
	{
		return parentDisease;
	}

	public void setParentDisease(String parentDisease)
	{
		this.parentDisease = parentDisease;
	}

	public boolean isStrIsNext()
	{
		return strIsNext;
	}

	public void setStrIsNext(boolean isNext)
	{
		strIsNext = isNext;
	}

	public boolean isStrIsPrev()
	{
		return strIsPrev;
	}

	public void setStrIsPrev(boolean isPrev)
	{
		strIsPrev = isPrev;
	}

}
