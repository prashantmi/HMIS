package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.UserVO;

public class SampleSendToExtLabDATA extends ControllerDATA
{
	public static Map getEssentialForSampleSend(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForSampleSend(postmortemId,userVO);
	}
	
	public static void saveSampleSendDetail(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtReqSampleDtlVO> lstSample,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveSampleSendDetail(extLabReqDtlVO,lstSample,lstInvestigation,userVO);
	}
	
}
