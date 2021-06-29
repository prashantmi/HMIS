package opd.dao;

import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.UserVO;

public interface PatDietAdviceDetailDAOi {
	
	public void savePatDietAdviceDetail(PatDietAdviceDetailVO patDietAdviceDetailVO, UserVO _userVO);
	public void updatePatDietAdviceDetail(PatDietAdviceDetailVO patDietAdviceDetailVO, UserVO _userVO);

}
