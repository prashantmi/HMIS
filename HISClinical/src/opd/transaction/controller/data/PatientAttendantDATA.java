package opd.transaction.controller.data;

import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.UserVO;

public class PatientAttendantDATA extends ControllerDATA
{
	public static EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getAllEpisodeOfThePatientTodayVisited(crNo,userVO);
	}
	
	public static Map getPatientAddedAttendant(String strPatCrNo_p, String strEpisodeCode_p, UserVO strUserVO_p)
	{
		OpdEssentialDelegate opdEssDelegate= new OpdEssentialDelegate();
		return opdEssDelegate.getPatientAddedAttendant(strPatCrNo_p, strEpisodeCode_p, strUserVO_p);
	}
	
	public static void savePatientAttendantDtl(String isExisting,PatientFamilyDtlVO patFamilyVO,EpisodeAttendantDetailVO epiAttendantVO,UserVO userVO)
	{
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		patientDelegate.savePatientAttendantDtl(isExisting,patFamilyVO,epiAttendantVO,userVO);
	}
}
