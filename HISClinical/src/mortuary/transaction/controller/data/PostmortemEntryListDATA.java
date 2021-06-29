package mortuary.transaction.controller.data;

import mortuary.transaction.delegate.MortuaryEssDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public class PostmortemEntryListDATA extends ControllerDATA
{
	public static DeceasedDetailVO[] getDeceasedListForPostmortemEntry(UserVO userVO)
	{
		MortuaryEssDelegate essDelegate=new MortuaryEssDelegate();
		return essDelegate.getDeceasedListForPostmortemEntry(userVO);
		
	}
}
