package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.UserVO;

public class PostmortemRequestAcceptanceDATA extends ControllerDATA
{
	public static PostmortemRequestDetailVO[] getPostmortemReqList(UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPostmortemReqList(userVO);
	}
	
	public static PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPostmortemRequestDetail(postmortemId,userVO);
	}
	
	public static Map getEssentialForPostmortemReqApproved(String deceasedNo,String consentStatus,String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForPostmortemReqApproved(deceasedNo,consentStatus,postmortemId,userVO);
	}
	
	public static void approvedPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO,List<PostmortemItemReqDtlVO> lstItemAdd,List<PostmortemItemReqDtlVO> lstItemRevoke,List<PostmortemOpinionReqDtlVO> lstOpinionAdd,List<PostmortemOpinionReqDtlVO> lstOpinionRevoke,List<PostmortemTeamDetailVO> lstTeam, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.approvedPostmortemRequest(postmortemReqVO,lstItemAdd,lstItemRevoke,lstOpinionAdd,lstOpinionRevoke, lstTeam, userVO);
	}
	
	public static void cancelledPostmortemRequest(PostmortemRequestDetailVO postmortemReqVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.cancelledPostmortemRequest(postmortemReqVO,userVO);
	}
	
}
