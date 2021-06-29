package opd.reports.controller.data;

import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

import registration.bo.delegate.EssentialDelegate;
import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

public class DiagnosisWiseOpdAndIpdReportDATA extends ReportDATA
{
	public static Map getAllHospitalEssentials(UserVO _userVO)
	{    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
	public static Map getAllConsultantForReportEssentials(UserVO _userVO)
	{    	
		return new MrdEssentialDelegate().getAllConsultant(_userVO);
    }
    
}

