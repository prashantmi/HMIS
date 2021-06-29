package opd.master.controller.data;

import opd.bo.delegate.OpdMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ProfileTypeMstVO;
import hisglobal.vo.UserVO;

public class ChartMasterDATA extends ControllerDATA  
{
	public static void saveChart(ChartMasterVO _chartMasterVO,
			UserVO _userVO) {
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.saveChart(_chartMasterVO,_userVO);
		
	}
	
	public static ChartMasterVO getModifyDetail(ChartMasterVO _chartMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getModifyDetail(_chartMasterVO, _userVO);
	}
	
	public static void modifySave(ChartMasterVO _chartMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySave(_chartMasterVO,_userVO);
	}
}
