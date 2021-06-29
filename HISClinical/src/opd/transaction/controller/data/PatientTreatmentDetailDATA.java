package opd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.UserVO;

public class PatientTreatmentDetailDATA extends ControllerDATA
{
	// Getting Patient Treatment Detail Essentials
	public static Map getPatTreatmentDetailEssential(String _patCrNo, String _episodeCode,String deptUnitCode,String genderType,  PatientDetailVO _patDetailVO, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getPatTreatmentDetailEssential(_patCrNo, _episodeCode,deptUnitCode,genderType, _patDetailVO, _userVO);
	} 

	public static void savePatTreatmentDetail(List<PatDrugTreatmentDetailVO> _lstDrugDtl,List<PatExtTreatmentDetailVO> _lstExtDtl,PatDietAdviceDetailVO _patDietDetailVO,Map drugScheduleMap,RestAdviceDtlVO restAdviceDtlVO,List drugAdminList,List extAdminVoList,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		delegate.savePatTreatmentDetail(_lstDrugDtl,_lstExtDtl,_patDietDetailVO,drugScheduleMap,restAdviceDtlVO,drugAdminList,extAdminVoList ,_userVO);
	}
	
	public static String getDrugAllergyAllerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getDrugAllergyAllerts(patDrugDtlVO, _userVO);
	}
	
	public static String getPrevDrugStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPrevDrugStatus(patDrugDtlVO, _userVO);
	}
	
	public static String getdrugAdviceAlerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getdrugAdviceAlerts(patDrugDtlVO, _userVO);
	}
	
	
	public static String getdrugReactionStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getdrugReactionStatus(patDrugDtlVO, _userVO);
	}
	
	public static Map getDrugProperties(String itemId,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getDrugProperties(itemId, _userVO);
	}

	public static List getParticularDrugListDtl(String drugListId, UserVO userVO) {
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getParticularDrugListDtl(drugListId, userVO);
		
	}
	public static List treatmentDetailList(String patcrNO,String episodeCode, UserVO userVO) {
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.treatmentDetailList(patcrNO,episodeCode, userVO);
		
	}

	
}
