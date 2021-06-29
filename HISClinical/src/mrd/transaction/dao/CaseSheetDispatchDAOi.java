package mrd.transaction.dao;

import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

public interface CaseSheetDispatchDAOi
{
	public void saveCaseSheetDispatch(CaseSheetDispatchVO _CaseSheetDispatchVO,RecordTypeWiseEnclosureMstVO[] _RecordTypeWiseEnclosureMstVOs,RecordTypeCheckListMstVO[] _RecordTypeCheckListMstVOs,UserVO userVO);
	
	public void update(CaseSheetDispatchVO _CaseSheetDispatchVO,UserVO userVO);
}
