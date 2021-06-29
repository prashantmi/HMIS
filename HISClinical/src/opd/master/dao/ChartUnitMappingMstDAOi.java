package opd.master.dao;

import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.UserVO;

public interface ChartUnitMappingMstDAOi {
	
	public void create(ChartUnitMapppingVO _unitChartListMasterVO,UserVO _userVO);
	public void update(String _unitCode,UserVO _userVO);

}
