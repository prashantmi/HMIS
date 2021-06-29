package inpatient.reports.controller.data;



import java.util.List;
import java.util.Map;

import inpatient.transaction.delegate.InPatientEssentialDelegate;
import registration.bo.delegate.EssentialDelegate;
import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class ListOfNonMlcPatientReportDATA extends ReportDATA{
	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	public static Map getDepartment(UserVO _userVO){
		return new EssentialDelegate().getAllGlobalDepartment(_userVO);
	}
	
	public static List getUnitBasedOnDepartment(String _deptCode,UserVO _userVO){
		return new EssentialDelegate().getUnitBasedOnDept(_deptCode,_userVO);
	}
	
	public static List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode,UserVO _userVO){
		return new InPatientEssentialDelegate().getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO);
	}
	
}
