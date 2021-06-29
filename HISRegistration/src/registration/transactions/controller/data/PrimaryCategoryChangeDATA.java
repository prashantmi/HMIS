package registration.transactions.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import registration.bo.PatientBO;
import registration.bo.RegEssentialBO;
import vo.registration.EmpMasterVO;
import vo.registration.EpisodeRefDtlVO;
import vo.registration.PatientVO;
import vo.registration.PrimaryCategoryChangeVO;
import vo.registration.VerificationDocumentVO;

public class PrimaryCategoryChangeDATA extends ControllerDATA {

	@SuppressWarnings("rawtypes")
	public static Map getPrimaryCategoryChangeInitials(String crNo,String patCatCode, UserVO _userVO) {
		RegEssentialBO bo = new RegEssentialBO();
		Map mp = bo.getChangePrimaryCategoryEssential(crNo,patCatCode, _userVO);
		return mp;
	}
	
	
	public static PatientVO getPatientDtl(PatientVO _patVO, UserVO _userVO) {
		PatientBO bo = new PatientBO();
		_patVO = bo.searchPatientByCrNo(_patVO,_userVO);
		return _patVO;
	}

	public static boolean changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO,VerificationDocumentVO _veriDocVO,EmpMasterVO _empMstVO){
		PatientBO bo = new PatientBO();
		return bo.changePrimaryCategory(_primCatChangeVO, _userVO,_veriDocVO,_empMstVO);
	}

	@SuppressWarnings("rawtypes")
	public static List getPrimaryCatListExceptPatientCat(String patCat,UserVO userVO){
		PatientBO bo = new PatientBO();
		return bo.getPrimaryCatListExceptPatientCat(patCat,userVO);
	}
	public static List getDistrictBasedOnState(UserVO _userVO, String strStateCode_p,String strCountryCode) 
	{
		PatientBO bo = new PatientBO();
		return bo.getDistrictBasedOnState(_userVO,strStateCode_p,strCountryCode);
	}
	public static List getRelationsList(UserVO _userVO) 
	{
		PatientBO bo = new PatientBO();
		return bo.getRelationsList(_userVO);
	}
	public static List getCompanyList(UserVO _userVO) 
	{
		PatientBO bo = new PatientBO();
		return bo.getCompanyList(_userVO);
	}
	
	public static List getVerificationDocumetByCatCode(String catCode,UserVO userVO){
		RegEssentialBO bo = new RegEssentialBO();
		//return bo.getVerificationDocumetByCatCode(catCode,userVO);		
		return bo.getCatVerificationDocumetByCatCode(catCode,userVO);
	}
	public static PrimaryCategoryChangeVO[] getpatcatchangeLog	( String crNo,UserVO objUserVO_p,PrimaryCategoryChangeVO catchangevo)
	{
		PatientBO objPatientBO= new PatientBO();
		PrimaryCategoryChangeVO[] arrEpisodecatLogVO=objPatientBO.getpatcatchangeLog(crNo,objUserVO_p,catchangevo);
		return arrEpisodecatLogVO;
	}
	//By Mukund for Audit Log
	public static List getauditLog( String crNo,UserVO objUserVO_p,PrimaryCategoryChangeVO catchangevo)
	{
		PatientBO objPatientBO= new PatientBO();
		List alrecordList=objPatientBO.getauditLog(crNo,objUserVO_p,catchangevo);
		return alrecordList;
	}//End
	
}
