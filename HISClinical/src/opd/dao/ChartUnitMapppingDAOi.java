package opd.dao;

import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface ChartUnitMapppingDAOi
{
	/**
	 * Get Chart Category & Unit Wise Chart List
	 * 
	 * @param _chartCategory Chart Category
	 * @param _userVO User Detail
	 * @return List of Chart Detail
	 */
	public List<ChartMasterVO> getChartsByCategoryByUnit(String _chartCategory, String _deptUnitCode, UserVO _userVO);
}
