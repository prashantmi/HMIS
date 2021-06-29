package mrd.transaction.dao;

import java.util.List;

import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.UserVO;

public interface RequestRecordDtlDAOi 
{
	//Offline
	public void create(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);
	
	//Online
	public void onlineInsert(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);
	
	public void update(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);
	
	public void updateRejectDetail(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);
	
	public void updateRequestStatus(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);
	
	public List<RequestRecordDtlVO> selectAllRecordByRequestID(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);
	
	public List<RequestRecordDtlVO> getPendingRecordRequestStatus(String requestId,UserVO userVO);
	
}
