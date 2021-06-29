package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.IntakeOutputParaMasterVO;
import hisglobal.vo.UserVO;

public class IntakeOutputParaMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveIntakeOutputParaMaster(IntakeOutputParaMasterVO inoutparaMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveIntakeOutputParaMaster(inoutparaMasterVO,userVO);
	}
	public static AnomalyTypeMasterVO getDataForModify(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForAnomalyTypeModify(anomalyTypeMasterVO, _UserVO));
	}

	public static boolean saveModAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModAnomalyTypeMaster(anomalyTypeMasterVO, _UserVO);
		return flag;
		
	}
	public static IntakeOutputParaMasterVO getDataForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForModify(inoutparaMasterVO, userVO));
	}
	public static boolean saveModInOutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModInOutParaMaster(inoutparaMasterVO, userVO);
		return flag;
	}
}
