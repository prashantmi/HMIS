package mortuary.transaction.dao;

import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.UserVO;

public interface ExtLabInvReqDtlDAOi
{
	public void create(MortuaryExtLabInvReqDtlVO extLabInvReqDtlVO, UserVO userVO);
	
	public MortuaryExtLabInvReqDtlVO[] getRequestedInvestigationByRequestId(String requestId,UserVO userVO);
	
	public void updateLabResult(MortuaryExtLabInvReqDtlVO extLabInvReqDtlVO, UserVO userVO);
	
}
