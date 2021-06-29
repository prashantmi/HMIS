package new_investigation.masters.controller.data;

 
import java.util.Map;

import new_investigation.vo.InvSampleMasterVO;
import hisglobal.vo.UserVO;
import new_investigation.masters.InvestigationMasterDelegate;


public class InvSampleMstDATA
{
	public static void saveCheckList(InvSampleMasterVO bCheckListMasterVO,UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.saveCheckList(bCheckListMasterVO, _UserVO);
	}

	public static Map fetchCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchCheckList(bCheckListMasterVO, _UserVO);
	}
	
	
	public static Map fetchSample(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		return masterDelegate.fetchSample(bCheckListMasterVO, _UserVO);
	}
	
	public static void savemodifyCheckList(InvSampleMasterVO bCheckListMasterVO, UserVO _UserVO)
	{
		InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
		 masterDelegate.savemodifyCheckList(bCheckListMasterVO, _UserVO);
	}
}
