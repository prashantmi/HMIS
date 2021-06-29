package medicalboard.transactions.dao;

import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;

public interface MbChecklistDetailDAOi {

   public void create(MedicalBoardChecklistVO mChecklistVO, UserVO userVO);
	
}
