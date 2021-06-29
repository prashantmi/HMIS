package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PostmortemTeamDetailVO;
import hisglobal.vo.UserVO;

public class PostmortemTeamDetailDATA extends ControllerDATA
{
	public static Map getEssentialForTeamDetail(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getEssentialForTeamDetail(postmortemId,userVO);
	}
	
	public static void saveTeamDetail(List<PostmortemTeamDetailVO> lstTeamModify,List<PostmortemTeamDetailVO> lstTeamAdd,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveTeamDetail(lstTeamModify,lstTeamAdd,userVO);
	}
}
