package mortuary.transaction.dao;

import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.UserVO;

public interface PostmortemTeamDetailDAOi 
{
	public void create(PostmortemTeamDetailVO postmortemTeamVO, UserVO userVO);
	
	public PostmortemTeamDetailVO[] getAddedTeamMember(String postmortmId,UserVO userVO);
	
	public void update(PostmortemTeamDetailVO postmortemTeamVO, UserVO userVO);
	
}
