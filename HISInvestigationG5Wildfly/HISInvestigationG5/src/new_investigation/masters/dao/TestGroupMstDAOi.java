package new_investigation.masters.dao;

import java.util.List;



import new_investigation.vo.TestGroupMasterVO;
import hisglobal.vo.UserVO;


public interface TestGroupMstDAOi
{

			
			//START Global TestGroup MASTER(add by yogender)//
			
				public String checkDuplicateTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				public void createTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				public void fetchTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				public String checkDuplicateModifyTestGroup(TestGroupMasterVO testgroupmaster_VO,UserVO _UserVO);
				public void updateTestGroup(TestGroupMasterVO testgroupmaster_VO, UserVO _UserVO);
				
			//ENDS Global TestGroup Master //
}
