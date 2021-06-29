package mrd.transaction.dao;

import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.UserVO;

public interface InsuranceDetailDAOi 
{
	public void creat(InsuranceDetailVO insuranceDetailVO,UserVO userVO);
	
	public void saveInsuranceClaimRecordEntry(InsuranceDetailVO insuranceDetailVO,UserVO userVO);
}
