package opd.transaction.controller.data;

/**
 * @copyright CDAC
 * @author Pragya Sharma
 */

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

public class ChartReportingDATA extends ControllerDATA
{
	/**
	 * Getting Generic Chart Reporting Essentials
	 * 
	 * @param _deskType Desk Type
	 * @param _unitCode Department Unit Code
	 * @param _patDtlVO Patient Detail
	 * @param _userVO User Detail
	 * @return Map of Essentials
	 */
	public static Map<String, Object> getChartReportingEssentials(String _deskType, String _unitCode, PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getChartReportingEssentials(_deskType, _unitCode, _patDtlVO, _userVO);
	}

	/**
	 * Getting Generic Chart Detail
	 * 
	 * @param _deskType Desk Type
	 * @param _patDtlVO Patient Detail 
	 * @param _chartVO Chart Detail
	 * @param _lstChartParas List Chart Parameters
	 * @param _criteria Criteria
	 * @param _fromDate From Date
	 * @param _toDate To Date
	 * @param _userVO User Detail
	 * @return Map of Data
	 */
	public static Map<String, Object> getChartReportingData(String _deskType, PatientDetailVO _patDtlVO, ChartMasterVO _chartVO, List<ChartParameterMappingVO> _lstChartParas, 
				String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getChartReportingData(_deskType, _patDtlVO, _chartVO, _lstChartParas, _criteria, _fromDate, _toDate, _userVO);
	}

	/**
	 * Getting Column Chart Paramter List
	 * 
	 * @param _chartVO Chart Detail
	 * @param _userVO User Detail
	 * @return List Chart Paras
	 */
	public static List<ChartParameterMappingVO> getChartParamtersList(ChartMasterVO _chartVO, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getChartParamtersList(_chartVO, _userVO);
	}
}
