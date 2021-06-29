package medicalboard.transactions.bo;

import java.util.List;

import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public interface MedicalBoardBOi {
	
	public EpisodeVO newPatRegistration(EpisodeVO _arrEpisodeVO, PatientVO _patientVO,List lstCheckList,MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO);
	
	public void saveBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO);
	
	public void removeBoard(String boardNo,UserVO userVO);
	
	public void updateAssignBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO);
	
	public void saveHandOverDetail(List handOverDetailVOList,UserVO _UserVO);
	
	public void saveCertVerificationDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO);
	
	public PatientImageDtlVO getPatientImageDtlByCrNo(String crNo, UserVO userVO) ;

}
