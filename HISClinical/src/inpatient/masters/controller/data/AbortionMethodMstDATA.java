package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AbortionMethodMasterVO;
import hisglobal.vo.UserVO;

public class AbortionMethodMstDATA extends ControllerDATA
{
	
	public static String getAbortionTypeName(String typeID,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		return mstDelegate.getAbortionTypeName(typeID,userVO);
	}
	
	//Saving the Data
	public static void saveAbortionMethodMaster(AbortionMethodMasterVO abortionTypeMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveAbortionMethodMaster(abortionTypeMasterVO,userVO);
	}
	public static AbortionMethodMasterVO getDataForModify(AbortionMethodMasterVO _abortionMethodMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForAbortionMethodModify(_abortionMethodMasterVO, _UserVO));
	}

	public static boolean saveModAbortionMethodMaster(AbortionMethodMasterVO _abortionMethodMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModAbortionMethodMaster(_abortionMethodMasterVO, _UserVO);
		return flag;
		
	}
}
