package inpatient.masters.controller.data;

import java.util.List;
import java.util.Map;

import inpatient.masters.delegate.MasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DeliveryPlaceMasterVO;
import hisglobal.vo.UserVO;

public class DeliveryPlaceMstDATA extends ControllerDATA
{
	
	//Saving the Data
	public static void saveDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO,UserVO userVO)
	{
		MasterDelegate mstDelegate=new MasterDelegate();
		mstDelegate.saveDeliveryPlaceMaster(deliveryPlaceMasterVO,userVO);
	}
	public static DeliveryPlaceMasterVO getDataForModify(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO)
	{
		MasterDelegate masterDelegate = new MasterDelegate();
		return(masterDelegate.getDataForDeliveryPlaceModify(deliveryPlaceMasterVO, _UserVO));
	}

	public static boolean saveModDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MasterDelegate masterDelegate = new MasterDelegate();
		flag= masterDelegate.saveModDeliveryPlaceMaster(deliveryPlaceMasterVO, _UserVO);
		return flag;
		
	}
}
