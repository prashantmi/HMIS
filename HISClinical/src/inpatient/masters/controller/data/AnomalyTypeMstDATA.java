package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.UserVO;

public class AnomalyTypeMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveAnomalyTypeMaster(anomalyTypeMasterVO,userVO);
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
}
