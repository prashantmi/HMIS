package mortuary.masters.delegate;

import java.util.List;
import java.util.Map;

import opd.bo.OpdEssentialBOi;

import mortuary.masters.bo.MortuaryEssentialBO;
import mortuary.masters.bo.MortuaryEssentialBOi;
import mortuary.masters.bo.MortuaryMasterBOi;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;

public class MortuaryEssentialDelegate extends Delegate
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public MortuaryEssentialDelegate() 
	{
		super(new MortuaryEssentialBO()); // /<<Setting the service provider
	}
	
	//Getting Essential Data 
	public Map getEssentialForMortuaryMst(UserVO userVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		Map mp=serviceBO.getEssentialForMortuaryMst(userVO);
		return mp;
	}
	
	//Getting the List of Employee on the Basis of Department
	public List getEmployeeListBasedOnDept(String deptCode,UserVO userVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return serviceBO.getEmployeeListBasedOnDept(deptCode,userVO);
	}
	
	//Getting the List of Block on the Basis of Building
	public List getBlockList(String buildingCode,UserVO userVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return serviceBO.getBlockList(buildingCode,userVO);
	}
	
	//Getting the List of Floor on the Basis of Block
	public List getFloorList(String blockId,UserVO userVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return serviceBO.getFloorList(blockId,userVO);
	}
	
	//Getting the List of Room on the Basis of Floor
	public List getRoomList(String floorId,UserVO userVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return serviceBO.getRoomList(floorId,userVO);
	}
	
	public Map getEssentialForMortuaryAreaMst(UserVO userVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		Map mp=serviceBO.getEssentialForMortuaryAreaMst(userVO);
		return mp;
	}
	
	/*********************************Chamber Namer***********************************************/
	
	public String getChamberName(String  _chamberId, UserVO _userVO) {
		
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi) super.getServiceProvider();
		return(serviceBO.getChamberName(_chamberId, _userVO));
	}
	
	public List getAllDoctor(UserVO _UserVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return (serviceBO.getAllDoctor(_UserVO));
	}
	
	public List getDeathCause(UserVO _UserVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return (serviceBO.getDeathCause(_UserVO));
	}

	public List getLab(UserVO _UserVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return (serviceBO.getLab(_UserVO));
	}
	
	public List getLabTest(UserVO _UserVO)
	{
		MortuaryEssentialBOi serviceBO = (MortuaryEssentialBOi)super.getServiceProvider();
		return (serviceBO.getLabTest(_UserVO));
	}

}
