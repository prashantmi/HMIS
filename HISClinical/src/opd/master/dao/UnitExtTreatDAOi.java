package opd.master.dao;

import hisglobal.vo.UnitExtTreatMstVO;
import hisglobal.vo.UserVO;

public interface UnitExtTreatDAOi 
{
	public void create(UnitExtTreatMstVO vo,UserVO userVO);
	public void updateByDeptUnit(String deptUnitCode,UserVO userVO);
	
}
