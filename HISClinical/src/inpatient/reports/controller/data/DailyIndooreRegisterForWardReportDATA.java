package inpatient.reports.controller.data;

import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.List;
import java.util.Map;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class DailyIndooreRegisterForWardReportDATA extends ReportDATA{
	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new InPatientEssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	public static Map getAllGlobalDepartment(UserVO _userVO){
		return new InPatientEssentialDelegate().getAllGlobalDepartment(_userVO);
	}
	
	public static List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode,UserVO _userVO){
		return new InPatientEssentialDelegate().getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO);
	}
	
	public static Map getRegAndPatCategory(UserVO _userVO){
		return new InPatientEssentialDelegate().getRegAndPatCategory(_userVO);
	}
	
	public static Map getDischargeStatus(UserVO _userVO){
		return new InPatientEssentialDelegate().getDischargeStatus(_userVO);
	}
	
	
}
