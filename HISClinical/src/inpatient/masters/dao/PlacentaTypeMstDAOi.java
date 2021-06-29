package inpatient.masters.dao;

import hisglobal.vo.PlacentaTypeMasterVO;
import hisglobal.vo.UserVO;

public interface PlacentaTypeMstDAOi 
{
	//Inserting Record 
	public void create(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO);
	
	//Checking For Duplicate Name
	public String checkDuplicatePlacentaTypeName(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO);
	
	public PlacentaTypeMasterVO getDataForModify(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO);
	
	public void updatePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO _UserVO);
	
	public void modifySavePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO);
	
	public String checkDuplicateNameForModify(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO);

	
}
