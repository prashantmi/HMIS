package dutyroster.masters.dao;

import hisglobal.vo.RosterTypeMstVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface RosterTypeMstDAOi { 

	public void create(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);

	public RosterTypeMstVO fetch(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);

	public void update(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);

	public void createwhileUpdate(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);	
	
	public int checkDuplicateBeforeInsert(String rosterTypeName, UserVO _UserVO);

	public int checkDuplicateBeforeModify(RosterTypeMstVO _rosterTypeVO, UserVO _UserVO);
	
	public List getRosterCat(UserVO _UserVO);	
	
	public List getDutyAreaType(UserVO _UserVO);	
	
	public List checkDutyTypeWhileInsert(RosterTypeMstVO _rosterTypeVO, UserVO _userVO);
	
	public Map checkDutyTypeWhileModify(RosterTypeMstVO _rosterTypeVO, UserVO _userVO);
}
