package opd.master.dao;

import hisglobal.vo.UnitDrugListMasterVO;
import hisglobal.vo.UserVO;

public interface UnitDrugListDAOi {
	
	public void create(UnitDrugListMasterVO unitDrugListMasterVO,UserVO userVO);
	public void update(String unitCode,UserVO userVO);

}
