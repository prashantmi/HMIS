package registration.transactions.controller.data;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import vo.registration.EpisodeVO;
import vo.registration.MlcParameterDtlVO;
import vo.registration.MlcVO;
import vo.registration.PatientBroughtByDtlVO;
import vo.registration.PoliceVerificationDtlVO;
import vo.registration.RegistrationConfigVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class EmgMlcDtlDATA extends ControllerDATA 
{
	public static RegistrationConfigVO getRegistrationConfigDtl(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getRegistrationConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_EMG,"2");
	}
	
	public static Map getEssentials(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMlcDetailEssentials(objUserVO_p);
	}
	
	public static EpisodeVO[] getEpisodeDtlForMLC(UserVO objUserVO_p, String strCrNo_p, String strVisitDate_p, String strMode_p){
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getEpisodeDtlForMLC(strMode_p, objUserVO_p, strCrNo_p, strVisitDate_p);
	}
	
	/**
	 * sets patient's address details in PatientBroughtByDtlVO
	 * 
	 * @param _patVO Provides patient details.
	 * @param _userVO Provides User details.
	 * @return array of addressVO
	 */
	public static List<PatientBroughtByDtlVO> getBroughtByDetails(PatientBroughtByDtlVO objPatientBroughtByDtlVO_p, UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getPatBroughtByDetialsList(objPatientBroughtByDtlVO_p, objUserVO_p);
	}

	public static MlcVO saveMlcDetails(MlcVO objMlcVO_p, List<MlcParameterDtlVO> lstMlcParameterDtlVO_p, UserVO objUserVO_p,
			PatientBroughtByDtlVO objPatBroughtByVO_p, PoliceVerificationDtlVO objPoliceVerDtlVO_p, 
			String strflagMlcOnlineOffline_p, boolean isTodayOfflineVisit_p) {
		
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.savePatMlcDtl(objMlcVO_p,lstMlcParameterDtlVO_p, objUserVO_p, objPatBroughtByVO_p, objPoliceVerDtlVO_p,strflagMlcOnlineOffline_p,isTodayOfflineVisit_p);
	}
	
	/*Start : Surabhi
	 * Reason : to get the patient mlc details for the certificate
	 * date : 7th oct 2016 */
	public static MlcVO getMlcDetailByCrNo(MlcVO objMlcVO_p,String strCrNo_p,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getMlcDetailByCrNo(objMlcVO_p,strCrNo_p,objUserVO_p);
	}
	//end
}
