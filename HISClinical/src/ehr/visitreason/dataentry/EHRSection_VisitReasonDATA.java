package ehr.visitreason.dataentry;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import ehr.visitreason.vo.EHRSection_VisitReasonVO;

public class EHRSection_VisitReasonDATA extends  ControllerDATA{

	public static EHRSection_VisitReasonVO[] getEssentials(PatientDetailVO visitVO,
			UserVO userVO) {
		EHRSection_VisitReasonBO serviceBO = new EHRSection_VisitReasonBO();
		return serviceBO.getEssentials(visitVO, userVO);
	}

	public static void saveDetails(EHRSection_VisitReasonVO visitVO,
			UserVO userVO) {
		EHRSection_VisitReasonBO serviceBO = new EHRSection_VisitReasonBO();
		serviceBO.saveDetails(visitVO, userVO);
		
	}

}
