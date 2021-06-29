package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdMasterDelegate;

public class ChartUnitMappingMstDATA extends ControllerDATA
{
	public static Map getChartUnitListEssential(UserVO _userVO)
	{
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getChartUnitListEssential(_userVO);
	}

	public static void saveChartUnitList(List _chartUnitMapppingVO, UserVO _userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.saveChartUnitList(_chartUnitMapppingVO,_userVO);
		
	}
	
	public static Map getChartUnitListForModify(ChartUnitMapppingVO _vo, UserVO _userVO) {
		OpdEssentialDelegate delegate=new OpdEssentialDelegate();
		return delegate.getChartUnitListForModify(_vo,_userVO);
	}

	public static void modifySaveChartList(List _chartUnitMapppingVO,
			UserVO _userVO) {
		OpdMasterDelegate masterDelegate=new OpdMasterDelegate();
		masterDelegate.modifySaveChartList(_chartUnitMapppingVO,_userVO);
		
	}

}
