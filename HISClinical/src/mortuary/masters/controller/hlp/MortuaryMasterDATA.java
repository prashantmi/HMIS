package mortuary.masters.controller.hlp;

import java.util.List;
import java.util.Map;

import mortuary.masters.delegate.MortuaryEssentialDelegate;
import mortuary.masters.delegate.MortuaryMasterDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.MortuaryMasterVO;
import hisglobal.vo.UserVO;

public class MortuaryMasterDATA extends ControllerDATA
{
	//Getting Essential Data 
	public static Map getEssentialForMortuaryMst(UserVO userVO)
	{
		 
		MortuaryEssentialDelegate essDelegate=new MortuaryEssentialDelegate();
		Map mp=essDelegate.getEssentialForMortuaryMst(userVO);
		return mp;
	}
	
	//Getting the List of Employee on the Basis of Department
	public static List getEmployeeListBasedOnDept(String deptCode,UserVO userVO)
	{
		MortuaryEssentialDelegate essDelegate=new MortuaryEssentialDelegate();
		return essDelegate.getEmployeeListBasedOnDept(deptCode,userVO);
	}
	
	//Getting the List of Block on the Basis of Building
	public static List getBlockList(String buildingCode,UserVO userVO)
	{
		MortuaryEssentialDelegate essDelegate=new MortuaryEssentialDelegate();
		return essDelegate.getBlockList(buildingCode,userVO);
	}	
	
	//Getting the List of Floor on the Basis of Block
	public static List getFloorList(String blockId,UserVO userVO)
	{
		MortuaryEssentialDelegate essDelegate=new MortuaryEssentialDelegate();
		return essDelegate.getFloorList(blockId,userVO);
	}
	
	//Getting the List of Room on the Basis of Floor
	public static List getRoomList(String floorId,UserVO userVO)
	{
		MortuaryEssentialDelegate essDelegate=new MortuaryEssentialDelegate();
		return essDelegate.getRoomList(floorId,userVO);
	}
	
	//Saving the Data
	public static void saveMortuaryMaster(MortuaryMasterVO mortuaryMstVO,UserVO userVO)
	{
		MortuaryMasterDelegate mstDelegate=new MortuaryMasterDelegate();
		mstDelegate.saveMortuaryMaster(mortuaryMstVO,userVO);
	}
	public static MortuaryMasterVO getDataForModify(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		return(masterDelegate.getDataForModify(_MortuaryMasterVO, _UserVO));
	}

	public static boolean saveModMortuaryMaster(MortuaryMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		boolean  flag=false;
		MortuaryMasterDelegate masterDelegate = new MortuaryMasterDelegate();
		flag= masterDelegate.saveModMortuaryMaster(_MortuaryMasterVO, _UserVO);
		return flag;
		
	}
}
