package mrd.masters.dao;

import hisglobal.vo.EnclosureMasterVO;
import hisglobal.vo.UserVO;

public interface EnclosureMasterDAOi 
{
	public void creat(EnclosureMasterVO enclosureMstVO ,UserVO userVO);
	public String checkDuplicateName(EnclosureMasterVO enclosureMstVO ,UserVO userVO);
	public EnclosureMasterVO getDataForModify(EnclosureMasterVO enclosureMstVO, UserVO _UserVO);
	public String checkDuplicateNameForModify(EnclosureMasterVO enclosureMstVO,UserVO userVO);
	public void updateEnclosureMaster(EnclosureMasterVO enclosureMstVO,UserVO _UserVO);
	public void saveModEnclosureMaster(EnclosureMasterVO enclosureMstVO ,UserVO userVO);
	
}
