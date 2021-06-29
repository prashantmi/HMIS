package opd.icdsearchengine.fb;


import javax.servlet.http.HttpServletRequest;

import opd.icdsearchengine.ICDSearchEngineConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ICDSearchEngineFB extends ActionForm
{
	private String strMainMode;
	private String strCurOption;
	private boolean strSearchStart;
	private String strSearchType;
	
	// Searching
	private String strIcdCodeName;
	private String strExactIcdCode;
		// Volume 1
	private String strIcdDiseaseName;
	private String strIcdCodeMain;
	private String strIcdCodeSub;
	private String strRecordType;
	private String strMainDiseaseTypeCode;
	private String strDiseaseTypeCode;
	private String strChronicDiseaseCode;
	private String strOrderBy;
		// Voulume 3
	private String strIndexSearchString;
	private String strAutoCompleteString;
	private String strIndexCode;
		// All 
	private String strDiseaseName;
		// Reports
	private String strReportOption;
	private String strIcdGroupCode;
	private String strIcdSubGroupCode;
	private String strIcdDiseaseCode;
	private String strHospitalCode;
	private String jrxmlPath;
	private String jrxmlName;
	private String strReportType;// rtf/pdf
	//private String reportMode;// report or chart
	//private String mode;// report or chart
	//private String choice;
	//private String graphOrText;
	//private String chartType;
	//private String sysDate;
	//private String title;


	
	public ICDSearchEngineFB()
	{
		this.strCurOption = ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_DEFAULT;
		this.strRecordType = ICDSearchEngineConfig.ICD_DISEASE_RECORD_TYPE_DISEASE;
		this.strOrderBy = ICDSearchEngineConfig.ORDER_BY_DISEASE_NAME;
		this.strSearchType = ICDSearchEngineConfig.ICD_ENGINE_SEARCH_TYPE_DISEASE_NAME;

		this.strIcdCodeName="";
		this.strExactIcdCode="";
			// Volume 1
		this.strIcdDiseaseName="";
		this.strIcdCodeMain="";
		this.strIcdCodeSub="";
		this.strRecordType="";
		this.strMainDiseaseTypeCode="";
		this.strDiseaseTypeCode="";
		this.strChronicDiseaseCode="";
		this.strOrderBy="";
			// Voulume 3
		this.strIndexSearchString="";
		this.strAutoCompleteString="0";
		this.strIndexCode="";
			// All 
		this.strDiseaseName="";
			// Reports
		this.strIcdGroupCode="";
		this.strIcdSubGroupCode="";
		this.strIcdDiseaseCode="";
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.strIcdCodeName="";
		this.strExactIcdCode="";
			// Volume 1
		this.strIcdDiseaseName="";
		this.strIcdCodeMain="";
		this.strIcdCodeSub="";
		this.strRecordType="";
		this.strMainDiseaseTypeCode="";
		this.strDiseaseTypeCode="";
		this.strChronicDiseaseCode="";
		this.strOrderBy="";
			// Voulume 3
		this.strIndexSearchString="";
		this.strAutoCompleteString="0";
		this.strIndexCode="";
			// All 
		this.strDiseaseName="";
		// Reports
		this.strIcdGroupCode="";
		this.strIcdSubGroupCode="";
		this.strIcdDiseaseCode="";
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

	public String getStrIndexSearchString()
	{
		return strIndexSearchString;
	}

	public void setStrIndexSearchString(String strIndexSearchString)
	{
		this.strIndexSearchString = strIndexSearchString;
	}

	public boolean isStrSearchStart()
	{
		return strSearchStart;
	}

	public void setStrSearchStart(boolean searchStart)
	{
		strSearchStart = searchStart;
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

	public String getStrSearchType()
	{
		return strSearchType;
	}

	public void setStrSearchType(String strSearchType)
	{
		this.strSearchType = strSearchType;
	}

	public String getStrExactIcdCode()
	{
		return strExactIcdCode;
	}

	public void setStrExactIcdCode(String strExactIcdCode)
	{
		this.strExactIcdCode = strExactIcdCode;
	}

	public String getStrMainDiseaseTypeCode()
	{
		return strMainDiseaseTypeCode;
	}

	public void setStrMainDiseaseTypeCode(String strMainDiseaseTypeCode)
	{
		this.strMainDiseaseTypeCode = strMainDiseaseTypeCode;
	}

	public String getStrDiseaseName()
	{
		return strDiseaseName;
	}

	public void setStrDiseaseName(String strDiseaseName)
	{
		this.strDiseaseName = strDiseaseName;
	}

	public String getStrAutoCompleteString()
	{
		return strAutoCompleteString;
	}

	public void setStrAutoCompleteString(String strAutoCompleteString)
	{
		this.strAutoCompleteString = strAutoCompleteString;
	}

	public String getStrIndexCode()
	{
		return strIndexCode;
	}

	public void setStrIndexCode(String strIndexCode)
	{
		this.strIndexCode = strIndexCode;
	}

	public String getStrIcdGroupCode()
	{
		return strIcdGroupCode;
	}

	public void setStrIcdGroupCode(String strIcdGroupCode)
	{
		this.strIcdGroupCode = strIcdGroupCode;
	}

	public String getStrIcdSubGroupCode()
	{
		return strIcdSubGroupCode;
	}

	public void setStrIcdSubGroupCode(String strIcdSubGroupCode)
	{
		this.strIcdSubGroupCode = strIcdSubGroupCode;
	}

	public String getStrIcdDiseaseCode()
	{
		return strIcdDiseaseCode;
	}

	public void setStrIcdDiseaseCode(String strIcdDiseaseCode)
	{
		this.strIcdDiseaseCode = strIcdDiseaseCode;
	}

	public String getJrxmlPath()
	{
		return jrxmlPath;
	}

	public void setJrxmlPath(String jrxmlPath)
	{
		this.jrxmlPath = jrxmlPath;
	}

	public String getJrxmlName()
	{
		return jrxmlName;
	}

	public void setJrxmlName(String jrxmlName)
	{
		this.jrxmlName = jrxmlName;
	}

	public String getStrHospitalCode()
	{
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode)
	{
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrReportOption()
	{
		return strReportOption;
	}

	public void setStrReportOption(String strReportOption)
	{
		this.strReportOption = strReportOption;
	}

	public String getStrReportType()
	{
		return strReportType;
	}

	public void setStrReportType(String strReportType)
	{
		this.strReportType = strReportType;
	}

}
