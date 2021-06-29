package mrd.transaction.dao;

import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.UserVO;

public interface BirthDeathUploadDAOi 
{
	public void insertUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO); 
	
	public void insertUploadHandover(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO); 
	
	public BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType, String crNo,UserVO userVO);
	
	public void insertHandover(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO);
	
}
