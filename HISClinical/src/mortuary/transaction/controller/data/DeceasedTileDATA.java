package mortuary.transaction.controller.data;

import mortuary.transaction.delegate.MortuaryDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public class DeceasedTileDATA extends ControllerDATA
{
	public static DeceasedDetailVO getDeceasedDetailByDeceasedNo(DeceasedDetailVO deceasedVO,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getDeceasedDetailByDeceasedNo(deceasedVO,userVO);
	}
	
	public static String getMlcNo(String deceasedNo,String crNo,UserVO userVO)
	{
		MortuaryDelegate delegate=new MortuaryDelegate();
		return delegate.getMlcNo(deceasedNo,crNo,userVO);
	}
}
