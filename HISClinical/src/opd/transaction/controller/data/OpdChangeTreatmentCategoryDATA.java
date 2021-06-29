package opd.transaction.controller.data;

import java.util.Map;

import registration.bo.delegate.EssentialDelegate;
import registration.bo.delegate.PatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UserVO;

public class OpdChangeTreatmentCategoryDATA extends ControllerDATA {
	
	public static Map getSecondaryCategoryChangeInitials(UserVO _userVO) 
	{
		EssentialDelegate  essentialDelegate=new EssentialDelegate();
		
		return essentialDelegate.getChangeSecondaryCategoryEssential(_userVO);		
	}
		
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO){
	   
		PatientDelegate  patientDelegate= new PatientDelegate();
		return patientDelegate.searchPatientByCrNo(_patVO,_userVO);
	}
	
	public static EpisodeVO[] getAllOpenEpisodes(String crNo, UserVO _userVO){
	  
		PatientDelegate  patientDelegate= new PatientDelegate();
		EpisodeVO[] arrOpenEpisodeVO =patientDelegate.getAllOpenEpisodesVisitedToday(crNo,_userVO);
		return arrOpenEpisodeVO;
	}
	
	public static boolean changeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO,SecondaryCategoryChangeVO[] secCatRevokeVO, UserVO _userVO){
	   
		PatientDelegate  patientDelegate= new PatientDelegate();
		return patientDelegate.changeSecondaryCategory(_secCatChangeVO,secCatRevokeVO, _userVO);
	}
	
	public static EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO)
	{
	    PatientDelegate  patientDelegate= new PatientDelegate();
		EpisodeVO[] arrOpenEpisodeVO =patientDelegate.getPatientAdmittedEpisodes(crNo,_userVO);
		return arrOpenEpisodeVO;
	}


}
