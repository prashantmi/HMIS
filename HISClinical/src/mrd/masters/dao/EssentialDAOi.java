package mrd.masters.dao;

import hisglobal.vo.UserVO;
import java.util.List;

public interface EssentialDAOi {
	
	public List getRackNameList(UserVO _userVO);
	
	public List getMrdList(UserVO userVO);
	
	public List getBuilding(UserVO userVO);
	
	public List getBlockList(String buildingCode,UserVO userVO);
	
	public List getFloorList(String blockId,UserVO userVO);
	
	public List getRoomList(String floorId,UserVO userVO);
	
	public List getAllDepartment(UserVO userVO);

	public List getEmployeeListBasedOnDept(String deptCode,UserVO userVO);
	
	public List getRackBasedOnMrd(String mrdCode,UserVO userVO);
	
	public List getShelfBasedOnRack(String rackId,UserVO userVO);
	
	public List getAllMrdRecordType(UserVO _userVO);


}
