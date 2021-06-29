package inpatient.transaction.dao;

import hisglobal.vo.ExtAdminDtlVO;
import hisglobal.vo.UserVO;

public interface ExtAdminDtlDAOi 
{
	public void save(ExtAdminDtlVO extAdminDtlVO,UserVO userVO);
	public void updateTodayExtDetail(ExtAdminDtlVO _extAdminDtlVO, UserVO _userVO);
	public void updateAllNotExtDetail(ExtAdminDtlVO _extAdminDtlVO, UserVO _userVO);
	public String getExtTreatmentStatus(ExtAdminDtlVO vo,UserVO userVO);
}
