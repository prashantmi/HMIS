package registration.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.PatientVO;

public class PatientReferralDATA extends ControllerDATA {

	public static Map getAllOpenEpisodesVisitedToday(String crNo, UserVO _userVO) {
		PatientBO bo = new PatientBO();
		Map mp = bo.getAllOpenEpisodesVisitedToday(crNo, _userVO);
		return mp;
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

	public static void saveOpdReferPatient(EpisodeRefDtlVO episodeRefDtlVO,
			UserVO userVO) {
		PatientBO bo = new PatientBO();
		bo.savePatientReferDetail(episodeRefDtlVO, userVO);

	}
	
	public static PatientVO getPatientDtl(PatientVO objPatVO_p, UserVO objUserVO_p){
		   
		PatientBO  objPatientBO= new PatientBO();
		objPatVO_p=objPatientBO.searchPatientByCrNo(objPatVO_p,objUserVO_p);
		
	    return objPatVO_p;
	}

}
