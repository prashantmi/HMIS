package inpatient.masters.dao;

import java.util.List;

import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.LaborRoomMasterVO;
import hisglobal.vo.UserVO;

public interface LaborRoomMasterDAOi {

	public List getDeskType(UserVO _userVO);
	public boolean checkDuplicateLaborRoom(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO);
	public void create(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO);
	public LaborRoomMasterVO fetchRecord(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO);
	public boolean check_Modify_DuplicateLaborRoom(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO);
	public void update(LaborRoomMasterVO _LaborRoomMasterVO, UserVO _UserVO);
	public void modifySave(LaborRoomMasterVO laborRoomMasterVO,UserVO _userVO);
}
