package mrd.transaction.dao;

import hisglobal.vo.PatFamilyDocDtlVO;
import hisglobal.vo.UserVO;

public interface PatFamilyDocDtlDAOi 
{
	public PatFamilyDocDtlVO[] getExistingFamilyDoctorRecord(String crNo,UserVO userVO);
	
	public void savePatientFamilyDoctorDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO);
	
	public PatFamilyDocDtlVO getPatientFamilyDocDetail(String chk,UserVO userVO);
	
	public void modifyPatientFamilyDocDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO);
	
	public void revokePatientFamilyDocDetail(String crNo,String hCode,String slNo,UserVO userVO);
}
