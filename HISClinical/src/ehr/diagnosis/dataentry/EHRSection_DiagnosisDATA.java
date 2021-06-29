/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.diagnosis.dataentry;

import java.util.List;
import java.util.Map;

import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EHRSection_DiagnosisDATA extends  ControllerDATA

{	
	
	public static void saveOpdDiagnosisDetails(
			List<EHRSection_DiagnosisVO> addepisodeDiaVO, List<EHRSection_DiagnosisVO> episodeRevokeDiaVO, UserVO userVO) 
	{
		EHRSection_DiagnosisBO serviceBO = new EHRSection_DiagnosisBO();
		serviceBO.saveOpdDiagnosisDetails(addepisodeDiaVO, episodeRevokeDiaVO,userVO);

	}
	
	public static Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO, UserVO _userVO)
	{
		EHRSection_DiagnosisBO serviceBO = new EHRSection_DiagnosisBO();
		return (serviceBO.getSnomdDiagnosisEssential(_patDtlVO, _userVO));
	}
	

}
