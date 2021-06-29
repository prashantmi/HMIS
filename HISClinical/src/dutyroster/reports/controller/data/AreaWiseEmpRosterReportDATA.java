
package dutyroster.reports.controller.data;

import hisglobal.presentation.ControllerDATA;
import java.util.List;
import java.util.Map;

import registration.bo.delegate.EssentialDelegate;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;
import dutyroster.masters.delegate.DutyRosterEssentialDelegate;
import dutyroster.masters.delegate.DutyRosterMasterDelegate;
import dutyroster.transaction.delegate.DutyRosterDelegate;


public class AreaWiseEmpRosterReportDATA  extends ControllerDATA 
{
		
	public static List getDutyRosterCategory(UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getEmpWiseRosterCategory(_userVO);	
	}
	public static Map getAllHospitalEssentials(UserVO _userVO)
	{    	
		return new DutyRosterEssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
	public static List getRosterCategoryBasedOnHospital(String hospitalCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getRosterCategoryBasedOnHospital(hospitalCode,_userVO);
	}
	public static List getDutyAreasBasedOnRosterCategory(String rosterCategory,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDutyAreasBasedOnRosterCategory(rosterCategory,_userVO);
	}
	
	public static List getRostersBasedOnDutyAreaAndRosterCatg(String rosterCategory,String _areaTypeCode,String _areaCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getRostersBasedOnDutyAreaAndRosterCatg(rosterCategory,_areaTypeCode,_areaCode,_userVO);
	}
	
	public static List getDesignationBasedOnHospital(String hospitalCode,UserVO _userVO)
	{
		DutyRosterEssentialDelegate delegateObj = new DutyRosterEssentialDelegate();
		return delegateObj.getDesignationBasedOnHospital(hospitalCode,_userVO);
	}
	public static Map getAreaWiseReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO,String _rosterCatg)
	{
		DutyRosterDelegate delegateObj = new DutyRosterDelegate();
		return delegateObj.getAreaWiseReport(_year,_month,_areaTypeCode, _areaCode, _rosterId, _userVO,_rosterCatg);
	}
	
	
		
}
