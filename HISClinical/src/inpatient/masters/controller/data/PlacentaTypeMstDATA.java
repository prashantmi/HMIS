package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PlacentaTypeMasterVO;
import hisglobal.vo.UserVO;

public class PlacentaTypeMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void savePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.savePlacentaTypeMaster(placentaTypeMasterVO,userVO);
	}
	public static PlacentaTypeMasterVO getDataForModify(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForPlacentaTypeModify(placentaTypeMasterVO, _UserVO));
	}

	public static boolean saveModPlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModPlacentaTypeMaster(placentaTypeMasterVO, _UserVO);
		return flag;
		
	}
}
