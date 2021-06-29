package mrd.masters.controller.data;

import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.UserVO;
import mrd.masters.delegate.MrdMasterDelegate;

public class RackShelfMstDATA  
{
	
	public static boolean saveRackShelf(RackShelfMstVO rackShelfVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.saveRackShelf(rackShelfVO,userVO);
		
	}
	
	public static RackShelfMstVO getRackShelfDetail(RackShelfMstVO rackShelfVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getRackShelfDetail(rackShelfVO,userVO);
	}
	
	public static boolean modifyRackShelf(RackShelfMstVO rackShelfVO,UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.modifyRackShelf(rackShelfVO,userVO);
	}

	public static String getRackNameByRackId(String rackId, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getRackNameByRackId(rackId,userVO);
	}
	
	/*public static void modifyRackShelf(RackShelfMstVO[] updateRackShelfVO, RackShelfMstVO[] insertRackShelfVO, UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		masterDelegate.modifyRackShelf(updateRackShelfVO,insertRackShelfVO,userVO);
		
	}
	
	
	public static Map getEssentialsForRackShelf(UserVO userVO) {
		MrdMasterDelegate masterDelegate = new MrdMasterDelegate();
		return masterDelegate.getEssentialsForRackShelf(userVO);
	}*/
				
}
