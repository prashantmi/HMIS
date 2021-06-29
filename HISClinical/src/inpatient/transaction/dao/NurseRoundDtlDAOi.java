package inpatient.transaction.dao;

import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.UserVO;

public interface NurseRoundDtlDAOi 
{
	public void create(NurseRoundDtlVO nurseRoundDtlVO,UserVO userVO);
	
	public NurseRoundDtlVO[] getPreviousProgressNotes(String patAdmNo,UserVO userVO);
}
