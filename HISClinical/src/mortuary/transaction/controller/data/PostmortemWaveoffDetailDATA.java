package mortuary.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.PostmortemRequestDetailVO;
import hisglobal.vo.MortuaryPoliceVerificationVO;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.PostmortemWaveoffDtlVO;
import hisglobal.vo.UserVO;

public class PostmortemWaveoffDetailDATA extends ControllerDATA
{
	
	public static DeceasedDetailVO[] getWaveoffDetails(UserVO userVO)
	{
		MortuaryDelegate mortuaryDelegate=new MortuaryDelegate();
		return mortuaryDelegate.getWaveoffDetails(userVO);
	}

	public static PostmortemRequestDetailVO getPostmortemRequestDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPostmortemRequestDetail(postmortemId,userVO);
	}
	
	public static Map fetchPoliceVeriDetails(String postmortemId,String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.fetchPoliceVeriDetails(postmortemId,deceasedNo,userVO);
	}
	
	public static void savePostMortemWaveoffDetail(PostmortemWaveoffDtlVO postmortemWaveoffDtlVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.savePostMortemWaveoffDetail(postmortemWaveoffDtlVO,userVO);
	}
}
