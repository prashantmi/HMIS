package registration.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import vo.registration.ChangeTreatmentCategoryVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;

public class ChangeTreatmentCategoryDATA extends ControllerDATA {

	public static EpisodeVO[] getAllOpenEpisodesVisitedToday(String crNo, UserVO _userVO) {
		PatientBO bo = new PatientBO();
		EpisodeVO[] arrOpenEpisodeVO = bo.getAllOpenEpisodesVisitedTodayForTreatment(crNo, _userVO);
		return arrOpenEpisodeVO;
	}

	public static Map getPatientReferralEssentials(String _crNO, UserVO _UserVO) {
		RegEssentialBO bo = new RegEssentialBO();
		Map mp = bo.getPatientReferralEssentials(_crNO, "", _UserVO);
		return mp;
	}

	public static List getRefDept_AJAX(UserVO _UserVO, String strRefHospCode_p) {
		RegEssentialBO essentialBO = new RegEssentialBO();
		return essentialBO.getRefDeptBasedOnRefHosp_AJAX(_UserVO,
				strRefHospCode_p);
	}

	public static void changeTreatmentCategory(ChangeTreatmentCategoryVO[] _TreatmentCategoryChangeVO,ChangeTreatmentCategoryVO[] TreatmentCategoryRevokeVO, UserVO _userVO) {
		PatientBO bo = new PatientBO();
		bo.changeTreatmentCategory(_TreatmentCategoryChangeVO,TreatmentCategoryRevokeVO, _userVO);

	}
	
	//public static void changeIPDTreatmentCategory(ChangeTreatmentCategoryVO _admittedPatientVO, UserVO _userVO) {
	public static void changeIPDTreatmentCategory(ChangeTreatmentCategoryVO[] _admittedPatientVO, UserVO _userVO) {
		PatientBO bo = new PatientBO();
		bo.changeIPDTreatmentCategory(_admittedPatientVO, _userVO);

	}
	public static PatientVO getPatientDtl(PatientVO objPatVO_p, UserVO objUserVO_p){
		   
		PatientBO  objPatientBO= new PatientBO();
		objPatVO_p=objPatientBO.searchPatientByCrNo(objPatVO_p,objUserVO_p);
		
	    return objPatVO_p;
	}
	public static Map getTreatmentCategoryChangeInitials(UserVO _userVO) 
	{
		PatientBO bo = new PatientBO();
		Map mp=bo.getTreatmentCategoryEssential(_userVO);
		return mp;		
	}
	public static EpisodeVO getPatientAdmittedEpisodes(String crNo, UserVO _userVO){
		PatientBO bo = new PatientBO();
		EpisodeVO arrOpenEpisodeVO =bo.getPatientAdmittedEpisodes(crNo,_userVO);
		return arrOpenEpisodeVO;
	}
	
	//Added by Vasu dated on 9-April-2018 for adding multiple treatment categories into a particular episode
	public static void changePreviousTreatmentCategory(ChangeTreatmentCategoryVO[] _TreatmentCategoryChangeVO, UserVO _userVO) {
		PatientBO bo = new PatientBO();
		bo.changePreviousTreatmentCategory(_TreatmentCategoryChangeVO, _userVO);

	}
	
	//Added by Vasu on 23.April.18 for fetching previous saved treatment categories
	
	public static EpisodeVO[] getAllPreviousTreatmentCategories(String crNo, String episodeCode,UserVO _userVO) {
		PatientBO bo = new PatientBO();
		EpisodeVO[] previousTreatmentCategories = bo.getPreviousTreatmentCategoriesForSelectedEpisode(crNo, episodeCode, _userVO);
		return previousTreatmentCategories;
	}
}
