package mrd.reports.controller.data;

import hisglobal.presentation.ReportDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import mrd.transaction.delegate.MrdEssentialDelegate;

import registration.bo.delegate.EssentialDelegate;

public class LabCensusReportDATA extends ReportDATA{

	public static Map getAllHospitalEssentials(UserVO _userVO){    	
		return new EssentialDelegate().getHospitalEssentialCombo(_userVO);
    }
	
	public static Map getDepartment(UserVO _userVO){
		return new EssentialDelegate().getDepartmentWiseTotalPatReportEssentials(_userVO);
	}
	
	public static List getLaboratoryandTimeMap(UserVO _userVO, String departmentCode) 
	{
		return new MrdEssentialDelegate().getLaboratoryandTimeMap(_userVO,departmentCode);
	}
}
