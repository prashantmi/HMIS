package inpatient.transaction.dao;

import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.UserVO;

public interface PatDischargeRequestDAOi 
{
	public void create(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
	
	public PatDischargeReqDtlVO getDischargeRemarks(String admNo,UserVO userVO);
	
	public String getDischargeStatus(String admNo,UserVO userVO);//added by swati on date11-06-2019
	
	
	public void crossmatched_bag_revert(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
}
