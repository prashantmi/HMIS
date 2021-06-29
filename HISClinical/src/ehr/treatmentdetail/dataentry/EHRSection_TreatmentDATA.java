/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentdetail.dataentry;


import java.util.List;
import java.util.Map;

import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.UserVO;

public class EHRSection_TreatmentDATA extends ControllerDATA
{
	// Getting Patient Treatment Detail Essentials
	public static Map getPatTreatmentDetailEssential(String _patCrNo,String adviceType, String _episodeCode,String deptUnitCode,String genderType,  PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		EHRSection_TreatmentBO delegate = new EHRSection_TreatmentBO();
		return delegate.getPatTreatmentDetailEssential(_patCrNo, adviceType, _episodeCode,deptUnitCode,genderType, _patDetailVO, _userVO);
	} 

	/*public static void savePatTreatmentDetail(List<EHRSection_TreatmentVO> _lstDrugDtl, Map drugScheduleMap,List drugAdminList,UserVO _userVO)
	{
		EHRSection_TreatmentBO delegate = new EHRSection_TreatmentBO();
		delegate.savePatTreatmentDetail(_lstDrugDtl,drugScheduleMap,drugAdminList,_userVO);
	}*/
	
	public static void savePatTreatmentDetail(List<EHRSection_TreatmentVO> lstDrugTreatmentDtl, List<EHRSection_TreatmentVO> revokedDrugTreatmentDtl,  Map drugScheduleMap,List drugAdminList,UserVO _userVO)
	{
		EHRSection_TreatmentBO delegate = new EHRSection_TreatmentBO();
		delegate.savePatTreatmentDetail(lstDrugTreatmentDtl,revokedDrugTreatmentDtl,drugScheduleMap,drugAdminList,_userVO);
	}
	
	
	public static String getdrugAdviceAlerts(EHRSection_TreatmentVO patDrugDtlVO,UserVO _userVO)
	{
		EHRSection_TreatmentBO delegate = new EHRSection_TreatmentBO();
		return delegate.getdrugAdviceAlerts(patDrugDtlVO, _userVO);
	}
	
	
	public static List treatmentDetailList(String patcrNO,String episodeCode, UserVO userVO) {
		EHRSection_TreatmentBO delegate = new EHRSection_TreatmentBO();
		return delegate.treatmentDetailList(patcrNO,episodeCode, userVO);
		
	}
	
	public static List getParticularDrugListDtl(String drugListId, UserVO userVO) {
		EHRSection_TreatmentBO delegate = new EHRSection_TreatmentBO();
		return delegate.getParticularDrugListDtl(drugListId, userVO);
		
	}


	
}
