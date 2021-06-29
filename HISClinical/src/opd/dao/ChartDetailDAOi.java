package opd.dao;

import hisglobal.utility.generictemplate.ChartCellData;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ChartDetailDAOi
{
	// Getting Map of Dynaically Fetched Details of Chart Data of Template Based Parameters in OPD
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataTemplateOPD(PatientDetailVO _patDtlVO, String _strParaIds, String _filterCriteria, String _fromDate, String _toDate, UserVO _userVO);

	// Getting Map of Dynaically Fetched Details of Chart Data of Template Based Parameters in IPD
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataTemplateIPD(PatientDetailVO _patDtlVO, String _strParaIds, String _filterCriteria, String _fromDate, String _toDate, UserVO _userVO);

	// Getting Map of Dynaically Fetched Details of Chart Data of Investigation Parameters
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataInvestigation(LinkedHashMap<String, Map<String,ChartCellData>> _mp, PatientDetailVO _patDtlVO, String _strParaIds, String _criteria, String _fromDate, String _toDate, UserVO _userVO);

	// Getting Map of Dynaically Fetched Details of Chart Data of Intake Output Parameters
	public LinkedHashMap<String, Map<String,ChartCellData>> getDynamicChartDataIntakeOutput(LinkedHashMap<String, Map<String,ChartCellData>> _mp, PatientDetailVO _patDtlVO, String _strParaIds, String _criteria, String _fromDate, String _toDate, UserVO _userVO);

	// Getting Map of Dynaically Fetched Details of Chart Data of Investigation Parameters
	public Map<Object, Object> getDynamicChartRowWiseData(String _query, PatientDetailVO _patDtlVO, String _fromDate, String _toDate, UserVO _userVO);
}
