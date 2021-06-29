package opd.master.dao;

import hisglobal.utility.Entry;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ChartParameterMappingDAOi
{
	public List getAllChart(UserVO _userVO);

	public List getParaByChartId(String _chartId, UserVO _userVO);

	public List getParaNotAdded(String chartId, UserVO _userVO);

	public void update(String _chartId, UserVO _userVO);

	public void create(ChartParameterMappingVO _chartParameterMappingVO, UserVO _userVO);

	// Getting List of Parameters based on Parameter Type
	public List<Entry> getParametersByParaType(String _paraType, UserVO _userVO);
}
