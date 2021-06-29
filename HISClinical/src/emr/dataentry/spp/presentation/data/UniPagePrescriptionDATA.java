/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.presentation.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import registration.bo.PatientBO;
import emr.dataentry.spp.business.bo.UniPagePrescriptionBO;
import emr.dataentry.spp.business.bo.UniPagePrescriptionBOi;




public class UniPagePrescriptionDATA extends ControllerDATA {

	public static void getPatDetailOpp(PatientDetailVO ptaientDetailVO,
			UserVO userVO) {
		UniPagePrescriptionBOi delegate=new UniPagePrescriptionBO();
		delegate.getPatDetailOpp(ptaientDetailVO,userVO) ;  
	}

	public static List getAdvisedByList(String string, UserVO userVO) {
		// TODO Auto-generated method stub
	//	OpdOPPDelegate essentialDelegate = new OpdOPPDelegate();
		UniPagePrescriptionBOi essentialDelegate=new UniPagePrescriptionBO();
		List AdvisedByList = new ArrayList();
		AdvisedByList = essentialDelegate.getAdvisedByList(string,userVO);
		return AdvisedByList;
	}
	
	public static EpisodeVO getPatientAdmittedEpisodes(String crNo, UserVO _userVO){
		UniPagePrescriptionBO bo=new UniPagePrescriptionBO();
		EpisodeVO arrOpenEpisodeVO =bo.getPatientAdmittedEpisodes(crNo,_userVO);
		return arrOpenEpisodeVO;
	}
	
	
	public static EpisodeVO getPatientAdmittedEpisodesMRD(String crNo, UserVO _userVO){
		UniPagePrescriptionBO bo=new UniPagePrescriptionBO();
		EpisodeVO arrOpenEpisodeVO =bo.getPatientAdmittedEpisodesMRD(crNo,_userVO);
		return arrOpenEpisodeVO;
	}
	
	
	public static EpisodeVO[] getAllOpenEpisodesVisitedToday(String crNo, UserVO _userVO) {
		UniPagePrescriptionBO bo=new UniPagePrescriptionBO();
		EpisodeVO[] arrOpenEpisodeVO = bo.getAllOpenEpisodesVisitedTodayForTreatment(crNo, _userVO);
		return arrOpenEpisodeVO;
	}


	}
