package registration.transactions.controller.data;

import vo.registration.BeneficiaryPatientVO;
import vo.registration.CrNoMergeDtlVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.EpisodeVO;
import vo.registration.PatientVO;
import vo.registration.RegistrationConfigVO;
import vo.registration.RenewalConfigVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientVisitSUP;
import registration.transactions.controller.fb.SplPatientVisitFB;


public class CrNoMergeDATA  {
	
	public static Map<String, RenewalConfigVO> getMapOfRenewalConfigDtlOnKeyPatCat(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getMapOfRenewalConfigDtlOnKeyPatCat(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD);
	}	
	
	public static RegistrationConfigVO getRegistrationConfigDtl(UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getRegistrationConfigDtl(objUserVO_p,RegistrationConfig.REGISTRATIONDESK_RENEWEL_CONFIG_OPD,"2");
	}	
	
	public static PatientVO getPatientDtl(PatientVO objPatVO_p, UserVO objUserVO_p){
	   
		PatientBO  objPatientBO= new PatientBO();
		objPatVO_p=objPatientBO.searchPatientByCrNo(objPatVO_p,objUserVO_p);
		
	    return objPatVO_p;
	}
	
	public static PatientVO getNotUsedCrNo(String crNo,UserVO userVO)
	{
		PatientBO objPatientBO=new PatientBO();
		return objPatientBO.getNotUsedCrNo(crNo,userVO);
	}
	
	public static void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO)
	{
		PatientBO objPatientBO=new PatientBO();
		objPatientBO.saveNotUsedCrNo(lstNotUsedCRNo,userVO);
	}
	
	/*public static PatientVO[] searchPatient(HashMap searchMap,UserVO userVO)
	{
		PatientBO objPatientBO=new PatientBO();
		return objPatientBO.searchPatient(searchMap,userVO);
	}*/
	
	public static List<PatientVO> getMergedCrNo(String crNo,UserVO userVO)
	{
		PatientBO objPatientBO=new PatientBO();
		return objPatientBO.getMergedCrNo(crNo,userVO);
	}
	
	public static void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO)
	{
		PatientBO objPatientBO=new PatientBO();
		objPatientBO.revokeMergedCRNo(reason,mainCrNo,crNo,userVO);
	}
	
	
	
}