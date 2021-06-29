package registration.transactions.controller.data;

/*
 * @ author Aadil
 * Created at 07-Apr-2014
 */

import java.util.List;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import vo.registration.EpisodeVO;
import vo.registration.MlcToNonMlcVO;
import vo.registration.PatientVO;

public class MLCtoNonMLCDATA extends ControllerDATA
{
	/** List of All Open MLC Episode of the Patient
	 * @param crNo Patient CR Number
	 * @param _userVO User VO
	 * @return
	 */
	public static EpisodeVO[] getAllProvisionalMLCEpisodes(String strCrNo_p, UserVO objUserVO_p, String visitType_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p, "", "", objUserVO_p, "9", visitType_p);
	}
	
	/** Revoking MLC Episodes 
	 * @param _mlcRevVO MLC Revoking VO
	 * @param _userVO User VO
	 * @return true if Revoked otherwise false
	 */
	public static boolean saveMlcToNonMlcEpisodes(MlcToNonMlcVO[] objArrMlcToNonMlcVO_p, UserVO objUserVO_p)
	{
		return new PatientBO().saveMlcToNonMlcEpisodes(objArrMlcToNonMlcVO_p, objUserVO_p);
	}
	
}
