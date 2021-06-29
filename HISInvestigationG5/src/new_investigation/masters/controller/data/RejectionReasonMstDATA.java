package new_investigation.masters.controller.data;

 
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.RejectionReasonMasterVO;

import java.util.Map;



public class RejectionReasonMstDATA
{
	public static void saveRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		  masterDelegate.saveRejectionReason(rejectionreasonmaster_VO, _UserVO);
	}

	public static void fetchCheckListRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchCheckListRejectionReason(rejectionreasonmaster_VO, _UserVO);
	}
	
	public static void fetchRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.fetchRejectionReason(rejectionreasonmaster_VO, _UserVO);
	}
	
	public static void savemodifyRejectionReason(RejectionReasonMasterVO rejectionreasonmaster_VO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		masterDelegate.savemodifyRejectionReason(rejectionreasonmaster_VO, _UserVO);
	}
}