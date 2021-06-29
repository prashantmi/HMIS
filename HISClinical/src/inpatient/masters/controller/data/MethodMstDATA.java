
package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MethodMasterVO;
import hisglobal.vo.UserVO;

public class MethodMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveMethodMaster(MethodMasterVO methodMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveMethodMaster(methodMasterVO,userVO);
	}
	public static MethodMasterVO getDataForModify(MethodMasterVO methodMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForMethodModify(methodMasterVO, _UserVO));
	}

	public static boolean saveModMethodMaster(MethodMasterVO methodMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModMethodMaster(methodMasterVO, _UserVO);
		return flag;
		
	}
}
