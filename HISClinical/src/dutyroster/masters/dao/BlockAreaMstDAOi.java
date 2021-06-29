package dutyroster.masters.dao;

import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface BlockAreaMstDAOi { 

	//public List getDutyBlockId(UserVO userVO) ;	
	
	public List getAssignedAreaCode(BlockAreaMstVO blockAreaVO,UserVO userVO);
	
	public void modifyBlockArea(BlockAreaMstVO blockAreaVO ,UserVO userVO);
	
	public void modifyInsertBlockArea(BlockAreaMstVO blockAreaVO ,UserVO userVO);
	
	public BlockAreaMstVO[] getBlockArea(String dutyBlockId ,UserVO userVO);
	
	public void modifyWorkPrefrence(BlockAreaMstVO blockAreaVO, UserVO userVO) ;
	
	public List getAreaCodeList(String  blockId, UserVO userVO);
	
	public void modifyWorkPrefrenceDuringModify(BlockAreaMstVO blockAreaVO,UserVO userVO); 
	
			
}
