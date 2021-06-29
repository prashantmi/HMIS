package mortuary.transaction.dao;

import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.UserVO;

public interface ExtReqSampleDtlDAOi 
{
	public void create(MortuaryExtReqSampleDtlVO extReqSampleDtlVO, UserVO userVO);
	
	public MortuaryExtReqSampleDtlVO[] getRequestedSampleByRequestId(String requestId,UserVO userVO);
	
	public void updateReceiveRemarks(MortuaryExtReqSampleDtlVO extReqSampleDtlVO, UserVO userVO);
}
