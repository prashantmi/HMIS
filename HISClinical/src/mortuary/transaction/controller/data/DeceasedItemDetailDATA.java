package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedItemDetailDATA extends ControllerDATA
{
	public static PostmortemItemReqDtlVO[] getItemToBePreserved(String postmortemId,UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getItemToBePreserved(postmortemId,userVO);
	}
	
	public static void saveItemToBePreserved(List<PostmortemItemDtlVO> lstItem, UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveItemToBePreserved(lstItem,userVO);
	}
	
	public static Map getPreservativeList(String postmortemId, UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getPreservativeList(postmortemId,userVO);
	}
}
