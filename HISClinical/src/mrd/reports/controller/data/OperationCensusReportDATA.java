package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;

public class OperationCensusReportDATA extends ReportDATA{

	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
	public static Map getDepartment(UserVO _userVO){
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
}
