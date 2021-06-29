package opd.master.dao;

import hisglobal.vo.UnitDrugMstVO;
import hisglobal.vo.UserVO;

public interface UnitDrugDAOi {
	public void create(UnitDrugMstVO vo,UserVO userVO);
	public void delete(UnitDrugMstVO vo,UserVO userVO);
	public void updateByDeptUnit(String deptUnitCode,UserVO userVO);
}
