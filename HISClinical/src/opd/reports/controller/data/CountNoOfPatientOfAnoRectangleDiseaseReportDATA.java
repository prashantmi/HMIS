package opd.reports.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

import registration.bo.delegate.EssentialDelegate;
import hisglobal.presentation.ReportDATA;
import opd.bo.delegate.OpdEssentialDelegate;
import hisglobal.vo.UserVO;

public class CountNoOfPatientOfAnoRectangleDiseaseReportDATA extends ReportDATA
{
	public static Map getAllHospitalEssentials(UserVO _userVO)
	{    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
	public static Map getMinYearForEssentialForReport(UserVO _userVO, String strMode_p)
	{    	
		return new OpdEssentialDelegate().getMinYearEssential(_userVO, strMode_p);
    }
	    
}

