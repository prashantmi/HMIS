package opd.transaction.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.UserVO;

public class LeftMenuOpdDeskDATA extends ControllerDATA
{
	/**
	 * gets Left Menu Display Essentials calls getOpdMenuDetail from OpdEssentialDelegate
	 * 
	 * @param _UserVO Provides User details.
	 * @return UserDeskMenuMasterVO[] containing All Details For Left Menu Display
	 */
	// *
	public static DeskDetailVO[] getOpdLeftMenuDetail(UserVO _UserVO, String location, String unitCode, String deskType)
	{
		OpdEssentialDelegate opdessentialDelegate = new OpdEssentialDelegate();
		DeskDetailVO[] deskDetailVOs = opdessentialDelegate.getOpdMenuDetail(_UserVO, location, unitCode, deskType);
		return deskDetailVOs;
	}

}
