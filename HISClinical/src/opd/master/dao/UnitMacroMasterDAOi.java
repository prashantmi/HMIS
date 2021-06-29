package opd.master.dao;

import hisglobal.vo.UnitWiseMacroMstVO;
import hisglobal.vo.UserVO;

public interface UnitMacroMasterDAOi 
{
	public void create(UnitWiseMacroMstVO vo,UserVO userVO);
	
	public void Update(String unittCode,UserVO userVO);
}
