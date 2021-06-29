package inpatient.masters.controller.data;

/**
 * @author  CDAC
 */

import java.util.List;
import java.util.Map;

import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.masters.controller.*;
import inpatient.masters.delegate.MasterDelegate;

public class UnitInvParaMappingMstDATA 
{
	//* Getting Essentials for Adding into User Desk Menu Master
	public static Map getEssentials(UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		Map mp=essentialDelegate.getEssentialsForUnitWise(_UserVO);
		return mp;		
	}
	public static Map getEssentialsForModify(UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		Map mp=essentialDelegate.getEssentialsForUnitWiseForModify(_UserVO);
		return mp;		
	}
	
	//* Getting Essentials for Adding into User Desk Menu Master
	public static Map getEssentialsForWardWise(UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		Map mp=essentialDelegate.getEssentialsForWardWise(_UserVO);
		return mp;		
	}
	public static List getParameter(UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return(essentialDelegate.getParameter(_UserVO));
	}
	
	public static List getParameterForModify(UnitInvParaMappingVO _unitInvParaMapVo,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return(essentialDelegate.getParameterForModify(_unitInvParaMapVo,_UserVO));
	}
	
	public static List getParameterForWardWise(UnitInvParaMappingVO _unitInvParaMapVo,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return(essentialDelegate.getParameterForWardWise(_unitInvParaMapVo,_UserVO));
	}
	
	public static void saveUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		MasterDelegate masterDelegate=new MasterDelegate();
		masterDelegate.saveUnitWise(_voUDMT, _UserVO);
	}
	
	public static void updateTableUnitWise(String _unitId,UserVO _UserVO)
	{
		MasterDelegate masterDelegate=new MasterDelegate();
		masterDelegate.updateTableUnitWise(_unitId, _UserVO);
	}
	
	public static void updateTableWardWise(String _unitId,String _wardCode, UserVO _UserVO)
	{
		MasterDelegate masterDelegate=new MasterDelegate();
		masterDelegate.updateTableWardWise(_unitId,_wardCode, _UserVO);
	}
	
	public static void saveUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		MasterDelegate masterDelegate=new MasterDelegate();
		masterDelegate.saveUnitWardWise(_voUDMT, _UserVO);
	}
	
	public static UnitInvParaMappingVO fetchData(UnitInvParaMappingVO _UnitInvParaMStVO, UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return essentialDelegate.getData(_UnitInvParaMStVO ,_UserVO);
	}

	public static List getWards(String _deptUnitCode,UserVO _UserVO)
	{
		MasterDelegate masterDelegate=new MasterDelegate();
		return (masterDelegate.gettingWards(_deptUnitCode,_UserVO));
	}
	
	public static List getWardsForModify(String _unitId,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return essentialDelegate.getWardsForModify(_unitId,_UserVO);
	}
	
	public static UnitInvParaMappingVO getWardName(UnitInvParaMappingVO _unitInvParaMapVO,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return essentialDelegate.getWardName(_unitInvParaMapVO,_UserVO);
	}
	
	/*public static List getWardList(UnitInvParaMappingVO voUDMT,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return essentialDelegate.getWardList(voUDMT,_UserVO);
	}*/
	
	public static UnitInvParaMappingVO getParameterName(String _paraCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return (essentialDelegate.getParameterName(_paraCode,_UserVO));
	}
	
	public static List getParaListForModify(String _slno,String _unitId,String _wardCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return (essentialDelegate.getParaListForModify(_slno,_unitId,_wardCode,_UserVO));
	}
	
	public static List getParaListForWardWise(String _slno,String _unitId,String _wardCode,UserVO _UserVO)
	{
		InPatientEssentialDelegate essentialDelegate=new InPatientEssentialDelegate();
		return (essentialDelegate.getParaListForWardWise(_slno,_unitId,_wardCode,_UserVO));
	}
}