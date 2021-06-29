package medicalboard.transactions.dao;

import hisglobal.vo.MedicalBoardVerificationDtlVO;
import hisglobal.vo.UserVO;

public interface MbVerificationDetailDAOi 
{
	public void create(MedicalBoardVerificationDtlVO verificationDtlVO, UserVO userVO);
}
