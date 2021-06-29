package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AbortionTypeMasterVO;
import hisglobal.vo.UserVO;

public class AbortionTypeMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveAbortionTypeMaster(abortionTypeMasterVO,userVO);
	}
	public static AbortionTypeMasterVO getDataForModify(AbortionTypeMasterVO _abortionTypeMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForAbortionTypeModify(_abortionTypeMasterVO, _UserVO));
	}

	public static boolean saveModAbortionTypeMaster(AbortionTypeMasterVO _abortionTypeMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModAbortionTypeMaster(_abortionTypeMasterVO, _UserVO);
		return flag;
		
	}
}
