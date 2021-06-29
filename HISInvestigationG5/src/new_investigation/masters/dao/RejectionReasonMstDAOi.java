package new_investigation.masters.dao;

import java.util.List;




import new_investigation.vo.RejectionReasonMasterVO;
import hisglobal.vo.UserVO;


public interface RejectionReasonMstDAOi
{

			
			//START RejectionReason MASTER(add by yogender)//
			
				public String checkDuplicateRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO);
				public void createRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO);
				public void fetchRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO);
				public String checkDuplicateModifyRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO);
				public void updateRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO);
				
			//ENDS RejectionReason Master Master//
}
