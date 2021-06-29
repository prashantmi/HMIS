package mrd.transaction.dao;

import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.UserVO;

public interface MrdRecordLostFoundDAOi 
{
	public void insertLostDtl(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO);
	
	public MrdRecordLostFoundDtlVO[] getLostRecordInMrdList(UserVO userVO);
	
	public void updateFoundDetail(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO);
	
}
