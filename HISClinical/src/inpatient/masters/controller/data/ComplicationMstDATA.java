package inpatient.masters.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ComplicationMasterVO;
import hisglobal.vo.UserVO;
import inpatient.masters.delegate.MasterDelegate;

public class ComplicationMstDATA extends ControllerDATA
{
	// Saving the Data
	public static void saveCompMaster(ComplicationMasterVO complicationMasterVO, UserVO userVO)
	{
		MasterDelegate mstDelegate = new MasterDelegate();
		mstDelegate.saveCompMaster(complicationMasterVO, userVO);
	}

	public static ComplicationMasterVO getDataForModify(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return (masterDelegate.getDataForCompModify(complicationMasterVO, _UserVO));
	}

	public static void saveModCompMaster(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		masterDelegate.saveModCompMaster(complicationMasterVO, _UserVO);
	}
}
