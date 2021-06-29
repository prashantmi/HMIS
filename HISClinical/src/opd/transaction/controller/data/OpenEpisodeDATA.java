package opd.transaction.controller.data;

import opd.bo.delegate.OpdPatientDelegate;
import registration.bo.delegate.PatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

public class OpenEpisodeDATA extends ControllerDATA
{
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO){
	    PatientDelegate  patientDelegate= new PatientDelegate();
		_patVO=patientDelegate.searchPatientByCrNo(_patVO,_userVO);
		return _patVO;
	}
	public static EpisodeCloseVO[] getPatientEpisodeDtl(String crNo,UserVO _userVO,PatientVO _patVO)
	{
		OpdPatientDelegate patientDelegate=new OpdPatientDelegate();
		EpisodeCloseVO[] episodeClosevo=patientDelegate.getPatientEpisodeDtl(crNo,_userVO,_patVO);
		
		return episodeClosevo;
	}
	
	public static void updatePatientEpisode(String crNo,UserVO _userVO,EpisodeCloseVO[] selectedEpisodeCloseVO)
	{
		OpdPatientDelegate patientDelegate=new OpdPatientDelegate();
		patientDelegate.updatePatientEpisode(crNo,_userVO,selectedEpisodeCloseVO);
	}
}

