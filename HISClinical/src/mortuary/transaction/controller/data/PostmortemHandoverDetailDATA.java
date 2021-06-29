package mortuary.transaction.controller.data;

import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.PostmortemHandoverDtlVO;
import hisglobal.vo.UserVO;

public class PostmortemHandoverDetailDATA extends ControllerDATA
{
	public static DeceasedDetailVO[] getPostmortemReadyToHandover(UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPostmortemReadyToHandover(userVO);
	}
	
	public static Map getEssentialForPostmortemHandover(UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForPostmortemHandover(userVO);
	}
	
	public static void savePostmortemHandoverDetail(PostmortemHandoverDtlVO postmortemHandoverDtlVO, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.savePostmortemHandoverDetail(postmortemHandoverDtlVO,userVO);
	}
	
}
