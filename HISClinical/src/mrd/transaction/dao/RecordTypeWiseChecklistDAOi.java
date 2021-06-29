package mrd.transaction.dao;

import java.util.List;

import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.UserVO;

public interface RecordTypeWiseChecklistDAOi {
	
	public void saveCaseSheetDispatchCheckListDetails(RecordTypeCheckListMstVO _RecordTypeCheckListMstVOs,UserVO userVO);
	
	public List<RecordTypeCheckListMstVO> selectCheckListDtl(String recordId,UserVO userVO);
	
	public void create(RecordCheckListDtlVO recordCheckListDtlVO,UserVO userVO);
	
	public RecordCheckListDtlVO[] getCheckListedRecord(String dispatchId,String recordType,UserVO userVO);
	
}
