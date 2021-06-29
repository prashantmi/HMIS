package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.MortuaryExtLabInvReqDtlVO;
import hisglobal.vo.MortuaryExtLabRequestDtlVO;
import hisglobal.vo.MortuaryExtReqSampleDtlVO;
import hisglobal.vo.UserVO;

public class SampleResultEntryDATA extends ControllerDATA
{
	public static Map getAllRequestedIdDetailNDeceasedNo(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAllRequestedIdDetailNDeceasedNo(postmortemId,userVO);
	}
	
	public static Map getAllSampleNInvestigationRequestDetail(String requestId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAllSampleNInvestigationRequestDetail(requestId,userVO);
	}
	
	public static void saveSampleResultEntry(MortuaryExtLabRequestDtlVO extLabReqDtlVO,List<MortuaryExtLabInvReqDtlVO> lstInvestigation,List<MortuaryExtReqSampleDtlVO> lstSample,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveSampleResultEntry(extLabReqDtlVO,lstInvestigation,lstSample,userVO);
	}
	
	public static DeceasedDetailVO[] searchPostmortemNo(String decNo,String fName,String mName,String lName,String deathDate,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.searchPostmortemNo(decNo,fName,mName,lName,deathDate,userVO);
	}
	
	
}
