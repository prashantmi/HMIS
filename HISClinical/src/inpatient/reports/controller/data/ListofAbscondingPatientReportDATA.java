package inpatient.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import registration.bo.delegate.EssentialDelegate;

public class ListofAbscondingPatientReportDATA extends ReportDATA
{
	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	public static Map getAllGlobalDepartment(UserVO _userVO){
		return new EssentialDelegate().getAllGlobalDepartment(_userVO);
	}
	
	public static List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode,UserVO _userVO){
		return new InPatientEssentialDelegate().getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO);
	}
	
	
}


