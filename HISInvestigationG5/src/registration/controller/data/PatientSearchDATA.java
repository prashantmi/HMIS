package registration.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;

public class PatientSearchDATA extends ControllerDATA {

	

	public static Map getPatientSearchEssentials(UserVO _UserVO, String strStateCode) {
		RegEssentialBO bo = new RegEssentialBO();
		Map mp = bo.getPatientSearchEssentials( _UserVO,strStateCode);
		return mp;
	}
	
	public static List getState_AJAX(UserVO _UserVO, String strCountryCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getStateBasedOnCountry_AJAX(_UserVO,strCountryCode_p);
	}
	public static List getDistrict_AJAX(UserVO _UserVO,String strStateCode, String strCountryCode_p)
	{
		RegEssentialBO  essentialBO=new RegEssentialBO();
		return essentialBO.getDistrictBasedOnState_AJAX(_UserVO,strStateCode,strCountryCode_p);
	}

	

}
