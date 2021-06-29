package mrd.transaction.dao;

import java.util.List;

import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

public interface RecordTypeWiseEnclosureDAOi 
{
	public void saveCaseSheetDispatchEnclosureDetails(CaseSheetDispatchVO _CaseSheetDispatchVO,RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVOs,RecordTypeCheckListMstVO[] _RecordTypeCheckListMstVOs,UserVO userVO);
	
	public void update(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVOs,UserVO userVO);

	public List<RecordTypeWiseEnclosureMstVO> getEnclosureDetailByDispatchId(String dispatchId,String recordType,UserVO userVO);
	
	public void create(RecordTypeWiseEnclosureMstVO enclosureMstVO,UserVO userVO);
	
	public void updateEnclosurePagesInMrd(RecordTypeWiseEnclosureMstVO recordTypeWiseEnclosureMstVOs,UserVO userVO);

	public void updateVerifyByPeon(RecordTypeWiseEnclosureMstVO _RecordTypeWiseEnclosureMstVOs,UserVO userVO);

}
