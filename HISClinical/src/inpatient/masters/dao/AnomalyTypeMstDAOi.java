package inpatient.masters.dao;

import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.UserVO;

public interface AnomalyTypeMstDAOi 
{
	//Inserting Record 
	public void create(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateAnomalyTypeName(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO);
	
	public AnomalyTypeMasterVO getDataForModify(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO);
	
	public void updateAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO _UserVO);
	
	public void modifySaveAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO);

	
}
