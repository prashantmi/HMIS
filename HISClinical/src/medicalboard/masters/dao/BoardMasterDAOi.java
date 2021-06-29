package medicalboard.masters.dao;

import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.UserVO;

public interface BoardMasterDAOi {

	public String checkDuplicateBoardName(MedicalBoardMasterVO mBoardMasterVO,UserVO userVO);
	public String getMaxBoardId(UserVO userVO);
	public void create(MedicalBoardMasterVO mBoardMasterVO,String maxBoardId,UserVO userVO);
	public void saveBoardTeamDetail(String maxBoardId,String empId,String roleID,UserVO userVO);
	public MedicalBoardMasterVO[] getBoardDetail(MedicalBoardMasterVO mBoardMasterVO, UserVO _UserVO);
	public void updateBoardTeamDetail(MedicalBoardMasterVO mBoardMasterVO,UserVO _UserVO);
	public void updateBoardDetail(MedicalBoardMasterVO mBoardMasterVO,UserVO _UserVO);
	public String checkDuplicateBoardNameForModify(MedicalBoardMasterVO mBoardMasterVO,UserVO userVO);
}
