package medicalboard.transactions.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.BoardDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.List;

import medicalboard.transactions.bo.MedicalBoardBO;
import medicalboard.transactions.bo.MedicalBoardBOi;

public class MbTransactionDelegate extends Delegate{


	public MbTransactionDelegate() {
		super(new MedicalBoardBO());
	}

	/**
	 * Registers a patient when he/she visits the hospital for the first time.
	 * Generates the CR No of the Patient.
	 * Calculates age of the patient if DOB is provided and DOB if age is provided.
	 * Saves data in Patient Dtl, Address dtl, Daily Patient dtl, and Episode dtl tables.
	 * @param	_arrEpisodeVO[]	Provides the departments in which patient has been registered.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO newPatRegistration(EpisodeVO _arrEpisodeVO, PatientVO _patientVO,List lstCheckList,MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO)
	{
		System.out.println(" in side new patreg delegate");
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		return (serviceBO.newPatRegistration(_arrEpisodeVO, _patientVO,lstCheckList,mRequisitionVO,_userVO));
	}// each VisitStampVO keeps PatientVO

	public void saveBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		serviceBO.saveBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);
	}
	
	public void removeBoard(String boardNo,UserVO userVO)
	{
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		serviceBO.removeBoard(boardNo,userVO);
	}
	
	public void updateAssignBoardAndBoardTeamDetail(BoardDetailVO boardDetailVO,List docRoleTeamDetailList,List escortedTeamDetailList,UserVO userVO)
	{
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		serviceBO.updateAssignBoardAndBoardTeamDetail(boardDetailVO,docRoleTeamDetailList,escortedTeamDetailList,userVO);
	}
	
	public void saveHandOverDetail(List handOverDetailVOList,UserVO _UserVO)
	{
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		serviceBO.saveHandOverDetail(handOverDetailVOList,_UserVO);
	}
	public void saveCertVerificationDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO)
	{
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		serviceBO.saveCertVerificationDetail(requisitionVO,userVO);
	}
	public PatientImageDtlVO getPatientImageDtlByCrNo(String crNo,UserVO userVO)
	{
		MedicalBoardBOi serviceBO = (MedicalBoardBOi) super.getServiceProvider();
		return serviceBO.getPatientImageDtlByCrNo(crNo, userVO);
	}

}





