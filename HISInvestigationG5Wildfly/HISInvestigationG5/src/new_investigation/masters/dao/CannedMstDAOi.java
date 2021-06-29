package new_investigation.masters.dao;

import java.util.List;





import new_investigation.vo.CannedMasterVO;
import hisglobal.vo.UserVO;


public interface CannedMstDAOi
{

			
			//START Canned MASTER(add by yogender)//
			
				public String checkDuplicateCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO);
				public void createCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO);
				public void fetchCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO);
				public String checkDuplicateModifyCanned(CannedMasterVO cannedmaster_VO,UserVO _UserVO);
				public void updateCanned(CannedMasterVO cannedmaster_VO, UserVO _UserVO);
				
			//ENDS Canned Master //
}
