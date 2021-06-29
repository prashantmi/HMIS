package inpatient.masters.dao;

import hisglobal.vo.DeliveryPlaceMasterVO;
import hisglobal.vo.UserVO;

public interface DeliveryPlaceMstDAOi 
{
	//Inserting Record 
	public void create(DeliveryPlaceMasterVO deliveryPlaceMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateDeliveryPlaceName(DeliveryPlaceMasterVO deliveryPlaceMasterVO,UserVO userVO);
	
	public DeliveryPlaceMasterVO getDataForModify(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO);
	
	public void updateDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO,UserVO _UserVO);
	
	public void modifySaveDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(DeliveryPlaceMasterVO deliveryPlaceMasterVO,UserVO userVO);

	
}
