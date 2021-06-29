package mortuary.transaction.dao;

import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.UserVO;

public interface ExtLabReqDtlDAOi 
{
	public void create(MortuaryExtLabRequestDtlVO extLabRequestDtlVO, UserVO userVO);
	
	public String getmaxSrNoBypostmortemId(String postmortemId,UserVO userVO);
	
	public MortuaryExtLabRequestDtlVO[] getAllRequestDetail(String postmortemId,UserVO userVO);
	
	public void updateFinalResult(MortuaryExtLabRequestDtlVO extLabRequestDtlVO, UserVO userVO);
	
	public MortuaryExtLabRequestDtlVO[] getAllReceivedReport(String postmortemId,UserVO userVO);
	
	
	
}
