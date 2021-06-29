package mortuary.masters.bo;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface MortuaryEssentialBOi 
{
	//Getting Essential For Mortuary Master
	public Map getEssentialForMortuaryMst(UserVO userVO);
	
	//getting List of Employee Based On Department
	public List getEmployeeListBasedOnDept(String deptCode,UserVO userVO);
	
	//Getting Block List
	public List getBlockList(String buildingCode,UserVO userVO);
	
	//Getting Floor List
	public List getFloorList(String blockId,UserVO userVO);
	
	//Getting Room List
	public List getRoomList(String floorId,UserVO userVO);
	
	public Map getEssentialForMortuaryAreaMst(UserVO userVO);
	
	public String getChamberName(String  _chamberId, UserVO _userVO);
	
	public List getAllDoctor(UserVO _UserVO);
	
	public List getDeathCause(UserVO _UserVO);
	
	public List getLab(UserVO _UserVO);
	
	public List getLabTest(UserVO _UserVO);
}
