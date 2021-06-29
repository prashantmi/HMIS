package mrd.transaction.dao;

import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.UserVO;

public interface CaseSheetDtlDAOi {
	
	public void create(CaseSheetDtlVO caseSheetDtlVO,UserVO userVO);
	
	public void update(CaseSheetDtlVO caseSheetDtlVO,UserVO userVO);
	
}
