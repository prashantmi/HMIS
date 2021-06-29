package new_investigation.masters.dao;

import java.util.List;

import new_investigation.vo.TestGroupMasterVO;
import hisglobal.vo.UserVO;


public interface LocalTestGroupMstDAOi
{

			
			//START Local Test Group MASTER(add by yogender)//
			
				public String checkDuplicateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				public void createLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				/*public void fetchLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);*/
				public void fetchCheckListLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				public List gettestgroupCombo(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);//
				public String checkDuplicateModifyLocalTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);
				public void updateLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				public void fetchdisplaydataLocalTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				
			//ENDS Local TestGroup Master //
}
