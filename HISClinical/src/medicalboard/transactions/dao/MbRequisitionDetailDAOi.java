package medicalboard.transactions.dao;

import hisglobal.vo.HandOverDetailVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.UserVO;

public interface MbRequisitionDetailDAOi {

  public MedicalBoardRequisitionVO create(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO);
  
  public void update(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO);
  
  public void updateReqStatus(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO);
  
  public void updatePostEntryDetail(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO);

  public void updateApprovalDetail(MedicalBoardRequisitionVO mRequisitionVO,UserVO _userVO);

  public void updateReqStatusHandOver(HandOverDetailVO handOverDetailVO,UserVO _userVO);
}
