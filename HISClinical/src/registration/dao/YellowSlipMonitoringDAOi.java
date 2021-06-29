package registration.dao;

import java.util.List;

import hisglobal.vo.UserVO;
import hisglobal.vo.YellowSlipMonitoringVO;

public interface YellowSlipMonitoringDAOi {

	public void save(YellowSlipMonitoringVO YellowSlipMonitoringVO,UserVO userVO);
	
	public List getYellowSlipEntryUserList(String fromDate,String toDate,UserVO userVO);
}
