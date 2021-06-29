package inpatient.masters.dao;

import hisglobal.vo.HealthWorkerMasterVO;
import hisglobal.vo.UserVO;

public interface HealthWorkerMstDAOi 
{
	//Inserting Record 
	public void create(HealthWorkerMasterVO healthWorkerMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicateHealthWorkerName(HealthWorkerMasterVO healthWorkerMasterVO,UserVO userVO);
	
	public HealthWorkerMasterVO getDataForModify(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO);
	
	public void updateHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO,UserVO _UserVO);
	
	public void modifySaveHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(HealthWorkerMasterVO healthWorkerMasterVO,UserVO userVO);

	
}
