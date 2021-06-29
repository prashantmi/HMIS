/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package emr.dataentry.spp.persistence.dao;

import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface UniPagePrescriptionDAOi
{
	public void getPatDetailOpp(PatientDetailVO ptaientDetailVO, UserVO userVO);

	public List getAdvisedByList(String string, UserVO userVO);
}