package mrd.transaction.dao;

import java.util.List;

import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.UserVO;

public interface MrdRecordIssueDtlDAOi
{
	
	public void create(MrdRecordIssueDtlVO mrdRecordIssueDtlDAO,UserVO userVO);

	public List<MrdRecordIssueDtlVO> selectByRequestId(MrdRecordIssueDtlVO mrdRecordIssueDtlDAO,UserVO userVO);
	
	public List<MrdRecordIssueDtlVO> selectByMrdRecordId(MrdRecordIssueDtlVO mrdRecordIssueDtlVO,UserVO userVO);
	
	public List<MrdRecordIssueDtlVO> getReturnedMrdRecordListByRequestId(String requestId,String recordId,UserVO userVO);


	public void createExtended(MrdRecordIssueDtlVO mrdRecordIssueDtlVO,
			UserVO userVO);
	
}
