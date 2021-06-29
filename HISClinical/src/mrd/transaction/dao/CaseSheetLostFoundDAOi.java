package mrd.transaction.dao;

import hisglobal.vo.CaseSheetLostFoundVO;
import hisglobal.vo.UserVO;

public interface CaseSheetLostFoundDAOi {
	
	public void create(CaseSheetLostFoundVO caseSheetLostFoundVO,UserVO userVO);
	
	public CaseSheetLostFoundVO select(CaseSheetLostFoundVO caseSheetLostFoundVO,UserVO userVO);
}
