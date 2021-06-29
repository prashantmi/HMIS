package opd.transaction.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class OpdDeskFooterDATA extends ControllerDATA
{
	// *
	public static String getPatientEssentials(UserVO userVO, String unitCode, String roomCode)
	{
		String patCount;
		OpdEssentialDelegate opdEssentialDelegate = new OpdEssentialDelegate();
		patCount = opdEssentialDelegate.getOpdPatientCount(userVO, unitCode, roomCode);
		return patCount;
	}
}
