package inpatient.masters.dao;

import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.IntakeOutputParaMasterVO;
import hisglobal.vo.UserVO;

public interface IntakeOutputParaMstDAOi 
{
	//Inserting Record 
		/*public void create(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO);
		
		//Checking For Duplicate Name
		public String checkDuplicateAnomalyTypeName(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO);
		
		public AnomalyTypeMasterVO getDataForModify(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO);
		
		public void updateAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO _UserVO);
		
		public void modifySaveAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO);
		
		public String checkDuplicateNameForModify(AnomalyTypeMasterVO anomalyTypeMasterVO,UserVO userVO);*/

	public void saveIntakeOutputParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public String checkDuplicateParaName(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public IntakeOutputParaMasterVO getDataForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public String checkDuplicateNameForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public void updateInoutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public void modifySaveInoutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	
}
