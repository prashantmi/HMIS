package registration.transactions.controller.data;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import registration.bo.RegMasterBO;
import vo.registration.EpisodeVO;
import vo.registration.PatientDeathDetailVO;
import vo.registration.PatientVO;
import vo.registration.UnitConsultantVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

public class PatientDeathDetailDATA extends ControllerDATA{
	public static Map getPatientdeathDetailEssential(String strCrNo_p,String strEpisodeCode_p,UserVO objUserVO_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getPatientdeathDetailEssential(strCrNo_p,strEpisodeCode_p,objUserVO_p);
	}
	
	public static boolean isPatientDeathDtlAdded(String strCrNo_p,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.isPatientDeathDtlAdded(strCrNo_p,objUserVO_p);
	}
	
	public static List getConsultantList(UnitConsultantVO objUnitConsultantVO_p,UserVO uservo){
		RegMasterBO bo = new RegMasterBO();
		return bo.getConsultantList(objUnitConsultantVO_p, uservo);	
	}
	public static EpisodeVO[] getOpenEpisodes(String strCrNo_p, UserVO objUserVO_p, String visitType_p)
	{
		RegEssentialBO objRegEssentialBO = new RegEssentialBO();
		return objRegEssentialBO.getEpisodeDtlByCrNoByDeptByUnit(strCrNo_p, "", "", objUserVO_p, "10", visitType_p);
	}
	public static void savePatientDeathDetail(PatientDeathDetailVO objPatDeathDtlVO_p, PatientVO objPatientVO_p,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		objPatientBO.savePatientDeathDetail(objPatDeathDtlVO_p,objPatientVO_p,objUserVO_p);
	}
	
	/*Start : Surabhi
	 * Reason : to get the patient death details for the certificate
	 * date : 7th oct 2016 */
	public static PatientDeathDetailVO getDeathDetailByCrNo(String strCrNo_p,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getDeathDetailByCrNo(strCrNo_p,objUserVO_p);
	}
	//end
	
	/*public static AddressVO getPatAddress(String strCrNo_p,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getPatAddress(strCrNo_p,objUserVO_p);
	}
	
	public static String getPatientMlcNo(PatientDetailVO selectedPatientVO,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getPatientMlcNo(selectedPatientVO,objUserVO_p);
	}
	
	public static ANCDetailVO getPatientANCDetail(String strCrNo_p,UserVO objUserVO_p)
	{
		PatientBO objPatientBO = new PatientBO();
		return objPatientBO.getPatientANCDetail(strCrNo_p,objUserVO_p);
	}*/
}
