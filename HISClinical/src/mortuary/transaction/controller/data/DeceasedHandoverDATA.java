package mortuary.transaction.controller.data;

import java.util.List;
import java.util.Map;

import mortuary.transaction.delegate.MortuaryDelegate;
import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedHandoverDATA extends ControllerDATA
{
	
	public static DeceasedDetailVO[] getDeceasedListToHandover(UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getDeceasedListToHandover(userVO);
	}
	
	public static List getAllRelationship(UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getAllRelationship(userVO);
	}
	
	public static void saveDeceasedHandoverDetail(String bodyStatus, DeceasedRelativeDtlVO deceasedRelativeVO, DeceasedHandoverDtlVO deceasedHandoverVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		delegate.saveDeceasedHandoverDetail(bodyStatus,deceasedRelativeVO,deceasedHandoverVO,userVO);
	}
	
	public static String getChamberRackName(String deceasedNo,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getChamberRackName(deceasedNo,userVO);
	}
	
	public static DeceasedRelativeDtlVO[] getExistingRelative(String deceasedNo,UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.getExistingRelative(deceasedNo,userVO);
	}
	
	public static Map checkForDeceasedHandover(String deceasedNo,String postmortemReq,UserVO userVO)
	{
		MortuaryEssDelegate	essDelegate=new MortuaryEssDelegate();
		return essDelegate.checkForDeceasedHandover(deceasedNo,postmortemReq,userVO);
	}
	
	public static DeceasedHandoverDtlVO getDeceasedDtlAfterHandover(String deceasedNo,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getDeceasedDtlAfterHandover(deceasedNo,userVO);
	}
}
