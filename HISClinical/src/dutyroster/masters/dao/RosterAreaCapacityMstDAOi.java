package dutyroster.masters.dao;

import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.UserVO;

public interface RosterAreaCapacityMstDAOi {
	
	
	public void create(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO);

	public RosterAreaCapacityMstVO fetch(RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO);

	public void update(String areaCode,RosterAreaCapacityMstVO _rosterAreaCapMstVO, UserVO _userVO);

	
}	