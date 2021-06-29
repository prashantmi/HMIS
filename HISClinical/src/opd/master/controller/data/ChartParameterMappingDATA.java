package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;

public class ChartParameterMappingDATA extends ControllerDATA
{
	public static Map<String,Object> getEssentials(UserVO _userVO)
	{		
		OpdMasterDelegate delegate= new OpdMasterDelegate();
		return delegate.getChartParameterEssentials(_userVO);
	}
	
	public static Map getParameterForChart(String _chartId, UserVO _userVO)
	{
		OpdMasterDelegate delegate = new OpdMasterDelegate();
		return delegate.getParameterForChart(_chartId,_userVO);
	}
	
	public static void saveChartParameterMapping(String _chartId, List<ChartParameterMappingVO> _insertList,UserVO _userVO) 
	{
		OpdMasterDelegate delegate = new OpdMasterDelegate();
		delegate.saveChartParameterMapping(_chartId, _insertList,_userVO);
	}	
}
