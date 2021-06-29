package mrd.transaction.dao;

import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface PatientFamilyDtlDAOi 
{
	public List<PatientFamilyDtlVO> getPatAttendantDtlByCrNo(String patCrNo, UserVO userVO);
	
	public void create(PatientFamilyDtlVO patFamilyVO,UserVO userVO);
	
	public String generatePatientRelativeId(String patCrNo);

	// returns true if Relative already exists
	public boolean isExist(PatientFamilyDtlVO patFamilyVO,UserVO userVO);
}
