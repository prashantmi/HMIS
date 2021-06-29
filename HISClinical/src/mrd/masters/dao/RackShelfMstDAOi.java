package mrd.masters.dao;

import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.UserVO;


public interface RackShelfMstDAOi {
	
	/*public List getNotAssignedShelf(String rackId,UserVO _userVO);
	
	public List getAssignedShelf(String rackId,UserVO _userVO);
	
	public void saveRackShelf(RackShelfMstVO rackShelfVo,UserVO userVO);
	
	public List getRackNameList(UserVO _userVO);
	
	public String getRackName(String rackId,UserVO _userVO);
	
	public List getShelfList(UserVO _userVO);*/
	
	public void create(RackShelfMstVO rackShelfMstVO,UserVO _userVO);
	
	public boolean checkDuplicateBeforeSave(RackShelfMstVO rackShelfMstVO,UserVO _userVO);
	
	public boolean checkDuplicateBeforeModify(RackShelfMstVO rackShelfMstVO,UserVO _userVO);
	
	public RackShelfMstVO getRackShelfDetail(RackShelfMstVO rackShelfMstVO,UserVO _userVO);
	
	public void modifyRackShelf(RackShelfMstVO rackShelfVo, UserVO userVO);

	public void modifyInsertRackShelf(RackShelfMstVO rackShelfVo,UserVO userVO);
}


