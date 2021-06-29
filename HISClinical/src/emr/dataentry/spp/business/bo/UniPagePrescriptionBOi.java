/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.business.bo;

import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
//import hisglobal.vo.Apt_appointmentDtlVO;
//import hisglobal.vo.Apt_slotDtlVO;
import java.util.Map;


public interface UniPagePrescriptionBOi {
	public void getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO);

	public List getAdvisedByList(String string, UserVO userVO);

	public Map getSnomdDiagnosisEssential(PatientDetailVO _patDtlVO,
			UserVO _userVO);
	
}
