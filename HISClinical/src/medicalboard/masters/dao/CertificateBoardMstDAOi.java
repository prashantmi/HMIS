package medicalboard.masters.dao;

import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface CertificateBoardMstDAOi {
	
	public List getUnselectedBoard(MbCertificateBoardMstVO mBoardMstVO,UserVO userVO);
	public List getSelectedBoard(MbCertificateBoardMstVO mBoardMstVO,UserVO userVO);
	public void updateCertificateBoardDetail(MbCertificateBoardMstVO mBoardMstVO,UserVO _UserVO);
	public void create(MbCertificateBoardMstVO mBoardMstVO,String BoardId,UserVO userVO);
}
