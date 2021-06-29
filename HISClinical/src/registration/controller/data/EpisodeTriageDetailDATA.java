package registration.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;
import registration.bo.delegate.PatientDelegate;

public class EpisodeTriageDetailDATA extends ControllerDATA
{
	// Getting Triage Detail Essentials
	public static Map getTriageDetailEssentials(EpisodeTriageDetailVO _episodeTriageVO, UserVO _userVO)
	{
		EssentialDelegate essentialDelegate = new EssentialDelegate();
		return essentialDelegate.getTriageDetailEssentials(_episodeTriageVO, _userVO);
	}
	
	//* Getting Last Episode in Emergency 
	public static EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		return delegate.getLastEpisodeInEmergency(_patVO, _userVO);
	}	

	//* Getting Last Episode Detail 
	public static EpisodeVO retrieveByEpisodeNo(EpisodeVO _epiVO, UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		return delegate.retrieveByEpisodeNo(_epiVO, _userVO);
	}	

	//* Saving Triage Details
	public static void saveTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		delegate.saveTriageDetails(_triVO, _userVO);
	}
	
	//* Modify Triage Details
	public static void modifyTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		delegate.modifyTriageDetails(_triVO, _userVO);
	}

	//* Getting Patient Triage Detail
	public static EpisodeTriageDetailVO getPatTriageDtl(String _crNo,String _epiCode,String _visitNo,UserVO _userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		return delegate.getPatTriageDtl(_crNo,_epiCode,_visitNo, _userVO);
	}
	
}
