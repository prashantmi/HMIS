package opd.master.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class AddImageDescriptionDATA extends ControllerDATA
{
	public static void addImageDescription(String imageDesc,UserVO userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate=new OpdEssentialDelegate();
		opdEssentialDelegate.addImageDescription(imageDesc,userVO);
	}
}
