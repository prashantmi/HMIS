package new_investigation.masters.dao;

import java.util.List;


import new_investigation.vo.InvTestSampleMasterVO;
import hisglobal.vo.UserVO;

public interface InvTestSampleMstDAOi
{


	public String checkDuplicateInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public void createInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public void update_insertInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public void fetchCheckListInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public void updateInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public void gettestName(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);
	public String checkDefaultInvTestSample(InvTestSampleMasterVO testsample_VO, UserVO _UserVO);

	public List gettestCombo(UserVO _UserVO);
	public List getsampleCombo(InvTestSampleMasterVO testsample_VO,UserVO _UserVO);
	public List getuomCombo(UserVO _UserVO);
	public List getcontainerCombo(UserVO _UserVO);

}
