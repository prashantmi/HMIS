package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class ASRDORWiseHospitalRegistrationReportDATA extends ReportDATA
	{
		public static Map getAgeSexReligionHosRegEssentials(UserVO _userVO)
		{
			return new EssentialDelegate().getAgeSexReligionHosRegEssentials(_userVO);
		}
		
	}

