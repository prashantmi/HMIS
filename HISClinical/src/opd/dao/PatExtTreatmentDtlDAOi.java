package opd.dao;

import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.UserVO;

public interface PatExtTreatmentDtlDAOi {
	
	public void savePatExtTreatmentDetail(PatExtTreatmentDetailVO _patExtTreatmentDetailVO, UserVO _userVO);
	public void updateRevoke(PatExtTreatmentDetailVO _patExtTreatDtlVO, UserVO _userVO);
	public void updateLastTodayVisitRecord(PatExtTreatmentDetailVO _patExtTreatDtlVO, UserVO _userVO);
	public void saveRxandRevoke(PatExtTreatmentDetailVO _patExtTreatmentDetailVO, UserVO _userVO);
}
