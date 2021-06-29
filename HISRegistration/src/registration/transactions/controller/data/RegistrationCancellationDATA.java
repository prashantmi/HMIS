package registration.transactions.controller.data;

/*
 * @ author Pragya Sharma
 * Created at 04-Aug-2011
 */

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import vo.registration.DirectChageDetailVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegCardPrintVO;
import vo.registration.RegistrationCancellationVO;
import vo.registration.RenewalConfigVO;

public class RegistrationCancellationDATA extends ControllerDATA
{

/**
 * gets patient's Episode Details by CR No
 * @param _patVO Provides patient details.
 * @param _userVO Provides User details.
 * @param _status String 
 * @return array of episodeVO Providing episode details.
 */
 public static Map getPatientEpisodeDtl(String _patCrNO, UserVO _userVO){
		PatientBO bo = new PatientBO();
		Map mp =  bo.getAllEpisodesForCancellation(_patCrNO, _userVO);//.searchPatientEpiosdeByCrNo(_patVO, _userVO,  RegistrationConfig.EPISODE_VISIT_TYPE_OPD);
		return mp;
	}
	
/** gets the System Date from essential Delegate
 * 
 * @return sys date
 */
  
 public static String getBillAmount(String primCatcode, UserVO _userVO){		
		RegEssentialBO  essentialBO=new RegEssentialBO();
	    String billAmount=essentialBO.getBillAmountBasedOnCategory(primCatcode, _userVO);
	    return billAmount;		
	}
 
 /**
	 * searches patient detail by name
	 * @param searchName String containing text for search
	 * @param _userVO Provides User details.
	 * @return patientVO array of patientVO Providing patient details.
	 */

public static PatientVO[] getPatientDtlByName(String searchName, UserVO userVO) {
	// TODO Auto-generated method stub
	return null;
}
/**
 * saves duplicate card printing recor
 * @param _episodeVO object of episodeVO
 * @param _duplicateRenewVO object of duplicateRenewVO
 * @param _userVO Provides User details.
 * @return DuplicateRenewVO  
 */


public static RegistrationCancellationVO saveRegistrationCancellation(RegistrationCancellationVO regCancallationVO, UserVO _userVO){
	PatientBO bo = new PatientBO();
	return bo.saveRegistrationCancellationDtl(regCancallationVO, _userVO);
	}

public static List<RenewalConfigVO> getMapOfRenewalConfigDtl(UserVO objUserVO_p)
{
	PatientBO bo = new PatientBO();
	return bo.getMapOfRenewalConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD);
}

public static RegistrationCancellationVO updateExpirydates(RegistrationCancellationVO regCancallationVO, UserVO _userVO,String strMode)
{
	PatientBO bo = new PatientBO();
	return bo.updateExpiryDates(regCancallationVO, _userVO,strMode);
}	

}
