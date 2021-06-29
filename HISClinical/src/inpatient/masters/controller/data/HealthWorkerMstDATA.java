package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.HealthWorkerMasterVO;
import hisglobal.vo.UserVO;

public class HealthWorkerMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveHealthWorkerMaster(healthWorkerMasterVO,userVO);
	}
	public static HealthWorkerMasterVO getDataForModify(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForHealthWorkerModify(healthWorkerMasterVO, _UserVO));
	}

	public static boolean saveModHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModHealthWorkerMaster(healthWorkerMasterVO, _UserVO);
		return flag;
		
	}
}
