package mrd.transaction.dao;

import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.UserVO;

public interface RecordLostFoundDtlDAOi 
{
	public void create(RecordLostFoundDtlVO recordLostDtlVO,UserVO userVO);
	
	public RecordLostFoundDtlVO[] getLostRecordList(String recordType,String mrdCode,UserVO userVO);
	
	public void updateFoundDetail(RecordLostFoundDtlVO recordLostDtlVO,UserVO userVO);
	
}
