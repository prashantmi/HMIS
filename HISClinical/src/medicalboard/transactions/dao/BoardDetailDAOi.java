package medicalboard.transactions.dao;

import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.UserVO;

public interface BoardDetailDAOi 
{
	public void create(BoardDetailVO boardDetailVO, UserVO userVO);
	public String  checkIsBoardExistForCertifecateType(String certificateTypeId,String scheduleDate ,UserVO userVO);
	public String  getBoardNumber(UserVO userVO);
	public void deleteBoardDetail(String boardNo,UserVO userVO);
	public void updateBoardDetail(String boardNo,UserVO userVO);
	
}
