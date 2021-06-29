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

public class EmgMlcModificationDtlDATA extends ControllerDATA 
{
	
	
	
	public static Map getMlcDetailEssentials(MlcVO objMlcVO_p, UserVO objUserVO_p, String strMlcMode_p){
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMlcDetailEssentials(objUserVO_p, objMlcVO_p, strMlcMode_p);
	}
	
	public static MlcVO getMlcDetails(MlcVO objMlcVO_p, UserVO objUserVO_p, String strMode_p){
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMlcDtls(objMlcVO_p, objUserVO_p, strMode_p);
	}
	
	public static List<MlcParameterDtlVO> getMlcParameterDtlList(MlcParameterDtlVO objMlcParameterDtlVO_p, UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMlcParameterDtlList(objMlcParameterDtlVO_p, objUserVO_p, "1");
	}

	/**
	 * sets patient's address details in PatientBroughtByDtlVO
	 * 
	 * @param _patVO Provides patient details.
	 * @param _userVO Provides User details.
	 * @return array of addressVO
	 */
	public static PatientBroughtByDtlVO getPatBroughtByDtlsEpisodeWise(PatientBroughtByDtlVO objPatientBroughtByDtlVO_p, UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getPatBroughtByDtlsEpisodeWise(objPatientBroughtByDtlVO_p, objUserVO_p);
	}
	
	public static MlcVO saveMlcDetails(MlcVO objMlcVO_p, List<MlcParameterDtlVO> lstMlcParameterDtlVO_p, UserVO objUserVO_p,
			PatientBroughtByDtlVO objPatBroughtByVO_p, PoliceVerificationDtlVO objPoliceVerDtlVO_p) {
		
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.savePatMlcModificationDtl(objMlcVO_p,lstMlcParameterDtlVO_p, objUserVO_p, objPatBroughtByVO_p, objPoliceVerDtlVO_p);
	}
}
