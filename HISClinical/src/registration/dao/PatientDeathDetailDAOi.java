package registration.dao;

import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.UserVO;

public interface PatientDeathDetailDAOi 
{
	public void create(PatientDeathDetailVO patDeathDtlVO,UserVO userVO);
	
	public boolean checkRecordAdded(String crNo,UserVO userVO);
	
	public String generateDeathCertificateId(PatientDeathDetailVO patDeathDtlVO,String genMode, UserVO userVO);
	
	public PatientDeathDetailVO getDeathDetailByCrNo(String crNo,UserVO userVO);
	
	public PatientDeathDetailVO getHandoverToDetail(String crNo,UserVO userVO);
	
	public void updateHandoverDetailTo(PatientDeathDetailVO patDeathDtlVO, UserVO _userVO);
	
	public void updateCertificateId(PatientDeathDetailVO patDeathDtlVO, UserVO _userVO);
	
	public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO);
	
}
