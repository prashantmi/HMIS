package mortuary.masters.dao;

import java.util.List;

import hisglobal.vo.ChamberRackMasterVO;
import hisglobal.vo.UserVO;

public interface MortuaryMstEssentialDAOi 
{
	//Getting The List Of All Department
	public List getAllDepartment(UserVO userVO);
	
	//Getting The List Of All Building
	public List getAllBuilding(UserVO userVO);
	
	//Getting The List of Employee on the Basis of Department
	public List getEmployeeListBasedOnDept(String deptCode,UserVO userVO);
	
	//Getting The List of Block on the Basis of Building
	public List getBlockListBasedOnBuilding(String buildingCode,UserVO userVO);
	
	//Getting The List of Floor on the Basis of Block
	public List getFloorListBasedOnBlock(String blockId,UserVO userVO);
	
	//Getting The List of Room on the Basis of Floor
	public List getRoomListBasedOnFloor(String floorId,UserVO userVO);
	
	//Getting All Relation
	public List getAllRelation(UserVO userVO);
	
	//Getting All Chamber
	public List getAllChamber(UserVO userVO);
	
	//Getting All Chamber Rack
	public ChamberRackMasterVO[] getAllChamberRack(UserVO userVO);
	
	//Getting All Deceased Item
	public List getAllDeceasedItem(UserVO userVO);
	
	//Getting All Opinion
	public List getAllOpinion(UserVO userVO);
	
	public List getPostmortemRole(UserVO userVO);
	
	public List getDeathMannerList(UserVO userVO);
	
	public List getInjuryNatureList(UserVO userVO);
	
	public List getInjuryTypeList(UserVO userVO);
	
	public List getIncisionTypeList(UserVO userVO);
	
	public List getAutopsyTypeList(UserVO userVO);
	
	public List getOpinionListNotRequested(String postmortemId,UserVO userVO);
	
	public List getAllMortuary(UserVO userVO);
	
	public List getAllAreaBasedOnMortuary(String mortuary, UserVO userVO);
	
	public List getAllChamberBasedOnMortuaryArea(String mortuaryArea, UserVO userVO);
	
	public List getOpinionNotAdded(String postmortemId, UserVO userVO);
	
	public List getItemListNotRequested(String postmortemId,UserVO userVO);
	
	public List getAllPreservative(UserVO userVO);
	
	public List getItemNotRequestedNPreserved(String postmortemId, UserVO userVO);
	
	public List getExternalLabList(UserVO userVO);
	
	public List getExternalLabTestList(UserVO userVO);
	
	public String getChamberName(String _chamberId,UserVO userVO);
	
	public List getAllAssociatedHospital(UserVO userVO);
	
	
}
