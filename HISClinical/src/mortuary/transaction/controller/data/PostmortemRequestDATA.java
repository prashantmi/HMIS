package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.PostmortemOpinionReqDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.UserVO;

public class PostmortemRequestDATA extends ControllerDATA
{
	
	public static DeceasedDetailVO[] getDeceasedListForPostmortemRequest(UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getDeceasedListForPostmortemRequest(userVO);
	}
	
	public static Map getEssentialForPostmortemRequest(String deceasedNo, UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForPostmortemRequest(deceasedNo,userVO);
	}
	
	public static String savePostmortemRequest(MortuaryPoliceVerificationVO postmortemPoliceVerVO, PostmortemRequestDetailVO postmortemRequestDtlVO,List<PostmortemItemReqDtlVO> lstItemVO, List<PostmortemOpinionReqDtlVO> lstOpinionVO, List<DeceasedRelativeDtlVO> lstRelativeVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.savePostmortemRequest(postmortemPoliceVerVO, postmortemRequestDtlVO,lstItemVO,lstOpinionVO, lstRelativeVO, userVO);
	}
}
