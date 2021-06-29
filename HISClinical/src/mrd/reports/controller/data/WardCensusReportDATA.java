package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

import registration.bo.delegate.EssentialDelegate;

public class WardCensusReportDATA extends ReportDATA
{
	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	public static Map getDepartment(UserVO _userVO){
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO){
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
	
	public static List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode,UserVO _userVO){
		return new MrdEssentialDelegate().getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO);
	}
	
	public static List getRoomBasedOnHospitalDeptUnitWard(String _hosCode,String _deptUnitCode,String _wardCode){
		return new MrdEssentialDelegate().getRoomBasedOnHospitalDeptUnitWard(_hosCode, _deptUnitCode, _wardCode);
	}
}


