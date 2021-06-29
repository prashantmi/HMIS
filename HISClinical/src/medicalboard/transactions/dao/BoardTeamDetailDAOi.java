package medicalboard.transactions.dao;

import hisglobal.vo.BoardTeamDetailVO;
import hisglobal.vo.UserVO;

public interface BoardTeamDetailDAOi 
{
	public void create(BoardTeamDetailVO boardTeamDetailVO, UserVO userVO);
	
	public void deleteBoardTeamDetail(String boardNo,UserVO userVO);
	
	public void updateBoardTeamDetail(String boardNo,UserVO userVO);
}
