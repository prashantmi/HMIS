package opd.dao;

import java.util.List;

import hisglobal.vo.IcdMappingMasterVO;
import hisglobal.vo.UserVO;

public interface OpdIcdMappingDAOi {
	public void create(IcdMappingMasterVO _pListIcdMasterVOLst,UserVO _userVO);
	public IcdMappingMasterVO[] getIcdMappingForModify(IcdMappingMasterVO _vo, UserVO _userVO);
	public List getNotMappedIcdDisease(String _pMappingType,String _pMappingId,String _pSubGroupCode,UserVO _userVO);
	public void update(String _pMappingId, String _pMappingType ,UserVO _userVO);
}
