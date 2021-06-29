package inpatient.reports.controller.data;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;
import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class DeathDetailsReportDATA  extends ReportDATA{
	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
}
